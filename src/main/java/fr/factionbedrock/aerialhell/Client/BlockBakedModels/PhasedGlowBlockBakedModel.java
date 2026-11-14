package fr.factionbedrock.aerialhell.Client.BlockBakedModels;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Client.Texture.PhasedAnimationSpriteSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.dispatch.BlockModelRotation;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ResolvableModel;
import net.minecraft.client.resources.model.ResolvedModel;
import net.minecraft.client.resources.model.SimpleModelWrapper;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.client.resources.model.geometry.QuadCollection;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.client.resources.model.sprite.TextureSlots;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;
import net.neoforged.neoforge.client.model.DynamicBlockStateModel;
import net.neoforged.neoforge.client.model.block.CustomUnbakedBlockStateModel;
import org.jspecify.annotations.Nullable;

import java.util.List;

//client side only blockstate model "aerialhell:phased_glow"
//models are baked once per phase (see PhasedAnimationSpriteSource)
public class PhasedGlowBlockBakedModel implements DynamicBlockStateModel
{
    public static final String GLOW_SLOT = "glow";
    private static final long PHASE_NOISE_SEED = 1234567L; //needs to be the same on every client to get same render
    private static final SimplexNoise PHASE_NOISE = new SimplexNoise(RandomSource.create(PHASE_NOISE_SEED));

    private final BlockStateModelPart[][] partsByModelAndPhase;
    //each block picks its phase with a 3d noise
    private final double noiseScale;
    private final double noiseCycles;
    private final Material.Baked particleMaterial;
    private final @BakedQuad.MaterialFlags int materialFlags;

    public PhasedGlowBlockBakedModel(BlockStateModelPart[][] partsByModelAndPhase, double noiseScale, double noiseCycles)
    {
        this.partsByModelAndPhase = partsByModelAndPhase;
        this.noiseScale = noiseScale;
        this.noiseCycles = noiseCycles;
        this.particleMaterial = partsByModelAndPhase[0][0].particleMaterial();
        int flags = 0;
        for (BlockStateModelPart[] phases : partsByModelAndPhase) {for (BlockStateModelPart part : phases) {flags |= part.materialFlags();}}
        this.materialFlags = flags;
    }

    @Override public void collectParts(BlockAndTintGetter level, BlockPos pos, BlockState state, RandomSource random, List<BlockStateModelPart> output)
    {
        BlockStateModelPart[] phases = this.partsByModelAndPhase[random.nextInt(this.partsByModelAndPhase.length)]; //same pick as a vanilla variant list
        output.add(phases[this.getPhase(pos, phases.length)]);
    }

    //neighbor blocks get close phases, so the light goes from one to the next
    private int getPhase(BlockPos pos, int phases)
    {
        if (phases == 1) {return 0;}
        double noise = PHASE_NOISE.get(pos.getX() / this.noiseScale, pos.getY() / this.noiseScale, pos.getZ() / this.noiseScale); //mostly between -1 and 1
        return Math.floorMod(Mth.floor((noise * 0.5 + 0.5) * this.noiseCycles * phases), phases);
    }

    @Override public Material.Baked particleMaterial() {return this.particleMaterial;}
    @Override public @BakedQuad.MaterialFlags int materialFlags() {return this.materialFlags;}

    public record Unbaked(List<Identifier> models, int phases, float noiseScale, float noiseCycles) implements CustomUnbakedBlockStateModel
    {
        public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                ExtraCodecs.nonEmptyList(Identifier.CODEC.listOf()).fieldOf("models").forGetter(Unbaked::models),
                ExtraCodecs.POSITIVE_INT.fieldOf("phases").forGetter(Unbaked::phases), //same as in atlases/blocks.json
                ExtraCodecs.POSITIVE_FLOAT.fieldOf("noise_scale").forGetter(Unbaked::noiseScale),
                ExtraCodecs.POSITIVE_FLOAT.fieldOf("noise_cycles").forGetter(Unbaked::noiseCycles)
            ).apply(instance, Unbaked::new));

        @Override public MapCodec<Unbaked> codec() {return MAP_CODEC;}

        @Override public void resolveDependencies(ResolvableModel.Resolver resolver) {this.models.forEach(resolver::markDependency);}

        @Override public BlockStateModel bake(ModelBaker baker)
        {
            BlockStateModelPart[][] partsByModelAndPhase = new BlockStateModelPart[this.models.size()][];
            for (int i = 0; i < this.models.size(); i++) {partsByModelAndPhase[i] = this.bakePhases(baker, baker.getModel(this.models.get(i)));}
            return new PhasedGlowBlockBakedModel(partsByModelAndPhase, this.noiseScale, this.noiseCycles);
        }

        private BlockStateModelPart[] bakePhases(ModelBaker baker, ResolvedModel model)
        {
            @Nullable Material glow = model.getTopTextureSlots().getMaterial(GLOW_SLOT);
            if (glow == null) {return new BlockStateModelPart[] {SimpleModelWrapper.bake(baker, model, BlockModelRotation.IDENTITY)};}

            BlockStateModelPart[] parts = new BlockStateModelPart[this.phases];
            for (int phase = 0; phase < this.phases; phase++)
            {
                Material phaseGlow = new Material(PhasedAnimationSpriteSource.phaseSpriteId(glow.sprite(), phase), glow.forceTranslucent());
                TextureSlots textures = withTexture(model, GLOW_SLOT, phaseGlow);
                //not model.bakeTopGeometry : it caches the geometry and would ignore the new glow texture
                QuadCollection geometry = model.getTopGeometry().bake(textures, baker, BlockModelRotation.IDENTITY, model, model.getTopAdditionalProperties());
                parts[phase] = new SimpleModelWrapper(geometry, model.getTopAmbientOcclusion(), ResolvedModel.resolveParticleMaterial(textures, baker, model));
            }
            return parts;
        }

        //textures of the model, with one slot replaced
        private static TextureSlots withTexture(ResolvedModel model, String slot, Material material)
        {
            TextureSlots.Resolver resolver = new TextureSlots.Resolver();
            resolver.addLast(new TextureSlots.Data.Builder().addTexture(slot, material).build());
            for (@Nullable ResolvedModel current = model; current != null; current = current.parent()) {resolver.addLast(current.wrapped().textureSlots());}
            return resolver.resolve(model);
        }
    }
}
