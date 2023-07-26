package fr.factionbedrock.aerialhell.Client.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Particle.AerialHellPortalParticle;
import fr.factionbedrock.aerialhell.Client.Particle.CopperPineLeavesParticle;
import fr.factionbedrock.aerialhell.Client.Particle.LightParticle;
import fr.factionbedrock.aerialhell.Client.Particle.ShadowParticle;
import fr.factionbedrock.aerialhell.Client.Particle.SnowFlakeParticle;
import fr.factionbedrock.aerialhell.Client.Particle.OscillatorParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.CampfireSmokeParticle;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = AerialHell.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AerialHellParticleTypes
{
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, AerialHell.MODID);

	public static final RegistryObject<SimpleParticleType> AERIAL_HELL_PORTAL = PARTICLES.register("aerial_hell_portal", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> LUNATIC_PARTICLE = PARTICLES.register("lunatic_particle", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> COPPER_PINE_LEAVES = PARTICLES.register("copper_pine_leaves", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> OSCILLATOR = PARTICLES.register("oscillator", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> SNOWFLAKE = PARTICLES.register("snowflake", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> GOD_FLAME = PARTICLES.register("god_flame", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> SHADOW_TROLL_BAT = PARTICLES.register("shadow_troll_bat", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> SHADOW_PARTICLE = PARTICLES.register("shadow_particle", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> SHADOW_LIGHT = PARTICLES.register("shadow_light", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> FAT_PHANTOM_SMOKE = PARTICLES.register("fat_phantom_smoke", () -> new SimpleParticleType(false));
	
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
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
	}
}
