package fr.factionbedrock.aerialhell.Registry;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class AerialHellItemGroups
{
    public static final ItemGroup AERIAL_HELL_BLOCKS = new ItemGroup("aerialhell_blocks")
	{
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(AerialHellBlocksAndItems.STELLAR_STONE_BRICKS_ITEM.get());
        }
    };

    public static final ItemGroup AERIAL_HELL_DUNGEON_BLOCKS = new ItemGroup("aerialhell_dungeon_blocks")
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(AerialHellBlocksAndItems.LIGHT_LUNATIC_STONE.get());
        }
    };
    
    public static final ItemGroup AERIAL_HELL_TOOLS = new ItemGroup("aerialhell_tools")
	{
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(AerialHellBlocksAndItems.MAGMATIC_GEL_PICKAXE.get());
        }
    };
    
    public static final ItemGroup AERIAL_HELL_COMBAT = new ItemGroup("aerialhell_combat")
	{
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(AerialHellBlocksAndItems.RUBY_SWORD.get());
        }
    };
    
    public static final ItemGroup AERIAL_HELL_FOODSTUFFS = new ItemGroup("aerialhell_foodstuffs")
	{
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(AerialHellBlocksAndItems.VIBRANT_AERIAL_BERRY.get());
        }
    };
    
    public static final ItemGroup AERIAL_HELL_MISCELLANEOUS = new ItemGroup("aerialhell_miscellaneous")
	{
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(AerialHellBlocksAndItems.RUBY_LIQUID_OF_GODS_BUCKET.get());
        }
    };
    
    public static final ItemGroup AERIAL_HELL_SPAWN_EGGS = new ItemGroup("aerialhell_spawn_eggs")
	{
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(AerialHellBlocksAndItems.EVIL_COW_SPAWN_EGG.get());
        }
    };
}
