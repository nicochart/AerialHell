package fr.factionbedrock.aerialhell.Registry.CreativeModeTabs;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class AerialHellCreativeModeTabs
{
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> AERIAL_HELL_BLOCKS = createTab("aerialhell_blocks", () -> AerialHellBlocksAndItems.STELLAR_STONE_BRICKS_ITEM.get());
    public static final RegistryObject<CreativeModeTab> AERIAL_HELL_DUNGEON_BLOCKS = createTab("aerialhell_dungeon_blocks", () -> AerialHellBlocksAndItems.LIGHT_LUNATIC_STONE_ITEM.get());
    public static final RegistryObject<CreativeModeTab> AERIAL_HELL_TOOLS = createTab("aerialhell_tools", () -> AerialHellBlocksAndItems.MAGMATIC_GEL_PICKAXE.get());
    public static final RegistryObject<CreativeModeTab> AERIAL_HELL_COMBAT = createTab("aerialhell_combat", () -> AerialHellBlocksAndItems.RUBY_SWORD.get());
    public static final RegistryObject<CreativeModeTab> AERIAL_HELL_FOODSTUFFS = createTab("aerialhell_foodstuffs", () -> AerialHellBlocksAndItems.VIBRANT_AERIAL_BERRY.get());
    public static final RegistryObject<CreativeModeTab> AERIAL_HELL_MISCELLANEOUS = createTab("aerialhell_miscellaneous", () -> AerialHellBlocksAndItems.RUBY_LIQUID_OF_GODS_BUCKET.get());
    public static final RegistryObject<CreativeModeTab> AERIAL_HELL_SPAWN_EGGS = createTab("aerialhell_spawn_eggs", () -> AerialHellBlocksAndItems.EVIL_COW_SPAWN_EGG.get());

    private static RegistryObject<CreativeModeTab> createTab(String id, Supplier<Item> iconItem)
    {
        return TABS.register(id, () -> CreativeModeTab.builder().title(Component.translatable("itemGroup."+id)).icon(iconItem.get()::getDefaultInstance).build());
    }
}
