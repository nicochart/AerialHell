package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.Client.BlockBakedModels.ShiftingBlockBakedModel;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.BlockModels;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;

public class ShiftedRenderDuo
{
    /*private final ModelIdentifier baseModelRL;
    private final ShiftingBlockBakedModel newBakedModel;

    protected ShiftedRenderDuo(Block baseBlock, BlockState shiftedBlockState, ModelModifier.AfterBake.Context context)
    {
        this.baseModelRL = BlockModels.getModelId(baseBlock.getDefaultState()); //warning : will set all state values to default before looking for model. For example, for leaves, default is #distance=7,persistent=false,shifted_render=false,waterlogged=false.. which means the only shifted model is distance=7,persistent=false,..
        ModelIdentifier shiftedModelRL = BlockModels.getModelId(shiftedBlockState);
        BakedModel shiftedModel = context.loader().getBakedModelMap().get(shiftedModelRL);
        this.newBakedModel = new ShiftingBlockBakedModel(MinecraftClient.getInstance().getBlockRenderManager().getModels().getModel(baseBlock.getDefaultState()), shiftedModel, (forceShifted) -> BlocksAndItemsColorHandler.isCurrentPlayerInstanceShadowBind() || forceShifted);
    }

    protected ShiftedRenderDuo(BlockState baseBlockState, BlockState shiftedBlockState, ModelModifier.AfterBake.Context context)
    {
        this.baseModelRL = BlockModels.getModelId(baseBlockState);
        ModelIdentifier shiftedModelRL = BlockModels.getModelId(shiftedBlockState);
        BakedModel shiftedModel = context.loader().getBakedModelMap().get(shiftedModelRL);
        this.newBakedModel = new ShiftingBlockBakedModel(MinecraftClient.getInstance().getBlockRenderManager().getModels().getModel(baseBlockState), shiftedModel, (forceShifted) -> BlocksAndItemsColorHandler.isCurrentPlayerInstanceShadowBind() || forceShifted);
    }

    public ModelIdentifier getBaseModelRL() {return baseModelRL;}
    public BakedModel getNewBakedModel() {return newBakedModel;}

    public boolean isValid() {return this.baseModelRL != null && this.newBakedModel != null && this.newBakedModel.getDefault() != null && this.newBakedModel.getShifted() != null;}
    */
}
