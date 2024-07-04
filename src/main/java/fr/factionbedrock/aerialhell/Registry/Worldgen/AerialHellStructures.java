package fr.factionbedrock.aerialhell.Registry.Worldgen;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.World.Structure.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.StructureSet;

/*
 * For 1.16.5, 1.18.2 and 1.20.1, I followed TelepathicGrunt's tutorial for structures : https://github.com/TelepathicGrunt/StructureTutorialMod/
 * Thank you TelepathicGrunt !
 * For 1.20.1 : I also used https://misode.github.io/guides/adding-custom-structures/ & https://misode.github.io/worldgen/structure/?version=1.20
 */

import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AerialHellStructures
{

	public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, MODID);

	public static final DeferredHolder<StructureType<?>, StructureType<OverworldAbandonnedPortalStructure>> OVERWORLD_ABANDONNED_PORTAL_STRUCTURE = STRUCTURES.register("overworld_abandonned_portal", () -> () -> OverworldAbandonnedPortalStructure.CODEC);
	public static final DeferredHolder<StructureType<?>, StructureType<MudDungeonStructure>> MUD_DUNGEON_STRUCTURE = STRUCTURES.register("mud_dungeon", () -> () -> MudDungeonStructure.CODEC);
	public static final DeferredHolder<StructureType<?>, StructureType<LunaticTempleStructure>> LUNATIC_TEMPLE_STRUCTURE = STRUCTURES.register("lunatic_temple", () -> () -> LunaticTempleStructure.CODEC);
	public static final DeferredHolder<StructureType<?>, StructureType<ShadowCatacombsStructure>> SHADOW_CATACOMBS_STRUCTURE = STRUCTURES.register("shadow_catacombs", () -> () -> ShadowCatacombsStructure.CODEC);
	public static final DeferredHolder<StructureType<?>, StructureType<GoldenNetherPrisonStructure>> GOLDEN_NETHER_PRISON_STRUCTURE = STRUCTURES.register("golden_nether_prison", () -> () -> GoldenNetherPrisonStructure.CODEC);
	public static final DeferredHolder<StructureType<?>, StructureType<StellarStoneBricksTowerStructure>> STELLAR_STONE_BRICKS_TOWER_STRUCTURE = STRUCTURES.register("stellar_stone_bricks_tower", () -> () -> StellarStoneBricksTowerStructure.CODEC);
	public static final DeferredHolder<StructureType<?>, StructureType<CopperPineCottageStructure>> COPPER_PINE_COTTAGE_STRUCTURE = STRUCTURES.register("copper_pine_cottage", () -> () -> CopperPineCottageStructure.CODEC);
	public static final DeferredHolder<StructureType<?>, StructureType<SlipperySandOceanAbandonnedStructure>> SLIPPERY_SAND_OCEAN_ABANDONNED_STRUCTURE = STRUCTURES.register("slippery_sand_ocean_abandonned_structure", () -> () -> SlipperySandOceanAbandonnedStructure.CODEC);
	public static final DeferredHolder<StructureType<?>, StructureType<ShadowPineTowerStructure>> SHADOW_PINE_TOWER_STRUCTURE = STRUCTURES.register("shadow_pine_tower", () -> () -> ShadowPineTowerStructure.CODEC);
	public static final DeferredHolder<StructureType<?>, StructureType<LapisRobiniaHutStructure>> LAPIS_ROBINIA_HUT_STRUCTURE = STRUCTURES.register("lapis_robinia_hut", () -> () -> LapisRobiniaHutStructure.CODEC);
	public static final DeferredHolder<StructureType<?>, StructureType<UpsideDownPyramidStructure>> UPSIDE_DOWN_PYRAMID = STRUCTURES.register("upside_down_pyramid", () -> () -> UpsideDownPyramidStructure.CODEC);
	public static final DeferredHolder<StructureType<?>, StructureType<FloatingBoatStructure>> FLOATING_BOAT = STRUCTURES.register("floating_boat", () -> () -> FloatingBoatStructure.CODEC);

	public static class Sets
	{
		public static final ResourceKey<StructureSet> OVERWORLD_ABANDONNED_PORTAL_STRUCTURE = register("overworld_abandonned_portal");
		public static final ResourceKey<StructureSet> MUD_DUNGEON_STRUCTURE = register("mud_dungeon");
		public static final ResourceKey<StructureSet> LUNATIC_TEMPLE_STRUCTURE = register("lunatic_temple");
		public static final ResourceKey<StructureSet> SHADOW_CATACOMBS_STRUCTURE = register("shadow_catacombs");
		public static final ResourceKey<StructureSet> GOLDEN_NETHER_PRISON_STRUCTURE = register("golden_nether_prison");
		public static final ResourceKey<StructureSet> STELLAR_STONE_BRICKS_TOWER_STRUCTURE = register("stellar_stone_bricks_tower");
		public static final ResourceKey<StructureSet> COPPER_PINE_COTTAGE_STRUCTURE = register("copper_pine_cottage");
		public static final ResourceKey<StructureSet> SLIPPERY_SAND_OCEAN_ABANDONNED_STRUCTURE = register("slippery_sand_ocean_abandonned_structure");
		public static final ResourceKey<StructureSet> SHADOW_PINE_TOWER_STRUCTURE = register("shadow_pine_tower");
		public static final ResourceKey<StructureSet> LAPIS_ROBINIA_HUT_STRUCTURE = register("lapis_robinia_hut");
		public static final ResourceKey<StructureSet> UPSIDE_DOWN_PYRAMID = register("upside_down_pyramid");

		private static ResourceKey<StructureSet> register(String name)
		{
			return ResourceKey.create(Registries.STRUCTURE_SET, ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, name));
		}
	}

}
