package fr.factionbedrock.aerialhell.Client.BlockBakedModels;

import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.client.resources.model.sprite.Material;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.util.RandomSource;

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

    @Override public void collectParts(RandomSource randomSource, List<BlockStateModelPart> list) {this.getModel().collectParts(randomSource, list);}
    @Override public Material.Baked particleMaterial() {return this.getModel().particleMaterial();}
    @Override public @BakedQuad.MaterialFlags int materialFlags() {return this.getModel().materialFlags();}

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