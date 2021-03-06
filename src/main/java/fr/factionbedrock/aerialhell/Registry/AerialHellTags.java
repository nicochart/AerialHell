package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class AerialHellTags
{
	public static class Blocks
	{
		public static final ITag.INamedTag<Block> STELLAR_STONE = tag("stellar_stone");
		
		public static final ITag.INamedTag<Block> STELLAR_DIRT = tag("stellar_dirt");
		
		public static final ITag.INamedTag<Block> AERIALHELL_WOODEN_DOORS = tag("aerialhell_wooden_doors");
		
		public static final ITag.INamedTag<Block> AERIALHELL_WOODEN_TRAPDOORS = tag("aerialhell_wooden_trapdoors");
		
		public static final ITag.INamedTag<Block> AERIALHELL_WOODEN_FENCES = tag("aerialhell_wooden_fences");
		
		public static final ITag.INamedTag<Block> AERIALHELL_WOODEN_GATES = tag("aerialhell_wooden_gates");
		
		public static final ITag.INamedTag<Block> AERIALHELL_WOODEN_BUTTONS = tag("aerialhell_wooden_buttons");
		
		public static final ITag.INamedTag<Block> AERIALHELL_WOODEN_SLABS = tag("aerialhell_wooden_slabs");
		
		public static final ITag.INamedTag<Block> AERIALHELL_WOODEN_STAIRS = tag("aerialhell_wooden_stairs");
		
		public static final ITag.INamedTag<Block> AERIALHELL_BUTTONS = tag("aerialhell_buttons");
		
		public static final ITag.INamedTag<Block> AERIALHELL_PRESSURE_PLATES = tag("aerialhell_pressure_plates");
		
		public static final ITag.INamedTag<Block> AERIALHELL_WOODEN_PRESSURE_PLATES = tag("aerialhell_wooden_pressure_plates");
		
		public static final ITag.INamedTag<Block> AERIALHELL_SIGNS = tag("aerialhell_signs");
		
		public static final ITag.INamedTag<Block> AERIALHELL_PLANKS = tag("aerialhell_planks");
		
		public static final ITag.INamedTag<Block> AERIALHELL_SLABS = tag("aerialhell_slabs");
		
		public static final ITag.INamedTag<Block> AERIALHELL_STAIRS = tag("aerialhell_stairs");
		
		public static final ITag.INamedTag<Block> AERIALHELL_WALLS = tag("aerialhell_walls");
		
		public static final ITag.INamedTag<Block> BOOKSHELVES = tag("bookshelves");
		
		public static final ITag.INamedTag<Block> DUNGEON = tag("dungeon");
		
		public static final ITag.INamedTag<Block> DUNGEON_CORES = tag("dungeon_cores");
		
		public static final ITag.INamedTag<Block> MUD_DUNGEON = tag("mud_dungeon");
		
		public static final ITag.INamedTag<Block> LUNATIC_DUNGEON = tag("lunatic_dungeon");
		
		public static final ITag.INamedTag<Block> GOLDEN_NETHER_DUNGEON = tag("golden_nether_dungeon");
		
		public static final ITag.INamedTag<Block> VOLUCITE_DUNGEON = tag("volucite_dungeon");
		
		public static final ITag.INamedTag<Block> SAPLINGS = tag("aerialhell_saplings");
		
		public static final ITag.INamedTag<Block> OVERWORLD_LANTERN = tag("overworld_lantern");

		private static ITag.INamedTag<Block> tag(String name)
		{
			return BlockTags.makeWrapperTag(new ResourceLocation(AerialHell.MODID, name).toString());
		}
	}
	
	public static class Items
	{
		public static final ITag.INamedTag<Item> STICKS = tag("sticks");
		
		public static final ITag.INamedTag<Item> BLOWPIPE_ARROWS = tag("blowpipe_arrows");
		
		public static final ITag.INamedTag<Item> AERIALHELL_PLANKS = tag("aerialhell_planks");
		
		public static final ITag.INamedTag<Item> MAGMATIC_GEL = tag("magmatic_gel");
		
		public static final ITag.INamedTag<Item> ARSONIST = tag("arsonist");
		
		public static final ITag.INamedTag<Item> ARSONIST_STUFF = tag("arsonist_stuff");
		
		public static final ITag.INamedTag<Item> OBSIDIAN_STUFF = tag("obsidian_stuff");
		
		public static final ITag.INamedTag<Item> LUNATIC_STUFF = tag("lunatic_stuff");
		
		private static ITag.INamedTag<Item> tag(String name)
		{
			return ItemTags.makeWrapperTag(new ResourceLocation(AerialHell.MODID, name).toString());
		}
	}
	
	public static class Fluids
	{
		public static final ITag.INamedTag<Fluid> CRYSTALLIZABLE = tag("crystallizable");
		
		public static final ITag.INamedTag<Fluid> LIQUID_OF_THE_GODS = tag("liquid_of_the_gods");

		private static ITag.INamedTag<Fluid> tag(String name)
		{
			return FluidTags.makeWrapperTag(new ResourceLocation(AerialHell.MODID, name).toString());
		}
	}
}
