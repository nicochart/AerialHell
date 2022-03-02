package fr.factionbedrock.aerialhell.Registry;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import fr.factionbedrock.aerialhell.World.Structure.BigWhiteAercloudStructure;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellStructures
{
	public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, MODID);
	
	public static final RegistryObject<Structure<NoFeatureConfig>> BIG_SOLID_ETHER_CLOUD_STRUCTURE = STRUCTURES.register("big_solid_ether_cloud", () -> new BigWhiteAercloudStructure(NoFeatureConfig.field_236558_a_));
	 
	 public static void setupStructures()
	 {	        
	        setupMapSpacingAndLand(
	        		BIG_SOLID_ETHER_CLOUD_STRUCTURE.get(), /* The instance of the structure */
	                new StructureSeparationSettings(10 /* maximum distance apart in chunks between spawn attempts */,
	                        2 /* minimum distance apart in chunks between spawn attempts */,
	                        1234567890 /* this modifies the seed of the structure so no two structures always spawn over each-other. Make this large and unique. */),
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
