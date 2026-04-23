package fr.factionbedrock.aerialhell.Client.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Particle.*;
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.CampfireSmokeParticle;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public class AerialHellParticleTypes
{
	public static final SimpleParticleType AERIAL_HELL_PORTAL = register("aerial_hell_portal", FabricParticleTypes.simple(false));
	public static final SimpleParticleType LUNATIC_PARTICLE = register("lunatic_particle", FabricParticleTypes.simple(false));
	public static final SimpleParticleType COPPER_PINE_LEAVES = register("copper_pine_leaves", FabricParticleTypes.simple(false));
	public static final SimpleParticleType OSCILLATOR = register("oscillator", FabricParticleTypes.simple(false));
	public static final SimpleParticleType SNOWFLAKE = register("snowflake", FabricParticleTypes.simple(false));
	public static final SimpleParticleType GOD_FLAME = register("god_flame", FabricParticleTypes.simple(false));
	public static final SimpleParticleType SHADOW_TROLL_BAT = register("shadow_troll_bat", FabricParticleTypes.simple(false));
	public static final SimpleParticleType SHADOW_PARTICLE = register("shadow_particle", FabricParticleTypes.simple(false));
	public static final SimpleParticleType SHADOW_LIGHT = register("shadow_light", FabricParticleTypes.simple(false));
	public static final SimpleParticleType FAT_PHANTOM_SMOKE = register("fat_phantom_smoke", FabricParticleTypes.simple(false));
	public static final SimpleParticleType FALLING_CRYSTALLIZED_LEAVES = register("falling_crystallized_leaves", FabricParticleTypes.simple(false));


	public static SimpleParticleType register(String name, SimpleParticleType type) {return Registry.register(BuiltInRegistries.PARTICLE_TYPE, AerialHell.id(name), type);}

	public static void load() {}

	public static void registerParticleFactories()
	{
		ParticleProviderRegistry.getInstance().register(AERIAL_HELL_PORTAL, AerialHellPortalParticle.Factory::new);
		ParticleProviderRegistry.getInstance().register(LUNATIC_PARTICLE, LightParticle.Factory::new);
		ParticleProviderRegistry.getInstance().register(COPPER_PINE_LEAVES, CopperPineLeavesParticle.Factory::new);
		ParticleProviderRegistry.getInstance().register(OSCILLATOR, OscillatorParticle.Factory::new);
		ParticleProviderRegistry.getInstance().register(SNOWFLAKE, SnowFlakeParticle.Factory::new);
		ParticleProviderRegistry.getInstance().register(GOD_FLAME, FlameParticle.Provider::new);
		ParticleProviderRegistry.getInstance().register(SHADOW_TROLL_BAT, ShadowParticle.Factory::new);
		ParticleProviderRegistry.getInstance().register(SHADOW_PARTICLE, ShadowParticle.Factory::new);
		ParticleProviderRegistry.getInstance().register(SHADOW_LIGHT, LightParticle.Factory::new);
		ParticleProviderRegistry.getInstance().register(FAT_PHANTOM_SMOKE, CampfireSmokeParticle.CosyProvider::new);
		ParticleProviderRegistry.getInstance().register(FALLING_CRYSTALLIZED_LEAVES, FallingCrystallizedLeavesParticle.Factory::new);
	}
}
