package fr.factionbedrock.aerialhell.Registry.CreativeModeTabs;


import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class AerialHellCreativeModeTabs
{
    public static final ItemGroup AERIAL_HELL_BLOCKS = register("aerialhell_blocks", AerialHellItems.STELLAR_STONE_BRICKS);
    public static final ItemGroup AERIAL_HELL_DUNGEON_BLOCKS = register("aerialhell_dungeon_blocks", AerialHellItems.LIGHT_LUNATIC_STONE);
    public static final ItemGroup AERIAL_HELL_TOOLS = register("aerialhell_tools", AerialHellItems.MAGMATIC_GEL_PICKAXE);
    public static final ItemGroup AERIAL_HELL_COMBAT = register("aerialhell_combat", AerialHellItems.RUBY_SWORD);
    public static final ItemGroup AERIAL_HELL_FOODSTUFFS = register("aerialhell_foodstuffs", AerialHellItems.VIBRANT_AERIAL_BERRY);
    public static final ItemGroup AERIAL_HELL_MISCELLANEOUS = register("aerialhell_miscellaneous", AerialHellItems.RUBY_LIQUID_OF_GODS_BUCKET);
    public static final ItemGroup AERIAL_HELL_SPAWN_EGGS = register("aerialhell_spawn_eggs", AerialHellItems.EVIL_COW_SPAWN_EGG);

    private static ItemGroup register(String name, Item iconItem) {return Registry.register(Registries.ITEM_GROUP, AerialHell.id(name), createItemGroup(name, iconItem));}

    private static ItemGroup createItemGroup(String name, Item iconItem)
    {
        return FabricItemGroup.builder().displayName(Text.translatable("itemGroup."+name)).icon(iconItem::getDefaultStack).build();
    }

    @Nullable public static RegistryKey<ItemGroup> getItemGroupKey(ItemGroup group)
    {
        return Registries.ITEM_GROUP.getKey(group).orElse(null);
    }

    public static void load() {}
}
