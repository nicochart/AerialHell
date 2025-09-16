package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.BlockBakedModels.ShiftingBlockBakedModel;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.minecraft.block.*;
import net.minecraft.client.render.model.BakedSimpleModel;
import net.minecraft.client.render.model.Baker;
import net.minecraft.client.render.model.BlockStateModel;
import net.minecraft.client.render.model.ModelRotation;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ShiftedModelRenderHelper
{
    //model variant with default separator : "_"
    public static boolean contextMatch(ModelModifier.AfterBakeBlock.Context context, RegistryKey<Block> key, String postVariantString)
    {
        return contextMatchVariant(context, key, "_"+postVariantString);
    }

    //model variant but the separator must be included in the string
    public static boolean contextMatchVariant(ModelModifier.AfterBakeBlock.Context context, RegistryKey<Block> key, String postVariantStringWithSeparator)
    {
        return contextMatch(context, "aerialhell:block/"+key.getValue().getPath()+postVariantStringWithSeparator);
    }

    public static boolean contextMatch(ModelModifier.AfterBakeBlock.Context context, RegistryKey<Block> key)
    {
        return contextMatch(context, "aerialhell:block/"+key.getValue().getPath());
    }

    public static boolean contextMatch(ModelModifier.AfterBakeBlock.Context context, String modelString)
    {
        return context.state().getBlock().getTranslationKey().equals(modelString) && !context.state().toString().contains("shifted_render=true");
    }

    public static BlockStateModel getDefaultShiftingModel(BlockStateModel original, RegistryKey<Block> key, Baker baker)
    {
        return getShiftingModel(original, key, baker, "", ModelRotation.X0_Y0);
    }

    public static BlockStateModel getDefaultShiftingModel(BlockStateModel original, RegistryKey<Block> key, Baker baker, String postVariantString)
    {
        return getShiftingModel(original, key, baker, "_"+postVariantString, ModelRotation.X0_Y0);
    }

    public static BlockStateModel getDefaultShiftingModelVariant(BlockStateModel original, RegistryKey<Block> key, Baker baker, String postVariantStringWithSeparator)
    {
        return getShiftingModel(original, key, baker, postVariantStringWithSeparator, ModelRotation.X0_Y0);
    }

    public static BlockStateModel getCustomRotationDefaultShiftingModel(BlockStateModel original, RegistryKey<Block> key, Baker baker, String postVariantString, ModelRotationList<ModelRotation> rotationList)
    {
        return getShiftingModel(original, key, baker, "_"+postVariantString, rotationList.getNext());
    }

    public static BlockStateModel getCustomRotationDefaultShiftingModel(BlockStateModel original, RegistryKey<Block> key, Baker baker, ModelRotationList<ModelRotation> rotationList)
    {
        return getShiftingModel(original, key, baker, "", rotationList.getNext());
    }

    public static BlockStateModel getShiftingModel(BlockStateModel original, RegistryKey<Block> key, Baker baker, String postVariantStringWithSeparator, ModelRotation rotation)
    {
        return getShiftingModel("shifted", original, key, baker, postVariantStringWithSeparator, rotation);
    }

    public static BlockStateModel getShiftingModel(String shiftedModelPrefix, BlockStateModel original, RegistryKey<Block> key, Baker baker, String postVariantStringWithSeparator, ModelRotation rotation)
    {
        BlockStateModel shiftedModel = getShiftedBlockStateModelFromId(AerialHell.id("block/"+shiftedModelPrefix+"_"+key.getValue().getPath()+postVariantStringWithSeparator), baker); //TODO rotation ??
        BlockStateModel editedModel = new ShiftingBlockBakedModel(original, shiftedModel, (forceShifted) -> BlocksAndItemsColorHandler.isShadowBindEnabled() || forceShifted);
        return editedModel;
    }

    public static BlockStateModel getShiftedBlockStateModelFromId(Identifier modelId, Baker baker)
    {
        //TODO does it work ?
        BakedSimpleModel shiftedSimpleModel = baker.getModel(modelId);
        assert shiftedSimpleModel instanceof BlockStateModel.Unbaked;
        BlockStateModel.Unbaked shiftedUnbakedModel = (BlockStateModel.Unbaked) shiftedSimpleModel;
        return shiftedUnbakedModel.bake(baker);
    }
}
