package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.BlockBakedModels.ShiftingBlockBakedModel;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.RenderRegistrationListener;
import fr.factionbedrock.aerialhell.Block.DirtAndVariants.AerialHellGrassBlock;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.BasicShadowSpreaderBlock;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.ShadowLeavesBlock;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.ShadowLogBlock;
import fr.factionbedrock.aerialhell.Block.ShiftableLeavesBlock;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.minecraft.block.*;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.Baker;
import net.minecraft.client.render.model.ModelRotation;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

public class ShiftedModelRenderHelper
{
    public static boolean contextMatch(ModelModifier.AfterBake.Context context, RegistryKey<Block> key, String postVariantString)
    {
        return contextMatch(context, "aerialhell:block/"+key.getValue().getPath()+"_"+postVariantString);
    }

    public static boolean contextMatch(ModelModifier.AfterBake.Context context, RegistryKey<Block> key)
    {
        return contextMatch(context, "aerialhell:block/"+key.getValue().getPath());
    }

    public static boolean contextMatch(ModelModifier.AfterBake.Context context, String modelString)
    {
        return context.id().toString().equals(modelString) && !context.baker().getModelNameSupplier().get().contains("shifted_render=true");
    }

    public static BakedModel getDefaultShiftingModel(BakedModel original, RegistryKey<Block> key, Baker baker)
    {
        BakedModel shiftedModel = baker.bake(AerialHell.id("block/shifted_"+key.getValue().getPath()), ModelRotation.X0_Y0);
        BakedModel editedModel = new ShiftingBlockBakedModel(original, shiftedModel, (forceShifted) -> BlocksAndItemsColorHandler.isCurrentPlayerInstanceShadowBind() || forceShifted);
        return editedModel;
    }

    public static BakedModel getDefaultShiftingModel(BakedModel original, RegistryKey<Block> key, Baker baker, String variant)
    {
        BakedModel shiftedModel = baker.bake(AerialHell.id("block/shifted_"+key.getValue().getPath()+"_"+variant), ModelRotation.X0_Y0);
        BakedModel editedModel = new ShiftingBlockBakedModel(original, shiftedModel, (forceShifted) -> BlocksAndItemsColorHandler.isCurrentPlayerInstanceShadowBind() || forceShifted);
        return editedModel;
    }

    public static BakedModel getCustomRotationDefaultShiftingModel(BakedModel original, RegistryKey<Block> key, Baker baker, String variant, ModelRotationList<ModelRotation> rotationList)
    {
        BakedModel shiftedModel = baker.bake(AerialHell.id("block/shifted_"+key.getValue().getPath()+"_"+variant), rotationList.getNext());
        BakedModel editedModel = new ShiftingBlockBakedModel(original, shiftedModel, (forceShifted) -> BlocksAndItemsColorHandler.isCurrentPlayerInstanceShadowBind() || forceShifted);
        return editedModel;
    }

    public static BakedModel getCustomRotationDefaultShiftingModel(BakedModel original, RegistryKey<Block> key, Baker baker, ModelRotationList<ModelRotation> rotationList)
    {
        BakedModel shiftedModel = baker.bake(AerialHell.id("block/shifted_"+key.getValue().getPath()), rotationList.getNext());
        BakedModel editedModel = new ShiftingBlockBakedModel(original, shiftedModel, (forceShifted) -> BlocksAndItemsColorHandler.isCurrentPlayerInstanceShadowBind() || forceShifted);
        return editedModel;
    }

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
