package fr.factionbedrock.aerialhell.Client.BlockBakedModels;

import java.util.List;
import java.util.function.Function;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

public class ShiftingBlockBakedModel implements BakedModel
{
    private final BakedModel defaultModel;
    private final BakedModel shiftedModel;
    private final Function<Boolean, Boolean> shouldDisplayShiftedModel;

    public ShiftingBlockBakedModel(BakedModel defaultModel, BakedModel shiftedModel, Function<Boolean, Boolean> shouldDisplayShiftedModel)
    {
        this.defaultModel = defaultModel;
        this.shiftedModel = shiftedModel;
        this.shouldDisplayShiftedModel = shouldDisplayShiftedModel;
    }

    @Override public List<BakedQuad> getQuads(BlockState state, Direction side, RandomSource rand) {return getModel().getQuads(state, side, rand);}
    @Override public boolean useAmbientOcclusion() {return getModel().useAmbientOcclusion();}
    @Override public boolean isGui3d() {return getModel().isGui3d();}
    @Override public boolean usesBlockLight() {return getModel().usesBlockLight();}
    @Override public boolean isCustomRenderer() {return getModel().isCustomRenderer();}
    @Override public TextureAtlasSprite getParticleIcon() {return getModel().getParticleIcon();}
    @Override public ItemTransforms getTransforms() {return getModel().getTransforms();}
    @Override public ItemOverrides getOverrides() {return getModel().getOverrides();}

    private BakedModel getModel()
    {
        return shouldDisplayShiftedModel(false) ? shiftedModel : defaultModel;
    }

    protected boolean shouldDisplayShiftedModel(boolean forceDefault) {return this.shouldDisplayShiftedModel.apply(forceDefault);}
    public BakedModel getDefault() {return this.defaultModel;}
    public BakedModel getShifted() {return this.shiftedModel;}
}