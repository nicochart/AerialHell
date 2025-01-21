package fr.factionbedrock.aerialhell.Registry.CreativeModeTabs;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class AerialHellCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AERIAL_HELL_BLOCKS = createTab("aerialhell_blocks", () -> AerialHellItems.STELLAR_STONE_BRICKS.get());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AERIAL_HELL_DUNGEON_BLOCKS = createTab("aerialhell_dungeon_blocks", () -> AerialHellItems.LIGHT_LUNATIC_STONE.get());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AERIAL_HELL_TOOLS = createTab("aerialhell_tools", () -> AerialHellItems.MAGMATIC_GEL_PICKAXE.get());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AERIAL_HELL_COMBAT = createTab("aerialhell_combat", () -> AerialHellItems.RUBY_SWORD.get());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AERIAL_HELL_FOODSTUFFS = createTab("aerialhell_foodstuffs", () -> AerialHellItems.VIBRANT_AERIAL_BERRY.get());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AERIAL_HELL_MISCELLANEOUS = createTab("aerialhell_miscellaneous", () -> AerialHellItems.RUBY_LIQUID_OF_GODS_BUCKET.get());
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AERIAL_HELL_SPAWN_EGGS = createTab("aerialhell_spawn_eggs", () -> AerialHellItems.EVIL_COW_SPAWN_EGG.get());

    private static DeferredHolder<CreativeModeTab, CreativeModeTab> createTab(String id, Supplier<Item> iconItem)
    {
        return TABS.register(id, () -> CreativeModeTab.builder().title(Component.translatable("itemGroup."+id)).icon(iconItem.get()::getDefaultInstance).build());
    }
}
