package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.Client.BlockBakedModels.ShiftingBlockBakedModel;
import fr.factionbedrock.aerialhell.Client.BlockEntityRenderer.AerialHellChestBlockEntityRenderer;
import fr.factionbedrock.aerialhell.Client.BlockEntityRenderer.AerialHellChestMimicBlockEntityRenderer;
import fr.factionbedrock.aerialhell.Client.EntityModels.*;
import fr.factionbedrock.aerialhell.Client.EntityRender.*;
import fr.factionbedrock.aerialhell.Client.Util.ShiftedModelRenderHelper;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class RenderRegistrationListener
{
    public static void registerRenderers()
    {
        EntityRendererRegistry.register(AerialHellEntities.STELLAR_STONE_AUTOMATON, StellarStoneAutomatonRender::new);
        EntityRendererRegistry.register(AerialHellEntities.MUD_GOLEM, MudGolemRender::new);
        EntityRendererRegistry.register(AerialHellEntities.MUD_SPECTRAL_GOLEM, MudGolemRender::new);
        EntityRendererRegistry.register(AerialHellEntities.CRYSTAL_GOLEM, CrystalGolemRender::new);
        EntityRendererRegistry.register(AerialHellEntities.LUNATIC_PRIEST, LunaticPriestRender::new);
        EntityRendererRegistry.register(AerialHellEntities.EVIL_COW, EvilCowRender::new);
        EntityRendererRegistry.register(AerialHellEntities.STELLAR_ENT, EntRender::new);
        EntityRendererRegistry.register(AerialHellEntities.VENOMOUS_SNAKE, SnakeRender::new);
        EntityRendererRegistry.register(AerialHellEntities.WORM, SnakeRender::new);
        EntityRendererRegistry.register(AerialHellEntities.CORTINARIUS_COW, CortinariusCowRender::new);
        EntityRendererRegistry.register(AerialHellEntities.STELLAR_CHICKEN, StellarChickenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.STELLAR_BOAR, BoarRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SHROOMBOOM, ShroomBoomRender::new);
        EntityRendererRegistry.register(AerialHellEntities.VERDIGRIS_ZOMBIE, VerdigrisZombieRender::new);
        EntityRendererRegistry.register(AerialHellEntities.MUMMY, HumanoidTwoLayerRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SLIME_PIRATE, HumanoidTwoLayerRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SLIME_NINJA_PIRATE, HumanoidTwoLayerRender::new);
        EntityRendererRegistry.register(AerialHellEntities.GHOST_SLIME_PIRATE, HumanoidTwoLayerRender::new);
        EntityRendererRegistry.register(AerialHellEntities.GHOST_SLIME_NINJA_PIRATE, HumanoidTwoLayerRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SANDY_SHEEP, SandySheepRender::new);
        EntityRendererRegistry.register(AerialHellEntities.GLIDING_TURTLE, GlidingTurtleRender::new);
        EntityRendererRegistry.register(AerialHellEntities.FAT_PHANTOM, FatPhantomRender::new);
        EntityRendererRegistry.register(AerialHellEntities.KODAMA, KodamaRender::new);
        EntityRendererRegistry.register(AerialHellEntities.CRYSTAL_SLIME, CrystalSlimeRender::new);
        EntityRendererRegistry.register(AerialHellEntities.MUD_SOLDIER, MudSoldierRender::new);
        EntityRendererRegistry.register(AerialHellEntities.MUD_SPECTRAL_SOLDIER, MudSoldierRender::new);
        EntityRendererRegistry.register(AerialHellEntities.MUD_CYCLE_MAGE, MudCycleMageRender::new);
        EntityRendererRegistry.register(AerialHellEntities.MUD_SPECTRAL_CYCLE_MAGE, MudCycleMageRender::new);
        EntityRendererRegistry.register(AerialHellEntities.TORN_SPIRIT, TornSpiritRender::new);
        EntityRendererRegistry.register(AerialHellEntities.ICE_SPIRIT, ElementSpiritRender::new);
        EntityRendererRegistry.register(AerialHellEntities.FIRE_SPIRIT, ElementSpiritRender::new);
        EntityRendererRegistry.register(AerialHellEntities.ELECTRO_SPIRIT, ElementSpiritRender::new);
        EntityRendererRegistry.register(AerialHellEntities.CHAINED_GOD, ChainedGodRender::new);
        EntityRendererRegistry.register(AerialHellEntities.FLYING_JELLYFISH, FlyingJellyfishRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SHADOW_FLYING_SKULL, ShadowFlyingSkullRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SHADOW_TROLL, ShadowTrollRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SHADOW_AUTOMATON, ShadowAutomatonRender::new);
        EntityRendererRegistry.register(AerialHellEntities.AERIAL_TREE_MIMIC, ChestMimicRender::new);
        EntityRendererRegistry.register(AerialHellEntities.GOLDEN_BEECH_MIMIC, ChestMimicRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SKY_CACTUS_FIBER_MIMIC, ChestMimicRender::new);
        EntityRendererRegistry.register(AerialHellEntities.COPPER_PINE_MIMIC, ChestMimicRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SHADOW_PINE_MIMIC, SpiderBarrelMimicRender::new);
        EntityRendererRegistry.register(AerialHellEntities.HELL_SPIDER, HellSpiderRender::new);
        EntityRendererRegistry.register(AerialHellEntities.CRYSTAL_SPIDER, HellSpiderRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SHADOW_SPIDER, HellSpiderRender::new);
        EntityRendererRegistry.register(AerialHellEntities.LILITH, LilithRender::new);
        EntityRendererRegistry.register(AerialHellEntities.FOREST_CATERPILLAR, ForestCaterpillarRender::new);
        EntityRendererRegistry.register(AerialHellEntities.CRYSTAL_CATERPILLAR, CaterpillarRender::new);

        EntityRendererRegistry.register(AerialHellEntities.THROWN_STELLAR_EGG, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(AerialHellEntities.IRON_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.GOLD_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.DIAMOND_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.NETHERITE_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.RUBY_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.AZURITE_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.MAGMATIC_GEL_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.VOLUCITE_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.OBSIDIAN_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.ARSONIST_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.LIGHTNING_SHURIKEN, ShurikenRender::new);
        EntityRendererRegistry.register(AerialHellEntities.POISONBALL, FireballLikeProjectileRender::new);
        EntityRendererRegistry.register(AerialHellEntities.DIMENSION_SHATTERER_PROJECTILE, FireballLikeProjectileRender::new);
        EntityRendererRegistry.register(AerialHellEntities.VOLUCITE_BLOWPIPE_ARROW, AerialArrowRenderer::new);
        EntityRendererRegistry.register(AerialHellEntities.RUBY_BLOWPIPE_ARROW, AerialArrowRenderer::new);
        EntityRendererRegistry.register(AerialHellEntities.LUNATIC_PROJECTILE, LightProjectileRender::new);
        EntityRendererRegistry.register(AerialHellEntities.SHADOW_PROJECTILE, LightProjectileRender::new);
        EntityRendererRegistry.register(AerialHellEntities.AERIAL_HELL_PAINTING, AerialHellPaintingRender::new);

        event.registerBlockEntityRenderer(AerialHellBlockEntities.CHEST, AerialHellChestBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(AerialHellBlockEntities.CHEST_MIMIC, AerialHellChestMimicBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(AerialHellBlockEntities.SIGN, SignRenderer::new);
        event.registerBlockEntityRenderer(AerialHellBlockEntities.HANGING_SIGN, HangingSignRenderer::new);
    }

    public static void registerLayerDefinitions()
    {
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.CATERPILLAR, ForestCaterpillarModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.BOAR, BoarModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.ENT, EntModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.SNAKE, SnakeModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.CHAINED_GOD, ChainedGodModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.CHEST_MIMIC, ChestMimicModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.CRYSTAL_GOLEM, CrystalGolemModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.CRYSTAL_SLIME, CrystalSlimeModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.ELEMENT_SPIRIT, ElementSpiritModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.FLYING_JELLYFISH, FlyingJellyfishModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.GLIDING_TURTLE, GlidingTurtleModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.KODAMA, KodamaModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.LILITH, LilithModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.LUNATIC_PRIEST, LunaticPriestModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.MUD_CYCLE_MAGE, MudCycleMageModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.MUD_GOLEM, MudGolemModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.SANDY_SHEEP, SandySheepModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.SHADOW_FLYING_SKULL, ShadowFlyingSkullModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.SHADOW_TROLL, ShadowTrollModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.SHROOMBOOM, ShroomBoomModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.SPIDER_BARREL_MIMIC, SpiderBarrelMimicModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.AUTOMATON, AutomatonModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.TORN_SPIRIT, TornSpiritModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.VERDIGRIS_ZOMBIE, VerdigrisZombieModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.SLIME_PIRATE, HumanoidTwoLayerModel::createBodyLayer);

        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.CORTINARIUS_COW_SHROOM, CortinariusCowShroomModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.CRYSTAL_GOLEM_CRYSTAL, CrystalGolemCrystalModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(AerialHellModelLayers.SPIDER_SPIKE, HellSpiderSpikeModel::createBodyLayer);
    }

    public static void onModelBake(ModelEvent.ModifyBakingResult event)
    {
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
    }
}
