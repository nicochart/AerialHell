package fr.factionbedrock.aerialhell.Registry.Entities;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.AbstractElementSpiritEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.*;
import fr.factionbedrock.aerialhell.Entity.Monster.*;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.*;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.*;
import fr.factionbedrock.aerialhell.Entity.Neutral.ForestCaterpillarEntity;
import fr.factionbedrock.aerialhell.Entity.Passive.*;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
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
		Do not forget to register the attributes ! If you forget and try to summon the entity, this exception will pop :
		java.lang.NullPointerException: Cannot invoke "net.minecraft.entity.ai.attributes.AttributeModifierMap.getAttributeValue(net.minecraft.entity.ai.attributes.Attribute)" because "this.attributeMap" is null
		Entity with no attributes registered and be summoned 
		*/
		event.put(AerialHellEntities.STELLAR_STONE_AUTOMATON.get(), StellarStoneAutomatonEntity.registerAttributes().build());
		event.put(AerialHellEntities.MUD_GOLEM.get(), MudGolemEntity.registerAttributes().build());
		event.put(AerialHellEntities.MUD_SPECTRAL_GOLEM.get(), MudSpectralGolemEntity.registerAttributes().build());
		event.put(AerialHellEntities.CRYSTAL_GOLEM.get(), CrystalGolemEntity.registerAttributes().build());
		event.put(AerialHellEntities.LUNATIC_PRIEST.get(), LunaticPriestEntity.registerAttributes().build());
		event.put(AerialHellEntities.EVIL_COW.get(), EvilCowEntity.registerAttributes().build());
		event.put(AerialHellEntities.CORTINARIUS_COW.get(), EvilCowEntity.registerAttributes().build());
		event.put(AerialHellEntities.SHROOMBOOM.get(), ShroomBoomEntity.registerAttributes().build());
		event.put(AerialHellEntities.VERDIGRIS_ZOMBIE.get(), VerdigrisZombieEntity.registerAttributes().build());
		event.put(AerialHellEntities.SANDY_SHEEP.get(), SandySheepEntity.registerAttributes().build());
		event.put(AerialHellEntities.GLIDING_TURTLE.get(), GlidingTurtleEntity.registerAttributes().build());
		event.put(AerialHellEntities.FAT_PHANTOM.get(), FatPhantomEntity.registerAttributes().build());
		event.put(AerialHellEntities.CRYSTAL_SLIME.get(), CrystalSlimeEntity.registerAttributes().build());
		event.put(AerialHellEntities.MUD_SOLDIER.get(), MudSoldierEntity.registerAttributes().build());
		event.put(AerialHellEntities.MUD_SPECTRAL_SOLDIER.get(), MudSpectralSoldierEntity.registerAttributes().build());
		event.put(AerialHellEntities.MUD_CYCLE_MAGE.get(), MudCycleMageEntity.registerAttributes().build());
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