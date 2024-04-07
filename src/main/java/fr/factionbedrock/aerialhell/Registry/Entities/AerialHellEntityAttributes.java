package fr.factionbedrock.aerialhell.Registry.Entities;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit.AbstractElementSpiritEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.*;
import fr.factionbedrock.aerialhell.Entity.Monster.*;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.*;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.*;
import fr.factionbedrock.aerialhell.Entity.Monster.Flying.FlyingJellyfishEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.*;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.*;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowAutomatonEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowFlyingSkullEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowTrollEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Spider.CrystalSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Spider.HellSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Neutral.BoarEntity;
import fr.factionbedrock.aerialhell.Entity.Neutral.ForestCaterpillarEntity;
import fr.factionbedrock.aerialhell.Entity.Passive.*;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = AerialHell.MODID, bus = Bus.MOD)
public class AerialHellEntityAttributes
{
	@SubscribeEvent
	public static void entityAttributes(EntityAttributeCreationEvent event)
	{
		/*
		Do not forget to register the attributes ! Entity with no attributes registered can't be summoned
		*/
		event.put(AerialHellEntities.STELLAR_STONE_AUTOMATON.get(), StellarStoneAutomatonEntity.registerAttributes().build());
		event.put(AerialHellEntities.MUD_GOLEM.get(), MudGolemEntity.registerAttributes().build());
		event.put(AerialHellEntities.MUD_SPECTRAL_GOLEM.get(), MudSpectralGolemEntity.registerAttributes().build());
		event.put(AerialHellEntities.CRYSTAL_GOLEM.get(), CrystalGolemEntity.registerAttributes().build());
		event.put(AerialHellEntities.LUNATIC_PRIEST.get(), LunaticPriestEntity.registerAttributes().build());
		event.put(AerialHellEntities.EVIL_COW.get(), EvilCowEntity.registerAttributes().build());
		event.put(AerialHellEntities.CORTINARIUS_COW.get(), EvilCowEntity.registerAttributes().build());
		event.put(AerialHellEntities.STELLAR_ENT.get(), EntEntity.registerAttributes().build());
		event.put(AerialHellEntities.VENOMOUS_SNAKE.get(), SnakeEntity.registerAttributes().build());
		event.put(AerialHellEntities.STELLAR_CHICKEN.get(), StellarChickenEntity.registerAttributes().build());
		event.put(AerialHellEntities.STELLAR_BOAR.get(), BoarEntity.registerAttributes().build());
		event.put(AerialHellEntities.SHROOMBOOM.get(), ShroomBoomEntity.registerAttributes().build());
		event.put(AerialHellEntities.VERDIGRIS_ZOMBIE.get(), VerdigrisZombieEntity.registerAttributes().build());
		event.put(AerialHellEntities.SLIME_PIRATE.get(), SlimePirateEntity.registerAttributes().build());
		event.put(AerialHellEntities.SLIME_NINJA_PIRATE.get(), SlimeNinjaPirateEntity.registerAttributes().build());
		event.put(AerialHellEntities.GHOST_SLIME_PIRATE.get(), GhostSlimePirateEntity.registerAttributes().build());
		event.put(AerialHellEntities.GHOST_SLIME_NINJA_PIRATE.get(), GhostSlimeNinjaPirateEntity.registerAttributes().build());
		event.put(AerialHellEntities.SANDY_SHEEP.get(), SandySheepEntity.registerAttributes().build());
		event.put(AerialHellEntities.GLIDING_TURTLE.get(), GlidingTurtleEntity.registerAttributes().build());
		event.put(AerialHellEntities.FAT_PHANTOM.get(), FatPhantomEntity.registerAttributes().build());
		event.put(AerialHellEntities.KODAMA.get(), KodamaEntity.registerAttributes().build());
		event.put(AerialHellEntities.CRYSTAL_SLIME.get(), CrystalSlimeEntity.registerAttributes().build());
		event.put(AerialHellEntities.MUD_SOLDIER.get(), MudSoldierEntity.registerAttributes().build());
		event.put(AerialHellEntities.MUD_SPECTRAL_SOLDIER.get(), MudSpectralSoldierEntity.registerAttributes().build());
		event.put(AerialHellEntities.MUD_CYCLE_MAGE.get(), MudCycleMageEntity.registerAttributes().build());
		event.put(AerialHellEntities.MUD_SPECTRAL_CYCLE_MAGE.get(), MudSpectralCycleMageEntity.registerAttributes().build());
		event.put(AerialHellEntities.TORN_SPIRIT.get(), TornSpiritEntity.registerAttributes().build());
		event.put(AerialHellEntities.ICE_SPIRIT.get(), AbstractElementSpiritEntity.registerAttributes().build());
		event.put(AerialHellEntities.FIRE_SPIRIT.get(), AbstractElementSpiritEntity.registerAttributes().build());
		event.put(AerialHellEntities.ELECTRO_SPIRIT.get(), AbstractElementSpiritEntity.registerAttributes().build());
		event.put(AerialHellEntities.CHAINED_GOD.get(), ChainedGodEntity.registerAttributes().build());
		event.put(AerialHellEntities.FLYING_JELLYFISH.get(), FlyingJellyfishEntity.registerAttributes().build());
		event.put(AerialHellEntities.SHADOW_FLYING_SKULL.get(), ShadowFlyingSkullEntity.registerAttributes().build());
		event.put(AerialHellEntities.SHADOW_TROLL.get(), ShadowTrollEntity.registerAttributes().build());
		event.put(AerialHellEntities.SHADOW_AUTOMATON.get(), ShadowAutomatonEntity.registerAttributes().build());
		event.put(AerialHellEntities.AERIAL_TREE_MIMIC.get(), AerialTreeChestMimicEntity.registerAttributes().build());
		event.put(AerialHellEntities.GOLDEN_BEECH_MIMIC.get(), GoldenBeechChestMimicEntity.registerAttributes().build());
		event.put(AerialHellEntities.SKY_CACTUS_FIBER_MIMIC.get(), SkyCactusFiberChestMimicEntity.registerAttributes().build());
		event.put(AerialHellEntities.COPPER_PINE_MIMIC.get(), CopperPineChestMimicEntity.registerAttributes().build());
		event.put(AerialHellEntities.SHADOW_PINE_MIMIC.get(), ShadowPineBarrelMimicEntity.registerAttributes().build());
		event.put(AerialHellEntities.HELL_SPIDER.get(), HellSpiderEntity.registerAttributes().build());
		event.put(AerialHellEntities.CRYSTAL_SPIDER.get(), CrystalSpiderEntity.registerAttributes().build());
		event.put(AerialHellEntities.LILITH.get(), LilithEntity.registerAttributes().build());
		event.put(AerialHellEntities.SHADOW_SPIDER.get(), ShadowSpiderEntity.registerAttributes().build());
		event.put(AerialHellEntities.FOREST_CATERPILLAR.get(), ForestCaterpillarEntity.registerAttributes().build());
		event.put(AerialHellEntities.CRYSTAL_CATERPILLAR.get(), CrystalCaterpillarEntity.registerAttributes().build());
	}
}
