package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.Block.DirtAndVariants.AerialHellGrassBlock;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.ShadowLeavesBlock;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.ShadowLogBlock;
import fr.factionbedrock.aerialhell.Block.ShiftableLeavesBlock;
import fr.factionbedrock.aerialhell.Client.BlockBakedModels.ShiftingBlockBakedModel;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.event.ModelEvent;

import java.util.ArrayList;
import java.util.List;

public class ShiftedModelRenderHelper
{
    public static void createAndRegisterGrassBlockShiftedRender(GrassBlock block, ModelEvent.ModifyBakingResult event)
    {
        ShiftedRenderDuo shiftedRender = new ShiftedRenderDuo(block, block.defaultBlockState().setValue(AerialHellGrassBlock.SNOWY, false).setValue(AerialHellGrassBlock.SHIFTED_RENDER, true), ShiftingBlockBakedModel.CUTOUT_MIPPED, event);
        //replaces the models in the map
        event.getModels().put(shiftedRender.getBaseModelRL(), shiftedRender.getNewBakedModel());
    }

    public static void createAndRegisterLeavesBlockShiftedRender(LeavesBlock block, ModelEvent.ModifyBakingResult event)
    {
        BlockState state;
        List<Boolean> booleanValues = new ArrayList<>();
        booleanValues.add(true); booleanValues.add(false);
        for (Boolean can_spread : booleanValues)
        {
            for (int distance = 1; distance <= 7; distance++)
            {
                for (Boolean persistent : booleanValues)
                {
                    for (Boolean waterlogged : booleanValues)
                    {
                        state = block.defaultBlockState().setValue(LeavesBlock.DISTANCE, distance).setValue(LeavesBlock.PERSISTENT, persistent).setValue(ShiftableLeavesBlock.SHIFTED_RENDER, false).setValue(LeavesBlock.WATERLOGGED, waterlogged);
                        if (block instanceof ShadowLeavesBlock) {state = state.setValue(ShadowLeavesBlock.CAN_SPREAD, can_spread);}
                        ShiftedRenderDuo shiftedRender = new ShiftedRenderDuo(state, block.defaultBlockState().setValue(ShadowLeavesBlock.SHIFTED_RENDER, true), ShiftingBlockBakedModel.CUTOUT, event);
                        //replaces the models in the map
                        event.getModels().put(shiftedRender.getBaseModelRL(), shiftedRender.getNewBakedModel());
                    }
                }
            }
        }
    }

    public static void createAndRegisterLogBlockShiftedRender(RotatedPillarBlock block, ModelEvent.ModifyBakingResult event)
    {
        BlockState state;
        List<Boolean> booleanValues = new ArrayList<>();
        booleanValues.add(true); booleanValues.add(false);
        for (Boolean can_spread : booleanValues)
        {
            for (Direction.Axis axis : Direction.Axis.VALUES)
            {
                state = block.defaultBlockState().setValue(RotatedPillarBlock.AXIS, axis);
                if (block instanceof ShadowLogBlock) {state = state.setValue(ShadowLogBlock.CAN_SPREAD, can_spread);}
                ShiftedRenderDuo shiftedRender = new ShiftedRenderDuo(state, block.defaultBlockState().setValue(ShadowLogBlock.SHIFTED_RENDER, true), ShiftingBlockBakedModel.SOLID, event);
                //replaces the models in the map
                event.getModels().put(shiftedRender.getBaseModelRL(), shiftedRender.getNewBakedModel());
            }
        }
    }
}
