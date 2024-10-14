package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Block.DirtAndVariants.AerialHellGrassBlock;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.ShadowLeavesBlock;
import fr.factionbedrock.aerialhell.Block.ShiftableLeavesBlock;
import fr.factionbedrock.aerialhell.Client.BlockBakedModels.ShiftingBlockBakedModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.neoforged.neoforge.client.event.ModelEvent;

import java.util.ArrayList;
import java.util.List;

public class ShiftedModelRenderHelper
{
    public static void createAndRegisterBothWaysGrassBlockShiftedRender(GrassBlock grassBlock1, GrassBlock grassBlock2, ModelEvent.ModifyBakingResult event)
    {
        createAndRegisterSingleWayGrassBlockShiftedRender(grassBlock1, grassBlock2, event);
        createAndRegisterSingleWayGrassBlockShiftedRender(grassBlock2, grassBlock1, event);
    }

    public static void createAndRegisterSingleWayGrassBlockShiftedRender(GrassBlock baseBlock, GrassBlock shiftedBlock, ModelEvent.ModifyBakingResult event)
    {
        ShiftedRenderDuo shiftedRender = new ShiftedRenderDuo(baseBlock, shiftedBlock.defaultBlockState().setValue(AerialHellGrassBlock.SNOWY, false).setValue(AerialHellGrassBlock.SHIFTED_RENDER, true), ShiftingBlockBakedModel.CUTOUT_MIPPED, event);
        //replaces the models in the map
        event.getModels().put(shiftedRender.getBaseModelRL(), shiftedRender.getNewBakedModel());
    }

    public static void createAndRegisterBothWaysLeavesBlockShiftedRender(LeavesBlock leavesBlock1, LeavesBlock leavesBlock2, ModelEvent.ModifyBakingResult event)
    {
        createAndRegisterSingleWayLeavesBlockShiftedRender(leavesBlock1, leavesBlock2, event);
        createAndRegisterSingleWayLeavesBlockShiftedRender(leavesBlock2, leavesBlock1, event);
    }

    public static void createAndRegisterSingleWayLeavesBlockShiftedRender(LeavesBlock baseBlock, LeavesBlock shiftedBlock, ModelEvent.ModifyBakingResult event)
    {
        List<String> booleanValues = new ArrayList<>();
        booleanValues.add("true"); booleanValues.add("false");
        for (String can_spread : booleanValues)
        {
            for (int distance = 0; distance <= 7; distance++)
            {
                for (String persistent : booleanValues)
                {
                    for (String waterlogged : booleanValues)
                    {
                        String canSpread = baseBlock instanceof ShadowLeavesBlock ? "can_spread="+can_spread+"," : "";
                        String leaves_state = canSpread+"distance=" + distance + ",persistent=" + persistent + ",shifted_render=false,waterlogged=" + waterlogged;
                        ShiftedRenderDuo shiftedRender = new ShiftedRenderDuo(baseBlock, shiftedBlock.defaultBlockState().setValue(ShiftableLeavesBlock.SHIFTED_RENDER, true), leaves_state, ShiftingBlockBakedModel.CUTOUT, event);
                        //replaces the models in the map
                        event.getModels().put(shiftedRender.getBaseModelRL(), shiftedRender.getNewBakedModel());
                    }
                }
            }
        }
    }
}
