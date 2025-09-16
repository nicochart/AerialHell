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
import fr.factionbedrock.aerialhell.Block.Plants.*;
import fr.factionbedrock.aerialhell.Block.Plants.Bushes.AerialBerryBushBlock;
import fr.factionbedrock.aerialhell.Block.Plants.Bushes.VibrantAerialBerryBushBlock;
import fr.factionbedrock.aerialhell.Block.Plants.Vines.*;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.*;
import fr.factionbedrock.aerialhell.Block.StandingAndWall.*;
import fr.factionbedrock.aerialhell.Block.Trophies.BottomSlabLikeTrophyBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellTreeGrowers;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AxeItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
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
    public static final AerialHellPortalBlock AERIAL_HELL_PORTAL = register(Keys.AERIAL_HELL_PORTAL.getValue().getPath(), new AerialHellPortalBlock(AbstractBlock.Settings.copy(Blocks.NETHER_PORTAL).registryKey(Keys.AERIAL_HELL_PORTAL).nonOpaque().blockVision((state, blockview, pos) -> false).luminance((state) -> 10).mapColor(DyeColor.GREEN)));
    public static final Block STELLAR_PORTAL_FRAME_BLOCK = register(Keys.STELLAR_PORTAL_FRAME_BLOCK.getValue().getPath(), new Block(AbstractBlock.Settings.create().registryKey(Keys.STELLAR_PORTAL_FRAME_BLOCK).mapColor(MapColor.STONE_GRAY).requiresTool().strength(25.0F, 600.0F)));
    public static final Block STELLAR_PORTAL_FRAME_ORE = register(Keys.STELLAR_PORTAL_FRAME_ORE.getValue().getPath(), new AerialHellOreBlock(0, 0, AbstractBlock.Settings.create().registryKey(Keys.STELLAR_PORTAL_FRAME_ORE).strength(25.0F, 600.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block DEEPSLATE_STELLAR_PORTAL_FRAME_ORE = register(Keys.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE.getValue().getPath(), new AerialHellOreBlock(0, 0, AbstractBlock.Settings.create().registryKey(Keys.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE).strength(30.0F, 600.0F).sounds(BlockSoundGroup.STONE).requiresTool()));

    //torch
    public static final Block FLUORITE_TORCH = register(Keys.FLUORITE_TORCH.getValue().getPath(), new AerialHellTorchBlock(AbstractBlock.Settings.copy(Blocks.TORCH).registryKey(Keys.FLUORITE_TORCH)));
    public static final Block FLUORITE_WALL_TORCH = register(Keys.FLUORITE_WALL_TORCH.getValue().getPath(), new AerialHellWallTorchBlock(AbstractBlock.Settings.copy(Blocks.WALL_TORCH).registryKey(Keys.FLUORITE_WALL_TORCH).lootTable(FLUORITE_TORCH.getLootTableKey())));
    public static final Block VOLUCITE_TORCH = register(Keys.VOLUCITE_TORCH.getValue().getPath(), new AerialHellTorchBlock(AbstractBlock.Settings.copy(Blocks.TORCH).registryKey(Keys.VOLUCITE_TORCH).luminance((state) -> {return 9;})));
    public static final Block VOLUCITE_WALL_TORCH = register(Keys.VOLUCITE_WALL_TORCH.getValue().getPath(), new AerialHellWallTorchBlock(AbstractBlock.Settings.copy(Blocks.WALL_TORCH).registryKey(Keys.VOLUCITE_WALL_TORCH).lootTable(VOLUCITE_TORCH.getLootTableKey()).luminance((state) -> {return 9;})));
    public static final Block SHADOW_TORCH = register(Keys.SHADOW_TORCH.getValue().getPath(), new AerialHellTorchBlock(AbstractBlock.Settings.copy(Blocks.TORCH).registryKey(Keys.SHADOW_TORCH).luminance((state) -> {return 9;})));
    public static final Block SHADOW_WALL_TORCH = register(Keys.SHADOW_WALL_TORCH.getValue().getPath(), new AerialHellWallTorchBlock(AbstractBlock.Settings.copy(Blocks.WALL_TORCH).registryKey(Keys.SHADOW_WALL_TORCH).lootTable(SHADOW_TORCH.getLootTableKey()).luminance((state) -> {return 9;})));

    //lanterns
    public static final Block FLUORITE_LANTERN = register(Keys.FLUORITE_LANTERN.getValue().getPath(), new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN).registryKey(Keys.FLUORITE_LANTERN)));
    public static final Block RUBY_LANTERN = register(Keys.RUBY_LANTERN.getValue().getPath(), new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN).registryKey(Keys.RUBY_LANTERN)));
    public static final Block RUBY_FLUORITE_LANTERN = register(Keys.RUBY_FLUORITE_LANTERN.getValue().getPath(), new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN).registryKey(Keys.RUBY_FLUORITE_LANTERN)));
    public static final Block VOLUCITE_LANTERN = register(Keys.VOLUCITE_LANTERN.getValue().getPath(), new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN).registryKey(Keys.VOLUCITE_LANTERN)));
    public static final Block VOLUCITE_FLUORITE_LANTERN = register(Keys.VOLUCITE_FLUORITE_LANTERN.getValue().getPath(), new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN).registryKey(Keys.VOLUCITE_FLUORITE_LANTERN)));
    public static final Block LUNATIC_LANTERN = register(Keys.LUNATIC_LANTERN.getValue().getPath(), new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN).registryKey(Keys.LUNATIC_LANTERN)));
    public static final Block SHADOW_LANTERN = register(Keys.SHADOW_LANTERN.getValue().getPath(), new LanternBlock(AbstractBlock.Settings.copy(Blocks.SOUL_LANTERN).registryKey(Keys.SHADOW_LANTERN)));

    //chains
    public static final ChainBlock RUBY_CHAIN = register(Keys.RUBY_CHAIN.getValue().getPath(), new ChainBlock(AbstractBlock.Settings.copy(Blocks.CHAIN).registryKey(Keys.RUBY_CHAIN)));
    public static final ChainBlock VOLUCITE_CHAIN = register(Keys.VOLUCITE_CHAIN.getValue().getPath(), new ChainBlock(AbstractBlock.Settings.copy(Blocks.CHAIN).registryKey(Keys.VOLUCITE_CHAIN)));
    public static final ChainBlock LUNATIC_CHAIN = register(Keys.LUNATIC_CHAIN.getValue().getPath(), new ChainBlock(AbstractBlock.Settings.copy(Blocks.CHAIN).registryKey(Keys.LUNATIC_CHAIN)));
    public static final ChainBlock SHADOW_CHAIN = register(Keys.SHADOW_CHAIN.getValue().getPath(), new ShadowChainBlock(AbstractBlock.Settings.copy(Blocks.CHAIN).registryKey(Keys.SHADOW_CHAIN)));

    //grass & dirt
    public static final StellarGrassBlock STELLAR_GRASS_BLOCK = register(Keys.STELLAR_GRASS_BLOCK.getValue().getPath(), new StellarGrassBlock(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK).registryKey(Keys.STELLAR_GRASS_BLOCK)));
    public static final Block CHISELED_STELLAR_GRASS_BLOCK = register(Keys.CHISELED_STELLAR_GRASS_BLOCK.getValue().getPath(), new StellarGrassBlock(AbstractBlock.Settings.copy(STELLAR_GRASS_BLOCK).registryKey(Keys.CHISELED_STELLAR_GRASS_BLOCK)));
    public static final Block STELLAR_DIRT = register(Keys.STELLAR_DIRT.getValue().getPath(), new StellarDirtBlock(AbstractBlock.Settings.copy(Blocks.DIRT).registryKey(Keys.STELLAR_DIRT)));
    public static final Block STELLAR_COARSE_DIRT = register(Keys.STELLAR_COARSE_DIRT.getValue().getPath(), new StellarDirtBlock(AbstractBlock.Settings.copy(Blocks.COARSE_DIRT).registryKey(Keys.STELLAR_COARSE_DIRT)));
    public static final Block STELLAR_FARMLAND = register(Keys.STELLAR_FARMLAND.getValue().getPath(), new StellarFarmBlock(AbstractBlock.Settings.copy(Blocks.DIRT).registryKey(Keys.STELLAR_FARMLAND).ticksRandomly().strength(0.6F).sounds(BlockSoundGroup.GRAVEL).blockVision((state, blockgetter, pos) -> true).suffocates((state, blockgetter, pos) -> true)));
    public static final Block STELLAR_DIRT_PATH = register(Keys.STELLAR_DIRT_PATH.getValue().getPath(), new StellarDirtPathBlock(AbstractBlock.Settings.copy(Blocks.DIRT_PATH).registryKey(Keys.STELLAR_DIRT_PATH)));
    public static final Block STELLAR_PODZOL = register(Keys.STELLAR_PODZOL.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.PODZOL).registryKey(Keys.STELLAR_PODZOL)));
    public static final Block STELLAR_CRYSTAL_PODZOL = register(Keys.STELLAR_CRYSTAL_PODZOL.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.PODZOL).registryKey(Keys.STELLAR_CRYSTAL_PODZOL)));
    public static final Block CHISELED_STELLAR_DIRT = register(Keys.CHISELED_STELLAR_DIRT.getValue().getPath(), new StellarDirtBlock(AbstractBlock.Settings.copy(STELLAR_DIRT).registryKey(Keys.CHISELED_STELLAR_DIRT)));
    public static final ShadowGrassBlock SHADOW_GRASS_BLOCK = register(Keys.SHADOW_GRASS_BLOCK.getValue().getPath(), new ShadowGrassBlock(AbstractBlock.Settings.copy(Blocks.GRASS_BLOCK).registryKey(Keys.SHADOW_GRASS_BLOCK)));

    //slippery sand
    public static final Block SLIPPERY_SAND = register(Keys.SLIPPERY_SAND.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.SAND).registryKey(Keys.SLIPPERY_SAND).slipperiness(1.025F)));
    public static final Block SLIPPERY_SAND_STONE = register(Keys.SLIPPERY_SAND_STONE.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.SANDSTONE).registryKey(Keys.SLIPPERY_SAND_STONE).slipperiness(1.01F)));
    public static final Block SLIPPERY_SAND_STONE_BRICKS = register(Keys.SLIPPERY_SAND_STONE_BRICKS.getValue().getPath(), new Block(AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE).registryKey(Keys.SLIPPERY_SAND_STONE_BRICKS).slipperiness(1.005F)));
    public static final Block CUT_SLIPPERY_SAND_STONE = register(Keys.CUT_SLIPPERY_SAND_STONE.getValue().getPath(), new Block(AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE).registryKey(Keys.CUT_SLIPPERY_SAND_STONE).slipperiness(1.005F)));
    public static final Block CRACKED_SLIPPERY_SAND_STONE_BRICKS = register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS.getValue().getPath(), new Block(AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE).registryKey(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS).slipperiness(1.003F)));

    //giant root
    public static final PillarBlock GIANT_ROOT = register(Keys.GIANT_ROOT.getValue().getPath(), new PillarBlock(AERIAL_TREE_MATERIAL.registryKey(Keys.GIANT_ROOT)));

    //aerial_tree
    public static final ShiftableLogBlock AERIAL_TREE_LOG = register(Keys.AERIAL_TREE_LOG.getValue().getPath(), new ShiftableLogBlock(AERIAL_TREE_MATERIAL.registryKey(Keys.AERIAL_TREE_LOG), () -> AerialHellBlocks.SHADOW_AERIAL_TREE_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final PillarBlock STRIPPED_AERIAL_TREE_LOG = register(Keys.STRIPPED_AERIAL_TREE_LOG.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(AERIAL_TREE_LOG).registryKey(Keys.STRIPPED_AERIAL_TREE_LOG)));
    public static final PillarBlock AERIAL_TREE_WOOD = register(Keys.AERIAL_TREE_WOOD.getValue().getPath(), new PillarBlock(AERIAL_TREE_MATERIAL.registryKey(Keys.AERIAL_TREE_WOOD)));
    public static final PillarBlock STRIPPED_AERIAL_TREE_WOOD = register(Keys.STRIPPED_AERIAL_TREE_WOOD.getValue().getPath(), new PillarBlock(AERIAL_TREE_MATERIAL.registryKey(Keys.STRIPPED_AERIAL_TREE_WOOD)));
    public static final ShiftableLeavesBlock AERIAL_TREE_LEAVES = register(Keys.AERIAL_TREE_LEAVES.getValue().getPath(), new ShiftableLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).registryKey(Keys.AERIAL_TREE_LEAVES), () -> AerialHellBlocks.SHADOW_AERIAL_TREE_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block AERIAL_TREE_PLANKS = register(Keys.AERIAL_TREE_PLANKS.getValue().getPath(), new Block(AbstractBlock.Settings.copy(AERIAL_TREE_LOG).registryKey(Keys.AERIAL_TREE_PLANKS)));
    public static final Block CHISELED_AERIAL_TREE_PLANKS = register(Keys.CHISELED_AERIAL_TREE_PLANKS.getValue().getPath(), new Block(AbstractBlock.Settings.copy(AERIAL_TREE_PLANKS).registryKey(Keys.CHISELED_AERIAL_TREE_PLANKS)));
    public static final Block AERIAL_TREE_BOOKSHELF = register(Keys.AERIAL_TREE_BOOKSHELF.getValue().getPath(), new Block(AbstractBlock.Settings.copy(AERIAL_TREE_PLANKS).registryKey(Keys.AERIAL_TREE_BOOKSHELF)));
    public static final SaplingBlock AERIAL_TREE_SAPLING = register(Keys.AERIAL_TREE_SAPLING.getValue().getPath(), new AerialHellSaplingBlock(AerialHellTreeGrowers.AERIAL_TREE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).registryKey(Keys.AERIAL_TREE_SAPLING), AerialHellConfiguredFeatures.GIANT_AERIAL_TREE));

    //petrified aerial tree
    public static final Block PETRIFIED_AERIAL_TREE_LOG = register(Keys.PETRIFIED_AERIAL_TREE_LOG.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(AERIAL_TREE_LOG).registryKey(Keys.PETRIFIED_AERIAL_TREE_LOG)));

    //golden beech
    public static final ShiftableLogBlock GOLDEN_BEECH_LOG = register(Keys.GOLDEN_BEECH_LOG.getValue().getPath(), new ShiftableLogBlock(AbstractBlock.Settings.copy(AERIAL_TREE_LOG).registryKey(Keys.GOLDEN_BEECH_LOG), () -> AerialHellBlocks.SHADOW_GOLDEN_BEECH_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final Block STRIPPED_GOLDEN_BEECH_LOG = register(Keys.STRIPPED_GOLDEN_BEECH_LOG.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(GOLDEN_BEECH_LOG).registryKey(Keys.STRIPPED_GOLDEN_BEECH_LOG)));
    public static final Block GOLDEN_BEECH_WOOD = register(Keys.GOLDEN_BEECH_WOOD.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(GOLDEN_BEECH_LOG).registryKey(Keys.GOLDEN_BEECH_WOOD)));
    public static final Block STRIPPED_GOLDEN_BEECH_WOOD = register(Keys.STRIPPED_GOLDEN_BEECH_WOOD.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(GOLDEN_BEECH_LOG).registryKey(Keys.STRIPPED_GOLDEN_BEECH_WOOD)));
    public static final Block GOLDEN_BEECH_PLANKS = register(Keys.GOLDEN_BEECH_PLANKS.getValue().getPath(), new Block(AbstractBlock.Settings.copy(GOLDEN_BEECH_LOG).registryKey(Keys.GOLDEN_BEECH_PLANKS)));
    public static final Block CHISELED_GOLDEN_BEECH_PLANKS = register(Keys.CHISELED_GOLDEN_BEECH_PLANKS.getValue().getPath(), new Block(AbstractBlock.Settings.copy(GOLDEN_BEECH_PLANKS).registryKey(Keys.CHISELED_GOLDEN_BEECH_PLANKS)));
    public static final ShiftableLeavesBlock GOLDEN_BEECH_LEAVES = register(Keys.GOLDEN_BEECH_LEAVES.getValue().getPath(), new ShiftableLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).registryKey(Keys.GOLDEN_BEECH_LEAVES), () -> AerialHellBlocks.SHADOW_GOLDEN_BEECH_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block GOLDEN_BEECH_BOOKSHELF = register(Keys.GOLDEN_BEECH_BOOKSHELF.getValue().getPath(), new Block(AbstractBlock.Settings.copy(GOLDEN_BEECH_PLANKS).registryKey(Keys.GOLDEN_BEECH_BOOKSHELF)));
    public static final Block GOLDEN_BEECH_SAPLING = register(Keys.GOLDEN_BEECH_SAPLING.getValue().getPath(), new SaplingBlock(AerialHellTreeGrowers.GOLDEN_BEECH, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).registryKey(Keys.GOLDEN_BEECH_SAPLING)));

    //copper pine
    public static final ShiftableLogBlock COPPER_PINE_LOG = register(Keys.COPPER_PINE_LOG.getValue().getPath(), new ShiftableLogBlock(COPPER_PINE_MATERIAL.registryKey(Keys.COPPER_PINE_LOG), () -> AerialHellBlocks.SHADOW_COPPER_PINE_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final PillarBlock STRIPPED_COPPER_PINE_LOG = register(Keys.STRIPPED_COPPER_PINE_LOG.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(COPPER_PINE_LOG).registryKey(Keys.STRIPPED_COPPER_PINE_LOG)));
    public static final PillarBlock COPPER_PINE_WOOD = register(Keys.COPPER_PINE_WOOD.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(COPPER_PINE_LOG).registryKey(Keys.COPPER_PINE_WOOD)));
    public static final PillarBlock STRIPPED_COPPER_PINE_WOOD = register(Keys.STRIPPED_COPPER_PINE_WOOD.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(COPPER_PINE_LOG).registryKey(Keys.STRIPPED_COPPER_PINE_WOOD)));
    public static final Block COPPER_PINE_PLANKS = register(Keys.COPPER_PINE_PLANKS.getValue().getPath(), new Block(AbstractBlock.Settings.copy(COPPER_PINE_LOG).registryKey(Keys.COPPER_PINE_PLANKS)));
    public static final ShiftableLeavesBlock COPPER_PINE_LEAVES = register(Keys.COPPER_PINE_LEAVES.getValue().getPath(), new LeavesWithAmbientParticlesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).registryKey(Keys.COPPER_PINE_LEAVES), () -> AerialHellBlocks.SHADOW_COPPER_PINE_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block COPPER_PINE_BOOKSHELF = register(Keys.COPPER_PINE_BOOKSHELF.getValue().getPath(), new Block(AbstractBlock.Settings.copy(COPPER_PINE_PLANKS).registryKey(Keys.COPPER_PINE_BOOKSHELF)));
    public static final SaplingBlock COPPER_PINE_SAPLING = register(Keys.COPPER_PINE_SAPLING.getValue().getPath(), new AerialHellSaplingBlock(AerialHellTreeGrowers.COPPER_PINE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).registryKey(Keys.COPPER_PINE_SAPLING), AerialHellConfiguredFeatures.GIANT_COPPER_PINE, AerialHellConfiguredFeatures.HUGE_COPPER_PINE, 0.1F));

    //lapis robinia
    public static final ShiftableLogBlock LAPIS_ROBINIA_LOG = register(Keys.LAPIS_ROBINIA_LOG.getValue().getPath(), new ShiftableLogBlock(COPPER_PINE_MATERIAL.registryKey(Keys.LAPIS_ROBINIA_LOG), () -> AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final EffectLogBlock ENCHANTED_LAPIS_ROBINIA_LOG = register(Keys.ENCHANTED_LAPIS_ROBINIA_LOG.getValue().getPath(), new EffectLogBlock(COPPER_PINE_MATERIAL.registryKey(Keys.ENCHANTED_LAPIS_ROBINIA_LOG), () -> AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final PillarBlock STRIPPED_LAPIS_ROBINIA_LOG = register(Keys.STRIPPED_LAPIS_ROBINIA_LOG.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(LAPIS_ROBINIA_LOG).registryKey(Keys.STRIPPED_LAPIS_ROBINIA_LOG)));
    public static final PillarBlock LAPIS_ROBINIA_WOOD = register(Keys.LAPIS_ROBINIA_WOOD.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(LAPIS_ROBINIA_LOG).registryKey(Keys.LAPIS_ROBINIA_WOOD)));
    public static final PillarBlock STRIPPED_LAPIS_ROBINIA_WOOD = register(Keys.STRIPPED_LAPIS_ROBINIA_WOOD.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(LAPIS_ROBINIA_LOG).registryKey(Keys.STRIPPED_LAPIS_ROBINIA_WOOD)));
    public static final ShiftableLeavesBlock LAPIS_ROBINIA_LEAVES = register(Keys.LAPIS_ROBINIA_LEAVES.getValue().getPath(), new ShiftableLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).registryKey(Keys.LAPIS_ROBINIA_LEAVES), () -> AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block LAPIS_ROBINIA_PLANKS = register(Keys.LAPIS_ROBINIA_PLANKS.getValue().getPath(), new Block(AbstractBlock.Settings.copy(LAPIS_ROBINIA_LOG).registryKey(Keys.LAPIS_ROBINIA_PLANKS)));
    public static final Block LAPIS_ROBINIA_BOOKSHELF = register(Keys.LAPIS_ROBINIA_BOOKSHELF.getValue().getPath(), new Block(AbstractBlock.Settings.copy(LAPIS_ROBINIA_PLANKS).registryKey(Keys.LAPIS_ROBINIA_BOOKSHELF)));
    public static final SaplingBlock LAPIS_ROBINIA_SAPLING = register(Keys.LAPIS_ROBINIA_SAPLING.getValue().getPath(), new AerialHellSaplingBlock(AerialHellTreeGrowers.LAPIS_ROBINIA, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).registryKey(Keys.LAPIS_ROBINIA_SAPLING), AerialHellConfiguredFeatures.GIANT_LAPIS_ROBINIA));

    //shadow_pine
    public static final ShiftableLogBlock SHADOW_PINE_LOG = register(Keys.SHADOW_PINE_LOG.getValue().getPath(), new ShadowLogBlock(SHADOW_PINE_MATERIAL.registryKey(Keys.SHADOW_PINE_LOG), () -> AerialHellBlocks.HOLLOW_SHADOW_PINE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLogBlock EYE_SHADOW_PINE_LOG = register(Keys.EYE_SHADOW_PINE_LOG.getValue().getPath(), new ShadowEffectLogBlock(SHADOW_PINE_MATERIAL.registryKey(Keys.EYE_SHADOW_PINE_LOG), () -> AerialHellBlocks.HOLLOW_SHADOW_PINE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final PillarBlock STRIPPED_SHADOW_PINE_LOG = register(Keys.STRIPPED_SHADOW_PINE_LOG.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(SHADOW_PINE_LOG).registryKey(Keys.STRIPPED_SHADOW_PINE_LOG)));
    public static final PillarBlock SHADOW_PINE_WOOD = register(Keys.SHADOW_PINE_WOOD.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(SHADOW_PINE_LOG).registryKey(Keys.SHADOW_PINE_WOOD)));
    public static final PillarBlock STRIPPED_SHADOW_PINE_WOOD = register(Keys.STRIPPED_SHADOW_PINE_WOOD.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(SHADOW_PINE_LOG).registryKey(Keys.STRIPPED_SHADOW_PINE_WOOD)));
    public static final ShiftableLeavesBlock SHADOW_PINE_LEAVES = register(Keys.SHADOW_PINE_LEAVES.getValue().getPath(), new ShadowLeavesWithParticlesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).registryKey(Keys.SHADOW_PINE_LEAVES), () -> AerialHellBlocks.HOLLOW_SHADOW_PINE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock PURPLE_SHADOW_PINE_LEAVES = register(Keys.PURPLE_SHADOW_PINE_LEAVES.getValue().getPath(), new ShadowLeavesWithParticlesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).registryKey(Keys.PURPLE_SHADOW_PINE_LEAVES), () -> AerialHellBlocks.HOLLOW_PURPLE_SHADOW_PINE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final Block SHADOW_PINE_PLANKS = register(Keys.SHADOW_PINE_PLANKS.getValue().getPath(), new Block(AbstractBlock.Settings.copy(SHADOW_PINE_LOG).registryKey(Keys.SHADOW_PINE_PLANKS)));
    public static final Block SHADOW_PINE_BOOKSHELF = register(Keys.SHADOW_PINE_BOOKSHELF.getValue().getPath(), new Block(AbstractBlock.Settings.copy(SHADOW_PINE_PLANKS).registryKey(Keys.SHADOW_PINE_BOOKSHELF)));
    public static final SaplingBlock SHADOW_PINE_SAPLING = register(Keys.SHADOW_PINE_SAPLING.getValue().getPath(), new ShadowPineSaplingBlock(AerialHellTreeGrowers.SHADOW_PINE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).registryKey(Keys.SHADOW_PINE_SAPLING), AerialHellConfiguredFeatures.GIANT_SHADOW_PINE, AerialHellConfiguredFeatures.HUGE_SHADOW_PINE, 0.1F));
    public static final SaplingBlock PURPLE_SHADOW_PINE_SAPLING = register(Keys.PURPLE_SHADOW_PINE_SAPLING.getValue().getPath(), new ShadowPineSaplingBlock(AerialHellTreeGrowers.PURPLE_SHADOW_PINE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).registryKey(Keys.PURPLE_SHADOW_PINE_SAPLING), AerialHellConfiguredFeatures.GIANT_PURPLE_SHADOW_PINE, AerialHellConfiguredFeatures.HUGE_PURPLE_SHADOW_PINE, 0.1F));

    //stellar jungle tree
    public static final ShiftableLogBlock STELLAR_JUNGLE_TREE_LOG = register(Keys.STELLAR_JUNGLE_TREE_LOG.getValue().getPath(), new ShiftableLogBlock(COPPER_PINE_MATERIAL.registryKey(Keys.STELLAR_JUNGLE_TREE_LOG), () -> AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final PillarBlock STRIPPED_STELLAR_JUNGLE_TREE_LOG = register(Keys.STRIPPED_STELLAR_JUNGLE_TREE_LOG.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_LOG).registryKey(Keys.STRIPPED_STELLAR_JUNGLE_TREE_LOG)));
    public static final PillarBlock STELLAR_JUNGLE_TREE_WOOD = register(Keys.STELLAR_JUNGLE_TREE_WOOD.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_LOG).registryKey(Keys.STELLAR_JUNGLE_TREE_WOOD)));
    public static final PillarBlock STRIPPED_STELLAR_JUNGLE_TREE_WOOD = register(Keys.STRIPPED_STELLAR_JUNGLE_TREE_WOOD.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_LOG).registryKey(Keys.STRIPPED_STELLAR_JUNGLE_TREE_WOOD)));
    public static final ShiftableLeavesBlock STELLAR_JUNGLE_TREE_LEAVES = register(Keys.STELLAR_JUNGLE_TREE_LEAVES.getValue().getPath(), new ShiftableLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).registryKey(Keys.STELLAR_JUNGLE_TREE_LEAVES), () -> AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block STELLAR_JUNGLE_TREE_PLANKS = register(Keys.STELLAR_JUNGLE_TREE_PLANKS.getValue().getPath(), new Block(AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_LOG).registryKey(Keys.STELLAR_JUNGLE_TREE_PLANKS)));
    public static final Block STELLAR_JUNGLE_TREE_BOOKSHELF = register(Keys.STELLAR_JUNGLE_TREE_BOOKSHELF.getValue().getPath(), new Block(AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_PLANKS).registryKey(Keys.STELLAR_JUNGLE_TREE_BOOKSHELF)));
    public static final SaplingBlock STELLAR_JUNGLE_TREE_SAPLING = register(Keys.STELLAR_JUNGLE_TREE_SAPLING.getValue().getPath(), new AerialHellSaplingBlock(AerialHellTreeGrowers.STELLAR_JUNGLE_TREE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING).registryKey(Keys.STELLAR_JUNGLE_TREE_SAPLING), AerialHellConfiguredFeatures.GIANT_STELLAR_JUNGLE_TREE));
    public static final LargeDeadLogBlock DEAD_STELLAR_JUNGLE_TREE_LOG = register(Keys.DEAD_STELLAR_JUNGLE_TREE_LOG.getValue().getPath(), new LargeDeadLogBlock(STELLAR_JUNGLE_TREE_PLANKS.getDefaultState(), COPPER_PINE_MATERIAL.registryKey(Keys.DEAD_STELLAR_JUNGLE_TREE_LOG)));

    //shroom
    public static final PillarBlock GIANT_CORTINARIUS_VIOLACEUS_STEM = register(Keys.GIANT_CORTINARIUS_VIOLACEUS_STEM.getValue().getPath(), new PillarBlock(SHROOM_MATERIAL.registryKey(Keys.GIANT_CORTINARIUS_VIOLACEUS_STEM)));
    public static final PillarBlock STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM = register(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(GIANT_CORTINARIUS_VIOLACEUS_STEM).registryKey(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM)));
    public static final PillarBlock GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = register(Keys.GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(GIANT_CORTINARIUS_VIOLACEUS_STEM).registryKey(Keys.GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM)));
    public static final PillarBlock STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = register(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(GIANT_CORTINARIUS_VIOLACEUS_STEM).registryKey(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM)));
    public static final Block GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK = register(Keys.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.getValue().getPath(), new Block(AbstractBlock.Settings.create().registryKey(Keys.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK).mapColor(MapColor.TERRACOTTA_BLUE).strength(0.5F).sounds(BlockSoundGroup.STEM)));
    public static final Block GIANT_CORTINARIUS_VIOLACEUS_LIGHT = register(Keys.GIANT_CORTINARIUS_VIOLACEUS_LIGHT.getValue().getPath(), new Block(AbstractBlock.Settings.create().registryKey(Keys.GIANT_CORTINARIUS_VIOLACEUS_LIGHT).mapColor(MapColor.PURPLE).strength(1.0F).sounds(BlockSoundGroup.SHROOMLIGHT).luminance((state) -> {return 15;})));
    public static final FungusBlock CORTINARIUS_VIOLACEUS = register(Keys.CORTINARIUS_VIOLACEUS.getValue().getPath(), new AerialHellFungusBlock(AerialHellConfiguredFeatures.GIANT_CORTINARIUS_VIOLACEUS_PLANTED, STELLAR_GRASS_BLOCK, AbstractBlock.Settings.copy(Blocks.WARPED_FUNGUS).registryKey(Keys.CORTINARIUS_VIOLACEUS)));
    public static final Block GLOWING_BOLETUS = register(Keys.GLOWING_BOLETUS.getValue().getPath(), new AerialHellTallShroomBlock(AbstractBlock.Settings.create().registryKey(Keys.GLOWING_BOLETUS).mapColor(MapColor.GREEN).noCollision().luminance((state) -> {return 9;}).breakInstantly().sounds(BlockSoundGroup.GLOW_LICHEN), true));
    public static final Block TALL_GLOWING_BOLETUS = register(Keys.TALL_GLOWING_BOLETUS.getValue().getPath(), new DoubleShroomBlock(AbstractBlock.Settings.create().registryKey(Keys.TALL_GLOWING_BOLETUS).mapColor(MapColor.GREEN).noCollision().luminance((state) -> {return 11;}).breakInstantly().sounds(BlockSoundGroup.GLOW_LICHEN)));
    public static final Block BLUE_MEANIE_CLUSTER = register(Keys.BLUE_MEANIE_CLUSTER.getValue().getPath(), new TallShroomBlock(AbstractBlock.Settings.copy(Blocks.ROSE_BUSH).registryKey(Keys.BLUE_MEANIE_CLUSTER)));
    public static final Block GIANT_ROOT_SHROOM = register(Keys.GIANT_ROOT_SHROOM.getValue().getPath(), new AerialHellTallShroomBlock(AbstractBlock.Settings.create().registryKey(Keys.GIANT_ROOT_SHROOM).mapColor(MapColor.GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.NETHER_WART), false));

    public static final PillarBlock GIANT_VERDIGRIS_AGARIC_STEM = register(Keys.GIANT_VERDIGRIS_AGARIC_STEM.getValue().getPath(), new PillarBlock(SHROOM_MATERIAL.registryKey(Keys.GIANT_VERDIGRIS_AGARIC_STEM)));
    public static final PillarBlock STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM = register(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(GIANT_CORTINARIUS_VIOLACEUS_STEM).registryKey(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM)));
    public static final PillarBlock GIANT_VERDIGRIS_AGARIC_BARK_STEM = register(Keys.GIANT_VERDIGRIS_AGARIC_BARK_STEM.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(GIANT_CORTINARIUS_VIOLACEUS_STEM).registryKey(Keys.GIANT_VERDIGRIS_AGARIC_BARK_STEM)));
    public static final PillarBlock STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM = register(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(GIANT_CORTINARIUS_VIOLACEUS_STEM).registryKey(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM)));
    public static final Block GIANT_VERDIGRIS_AGARIC_CAP_BLOCK = register(Keys.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK.getValue().getPath(), new MushroomBlock(AbstractBlock.Settings.create().registryKey(Keys.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK).mapColor(MapColor.TERRACOTTA_BLUE).luminance((state) -> {return 10;}).strength(0.4F).sounds(BlockSoundGroup.STEM)));
    public static final MushroomPlantBlock VERDIGRIS_AGARIC = register(Keys.VERDIGRIS_AGARIC.getValue().getPath(), new AerialHellMushroomBlock(AerialHellConfiguredFeatures.GIANT_VERDIGRIS_AGARIC, AbstractBlock.Settings.create().registryKey(Keys.VERDIGRIS_AGARIC).mapColor(MapColor.GRAY).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS)));

    public static final Block GIANT_GANODERMA_APPLANATUM_BLOCK = register(Keys.GIANT_GANODERMA_APPLANATUM_BLOCK.getValue().getPath(), new MushroomBlock(AbstractBlock.Settings.create().registryKey(Keys.GIANT_GANODERMA_APPLANATUM_BLOCK).mapColor(MapColor.BROWN).strength(0.4F).sounds(BlockSoundGroup.STEM)));

    public static final Block GRAY_SHROOM_PLANKS = register(Keys.GRAY_SHROOM_PLANKS.getValue().getPath(), new Block(AbstractBlock.Settings.copy(GIANT_CORTINARIUS_VIOLACEUS_STEM).registryKey(Keys.GRAY_SHROOM_PLANKS)));
    public static final Block GRAY_SHROOM_BOOKSHELF = register(Keys.GRAY_SHROOM_BOOKSHELF.getValue().getPath(), new Block(AbstractBlock.Settings.copy(GRAY_SHROOM_PLANKS).registryKey(Keys.GRAY_SHROOM_BOOKSHELF)));

    //shadow corrupted / uncorrupted variants
    public static final ShadowLogBlock SHADOW_AERIAL_TREE_LOG = register(Keys.SHADOW_AERIAL_TREE_LOG.getValue().getPath(), new ShadowLogBlock(AbstractBlock.Settings.copy(AERIAL_TREE_LOG).registryKey(Keys.SHADOW_AERIAL_TREE_LOG), () -> AERIAL_TREE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShadowLogBlock SHADOW_GOLDEN_BEECH_LOG = register(Keys.SHADOW_GOLDEN_BEECH_LOG.getValue().getPath(), new ShadowLogBlock(AbstractBlock.Settings.copy(GOLDEN_BEECH_LOG).registryKey(Keys.SHADOW_GOLDEN_BEECH_LOG), () -> GOLDEN_BEECH_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShadowLogBlock SHADOW_COPPER_PINE_LOG = register(Keys.SHADOW_COPPER_PINE_LOG.getValue().getPath(), new ShadowLogBlock(AbstractBlock.Settings.copy(COPPER_PINE_LOG).registryKey(Keys.SHADOW_COPPER_PINE_LOG), () -> COPPER_PINE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShadowLogBlock SHADOW_LAPIS_ROBINIA_LOG = register(Keys.SHADOW_LAPIS_ROBINIA_LOG.getValue().getPath(), new ShadowLogBlock(AbstractBlock.Settings.copy(LAPIS_ROBINIA_LOG).registryKey(Keys.SHADOW_LAPIS_ROBINIA_LOG), () -> LAPIS_ROBINIA_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShadowLogBlock SHADOW_STELLAR_JUNGLE_TREE_LOG = register(Keys.SHADOW_STELLAR_JUNGLE_TREE_LOG.getValue().getPath(), new ShadowLogBlock(AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_LOG).registryKey(Keys.SHADOW_STELLAR_JUNGLE_TREE_LOG), () -> STELLAR_JUNGLE_TREE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLogBlock HOLLOW_SHADOW_PINE_LOG = register(Keys.HOLLOW_SHADOW_PINE_LOG.getValue().getPath(), new ShiftableLogBlock(AbstractBlock.Settings.copy(AERIAL_TREE_LOG).registryKey(Keys.HOLLOW_SHADOW_PINE_LOG), () -> SHADOW_PINE_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final ShiftableLeavesBlock SHADOW_AERIAL_TREE_LEAVES = register(Keys.SHADOW_AERIAL_TREE_LEAVES.getValue().getPath(), new ShadowLeavesBlock(AbstractBlock.Settings.copy(AERIAL_TREE_LEAVES).registryKey(Keys.SHADOW_AERIAL_TREE_LEAVES), () -> AerialHellBlocks.AERIAL_TREE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock SHADOW_GOLDEN_BEECH_LEAVES = register(Keys.SHADOW_GOLDEN_BEECH_LEAVES.getValue().getPath(), new ShadowLeavesBlock(AbstractBlock.Settings.copy(GOLDEN_BEECH_LEAVES).registryKey(Keys.SHADOW_GOLDEN_BEECH_LEAVES), () -> AerialHellBlocks.GOLDEN_BEECH_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock SHADOW_COPPER_PINE_LEAVES = register(Keys.SHADOW_COPPER_PINE_LEAVES.getValue().getPath(), new ShadowLeavesBlock(AbstractBlock.Settings.copy(COPPER_PINE_LEAVES).registryKey(Keys.SHADOW_COPPER_PINE_LEAVES), () -> AerialHellBlocks.COPPER_PINE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock SHADOW_LAPIS_ROBINIA_LEAVES = register(Keys.SHADOW_LAPIS_ROBINIA_LEAVES.getValue().getPath(), new ShadowLeavesBlock(AbstractBlock.Settings.copy(LAPIS_ROBINIA_LEAVES).registryKey(Keys.SHADOW_LAPIS_ROBINIA_LEAVES), () -> AerialHellBlocks.LAPIS_ROBINIA_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock SHADOW_STELLAR_JUNGLE_TREE_LEAVES = register(Keys.SHADOW_STELLAR_JUNGLE_TREE_LEAVES.getValue().getPath(), new ShadowLeavesBlock(AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_LEAVES).registryKey(Keys.SHADOW_STELLAR_JUNGLE_TREE_LEAVES), () -> AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock HOLLOW_SHADOW_PINE_LEAVES = register(Keys.HOLLOW_SHADOW_PINE_LEAVES.getValue().getPath(), new ShiftableLeavesBlock(AbstractBlock.Settings.copy(SHADOW_PINE_LEAVES).registryKey(Keys.HOLLOW_SHADOW_PINE_LEAVES), () -> AerialHellBlocks.SHADOW_PINE_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final ShiftableLeavesBlock HOLLOW_PURPLE_SHADOW_PINE_LEAVES = register(Keys.HOLLOW_PURPLE_SHADOW_PINE_LEAVES.getValue().getPath(), new ShiftableLeavesBlock(AbstractBlock.Settings.copy(PURPLE_SHADOW_PINE_LEAVES).registryKey(Keys.HOLLOW_PURPLE_SHADOW_PINE_LEAVES), () -> AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES, BiomeShifter.ShiftType.CORRUPT));

    //ladder
    public static final LadderBlock SKY_LADDER = register(Keys.SKY_LADDER.getValue().getPath(), new LadderBlock(AbstractBlock.Settings.copy(AERIAL_TREE_PLANKS).registryKey(Keys.SKY_LADDER).nonOpaque()));

    //natural blocks and items
    public static final Block STELLAR_STONE = register(Keys.STELLAR_STONE.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.STONE).registryKey(Keys.STELLAR_STONE)));
    public static final Block STELLAR_COBBLESTONE = register(Keys.STELLAR_COBBLESTONE.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.COBBLESTONE).registryKey(Keys.STELLAR_COBBLESTONE)));
    public static final Block STELLAR_STONE_BRICKS = register(Keys.STELLAR_STONE_BRICKS.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).registryKey(Keys.STELLAR_STONE_BRICKS).strength(0.5F, 10.0F)));
    public static final Block MOSSY_STELLAR_STONE = register(Keys.MOSSY_STELLAR_STONE.getValue().getPath(), new Block(AbstractBlock.Settings.copy(STELLAR_STONE).registryKey(Keys.MOSSY_STELLAR_STONE)));
    public static final Block MOSSY_STELLAR_COBBLESTONE = register(Keys.MOSSY_STELLAR_COBBLESTONE.getValue().getPath(), new Block(AbstractBlock.Settings.copy(STELLAR_STONE).registryKey(Keys.MOSSY_STELLAR_COBBLESTONE)));
    public static final Block STELLAR_CLAY = register(Keys.STELLAR_CLAY.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.CLAY).registryKey(Keys.STELLAR_CLAY)));
    public static final Block GLAUCOPHANITE = register(Keys.GLAUCOPHANITE.getValue().getPath(), new Block(AbstractBlock.Settings.create().registryKey(Keys.GLAUCOPHANITE).strength(3.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block POLISHED_GLAUCOPHANITE = register(Keys.POLISHED_GLAUCOPHANITE.getValue().getPath(), new Block(AbstractBlock.Settings.create().registryKey(Keys.POLISHED_GLAUCOPHANITE).strength(3.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block SHADOW_STONE = register(Keys.SHADOW_STONE.getValue().getPath(), new ShadowStoneBlock(AbstractBlock.Settings.copy(Blocks.STONE).registryKey(Keys.SHADOW_STONE)));

    //crystal
    public static final Block CRYSTAL_BLOCK = register(Keys.CRYSTAL_BLOCK.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.GLASS).registryKey(Keys.CRYSTAL_BLOCK).luminance((state) -> 14)));
    public static final Block CRYSTAL_BRICKS = register(Keys.CRYSTAL_BRICKS.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.STONE_BRICKS).registryKey(Keys.CRYSTAL_BRICKS).luminance((state) -> 9)));
    public static final Block CRYSTAL_BRICKS_SLAB = register(Keys.CRYSTAL_BRICKS_SLAB.getValue().getPath(), new SlabBlock(AbstractBlock.Settings.copy(CRYSTAL_BRICKS).registryKey(Keys.CRYSTAL_BRICKS_SLAB).nonOpaque()));
    public static final Block CRYSTAL_BRICKS_STAIRS = register(Keys.CRYSTAL_BRICKS_STAIRS.getValue().getPath(), new StairsBlock(CRYSTAL_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(CRYSTAL_BRICKS).registryKey(Keys.CRYSTAL_BRICKS_STAIRS)));
    public static final Block CRYSTAL_BRICKS_WALL = register(Keys.CRYSTAL_BRICKS_WALL.getValue().getPath(), new WallBlock(AbstractBlock.Settings.copy(CRYSTAL_BRICKS).registryKey(Keys.CRYSTAL_BRICKS_WALL)));
    public static final Block STELLAR_STONE_CRYSTAL_BLOCK = register(Keys.STELLAR_STONE_CRYSTAL_BLOCK.getValue().getPath(), new BasicShiftableRenderBlock(AbstractBlock.Settings.copy(CRYSTAL_BLOCK).registryKey(Keys.STELLAR_STONE_CRYSTAL_BLOCK).luminance((state) -> 13)));
    public static final Block SHADOW_CRYSTAL_BLOCK = register(Keys.SHADOW_CRYSTAL_BLOCK.getValue().getPath(), new BasicShadowSpreaderBlock(AbstractBlock.Settings.copy(CRYSTAL_BLOCK).registryKey(Keys.SHADOW_CRYSTAL_BLOCK).luminance((state) -> 12)));
    public static final Block CRYSTALLIZED_LEAVES = register(Keys.CRYSTALLIZED_LEAVES.getValue().getPath(), new UntintedParticleLeavesBlock(0.1F, AerialHellParticleTypes.FALLING_CRYSTALLIZED_LEAVES, AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).registryKey(Keys.CRYSTALLIZED_LEAVES).luminance((state) -> 12)));
    public static final Block CRYSTALLIZED_FIRE = register(Keys.CRYSTALLIZED_FIRE.getValue().getPath(), new TransparentBlock(AbstractBlock.Settings.copy(Blocks.GLASS).registryKey(Keys.CRYSTALLIZED_FIRE).luminance((state) -> 12).breakInstantly()));

    //glass and glass pane
    public static final Block SLIPPERY_SAND_GLASS = register(Keys.SLIPPERY_SAND_GLASS.getValue().getPath(), new TransparentBlock(AbstractBlock.Settings.copy(Blocks.GLASS).registryKey(Keys.SLIPPERY_SAND_GLASS).slipperiness(1.01F).allowsSpawning((state, reader, pos, entity) -> false).solidBlock((state, reader, pos) -> false).suffocates((state, reader, pos) -> false).blockVision((state, reader, pos) -> false)));
    public static final Block RED_SLIPPERY_SAND_GLASS = register(Keys.RED_SLIPPERY_SAND_GLASS.getValue().getPath(), new TransparentBlock(AbstractBlock.Settings.copy(Blocks.GLASS).registryKey(Keys.RED_SLIPPERY_SAND_GLASS).slipperiness(1.01F).allowsSpawning((state, reader, pos, entity) -> false).solidBlock((state, reader, pos) -> false).suffocates((state, reader, pos) -> false).blockVision((state, reader, pos) -> false)));
    public static final Block BLACK_SLIPPERY_SAND_GLASS = register(Keys.BLACK_SLIPPERY_SAND_GLASS.getValue().getPath(), new TransparentBlock(AbstractBlock.Settings.copy(Blocks.GLASS).registryKey(Keys.BLACK_SLIPPERY_SAND_GLASS).slipperiness(1.01F).allowsSpawning((state, reader, pos, entity) -> false).solidBlock((state, reader, pos) -> false).suffocates((state, reader, pos) -> false).blockVision((state, reader, pos) -> false)));
    public static final Block BLUE_SLIPPERY_SAND_GLASS = register(Keys.BLUE_SLIPPERY_SAND_GLASS.getValue().getPath(), new TransparentBlock(AbstractBlock.Settings.copy(Blocks.GLASS).registryKey(Keys.BLUE_SLIPPERY_SAND_GLASS).slipperiness(1.01F).allowsSpawning((state, reader, pos, entity) -> false).solidBlock((state, reader, pos) -> false).suffocates((state, reader, pos) -> false).blockVision((state, reader, pos) -> false)));
    public static final Block GREEN_SLIPPERY_SAND_GLASS = register(Keys.GREEN_SLIPPERY_SAND_GLASS.getValue().getPath(), new TransparentBlock(AbstractBlock.Settings.copy(Blocks.GLASS).registryKey(Keys.GREEN_SLIPPERY_SAND_GLASS).slipperiness(1.01F).allowsSpawning((state, reader, pos, entity) -> false).solidBlock((state, reader, pos) -> false).suffocates((state, reader, pos) -> false).blockVision((state, reader, pos) -> false)));
    public static final Block SLIPPERY_SAND_GLASS_PANE = register(Keys.SLIPPERY_SAND_GLASS_PANE.getValue().getPath(), new StainedGlassPaneBlock(DyeColor.YELLOW, AbstractBlock.Settings.create().registryKey(Keys.SLIPPERY_SAND_GLASS_PANE).slipperiness(1.01F).strength(0.3F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block RED_SLIPPERY_SAND_GLASS_PANE = register(Keys.RED_SLIPPERY_SAND_GLASS_PANE.getValue().getPath(), new StainedGlassPaneBlock(DyeColor.RED, AbstractBlock.Settings.create().registryKey(Keys.RED_SLIPPERY_SAND_GLASS_PANE).slipperiness(1.01F).strength(0.3F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block BLACK_SLIPPERY_SAND_GLASS_PANE = register(Keys.BLACK_SLIPPERY_SAND_GLASS_PANE.getValue().getPath(), new StainedGlassPaneBlock(DyeColor.BLACK, AbstractBlock.Settings.create().registryKey(Keys.BLACK_SLIPPERY_SAND_GLASS_PANE).slipperiness(1.01F).strength(0.3F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block BLUE_SLIPPERY_SAND_GLASS_PANE = register(Keys.BLUE_SLIPPERY_SAND_GLASS_PANE.getValue().getPath(), new StainedGlassPaneBlock(DyeColor.BLUE, AbstractBlock.Settings.create().registryKey(Keys.BLUE_SLIPPERY_SAND_GLASS_PANE).slipperiness(1.01F).strength(0.3F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
    public static final Block GREEN_SLIPPERY_SAND_GLASS_PANE = register(Keys.GREEN_SLIPPERY_SAND_GLASS_PANE.getValue().getPath(), new StainedGlassPaneBlock(DyeColor.GREEN, AbstractBlock.Settings.create().registryKey(Keys.GREEN_SLIPPERY_SAND_GLASS_PANE).slipperiness(1.01F).strength(0.3F).sounds(BlockSoundGroup.GLASS).nonOpaque()));

    //ghost boat
    public static final Block GHOST_BOAT_PLANKS = register(Keys.GHOST_BOAT_PLANKS.getValue().getPath(), new GhostBoatBlock(AbstractBlock.Settings.create().registryKey(Keys.GHOST_BOAT_PLANKS).strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final GhostBoatRotatedPillarBlock GHOST_BOAT_LOG = register(Keys.GHOST_BOAT_LOG.getValue().getPath(), new GhostBoatRotatedPillarBlock(AbstractBlock.Settings.copy(AERIAL_TREE_LOG).registryKey(Keys.GHOST_BOAT_LOG).nonOpaque()));
    public static final GhostBoatRotatedPillarBlock GHOST_BOAT_WOOD = register(Keys.GHOST_BOAT_WOOD.getValue().getPath(), new GhostBoatRotatedPillarBlock(AbstractBlock.Settings.copy(GHOST_BOAT_LOG).registryKey(Keys.GHOST_BOAT_WOOD).nonOpaque()));
    public static final SlabBlock GHOST_BOAT_SLAB = register(Keys.GHOST_BOAT_SLAB.getValue().getPath(), new GhostBoatSlabBlock(AbstractBlock.Settings.create().registryKey(Keys.GHOST_BOAT_SLAB).strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final StairsBlock GHOST_BOAT_STAIRS = register(Keys.GHOST_BOAT_STAIRS.getValue().getPath(), new GhostBoatStairBlock(GHOST_BOAT_PLANKS.getDefaultState(), AbstractBlock.Settings.create().registryKey(Keys.GHOST_BOAT_STAIRS).strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final FenceBlock GHOST_BOAT_FENCE = register(Keys.GHOST_BOAT_FENCE.getValue().getPath(), new GhostBoatFenceBlock(AbstractBlock.Settings.create().registryKey(Keys.GHOST_BOAT_FENCE).strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final FenceGateBlock GHOST_BOAT_GATE = register(Keys.GHOST_BOAT_GATE.getValue().getPath(), new GhostBoatFenceGateBlock(AerialHellWoodTypes.AERIAL_TREE, AbstractBlock.Settings.create().registryKey(Keys.GHOST_BOAT_GATE).strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final DoorBlock GHOST_BOAT_DOOR = register(Keys.GHOST_BOAT_DOOR.getValue().getPath(), new GhostBoatDoorBlock(BlockSetType.OAK, AbstractBlock.Settings.create().registryKey(Keys.GHOST_BOAT_DOOR).strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final TrapdoorBlock GHOST_BOAT_TRAPDOOR = register(Keys.GHOST_BOAT_TRAPDOOR.getValue().getPath(), new GhostBoatTrapDoorBlock(BlockSetType.OAK, AbstractBlock.Settings.create().registryKey(Keys.GHOST_BOAT_TRAPDOOR).strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final ChestBlock GHOST_BOAT_CHEST = register(Keys.GHOST_BOAT_CHEST.getValue().getPath(), new GhostBoatChestBlock(AbstractBlock.Settings.create().registryKey(Keys.GHOST_BOAT_CHEST).strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block GHOST_BOAT_WOOL = register(Keys.GHOST_BOAT_WOOL.getValue().getPath(), new GhostBoatBlock(AbstractBlock.Settings.create().registryKey(Keys.GHOST_BOAT_WOOL).mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sounds(BlockSoundGroup.WOOL).nonOpaque()));
    public static final Block GHOST_STELLAR_COBBLESTONE = register(Keys.GHOST_STELLAR_COBBLESTONE.getValue().getPath(), new GhostBoatBlock(AbstractBlock.Settings.create().registryKey(Keys.GHOST_STELLAR_COBBLESTONE).strength(2.5F, 2.5F).requiresTool().sounds(BlockSoundGroup.STONE).nonOpaque()));
    public static final Block GHOST_RUBY_BLOCK = register(Keys.GHOST_RUBY_BLOCK.getValue().getPath(), new GhostBoatBlock(AbstractBlock.Settings.create().registryKey(Keys.GHOST_RUBY_BLOCK).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).nonOpaque()));
    public static final Block GHOST_FLUORITE_BLOCK = register(Keys.GHOST_FLUORITE_BLOCK.getValue().getPath(), new GhostBoatBlock(AbstractBlock.Settings.create().registryKey(Keys.GHOST_FLUORITE_BLOCK).strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL).nonOpaque()));
    public static final Block GHOST_AZURITE_BLOCK = register(Keys.GHOST_AZURITE_BLOCK.getValue().getPath(), new GhostBoatBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).registryKey(Keys.GHOST_AZURITE_BLOCK).nonOpaque()));
    public static final Block GHOST_GOLD_BLOCK = register(Keys.GHOST_GOLD_BLOCK.getValue().getPath(), new GhostBoatBlock(AbstractBlock.Settings.copy(Blocks.GOLD_BLOCK).registryKey(Keys.GHOST_GOLD_BLOCK).nonOpaque()));
    public static final AerialHellBarrelBlock GHOST_BOAT_BARREL = register(Keys.GHOST_BOAT_BARREL.getValue().getPath(), new GhostBoatBarrelBlock(AbstractBlock.Settings.create().registryKey(Keys.GHOST_BOAT_BARREL).strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final CraftingTableBlock GHOST_BOAT_CRAFTING_TABLE = register(Keys.GHOST_BOAT_CRAFTING_TABLE.getValue().getPath(), new GhostBoatCraftingTableBlock(AbstractBlock.Settings.create().registryKey(Keys.GHOST_BOAT_CRAFTING_TABLE).strength(2.5F, 2.5F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final PillarBlock GHOST_BOAT_VINE_ROPE_SPOOL = register(Keys.GHOST_BOAT_VINE_ROPE_SPOOL.getValue().getPath(), new GhostBoatVineRopeSpoolBlock(AbstractBlock.Settings.create().registryKey(Keys.GHOST_BOAT_VINE_ROPE_SPOOL).nonOpaque().mapColor(MapColor.BROWN).strength(1.2F).sounds(BlockSoundGroup.WOOD)));
    public static final Block GHOST_LANTERN = register(Keys.GHOST_LANTERN.getValue().getPath(), new GhostLanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN).registryKey(Keys.GHOST_LANTERN)));

    //other condition condition blocks
    public static final Block INTANGIBLE_TEMPORARY_BLOCK = register(Keys.INTANGIBLE_TEMPORARY_BLOCK.getValue().getPath(), new IntangibleTemporaryBlock(AbstractBlock.Settings.create().registryKey(Keys.INTANGIBLE_TEMPORARY_BLOCK).strength(2.0F, 3600000.0F).dropsNothing().pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.GLASS).luminance((state) -> 7).nonOpaque()));

    //reactors
    public static final Block WEAK_LIGHT_REACTOR = register(Keys.WEAK_LIGHT_REACTOR.getValue().getPath(), new ReactorBlock(AbstractBlock.Settings.create().registryKey(Keys.WEAK_LIGHT_REACTOR).strength(5.0F, 100.0F).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.STONE).nonOpaque(), 32, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.BROKEN_WEAK_LIGHT_REACTOR));
    public static final Block HIGH_POWER_LIGHT_REACTOR = register(Keys.HIGH_POWER_LIGHT_REACTOR.getValue().getPath(), new ReactorBlock(AbstractBlock.Settings.create().registryKey(Keys.HIGH_POWER_LIGHT_REACTOR).strength(5.0F, 100.0F).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.STONE).nonOpaque(), 58, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.BROKEN_HIGH_POWER_LIGHT_REACTOR));
    public static final Block WEAK_SHADOW_REACTOR = register(Keys.WEAK_SHADOW_REACTOR.getValue().getPath(), new ReactorBlock(AbstractBlock.Settings.create().registryKey(Keys.WEAK_SHADOW_REACTOR).strength(5.0F, 100.0F).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.STONE).nonOpaque(), 26, BiomeShifter.ShiftType.CORRUPT, () -> AerialHellBlocks.BROKEN_WEAK_SHADOW_REACTOR));
    public static final Block HIGH_POWER_SHADOW_REACTOR = register(Keys.HIGH_POWER_SHADOW_REACTOR.getValue().getPath(), new ReactorBlock(AbstractBlock.Settings.create().registryKey(Keys.HIGH_POWER_SHADOW_REACTOR).strength(5.0F, 100.0F).pistonBehavior(PistonBehavior.IGNORE).sounds(BlockSoundGroup.STONE).nonOpaque(), 60, BiomeShifter.ShiftType.CORRUPT, () -> AerialHellBlocks.BROKEN_HIGH_POWER_SHADOW_REACTOR));

    public static final Block BROKEN_WEAK_LIGHT_REACTOR = register(Keys.BROKEN_WEAK_LIGHT_REACTOR.getValue().getPath(), new Block(AbstractBlock.Settings.copy(WEAK_LIGHT_REACTOR).registryKey(Keys.BROKEN_WEAK_LIGHT_REACTOR)));
    public static final Block BROKEN_HIGH_POWER_LIGHT_REACTOR = register(Keys.BROKEN_HIGH_POWER_LIGHT_REACTOR.getValue().getPath(), new Block(AbstractBlock.Settings.copy(HIGH_POWER_LIGHT_REACTOR).registryKey(Keys.BROKEN_HIGH_POWER_LIGHT_REACTOR)));
    public static final Block BROKEN_WEAK_SHADOW_REACTOR = register(Keys.BROKEN_WEAK_SHADOW_REACTOR.getValue().getPath(), new Block(AbstractBlock.Settings.copy(WEAK_SHADOW_REACTOR).registryKey(Keys.BROKEN_WEAK_SHADOW_REACTOR)));
    public static final Block BROKEN_HIGH_POWER_SHADOW_REACTOR = register(Keys.BROKEN_HIGH_POWER_SHADOW_REACTOR.getValue().getPath(), new Block(AbstractBlock.Settings.copy(HIGH_POWER_SHADOW_REACTOR).registryKey(Keys.BROKEN_HIGH_POWER_SHADOW_REACTOR)));

    //solid_ethers
    public static final Block WHITE_SOLID_ETHER = register(Keys.WHITE_SOLID_ETHER.getValue().getPath(), new SolidEtherBlock(AbstractBlock.Settings.create().registryKey(Keys.WHITE_SOLID_ETHER).strength(0.2F).sounds(BlockSoundGroup.WOOL).nonOpaque()));
    public static final Block BLUE_SOLID_ETHER = register(Keys.BLUE_SOLID_ETHER.getValue().getPath(), new BlueSolidEtherBlock(AbstractBlock.Settings.copy(WHITE_SOLID_ETHER).registryKey(Keys.BLUE_SOLID_ETHER)));
    public static final Block GOLDEN_SOLID_ETHER = register(Keys.GOLDEN_SOLID_ETHER.getValue().getPath(), new SolidEtherBlock(AbstractBlock.Settings.copy(WHITE_SOLID_ETHER).registryKey(Keys.GOLDEN_SOLID_ETHER)));
    public static final Block GREEN_SOLID_ETHER = register(Keys.GREEN_SOLID_ETHER.getValue().getPath(), new GreenSolidEtherBlock(AbstractBlock.Settings.copy(WHITE_SOLID_ETHER).registryKey(Keys.GREEN_SOLID_ETHER)));
    public static final Block PURPLE_SOLID_ETHER = register(Keys.PURPLE_SOLID_ETHER.getValue().getPath(), new PurpleSolidEtherBlock(AbstractBlock.Settings.copy(WHITE_SOLID_ETHER).registryKey(Keys.PURPLE_SOLID_ETHER)));

    //dungeons blocks
    public static final Block MUD_BRICKS = register(Keys.MUD_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.create().registryKey(Keys.MUD_BRICKS).strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block CRACKED_MUD_BRICKS = register(Keys.CRACKED_MUD_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(MUD_BRICKS).registryKey(Keys.CRACKED_MUD_BRICKS)));
    public static final Block MOSSY_MUD_BRICKS = register(Keys.MOSSY_MUD_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(MUD_BRICKS).registryKey(Keys.MOSSY_MUD_BRICKS)));
    public static final Block CHISELED_MUD_BRICKS = register(Keys.CHISELED_MUD_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(MUD_BRICKS).registryKey(Keys.CHISELED_MUD_BRICKS)));
    public static final Block LIGHT_MUD_BRICKS = register(Keys.LIGHT_MUD_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(MUD_BRICKS).registryKey(Keys.LIGHT_MUD_BRICKS).luminance((state) -> 11)));
    public static final Block CRACKED_LIGHT_MUD_BRICKS = register(Keys.CRACKED_LIGHT_MUD_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(MUD_BRICKS).registryKey(Keys.CRACKED_LIGHT_MUD_BRICKS)));
    public static final Block LUNATIC_STONE = register(Keys.LUNATIC_STONE.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.create().registryKey(Keys.LUNATIC_STONE).strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block DARK_LUNATIC_STONE = register(Keys.DARK_LUNATIC_STONE.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.create().registryKey(Keys.DARK_LUNATIC_STONE).strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block ROOF_LUNATIC_STONE = register(Keys.ROOF_LUNATIC_STONE.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.create().registryKey(Keys.ROOF_LUNATIC_STONE).strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block CRACKED_LUNATIC_STONE = register(Keys.CRACKED_LUNATIC_STONE.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.create().registryKey(Keys.CRACKED_LUNATIC_STONE).strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block CHISELED_LUNATIC_STONE = register(Keys.CHISELED_LUNATIC_STONE.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.create().registryKey(Keys.CHISELED_LUNATIC_STONE).strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block LIGHT_LUNATIC_STONE = register(Keys.LIGHT_LUNATIC_STONE.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(LUNATIC_STONE).registryKey(Keys.LIGHT_LUNATIC_STONE).luminance((state) -> 11)));
    public static final Block ROOF_LIGHT_LUNATIC_STONE = register(Keys.ROOF_LIGHT_LUNATIC_STONE.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(LUNATIC_STONE).registryKey(Keys.ROOF_LIGHT_LUNATIC_STONE).luminance((state) -> 11)));
    public static final Block CRACKED_LIGHT_LUNATIC_STONE = register(Keys.CRACKED_LIGHT_LUNATIC_STONE.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(LUNATIC_STONE).registryKey(Keys.CRACKED_LIGHT_LUNATIC_STONE)));
    public static final Block SHADOW_CATACOMBS_BRICKS = register(Keys.SHADOW_CATACOMBS_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.create().registryKey(Keys.SHADOW_CATACOMBS_BRICKS).strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block CRACKED_SHADOW_CATACOMBS_BRICKS = register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS).registryKey(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS)));
    public static final Block MOSSY_SHADOW_CATACOMBS_BRICKS = register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS).registryKey(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS)));
    public static final Block CHISELED_SHADOW_CATACOMBS_BRICKS = register(Keys.CHISELED_SHADOW_CATACOMBS_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS).registryKey(Keys.CHISELED_SHADOW_CATACOMBS_BRICKS)));
    public static final Block BONE_SHADOW_CATACOMBS_BRICKS = register(Keys.BONE_SHADOW_CATACOMBS_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS).registryKey(Keys.BONE_SHADOW_CATACOMBS_BRICKS).sounds(BlockSoundGroup.BONE)));
    public static final Block SKULL_SHADOW_CATACOMBS_BRICKS = register(Keys.SKULL_SHADOW_CATACOMBS_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS).registryKey(Keys.SKULL_SHADOW_CATACOMBS_BRICKS).sounds(BlockSoundGroup.BONE)));
    public static final Block LIGHT_SHADOW_CATACOMBS_BRICKS = register(Keys.LIGHT_SHADOW_CATACOMBS_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS).registryKey(Keys.LIGHT_SHADOW_CATACOMBS_BRICKS).luminance((state) -> 11)));
    public static final Block CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS = register(Keys.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS).registryKey(Keys.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS)));
    public static final Block GOLDEN_NETHER_BRICKS  = register(Keys.GOLDEN_NETHER_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.create().registryKey(Keys.GOLDEN_NETHER_BRICKS).strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block CRACKED_GOLDEN_NETHER_BRICKS  = register(Keys.CRACKED_GOLDEN_NETHER_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.create().registryKey(Keys.CRACKED_GOLDEN_NETHER_BRICKS).strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block CHISELED_GOLDEN_NETHER_BRICKS  = register(Keys.CHISELED_GOLDEN_NETHER_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.create().registryKey(Keys.CHISELED_GOLDEN_NETHER_BRICKS).strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block LIGHT_GOLDEN_NETHER_BRICKS = register(Keys.LIGHT_GOLDEN_NETHER_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(GOLDEN_NETHER_BRICKS).registryKey(Keys.LIGHT_GOLDEN_NETHER_BRICKS).luminance((state) -> 11)));
    public static final Block CRACKED_LIGHT_GOLDEN_NETHER_BRICKS = register(Keys.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(GOLDEN_NETHER_BRICKS).registryKey(Keys.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS)));
    public static final PillarBlock LUNATIC_PILLAR = register(Keys.LUNATIC_PILLAR.getValue().getPath(), new CoreProtectedRotatedPillarBlock(AbstractBlock.Settings.create().registryKey(Keys.LUNATIC_PILLAR).mapColor(MapColor.WHITE).strength(2.0F, 6.0F).sounds(BlockSoundGroup.METAL).requiresTool()));
    public static final PillarBlock LUNATIC_PILLAR_TOP = register(Keys.LUNATIC_PILLAR_TOP.getValue().getPath(), new CoreProtectedRotatedPillarBlock(AbstractBlock.Settings.create().registryKey(Keys.LUNATIC_PILLAR_TOP).mapColor(MapColor.WHITE).strength(2.0F, 6.0F).sounds(BlockSoundGroup.METAL).requiresTool()));
    public static final Block VOLUCITE_STONE = register(Keys.VOLUCITE_STONE.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.create().registryKey(Keys.VOLUCITE_STONE).strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE)));
    public static final Block CRACKED_VOLUCITE_STONE = register(Keys.CRACKED_VOLUCITE_STONE.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.create().registryKey(Keys.CRACKED_VOLUCITE_STONE).strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE)));
    public static final Block CHISELED_VOLUCITE_STONE = register(Keys.CHISELED_VOLUCITE_STONE.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.create().registryKey(Keys.CHISELED_VOLUCITE_STONE).strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE)));
    public static final Block LIGHT_VOLUCITE_STONE = register(Keys.LIGHT_VOLUCITE_STONE.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.create().registryKey(Keys.LIGHT_VOLUCITE_STONE).strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).luminance((state) -> 11)));
    public static final Block CRACKED_LIGHT_VOLUCITE_STONE = register(Keys.CRACKED_LIGHT_VOLUCITE_STONE.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.create().registryKey(Keys.CRACKED_LIGHT_VOLUCITE_STONE).strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE)));

    //dungeon cores
    public static final Block MUD_DUNGEON_CORE = register(Keys.MUD_DUNGEON_CORE.getValue().getPath(), new DungeonCoreBlock(AbstractBlock.Settings.create().registryKey(Keys.MUD_DUNGEON_CORE).strength(30.0F, 1200.0F).sounds(BlockSoundGroup.STONE).requiresTool(), 181));
    public static final Block LUNATIC_DUNGEON_CORE = register(Keys.LUNATIC_DUNGEON_CORE.getValue().getPath(), new DungeonCoreBlock(AbstractBlock.Settings.create().registryKey(Keys.LUNATIC_DUNGEON_CORE).strength(40.0F, 1200.0F).sounds(BlockSoundGroup.STONE).requiresTool(), 181));
    public static final Block SHADOW_CATACOMBS_DUNGEON_CORE = register(Keys.SHADOW_CATACOMBS_DUNGEON_CORE.getValue().getPath(), new DungeonCoreBlock(AbstractBlock.Settings.create().registryKey(Keys.SHADOW_CATACOMBS_DUNGEON_CORE).strength(30.0F, 1200.0F).sounds(BlockSoundGroup.STONE).requiresTool(), 181));
    public static final Block GOLDEN_NETHER_DUNGEON_CORE = register(Keys.GOLDEN_NETHER_DUNGEON_CORE.getValue().getPath(), new DungeonCoreBlock(AbstractBlock.Settings.create().registryKey(Keys.GOLDEN_NETHER_DUNGEON_CORE).strength(50.0F, 1200.0F).sounds(BlockSoundGroup.STONE).requiresTool(), 101));
    public static final Block VOLUCITE_DUNGEON_CORE = register(Keys.VOLUCITE_DUNGEON_CORE.getValue().getPath(), new DungeonCoreBlock(AbstractBlock.Settings.create().registryKey(Keys.VOLUCITE_DUNGEON_CORE).strength(50.0F, 1200.0F).sounds(BlockSoundGroup.STONE).requiresTool(), 101));

    //dungeons slabs, stairs & walls
    public static final SlabBlock MUD_BRICKS_SLAB = register(Keys.MUD_BRICKS_SLAB.getValue().getPath(), new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(MUD_BRICKS).registryKey(Keys.MUD_BRICKS_SLAB)));
    public static final StairsBlock MUD_BRICKS_STAIRS = register(Keys.MUD_BRICKS_STAIRS.getValue().getPath(), new CoreProtectedStairsBlock(MUD_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(MUD_BRICKS).registryKey(Keys.MUD_BRICKS_STAIRS)));
    public static final WallBlock MUD_BRICKS_WALL = register(Keys.MUD_BRICKS_WALL.getValue().getPath(), new CoreProtectedWallBlock(AbstractBlock.Settings.copy(MUD_BRICKS).registryKey(Keys.MUD_BRICKS_WALL)));
    public static final SlabBlock CRACKED_MUD_BRICKS_SLAB = register(Keys.CRACKED_MUD_BRICKS_SLAB.getValue().getPath(), new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(CRACKED_MUD_BRICKS).registryKey(Keys.CRACKED_MUD_BRICKS_SLAB)));
    public static final StairsBlock CRACKED_MUD_BRICKS_STAIRS = register(Keys.CRACKED_MUD_BRICKS_STAIRS.getValue().getPath(), new CoreProtectedStairsBlock(CRACKED_MUD_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(MUD_BRICKS).registryKey(Keys.CRACKED_MUD_BRICKS_STAIRS)));
    public static final WallBlock CRACKED_MUD_BRICKS_WALL = register(Keys.CRACKED_MUD_BRICKS_WALL.getValue().getPath(), new CoreProtectedWallBlock(AbstractBlock.Settings.copy(CRACKED_MUD_BRICKS).registryKey(Keys.CRACKED_MUD_BRICKS_WALL)));
    public static final SlabBlock MOSSY_MUD_BRICKS_SLAB = register(Keys.MOSSY_MUD_BRICKS_SLAB.getValue().getPath(), new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(MOSSY_MUD_BRICKS).registryKey(Keys.MOSSY_MUD_BRICKS_SLAB)));
    public static final StairsBlock MOSSY_MUD_BRICKS_STAIRS = register(Keys.MOSSY_MUD_BRICKS_STAIRS.getValue().getPath(), new CoreProtectedStairsBlock(MOSSY_MUD_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(MOSSY_MUD_BRICKS).registryKey(Keys.MOSSY_MUD_BRICKS_STAIRS)));
    public static final WallBlock MOSSY_MUD_BRICKS_WALL = register(Keys.MOSSY_MUD_BRICKS_WALL.getValue().getPath(), new CoreProtectedWallBlock(AbstractBlock.Settings.copy(MOSSY_MUD_BRICKS).registryKey(Keys.MOSSY_MUD_BRICKS_WALL)));
    public static final SlabBlock VOLUCITE_STONE_SLAB = register(Keys.VOLUCITE_STONE_SLAB.getValue().getPath(), new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(VOLUCITE_STONE).registryKey(Keys.VOLUCITE_STONE_SLAB)));
    public static final StairsBlock VOLUCITE_STONE_STAIRS = register(Keys.VOLUCITE_STONE_STAIRS.getValue().getPath(), new CoreProtectedStairsBlock(VOLUCITE_STONE.getDefaultState(), AbstractBlock.Settings.copy(VOLUCITE_STONE).registryKey(Keys.VOLUCITE_STONE_STAIRS)));
    public static final WallBlock VOLUCITE_STONE_WALL = register(Keys.VOLUCITE_STONE_WALL.getValue().getPath(), new CoreProtectedWallBlock(AbstractBlock.Settings.copy(VOLUCITE_STONE).registryKey(Keys.VOLUCITE_STONE_WALL)));
    public static final SlabBlock CRACKED_VOLUCITE_STONE_SLAB = register(Keys.CRACKED_VOLUCITE_STONE_SLAB.getValue().getPath(), new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(CRACKED_VOLUCITE_STONE).registryKey(Keys.CRACKED_VOLUCITE_STONE_SLAB)));
    public static final StairsBlock CRACKED_VOLUCITE_STONE_STAIRS = register(Keys.CRACKED_VOLUCITE_STONE_STAIRS.getValue().getPath(), new CoreProtectedStairsBlock(CRACKED_VOLUCITE_STONE.getDefaultState(), AbstractBlock.Settings.copy(VOLUCITE_STONE).registryKey(Keys.CRACKED_VOLUCITE_STONE_STAIRS)));
    public static final WallBlock CRACKED_VOLUCITE_STONE_WALL = register(Keys.CRACKED_VOLUCITE_STONE_WALL.getValue().getPath(), new CoreProtectedWallBlock(AbstractBlock.Settings.copy(CRACKED_VOLUCITE_STONE).registryKey(Keys.CRACKED_VOLUCITE_STONE_WALL)));
    public static final SlabBlock LUNATIC_STONE_SLAB = register(Keys.LUNATIC_STONE_SLAB.getValue().getPath(), new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(LUNATIC_STONE).registryKey(Keys.LUNATIC_STONE_SLAB)));
    public static final StairsBlock LUNATIC_STONE_STAIRS = register(Keys.LUNATIC_STONE_STAIRS.getValue().getPath(), new CoreProtectedStairsBlock(LUNATIC_STONE.getDefaultState(), AbstractBlock.Settings.copy(LUNATIC_STONE).registryKey(Keys.LUNATIC_STONE_STAIRS)));
    public static final WallBlock LUNATIC_STONE_WALL = register(Keys.LUNATIC_STONE_WALL.getValue().getPath(), new CoreProtectedWallBlock(AbstractBlock.Settings.copy(LUNATIC_STONE).registryKey(Keys.LUNATIC_STONE_WALL)));
    public static final SlabBlock DARK_LUNATIC_STONE_SLAB = register(Keys.DARK_LUNATIC_STONE_SLAB.getValue().getPath(), new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(DARK_LUNATIC_STONE).registryKey(Keys.DARK_LUNATIC_STONE_SLAB)));
    public static final StairsBlock DARK_LUNATIC_STONE_STAIRS = register(Keys.DARK_LUNATIC_STONE_STAIRS.getValue().getPath(), new CoreProtectedStairsBlock(DARK_LUNATIC_STONE.getDefaultState(), AbstractBlock.Settings.copy(DARK_LUNATIC_STONE).registryKey(Keys.DARK_LUNATIC_STONE_STAIRS)));
    public static final WallBlock DARK_LUNATIC_STONE_WALL = register(Keys.DARK_LUNATIC_STONE_WALL.getValue().getPath(), new CoreProtectedWallBlock(AbstractBlock.Settings.copy(DARK_LUNATIC_STONE).registryKey(Keys.DARK_LUNATIC_STONE_WALL)));
    public static final SlabBlock CRACKED_LUNATIC_STONE_SLAB = register(Keys.CRACKED_LUNATIC_STONE_SLAB.getValue().getPath(), new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(CRACKED_LUNATIC_STONE).registryKey(Keys.CRACKED_LUNATIC_STONE_SLAB)));
    public static final StairsBlock CRACKED_LUNATIC_STONE_STAIRS = register(Keys.CRACKED_LUNATIC_STONE_STAIRS.getValue().getPath(), new CoreProtectedStairsBlock(CRACKED_LUNATIC_STONE.getDefaultState(), AbstractBlock.Settings.copy(VOLUCITE_STONE).registryKey(Keys.CRACKED_LUNATIC_STONE_STAIRS)));
    public static final WallBlock CRACKED_LUNATIC_STONE_WALL = register(Keys.CRACKED_LUNATIC_STONE_WALL.getValue().getPath(), new CoreProtectedWallBlock(AbstractBlock.Settings.copy(CRACKED_LUNATIC_STONE).registryKey(Keys.CRACKED_LUNATIC_STONE_WALL)));
    public static final SlabBlock SHADOW_CATACOMBS_BRICKS_SLAB = register(Keys.SHADOW_CATACOMBS_BRICKS_SLAB.getValue().getPath(), new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS).registryKey(Keys.SHADOW_CATACOMBS_BRICKS_SLAB)));
    public static final StairsBlock SHADOW_CATACOMBS_BRICKS_STAIRS = register(Keys.SHADOW_CATACOMBS_BRICKS_STAIRS.getValue().getPath(), new CoreProtectedStairsBlock(SHADOW_CATACOMBS_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS).registryKey(Keys.SHADOW_CATACOMBS_BRICKS_STAIRS)));
    public static final WallBlock SHADOW_CATACOMBS_BRICKS_WALL = register(Keys.SHADOW_CATACOMBS_BRICKS_WALL.getValue().getPath(), new CoreProtectedWallBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS).registryKey(Keys.SHADOW_CATACOMBS_BRICKS_WALL)));
    public static final SlabBlock CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB = register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB.getValue().getPath(), new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(CRACKED_SHADOW_CATACOMBS_BRICKS).registryKey(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB)));
    public static final StairsBlock CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS = register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS.getValue().getPath(), new CoreProtectedStairsBlock(CRACKED_SHADOW_CATACOMBS_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(VOLUCITE_STONE).registryKey(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS)));
    public static final WallBlock CRACKED_SHADOW_CATACOMBS_BRICKS_WALL = register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL.getValue().getPath(), new CoreProtectedWallBlock(AbstractBlock.Settings.copy(CRACKED_SHADOW_CATACOMBS_BRICKS).registryKey(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL)));
    public static final SlabBlock MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB = register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB.getValue().getPath(), new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(MOSSY_SHADOW_CATACOMBS_BRICKS).registryKey(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB)));
    public static final StairsBlock MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS = register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS.getValue().getPath(), new CoreProtectedStairsBlock(MOSSY_SHADOW_CATACOMBS_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(MOSSY_SHADOW_CATACOMBS_BRICKS).registryKey(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS)));
    public static final WallBlock MOSSY_SHADOW_CATACOMBS_BRICKS_WALL = register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL.getValue().getPath(), new CoreProtectedWallBlock(AbstractBlock.Settings.copy(MOSSY_SHADOW_CATACOMBS_BRICKS).registryKey(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL)));
    public static final PaneBlock SHADOW_BARS = register(Keys.SHADOW_BARS.getValue().getPath(), new ShadowBarsBlock(METAL_NOTSOLID_MATERIAL.registryKey(Keys.SHADOW_BARS)));
    public static final SlabBlock GOLDEN_NETHER_BRICKS_SLAB = register(Keys.GOLDEN_NETHER_BRICKS_SLAB.getValue().getPath(), new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(GOLDEN_NETHER_BRICKS).registryKey(Keys.GOLDEN_NETHER_BRICKS_SLAB)));
    public static final StairsBlock GOLDEN_NETHER_BRICKS_STAIRS = register(Keys.GOLDEN_NETHER_BRICKS_STAIRS.getValue().getPath(), new CoreProtectedStairsBlock(GOLDEN_NETHER_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(VOLUCITE_STONE).registryKey(Keys.GOLDEN_NETHER_BRICKS_STAIRS)));
    public static final WallBlock GOLDEN_NETHER_BRICKS_WALL = register(Keys.GOLDEN_NETHER_BRICKS_WALL.getValue().getPath(), new CoreProtectedWallBlock(AbstractBlock.Settings.copy(GOLDEN_NETHER_BRICKS).registryKey(Keys.GOLDEN_NETHER_BRICKS_WALL)));
    public static final SlabBlock CRACKED_GOLDEN_NETHER_BRICKS_SLAB = register(Keys.CRACKED_GOLDEN_NETHER_BRICKS_SLAB.getValue().getPath(), new CoreProtectedSlabBlock(AbstractBlock.Settings.copy(CRACKED_GOLDEN_NETHER_BRICKS).registryKey(Keys.CRACKED_GOLDEN_NETHER_BRICKS_SLAB)));
    public static final StairsBlock CRACKED_GOLDEN_NETHER_BRICKS_STAIRS = register(Keys.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS.getValue().getPath(), new CoreProtectedStairsBlock(CRACKED_GOLDEN_NETHER_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(VOLUCITE_STONE).registryKey(Keys.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS)));
    public static final WallBlock CRACKED_GOLDEN_NETHER_BRICKS_WALL = register(Keys.CRACKED_GOLDEN_NETHER_BRICKS_WALL.getValue().getPath(), new CoreProtectedWallBlock(AbstractBlock.Settings.copy(CRACKED_GOLDEN_NETHER_BRICKS).registryKey(Keys.CRACKED_GOLDEN_NETHER_BRICKS_WALL)));

    //smoky quartz
    public static final Block SMOKY_QUARTZ_BLOCK = register(Keys.SMOKY_QUARTZ_BLOCK.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK).registryKey(Keys.SMOKY_QUARTZ_BLOCK)));
    public static final Block SMOOTH_SMOKY_QUARTZ = register(Keys.SMOOTH_SMOKY_QUARTZ.getValue().getPath(), new Block(AbstractBlock.Settings.copy(SMOKY_QUARTZ_BLOCK).registryKey(Keys.SMOOTH_SMOKY_QUARTZ)));
    public static final Block CHISELED_SMOKY_QUARTZ_BLOCK = register(Keys.CHISELED_SMOKY_QUARTZ_BLOCK.getValue().getPath(), new Block(AbstractBlock.Settings.copy(SMOKY_QUARTZ_BLOCK).registryKey(Keys.CHISELED_SMOKY_QUARTZ_BLOCK)));
    public static final Block SMOKY_QUARTZ_BRICKS = register(Keys.SMOKY_QUARTZ_BRICKS.getValue().getPath(), new Block(AbstractBlock.Settings.copy(SMOKY_QUARTZ_BLOCK).registryKey(Keys.SMOKY_QUARTZ_BRICKS)));
    public static final PillarBlock SMOKY_QUARTZ_PILLAR = register(Keys.SMOKY_QUARTZ_PILLAR.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.copy(SMOKY_QUARTZ_BLOCK).registryKey(Keys.SMOKY_QUARTZ_PILLAR)));
    public static final SlabBlock SMOKY_QUARTZ_SLAB = register(Keys.SMOKY_QUARTZ_SLAB.getValue().getPath(), new SlabBlock(AbstractBlock.Settings.copy(SMOKY_QUARTZ_BLOCK).registryKey(Keys.SMOKY_QUARTZ_SLAB)));
    public static final SlabBlock SMOOTH_SMOKY_QUARTZ_SLAB = register(Keys.SMOOTH_SMOKY_QUARTZ_SLAB.getValue().getPath(), new SlabBlock(AbstractBlock.Settings.copy(SMOKY_QUARTZ_BLOCK).registryKey(Keys.SMOOTH_SMOKY_QUARTZ_SLAB)));
    public static final StairsBlock SMOKY_QUARTZ_STAIRS = register(Keys.SMOKY_QUARTZ_STAIRS.getValue().getPath(), new StairsBlock(SMOKY_QUARTZ_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(SMOKY_QUARTZ_BLOCK).registryKey(Keys.SMOKY_QUARTZ_STAIRS)));
    public static final StairsBlock SMOOTH_SMOKY_QUARTZ_STAIRS = register(Keys.SMOOTH_SMOKY_QUARTZ_STAIRS.getValue().getPath(), new StairsBlock(SMOOTH_SMOKY_QUARTZ.getDefaultState(), AbstractBlock.Settings.copy(SMOKY_QUARTZ_BLOCK).registryKey(Keys.SMOOTH_SMOKY_QUARTZ_STAIRS)));

    //dungeon trapped blocks
    public static final Block TRAPPED_MUD_BRICKS = register(Keys.TRAPPED_MUD_BRICKS.getValue().getPath(), new CoreProtectedTrappedBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN).registryKey(Keys.TRAPPED_MUD_BRICKS)));
    public static final Block TRAPPED_LIGHT_MUD_BRICKS = register(Keys.TRAPPED_LIGHT_MUD_BRICKS.getValue().getPath(), new CoreProtectedTrappedBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN).registryKey(Keys.TRAPPED_LIGHT_MUD_BRICKS).luminance((state) -> 11)));
    public static final Block TRAPPED_LUNATIC_STONE = register(Keys.TRAPPED_LUNATIC_STONE.getValue().getPath(), new CoreProtectedTrappedBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN).registryKey(Keys.TRAPPED_LUNATIC_STONE)));
    public static final Block TRAPPED_LIGHT_LUNATIC_STONE = register(Keys.TRAPPED_LIGHT_LUNATIC_STONE.getValue().getPath(), new CoreProtectedTrappedBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN).registryKey(Keys.TRAPPED_LIGHT_LUNATIC_STONE).luminance((state) -> 11)));
    public static final Block TRAPPED_GOLDEN_NETHER_BRICKS = register(Keys.TRAPPED_GOLDEN_NETHER_BRICKS.getValue().getPath(), new CoreProtectedTrappedBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN).registryKey(Keys.TRAPPED_GOLDEN_NETHER_BRICKS)));
    public static final Block TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS = register(Keys.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS.getValue().getPath(), new CoreProtectedTrappedBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN).registryKey(Keys.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS).luminance((state) -> 11)));

    //dungeon other blocks, loots
    public static final PillarBlock MUD_BONE_BLOCK = register(Keys.MUD_BONE_BLOCK.getValue().getPath(), new PillarBlock(AbstractBlock.Settings.create().registryKey(Keys.MUD_BONE_BLOCK).mapColor(MapColor.BROWN).requiresTool().strength(2.5F).sounds(BlockSoundGroup.BONE)));
    public static final Block MUD_BONE_PILE_BLOCK = register(Keys.MUD_BONE_PILE_BLOCK.getValue().getPath(), new BonePileBlock(AbstractBlock.Settings.create().registryKey(Keys.MUD_BONE_PILE_BLOCK).mapColor(MapColor.BROWN).ticksRandomly().strength(2.5F).sounds(BlockSoundGroup.BONE)));
    public static final Block THORNY_COBWEB = register(Keys.THORNY_COBWEB.getValue().getPath(), new ThornyWebBlock(AbstractBlock.Settings.create().registryKey(Keys.THORNY_COBWEB).noCollision().requiresTool().strength(8.0F)));
    public static final Block AERIAL_NETHERRACK = register(Keys.AERIAL_NETHERRACK.getValue().getPath(), new Block(AbstractBlock.Settings.create().registryKey(Keys.AERIAL_NETHERRACK).mapColor(MapColor.RED).requiresTool().strength(6.0F, 8.0F)));
    public static final SlabBlock AERIAL_NETHERRACK_SLAB = register(Keys.AERIAL_NETHERRACK_SLAB.getValue().getPath(), new SlabBlock(AbstractBlock.Settings.copy(AERIAL_NETHERRACK).registryKey(Keys.AERIAL_NETHERRACK_SLAB)));
    public static final StairsBlock AERIAL_NETHERRACK_STAIRS = register(Keys.AERIAL_NETHERRACK_STAIRS.getValue().getPath(), new StairsBlock(AERIAL_NETHERRACK.getDefaultState(), AbstractBlock.Settings.copy(AERIAL_NETHERRACK).registryKey(Keys.AERIAL_NETHERRACK_STAIRS)));
    public static final WallBlock AERIAL_NETHERRACK_WALL = register(Keys.AERIAL_NETHERRACK_WALL.getValue().getPath(), new WallBlock(AbstractBlock.Settings.copy(AERIAL_NETHERRACK).registryKey(Keys.AERIAL_NETHERRACK_WALL)));

    //dungeon bookshelfs
    public static final Block MUD_BOOKSHELF = register(Keys.MUD_BOOKSHELF.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(MUD_BRICKS).registryKey(Keys.MUD_BOOKSHELF)));
    public static final Block LUNATIC_BOOKSHELF = register(Keys.LUNATIC_BOOKSHELF.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(LUNATIC_STONE).registryKey(Keys.LUNATIC_BOOKSHELF)));
    public static final Block GOLDEN_NETHER_BOOKSHELF = register(Keys.GOLDEN_NETHER_BOOKSHELF.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(GOLDEN_NETHER_BRICKS).registryKey(Keys.GOLDEN_NETHER_BOOKSHELF)));
    public static final Block SHADOW_CATACOMBS_BOOKSHELF = register(Keys.SHADOW_CATACOMBS_BOOKSHELF.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS).registryKey(Keys.SHADOW_CATACOMBS_BOOKSHELF)));
    public static final Block VOLUCITE_BOOKSHELF = register(Keys.VOLUCITE_BOOKSHELF.getValue().getPath(), new CoreProtectedBlock(AbstractBlock.Settings.copy(VOLUCITE_STONE).registryKey(Keys.VOLUCITE_BOOKSHELF)));

    //glyph blocks
    public static final Block MUD_GLYPH_BLOCK = register(Keys.MUD_GLYPH_BLOCK.getValue().getPath(), new CoreProtectedGlyphBlock(AbstractBlock.Settings.copy(MUD_BRICKS).registryKey(Keys.MUD_GLYPH_BLOCK).luminance((state) -> 9)));
    public static final Block LUNATIC_GLYPH_BLOCK = register(Keys.LUNATIC_GLYPH_BLOCK.getValue().getPath(), new CoreProtectedGlyphBlock(AbstractBlock.Settings.copy(LUNATIC_STONE).registryKey(Keys.LUNATIC_GLYPH_BLOCK).luminance((state) -> 9)));
    public static final Block GOLDEN_NETHER_PRISON_GLYPH_BLOCK = register(Keys.GOLDEN_NETHER_PRISON_GLYPH_BLOCK.getValue().getPath(), new CoreProtectedGlyphBlock(AbstractBlock.Settings.copy(GOLDEN_NETHER_BRICKS).registryKey(Keys.GOLDEN_NETHER_PRISON_GLYPH_BLOCK).luminance((state) -> 9)));
    public static final Block VOLUCITE_GLYPH_BLOCK = register(Keys.VOLUCITE_GLYPH_BLOCK.getValue().getPath(), new CoreProtectedGlyphBlock(AbstractBlock.Settings.copy(VOLUCITE_STONE).registryKey(Keys.VOLUCITE_GLYPH_BLOCK).luminance((state) -> 9)));
    public static final Block SHADOW_CATACOMBS_GLYPH_BLOCK = register(Keys.SHADOW_CATACOMBS_GLYPH_BLOCK.getValue().getPath(), new CoreProtectedGlyphBlock(AbstractBlock.Settings.copy(SHADOW_CATACOMBS_BRICKS).registryKey(Keys.SHADOW_CATACOMBS_GLYPH_BLOCK).luminance((state) -> 9)));

    //trophies
    public static final Block MUD_CYCLE_MAGE_TROPHY = register(Keys.MUD_CYCLE_MAGE_TROPHY.getValue().getPath(), new BottomSlabLikeTrophyBlock(AbstractBlock.Settings.copy(LUNATIC_STONE).registryKey(Keys.MUD_CYCLE_MAGE_TROPHY).requiresTool()));
    public static final Block LUNAR_PRIEST_TROPHY = register(Keys.LUNAR_PRIEST_TROPHY.getValue().getPath(), new BottomSlabLikeTrophyBlock(AbstractBlock.Settings.copy(LUNATIC_STONE).registryKey(Keys.LUNAR_PRIEST_TROPHY).requiresTool()));
    public static final Block LILITH_TROPHY = register(Keys.LILITH_TROPHY.getValue().getPath(), new BottomSlabLikeTrophyBlock(AbstractBlock.Settings.copy(LUNATIC_STONE).registryKey(Keys.LILITH_TROPHY).requiresTool()));
    public static final Block CHAINED_GOD_TROPHY = register(Keys.CHAINED_GOD_TROPHY.getValue().getPath(), new BottomSlabLikeTrophyBlock(AbstractBlock.Settings.copy(GOLDEN_NETHER_BRICKS).registryKey(Keys.CHAINED_GOD_TROPHY).requiresTool()));

    //ores
    public static final Block IRON_STELLAR_ORE = register(Keys.IRON_STELLAR_ORE.getValue().getPath(), new AerialHellOreBlock(0, 2, AbstractBlock.Settings.create().registryKey(Keys.IRON_STELLAR_ORE).strength(3.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block GOLD_STELLAR_ORE = register(Keys.GOLD_STELLAR_ORE.getValue().getPath(), new AerialHellOreBlock(0, 2, AbstractBlock.Settings.create().registryKey(Keys.GOLD_STELLAR_ORE).strength(3.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block DIAMOND_STELLAR_ORE = register(Keys.DIAMOND_STELLAR_ORE.getValue().getPath(), new AerialHellOreBlock(3, 5, AbstractBlock.Settings.create().registryKey(Keys.DIAMOND_STELLAR_ORE).strength(5.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block FLUORITE_ORE = register(Keys.FLUORITE_ORE.getValue().getPath(), new BiomeShifterOreBlock(0, 2, AbstractBlock.Settings.create().registryKey(Keys.FLUORITE_ORE).strength(3.0F).sounds(BlockSoundGroup.STONE).requiresTool(), 2, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.SMOKY_QUARTZ_ORE));
    public static final Block MAGMATIC_GEL_ORE = register(Keys.MAGMATIC_GEL_ORE.getValue().getPath(), new MagmaticGelOreBlock(0, 2, AbstractBlock.Settings.create().registryKey(Keys.MAGMATIC_GEL_ORE).strength(3.0F).sounds(BlockSoundGroup.STONE).luminance(s -> 4).requiresTool()));
    public static final Block RUBY_ORE = register(Keys.RUBY_ORE.getValue().getPath(), new AerialHellOreBlock(0, 0, AbstractBlock.Settings.create().registryKey(Keys.RUBY_ORE).strength(3.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block AZURITE_ORE = register(Keys.AZURITE_ORE.getValue().getPath(), new AerialHellOreBlock(0, 0, AbstractBlock.Settings.create().registryKey(Keys.AZURITE_ORE).strength(3.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block VOLUCITE_ORE = register(Keys.VOLUCITE_ORE.getValue().getPath(), new AerialHellOreBlock(0, 0, AbstractBlock.Settings.create().registryKey(Keys.VOLUCITE_ORE).strength(5.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block OBSIDIAN_ORE = register(Keys.OBSIDIAN_ORE.getValue().getPath(), new AerialHellOreBlock(0, 0, AbstractBlock.Settings.create().registryKey(Keys.OBSIDIAN_ORE).strength(5.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static final Block SMOKY_QUARTZ_ORE = register(Keys.SMOKY_QUARTZ_ORE.getValue().getPath(), new AerialHellOreBlock(1, 3, AbstractBlock.Settings.create().registryKey(Keys.SMOKY_QUARTZ_ORE).strength(3.0F).sounds(BlockSoundGroup.STONE).requiresTool()));

    public static final Block RAW_RUBY_BLOCK = register(Keys.RAW_RUBY_BLOCK.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.RAW_IRON_BLOCK).registryKey(Keys.RAW_RUBY_BLOCK).requiresTool()));
    public static final Block RAW_AZURITE_BLOCK = register(Keys.RAW_AZURITE_BLOCK.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.RAW_IRON_BLOCK).registryKey(Keys.RAW_AZURITE_BLOCK).requiresTool()));
    public static final Block RAW_VOLUCITE_BLOCK = register(Keys.RAW_VOLUCITE_BLOCK.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK).registryKey(Keys.RAW_VOLUCITE_BLOCK).requiresTool()));

    public static final Block FLUORITE_BLOCK = register(Keys.FLUORITE_BLOCK.getValue().getPath(), new BiomeShifterBlock(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).registryKey(Keys.FLUORITE_BLOCK).requiresTool(), 7, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.SMOKY_QUARTZ_BLOCK));
    public static final Block MAGMATIC_GEL_BLOCK = register(Keys.MAGMATIC_GEL_BLOCK.getValue().getPath(), new MagmaticGelBlock(AbstractBlock.Settings.create().registryKey(Keys.MAGMATIC_GEL_BLOCK).strength(1.0F, 1600.0F).ticksRandomly().sounds(BlockSoundGroup.GLASS).nonOpaque().requiresTool().blockVision((state, reader, pos) -> false)));
    public static final Block RUBY_BLOCK = register(Keys.RUBY_BLOCK.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).registryKey(Keys.RUBY_BLOCK).requiresTool()));
    public static final Block AZURITE_BLOCK = register(Keys.AZURITE_BLOCK.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).registryKey(Keys.AZURITE_BLOCK).requiresTool()));
    public static final Block VOLUCITE_BLOCK = register(Keys.VOLUCITE_BLOCK.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.DIAMOND_BLOCK).registryKey(Keys.VOLUCITE_BLOCK).requiresTool()));

    //legendary ores blocks
    public static final Block ARSONIST_BLOCK = register(Keys.ARSONIST_BLOCK.getValue().getPath(), new ArsonistBlock(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK).registryKey(Keys.ARSONIST_BLOCK).requiresTool().luminance((state) -> 9)));
    public static final Block LUNATIC_CRYSTAL_BLOCK = register(Keys.LUNATIC_CRYSTAL_BLOCK.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK).registryKey(Keys.LUNATIC_CRYSTAL_BLOCK).sounds(BlockSoundGroup.GLASS).requiresTool().luminance((state) -> 9)));
    public static final Block CURSED_CRYSAL_BLOCK = register(Keys.CURSED_CRYSAL_BLOCK.getValue().getPath(), new Block(AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK).registryKey(Keys.CURSED_CRYSAL_BLOCK).sounds(BlockSoundGroup.GLASS).requiresTool().luminance((state) -> 9)));

    //cactus
    public static final SkyCactusBlock SKY_CACTUS = register(Keys.SKY_CACTUS.getValue().getPath(), new SkyCactusBlock(AbstractBlock.Settings.create().registryKey(Keys.SKY_CACTUS).mapColor(MapColor.CYAN).strength(0.4F).sounds(BlockSoundGroup.WOOL).ticksRandomly()));
    public static final Block SKY_CACTUS_FIBER_PLANKS = register(Keys.SKY_CACTUS_FIBER_PLANKS.getValue().getPath(), new Block(SKY_CACTUS_FIBER_MATERIAL.registryKey(Keys.SKY_CACTUS_FIBER_PLANKS)));
    public static final Block SKY_CACTUS_FIBER_BOOKSHELF = register(Keys.SKY_CACTUS_FIBER_BOOKSHELF.getValue().getPath(), new Block(AbstractBlock.Settings.copy(SKY_CACTUS_FIBER_PLANKS).registryKey(Keys.SKY_CACTUS_FIBER_BOOKSHELF)));
    public static final SkyCactusBlock VIBRANT_SKY_CACTUS = register(Keys.VIBRANT_SKY_CACTUS.getValue().getPath(), new SkyCactusBlock(AbstractBlock.Settings.create().registryKey(Keys.VIBRANT_SKY_CACTUS).mapColor(MapColor.BLUE).strength(0.4F).sounds(BlockSoundGroup.WOOL).ticksRandomly().luminance(s -> 15).nonOpaque()));
    public static final Block VIBRANT_SKY_CACTUS_FIBER_LANTERN = register(Keys.VIBRANT_SKY_CACTUS_FIBER_LANTERN.getValue().getPath(), new Block(AbstractBlock.Settings.create().registryKey(Keys.VIBRANT_SKY_CACTUS_FIBER_LANTERN).mapColor(MapColor.WHITE).strength(0.5F).sounds(BlockSoundGroup.GLASS).nonOpaque().luminance(s -> 15)));

    //bushes
    public static final Block AERIAL_BERRY_BUSH = register(Keys.AERIAL_BERRY_BUSH.getValue().getPath(), new AerialBerryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH).registryKey(Keys.AERIAL_BERRY_BUSH)));
    public static final Block VIBRANT_AERIAL_BERRY_BUSH = register(Keys.VIBRANT_AERIAL_BERRY_BUSH.getValue().getPath(), new VibrantAerialBerryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH).registryKey(Keys.VIBRANT_AERIAL_BERRY_BUSH)));

    //crops
    public static final Block STELLAR_WHEAT = register(Keys.STELLAR_WHEAT.getValue().getPath(), new StellarCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT).registryKey(Keys.STELLAR_WHEAT)));
    public static final Block BLUE_MEANIE_CROP = register(Keys.BLUE_MEANIE_CROP.getValue().getPath(), new StellarCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT).registryKey(Keys.BLUE_MEANIE_CROP)));

    //vertical growing plants
    public static final VerticalGrowingPlantBlock CLIMBING_VINE = register(Keys.CLIMBING_VINE.getValue().getPath(), new VerticalGrowingPlantBlock(AbstractBlock.Settings.copy(Blocks.SUGAR_CANE).registryKey(Keys.CLIMBING_VINE), 4));
    public static final VerticalGrowingPlantBlock STELLAR_SUGAR_CANE = register(Keys.STELLAR_SUGAR_CANE.getValue().getPath(), new VerticalGrowingPlantBlock(AbstractBlock.Settings.copy(Blocks.SUGAR_CANE).registryKey(Keys.STELLAR_SUGAR_CANE), 5));

    //chorus like
    public static final ChorusPlantLikeBlock FULL_MOON_PLANT = register(Keys.FULL_MOON_PLANT.getValue().getPath(), new ChorusPlantLikeBlock(AbstractBlock.Settings.create().registryKey(Keys.FULL_MOON_PLANT).mapColor(MapColor.PURPLE).notSolid().strength(0.4F).sounds(BlockSoundGroup.WOOD).nonOpaque().pistonBehavior(PistonBehavior.DESTROY).luminance((state) -> 10)));
    public static final ChorusFlowerLikeBlock FULL_MOON_FLOWER = register(Keys.FULL_MOON_FLOWER.getValue().getPath(), new ChorusFlowerLikeBlock(FULL_MOON_PLANT, AbstractBlock.Settings.create().registryKey(Keys.FULL_MOON_FLOWER).mapColor(MapColor.PURPLE).notSolid().ticksRandomly().strength(0.4F).sounds(BlockSoundGroup.WOOD).nonOpaque().allowsSpawning((state, blockGetter, pos, entitytype) -> false).pistonBehavior(PistonBehavior.DESTROY).solidBlock((state, blockGetter, pos) -> false).luminance((state) -> 15)));

    //vines
    public static final CaveVinesHeadBlock GLOWING_STICK_FRUIT_VINES = register(Keys.GLOWING_STICK_FRUIT_VINES.getValue().getPath(), new AerialHellCaveVinesBlock(AbstractBlock.Settings.copy(Blocks.CAVE_VINES).registryKey(Keys.GLOWING_STICK_FRUIT_VINES)));
    public static final CaveVinesBodyBlock GLOWING_STICK_FRUIT_VINES_PLANT = register(Keys.GLOWING_STICK_FRUIT_VINES_PLANT.getValue().getPath(), new AerialHellCaveVinesPlantBlock(AbstractBlock.Settings.copy(Blocks.CAVE_VINES_PLANT).registryKey(Keys.GLOWING_STICK_FRUIT_VINES_PLANT)));
    public static final CaveVinesHeadBlock BLOSSOMING_VINES = register(Keys.BLOSSOMING_VINES.getValue().getPath(), new AerialHellCaveVinesBlock(AbstractBlock.Settings.create().registryKey(Keys.BLOSSOMING_VINES).ticksRandomly().noCollision().breakInstantly().sounds(BlockSoundGroup.CAVE_VINES)));
    public static final CaveVinesBodyBlock BLOSSOMING_VINES_PLANT = register(Keys.BLOSSOMING_VINES_PLANT.getValue().getPath(), new AerialHellCaveVinesPlantBlock(AbstractBlock.Settings.copy(BLOSSOMING_VINES).registryKey(Keys.BLOSSOMING_VINES_PLANT)));
    public static final AerialHellTwistingVinesBlock LAZULI_ROOTS = register(Keys.LAZULI_ROOTS.getValue().getPath(), new AerialHellTwistingVinesBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES).registryKey(Keys.LAZULI_ROOTS)));
    public static final AerialHellTwistingVinesPlantBlock LAZULI_ROOTS_PLANT = register(Keys.LAZULI_ROOTS_PLANT.getValue().getPath(), new AerialHellTwistingVinesPlantBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES_PLANT).registryKey(Keys.LAZULI_ROOTS_PLANT)));
    public static final AerialHellTwistingVinesBlock STELLAR_ROOTS = register(Keys.STELLAR_ROOTS.getValue().getPath(), new AerialHellTwistingVinesBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES).registryKey(Keys.STELLAR_ROOTS)));
    public static final AerialHellTwistingVinesPlantBlock STELLAR_ROOTS_PLANT = register(Keys.STELLAR_ROOTS_PLANT.getValue().getPath(), new AerialHellTwistingVinesPlantBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES_PLANT).registryKey(Keys.STELLAR_ROOTS_PLANT)));
    public static final AerialHellTwistingVinesBlock DEAD_ROOTS = register(Keys.DEAD_ROOTS.getValue().getPath(), new DeadRootsBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES).registryKey(Keys.DEAD_ROOTS)));
    public static final AerialHellTwistingVinesPlantBlock DEAD_ROOTS_PLANT = register(Keys.DEAD_ROOTS_PLANT.getValue().getPath(), new DeadRootsPlantBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES_PLANT).registryKey(Keys.DEAD_ROOTS_PLANT)));
    public static final AerialHellTwistingVinesBlock GLOWING_ROOTS = register(Keys.GLOWING_ROOTS.getValue().getPath(), new AerialHellTwistingVinesBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES).luminance((state) -> 9).registryKey(Keys.GLOWING_ROOTS)));
    public static final AerialHellTwistingVinesPlantBlock GLOWING_ROOTS_PLANT = register(Keys.GLOWING_ROOTS_PLANT.getValue().getPath(), new AerialHellTwistingVinesPlantBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES_PLANT).registryKey(Keys.GLOWING_ROOTS_PLANT).luminance((state) -> 14)));
    public static final AerialHellTwistingVinesBlock SHADOW_GLOWING_ROOTS = register(Keys.SHADOW_GLOWING_ROOTS.getValue().getPath(), new AerialHellTwistingVinesBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES).registryKey(Keys.SHADOW_GLOWING_ROOTS).luminance((state) -> 8)));
    public static final AerialHellTwistingVinesPlantBlock SHADOW_GLOWING_ROOTS_PLANT = register(Keys.SHADOW_GLOWING_ROOTS_PLANT.getValue().getPath(), new AerialHellTwistingVinesPlantBlock(AbstractBlock.Settings.copy(Blocks.TWISTING_VINES_PLANT).registryKey(Keys.SHADOW_GLOWING_ROOTS_PLANT).luminance((state) -> 13)));

    //grass
    public static final Block STELLAR_GRASS = register(Keys.STELLAR_GRASS.getValue().getPath(), new ShiftableRenderTallGrassBlock(AbstractBlock.Settings.create().registryKey(Keys.STELLAR_GRASS).mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block STELLAR_GRASS_BALL = register(Keys.STELLAR_GRASS_BALL.getValue().getPath(), new ShiftableRenderTallGrassBlock(AbstractBlock.Settings.create().registryKey(Keys.STELLAR_GRASS_BALL).mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block STELLAR_FERN = register(Keys.STELLAR_FERN.getValue().getPath(), new AerialHellTallGrassBlock(AbstractBlock.Settings.create().registryKey(Keys.STELLAR_FERN).mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block STELLAR_TALL_GRASS = register(Keys.STELLAR_TALL_GRASS.getValue().getPath(), new TallPlantBlock(AbstractBlock.Settings.create().registryKey(Keys.STELLAR_TALL_GRASS).mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block STELLAR_TALL_FERN = register(Keys.STELLAR_TALL_FERN.getValue().getPath(), new TallPlantBlock(AbstractBlock.Settings.create().registryKey(Keys.STELLAR_TALL_FERN).mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final VerticalGrowingPlantBlock STELLAR_VERY_TALL_GRASS = register(Keys.STELLAR_VERY_TALL_GRASS.getValue().getPath(), new VerticalGrowingPlantBlock(AbstractBlock.Settings.copy(Blocks.SUGAR_CANE).registryKey(Keys.STELLAR_VERY_TALL_GRASS), 3));
    public static final Block BLUISH_FERN = register(Keys.BLUISH_FERN.getValue().getPath(), new AerialHellTallGrassBlock(AbstractBlock.Settings.create().registryKey(Keys.BLUISH_FERN).mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block TALL_BLUISH_FERN = register(Keys.TALL_BLUISH_FERN.getValue().getPath(), new TallPlantBlock(AbstractBlock.Settings.create().registryKey(Keys.TALL_BLUISH_FERN).mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block POLYCHROME_FERN = register(Keys.POLYCHROME_FERN.getValue().getPath(), new AerialHellTallGrassBlock(AbstractBlock.Settings.create().registryKey(Keys.POLYCHROME_FERN).mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block TALL_POLYCHROME_FERN = register(Keys.TALL_POLYCHROME_FERN.getValue().getPath(), new TallPlantBlock(AbstractBlock.Settings.create().registryKey(Keys.TALL_POLYCHROME_FERN).mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block STELLAR_DEAD_BUSH = register(Keys.STELLAR_DEAD_BUSH.getValue().getPath(), new AerialHellDeadBushBlock(AbstractBlock.Settings.create().registryKey(Keys.STELLAR_DEAD_BUSH).mapColor(MapColor.GREEN).replaceable().mapColor(MapColor.BROWN).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block BRAMBLES = register(Keys.BRAMBLES.getValue().getPath(), new BramblesBlock(AbstractBlock.Settings.create().registryKey(Keys.BRAMBLES).mapColor(MapColor.GREEN).noCollision().strength(0.5F).sounds(BlockSoundGroup.GRASS)));
    public static final Block SHADOW_BRAMBLES = register(Keys.SHADOW_BRAMBLES.getValue().getPath(), new BramblesBlock(AbstractBlock.Settings.create().registryKey(Keys.SHADOW_BRAMBLES).mapColor(MapColor.GREEN).noCollision().strength(0.5F).sounds(BlockSoundGroup.GRASS)));
    public static final Block SHADOW_GRASS = register(Keys.SHADOW_GRASS.getValue().getPath(), new ShadowPlantBlock(AbstractBlock.Settings.create().registryKey(Keys.SHADOW_GRASS).mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block SHADOW_GRASS_BALL = register(Keys.SHADOW_GRASS_BALL.getValue().getPath(), new ShadowPlantBlock(AbstractBlock.Settings.create().registryKey(Keys.SHADOW_GRASS_BALL).mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block PURPLISH_STELLAR_GRASS = register(Keys.PURPLISH_STELLAR_GRASS.getValue().getPath(), new AerialHellTallGrassBlock(AbstractBlock.Settings.create().registryKey(Keys.PURPLISH_STELLAR_GRASS).mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block STELLAR_CLOVERS = register(Keys.STELLAR_CLOVERS.getValue().getPath(), new AerialHellTallGrassBlock(AbstractBlock.Settings.create().registryKey(Keys.STELLAR_CLOVERS).mapColor(MapColor.GREEN).replaceable().noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block GLOWING_STELLAR_GRASS = register(Keys.GLOWING_STELLAR_GRASS.getValue().getPath(), new GlowingStellarTallGrass(AbstractBlock.Settings.create().registryKey(Keys.GLOWING_STELLAR_GRASS).mapColor(MapColor.GREEN).replaceable().ticksRandomly().noCollision().luminance((state) -> {return state.get(Properties.LIT) ? 10 : 0;}).breakInstantly().sounds(BlockSoundGroup.GRASS)));

    //flowers
    public static final Block BLUE_FLOWER = register(Keys.BLUE_FLOWER.getValue().getPath(), new FlowerBlock(StatusEffects.BLINDNESS, 4, AbstractBlock.Settings.copy(Blocks.DANDELION).registryKey(Keys.BLUE_FLOWER)));
    public static final Block BLACK_ROSE = register(Keys.BLACK_ROSE.getValue().getPath(), new FlowerBlock(StatusEffects.SLOW_FALLING, 12, AbstractBlock.Settings.copy(Blocks.DANDELION).registryKey(Keys.BLACK_ROSE)));
    public static final Block BELLFLOWER = register(Keys.BELLFLOWER.getValue().getPath(), new FlowerBlock(StatusEffects.MINING_FATIGUE, 12, AbstractBlock.Settings.copy(Blocks.DANDELION).registryKey(Keys.BELLFLOWER)));

    //potted things
    public static final FlowerPotBlock POTTED_BLUE_FLOWER = register(Keys.POTTED_BLUE_FLOWER.getValue().getPath(), new FlowerPotBlock(BLUE_FLOWER, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).registryKey(Keys.POTTED_BLUE_FLOWER)));
    public static final FlowerPotBlock POTTED_BLACK_ROSE = register(Keys.POTTED_BLACK_ROSE.getValue().getPath(), new FlowerPotBlock(BLACK_ROSE, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).registryKey(Keys.POTTED_BLACK_ROSE)));
    public static final FlowerPotBlock POTTED_BELLFLOWER = register(Keys.POTTED_BELLFLOWER.getValue().getPath(), new FlowerPotBlock(BELLFLOWER, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).registryKey(Keys.POTTED_BELLFLOWER)));
    public static final FlowerPotBlock POTTED_STELLAR_FERN = register(Keys.POTTED_STELLAR_FERN.getValue().getPath(), new FlowerPotBlock(STELLAR_FERN, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).registryKey(Keys.POTTED_STELLAR_FERN)));
    public static final FlowerPotBlock POTTED_STELLAR_DEAD_BUSH = register(Keys.POTTED_STELLAR_DEAD_BUSH.getValue().getPath(), new FlowerPotBlock(STELLAR_DEAD_BUSH, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).registryKey(Keys.POTTED_STELLAR_DEAD_BUSH)));
    public static final FlowerPotBlock POTTED_SKY_CACTUS = register(Keys.POTTED_SKY_CACTUS.getValue().getPath(), new FlowerPotBlock(SKY_CACTUS, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).registryKey(Keys.POTTED_SKY_CACTUS)));
    public static final FlowerPotBlock POTTED_VIBRANT_SKY_CACTUS = register(Keys.POTTED_VIBRANT_SKY_CACTUS.getValue().getPath(), new FlowerPotBlock(VIBRANT_SKY_CACTUS, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).registryKey(Keys.POTTED_VIBRANT_SKY_CACTUS)));
    public static final FlowerPotBlock POTTED_AERIAL_TREE_SAPLING = register(Keys.POTTED_AERIAL_TREE_SAPLING.getValue().getPath(), new FlowerPotBlock(AERIAL_TREE_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).registryKey(Keys.POTTED_AERIAL_TREE_SAPLING)));
    public static final FlowerPotBlock POTTED_GOLDEN_BEECH_SAPLING = register(Keys.POTTED_GOLDEN_BEECH_SAPLING.getValue().getPath(), new FlowerPotBlock(GOLDEN_BEECH_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).registryKey(Keys.POTTED_GOLDEN_BEECH_SAPLING)));
    public static final FlowerPotBlock POTTED_COPPER_PINE_SAPLING = register(Keys.POTTED_COPPER_PINE_SAPLING.getValue().getPath(), new FlowerPotBlock(COPPER_PINE_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).registryKey(Keys.POTTED_COPPER_PINE_SAPLING)));
    public static final FlowerPotBlock POTTED_LAPIS_ROBINIA_SAPLING = register(Keys.POTTED_LAPIS_ROBINIA_SAPLING.getValue().getPath(), new FlowerPotBlock(LAPIS_ROBINIA_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).registryKey(Keys.POTTED_LAPIS_ROBINIA_SAPLING)));
    public static final FlowerPotBlock POTTED_SHADOW_PINE_SAPLING = register(Keys.POTTED_SHADOW_PINE_SAPLING.getValue().getPath(), new FlowerPotBlock(SHADOW_PINE_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).registryKey(Keys.POTTED_SHADOW_PINE_SAPLING)));
    public static final FlowerPotBlock POTTED_PURPLE_SHADOW_PINE_SAPLING = register(Keys.POTTED_PURPLE_SHADOW_PINE_SAPLING.getValue().getPath(), new FlowerPotBlock(PURPLE_SHADOW_PINE_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).registryKey(Keys.POTTED_PURPLE_SHADOW_PINE_SAPLING)));
    public static final FlowerPotBlock POTTED_STELLAR_JUNGLE_TREE_SAPLING = register(Keys.POTTED_STELLAR_JUNGLE_TREE_SAPLING.getValue().getPath(), new FlowerPotBlock(STELLAR_JUNGLE_TREE_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).registryKey(Keys.POTTED_STELLAR_JUNGLE_TREE_SAPLING)));
    public static final FlowerPotBlock POTTED_CORTINARIUS_VIOLACEUS = register(Keys.POTTED_CORTINARIUS_VIOLACEUS.getValue().getPath(), new FlowerPotBlock(CORTINARIUS_VIOLACEUS, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).registryKey(Keys.POTTED_CORTINARIUS_VIOLACEUS)));
    public static final FlowerPotBlock POTTED_VERDIGRIS_AGARIC = register(Keys.POTTED_VERDIGRIS_AGARIC.getValue().getPath(), new FlowerPotBlock(VERDIGRIS_AGARIC, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).registryKey(Keys.POTTED_VERDIGRIS_AGARIC)));
    public static final FlowerPotBlock POTTED_VINE_BLOSSOM = register(Keys.POTTED_VINE_BLOSSOM.getValue().getPath(), new FlowerPotBlock(BLOSSOMING_VINES, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).registryKey(Keys.POTTED_VINE_BLOSSOM)));
    public static final FlowerPotBlock POTTED_GLOWING_BOLETUS = register(Keys.POTTED_GLOWING_BOLETUS.getValue().getPath(), new FlowerPotBlock(GLOWING_BOLETUS, AbstractBlock.Settings.copy(Blocks.FLOWER_POT).registryKey(Keys.POTTED_GLOWING_BOLETUS).luminance((state) -> 9)));

    //with gui
    public static final Block OSCILLATOR = register(Keys.OSCILLATOR.getValue().getPath(), new OscillatorBlock(AbstractBlock.Settings.create().registryKey(Keys.OSCILLATOR).strength(2.0F).sounds(BlockSoundGroup.STONE)));
    public static final Block FREEZER = register(Keys.FREEZER.getValue().getPath(), new FreezerBlock(AbstractBlock.Settings.create().registryKey(Keys.FREEZER).strength(2.0F).sounds(BlockSoundGroup.STONE)));
    public static final Block STELLAR_FURNACE = register(Keys.STELLAR_FURNACE.getValue().getPath(), new StellarFurnaceBlock(AbstractBlock.Settings.create().registryKey(Keys.STELLAR_FURNACE).requiresTool().strength(3.5F).luminance(getLightValueLit(13))));
    public static final Block GHOST_STELLAR_FURNACE = register(Keys.GHOST_STELLAR_FURNACE.getValue().getPath(), new GhostStellarFurnaceBlock(AbstractBlock.Settings.create().registryKey(Keys.GHOST_STELLAR_FURNACE).requiresTool().nonOpaque().strength(3.5F).luminance(getLightValueLit(13))));

    private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {return (state) -> {return state.get(Properties.LIT) ? lightValue : 0;};}

    //chests
    public static final ChestBlock AERIAL_TREE_CHEST = register(Keys.AERIAL_TREE_CHEST.getValue().getPath(), new AerialHellChestBlock(AERIAL_TREE_MATERIAL.registryKey(Keys.AERIAL_TREE_CHEST)));
    public static final ChestBlock GOLDEN_BEECH_CHEST = register(Keys.GOLDEN_BEECH_CHEST.getValue().getPath(), new AerialHellChestBlock(AERIAL_TREE_MATERIAL.registryKey(Keys.GOLDEN_BEECH_CHEST)));
    public static final ChestBlock COPPER_PINE_CHEST = register(Keys.COPPER_PINE_CHEST.getValue().getPath(), new AerialHellChestBlock(COPPER_PINE_MATERIAL.registryKey(Keys.COPPER_PINE_CHEST)));
    public static final ChestBlock LAPIS_ROBINIA_CHEST = register(Keys.LAPIS_ROBINIA_CHEST.getValue().getPath(), new AerialHellChestBlock(COPPER_PINE_MATERIAL.registryKey(Keys.LAPIS_ROBINIA_CHEST)));
    public static final ChestBlock SHADOW_PINE_CHEST = register(Keys.SHADOW_PINE_CHEST.getValue().getPath(), new AerialHellChestBlock(SHADOW_PINE_MATERIAL.registryKey(Keys.SHADOW_PINE_CHEST)));
    public static final ChestBlock STELLAR_JUNGLE_TREE_CHEST = register(Keys.STELLAR_JUNGLE_TREE_CHEST.getValue().getPath(), new AerialHellChestBlock(COPPER_PINE_MATERIAL.registryKey(Keys.STELLAR_JUNGLE_TREE_CHEST)));
    public static final ChestBlock SKY_CACTUS_FIBER_CHEST = register(Keys.SKY_CACTUS_FIBER_CHEST.getValue().getPath(), new AerialHellChestBlock(SKY_CACTUS_FIBER_MATERIAL.registryKey(Keys.SKY_CACTUS_FIBER_CHEST)));
    public static final ChestBlock GRAY_SHROOM_CHEST = register(Keys.GRAY_SHROOM_CHEST.getValue().getPath(), new AerialHellChestBlock(SHROOM_MATERIAL.registryKey(Keys.GRAY_SHROOM_CHEST)));
    public static final ChestBlock MUD_CHEST = register(Keys.MUD_CHEST.getValue().getPath(), new CoreProtectedChestBlock(MUD_CHEST_MATERIAL.registryKey(Keys.MUD_CHEST)));
    public static final ChestBlock LUNATIC_CHEST = register(Keys.LUNATIC_CHEST.getValue().getPath(), new CoreProtectedChestBlock(LUNATIC_CHEST_MATERIAL.registryKey(Keys.LUNATIC_CHEST)));
    public static final ChestBlock VOLUCITE_CHEST = register(Keys.VOLUCITE_CHEST.getValue().getPath(), new CoreProtectedChestBlock(VOLUCITE_CHEST_MATERIAL.registryKey(Keys.VOLUCITE_CHEST)));
    public static final ChestBlock SHADOW_CATACOMBS_CHEST = register(Keys.SHADOW_CATACOMBS_CHEST.getValue().getPath(), new CoreProtectedChestBlock(MUD_CHEST_MATERIAL.registryKey(Keys.SHADOW_CATACOMBS_CHEST)));
    public static final ChestBlock GOLDEN_NETHER_CHEST = register(Keys.GOLDEN_NETHER_CHEST.getValue().getPath(), new CoreProtectedChestBlock(GOLDEN_NETHER_CHEST_MATERIAL.registryKey(Keys.GOLDEN_NETHER_CHEST)));

    //chest mimics
    public static final Block AERIAL_TREE_CHEST_MIMIC = register(Keys.AERIAL_TREE_CHEST_MIMIC.getValue().getPath(), new ChestMimicBlock(AbstractBlock.Settings.copy(Blocks.CHEST).registryKey(Keys.AERIAL_TREE_CHEST_MIMIC)));
    public static final Block GOLDEN_BEECH_CHEST_MIMIC = register(Keys.GOLDEN_BEECH_CHEST_MIMIC.getValue().getPath(), new ChestMimicBlock(AbstractBlock.Settings.copy(Blocks.CHEST).registryKey(Keys.GOLDEN_BEECH_CHEST_MIMIC)));
    public static final Block COPPER_PINE_CHEST_MIMIC = register(Keys.COPPER_PINE_CHEST_MIMIC.getValue().getPath(), new ChestMimicBlock(AbstractBlock.Settings.copy(Blocks.CHEST).registryKey(Keys.COPPER_PINE_CHEST_MIMIC)));
    public static final Block SKY_CACTUS_FIBER_CHEST_MIMIC = register(Keys.SKY_CACTUS_FIBER_CHEST_MIMIC.getValue().getPath(), new ChestMimicBlock(AbstractBlock.Settings.copy(Blocks.CHEST).registryKey(Keys.SKY_CACTUS_FIBER_CHEST_MIMIC)));

    //barrel mimics
    public static final Block SHADOW_PINE_BARREL_MIMIC = register(Keys.SHADOW_PINE_BARREL_MIMIC.getValue().getPath(), new BarrelMimicBlock(AbstractBlock.Settings.copy(Blocks.BARREL).registryKey(Keys.SHADOW_PINE_BARREL_MIMIC)));

    //fences, bars or walls
    public static final FenceBlock AERIAL_TREE_FENCE = register(Keys.AERIAL_TREE_FENCE.getValue().getPath(), new FenceBlock(AERIAL_TREE_MATERIAL.registryKey(Keys.AERIAL_TREE_FENCE)));
    public static final FenceBlock GOLDEN_BEECH_FENCE = register(Keys.GOLDEN_BEECH_FENCE.getValue().getPath(), new FenceBlock(AERIAL_TREE_MATERIAL.registryKey(Keys.GOLDEN_BEECH_FENCE)));
    public static final FenceBlock COPPER_PINE_FENCE = register(Keys.COPPER_PINE_FENCE.getValue().getPath(), new FenceBlock(COPPER_PINE_MATERIAL.registryKey(Keys.COPPER_PINE_FENCE)));
    public static final FenceBlock LAPIS_ROBINIA_FENCE = register(Keys.LAPIS_ROBINIA_FENCE.getValue().getPath(), new FenceBlock(COPPER_PINE_MATERIAL.registryKey(Keys.LAPIS_ROBINIA_FENCE)));
    public static final FenceBlock SHADOW_PINE_FENCE = register(Keys.SHADOW_PINE_FENCE.getValue().getPath(), new FenceBlock(SHADOW_PINE_MATERIAL.registryKey(Keys.SHADOW_PINE_FENCE)));
    public static final FenceBlock STELLAR_JUNGLE_TREE_FENCE = register(Keys.STELLAR_JUNGLE_TREE_FENCE.getValue().getPath(), new FenceBlock(COPPER_PINE_MATERIAL.registryKey(Keys.STELLAR_JUNGLE_TREE_FENCE)));
    public static final FenceBlock SKY_CACTUS_FIBER_FENCE = register(Keys.SKY_CACTUS_FIBER_FENCE.getValue().getPath(), new FenceBlock(SKY_CACTUS_FIBER_MATERIAL.registryKey(Keys.SKY_CACTUS_FIBER_FENCE)));
    public static final FenceBlock GRAY_SHROOM_FENCE = register(Keys.GRAY_SHROOM_FENCE.getValue().getPath(), new FenceBlock(SHROOM_MATERIAL.registryKey(Keys.GRAY_SHROOM_FENCE)));
    public static final PaneBlock RUBY_BARS = register(Keys.RUBY_BARS.getValue().getPath(), new PaneBlock(METAL_NOTSOLID_MATERIAL.registryKey(Keys.RUBY_BARS)));
    public static final WallBlock STELLAR_STONE_WALL = register(Keys.STELLAR_STONE_WALL.getValue().getPath(), new WallBlock(AbstractBlock.Settings.copy(STELLAR_STONE).registryKey(Keys.STELLAR_STONE_WALL)));
    public static final WallBlock STELLAR_COBBLESTONE_WALL = register(Keys.STELLAR_COBBLESTONE_WALL.getValue().getPath(), new WallBlock(AbstractBlock.Settings.copy(STELLAR_COBBLESTONE).registryKey(Keys.STELLAR_COBBLESTONE_WALL)));
    public static final WallBlock STELLAR_STONE_BRICKS_WALL = register(Keys.STELLAR_STONE_BRICKS_WALL.getValue().getPath(), new WallBlock(AbstractBlock.Settings.copy(STELLAR_STONE_BRICKS).registryKey(Keys.STELLAR_STONE_BRICKS_WALL)));
    public static final WallBlock MOSSY_STELLAR_STONE_WALL = register(Keys.MOSSY_STELLAR_STONE_WALL.getValue().getPath(), new WallBlock(AbstractBlock.Settings.copy(MOSSY_STELLAR_STONE).registryKey(Keys.MOSSY_STELLAR_STONE_WALL)));
    public static final WallBlock MOSSY_STELLAR_COBBLESTONE_WALL = register(Keys.MOSSY_STELLAR_COBBLESTONE_WALL.getValue().getPath(), new WallBlock(AbstractBlock.Settings.copy(MOSSY_STELLAR_COBBLESTONE).registryKey(Keys.MOSSY_STELLAR_COBBLESTONE_WALL)));
    public static final WallBlock SLIPPERY_SAND_STONE_WALL = register(Keys.SLIPPERY_SAND_STONE_WALL.getValue().getPath(), new WallBlock(AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE).registryKey(Keys.SLIPPERY_SAND_STONE_WALL)));
    public static final WallBlock SLIPPERY_SAND_STONE_BRICKS_WALL = register(Keys.SLIPPERY_SAND_STONE_BRICKS_WALL.getValue().getPath(), new WallBlock(AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE_BRICKS).registryKey(Keys.SLIPPERY_SAND_STONE_BRICKS_WALL)));
    public static final WallBlock CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL = register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL.getValue().getPath(), new WallBlock(AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE_BRICKS).registryKey(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL)));
    public static final WallBlock GLAUCOPHANITE_WALL = register(Keys.GLAUCOPHANITE_WALL.getValue().getPath(), new WallBlock(AbstractBlock.Settings.copy(GLAUCOPHANITE).registryKey(Keys.GLAUCOPHANITE_WALL)));
    public static final WallBlock POLISHED_GLAUCOPHANITE_WALL = register(Keys.POLISHED_GLAUCOPHANITE_WALL.getValue().getPath(), new WallBlock(AbstractBlock.Settings.copy(POLISHED_GLAUCOPHANITE).registryKey(Keys.POLISHED_GLAUCOPHANITE_WALL)));
    public static final WallBlock MAGMATIC_GEL_WALL = register(Keys.MAGMATIC_GEL_WALL.getValue().getPath(), new WallBlock(AbstractBlock.Settings.copy(MAGMATIC_GEL_BLOCK).registryKey(Keys.MAGMATIC_GEL_WALL)));

    //gates
    public static final FenceGateBlock AERIAL_TREE_GATE = register(Keys.AERIAL_TREE_GATE.getValue().getPath(), new FenceGateBlock(AerialHellWoodTypes.AERIAL_TREE, AERIAL_TREE_MATERIAL.registryKey(Keys.AERIAL_TREE_GATE)));
    public static final FenceGateBlock GOLDEN_BEECH_GATE = register(Keys.GOLDEN_BEECH_GATE.getValue().getPath(), new FenceGateBlock(AerialHellWoodTypes.GOLDEN_BEECH, AERIAL_TREE_MATERIAL.registryKey(Keys.GOLDEN_BEECH_GATE)));
    public static final FenceGateBlock COPPER_PINE_GATE = register(Keys.COPPER_PINE_GATE.getValue().getPath(), new FenceGateBlock(AerialHellWoodTypes.COPPER_PINE, COPPER_PINE_MATERIAL.registryKey(Keys.COPPER_PINE_GATE)));
    public static final FenceGateBlock LAPIS_ROBINIA_GATE = register(Keys.LAPIS_ROBINIA_GATE.getValue().getPath(), new FenceGateBlock(AerialHellWoodTypes.LAPIS_ROBINIA, COPPER_PINE_MATERIAL.registryKey(Keys.LAPIS_ROBINIA_GATE)));
    public static final FenceGateBlock SHADOW_PINE_GATE = register(Keys.SHADOW_PINE_GATE.getValue().getPath(), new FenceGateBlock(AerialHellWoodTypes.SHADOW_PINE, SHADOW_PINE_MATERIAL.registryKey(Keys.SHADOW_PINE_GATE)));
    public static final FenceGateBlock STELLAR_JUNGLE_TREE_GATE = register(Keys.STELLAR_JUNGLE_TREE_GATE.getValue().getPath(), new FenceGateBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, COPPER_PINE_MATERIAL.registryKey(Keys.STELLAR_JUNGLE_TREE_GATE)));
    public static final FenceGateBlock SKY_CACTUS_FIBER_GATE = register(Keys.SKY_CACTUS_FIBER_GATE.getValue().getPath(), new FenceGateBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, SKY_CACTUS_FIBER_MATERIAL.registryKey(Keys.SKY_CACTUS_FIBER_GATE)));
    public static final FenceGateBlock GRAY_SHROOM_GATE = register(Keys.GRAY_SHROOM_GATE.getValue().getPath(), new FenceGateBlock(AerialHellWoodTypes.GRAY_SHROOM, SHROOM_MATERIAL.registryKey(Keys.GRAY_SHROOM_GATE)));

    //doors
    public static final DoorBlock AERIAL_TREE_DOOR = register(Keys.AERIAL_TREE_DOOR.getValue().getPath(), new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(AERIAL_TREE_PLANKS).registryKey(Keys.AERIAL_TREE_DOOR).nonOpaque()));
    public static final DoorBlock GOLDEN_BEECH_DOOR = register(Keys.GOLDEN_BEECH_DOOR.getValue().getPath(), new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(GOLDEN_BEECH_PLANKS).registryKey(Keys.GOLDEN_BEECH_DOOR).nonOpaque()));
    public static final DoorBlock COPPER_PINE_DOOR = register(Keys.COPPER_PINE_DOOR.getValue().getPath(), new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(COPPER_PINE_PLANKS).registryKey(Keys.COPPER_PINE_DOOR).nonOpaque()));
    public static final DoorBlock LAPIS_ROBINIA_DOOR = register(Keys.LAPIS_ROBINIA_DOOR.getValue().getPath(), new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(LAPIS_ROBINIA_PLANKS).registryKey(Keys.LAPIS_ROBINIA_DOOR).nonOpaque()));
    public static final DoorBlock SHADOW_PINE_DOOR = register(Keys.SHADOW_PINE_DOOR.getValue().getPath(), new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(SHADOW_PINE_PLANKS).registryKey(Keys.SHADOW_PINE_DOOR).nonOpaque()));
    public static final DoorBlock STELLAR_JUNGLE_TREE_DOOR = register(Keys.STELLAR_JUNGLE_TREE_DOOR.getValue().getPath(), new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_PLANKS).registryKey(Keys.STELLAR_JUNGLE_TREE_DOOR).nonOpaque()));
    public static final DoorBlock SKY_CACTUS_FIBER_DOOR = register(Keys.SKY_CACTUS_FIBER_DOOR.getValue().getPath(), new DoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(SKY_CACTUS_FIBER_PLANKS).registryKey(Keys.SKY_CACTUS_FIBER_DOOR).nonOpaque()));
    public static final DoorBlock GRAY_SHROOM_DOOR = register(Keys.GRAY_SHROOM_DOOR.getValue().getPath(), new DoorBlock(BlockSetType.CRIMSON, AbstractBlock.Settings.copy(GRAY_SHROOM_PLANKS).registryKey(Keys.GRAY_SHROOM_DOOR).nonOpaque()));
    public static final DoorBlock RUBY_DOOR = register(Keys.RUBY_DOOR.getValue().getPath(), new DoorBlock(BlockSetType.IRON, METAL_NOTSOLID_MATERIAL.registryKey(Keys.RUBY_DOOR)));

    //trapdoors
    public static final TrapdoorBlock AERIAL_TREE_TRAPDOOR = register(Keys.AERIAL_TREE_TRAPDOOR.getValue().getPath(), new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(AERIAL_TREE_PLANKS).registryKey(Keys.AERIAL_TREE_TRAPDOOR).nonOpaque()));
    public static final TrapdoorBlock GOLDEN_BEECH_TRAPDOOR = register(Keys.GOLDEN_BEECH_TRAPDOOR.getValue().getPath(), new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(GOLDEN_BEECH_PLANKS).registryKey(Keys.GOLDEN_BEECH_TRAPDOOR).nonOpaque()));
    public static final TrapdoorBlock COPPER_PINE_TRAPDOOR = register(Keys.COPPER_PINE_TRAPDOOR.getValue().getPath(), new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(COPPER_PINE_PLANKS).registryKey(Keys.COPPER_PINE_TRAPDOOR).nonOpaque()));
    public static final TrapdoorBlock LAPIS_ROBINIA_TRAPDOOR = register(Keys.LAPIS_ROBINIA_TRAPDOOR.getValue().getPath(), new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(LAPIS_ROBINIA_PLANKS).registryKey(Keys.LAPIS_ROBINIA_TRAPDOOR).nonOpaque()));
    public static final TrapdoorBlock SHADOW_PINE_TRAPDOOR = register(Keys.SHADOW_PINE_TRAPDOOR.getValue().getPath(), new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(SHADOW_PINE_PLANKS).registryKey(Keys.SHADOW_PINE_TRAPDOOR).nonOpaque()));
    public static final TrapdoorBlock STELLAR_JUNGLE_TREE_TRAPDOOR = register(Keys.STELLAR_JUNGLE_TREE_TRAPDOOR.getValue().getPath(), new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_PLANKS).registryKey(Keys.STELLAR_JUNGLE_TREE_TRAPDOOR).nonOpaque()));
    public static final TrapdoorBlock SKY_CACTUS_FIBER_TRAPDOOR = register(Keys.SKY_CACTUS_FIBER_TRAPDOOR.getValue().getPath(), new TrapdoorBlock(BlockSetType.OAK, AbstractBlock.Settings.copy(SKY_CACTUS_FIBER_PLANKS).registryKey(Keys.SKY_CACTUS_FIBER_TRAPDOOR).nonOpaque()));
    public static final TrapdoorBlock GRAY_SHROOM_TRAPDOOR = register(Keys.GRAY_SHROOM_TRAPDOOR.getValue().getPath(), new TrapdoorBlock(BlockSetType.CRIMSON, AbstractBlock.Settings.copy(GRAY_SHROOM_PLANKS).registryKey(Keys.GRAY_SHROOM_TRAPDOOR).nonOpaque()));
    public static final TrapdoorBlock RUBY_TRAPDOOR = register(Keys.RUBY_TRAPDOOR.getValue().getPath(), new TrapdoorBlock(BlockSetType.IRON, METAL_NOTSOLID_MATERIAL.registryKey(Keys.RUBY_TRAPDOOR)));

    //buttons
    public static final ButtonBlock STELLAR_STONE_BUTTON = register(Keys.STELLAR_STONE_BUTTON.getValue().getPath(), new ButtonBlock(BlockSetType.STONE, 20, AbstractBlock.Settings.copy(STELLAR_STONE).registryKey(Keys.STELLAR_STONE_BUTTON)));
    public static final ButtonBlock STELLAR_COBBLESTONE_BUTTON = register(Keys.STELLAR_COBBLESTONE_BUTTON.getValue().getPath(), new ButtonBlock(BlockSetType.STONE, 20, AbstractBlock.Settings.copy(STELLAR_COBBLESTONE).registryKey(Keys.STELLAR_COBBLESTONE_BUTTON)));
    public static final ButtonBlock STELLAR_STONE_BRICKS_BUTTON = register(Keys.STELLAR_STONE_BRICKS_BUTTON.getValue().getPath(), new ButtonBlock(BlockSetType.STONE, 20, AbstractBlock.Settings.copy(STELLAR_STONE_BRICKS).registryKey(Keys.STELLAR_STONE_BRICKS_BUTTON)));
    public static final ButtonBlock SLIPPERY_SAND_STONE_BUTTON = register(Keys.SLIPPERY_SAND_STONE_BUTTON.getValue().getPath(), new ButtonBlock(BlockSetType.STONE, 30, AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE).registryKey(Keys.SLIPPERY_SAND_STONE_BUTTON)));
    public static final ButtonBlock SLIPPERY_SAND_STONE_BRICKS_BUTTON = register(Keys.SLIPPERY_SAND_STONE_BRICKS_BUTTON.getValue().getPath(), new ButtonBlock(BlockSetType.OAK, 30, AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE_BRICKS).registryKey(Keys.SLIPPERY_SAND_STONE_BRICKS_BUTTON)));
    public static final ButtonBlock AERIAL_TREE_BUTTON = register(Keys.AERIAL_TREE_BUTTON.getValue().getPath(), new ButtonBlock(BlockSetType.OAK, 30, AERIAL_TREE_MATERIAL.registryKey(Keys.AERIAL_TREE_BUTTON)));
    public static final ButtonBlock GOLDEN_BEECH_BUTTON = register(Keys.GOLDEN_BEECH_BUTTON.getValue().getPath(), new ButtonBlock(BlockSetType.OAK, 30, AERIAL_TREE_MATERIAL.registryKey(Keys.GOLDEN_BEECH_BUTTON)));
    public static final ButtonBlock COPPER_PINE_BUTTON = register(Keys.COPPER_PINE_BUTTON.getValue().getPath(), new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL.registryKey(Keys.COPPER_PINE_BUTTON)));
    public static final ButtonBlock LAPIS_ROBINIA_BUTTON = register(Keys.LAPIS_ROBINIA_BUTTON.getValue().getPath(), new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL.registryKey(Keys.LAPIS_ROBINIA_BUTTON)));
    public static final ButtonBlock SHADOW_PINE_BUTTON = register(Keys.SHADOW_PINE_BUTTON.getValue().getPath(), new ButtonBlock(BlockSetType.OAK, 30, SHADOW_PINE_MATERIAL.registryKey(Keys.SHADOW_PINE_BUTTON)));
    public static final ButtonBlock STELLAR_JUNGLE_TREE_BUTTON = register(Keys.STELLAR_JUNGLE_TREE_BUTTON.getValue().getPath(), new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL.registryKey(Keys.STELLAR_JUNGLE_TREE_BUTTON)));
    public static final ButtonBlock SKY_CACTUS_FIBER_BUTTON = register(Keys.SKY_CACTUS_FIBER_BUTTON.getValue().getPath(), new ButtonBlock(BlockSetType.OAK, 30, SKY_CACTUS_FIBER_MATERIAL.registryKey(Keys.SKY_CACTUS_FIBER_BUTTON)));
    public static final ButtonBlock GRAY_SHROOM_BUTTON = register(Keys.GRAY_SHROOM_BUTTON.getValue().getPath(), new ButtonBlock(BlockSetType.CRIMSON, 30, SHROOM_MATERIAL.registryKey(Keys.GRAY_SHROOM_BUTTON)));
    public static final ButtonBlock GLAUCOPHANITE_BUTTON = register(Keys.GLAUCOPHANITE_BUTTON.getValue().getPath(), new ButtonBlock(BlockSetType.STONE, 20, AbstractBlock.Settings.copy(GLAUCOPHANITE).registryKey(Keys.GLAUCOPHANITE_BUTTON)));

    //pressure plates
    public static final PressurePlateBlock STELLAR_STONE_PRESSURE_PLATE = register(Keys.STELLAR_STONE_PRESSURE_PLATE.getValue().getPath(), new PressurePlateBlock(BlockSetType.STONE, AbstractBlock.Settings.copy(STELLAR_STONE).registryKey(Keys.STELLAR_STONE_PRESSURE_PLATE)));
    public static final PressurePlateBlock STELLAR_COBBLESTONE_PRESSURE_PLATE = register(Keys.STELLAR_COBBLESTONE_PRESSURE_PLATE.getValue().getPath(), new PressurePlateBlock(BlockSetType.STONE, AbstractBlock.Settings.copy(STELLAR_COBBLESTONE).registryKey(Keys.STELLAR_COBBLESTONE_PRESSURE_PLATE)));
    public static final PressurePlateBlock STELLAR_STONE_BRICKS_PRESSURE_PLATE = register(Keys.STELLAR_STONE_BRICKS_PRESSURE_PLATE.getValue().getPath(), new PressurePlateBlock(BlockSetType.STONE, AbstractBlock.Settings.copy(STELLAR_STONE_BRICKS).registryKey(Keys.STELLAR_STONE_BRICKS_PRESSURE_PLATE)));
    public static final PressurePlateBlock SLIPPERY_SAND_STONE_PRESSURE_PLATE = register(Keys.SLIPPERY_SAND_STONE_PRESSURE_PLATE.getValue().getPath(), new PressurePlateBlock(BlockSetType.STONE, AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE).registryKey(Keys.SLIPPERY_SAND_STONE_PRESSURE_PLATE)));
    public static final PressurePlateBlock SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE = register(Keys.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE.getValue().getPath(), new PressurePlateBlock(BlockSetType.STONE, AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE_BRICKS).registryKey(Keys.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE)));
    public static final PressurePlateBlock AERIAL_TREE_PRESSURE_PLATE = register(Keys.AERIAL_TREE_PRESSURE_PLATE.getValue().getPath(), new PressurePlateBlock(BlockSetType.OAK, AERIAL_TREE_MATERIAL.registryKey(Keys.AERIAL_TREE_PRESSURE_PLATE)));
    public static final PressurePlateBlock GOLDEN_BEECH_PRESSURE_PLATE = register(Keys.GOLDEN_BEECH_PRESSURE_PLATE.getValue().getPath(), new PressurePlateBlock(BlockSetType.OAK, AERIAL_TREE_MATERIAL.registryKey(Keys.GOLDEN_BEECH_PRESSURE_PLATE)));
    public static final PressurePlateBlock COPPER_PINE_PRESSURE_PLATE = register(Keys.COPPER_PINE_PRESSURE_PLATE.getValue().getPath(), new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL.registryKey(Keys.COPPER_PINE_PRESSURE_PLATE)));
    public static final PressurePlateBlock LAPIS_ROBINIA_PRESSURE_PLATE = register(Keys.LAPIS_ROBINIA_PRESSURE_PLATE.getValue().getPath(), new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL.registryKey(Keys.LAPIS_ROBINIA_PRESSURE_PLATE)));
    public static final PressurePlateBlock SHADOW_PINE_PRESSURE_PLATE = register(Keys.SHADOW_PINE_PRESSURE_PLATE.getValue().getPath(), new PressurePlateBlock(BlockSetType.OAK, SHADOW_PINE_MATERIAL.registryKey(Keys.SHADOW_PINE_PRESSURE_PLATE)));
    public static final PressurePlateBlock STELLAR_JUNGLE_TREE_PRESSURE_PLATE = register(Keys.STELLAR_JUNGLE_TREE_PRESSURE_PLATE.getValue().getPath(), new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL.registryKey(Keys.STELLAR_JUNGLE_TREE_PRESSURE_PLATE)));
    public static final PressurePlateBlock SKY_CACTUS_FIBER_PRESSURE_PLATE = register(Keys.SKY_CACTUS_FIBER_PRESSURE_PLATE.getValue().getPath(), new PressurePlateBlock(BlockSetType.OAK, SKY_CACTUS_FIBER_MATERIAL.registryKey(Keys.SKY_CACTUS_FIBER_PRESSURE_PLATE)));
    public static final PressurePlateBlock GRAY_SHROOM_PRESSURE_PLATE = register(Keys.GRAY_SHROOM_PRESSURE_PLATE.getValue().getPath(), new PressurePlateBlock(BlockSetType.CRIMSON, SHROOM_MATERIAL.registryKey(Keys.GRAY_SHROOM_PRESSURE_PLATE)));
    public static final PressurePlateBlock GLAUCOPHANITE_PRESSURE_PLATE = register(Keys.GLAUCOPHANITE_PRESSURE_PLATE.getValue().getPath(), new PressurePlateBlock(BlockSetType.STONE, AbstractBlock.Settings.copy(GLAUCOPHANITE).registryKey(Keys.GLAUCOPHANITE_PRESSURE_PLATE)));

    //slabs
    public static final SlabBlock AERIAL_TREE_SLAB = register(Keys.AERIAL_TREE_SLAB.getValue().getPath(), new SlabBlock(AERIAL_TREE_MATERIAL.registryKey(Keys.AERIAL_TREE_SLAB)));
    public static final SlabBlock GOLDEN_BEECH_SLAB = register(Keys.GOLDEN_BEECH_SLAB.getValue().getPath(), new SlabBlock(AERIAL_TREE_MATERIAL.registryKey(Keys.GOLDEN_BEECH_SLAB)));
    public static final SlabBlock COPPER_PINE_SLAB = register(Keys.COPPER_PINE_SLAB.getValue().getPath(), new SlabBlock(COPPER_PINE_MATERIAL.registryKey(Keys.COPPER_PINE_SLAB)));
    public static final SlabBlock LAPIS_ROBINIA_SLAB = register(Keys.LAPIS_ROBINIA_SLAB.getValue().getPath(), new SlabBlock(COPPER_PINE_MATERIAL.registryKey(Keys.LAPIS_ROBINIA_SLAB)));
    public static final SlabBlock SHADOW_PINE_SLAB = register(Keys.SHADOW_PINE_SLAB.getValue().getPath(), new SlabBlock(SHADOW_PINE_MATERIAL.registryKey(Keys.SHADOW_PINE_SLAB)));
    public static final SlabBlock STELLAR_JUNGLE_TREE_SLAB = register(Keys.STELLAR_JUNGLE_TREE_SLAB.getValue().getPath(), new SlabBlock(COPPER_PINE_MATERIAL.registryKey(Keys.STELLAR_JUNGLE_TREE_SLAB)));
    public static final SlabBlock SKY_CACTUS_FIBER_SLAB = register(Keys.SKY_CACTUS_FIBER_SLAB.getValue().getPath(), new SlabBlock(SKY_CACTUS_FIBER_MATERIAL.registryKey(Keys.SKY_CACTUS_FIBER_SLAB)));
    public static final SlabBlock GRAY_SHROOM_SLAB = register(Keys.GRAY_SHROOM_SLAB.getValue().getPath(), new SlabBlock(SHROOM_MATERIAL.registryKey(Keys.GRAY_SHROOM_SLAB)));
    public static final SlabBlock STELLAR_STONE_SLAB = register(Keys.STELLAR_STONE_SLAB.getValue().getPath(), new SlabBlock(AbstractBlock.Settings.copy(STELLAR_STONE).registryKey(Keys.STELLAR_STONE_SLAB)));
    public static final SlabBlock STELLAR_COBBLESTONE_SLAB = register(Keys.STELLAR_COBBLESTONE_SLAB.getValue().getPath(), new SlabBlock(AbstractBlock.Settings.copy(STELLAR_COBBLESTONE).registryKey(Keys.STELLAR_COBBLESTONE_SLAB)));
    public static final SlabBlock STELLAR_STONE_BRICKS_SLAB = register(Keys.STELLAR_STONE_BRICKS_SLAB.getValue().getPath(), new SlabBlock(AbstractBlock.Settings.copy(STELLAR_STONE_BRICKS).registryKey(Keys.STELLAR_STONE_BRICKS_SLAB)));
    public static final SlabBlock MOSSY_STELLAR_STONE_SLAB = register(Keys.MOSSY_STELLAR_STONE_SLAB.getValue().getPath(), new SlabBlock(AbstractBlock.Settings.copy(MOSSY_STELLAR_STONE).registryKey(Keys.MOSSY_STELLAR_STONE_SLAB)));
    public static final SlabBlock MOSSY_STELLAR_COBBLESTONE_SLAB = register(Keys.MOSSY_STELLAR_COBBLESTONE_SLAB.getValue().getPath(), new SlabBlock(AbstractBlock.Settings.copy(MOSSY_STELLAR_COBBLESTONE).registryKey(Keys.MOSSY_STELLAR_COBBLESTONE_SLAB)));
    public static final SlabBlock SLIPPERY_SAND_STONE_SLAB = register(Keys.SLIPPERY_SAND_STONE_SLAB.getValue().getPath(), new SlabBlock(AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE).registryKey(Keys.SLIPPERY_SAND_STONE_SLAB)));
    public static final SlabBlock SLIPPERY_SAND_STONE_BRICKS_SLAB = register(Keys.SLIPPERY_SAND_STONE_BRICKS_SLAB.getValue().getPath(), new SlabBlock(AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE_BRICKS).registryKey(Keys.SLIPPERY_SAND_STONE_BRICKS_SLAB)));
    public static final SlabBlock CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB = register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB.getValue().getPath(), new SlabBlock(AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE_BRICKS).registryKey(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB)));
    public static final SlabBlock POLISHED_GLAUCOPHANITE_SLAB = register(Keys.POLISHED_GLAUCOPHANITE_SLAB.getValue().getPath(), new SlabBlock(AbstractBlock.Settings.copy(POLISHED_GLAUCOPHANITE).registryKey(Keys.POLISHED_GLAUCOPHANITE_SLAB)));
    public static final SlabBlock MAGMATIC_GEL_SLAB = register(Keys.MAGMATIC_GEL_SLAB.getValue().getPath(), new SlabBlock(AbstractBlock.Settings.copy(MAGMATIC_GEL_BLOCK).registryKey(Keys.MAGMATIC_GEL_SLAB)));

    //stairs
    public static final StairsBlock AERIAL_TREE_STAIRS = register(Keys.AERIAL_TREE_STAIRS.getValue().getPath(), new StairsBlock(AERIAL_TREE_PLANKS.getDefaultState(), AERIAL_TREE_MATERIAL.registryKey(Keys.AERIAL_TREE_STAIRS)));
    public static final StairsBlock GOLDEN_BEECH_STAIRS = register(Keys.GOLDEN_BEECH_STAIRS.getValue().getPath(), new StairsBlock(GOLDEN_BEECH_PLANKS.getDefaultState(), AERIAL_TREE_MATERIAL.registryKey(Keys.GOLDEN_BEECH_STAIRS)));
    public static final StairsBlock COPPER_PINE_STAIRS = register(Keys.COPPER_PINE_STAIRS.getValue().getPath(), new StairsBlock(COPPER_PINE_PLANKS.getDefaultState(), COPPER_PINE_MATERIAL.registryKey(Keys.COPPER_PINE_STAIRS)));
    public static final StairsBlock LAPIS_ROBINIA_STAIRS = register(Keys.LAPIS_ROBINIA_STAIRS.getValue().getPath(), new StairsBlock(LAPIS_ROBINIA_PLANKS.getDefaultState(), COPPER_PINE_MATERIAL.registryKey(Keys.LAPIS_ROBINIA_STAIRS)));
    public static final StairsBlock SHADOW_PINE_STAIRS = register(Keys.SHADOW_PINE_STAIRS.getValue().getPath(), new StairsBlock(SHADOW_PINE_PLANKS.getDefaultState(), SHADOW_PINE_MATERIAL.registryKey(Keys.SHADOW_PINE_STAIRS)));
    public static final StairsBlock STELLAR_JUNGLE_TREE_STAIRS = register(Keys.STELLAR_JUNGLE_TREE_STAIRS.getValue().getPath(), new StairsBlock(STELLAR_JUNGLE_TREE_PLANKS.getDefaultState(), COPPER_PINE_MATERIAL.registryKey(Keys.STELLAR_JUNGLE_TREE_STAIRS)));
    public static final StairsBlock SKY_CACTUS_FIBER_STAIRS = register(Keys.SKY_CACTUS_FIBER_STAIRS.getValue().getPath(), new StairsBlock(SKY_CACTUS_FIBER_PLANKS.getDefaultState(), SKY_CACTUS_FIBER_MATERIAL.registryKey(Keys.SKY_CACTUS_FIBER_STAIRS)));
    public static final StairsBlock GRAY_SHROOM_STAIRS = register(Keys.GRAY_SHROOM_STAIRS.getValue().getPath(), new StairsBlock(GRAY_SHROOM_PLANKS.getDefaultState(), SHROOM_MATERIAL.registryKey(Keys.GRAY_SHROOM_STAIRS)));
    public static final StairsBlock STELLAR_STONE_STAIRS = register(Keys.STELLAR_STONE_STAIRS.getValue().getPath(), new StairsBlock(STELLAR_STONE.getDefaultState(), AbstractBlock.Settings.copy(STELLAR_STONE).registryKey(Keys.STELLAR_STONE_STAIRS)));
    public static final StairsBlock STELLAR_COBBLESTONE_STAIRS = register(Keys.STELLAR_COBBLESTONE_STAIRS.getValue().getPath(), new StairsBlock(STELLAR_COBBLESTONE.getDefaultState(), AbstractBlock.Settings.copy(STELLAR_COBBLESTONE).registryKey(Keys.STELLAR_COBBLESTONE_STAIRS)));
    public static final StairsBlock STELLAR_STONE_BRICKS_STAIRS = register(Keys.STELLAR_STONE_BRICKS_STAIRS.getValue().getPath(), new StairsBlock(STELLAR_STONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(STELLAR_STONE_BRICKS).registryKey(Keys.STELLAR_STONE_BRICKS_STAIRS)));
    public static final StairsBlock MOSSY_STELLAR_STONE_STAIRS = register(Keys.MOSSY_STELLAR_STONE_STAIRS.getValue().getPath(), new StairsBlock(MOSSY_STELLAR_STONE.getDefaultState(), AbstractBlock.Settings.copy(MOSSY_STELLAR_STONE).registryKey(Keys.MOSSY_STELLAR_STONE_STAIRS)));
    public static final StairsBlock MOSSY_STELLAR_COBBLESTONE_STAIRS = register(Keys.MOSSY_STELLAR_COBBLESTONE_STAIRS.getValue().getPath(), new StairsBlock(MOSSY_STELLAR_COBBLESTONE.getDefaultState(), AbstractBlock.Settings.copy(MOSSY_STELLAR_COBBLESTONE).registryKey(Keys.MOSSY_STELLAR_COBBLESTONE_STAIRS)));
    public static final StairsBlock SLIPPERY_SAND_STONE_STAIRS = register(Keys.SLIPPERY_SAND_STONE_STAIRS.getValue().getPath(), new StairsBlock(SLIPPERY_SAND_STONE.getDefaultState(), AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE).registryKey(Keys.SLIPPERY_SAND_STONE_STAIRS)));
    public static final StairsBlock SLIPPERY_SAND_STONE_BRICKS_STAIRS = register(Keys.SLIPPERY_SAND_STONE_BRICKS_STAIRS.getValue().getPath(), new StairsBlock(SLIPPERY_SAND_STONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE_BRICKS).registryKey(Keys.SLIPPERY_SAND_STONE_BRICKS_STAIRS)));
    public static final StairsBlock CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS = register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS.getValue().getPath(), new StairsBlock(SLIPPERY_SAND_STONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(SLIPPERY_SAND_STONE_BRICKS).registryKey(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS)));
    public static final StairsBlock POLISHED_GLAUCOPHANITE_STAIRS = register(Keys.POLISHED_GLAUCOPHANITE_STAIRS.getValue().getPath(), new StairsBlock(POLISHED_GLAUCOPHANITE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_GLAUCOPHANITE).registryKey(Keys.POLISHED_GLAUCOPHANITE_STAIRS)));
    public static final StairsBlock MAGMATIC_GEL_STAIRS = register(Keys.MAGMATIC_GEL_STAIRS.getValue().getPath(), new StairsBlock(MAGMATIC_GEL_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(MAGMATIC_GEL_BLOCK).registryKey(Keys.MAGMATIC_GEL_STAIRS)));

    //signs
    public static final AerialHellStandingSignBlock AERIAL_TREE_STANDING_SIGN = register(Keys.AERIAL_TREE_STANDING_SIGN.getValue().getPath(), new AerialHellStandingSignBlock(AERIAL_TREE_SIGN_MATERIAL.registryKey(Keys.AERIAL_TREE_STANDING_SIGN), AerialHellWoodTypes.AERIAL_TREE));
    public static final AerialHellWallSignBlock AERIAL_TREE_WALL_SIGN = register(Keys.AERIAL_TREE_WALL_SIGN.getValue().getPath(), new AerialHellWallSignBlock(AERIAL_TREE_SIGN_MATERIAL.registryKey(Keys.AERIAL_TREE_WALL_SIGN), AerialHellWoodTypes.AERIAL_TREE));
    public static final AerialHellStandingSignBlock GOLDEN_BEECH_STANDING_SIGN = register(Keys.GOLDEN_BEECH_STANDING_SIGN.getValue().getPath(), new AerialHellStandingSignBlock(AERIAL_TREE_SIGN_MATERIAL.registryKey(Keys.GOLDEN_BEECH_STANDING_SIGN), AerialHellWoodTypes.GOLDEN_BEECH));
    public static final AerialHellWallSignBlock GOLDEN_BEECH_WALL_SIGN = register(Keys.GOLDEN_BEECH_WALL_SIGN.getValue().getPath(), new AerialHellWallSignBlock(AERIAL_TREE_SIGN_MATERIAL.registryKey(Keys.GOLDEN_BEECH_WALL_SIGN), AerialHellWoodTypes.GOLDEN_BEECH));
    public static final AerialHellStandingSignBlock COPPER_PINE_STANDING_SIGN = register(Keys.COPPER_PINE_STANDING_SIGN.getValue().getPath(), new AerialHellStandingSignBlock(COPPER_PINE_SIGN_MATERIAL.registryKey(Keys.COPPER_PINE_STANDING_SIGN), AerialHellWoodTypes.COPPER_PINE));
    public static final AerialHellWallSignBlock COPPER_PINE_WALL_SIGN = register(Keys.COPPER_PINE_WALL_SIGN.getValue().getPath(), new AerialHellWallSignBlock(COPPER_PINE_SIGN_MATERIAL.registryKey(Keys.COPPER_PINE_WALL_SIGN), AerialHellWoodTypes.COPPER_PINE));
    public static final AerialHellStandingSignBlock LAPIS_ROBINIA_STANDING_SIGN = register(Keys.LAPIS_ROBINIA_STANDING_SIGN.getValue().getPath(), new AerialHellStandingSignBlock(COPPER_PINE_SIGN_MATERIAL.registryKey(Keys.LAPIS_ROBINIA_STANDING_SIGN), AerialHellWoodTypes.LAPIS_ROBINIA));
    public static final AerialHellWallSignBlock LAPIS_ROBINIA_WALL_SIGN = register(Keys.LAPIS_ROBINIA_WALL_SIGN.getValue().getPath(), new AerialHellWallSignBlock(COPPER_PINE_SIGN_MATERIAL.registryKey(Keys.LAPIS_ROBINIA_WALL_SIGN), AerialHellWoodTypes.LAPIS_ROBINIA));
    public static final AerialHellStandingSignBlock SHADOW_PINE_STANDING_SIGN = register(Keys.SHADOW_PINE_STANDING_SIGN.getValue().getPath(), new AerialHellStandingSignBlock(SHADOW_PINE_SIGN_MATERIAL.registryKey(Keys.SHADOW_PINE_STANDING_SIGN), AerialHellWoodTypes.SHADOW_PINE));
    public static final AerialHellWallSignBlock SHADOW_PINE_WALL_SIGN = register(Keys.SHADOW_PINE_WALL_SIGN.getValue().getPath(), new AerialHellWallSignBlock(SHADOW_PINE_SIGN_MATERIAL.registryKey(Keys.SHADOW_PINE_WALL_SIGN), AerialHellWoodTypes.SHADOW_PINE));
    public static final AerialHellStandingSignBlock STELLAR_JUNGLE_TREE_STANDING_SIGN = register(Keys.STELLAR_JUNGLE_TREE_STANDING_SIGN.getValue().getPath(), new AerialHellStandingSignBlock(COPPER_PINE_SIGN_MATERIAL.registryKey(Keys.STELLAR_JUNGLE_TREE_STANDING_SIGN), AerialHellWoodTypes.STELLAR_JUNGLE_TREE));
    public static final AerialHellWallSignBlock STELLAR_JUNGLE_TREE_WALL_SIGN = register(Keys.STELLAR_JUNGLE_TREE_WALL_SIGN.getValue().getPath(), new AerialHellWallSignBlock(COPPER_PINE_SIGN_MATERIAL.registryKey(Keys.STELLAR_JUNGLE_TREE_WALL_SIGN), AerialHellWoodTypes.STELLAR_JUNGLE_TREE));
    public static final AerialHellStandingSignBlock SKY_CACTUS_FIBER_STANDING_SIGN = register(Keys.SKY_CACTUS_FIBER_STANDING_SIGN.getValue().getPath(), new AerialHellStandingSignBlock(SKY_CACTUS_FIBER_SIGN_MATERIAL.registryKey(Keys.SKY_CACTUS_FIBER_STANDING_SIGN), AerialHellWoodTypes.SKY_CACTUS_FIBER));
    public static final AerialHellWallSignBlock SKY_CACTUS_FIBER_WALL_SIGN = register(Keys.SKY_CACTUS_FIBER_WALL_SIGN.getValue().getPath(), new AerialHellWallSignBlock(SKY_CACTUS_FIBER_SIGN_MATERIAL.registryKey(Keys.SKY_CACTUS_FIBER_WALL_SIGN), AerialHellWoodTypes.SKY_CACTUS_FIBER));
    public static final AerialHellStandingSignBlock GRAY_SHROOM_STANDING_SIGN = register(Keys.GRAY_SHROOM_STANDING_SIGN.getValue().getPath(), new AerialHellStandingSignBlock(SHROOM_SIGN_MATERIAL.registryKey(Keys.GRAY_SHROOM_STANDING_SIGN), AerialHellWoodTypes.GRAY_SHROOM));
    public static final AerialHellWallSignBlock GRAY_SHROOM_WALL_SIGN = register(Keys.GRAY_SHROOM_WALL_SIGN.getValue().getPath(), new AerialHellWallSignBlock(SHROOM_SIGN_MATERIAL.registryKey(Keys.GRAY_SHROOM_WALL_SIGN), AerialHellWoodTypes.GRAY_SHROOM));

    //hanging signs
    public static final HangingSignBlock AERIAL_TREE_HANGING_SIGN = register(Keys.AERIAL_TREE_HANGING_SIGN.getValue().getPath(), new AerialHellHangingSignBlock(AerialHellWoodTypes.AERIAL_TREE, AERIAL_TREE_SIGN_MATERIAL.registryKey(Keys.AERIAL_TREE_HANGING_SIGN)));
    public static final WallHangingSignBlock AERIAL_TREE_WALL_HANGING_SIGN = register(Keys.AERIAL_TREE_WALL_HANGING_SIGN.getValue().getPath(), new AerialHellWallHangingSignBlock(AerialHellWoodTypes.AERIAL_TREE, AbstractBlock.Settings.copy(AERIAL_TREE_HANGING_SIGN).registryKey(Keys.AERIAL_TREE_WALL_HANGING_SIGN).lootTable(AERIAL_TREE_HANGING_SIGN.getLootTableKey())));
    public static final HangingSignBlock GOLDEN_BEECH_HANGING_SIGN = register(Keys.GOLDEN_BEECH_HANGING_SIGN.getValue().getPath(), new AerialHellHangingSignBlock(AerialHellWoodTypes.GOLDEN_BEECH, AERIAL_TREE_SIGN_MATERIAL.registryKey(Keys.GOLDEN_BEECH_HANGING_SIGN)));
    public static final WallHangingSignBlock GOLDEN_BEECH_WALL_HANGING_SIGN = register(Keys.GOLDEN_BEECH_WALL_HANGING_SIGN.getValue().getPath(), new AerialHellWallHangingSignBlock(AerialHellWoodTypes.GOLDEN_BEECH, AbstractBlock.Settings.copy(GOLDEN_BEECH_HANGING_SIGN).registryKey(Keys.GOLDEN_BEECH_WALL_HANGING_SIGN).lootTable(GOLDEN_BEECH_HANGING_SIGN.getLootTableKey())));
    public static final HangingSignBlock COPPER_PINE_HANGING_SIGN = register(Keys.COPPER_PINE_HANGING_SIGN.getValue().getPath(), new AerialHellHangingSignBlock(AerialHellWoodTypes.COPPER_PINE, COPPER_PINE_SIGN_MATERIAL.registryKey(Keys.COPPER_PINE_HANGING_SIGN)));
    public static final WallHangingSignBlock COPPER_PINE_WALL_HANGING_SIGN = register(Keys.COPPER_PINE_WALL_HANGING_SIGN.getValue().getPath(), new AerialHellWallHangingSignBlock(AerialHellWoodTypes.COPPER_PINE, AbstractBlock.Settings.copy(COPPER_PINE_HANGING_SIGN).registryKey(Keys.COPPER_PINE_WALL_HANGING_SIGN).lootTable(COPPER_PINE_HANGING_SIGN.getLootTableKey())));
    public static final HangingSignBlock LAPIS_ROBINIA_HANGING_SIGN = register(Keys.LAPIS_ROBINIA_HANGING_SIGN.getValue().getPath(), new AerialHellHangingSignBlock(AerialHellWoodTypes.LAPIS_ROBINIA, COPPER_PINE_SIGN_MATERIAL.registryKey(Keys.LAPIS_ROBINIA_HANGING_SIGN)));
    public static final WallHangingSignBlock LAPIS_ROBINIA_WALL_HANGING_SIGN = register(Keys.LAPIS_ROBINIA_WALL_HANGING_SIGN.getValue().getPath(), new AerialHellWallHangingSignBlock(AerialHellWoodTypes.LAPIS_ROBINIA, AbstractBlock.Settings.copy(LAPIS_ROBINIA_HANGING_SIGN).registryKey(Keys.LAPIS_ROBINIA_WALL_HANGING_SIGN).lootTable(LAPIS_ROBINIA_HANGING_SIGN.getLootTableKey())));
    public static final HangingSignBlock SHADOW_PINE_HANGING_SIGN = register(Keys.SHADOW_PINE_HANGING_SIGN.getValue().getPath(), new AerialHellHangingSignBlock(AerialHellWoodTypes.SHADOW_PINE, SHADOW_PINE_SIGN_MATERIAL.registryKey(Keys.SHADOW_PINE_HANGING_SIGN)));
    public static final WallHangingSignBlock SHADOW_PINE_WALL_HANGING_SIGN = register(Keys.SHADOW_PINE_WALL_HANGING_SIGN.getValue().getPath(), new AerialHellWallHangingSignBlock(AerialHellWoodTypes.SHADOW_PINE, AbstractBlock.Settings.copy(SHADOW_PINE_HANGING_SIGN).registryKey(Keys.SHADOW_PINE_WALL_HANGING_SIGN).lootTable(SHADOW_PINE_HANGING_SIGN.getLootTableKey())));
    public static final HangingSignBlock STELLAR_JUNGLE_TREE_HANGING_SIGN = register(Keys.STELLAR_JUNGLE_TREE_HANGING_SIGN.getValue().getPath(), new AerialHellHangingSignBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, SHADOW_PINE_SIGN_MATERIAL.registryKey(Keys.STELLAR_JUNGLE_TREE_HANGING_SIGN)));
    public static final WallHangingSignBlock STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN = register(Keys.STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN.getValue().getPath(), new AerialHellWallHangingSignBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, AbstractBlock.Settings.copy(STELLAR_JUNGLE_TREE_HANGING_SIGN).registryKey(Keys.STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN).lootTable(STELLAR_JUNGLE_TREE_HANGING_SIGN.getLootTableKey())));
    public static final HangingSignBlock SKY_CACTUS_FIBER_HANGING_SIGN = register(Keys.SKY_CACTUS_FIBER_HANGING_SIGN.getValue().getPath(), new AerialHellHangingSignBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, SKY_CACTUS_FIBER_SIGN_MATERIAL.registryKey(Keys.SKY_CACTUS_FIBER_HANGING_SIGN)));
    public static final WallHangingSignBlock SKY_CACTUS_FIBER_WALL_HANGING_SIGN = register(Keys.SKY_CACTUS_FIBER_WALL_HANGING_SIGN.getValue().getPath(), new AerialHellWallHangingSignBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, AbstractBlock.Settings.copy(SKY_CACTUS_FIBER_HANGING_SIGN).registryKey(Keys.SKY_CACTUS_FIBER_WALL_HANGING_SIGN).lootTable(SKY_CACTUS_FIBER_HANGING_SIGN.getLootTableKey())));
    public static final HangingSignBlock GRAY_SHROOM_HANGING_SIGN = register(Keys.GRAY_SHROOM_HANGING_SIGN.getValue().getPath(), new AerialHellHangingSignBlock(AerialHellWoodTypes.GRAY_SHROOM, SKY_CACTUS_FIBER_SIGN_MATERIAL.registryKey(Keys.GRAY_SHROOM_HANGING_SIGN)));
    public static final WallHangingSignBlock GRAY_SHROOM_WALL_HANGING_SIGN = register(Keys.GRAY_SHROOM_WALL_HANGING_SIGN.getValue().getPath(), new AerialHellWallHangingSignBlock(AerialHellWoodTypes.GRAY_SHROOM, AbstractBlock.Settings.copy(GRAY_SHROOM_HANGING_SIGN).registryKey(Keys.GRAY_SHROOM_WALL_HANGING_SIGN).lootTable(GRAY_SHROOM_HANGING_SIGN.getLootTableKey())));

    //crafting tables
    public static final CraftingTableBlock AERIAL_TREE_CRAFTING_TABLE = register(Keys.AERIAL_TREE_CRAFTING_TABLE.getValue().getPath(), new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL.registryKey(Keys.AERIAL_TREE_CRAFTING_TABLE)));
    public static final CraftingTableBlock GOLDEN_BEECH_CRAFTING_TABLE = register(Keys.GOLDEN_BEECH_CRAFTING_TABLE.getValue().getPath(), new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL.registryKey(Keys.GOLDEN_BEECH_CRAFTING_TABLE)));
    public static final CraftingTableBlock COPPER_PINE_CRAFTING_TABLE = register(Keys.COPPER_PINE_CRAFTING_TABLE.getValue().getPath(), new AerialHellCraftingTableBlock(COPPER_PINE_MATERIAL.registryKey(Keys.COPPER_PINE_CRAFTING_TABLE)));
    public static final CraftingTableBlock LAPIS_ROBINIA_CRAFTING_TABLE = register(Keys.LAPIS_ROBINIA_CRAFTING_TABLE.getValue().getPath(), new AerialHellCraftingTableBlock(COPPER_PINE_MATERIAL.registryKey(Keys.LAPIS_ROBINIA_CRAFTING_TABLE)));
    public static final CraftingTableBlock SHADOW_PINE_CRAFTING_TABLE = register(Keys.SHADOW_PINE_CRAFTING_TABLE.getValue().getPath(), new AerialHellCraftingTableBlock(SHADOW_PINE_MATERIAL.registryKey(Keys.SHADOW_PINE_CRAFTING_TABLE)));
    public static final CraftingTableBlock STELLAR_JUNGLE_TREE_CRAFTING_TABLE = register(Keys.STELLAR_JUNGLE_TREE_CRAFTING_TABLE.getValue().getPath(), new AerialHellCraftingTableBlock(COPPER_PINE_MATERIAL.registryKey(Keys.STELLAR_JUNGLE_TREE_CRAFTING_TABLE)));
    public static final CraftingTableBlock SKY_CACTUS_FIBER_CRAFTING_TABLE = register(Keys.SKY_CACTUS_FIBER_CRAFTING_TABLE.getValue().getPath(), new AerialHellCraftingTableBlock(SKY_CACTUS_FIBER_MATERIAL.registryKey(Keys.SKY_CACTUS_FIBER_CRAFTING_TABLE)));
    public static final CraftingTableBlock GRAY_SHROOM_CRAFTING_TABLE = register(Keys.GRAY_SHROOM_CRAFTING_TABLE.getValue().getPath(), new AerialHellCraftingTableBlock(SHROOM_MATERIAL.registryKey(Keys.GRAY_SHROOM_CRAFTING_TABLE)));

    //barrels
    public static final AerialHellBarrelBlock AERIAL_TREE_BARREL = register(Keys.AERIAL_TREE_BARREL.getValue().getPath(), new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL.registryKey(Keys.AERIAL_TREE_BARREL)));
    public static final AerialHellBarrelBlock GOLDEN_BEECH_BARREL = register(Keys.GOLDEN_BEECH_BARREL.getValue().getPath(), new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL.registryKey(Keys.GOLDEN_BEECH_BARREL)));
    public static final AerialHellBarrelBlock COPPER_PINE_BARREL = register(Keys.COPPER_PINE_BARREL.getValue().getPath(), new AerialHellBarrelBlock(COPPER_PINE_MATERIAL.registryKey(Keys.COPPER_PINE_BARREL)));
    public static final AerialHellBarrelBlock LAPIS_ROBINIA_BARREL = register(Keys.LAPIS_ROBINIA_BARREL.getValue().getPath(), new AerialHellBarrelBlock(COPPER_PINE_MATERIAL.registryKey(Keys.LAPIS_ROBINIA_BARREL)));
    public static final AerialHellBarrelBlock SHADOW_PINE_BARREL = register(Keys.SHADOW_PINE_BARREL.getValue().getPath(), new AerialHellBarrelBlock(SHADOW_PINE_MATERIAL.registryKey(Keys.SHADOW_PINE_BARREL)));
    public static final AerialHellBarrelBlock STELLAR_JUNGLE_TREE_BARREL = register(Keys.STELLAR_JUNGLE_TREE_BARREL.getValue().getPath(), new AerialHellBarrelBlock(COPPER_PINE_MATERIAL.registryKey(Keys.STELLAR_JUNGLE_TREE_BARREL)));
    public static final AerialHellBarrelBlock SKY_CACTUS_FIBER_BARREL = register(Keys.SKY_CACTUS_FIBER_BARREL.getValue().getPath(), new AerialHellBarrelBlock(SKY_CACTUS_FIBER_MATERIAL.registryKey(Keys.SKY_CACTUS_FIBER_BARREL)));
    public static final AerialHellBarrelBlock GRAY_SHROOM_BARREL = register(Keys.GRAY_SHROOM_BARREL.getValue().getPath(), new AerialHellBarrelBlock(SHROOM_MATERIAL.registryKey(Keys.GRAY_SHROOM_BARREL)));

    //composters
    public static final ComposterBlock AERIAL_TREE_COMPOSTER = register(Keys.AERIAL_TREE_COMPOSTER.getValue().getPath(), new ComposterBlock(AERIAL_TREE_MATERIAL.registryKey(Keys.AERIAL_TREE_COMPOSTER)));
    public static final ComposterBlock GOLDEN_BEECH_COMPOSTER = register(Keys.GOLDEN_BEECH_COMPOSTER.getValue().getPath(), new ComposterBlock(AERIAL_TREE_MATERIAL.registryKey(Keys.GOLDEN_BEECH_COMPOSTER)));
    public static final ComposterBlock COPPER_PINE_COMPOSTER = register(Keys.COPPER_PINE_COMPOSTER.getValue().getPath(), new ComposterBlock(COPPER_PINE_MATERIAL.registryKey(Keys.COPPER_PINE_COMPOSTER)));
    public static final ComposterBlock LAPIS_ROBINIA_COMPOSTER = register(Keys.LAPIS_ROBINIA_COMPOSTER.getValue().getPath(), new ComposterBlock(COPPER_PINE_MATERIAL.registryKey(Keys.LAPIS_ROBINIA_COMPOSTER)));
    public static final ComposterBlock SHADOW_PINE_COMPOSTER = register(Keys.SHADOW_PINE_COMPOSTER.getValue().getPath(), new ComposterBlock(SHADOW_PINE_MATERIAL.registryKey(Keys.SHADOW_PINE_COMPOSTER)));
    public static final ComposterBlock STELLAR_JUNGLE_TREE_COMPOSTER = register(Keys.STELLAR_JUNGLE_TREE_COMPOSTER.getValue().getPath(), new ComposterBlock(COPPER_PINE_MATERIAL.registryKey(Keys.STELLAR_JUNGLE_TREE_COMPOSTER)));
    public static final ComposterBlock SKY_CACTUS_FIBER_COMPOSTER = register(Keys.SKY_CACTUS_FIBER_COMPOSTER.getValue().getPath(), new ComposterBlock(SKY_CACTUS_FIBER_MATERIAL.registryKey(Keys.SKY_CACTUS_FIBER_COMPOSTER)));
    public static final ComposterBlock GRAY_SHROOM_COMPOSTER = register(Keys.GRAY_SHROOM_COMPOSTER.getValue().getPath(), new ComposterBlock(SHROOM_MATERIAL.registryKey(Keys.GRAY_SHROOM_COMPOSTER)));

    //decorative
    public static final PillarBlock AERIAL_TREE_VINE_ROPE_SPOOL = register(Keys.AERIAL_TREE_VINE_ROPE_SPOOL.getValue().getPath(), new VineRopeSpoolBlock(AbstractBlock.Settings.create().registryKey(Keys.AERIAL_TREE_VINE_ROPE_SPOOL).nonOpaque().blockVision((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.BROWN).strength(1.2F).sounds(BlockSoundGroup.WOOD)));
    public static final PillarBlock GOLDEN_BEECH_VINE_ROPE_SPOOL = register(Keys.GOLDEN_BEECH_VINE_ROPE_SPOOL.getValue().getPath(), new VineRopeSpoolBlock(AbstractBlock.Settings.create().registryKey(Keys.GOLDEN_BEECH_VINE_ROPE_SPOOL).nonOpaque().blockVision((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.BROWN).strength(1.2F).sounds(BlockSoundGroup.WOOD)));
    public static final PillarBlock COPPER_PINE_VINE_ROPE_SPOOL = register(Keys.COPPER_PINE_VINE_ROPE_SPOOL.getValue().getPath(), new VineRopeSpoolBlock(AbstractBlock.Settings.create().registryKey(Keys.COPPER_PINE_VINE_ROPE_SPOOL).nonOpaque().blockVision((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.BROWN).strength(1.2F).sounds(BlockSoundGroup.WOOD)));
    public static final PillarBlock LAPIS_ROBINIA_VINE_ROPE_SPOOL = register(Keys.LAPIS_ROBINIA_VINE_ROPE_SPOOL.getValue().getPath(), new VineRopeSpoolBlock(AbstractBlock.Settings.create().registryKey(Keys.LAPIS_ROBINIA_VINE_ROPE_SPOOL).nonOpaque().blockVision((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.BROWN).strength(1.2F).sounds(BlockSoundGroup.WOOD)));
    public static final PillarBlock SHADOW_PINE_VINE_ROPE_SPOOL = register(Keys.SHADOW_PINE_VINE_ROPE_SPOOL.getValue().getPath(), new VineRopeSpoolBlock(AbstractBlock.Settings.create().registryKey(Keys.SHADOW_PINE_VINE_ROPE_SPOOL).nonOpaque().blockVision((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.BROWN).strength(1.2F).sounds(BlockSoundGroup.WOOD)));
    public static final PillarBlock STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL = register(Keys.STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL.getValue().getPath(), new VineRopeSpoolBlock(AbstractBlock.Settings.create().registryKey(Keys.STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL).nonOpaque().blockVision((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.BROWN).strength(1.2F).sounds(BlockSoundGroup.WOOD)));
    public static final PillarBlock SKY_CACTUS_FIBER_VINE_ROPE_SPOOL = register(Keys.SKY_CACTUS_FIBER_VINE_ROPE_SPOOL.getValue().getPath(), new VineRopeSpoolBlock(AbstractBlock.Settings.create().registryKey(Keys.SKY_CACTUS_FIBER_VINE_ROPE_SPOOL).nonOpaque().blockVision((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.BROWN).strength(1.2F).sounds(BlockSoundGroup.WOOD)));
    public static final PillarBlock GRAY_SHROOM_VINE_ROPE_SPOOL = register(Keys.GRAY_SHROOM_VINE_ROPE_SPOOL.getValue().getPath(), new VineRopeSpoolBlock(AbstractBlock.Settings.create().registryKey(Keys.GRAY_SHROOM_VINE_ROPE_SPOOL).nonOpaque().blockVision((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.BROWN).strength(1.2F).sounds(BlockSoundGroup.WOOD)));

    //fluids
    public static final FluidBlock LIQUID_OF_THE_GODS = register(Keys.LIQUID_OF_THE_GODS.getValue().getPath(), new AerialHellFluidBlock(AerialHellFluids.LIQUID_OF_THE_GODS_STILL, AbstractBlock.Settings.create().registryKey(Keys.LIQUID_OF_THE_GODS).replaceable().luminance((state) -> 8)));

    public static class Keys
    {
        //portal
        public static final RegistryKey<Block> AERIAL_HELL_PORTAL = createKey("aerial_hell_portal");
        public static final RegistryKey<Block> STELLAR_PORTAL_FRAME_BLOCK = createKey("stellar_portal_frame_block");
        public static final RegistryKey<Block> STELLAR_PORTAL_FRAME_ORE = createKey("stellar_portal_frame_ore");
        public static final RegistryKey<Block> DEEPSLATE_STELLAR_PORTAL_FRAME_ORE = createKey("deepslate_stellar_portal_frame_ore");

        //torch
        public static final RegistryKey<Block> FLUORITE_WALL_TORCH = createKey("fluorite_wall_torch");
        public static final RegistryKey<Block> FLUORITE_TORCH = createKey("fluorite_torch");
        public static final RegistryKey<Block> VOLUCITE_WALL_TORCH = createKey("volucite_wall_torch");
        public static final RegistryKey<Block> VOLUCITE_TORCH = createKey("volucite_torch");
        public static final RegistryKey<Block> SHADOW_WALL_TORCH = createKey("shadow_wall_torch");
        public static final RegistryKey<Block> SHADOW_TORCH = createKey("shadow_torch");

        //lanterns
        public static final RegistryKey<Block> FLUORITE_LANTERN = createKey("fluorite_lantern");
        public static final RegistryKey<Block> RUBY_LANTERN = createKey("ruby_lantern");
        public static final RegistryKey<Block> RUBY_FLUORITE_LANTERN = createKey("ruby_fluorite_lantern");
        public static final RegistryKey<Block> VOLUCITE_LANTERN = createKey("volucite_lantern");
        public static final RegistryKey<Block> VOLUCITE_FLUORITE_LANTERN = createKey("volucite_fluorite_lantern");
        public static final RegistryKey<Block> LUNATIC_LANTERN = createKey("lunatic_lantern");
        public static final RegistryKey<Block> SHADOW_LANTERN = createKey("shadow_lantern");

        //chains
        public static final RegistryKey<Block> RUBY_CHAIN = createKey("ruby_chain");
        public static final RegistryKey<Block> VOLUCITE_CHAIN = createKey("volucite_chain");
        public static final RegistryKey<Block> LUNATIC_CHAIN = createKey("lunatic_chain");
        public static final RegistryKey<Block> SHADOW_CHAIN = createKey("shadow_chain");

        //grass & dirt
        public static final RegistryKey<Block> STELLAR_GRASS_BLOCK = createKey("stellar_grass_block");
        public static final RegistryKey<Block> CHISELED_STELLAR_GRASS_BLOCK = createKey("chiseled_stellar_grass_block");
        public static final RegistryKey<Block> STELLAR_DIRT = createKey("stellar_dirt");
        public static final RegistryKey<Block> STELLAR_COARSE_DIRT = createKey("stellar_coarse_dirt");
        public static final RegistryKey<Block> STELLAR_FARMLAND = createKey("stellar_farmland");
        public static final RegistryKey<Block> STELLAR_DIRT_PATH = createKey("stellar_dirt_path");
        public static final RegistryKey<Block> STELLAR_PODZOL = createKey("stellar_podzol");
        public static final RegistryKey<Block> STELLAR_CRYSTAL_PODZOL = createKey("stellar_crystal_podzol");
        public static final RegistryKey<Block> CHISELED_STELLAR_DIRT = createKey("chiseled_stellar_dirt");
        public static final RegistryKey<Block> SHADOW_GRASS_BLOCK = createKey("shadow_grass_block");

        //slippery sand
        public static final RegistryKey<Block> SLIPPERY_SAND = createKey("slippery_sand");
        public static final RegistryKey<Block> SLIPPERY_SAND_STONE = createKey("slippery_sand_stone");
        public static final RegistryKey<Block> SLIPPERY_SAND_STONE_BRICKS = createKey("slippery_sand_stone_bricks");
        public static final RegistryKey<Block> CUT_SLIPPERY_SAND_STONE = createKey("cut_slippery_sand_stone");
        public static final RegistryKey<Block> CRACKED_SLIPPERY_SAND_STONE_BRICKS = createKey("cracked_slippery_sand_stone_bricks");

        //giant root
        public static final RegistryKey<Block> GIANT_ROOT = createKey("giant_root");

        //aerial_tree
        public static final RegistryKey<Block> AERIAL_TREE_LOG = createKey("aerial_tree_log");
        public static final RegistryKey<Block> STRIPPED_AERIAL_TREE_LOG = createKey("stripped_aerial_tree_log");
        public static final RegistryKey<Block> AERIAL_TREE_WOOD = createKey("aerial_tree_wood");
        public static final RegistryKey<Block> STRIPPED_AERIAL_TREE_WOOD = createKey("stripped_aerial_tree_wood");
        public static final RegistryKey<Block> AERIAL_TREE_LEAVES = createKey("aerial_tree_leaves");
        public static final RegistryKey<Block> AERIAL_TREE_PLANKS = createKey("aerial_tree_planks");
        public static final RegistryKey<Block> CHISELED_AERIAL_TREE_PLANKS = createKey("chiseled_aerial_tree_planks");
        public static final RegistryKey<Block> AERIAL_TREE_BOOKSHELF = createKey("aerial_tree_bookshelf");
        public static final RegistryKey<Block> AERIAL_TREE_SAPLING = createKey("aerial_tree_sapling");

        //petrified aerial tree
        public static final RegistryKey<Block> PETRIFIED_AERIAL_TREE_LOG = createKey("petrified_aerial_tree_log");

        //golden beech
        public static final RegistryKey<Block> GOLDEN_BEECH_LOG = createKey("golden_beech_log");
        public static final RegistryKey<Block> STRIPPED_GOLDEN_BEECH_LOG = createKey("stripped_golden_beech_log");
        public static final RegistryKey<Block> GOLDEN_BEECH_WOOD = createKey("golden_beech_wood");
        public static final RegistryKey<Block> STRIPPED_GOLDEN_BEECH_WOOD = createKey("stripped_golden_beech_wood");
        public static final RegistryKey<Block> GOLDEN_BEECH_PLANKS = createKey("golden_beech_planks");
        public static final RegistryKey<Block> CHISELED_GOLDEN_BEECH_PLANKS = createKey("chiseled_golden_beech_planks");
        public static final RegistryKey<Block> GOLDEN_BEECH_LEAVES = createKey("golden_beech_leaves");
        public static final RegistryKey<Block> GOLDEN_BEECH_BOOKSHELF = createKey("golden_beech_bookshelf");
        public static final RegistryKey<Block> GOLDEN_BEECH_SAPLING = createKey("golden_beech_sapling");

        //cropper pine
        public static final RegistryKey<Block> COPPER_PINE_LOG = createKey("copper_pine_log");
        public static final RegistryKey<Block> STRIPPED_COPPER_PINE_LOG = createKey("stripped_copper_pine_log");
        public static final RegistryKey<Block> COPPER_PINE_WOOD = createKey("copper_pine_wood");
        public static final RegistryKey<Block> STRIPPED_COPPER_PINE_WOOD = createKey("stripped_copper_pine_wood");
        public static final RegistryKey<Block> COPPER_PINE_PLANKS = createKey("copper_pine_planks");
        public static final RegistryKey<Block> COPPER_PINE_LEAVES = createKey("copper_pine_leaves");
        public static final RegistryKey<Block> COPPER_PINE_BOOKSHELF = createKey("copper_pine_bookshelf");
        public static final RegistryKey<Block> COPPER_PINE_SAPLING = createKey("copper_pine_sapling");

        //lapis robinia
        public static final RegistryKey<Block> LAPIS_ROBINIA_LOG = createKey("lapis_robinia_log");
        public static final RegistryKey<Block> ENCHANTED_LAPIS_ROBINIA_LOG = createKey("enchanted_lapis_robinia_log");
        public static final RegistryKey<Block> STRIPPED_LAPIS_ROBINIA_LOG = createKey("stripped_lapis_robinia_log");
        public static final RegistryKey<Block> LAPIS_ROBINIA_WOOD = createKey("lapis_robinia_wood");
        public static final RegistryKey<Block> STRIPPED_LAPIS_ROBINIA_WOOD = createKey("stripped_lapis_robinia_wood");
        public static final RegistryKey<Block> LAPIS_ROBINIA_LEAVES = createKey("lapis_robinia_leaves");
        public static final RegistryKey<Block> LAPIS_ROBINIA_PLANKS = createKey("lapis_robinia_planks");
        public static final RegistryKey<Block> LAPIS_ROBINIA_BOOKSHELF = createKey("lapis_robinia_bookshelf");
        public static final RegistryKey<Block> LAPIS_ROBINIA_SAPLING = createKey("lapis_robinia_sapling");

        //shadow_pine
        public static final RegistryKey<Block> SHADOW_PINE_LOG = createKey("shadow_pine_log");
        public static final RegistryKey<Block> EYE_SHADOW_PINE_LOG = createKey("eye_shadow_pine_log");
        public static final RegistryKey<Block> STRIPPED_SHADOW_PINE_LOG = createKey("stripped_shadow_pine_log");
        public static final RegistryKey<Block> SHADOW_PINE_WOOD = createKey("shadow_pine_wood");
        public static final RegistryKey<Block> STRIPPED_SHADOW_PINE_WOOD = createKey("stripped_shadow_pine_wood");
        public static final RegistryKey<Block> SHADOW_PINE_LEAVES = createKey("shadow_pine_leaves");
        public static final RegistryKey<Block> PURPLE_SHADOW_PINE_LEAVES = createKey("purple_shadow_pine_leaves");
        public static final RegistryKey<Block> SHADOW_PINE_PLANKS = createKey("shadow_pine_planks");
        public static final RegistryKey<Block> SHADOW_PINE_BOOKSHELF = createKey("shadow_pine_bookshelf");
        public static final RegistryKey<Block> SHADOW_PINE_SAPLING = createKey("shadow_pine_sapling");
        public static final RegistryKey<Block> PURPLE_SHADOW_PINE_SAPLING = createKey("purple_shadow_pine_sapling");

        //stellar jungle tree
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_LOG = createKey("stellar_jungle_tree_log");
        public static final RegistryKey<Block> STRIPPED_STELLAR_JUNGLE_TREE_LOG = createKey("stripped_stellar_jungle_tree_log");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_WOOD = createKey("stellar_jungle_tree_wood");
        public static final RegistryKey<Block> STRIPPED_STELLAR_JUNGLE_TREE_WOOD = createKey("stripped_stellar_jungle_tree_wood");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_LEAVES = createKey("stellar_jungle_tree_leaves");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_PLANKS = createKey("stellar_jungle_tree_planks");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_BOOKSHELF = createKey("stellar_jungle_tree_bookshelf");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_SAPLING = createKey("stellar_jungle_tree_sapling");
        public static final RegistryKey<Block> DEAD_STELLAR_JUNGLE_TREE_LOG = createKey("dead_stellar_jungle_tree_log");

        //shroom
        public static final RegistryKey<Block> GIANT_CORTINARIUS_VIOLACEUS_STEM = createKey("giant_cortinarius_violaceus_stem");
        public static final RegistryKey<Block> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM = createKey("stripped_giant_cortinarius_violaceus_stem");
        public static final RegistryKey<Block> GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = createKey("giant_cortinarius_violaceus_bark_stem");
        public static final RegistryKey<Block> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = createKey("stripped_giant_cortinarius_violaceus_bark_stem");
        public static final RegistryKey<Block> GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK = createKey("giant_cortinarius_violaceus_cap_block");
        public static final RegistryKey<Block> GIANT_CORTINARIUS_VIOLACEUS_LIGHT = createKey("giant_cortinarius_violaceus_light");
        public static final RegistryKey<Block> CORTINARIUS_VIOLACEUS = createKey("cortinarius_violaceus");
        public static final RegistryKey<Block> GLOWING_BOLETUS = createKey("glowing_boletus");
        public static final RegistryKey<Block> TALL_GLOWING_BOLETUS = createKey("tall_glowing_boletus");
        public static final RegistryKey<Block> BLUE_MEANIE_CLUSTER = createKey("blue_meanie_cluster");
        public static final RegistryKey<Block> GIANT_ROOT_SHROOM = createKey("giant_root_shroom");

        public static final RegistryKey<Block> GIANT_VERDIGRIS_AGARIC_STEM = createKey("giant_verdigris_agaric_stem");
        public static final RegistryKey<Block> STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM = createKey("stripped_giant_verdigris_agaric_stem");
        public static final RegistryKey<Block> GIANT_VERDIGRIS_AGARIC_BARK_STEM = createKey("giant_verdigris_agaric_bark_stem");
        public static final RegistryKey<Block> STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM = createKey("stripped_giant_verdigris_agaric_bark_stem");
        public static final RegistryKey<Block> GIANT_VERDIGRIS_AGARIC_CAP_BLOCK = createKey("giant_verdigris_agaric_cap_block");
        public static final RegistryKey<Block> VERDIGRIS_AGARIC = createKey("verdigris_agaric");

        public static final RegistryKey<Block> GIANT_GANODERMA_APPLANATUM_BLOCK = createKey("giant_ganoderma_applanatum_block");

        public static final RegistryKey<Block> GRAY_SHROOM_PLANKS = createKey("gray_shroom_planks");
        public static final RegistryKey<Block> GRAY_SHROOM_BOOKSHELF = createKey("gray_shroom_bookshelf");

        //shadow corrupted / uncorrupted variants
        public static final RegistryKey<Block> SHADOW_AERIAL_TREE_LOG = createKey("shadow_aerial_tree_log");
        public static final RegistryKey<Block> SHADOW_GOLDEN_BEECH_LOG = createKey("shadow_golden_beech_log");
        public static final RegistryKey<Block> SHADOW_COPPER_PINE_LOG = createKey("shadow_copper_pine_log");
        public static final RegistryKey<Block> SHADOW_LAPIS_ROBINIA_LOG = createKey("shadow_lapis_robinia_log");
        public static final RegistryKey<Block> SHADOW_STELLAR_JUNGLE_TREE_LOG = createKey("shadow_stellar_jungle_tree_log");
        public static final RegistryKey<Block> HOLLOW_SHADOW_PINE_LOG = createKey("hollow_shadow_pine_log");
        public static final RegistryKey<Block> SHADOW_AERIAL_TREE_LEAVES = createKey("shadow_aerial_tree_leaves");
        public static final RegistryKey<Block> SHADOW_GOLDEN_BEECH_LEAVES = createKey("shadow_golden_beech_leaves");
        public static final RegistryKey<Block> SHADOW_COPPER_PINE_LEAVES = createKey("shadow_copper_pine_leaves");
        public static final RegistryKey<Block> SHADOW_LAPIS_ROBINIA_LEAVES = createKey("shadow_lapis_robinia_leaves");
        public static final RegistryKey<Block> SHADOW_STELLAR_JUNGLE_TREE_LEAVES = createKey("shadow_stellar_jungle_tree_leaves");
        public static final RegistryKey<Block> HOLLOW_SHADOW_PINE_LEAVES = createKey("hollow_shadow_pine_leaves");
        public static final RegistryKey<Block> HOLLOW_PURPLE_SHADOW_PINE_LEAVES = createKey("hollow_purple_shadow_pine_leaves");

        //ladder
        public static final RegistryKey<Block> SKY_LADDER = createKey("sky_ladder");

        //natural blocks and items
        public static final RegistryKey<Block> STELLAR_STONE = createKey("stellar_stone");
        public static final RegistryKey<Block> STELLAR_COBBLESTONE = createKey("stellar_cobblestone");
        public static final RegistryKey<Block> STELLAR_STONE_BRICKS = createKey("stellar_stone_bricks");
        public static final RegistryKey<Block> MOSSY_STELLAR_STONE = createKey("mossy_stellar_stone");
        public static final RegistryKey<Block> MOSSY_STELLAR_COBBLESTONE = createKey("mossy_stellar_cobblestone");
        public static final RegistryKey<Block> STELLAR_CLAY = createKey("stellar_clay");
        public static final RegistryKey<Block> GLAUCOPHANITE = createKey("glaucophanite");
        public static final RegistryKey<Block> POLISHED_GLAUCOPHANITE = createKey("polished_glaucophanite");
        public static final RegistryKey<Block> SHADOW_STONE = createKey("shadow_stone");

        //crystal
        public static final RegistryKey<Block> CRYSTAL_BLOCK = createKey("crystal_block");
        public static final RegistryKey<Block> CRYSTAL_BRICKS = createKey("crystal_bricks");
        public static final RegistryKey<Block> CRYSTAL_BRICKS_SLAB = createKey("crystal_bricks_slab");
        public static final RegistryKey<Block> CRYSTAL_BRICKS_STAIRS = createKey("crystal_bricks_stairs");
        public static final RegistryKey<Block> CRYSTAL_BRICKS_WALL = createKey("crystal_bricks_wall");
        public static final RegistryKey<Block> STELLAR_STONE_CRYSTAL_BLOCK = createKey("stellar_stone_crystal_block");
        public static final RegistryKey<Block> SHADOW_CRYSTAL_BLOCK = createKey("shadow_crystal_block");
        public static final RegistryKey<Block> CRYSTALLIZED_LEAVES = createKey("crystallized_leaves");
        public static final RegistryKey<Block> CRYSTALLIZED_FIRE = createKey("crystallized_fire");

        //glass and glass pane
        public static final RegistryKey<Block> SLIPPERY_SAND_GLASS = createKey("slippery_sand_glass");
        public static final RegistryKey<Block> RED_SLIPPERY_SAND_GLASS = createKey("red_slippery_sand_glass");
        public static final RegistryKey<Block> BLACK_SLIPPERY_SAND_GLASS = createKey("black_slippery_sand_glass");
        public static final RegistryKey<Block> BLUE_SLIPPERY_SAND_GLASS = createKey("blue_slippery_sand_glass");
        public static final RegistryKey<Block> GREEN_SLIPPERY_SAND_GLASS = createKey("green_slippery_sand_glass");
        public static final RegistryKey<Block> SLIPPERY_SAND_GLASS_PANE = createKey("slippery_sand_glass_pane");
        public static final RegistryKey<Block> RED_SLIPPERY_SAND_GLASS_PANE = createKey("red_slippery_sand_glass_pane");
        public static final RegistryKey<Block> BLACK_SLIPPERY_SAND_GLASS_PANE = createKey("black_slippery_sand_glass_pane");
        public static final RegistryKey<Block> BLUE_SLIPPERY_SAND_GLASS_PANE = createKey("blue_slippery_sand_glass_pane");
        public static final RegistryKey<Block> GREEN_SLIPPERY_SAND_GLASS_PANE = createKey("green_slippery_sand_glass_pane");

        //ghost boat
        public static final RegistryKey<Block> GHOST_BOAT_PLANKS = createKey("ghost_boat_planks");
        public static final RegistryKey<Block> GHOST_BOAT_LOG = createKey("ghost_boat_log");
        public static final RegistryKey<Block> GHOST_BOAT_WOOD = createKey("ghost_boat_wood");
        public static final RegistryKey<Block> GHOST_BOAT_SLAB = createKey("ghost_boat_slab");
        public static final RegistryKey<Block> GHOST_BOAT_STAIRS = createKey("ghost_boat_stairs");
        public static final RegistryKey<Block> GHOST_BOAT_FENCE = createKey("ghost_boat_fence");
        public static final RegistryKey<Block> GHOST_BOAT_GATE = createKey("ghost_boat_gate");
        public static final RegistryKey<Block> GHOST_BOAT_DOOR = createKey("ghost_boat_door");
        public static final RegistryKey<Block> GHOST_BOAT_TRAPDOOR = createKey("ghost_boat_trapdoor");
        public static final RegistryKey<Block> GHOST_BOAT_CHEST = createKey("ghost_boat_chest");
        public static final RegistryKey<Block> GHOST_BOAT_WOOL = createKey("ghost_boat_wool");
        public static final RegistryKey<Block> GHOST_STELLAR_COBBLESTONE = createKey("ghost_stellar_cobblestone");
        public static final RegistryKey<Block> GHOST_RUBY_BLOCK = createKey("ghost_ruby_block");
        public static final RegistryKey<Block> GHOST_FLUORITE_BLOCK = createKey("ghost_fluorite_block");
        public static final RegistryKey<Block> GHOST_AZURITE_BLOCK = createKey("ghost_azurite_block");
        public static final RegistryKey<Block> GHOST_GOLD_BLOCK = createKey("ghost_gold_block");
        public static final RegistryKey<Block> GHOST_BOAT_BARREL = createKey("ghost_boat_barrel");
        public static final RegistryKey<Block> GHOST_BOAT_CRAFTING_TABLE = createKey("ghost_boat_crafting_table");
        public static final RegistryKey<Block> GHOST_BOAT_VINE_ROPE_SPOOL = createKey("ghost_boat_vine_rope_spool");
        public static final RegistryKey<Block> GHOST_LANTERN = createKey("ghost_lantern");

        //other condition condition blocks
        public static final RegistryKey<Block> INTANGIBLE_TEMPORARY_BLOCK = createKey("intangible_temporary_block");

        //reactors
        public static final RegistryKey<Block> WEAK_LIGHT_REACTOR = createKey("weak_light_reactor");
        public static final RegistryKey<Block> HIGH_POWER_LIGHT_REACTOR = createKey("high_power_light_reactor");
        public static final RegistryKey<Block> WEAK_SHADOW_REACTOR = createKey("weak_shadow_reactor");
        public static final RegistryKey<Block> HIGH_POWER_SHADOW_REACTOR = createKey("high_power_shadow_reactor");

        public static final RegistryKey<Block> BROKEN_WEAK_LIGHT_REACTOR = createKey("broken_weak_light_reactor");
        public static final RegistryKey<Block> BROKEN_HIGH_POWER_LIGHT_REACTOR = createKey("broken_high_power_light_reactor");
        public static final RegistryKey<Block> BROKEN_WEAK_SHADOW_REACTOR = createKey("broken_weak_shadow_reactor");
        public static final RegistryKey<Block> BROKEN_HIGH_POWER_SHADOW_REACTOR = createKey("broken_high_power_shadow_reactor");

        //solid_ethers
        public static final RegistryKey<Block> WHITE_SOLID_ETHER = createKey("white_solid_ether");
        public static final RegistryKey<Block> BLUE_SOLID_ETHER = createKey("blue_solid_ether");
        public static final RegistryKey<Block> GOLDEN_SOLID_ETHER = createKey("golden_solid_ether");
        public static final RegistryKey<Block> GREEN_SOLID_ETHER = createKey("green_solid_ether");
        public static final RegistryKey<Block> PURPLE_SOLID_ETHER = createKey("purple_solid_ether");

        //dungeons blocks
        public static final RegistryKey<Block> MUD_BRICKS = createKey("mud_bricks");
        public static final RegistryKey<Block> CRACKED_MUD_BRICKS = createKey("cracked_mud_bricks");
        public static final RegistryKey<Block> MOSSY_MUD_BRICKS = createKey("mossy_mud_bricks");
        public static final RegistryKey<Block> CHISELED_MUD_BRICKS = createKey("chiseled_mud_bricks");
        public static final RegistryKey<Block> LIGHT_MUD_BRICKS = createKey("light_mud_bricks");
        public static final RegistryKey<Block> CRACKED_LIGHT_MUD_BRICKS = createKey("cracked_light_mud_bricks");
        public static final RegistryKey<Block> LUNATIC_STONE = createKey("lunatic_stone");
        public static final RegistryKey<Block> DARK_LUNATIC_STONE = createKey("dark_lunatic_stone");
        public static final RegistryKey<Block> ROOF_LUNATIC_STONE = createKey("roof_lunatic_stone");
        public static final RegistryKey<Block> CRACKED_LUNATIC_STONE = createKey("cracked_lunatic_stone");
        public static final RegistryKey<Block> CHISELED_LUNATIC_STONE = createKey("chiseled_lunatic_stone");
        public static final RegistryKey<Block> LIGHT_LUNATIC_STONE = createKey("light_lunatic_stone");
        public static final RegistryKey<Block> ROOF_LIGHT_LUNATIC_STONE = createKey("roof_light_lunatic_stone");
        public static final RegistryKey<Block> CRACKED_LIGHT_LUNATIC_STONE = createKey("cracked_light_lunatic_stone");
        public static final RegistryKey<Block> SHADOW_CATACOMBS_BRICKS = createKey("shadow_catacombs_bricks");
        public static final RegistryKey<Block> CRACKED_SHADOW_CATACOMBS_BRICKS = createKey("cracked_shadow_catacombs_bricks");
        public static final RegistryKey<Block> MOSSY_SHADOW_CATACOMBS_BRICKS = createKey("mossy_shadow_catacombs_bricks");
        public static final RegistryKey<Block> CHISELED_SHADOW_CATACOMBS_BRICKS = createKey("chiseled_shadow_catacombs_bricks");
        public static final RegistryKey<Block> BONE_SHADOW_CATACOMBS_BRICKS = createKey("bone_shadow_catacombs_bricks");
        public static final RegistryKey<Block> SKULL_SHADOW_CATACOMBS_BRICKS = createKey("skull_shadow_catacombs_bricks");
        public static final RegistryKey<Block> LIGHT_SHADOW_CATACOMBS_BRICKS = createKey("light_shadow_catacombs_bricks");
        public static final RegistryKey<Block> CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS = createKey("cracked_light_shadow_catacombs_bricks");
        public static final RegistryKey<Block> GOLDEN_NETHER_BRICKS  = createKey("golden_nether_bricks");
        public static final RegistryKey<Block> CRACKED_GOLDEN_NETHER_BRICKS  = createKey("cracked_golden_nether_bricks");
        public static final RegistryKey<Block> CHISELED_GOLDEN_NETHER_BRICKS  = createKey("chiseled_golden_nether_bricks");
        public static final RegistryKey<Block> LIGHT_GOLDEN_NETHER_BRICKS = createKey("light_golden_nether_bricks");
        public static final RegistryKey<Block> CRACKED_LIGHT_GOLDEN_NETHER_BRICKS = createKey("cracked_light_golden_nether_bricks");
        public static final RegistryKey<Block> LUNATIC_PILLAR = createKey("lunatic_pillar");
        public static final RegistryKey<Block> LUNATIC_PILLAR_TOP = createKey("lunatic_pillar_top");
        public static final RegistryKey<Block> VOLUCITE_STONE = createKey("volucite_stone");
        public static final RegistryKey<Block> CRACKED_VOLUCITE_STONE = createKey("cracked_volucite_stone");
        public static final RegistryKey<Block> CHISELED_VOLUCITE_STONE = createKey("chiseled_volucite_stone");
        public static final RegistryKey<Block> LIGHT_VOLUCITE_STONE = createKey("light_volucite_stone");
        public static final RegistryKey<Block> CRACKED_LIGHT_VOLUCITE_STONE = createKey("cracked_light_volucite_stone");

        //dungeon cores
        public static final RegistryKey<Block> MUD_DUNGEON_CORE = createKey("mud_dungeon_core");
        public static final RegistryKey<Block> LUNATIC_DUNGEON_CORE = createKey("lunatic_dungeon_core");
        public static final RegistryKey<Block> SHADOW_CATACOMBS_DUNGEON_CORE = createKey("shadow_catacombs_dungeon_core");
        public static final RegistryKey<Block> GOLDEN_NETHER_DUNGEON_CORE = createKey("golden_nether_dungeon_core");
        public static final RegistryKey<Block> VOLUCITE_DUNGEON_CORE = createKey("volucite_dungeon_core");

        //dungeons slabs, stairs & walls
        public static final RegistryKey<Block> MUD_BRICKS_SLAB = createKey("mud_bricks_slab");
        public static final RegistryKey<Block> MUD_BRICKS_STAIRS = createKey("mud_bricks_stairs");
        public static final RegistryKey<Block> MUD_BRICKS_WALL = createKey("mud_bricks_wall");
        public static final RegistryKey<Block> CRACKED_MUD_BRICKS_SLAB = createKey("cracked_mud_bricks_slab");
        public static final RegistryKey<Block> CRACKED_MUD_BRICKS_STAIRS = createKey("cracked_mud_bricks_stairs");
        public static final RegistryKey<Block> CRACKED_MUD_BRICKS_WALL = createKey("cracked_mud_bricks_wall");
        public static final RegistryKey<Block> MOSSY_MUD_BRICKS_SLAB = createKey("mossy_mud_bricks_slab");
        public static final RegistryKey<Block> MOSSY_MUD_BRICKS_STAIRS = createKey("mossy_mud_bricks_stairs");
        public static final RegistryKey<Block> MOSSY_MUD_BRICKS_WALL = createKey("mossy_mud_bricks_wall");
        public static final RegistryKey<Block> VOLUCITE_STONE_SLAB = createKey("volucite_stone_slab");
        public static final RegistryKey<Block> VOLUCITE_STONE_STAIRS = createKey("volucite_stone_stairs");
        public static final RegistryKey<Block> VOLUCITE_STONE_WALL = createKey("volucite_stone_wall");
        public static final RegistryKey<Block> CRACKED_VOLUCITE_STONE_SLAB = createKey("cracked_volucite_stone_slab");
        public static final RegistryKey<Block> CRACKED_VOLUCITE_STONE_STAIRS = createKey("cracked_volucite_stone_stairs");
        public static final RegistryKey<Block> CRACKED_VOLUCITE_STONE_WALL = createKey("cracked_volucite_stone_wall");
        public static final RegistryKey<Block> LUNATIC_STONE_SLAB = createKey("lunatic_stone_slab");
        public static final RegistryKey<Block> LUNATIC_STONE_STAIRS = createKey("lunatic_stone_stairs");
        public static final RegistryKey<Block> LUNATIC_STONE_WALL = createKey("lunatic_stone_wall");
        public static final RegistryKey<Block> DARK_LUNATIC_STONE_SLAB = createKey("dark_lunatic_stone_slab");
        public static final RegistryKey<Block> DARK_LUNATIC_STONE_STAIRS = createKey("dark_lunatic_stone_stairs");
        public static final RegistryKey<Block> DARK_LUNATIC_STONE_WALL = createKey("dark_lunatic_stone_wall");
        public static final RegistryKey<Block> CRACKED_LUNATIC_STONE_SLAB = createKey("cracked_lunatic_stone_slab");
        public static final RegistryKey<Block> CRACKED_LUNATIC_STONE_STAIRS = createKey("cracked_lunatic_stone_stairs");
        public static final RegistryKey<Block> CRACKED_LUNATIC_STONE_WALL = createKey("cracked_lunatic_stone_wall");
        public static final RegistryKey<Block> SHADOW_CATACOMBS_BRICKS_SLAB = createKey("shadow_catacombs_bricks_slab");
        public static final RegistryKey<Block> SHADOW_CATACOMBS_BRICKS_STAIRS = createKey("shadow_catacombs_bricks_stairs");
        public static final RegistryKey<Block> SHADOW_CATACOMBS_BRICKS_WALL = createKey("shadow_catacombs_bricks_wall");
        public static final RegistryKey<Block> CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB = createKey("cracked_shadow_catacombs_bricks_slab");
        public static final RegistryKey<Block> CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS = createKey("cracked_shadow_catacombs_bricks_stairs");
        public static final RegistryKey<Block> CRACKED_SHADOW_CATACOMBS_BRICKS_WALL = createKey("cracked_shadow_catacombs_bricks_wall");
        public static final RegistryKey<Block> MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB = createKey("mossy_shadow_catacombs_bricks_slab");
        public static final RegistryKey<Block> MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS = createKey("mossy_shadow_catacombs_bricks_stairs");
        public static final RegistryKey<Block> MOSSY_SHADOW_CATACOMBS_BRICKS_WALL = createKey("mossy_shadow_catacombs_bricks_wall");
        public static final RegistryKey<Block> SHADOW_BARS = createKey("shadow_bars");
        public static final RegistryKey<Block> GOLDEN_NETHER_BRICKS_SLAB = createKey("golden_nether_bricks_slab");
        public static final RegistryKey<Block> GOLDEN_NETHER_BRICKS_STAIRS = createKey("golden_nether_bricks_stairs");
        public static final RegistryKey<Block> GOLDEN_NETHER_BRICKS_WALL = createKey("golden_nether_bricks_wall");
        public static final RegistryKey<Block> CRACKED_GOLDEN_NETHER_BRICKS_SLAB = createKey("cracked_golden_nether_bricks_slab");
        public static final RegistryKey<Block> CRACKED_GOLDEN_NETHER_BRICKS_STAIRS = createKey("cracked_golden_nether_bricks_stairs");
        public static final RegistryKey<Block> CRACKED_GOLDEN_NETHER_BRICKS_WALL = createKey("cracked_golden_nether_bricks_wall");

        //smoky quartz
        public static final RegistryKey<Block> SMOKY_QUARTZ_BLOCK = createKey("smoky_quartz_block");
        public static final RegistryKey<Block> SMOOTH_SMOKY_QUARTZ = createKey("smooth_smoky_quartz");
        public static final RegistryKey<Block> CHISELED_SMOKY_QUARTZ_BLOCK = createKey("chiseled_smoky_quartz_block");
        public static final RegistryKey<Block> SMOKY_QUARTZ_BRICKS = createKey("smoky_quartz_bricks");
        public static final RegistryKey<Block> SMOKY_QUARTZ_PILLAR = createKey("smoky_quartz_pillar");
        public static final RegistryKey<Block> SMOKY_QUARTZ_SLAB = createKey("smoky_quartz_slab");
        public static final RegistryKey<Block> SMOOTH_SMOKY_QUARTZ_SLAB = createKey("smooth_smoky_quartz_slab");
        public static final RegistryKey<Block> SMOKY_QUARTZ_STAIRS = createKey("smoky_quartz_stairs");
        public static final RegistryKey<Block> SMOOTH_SMOKY_QUARTZ_STAIRS = createKey("smooth_smoky_quartz_stairs");

        //dungeon trapped blocks
        public static final RegistryKey<Block> TRAPPED_MUD_BRICKS = createKey("trapped_mud_bricks");
        public static final RegistryKey<Block> TRAPPED_LIGHT_MUD_BRICKS = createKey("trapped_light_mud_bricks");
        public static final RegistryKey<Block> TRAPPED_LUNATIC_STONE = createKey("trapped_lunatic_stone");
        public static final RegistryKey<Block> TRAPPED_LIGHT_LUNATIC_STONE = createKey("trapped_light_lunatic_stone");
        public static final RegistryKey<Block> TRAPPED_GOLDEN_NETHER_BRICKS = createKey("trapped_golden_nether_bricks");
        public static final RegistryKey<Block> TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS = createKey("trapped_light_golden_nether_bricks");

        //dungeon other blocks, loots
        public static final RegistryKey<Block> MUD_BONE_BLOCK = createKey("mud_bone_block");
        public static final RegistryKey<Block> MUD_BONE_PILE_BLOCK = createKey("mud_bone_pile_block");
        public static final RegistryKey<Block> THORNY_COBWEB = createKey("thorny_cobweb");
        public static final RegistryKey<Block> AERIAL_NETHERRACK = createKey("aerial_netherrack");
        public static final RegistryKey<Block> AERIAL_NETHERRACK_SLAB = createKey("aerial_netherrack_slab");
        public static final RegistryKey<Block> AERIAL_NETHERRACK_STAIRS = createKey("aerial_netherrack_stairs");
        public static final RegistryKey<Block> AERIAL_NETHERRACK_WALL = createKey("aerial_netherrack_wall");

        //dungeon bookshelfs
        public static final RegistryKey<Block> MUD_BOOKSHELF = createKey("mud_bookshelf");
        public static final RegistryKey<Block> LUNATIC_BOOKSHELF = createKey("lunatic_bookshelf");
        public static final RegistryKey<Block> GOLDEN_NETHER_BOOKSHELF = createKey("golden_nether_bookshelf");
        public static final RegistryKey<Block> SHADOW_CATACOMBS_BOOKSHELF = createKey("shadow_catacombs_bookshelf");
        public static final RegistryKey<Block> VOLUCITE_BOOKSHELF = createKey("volucite_bookshelf");

        //glyph blocks
        public static final RegistryKey<Block> MUD_GLYPH_BLOCK = createKey("mud_glyph_block");
        public static final RegistryKey<Block> LUNATIC_GLYPH_BLOCK = createKey("lunatic_glyph_block");
        public static final RegistryKey<Block> GOLDEN_NETHER_PRISON_GLYPH_BLOCK = createKey("golden_nether_prison_glyph_block");
        public static final RegistryKey<Block> VOLUCITE_GLYPH_BLOCK = createKey("volucite_glyph_block");
        public static final RegistryKey<Block> SHADOW_CATACOMBS_GLYPH_BLOCK = createKey("shadow_catacombs_glyph_block");

        //trophies
        public static final RegistryKey<Block> MUD_CYCLE_MAGE_TROPHY = createKey("mud_cycle_mage_trophy");
        public static final RegistryKey<Block> LUNAR_PRIEST_TROPHY = createKey("lunar_priest_trophy");
        public static final RegistryKey<Block> LILITH_TROPHY = createKey("lilith_trophy");
        public static final RegistryKey<Block> CHAINED_GOD_TROPHY = createKey("chained_god_trophy");

        //ores
        public static final RegistryKey<Block> IRON_STELLAR_ORE = createKey("iron_stellar_ore");
        public static final RegistryKey<Block> GOLD_STELLAR_ORE = createKey("gold_stellar_ore");
        public static final RegistryKey<Block> DIAMOND_STELLAR_ORE = createKey("diamond_stellar_ore");
        public static final RegistryKey<Block> FLUORITE_ORE = createKey("fluorite_ore");
        public static final RegistryKey<Block> MAGMATIC_GEL_ORE = createKey("magmatic_gel_ore");
        public static final RegistryKey<Block> RUBY_ORE = createKey("ruby_ore");
        public static final RegistryKey<Block> AZURITE_ORE = createKey("azurite_ore");
        public static final RegistryKey<Block> VOLUCITE_ORE = createKey("volucite_ore");
        public static final RegistryKey<Block> OBSIDIAN_ORE = createKey("obsidian_ore");
        public static final RegistryKey<Block> SMOKY_QUARTZ_ORE = createKey("smoky_quartz_ore");

        public static final RegistryKey<Block> RAW_RUBY_BLOCK = createKey("raw_ruby_block");
        public static final RegistryKey<Block> RAW_AZURITE_BLOCK = createKey("raw_azurite_crystal_block");
        public static final RegistryKey<Block> RAW_VOLUCITE_BLOCK = createKey("raw_volucite_block");

        public static final RegistryKey<Block> FLUORITE_BLOCK = createKey("fluorite_block");
        public static final RegistryKey<Block> MAGMATIC_GEL_BLOCK = createKey("magmatic_gel_block");
        public static final RegistryKey<Block> RUBY_BLOCK = createKey("ruby_block");
        public static final RegistryKey<Block> AZURITE_BLOCK = createKey("azurite_block");
        public static final RegistryKey<Block> VOLUCITE_BLOCK = createKey("volucite_block");

        //legendary ores
        public static final RegistryKey<Block> ARSONIST_BLOCK = createKey("arsonist_block");
        public static final RegistryKey<Block> LUNATIC_CRYSTAL_BLOCK = createKey("lunatic_crystal_block");
        public static final RegistryKey<Block> CURSED_CRYSAL_BLOCK = createKey("cursed_crystal_block");

        //cactus
        public static final RegistryKey<Block> SKY_CACTUS = createKey("sky_cactus");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_PLANKS = createKey("sky_cactus_fiber_planks");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_BOOKSHELF = createKey("sky_cactus_fiber_bookshelf");
        public static final RegistryKey<Block> VIBRANT_SKY_CACTUS = createKey("vibrant_sky_cactus");
        public static final RegistryKey<Block> VIBRANT_SKY_CACTUS_FIBER_LANTERN = createKey("vibrant_sky_cactus_fiber_lantern");

        //bushes
        public static final RegistryKey<Block> AERIAL_BERRY_BUSH = createKey("aerial_berry_bush");
        public static final RegistryKey<Block> VIBRANT_AERIAL_BERRY_BUSH = createKey("vibrant_aerial_berry_bush");

        //crops
        public static final RegistryKey<Block> STELLAR_WHEAT = createKey("stellar_wheat");
        public static final RegistryKey<Block> BLUE_MEANIE_CROP = createKey("blue_meanie_crop");

        //vertical growing plants
        public static final RegistryKey<Block> CLIMBING_VINE = createKey("climbing_vine");
        public static final RegistryKey<Block> STELLAR_SUGAR_CANE = createKey("stellar_sugar_cane");

        //chorus like
        public static final RegistryKey<Block> FULL_MOON_PLANT = createKey("full_moon_plant");
        public static final RegistryKey<Block> FULL_MOON_FLOWER = createKey("full_moon_flower");

        //vines
        public static final RegistryKey<Block> GLOWING_STICK_FRUIT_VINES = createKey("glowing_stick_fruit_vines");
        public static final RegistryKey<Block> GLOWING_STICK_FRUIT_VINES_PLANT = createKey("glowing_stick_fruit_vines_plant");
        public static final RegistryKey<Block> BLOSSOMING_VINES = createKey("blossoming_vines");
        public static final RegistryKey<Block> BLOSSOMING_VINES_PLANT = createKey("blossoming_vines_plant");
        public static final RegistryKey<Block> LAZULI_ROOTS = createKey("lazuli_roots");
        public static final RegistryKey<Block> LAZULI_ROOTS_PLANT = createKey("lazuli_roots_plant");
        public static final RegistryKey<Block> STELLAR_ROOTS = createKey("stellar_roots");
        public static final RegistryKey<Block> STELLAR_ROOTS_PLANT = createKey("stellar_roots_plant");
        public static final RegistryKey<Block> DEAD_ROOTS = createKey("dead_roots");
        public static final RegistryKey<Block> DEAD_ROOTS_PLANT = createKey("dead_roots_plant");
        public static final RegistryKey<Block> GLOWING_ROOTS = createKey("glowing_roots");
        public static final RegistryKey<Block> GLOWING_ROOTS_PLANT = createKey("glowing_roots_plant");
        public static final RegistryKey<Block> SHADOW_GLOWING_ROOTS = createKey("shadow_glowing_roots");
        public static final RegistryKey<Block> SHADOW_GLOWING_ROOTS_PLANT = createKey("shadow_glowing_roots_plant");

        //grass
        public static final RegistryKey<Block> STELLAR_GRASS = createKey("stellar_grass");
        public static final RegistryKey<Block> STELLAR_GRASS_BALL = createKey("stellar_grass_ball");
        public static final RegistryKey<Block> STELLAR_FERN = createKey("stellar_fern");
        public static final RegistryKey<Block> STELLAR_TALL_GRASS = createKey("stellar_tall_grass");
        public static final RegistryKey<Block> STELLAR_TALL_FERN = createKey("stellar_tall_fern");
        public static final RegistryKey<Block> STELLAR_VERY_TALL_GRASS = createKey("stellar_very_tall_grass");
        public static final RegistryKey<Block> BLUISH_FERN = createKey("bluish_fern");
        public static final RegistryKey<Block> TALL_BLUISH_FERN = createKey("tall_bluish_fern");
        public static final RegistryKey<Block> POLYCHROME_FERN = createKey("polychrome_fern");
        public static final RegistryKey<Block> TALL_POLYCHROME_FERN = createKey("tall_polychrome_fern");
        public static final RegistryKey<Block> STELLAR_DEAD_BUSH = createKey("stellar_dead_bush");
        public static final RegistryKey<Block> BRAMBLES = createKey("brambles");
        public static final RegistryKey<Block> SHADOW_BRAMBLES = createKey("shadow_brambles");
        public static final RegistryKey<Block> SHADOW_GRASS = createKey("shadow_grass");
        public static final RegistryKey<Block> SHADOW_GRASS_BALL = createKey("shadow_grass_ball");
        public static final RegistryKey<Block> PURPLISH_STELLAR_GRASS = createKey("purplish_stellar_grass");
        public static final RegistryKey<Block> STELLAR_CLOVERS = createKey("stellar_clovers");
        public static final RegistryKey<Block> GLOWING_STELLAR_GRASS = createKey("glowing_stellar_grass");

        //flowers
        public static final RegistryKey<Block> BLUE_FLOWER = createKey("blue_flower");
        public static final RegistryKey<Block> BLACK_ROSE = createKey("black_rose");
        public static final RegistryKey<Block> BELLFLOWER = createKey("bellflower");

        //potted things
        public static final RegistryKey<Block> POTTED_BLUE_FLOWER = createKey("potted_blue_flower");
        public static final RegistryKey<Block> POTTED_BLACK_ROSE = createKey("potted_black_rose");
        public static final RegistryKey<Block> POTTED_BELLFLOWER = createKey("potted_bellflower");
        public static final RegistryKey<Block> POTTED_STELLAR_FERN = createKey("potted_stellar_fern");
        public static final RegistryKey<Block> POTTED_STELLAR_DEAD_BUSH = createKey("potted_stellar_dead_bush");
        public static final RegistryKey<Block> POTTED_SKY_CACTUS = createKey("potted_sky_cactus");
        public static final RegistryKey<Block> POTTED_VIBRANT_SKY_CACTUS = createKey("potted_vibrant_sky_cactus");
        public static final RegistryKey<Block> POTTED_AERIAL_TREE_SAPLING = createKey("potted_aerial_tree_sapling");
        public static final RegistryKey<Block> POTTED_GOLDEN_BEECH_SAPLING = createKey("potted_golden_beech_sapling");
        public static final RegistryKey<Block> POTTED_COPPER_PINE_SAPLING = createKey("potted_copper_pine_sapling");
        public static final RegistryKey<Block> POTTED_LAPIS_ROBINIA_SAPLING = createKey("potted_lapis_robinia_sapling");
        public static final RegistryKey<Block> POTTED_SHADOW_PINE_SAPLING = createKey("potted_shadow_pine_sapling");
        public static final RegistryKey<Block> POTTED_PURPLE_SHADOW_PINE_SAPLING = createKey("potted_purple_shadow_pine_sapling");
        public static final RegistryKey<Block> POTTED_STELLAR_JUNGLE_TREE_SAPLING = createKey("potted_stellar_jungle_tree_sapling");
        public static final RegistryKey<Block> POTTED_CORTINARIUS_VIOLACEUS = createKey("potted_cortinarius_violaceus");
        public static final RegistryKey<Block> POTTED_VERDIGRIS_AGARIC = createKey("potted_verdigris_agaric");
        public static final RegistryKey<Block> POTTED_VINE_BLOSSOM = createKey("potted_vine_blossom");
        public static final RegistryKey<Block> POTTED_GLOWING_BOLETUS = createKey("potted_glowing_boletus");

        //with gui
        public static final RegistryKey<Block> OSCILLATOR = createKey("oscillator");
        public static final RegistryKey<Block> FREEZER = createKey("freezer");
        public static final RegistryKey<Block> STELLAR_FURNACE = createKey("stellar_furnace");
        public static final RegistryKey<Block> GHOST_STELLAR_FURNACE = createKey("ghost_stellar_furnace");

        //chests
        public static final RegistryKey<Block> AERIAL_TREE_CHEST = createKey("aerial_tree_chest");
        public static final RegistryKey<Block> GOLDEN_BEECH_CHEST = createKey("golden_beech_chest");
        public static final RegistryKey<Block> COPPER_PINE_CHEST = createKey("copper_pine_chest");
        public static final RegistryKey<Block> LAPIS_ROBINIA_CHEST = createKey("lapis_robinia_chest");
        public static final RegistryKey<Block> SHADOW_PINE_CHEST = createKey("shadow_pine_chest");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_CHEST = createKey("stellar_jungle_tree_chest");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_CHEST = createKey("sky_cactus_fiber_chest");
        public static final RegistryKey<Block> GRAY_SHROOM_CHEST = createKey("gray_shroom_chest");
        public static final RegistryKey<Block> MUD_CHEST = createKey("mud_chest");
        public static final RegistryKey<Block> LUNATIC_CHEST = createKey("lunatic_chest");
        public static final RegistryKey<Block> VOLUCITE_CHEST = createKey("volucite_chest");
        public static final RegistryKey<Block> SHADOW_CATACOMBS_CHEST = createKey("shadow_catacombs_chest");
        public static final RegistryKey<Block> GOLDEN_NETHER_CHEST = createKey("golden_nether_chest");

        //chest mimics
        public static final RegistryKey<Block> AERIAL_TREE_CHEST_MIMIC = createKey("aerial_tree_chest_mimic");
        public static final RegistryKey<Block> GOLDEN_BEECH_CHEST_MIMIC = createKey("golden_beech_chest_mimic");
        public static final RegistryKey<Block> COPPER_PINE_CHEST_MIMIC = createKey("copper_pine_chest_mimic");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_CHEST_MIMIC = createKey("sky_cactus_fiber_chest_mimic");

        //barrel mimics
        public static final RegistryKey<Block> SHADOW_PINE_BARREL_MIMIC = createKey("shadow_pine_barrel_mimic");

        //fences, bars or walls
        public static final RegistryKey<Block> AERIAL_TREE_FENCE = createKey("aerial_tree_fence");
        public static final RegistryKey<Block> GOLDEN_BEECH_FENCE = createKey("golden_beech_fence");
        public static final RegistryKey<Block> COPPER_PINE_FENCE = createKey("copper_pine_fence");
        public static final RegistryKey<Block> LAPIS_ROBINIA_FENCE = createKey("lapis_robinia_fence");
        public static final RegistryKey<Block> SHADOW_PINE_FENCE = createKey("shadow_pine_fence");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_FENCE = createKey("stellar_jungle_tree_fence");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_FENCE = createKey("sky_cactus_fiber_fence");
        public static final RegistryKey<Block> GRAY_SHROOM_FENCE = createKey("gray_shroom_fence");
        public static final RegistryKey<Block> RUBY_BARS = createKey("ruby_bars");
        public static final RegistryKey<Block> STELLAR_STONE_WALL = createKey("stellar_stone_wall");
        public static final RegistryKey<Block> STELLAR_COBBLESTONE_WALL = createKey("stellar_cobblestone_wall");
        public static final RegistryKey<Block> STELLAR_STONE_BRICKS_WALL = createKey("stellar_stone_bricks_wall");
        public static final RegistryKey<Block> MOSSY_STELLAR_STONE_WALL = createKey("mossy_stellar_stone_wall");
        public static final RegistryKey<Block> MOSSY_STELLAR_COBBLESTONE_WALL = createKey("mossy_stellar_cobblestone_wall");
        public static final RegistryKey<Block> SLIPPERY_SAND_STONE_WALL = createKey("slippery_sand_stone_wall");
        public static final RegistryKey<Block> SLIPPERY_SAND_STONE_BRICKS_WALL = createKey("slippery_sand_stone_bricks_wall");
        public static final RegistryKey<Block> CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL = createKey("cracked_slippery_sand_stone_bricks_wall");
        public static final RegistryKey<Block> GLAUCOPHANITE_WALL = createKey("glaucophanite_wall");
        public static final RegistryKey<Block> POLISHED_GLAUCOPHANITE_WALL = createKey("polished_glaucophanite_wall");
        public static final RegistryKey<Block> MAGMATIC_GEL_WALL = createKey("magmatic_gel_wall");

        //gates
        public static final RegistryKey<Block> AERIAL_TREE_GATE = createKey("aerial_tree_gate");
        public static final RegistryKey<Block> GOLDEN_BEECH_GATE = createKey("golden_beech_gate");
        public static final RegistryKey<Block> COPPER_PINE_GATE = createKey("copper_pine_gate");
        public static final RegistryKey<Block> LAPIS_ROBINIA_GATE = createKey("lapis_robinia_gate");
        public static final RegistryKey<Block> SHADOW_PINE_GATE = createKey("shadow_pine_gate");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_GATE = createKey("stellar_jungle_tree_gate");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_GATE = createKey("sky_cactus_fiber_gate");
        public static final RegistryKey<Block> GRAY_SHROOM_GATE = createKey("gray_shroom_gate");

        //doors
        public static final RegistryKey<Block> AERIAL_TREE_DOOR = createKey("aerial_tree_door");
        public static final RegistryKey<Block> GOLDEN_BEECH_DOOR = createKey("golden_beech_door");
        public static final RegistryKey<Block> COPPER_PINE_DOOR = createKey("copper_pine_door");
        public static final RegistryKey<Block> LAPIS_ROBINIA_DOOR = createKey("lapis_robinia_door");
        public static final RegistryKey<Block> SHADOW_PINE_DOOR = createKey("shadow_pine_door");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_DOOR = createKey("stellar_jungle_tree_door");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_DOOR = createKey("sky_cactus_fiber_door");
        public static final RegistryKey<Block> GRAY_SHROOM_DOOR = createKey("gray_shroom_door");
        public static final RegistryKey<Block> RUBY_DOOR = createKey("ruby_door");

        //trapdoors
        public static final RegistryKey<Block> AERIAL_TREE_TRAPDOOR = createKey("aerial_tree_trapdoor");
        public static final RegistryKey<Block> GOLDEN_BEECH_TRAPDOOR = createKey("golden_beech_trapdoor");
        public static final RegistryKey<Block> COPPER_PINE_TRAPDOOR = createKey("copper_pine_trapdoor");
        public static final RegistryKey<Block> LAPIS_ROBINIA_TRAPDOOR = createKey("lapis_robinia_trapdoor");
        public static final RegistryKey<Block> SHADOW_PINE_TRAPDOOR = createKey("shadow_pine_trapdoor");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_TRAPDOOR = createKey("stellar_jungle_tree_trapdoor");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_TRAPDOOR = createKey("sky_cactus_fiber_trapdoor");
        public static final RegistryKey<Block> GRAY_SHROOM_TRAPDOOR = createKey("gray_shroom_trapdoor");
        public static final RegistryKey<Block> RUBY_TRAPDOOR = createKey("ruby_trapdoor");

        //buttons
        public static final RegistryKey<Block> STELLAR_STONE_BUTTON = createKey("stellar_stone_button");
        public static final RegistryKey<Block> STELLAR_COBBLESTONE_BUTTON = createKey("stellar_cobblestone_button");
        public static final RegistryKey<Block> STELLAR_STONE_BRICKS_BUTTON = createKey("stellar_stone_bricks_button");
        public static final RegistryKey<Block> SLIPPERY_SAND_STONE_BUTTON = createKey("slippery_sand_stone_button");
        public static final RegistryKey<Block> SLIPPERY_SAND_STONE_BRICKS_BUTTON = createKey("slippery_sand_stone_bricks_button");
        public static final RegistryKey<Block> AERIAL_TREE_BUTTON = createKey("aerial_tree_button");
        public static final RegistryKey<Block> GOLDEN_BEECH_BUTTON = createKey("golden_beech_button");
        public static final RegistryKey<Block> COPPER_PINE_BUTTON = createKey("copper_pine_button");
        public static final RegistryKey<Block> LAPIS_ROBINIA_BUTTON = createKey("lapis_robinia_button");
        public static final RegistryKey<Block> SHADOW_PINE_BUTTON = createKey("shadow_pine_button");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_BUTTON = createKey("stellar_jungle_tree_button");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_BUTTON = createKey("sky_cactus_fiber_button");
        public static final RegistryKey<Block> GRAY_SHROOM_BUTTON = createKey("gray_shroom_button");
        public static final RegistryKey<Block> GLAUCOPHANITE_BUTTON = createKey("glaucophanite_button");

        //pressure plates
        public static final RegistryKey<Block> STELLAR_STONE_PRESSURE_PLATE = createKey("stellar_stone_pressure_plate");
        public static final RegistryKey<Block> STELLAR_COBBLESTONE_PRESSURE_PLATE = createKey("stellar_cobblestone_pressure_plate");
        public static final RegistryKey<Block> STELLAR_STONE_BRICKS_PRESSURE_PLATE = createKey("stellar_stone_bricks_pressure_plate");
        public static final RegistryKey<Block> SLIPPERY_SAND_STONE_PRESSURE_PLATE = createKey("slippery_sand_stone_pressure_plate");
        public static final RegistryKey<Block> SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE = createKey("slippery_sand_stone_bricks_pressure_plate");
        public static final RegistryKey<Block> AERIAL_TREE_PRESSURE_PLATE = createKey("aerial_tree_pressure_plate");
        public static final RegistryKey<Block> GOLDEN_BEECH_PRESSURE_PLATE = createKey("golden_beech_pressure_plate");
        public static final RegistryKey<Block> COPPER_PINE_PRESSURE_PLATE = createKey("copper_pine_pressure_plate");
        public static final RegistryKey<Block> LAPIS_ROBINIA_PRESSURE_PLATE = createKey("lapis_robinia_pressure_plate");
        public static final RegistryKey<Block> SHADOW_PINE_PRESSURE_PLATE = createKey("shadow_pine_pressure_plate");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_PRESSURE_PLATE = createKey("stellar_jungle_tree_pressure_plate");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_PRESSURE_PLATE = createKey("sky_cactus_fiber_pressure_plate");
        public static final RegistryKey<Block> GRAY_SHROOM_PRESSURE_PLATE = createKey("gray_shroom_pressure_plate");
        public static final RegistryKey<Block> GLAUCOPHANITE_PRESSURE_PLATE = createKey("glaucophanite_pressure_plate");

        //slabs
        public static final RegistryKey<Block> AERIAL_TREE_SLAB = createKey("aerial_tree_slab");
        public static final RegistryKey<Block> GOLDEN_BEECH_SLAB = createKey("golden_beech_slab");
        public static final RegistryKey<Block> COPPER_PINE_SLAB = createKey("copper_pine_slab");
        public static final RegistryKey<Block> LAPIS_ROBINIA_SLAB = createKey("lapis_robinia_slab");
        public static final RegistryKey<Block> SHADOW_PINE_SLAB = createKey("shadow_pine_slab");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_SLAB = createKey("stellar_jungle_tree_slab");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_SLAB = createKey("sky_cactus_fiber_slab");
        public static final RegistryKey<Block> GRAY_SHROOM_SLAB = createKey("gray_shroom_slab");
        public static final RegistryKey<Block> STELLAR_STONE_SLAB = createKey("stellar_stone_slab");
        public static final RegistryKey<Block> STELLAR_COBBLESTONE_SLAB = createKey("stellar_cobblestone_slab");
        public static final RegistryKey<Block> STELLAR_STONE_BRICKS_SLAB = createKey("stellar_stone_bricks_slab");
        public static final RegistryKey<Block> MOSSY_STELLAR_STONE_SLAB = createKey("mossy_stellar_stone_slab");
        public static final RegistryKey<Block> MOSSY_STELLAR_COBBLESTONE_SLAB = createKey("mossy_stellar_cobblestone_slab");
        public static final RegistryKey<Block> SLIPPERY_SAND_STONE_SLAB = createKey("slippery_sand_stone_slab");
        public static final RegistryKey<Block> SLIPPERY_SAND_STONE_BRICKS_SLAB = createKey("slippery_sand_stone_bricks_slab");
        public static final RegistryKey<Block> CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB = createKey("cracked_slippery_sand_stone_bricks_slab");
        public static final RegistryKey<Block> POLISHED_GLAUCOPHANITE_SLAB = createKey("polished_glaucophanite_slab");
        public static final RegistryKey<Block> MAGMATIC_GEL_SLAB = createKey("magmatic_gel_slab");

        //stairs
        public static final RegistryKey<Block> AERIAL_TREE_STAIRS = createKey("aerial_tree_stairs");
        public static final RegistryKey<Block> GOLDEN_BEECH_STAIRS = createKey("golden_beech_stairs");
        public static final RegistryKey<Block> COPPER_PINE_STAIRS = createKey("copper_pine_stairs");
        public static final RegistryKey<Block> LAPIS_ROBINIA_STAIRS = createKey("lapis_robinia_stairs");
        public static final RegistryKey<Block> SHADOW_PINE_STAIRS = createKey("shadow_pine_stairs");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_STAIRS = createKey("stellar_jungle_tree_stairs");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_STAIRS = createKey("sky_cactus_fiber_stairs");
        public static final RegistryKey<Block> GRAY_SHROOM_STAIRS = createKey("gray_shroom_stairs");
        public static final RegistryKey<Block> STELLAR_STONE_STAIRS = createKey("stellar_stone_stairs");
        public static final RegistryKey<Block> STELLAR_COBBLESTONE_STAIRS = createKey("stellar_cobblestone_stairs");
        public static final RegistryKey<Block> STELLAR_STONE_BRICKS_STAIRS = createKey("stellar_stone_bricks_stairs");
        public static final RegistryKey<Block> MOSSY_STELLAR_STONE_STAIRS = createKey("mossy_stellar_stone_stairs");
        public static final RegistryKey<Block> MOSSY_STELLAR_COBBLESTONE_STAIRS = createKey("mossy_stellar_cobblestone_stairs");
        public static final RegistryKey<Block> SLIPPERY_SAND_STONE_STAIRS = createKey("slippery_sand_stone_stairs");
        public static final RegistryKey<Block> SLIPPERY_SAND_STONE_BRICKS_STAIRS = createKey("slippery_sand_stone_bricks_stairs");
        public static final RegistryKey<Block> CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS = createKey("cracked_slippery_sand_stone_bricks_stairs");
        public static final RegistryKey<Block> POLISHED_GLAUCOPHANITE_STAIRS = createKey("polished_glaucophanite_stairs");
        public static final RegistryKey<Block> MAGMATIC_GEL_STAIRS = createKey("magmatic_gel_stairs");

        //signs
        public static final RegistryKey<Block> AERIAL_TREE_STANDING_SIGN = createKey("aerial_tree_sign");
        public static final RegistryKey<Block> AERIAL_TREE_WALL_SIGN = createKey("aerial_tree_wall_sign");
        public static final RegistryKey<Block> GOLDEN_BEECH_STANDING_SIGN = createKey("golden_beech_sign");
        public static final RegistryKey<Block> GOLDEN_BEECH_WALL_SIGN = createKey("golden_beech_wall_sign");
        public static final RegistryKey<Block> COPPER_PINE_STANDING_SIGN = createKey("copper_pine_sign");
        public static final RegistryKey<Block> COPPER_PINE_WALL_SIGN = createKey("copper_pine_wall_sign");
        public static final RegistryKey<Block> LAPIS_ROBINIA_STANDING_SIGN = createKey("lapis_robinia_sign");
        public static final RegistryKey<Block> LAPIS_ROBINIA_WALL_SIGN = createKey("lapis_robinia_wall_sign");
        public static final RegistryKey<Block> SHADOW_PINE_STANDING_SIGN = createKey("shadow_pine_sign");
        public static final RegistryKey<Block> SHADOW_PINE_WALL_SIGN = createKey("shadow_pine_wall_sign");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_STANDING_SIGN = createKey("stellar_jungle_tree_sign");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_WALL_SIGN = createKey("stellar_jungle_tree_wall_sign");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_STANDING_SIGN = createKey("sky_cactus_fiber_sign");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_WALL_SIGN = createKey("sky_cactus_fiber_wall_sign");
        public static final RegistryKey<Block> GRAY_SHROOM_STANDING_SIGN = createKey("gray_shroom_sign");
        public static final RegistryKey<Block> GRAY_SHROOM_WALL_SIGN = createKey("gray_shroom_wall_sign");

        //hanging signs
        public static final RegistryKey<Block> AERIAL_TREE_HANGING_SIGN = createKey("aerial_tree_hanging_sign");
        public static final RegistryKey<Block> AERIAL_TREE_WALL_HANGING_SIGN = createKey("aerial_tree_wall_hanging_sign");
        public static final RegistryKey<Block> GOLDEN_BEECH_HANGING_SIGN = createKey("golden_beech_hanging_sign");
        public static final RegistryKey<Block> GOLDEN_BEECH_WALL_HANGING_SIGN = createKey("golden_beech_wall_hanging_sign");
        public static final RegistryKey<Block> COPPER_PINE_HANGING_SIGN = createKey("copper_pine_hanging_sign");
        public static final RegistryKey<Block> COPPER_PINE_WALL_HANGING_SIGN = createKey("copper_pine_wall_hanging_sign");
        public static final RegistryKey<Block> LAPIS_ROBINIA_HANGING_SIGN = createKey("lapis_robinia_hanging_sign");
        public static final RegistryKey<Block> LAPIS_ROBINIA_WALL_HANGING_SIGN = createKey("lapis_robinia_wall_hanging_sign");
        public static final RegistryKey<Block> SHADOW_PINE_HANGING_SIGN = createKey("shadow_pine_hanging_sign");
        public static final RegistryKey<Block> SHADOW_PINE_WALL_HANGING_SIGN = createKey("shadow_pine_wall_hanging_sign");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_HANGING_SIGN = createKey("stellar_jungle_tree_hanging_sign");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN = createKey("stellar_jungle_tree_wall_hanging_sign");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_HANGING_SIGN = createKey("sky_cactus_fiber_hanging_sign");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_WALL_HANGING_SIGN = createKey("sky_cactus_fiber_wall_hanging_sign");
        public static final RegistryKey<Block> GRAY_SHROOM_HANGING_SIGN = createKey("gray_shroom_hanging_sign");
        public static final RegistryKey<Block> GRAY_SHROOM_WALL_HANGING_SIGN = createKey("gray_shroom_wall_hanging_sign");

        //crafting tables
        public static final RegistryKey<Block> AERIAL_TREE_CRAFTING_TABLE = createKey("aerial_tree_crafting_table");
        public static final RegistryKey<Block> GOLDEN_BEECH_CRAFTING_TABLE = createKey("golden_beech_crafting_table");
        public static final RegistryKey<Block> COPPER_PINE_CRAFTING_TABLE = createKey("copper_pine_crafting_table");
        public static final RegistryKey<Block> LAPIS_ROBINIA_CRAFTING_TABLE = createKey("lapis_robinia_crafting_table");
        public static final RegistryKey<Block> SHADOW_PINE_CRAFTING_TABLE = createKey("shadow_pine_crafting_table");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_CRAFTING_TABLE = createKey("stellar_jungle_tree_crafting_table");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_CRAFTING_TABLE = createKey("sky_cactus_fiber_crafting_table");
        public static final RegistryKey<Block> GRAY_SHROOM_CRAFTING_TABLE = createKey("gray_shroom_crafting_table");

        //barrels
        public static final RegistryKey<Block> AERIAL_TREE_BARREL = createKey("aerial_tree_barrel");
        public static final RegistryKey<Block> GOLDEN_BEECH_BARREL = createKey("golden_beech_barrel");
        public static final RegistryKey<Block> COPPER_PINE_BARREL = createKey("copper_pine_barrel");
        public static final RegistryKey<Block> LAPIS_ROBINIA_BARREL = createKey("lapis_robinia_barrel");
        public static final RegistryKey<Block> SHADOW_PINE_BARREL = createKey("shadow_pine_barrel");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_BARREL = createKey("stellar_jungle_tree_barrel");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_BARREL = createKey("sky_cactus_fiber_barrel");
        public static final RegistryKey<Block> GRAY_SHROOM_BARREL = createKey("gray_shroom_barrel");

        //composters
        public static final RegistryKey<Block> AERIAL_TREE_COMPOSTER = createKey("aerial_tree_composter");
        public static final RegistryKey<Block> GOLDEN_BEECH_COMPOSTER = createKey("golden_beech_composter");
        public static final RegistryKey<Block> COPPER_PINE_COMPOSTER = createKey("copper_pine_composter");
        public static final RegistryKey<Block> LAPIS_ROBINIA_COMPOSTER = createKey("lapis_robinia_composter");
        public static final RegistryKey<Block> SHADOW_PINE_COMPOSTER = createKey("shadow_pine_composter");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_COMPOSTER = createKey("stellar_jungle_tree_composter");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_COMPOSTER = createKey("sky_cactus_fiber_composter");
        public static final RegistryKey<Block> GRAY_SHROOM_COMPOSTER = createKey("gray_shroom_composter");

        //decorative
        public static final RegistryKey<Block> AERIAL_TREE_VINE_ROPE_SPOOL = createKey("aerial_tree_vine_rope_spool");
        public static final RegistryKey<Block> GOLDEN_BEECH_VINE_ROPE_SPOOL = createKey("golden_beech_vine_rope_spool");
        public static final RegistryKey<Block> COPPER_PINE_VINE_ROPE_SPOOL = createKey("copper_pine_vine_rope_spool");
        public static final RegistryKey<Block> LAPIS_ROBINIA_VINE_ROPE_SPOOL = createKey("lapis_robinia_vine_rope_spool");
        public static final RegistryKey<Block> SHADOW_PINE_VINE_ROPE_SPOOL = createKey("shadow_pine_vine_rope_spool");
        public static final RegistryKey<Block> STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL = createKey("stellar_jungle_tree_vine_rope_spool");
        public static final RegistryKey<Block> SKY_CACTUS_FIBER_VINE_ROPE_SPOOL = createKey("sky_cactus_fiber_vine_rope_spool");
        public static final RegistryKey<Block> GRAY_SHROOM_VINE_ROPE_SPOOL = createKey("gray_shroom_vine_rope_spool");

        //fluids
        public static final RegistryKey<Block> LIQUID_OF_THE_GODS = createKey("liquid_of_the_gods");

        private static RegistryKey<Block> createKey(String name)
        {
            return RegistryKey.of(RegistryKeys.BLOCK, AerialHell.id(name));
        }
    }

    public static <T extends Block> T register(String name, T block) {return Registry.register(Registries.BLOCK, AerialHell.id(name), block);}

    public static void load() {}
}
