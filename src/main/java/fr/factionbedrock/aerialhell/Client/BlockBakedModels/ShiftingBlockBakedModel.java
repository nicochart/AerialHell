package fr.factionbedrock.aerialhell.Client.BlockBakedModels;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

import java.util.List;
import java.util.function.Function;

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

    @Override public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand) {return getModel().getQuads(state, side, rand);}
    @Override public boolean useAmbientOcclusion() {return getModel().useAmbientOcclusion();}
    @Override public boolean hasDepth() {return getModel().hasDepth();}
    @Override public boolean isSideLit() {return getModel().isSideLit();}
    @Override public boolean isBuiltin() {return getModel().isBuiltin();}
    @Override public Sprite getParticleSprite() {return getModel().getParticleSprite();}
    @Override public ModelTransformation getTransformation() {return getModel().getTransformation();}
    @Override public ModelOverrideList getOverrides() {return getModel().getOverrides();}

    private BakedModel getModel()
    {
        return shouldDisplayShiftedModel(false) ? shiftedModel : defaultModel;
    }

    protected boolean shouldDisplayShiftedModel(boolean forceDefault) {return this.shouldDisplayShiftedModel.apply(forceDefault);}
    public BakedModel getDefault() {return this.defaultModel;}
    public BakedModel getShifted() {return this.shiftedModel;}
}