package fr.factionbedrock.aerialhell.Client.BlockBakedModels;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

public class LazyShiftingBlockBakedModel implements BlockStateModel
{
    private final Supplier<BlockStateModel> defaultModel;
    private final Supplier<BlockStateModel> shiftedModel;
    private final Function<Boolean, Boolean> shouldDisplayShiftedModel;

    public LazyShiftingBlockBakedModel(Supplier<BlockStateModel> defaultModel, Supplier<BlockStateModel> shiftedModel, Function<Boolean, Boolean> shouldDisplayShiftedModel)
    {
        this.defaultModel = defaultModel;
        this.shiftedModel = shiftedModel;
        this.shouldDisplayShiftedModel = shouldDisplayShiftedModel;
    }

    @Override public List<BlockModelPart> collectParts(RandomSource random) {return getModel().collectParts(random);}
    @Override public @Nullable Object createGeometryKey(BlockAndTintGetter blockView, BlockPos pos, BlockState state, RandomSource random) {return this;}
    @Override public void collectParts(RandomSource random, List<BlockModelPart> parts) {getModel().collectParts(random, parts);}
    @Override public TextureAtlasSprite particleIcon() {return getModel().particleIcon();}

    private BlockStateModel getModel()
    {
        BlockStateModel model = shouldDisplayShiftedModel(false) ? this.getShifted() : this.getDefault();
        if (model == null)
        {
            System.out.println("Trying to get shifted model variant of "+this.getDefault()+" but got null");
        }
        return model;
    }

    protected boolean shouldDisplayShiftedModel(boolean forceDefault) {return this.shouldDisplayShiftedModel.apply(forceDefault);}
    public BlockStateModel getDefault() {return this.defaultModel.get();}
    public BlockStateModel getShifted() {return this.shiftedModel.get();}
}