package fr.factionbedrock.aerialhell.Client.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Particle.AerialHellPortalParticle;
import fr.factionbedrock.aerialhell.Client.Particle.CopperPineLeavesParticle;
import fr.factionbedrock.aerialhell.Client.Particle.LunaticParticle;
import fr.factionbedrock.aerialhell.Client.Particle.ShadowParticle;
import fr.factionbedrock.aerialhell.Client.Particle.SnowFlakeParticle;
import fr.factionbedrock.aerialhell.Client.Particle.VibratorParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.CampfireParticle;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = AerialHell.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AerialHellParticleTypes
{
	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, AerialHell.MODID);

	public static final RegistryObject<BasicParticleType> AERIAL_HELL_PORTAL = PARTICLES.register("aerial_hell_portal", () -> new BasicParticleType(false));
	public static final RegistryObject<BasicParticleType> LUNATIC_PARTICLE = PARTICLES.register("lunatic_particle", () -> new BasicParticleType(false));
	public static final RegistryObject<BasicParticleType> COPPER_PINE_LEAVES = PARTICLES.register("copper_pine_leaves", () -> new BasicParticleType(false));
	public static final RegistryObject<BasicParticleType> VIBRATOR = PARTICLES.register("vibrator", () -> new BasicParticleType(false));
	public static final RegistryObject<BasicParticleType> SNOWFLAKE = PARTICLES.register("snowflake", () -> new BasicParticleType(false));
	public static final RegistryObject<BasicParticleType> GOD_FLAME = PARTICLES.register("god_flame", () -> new BasicParticleType(false));
	public static final RegistryObject<BasicParticleType> SHADOW_TROLL_BAT = PARTICLES.register("shadow_troll_bat", () -> new BasicParticleType(false));
	public static final RegistryObject<BasicParticleType> SHADOW_PARTICLE = PARTICLES.register("shadow_particle", () -> new BasicParticleType(false));
	public static final RegistryObject<BasicParticleType> FAT_PHANTOM_SMOKE = PARTICLES.register("fat_phantom_smoke", () -> new BasicParticleType(false));
	
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void registerParticleFactories(ParticleFactoryRegisterEvent event)
	{
		ParticleManager particleManager = Minecraft.getInstance().particles;

		particleManager.registerFactory(AERIAL_HELL_PORTAL.get(), AerialHellPortalParticle.Factory::new);
		particleManager.registerFactory(LUNATIC_PARTICLE.get(), LunaticParticle.Factory::new);
		particleManager.registerFactory(COPPER_PINE_LEAVES.get(), CopperPineLeavesParticle.Factory::new);
		particleManager.registerFactory(VIBRATOR.get(), VibratorParticle.Factory::new);
		particleManager.registerFactory(SNOWFLAKE.get(), SnowFlakeParticle.Factory::new);
		particleManager.registerFactory(GOD_FLAME.get(), FlameParticle.Factory::new);
		particleManager.registerFactory(SHADOW_TROLL_BAT.get(), ShadowParticle.Factory::new);
		particleManager.registerFactory(SHADOW_PARTICLE.get(), ShadowParticle.Factory::new);
		particleManager.registerFactory(FAT_PHANTOM_SMOKE.get(), CampfireParticle.CozySmokeFactory::new);
	}
}
