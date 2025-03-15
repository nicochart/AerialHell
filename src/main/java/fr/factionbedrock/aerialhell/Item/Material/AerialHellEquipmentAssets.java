package fr.factionbedrock.aerialhell.Item.Material;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class AerialHellEquipmentAssets
{
    public static RegistryKey<EquipmentAsset> RUBY = createId("ruby");
    public static RegistryKey<EquipmentAsset> AZURITE = createId("azurite");
    public static RegistryKey<EquipmentAsset> MAGMATIC_GEL = createId("magmatic_gel");
    public static RegistryKey<EquipmentAsset> OBSIDIAN = createId("obsidian");
    public static RegistryKey<EquipmentAsset> VOLUCITE = createId("volucite");
    public static RegistryKey<EquipmentAsset> LUNATIC = createId("lunatic");
    public static RegistryKey<EquipmentAsset> SHADOW = createId("shadow");
    public static RegistryKey<EquipmentAsset> ARSONIST = createId("arsonist");

    //copy of net.minecraft.world.item.equipment.EquipmentAssets createId method
    static RegistryKey<EquipmentAsset> createId(String name)
    {
        return RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(AerialHell.MODID, name));
    }
}
