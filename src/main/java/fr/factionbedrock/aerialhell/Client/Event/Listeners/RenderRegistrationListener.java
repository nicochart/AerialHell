package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.Client.BlockEntityRenderer.AerialHellChestBlockEntityRenderer;
import fr.factionbedrock.aerialhell.Client.BlockEntityRenderer.AerialHellChestMimicBlockEntityRenderer;
import fr.factionbedrock.aerialhell.Client.EntityModels.*;
import fr.factionbedrock.aerialhell.Client.EntityRender.*;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;

public class RenderRegistrationListener
{
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(AerialHellEntities.STELLAR_STONE_AUTOMATON.get(), StellarStoneAutomatonRender::new);
        event.registerEntityRenderer(AerialHellEntities.MUD_GOLEM.get(), MudGolemRender::new);
        event.registerEntityRenderer(AerialHellEntities.MUD_SPECTRAL_GOLEM.get(), MudGolemRender::new);
        event.registerEntityRenderer(AerialHellEntities.CRYSTAL_GOLEM.get(), CrystalGolemRender::new);
        event.registerEntityRenderer(AerialHellEntities.LUNATIC_PRIEST.get(), LunaticPriestRender::new);
        event.registerEntityRenderer(AerialHellEntities.EVIL_COW.get(), EvilCowRender::new);
        event.registerEntityRenderer(AerialHellEntities.STELLAR_ENT.get(), EntRender::new);
        event.registerEntityRenderer(AerialHellEntities.VENOMOUS_SNAKE.get(), SnakeRender::new);
        event.registerEntityRenderer(AerialHellEntities.WORM.get(), SnakeRender::new);
        event.registerEntityRenderer(AerialHellEntities.CORTINARIUS_COW.get(), CortinariusCowRender::new);
        event.registerEntityRenderer(AerialHellEntities.STELLAR_CHICKEN.get(), StellarChickenRender::new);
        event.registerEntityRenderer(AerialHellEntities.STELLAR_BOAR.get(), BoarRender::new);
        event.registerEntityRenderer(AerialHellEntities.SHROOMBOOM.get(), ShroomBoomRender::new);
        event.registerEntityRenderer(AerialHellEntities.VERDIGRIS_ZOMBIE.get(), VerdigrisZombieRender::new);
        event.registerEntityRenderer(AerialHellEntities.MUMMY.get(), HumanoidTwoLayerRender::new);
        event.registerEntityRenderer(AerialHellEntities.SLIME_PIRATE.get(), HumanoidTwoLayerRender::new);
        event.registerEntityRenderer(AerialHellEntities.SLIME_NINJA_PIRATE.get(), HumanoidTwoLayerRender::new);
        event.registerEntityRenderer(AerialHellEntities.GHOST_SLIME_PIRATE.get(), HumanoidTwoLayerRender::new);
        event.registerEntityRenderer(AerialHellEntities.GHOST_SLIME_NINJA_PIRATE.get(), HumanoidTwoLayerRender::new);
        event.registerEntityRenderer(AerialHellEntities.SANDY_SHEEP.get(), SandySheepRender::new);
        event.registerEntityRenderer(AerialHellEntities.GLIDING_TURTLE.get(), GlidingTurtleRender::new);
        event.registerEntityRenderer(AerialHellEntities.FAT_PHANTOM.get(), FatPhantomRender::new);
        event.registerEntityRenderer(AerialHellEntities.KODAMA.get(), KodamaRender::new);
        event.registerEntityRenderer(AerialHellEntities.CRYSTAL_SLIME.get(), CrystalSlimeRender::new);
        event.registerEntityRenderer(AerialHellEntities.MUD_SOLDIER.get(), MudSoldierRender::new);
        event.registerEntityRenderer(AerialHellEntities.MUD_SPECTRAL_SOLDIER.get(), MudSoldierRender::new);
        event.registerEntityRenderer(AerialHellEntities.MUD_CYCLE_MAGE.get(), MudCycleMageRender::new);
        event.registerEntityRenderer(AerialHellEntities.MUD_SPECTRAL_CYCLE_MAGE.get(), MudCycleMageRender::new);
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
        event.registerEntityRenderer(AerialHellEntities.FOREST_CATERPILLAR.get(), ForestCaterpillarRender::new);
        event.registerEntityRenderer(AerialHellEntities.CRYSTAL_CATERPILLAR.get(), CaterpillarRender::new);

        event.registerEntityRenderer(AerialHellEntities.THROWN_STELLAR_EGG.get(), ThrownItemRenderer::new);
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
        event.registerEntityRenderer(AerialHellEntities.POISONBALL.get(), FireballLikeProjectileRender::new);
        event.registerEntityRenderer(AerialHellEntities.DIMENSION_SHATTERER_PROJECTILE.get(), FireballLikeProjectileRender::new);
        event.registerEntityRenderer(AerialHellEntities.VOLUCITE_BLOWPIPE_ARROW.get(), AerialArrowRenderer::new);
        event.registerEntityRenderer(AerialHellEntities.RUBY_BLOWPIPE_ARROW.get(), AerialArrowRenderer::new);
        event.registerEntityRenderer(AerialHellEntities.LUNATIC_PROJECTILE.get(), LightProjectileRender::new);
        event.registerEntityRenderer(AerialHellEntities.SHADOW_PROJECTILE.get(), LightProjectileRender::new);
        event.registerEntityRenderer(AerialHellEntities.AERIAL_HELL_PAINTING.get(), AerialHellPaintingRender::new);

