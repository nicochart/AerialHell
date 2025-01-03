package fr.factionbedrock.aerialhell.Registry;

import com.google.common.collect.ImmutableMap;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Block.*;
import fr.factionbedrock.aerialhell.Block.CollisionCondition.*;
import fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther.BlueSolidEtherBlock;
import fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther.GreenSolidEtherBlock;
import fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther.PurpleSolidEtherBlock;
import fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther.SolidEtherBlock;
import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.BiomeShifterBlock;
import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.ReactorBlock;
import fr.factionbedrock.aerialhell.Block.DirtAndVariants.*;
import fr.factionbedrock.aerialhell.Block.DungeonCores.*;
import fr.factionbedrock.aerialhell.Block.Furnaces.FreezerBlock;
import fr.factionbedrock.aerialhell.Block.Furnaces.OscillatorBlock;
import fr.factionbedrock.aerialhell.Block.Furnaces.StellarFurnaceBlock;
import fr.factionbedrock.aerialhell.Block.Mimics.BarrelMimicBlock;
import fr.factionbedrock.aerialhell.Block.Mimics.ChestMimicBlock;
import fr.factionbedrock.aerialhell.Block.Ores.AerialHellOreBlock;
import fr.factionbedrock.aerialhell.Block.Ores.BiomeShifterOreBlock;
import fr.factionbedrock.aerialhell.Block.Ores.MagmaticGelOreBlock;
import fr.factionbedrock.aerialhell.Block.Ores.VoluciteOreBlock;
import fr.factionbedrock.aerialhell.Block.Plants.*;
import fr.factionbedrock.aerialhell.Block.Plants.Bushes.AerialBerryBushBlock;
import fr.factionbedrock.aerialhell.Block.Plants.Bushes.VibrantAerialBerryBushBlock;
import fr.factionbedrock.aerialhell.Block.Plants.Vines.*;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.*;
import fr.factionbedrock.aerialhell.Block.StandingAndWall.*;
import fr.factionbedrock.aerialhell.Block.Trophies.BottomSlabLikeTrophyBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellTreeGrowers;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AxeItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.DyeColor;

import java.util.function.ToIntFunction;

public class AerialHellBlocks
{
    public static void registerAxeStrippingBlocks()
    {
        AxeItem.STRIPPED_BLOCKS = ImmutableMap.<Block, Block>builder()
                .putAll(AxeItem.STRIPPED_BLOCKS)
                .put(AERIAL_TREE_LOG, STRIPPED_AERIAL_TREE_LOG)
                .put(AERIAL_TREE_WOOD, STRIPPED_AERIAL_TREE_WOOD)
                .put(GOLDEN_BEECH_LOG, STRIPPED_GOLDEN_BEECH_LOG)
                .put(GOLDEN_BEECH_WOOD, STRIPPED_GOLDEN_BEECH_WOOD)
                .put(COPPER_PINE_LOG, STRIPPED_COPPER_PINE_LOG)
                .put(COPPER_PINE_WOOD, STRIPPED_COPPER_PINE_WOOD)
                .put(LAPIS_ROBINIA_LOG, STRIPPED_LAPIS_ROBINIA_LOG)
                .put(LAPIS_ROBINIA_WOOD, STRIPPED_LAPIS_ROBINIA_WOOD)
                .put(SHADOW_PINE_LOG, STRIPPED_SHADOW_PINE_LOG)
                .put(SHADOW_PINE_WOOD, STRIPPED_SHADOW_PINE_WOOD)
                .put(STELLAR_JUNGLE_TREE_LOG, STRIPPED_STELLAR_JUNGLE_TREE_LOG)
                .put(STELLAR_JUNGLE_TREE_WOOD, STRIPPED_STELLAR_JUNGLE_TREE_WOOD)
                .put(GIANT_CORTINARIUS_VIOLACEUS_STEM, STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM)
                .put(GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM, STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM)
                .put(GIANT_VERDIGRIS_AGARIC_STEM, STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM)
                .put(GIANT_VERDIGRIS_AGARIC_BARK_STEM, STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM)
                .build();
    }
    
