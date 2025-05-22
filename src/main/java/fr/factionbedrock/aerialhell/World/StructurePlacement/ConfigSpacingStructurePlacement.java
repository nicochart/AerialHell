package fr.factionbedrock.aerialhell.World.StructurePlacement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;

import java.util.Optional;

//copy of net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
public class ConfigSpacingStructurePlacement extends StructurePlacement
{
    public static final MapCodec<ConfigSpacingStructurePlacement> CODEC = RecordCodecBuilder.<ConfigSpacingStructurePlacement>mapCodec(
                    configPlacementInstance -> placementCodec(configPlacementInstance)
                            .and(
                                    configPlacementInstance.group(
                                            Codec.intRange(0, 4096).fieldOf("spacing").forGetter(ConfigSpacingStructurePlacement::spacing),
                                            Codec.intRange(0, 4096).fieldOf("separation").forGetter(ConfigSpacingStructurePlacement::separation),
                                            RandomSpreadType.CODEC
                                                    .optionalFieldOf("spread_type", RandomSpreadType.LINEAR)
                                                    .forGetter(ConfigSpacingStructurePlacement::spreadType)
                                    )
                            )
                            .apply(configPlacementInstance, ConfigSpacingStructurePlacement::new)
            )
            .validate(ConfigSpacingStructurePlacement::validate);
    private final int spacing;
    private final int separation;
    private final RandomSpreadType spreadType;

    private static DataResult<ConfigSpacingStructurePlacement> validate(ConfigSpacingStructurePlacement placement) {return placement.spacing <= placement.separation ? DataResult.error(() -> "Spacing has to be larger than separation") : DataResult.success(placement);}

    public ConfigSpacingStructurePlacement(Vec3i locateOffset, StructurePlacement.FrequencyReductionMethod frequencyReductionMethod, float frequency, int salt, Optional<ExclusionZone> exclusionZone, int spacing, int separation, RandomSpreadType spreadType)
    {
        super(locateOffset, frequencyReductionMethod, frequency, salt, exclusionZone);
        this.spacing = spacing;
        this.separation = separation;
        this.spreadType = spreadType;
    }

    public ConfigSpacingStructurePlacement(int spacing, int separation, RandomSpreadType spreadType, int salt)
    {
        this(Vec3i.ZERO, StructurePlacement.FrequencyReductionMethod.DEFAULT, 1.0F, salt, Optional.empty(), spacing, separation, spreadType);
    }

    public int spacing() {return this.spacing;}
    public int separation() {return LoadedConfigParams.OVERWORLD_ABANDONNED_PORTAL_SPACING_OVERRIDE;}

    public RandomSpreadType spreadType() {return this.spreadType;}

    public ChunkPos getPotentialStructureChunk(long seed, int regionX, int regionZ)
    {
        int i = Math.floorDiv(regionX, this.spacing);
        int j = Math.floorDiv(regionZ, this.spacing);
        WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(0L));
        worldgenrandom.setLargeFeatureWithSalt(seed, i, j, this.salt());
        int k = this.spacing - this.separation;
        int l = this.spreadType.evaluate(worldgenrandom, k);
        int i1 = this.spreadType.evaluate(worldgenrandom, k);
        return new ChunkPos(i * this.spacing + l, j * this.spacing + i1);
    }

    @Override protected boolean isPlacementChunk(ChunkGeneratorStructureState p_256267_, int p_256050_, int p_255975_)
    {
        ChunkPos chunkpos = this.getPotentialStructureChunk(p_256267_.getLevelSeed(), p_256050_, p_255975_);
        return chunkpos.x == p_256050_ && chunkpos.z == p_255975_;
    }

    @Override public StructurePlacementType<?> type() {return StructurePlacementType.RANDOM_SPREAD;}
}
