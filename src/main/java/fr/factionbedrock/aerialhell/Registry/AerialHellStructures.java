package fr.factionbedrock.aerialhell.Registry;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import fr.factionbedrock.aerialhell.World.Structure.CopperPineCottageStructure;
import fr.factionbedrock.aerialhell.World.Structure.GoldenNetherPrisonStructure;
import fr.factionbedrock.aerialhell.World.Structure.LunaticTempleStructure;
import fr.factionbedrock.aerialhell.World.Structure.MudDungeonStructure;
import fr.factionbedrock.aerialhell.World.Structure.OverworldAbandonnedPortalStructure;
import fr.factionbedrock.aerialhell.World.Structure.SlipperySandOceanAbandonnedStructure;
import fr.factionbedrock.aerialhell.World.Structure.StellarStoneBricksTowerStructure;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/*
 * I followed TelepathicGrunt's tutorial for structures : https://github.com/TelepathicGrunt/StructureTutorialMod/
 * (for 1.16.5 : https://github.com/TelepathicGrunt/StructureTutorialMod/tree/1.16.3-Forge-jigsaw)
 * Thank you TelepathicGrunt !
 */

public class AerialHellStructures
{
	public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, MODID);
	
	public static final RegistryObject<Structure<NoFeatureConfig>> OVERWORLD_ABANDONNED_PORTAL_STRUCTURE = STRUCTURES.register("overworld_abandonned_portal", () -> new OverworldAbandonnedPortalStructure(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Structure<NoFeatureConfig>> MUD_DUNGEON_STRUCTURE = STRUCTURES.register("mud_dungeon", () -> new MudDungeonStructure(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Structure<NoFeatureConfig>> LUNATIC_TEMPLE_STRUCTURE = STRUCTURES.register("lunatic_temple", () -> new LunaticTempleStructure(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Structure<NoFeatureConfig>> GOLDEN_NETHER_PRISON_STRUCTURE = STRUCTURES.register("golden_nether_prison", () -> new GoldenNetherPrisonStructure(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Structure<NoFeatureConfig>> STELLAR_STONE_BRICKS_TOWER_STRUCTURE = STRUCTURES.register("stellar_stone_bricks_tower", () -> new StellarStoneBricksTowerStructure(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Structure<NoFeatureConfig>> COPPER_PINE_COTTAGE_STRUCTURE = STRUCTURES.register("copper_pine_cottage", () -> new CopperPineCottageStructure(NoFeatureConfig.field_236558_a_));
	public static final RegistryObject<Structure<NoFeatureConfig>> SLIPPERY_SAND_OCEAN_ABANDONNED_STRUCTURE = STRUCTURES.register("slippery_sand_ocean_abandonned_structure", () -> new SlipperySandOceanAbandonnedStructure(NoFeatureConfig.field_236558_a_));
	
	 public static void setupStructures()
	 {
		 setupMapSpacingAndLand(
				    OVERWORLD_ABANDONNED_PORTAL_STRUCTURE.get(), /* The instance of the structure */
	                new StructureSeparationSettings(25 /* mean distance apart in chunks between spawn attempts */,
	                        15 /* minimum distance apart in chunks between spawn attempts */,
	                        166754456 /* seed */),
	                false); //TransformSurroundingLand
		 
		 setupMapSpacingAndLand(
	        		MUD_DUNGEON_STRUCTURE.get(), /* The instance of the structure */
	                new StructureSeparationSettings(4 /* mean distance apart in chunks between spawn attempts */,
	                        3 /* minimum distance apart in chunks between spawn attempts */,
	                        1236548790 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
	                false); //TransformSurroundingLand
	        
	        setupMapSpacingAndLand(
	        		LUNATIC_TEMPLE_STRUCTURE.get(), /* The instance of the structure */
	                new StructureSeparationSettings(30 /* mean distance apart in chunks between spawn attempts */,
	                        25 /* minimum distance apart in chunks between spawn attempts */,
	                        993252541 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
	                true); //TransformSurroundingLand
	        
	        setupMapSpacingAndLand(
	        		GOLDEN_NETHER_PRISON_STRUCTURE.get(), /* The instance of the structure */
	                new StructureSeparationSettings(35 /* mean distance apart in chunks between spawn attempts */,
	                        25 /* minimum distance apart in chunks between spawn attempts */,
	                        123334456 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
	                false); //TransformSurroundingLand
	        
	        setupMapSpacingAndLand(
	        		STELLAR_STONE_BRICKS_TOWER_STRUCTURE.get(), /* The instance of the structure */
	                new StructureSeparationSettings(12 /* mean distance apart in chunks between spawn attempts */,
	                        5 /* minimum distance apart in chunks between spawn attempts */,
	                        223454456 /* seed */),
	                false); //TransformSurroundingLand
	        
	        setupMapSpacingAndLand(
	        		COPPER_PINE_COTTAGE_STRUCTURE.get(), /* The instance of the structure */
	                new StructureSeparationSettings(6 /* mean distance apart in chunks between spawn attempts */,
	                        3 /* minimum distance apart in chunks between spawn attempts */,
	                        223454456 /* seed */),
	                false); //TransformSurroundingLand - This structure uses Stellar Bricks Tower "ground" (support) as surrounding land
	        
	        setupMapSpacingAndLand(
	        		SLIPPERY_SAND_OCEAN_ABANDONNED_STRUCTURE.get(), /* The instance of the structure */
	                new StructureSeparationSettings(2 /* mean distance apart in chunks between spawn attempts */,
	                        1 /* minimum distance apart in chunks between spawn attempts */,
	                        223454456 /* seed */),
	                false); //TransformSurroundingLand
	 }
	 
	 
	

	   public static <F extends Structure<?>> void setupMapSpacingAndLand(F structure,StructureSeparationSettings structureSeparationSettings,boolean transformSurroundingLand)
	   {
	        Structure.NAME_STRUCTURE_BIMAP.put(structure.getRegistryName().toString(), structure);

	        if(transformSurroundingLand)
	        {
	            Structure.field_236384_t_ =
	                    ImmutableList.<Structure<?>>builder()
	                            .addAll(Structure.field_236384_t_)
	                            .add(structure)
	                            .build();
	        }

	        DimensionStructuresSettings.field_236191_b_ =
	                ImmutableMap.<Structure<?>, StructureSeparationSettings>builder()
	                        .putAll(DimensionStructuresSettings.field_236191_b_)
	                        .put(structure, structureSeparationSettings)
	                        .build();

	        WorldGenRegistries.NOISE_SETTINGS.getEntries().forEach(settings ->
	        {
	            Map<Structure<?>, StructureSeparationSettings> structureMap = settings.getValue().getStructures().func_236195_a_();

	            /*
	                field_236193_d_ requires AccessTransformer  (See resources/META-INF/accesstransformer.cfg, and see build.gradle do say it to gradle)
	                I had a problem with the accesstransformer.cfg, I had to remove the project from eclipse, run "gradlew eclipse genEclipseRuns" in a command prompt, and re-open the project.
	            */
	            if(structureMap instanceof ImmutableMap)
	            {
	                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
	                tempMap.put(structure, structureSeparationSettings);
	                settings.getValue().getStructures().field_236193_d_ = tempMap;
	            }
	            else
	            {
	                structureMap.put(structure, structureSeparationSettings);
	            }
	        });
	   }
}
