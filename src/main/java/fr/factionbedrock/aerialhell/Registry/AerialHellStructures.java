package fr.factionbedrock.aerialhell.Registry;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;
import fr.factionbedrock.aerialhell.World.Structure.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.StructureSet;
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

	public static final class Keys
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

		private static ResourceKey<StructureSet> register(String name)
		{
			return ResourceKey.create(Registry.STRUCTURE_SET_REGISTRY, new ResourceLocation(MODID, name));
		}
	}
}
