package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.Client.BlockBakedModels.ShiftingBlockBakedModel;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class ShiftedRenderDuo
{
    private final ModelResourceLocation baseModelRL;
    private final ShiftingBlockBakedModel newBakedModel;

    protected ShiftedRenderDuo(Block baseBlock, BlockState shiftedBlockState, ModelModifier.AfterBake.Context context)
    {
        this.baseModelRL = BlockModelShaper.stateToModelLocation(baseBlock.defaultBlockState()); //warning : will set all state values to default before looking for model. For example, for leaves, default is #distance=7,persistent=false,shifted_render=false,waterlogged=false.. which means the only shifted model is distance=7,persistent=false,..
        ModelResourceLocation shiftedModelRL = BlockModelShaper.stateToModelLocation(shiftedBlockState);
        BakedModel shiftedModel = context.loader().getBakedTopLevelModels().get(shiftedModelRL);
        this.newBakedModel = new ShiftingBlockBakedModel(Minecraft.getInstance().getBlockRenderer().getBlockModelShaper().getBlockModel(baseBlock.defaultBlockState()), shiftedModel, (forceShifted) -> BlocksAndItemsColorHandler.isCurrentPlayerInstanceShadowBind() || forceShifted);
    }

    protected ShiftedRenderDuo(BlockState baseBlockState, BlockState shiftedBlockState, ModelModifier.AfterBake.Context context)
    {
        this.baseModelRL = BlockModelShaper.stateToModelLocation(baseBlockState);
        ModelResourceLocation shiftedModelRL = BlockModelShaper.stateToModelLocation(shiftedBlockState);
        BakedModel shiftedModel = context.loader().getBakedTopLevelModels().get(shiftedModelRL);
        this.newBakedModel = new ShiftingBlockBakedModel(Minecraft.getInstance().getBlockRenderer().getBlockModelShaper().getBlockModel(baseBlockState), shiftedModel, (forceShifted) -> BlocksAndItemsColorHandler.isCurrentPlayerInstanceShadowBind() || forceShifted);
    }

    public ModelResourceLocation getBaseModelRL() {return baseModelRL;}
    public BakedModel getNewBakedModel() {return newBakedModel;}

    public boolean isValid() {return this.baseModelRL != null && this.newBakedModel != null && this.newBakedModel.getDefault() != null && this.newBakedModel.getShifted() != null;}
}
