package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.BlockEntityRenderer.AerialHellChestBlockEntityRenderer;
import fr.factionbedrock.aerialhell.Client.BlockEntityRenderer.AerialHellChestMimicBlockEntityRenderer;
import fr.factionbedrock.aerialhell.Client.EntityModels.*;
import fr.factionbedrock.aerialhell.Client.EntityRender.*;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AerialHell.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RenderRegistrationListener
{
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(AerialHellEntities.STELLAR_STONE_AUTOMATON.get(), StellarStoneAutomatonRender::new);
        event.registerEntityRenderer(AerialHellEntities.MUD_GOLEM.get(), MudGolemRender::new);
        event.registerEntityRenderer(AerialHellEntities.MUD_SPECTRAL_GOLEM.get(), MudGolemRender::new);
        event.registerEntityRenderer(AerialHellEntities.CRYSTAL_GOLEM.get(), CrystalGolemRender::new);
        event.registerEntityRenderer(AerialHellEntities.LUNATIC_PRIEST.get(), LunaticPriestRender::new);
        event.registerEntityRenderer(AerialHellEntities.EVIL_COW.get(), EvilCowRender::new);
        event.registerEntityRenderer(AerialHellEntities.CORTINARIUS_COW.get(), CortinariusCowRender::new);
        event.registerEntityRenderer(AerialHellEntities.SHROOMBOOM.get(), ShroomBoomRender::new);
        event.registerEntityRenderer(AerialHellEntities.VERDIGRIS_ZOMBIE.get(), VerdigrisZombieRender::new);
        event.registerEntityRenderer(AerialHellEntities.SANDY_SHEEP.get(), SandySheepRender::new);
        event.registerEntityRenderer(AerialHellEntities.GLIDING_TURTLE.get(), GlidingTurtleRender::new);
        event.registerEntityRenderer(AerialHellEntities.FAT_PHANTOM.get(), FatPhantomRender::new);
        event.registerEntityRenderer(AerialHellEntities.CRYSTAL_SLIME.get(), CrystalSlimeRender::new);
        event.registerEntityRenderer(AerialHellEntities.MUD_SOLDIER.get(), MudSoldierRender::new);
        event.registerEntityRenderer(AerialHellEntities.MUD_SPECTRAL_SOLDIER.get(), MudSoldierRender::new);
        event.registerEntityRenderer(AerialHellEntities.MUD_CYCLE_MAGE.get(), MudCycleMageRender::new);
        event.registerEntityRenderer(AerialHellEntities.TORN_SPIRIT.get(), TornSpiritRender::new);
        event.registerEntityRenderer(AerialHellEntities.ICE_SPIRIT.get(), ElementSpiritRender::new);
        event.registerEntityRenderer(AerialHellEntities.FIRE_SPIRIT.get(), ElementSpiritRender::new);
        event.registerEntityRenderer(AerialHellEntities.ELECTRO_SPIRIT.get(), ElementSpiritRender::new);
        event.registerEntityRenderer(AerialHellEntities.CHAINED_GOD.get(), ChainedGodRender::new);
        event.registerEntityRenderer(AerialHellEntities.FLYING_JELLYFISH.get(), FlyingJellyfishRender::new);
        event.registerEntityRenderer(AerialHellEntities.SHADOW_FLYING_SKULL.get(), ShadowFlyingSkullRender::new);
        event.registerEntityRenderer(AerialHellEntities.SHADOW_TROLL.get(), ShadowTrollRender::new);
        event.registerEntityRenderer(AerialHellEntities.SHADOW_AUTOMATON.get(), ShadowAutomatonRender::new);
        event.registerEntityRenderer(AerialHellEntities.AERIAL_TREE_MIMIC.get(), ChestMimicRender::new);
        event.registerEntityRenderer(AerialHellEntities.GOLDEN_BEECH_MIMIC.get(), ChestMimicRender::new);
        event.registerEntityRenderer(AerialHellEntities.SKY_CACTUS_FIBER_MIMIC.get(), ChestMimicRender::new);
        event.registerEntityRenderer(AerialHellEntities.COPPER_PINE_MIMIC.get(), ChestMimicRender::new);
        event.registerEntityRenderer(AerialHellEntities.SHADOW_PINE_MIMIC.get(), SpiderBarrelMimicRender::new);
        event.registerEntityRenderer(AerialHellEntities.HELL_SPIDER.get(), HellSpiderRender::new);
        event.registerEntityRenderer(AerialHellEntities.CRYSTAL_SPIDER.get(), HellSpiderRender::new);
        event.registerEntityRenderer(AerialHellEntities.SHADOW_SPIDER.get(), HellSpiderRender::new);
        event.registerEntityRenderer(AerialHellEntities.LILITH.get(), LilithRender::new);
        event.registerEntityRenderer(AerialHellEntities.FOREST_CATERPILLAR.get(), CaterpillarRender::new);
        event.registerEntityRenderer(AerialHellEntities.CRYSTAL_CATERPILLAR.get(), CaterpillarRender::new);

        event.registerEntityRenderer(AerialHellEntities.IRON_SHURIKEN.get(), ShurikenRender::new);
        event.registerEntityRenderer(AerialHellEntities.GOLD_SHURIKEN.get(), ShurikenRender::new);
        event.registerEntityRenderer(AerialHellEntities.DIAMOND_SHURIKEN.get(), ShurikenRender::new);
        event.registerEntityRenderer(AerialHellEntities.NETHERITE_SHURIKEN.get(), ShurikenRender::new);
        event.registerEntityRenderer(AerialHellEntities.RUBY_SHURIKEN.get(), ShurikenRender::new);
        event.registerEntityRenderer(AerialHellEntities.AZURITE_SHURIKEN.get(), ShurikenRender::new);
        event.registerEntityRenderer(AerialHellEntities.MAGMATIC_GEL_SHURIKEN.get(), ShurikenRender::new);
        event.registerEntityRenderer(AerialHellEntities.VOLUCITE_SHURIKEN.get(), ShurikenRender::new);
        event.registerEntityRenderer(AerialHellEntities.OBSIDIAN_SHURIKEN.get(), ShurikenRender::new);
        event.registerEntityRenderer(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.get(), ShurikenRender::new);
        event.registerEntityRenderer(AerialHellEntities.ARSONIST_SHURIKEN.get(), ShurikenRender::new);
        event.registerEntityRenderer(AerialHellEntities.LIGHTNING_SHURIKEN.get(), ShurikenRender::new);
        event.registerEntityRenderer(AerialHellEntities.POISONBALL.get(), PoisonballProjectileRender::new);
        event.registerEntityRenderer(AerialHellEntities.VOLUCITE_BLOWPIPE_ARROW.get(), AerialArrowRenderer::new);
        event.registerEntityRenderer(AerialHellEntities.RUBY_BLOWPIPE_ARROW.get(), AerialArrowRenderer::new);
        event.registerEntityRenderer(AerialHellEntities.LUNATIC_PROJECTILE.get(), LightProjectileRender::new);
        event.registerEntityRenderer(AerialHellEntities.SHADOW_PROJECTILE.get(), LightProjectileRender::new);

        event.registerBlockEntityRenderer(AerialHellBlockEntities.CHEST.get(), AerialHellChestBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(AerialHellBlockEntities.CHEST_MIMIC.get(), AerialHellChestMimicBlockEntityRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(AerialHellModelLayers.CATERPILLAR, ForestCaterpillarModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.CHAINED_GOD, ChainedGodModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.CHEST_MIMIC, ChestMimicModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.CRYSTAL_GOLEM, CrystalGolemModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.CRYSTAL_SLIME, CrystalSlimeModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.ELEMENT_SPIRIT, ElementSpiritModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.FLYING_JELLYFISH, FlyingJellyfishModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.GLIDING_TURTLE, GlidingTurtleModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.LILITH, LilithModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.LUNATIC_PRIEST, LunaticPriestModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.MUD_CYCLE_MAGE, MudCycleMageModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.MUD_GOLEM, MudGolemModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.SANDY_SHEEP, SandySheepModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.SHADOW_FLYING_SKULL, ShadowFlyingSkullModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.SHADOW_TROLL, ShadowTrollModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.SHROOMBOOM, ShroomBoomModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.SPIDER_BARREL_MIMIC, SpiderBarrelMimicModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.AUTOMATON, AutomatonModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.TORN_SPIRIT, TornSpiritModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.VERDIGRIS_ZOMBIE, VerdigrisZombieModel::createBodyLayer);

        event.registerLayerDefinition(AerialHellModelLayers.CORTINARIUS_COW_SHROOM, CortinariusCowShroomModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.CRYSTAL_GOLEM_CRYSTAL, CrystalGolemCrystalModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.SPIDER_SPIKE, HellSpiderSpikeModel::createBodyLayer);
    }
}
