package fr.factionbedrock.aerialhell.Registry.Misc;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.material.Fluid;

public class AerialHellTags
{
	public static class Blocks
	{
		public static final TagKey<Block> STELLAR_STONE = tag("stellar_stone");

		public static final TagKey<Block> STELLAR_STONE_AND_DERIVATIVES = tag("stellar_stone_and_derivatives");

		public static final TagKey<Block> STELLAR_DIRT = tag("stellar_dirt");

		public static final TagKey<Block> STELLAR_PLANTS_MAY_PLACE_ON = tag("stellar_plants_may_place_on");

		public static final TagKey<Block> WOODEN_GHOST_BLOCK = tag("wooden_ghost_blocks");

		public static final TagKey<Block> STONE_GHOST_BLOCK = tag("stone_ghost_blocks");

		public static final TagKey<Block> GHOST_BLOCK = tag("ghost_blocks");

		public static final TagKey<Block> GHOST_BLOCK_NO_OVERLAY = tag("ghost_blocks_no_overlay");

		public static final TagKey<Block> SOLID_ETHER = tag("solid_ether");

		public static final TagKey<Block> LEAVES = tag("leaves");

		public static final TagKey<Block> ROOTS = tag("roots");

		public static final TagKey<Block> AERIALHELL_WOODEN_DOORS = tag("aerialhell_wooden_doors");

		public static final TagKey<Block> AERIALHELL_WOODEN_TRAPDOORS = tag("aerialhell_wooden_trapdoors");

		public static final TagKey<Block> AERIALHELL_WOODEN_FENCES = tag("aerialhell_wooden_fences");

		public static final TagKey<Block> AERIALHELL_WOODEN_GATES = tag("aerialhell_wooden_gates");

		public static final TagKey<Block> AERIALHELL_WOODEN_BUTTONS = tag("aerialhell_wooden_buttons");

		public static final TagKey<Block> AERIALHELL_WOODEN_SLABS = tag("aerialhell_wooden_slabs");

		public static final TagKey<Block> AERIALHELL_WOODEN_STAIRS = tag("aerialhell_wooden_stairs");

		public static final TagKey<Block> AERIALHELL_BUTTONS = tag("aerialhell_buttons");

		public static final TagKey<Block> AERIALHELL_PRESSURE_PLATES = tag("aerialhell_pressure_plates");

		public static final TagKey<Block> AERIALHELL_WOODEN_PRESSURE_PLATES = tag("aerialhell_wooden_pressure_plates");

		public static final TagKey<Block> AERIALHELL_SIGNS = tag("aerialhell_signs");

		public static final TagKey<Block> AERIALHELL_PLANKS = tag("aerialhell_planks");

		public static final TagKey<Block> AERIALHELL_SLABS = tag("aerialhell_slabs");

		public static final TagKey<Block> AERIALHELL_STAIRS = tag("aerialhell_stairs");

		public static final TagKey<Block> AERIALHELL_WALLS = tag("aerialhell_walls");

		public static final TagKey<Block> BOOKSHELVES = tag("bookshelves");

		public static final TagKey<Block> DUNGEON_CHESTS = tag("dungeon_chests");

		public static final TagKey<Block> CHESTS = tag("chests");

		public static final TagKey<Block> DUNGEON = tag("dungeon");

		public static final TagKey<Block> DUNGEON_CORES = tag("dungeon_cores");

		public static final TagKey<Block> MUD_DUNGEON = tag("mud_dungeon");

		public static final TagKey<Block> LUNATIC_DUNGEON = tag("lunatic_dungeon");

		public static final TagKey<Block> SHADOW_CATACOMBS_DUNGEON = tag("shadow_catacombs_dungeon");

		public static final TagKey<Block> GOLDEN_NETHER_DUNGEON = tag("golden_nether_dungeon");

		public static final TagKey<Block> VOLUCITE_DUNGEON = tag("volucite_dungeon");

		public static final TagKey<Block> AERIALHELL_SAPLINGS = tag("aerialhell_saplings");

		public static final TagKey<Block> AERIALHELL_SMALL_FLOWERS = tag("aerialhell_small_flowers");

		public static final TagKey<Block> AERIALHELL_FLOWERS = tag("aerialhell_flowers");

		public static final TagKey<Block> AERIALHELL_SHROOMS = tag("aerialhell_shrooms");

		public static final TagKey<Block> AERIALHELL_PLANTS = tag("aerialhell_plants");

		public static final TagKey<Block> OVERWORLD_LANTERN = tag("overworld_lantern");

		public static final TagKey<Block> AERIALHELL_LANTERN = tag("aerialhell_lantern");

		public static final TagKey<Block> FEATURE_CAN_REPLACE = tag("feature_can_replace");

		public static final TagKey<Block> SLIPPERY_SAND = tag("slippery_sand");

