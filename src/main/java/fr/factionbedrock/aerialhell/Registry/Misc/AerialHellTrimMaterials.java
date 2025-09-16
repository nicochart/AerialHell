package fr.factionbedrock.aerialhell.Registry.Misc;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.item.equipment.trim.ArmorTrimMaterial;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class AerialHellTrimMaterials
{
    public static final RegistryKey<ArmorTrimMaterial> ARSONIST_INGOT = createKey("arsonist_ingot");
    public static final RegistryKey<ArmorTrimMaterial> CURSED_CRYSTAL = createKey("cursed_crystal");
    public static final RegistryKey<ArmorTrimMaterial> FLUORITE = createKey("fluorite");
    public static final RegistryKey<ArmorTrimMaterial> LUNATIC_CRYSTAL = createKey("lunatic_crystal");
    public static final RegistryKey<ArmorTrimMaterial> MAGMATIC_GEL = createKey("magmatic_gel");
    public static final RegistryKey<ArmorTrimMaterial> OBSIDIAN_SHARD = createKey("obsidian_shard");
    public static final RegistryKey<ArmorTrimMaterial> RUBY = createKey("ruby");
    public static final RegistryKey<ArmorTrimMaterial> VOLUCITE_VIBRANT = createKey("volucite_vibrant");

    private static RegistryKey<ArmorTrimMaterial> createKey(String name)
    {
        return RegistryKey.of(RegistryKeys.TRIM_MATERIAL, AerialHell.id(name));
    }

    public static void load() {}
}
