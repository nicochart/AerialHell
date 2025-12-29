package fr.factionbedrock.aerialhell.Registry.TrimMaterials;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AerialHellTrimMaterials
{
    //not necessary since I wrote all the jsons manually (the keys below are necessary)
    public static final DeferredRegister<TrimMaterial> TRIM_MATERIALS = DeferredRegister.create(Registries.TRIM_MATERIAL, AerialHell.MODID);

    public static final DeferredHolder<TrimMaterial, TrimMaterial> ARSONIST_INGOT = register(Keys.ARSONIST_INGOT, Style.EMPTY.withColor(15559424), AerialHellMaterialAssetGroup.ARSONIST_INGOT);
    public static final DeferredHolder<TrimMaterial, TrimMaterial> CURSED_CRYSTAL = register(Keys.CURSED_CRYSTAL, Style.EMPTY.withColor(6438517), AerialHellMaterialAssetGroup.CURSED_CRYSTAL);
    public static final DeferredHolder<TrimMaterial, TrimMaterial> FLUORITE = register(Keys.FLUORITE, Style.EMPTY.withColor(613193), AerialHellMaterialAssetGroup.FLUORITE);
    public static final DeferredHolder<TrimMaterial, TrimMaterial> LUNATIC_CRYSTAL = register(Keys.LUNATIC_CRYSTAL, Style.EMPTY.withColor(10935724), AerialHellMaterialAssetGroup.LUNATIC_CRYSTAL);
    public static final DeferredHolder<TrimMaterial, TrimMaterial> MAGMATIC_GEL = register(Keys.MAGMATIC_GEL, Style.EMPTY.withColor(6681087), AerialHellMaterialAssetGroup.MAGMATIC_GEL);
    public static final DeferredHolder<TrimMaterial, TrimMaterial> OBSIDIAN_SHARD = register(Keys.OBSIDIAN_SHARD, Style.EMPTY.withColor(2563645), AerialHellMaterialAssetGroup.OBSIDIAN_SHARD);
    public static final DeferredHolder<TrimMaterial, TrimMaterial> RUBY = register(Keys.RUBY, Style.EMPTY.withColor(12329506), AerialHellMaterialAssetGroup.RUBY);
    public static final DeferredHolder<TrimMaterial, TrimMaterial> VOLUCITE_VIBRANT = register(Keys.VOLUCITE_VIBRANT, Style.EMPTY.withColor(12329506), AerialHellMaterialAssetGroup.VOLUCITE_VIBRANT);

    private static DeferredHolder<TrimMaterial, TrimMaterial> register(ResourceKey<TrimMaterial> key, Style style, MaterialAssetGroup assets)
    {
        Component trimMaterialComponent = Component.translatable(Util.makeDescriptionId("trim_material", key.identifier())).withStyle(style);;
        return register(key.identifier().getPath(), trimMaterialComponent, assets);
    }

    private static DeferredHolder<TrimMaterial, TrimMaterial> register(String name, Component trimMaterialComponent, MaterialAssetGroup assets)
    {
        return TRIM_MATERIALS.register(name, () -> new TrimMaterial(assets, trimMaterialComponent));
    }

    public static class Keys
    {
        public static final ResourceKey<TrimMaterial> ARSONIST_INGOT = registryKey("arsonist_ingot");
        public static final ResourceKey<TrimMaterial> CURSED_CRYSTAL = registryKey("cursed_crystal");
        public static final ResourceKey<TrimMaterial> FLUORITE = registryKey("fluorite");
        public static final ResourceKey<TrimMaterial> LUNATIC_CRYSTAL = registryKey("lunatic_crystal");
        public static final ResourceKey<TrimMaterial> MAGMATIC_GEL = registryKey("magmatic_gel");
        public static final ResourceKey<TrimMaterial> OBSIDIAN_SHARD = registryKey("obsidian_shard");
        public static final ResourceKey<TrimMaterial> RUBY = registryKey("ruby");
        public static final ResourceKey<TrimMaterial> VOLUCITE_VIBRANT = registryKey("volucite_vibrant");

        private static ResourceKey<TrimMaterial> registryKey(String name)
        {
            return ResourceKey.create(Registries.TRIM_MATERIAL, Identifier.fromNamespaceAndPath(AerialHell.MODID, name));
        }
    }
}
