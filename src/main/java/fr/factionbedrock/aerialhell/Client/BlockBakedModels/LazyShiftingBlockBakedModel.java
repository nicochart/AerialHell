package fr.factionbedrock.aerialhell.Client.BlockBakedModels;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.BlockModelPart;
import net.minecraft.client.render.model.BlockStateModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

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

    @Override public List<BlockModelPart> getParts(Random random) {return getModel().getParts(random);}
    @Override public @Nullable Object createGeometryKey(BlockRenderView blockView, BlockPos pos, BlockState state, Random random) {return this;}
    @Override public void addParts(Random random, List<BlockModelPart> parts) {getModel().addParts(random, parts);}
    @Override public Sprite particleSprite() {return getModel().particleSprite();}

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