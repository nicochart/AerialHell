package fr.factionbedrock.aerialhell.Registry.TrimMaterials;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.resources.Identifier;

//since 26.3, trim materials reference a palette texture id (aerialhell:trim/<name> -> assets/aerialhell/textures/palettes/trim/<name>.png) instead of a MaterialAssetGroup
public class AerialHellMaterialAssetGroup
{
    //not necessary since TrimMaterials registration is not necessary (see AerialHellTrimMaterials)
    public static final Identifier ARSONIST_INGOT = create("arsonist_ingot");
    public static final Identifier CURSED_CRYSTAL = create("cursed_crystal");
    public static final Identifier FLUORITE = create("fluorite");
    public static final Identifier LUNATIC_CRYSTAL = create("lunatic_crystal");
    public static final Identifier MAGMATIC_GEL = create("magmatic_gel");
    public static final Identifier OBSIDIAN_SHARD = create("obsidian_shard");
    public static final Identifier RUBY = create("ruby");
    public static final Identifier VOLUCITE_VIBRANT = create("volucite_vibrant");

    public static Identifier create(String name)
    {
        return Identifier.fromNamespaceAndPath(AerialHell.MODID, "trim/" + name);
    }
}
