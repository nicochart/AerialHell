package fr.factionbedrock.aerialhell.Registry;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class AerialHellCreativeModeTabs
{
    public static final CreativeModeTab AERIAL_HELL_BLOCKS = new CreativeModeTab( "aerialhell_blocks")
	{
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(AerialHellBlocksAndItems.STELLAR_STONE_BRICKS_ITEM.get());
        }
    };

    public static final CreativeModeTab AERIAL_HELL_DUNGEON_BLOCKS = new CreativeModeTab("aerialhell_dungeon_blocks")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(AerialHellBlocksAndItems.LIGHT_LUNATIC_STONE.get());
        }
    };
    
    public static final CreativeModeTab AERIAL_HELL_TOOLS = new CreativeModeTab("aerialhell_tools")
	{
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(AerialHellBlocksAndItems.MAGMATIC_GEL_PICKAXE.get());
        }
    };
    
    public static final CreativeModeTab AERIAL_HELL_COMBAT = new CreativeModeTab("aerialhell_combat")
	{
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(AerialHellBlocksAndItems.RUBY_SWORD.get());
        }
    };
    
    public static final CreativeModeTab AERIAL_HELL_FOODSTUFFS = new CreativeModeTab("aerialhell_foodstuffs")
	{
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(AerialHellBlocksAndItems.VIBRANT_AERIAL_BERRY.get());
        }
    };
    
    public static final CreativeModeTab AERIAL_HELL_MISCELLANEOUS = new CreativeModeTab("aerialhell_miscellaneous")
	{
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(AerialHellBlocksAndItems.RUBY_LIQUID_OF_GODS_BUCKET.get());
        }
    };
    
    public static final CreativeModeTab AERIAL_HELL_SPAWN_EGGS = new CreativeModeTab("aerialhell_spawn_eggs")
	{
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(AerialHellBlocksAndItems.EVIL_COW_SPAWN_EGG.get());
        }
    };
}
