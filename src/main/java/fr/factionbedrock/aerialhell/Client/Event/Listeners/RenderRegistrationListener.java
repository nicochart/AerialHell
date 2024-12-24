package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.Client.BlockEntityRenderer.AerialHellChestBlockEntityRenderer;
import fr.factionbedrock.aerialhell.Client.BlockEntityRenderer.AerialHellChestMimicBlockEntityRenderer;
import fr.factionbedrock.aerialhell.Client.EntityModels.*;
import fr.factionbedrock.aerialhell.Client.EntityRender.*;
import fr.factionbedrock.aerialhell.Client.Util.ShiftedModelRenderHelper;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.HangingSignBlockEntityRenderer;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.registry.Registries;

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

        BlockEntityRendererFactories.register(AerialHellBlockEntities.CHEST, AerialHellChestBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(AerialHellBlockEntities.CHEST, AerialHellChestBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(AerialHellBlockEntities.CHEST_MIMIC, AerialHellChestMimicBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(AerialHellBlockEntities.SIGN, SignBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(AerialHellBlockEntities.HANGING_SIGN, HangingSignBlockEntityRenderer::new);
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

    public static void registerShiftingBakedModels()
    {
        ModelLoadingPlugin.register(plugin -> plugin.modifyModelAfterBake().register((original, context) ->
        {
            //TODO state to shift list & use list to avoid multiple registration problem
            if (!validateModelBakeContext(context)) {return original;} //temporary solution to avoid registering the same shifted render multiple times
            ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocks.STELLAR_STONE_CRYSTAL_BLOCK, context);
            ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocks.SHADOW_CRYSTAL_BLOCK, context);
            ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocks.STELLAR_GRASS, context);
            ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocks.SHADOW_GRASS, context);
            ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocks.STELLAR_GRASS_BALL, context);
            ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocks.SHADOW_GRASS_BALL, context);
            ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocks.BRAMBLES, context);
            ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocks.SHADOW_BRAMBLES, context);
            ShiftedModelRenderHelper.createAndRegisterAbstractPlantStemBlock(AerialHellBlocks.GLOWING_ROOTS, context);
            ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocks.GLOWING_ROOTS_PLANT, context);
            ShiftedModelRenderHelper.createAndRegisterAbstractPlantStemBlock(AerialHellBlocks.SHADOW_GLOWING_ROOTS, context);
            ShiftedModelRenderHelper.createAndRegisterDefaultBlockShiftedRender(AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT, context);
            ShiftedModelRenderHelper.createAndRegisterGrassBlockShiftedRender(AerialHellBlocks.STELLAR_GRASS_BLOCK, context);
            ShiftedModelRenderHelper.createAndRegisterGrassBlockShiftedRender(AerialHellBlocks.SHADOW_GRASS_BLOCK, context);
            ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocks.AERIAL_TREE_LEAVES, context);
            ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocks.SHADOW_AERIAL_TREE_LEAVES, context);
            ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocks.GOLDEN_BEECH_LEAVES, context);
            ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocks.SHADOW_GOLDEN_BEECH_LEAVES, context);
            ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocks.COPPER_PINE_LEAVES, context);
            ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocks.SHADOW_COPPER_PINE_LEAVES, context);
            ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocks.LAPIS_ROBINIA_LEAVES, context);
            ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LEAVES, context);
            ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocks.SHADOW_PINE_LEAVES, context);
            ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocks.HOLLOW_SHADOW_PINE_LEAVES, context);
            ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES, context);
            ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocks.HOLLOW_PURPLE_SHADOW_PINE_LEAVES, context);
            ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES, context);
            ShiftedModelRenderHelper.createAndRegisterLeavesBlockShiftedRender(AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES, context);
            ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocks.AERIAL_TREE_LOG, context);
            ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocks.SHADOW_AERIAL_TREE_LOG, context);
            ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocks.GOLDEN_BEECH_LOG, context);
            ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocks.SHADOW_GOLDEN_BEECH_LOG, context);
            ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocks.COPPER_PINE_LOG, context);
            ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocks.SHADOW_COPPER_PINE_LOG, context);
            ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocks.LAPIS_ROBINIA_LOG, context);
            ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocks.ENCHANTED_LAPIS_ROBINIA_LOG, context);
            ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LOG, context);
            ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocks.HOLLOW_SHADOW_PINE_LOG, context);
            ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocks.SHADOW_PINE_LOG, context);
            ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocks.EYE_SHADOW_PINE_LOG, context);
            ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocks.STELLAR_JUNGLE_TREE_LOG, context);
            ShiftedModelRenderHelper.createAndRegisterLogBlockShiftedRender(AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LOG, context);
            return original;
        }));
    }

    private static boolean validateModelBakeContext(ModelModifier.AfterBake.Context context)
    {
        if (context.topLevelId() == null) {return false;}
        return context.topLevelId().id().equals(Registries.BLOCK.getId(Blocks.DIRT));
    }
}
