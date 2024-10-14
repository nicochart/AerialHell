package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.ShadowLeavesBlock;
import fr.factionbedrock.aerialhell.Client.BlockBakedModels.ShiftingBlockBakedModel;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.ChunkRenderTypeSet;
import net.neoforged.neoforge.client.event.ModelEvent;

public class ShiftedRenderDuo
{
    private final ModelResourceLocation baseModelRL;
    private final BakedModel newBakedModel;

    protected ShiftedRenderDuo(Block baseBlock, BlockState shiftedBlockState, ChunkRenderTypeSet renderType, ModelEvent.ModifyBakingResult event)
    {
        this.baseModelRL = BlockModelShaper.stateToModelLocation(baseBlock.defaultBlockState()); //warning : will set all state values to default before looking for model. For example, for leaves, default is #distance=7,persistent=false,shifted_render=false,waterlogged=false.. which means the only shifted model is distance=7,persistent=false,..
        ModelResourceLocation shiftedModelRL = BlockModelShaper.stateToModelLocation(shiftedBlockState);
        BakedModel shiftedModel = event.getModels().get(shiftedModelRL);
        this.newBakedModel = new ShiftingBlockBakedModel(event.getModels().get(baseModelRL), shiftedModel, (forceShifted) -> EntityHelper.isCurrentPlayerInstanceShadowBind() || forceShifted, renderType);
    }

    protected ShiftedRenderDuo(Block baseBlock, BlockState shiftedBlockState, String state, ChunkRenderTypeSet renderType, ModelEvent.ModifyBakingResult event)
    {
        this.baseModelRL = new ModelResourceLocation(ResourceLocation.parse(BuiltInRegistries.BLOCK.wrapAsHolder(baseBlock).getRegisteredName()), state);
        ModelResourceLocation shiftedModelRL = BlockModelShaper.stateToModelLocation(shiftedBlockState);
        BakedModel shiftedModel = event.getModels().get(shiftedModelRL);
        this.newBakedModel = new ShiftingBlockBakedModel(event.getModels().get(baseModelRL), shiftedModel, (forceShifted) -> EntityHelper.isCurrentPlayerInstanceShadowBind() || forceShifted, renderType);
    }

    public ModelResourceLocation getBaseModelRL() {return baseModelRL;}
    public BakedModel getNewBakedModel() {return newBakedModel;}
}
