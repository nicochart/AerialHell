package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Bosses.MudCycleMageEntity;
import fr.factionbedrock.aerialhell.Entity.AbstractElementSpiritEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ShadowTrollEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.MudGolemEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.GoldenWalkerEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.HellSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.MudSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.MudSpectralGolemEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.MudSpectralSoldierEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.CrystalCaterpillarEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.CrystalGolemEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.CrystalSlimeEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.CrystalSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.EvilCowEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.FlyingJellyfishEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.AerialTreeChestMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.CopperPineChestMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.GoldenBeechChestMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.SkyCactusFiberChestMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Neutral.ForestCaterpillarEntity;
import fr.factionbedrock.aerialhell.Entity.Passive.FatPhantomEntity;
import fr.factionbedrock.aerialhell.Entity.Passive.SandySheepEntity;
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
		event.put(AerialHellEntities.GOLDEN_WALKER.get(), GoldenWalkerEntity.registerAttributes().create());
		event.put(AerialHellEntities.MUD_GOLEM.get(), MudGolemEntity.registerAttributes().create());
		event.put(AerialHellEntities.MUD_SPECTRAL_GOLEM.get(), MudSpectralGolemEntity.registerAttributes().create());
		event.put(AerialHellEntities.CRYSTAL_GOLEM.get(), CrystalGolemEntity.registerAttributes().create());
		event.put(AerialHellEntities.LUNATIC_PRIEST.get(), LunaticPriestEntity.registerAttributes().create());
		event.put(AerialHellEntities.EVIL_COW.get(), EvilCowEntity.registerAttributes().create());
		event.put(AerialHellEntities.SANDY_SHEEP.get(), SandySheepEntity.registerAttributes().create());
		event.put(AerialHellEntities.FAT_PHANTOM.get(), FatPhantomEntity.registerAttributes().create());
		event.put(AerialHellEntities.CRYSTAL_SLIME.get(), CrystalSlimeEntity.registerAttributes().create());
		event.put(AerialHellEntities.MUD_SOLDIER.get(), MudSoldierEntity.registerAttributes().create());
		event.put(AerialHellEntities.MUD_SPECTRAL_SOLDIER.get(), MudSpectralSoldierEntity.registerAttributes().create());
		event.put(AerialHellEntities.MUD_CYCLE_MAGE.get(), MudCycleMageEntity.registerAttributes().create());
		event.put(AerialHellEntities.TORN_SPIRIT.get(), TornSpiritEntity.registerAttributes().create());
		event.put(AerialHellEntities.ICE_SPIRIT.get(), AbstractElementSpiritEntity.registerAttributes().create());
		event.put(AerialHellEntities.FIRE_SPIRIT.get(), AbstractElementSpiritEntity.registerAttributes().create());
		event.put(AerialHellEntities.ELECTRO_SPIRIT.get(), AbstractElementSpiritEntity.registerAttributes().create());
		event.put(AerialHellEntities.CHAINED_GOD.get(), ChainedGodEntity.registerAttributes().create());
		event.put(AerialHellEntities.FLYING_JELLYFISH.get(), FlyingJellyfishEntity.registerAttributes().create());
		event.put(AerialHellEntities.SHADOW_TROLL.get(), ShadowTrollEntity.registerAttributes().create());
		event.put(AerialHellEntities.AERIAL_TREE_MIMIC.get(), AerialTreeChestMimicEntity.registerAttributes().create());
		event.put(AerialHellEntities.GOLDEN_BEECH_MIMIC.get(), GoldenBeechChestMimicEntity.registerAttributes().create());
		event.put(AerialHellEntities.SKY_CACTUS_FIBER_MIMIC.get(), SkyCactusFiberChestMimicEntity.registerAttributes().create());
		event.put(AerialHellEntities.COPPER_PINE_MIMIC.get(), CopperPineChestMimicEntity.registerAttributes().create());
		event.put(AerialHellEntities.HELL_SPIDER.get(), HellSpiderEntity.registerAttributes().create());
		event.put(AerialHellEntities.CRYSTAL_SPIDER.get(), CrystalSpiderEntity.registerAttributes().create());
		event.put(AerialHellEntities.FOREST_CATERPILLAR.get(), ForestCaterpillarEntity.registerAttributes().create());
		event.put(AerialHellEntities.CRYSTAL_CATERPILLAR.get(), CrystalCaterpillarEntity.registerAttributes().create());
	}
}
