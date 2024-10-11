package fr.factionbedrock.aerialhell.Client.BlockBakedModels;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.ChunkRenderTypeSet;
import net.neoforged.neoforge.client.model.data.ModelData;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ShiftingGrassBlockBakedModel implements BakedModel
{
    //copy of net.minecraft.client.renderer.ItemBlockRenderTypes private final CUTOUT_MIPPED
    private static final ChunkRenderTypeSet CUTOUT_MIPPED = ChunkRenderTypeSet.of(RenderType.cutoutMipped());
    private final BakedModel defaultModel;
    private final Supplier<Block> shiftedBlock;
    private final Function<Boolean, Boolean> shouldDisplayShiftedModel;

    public ShiftingGrassBlockBakedModel(BakedModel defaultModel, Supplier<Block> shiftedBlock, Function<Boolean, Boolean> shouldDisplayShiftedModel)
    {
        this.defaultModel = defaultModel;
        this.shiftedBlock = shiftedBlock;
        this.shouldDisplayShiftedModel = shouldDisplayShiftedModel;
    }

    @Override public List<BakedQuad> getQuads(BlockState state, Direction side, RandomSource rand)
    {
        if (shouldDisplayShiftedModel(false))
        {
            BlockState shiftedState = getShiftedDefaultState();
            BakedModel shiftedModel = Minecraft.getInstance().getBlockRenderer().getBlockModel(shiftedState);
            return shiftedModel.getQuads(shiftedState, side, rand);
        }
        else
        {
            return defaultModel.getQuads(state, side, rand);
        }
    }

    @Override public ChunkRenderTypeSet getRenderTypes(BlockState state, RandomSource rand, ModelData data) {return CUTOUT_MIPPED;}
    @Override public boolean useAmbientOcclusion() {return getModel().useAmbientOcclusion();}
    @Override public boolean isGui3d() {return getModel().isGui3d();}
    @Override public boolean usesBlockLight() {return getModel().usesBlockLight();}
    @Override public boolean isCustomRenderer() {return getModel().isCustomRenderer();}
    @Override public TextureAtlasSprite getParticleIcon() {return getModel().getParticleIcon();}
    @Override public ItemTransforms getTransforms() {return getModel().getTransforms();}
    @Override public ItemOverrides getOverrides() {return getModel().getOverrides();}

    private BakedModel getModel()
    {
        return shouldDisplayShiftedModel(false) ? getShiftedDefaultModel() : defaultModel;
    }

    protected boolean shouldDisplayShiftedModel(boolean forceDefault) {return this.shouldDisplayShiftedModel.apply(forceDefault);}

    private BakedModel getShiftedDefaultModel() {return getShiftedModelFromState(getShiftedDefaultState());}
    private BakedModel getShiftedModelFromState(BlockState state) {return Minecraft.getInstance().getBlockRenderer().getBlockModel(state);}

    protected BlockState getShiftedDefaultState() {return shiftedBlock.get().defaultBlockState();}
}