package fr.factionbedrock.aerialhell.Registry.Worldgen;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;
import fr.factionbedrock.aerialhell.World.Structure.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

/*
 * For 1.16.5, 1.18.2 and 1.20.1, I followed TelepathicGrunt's tutorial for structures : https://github.com/TelepathicGrunt/StructureTutorialMod/
 * Thank you TelepathicGrunt !
 * For 1.20.1 : I also used https://misode.github.io/guides/adding-custom-structures/ & https://misode.github.io/worldgen/structure/?version=1.20
 */

import net.minecraft.world.level.levelgen.structure.StructureType;

public class AerialHellStructures
{

	public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, MODID);

	public static final RegistryObject<StructureType<OverworldAbandonnedPortalStructure>> OVERWORLD_ABANDONNED_PORTAL_STRUCTURE = STRUCTURES.register("overworld_abandonned_portal", () -> () -> OverworldAbandonnedPortalStructure.CODEC);
	public static final RegistryObject<StructureType<MudDungeonStructure>> MUD_DUNGEON_STRUCTURE = STRUCTURES.register("mud_dungeon", () -> () -> MudDungeonStructure.CODEC);
	public static final RegistryObject<StructureType<LunaticTempleStructure>> LUNATIC_TEMPLE_STRUCTURE = STRUCTURES.register("lunatic_temple", () -> () -> LunaticTempleStructure.CODEC);
	public static final RegistryObject<StructureType<ShadowCatacombsStructure>> SHADOW_CATACOMBS_STRUCTURE = STRUCTURES.register("shadow_catacombs", () -> () -> ShadowCatacombsStructure.CODEC);
	public static final RegistryObject<StructureType<GoldenNetherPrisonStructure>> GOLDEN_NETHER_PRISON_STRUCTURE = STRUCTURES.register("golden_nether_prison", () -> () -> GoldenNetherPrisonStructure.CODEC);
	public static final RegistryObject<StructureType<StellarStoneBricksTowerStructure>> STELLAR_STONE_BRICKS_TOWER_STRUCTURE = STRUCTURES.register("stellar_stone_bricks_tower", () -> () -> StellarStoneBricksTowerStructure.CODEC);
	public static final RegistryObject<StructureType<CopperPineCottageStructure>> COPPER_PINE_COTTAGE_STRUCTURE = STRUCTURES.register("copper_pine_cottage", () -> () -> CopperPineCottageStructure.CODEC);
	public static final RegistryObject<StructureType<SlipperySandOceanAbandonnedStructure>> SLIPPERY_SAND_OCEAN_ABANDONNED_STRUCTURE = STRUCTURES.register("slippery_sand_ocean_abandonned_structure", () -> () -> SlipperySandOceanAbandonnedStructure.CODEC);
	public static final RegistryObject<StructureType<ShadowPineTowerStructure>> SHADOW_PINE_TOWER_STRUCTURE = STRUCTURES.register("shadow_pine_tower", () -> () -> ShadowPineTowerStructure.CODEC);
	public static final RegistryObject<StructureType<LapisRobiniaHutStructure>> LAPIS_ROBINIA_HUT_STRUCTURE = STRUCTURES.register("lapis_robinia_hut", () -> () -> LapisRobiniaHutStructure.CODEC);
}