    //materials
    public static AbstractBlock.Settings AERIAL_TREE_MATERIAL = AbstractBlock.Settings.create().mapColor(MapColor.BROWN).strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD);
    public static AbstractBlock.Settings COPPER_PINE_MATERIAL = AbstractBlock.Settings.create().mapColor(MapColor.BROWN).strength(4.5F, 4.5F).sounds(BlockSoundGroup.WOOD);
    public static AbstractBlock.Settings SHADOW_PINE_MATERIAL = AbstractBlock.Settings.create().mapColor(MapColor.BLACK).strength(4.0F, 4.0F).sounds(BlockSoundGroup.WOOD);
    public static AbstractBlock.Settings SHROOM_MATERIAL = AbstractBlock.Settings.create().mapColor(MapColor.GRAY).strength(3.5F, 3.5F).sounds(BlockSoundGroup.STEM);
    public static AbstractBlock.Settings AERIAL_TREE_SIGN_MATERIAL = AbstractBlock.Settings.create().mapColor(MapColor.BROWN).strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque().noCollision();
    public static AbstractBlock.Settings COPPER_PINE_SIGN_MATERIAL = AbstractBlock.Settings.create().mapColor(MapColor.BROWN).strength(4.5F, 4.5F).sounds(BlockSoundGroup.WOOD).nonOpaque().noCollision();
    public static AbstractBlock.Settings SHADOW_PINE_SIGN_MATERIAL = AbstractBlock.Settings.create().mapColor(MapColor.BLACK).strength(4.0F, 4.0F).sounds(BlockSoundGroup.WOOD).nonOpaque().noCollision();
    public static AbstractBlock.Settings SKY_CACTUS_FIBER_MATERIAL = AbstractBlock.Settings.create().mapColor(MapColor.CYAN).strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD);
    public static AbstractBlock.Settings SKY_CACTUS_FIBER_SIGN_MATERIAL = AbstractBlock.Settings.create().mapColor(MapColor.CYAN).strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque().noCollision();
    public static AbstractBlock.Settings SHROOM_SIGN_MATERIAL = AbstractBlock.Settings.create().mapColor(MapColor.GRAY).strength(3.5F, 3.5F).sounds(BlockSoundGroup.WOOD).nonOpaque().noCollision();
    public static AbstractBlock.Settings MUD_CHEST_MATERIAL = AbstractBlock.Settings.create().mapColor(MapColor.GRAY).strength(20.0F, 1000.0F).sounds(BlockSoundGroup.STONE).requiresTool().nonOpaque();
    public static AbstractBlock.Settings LUNATIC_CHEST_MATERIAL = AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_ORANGE).strength(30.0F, 1000.0F).sounds(BlockSoundGroup.METAL).requiresTool().nonOpaque();
    public static AbstractBlock.Settings VOLUCITE_CHEST_MATERIAL = AbstractBlock.Settings.create().mapColor(MapColor.GRAY).strength(50.0F, 1200.0F).sounds(BlockSoundGroup.METAL).requiresTool().nonOpaque();
    public static AbstractBlock.Settings GOLDEN_NETHER_CHEST_MATERIAL = AbstractBlock.Settings.create().mapColor(MapColor.RED).strength(50.0F, 1200.0F).sounds(BlockSoundGroup.BASALT).requiresTool().nonOpaque();
    public static AbstractBlock.Settings METAL_NOTSOLID_MATERIAL = AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).strength(10.0F, 2.0F).sounds(BlockSoundGroup.METAL).requiresTool().nonOpaque();

    
    //portal
    public static final AerialHellPortalBlock AERIAL_HELL_PORTAL = register("aerial_hell_portal", new AerialHellPortalBlock(AbstractBlock.Settings.copy(Blocks.NETHER_PORTAL).nonOpaque().blockVision(( state, blockview, pos) -> false).luminance((state) -> 10).mapColor(DyeColor.GREEN)));
    public static final Block STELLAR_PORTAL_FRAME_BLOCK = register("stellar_portal_frame_block", new Block(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).requiresTool().strength(25.0F, 600.0F)));
    public static final Block STELLAR_PORTAL_FRAME_ORE = register("stellar_portal_frame_ore", new AerialHellOreBlock(0, 0, AbstractBlock.Settings.create().strength(25.0F, 600.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block DEEPSLATE_STELLAR_PORTAL_FRAME_ORE = register("deepslate_stellar_portal_frame_ore", new AerialHellOreBlock(0, 0, AbstractBlock.Settings.create().strength(30.0F, 600.0F).sounds(BlockSoundGroup.STONE).requiresTool()));

    //torch
    public static final Block FLUORITE_TORCH = register("fluorite_torch", new AerialHellTorchBlock(AbstractBlock.Settings.copy(Blocks.TORCH)));
    public static final Block FLUORITE_WALL_TORCH = register("fluorite_wall_torch", new AerialHellWallTorchBlock(AbstractBlock.Settings.copy(Blocks.WALL_TORCH).dropsLike(FLUORITE_TORCH)));
    public static final Block VOLUCITE_TORCH = register("volucite_torch", new AerialHellTorchBlock(AbstractBlock.Settings.copy(Blocks.TORCH).luminance((state) -> {return 9;})));
    public static final Block VOLUCITE_WALL_TORCH = register("volucite_wall_torch", new AerialHellWallTorchBlock(AbstractBlock.Settings.copy(Blocks.WALL_TORCH).dropsLike(VOLUCITE_TORCH).luminance((state) -> {return 9;})));
    public static final Block SHADOW_TORCH = register("shadow_torch", new AerialHellTorchBlock(AbstractBlock.Settings.copy(Blocks.TORCH).luminance((state) -> {return 9;})));
    public static final Block SHADOW_WALL_TORCH = register("shadow_wall_torch", new AerialHellWallTorchBlock(AbstractBlock.Settings.copy(Blocks.WALL_TORCH).dropsLike(SHADOW_TORCH).luminance((state) -> {return 9;})));

    //lanterns
    public static final Block FLUORITE_LANTERN = register("fluorite_lantern", new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block RUBY_LANTERN = register("ruby_lantern", new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block RUBY_FLUORITE_LANTERN = register("ruby_fluorite_lantern", new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block VOLUCITE_LANTERN = register("volucite_lantern", new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block VOLUCITE_FLUORITE_LANTERN = register("volucite_fluorite_lantern", new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block LUNATIC_LANTERN = register("lunatic_lantern", new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));
    public static final Block SHADOW_LANTERN = register("shadow_lantern", new LanternBlock(AbstractBlock.Settings.copy(Blocks.SOUL_LANTERN)));

    //chains
    public static final ChainBlock RUBY_CHAIN = register("ruby_chain", new ChainBlock(AbstractBlock.Settings.copy(Blocks.CHAIN)));
    public static final ChainBlock VOLUCITE_CHAIN = register("volucite_chain", new ChainBlock(AbstractBlock.Settings.copy(Blocks.CHAIN)));
    public static final ChainBlock LUNATIC_CHAIN = register("lunatic_chain", new ChainBlock(AbstractBlock.Settings.copy(Blocks.CHAIN)));
    public static final ChainBlock SHADOW_CHAIN = register("shadow_chain", new ShadowChainBlock(AbstractBlock.Settings.copy(Blocks.CHAIN)));

    //grass & dirt
    public static final StellarGrassBlock STELLAR_GRASS_BLOCK = register("stellar_grass_block", new StellarGrassBlock(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK)));
    public static final Block CHISELED_STELLAR_GRASS_BLOCK = register("chiseled_stellar_grass_block", new StellarGrassBlock(AbstractBlock.Settings.copy(STELLAR_GRASS_BLOCK)));
    public static final Block STELLAR_DIRT = register("stellar_dirt", new StellarDirtBlock(AbstractBlock.Settings.copy(Blocks.DIRT)));
    public static final Block STELLAR_COARSE_DIRT = register("stellar_coarse_dirt", new StellarDirtBlock(AbstractBlock.Settings.copy(Blocks.COARSE_DIRT)));
    public static final Block STELLAR_FARMLAND = register("stellar_farmland", new StellarFarmBlock(AbstractBlock.Settings.copy(Blocks.DIRT).ticksRandomly().strength(0.6F).sounds(BlockSoundGroup.GRAVEL).blockVision((state, blockgetter, pos) -> true).suffocates((state, blockgetter, pos) -> true)));
    public static final Block STELLAR_DIRT_PATH = register("stellar_dirt_path", new StellarDirtPathBlock(AbstractBlock.Settings.copy(Blocks.DIRT_PATH)));
    public static final Block STELLAR_PODZOL = register("stellar_podzol", new Block(AbstractBlock.Settings.copy(Blocks.PODZOL)));
    public static final Block STELLAR_CRYSTAL_PODZOL = register("stellar_crystal_podzol", new Block(AbstractBlock.Settings.copy(Blocks.PODZOL)));
    public static final Block CHISELED_STELLAR_DIRT = register("chiseled_stellar_dirt", new StellarDirtBlock(AbstractBlock.Settings.copy(STELLAR_DIRT)));
    public static final ShadowGrassBlock SHADOW_GRASS_BLOCK = register("shadow_grass_block", new ShadowGrassBlock(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK)));

    //slippery sand
    public static final Block SLIPPERY_SAND = register("slippery_sand", new Block(AbstractBlock.Settings.copy(Blocks.SAND).slipperiness(1.025F)));
    public static final Block SLIPPERY_SAND_STONE = register("slippery_sand_stone", new Block(AbstractBlock.Settings.copy(Blocks.SANDSTONE).slipperiness(1.01F)));
    public static final Block SLIPPERY_SAND_STONE_BRICKS = register("slippery_sand_stone_bricks", new Block(AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE).slipperiness(1.005F)));
    public static final Block CUT_SLIPPERY_SAND_STONE = register("cut_slippery_sand_stone", new Block(AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE).slipperiness(1.005F)));
    public static final Block CRACKED_SLIPPERY_SAND_STONE_BRICKS = register("cracked_slippery_sand_stone_bricks", new Block(AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE).slipperiness(1.003F)));

    //giant root
    public static final PillarBlock GIANT_ROOT = register("giant_root", new PillarBlock(AERIAL_TREE_MATERIAL));

    //aerial_tree
    public static final ShiftableLogBlock AERIAL_TREE_LOG = register("aerial_tree_log", new ShiftableLogBlock(AERIAL_TREE_MATERIAL, () -> AerialHellBlocks.SHADOW_AERIAL_TREE_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final PillarBlock STRIPPED_AERIAL_TREE_LOG = register("stripped_aerial_tree_log", new PillarBlock(AbstractBlock.Settings.copy(AERIAL_TREE_LOG)));
    public static final PillarBlock AERIAL_TREE_WOOD = register("aerial_tree_wood", new PillarBlock(AERIAL_TREE_MATERIAL));
    public static final PillarBlock STRIPPED_AERIAL_TREE_WOOD = register("stripped_aerial_tree_wood", new PillarBlock(AERIAL_TREE_MATERIAL));
    public static final ShiftableLeavesBlock AERIAL_TREE_LEAVES = register("aerial_tree_leaves", new ShiftableLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.SHADOW_AERIAL_TREE_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block AERIAL_TREE_PLANKS = register("aerial_tree_planks", new Block(AbstractBlock.Settings.copy(AERIAL_TREE_LOG)));
    public static final Block CHISELED_AERIAL_TREE_PLANKS = register("chiseled_aerial_tree_planks", new Block(AbstractBlock.Settings.copy(AERIAL_TREE_PLANKS)));
    public static final Block AERIAL_TREE_BOOKSHELF = register("aerial_tree_bookshelf", new Block(AbstractBlock.Settings.copy(AERIAL_TREE_PLANKS)));
    public static final SaplingBlock AERIAL_TREE_SAPLING = register("aerial_tree_sapling", new AerialHellSaplingBlock(AerialHellTreeGrowers.AERIAL_TREE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_AERIAL_TREE));

    //petrified aerial tree
    public static final Block PETRIFIED_AERIAL_TREE_LOG = register("petrified_aerial_tree_log", new PillarBlock(AbstractBlock.Settings.copy(AERIAL_TREE_LOG)));

    //golden beech
    public static final ShiftableLogBlock GOLDEN_BEECH_LOG = register("golden_beech_log", new ShiftableLogBlock(AbstractBlock.Settings.copy(AERIAL_TREE_LOG), () -> AerialHellBlocks.SHADOW_GOLDEN_BEECH_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final Block STRIPPED_GOLDEN_BEECH_LOG = register("stripped_golden_beech_log", new PillarBlock(AbstractBlock.Settings.copy(GOLDEN_BEECH_LOG)));
    public static final Block GOLDEN_BEECH_WOOD = register("golden_beech_wood", new PillarBlock(AbstractBlock.Settings.copy(GOLDEN_BEECH_LOG)));
    public static final Block STRIPPED_GOLDEN_BEECH_WOOD = register("stripped_golden_beech_wood", new PillarBlock(AbstractBlock.Settings.copy(GOLDEN_BEECH_LOG)));
    public static final Block GOLDEN_BEECH_PLANKS = register("golden_beech_planks", new Block(AbstractBlock.Settings.copy(GOLDEN_BEECH_LOG)));
    public static final Block CHISELED_GOLDEN_BEECH_PLANKS = register("chiseled_golden_beech_planks", new Block(AbstractBlock.Settings.copy(GOLDEN_BEECH_PLANKS)));
    public static final ShiftableLeavesBlock GOLDEN_BEECH_LEAVES = register("golden_beech_leaves", new ShiftableLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.SHADOW_GOLDEN_BEECH_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block GOLDEN_BEECH_BOOKSHELF = register("golden_beech_bookshelf", new Block(AbstractBlock.Settings.copy(GOLDEN_BEECH_PLANKS)));
    public static final Block GOLDEN_BEECH_SAPLING = register("golden_beech_sapling", new SaplingBlock(AerialHellTreeGrowers.GOLDEN_BEECH, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

    //copper pine
    public static final ShiftableLogBlock COPPER_PINE_LOG = register("copper_pine_log", new ShiftableLogBlock(COPPER_PINE_MATERIAL, () -> AerialHellBlocks.SHADOW_COPPER_PINE_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final PillarBlock STRIPPED_COPPER_PINE_LOG = register("stripped_copper_pine_log", new PillarBlock(AbstractBlock.Settings.copy(COPPER_PINE_LOG)));
    public static final PillarBlock COPPER_PINE_WOOD = register("copper_pine_wood", new PillarBlock(AbstractBlock.Settings.copy(COPPER_PINE_LOG)));
    public static final PillarBlock STRIPPED_COPPER_PINE_WOOD = register("stripped_copper_pine_wood", new PillarBlock(AbstractBlock.Settings.copy(COPPER_PINE_LOG)));
    public static final Block COPPER_PINE_PLANKS = register("copper_pine_planks", new Block(AbstractBlock.Settings.copy(COPPER_PINE_LOG)));
    public static final ShiftableLeavesBlock COPPER_PINE_LEAVES = register("copper_pine_leaves", new LeavesWithAmbientParticlesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.SHADOW_COPPER_PINE_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block COPPER_PINE_BOOKSHELF = register("copper_pine_bookshelf", new Block(AbstractBlock.Settings.copy(COPPER_PINE_PLANKS)));
    public static final SaplingBlock COPPER_PINE_SAPLING = register("copper_pine_sapling", new AerialHellSaplingBlock(AerialHellTreeGrowers.COPPER_PINE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_COPPER_PINE, AerialHellConfiguredFeatures.HUGE_COPPER_PINE, 0.1F));

    //lapis robinia
    public static final ShiftableLogBlock LAPIS_ROBINIA_LOG = register("lapis_robinia_log", new ShiftableLogBlock(COPPER_PINE_MATERIAL, () -> AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final EffectLogBlock ENCHANTED_LAPIS_ROBINIA_LOG = register("enchanted_lapis_robinia_log", new EffectLogBlock(COPPER_PINE_MATERIAL, () -> AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final PillarBlock STRIPPED_LAPIS_ROBINIA_LOG = register("stripped_lapis_robinia_log", new PillarBlock(AbstractBlock.Settings.copy(LAPIS_ROBINIA_LOG)));
    public static final PillarBlock LAPIS_ROBINIA_WOOD = register("lapis_robinia_wood", new PillarBlock(AbstractBlock.Settings.copy(LAPIS_ROBINIA_LOG)));
    public static final PillarBlock STRIPPED_LAPIS_ROBINIA_WOOD = register("stripped_lapis_robinia_wood", new PillarBlock(AbstractBlock.Settings.copy(LAPIS_ROBINIA_LOG)));
    public static final ShiftableLeavesBlock LAPIS_ROBINIA_LEAVES = register("lapis_robinia_leaves", new ShiftableLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block LAPIS_ROBINIA_PLANKS = register("lapis_robinia_planks", new Block(AbstractBlock.Settings.copy(LAPIS_ROBINIA_LOG)));
    public static final Block LAPIS_ROBINIA_BOOKSHELF = register("lapis_robinia_bookshelf", new Block(AbstractBlock.Settings.copy(LAPIS_ROBINIA_PLANKS)));
    public static final SaplingBlock LAPIS_ROBINIA_SAPLING = register("lapis_robinia_sapling", new AerialHellSaplingBlock(AerialHellTreeGrowers.LAPIS_ROBINIA, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_LAPIS_ROBINIA));

    //shadow_pine
    public static final ShiftableLogBlock SHADOW_PINE_LOG = register("shadow_pine_log", new ShadowLogBlock(SHADOW_PINE_MATERIAL, () -> AerialHellBlocks.HOLLOW_SHADOW_PINE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLogBlock EYE_SHADOW_PINE_LOG = register("eye_shadow_pine_log", new ShadowEffectLogBlock(SHADOW_PINE_MATERIAL, () -> AerialHellBlocks.HOLLOW_SHADOW_PINE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final PillarBlock STRIPPED_SHADOW_PINE_LOG = register("stripped_shadow_pine_log", new PillarBlock(AbstractBlock.Settings.copy(SHADOW_PINE_LOG)));
    public static final PillarBlock SHADOW_PINE_WOOD = register("shadow_pine_wood", new PillarBlock(AbstractBlock.Settings.copy(SHADOW_PINE_LOG)));
    public static final PillarBlock STRIPPED_SHADOW_PINE_WOOD = register("stripped_shadow_pine_wood", new PillarBlock(AbstractBlock.Settings.copy(SHADOW_PINE_LOG)));
    public static final ShiftableLeavesBlock SHADOW_PINE_LEAVES = register("shadow_pine_leaves", new ShadowLeavesWithParticlesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.HOLLOW_SHADOW_PINE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock PURPLE_SHADOW_PINE_LEAVES = register("purple_shadow_pine_leaves", new ShadowLeavesWithParticlesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.HOLLOW_PURPLE_SHADOW_PINE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final Block SHADOW_PINE_PLANKS = register("shadow_pine_planks", new Block(AbstractBlock.Settings.copy(SHADOW_PINE_LOG)));
    public static final Block SHADOW_PINE_BOOKSHELF = register("shadow_pine_bookshelf", new Block(AbstractBlock.Settings.copy(SHADOW_PINE_PLANKS)));
    public static final SaplingBlock SHADOW_PINE_SAPLING = register("shadow_pine_sapling", new ShadowPineSaplingBlock(AerialHellTreeGrowers.SHADOW_PINE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_SHADOW_PINE, AerialHellConfiguredFeatures.HUGE_SHADOW_PINE, 0.1F));
    public static final SaplingBlock PURPLE_SHADOW_PINE_SAPLING = register("purple_shadow_pine_sapling", new ShadowPineSaplingBlock(AerialHellTreeGrowers.PURPLE_SHADOW_PINE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_PURPLE_SHADOW_PINE, AerialHellConfiguredFeatures.HUGE_PURPLE_SHADOW_PINE, 0.1F));

    //stellar jungle tree
    public static final ShiftableLogBlock STELLAR_JUNGLE_TREE_LOG = register("stellar_jungle_tree_log", new ShiftableLogBlock(COPPER_PINE_MATERIAL, () -> AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final PillarBlock STRIPPED_STELLAR_JUNGLE_TREE_LOG = register("stripped_stellar_jungle_tree_log", new PillarBlock(AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_LOG)));
    public static final PillarBlock STELLAR_JUNGLE_TREE_WOOD = register("stellar_jungle_tree_wood", new PillarBlock(AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_LOG)));
    public static final PillarBlock STRIPPED_STELLAR_JUNGLE_TREE_WOOD = register("stripped_stellar_jungle_tree_wood", new PillarBlock(AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_LOG)));
    public static final ShiftableLeavesBlock STELLAR_JUNGLE_TREE_LEAVES = register("stellar_jungle_tree_leaves", new ShiftableLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block STELLAR_JUNGLE_TREE_PLANKS = register("stellar_jungle_tree_planks", new Block(AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_LOG)));
    public static final Block STELLAR_JUNGLE_TREE_BOOKSHELF = register("stellar_jungle_tree_bookshelf", new Block(AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_PLANKS)));
    public static final SaplingBlock STELLAR_JUNGLE_TREE_SAPLING = register("stellar_jungle_tree_sapling", new AerialHellSaplingBlock(AerialHellTreeGrowers.STELLAR_JUNGLE_TREE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_STELLAR_JUNGLE_TREE));
    public static final LargeDeadLogBlock DEAD_STELLAR_JUNGLE_TREE_LOG = register("dead_stellar_jungle_tree_log", new LargeDeadLogBlock(STELLAR_JUNGLE_TREE_PLANKS.getDefaultState(), COPPER_PINE_MATERIAL));

    //shroom
    public static final PillarBlock GIANT_CORTINARIUS_VIOLACEUS_STEM = register("giant_cortinarius_violaceus_stem", new PillarBlock(SHROOM_MATERIAL));
    public static final PillarBlock STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM = register("stripped_giant_cortinarius_violaceus_stem", new PillarBlock(AbstractBlock.Settings.copy(GIANT_CORTINARIUS_VIOLACEUS_STEM)));
    public static final PillarBlock GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = register("giant_cortinarius_violaceus_bark_stem", new PillarBlock(AbstractBlock.Settings.copy(GIANT_CORTINARIUS_VIOLACEUS_STEM)));
    public static final PillarBlock STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = register("stripped_giant_cortinarius_violaceus_bark_stem", new PillarBlock(AbstractBlock.Settings.copy(GIANT_CORTINARIUS_VIOLACEUS_STEM)));
    public static final Block GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK = register("giant_cortinarius_violaceus_cap_block", new Block(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_BLUE).strength(0.5F).sounds(BlockSoundGroup.STEM)));
    public static final Block GIANT_CORTINARIUS_VIOLACEUS_LIGHT = register("giant_cortinarius_violaceus_light", new Block(AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).strength(1.0F).sounds(BlockSoundGroup.SHROOMLIGHT).luminance((state) -> {return 15;})));
    public static final FungusBlock CORTINARIUS_VIOLACEUS = register("cortinarius_violaceus", new AerialHellFungusBlock(AerialHellConfiguredFeatures.GIANT_CORTINARIUS_VIOLACEUS_PLANTED, STELLAR_GRASS_BLOCK, AbstractBlock.Settings.copy(Blocks.WARPED_FUNGUS)));
    public static final Block GLOWING_BOLETUS = register("glowing_boletus", new AerialHellTallShroomBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).noCollision().luminance((state) -> {return 9;}).breakInstantly().sounds(BlockSoundGroup.GLOW_LICHEN), true));
    public static final Block TALL_GLOWING_BOLETUS = register("tall_glowing_boletus", new DoubleShroomBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).noCollision().luminance((state) -> {return 11;}).breakInstantly().sounds(BlockSoundGroup.GLOW_LICHEN)));
    public static final Block BLUE_MEANIE_CLUSTER = register("blue_meanie_cluster", new TallShroomBlock(AbstractBlock.Settings.copy(Blocks.ROSE_BUSH)));
    public static final Block GIANT_ROOT_SHROOM = register("giant_root_shroom", new AerialHellTallShroomBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.NETHER_WART), false));

    public static final PillarBlock GIANT_VERDIGRIS_AGARIC_STEM = register("giant_verdigris_agaric_stem", new PillarBlock(SHROOM_MATERIAL));
    public static final PillarBlock STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM = register("stripped_giant_verdigris_agaric_stem", new PillarBlock(AbstractBlock.Settings.copy(GIANT_CORTINARIUS_VIOLACEUS_STEM)));
    public static final PillarBlock GIANT_VERDIGRIS_AGARIC_BARK_STEM = register("giant_verdigris_agaric_bark_stem", new PillarBlock(AbstractBlock.Settings.copy(GIANT_CORTINARIUS_VIOLACEUS_STEM)));
    public static final PillarBlock STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM = register("stripped_giant_verdigris_agaric_bark_stem", new PillarBlock(AbstractBlock.Settings.copy(GIANT_CORTINARIUS_VIOLACEUS_STEM)));
    public static final Block GIANT_VERDIGRIS_AGARIC_CAP_BLOCK = register("giant_verdigris_agaric_cap_block", new MushroomBlock(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_BLUE).luminance((state) -> {return 10;}).strength(0.4F).sounds(BlockSoundGroup.STEM)));
    public static final MushroomPlantBlock VERDIGRIS_AGARIC = register("verdigris_agaric", new AerialHellMushroomBlock(AerialHellConfiguredFeatures.GIANT_VERDIGRIS_AGARIC, AbstractBlock.Settings.create().mapColor(MapColor.GRAY).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS)));

    public static final Block GIANT_GANODERMA_APPLANATUM_BLOCK = register("giant_ganoderma_applanatum_block", new MushroomBlock(AbstractBlock.Settings.create().mapColor(MapColor.BROWN).strength(0.4F).sounds(BlockSoundGroup.STEM)));

    public static final Block GRAY_SHROOM_PLANKS = register("gray_shroom_planks", new Block(AbstractBlock.Settings.copy(GIANT_CORTINARIUS_VIOLACEUS_STEM)));
    public static final Block GRAY_SHROOM_BOOKSHELF = register("gray_shroom_bookshelf", new Block(AbstractBlock.Settings.copy(GRAY_SHROOM_PLANKS)));

    //shadow corrupted / uncorrupted variants
    public static final ShadowLogBlock SHADOW_AERIAL_TREE_LOG = register("shadow_aerial_tree_log", new ShadowLogBlock(AbstractBlock.Settings.copy(AERIAL_TREE_LOG), () -> AERIAL_TREE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShadowLogBlock SHADOW_GOLDEN_BEECH_LOG = register("shadow_golden_beech_log", new ShadowLogBlock(AbstractBlock.Settings.copy(GOLDEN_BEECH_LOG), () -> GOLDEN_BEECH_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShadowLogBlock SHADOW_COPPER_PINE_LOG = register("shadow_copper_pine_log", new ShadowLogBlock(AbstractBlock.Settings.copy(COPPER_PINE_LOG), () -> COPPER_PINE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShadowLogBlock SHADOW_LAPIS_ROBINIA_LOG = register("shadow_lapis_robinia_log", new ShadowLogBlock(AbstractBlock.Settings.copy(LAPIS_ROBINIA_LOG), () -> LAPIS_ROBINIA_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShadowLogBlock SHADOW_STELLAR_JUNGLE_TREE_LOG = register("shadow_stellar_jungle_tree_log", new ShadowLogBlock(AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_LOG), () -> STELLAR_JUNGLE_TREE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLogBlock HOLLOW_SHADOW_PINE_LOG = register("hollow_shadow_pine_log", new ShiftableLogBlock(AbstractBlock.Settings.copy(AERIAL_TREE_LOG), () -> SHADOW_PINE_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final ShiftableLeavesBlock SHADOW_AERIAL_TREE_LEAVES = register("shadow_aerial_tree_leaves", new ShadowLeavesBlock(AbstractBlock.Settings.copy(AERIAL_TREE_LEAVES), () -> AerialHellBlocks.AERIAL_TREE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock SHADOW_GOLDEN_BEECH_LEAVES = register("shadow_golden_beech_leaves", new ShadowLeavesBlock(AbstractBlock.Settings.copy(GOLDEN_BEECH_LEAVES), () -> AerialHellBlocks.GOLDEN_BEECH_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock SHADOW_COPPER_PINE_LEAVES = register("shadow_copper_pine_leaves", new ShadowLeavesBlock(AbstractBlock.Settings.copy(COPPER_PINE_LEAVES), () -> AerialHellBlocks.COPPER_PINE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock SHADOW_LAPIS_ROBINIA_LEAVES = register("shadow_lapis_robinia_leaves", new ShadowLeavesBlock(AbstractBlock.Settings.copy(LAPIS_ROBINIA_LEAVES), () -> AerialHellBlocks.LAPIS_ROBINIA_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock SHADOW_STELLAR_JUNGLE_TREE_LEAVES = register("shadow_stellar_jungle_tree_leaves", new ShadowLeavesBlock(AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_LEAVES), () -> AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock HOLLOW_SHADOW_PINE_LEAVES = register("hollow_shadow_pine_leaves", new ShiftableLeavesBlock(AbstractBlock.Settings.copy(SHADOW_PINE_LEAVES), () -> AerialHellBlocks.SHADOW_PINE_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final ShiftableLeavesBlock HOLLOW_PURPLE_SHADOW_PINE_LEAVES = register("hollow_purple_shadow_pine_leaves", new ShiftableLeavesBlock(AbstractBlock.Settings.copy(PURPLE_SHADOW_PINE_LEAVES), () -> AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES, BiomeShifter.ShiftType.CORRUPT));

    //ladder
    public static final LadderBlock SKY_LADDER = register("sky_ladder", new LadderBlock(AbstractBlock.Settings.copy(AERIAL_TREE_PLANKS).nonOpaque()));

    //natural blocks and items
    public static final Block STELLAR_STONE = register("stellar_stone", new Block(AbstractBlock.Settings.copy(Blocks.STONE)));
    public static final Block STELLAR_COBBLESTONE = register("stellar_cobblestone", new Block(AbstractBlock.Settings.copy(Blocks.COBBLESTONE)));
    public static final Block STELLAR_STONE_BRICKS = register("stellar_stone_bricks", new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).strength(0.5F, 10.0F)));
    public static final Block MOSSY_STELLAR_STONE = register("mossy_stellar_stone", new Block(AbstractBlock.Settings.copy(STELLAR_STONE)));
    public static final Block MOSSY_STELLAR_COBBLESTONE = register("mossy_stellar_cobblestone", new Block(AbstractBlock.Settings.copy(STELLAR_STONE)));
    public static final Block STELLAR_CLAY = register("stellar_clay", new Block(AbstractBlock.Settings.copy(Blocks.CLAY)));
    public static final Block GLAUCOPHANITE = register("glaucophanite", new Block(AbstractBlock.Settings.create().strength(3.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block POLISHED_GLAUCOPHANITE = register("polished_glaucophanite", new Block(AbstractBlock.Settings.create().strength(3.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block SHADOW_STONE = register("shadow_stone", new ShadowStoneBlock(AbstractBlock.Settings.copy(Blocks.STONE)));

    //crystal
    public static final Block CRYSTAL_BLOCK = register("crystal_block", new Block(AbstractBlock.Settings.copy(Blocks.GLASS).luminance((state) -> 14)));
    public static final Block CRYSTAL_BRICKS = register("crystal_bricks", new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).luminance((state) -> 9)));
    public static final Block CRYSTAL_BRICKS_SLAB = register("crystal_bricks_slab", new SlabBlock(AbstractBlock.Settings.copy(CRYSTAL_BRICKS).nonOpaque()));
    public static final Block CRYSTAL_BRICKS_STAIRS = register("crystal_bricks_stairs", new StairsBlock(CRYSTAL_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(CRYSTAL_BRICKS)));
    public static final Block CRYSTAL_BRICKS_WALL = register("crystal_bricks_wall", new WallBlock(AbstractBlock.Settings.copy(CRYSTAL_BRICKS)));
    public static final Block STELLAR_STONE_CRYSTAL_BLOCK = register("stellar_stone_crystal_block", new BasicShiftableRenderBlock(AbstractBlock.Settings.copy(CRYSTAL_BLOCK).luminance((state) -> 13)));
    public static final Block SHADOW_CRYSTAL_BLOCK = register("shadow_crystal_block", new BasicShadowSpreaderBlock(AbstractBlock.Settings.copy(CRYSTAL_BLOCK).luminance((state) -> 12)));
    public static final Block CRYSTALLIZED_LEAVES = register("crystallized_leaves", new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).luminance((state) -> 12)));
    public static final Block CRYSTALLIZED_FIRE = register("crystallized_fire", new TransparentBlock(AbstractBlock.Settings.copy(Blocks.GLASS).luminance((state) -> 12).breakInstantly()));

    //glass and glass pane
    public static final Block SLIPPERY_SAND_GLASS = register("slippery_sand_glass", new TransparentBlock(AbstractBlock.Settings.copy(Blocks.GLASS).slipperiness(1.01F).allowsSpawning((state, reader, pos, entity) -> false).solidBlock((state, reader, pos) -> false).suffocates((state, reader, pos) -> false).blockVision((state, reader, pos) -> false)));
    public static final Block RED_SLIPPERY_SAND_GLASS = register("red_slippery_sand_glass", new TransparentBlock(AbstractBlock.Settings.copy(Blocks.GLASS).slipperiness(1.01F).allowsSpawning((state, reader, pos, entity) -> false).solidBlock((state, reader, pos) -> false).suffocates((state, reader, pos) -> false).blockVision((state, reader, pos) -> false)));
    public static final Block BLACK_SLIPPERY_SAND_GLASS = register("black_slippery_sand_glass", new TransparentBlock(AbstractBlock.Settings.copy(Blocks.GLASS).slipperiness(1.01F).allowsSpawning((state, reader, pos, entity) -> false).solidBlock((state, reader, pos) -> false).suffocates((state, reader, pos) -> false).blockVision((state, reader, pos) -> false)));
    public static final Block BLUE_SLIPPERY_SAND_GLASS = register("blue_slippery_sand_glass", new TransparentBlock(AbstractBlock.Settings.copy(Blocks.GLASS).slipperiness(1.01F).allowsSpawning((state, reader, pos, entity) -> false).solidBlock((state, reader, pos) -> false).suffocates((state, reader, pos) -> false).blockVision((state, reader, pos) -> false)));
    public static final Block GREEN_SLIPPERY_SAND_GLASS = register("green_slippery_sand_glass", new TransparentBlock(AbstractBlock.Settings.copy(Blocks.GLASS).slipperiness(1.01F).allowsSpawning((state, reader, pos, entity) -> false).solidBlock((state, reader, pos) -> false).suffocates((state, reader, pos) -> false).blockVision((state, reader, pos) -> false)));
    public static final Block SLIPPERY_SAND_GLASS_PANE = register("slippery_sand_glass_pane", new StainedGlassPaneBlock(DyeColor.YELLOW, AbstractBlock.Settings.create().slipperiness(1.01F).strength(0.3F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block RED_SLIPPERY_SAND_GLASS_PANE = register("red_slippery_sand_glass_pane", new StainedGlassPaneBlock(DyeColor.RED, AbstractBlock.Settings.create().slipperiness(1.01F).strength(0.3F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block BLACK_SLIPPERY_SAND_GLASS_PANE = register("black_slippery_sand_glass_pane", new StainedGlassPaneBlock(DyeColor.BLACK, AbstractBlock.Settings.create().slipperiness(1.01F).strength(0.3F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block BLUE_SLIPPERY_SAND_GLASS_PANE = register("blue_slippery_sand_glass_pane", new StainedGlassPaneBlock(DyeColor.BLUE, AbstractBlock.Settings.create().slipperiness(1.01F).strength(0.3F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block GREEN_SLIPPERY_SAND_GLASS_PANE = register("green_slippery_sand_glass_pane", new StainedGlassPaneBlock(DyeColor.GREEN, AbstractBlock.Settings.create().slipperiness(1.01F).strength(0.3F).sounds(BlockSoundGroup.GLASS).nonOpaque()));

    //ghost boat
    public static final Block GHOST_BOAT_PLANKS = register("ghost_boat_planks", new GhostBoatBlock(AbstractBlock.Settings.create().strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final GhostBoatRotatedPillarBlock GHOST_BOAT_LOG = register("ghost_boat_log", new GhostBoatRotatedPillarBlock(AbstractBlock.Settings.copy(AERIAL_TREE_LOG).nonOpaque()));
    public static final GhostBoatRotatedPillarBlock GHOST_BOAT_WOOD = register("ghost_boat_wood", new GhostBoatRotatedPillarBlock(AbstractBlock.Settings.copy(GHOST_BOAT_LOG).nonOpaque()));
    public static final SlabBlock GHOST_BOAT_SLAB = register("ghost_boat_slab", new GhostBoatSlabBlock(AbstractBlock.Settings.create().strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final StairsBlock GHOST_BOAT_STAIRS = register("ghost_boat_stairs", new GhostBoatStairBlock(GHOST_BOAT_PLANKS.getDefaultState(), AbstractBlock.Settings.create().strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final FenceBlock GHOST_BOAT_FENCE = register("ghost_boat_fence", new GhostBoatFenceBlock(AbstractBlock.Settings.create().strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final FenceGateBlock GHOST_BOAT_GATE = register("ghost_boat_gate", new GhostBoatFenceGateBlock(AerialHellWoodTypes.AERIAL_TREE, AbstractBlock.Settings.create().strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final DoorBlock GHOST_BOAT_DOOR = register("ghost_boat_door", new GhostBoatDoorBlock(BlockSetType.OAK, AbstractBlock.Settings.create().strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final TrapdoorBlock GHOST_BOAT_TRAPDOOR = register("ghost_boat_trapdoor", new GhostBoatTrapDoorBlock(BlockSetType.OAK, AbstractBlock.Settings.create().strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final ChestBlock GHOST_BOAT_CHEST = register("ghost_boat_chest", new GhostBoatChestBlock(AbstractBlock.Settings.create().strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block GHOST_BOAT_WOOL = register("ghost_boat_wool", new GhostBoatBlock(AbstractBlock.Settings.create().mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sounds(BlockSoundGroup.WOOL).nonOpaque()));
    public static final Block GHOST_STELLAR_COBBLESTONE = register("ghost_stellar_cobblestone", new GhostBoatBlock(AbstractBlock.Settings.create().strength(2.5F, 2.5F).requiresTool().sounds(BlockSoundGroup.STONE).nonOpaque()));
    public static final Block GHOST_RUBY_BLOCK = register("ghost_ruby_block", new GhostBoatBlock(AbstractBlock.Settings.create().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).nonOpaque()));
    public static final Block GHOST_FLUORITE_BLOCK = register("ghost_fluorite_block", new GhostBoatBlock(AbstractBlock.Settings.create().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).nonOpaque()));
    public static final Block GHOST_AZURITE_BLOCK = register("ghost_azurite_block", new GhostBoatBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).nonOpaque()));
    public static final Block GHOST_GOLD_BLOCK = register("ghost_gold_block", new GhostBoatBlock(AbstractBlock.Settings.copy(Blocks.GOLD_BLOCK).nonOpaque()));
    public static final AerialHellBarrelBlock GHOST_BOAT_BARREL = register("ghost_boat_barrel", new GhostBoatBarrelBlock(AbstractBlock.Settings.create().strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final CraftingTableBlock GHOST_BOAT_CRAFTING_TABLE = register("ghost_boat_crafting_table", new GhostBoatCraftingTableBlock(AbstractBlock.Settings.create().strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final PillarBlock GHOST_BOAT_VINE_ROPE_SPOOL = register("ghost_boat_vine_rope_spool", new GhostBoatVineRopeSpoolBlock(AbstractBlock.Settings.create().nonOpaque().mapColor(MapColor.BROWN).strength(1.2F).sounds(BlockSoundGroup.WOOD)));
    public static final Block GHOST_LANTERN = register("ghost_lantern", new GhostLanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN)));

    //other condition condition blocks
    public static final Block INTANGIBLE_TEMPORARY_BLOCK = register("intangible_temporary_block", new IntangibleTemporaryBlock(AbstractBlock.Settings.create().strength(2.0F, 3600000.0F).dropsNothing().pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.GLASS).luminance((state) -> 7).nonOpaque()));

    //reactors
    public static final Block WEAK_LIGHT_REACTOR = register("weak_light_reactor", new ReactorBlock(AbstractBlock.Settings.create().strength(5.0F, 100.0F).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.STONE).nonOpaque(), 32, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.BROKEN_WEAK_LIGHT_REACTOR));
    public static final Block HIGH_POWER_LIGHT_REACTOR = register("high_power_light_reactor", new ReactorBlock(AbstractBlock.Settings.create().strength(5.0F, 100.0F).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.STONE).nonOpaque(), 58, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.BROKEN_HIGH_POWER_LIGHT_REACTOR));
    public static final Block WEAK_SHADOW_REACTOR = register("weak_shadow_reactor", new ReactorBlock(AbstractBlock.Settings.create().strength(5.0F, 100.0F).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.STONE).nonOpaque(), 26, BiomeShifter.ShiftType.CORRUPT, () -> AerialHellBlocks.BROKEN_WEAK_SHADOW_REACTOR));
    public static final Block HIGH_POWER_SHADOW_REACTOR = register("high_power_shadow_reactor", new ReactorBlock(AbstractBlock.Settings.create().strength(5.0F, 100.0F).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.STONE).nonOpaque(), 60, BiomeShifter.ShiftType.CORRUPT, () -> AerialHellBlocks.BROKEN_HIGH_POWER_SHADOW_REACTOR));

    public static final Block BROKEN_WEAK_LIGHT_REACTOR = register("broken_weak_light_reactor", new Block(AbstractBlock.Settings.copy(WEAK_LIGHT_REACTOR)));
    public static final Block BROKEN_HIGH_POWER_LIGHT_REACTOR = register("broken_high_power_light_reactor", new Block(AbstractBlock.Settings.copy(HIGH_POWER_LIGHT_REACTOR)));
    public static final Block BROKEN_WEAK_SHADOW_REACTOR = register("broken_weak_shadow_reactor", new Block(AbstractBlock.Settings.copy(WEAK_SHADOW_REACTOR)));
    public static final Block BROKEN_HIGH_POWER_SHADOW_REACTOR = register("broken_high_power_shadow_reactor", new Block(AbstractBlock.Settings.copy(HIGH_POWER_SHADOW_REACTOR)));

    //solid_ethers
    public static final Block WHITE_SOLID_ETHER = register("white_solid_ether", new SolidEtherBlock(AbstractBlock.Settings.create().strength(0.2F).sounds(BlockSoundGroup.WOOL).nonOpaque()));
    public static final Block BLUE_SOLID_ETHER = register("blue_solid_ether", new BlueSolidEtherBlock(AbstractBlock.Settings.copy(WHITE_SOLID_ETHER)));
    public static final Block GOLDEN_SOLID_ETHER = register("golden_solid_ether", new SolidEtherBlock(AbstractBlock.Settings.copy(WHITE_SOLID_ETHER)));
    public static final Block GREEN_SOLID_ETHER = register("green_solid_ether", new GreenSolidEtherBlock(AbstractBlock.Settings.copy(WHITE_SOLID_ETHER)));
    public static final Block PURPLE_SOLID_ETHER = register("purple_solid_ether", new PurpleSolidEtherBlock(AbstractBlock.Settings.copy(WHITE_SOLID_ETHER)));

    //dungeons blocks
    public static final Block MUD_BRICKS = register("mud_bricks", new CoreProtectedBlock(AbstractBlock.Settings.create().strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block CRACKED_MUD_BRICKS = register("cracked_mud_bricks", new CoreProtectedBlock(AbstractBlock.Settings.copy(MUD_BRICKS)));
    public static final Block MOSSY_MUD_BRICKS = register("mossy_mud_bricks", new CoreProtectedBlock(AbstractBlock.Settings.copy(MUD_BRICKS)));
    public static final Block CHISELED_MUD_BRICKS = register("chiseled_mud_bricks", new CoreProtectedBlock(AbstractBlock.Settings.copy(MUD_BRICKS)));
    public static final Block LIGHT_MUD_BRICKS = register("light_mud_bricks", new CoreProtectedBlock(AbstractBlock.Settings.copy(MUD_BRICKS).luminance((state) -> 11)));
    public static final Block CRACKED_LIGHT_MUD_BRICKS = register("cracked_light_mud_bricks", new CoreProtectedBlock(AbstractBlock.Settings.copy(MUD_BRICKS)));
    public static final Block LUNATIC_STONE = register("lunatic_stone", new CoreProtectedBlock(AbstractBlock.Settings.create().strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block DARK_LUNATIC_STONE = register("dark_lunatic_stone", new CoreProtectedBlock(AbstractBlock.Settings.create().strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block ROOF_LUNATIC_STONE = register("roof_lunatic_stone", new CoreProtectedBlock(AbstractBlock.Settings.create().strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block CRACKED_LUNATIC_STONE = register("cracked_lunatic_stone", new CoreProtectedBlock(AbstractBlock.Settings.create().strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block CHISELED_LUNATIC_STONE = register("chiseled_lunatic_stone", new CoreProtectedBlock(AbstractBlock.Settings.create().strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block LIGHT_LUNATIC_STONE = register("light_lunatic_stone", new CoreProtectedBlock(AbstractBlock.Settings.copy(LUNATIC_STONE).luminance((state) -> 11)));
    public static final Block ROOF_LIGHT_LUNATIC_STONE = register("roof_light_lunatic_stone", new CoreProtectedBlock(AbstractBlock.Settings.copy(LUNATIC_STONE).luminance((state) -> 11)));
    public static final Block CRACKED_LIGHT_LUNATIC_STONE = register("cracked_light_lunatic_stone", new CoreProtectedBlock(AbstractBlock.Settings.copy(LUNATIC_STONE)));
    public static final Block SHADOW_CATACOMBS_BRICKS = register("shadow_catacombs_bricks", new CoreProtectedBlock(AbstractBlock.Settings.create().strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block CRACKED_SHADOW_CATACOMBS_BRICKS = register("cracked_shadow_catacombs_bricks", new CoreProtectedBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS)));
    public static final Block MOSSY_SHADOW_CATACOMBS_BRICKS = register("mossy_shadow_catacombs_bricks", new CoreProtectedBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS)));
    public static final Block CHISELED_SHADOW_CATACOMBS_BRICKS = register("chiseled_shadow_catacombs_bricks", new CoreProtectedBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS)));
    public static final Block BONE_SHADOW_CATACOMBS_BRICKS = register("bone_shadow_catacombs_bricks", new CoreProtectedBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS).sounds(BlockSoundGroup.BONE)));
    public static final Block SKULL_SHADOW_CATACOMBS_BRICKS = register("skull_shadow_catacombs_bricks", new CoreProtectedBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS).sounds(BlockSoundGroup.BONE)));
    public static final Block LIGHT_SHADOW_CATACOMBS_BRICKS = register("light_shadow_catacombs_bricks", new CoreProtectedBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS).luminance((state) -> 11)));
    public static final Block CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS = register("cracked_light_shadow_catacombs_bricks", new CoreProtectedBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS)));
    public static final Block GOLDEN_NETHER_BRICKS  = register("golden_nether_bricks", new CoreProtectedBlock(AbstractBlock.Settings.create().strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block CRACKED_GOLDEN_NETHER_BRICKS  = register("cracked_golden_nether_bricks", new CoreProtectedBlock(AbstractBlock.Settings.create().strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block CHISELED_GOLDEN_NETHER_BRICKS  = register("chiseled_golden_nether_bricks", new CoreProtectedBlock(AbstractBlock.Settings.create().strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block LIGHT_GOLDEN_NETHER_BRICKS = register("light_golden_nether_bricks", new CoreProtectedBlock(AbstractBlock.Settings.copy(GOLDEN_NETHER_BRICKS).luminance((state) -> 11)));
    public static final Block CRACKED_LIGHT_GOLDEN_NETHER_BRICKS = register("cracked_light_golden_nether_bricks", new CoreProtectedBlock(AbstractBlock.Settings.copy(GOLDEN_NETHER_BRICKS)));
    public static final PillarBlock LUNATIC_PILLAR = register("lunatic_pillar", new CoreProtectedRotatedPillarBlock(AbstractBlock.Settings.create().mapColor(MapColor.WHITE).strength(2.0F, 6.0F).sounds(BlockSoundGroup.METAL).requiresTool()));
    public static final PillarBlock LUNATIC_PILLAR_TOP = register("lunatic_pillar_top", new CoreProtectedRotatedPillarBlock(AbstractBlock.Settings.create().mapColor(MapColor.WHITE).strength(2.0F, 6.0F).sounds(BlockSoundGroup.METAL).requiresTool()));
    public static final Block VOLUCITE_STONE = register("volucite_stone", new CoreProtectedBlock(AbstractBlock.Settings.create().strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE)));
    public static final Block CRACKED_VOLUCITE_STONE = register("cracked_volucite_stone", new CoreProtectedBlock(AbstractBlock.Settings.create().strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE)));
    public static final Block CHISELED_VOLUCITE_STONE = register("chiseled_volucite_stone", new CoreProtectedBlock(AbstractBlock.Settings.create().strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE)));
    public static final Block LIGHT_VOLUCITE_STONE = register("light_volucite_stone", new CoreProtectedBlock(AbstractBlock.Settings.create().strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).luminance((state) -> 11)));
    public static final Block CRACKED_LIGHT_VOLUCITE_STONE = register("cracked_light_volucite_stone", new CoreProtectedBlock(AbstractBlock.Settings.create().strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE)));

    //dungeon cores
    public static final Block MUD_DUNGEON_CORE = register("mud_dungeon_core", new DungeonCoreBlock(AbstractBlock.Settings.create().strength(30.0F, 1200.0F).sounds(BlockSoundGroup.STONE).requiresTool(), 181));
    public static final Block LUNATIC_DUNGEON_CORE = register("lunatic_dungeon_core", new DungeonCoreBlock(AbstractBlock.Settings.create().strength(40.0F, 1200.0F).sounds(BlockSoundGroup.STONE).requiresTool(), 181));
    public static final Block SHADOW_CATACOMBS_DUNGEON_CORE = register("shadow_catacombs_dungeon_core", new DungeonCoreBlock(AbstractBlock.Settings.create().strength(30.0F, 1200.0F).sounds(BlockSoundGroup.STONE).requiresTool(), 181));
    public static final Block GOLDEN_NETHER_DUNGEON_CORE = register("golden_nether_dungeon_core", new DungeonCoreBlock(AbstractBlock.Settings.create().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.STONE).requiresTool(), 101));
    public static final Block VOLUCITE_DUNGEON_CORE = register("volucite_dungeon_core", new DungeonCoreBlock(AbstractBlock.Settings.create().strength(50.0F, 1200.0F).sounds(BlockSoundGroup.STONE).requiresTool(), 101));

    //dungeons slabs, stairs & walls
    public static final SlabBlock MUD_BRICKS_SLAB = register("mud_bricks_slab", new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(MUD_BRICKS)));
    public static final StairsBlock MUD_BRICKS_STAIRS = register("mud_bricks_stairs", new CoreProtectedStairsBlock(MUD_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(MUD_BRICKS)));
    public static final WallBlock MUD_BRICKS_WALL = register("mud_bricks_wall", new CoreProtectedWallBlock(AbstractBlock.Settings.copy(MUD_BRICKS)));
    public static final SlabBlock CRACKED_MUD_BRICKS_SLAB = register("cracked_mud_bricks_slab", new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(CRACKED_MUD_BRICKS)));
    public static final StairsBlock CRACKED_MUD_BRICKS_STAIRS = register("cracked_mud_bricks_stairs", new CoreProtectedStairsBlock(CRACKED_MUD_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(MUD_BRICKS)));
    public static final WallBlock CRACKED_MUD_BRICKS_WALL = register("cracked_mud_bricks_wall", new CoreProtectedWallBlock(AbstractBlock.Settings.copy(CRACKED_MUD_BRICKS)));
    public static final SlabBlock MOSSY_MUD_BRICKS_SLAB = register("mossy_mud_bricks_slab", new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(MOSSY_MUD_BRICKS)));
    public static final StairsBlock MOSSY_MUD_BRICKS_STAIRS = register("mossy_mud_bricks_stairs", new CoreProtectedStairsBlock(MOSSY_MUD_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(MOSSY_MUD_BRICKS)));
    public static final WallBlock MOSSY_MUD_BRICKS_WALL = register("mossy_mud_bricks_wall", new CoreProtectedWallBlock(AbstractBlock.Settings.copy(MOSSY_MUD_BRICKS)));
    public static final SlabBlock VOLUCITE_STONE_SLAB = register("volucite_stone_slab", new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(VOLUCITE_STONE)));
    public static final StairsBlock VOLUCITE_STONE_STAIRS = register("volucite_stone_stairs", new CoreProtectedStairsBlock(VOLUCITE_STONE.getDefaultState(), AbstractBlock.Settings.copy(VOLUCITE_STONE)));
    public static final WallBlock VOLUCITE_STONE_WALL = register("volucite_stone_wall", new CoreProtectedWallBlock(AbstractBlock.Settings.copy(VOLUCITE_STONE)));
    public static final SlabBlock CRACKED_VOLUCITE_STONE_SLAB = register("cracked_volucite_stone_slab", new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(CRACKED_VOLUCITE_STONE)));
    public static final StairsBlock CRACKED_VOLUCITE_STONE_STAIRS = register("cracked_volucite_stone_stairs", new CoreProtectedStairsBlock(CRACKED_VOLUCITE_STONE.getDefaultState(), AbstractBlock.Settings.copy(VOLUCITE_STONE)));
    public static final WallBlock CRACKED_VOLUCITE_STONE_WALL = register("cracked_volucite_stone_wall", new CoreProtectedWallBlock(AbstractBlock.Settings.copy(CRACKED_VOLUCITE_STONE)));
    public static final SlabBlock LUNATIC_STONE_SLAB = register("lunatic_stone_slab", new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(LUNATIC_STONE)));
    public static final StairsBlock LUNATIC_STONE_STAIRS = register("lunatic_stone_stairs", new CoreProtectedStairsBlock(LUNATIC_STONE.getDefaultState(), AbstractBlock.Settings.copy(LUNATIC_STONE)));
    public static final WallBlock LUNATIC_STONE_WALL = register("lunatic_stone_wall", new CoreProtectedWallBlock(AbstractBlock.Settings.copy(LUNATIC_STONE)));
    public static final SlabBlock DARK_LUNATIC_STONE_SLAB = register("dark_lunatic_stone_slab", new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(DARK_LUNATIC_STONE)));
    public static final StairsBlock DARK_LUNATIC_STONE_STAIRS = register("dark_lunatic_stone_stairs", new CoreProtectedStairsBlock(DARK_LUNATIC_STONE.getDefaultState(), AbstractBlock.Settings.copy(DARK_LUNATIC_STONE)));
    public static final WallBlock DARK_LUNATIC_STONE_WALL = register("dark_lunatic_stone_wall", new CoreProtectedWallBlock(AbstractBlock.Settings.copy(DARK_LUNATIC_STONE)));
    public static final SlabBlock CRACKED_LUNATIC_STONE_SLAB = register("cracked_lunatic_stone_slab", new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(CRACKED_LUNATIC_STONE)));
    public static final StairsBlock CRACKED_LUNATIC_STONE_STAIRS = register("cracked_lunatic_stone_stairs", new CoreProtectedStairsBlock(CRACKED_LUNATIC_STONE.getDefaultState(), AbstractBlock.Settings.copy(VOLUCITE_STONE)));
    public static final WallBlock CRACKED_LUNATIC_STONE_WALL = register("cracked_lunatic_stone_wall", new CoreProtectedWallBlock(AbstractBlock.Settings.copy(CRACKED_LUNATIC_STONE)));
    public static final SlabBlock SHADOW_CATACOMBS_BRICKS_SLAB = register("shadow_catacombs_bricks_slab", new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS)));
    public static final StairsBlock SHADOW_CATACOMBS_BRICKS_STAIRS = register("shadow_catacombs_bricks_stairs", new CoreProtectedStairsBlock(SHADOW_CATACOMBS_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS)));
    public static final WallBlock SHADOW_CATACOMBS_BRICKS_WALL = register("shadow_catacombs_bricks_wall", new CoreProtectedWallBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS)));
    public static final SlabBlock CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB = register("cracked_shadow_catacombs_bricks_slab", new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(CRACKED_SHADOW_CATACOMBS_BRICKS)));
    public static final StairsBlock CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS = register("cracked_shadow_catacombs_bricks_stairs", new CoreProtectedStairsBlock(CRACKED_SHADOW_CATACOMBS_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(VOLUCITE_STONE)));
    public static final WallBlock CRACKED_SHADOW_CATACOMBS_BRICKS_WALL = register("cracked_shadow_catacombs_bricks_wall", new CoreProtectedWallBlock(AbstractBlock.Settings.copy(CRACKED_SHADOW_CATACOMBS_BRICKS)));
    public static final SlabBlock MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB = register("mossy_shadow_catacombs_bricks_slab", new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(MOSSY_SHADOW_CATACOMBS_BRICKS)));
    public static final StairsBlock MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS = register("mossy_shadow_catacombs_bricks_stairs", new CoreProtectedStairsBlock(MOSSY_SHADOW_CATACOMBS_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(MOSSY_SHADOW_CATACOMBS_BRICKS)));
    public static final WallBlock MOSSY_SHADOW_CATACOMBS_BRICKS_WALL = register("mossy_shadow_catacombs_bricks_wall", new CoreProtectedWallBlock(AbstractBlock.Settings.copy(MOSSY_SHADOW_CATACOMBS_BRICKS)));
    public static final PaneBlock SHADOW_BARS = register("shadow_bars", new ShadowBarsBlock(METAL_NOTSOLID_MATERIAL));
    public static final SlabBlock GOLDEN_NETHER_BRICKS_SLAB = register("golden_nether_bricks_slab", new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(GOLDEN_NETHER_BRICKS)));
    public static final StairsBlock GOLDEN_NETHER_BRICKS_STAIRS = register("golden_nether_bricks_stairs", new CoreProtectedStairsBlock(GOLDEN_NETHER_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(VOLUCITE_STONE)));
    public static final WallBlock GOLDEN_NETHER_BRICKS_WALL = register("golden_nether_bricks_wall", new CoreProtectedWallBlock(AbstractBlock.Settings.copy(GOLDEN_NETHER_BRICKS)));
    public static final SlabBlock CRACKED_GOLDEN_NETHER_BRICKS_SLAB = register("cracked_golden_nether_bricks_slab", new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(CRACKED_GOLDEN_NETHER_BRICKS)));
    public static final StairsBlock CRACKED_GOLDEN_NETHER_BRICKS_STAIRS = register("cracked_golden_nether_bricks_stairs", new CoreProtectedStairsBlock(CRACKED_GOLDEN_NETHER_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(VOLUCITE_STONE)));
    public static final WallBlock CRACKED_GOLDEN_NETHER_BRICKS_WALL = register("cracked_golden_nether_bricks_wall", new CoreProtectedWallBlock(AbstractBlock.Settings.copy(CRACKED_GOLDEN_NETHER_BRICKS)));

    //smoky quartz
    public static final Block SMOKY_QUARTZ_BLOCK = register("smoky_quartz_block", new Block(AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK)));
    public static final Block SMOOTH_SMOKY_QUARTZ = register("smooth_smoky_quartz", new Block(AbstractBlock.Settings.copy(SMOKY_QUARTZ_BLOCK)));
    public static final Block CHISELED_SMOKY_QUARTZ_BLOCK = register("chiseled_smoky_quartz_block", new Block(AbstractBlock.Settings.copy(SMOKY_QUARTZ_BLOCK)));
    public static final Block SMOKY_QUARTZ_BRICKS = register("smoky_quartz_bricks", new Block(AbstractBlock.Settings.copy(SMOKY_QUARTZ_BLOCK)));
    public static final PillarBlock SMOKY_QUARTZ_PILLAR = register("smoky_quartz_pillar", new PillarBlock(AbstractBlock.Settings.copy(SMOKY_QUARTZ_BLOCK)));
    public static final SlabBlock SMOKY_QUARTZ_SLAB = register("smoky_quartz_slab", new SlabBlock(AbstractBlock.Settings.copy(SMOKY_QUARTZ_BLOCK)));
    public static final SlabBlock SMOOTH_SMOKY_QUARTZ_SLAB = register("smooth_smoky_quartz_slab", new SlabBlock(AbstractBlock.Settings.copy(SMOKY_QUARTZ_BLOCK)));
    public static final StairsBlock SMOKY_QUARTZ_STAIRS = register("smoky_quartz_stairs", new StairsBlock(SMOKY_QUARTZ_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(SMOKY_QUARTZ_BLOCK)));
    public static final StairsBlock SMOOTH_SMOKY_QUARTZ_STAIRS = register("smooth_smoky_quartz_stairs", new StairsBlock(SMOOTH_SMOKY_QUARTZ.getDefaultState(), AbstractBlock.Settings.copy(SMOKY_QUARTZ_BLOCK)));

    //dungeon trapped blocks
    public static final Block TRAPPED_MUD_BRICKS = register("trapped_mud_bricks", new CoreProtectedTrappedBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN)));
    public static final Block TRAPPED_LIGHT_MUD_BRICKS = register("trapped_light_mud_bricks", new CoreProtectedTrappedBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN).luminance((state) -> 11)));
    public static final Block TRAPPED_LUNATIC_STONE = register("trapped_lunatic_stone", new CoreProtectedTrappedBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN)));
    public static final Block TRAPPED_LIGHT_LUNATIC_STONE = register("trapped_light_lunatic_stone", new CoreProtectedTrappedBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN).luminance((state) -> 11)));
    public static final Block TRAPPED_GOLDEN_NETHER_BRICKS = register("trapped_golden_nether_bricks", new CoreProtectedTrappedBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN)));
    public static final Block TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS = register("trapped_light_golden_nether_bricks", new CoreProtectedTrappedBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN).luminance((state) -> 11)));

    //dungeon other blocks, loots
    public static final PillarBlock MUD_BONE_BLOCK = register("mud_bone_block", new PillarBlock(AbstractBlock.Settings.create().mapColor(MapColor.BROWN).requiresTool().strength(2.5F).sounds(BlockSoundGroup.BONE)));
    public static final Block MUD_BONE_PILE_BLOCK = register("mud_bone_pile_block", new BonePileBlock(AbstractBlock.Settings.create().mapColor(MapColor.BROWN).ticksRandomly().strength(2.5F).sounds(BlockSoundGroup.BONE)));
    public static final Block THORNY_COBWEB = register("thorny_cobweb", new ThornyWebBlock(AbstractBlock.Settings.create().noCollision().requiresTool().strength(8.0F)));
    public static final Block AERIAL_NETHERRACK = register("aerial_netherrack", new Block(AbstractBlock.Settings.create().mapColor(MapColor.RED).requiresTool().strength(6.0F, 8.0F)));
    public static final SlabBlock AERIAL_NETHERRACK_SLAB = register("aerial_netherrack_slab", new SlabBlock(AbstractBlock.Settings.copy(AERIAL_NETHERRACK)));
    public static final StairsBlock AERIAL_NETHERRACK_STAIRS = register("aerial_netherrack_stairs", new StairsBlock(AERIAL_NETHERRACK.getDefaultState(), AbstractBlock.Settings.copy(AERIAL_NETHERRACK)));
    public static final WallBlock AERIAL_NETHERRACK_WALL = register("aerial_netherrack_wall", new WallBlock(AbstractBlock.Settings.copy(AERIAL_NETHERRACK)));

    //dungeon bookshelfs
    public static final Block MUD_BOOKSHELF = register("mud_bookshelf", new CoreProtectedBlock(AbstractBlock.Settings.copy(MUD_BRICKS)));
    public static final Block LUNATIC_BOOKSHELF = register("lunatic_bookshelf", new CoreProtectedBlock(AbstractBlock.Settings.copy(LUNATIC_STONE)));
    public static final Block GOLDEN_NETHER_BOOKSHELF = register("golden_nether_bookshelf", new CoreProtectedBlock(AbstractBlock.Settings.copy(GOLDEN_NETHER_BRICKS)));
    public static final Block SHADOW_CATACOMBS_BOOKSHELF = register("shadow_catacombs_bookshelf", new CoreProtectedBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS)));
    public static final Block VOLUCITE_BOOKSHELF = register("volucite_bookshelf", new CoreProtectedBlock(AbstractBlock.Settings.copy(VOLUCITE_STONE)));

    //glyph blocks
    public static final Block MUD_GLYPH_BLOCK = register("mud_glyph_block", new CoreProtectedGlyphBlock(AbstractBlock.Settings.copy(MUD_BRICKS).luminance((state) -> 9)));
    public static final Block LUNATIC_GLYPH_BLOCK = register("lunatic_glyph_block", new CoreProtectedGlyphBlock(AbstractBlock.Settings.copy(LUNATIC_STONE).luminance((state) -> 9)));
    public static final Block GOLDEN_NETHER_PRISON_GLYPH_BLOCK = register("golden_nether_prison_glyph_block", new CoreProtectedGlyphBlock(AbstractBlock.Settings.copy(GOLDEN_NETHER_BRICKS).luminance((state) -> 9)));
    public static final Block VOLUCITE_GLYPH_BLOCK = register("volucite_glyph_block", new CoreProtectedGlyphBlock(AbstractBlock.Settings.copy(VOLUCITE_STONE).luminance((state) -> 9)));
    public static final Block SHADOW_CATACOMBS_GLYPH_BLOCK = register("shadow_catacombs_glyph_block", new CoreProtectedGlyphBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS).luminance((state) -> 9)));

    //trophies
    public static final Block MUD_CYCLE_MAGE_TROPHY = register("mud_cycle_mage_trophy", new BottomSlabLikeTrophyBlock(AbstractBlock.Settings.copy(LUNATIC_STONE).requiresTool()));
    public static final Block LUNAR_PRIEST_TROPHY = register("lunar_priest_trophy", new BottomSlabLikeTrophyBlock(AbstractBlock.Settings.copy(LUNATIC_STONE).requiresTool()));
    public static final Block LILITH_TROPHY = register("lilith_trophy", new BottomSlabLikeTrophyBlock(AbstractBlock.Settings.copy(LUNATIC_STONE).requiresTool()));
    public static final Block CHAINED_GOD_TROPHY = register("chained_god_trophy", new BottomSlabLikeTrophyBlock(AbstractBlock.Settings.copy(GOLDEN_NETHER_BRICKS).requiresTool()));

    //ores
    public static final Block IRON_STELLAR_ORE = register("iron_stellar_ore", new AerialHellOreBlock(0, 2, AbstractBlock.Settings.create().strength(3.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block GOLD_STELLAR_ORE = register("gold_stellar_ore", new AerialHellOreBlock(0, 2, AbstractBlock.Settings.create().strength(3.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block DIAMOND_STELLAR_ORE = register("diamond_stellar_ore", new AerialHellOreBlock(3, 5, AbstractBlock.Settings.create().strength(5.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block FLUORITE_ORE = register("fluorite_ore", new BiomeShifterOreBlock(0, 2, AbstractBlock.Settings.create().strength(3.0F).sounds(BlockSoundGroup.STONE).requiresTool(), 2, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.SMOKY_QUARTZ_ORE));
    public static final Block MAGMATIC_GEL_ORE = register("magmatic_gel_ore", new MagmaticGelOreBlock(0, 2, AbstractBlock.Settings.create().strength(3.0F).sounds(BlockSoundGroup.STONE).luminance(s -> 4).requiresTool()));
    public static final Block RUBY_ORE = register("ruby_ore", new AerialHellOreBlock(0, 0, AbstractBlock.Settings.create().strength(3.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block AZURITE_ORE = register("azurite_ore", new AerialHellOreBlock(0, 0, AbstractBlock.Settings.create().strength(3.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block VOLUCITE_ORE = register("volucite_ore", new VoluciteOreBlock(0, 0, AbstractBlock.Settings.create().strength(5.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block OBSIDIAN_ORE = register("obsidian_ore", new AerialHellOreBlock(0, 0, AbstractBlock.Settings.create().strength(5.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block SMOKY_QUARTZ_ORE = register("smoky_quartz_ore", new AerialHellOreBlock(1, 3, AbstractBlock.Settings.create().strength(3.0F).sounds(BlockSoundGroup.STONE).requiresTool()));

    public static final Block RAW_RUBY_BLOCK = register("raw_ruby_block", new Block(AbstractBlock.Settings.copy(Blocks.RAW_IRON_BLOCK).requiresTool()));
    public static final Block RAW_AZURITE_BLOCK = register("raw_azurite_crystal_block", new Block(AbstractBlock.Settings.copy(Blocks.RAW_IRON_BLOCK).requiresTool()));
    public static final Block RAW_VOLUCITE_BLOCK = register("raw_volucite_block", new Block(AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK).requiresTool()));

    public static final Block FLUORITE_BLOCK = register("fluorite_block", new BiomeShifterBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).requiresTool(), 7, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.SMOKY_QUARTZ_BLOCK));
    public static final Block MAGMATIC_GEL_BLOCK = register("magmatic_gel_block", new MagmaticGelBlock(AbstractBlock.Settings.create().strength(1.0F, 1600.0F).ticksRandomly().sounds(BlockSoundGroup.GLASS).nonOpaque().requiresTool().blockVision((state, reader, pos) -> false)));
    public static final Block RUBY_BLOCK = register("ruby_block", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).requiresTool()));
    public static final Block AZURITE_BLOCK = register("azurite_block", new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).requiresTool()));
    public static final Block VOLUCITE_BLOCK = register("volucite_block", new Block(AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK).requiresTool()));

    //legendary ores blocks
    public static final Block ARSONIST_BLOCK = register("arsonist_block", new ArsonistBlock(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK).requiresTool().luminance((state) -> 9)));
    public static final Block LUNATIC_CRYSTAL_BLOCK = register("lunatic_crystal_block", new Block(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK).sounds(BlockSoundGroup.GLASS).requiresTool().luminance((state) -> 9)));
    public static final Block CURSED_CRYSAL_BLOCK = register("cursed_crystal_block", new Block(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK).sounds(BlockSoundGroup.GLASS).requiresTool().luminance((state) -> 9)));

    //cactus
    public static final SkyCactusBlock SKY_CACTUS = register("sky_cactus", new SkyCactusBlock(AbstractBlock.Settings.create().mapColor(MapColor.CYAN).strength(0.4F).sounds(BlockSoundGroup.WOOL).ticksRandomly()));
    public static final Block SKY_CACTUS_FIBER_PLANKS = register("sky_cactus_fiber_planks", new Block(SKY_CACTUS_FIBER_MATERIAL));
    public static final Block SKY_CACTUS_FIBER_BOOKSHELF = register("sky_cactus_fiber_bookshelf", new Block(AbstractBlock.Settings.copy(SKY_CACTUS_FIBER_PLANKS)));
    public static final SkyCactusBlock VIBRANT_SKY_CACTUS = register("vibrant_sky_cactus", new SkyCactusBlock(AbstractBlock.Settings.create().mapColor(MapColor.BLUE).strength(0.4F).sounds(BlockSoundGroup.WOOL).ticksRandomly().luminance(s -> 15).nonOpaque()));
    public static final Block VIBRANT_SKY_CACTUS_FIBER_LANTERN = register("vibrant_sky_cactus_fiber_lantern", new Block(AbstractBlock.Settings.create().mapColor(MapColor.WHITE).strength(0.5F).sounds(BlockSoundGroup.GLASS).nonOpaque().luminance(s -> 15)));

    //bushes
    public static final Block AERIAL_BERRY_BUSH = register("aerial_berry_bush", new AerialBerryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)));
    public static final Block VIBRANT_AERIAL_BERRY_BUSH = register("vibrant_aerial_berry_bush", new VibrantAerialBerryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)));

    //crops
    public static final Block STELLAR_WHEAT = register("stellar_wheat", new StellarWheatBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)));

    //vertical growing plants
    public static final VerticalGrowingPlantBlock CLIMBING_VINE = register("climbing_vine", new VerticalGrowingPlantBlock(AbstractBlock.Settings.copy(Blocks.SUGAR_CANE), 4));
    public static final VerticalGrowingPlantBlock STELLAR_SUGAR_CANE = register("stellar_sugar_cane", new VerticalGrowingPlantBlock(AbstractBlock.Settings.copy(Blocks.SUGAR_CANE), 5));

    //chorus like
    public static final ChorusPlantLikeBlock FULL_MOON_PLANT = register("full_moon_plant", new ChorusPlantLikeBlock(AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).notSolid().strength(0.4F).sounds(BlockSoundGroup.WOOD).nonOpaque().pistonBehavior(PistonBehavior.DESTROY).luminance((state) -> 10)));
    public static final ChorusFlowerLikeBlock FULL_MOON_FLOWER = register("full_moon_flower", new ChorusFlowerLikeBlock(FULL_MOON_PLANT, AbstractBlock.Settings.create().mapColor(MapColor.PURPLE).notSolid().ticksRandomly().strength(0.4F).sounds(BlockSoundGroup.WOOD).nonOpaque().allowsSpawning((state, blockGetter, pos, entitytype) -> false).pistonBehavior(PistonBehavior.DESTROY).solidBlock((state, blockGetter, pos) -> false).luminance((state) -> 15)));

    //vines
    public static final CaveVinesHeadBlock GLOWING_STICK_FRUIT_VINES = register("glowing_stick_fruit_vines", new AerialHellCaveVinesBlock(AbstractBlock.Settings.copy(Blocks.CAVE_VINES)));
    public static final CaveVinesBodyBlock GLOWING_STICK_FRUIT_VINES_PLANT = register("glowing_stick_fruit_vines_plant", new AerialHellCaveVinesPlantBlock(AbstractBlock.Settings.copy(Blocks.CAVE_VINES_PLANT)));
    public static final CaveVinesHeadBlock BLOSSOMING_VINES = register("blossoming_vines", new AerialHellCaveVinesBlock(AbstractBlock.Settings.create().ticksRandomly().noCollision().breakInstantly().sounds(BlockSoundGroup.CAVE_VINES)));
    public static final CaveVinesBodyBlock BLOSSOMING_VINES_PLANT = register("blossoming_vines_plant", new AerialHellCaveVinesPlantBlock(AbstractBlock.Settings.copy(BLOSSOMING_VINES)));
    public static final AerialHellTwistingVinesBlock LAZULI_ROOTS = register("lazuli_roots", new AerialHellTwistingVinesBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES)));
    public static final AerialHellTwistingVinesPlantBlock LAZULI_ROOTS_PLANT = register("lazuli_roots_plant", new AerialHellTwistingVinesPlantBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES_PLANT)));
    public static final AerialHellTwistingVinesBlock STELLAR_ROOTS = register("stellar_roots", new AerialHellTwistingVinesBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES)));
    public static final AerialHellTwistingVinesPlantBlock STELLAR_ROOTS_PLANT = register("stellar_roots_plant", new AerialHellTwistingVinesPlantBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES_PLANT)));
    public static final AerialHellTwistingVinesBlock DEAD_ROOTS = register("dead_roots", new DeadRootsBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES)));
    public static final AerialHellTwistingVinesPlantBlock DEAD_ROOTS_PLANT = register("dead_roots_plant", new DeadRootsPlantBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES_PLANT)));
    public static final AerialHellTwistingVinesBlock GLOWING_ROOTS = register("glowing_roots", new AerialHellTwistingVinesBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES).luminance((state) -> 9)));
    public static final AerialHellTwistingVinesPlantBlock GLOWING_ROOTS_PLANT = register("glowing_roots_plant", new AerialHellTwistingVinesPlantBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES_PLANT).luminance((state) -> 14)));
    public static final AerialHellTwistingVinesBlock SHADOW_GLOWING_ROOTS = register("shadow_glowing_roots", new AerialHellTwistingVinesBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES).luminance((state) -> 8)));
    public static final AerialHellTwistingVinesPlantBlock SHADOW_GLOWING_ROOTS_PLANT = register("shadow_glowing_roots_plant", new AerialHellTwistingVinesPlantBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES_PLANT).luminance((state) -> 13)));

    //grass
    public static final Block STELLAR_GRASS = register("stellar_grass", new ShiftableRenderTallGrassBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block STELLAR_GRASS_BALL = register("stellar_grass_ball", new ShiftableRenderTallGrassBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block STELLAR_FERN = register("stellar_fern", new AerialHellTallGrassBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block STELLAR_TALL_GRASS = register("stellar_tall_grass", new TallPlantBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block STELLAR_TALL_FERN = register("stellar_tall_fern", new TallPlantBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final VerticalGrowingPlantBlock STELLAR_VERY_TALL_GRASS = register("stellar_very_tall_grass", new VerticalGrowingPlantBlock(AbstractBlock.Settings.copy(Blocks.SUGAR_CANE), 3));
    public static final Block BLUISH_FERN = register("bluish_fern", new AerialHellTallGrassBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block TALL_BLUISH_FERN = register("tall_bluish_fern", new TallPlantBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block POLYCHROME_FERN = register("polychrome_fern", new AerialHellTallGrassBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block TALL_POLYCHROME_FERN = register("tall_polychrome_fern", new TallPlantBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block STELLAR_DEAD_BUSH = register("stellar_dead_bush", new AerialHellDeadBushBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).replaceable().mapColor(MapColor.BROWN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block BRAMBLES = register("brambles", new BramblesBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).noCollision().strength(0.5F).sounds(BlockSoundGroup.GRASS)));
    public static final Block SHADOW_BRAMBLES = register("shadow_brambles", new BramblesBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).noCollision().strength(0.5F).sounds(BlockSoundGroup.GRASS)));
    public static final Block SHADOW_GRASS = register("shadow_grass", new ShadowPlantBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block SHADOW_GRASS_BALL = register("shadow_grass_ball", new ShadowPlantBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block PURPLISH_STELLAR_GRASS = register("purplish_stellar_grass", new AerialHellTallGrassBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block STELLAR_CLOVERS = register("stellar_clovers", new AerialHellTallGrassBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block GLOWING_STELLAR_GRASS = register("glowing_stellar_grass", new GlowingStellarTallGrass(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).replaceable().ticksRandomly().noCollision().luminance((state) -> {return state.get(Properties.LIT) ? 10 : 0;}).breakInstantly().sounds(BlockSoundGroup.GRASS)));

    //flowers
    public static final Block BLUE_FLOWER = register("blue_flower", new FlowerBlock(StatusEffects.BLINDNESS, 4, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final Block BLACK_ROSE = register("black_rose", new FlowerBlock(StatusEffects.SLOW_FALLING, 12, AbstractBlock.Settings.copy(Blocks.DANDELION)));
    public static final Block BELLFLOWER = register("bellflower", new FlowerBlock(StatusEffects.MINING_FATIGUE, 12, AbstractBlock.Settings.copy(Blocks.DANDELION)));

    //potted things
    public static final FlowerPotBlock POTTED_BLUE_FLOWER = register("potted_blue_flower", new FlowerPotBlock(BLUE_FLOWER, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_BLACK_ROSE = register("potted_black_rose", new FlowerPotBlock(BLACK_ROSE, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_BELLFLOWER = register("potted_bellflower", new FlowerPotBlock(BELLFLOWER, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_STELLAR_FERN = register("potted_stellar_fern", new FlowerPotBlock(STELLAR_FERN, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_STELLAR_DEAD_BUSH = register("potted_stellar_dead_bush", new FlowerPotBlock(STELLAR_DEAD_BUSH, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_SKY_CACTUS = register("potted_sky_cactus", new FlowerPotBlock(SKY_CACTUS, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_VIBRANT_SKY_CACTUS = register("potted_vibrant_sky_cactus", new FlowerPotBlock(VIBRANT_SKY_CACTUS, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_AERIAL_TREE_SAPLING = register("potted_aerial_tree_sapling", new FlowerPotBlock(AERIAL_TREE_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_GOLDEN_BEECH_SAPLING = register("potted_golden_beech_sapling", new FlowerPotBlock(GOLDEN_BEECH_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_COPPER_PINE_SAPLING = register("potted_copper_pine_sapling", new FlowerPotBlock(COPPER_PINE_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_LAPIS_ROBINIA_SAPLING = register("potted_lapis_robinia_sapling", new FlowerPotBlock(LAPIS_ROBINIA_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_SHADOW_PINE_SAPLING = register("potted_shadow_pine_sapling", new FlowerPotBlock(SHADOW_PINE_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_PURPLE_SHADOW_PINE_SAPLING = register("potted_purple_shadow_pine_sapling", new FlowerPotBlock(PURPLE_SHADOW_PINE_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_STELLAR_JUNGLE_TREE_SAPLING = register("potted_stellar_jungle_tree_sapling", new FlowerPotBlock(STELLAR_JUNGLE_TREE_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_CORTINARIUS_VIOLACEUS = register("potted_cortinarius_violaceus", new FlowerPotBlock(CORTINARIUS_VIOLACEUS, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_VERDIGRIS_AGARIC = register("potted_verdigris_agaric", new FlowerPotBlock(VERDIGRIS_AGARIC, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_VINE_BLOSSOM = register("potted_vine_blossom", new FlowerPotBlock(BLOSSOMING_VINES, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_GLOWING_BOLETUS = register("potted_glowing_boletus", new FlowerPotBlock(GLOWING_BOLETUS, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).luminance((state) -> 9)));

    //with gui
    public static final Block OSCILLATOR = register("oscillator", new OscillatorBlock(AbstractBlock.Settings.create().strength(2.0F).sounds(BlockSoundGroup.STONE)));
    public static final Block FREEZER = register("freezer", new FreezerBlock(AbstractBlock.Settings.create().strength(2.0F).sounds(BlockSoundGroup.STONE)));
    public static final Block STELLAR_FURNACE = register("stellar_furnace", new StellarFurnaceBlock(AbstractBlock.Settings.create().requiresTool().strength(3.5F).luminance(getLightValueLit(13))));
    public static final Block GHOST_STELLAR_FURNACE = register("ghost_stellar_furnace", new GhostStellarFurnaceBlock(AbstractBlock.Settings.create().requiresTool().nonOpaque().strength(3.5F).luminance(getLightValueLit(13))));

    private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {return (state) -> {return state.get(Properties.LIT) ? lightValue : 0;};}

    //chests
    public static final ChestBlock AERIAL_TREE_CHEST = register("aerial_tree_chest", new AerialHellChestBlock(AERIAL_TREE_MATERIAL));
    public static final ChestBlock GOLDEN_BEECH_CHEST = register("golden_beech_chest", new AerialHellChestBlock(AERIAL_TREE_MATERIAL));
    public static final ChestBlock COPPER_PINE_CHEST = register("copper_pine_chest", new AerialHellChestBlock(COPPER_PINE_MATERIAL));
    public static final ChestBlock LAPIS_ROBINIA_CHEST = register("lapis_robinia_chest", new AerialHellChestBlock(COPPER_PINE_MATERIAL));
    public static final ChestBlock SHADOW_PINE_CHEST = register("shadow_pine_chest", new AerialHellChestBlock(SHADOW_PINE_MATERIAL));
    public static final ChestBlock STELLAR_JUNGLE_TREE_CHEST = register("stellar_jungle_tree_chest", new AerialHellChestBlock(COPPER_PINE_MATERIAL));
    public static final ChestBlock SKY_CACTUS_FIBER_CHEST = register("sky_cactus_fiber_chest", new AerialHellChestBlock(SKY_CACTUS_FIBER_MATERIAL));
    public static final ChestBlock GRAY_SHROOM_CHEST = register("gray_shroom_chest", new AerialHellChestBlock(SHROOM_MATERIAL));
    public static final ChestBlock MUD_CHEST = register("mud_chest", new CoreProtectedChestBlock(MUD_CHEST_MATERIAL));
    public static final ChestBlock LUNATIC_CHEST = register("lunatic_chest", new CoreProtectedChestBlock(LUNATIC_CHEST_MATERIAL));
    public static final ChestBlock VOLUCITE_CHEST = register("volucite_chest", new CoreProtectedChestBlock(VOLUCITE_CHEST_MATERIAL));
    public static final ChestBlock SHADOW_CATACOMBS_CHEST = register("shadow_catacombs_chest", new CoreProtectedChestBlock(MUD_CHEST_MATERIAL));
    public static final ChestBlock GOLDEN_NETHER_CHEST = register("golden_nether_chest", new CoreProtectedChestBlock(GOLDEN_NETHER_CHEST_MATERIAL));

    //chest mimics
    public static final Block AERIAL_TREE_CHEST_MIMIC = register("aerial_tree_chest_mimic", new ChestMimicBlock(AbstractBlock.Settings.copy(Blocks.CHEST)));
    public static final Block GOLDEN_BEECH_CHEST_MIMIC = register("golden_beech_chest_mimic", new ChestMimicBlock(AbstractBlock.Settings.copy(Blocks.CHEST)));
    public static final Block COPPER_PINE_CHEST_MIMIC = register("copper_pine_chest_mimic", new ChestMimicBlock(AbstractBlock.Settings.copy(Blocks.CHEST)));
    public static final Block SKY_CACTUS_FIBER_CHEST_MIMIC = register("sky_cactus_fiber_chest_mimic", new ChestMimicBlock(AbstractBlock.Settings.copy(Blocks.CHEST)));

    //barrel mimics
    public static final Block SHADOW_PINE_BARREL_MIMIC = register("shadow_pine_barrel_mimic", new BarrelMimicBlock(AbstractBlock.Settings.copy(Blocks.BARREL)));

    //fences, bars or walls
    public static final FenceBlock AERIAL_TREE_FENCE = register("aerial_tree_fence", new FenceBlock(AERIAL_TREE_MATERIAL));
    public static final FenceBlock GOLDEN_BEECH_FENCE = register("golden_beech_fence", new FenceBlock(AERIAL_TREE_MATERIAL));
    public static final FenceBlock COPPER_PINE_FENCE = register("copper_pine_fence", new FenceBlock(COPPER_PINE_MATERIAL));
    public static final FenceBlock LAPIS_ROBINIA_FENCE = register("lapis_robinia_fence", new FenceBlock(COPPER_PINE_MATERIAL));
    public static final FenceBlock SHADOW_PINE_FENCE = register("shadow_pine_fence", new FenceBlock(SHADOW_PINE_MATERIAL));
    public static final FenceBlock STELLAR_JUNGLE_TREE_FENCE = register("stellar_jungle_tree_fence", new FenceBlock(COPPER_PINE_MATERIAL));
    public static final FenceBlock SKY_CACTUS_FIBER_FENCE = register("sky_cactus_fiber_fence", new FenceBlock(SKY_CACTUS_FIBER_MATERIAL));
    public static final FenceBlock GRAY_SHROOM_FENCE = register("gray_shroom_fence", new FenceBlock(SHROOM_MATERIAL));
    public static final PaneBlock RUBY_BARS = register("ruby_bars", new PaneBlock(METAL_NOTSOLID_MATERIAL));
    public static final WallBlock STELLAR_STONE_WALL = register("stellar_stone_wall", new WallBlock(AbstractBlock.Settings.copy(STELLAR_STONE)));
    public static final WallBlock STELLAR_COBBLESTONE_WALL = register("stellar_cobblestone_wall", new WallBlock(AbstractBlock.Settings.copy(STELLAR_COBBLESTONE)));
    public static final WallBlock STELLAR_STONE_BRICKS_WALL = register("stellar_stone_bricks_wall", new WallBlock(AbstractBlock.Settings.copy(STELLAR_STONE_BRICKS)));
    public static final WallBlock MOSSY_STELLAR_STONE_WALL = register("mossy_stellar_stone_wall", new WallBlock(AbstractBlock.Settings.copy(MOSSY_STELLAR_STONE)));
    public static final WallBlock MOSSY_STELLAR_COBBLESTONE_WALL = register("mossy_stellar_cobblestone_wall", new WallBlock(AbstractBlock.Settings.copy(MOSSY_STELLAR_COBBLESTONE)));
    public static final WallBlock SLIPPERY_SAND_STONE_WALL = register("slippery_sand_stone_wall", new WallBlock(AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE)));
    public static final WallBlock SLIPPERY_SAND_STONE_BRICKS_WALL = register("slippery_sand_stone_bricks_wall", new WallBlock(AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE_BRICKS)));
    public static final WallBlock CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL = register("cracked_slippery_sand_stone_bricks_wall", new WallBlock(AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE_BRICKS)));
    public static final WallBlock GLAUCOPHANITE_WALL = register("glaucophanite_wall", new WallBlock(AbstractBlock.Settings.copy(GLAUCOPHANITE)));
    public static final WallBlock POLISHED_GLAUCOPHANITE_WALL = register("polished_glaucophanite_wall", new WallBlock(AbstractBlock.Settings.copy(POLISHED_GLAUCOPHANITE)));
    public static final WallBlock MAGMATIC_GEL_WALL = register("magmatic_gel_wall", new WallBlock(AbstractBlock.Settings.copy(MAGMATIC_GEL_BLOCK)));

    //gates
    public static final FenceGateBlock AERIAL_TREE_GATE = register("aerial_tree_gate", new FenceGateBlock(AerialHellWoodTypes.AERIAL_TREE, AERIAL_TREE_MATERIAL));
    public static final FenceGateBlock GOLDEN_BEECH_GATE = register("golden_beech_gate", new FenceGateBlock(AerialHellWoodTypes.GOLDEN_BEECH, AERIAL_TREE_MATERIAL));
    public static final FenceGateBlock COPPER_PINE_GATE = register("copper_pine_gate", new FenceGateBlock(AerialHellWoodTypes.COPPER_PINE, COPPER_PINE_MATERIAL));
    public static final FenceGateBlock LAPIS_ROBINIA_GATE = register("lapis_robinia_gate", new FenceGateBlock(AerialHellWoodTypes.LAPIS_ROBINIA, COPPER_PINE_MATERIAL));
    public static final FenceGateBlock SHADOW_PINE_GATE = register("shadow_pine_gate", new FenceGateBlock(AerialHellWoodTypes.SHADOW_PINE, SHADOW_PINE_MATERIAL));
    public static final FenceGateBlock STELLAR_JUNGLE_TREE_GATE = register("stellar_jungle_tree_gate", new FenceGateBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, COPPER_PINE_MATERIAL));
    public static final FenceGateBlock SKY_CACTUS_FIBER_GATE = register("sky_cactus_fiber_gate", new FenceGateBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, SKY_CACTUS_FIBER_MATERIAL));
    public static final FenceGateBlock GRAY_SHROOM_GATE = register("gray_shroom_gate", new FenceGateBlock(AerialHellWoodTypes.GRAY_SHROOM, SHROOM_MATERIAL));

    //doors
    public static final DoorBlock AERIAL_TREE_DOOR = register("aerial_tree_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(AERIAL_TREE_PLANKS).nonOpaque()));
    public static final DoorBlock GOLDEN_BEECH_DOOR = register("golden_beech_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(GOLDEN_BEECH_PLANKS).nonOpaque()));
    public static final DoorBlock COPPER_PINE_DOOR = register("copper_pine_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(COPPER_PINE_PLANKS).nonOpaque()));
    public static final DoorBlock LAPIS_ROBINIA_DOOR = register("lapis_robinia_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(LAPIS_ROBINIA_PLANKS).nonOpaque()));
    public static final DoorBlock SHADOW_PINE_DOOR = register("shadow_pine_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(SHADOW_PINE_PLANKS).nonOpaque()));
    public static final DoorBlock STELLAR_JUNGLE_TREE_DOOR = register("stellar_jungle_tree_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_PLANKS).nonOpaque()));
    public static final DoorBlock SKY_CACTUS_FIBER_DOOR = register("sky_cactus_fiber_door", new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(SKY_CACTUS_FIBER_PLANKS).nonOpaque()));
    public static final DoorBlock GRAY_SHROOM_DOOR = register("gray_shroom_door", new DoorBlock(BlockSetType.CRIMSON, AbstractBlock.Settings.copy(GRAY_SHROOM_PLANKS).nonOpaque()));
    public static final DoorBlock RUBY_DOOR = register("ruby_door", new DoorBlock(BlockSetType.IRON, METAL_NOTSOLID_MATERIAL));

    //trapdoors
    public static final TrapdoorBlock AERIAL_TREE_TRAPDOOR = register("aerial_tree_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(AERIAL_TREE_PLANKS).nonOpaque()));
    public static final TrapdoorBlock GOLDEN_BEECH_TRAPDOOR = register("golden_beech_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(GOLDEN_BEECH_PLANKS).nonOpaque()));
    public static final TrapdoorBlock COPPER_PINE_TRAPDOOR = register("copper_pine_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(COPPER_PINE_PLANKS).nonOpaque()));
    public static final TrapdoorBlock LAPIS_ROBINIA_TRAPDOOR = register("lapis_robinia_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(LAPIS_ROBINIA_PLANKS).nonOpaque()));
    public static final TrapdoorBlock SHADOW_PINE_TRAPDOOR = register("shadow_pine_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(SHADOW_PINE_PLANKS).nonOpaque()));
    public static final TrapdoorBlock STELLAR_JUNGLE_TREE_TRAPDOOR = register("stellar_jungle_tree_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_PLANKS).nonOpaque()));
    public static final TrapdoorBlock SKY_CACTUS_FIBER_TRAPDOOR = register("sky_cactus_fiber_trapdoor", new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(SKY_CACTUS_FIBER_PLANKS).nonOpaque()));
    public static final TrapdoorBlock GRAY_SHROOM_TRAPDOOR = register("gray_shroom_trapdoor", new TrapdoorBlock(BlockSetType.CRIMSON, AbstractBlock.Settings.copy(GRAY_SHROOM_PLANKS).nonOpaque()));
    public static final TrapdoorBlock RUBY_TRAPDOOR = register("ruby_trapdoor", new TrapdoorBlock(BlockSetType.IRON, METAL_NOTSOLID_MATERIAL));

    //buttons
    public static final ButtonBlock STELLAR_STONE_BUTTON = register("stellar_stone_button", new ButtonBlock(BlockSetType.STONE, 20, AbstractBlock.Settings.copy(STELLAR_STONE)));
    public static final ButtonBlock STELLAR_COBBLESTONE_BUTTON = register("stellar_cobblestone_button", new ButtonBlock(BlockSetType.STONE, 20, AbstractBlock.Settings.copy(STELLAR_COBBLESTONE)));
    public static final ButtonBlock STELLAR_STONE_BRICKS_BUTTON = register("stellar_stone_bricks_button", new ButtonBlock(BlockSetType.STONE, 20, AbstractBlock.Settings.copy(STELLAR_STONE_BRICKS)));
    public static final ButtonBlock SLIPPERY_SAND_STONE_BUTTON = register("slippery_sand_stone_button", new ButtonBlock(BlockSetType.STONE, 30, AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE)));
    public static final ButtonBlock SLIPPERY_SAND_STONE_BRICKS_BUTTON = register("slippery_sand_stone_bricks_button", new ButtonBlock(BlockSetType.OAK, 30, AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE_BRICKS)));
    public static final ButtonBlock AERIAL_TREE_BUTTON = register("aerial_tree_button", new ButtonBlock(BlockSetType.OAK, 30, AERIAL_TREE_MATERIAL));
    public static final ButtonBlock GOLDEN_BEECH_BUTTON = register("golden_beech_button", new ButtonBlock(BlockSetType.OAK, 30, AERIAL_TREE_MATERIAL));
    public static final ButtonBlock COPPER_PINE_BUTTON = register("copper_pine_button", new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL));
    public static final ButtonBlock LAPIS_ROBINIA_BUTTON = register("lapis_robinia_button", new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL));
    public static final ButtonBlock SHADOW_PINE_BUTTON = register("shadow_pine_button", new ButtonBlock(BlockSetType.OAK, 30, SHADOW_PINE_MATERIAL));
    public static final ButtonBlock STELLAR_JUNGLE_TREE_BUTTON = register("stellar_jungle_tree_button", new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL));
    public static final ButtonBlock SKY_CACTUS_FIBER_BUTTON = register("sky_cactus_fiber_button", new ButtonBlock(BlockSetType.OAK, 30, SKY_CACTUS_FIBER_MATERIAL));
    public static final ButtonBlock GRAY_SHROOM_BUTTON = register("gray_shroom_button", new ButtonBlock(BlockSetType.CRIMSON, 30, SHROOM_MATERIAL));
    public static final ButtonBlock GLAUCOPHANITE_BUTTON = register("glaucophanite_button", new ButtonBlock(BlockSetType.STONE, 20, AbstractBlock.Settings.copy(GLAUCOPHANITE)));

    //pressure plates
    public static final PressurePlateBlock STELLAR_STONE_PRESSURE_PLATE = register("stellar_stone_pressure_plate", new PressurePlateBlock(BlockSetType.STONE, AbstractBlock.Settings.copy(STELLAR_STONE)));
    public static final PressurePlateBlock STELLAR_COBBLESTONE_PRESSURE_PLATE = register("stellar_cobblestone_pressure_plate", new PressurePlateBlock(BlockSetType.STONE, AbstractBlock.Settings.copy(STELLAR_COBBLESTONE)));
    public static final PressurePlateBlock STELLAR_STONE_BRICKS_PRESSURE_PLATE = register("stellar_stone_bricks_pressure_plate", new PressurePlateBlock(BlockSetType.STONE, AbstractBlock.Settings.copy(STELLAR_STONE_BRICKS)));
    public static final PressurePlateBlock SLIPPERY_SAND_STONE_PRESSURE_PLATE = register("slippery_sand_stone_pressure_plate", new PressurePlateBlock(BlockSetType.STONE, AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE)));
    public static final PressurePlateBlock SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE = register("slippery_sand_stone_bricks_pressure_plate", new PressurePlateBlock(BlockSetType.STONE, AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE_BRICKS)));
    public static final PressurePlateBlock AERIAL_TREE_PRESSURE_PLATE = register("aerial_tree_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, AERIAL_TREE_MATERIAL));
    public static final PressurePlateBlock GOLDEN_BEECH_PRESSURE_PLATE = register("golden_beech_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, AERIAL_TREE_MATERIAL));
    public static final PressurePlateBlock COPPER_PINE_PRESSURE_PLATE = register("copper_pine_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL));
    public static final PressurePlateBlock LAPIS_ROBINIA_PRESSURE_PLATE = register("lapis_robinia_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL));
    public static final PressurePlateBlock SHADOW_PINE_PRESSURE_PLATE = register("shadow_pine_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, SHADOW_PINE_MATERIAL));
    public static final PressurePlateBlock STELLAR_JUNGLE_TREE_PRESSURE_PLATE = register("stellar_jungle_tree_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL));
    public static final PressurePlateBlock SKY_CACTUS_FIBER_PRESSURE_PLATE = register("sky_cactus_fiber_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, SKY_CACTUS_FIBER_MATERIAL));
    public static final PressurePlateBlock GRAY_SHROOM_PRESSURE_PLATE = register("gray_shroom_pressure_plate", new PressurePlateBlock(BlockSetType.CRIMSON, SHROOM_MATERIAL));
    public static final PressurePlateBlock GLAUCOPHANITE_PRESSURE_PLATE = register("glaucophanite_pressure_plate", new PressurePlateBlock(BlockSetType.STONE, AbstractBlock.Settings.copy(GLAUCOPHANITE)));

    //slabs
    public static final SlabBlock AERIAL_TREE_SLAB = register("aerial_tree_slab", new SlabBlock(AERIAL_TREE_MATERIAL));
    public static final SlabBlock GOLDEN_BEECH_SLAB = register("golden_beech_slab", new SlabBlock(AERIAL_TREE_MATERIAL));
    public static final SlabBlock COPPER_PINE_SLAB = register("copper_pine_slab", new SlabBlock(COPPER_PINE_MATERIAL));
    public static final SlabBlock LAPIS_ROBINIA_SLAB = register("lapis_robinia_slab", new SlabBlock(COPPER_PINE_MATERIAL));
    public static final SlabBlock SHADOW_PINE_SLAB = register("shadow_pine_slab", new SlabBlock(SHADOW_PINE_MATERIAL));
    public static final SlabBlock STELLAR_JUNGLE_TREE_SLAB = register("stellar_jungle_tree_slab", new SlabBlock(COPPER_PINE_MATERIAL));
    public static final SlabBlock SKY_CACTUS_FIBER_SLAB = register("sky_cactus_fiber_slab", new SlabBlock(SKY_CACTUS_FIBER_MATERIAL));
    public static final SlabBlock GRAY_SHROOM_SLAB = register("gray_shroom_slab", new SlabBlock(SHROOM_MATERIAL));
    public static final SlabBlock STELLAR_STONE_SLAB = register("stellar_stone_slab", new SlabBlock(AbstractBlock.Settings.copy(STELLAR_STONE)));
    public static final SlabBlock STELLAR_COBBLESTONE_SLAB = register("stellar_cobblestone_slab", new SlabBlock(AbstractBlock.Settings.copy(STELLAR_COBBLESTONE)));
    public static final SlabBlock STELLAR_STONE_BRICKS_SLAB = register("stellar_stone_bricks_slab", new SlabBlock(AbstractBlock.Settings.copy(STELLAR_STONE_BRICKS)));
    public static final SlabBlock MOSSY_STELLAR_STONE_SLAB = register("mossy_stellar_stone_slab", new SlabBlock(AbstractBlock.Settings.copy(MOSSY_STELLAR_STONE)));
    public static final SlabBlock MOSSY_STELLAR_COBBLESTONE_SLAB = register("mossy_stellar_cobblestone_slab", new SlabBlock(AbstractBlock.Settings.copy(MOSSY_STELLAR_COBBLESTONE)));
    public static final SlabBlock SLIPPERY_SAND_STONE_SLAB = register("slippery_sand_stone_slab", new SlabBlock(AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE)));
    public static final SlabBlock SLIPPERY_SAND_STONE_BRICKS_SLAB = register("slippery_sand_stone_bricks_slab", new SlabBlock(AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE_BRICKS)));
    public static final SlabBlock CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB = register("cracked_slippery_sand_stone_bricks_slab", new SlabBlock(AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE_BRICKS)));
    public static final SlabBlock POLISHED_GLAUCOPHANITE_SLAB = register("polished_glaucophanite_slab", new SlabBlock(AbstractBlock.Settings.copy(POLISHED_GLAUCOPHANITE)));
    public static final SlabBlock MAGMATIC_GEL_SLAB = register("magmatic_gel_slab", new SlabBlock(AbstractBlock.Settings.copy(MAGMATIC_GEL_BLOCK)));

    //stairs
    public static final StairsBlock AERIAL_TREE_STAIRS = register("aerial_tree_stairs", new StairsBlock(AERIAL_TREE_PLANKS.getDefaultState(), AERIAL_TREE_MATERIAL));
    public static final StairsBlock GOLDEN_BEECH_STAIRS = register("golden_beech_stairs", new StairsBlock(GOLDEN_BEECH_PLANKS.getDefaultState(), AERIAL_TREE_MATERIAL));
    public static final StairsBlock COPPER_PINE_STAIRS = register("copper_pine_stairs", new StairsBlock(COPPER_PINE_PLANKS.getDefaultState(), COPPER_PINE_MATERIAL));
    public static final StairsBlock LAPIS_ROBINIA_STAIRS = register("lapis_robinia_stairs", new StairsBlock(LAPIS_ROBINIA_PLANKS.getDefaultState(), COPPER_PINE_MATERIAL));
    public static final StairsBlock SHADOW_PINE_STAIRS = register("shadow_pine_stairs", new StairsBlock(SHADOW_PINE_PLANKS.getDefaultState(), SHADOW_PINE_MATERIAL));
    public static final StairsBlock STELLAR_JUNGLE_TREE_STAIRS = register("stellar_jungle_tree_stairs", new StairsBlock(STELLAR_JUNGLE_TREE_PLANKS.getDefaultState(), COPPER_PINE_MATERIAL));
    public static final StairsBlock SKY_CACTUS_FIBER_STAIRS = register("sky_cactus_fiber_stairs", new StairsBlock(SKY_CACTUS_FIBER_PLANKS.getDefaultState(), SKY_CACTUS_FIBER_MATERIAL));
    public static final StairsBlock GRAY_SHROOM_STAIRS = register("gray_shroom_stairs", new StairsBlock(GRAY_SHROOM_PLANKS.getDefaultState(), SHROOM_MATERIAL));
    public static final StairsBlock STELLAR_STONE_STAIRS = register("stellar_stone_stairs", new StairsBlock(STELLAR_STONE.getDefaultState(), AbstractBlock.Settings.copy(STELLAR_STONE)));
    public static final StairsBlock STELLAR_COBBLESTONE_STAIRS = register("stellar_cobblestone_stairs", new StairsBlock(STELLAR_COBBLESTONE.getDefaultState(), AbstractBlock.Settings.copy(STELLAR_COBBLESTONE)));
    public static final StairsBlock STELLAR_STONE_BRICKS_STAIRS = register("stellar_stone_bricks_stairs", new StairsBlock(STELLAR_STONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(STELLAR_STONE_BRICKS)));
    public static final StairsBlock MOSSY_STELLAR_STONE_STAIRS = register("mossy_stellar_stone_stairs", new StairsBlock(MOSSY_STELLAR_STONE.getDefaultState(), AbstractBlock.Settings.copy(MOSSY_STELLAR_STONE)));
    public static final StairsBlock MOSSY_STELLAR_COBBLESTONE_STAIRS = register("mossy_stellar_cobblestone_stairs", new StairsBlock(MOSSY_STELLAR_COBBLESTONE.getDefaultState(), AbstractBlock.Settings.copy(MOSSY_STELLAR_COBBLESTONE)));
    public static final StairsBlock SLIPPERY_SAND_STONE_STAIRS = register("slippery_sand_stone_stairs", new StairsBlock(SLIPPERY_SAND_STONE.getDefaultState(), AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE)));
    public static final StairsBlock SLIPPERY_SAND_STONE_BRICKS_STAIRS = register("slippery_sand_stone_bricks_stairs", new StairsBlock(SLIPPERY_SAND_STONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE_BRICKS)));
    public static final StairsBlock CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS = register("cracked_slippery_sand_stone_bricks_stairs", new StairsBlock(SLIPPERY_SAND_STONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE_BRICKS)));
    public static final StairsBlock POLISHED_GLAUCOPHANITE_STAIRS = register("polished_glaucophanite_stairs", new StairsBlock(POLISHED_GLAUCOPHANITE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_GLAUCOPHANITE)));
    public static final StairsBlock MAGMATIC_GEL_STAIRS = register("magmatic_gel_stairs", new StairsBlock(MAGMATIC_GEL_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(MAGMATIC_GEL_BLOCK)));

    //signs
    public static final AerialHellStandingSignBlock AERIAL_TREE_STANDING_SIGN = register("aerial_tree_sign", new AerialHellStandingSignBlock(AERIAL_TREE_SIGN_MATERIAL, AerialHellWoodTypes.AERIAL_TREE));
    public static final AerialHellWallSignBlock AERIAL_TREE_WALL_SIGN = register("aerial_tree_wall_sign", new AerialHellWallSignBlock(AERIAL_TREE_SIGN_MATERIAL, AerialHellWoodTypes.AERIAL_TREE));
    public static final AerialHellStandingSignBlock GOLDEN_BEECH_STANDING_SIGN = register("golden_beech_sign", new AerialHellStandingSignBlock(AERIAL_TREE_SIGN_MATERIAL, AerialHellWoodTypes.GOLDEN_BEECH));
    public static final AerialHellWallSignBlock GOLDEN_BEECH_WALL_SIGN = register("golden_beech_wall_sign", new AerialHellWallSignBlock(AERIAL_TREE_SIGN_MATERIAL, AerialHellWoodTypes.GOLDEN_BEECH));
    public static final AerialHellStandingSignBlock COPPER_PINE_STANDING_SIGN = register("copper_pine_sign", new AerialHellStandingSignBlock(COPPER_PINE_SIGN_MATERIAL, AerialHellWoodTypes.COPPER_PINE));
    public static final AerialHellWallSignBlock COPPER_PINE_WALL_SIGN = register("copper_pine_wall_sign", new AerialHellWallSignBlock(COPPER_PINE_SIGN_MATERIAL, AerialHellWoodTypes.COPPER_PINE));
    public static final AerialHellStandingSignBlock LAPIS_ROBINIA_STANDING_SIGN = register("lapis_robinia_sign", new AerialHellStandingSignBlock(COPPER_PINE_SIGN_MATERIAL, AerialHellWoodTypes.LAPIS_ROBINIA));
    public static final AerialHellWallSignBlock LAPIS_ROBINIA_WALL_SIGN = register("lapis_robinia_wall_sign", new AerialHellWallSignBlock(COPPER_PINE_SIGN_MATERIAL, AerialHellWoodTypes.LAPIS_ROBINIA));
    public static final AerialHellStandingSignBlock SHADOW_PINE_STANDING_SIGN = register("shadow_pine_sign", new AerialHellStandingSignBlock(SHADOW_PINE_SIGN_MATERIAL, AerialHellWoodTypes.SHADOW_PINE));
    public static final AerialHellWallSignBlock SHADOW_PINE_WALL_SIGN = register("shadow_pine_wall_sign", new AerialHellWallSignBlock(SHADOW_PINE_SIGN_MATERIAL, AerialHellWoodTypes.SHADOW_PINE));
    public static final AerialHellStandingSignBlock STELLAR_JUNGLE_TREE_STANDING_SIGN = register("stellar_jungle_tree_sign", new AerialHellStandingSignBlock(COPPER_PINE_SIGN_MATERIAL, AerialHellWoodTypes.STELLAR_JUNGLE_TREE));
    public static final AerialHellWallSignBlock STELLAR_JUNGLE_TREE_WALL_SIGN = register("stellar_jungle_tree_wall_sign", new AerialHellWallSignBlock(COPPER_PINE_SIGN_MATERIAL, AerialHellWoodTypes.STELLAR_JUNGLE_TREE));
    public static final AerialHellStandingSignBlock SKY_CACTUS_FIBER_STANDING_SIGN = register("sky_cactus_fiber_sign", new AerialHellStandingSignBlock(SKY_CACTUS_FIBER_SIGN_MATERIAL, AerialHellWoodTypes.SKY_CACTUS_FIBER));
    public static final AerialHellWallSignBlock SKY_CACTUS_FIBER_WALL_SIGN = register("sky_cactus_fiber_wall_sign", new AerialHellWallSignBlock(SKY_CACTUS_FIBER_SIGN_MATERIAL, AerialHellWoodTypes.SKY_CACTUS_FIBER));
    public static final AerialHellStandingSignBlock GRAY_SHROOM_STANDING_SIGN = register("gray_shroom_sign", new AerialHellStandingSignBlock(SHROOM_SIGN_MATERIAL, AerialHellWoodTypes.GRAY_SHROOM));
    public static final AerialHellWallSignBlock GRAY_SHROOM_WALL_SIGN = register("gray_shroom_wall_sign", new AerialHellWallSignBlock(SHROOM_SIGN_MATERIAL, AerialHellWoodTypes.GRAY_SHROOM));

    //hanging signs
    public static final HangingSignBlock AERIAL_TREE_HANGING_SIGN = register("aerial_tree_hanging_sign", new AerialHellHangingSignBlock(AerialHellWoodTypes.AERIAL_TREE, AERIAL_TREE_SIGN_MATERIAL));
    public static final WallHangingSignBlock AERIAL_TREE_WALL_HANGING_SIGN = register("aerial_tree_wall_hanging_sign", new AerialHellWallHangingSignBlock(AerialHellWoodTypes.AERIAL_TREE, AbstractBlock.Settings.copy(AERIAL_TREE_HANGING_SIGN).dropsLike(AERIAL_TREE_HANGING_SIGN)));
    public static final HangingSignBlock GOLDEN_BEECH_HANGING_SIGN = register("golden_beech_hanging_sign", new AerialHellHangingSignBlock(AerialHellWoodTypes.GOLDEN_BEECH, AERIAL_TREE_SIGN_MATERIAL));
    public static final WallHangingSignBlock GOLDEN_BEECH_WALL_HANGING_SIGN = register("golden_beech_wall_hanging_sign", new AerialHellWallHangingSignBlock(AerialHellWoodTypes.GOLDEN_BEECH, AbstractBlock.Settings.copy(GOLDEN_BEECH_HANGING_SIGN).dropsLike(GOLDEN_BEECH_HANGING_SIGN)));
    public static final HangingSignBlock COPPER_PINE_HANGING_SIGN = register("copper_pine_hanging_sign", new AerialHellHangingSignBlock(AerialHellWoodTypes.COPPER_PINE, COPPER_PINE_SIGN_MATERIAL));
    public static final WallHangingSignBlock COPPER_PINE_WALL_HANGING_SIGN = register("copper_pine_wall_hanging_sign", new AerialHellWallHangingSignBlock(AerialHellWoodTypes.COPPER_PINE, AbstractBlock.Settings.copy(COPPER_PINE_HANGING_SIGN).dropsLike(COPPER_PINE_HANGING_SIGN)));
    public static final HangingSignBlock LAPIS_ROBINIA_HANGING_SIGN = register("lapis_robinia_hanging_sign", new AerialHellHangingSignBlock(AerialHellWoodTypes.LAPIS_ROBINIA, COPPER_PINE_SIGN_MATERIAL));
    public static final WallHangingSignBlock LAPIS_ROBINIA_WALL_HANGING_SIGN = register("lapis_robinia_wall_hanging_sign", new AerialHellWallHangingSignBlock(AerialHellWoodTypes.LAPIS_ROBINIA, AbstractBlock.Settings.copy(LAPIS_ROBINIA_HANGING_SIGN).dropsLike(LAPIS_ROBINIA_HANGING_SIGN)));
    public static final HangingSignBlock SHADOW_PINE_HANGING_SIGN = register("shadow_pine_hanging_sign", new AerialHellHangingSignBlock(AerialHellWoodTypes.SHADOW_PINE, SHADOW_PINE_SIGN_MATERIAL));
    public static final WallHangingSignBlock SHADOW_PINE_WALL_HANGING_SIGN = register("shadow_pine_wall_hanging_sign", new AerialHellWallHangingSignBlock(AerialHellWoodTypes.SHADOW_PINE, AbstractBlock.Settings.copy(SHADOW_PINE_HANGING_SIGN).dropsLike(SHADOW_PINE_HANGING_SIGN)));
    public static final HangingSignBlock STELLAR_JUNGLE_TREE_HANGING_SIGN = register("stellar_jungle_tree_hanging_sign", new AerialHellHangingSignBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, SHADOW_PINE_SIGN_MATERIAL));
    public static final WallHangingSignBlock STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN = register("stellar_jungle_tree_wall_hanging_sign", new AerialHellWallHangingSignBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_HANGING_SIGN).dropsLike(STELLAR_JUNGLE_TREE_HANGING_SIGN)));
    public static final HangingSignBlock SKY_CACTUS_FIBER_HANGING_SIGN = register("sky_cactus_fiber_hanging_sign", new AerialHellHangingSignBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, SKY_CACTUS_FIBER_SIGN_MATERIAL));
    public static final WallHangingSignBlock SKY_CACTUS_FIBER_WALL_HANGING_SIGN = register("sky_cactus_fiber_wall_hanging_sign", new AerialHellWallHangingSignBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, AbstractBlock.Settings.copy(SKY_CACTUS_FIBER_HANGING_SIGN).dropsLike(SKY_CACTUS_FIBER_HANGING_SIGN)));
    public static final HangingSignBlock GRAY_SHROOM_HANGING_SIGN = register("gray_shroom_hanging_sign", new AerialHellHangingSignBlock(AerialHellWoodTypes.GRAY_SHROOM, SKY_CACTUS_FIBER_SIGN_MATERIAL));
    public static final WallHangingSignBlock GRAY_SHROOM_WALL_HANGING_SIGN = register("gray_shroom_wall_hanging_sign", new AerialHellWallHangingSignBlock(AerialHellWoodTypes.GRAY_SHROOM, AbstractBlock.Settings.copy(GRAY_SHROOM_HANGING_SIGN).dropsLike(GRAY_SHROOM_HANGING_SIGN)));

    //crafting tables
    public static final CraftingTableBlock AERIAL_TREE_CRAFTING_TABLE = register("aerial_tree_crafting_table", new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL));
    public static final CraftingTableBlock GOLDEN_BEECH_CRAFTING_TABLE = register("golden_beech_crafting_table", new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL));
    public static final CraftingTableBlock COPPER_PINE_CRAFTING_TABLE = register("copper_pine_crafting_table", new AerialHellCraftingTableBlock(COPPER_PINE_MATERIAL));
    public static final CraftingTableBlock LAPIS_ROBINIA_CRAFTING_TABLE = register("lapis_robinia_crafting_table", new AerialHellCraftingTableBlock(COPPER_PINE_MATERIAL));
    public static final CraftingTableBlock SHADOW_PINE_CRAFTING_TABLE = register("shadow_pine_crafting_table", new AerialHellCraftingTableBlock(SHADOW_PINE_MATERIAL));
    public static final CraftingTableBlock STELLAR_JUNGLE_TREE_CRAFTING_TABLE = register("stellar_jungle_tree_crafting_table", new AerialHellCraftingTableBlock(COPPER_PINE_MATERIAL));
    public static final CraftingTableBlock SKY_CACTUS_FIBER_CRAFTING_TABLE = register("sky_cactus_fiber_crafting_table", new AerialHellCraftingTableBlock(SKY_CACTUS_FIBER_MATERIAL));
    public static final CraftingTableBlock GRAY_SHROOM_CRAFTING_TABLE = register("gray_shroom_crafting_table", new AerialHellCraftingTableBlock(SHROOM_MATERIAL));

    //barrels
    public static final AerialHellBarrelBlock AERIAL_TREE_BARREL = register("aerial_tree_barrel", new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL));
    public static final AerialHellBarrelBlock GOLDEN_BEECH_BARREL = register("golden_beech_barrel", new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL));
    public static final AerialHellBarrelBlock COPPER_PINE_BARREL = register("copper_pine_barrel", new AerialHellBarrelBlock(COPPER_PINE_MATERIAL));
    public static final AerialHellBarrelBlock LAPIS_ROBINIA_BARREL = register("lapis_robinia_barrel", new AerialHellBarrelBlock(COPPER_PINE_MATERIAL));
    public static final AerialHellBarrelBlock SHADOW_PINE_BARREL = register("shadow_pine_barrel", new AerialHellBarrelBlock(SHADOW_PINE_MATERIAL));
    public static final AerialHellBarrelBlock STELLAR_JUNGLE_TREE_BARREL = register("stellar_jungle_tree_barrel", new AerialHellBarrelBlock(COPPER_PINE_MATERIAL));
    public static final AerialHellBarrelBlock SKY_CACTUS_FIBER_BARREL = register("sky_cactus_fiber_barrel", new AerialHellBarrelBlock(SKY_CACTUS_FIBER_MATERIAL));
    public static final AerialHellBarrelBlock GRAY_SHROOM_BARREL = register("gray_shroom_barrel", new AerialHellBarrelBlock(SHROOM_MATERIAL));

    //composters
    public static final ComposterBlock AERIAL_TREE_COMPOSTER = register("aerial_tree_composter", new ComposterBlock(AERIAL_TREE_MATERIAL));
    public static final ComposterBlock GOLDEN_BEECH_COMPOSTER = register("golden_beech_composter", new ComposterBlock(AERIAL_TREE_MATERIAL));
    public static final ComposterBlock COPPER_PINE_COMPOSTER = register("copper_pine_composter", new ComposterBlock(COPPER_PINE_MATERIAL));
    public static final ComposterBlock LAPIS_ROBINIA_COMPOSTER = register("lapis_robinia_composter", new ComposterBlock(COPPER_PINE_MATERIAL));
    public static final ComposterBlock SHADOW_PINE_COMPOSTER = register("shadow_pine_composter", new ComposterBlock(SHADOW_PINE_MATERIAL));
    public static final ComposterBlock STELLAR_JUNGLE_TREE_COMPOSTER = register("stellar_jungle_tree_composter", new ComposterBlock(COPPER_PINE_MATERIAL));
    public static final ComposterBlock SKY_CACTUS_FIBER_COMPOSTER = register("sky_cactus_fiber_composter", new ComposterBlock(SKY_CACTUS_FIBER_MATERIAL));
    public static final ComposterBlock GRAY_SHROOM_COMPOSTER = register("gray_shroom_composter", new ComposterBlock(SHROOM_MATERIAL));

    //decorative
    public static final PillarBlock AERIAL_TREE_VINE_ROPE_SPOOL = register("aerial_tree_vine_rope_spool", new VineRopeSpoolBlock(AbstractBlock.Settings.create().nonOpaque().blockVision((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.BROWN).strength(1.2F).sounds(BlockSoundGroup.WOOD)));
    public static final PillarBlock GOLDEN_BEECH_VINE_ROPE_SPOOL = register("golden_beech_vine_rope_spool", new VineRopeSpoolBlock(AbstractBlock.Settings.create().nonOpaque().blockVision((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.BROWN).strength(1.2F).sounds(BlockSoundGroup.WOOD)));
    public static final PillarBlock COPPER_PINE_VINE_ROPE_SPOOL = register("copper_pine_vine_rope_spool", new VineRopeSpoolBlock(AbstractBlock.Settings.create().nonOpaque().blockVision((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.BROWN).strength(1.2F).sounds(BlockSoundGroup.WOOD)));
    public static final PillarBlock LAPIS_ROBINIA_VINE_ROPE_SPOOL = register("lapis_robinia_vine_rope_spool", new VineRopeSpoolBlock(AbstractBlock.Settings.create().nonOpaque().blockVision((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.BROWN).strength(1.2F).sounds(BlockSoundGroup.WOOD)));
    public static final PillarBlock SHADOW_PINE_VINE_ROPE_SPOOL = register("shadow_pine_vine_rope_spool", new VineRopeSpoolBlock(AbstractBlock.Settings.create().nonOpaque().blockVision((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.BROWN).strength(1.2F).sounds(BlockSoundGroup.WOOD)));
    public static final PillarBlock STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL = register("stellar_jungle_tree_vine_rope_spool", new VineRopeSpoolBlock(AbstractBlock.Settings.create().nonOpaque().blockVision((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.BROWN).strength(1.2F).sounds(BlockSoundGroup.WOOD)));
    public static final PillarBlock SKY_CACTUS_FIBER_VINE_ROPE_SPOOL = register("sky_cactus_fiber_vine_rope_spool", new VineRopeSpoolBlock(AbstractBlock.Settings.create().nonOpaque().blockVision((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.BROWN).strength(1.2F).sounds(BlockSoundGroup.WOOD)));
    public static final PillarBlock GRAY_SHROOM_VINE_ROPE_SPOOL = register("gray_shroom_vine_rope_spool", new VineRopeSpoolBlock(AbstractBlock.Settings.create().nonOpaque().blockVision((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.BROWN).strength(1.2F).sounds(BlockSoundGroup.WOOD)));

    //fluids
    public static final FluidBlock LIQUID_OF_THE_GODS = register("liquid_of_the_gods", new AerialHellFluidBlock(AerialHellFluids.LIQUID_OF_THE_GODS_STILL, AbstractBlock.Settings.create().replaceable().luminance((state) -> 8)));

    public static <T extends Block> T register(String name, T block) {return Registry.register(Registries.BLOCK, AerialHell.id(name), block);}

    public static void load() {}
}
