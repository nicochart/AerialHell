package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.Client.Event.Listeners.RenderRegistrationListener;
import fr.factionbedrock.aerialhell.Block.DirtAndVariants.AerialHellGrassBlock;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.BasicShadowSpreaderBlock;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.ShadowLeavesBlock;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.ShadowLogBlock;
import fr.factionbedrock.aerialhell.Block.ShiftableLeavesBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
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
                BlockState state = block.defaultBlockState().setValue(BasicShadowSpreaderBlock.CAN_SPREAD, canSpread).setValue(AerialHellGrassBlock.SHIFTED_RENDER, false);
                RenderRegistrationListener.TO_BAKE_LIST.add(state);
            }
        }
        else
        {
            RenderRegistrationListener.TO_BAKE_LIST.add(block.defaultBlockState());
        }
    }

    public static void addToBakeAbstractPlantStemBlock(GrowingPlantHeadBlock block)
    {
        for (int age = 0; age <= 25; age++)
        {
            BlockState state = block.defaultBlockState().setValue(GrowingPlantHeadBlock.AGE, age).setValue(AerialHellGrassBlock.SHIFTED_RENDER, false);
            RenderRegistrationListener.TO_BAKE_LIST.add(state);
        }
    }

    public static void addToBakeGrassBlock(GrassBlock block)
    {
        RenderRegistrationListener.TO_BAKE_LIST.add(block.defaultBlockState());
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
                        state = block.defaultBlockState().setValue(LeavesBlock.DISTANCE, distance).setValue(LeavesBlock.PERSISTENT, persistent).setValue(ShiftableLeavesBlock.SHIFTED_RENDER, false).setValue(LeavesBlock.WATERLOGGED, waterlogged);
                        if (block instanceof ShadowLeavesBlock) {state = state.setValue(ShadowLeavesBlock.CAN_SPREAD, can_spread);}
                        RenderRegistrationListener.TO_BAKE_LIST.add(state);
                    }
                }
            }
        }
    }

    public static void addToBakeLogBlock(RotatedPillarBlock block)
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
                RenderRegistrationListener.TO_BAKE_LIST.add(state);
            }
        }
    }
}
