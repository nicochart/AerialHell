package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.Client.Event.Listeners.RenderRegistrationListener;
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
    public static void addToBakeDefaultBlockstate(Block block)
    {
        if (block instanceof BasicShadowSpreaderBlock)
        {
            List<Boolean> booleanValues = new ArrayList<>();
            booleanValues.add(true); booleanValues.add(false);

            for (Boolean canSpread : booleanValues)
            {
                BlockState state = block.getDefaultState().with(BasicShadowSpreaderBlock.CAN_SPREAD, canSpread).with(AerialHellGrassBlock.SHIFTED_RENDER, false);
                RenderRegistrationListener.TO_BAKE_LIST.add(state);
            }
        }
        else
        {
            RenderRegistrationListener.TO_BAKE_LIST.add(block.getDefaultState());
        }
    }

    public static void addToBakeAbstractPlantStemBlock(AbstractPlantStemBlock block)
    {
        for (int age = 0; age <= 25; age++)
        {
            BlockState state = block.getDefaultState().with(AbstractPlantStemBlock.AGE, age).with(AerialHellGrassBlock.SHIFTED_RENDER, false);
            RenderRegistrationListener.TO_BAKE_LIST.add(state);
        }
    }

    public static void addToBakeGrassBlock(GrassBlock block)
    {
        RenderRegistrationListener.TO_BAKE_LIST.add(block.getDefaultState());
    }

    public static void addToBakeLeavesBlock(LeavesBlock block)
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
                        state = block.getDefaultState().with(LeavesBlock.DISTANCE, distance).with(LeavesBlock.PERSISTENT, persistent).with(ShiftableLeavesBlock.SHIFTED_RENDER, false).with(LeavesBlock.WATERLOGGED, waterlogged);
                        if (block instanceof ShadowLeavesBlock) {state = state.with(ShadowLeavesBlock.CAN_SPREAD, can_spread);}
                        RenderRegistrationListener.TO_BAKE_LIST.add(state);
                    }
                }
            }
        }
    }

    public static void addToBakeLogBlock(PillarBlock block)
    {
        BlockState state;
        List<Boolean> booleanValues = new ArrayList<>();
        booleanValues.add(true); booleanValues.add(false);
        for (Boolean can_spread : booleanValues)
        {
            for (Direction.Axis axis : Direction.Axis.VALUES)
            {
                state = block.getDefaultState().with(PillarBlock.AXIS, axis);
                if (block instanceof ShadowLogBlock) {state = state.with(ShadowLogBlock.CAN_SPREAD, can_spread);}
                RenderRegistrationListener.TO_BAKE_LIST.add(state);
            }
        }
    }
}
