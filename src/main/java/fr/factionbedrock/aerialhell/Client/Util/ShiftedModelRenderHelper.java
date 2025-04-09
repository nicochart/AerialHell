package fr.factionbedrock.aerialhell.Client.Util;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.BlockBakedModels.ShiftingBlockBakedModel;
import fr.factionbedrock.aerialhell.Client.Event.Listeners.BlocksAndItemsColorHandler;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.minecraft.block.*;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.Baker;
import net.minecraft.client.render.model.ModelRotation;
import net.minecraft.registry.RegistryKey;

public class ShiftedModelRenderHelper
{
    //model variant with default separator : "_"
    public static boolean contextMatch(ModelModifier.AfterBake.Context context, RegistryKey<Block> key, String postVariantString)
    {
        return contextMatchVariant(context, key, "_"+postVariantString);
    }

    //model variant but the separator must be included in the string
    public static boolean contextMatchVariant(ModelModifier.AfterBake.Context context, RegistryKey<Block> key, String postVariantStringWithSeparator)
    {
        return contextMatch(context, "aerialhell:block/"+key.getValue().getPath()+postVariantStringWithSeparator);
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
        return getShiftingModel(original, key, baker, "", ModelRotation.X0_Y0);
    }

    public static BakedModel getDefaultShiftingModel(BakedModel original, RegistryKey<Block> key, Baker baker, String postVariantString)
    {
        return getShiftingModel(original, key, baker, "_"+postVariantString, ModelRotation.X0_Y0);
    }

    public static BakedModel getDefaultShiftingModelVariant(BakedModel original, RegistryKey<Block> key, Baker baker, String postVariantStringWithSeparator)
    {
        return getShiftingModel(original, key, baker, postVariantStringWithSeparator, ModelRotation.X0_Y0);
    }

    public static BakedModel getCustomRotationDefaultShiftingModel(BakedModel original, RegistryKey<Block> key, Baker baker, String postVariantString, ModelRotationList<ModelRotation> rotationList)
    {
        return getShiftingModel(original, key, baker, "_"+postVariantString, rotationList.getNext());
    }

    public static BakedModel getCustomRotationDefaultShiftingModel(BakedModel original, RegistryKey<Block> key, Baker baker, ModelRotationList<ModelRotation> rotationList)
    {
        return getShiftingModel(original, key, baker, "", rotationList.getNext());
    }

    public static BakedModel getShiftingModel(BakedModel original, RegistryKey<Block> key, Baker baker, String postVariantStringWithSeparator, ModelRotation rotation)
    {
        return getShiftingModel("shifted", original, key, baker, postVariantStringWithSeparator, rotation);
    }

    public static BakedModel getShiftingModel(String shiftedModelPrefix, BakedModel original, RegistryKey<Block> key, Baker baker, String postVariantStringWithSeparator, ModelRotation rotation)
    {
        BakedModel shiftedModel = baker.bake(AerialHell.id("block/"+shiftedModelPrefix+"_"+key.getValue().getPath()+postVariantStringWithSeparator), rotation);
        BakedModel editedModel = new ShiftingBlockBakedModel(original, shiftedModel, (forceShifted) -> BlocksAndItemsColorHandler.isShadowBindEnabled() || forceShifted);
        return editedModel;
    }
}