        event.registerBlockEntityRenderer(AerialHellBlockEntities.CHEST.get(), AerialHellChestBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(AerialHellBlockEntities.CHEST_MIMIC.get(), AerialHellChestMimicBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(AerialHellBlockEntities.SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(AerialHellBlockEntities.HANGING_SIGN.get(), HangingSignRenderer::new);
    }

    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(AerialHellModelLayers.CATERPILLAR, ForestCaterpillarModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.BOAR, BoarModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.ENT, EntModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.SNAKE, SnakeModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.STELLAR_CHICKEN, StellarChickenModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.CHAINED_GOD, ChainedGodModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.CHEST_MIMIC, ChestMimicModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.CRYSTAL_GOLEM, CrystalGolemModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.CRYSTAL_SLIME, CrystalSlimeModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.ELEMENT_SPIRIT, ElementSpiritModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.FLYING_JELLYFISH, FlyingJellyfishModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.GLIDING_TURTLE, GlidingTurtleModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.KODAMA, KodamaModel::createBodyLayer);
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
        event.registerLayerDefinition(AerialHellModelLayers.SLIME_PIRATE, HumanoidTwoLayerModel::createBodyLayer);

        event.registerLayerDefinition(AerialHellModelLayers.CORTINARIUS_COW_SHROOM, CortinariusCowShroomModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.CRYSTAL_GOLEM_CRYSTAL, CrystalGolemCrystalModel::createBodyLayer);
        event.registerLayerDefinition(AerialHellModelLayers.SPIDER_SPIKE, HellSpiderSpikeModel::createBodyLayer);

        event.registerLayerDefinition(AerialHellModelLayers.EMPTY, EmptyModel::createBodyLayer);
    }

    public static void onModelBake(ModelEvent.ModifyBakingResult event)
    {
        /* TODO
        ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocksAndItems.STELLAR_STONE_CRYSTAL_BLOCK.get(), event, ShiftingBlockBakedModel.TRANSLUCENT);
        ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocksAndItems.SHADOW_CRYSTAL_BLOCK.get(), event, ShiftingBlockBakedModel.TRANSLUCENT);
        ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocksAndItems.STELLAR_GRASS.get(), event, ShiftingBlockBakedModel.CUTOUT);
        ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocksAndItems.SHADOW_GRASS.get(), event, ShiftingBlockBakedModel.CUTOUT);
        ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocksAndItems.STELLAR_GRASS_BALL.get(), event, ShiftingBlockBakedModel.CUTOUT);
        ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocksAndItems.SHADOW_GRASS_BALL.get(), event, ShiftingBlockBakedModel.CUTOUT);
        ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocksAndItems.BRAMBLES.get(), event, ShiftingBlockBakedModel.CUTOUT);
        ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocksAndItems.SHADOW_BRAMBLES.get(), event, ShiftingBlockBakedModel.CUTOUT);
        ShiftedModelRenderHelper.createAndRegisterGrowingPlantHeadBlock(AerialHellBlocksAndItems.GLOWING_ROOTS.get(), event);
        ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocksAndItems.GLOWING_ROOTS_PLANT.get(), event, ShiftingBlockBakedModel.CUTOUT);
        ShiftedModelRenderHelper.createAndRegisterGrowingPlantHeadBlock(AerialHellBlocksAndItems.SHADOW_GLOWING_ROOTS.get(), event);
        ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocksAndItems.SHADOW_GLOWING_ROOTS_PLANT.get(), event, ShiftingBlockBakedModel.CUTOUT);
        ShiftedModelRenderHelper.createAndRegisterGrassBlockShiftedRender(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get(), event);
        ShiftedModelRenderHelper.createAndRegisterGrassBlockShiftedRender(AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocksAndItems.AERIAL_TREE_LEAVES.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocksAndItems.SHADOW_AERIAL_TREE_LEAVES.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocksAndItems.GOLDEN_BEECH_LEAVES.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocksAndItems.SHADOW_GOLDEN_BEECH_LEAVES.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocksAndItems.COPPER_PINE_LEAVES.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocksAndItems.SHADOW_COPPER_PINE_LEAVES.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocksAndItems.LAPIS_ROBINIA_LEAVES.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocksAndItems.SHADOW_LAPIS_ROBINIA_LEAVES.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocksAndItems.SHADOW_PINE_LEAVES.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocksAndItems.HOLLOW_SHADOW_PINE_LEAVES.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocksAndItems.PURPLE_SHADOW_PINE_LEAVES.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocksAndItems.HOLLOW_PURPLE_SHADOW_PINE_LEAVES.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_LEAVES.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocksAndItems.SHADOW_STELLAR_JUNGLE_TREE_LEAVES.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocksAndItems.AERIAL_TREE_LOG.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocksAndItems.SHADOW_AERIAL_TREE_LOG.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocksAndItems.GOLDEN_BEECH_LOG.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocksAndItems.SHADOW_GOLDEN_BEECH_LOG.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocksAndItems.COPPER_PINE_LOG.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocksAndItems.SHADOW_COPPER_PINE_LOG.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocksAndItems.LAPIS_ROBINIA_LOG.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocksAndItems.ENCHANTED_LAPIS_ROBINIA_LOG.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocksAndItems.SHADOW_LAPIS_ROBINIA_LOG.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocksAndItems.HOLLOW_SHADOW_PINE_LOG.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocksAndItems.SHADOW_PINE_LOG.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocksAndItems.EYE_SHADOW_PINE_LOG.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_LOG.get(), event);
        ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocksAndItems.SHADOW_STELLAR_JUNGLE_TREE_LOG.get(), event);
        */
    }
}
