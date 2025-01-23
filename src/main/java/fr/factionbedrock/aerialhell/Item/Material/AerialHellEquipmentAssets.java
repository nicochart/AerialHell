package fr.factionbedrock.aerialhell.Item.Material;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public class AerialHellEquipmentAssets
{
    public static ResourceKey<EquipmentAsset> RUBY = createId("ruby");
    public static ResourceKey<EquipmentAsset> AZURITE = createId("azurite");
    public static ResourceKey<EquipmentAsset> MAGMATIC_GEL = createId("magmatic_gel");
    public static ResourceKey<EquipmentAsset> OBSIDIAN = createId("obsidian");
    public static ResourceKey<EquipmentAsset> VOLUCITE = createId("volucite");
    public static ResourceKey<EquipmentAsset> LUNATIC = createId("lunatic");
    public static ResourceKey<EquipmentAsset> SHADOW = createId("shadow");
    public static ResourceKey<EquipmentAsset> ARSONIST = createId("arsonist");

    //copy of net.minecraft.world.item.equipment.EquipmentAssets createId method
    static ResourceKey<EquipmentAsset> createId(String name)
    {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.withDefaultNamespace(name));
    }
}
