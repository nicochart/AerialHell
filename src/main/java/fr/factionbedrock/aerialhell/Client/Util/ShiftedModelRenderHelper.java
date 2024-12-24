package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.AerialHell;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier.AfterBake.Context;
import fr.factionbedrock.aerialhell.Block.DirtAndVariants.AerialHellGrassBlock;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.BasicShadowSpreaderBlock;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.ShadowLeavesBlock;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.ShadowLogBlock;
import fr.factionbedrock.aerialhell.Block.ShiftableLeavesBlock;
import net.minecraft.block.*;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

public class ShiftedModelRenderHelper
{
    public static void createAndRegisterDefaultBlockShiftedRender(Block block, Context context)
    {
        boolean registeredAll = true;
        if (block instanceof BasicShadowSpreaderBlock)
        {
            List<Boolean> booleanValues = new ArrayList<>();
            booleanValues.add(true); booleanValues.add(false);

            for (Boolean canSpread : booleanValues)
            {
                BlockState state = block.getDefaultState().with(BasicShadowSpreaderBlock.CAN_SPREAD, canSpread).with(AerialHellGrassBlock.SHIFTED_RENDER, false);
                //replaces the models in the map
                ShiftedRenderDuo shiftedRender = new ShiftedRenderDuo(state, block.getDefaultState().with(AerialHellGrassBlock.SHIFTED_RENDER, true), context);
                if (!shiftedRender.isValid()) {registeredAll = false;}
                else {context.loader().getBakedModelMap().put(shiftedRender.getBaseModelRL(), shiftedRender.getNewBakedModel());}
            }
        }
        else
        {
            ShiftedRenderDuo shiftedRender = new ShiftedRenderDuo(block, block.getDefaultState().with(AerialHellGrassBlock.SHIFTED_RENDER, true), context);
            //replaces the models in the map
            if (!shiftedRender.isValid()) {registeredAll = false;}
            else {context.loader().getBakedModelMap().put(shiftedRender.getBaseModelRL(), shiftedRender.getNewBakedModel());}
        }
        if (!registeredAll) {logRegisterError(block);}
    }

    public static void createAndRegisterAbstractPlantStemBlock(AbstractPlantStemBlock block, Context context)
    {
        boolean registeredAll = true;
        for (int age = 0; age <= 25; age++)
        {
            BlockState state = block.getDefaultState().with(AbstractPlantStemBlock.AGE, age).with(AerialHellGrassBlock.SHIFTED_RENDER, false);
            //replaces the models in the map
            ShiftedRenderDuo shiftedRender = new ShiftedRenderDuo(state, block.getDefaultState().with(AerialHellGrassBlock.SHIFTED_RENDER, true), context);
            if (!shiftedRender.isValid()) {registeredAll = false;}
            else {context.loader().getBakedModelMap().put(shiftedRender.getBaseModelRL(), shiftedRender.getNewBakedModel());}
        }
        if (!registeredAll) {logRegisterError(block);}
    }

    public static void createAndRegisterGrassBlockShiftedRender(GrassBlock block, Context context)
    {
        ShiftedRenderDuo shiftedRender = new ShiftedRenderDuo(block, block.getDefaultState().with(AerialHellGrassBlock.SNOWY, false).with(AerialHellGrassBlock.SHIFTED_RENDER, true), context);
        //replaces the models in the map
        if (!shiftedRender.isValid()) {logRegisterError(block);}
        else {context.loader().getBakedModelMap().put(shiftedRender.getBaseModelRL(), shiftedRender.getNewBakedModel());}
    }

    public static void createAndRegisterLeavesBlockShiftedRender(LeavesBlock block, Context context)
    {
        boolean registeredAll = true;
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
                        state = block.getDefaultState().with(LeavesBlock.DISTANCE, distance).with(LeavesBlock.PERSISTENT, persistent).with(ShiftableLeavesBlock.SHIFTED_RENDER, false).with(LeavesBlock.WATERLOGGED, waterlogged);
                        if (block instanceof ShadowLeavesBlock) {state = state.with(ShadowLeavesBlock.CAN_SPREAD, can_spread);}
                        ShiftedRenderDuo shiftedRender = new ShiftedRenderDuo(state, block.getDefaultState().with(ShadowLeavesBlock.SHIFTED_RENDER, true), context);
                        //replaces the models in the map
                        if (!shiftedRender.isValid()) {registeredAll = false;}
                        else {context.loader().getBakedModelMap().put(shiftedRender.getBaseModelRL(), shiftedRender.getNewBakedModel());}
                    }
                }
            }
        }
        if (!registeredAll) {logRegisterError(block);}
    }

    public static void createAndRegisterLogBlockShiftedRender(PillarBlock block, Context context)
    {
        boolean registeredAll = true;
        BlockState state;
        List<Boolean> booleanValues = new ArrayList<>();
        booleanValues.add(true); booleanValues.add(false);
        for (Boolean can_spread : booleanValues)
        {
            for (Direction.Axis axis : Direction.Axis.VALUES)
            {
                state = block.getDefaultState().with(PillarBlock.AXIS, axis);
                if (block instanceof ShadowLogBlock) {state = state.with(ShadowLogBlock.CAN_SPREAD, can_spread);}
                ShiftedRenderDuo shiftedRender = new ShiftedRenderDuo(state, block.getDefaultState().with(ShadowLogBlock.SHIFTED_RENDER, true).with(PillarBlock.AXIS, axis), context);
                //replaces the models in the map
                if (!shiftedRender.isValid()) {registeredAll = false;}
                else {context.loader().getBakedModelMap().put(shiftedRender.getBaseModelRL(), shiftedRender.getNewBakedModel());}
            }
        }
        if (!registeredAll) {logRegisterError(block);}
    }

    public static void logRegisterError(Block block)
    {
        AerialHell.LOGGER.error("Can't register all variants of shifted baked model for {}", block.getTranslationKey());
    }
}