		public static final TagKey<Block> SLIPPERY_SAND_GLASS = tag("slippery_sand_glass");

		public static final TagKey<Block> SMOKY_QUARTZ = tag("smoky_quartz");

		public static final TagKey<Block> LILITH_TRANSFORMABLE = tag("lilith_transformable");

		public static final TagKey<Block> NEEDS_STELLAR_STONE_TOOL = tag("needs_stellar_stone_tool");

		public static final TagKey<Block> NEEDS_RUBY_TOOL = tag("needs_ruby_tool");

		public static final TagKey<Block> NEEDS_OBSIDIAN_TOOL = tag("needs_obsidian_tool");

		public static final TagKey<Block> NEEDS_LUNAR_TOOL = tag("needs_lunar_tool");

		public static final TagKey<Block> CHAINED_GOD_CAN_WALK_DESTROY = tag("chained_god_can_walk_destroy");

		public static final TagKey<Block> TROPHIES = tag("trophies");

		public static final TagKey<Block> REPLACE_IN = tag("replace_in");

		public static final TagKey<Block> REPLACE_OUT = tag("replace_out");

		private static TagKey<Block> tag(String name)
		{
			return BlockTags.create(ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, name));
		}
	}

	public static class Items
	{
		public static final TagKey<Item> STICKS = tag("sticks");

		public static final TagKey<Item> AERIALHELL_SIGNS = tag("aerialhell_signs");

		public static final TagKey<Item> AERIALHELL_WOODEN_FENCES = tag("aerialhell_wooden_fences");

		public static final TagKey<Item> AERIALHELL_WOODEN_SLABS = tag("aerialhell_wooden_slabs");

		public static final TagKey<Item> AERIALHELL_WOODEN_GATES = tag("aerialhell_wooden_gates");

		public static final TagKey<Item> BOOKSHELVES = tag("bookshelves");

		public static final TagKey<Item> CHESTS = tag("chests");

		public static final TagKey<Item> BLOWPIPE_ARROWS = tag("blowpipe_arrows");

		public static final TagKey<Item> AERIALHELL_PLANKS = tag("aerialhell_planks");

		public static final TagKey<Item> AERIALHELL_SAPLINGS = tag("aerialhell_saplings");

		public static final TagKey<Item> MAGMATIC_GEL = tag("magmatic_gel");

		public static final TagKey<Item> ARSONIST = tag("arsonist");

		public static final TagKey<Item> ARSONIST_STUFF = tag("arsonist_stuff");

		public static final TagKey<Item> SHADOW_ARMOR = tag("shadow_armor");

		public static final TagKey<Item> SHADOW_STUFF = tag("shadow_stuff");

		public static final TagKey<Item> OBSIDIAN_STUFF = tag("obsidian_stuff");

		public static final TagKey<Item> LUNATIC_STUFF = tag("lunatic_stuff");

		public static final TagKey<Item> VOLUCITE_STUFF = tag("volucite_stuff");

		public static final TagKey<Item> SLIPPERY_SAND_GLASS = tag("slippery_sand_glass");

		private static TagKey<Item> tag(String name)
		{
			return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, name));
		}
	}

	public static class Fluids
	{
		public static final TagKey<Fluid> CRYSTALLIZABLE = tag("crystallizable");

		public static final TagKey<Fluid> LIQUID_OF_THE_GODS = tag("liquid_of_the_gods");

		private static TagKey<Fluid> tag(String name)
		{
			return FluidTags.create(ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, name));
		}
	}

	public static class Structures
	{
		public static final TagKey<Structure> DUNGEONS = tag("dungeons");

		private static TagKey<Structure> tag(String name)
		{
			return TagKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, name));
		}
	}

	public static class Biomes
	{
		public static final TagKey<Biome> IS_AERIAL_HELL = tag("is_aerial_hell");

		public static final TagKey<Biome> IS_AERIAL_HELL_NON_SHADOW = tag("is_aerial_hell_non_shadow");

		public static final TagKey<Biome> IS_CRYSTAL = tag("is_crystal");

		public static final TagKey<Biome> IS_DESERT = tag("is_desert");

		public static final TagKey<Biome> IS_FOREST = tag("is_forest");

		public static final TagKey<Biome> IS_PLAIN = tag("is_plain");

		public static final TagKey<Biome> IS_SAVANA = tag("is_savana");

		public static final TagKey<Biome> IS_SHADOW = tag("is_shadow");

		public static final TagKey<Biome> IS_SHROOM = tag("is_shroom");

		private static TagKey<Biome> tag(String name)
		{
			return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, name));
		}
	}

	public static class PaintingVariants
	{
		public static final TagKey<PaintingVariant> PLACEABLE = tag("placeable");

		private static TagKey<PaintingVariant> tag(String name)
		{
			return TagKey.create(Registries.PAINTING_VARIANT, ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, name));
		}
	}
}
