package fr.factionbedrock.aerialhell.Client.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Particle.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.CampfireSmokeParticle;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AerialHellParticleTypes
{
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, AerialHell.MODID);

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> AERIAL_HELL_PORTAL = PARTICLES.register("aerial_hell_portal", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> LUNATIC_PARTICLE = PARTICLES.register("lunatic_particle", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> COPPER_PINE_LEAVES = PARTICLES.register("copper_pine_leaves", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> OSCILLATOR = PARTICLES.register("oscillator", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SNOWFLAKE = PARTICLES.register("snowflake", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GOD_FLAME = PARTICLES.register("god_flame", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SHADOW_TROLL_BAT = PARTICLES.register("shadow_troll_bat", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SHADOW_PARTICLE = PARTICLES.register("shadow_particle", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SHADOW_LIGHT = PARTICLES.register("shadow_light", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FAT_PHANTOM_SMOKE = PARTICLES.register("fat_phantom_smoke", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> FALLING_CRYSTALLIZED_LEAVES = PARTICLES.register("falling_crystallized_leaves", () -> new SimpleParticleType(false));

	public static void registerParticleFactories(RegisterParticleProvidersEvent event)
	{
		ParticleEngine particleManager = Minecraft.getInstance().particleEngine;

		particleManager.register(AERIAL_HELL_PORTAL.get(), AerialHellPortalParticle.Factory::new);
		particleManager.register(LUNATIC_PARTICLE.get(), LightParticle.Factory::new);
		particleManager.register(COPPER_PINE_LEAVES.get(), CopperPineLeavesParticle.Factory::new);
		particleManager.register(OSCILLATOR.get(), OscillatorParticle.Factory::new);
		particleManager.register(SNOWFLAKE.get(), SnowFlakeParticle.Factory::new);
		particleManager.register(GOD_FLAME.get(), FlameParticle.Provider::new);
		particleManager.register(SHADOW_TROLL_BAT.get(), ShadowParticle.Factory::new);
		particleManager.register(SHADOW_PARTICLE.get(), ShadowParticle.Factory::new);
		particleManager.register(SHADOW_LIGHT.get(), LightParticle.Factory::new);
		particleManager.register(FAT_PHANTOM_SMOKE.get(), CampfireSmokeParticle.CosyProvider::new);
		particleManager.register(FALLING_CRYSTALLIZED_LEAVES.get(), FallingCrystallizedLeavesParticle.Factory::new);
	}
}
