package fr.factionbedrock.aerialhell.Client.BlockBakedModels;

import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
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

    @Override public List<BlockModelPart> collectParts(RandomSource random) {return getModel().collectParts(random);}
    @Override public Object createGeometryKey(BlockAndTintGetter level, BlockPos pos, BlockState state, RandomSource random) {return this;}
    @Override public void collectParts(RandomSource randomSource, List<BlockModelPart> list) {getModel().collectParts(randomSource, list);}
    @Override public TextureAtlasSprite particleIcon() {return getModel().particleIcon();}

    private BlockStateModel getModel()
    {
        return shouldDisplayShiftedModel(false) ? shiftedModel : defaultModel;
    }

    protected boolean shouldDisplayShiftedModel(boolean forceDefault) {return this.shouldDisplayShiftedModel.apply(forceDefault);}
}