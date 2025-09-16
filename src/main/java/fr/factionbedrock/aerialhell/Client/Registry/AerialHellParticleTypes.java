package fr.factionbedrock.aerialhell.Client.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Particle.*;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.CampfireSmokeParticle;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

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


	public static SimpleParticleType register(String name, SimpleParticleType type) {return Registry.register(Registries.PARTICLE_TYPE, AerialHell.id(name), type);}

	public static void load() {}

	public static void registerParticleFactories()
	{
		ParticleFactoryRegistry.getInstance().register(AERIAL_HELL_PORTAL, AerialHellPortalParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(LUNATIC_PARTICLE, LightParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(COPPER_PINE_LEAVES, CopperPineLeavesParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(OSCILLATOR, OscillatorParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(SNOWFLAKE, SnowFlakeParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(GOD_FLAME, FlameParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(SHADOW_TROLL_BAT, ShadowParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(SHADOW_PARTICLE, ShadowParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(SHADOW_LIGHT, LightParticle.Factory::new);
		ParticleFactoryRegistry.getInstance().register(FAT_PHANTOM_SMOKE, CampfireSmokeParticle.CosySmokeFactory::new);
		ParticleFactoryRegistry.getInstance().register(FALLING_CRYSTALLIZED_LEAVES, FallingCrystallizedLeavesParticle.Factory::new);
	}
}
