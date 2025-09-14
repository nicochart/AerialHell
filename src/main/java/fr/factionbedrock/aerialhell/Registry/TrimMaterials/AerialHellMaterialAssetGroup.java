package fr.factionbedrock.aerialhell.Registry.TrimMaterials;

import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;

import java.util.Map;

public class AerialHellMaterialAssetGroup
{
    //not necessary since TrimMaterials registration is not necessary (see AerialHellTrimMaterials)
    public static final MaterialAssetGroup ARSONIST_INGOT = create("arsonist_ingot");
    public static final MaterialAssetGroup CURSED_CRYSTAL = create("cursed_crystal");
    public static final MaterialAssetGroup FLUORITE = create("fluorite");
    public static final MaterialAssetGroup LUNATIC_CRYSTAL = create("lunatic_crystal");
    public static final MaterialAssetGroup MAGMATIC_GEL = create("magmatic_gel");
    public static final MaterialAssetGroup OBSIDIAN_SHARD = create("obsidian_shard");
    public static final MaterialAssetGroup RUBY = create("ruby");
    public static final MaterialAssetGroup VOLUCITE_VIBRANT = create("volucite_vibrant");

    public static MaterialAssetGroup create(String name)
    {
        return new MaterialAssetGroup(new MaterialAssetGroup.AssetInfo(name), Map.of());
    }
}
