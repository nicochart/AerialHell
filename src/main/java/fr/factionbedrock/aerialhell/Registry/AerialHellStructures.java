package fr.factionbedrock.aerialhell.Registry;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

import fr.factionbedrock.aerialhell.World.Structure.*;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/*
 * I followed TelepathicGrunt's tutorial for structures : https://github.com/TelepathicGrunt/StructureTutorialMod/
 * (for 1.18.2 : https://github.com/TelepathicGrunt/StructureTutorialMod/tree/1.18.2-Forge-Jigsaw)
 * Thank you TelepathicGrunt !
 */

public class AerialHellStructures
{
	public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, MODID);
	
	public static final RegistryObject<StructureFeature<?>> OVERWORLD_ABANDONNED_PORTAL_STRUCTURE = STRUCTURES.register("overworld_abandonned_portal", () -> new OverworldAbandonnedPortalStructure());
	public static final RegistryObject<StructureFeature<?>> MUD_DUNGEON_STRUCTURE = STRUCTURES.register("mud_dungeon", () -> new MudDungeonStructure());
	public static final RegistryObject<StructureFeature<?>> LUNATIC_TEMPLE_STRUCTURE = STRUCTURES.register("lunatic_temple", () -> new LunaticTempleStructure());
	public static final RegistryObject<StructureFeature<?>> SHADOW_CATACOMBS_STRUCTURE = STRUCTURES.register("shadow_catacombs", () -> new ShadowCatacombsStructure());
	public static final RegistryObject<StructureFeature<?>> GOLDEN_NETHER_PRISON_STRUCTURE = STRUCTURES.register("golden_nether_prison", () -> new GoldenNetherPrisonStructure());
	public static final RegistryObject<StructureFeature<?>> STELLAR_STONE_BRICKS_TOWER_STRUCTURE = STRUCTURES.register("stellar_stone_bricks_tower", () -> new StellarStoneBricksTowerStructure());
	public static final RegistryObject<StructureFeature<?>> COPPER_PINE_COTTAGE_STRUCTURE = STRUCTURES.register("copper_pine_cottage", () -> new CopperPineCottageStructure());
	public static final RegistryObject<StructureFeature<?>> SLIPPERY_SAND_OCEAN_ABANDONNED_STRUCTURE = STRUCTURES.register("slippery_sand_ocean_abandonned_structure", () -> new SlipperySandOceanAbandonnedStructure());
	public static final RegistryObject<StructureFeature<?>> SHADOW_PINE_TOWER_STRUCTURE = STRUCTURES.register("shadow_pine_tower", () -> new ShadowPineTowerStructure());
	public static final RegistryObject<StructureFeature<?>> LAPIS_ROBINIA_HUT_STRUCTURE = STRUCTURES.register("lapis_robinia_hut", () -> new LapisRobiniaHutStructure());
}
