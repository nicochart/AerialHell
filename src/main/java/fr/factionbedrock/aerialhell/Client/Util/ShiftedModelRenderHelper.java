package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.Block.DirtAndVariants.AerialHellGrassBlock;
import fr.factionbedrock.aerialhell.Client.BlockBakedModels.LazyShiftingBlockBakedModel;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import fr.factionbedrock.aerialhell.Registry.AerialHellBooleanProperties;
import net.minecraft.block.*;
import net.minecraft.client.render.model.BlockStateModel;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ShiftedModelRenderHelper
{
    //map filled during model bake with original BlockStates with their associated original BlockStateModels
    public static Map<BlockState, BlockStateModel> ORIGINAL_MODEL_MAP = new HashMap<>();

    //actually returns the shifting model only if the originalState has SHIFTED_RENDER property set to false
    //else, returns original model
    public static BlockStateModel createAndRegisterDefaultShiftedRender(BlockStateModel originalModel, BlockState originalState)
    {
        boolean isShiftedRenderState = originalState.get(AerialHellBooleanProperties.SHIFTED_RENDER);
        if (isShiftedRenderState) //will need the original (* shifted *) model in the map for the shifting model lazy access. then return original
        {
            ShiftedModelRenderHelper.ORIGINAL_MODEL_MAP.put(originalState, originalModel); return originalModel;
        }
        else //create and return shifting model with lazy access to shifted model
        {
            return ShiftedModelRenderHelper.createShiftingModel(originalModel, originalState.with(AerialHellGrassBlock.SHIFTED_RENDER, true));
        }
    }

    public static BlockStateModel createShiftingModel(BlockStateModel originalModel, BlockState shiftedState)
    {
        Supplier<BlockStateModel> shiftedModel = () -> ORIGINAL_MODEL_MAP.get(shiftedState);
        return new LazyShiftingBlockBakedModel(() -> originalModel, shiftedModel, (forceShifted) -> BlocksAndItemsColorHandler.isShadowBindEnabled() || forceShifted);
    }
}
