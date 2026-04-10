package fr.factionbedrock.aerialhell.Registry.CreativeModeTabs;


import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;

public class AerialHellCreativeModeTabs
{
    public static final CreativeModeTab AERIAL_HELL_BLOCKS = register("aerialhell_blocks", AerialHellItems.STELLAR_STONE_BRICKS);
    public static final CreativeModeTab AERIAL_HELL_DUNGEON_BLOCKS = register("aerialhell_dungeon_blocks", AerialHellItems.LIGHT_LUNATIC_STONE);
    public static final CreativeModeTab AERIAL_HELL_TOOLS = register("aerialhell_tools", AerialHellItems.MAGMATIC_GEL_PICKAXE);
    public static final CreativeModeTab AERIAL_HELL_COMBAT = register("aerialhell_combat", AerialHellItems.RUBY_SWORD);
    public static final CreativeModeTab AERIAL_HELL_FOODSTUFFS = register("aerialhell_foodstuffs", AerialHellItems.VIBRANT_AERIAL_BERRY);
    public static final CreativeModeTab AERIAL_HELL_MISCELLANEOUS = register("aerialhell_miscellaneous", AerialHellItems.RUBY_LIQUID_OF_GODS_BUCKET);
    public static final CreativeModeTab AERIAL_HELL_SPAWN_EGGS = register("aerialhell_spawn_eggs", AerialHellItems.EVIL_COW_SPAWN_EGG);

    private static CreativeModeTab register(String name, Item iconItem) {return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, AerialHell.id(name), createItemGroup(name, iconItem));}

    private static CreativeModeTab createItemGroup(String name, Item iconItem)
    {
        return FabricItemGroup.builder().title(Component.translatable("itemGroup."+name)).icon(iconItem::getDefaultInstance).build();
    }

    @Nullable public static ResourceKey<CreativeModeTab> getItemGroupKey(CreativeModeTab group)
    {
        return BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(group).orElse(null);
    }

    public static void load() {}
}
