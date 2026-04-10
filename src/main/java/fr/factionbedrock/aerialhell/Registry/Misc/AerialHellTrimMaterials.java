package fr.factionbedrock.aerialhell.Registry.Misc;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

public class AerialHellTrimMaterials
{
    public static final ResourceKey<TrimMaterial> ARSONIST_INGOT = createKey("arsonist_ingot");
    public static final ResourceKey<TrimMaterial> CURSED_CRYSTAL = createKey("cursed_crystal");
    public static final ResourceKey<TrimMaterial> FLUORITE = createKey("fluorite");
    public static final ResourceKey<TrimMaterial> LUNATIC_CRYSTAL = createKey("lunatic_crystal");
    public static final ResourceKey<TrimMaterial> MAGMATIC_GEL = createKey("magmatic_gel");
    public static final ResourceKey<TrimMaterial> OBSIDIAN_SHARD = createKey("obsidian_shard");
    public static final ResourceKey<TrimMaterial> RUBY = createKey("ruby");
    public static final ResourceKey<TrimMaterial> VOLUCITE_VIBRANT = createKey("volucite_vibrant");

    private static ResourceKey<TrimMaterial> createKey(String name)
    {
        return ResourceKey.create(Registries.TRIM_MATERIAL, AerialHell.id(name));
    }

    public static void load() {}
}
