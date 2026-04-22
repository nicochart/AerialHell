package fr.factionbedrock.aerialhell.Client.BlockBakedModels;

import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.Function;

public class ShiftingBlockBakedModel implements BlockStateModel
{
    private final BlockStateModel defaultModel;
    private final BlockStateModel shiftedModel;
    private final Function<Boolean, Boolean> shouldDisplayShiftedModel;

    public ShiftingBlockBakedModel(BlockStateModel defaultModel, BlockStateModel shiftedModel, Function<Boolean, Boolean> shouldDisplayShiftedModel)
    {
        this.defaultModel = defaultModel;
        this.shiftedModel = shiftedModel;
        this.shouldDisplayShiftedModel = shouldDisplayShiftedModel;
    }

    @Override public void collectParts(RandomSource randomSource, List<BlockStateModelPart> list) {this.getModel().collectParts(randomSource, list);}
    @Override public Material.Baked particleMaterial() {return this.getModel().particleMaterial();}
    @Override public @BakedQuad.MaterialFlags int materialFlags() {return this.getModel().materialFlags();}

    private BlockStateModel getModel()
    {
        return shouldDisplayShiftedModel(false) ? shiftedModel : defaultModel;
    }

    protected boolean shouldDisplayShiftedModel(boolean forceDefault) {return this.shouldDisplayShiftedModel.apply(forceDefault);}
}