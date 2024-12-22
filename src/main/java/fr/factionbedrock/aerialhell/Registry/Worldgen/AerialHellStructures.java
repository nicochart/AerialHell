package fr.factionbedrock.aerialhell.Registry.Worldgen;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.World.Structure.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.StructureSet;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.function.Supplier;

/*
 * For 1.16.5, 1.18.2 and 1.20.1, I followed TelepathicGrunt's tutorial for structures : https://github.com/TelepathicGrunt/StructureTutorialMod/
 * Thank you TelepathicGrunt !
 * For 1.20.1 : I also used https://misode.github.io/guides/adding-custom-structures/ & https://misode.github.io/worldgen/structure/?version=1.20
 */

public class AerialHellStructures
{
	public static final StructureType<?> OVERWORLD_ABANDONNED_PORTAL_STRUCTURE = register("overworld_abandonned_portal", OverworldAbandonnedPortalStructure.CODEC);
	public static final StructureType<?> MUD_DUNGEON_STRUCTURE = register("mud_dungeon", MudDungeonStructure.CODEC);
	public static final StructureType<?> LUNATIC_TEMPLE_STRUCTURE = register("lunatic_temple", LunaticTempleStructure.CODEC);
	public static final StructureType<?> SHADOW_CATACOMBS_STRUCTURE = register("shadow_catacombs", ShadowCatacombsStructure.CODEC);
	public static final StructureType<?> GOLDEN_NETHER_PRISON_STRUCTURE = register("golden_nether_prison", GoldenNetherPrisonStructure.CODEC);
	public static final StructureType<?> STELLAR_STONE_BRICKS_TOWER_STRUCTURE = register("stellar_stone_bricks_tower", StellarStoneBricksTowerStructure.CODEC);
	public static final StructureType<?> COPPER_PINE_COTTAGE_STRUCTURE = register("copper_pine_cottage", CopperPineCottageStructure.CODEC);
	public static final StructureType<?> SLIPPERY_SAND_OCEAN_ABANDONNED_STRUCTURE = register("slippery_sand_ocean_abandonned_structure", SlipperySandOceanAbandonnedStructure.CODEC);
	public static final StructureType<?> SHADOW_PINE_TOWER_STRUCTURE = register("shadow_pine_tower", ShadowPineTowerStructure.CODEC);
	public static final StructureType<?> LAPIS_ROBINIA_HUT_STRUCTURE = register("lapis_robinia_hut", LapisRobiniaHutStructure.CODEC);
	public static final StructureType<?> UPSIDE_DOWN_PYRAMID = register("upside_down_pyramid", UpsideDownPyramidStructure.CODEC);
	public static final StructureType<?> FLOATING_BOAT = register("floating_boat", FloatingBoatStructure.CODEC);

	public static class Sets
	{
		public static final RegistryKey<StructureSet> OVERWORLD_ABANDONNED_PORTAL_STRUCTURE = register("overworld_abandonned_portal");
		public static final RegistryKey<StructureSet> MUD_DUNGEON_STRUCTURE = register("mud_dungeon");
		public static final RegistryKey<StructureSet> LUNATIC_TEMPLE_STRUCTURE = register("lunatic_temple");
		public static final RegistryKey<StructureSet> SHADOW_CATACOMBS_STRUCTURE = register("shadow_catacombs");
		public static final RegistryKey<StructureSet> GOLDEN_NETHER_PRISON_STRUCTURE = register("golden_nether_prison");
		public static final RegistryKey<StructureSet> STELLAR_STONE_BRICKS_TOWER_STRUCTURE = register("stellar_stone_bricks_tower");
		public static final RegistryKey<StructureSet> COPPER_PINE_COTTAGE_STRUCTURE = register("copper_pine_cottage");
		public static final RegistryKey<StructureSet> SLIPPERY_SAND_OCEAN_ABANDONNED_STRUCTURE = register("slippery_sand_ocean_abandonned_structure");
		public static final RegistryKey<StructureSet> SHADOW_PINE_TOWER_STRUCTURE = register("shadow_pine_tower");
		public static final RegistryKey<StructureSet> LAPIS_ROBINIA_HUT_STRUCTURE = register("lapis_robinia_hut");
		public static final RegistryKey<StructureSet> UPSIDE_DOWN_PYRAMID = register("upside_down_pyramid");

		public static RegistryKey<StructureSet> register(String name)
		{
			return RegistryKey.of(RegistryKeys.STRUCTURE_SET, AerialHell.id(name));
		}
	}

	public static <S extends Structure> StructureType<S> register(String name, MapCodec<S> codec)
	{
		return Registry.register(Registries.STRUCTURE_TYPE, AerialHell.id(name), () -> codec);
	}

	public static void load() {}
}
