package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.Block.DirtAndVariants.AerialHellGrassBlock;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.BasicShadowSpreaderBlock;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.ShadowLeavesBlock;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.ShadowLogBlock;
import fr.factionbedrock.aerialhell.Block.ShiftableLeavesBlock;
import fr.factionbedrock.aerialhell.Client.BlockBakedModels.ShiftingBlockBakedModel;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import net.minecraft.client.renderer.block.model.BlockModelPart;
import net.minecraft.client.renderer.block.model.BlockStateModel;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.event.ModelEvent;

import java.util.ArrayList;
import java.util.List;

public class ShiftedModelRenderHelper
{
    public static void createAndRegisterShiftedRender(BlockState baseState, BlockState shiftedState, ModelEvent.ModifyBakingResult event)
    {
        //TODO do for all blockstates variants
        BlockStateModel baseModel = event.getBakingResult().blockStateModels().get(baseState);
        BlockStateModel shiftedModel = event.getBakingResult().blockStateModels().get(shiftedState);

        BlockStateModel shiftingModel = new ShiftingBlockBakedModel(baseModel, shiftedModel, (forceShifted) -> BlocksAndItemsColorHandler.isShadowBindEnabled() || forceShifted);
        //replaces the models in the map
        event.getBakingResult().blockStateModels().put(baseState, shiftingModel);
    }

    public static void createAndRegisterDefaultBlockShiftedRender(Block block, ModelEvent.ModifyBakingResult event)
    {
        if (block instanceof BasicShadowSpreaderBlock)
        {
            List<Boolean> booleanValues = new ArrayList<>();
            booleanValues.add(true); booleanValues.add(false);

            BlockState baseState, shiftedState;
            for (Boolean canSpread : booleanValues)
            {
                baseState = block.defaultBlockState().setValue(BasicShadowSpreaderBlock.CAN_SPREAD, canSpread).setValue(AerialHellGrassBlock.SHIFTED_RENDER, false);
                shiftedState = block.defaultBlockState().setValue(BasicShadowSpreaderBlock.CAN_SPREAD, canSpread).setValue(AerialHellGrassBlock.SHIFTED_RENDER, true);
                createAndRegisterShiftedRender(baseState, shiftedState, event);
            }
        }
        else
        {
            BlockState baseState = block.defaultBlockState().setValue(AerialHellGrassBlock.SHIFTED_RENDER, false);
            BlockState shiftedState = block.defaultBlockState().setValue(AerialHellGrassBlock.SHIFTED_RENDER, true);
            createAndRegisterShiftedRender(baseState, shiftedState, event);
        }
    }

    public static void createAndRegisterGrowingPlantHeadBlock(GrowingPlantHeadBlock block, ModelEvent.ModifyBakingResult event)
    {
        BlockState baseState, shiftedState;
        for (int age = 0; age <= 25; age++)
        {
            baseState = block.defaultBlockState().setValue(GrowingPlantHeadBlock.AGE, age).setValue(AerialHellGrassBlock.SHIFTED_RENDER, false);
            shiftedState = block.defaultBlockState().setValue(GrowingPlantHeadBlock.AGE, age).setValue(AerialHellGrassBlock.SHIFTED_RENDER, true);
            createAndRegisterShiftedRender(baseState, shiftedState, event);
        }
    }

    public static void createAndRegisterGrassBlockShiftedRender(GrassBlock block, ModelEvent.ModifyBakingResult event)
    {
        BlockState baseState = block.defaultBlockState().setValue(AerialHellGrassBlock.SNOWY, false).setValue(AerialHellGrassBlock.SHIFTED_RENDER, false);
        BlockState shiftedState = block.defaultBlockState().setValue(AerialHellGrassBlock.SNOWY, false).setValue(AerialHellGrassBlock.SHIFTED_RENDER, true);
        createAndRegisterShiftedRender(baseState, shiftedState, event);
    }

    public static void createAndRegisterLeavesBlockShiftedRender(LeavesBlock block, ModelEvent.ModifyBakingResult event)
    {
        BlockState baseState, shiftedState;
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
                        baseState = block.defaultBlockState().setValue(LeavesBlock.DISTANCE, distance).setValue(LeavesBlock.PERSISTENT, persistent).setValue(ShiftableLeavesBlock.SHIFTED_RENDER, false).setValue(LeavesBlock.WATERLOGGED, waterlogged);
                        shiftedState = block.defaultBlockState().setValue(LeavesBlock.DISTANCE, distance).setValue(LeavesBlock.PERSISTENT, persistent).setValue(ShiftableLeavesBlock.SHIFTED_RENDER, true).setValue(LeavesBlock.WATERLOGGED, waterlogged);
                        if (block instanceof ShadowLeavesBlock) {baseState = baseState.setValue(ShadowLeavesBlock.CAN_SPREAD, can_spread); shiftedState = shiftedState.setValue(ShadowLeavesBlock.CAN_SPREAD, can_spread);}
                        createAndRegisterShiftedRender(baseState, shiftedState, event);
                    }
                }
            }
        }
    }

    public static void createAndRegisterLogBlockShiftedRender(RotatedPillarBlock block, ModelEvent.ModifyBakingResult event)
    {
        BlockState baseState, shiftedState;
        List<Boolean> booleanValues = new ArrayList<>();
        booleanValues.add(true); booleanValues.add(false);
        for (Boolean can_spread : booleanValues)
        {
            for (Direction.Axis axis : Direction.Axis.VALUES)
            {
                baseState = block.defaultBlockState().setValue(RotatedPillarBlock.AXIS, axis).setValue(ShadowLogBlock.SHIFTED_RENDER, false);
                shiftedState = block.defaultBlockState().setValue(RotatedPillarBlock.AXIS, axis).setValue(ShadowLogBlock.SHIFTED_RENDER, true);
                if (block instanceof ShadowLogBlock) {baseState = baseState.setValue(ShadowLogBlock.CAN_SPREAD, can_spread); shiftedState = shiftedState.setValue(ShadowLogBlock.CAN_SPREAD, can_spread);}
                createAndRegisterShiftedRender(baseState, shiftedState, event);
            }
        }
    }
}
