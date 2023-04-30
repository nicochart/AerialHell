package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.World.Carver.AerialHellCaveWorldCarver;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellCarvers
{
	public static final DeferredRegister<WorldCarver<?>> CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, AerialHell.MODID);

    public static final RegistryObject<WorldCarver<ProbabilityConfig>> AERIALHELL_CARVER = CARVERS.register("aerialhell_cave", () -> new AerialHellCaveWorldCarver(ProbabilityConfig.CODEC, 256));
    
    public static void registerConfiguredCarvers()
    {
    	Registry<ConfiguredCarver<?>> CCregistry = WorldGenRegistries.CONFIGURED_CARVER;
    	
    	Registry.register(CCregistry, new ResourceLocation(AerialHell.MODID, "aerialhell_cave"), AERIALHELL_CARVER.get().func_242761_a(new ProbabilityConfig(0.7F)));
    }
}
