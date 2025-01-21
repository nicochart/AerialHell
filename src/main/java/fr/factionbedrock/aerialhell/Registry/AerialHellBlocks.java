package fr.factionbedrock.aerialhell.Registry;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

import java.util.function.ToIntFunction;

import com.google.common.collect.ImmutableMap;

import fr.factionbedrock.aerialhell.Block.*;
import fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther.*;
import fr.factionbedrock.aerialhell.Block.CorruptionProtectors.*;
import fr.factionbedrock.aerialhell.Block.DirtAndVariants.*;
import fr.factionbedrock.aerialhell.Block.DungeonCores.*;
import fr.factionbedrock.aerialhell.Block.Furnaces.*;
import fr.factionbedrock.aerialhell.Block.Mimics.*;
import fr.factionbedrock.aerialhell.Block.Ores.*;
import fr.factionbedrock.aerialhell.Block.Plants.*;
import fr.factionbedrock.aerialhell.Block.Plants.Bushes.*;
import fr.factionbedrock.aerialhell.Block.Plants.Vines.*;
import fr.factionbedrock.aerialhell.Block.CollisionCondition.*;
import fr.factionbedrock.aerialhell.Block.ShadowSpreader.*;
import fr.factionbedrock.aerialhell.Block.StandingAndWall.*;
import fr.factionbedrock.aerialhell.Block.Trophies.BottomSlabLikeTrophyBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellTreeGrowers;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AerialHellBlocks
{
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

	public static void registerPots() {
		FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;

		pot.addPlant(BLUE_FLOWER.getId(), POTTED_BLUE_FLOWER);
		pot.addPlant(BLACK_ROSE.getId(), POTTED_BLACK_ROSE);
		pot.addPlant(BELLFLOWER.getId(), POTTED_BELLFLOWER);
		pot.addPlant(STELLAR_FERN.getId(), POTTED_STELLAR_FERN);
		pot.addPlant(STELLAR_DEAD_BUSH.getId(), POTTED_STELLAR_DEAD_BUSH);
		pot.addPlant(SKY_CACTUS.getId(), POTTED_SKY_CACTUS);
		pot.addPlant(VIBRANT_SKY_CACTUS.getId(), POTTED_VIBRANT_SKY_CACTUS);
		pot.addPlant(AERIAL_TREE_SAPLING.getId(), POTTED_AERIAL_TREE_SAPLING);
		pot.addPlant(GOLDEN_BEECH_SAPLING.getId(), POTTED_GOLDEN_BEECH_SAPLING);
		pot.addPlant(COPPER_PINE_SAPLING.getId(), POTTED_COPPER_PINE_SAPLING);
		pot.addPlant(LAPIS_ROBINIA_SAPLING.getId(), POTTED_LAPIS_ROBINIA_SAPLING);
		pot.addPlant(SHADOW_PINE_SAPLING.getId(), POTTED_SHADOW_PINE_SAPLING);
		pot.addPlant(PURPLE_SHADOW_PINE_SAPLING.getId(), POTTED_PURPLE_SHADOW_PINE_SAPLING);
		pot.addPlant(STELLAR_JUNGLE_TREE_SAPLING.getId(), POTTED_STELLAR_JUNGLE_TREE_SAPLING);
		pot.addPlant(CORTINARIUS_VIOLACEUS.getId(), POTTED_CORTINARIUS_VIOLACEUS);
		pot.addPlant(VERDIGRIS_AGARIC.getId(), POTTED_VERDIGRIS_AGARIC);
		pot.addPlant(BLOSSOMING_VINES.getId(), POTTED_VINE_BLOSSOM);
		pot.addPlant(GLOWING_BOLETUS.getId(), POTTED_GLOWING_BOLETUS);
	}

	public static void registerAxeStrippingBlocks() {
		AxeItem.STRIPPABLES = ImmutableMap.<Block, Block>builder()
				.putAll(AxeItem.STRIPPABLES)
				.put(AERIAL_TREE_LOG.get(), STRIPPED_AERIAL_TREE_LOG.get())
				.put(AERIAL_TREE_WOOD.get(), STRIPPED_AERIAL_TREE_WOOD.get())
				.put(GOLDEN_BEECH_LOG.get(), STRIPPED_GOLDEN_BEECH_LOG.get())
				.put(GOLDEN_BEECH_WOOD.get(), STRIPPED_GOLDEN_BEECH_WOOD.get())
				.put(COPPER_PINE_LOG.get(), STRIPPED_COPPER_PINE_LOG.get())
				.put(COPPER_PINE_WOOD.get(), STRIPPED_COPPER_PINE_WOOD.get())
				.put(LAPIS_ROBINIA_LOG.get(), STRIPPED_LAPIS_ROBINIA_LOG.get())
				.put(LAPIS_ROBINIA_WOOD.get(), STRIPPED_LAPIS_ROBINIA_WOOD.get())
				.put(SHADOW_PINE_LOG.get(), STRIPPED_SHADOW_PINE_LOG.get())
				.put(SHADOW_PINE_WOOD.get(), STRIPPED_SHADOW_PINE_WOOD.get())
				.put(STELLAR_JUNGLE_TREE_LOG.get(), STRIPPED_STELLAR_JUNGLE_TREE_LOG.get())
				.put(STELLAR_JUNGLE_TREE_WOOD.get(), STRIPPED_STELLAR_JUNGLE_TREE_WOOD.get())
				.put(GIANT_CORTINARIUS_VIOLACEUS_STEM.get(), STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM.get())
				.put(GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.get(), STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.get())
				.put(GIANT_VERDIGRIS_AGARIC_STEM.get(), STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM.get())
				.put(GIANT_VERDIGRIS_AGARIC_BARK_STEM.get(), STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM.get())
				.build();
	}

	//materials
	public static BlockBehaviour.Properties AERIAL_TREE_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.5F, 2.5F).sound(SoundType.WOOD);
	public static BlockBehaviour.Properties COPPER_PINE_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(4.5F, 4.5F).sound(SoundType.WOOD);
	public static BlockBehaviour.Properties SHADOW_PINE_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(4.0F, 4.0F).sound(SoundType.WOOD);
	public static BlockBehaviour.Properties SHROOM_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(3.5F, 3.5F).sound(SoundType.STEM);
	public static BlockBehaviour.Properties AERIAL_TREE_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion().noCollission();
	public static BlockBehaviour.Properties COPPER_PINE_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(4.5F, 4.5F).sound(SoundType.WOOD).noOcclusion().noCollission();
	public static BlockBehaviour.Properties SHADOW_PINE_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(4.0F, 4.0F).sound(SoundType.WOOD).noOcclusion().noCollission();
	public static BlockBehaviour.Properties SKY_CACTUS_FIBER_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.5F, 2.5F).sound(SoundType.WOOD);
	public static BlockBehaviour.Properties SKY_CACTUS_FIBER_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion().noCollission();
	public static BlockBehaviour.Properties SHROOM_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(3.5F, 3.5F).sound(SoundType.WOOD).noOcclusion().noCollission();
	public static BlockBehaviour.Properties MUD_CHEST_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(20.0F, 1000.0F).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion();
	public static BlockBehaviour.Properties LUNATIC_CHEST_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).strength(30.0F, 1000.0F).sound(SoundType.NETHERITE_BLOCK).requiresCorrectToolForDrops().noOcclusion();
	public static BlockBehaviour.Properties VOLUCITE_CHEST_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).strength(50.0F, 1200.0F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion();
	public static BlockBehaviour.Properties GOLDEN_NETHER_CHEST_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).strength(50.0F, 1200.0F).sound(SoundType.BASALT).requiresCorrectToolForDrops().noOcclusion();
	public static BlockBehaviour.Properties METAL_NOTSOLID_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(10.0F, 2.0F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion();

	//portal
	public static final DeferredBlock<AerialHellPortalBlock> AERIAL_HELL_PORTAL = BLOCKS.register("aerial_hell_portal", () -> new AerialHellPortalBlock(BlockBehaviour.Properties.of().sound(SoundType.GLASS).strength(-1F).noCollission().lightLevel((state) -> 10).noLootTable()));
	public static final DeferredBlock<Block> STELLAR_PORTAL_FRAME_BLOCK = BLOCKS.register("stellar_portal_frame_block", () -> new Block((BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(25.0F, 600.0F))));
	public static final DeferredBlock<Block> STELLAR_PORTAL_FRAME_ORE = BLOCKS.register("stellar_portal_frame_ore",() -> new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().strength(25.0F, 600.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEEPSLATE_STELLAR_PORTAL_FRAME_ORE = BLOCKS.register("deepslate_stellar_portal_frame_ore",() -> new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().strength(30.0F, 600.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));

	//torch
	public static final DeferredBlock<Block> FLUORITE_WALL_TORCH = BLOCKS.register("fluorite_wall_torch", () -> new AerialHellWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH)));
	public static final DeferredBlock<Block> FLUORITE_TORCH = BLOCKS.register("fluorite_torch", () -> new AerialHellTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH)));
	public static final DeferredBlock<Block> VOLUCITE_WALL_TORCH = BLOCKS.register("volucite_wall_torch", () -> new AerialHellWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).lightLevel((state) -> {return 9;})));
	public static final DeferredBlock<Block> VOLUCITE_TORCH = BLOCKS.register("volucite_torch", () -> new AerialHellTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).lightLevel((state) -> {return 9;})));
	public static final DeferredBlock<Block> SHADOW_WALL_TORCH = BLOCKS.register("shadow_wall_torch", () -> new AerialHellWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).lightLevel((state) -> {return 9;})));
	public static final DeferredBlock<Block> SHADOW_TORCH = BLOCKS.register("shadow_torch", () -> new AerialHellTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).lightLevel((state) -> {return 9;})));

	//lanterns
	public static final DeferredBlock<Block> FLUORITE_LANTERN = BLOCKS.register("fluorite_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
	public static final DeferredBlock<Block> RUBY_LANTERN = BLOCKS.register("ruby_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
	public static final DeferredBlock<Block> RUBY_FLUORITE_LANTERN = BLOCKS.register("ruby_fluorite_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
	public static final DeferredBlock<Block> VOLUCITE_LANTERN = BLOCKS.register("volucite_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
	public static final DeferredBlock<Block> VOLUCITE_FLUORITE_LANTERN = BLOCKS.register("volucite_fluorite_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
	public static final DeferredBlock<Block> LUNATIC_LANTERN = BLOCKS.register("lunatic_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
	public static final DeferredBlock<Block> SHADOW_LANTERN = BLOCKS.register("shadow_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_LANTERN)));

	//chains
	public static final DeferredBlock<ChainBlock> RUBY_CHAIN = BLOCKS.register("ruby_chain", () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
	public static final DeferredBlock<ChainBlock> VOLUCITE_CHAIN = BLOCKS.register("volucite_chain", () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
	public static final DeferredBlock<ChainBlock> LUNATIC_CHAIN = BLOCKS.register("lunatic_chain", () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
	public static final DeferredBlock<ChainBlock> SHADOW_CHAIN = BLOCKS.register("shadow_chain", () -> new ShadowChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));

	//grass & dirt
	public static final DeferredBlock<StellarGrassBlock> STELLAR_GRASS_BLOCK = BLOCKS.register("stellar_grass_block", () -> new StellarGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)));
	public static final DeferredBlock<Block> CHISELED_STELLAR_GRASS_BLOCK = BLOCKS.register("chiseled_stellar_grass_block", () -> new StellarGrassBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_GRASS_BLOCK.get())));
	public static final DeferredBlock<Block> STELLAR_DIRT = BLOCKS.register("stellar_dirt", () -> new StellarDirtBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)));
	public static final DeferredBlock<Block> STELLAR_COARSE_DIRT = BLOCKS.register("stellar_coarse_dirt", () -> new StellarDirtBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COARSE_DIRT)));
	public static final DeferredBlock<Block> STELLAR_FARMLAND = BLOCKS.register("stellar_farmland", () -> new StellarFarmBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).randomTicks().strength(0.6F).sound(SoundType.GRAVEL).isViewBlocking((state, blockgetter, pos) -> true).isSuffocating((state, blockgetter, pos) -> true)));
	public static final DeferredBlock<Block> STELLAR_DIRT_PATH = BLOCKS.register("stellar_dirt_path", () -> new StellarDirtPathBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT_PATH)));
	public static final DeferredBlock<Block> STELLAR_PODZOL = BLOCKS.register("stellar_podzol", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL)));
	public static final DeferredBlock<Block> STELLAR_CRYSTAL_PODZOL = BLOCKS.register("stellar_crystal_podzol", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL)));
	public static final DeferredBlock<Block> CHISELED_STELLAR_DIRT = BLOCKS.register("chiseled_stellar_dirt", () -> new StellarDirtBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_DIRT.get())));
	public static final DeferredBlock<ShadowGrassBlock> SHADOW_GRASS_BLOCK = BLOCKS.register("shadow_grass_block", () -> new ShadowGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)));

	//slippery sand
	public static final DeferredBlock<Block> SLIPPERY_SAND = BLOCKS.register("slippery_sand", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).friction(1.025F)));
	public static final DeferredBlock<Block> SLIPPERY_SAND_STONE = BLOCKS.register("slippery_sand_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).friction(1.01F)));
	public static final DeferredBlock<Block> SLIPPERY_SAND_STONE_BRICKS = BLOCKS.register("slippery_sand_stone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get()).friction(1.005F)));
	public static final DeferredBlock<Block> CUT_SLIPPERY_SAND_STONE = BLOCKS.register("cut_slippery_sand_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get()).friction(1.005F)));
	public static final DeferredBlock<Block> CRACKED_SLIPPERY_SAND_STONE_BRICKS = BLOCKS.register("cracked_slippery_sand_stone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get()).friction(1.003F)));

	//giant root
	public static final DeferredBlock<RotatedPillarBlock> GIANT_ROOT = BLOCKS.register("giant_root", () -> new RotatedPillarBlock(AERIAL_TREE_MATERIAL));

    //aerial_tree
	public static final DeferredBlock<ShiftableLogBlock> AERIAL_TREE_LOG = BLOCKS.register("aerial_tree_log", () -> new ShiftableLogBlock(AERIAL_TREE_MATERIAL, () -> AerialHellBlocks.SHADOW_AERIAL_TREE_LOG.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_AERIAL_TREE_LOG = BLOCKS.register("stripped_aerial_tree_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get())));
	public static final DeferredBlock<RotatedPillarBlock> AERIAL_TREE_WOOD = BLOCKS.register("aerial_tree_wood", () -> new RotatedPillarBlock(AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_AERIAL_TREE_WOOD = BLOCKS.register("stripped_aerial_tree_wood", () -> new RotatedPillarBlock(AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<ShiftableLeavesBlock> AERIAL_TREE_LEAVES = BLOCKS.register("aerial_tree_leaves", () -> new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.SHADOW_AERIAL_TREE_LEAVES.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<Block> AERIAL_TREE_PLANKS = BLOCKS.register("aerial_tree_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get())));
	public static final DeferredBlock<Block> CHISELED_AERIAL_TREE_PLANKS = BLOCKS.register("chiseled_aerial_tree_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS.get())));
	public static final DeferredBlock<Block> AERIAL_TREE_BOOKSHELF = BLOCKS.register("aerial_tree_bookshelf", () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS.get())));
	public static final DeferredBlock<SaplingBlock> AERIAL_TREE_SAPLING = BLOCKS.register("aerial_tree_sapling", () -> new AerialHellSaplingBlock(AerialHellTreeGrowers.AERIAL_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_AERIAL_TREE));

	//petrified aerial tree
	public static final DeferredBlock<RotatedPillarBlock> PETRIFIED_AERIAL_TREE_LOG = BLOCKS.register("petrified_aerial_tree_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get())));

	//golden beech
	public static final DeferredBlock<ShiftableLogBlock> GOLDEN_BEECH_LOG = BLOCKS.register("golden_beech_log", () -> new ShiftableLogBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get()), () -> AerialHellBlocks.SHADOW_GOLDEN_BEECH_LOG.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GOLDEN_BEECH_LOG = BLOCKS.register("stripped_golden_beech_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG.get())));
	public static final DeferredBlock<RotatedPillarBlock> GOLDEN_BEECH_WOOD = BLOCKS.register("golden_beech_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG.get())));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GOLDEN_BEECH_WOOD = BLOCKS.register("stripped_golden_beech_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG.get())));
	public static final DeferredBlock<Block> GOLDEN_BEECH_PLANKS = BLOCKS.register("golden_beech_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG.get())));
	public static final DeferredBlock<Block> CHISELED_GOLDEN_BEECH_PLANKS = BLOCKS.register("chiseled_golden_beech_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS.get())));
	public static final DeferredBlock<ShiftableLeavesBlock> GOLDEN_BEECH_LEAVES = BLOCKS.register("golden_beech_leaves", () -> new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.SHADOW_GOLDEN_BEECH_LEAVES.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<Block> GOLDEN_BEECH_BOOKSHELF = BLOCKS.register("golden_beech_bookshelf", () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS.get())));
	public static final DeferredBlock<SaplingBlock> GOLDEN_BEECH_SAPLING = BLOCKS.register("golden_beech_sapling", () -> new SaplingBlock(AerialHellTreeGrowers.GOLDEN_BEECH, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

	//cropper pine
	public static final DeferredBlock<ShiftableLogBlock> COPPER_PINE_LOG = BLOCKS.register("copper_pine_log", () -> new ShiftableLogBlock(COPPER_PINE_MATERIAL, () -> AerialHellBlocks.SHADOW_COPPER_PINE_LOG.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_COPPER_PINE_LOG = BLOCKS.register("stripped_copper_pine_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG.get())));
	public static final DeferredBlock<RotatedPillarBlock> COPPER_PINE_WOOD = BLOCKS.register("copper_pine_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG.get())));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_COPPER_PINE_WOOD = BLOCKS.register("stripped_copper_pine_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG.get())));
	public static final DeferredBlock<Block> COPPER_PINE_PLANKS = BLOCKS.register("copper_pine_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG.get())));
	public static final DeferredBlock<ShiftableLeavesBlock> COPPER_PINE_LEAVES = BLOCKS.register("copper_pine_leaves", () -> new LeavesWithAmbientParticlesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.SHADOW_COPPER_PINE_LEAVES.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<Block> COPPER_PINE_BOOKSHELF = BLOCKS.register("copper_pine_bookshelf", () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_PLANKS.get())));
	public static final DeferredBlock<SaplingBlock> COPPER_PINE_SAPLING = BLOCKS.register("copper_pine_sapling", () -> new AerialHellSaplingBlock(AerialHellTreeGrowers.COPPER_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_COPPER_PINE, AerialHellConfiguredFeatures.HUGE_COPPER_PINE, 0.1F));

	//lapis robinia
	public static final DeferredBlock<ShiftableLogBlock> LAPIS_ROBINIA_LOG = BLOCKS.register("lapis_robinia_log", () -> new ShiftableLogBlock(COPPER_PINE_MATERIAL, () -> AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LOG.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<EffectLogBlock> ENCHANTED_LAPIS_ROBINIA_LOG = BLOCKS.register("enchanted_lapis_robinia_log", () -> new EffectLogBlock(COPPER_PINE_MATERIAL, () -> AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LOG.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_LAPIS_ROBINIA_LOG = BLOCKS.register("stripped_lapis_robinia_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG.get())));
	public static final DeferredBlock<RotatedPillarBlock> LAPIS_ROBINIA_WOOD = BLOCKS.register("lapis_robinia_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG.get())));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_LAPIS_ROBINIA_WOOD = BLOCKS.register("stripped_lapis_robinia_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG.get())));
	public static final DeferredBlock<ShiftableLeavesBlock> LAPIS_ROBINIA_LEAVES = BLOCKS.register("lapis_robinia_leaves", () -> new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LEAVES.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<Block> LAPIS_ROBINIA_PLANKS = BLOCKS.register("lapis_robinia_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG.get())));
	public static final DeferredBlock<Block> LAPIS_ROBINIA_BOOKSHELF = BLOCKS.register("lapis_robinia_bookshelf", () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_PLANKS.get())));
	public static final DeferredBlock<SaplingBlock> LAPIS_ROBINIA_SAPLING = BLOCKS.register("lapis_robinia_sapling", () -> new AerialHellSaplingBlock(AerialHellTreeGrowers.LAPIS_ROBINIA, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_LAPIS_ROBINIA));

	//shadow_pine
	public static final DeferredBlock<ShiftableLogBlock> SHADOW_PINE_LOG = BLOCKS.register("shadow_pine_log", () -> new ShadowLogBlock(SHADOW_PINE_MATERIAL, () -> AerialHellBlocks.HOLLOW_SHADOW_PINE_LOG.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShiftableLogBlock> EYE_SHADOW_PINE_LOG = BLOCKS.register("eye_shadow_pine_log", () -> new ShadowEffectLogBlock(SHADOW_PINE_MATERIAL, () -> AerialHellBlocks.HOLLOW_SHADOW_PINE_LOG.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_SHADOW_PINE_LOG = BLOCKS.register("stripped_shadow_pine_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG.get())));
	public static final DeferredBlock<RotatedPillarBlock> SHADOW_PINE_WOOD = BLOCKS.register("shadow_pine_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG.get())));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_SHADOW_PINE_WOOD = BLOCKS.register("stripped_shadow_pine_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG.get())));
	public static final DeferredBlock<ShiftableLeavesBlock> SHADOW_PINE_LEAVES = BLOCKS.register("shadow_pine_leaves", () -> new ShadowLeavesWithParticlesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.HOLLOW_SHADOW_PINE_LEAVES.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShiftableLeavesBlock> PURPLE_SHADOW_PINE_LEAVES = BLOCKS.register("purple_shadow_pine_leaves", () -> new ShadowLeavesWithParticlesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.HOLLOW_PURPLE_SHADOW_PINE_LEAVES.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<Block> SHADOW_PINE_PLANKS = BLOCKS.register("shadow_pine_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG.get())));
	public static final DeferredBlock<Block> SHADOW_PINE_BOOKSHELF = BLOCKS.register("shadow_pine_bookshelf", () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_PLANKS.get())));
	public static final DeferredBlock<SaplingBlock> SHADOW_PINE_SAPLING = BLOCKS.register("shadow_pine_sapling", () -> new ShadowPineSaplingBlock(AerialHellTreeGrowers.SHADOW_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_SHADOW_PINE, AerialHellConfiguredFeatures.HUGE_SHADOW_PINE, 0.1F));
	public static final DeferredBlock<SaplingBlock> PURPLE_SHADOW_PINE_SAPLING = BLOCKS.register("purple_shadow_pine_sapling", () -> new ShadowPineSaplingBlock(AerialHellTreeGrowers.PURPLE_SHADOW_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_PURPLE_SHADOW_PINE, AerialHellConfiguredFeatures.HUGE_PURPLE_SHADOW_PINE, 0.1F));

	//stellar jungle tree
	public static final DeferredBlock<ShiftableLogBlock> STELLAR_JUNGLE_TREE_LOG = BLOCKS.register("stellar_jungle_tree_log", () -> new ShiftableLogBlock(COPPER_PINE_MATERIAL, () -> AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LOG.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_STELLAR_JUNGLE_TREE_LOG = BLOCKS.register("stripped_stellar_jungle_tree_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG.get())));
	public static final DeferredBlock<RotatedPillarBlock> STELLAR_JUNGLE_TREE_WOOD = BLOCKS.register("stellar_jungle_tree_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG.get())));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_STELLAR_JUNGLE_TREE_WOOD = BLOCKS.register("stripped_stellar_jungle_tree_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG.get())));
	public static final DeferredBlock<ShiftableLeavesBlock> STELLAR_JUNGLE_TREE_LEAVES = BLOCKS.register("stellar_jungle_tree_leaves", () -> new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<Block> STELLAR_JUNGLE_TREE_PLANKS = BLOCKS.register("stellar_jungle_tree_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG.get())));
	public static final DeferredBlock<Block> STELLAR_JUNGLE_TREE_BOOKSHELF = BLOCKS.register("stellar_jungle_tree_bookshelf", () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_PLANKS.get())));
	public static final DeferredBlock<SaplingBlock> STELLAR_JUNGLE_TREE_SAPLING = BLOCKS.register("stellar_jungle_tree_sapling", () -> new AerialHellSaplingBlock(AerialHellTreeGrowers.STELLAR_JUNGLE_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_STELLAR_JUNGLE_TREE));
	public static final DeferredBlock<LargeDeadLogBlock> DEAD_STELLAR_JUNGLE_TREE_LOG = BLOCKS.register("dead_stellar_jungle_tree_log", () -> new LargeDeadLogBlock(STELLAR_JUNGLE_TREE_PLANKS.get().defaultBlockState(), COPPER_PINE_MATERIAL));

	//shroom
	public static final DeferredBlock<RotatedPillarBlock> GIANT_CORTINARIUS_VIOLACEUS_STEM = BLOCKS.register("giant_cortinarius_violaceus_stem", () -> new RotatedPillarBlock(SHROOM_MATERIAL));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM = BLOCKS.register("stripped_giant_cortinarius_violaceus_stem", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get())));
	public static final DeferredBlock<RotatedPillarBlock> GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = BLOCKS.register("giant_cortinarius_violaceus_bark_stem", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get())));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = BLOCKS.register("stripped_giant_cortinarius_violaceus_bark_stem", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get())));
	public static final DeferredBlock<Block> GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK = BLOCKS.register("giant_cortinarius_violaceus_cap_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).strength(0.5F).sound(SoundType.STEM)));
	public static final DeferredBlock<Block> GIANT_CORTINARIUS_VIOLACEUS_LIGHT = BLOCKS.register("giant_cortinarius_violaceus_light", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(1.0F).sound(SoundType.SHROOMLIGHT).lightLevel((state) -> {return 15;})));
	public static final DeferredBlock<FungusBlock> CORTINARIUS_VIOLACEUS = BLOCKS.register("cortinarius_violaceus", () -> new AerialHellFungusBlock(AerialHellConfiguredFeatures.GIANT_CORTINARIUS_VIOLACEUS_PLANTED, STELLAR_GRASS_BLOCK.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FUNGUS)));
	public static final DeferredBlock<Block> GLOWING_BOLETUS = BLOCKS.register("glowing_boletus", () -> new AerialHellTallShroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().lightLevel((state) -> {return 9;}).instabreak().sound(SoundType.GLOW_LICHEN), true));
	public static final DeferredBlock<Block> TALL_GLOWING_BOLETUS = BLOCKS.register("tall_glowing_boletus", () -> new DoubleShroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().lightLevel((state) -> {return 11;}).instabreak().sound(SoundType.GLOW_LICHEN)));
	public static final DeferredBlock<Block> BLUE_MEANIE_CLUSTER = BLOCKS.register("blue_meanie_cluster", () -> new TallShroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)));
	public static final DeferredBlock<Block> GIANT_ROOT_SHROOM = BLOCKS.register("giant_root_shroom", () -> new AerialHellTallShroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.NETHER_WART), false));

	public static final DeferredBlock<RotatedPillarBlock> GIANT_VERDIGRIS_AGARIC_STEM = BLOCKS.register("giant_verdigris_agaric_stem", () -> new RotatedPillarBlock(SHROOM_MATERIAL));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM = BLOCKS.register("stripped_giant_verdigris_agaric_stem", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get())));
	public static final DeferredBlock<RotatedPillarBlock> GIANT_VERDIGRIS_AGARIC_BARK_STEM = BLOCKS.register("giant_verdigris_agaric_bark_stem", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get())));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM = BLOCKS.register("stripped_giant_verdigris_agaric_bark_stem", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get())));
	public static final DeferredBlock<Block> GIANT_VERDIGRIS_AGARIC_CAP_BLOCK = BLOCKS.register("giant_verdigris_agaric_cap_block", () -> new HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).lightLevel((state) -> {return 10;}).strength(0.4F).sound(SoundType.STEM)));
	public static final DeferredBlock<MushroomBlock> VERDIGRIS_AGARIC = BLOCKS.register("verdigris_agaric", () -> new AerialHellMushroomBlock(AerialHellConfiguredFeatures.GIANT_VERDIGRIS_AGARIC, BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));

	public static final DeferredBlock<Block> GIANT_GANODERMA_APPLANATUM_BLOCK = BLOCKS.register("giant_ganoderma_applanatum_block", () -> new HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.4F).sound(SoundType.STEM)));

	public static final DeferredBlock<Block> GRAY_SHROOM_PLANKS = BLOCKS.register("gray_shroom_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get())));
	public static final DeferredBlock<Block> GRAY_SHROOM_BOOKSHELF = BLOCKS.register("gray_shroom_bookshelf", () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_PLANKS.get())));

	//shadow corrupted / uncorrupted variants
	public static final DeferredBlock<ShadowLogBlock> SHADOW_AERIAL_TREE_LOG = BLOCKS.register("shadow_aerial_tree_log", () -> new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get()), () -> AERIAL_TREE_LOG.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShadowLogBlock> SHADOW_GOLDEN_BEECH_LOG = BLOCKS.register("shadow_golden_beech_log", () -> new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG.get()), () -> GOLDEN_BEECH_LOG.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShadowLogBlock> SHADOW_COPPER_PINE_LOG = BLOCKS.register("shadow_copper_pine_log", () -> new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG.get()), () -> COPPER_PINE_LOG.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShadowLogBlock> SHADOW_LAPIS_ROBINIA_LOG = BLOCKS.register("shadow_lapis_robinia_log", () -> new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG.get()), () -> LAPIS_ROBINIA_LOG.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShadowLogBlock> SHADOW_STELLAR_JUNGLE_TREE_LOG = BLOCKS.register("shadow_stellar_jungle_tree_log", () -> new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG.get()), () -> STELLAR_JUNGLE_TREE_LOG.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShiftableLogBlock> HOLLOW_SHADOW_PINE_LOG = BLOCKS.register("hollow_shadow_pine_log", () -> new ShiftableLogBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get()), () -> SHADOW_PINE_LOG.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<ShiftableLeavesBlock> SHADOW_AERIAL_TREE_LEAVES = BLOCKS.register("shadow_aerial_tree_leaves", () -> new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LEAVES.get()), () -> AerialHellBlocks.AERIAL_TREE_LEAVES.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShiftableLeavesBlock> SHADOW_GOLDEN_BEECH_LEAVES = BLOCKS.register("shadow_golden_beech_leaves", () -> new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LEAVES.get()), () -> AerialHellBlocks.GOLDEN_BEECH_LEAVES.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShiftableLeavesBlock> SHADOW_COPPER_PINE_LEAVES = BLOCKS.register("shadow_copper_pine_leaves", () -> new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LEAVES.get()), () -> AerialHellBlocks.COPPER_PINE_LEAVES.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShiftableLeavesBlock> SHADOW_LAPIS_ROBINIA_LEAVES = BLOCKS.register("shadow_lapis_robinia_leaves", () -> new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LEAVES.get()), () -> AerialHellBlocks.LAPIS_ROBINIA_LEAVES.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShiftableLeavesBlock> SHADOW_STELLAR_JUNGLE_TREE_LEAVES = BLOCKS.register("shadow_stellar_jungle_tree_leaves", () -> new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LEAVES.get()), () -> AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShiftableLeavesBlock> HOLLOW_SHADOW_PINE_LEAVES = BLOCKS.register("hollow_shadow_pine_leaves", () -> new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LEAVES.get()), () -> AerialHellBlocks.SHADOW_PINE_LEAVES.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<ShiftableLeavesBlock> HOLLOW_PURPLE_SHADOW_PINE_LEAVES = BLOCKS.register("hollow_purple_shadow_pine_leaves", () -> new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(PURPLE_SHADOW_PINE_LEAVES.get()), () -> AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES.get(), BiomeShifter.ShiftType.CORRUPT));

	//ladder
	public static final DeferredBlock<LadderBlock> SKY_LADDER = BLOCKS.register("sky_ladder", () -> new LadderBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS.get()).noOcclusion()));

	//natural blocks and items
	public static final DeferredBlock<Block> STELLAR_STONE = BLOCKS.register("stellar_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
	public static final DeferredBlock<Block> STELLAR_COBBLESTONE = BLOCKS.register("stellar_cobblestone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
	public static final DeferredBlock<Block> STELLAR_STONE_BRICKS = BLOCKS.register("stellar_stone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).strength(0.5F, 10.0F)));
	public static final DeferredBlock<Block> MOSSY_STELLAR_STONE = BLOCKS.register("mossy_stellar_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get())));
	public static final DeferredBlock<Block> MOSSY_STELLAR_COBBLESTONE = BLOCKS.register("mossy_stellar_cobblestone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get())));
	public static final DeferredBlock<Block> STELLAR_CLAY = BLOCKS.register("stellar_clay", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CLAY)));
	public static final DeferredBlock<Block> GLAUCOPHANITE = BLOCKS.register("glaucophanite",() -> new Block(BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> POLISHED_GLAUCOPHANITE = BLOCKS.register("polished_glaucophanite",() -> new Block(BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> SHADOW_STONE = BLOCKS.register("shadow_stone", () -> new ShadowStoneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

	//crystal
	public static final DeferredBlock<Block> CRYSTAL_BLOCK = BLOCKS.register("crystal_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).lightLevel((state) -> 14)));
	public static final DeferredBlock<Block> CRYSTAL_BRICKS = BLOCKS.register("crystal_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).lightLevel((state) -> 9)));
	public static final DeferredBlock<SlabBlock> CRYSTAL_BRICKS_SLAB = BLOCKS.register("crystal_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BRICKS.get())));
	public static final DeferredBlock<StairBlock> CRYSTAL_BRICKS_STAIRS = BLOCKS.register("crystal_bricks_stairs", () -> new StairBlock(CRYSTAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BRICKS.get())));
	public static final DeferredBlock<WallBlock> CRYSTAL_BRICKS_WALL = BLOCKS.register("crystal_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BRICKS.get())));
	public static final DeferredBlock<Block> STELLAR_STONE_CRYSTAL_BLOCK = BLOCKS.register("stellar_stone_crystal_block", () -> new BasicShiftableRenderBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BLOCK.get()).lightLevel((state) -> 13)));
	public static final DeferredBlock<Block> SHADOW_CRYSTAL_BLOCK = BLOCKS.register("shadow_crystal_block", () -> new BasicShadowSpreaderBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BLOCK.get()).lightLevel((state) -> 12)));
	public static final DeferredBlock<Block> CRYSTALLIZED_LEAVES = BLOCKS.register("crystallized_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).lightLevel((state) -> 12)));
	public static final DeferredBlock<Block> CRYSTALLIZED_FIRE = BLOCKS.register("crystallized_fire", () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).lightLevel((state) -> 12).instabreak()));

	//glass and glass pane
	public static final DeferredBlock<Block> SLIPPERY_SAND_GLASS = BLOCKS.register("slippery_sand_glass", () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
	public static final DeferredBlock<Block> RED_SLIPPERY_SAND_GLASS = BLOCKS.register("red_slippery_sand_glass", () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
	public static final DeferredBlock<Block> BLACK_SLIPPERY_SAND_GLASS = BLOCKS.register("black_slippery_sand_glass", () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
	public static final DeferredBlock<Block> BLUE_SLIPPERY_SAND_GLASS = BLOCKS.register("blue_slippery_sand_glass", () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
	public static final DeferredBlock<Block> GREEN_SLIPPERY_SAND_GLASS = BLOCKS.register("green_slippery_sand_glass", () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
	public static final DeferredBlock<Block> SLIPPERY_SAND_GLASS_PANE = BLOCKS.register("slippery_sand_glass_pane", () -> new StainedGlassPaneBlock(DyeColor.YELLOW, BlockBehaviour.Properties.of().friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
	public static final DeferredBlock<Block> RED_SLIPPERY_SAND_GLASS_PANE = BLOCKS.register("red_slippery_sand_glass_pane", () -> new StainedGlassPaneBlock(DyeColor.RED, BlockBehaviour.Properties.of().friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
	public static final DeferredBlock<Block> BLACK_SLIPPERY_SAND_GLASS_PANE = BLOCKS.register("black_slippery_sand_glass_pane", () -> new StainedGlassPaneBlock(DyeColor.BLACK, BlockBehaviour.Properties.of().friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
	public static final DeferredBlock<Block> BLUE_SLIPPERY_SAND_GLASS_PANE = BLOCKS.register("blue_slippery_sand_glass_pane", () -> new StainedGlassPaneBlock(DyeColor.BLUE, BlockBehaviour.Properties.of().friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
	public static final DeferredBlock<Block> GREEN_SLIPPERY_SAND_GLASS_PANE = BLOCKS.register("green_slippery_sand_glass_pane", () -> new StainedGlassPaneBlock(DyeColor.GREEN, BlockBehaviour.Properties.of().friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));

	//ghost boat
	public static final DeferredBlock<Block> GHOST_BOAT_PLANKS = BLOCKS.register("ghost_boat_planks", () -> new GhostBoatBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<GhostBoatRotatedPillarBlock> GHOST_BOAT_LOG = BLOCKS.register("ghost_boat_log", () -> new GhostBoatRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get()).noOcclusion()));
	public static final DeferredBlock<GhostBoatRotatedPillarBlock> GHOST_BOAT_WOOD = BLOCKS.register("ghost_boat_wood", () -> new GhostBoatRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GHOST_BOAT_LOG.get()).noOcclusion()));
	public static final DeferredBlock<SlabBlock> GHOST_BOAT_SLAB = BLOCKS.register("ghost_boat_slab", () -> new GhostBoatSlabBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<StairBlock> GHOST_BOAT_STAIRS = BLOCKS.register("ghost_boat_stairs", () -> new GhostBoatStairBlock(GHOST_BOAT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<FenceBlock> GHOST_BOAT_FENCE = BLOCKS.register("ghost_boat_fence", () -> new GhostBoatFenceBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<FenceGateBlock> GHOST_BOAT_GATE = BLOCKS.register("ghost_boat_gate", () -> new GhostBoatFenceGateBlock(AerialHellWoodTypes.AERIAL_TREE, BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<DoorBlock> GHOST_BOAT_DOOR = BLOCKS.register("ghost_boat_door", () -> new GhostBoatDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<TrapDoorBlock> GHOST_BOAT_TRAPDOOR = BLOCKS.register("ghost_boat_trapdoor", () -> new GhostBoatTrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<ChestBlock> GHOST_BOAT_CHEST = BLOCKS.register("ghost_boat_chest", () -> new GhostBoatChestBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<Block> GHOST_BOAT_WOOL = BLOCKS.register("ghost_boat_wool", () -> new GhostBoatBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL).noOcclusion()));
	public static final DeferredBlock<Block> GHOST_STELLAR_COBBLESTONE = BLOCKS.register("ghost_stellar_cobblestone", () -> new GhostBoatBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).requiresCorrectToolForDrops().sound(SoundType.STONE).noOcclusion()));
	public static final DeferredBlock<Block> GHOST_RUBY_BLOCK = BLOCKS.register("ghost_ruby_block", () -> new GhostBoatBlock(BlockBehaviour.Properties.of().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));
	public static final DeferredBlock<Block> GHOST_FLUORITE_BLOCK = BLOCKS.register("ghost_fluorite_block", () -> new GhostBoatBlock(BlockBehaviour.Properties.of().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));
	public static final DeferredBlock<Block> GHOST_AZURITE_BLOCK = BLOCKS.register("ghost_azurite_block", () -> new GhostBoatBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));
	public static final DeferredBlock<Block> GHOST_GOLD_BLOCK = BLOCKS.register("ghost_gold_block", () -> new GhostBoatBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).noOcclusion()));
	public static final DeferredBlock<AerialHellBarrelBlock> GHOST_BOAT_BARREL = BLOCKS.register("ghost_boat_barrel", () -> new GhostBoatBarrelBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<CraftingTableBlock> GHOST_BOAT_CRAFTING_TABLE = BLOCKS.register("ghost_boat_crafting_table", () -> new GhostBoatCraftingTableBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<RotatedPillarBlock> GHOST_BOAT_VINE_ROPE_SPOOL = BLOCKS.register("ghost_boat_vine_rope_spool", () -> new GhostBoatVineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> GHOST_LANTERN = BLOCKS.register("ghost_lantern", () -> new GhostLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));

	//other condition condition blocks
	public static final DeferredBlock<Block> INTANGIBLE_TEMPORARY_BLOCK = BLOCKS.register("intangible_temporary_block", () -> new IntangibleTemporaryBlock(BlockBehaviour.Properties.of().strength(2.0F, 3600000.0F).noLootTable().pushReaction(PushReaction.IGNORE).sound(SoundType.GLASS).lightLevel((state) -> 7).noOcclusion()));

	//reactors
	public static final DeferredBlock<Block> WEAK_LIGHT_REACTOR = BLOCKS.register("weak_light_reactor", () -> new ReactorBlock(BlockBehaviour.Properties.of().strength(5.0F, 100.0F).pushReaction(PushReaction.IGNORE).sound(SoundType.STONE).noOcclusion(), 32, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.BROKEN_WEAK_LIGHT_REACTOR.get()));
	public static final DeferredBlock<Block> HIGH_POWER_LIGHT_REACTOR = BLOCKS.register("high_power_light_reactor", () -> new ReactorBlock(BlockBehaviour.Properties.of().strength(5.0F, 100.0F).pushReaction(PushReaction.IGNORE).sound(SoundType.STONE).noOcclusion(), 58, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.BROKEN_HIGH_POWER_LIGHT_REACTOR.get()));
	public static final DeferredBlock<Block> WEAK_SHADOW_REACTOR = BLOCKS.register("weak_shadow_reactor", () -> new ReactorBlock(BlockBehaviour.Properties.of().strength(5.0F, 100.0F).pushReaction(PushReaction.IGNORE).sound(SoundType.STONE).noOcclusion(), 26, BiomeShifter.ShiftType.CORRUPT, () -> AerialHellBlocks.BROKEN_WEAK_SHADOW_REACTOR.get()));
	public static final DeferredBlock<Block> HIGH_POWER_SHADOW_REACTOR = BLOCKS.register("high_power_shadow_reactor", () -> new ReactorBlock(BlockBehaviour.Properties.of().strength(5.0F, 100.0F).pushReaction(PushReaction.IGNORE).sound(SoundType.STONE).noOcclusion(), 60, BiomeShifter.ShiftType.CORRUPT, () -> AerialHellBlocks.BROKEN_HIGH_POWER_SHADOW_REACTOR.get()));

	public static final DeferredBlock<Block> BROKEN_WEAK_LIGHT_REACTOR = BLOCKS.register("broken_weak_light_reactor", () -> new Block(BlockBehaviour.Properties.ofFullCopy(WEAK_LIGHT_REACTOR.get())));
	public static final DeferredBlock<Block> BROKEN_HIGH_POWER_LIGHT_REACTOR = BLOCKS.register("broken_high_power_light_reactor", () -> new Block(BlockBehaviour.Properties.ofFullCopy(HIGH_POWER_LIGHT_REACTOR.get())));
	public static final DeferredBlock<Block> BROKEN_WEAK_SHADOW_REACTOR = BLOCKS.register("broken_weak_shadow_reactor", () -> new Block(BlockBehaviour.Properties.ofFullCopy(WEAK_SHADOW_REACTOR.get())));
	public static final DeferredBlock<Block> BROKEN_HIGH_POWER_SHADOW_REACTOR = BLOCKS.register("broken_high_power_shadow_reactor", () -> new Block(BlockBehaviour.Properties.ofFullCopy(HIGH_POWER_SHADOW_REACTOR.get())));

	//solid_ethers
	public static final DeferredBlock<Block> WHITE_SOLID_ETHER = BLOCKS.register("white_solid_ether", () -> new SolidEtherBlock(BlockBehaviour.Properties.of().strength(0.2F).sound(SoundType.WOOL).noOcclusion()));
	public static final DeferredBlock<Block> BLUE_SOLID_ETHER = BLOCKS.register("blue_solid_ether", () -> new BlueSolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER.get())));
	public static final DeferredBlock<Block> GOLDEN_SOLID_ETHER = BLOCKS.register("golden_solid_ether", () -> new SolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER.get())));
	public static final DeferredBlock<Block> GREEN_SOLID_ETHER = BLOCKS.register("green_solid_ether", () -> new GreenSolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER.get())));
	public static final DeferredBlock<Block> PURPLE_SOLID_ETHER = BLOCKS.register("purple_solid_ether", () -> new PurpleSolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER.get())));

	//dungeons blocks
	public static final DeferredBlock<Block> MUD_BRICKS = BLOCKS.register("mud_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> CRACKED_MUD_BRICKS = BLOCKS.register("cracked_mud_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get())));
	public static final DeferredBlock<Block> MOSSY_MUD_BRICKS = BLOCKS.register("mossy_mud_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get())));
	public static final DeferredBlock<Block> CHISELED_MUD_BRICKS = BLOCKS.register("chiseled_mud_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get())));
	public static final DeferredBlock<Block> LIGHT_MUD_BRICKS = BLOCKS.register("light_mud_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get()).lightLevel((state) -> 11)));
	public static final DeferredBlock<Block> CRACKED_LIGHT_MUD_BRICKS = BLOCKS.register("cracked_light_mud_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get())));
	public static final DeferredBlock<Block> LUNATIC_STONE = BLOCKS.register("lunatic_stone", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DARK_LUNATIC_STONE = BLOCKS.register("dark_lunatic_stone", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> ROOF_LUNATIC_STONE = BLOCKS.register("roof_lunatic_stone", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> CRACKED_LUNATIC_STONE = BLOCKS.register("cracked_lunatic_stone", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> CHISELED_LUNATIC_STONE = BLOCKS.register("chiseled_lunatic_stone", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> LIGHT_LUNATIC_STONE = BLOCKS.register("light_lunatic_stone", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).lightLevel((state) -> 11)));
	public static final DeferredBlock<Block> ROOF_LIGHT_LUNATIC_STONE = BLOCKS.register("roof_light_lunatic_stone", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).lightLevel((state) -> 11)));
	public static final DeferredBlock<Block> CRACKED_LIGHT_LUNATIC_STONE = BLOCKS.register("cracked_light_lunatic_stone", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get())));
	public static final DeferredBlock<Block> SHADOW_CATACOMBS_BRICKS = BLOCKS.register("shadow_catacombs_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> CRACKED_SHADOW_CATACOMBS_BRICKS = BLOCKS.register("cracked_shadow_catacombs_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get())));
	public static final DeferredBlock<Block> MOSSY_SHADOW_CATACOMBS_BRICKS = BLOCKS.register("mossy_shadow_catacombs_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get())));
	public static final DeferredBlock<Block> CHISELED_SHADOW_CATACOMBS_BRICKS = BLOCKS.register("chiseled_shadow_catacombs_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get())));
	public static final DeferredBlock<Block> BONE_SHADOW_CATACOMBS_BRICKS = BLOCKS.register("bone_shadow_catacombs_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> SKULL_SHADOW_CATACOMBS_BRICKS = BLOCKS.register("skull_shadow_catacombs_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> LIGHT_SHADOW_CATACOMBS_BRICKS = BLOCKS.register("light_shadow_catacombs_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).lightLevel((state) -> 11)));
	public static final DeferredBlock<Block> CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS = BLOCKS.register("cracked_light_shadow_catacombs_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get())));
	public static final DeferredBlock<Block> GOLDEN_NETHER_BRICKS  = BLOCKS.register("golden_nether_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> CRACKED_GOLDEN_NETHER_BRICKS  = BLOCKS.register("cracked_golden_nether_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> CHISELED_GOLDEN_NETHER_BRICKS  = BLOCKS.register("chiseled_golden_nether_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> LIGHT_GOLDEN_NETHER_BRICKS = BLOCKS.register("light_golden_nether_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get()).lightLevel((state) -> 11)));
	public static final DeferredBlock<Block> CRACKED_LIGHT_GOLDEN_NETHER_BRICKS = BLOCKS.register("cracked_light_golden_nether_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get())));
	public static final DeferredBlock<RotatedPillarBlock> LUNATIC_PILLAR = BLOCKS.register("lunatic_pillar", () -> new CoreProtectedRotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(2.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	public static final DeferredBlock<RotatedPillarBlock> LUNATIC_PILLAR_TOP = BLOCKS.register("lunatic_pillar_top", () -> new CoreProtectedRotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(2.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> VOLUCITE_STONE = BLOCKS.register("volucite_stone", () -> new CoreProtectedBlock(Block.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<Block> CRACKED_VOLUCITE_STONE = BLOCKS.register("cracked_volucite_stone", () -> new CoreProtectedBlock(Block.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<Block> CHISELED_VOLUCITE_STONE = BLOCKS.register("chiseled_volucite_stone", () -> new CoreProtectedBlock(Block.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<Block> LIGHT_VOLUCITE_STONE = BLOCKS.register("light_volucite_stone", () -> new CoreProtectedBlock(Block.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).lightLevel((state) -> 11)));
	public static final DeferredBlock<Block> CRACKED_LIGHT_VOLUCITE_STONE = BLOCKS.register("cracked_light_volucite_stone", () -> new CoreProtectedBlock(Block.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE)));

	//dungeon cores
	public static final DeferredBlock<Block> MUD_DUNGEON_CORE = BLOCKS.register("mud_dungeon_core", () -> new DungeonCoreBlock(BlockBehaviour.Properties.of().strength(30.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 181));
	public static final DeferredBlock<Block> LUNATIC_DUNGEON_CORE = BLOCKS.register("lunatic_dungeon_core", () -> new DungeonCoreBlock(BlockBehaviour.Properties.of().strength(40.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 181));
	public static final DeferredBlock<Block> SHADOW_CATACOMBS_DUNGEON_CORE = BLOCKS.register("shadow_catacombs_dungeon_core", () -> new DungeonCoreBlock(BlockBehaviour.Properties.of().strength(30.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 181));
	public static final DeferredBlock<Block> GOLDEN_NETHER_DUNGEON_CORE = BLOCKS.register("golden_nether_dungeon_core", () -> new DungeonCoreBlock(BlockBehaviour.Properties.of().strength(50.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 101));
	public static final DeferredBlock<Block> VOLUCITE_DUNGEON_CORE = BLOCKS.register("volucite_dungeon_core", () -> new DungeonCoreBlock(BlockBehaviour.Properties.of().strength(50.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 101));

	//dungeons slabs, stairs & walls
	public static final DeferredBlock<SlabBlock> MUD_BRICKS_SLAB = BLOCKS.register("mud_bricks_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get())));
	public static final DeferredBlock<StairBlock> MUD_BRICKS_STAIRS = BLOCKS.register("mud_bricks_stairs", () -> new CoreProtectedStairsBlock(MUD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get())));
	public static final DeferredBlock<WallBlock> MUD_BRICKS_WALL = BLOCKS.register("mud_bricks_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get())));
	public static final DeferredBlock<SlabBlock> CRACKED_MUD_BRICKS_SLAB = BLOCKS.register("cracked_mud_bricks_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_MUD_BRICKS.get())));
	public static final DeferredBlock<StairBlock> CRACKED_MUD_BRICKS_STAIRS = BLOCKS.register("cracked_mud_bricks_stairs", () -> new CoreProtectedStairsBlock(CRACKED_MUD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get())));
	public static final DeferredBlock<WallBlock> CRACKED_MUD_BRICKS_WALL = BLOCKS.register("cracked_mud_bricks_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_MUD_BRICKS.get())));
	public static final DeferredBlock<SlabBlock> MOSSY_MUD_BRICKS_SLAB = BLOCKS.register("mossy_mud_bricks_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS.get())));
	public static final DeferredBlock<StairBlock> MOSSY_MUD_BRICKS_STAIRS = BLOCKS.register("mossy_mud_bricks_stairs", () -> new CoreProtectedStairsBlock(MOSSY_MUD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS.get())));
	public static final DeferredBlock<WallBlock> MOSSY_MUD_BRICKS_WALL = BLOCKS.register("mossy_mud_bricks_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS.get())));
	public static final DeferredBlock<SlabBlock> VOLUCITE_STONE_SLAB = BLOCKS.register("volucite_stone_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get())));
	public static final DeferredBlock<StairBlock> VOLUCITE_STONE_STAIRS = BLOCKS.register("volucite_stone_stairs", () -> new CoreProtectedStairsBlock(VOLUCITE_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get())));
	public static final DeferredBlock<WallBlock> VOLUCITE_STONE_WALL = BLOCKS.register("volucite_stone_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get())));
	public static final DeferredBlock<SlabBlock> CRACKED_VOLUCITE_STONE_SLAB = BLOCKS.register("cracked_volucite_stone_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE.get())));
	public static final DeferredBlock<StairBlock> CRACKED_VOLUCITE_STONE_STAIRS = BLOCKS.register("cracked_volucite_stone_stairs", () -> new CoreProtectedStairsBlock(CRACKED_VOLUCITE_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get())));
	public static final DeferredBlock<WallBlock> CRACKED_VOLUCITE_STONE_WALL = BLOCKS.register("cracked_volucite_stone_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE.get())));
	public static final DeferredBlock<SlabBlock> LUNATIC_STONE_SLAB = BLOCKS.register("lunatic_stone_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get())));
	public static final DeferredBlock<StairBlock> LUNATIC_STONE_STAIRS = BLOCKS.register("lunatic_stone_stairs", () -> new CoreProtectedStairsBlock(LUNATIC_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get())));
	public static final DeferredBlock<WallBlock> LUNATIC_STONE_WALL = BLOCKS.register("lunatic_stone_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get())));
	public static final DeferredBlock<SlabBlock> DARK_LUNATIC_STONE_SLAB = BLOCKS.register("dark_lunatic_stone_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(DARK_LUNATIC_STONE.get())));
	public static final DeferredBlock<StairBlock> DARK_LUNATIC_STONE_STAIRS = BLOCKS.register("dark_lunatic_stone_stairs", () -> new CoreProtectedStairsBlock(DARK_LUNATIC_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(DARK_LUNATIC_STONE.get())));
	public static final DeferredBlock<WallBlock> DARK_LUNATIC_STONE_WALL = BLOCKS.register("dark_lunatic_stone_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(DARK_LUNATIC_STONE.get())));
	public static final DeferredBlock<SlabBlock> CRACKED_LUNATIC_STONE_SLAB = BLOCKS.register("cracked_lunatic_stone_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_LUNATIC_STONE.get())));
	public static final DeferredBlock<StairBlock> CRACKED_LUNATIC_STONE_STAIRS = BLOCKS.register("cracked_lunatic_stone_stairs", () -> new CoreProtectedStairsBlock(CRACKED_LUNATIC_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get())));
	public static final DeferredBlock<WallBlock> CRACKED_LUNATIC_STONE_WALL = BLOCKS.register("cracked_lunatic_stone_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_LUNATIC_STONE.get())));
	public static final DeferredBlock<SlabBlock> SHADOW_CATACOMBS_BRICKS_SLAB = BLOCKS.register("shadow_catacombs_bricks_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get())));
	public static final DeferredBlock<StairBlock> SHADOW_CATACOMBS_BRICKS_STAIRS = BLOCKS.register("shadow_catacombs_bricks_stairs", () -> new CoreProtectedStairsBlock(SHADOW_CATACOMBS_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get())));
	public static final DeferredBlock<WallBlock> SHADOW_CATACOMBS_BRICKS_WALL = BLOCKS.register("shadow_catacombs_bricks_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get())));
	public static final DeferredBlock<SlabBlock> CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB = BLOCKS.register("cracked_shadow_catacombs_bricks_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_SHADOW_CATACOMBS_BRICKS.get())));
	public static final DeferredBlock<StairBlock> CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS = BLOCKS.register("cracked_shadow_catacombs_bricks_stairs", () -> new CoreProtectedStairsBlock(CRACKED_SHADOW_CATACOMBS_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get())));
	public static final DeferredBlock<WallBlock> CRACKED_SHADOW_CATACOMBS_BRICKS_WALL = BLOCKS.register("cracked_shadow_catacombs_bricks_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_SHADOW_CATACOMBS_BRICKS.get())));
	public static final DeferredBlock<SlabBlock> MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB = BLOCKS.register("mossy_shadow_catacombs_bricks_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_SHADOW_CATACOMBS_BRICKS.get())));
	public static final DeferredBlock<StairBlock> MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS = BLOCKS.register("mossy_shadow_catacombs_bricks_stairs", () -> new CoreProtectedStairsBlock(MOSSY_SHADOW_CATACOMBS_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_SHADOW_CATACOMBS_BRICKS.get())));
	public static final DeferredBlock<WallBlock> MOSSY_SHADOW_CATACOMBS_BRICKS_WALL = BLOCKS.register("mossy_shadow_catacombs_bricks_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_SHADOW_CATACOMBS_BRICKS.get())));
	public static final DeferredBlock<IronBarsBlock> SHADOW_BARS = BLOCKS.register("shadow_bars", () -> new ShadowBarsBlock(METAL_NOTSOLID_MATERIAL));
	public static final DeferredBlock<SlabBlock> GOLDEN_NETHER_BRICKS_SLAB = BLOCKS.register("golden_nether_bricks_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get())));
	public static final DeferredBlock<StairBlock> GOLDEN_NETHER_BRICKS_STAIRS = BLOCKS.register("golden_nether_bricks_stairs", () -> new CoreProtectedStairsBlock(GOLDEN_NETHER_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get())));
	public static final DeferredBlock<WallBlock> GOLDEN_NETHER_BRICKS_WALL = BLOCKS.register("golden_nether_bricks_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get())));
	public static final DeferredBlock<SlabBlock> CRACKED_GOLDEN_NETHER_BRICKS_SLAB = BLOCKS.register("cracked_golden_nether_bricks_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_GOLDEN_NETHER_BRICKS.get())));
	public static final DeferredBlock<StairBlock> CRACKED_GOLDEN_NETHER_BRICKS_STAIRS = BLOCKS.register("cracked_golden_nether_bricks_stairs", () -> new CoreProtectedStairsBlock(CRACKED_GOLDEN_NETHER_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get())));
	public static final DeferredBlock<WallBlock> CRACKED_GOLDEN_NETHER_BRICKS_WALL = BLOCKS.register("cracked_golden_nether_bricks_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_GOLDEN_NETHER_BRICKS.get())));

	//smoky quartz
	public static final DeferredBlock<Block> SMOKY_QUARTZ_BLOCK = BLOCKS.register("smoky_quartz_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)));
	public static final DeferredBlock<Block> SMOOTH_SMOKY_QUARTZ = BLOCKS.register("smooth_smoky_quartz", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get())));
	public static final DeferredBlock<Block> CHISELED_SMOKY_QUARTZ_BLOCK = BLOCKS.register("chiseled_smoky_quartz_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get())));
	public static final DeferredBlock<Block> SMOKY_QUARTZ_BRICKS = BLOCKS.register("smoky_quartz_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get())));
	public static final DeferredBlock<RotatedPillarBlock> SMOKY_QUARTZ_PILLAR = BLOCKS.register("smoky_quartz_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get())));
	public static final DeferredBlock<SlabBlock> SMOKY_QUARTZ_SLAB = BLOCKS.register("smoky_quartz_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get())));
	public static final DeferredBlock<SlabBlock> SMOOTH_SMOKY_QUARTZ_SLAB = BLOCKS.register("smooth_smoky_quartz_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get())));
	public static final DeferredBlock<StairBlock> SMOKY_QUARTZ_STAIRS = BLOCKS.register("smoky_quartz_stairs", () -> new StairBlock(SMOKY_QUARTZ_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get())));
	public static final DeferredBlock<StairBlock> SMOOTH_SMOKY_QUARTZ_STAIRS = BLOCKS.register("smooth_smoky_quartz_stairs", () -> new StairBlock(SMOOTH_SMOKY_QUARTZ.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get())));

	//dungeon trapped blocks
	public static final DeferredBlock<Block> TRAPPED_MUD_BRICKS = BLOCKS.register("trapped_mud_bricks", () -> new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)));
	public static final DeferredBlock<Block> TRAPPED_LIGHT_MUD_BRICKS = BLOCKS.register("trapped_light_mud_bricks", () -> new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).lightLevel((state) -> 11)));
	public static final DeferredBlock<Block> TRAPPED_LUNATIC_STONE = BLOCKS.register("trapped_lunatic_stone", () -> new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)));
	public static final DeferredBlock<Block> TRAPPED_LIGHT_LUNATIC_STONE = BLOCKS.register("trapped_light_lunatic_stone", () -> new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).lightLevel((state) -> 11)));
	public static final DeferredBlock<Block> TRAPPED_GOLDEN_NETHER_BRICKS = BLOCKS.register("trapped_golden_nether_bricks", () -> new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)));
	public static final DeferredBlock<Block> TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS = BLOCKS.register("trapped_light_golden_nether_bricks", () -> new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).lightLevel((state) -> 11)));

	//dungeon other blocks, loots
	public static final DeferredBlock<RotatedPillarBlock> MUD_BONE_BLOCK = BLOCKS.register("mud_bone_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).requiresCorrectToolForDrops().strength(2.5F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> MUD_BONE_PILE_BLOCK = BLOCKS.register("mud_bone_pile_block", () -> new BonePileBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).randomTicks().strength(2.5F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> THORNY_COBWEB = BLOCKS.register("thorny_cobweb", () -> new ThornyWebBlock(BlockBehaviour.Properties.of().noCollission().requiresCorrectToolForDrops().strength(8.0F)));
	public static final DeferredBlock<Block> AERIAL_NETHERRACK = BLOCKS.register("aerial_netherrack", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).requiresCorrectToolForDrops().strength(6.0F, 8.0F)));
	public static final DeferredBlock<SlabBlock> AERIAL_NETHERRACK_SLAB = BLOCKS.register("aerial_netherrack_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_NETHERRACK.get())));
	public static final DeferredBlock<StairBlock> AERIAL_NETHERRACK_STAIRS = BLOCKS.register("aerial_netherrack_stairs", () -> new StairBlock(AERIAL_NETHERRACK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(AERIAL_NETHERRACK.get())));
	public static final DeferredBlock<WallBlock> AERIAL_NETHERRACK_WALL = BLOCKS.register("aerial_netherrack_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_NETHERRACK.get())));

	//dungeon bookshelfs
	public static final DeferredBlock<Block> MUD_BOOKSHELF = BLOCKS.register("mud_bookshelf", () -> new CoreProtectedBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get())));
	public static final DeferredBlock<Block> LUNATIC_BOOKSHELF = BLOCKS.register("lunatic_bookshelf", () -> new CoreProtectedBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get())));
	public static final DeferredBlock<Block> GOLDEN_NETHER_BOOKSHELF = BLOCKS.register("golden_nether_bookshelf", () -> new CoreProtectedBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get())));
	public static final DeferredBlock<Block> SHADOW_CATACOMBS_BOOKSHELF = BLOCKS.register("shadow_catacombs_bookshelf", () -> new CoreProtectedBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get())));
	public static final DeferredBlock<Block> VOLUCITE_BOOKSHELF = BLOCKS.register("volucite_bookshelf", () -> new CoreProtectedBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get())));

	//glyph blocks
	public static final DeferredBlock<Block> MUD_GLYPH_BLOCK = BLOCKS.register("mud_glyph_block", () -> new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get()).lightLevel((state) -> 9)));
	public static final DeferredBlock<Block> LUNATIC_GLYPH_BLOCK = BLOCKS.register("lunatic_glyph_block", () -> new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).lightLevel((state) -> 9)));
	public static final DeferredBlock<Block> GOLDEN_NETHER_PRISON_GLYPH_BLOCK = BLOCKS.register("golden_nether_prison_glyph_block", () -> new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get()).lightLevel((state) -> 9)));
	public static final DeferredBlock<Block> VOLUCITE_GLYPH_BLOCK = BLOCKS.register("volucite_glyph_block", () -> new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get()).lightLevel((state) -> 9)));
	public static final DeferredBlock<Block> SHADOW_CATACOMBS_GLYPH_BLOCK = BLOCKS.register("shadow_catacombs_glyph_block", () -> new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).lightLevel((state) -> 9)));

	//trophies
	public static final DeferredBlock<Block> MUD_CYCLE_MAGE_TROPHY = BLOCKS.register("mud_cycle_mage_trophy", () -> new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> LUNAR_PRIEST_TROPHY = BLOCKS.register("lunar_priest_trophy", () -> new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> LILITH_TROPHY = BLOCKS.register("lilith_trophy", () -> new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> CHAINED_GOD_TROPHY = BLOCKS.register("chained_god_trophy", () -> new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get()).requiresCorrectToolForDrops()));

	//ores
	public static final DeferredBlock<Block> IRON_STELLAR_ORE = BLOCKS.register("iron_stellar_ore",() -> new AerialHellOreBlock(0, 2, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> GOLD_STELLAR_ORE = BLOCKS.register("gold_stellar_ore",() -> new AerialHellOreBlock(0, 2, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DIAMOND_STELLAR_ORE = BLOCKS.register("diamond_stellar_ore",() -> new AerialHellOreBlock(3, 5, BlockBehaviour.Properties.of().strength(5.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> FLUORITE_ORE = BLOCKS.register("fluorite_ore",() -> new BiomeShifterOreBlock(0, 2, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 2, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.SMOKY_QUARTZ_ORE.get()));
	public static final DeferredBlock<Block> MAGMATIC_GEL_ORE = BLOCKS.register("magmatic_gel_ore",() -> new MagmaticGelOreBlock(0, 2, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).lightLevel(s -> 4).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> RUBY_ORE = BLOCKS.register("ruby_ore",() -> new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> AZURITE_ORE = BLOCKS.register("azurite_ore",() -> new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> VOLUCITE_ORE = BLOCKS.register("volucite_ore",() -> new VoluciteOreBlock(0, 0, BlockBehaviour.Properties.of().strength(5.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> OBSIDIAN_ORE = BLOCKS.register("obsidian_ore",() -> new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().strength(5.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> SMOKY_QUARTZ_ORE = BLOCKS.register("smoky_quartz_ore",() -> new AerialHellOreBlock(1, 3, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));

	public static final DeferredBlock<Block> RAW_RUBY_BLOCK = BLOCKS.register("raw_ruby_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> RAW_AZURITE_BLOCK = BLOCKS.register("raw_azurite_crystal_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> RAW_VOLUCITE_BLOCK = BLOCKS.register("raw_volucite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).requiresCorrectToolForDrops()));

	public static final DeferredBlock<Block> FLUORITE_BLOCK = BLOCKS.register("fluorite_block", () -> new BiomeShifterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), 7, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.SMOKY_QUARTZ_BLOCK.get()));
	public static final DeferredBlock<Block> MAGMATIC_GEL_BLOCK = BLOCKS.register("magmatic_gel_block", () -> new MagmaticGelBlock(BlockBehaviour.Properties.of().strength(1.0F, 1600.0F).randomTicks().sound(SoundType.GLASS).noOcclusion().requiresCorrectToolForDrops().isViewBlocking((state, reader, pos) -> false)));
	public static final DeferredBlock<Block> RUBY_BLOCK = BLOCKS.register("ruby_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> AZURITE_BLOCK = BLOCKS.register("azurite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> VOLUCITE_BLOCK = BLOCKS.register("volucite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).requiresCorrectToolForDrops()));

	//legendary ores
	public static final DeferredBlock<Block> ARSONIST_BLOCK = BLOCKS.register("arsonist_block", () -> new ArsonistBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).requiresCorrectToolForDrops().lightLevel((state) -> 9)));
	public static final DeferredBlock<Block> LUNATIC_CRYSTAL_BLOCK = BLOCKS.register("lunatic_crystal_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).sound(SoundType.GLASS).requiresCorrectToolForDrops().lightLevel((state) -> 9)));
	public static final DeferredBlock<Block> CURSED_CRYSAL_BLOCK = BLOCKS.register("cursed_crystal_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).sound(SoundType.GLASS).requiresCorrectToolForDrops().lightLevel((state) -> 9)));

	//cactus
	public static final DeferredBlock<SkyCactusBlock> SKY_CACTUS = BLOCKS.register("sky_cactus", () -> new SkyCactusBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(0.4F).sound(SoundType.WOOL).randomTicks()));
	public static final DeferredBlock<Block> SKY_CACTUS_FIBER_PLANKS = BLOCKS.register("sky_cactus_fiber_planks", () -> new Block(SKY_CACTUS_FIBER_MATERIAL));
	public static final DeferredBlock<Block> SKY_CACTUS_FIBER_BOOKSHELF = BLOCKS.register("sky_cactus_fiber_bookshelf", () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_PLANKS.get())));
	public static final DeferredBlock<SkyCactusBlock> VIBRANT_SKY_CACTUS = BLOCKS.register("vibrant_sky_cactus", () -> new SkyCactusBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(0.4F).sound(SoundType.WOOL).randomTicks().lightLevel(s -> 15).noOcclusion()));
	public static final DeferredBlock<Block> VIBRANT_SKY_CACTUS_FIBER_LANTERN = BLOCKS.register("vibrant_sky_cactus_fiber_lantern", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(0.5F).sound(SoundType.GLASS).noOcclusion().lightLevel(s -> 15)));

	//bushes
	public static final DeferredBlock<Block> AERIAL_BERRY_BUSH = BLOCKS.register("aerial_berry_bush", () -> new AerialBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));
	public static final DeferredBlock<Block> VIBRANT_AERIAL_BERRY_BUSH = BLOCKS.register("vibrant_aerial_berry_bush", () -> new VibrantAerialBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

	//crops
	public static final DeferredBlock<Block> STELLAR_WHEAT = BLOCKS.register("stellar_wheat", () -> new StellarWheatBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

	//vertical growing plants
	public static final DeferredBlock<VerticalGrowingPlantBlock> CLIMBING_VINE = BLOCKS.register("climbing_vine", () -> new VerticalGrowingPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE), 4));
	public static final DeferredBlock<VerticalGrowingPlantBlock> STELLAR_SUGAR_CANE = BLOCKS.register("stellar_sugar_cane", () -> new VerticalGrowingPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE), 5));

	//chorus like
	public static final DeferredBlock<ChorusPlantLikeBlock> FULL_MOON_PLANT = BLOCKS.register("full_moon_plant", () -> new ChorusPlantLikeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).forceSolidOff().strength(0.4F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY).lightLevel((state) -> 10)));
	public static final DeferredBlock<ChorusFlowerLikeBlock> FULL_MOON_FLOWER = BLOCKS.register("full_moon_flower", () -> new ChorusFlowerLikeBlock(FULL_MOON_PLANT.get(), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).forceSolidOff().randomTicks().strength(0.4F).sound(SoundType.WOOD).noOcclusion().isValidSpawn((state, blockGetter, pos, entitytype) -> false).pushReaction(PushReaction.DESTROY).isRedstoneConductor((state, blockGetter, pos) -> false).lightLevel((state) -> 15)));

	//vines
	public static final DeferredBlock<CaveVinesBlock> GLOWING_STICK_FRUIT_VINES = BLOCKS.register("glowing_stick_fruit_vines", () -> new AerialHellCaveVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAVE_VINES)));
	public static final DeferredBlock<CaveVinesPlantBlock> GLOWING_STICK_FRUIT_VINES_PLANT = BLOCKS.register("glowing_stick_fruit_vines_plant", () -> new AerialHellCaveVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAVE_VINES_PLANT)));
	public static final DeferredBlock<CaveVinesBlock> BLOSSOMING_VINES = BLOCKS.register("blossoming_vines", () -> new AerialHellCaveVinesBlock(BlockBehaviour.Properties.of().randomTicks().noCollission().instabreak().sound(SoundType.CAVE_VINES)));
	public static final DeferredBlock<CaveVinesPlantBlock> BLOSSOMING_VINES_PLANT = BLOCKS.register("blossoming_vines_plant", () -> new AerialHellCaveVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(BLOSSOMING_VINES.get())));
	public static final DeferredBlock<AerialHellTwistingVinesBlock> LAZULI_ROOTS = BLOCKS.register("lazuli_roots", () -> new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES)));
	public static final DeferredBlock<AerialHellTwistingVinesPlantBlock> LAZULI_ROOTS_PLANT = BLOCKS.register("lazuli_roots_plant", () -> new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT)));
	public static final DeferredBlock<AerialHellTwistingVinesBlock> STELLAR_ROOTS = BLOCKS.register("stellar_roots", () -> new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES)));
	public static final DeferredBlock<AerialHellTwistingVinesPlantBlock> STELLAR_ROOTS_PLANT = BLOCKS.register("stellar_roots_plant", () -> new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT)));
	public static final DeferredBlock<AerialHellTwistingVinesBlock> DEAD_ROOTS = BLOCKS.register("dead_roots", () -> new DeadRootsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES)));
	public static final DeferredBlock<AerialHellTwistingVinesPlantBlock> DEAD_ROOTS_PLANT = BLOCKS.register("dead_roots_plant", () -> new DeadRootsPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT)));
	public static final DeferredBlock<AerialHellTwistingVinesBlock> GLOWING_ROOTS = BLOCKS.register("glowing_roots", () -> new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES).lightLevel((state) -> 9)));
	public static final DeferredBlock<AerialHellTwistingVinesPlantBlock> GLOWING_ROOTS_PLANT = BLOCKS.register("glowing_roots_plant", () -> new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT).lightLevel((state) -> 14)));
	public static final DeferredBlock<AerialHellTwistingVinesBlock> SHADOW_GLOWING_ROOTS = BLOCKS.register("shadow_glowing_roots", () -> new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES).lightLevel((state) -> 8)));
	public static final DeferredBlock<AerialHellTwistingVinesPlantBlock> SHADOW_GLOWING_ROOTS_PLANT = BLOCKS.register("shadow_glowing_roots_plant", () -> new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT).lightLevel((state) -> 13)));

	//grass
	public static final DeferredBlock<Block> STELLAR_GRASS = BLOCKS.register("stellar_grass", () -> new ShiftableRenderTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> STELLAR_GRASS_BALL = BLOCKS.register("stellar_grass_ball", () -> new ShiftableRenderTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> STELLAR_FERN = BLOCKS.register("stellar_fern", () -> new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> STELLAR_TALL_GRASS = BLOCKS.register("stellar_tall_grass", () -> new DoublePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> STELLAR_TALL_FERN = BLOCKS.register("stellar_tall_fern", () -> new DoublePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<VerticalGrowingPlantBlock> STELLAR_VERY_TALL_GRASS = BLOCKS.register("stellar_very_tall_grass", () -> new VerticalGrowingPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE), 3));
	public static final DeferredBlock<Block> BLUISH_FERN = BLOCKS.register("bluish_fern", () -> new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> TALL_BLUISH_FERN = BLOCKS.register("tall_bluish_fern", () -> new DoublePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> POLYCHROME_FERN = BLOCKS.register("polychrome_fern", () -> new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> TALL_POLYCHROME_FERN = BLOCKS.register("tall_polychrome_fern", () -> new DoublePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> STELLAR_DEAD_BUSH = BLOCKS.register("stellar_dead_bush", () -> new AerialHellDeadBushBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().mapColor(MapColor.WOOD).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> BRAMBLES = BLOCKS.register("brambles", () -> new BramblesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().strength(0.5F).sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> SHADOW_BRAMBLES = BLOCKS.register("shadow_brambles", () -> new BramblesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().strength(0.5F).sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> SHADOW_GRASS = BLOCKS.register("shadow_grass", () -> new ShadowPlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> SHADOW_GRASS_BALL = BLOCKS.register("shadow_grass_ball", () -> new ShadowPlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> PURPLISH_STELLAR_GRASS = BLOCKS.register("purplish_stellar_grass", () -> new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> STELLAR_CLOVERS = BLOCKS.register("stellar_clovers", () -> new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> GLOWING_STELLAR_GRASS = BLOCKS.register("glowing_stellar_grass", () -> new GlowingStellarTallGrass(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().randomTicks().noCollission().lightLevel((state) -> {return state.getValue(BlockStateProperties.LIT) ? 10 : 0;}).instabreak().sound(SoundType.GRASS)));

	//flowers
	public static final DeferredBlock<Block> BLUE_FLOWER = BLOCKS.register("blue_flower", () -> new FlowerBlock(MobEffects.BLINDNESS, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));
	public static final DeferredBlock<Block> BLACK_ROSE = BLOCKS.register("black_rose", () -> new FlowerBlock(MobEffects.SLOW_FALLING, 12, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));
	public static final DeferredBlock<Block> BELLFLOWER = BLOCKS.register("bellflower", () -> new FlowerBlock(MobEffects.DIG_SLOWDOWN, 12, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));

	//potted things
	public static final DeferredBlock<FlowerPotBlock> POTTED_BLUE_FLOWER = BLOCKS.register("potted_blue_flower", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLUE_FLOWER, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_BLACK_ROSE = BLOCKS.register("potted_black_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLACK_ROSE, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_BELLFLOWER = BLOCKS.register("potted_bellflower", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BELLFLOWER, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_STELLAR_FERN = BLOCKS.register("potted_stellar_fern", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, STELLAR_FERN, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_STELLAR_DEAD_BUSH = BLOCKS.register("potted_stellar_dead_bush", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, STELLAR_DEAD_BUSH, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_SKY_CACTUS = BLOCKS.register("potted_sky_cactus", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SKY_CACTUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_VIBRANT_SKY_CACTUS = BLOCKS.register("potted_vibrant_sky_cactus", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, VIBRANT_SKY_CACTUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_AERIAL_TREE_SAPLING = BLOCKS.register("potted_aerial_tree_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, AERIAL_TREE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_GOLDEN_BEECH_SAPLING = BLOCKS.register("potted_golden_beech_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GOLDEN_BEECH_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_COPPER_PINE_SAPLING = BLOCKS.register("potted_copper_pine_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, COPPER_PINE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_LAPIS_ROBINIA_SAPLING = BLOCKS.register("potted_lapis_robinia_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, LAPIS_ROBINIA_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_SHADOW_PINE_SAPLING = BLOCKS.register("potted_shadow_pine_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SHADOW_PINE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_PURPLE_SHADOW_PINE_SAPLING = BLOCKS.register("potted_purple_shadow_pine_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, PURPLE_SHADOW_PINE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_STELLAR_JUNGLE_TREE_SAPLING = BLOCKS.register("potted_stellar_jungle_tree_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, STELLAR_JUNGLE_TREE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_CORTINARIUS_VIOLACEUS = BLOCKS.register("potted_cortinarius_violaceus", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CORTINARIUS_VIOLACEUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_VERDIGRIS_AGARIC = BLOCKS.register("potted_verdigris_agaric", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, VERDIGRIS_AGARIC, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_VINE_BLOSSOM = BLOCKS.register("potted_vine_blossom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLOSSOMING_VINES, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_GLOWING_BOLETUS = BLOCKS.register("potted_glowing_boletus", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GLOWING_BOLETUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).lightLevel((state) -> 9)));

	//with gui
	public static final DeferredBlock<Block> OSCILLATOR = BLOCKS.register("oscillator", () -> new OscillatorBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<Block> FREEZER = BLOCKS.register("freezer",() -> new FreezerBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<Block> STELLAR_FURNACE = BLOCKS.register("stellar_furnace", () -> new StellarFurnaceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.5F).lightLevel(getLightValueLit(13))));
	public static final DeferredBlock<Block> GHOST_STELLAR_FURNACE = BLOCKS.register("ghost_stellar_furnace", () -> new GhostStellarFurnaceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().noOcclusion().strength(3.5F).lightLevel(getLightValueLit(13))));

	private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {return (state) -> {return state.getValue(BlockStateProperties.LIT) ? lightValue : 0;};}

	//chests
	public static final DeferredBlock<ChestBlock> AERIAL_TREE_CHEST = BLOCKS.register("aerial_tree_chest", () -> new AerialHellChestBlock(AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<ChestBlock> GOLDEN_BEECH_CHEST = BLOCKS.register("golden_beech_chest", () -> new AerialHellChestBlock(AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<ChestBlock> COPPER_PINE_CHEST = BLOCKS.register("copper_pine_chest", () -> new AerialHellChestBlock(COPPER_PINE_MATERIAL));
	public static final DeferredBlock<ChestBlock> LAPIS_ROBINIA_CHEST = BLOCKS.register("lapis_robinia_chest", () -> new AerialHellChestBlock(COPPER_PINE_MATERIAL));
	public static final DeferredBlock<ChestBlock> SHADOW_PINE_CHEST = BLOCKS.register("shadow_pine_chest", () -> new AerialHellChestBlock(SHADOW_PINE_MATERIAL));
	public static final DeferredBlock<ChestBlock> STELLAR_JUNGLE_TREE_CHEST = BLOCKS.register("stellar_jungle_tree_chest", () -> new AerialHellChestBlock(COPPER_PINE_MATERIAL));
	public static final DeferredBlock<ChestBlock> SKY_CACTUS_FIBER_CHEST = BLOCKS.register("sky_cactus_fiber_chest", () -> new AerialHellChestBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final DeferredBlock<ChestBlock> GRAY_SHROOM_CHEST = BLOCKS.register("gray_shroom_chest", () -> new AerialHellChestBlock(SHROOM_MATERIAL));
	public static final DeferredBlock<ChestBlock> MUD_CHEST = BLOCKS.register("mud_chest", () -> new CoreProtectedChestBlock(MUD_CHEST_MATERIAL));
	public static final DeferredBlock<ChestBlock> LUNATIC_CHEST = BLOCKS.register("lunatic_chest", () -> new CoreProtectedChestBlock(LUNATIC_CHEST_MATERIAL));
	public static final DeferredBlock<ChestBlock> VOLUCITE_CHEST = BLOCKS.register("volucite_chest", () -> new CoreProtectedChestBlock(VOLUCITE_CHEST_MATERIAL));
	public static final DeferredBlock<ChestBlock> SHADOW_CATACOMBS_CHEST = BLOCKS.register("shadow_catacombs_chest", () -> new CoreProtectedChestBlock(MUD_CHEST_MATERIAL));
	public static final DeferredBlock<ChestBlock> GOLDEN_NETHER_CHEST = BLOCKS.register("golden_nether_chest", () -> new CoreProtectedChestBlock(GOLDEN_NETHER_CHEST_MATERIAL));

	//chest mimics
	public static final DeferredBlock<Block> AERIAL_TREE_CHEST_MIMIC = BLOCKS.register("aerial_tree_chest_mimic", () -> new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
	public static final DeferredBlock<Block> GOLDEN_BEECH_CHEST_MIMIC = BLOCKS.register("golden_beech_chest_mimic", () -> new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
	public static final DeferredBlock<Block> COPPER_PINE_CHEST_MIMIC = BLOCKS.register("copper_pine_chest_mimic", () -> new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
	public static final DeferredBlock<Block> SKY_CACTUS_FIBER_CHEST_MIMIC = BLOCKS.register("sky_cactus_fiber_chest_mimic", () -> new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));

	//barrel mimics
	public static final DeferredBlock<Block> SHADOW_PINE_BARREL_MIMIC = BLOCKS.register("shadow_pine_barrel_mimic", () -> new BarrelMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL)));

	//fences, bars or walls
	public static final DeferredBlock<FenceBlock> AERIAL_TREE_FENCE = BLOCKS.register("aerial_tree_fence", () -> new FenceBlock(AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<FenceBlock> GOLDEN_BEECH_FENCE = BLOCKS.register("golden_beech_fence", () -> new FenceBlock(AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<FenceBlock> COPPER_PINE_FENCE = BLOCKS.register("copper_pine_fence", () -> new FenceBlock(COPPER_PINE_MATERIAL));
	public static final DeferredBlock<FenceBlock> LAPIS_ROBINIA_FENCE = BLOCKS.register("lapis_robinia_fence", () -> new FenceBlock(COPPER_PINE_MATERIAL));
	public static final DeferredBlock<FenceBlock> SHADOW_PINE_FENCE = BLOCKS.register("shadow_pine_fence", () -> new FenceBlock(SHADOW_PINE_MATERIAL));
	public static final DeferredBlock<FenceBlock> STELLAR_JUNGLE_TREE_FENCE = BLOCKS.register("stellar_jungle_tree_fence", () -> new FenceBlock(COPPER_PINE_MATERIAL));
	public static final DeferredBlock<FenceBlock> SKY_CACTUS_FIBER_FENCE = BLOCKS.register("sky_cactus_fiber_fence", () -> new FenceBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final DeferredBlock<FenceBlock> GRAY_SHROOM_FENCE = BLOCKS.register("gray_shroom_fence", () -> new FenceBlock(SHROOM_MATERIAL));
	public static final DeferredBlock<IronBarsBlock> RUBY_BARS = BLOCKS.register("ruby_bars", () -> new IronBarsBlock(METAL_NOTSOLID_MATERIAL));
	public static final DeferredBlock<WallBlock> STELLAR_STONE_WALL = BLOCKS.register("stellar_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get())));
	public static final DeferredBlock<WallBlock> STELLAR_COBBLESTONE_WALL = BLOCKS.register("stellar_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE.get())));
	public static final DeferredBlock<WallBlock> STELLAR_STONE_BRICKS_WALL = BLOCKS.register("stellar_stone_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS.get())));
	public static final DeferredBlock<WallBlock> MOSSY_STELLAR_STONE_WALL = BLOCKS.register("mossy_stellar_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_STONE.get())));
	public static final DeferredBlock<WallBlock> MOSSY_STELLAR_COBBLESTONE_WALL = BLOCKS.register("mossy_stellar_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_COBBLESTONE.get())));
	public static final DeferredBlock<WallBlock> SLIPPERY_SAND_STONE_WALL = BLOCKS.register("slippery_sand_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get())));
	public static final DeferredBlock<WallBlock> SLIPPERY_SAND_STONE_BRICKS_WALL = BLOCKS.register("slippery_sand_stone_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final DeferredBlock<WallBlock> CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL = BLOCKS.register("cracked_slippery_sand_stone_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final DeferredBlock<WallBlock> GLAUCOPHANITE_WALL = BLOCKS.register("glaucophanite_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(GLAUCOPHANITE.get())));
	public static final DeferredBlock<WallBlock> POLISHED_GLAUCOPHANITE_WALL = BLOCKS.register("polished_glaucophanite_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_GLAUCOPHANITE.get())));
	public static final DeferredBlock<WallBlock> MAGMATIC_GEL_WALL = BLOCKS.register("magmatic_gel_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MAGMATIC_GEL_BLOCK.get())));

	//gates
	public static final DeferredBlock<FenceGateBlock> AERIAL_TREE_GATE = BLOCKS.register("aerial_tree_gate", () -> new FenceGateBlock(AerialHellWoodTypes.AERIAL_TREE, AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<FenceGateBlock> GOLDEN_BEECH_GATE = BLOCKS.register("golden_beech_gate", () -> new FenceGateBlock(AerialHellWoodTypes.GOLDEN_BEECH, AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<FenceGateBlock> COPPER_PINE_GATE = BLOCKS.register("copper_pine_gate", () -> new FenceGateBlock(AerialHellWoodTypes.COPPER_PINE, COPPER_PINE_MATERIAL));
	public static final DeferredBlock<FenceGateBlock> LAPIS_ROBINIA_GATE = BLOCKS.register("lapis_robinia_gate", () -> new FenceGateBlock(AerialHellWoodTypes.LAPIS_ROBINIA, COPPER_PINE_MATERIAL));
	public static final DeferredBlock<FenceGateBlock> SHADOW_PINE_GATE = BLOCKS.register("shadow_pine_gate", () -> new FenceGateBlock(AerialHellWoodTypes.SHADOW_PINE, SHADOW_PINE_MATERIAL));
	public static final DeferredBlock<FenceGateBlock> STELLAR_JUNGLE_TREE_GATE = BLOCKS.register("stellar_jungle_tree_gate", () -> new FenceGateBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, COPPER_PINE_MATERIAL));
	public static final DeferredBlock<FenceGateBlock> SKY_CACTUS_FIBER_GATE = BLOCKS.register("sky_cactus_fiber_gate", () -> new FenceGateBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, SKY_CACTUS_FIBER_MATERIAL));
	public static final DeferredBlock<FenceGateBlock> GRAY_SHROOM_GATE = BLOCKS.register("gray_shroom_gate", () -> new FenceGateBlock(AerialHellWoodTypes.GRAY_SHROOM, SHROOM_MATERIAL));

	//doors
	public static final DeferredBlock<DoorBlock> AERIAL_TREE_DOOR = BLOCKS.register("aerial_tree_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS.get()).noOcclusion()));
	public static final DeferredBlock<DoorBlock> GOLDEN_BEECH_DOOR = BLOCKS.register("golden_beech_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS.get()).noOcclusion()));
	public static final DeferredBlock<DoorBlock> COPPER_PINE_DOOR = BLOCKS.register("copper_pine_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_PLANKS.get()).noOcclusion()));
	public static final DeferredBlock<DoorBlock> LAPIS_ROBINIA_DOOR = BLOCKS.register("lapis_robinia_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_PLANKS.get()).noOcclusion()));
	public static final DeferredBlock<DoorBlock> SHADOW_PINE_DOOR = BLOCKS.register("shadow_pine_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_PLANKS.get()).noOcclusion()));
	public static final DeferredBlock<DoorBlock> STELLAR_JUNGLE_TREE_DOOR = BLOCKS.register("stellar_jungle_tree_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_PLANKS.get()).noOcclusion()));
	public static final DeferredBlock<DoorBlock> SKY_CACTUS_FIBER_DOOR = BLOCKS.register("sky_cactus_fiber_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_PLANKS.get()).noOcclusion()));
	public static final DeferredBlock<DoorBlock> GRAY_SHROOM_DOOR = BLOCKS.register("gray_shroom_door", () -> new DoorBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_PLANKS.get()).noOcclusion()));
	public static final DeferredBlock<DoorBlock> RUBY_DOOR = BLOCKS.register("ruby_door", () -> new DoorBlock(BlockSetType.IRON, METAL_NOTSOLID_MATERIAL));

	//trapdoors
	public static final DeferredBlock<TrapDoorBlock> AERIAL_TREE_TRAPDOOR = BLOCKS.register("aerial_tree_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS.get()).noOcclusion()));
	public static final DeferredBlock<TrapDoorBlock> GOLDEN_BEECH_TRAPDOOR = BLOCKS.register("golden_beech_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS.get()).noOcclusion()));
	public static final DeferredBlock<TrapDoorBlock> COPPER_PINE_TRAPDOOR = BLOCKS.register("copper_pine_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_PLANKS.get()).noOcclusion()));
	public static final DeferredBlock<TrapDoorBlock> LAPIS_ROBINIA_TRAPDOOR = BLOCKS.register("lapis_robinia_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_PLANKS.get()).noOcclusion()));
	public static final DeferredBlock<TrapDoorBlock> SHADOW_PINE_TRAPDOOR = BLOCKS.register("shadow_pine_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_PLANKS.get()).noOcclusion()));
	public static final DeferredBlock<TrapDoorBlock> STELLAR_JUNGLE_TREE_TRAPDOOR = BLOCKS.register("stellar_jungle_tree_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_PLANKS.get()).noOcclusion()));
	public static final DeferredBlock<TrapDoorBlock> SKY_CACTUS_FIBER_TRAPDOOR = BLOCKS.register("sky_cactus_fiber_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_PLANKS.get()).noOcclusion()));
	public static final DeferredBlock<TrapDoorBlock> GRAY_SHROOM_TRAPDOOR = BLOCKS.register("gray_shroom_trapdoor", () -> new TrapDoorBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_PLANKS.get()).noOcclusion()));
	public static final DeferredBlock<TrapDoorBlock> RUBY_TRAPDOOR = BLOCKS.register("ruby_trapdoor", () -> new TrapDoorBlock(BlockSetType.IRON, METAL_NOTSOLID_MATERIAL));

	//buttons
	public static final DeferredBlock<ButtonBlock> STELLAR_STONE_BUTTON = BLOCKS.register("stellar_stone_button", () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get())));
	public static final DeferredBlock<ButtonBlock> STELLAR_COBBLESTONE_BUTTON = BLOCKS.register("stellar_cobblestone_button", () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE.get())));
	public static final DeferredBlock<ButtonBlock> STELLAR_STONE_BRICKS_BUTTON = BLOCKS.register("stellar_stone_bricks_button", () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS.get())));
	public static final DeferredBlock<ButtonBlock> SLIPPERY_SAND_STONE_BUTTON = BLOCKS.register("slippery_sand_stone_button", () -> new ButtonBlock(BlockSetType.STONE, 30, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get())));
	public static final DeferredBlock<ButtonBlock> SLIPPERY_SAND_STONE_BRICKS_BUTTON = BLOCKS.register("slippery_sand_stone_bricks_button", () -> new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final DeferredBlock<ButtonBlock> AERIAL_TREE_BUTTON = BLOCKS.register("aerial_tree_button", () -> new ButtonBlock(BlockSetType.OAK, 30, AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<ButtonBlock> GOLDEN_BEECH_BUTTON = BLOCKS.register("golden_beech_button", () -> new ButtonBlock(BlockSetType.OAK, 30, AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<ButtonBlock> COPPER_PINE_BUTTON = BLOCKS.register("copper_pine_button", () -> new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL));
	public static final DeferredBlock<ButtonBlock> LAPIS_ROBINIA_BUTTON = BLOCKS.register("lapis_robinia_button", () -> new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL));
	public static final DeferredBlock<ButtonBlock> SHADOW_PINE_BUTTON = BLOCKS.register("shadow_pine_button", () -> new ButtonBlock(BlockSetType.OAK, 30, SHADOW_PINE_MATERIAL));
	public static final DeferredBlock<ButtonBlock> STELLAR_JUNGLE_TREE_BUTTON = BLOCKS.register("stellar_jungle_tree_button", () -> new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL));
	public static final DeferredBlock<ButtonBlock> SKY_CACTUS_FIBER_BUTTON = BLOCKS.register("sky_cactus_fiber_button", () -> new ButtonBlock(BlockSetType.OAK, 30, SKY_CACTUS_FIBER_MATERIAL));
	public static final DeferredBlock<ButtonBlock> GRAY_SHROOM_BUTTON = BLOCKS.register("gray_shroom_button", () -> new ButtonBlock(BlockSetType.CRIMSON, 30, SHROOM_MATERIAL));
	public static final DeferredBlock<ButtonBlock> GLAUCOPHANITE_BUTTON = BLOCKS.register("glaucophanite_button", () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(GLAUCOPHANITE.get())));

	//pressure plates
	public static final DeferredBlock<PressurePlateBlock> STELLAR_STONE_PRESSURE_PLATE = BLOCKS.register("stellar_stone_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get())));
	public static final DeferredBlock<PressurePlateBlock> STELLAR_COBBLESTONE_PRESSURE_PLATE = BLOCKS.register("stellar_cobblestone_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE.get())));
	public static final DeferredBlock<PressurePlateBlock> STELLAR_STONE_BRICKS_PRESSURE_PLATE = BLOCKS.register("stellar_stone_bricks_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS.get())));
	public static final DeferredBlock<PressurePlateBlock> SLIPPERY_SAND_STONE_PRESSURE_PLATE = BLOCKS.register("slippery_sand_stone_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get())));
	public static final DeferredBlock<PressurePlateBlock> SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE = BLOCKS.register("slippery_sand_stone_bricks_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final DeferredBlock<PressurePlateBlock> AERIAL_TREE_PRESSURE_PLATE = BLOCKS.register("aerial_tree_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<PressurePlateBlock> GOLDEN_BEECH_PRESSURE_PLATE = BLOCKS.register("golden_beech_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<PressurePlateBlock> COPPER_PINE_PRESSURE_PLATE = BLOCKS.register("copper_pine_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL));
	public static final DeferredBlock<PressurePlateBlock> LAPIS_ROBINIA_PRESSURE_PLATE = BLOCKS.register("lapis_robinia_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL));
	public static final DeferredBlock<PressurePlateBlock> SHADOW_PINE_PRESSURE_PLATE = BLOCKS.register("shadow_pine_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, SHADOW_PINE_MATERIAL));
	public static final DeferredBlock<PressurePlateBlock> STELLAR_JUNGLE_TREE_PRESSURE_PLATE = BLOCKS.register("stellar_jungle_tree_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL));
	public static final DeferredBlock<PressurePlateBlock> SKY_CACTUS_FIBER_PRESSURE_PLATE = BLOCKS.register("sky_cactus_fiber_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, SKY_CACTUS_FIBER_MATERIAL));
	public static final DeferredBlock<PressurePlateBlock> GRAY_SHROOM_PRESSURE_PLATE = BLOCKS.register("gray_shroom_pressure_plate", () -> new PressurePlateBlock(BlockSetType.CRIMSON, SHROOM_MATERIAL));
	public static final DeferredBlock<PressurePlateBlock> GLAUCOPHANITE_PRESSURE_PLATE = BLOCKS.register("glaucophanite_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(GLAUCOPHANITE.get())));

	//slabs
	public static final DeferredBlock<SlabBlock> AERIAL_TREE_SLAB = BLOCKS.register("aerial_tree_slab", () -> new SlabBlock(AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<SlabBlock> GOLDEN_BEECH_SLAB = BLOCKS.register("golden_beech_slab", () -> new SlabBlock(AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<SlabBlock> COPPER_PINE_SLAB = BLOCKS.register("copper_pine_slab", () -> new SlabBlock(COPPER_PINE_MATERIAL));
	public static final DeferredBlock<SlabBlock> LAPIS_ROBINIA_SLAB = BLOCKS.register("lapis_robinia_slab", () -> new SlabBlock(COPPER_PINE_MATERIAL));
	public static final DeferredBlock<SlabBlock> SHADOW_PINE_SLAB = BLOCKS.register("shadow_pine_slab", () -> new SlabBlock(SHADOW_PINE_MATERIAL));
	public static final DeferredBlock<SlabBlock> STELLAR_JUNGLE_TREE_SLAB = BLOCKS.register("stellar_jungle_tree_slab", () -> new SlabBlock(COPPER_PINE_MATERIAL));
	public static final DeferredBlock<SlabBlock> SKY_CACTUS_FIBER_SLAB = BLOCKS.register("sky_cactus_fiber_slab", () -> new SlabBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final DeferredBlock<SlabBlock> GRAY_SHROOM_SLAB = BLOCKS.register("gray_shroom_slab", () -> new SlabBlock(SHROOM_MATERIAL));
	public static final DeferredBlock<SlabBlock> STELLAR_STONE_SLAB = BLOCKS.register("stellar_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get())));
	public static final DeferredBlock<SlabBlock> STELLAR_COBBLESTONE_SLAB = BLOCKS.register("stellar_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE.get())));
	public static final DeferredBlock<SlabBlock> STELLAR_STONE_BRICKS_SLAB = BLOCKS.register("stellar_stone_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS.get())));
	public static final DeferredBlock<SlabBlock> MOSSY_STELLAR_STONE_SLAB = BLOCKS.register("mossy_stellar_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_STONE.get())));
	public static final DeferredBlock<SlabBlock> MOSSY_STELLAR_COBBLESTONE_SLAB = BLOCKS.register("mossy_stellar_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_COBBLESTONE.get())));
	public static final DeferredBlock<SlabBlock> SLIPPERY_SAND_STONE_SLAB = BLOCKS.register("slippery_sand_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get())));
	public static final DeferredBlock<SlabBlock> SLIPPERY_SAND_STONE_BRICKS_SLAB = BLOCKS.register("slippery_sand_stone_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final DeferredBlock<SlabBlock> CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB = BLOCKS.register("cracked_slippery_sand_stone_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final DeferredBlock<SlabBlock> POLISHED_GLAUCOPHANITE_SLAB = BLOCKS.register("polished_glaucophanite_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_GLAUCOPHANITE.get())));
	public static final DeferredBlock<SlabBlock> MAGMATIC_GEL_SLAB = BLOCKS.register("magmatic_gel_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MAGMATIC_GEL_BLOCK.get())));

	//stairs
	public static final DeferredBlock<StairBlock> AERIAL_TREE_STAIRS = BLOCKS.register("aerial_tree_stairs", () -> new StairBlock(AERIAL_TREE_PLANKS.get().defaultBlockState(), AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<StairBlock> GOLDEN_BEECH_STAIRS = BLOCKS.register("golden_beech_stairs", () -> new StairBlock(GOLDEN_BEECH_PLANKS.get().defaultBlockState(), AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<StairBlock> COPPER_PINE_STAIRS = BLOCKS.register("copper_pine_stairs", () -> new StairBlock(COPPER_PINE_PLANKS.get().defaultBlockState(), COPPER_PINE_MATERIAL));
	public static final DeferredBlock<StairBlock> LAPIS_ROBINIA_STAIRS = BLOCKS.register("lapis_robinia_stairs", () -> new StairBlock(LAPIS_ROBINIA_PLANKS.get().defaultBlockState(), COPPER_PINE_MATERIAL));
	public static final DeferredBlock<StairBlock> SHADOW_PINE_STAIRS = BLOCKS.register("shadow_pine_stairs", () -> new StairBlock(SHADOW_PINE_PLANKS.get().defaultBlockState(), SHADOW_PINE_MATERIAL));
	public static final DeferredBlock<StairBlock> STELLAR_JUNGLE_TREE_STAIRS = BLOCKS.register("stellar_jungle_tree_stairs", () -> new StairBlock(STELLAR_JUNGLE_TREE_PLANKS.get().defaultBlockState(), COPPER_PINE_MATERIAL));
	public static final DeferredBlock<StairBlock> SKY_CACTUS_FIBER_STAIRS = BLOCKS.register("sky_cactus_fiber_stairs", () -> new StairBlock(SKY_CACTUS_FIBER_PLANKS.get().defaultBlockState(), SKY_CACTUS_FIBER_MATERIAL));
	public static final DeferredBlock<StairBlock> GRAY_SHROOM_STAIRS = BLOCKS.register("gray_shroom_stairs", () -> new StairBlock(GRAY_SHROOM_PLANKS.get().defaultBlockState(), SHROOM_MATERIAL));
	public static final DeferredBlock<StairBlock> STELLAR_STONE_STAIRS = BLOCKS.register("stellar_stone_stairs", () -> new StairBlock(STELLAR_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get())));
	public static final DeferredBlock<StairBlock> STELLAR_COBBLESTONE_STAIRS = BLOCKS.register("stellar_cobblestone_stairs", () -> new StairBlock(STELLAR_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE.get())));
	public static final DeferredBlock<StairBlock> STELLAR_STONE_BRICKS_STAIRS = BLOCKS.register("stellar_stone_bricks_stairs", () -> new StairBlock(STELLAR_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS.get())));
	public static final DeferredBlock<StairBlock> MOSSY_STELLAR_STONE_STAIRS = BLOCKS.register("mossy_stellar_stone_stairs", () -> new StairBlock(MOSSY_STELLAR_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_STONE.get())));
	public static final DeferredBlock<StairBlock> MOSSY_STELLAR_COBBLESTONE_STAIRS = BLOCKS.register("mossy_stellar_cobblestone_stairs", () -> new StairBlock(MOSSY_STELLAR_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_COBBLESTONE.get())));
	public static final DeferredBlock<StairBlock> SLIPPERY_SAND_STONE_STAIRS = BLOCKS.register("slippery_sand_stone_stairs", () -> new StairBlock(SLIPPERY_SAND_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get())));
	public static final DeferredBlock<StairBlock> SLIPPERY_SAND_STONE_BRICKS_STAIRS = BLOCKS.register("slippery_sand_stone_bricks_stairs", () -> new StairBlock(SLIPPERY_SAND_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final DeferredBlock<StairBlock> CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS = BLOCKS.register("cracked_slippery_sand_stone_bricks_stairs", () -> new StairBlock(SLIPPERY_SAND_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final DeferredBlock<StairBlock> POLISHED_GLAUCOPHANITE_STAIRS = BLOCKS.register("polished_glaucophanite_stairs", () -> new StairBlock(POLISHED_GLAUCOPHANITE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(POLISHED_GLAUCOPHANITE.get())));
	public static final DeferredBlock<StairBlock> MAGMATIC_GEL_STAIRS = BLOCKS.register("magmatic_gel_stairs", () -> new StairBlock(MAGMATIC_GEL_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MAGMATIC_GEL_BLOCK.get())));

	//signs
	public static final DeferredBlock<AerialHellStandingSignBlock> AERIAL_TREE_STANDING_SIGN = BLOCKS.register("aerial_tree_sign", () -> new AerialHellStandingSignBlock(AERIAL_TREE_SIGN_MATERIAL, AerialHellWoodTypes.AERIAL_TREE));
	public static final DeferredBlock<AerialHellWallSignBlock> AERIAL_TREE_WALL_SIGN = BLOCKS.register("aerial_tree_wall_sign", () -> new AerialHellWallSignBlock(AERIAL_TREE_SIGN_MATERIAL, AerialHellWoodTypes.AERIAL_TREE));
	public static final DeferredBlock<AerialHellStandingSignBlock> GOLDEN_BEECH_STANDING_SIGN = BLOCKS.register("golden_beech_sign", () -> new AerialHellStandingSignBlock(AERIAL_TREE_SIGN_MATERIAL, AerialHellWoodTypes.GOLDEN_BEECH));
	public static final DeferredBlock<AerialHellWallSignBlock> GOLDEN_BEECH_WALL_SIGN = BLOCKS.register("golden_beech_wall_sign", () -> new AerialHellWallSignBlock(AERIAL_TREE_SIGN_MATERIAL, AerialHellWoodTypes.GOLDEN_BEECH));
	public static final DeferredBlock<AerialHellStandingSignBlock> COPPER_PINE_STANDING_SIGN = BLOCKS.register("copper_pine_sign", () -> new AerialHellStandingSignBlock(COPPER_PINE_SIGN_MATERIAL, AerialHellWoodTypes.COPPER_PINE));
	public static final DeferredBlock<AerialHellWallSignBlock> COPPER_PINE_WALL_SIGN = BLOCKS.register("copper_pine_wall_sign", () -> new AerialHellWallSignBlock(COPPER_PINE_SIGN_MATERIAL, AerialHellWoodTypes.COPPER_PINE));
	public static final DeferredBlock<AerialHellStandingSignBlock> LAPIS_ROBINIA_STANDING_SIGN = BLOCKS.register("lapis_robinia_sign", () -> new AerialHellStandingSignBlock(COPPER_PINE_SIGN_MATERIAL, AerialHellWoodTypes.LAPIS_ROBINIA));
	public static final DeferredBlock<AerialHellWallSignBlock> LAPIS_ROBINIA_WALL_SIGN = BLOCKS.register("lapis_robinia_wall_sign", () -> new AerialHellWallSignBlock(COPPER_PINE_SIGN_MATERIAL, AerialHellWoodTypes.LAPIS_ROBINIA));
	public static final DeferredBlock<AerialHellStandingSignBlock> SHADOW_PINE_STANDING_SIGN = BLOCKS.register("shadow_pine_sign", () -> new AerialHellStandingSignBlock(SHADOW_PINE_SIGN_MATERIAL, AerialHellWoodTypes.SHADOW_PINE));
	public static final DeferredBlock<AerialHellWallSignBlock> SHADOW_PINE_WALL_SIGN = BLOCKS.register("shadow_pine_wall_sign", () -> new AerialHellWallSignBlock(SHADOW_PINE_SIGN_MATERIAL, AerialHellWoodTypes.SHADOW_PINE));
	public static final DeferredBlock<AerialHellStandingSignBlock> STELLAR_JUNGLE_TREE_STANDING_SIGN = BLOCKS.register("stellar_jungle_tree_sign", () -> new AerialHellStandingSignBlock(COPPER_PINE_SIGN_MATERIAL, AerialHellWoodTypes.STELLAR_JUNGLE_TREE));
	public static final DeferredBlock<AerialHellWallSignBlock> STELLAR_JUNGLE_TREE_WALL_SIGN = BLOCKS.register("stellar_jungle_tree_wall_sign", () -> new AerialHellWallSignBlock(COPPER_PINE_SIGN_MATERIAL, AerialHellWoodTypes.STELLAR_JUNGLE_TREE));
	public static final DeferredBlock<AerialHellStandingSignBlock> SKY_CACTUS_FIBER_STANDING_SIGN = BLOCKS.register("sky_cactus_fiber_sign", () -> new AerialHellStandingSignBlock(SKY_CACTUS_FIBER_SIGN_MATERIAL, AerialHellWoodTypes.SKY_CACTUS_FIBER));
	public static final DeferredBlock<AerialHellWallSignBlock> SKY_CACTUS_FIBER_WALL_SIGN = BLOCKS.register("sky_cactus_fiber_wall_sign", () -> new AerialHellWallSignBlock(SKY_CACTUS_FIBER_SIGN_MATERIAL, AerialHellWoodTypes.SKY_CACTUS_FIBER));
	public static final DeferredBlock<AerialHellStandingSignBlock> GRAY_SHROOM_STANDING_SIGN = BLOCKS.register("gray_shroom_sign", () -> new AerialHellStandingSignBlock(SHROOM_SIGN_MATERIAL, AerialHellWoodTypes.GRAY_SHROOM));
	public static final DeferredBlock<AerialHellWallSignBlock> GRAY_SHROOM_WALL_SIGN = BLOCKS.register("gray_shroom_wall_sign", () -> new AerialHellWallSignBlock(SHROOM_SIGN_MATERIAL, AerialHellWoodTypes.GRAY_SHROOM));

	//hanging signs
	public static final DeferredBlock<CeilingHangingSignBlock> AERIAL_TREE_HANGING_SIGN = BLOCKS.register("aerial_tree_hanging_sign", () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.AERIAL_TREE, AERIAL_TREE_SIGN_MATERIAL));
	public static final DeferredBlock<WallHangingSignBlock> AERIAL_TREE_WALL_HANGING_SIGN = BLOCKS.register("aerial_tree_wall_hanging_sign", () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.AERIAL_TREE, BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_HANGING_SIGN.get()).overrideLootTable(AERIAL_TREE_HANGING_SIGN.get().getLootTable())));
	public static final DeferredBlock<CeilingHangingSignBlock> GOLDEN_BEECH_HANGING_SIGN = BLOCKS.register("golden_beech_hanging_sign", () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.GOLDEN_BEECH, AERIAL_TREE_SIGN_MATERIAL));
	public static final DeferredBlock<WallHangingSignBlock> GOLDEN_BEECH_WALL_HANGING_SIGN = BLOCKS.register("golden_beech_wall_hanging_sign", () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.GOLDEN_BEECH, BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_HANGING_SIGN.get()).overrideLootTable(GOLDEN_BEECH_HANGING_SIGN.get().getLootTable())));
	public static final DeferredBlock<CeilingHangingSignBlock> COPPER_PINE_HANGING_SIGN = BLOCKS.register("copper_pine_hanging_sign", () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.COPPER_PINE, COPPER_PINE_SIGN_MATERIAL));
	public static final DeferredBlock<WallHangingSignBlock> COPPER_PINE_WALL_HANGING_SIGN = BLOCKS.register("copper_pine_wall_hanging_sign", () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.COPPER_PINE, BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_HANGING_SIGN.get()).overrideLootTable(COPPER_PINE_HANGING_SIGN.get().getLootTable())));
	public static final DeferredBlock<CeilingHangingSignBlock> LAPIS_ROBINIA_HANGING_SIGN = BLOCKS.register("lapis_robinia_hanging_sign", () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.LAPIS_ROBINIA, COPPER_PINE_SIGN_MATERIAL));
	public static final DeferredBlock<WallHangingSignBlock> LAPIS_ROBINIA_WALL_HANGING_SIGN = BLOCKS.register("lapis_robinia_wall_hanging_sign", () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.LAPIS_ROBINIA, BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_HANGING_SIGN.get()).overrideLootTable(LAPIS_ROBINIA_HANGING_SIGN.get().getLootTable())));
	public static final DeferredBlock<CeilingHangingSignBlock> SHADOW_PINE_HANGING_SIGN = BLOCKS.register("shadow_pine_hanging_sign", () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.SHADOW_PINE, SHADOW_PINE_SIGN_MATERIAL));
	public static final DeferredBlock<WallHangingSignBlock> SHADOW_PINE_WALL_HANGING_SIGN = BLOCKS.register("shadow_pine_wall_hanging_sign", () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.SHADOW_PINE, BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_HANGING_SIGN.get()).overrideLootTable(SHADOW_PINE_HANGING_SIGN.get().getLootTable())));
	public static final DeferredBlock<CeilingHangingSignBlock> STELLAR_JUNGLE_TREE_HANGING_SIGN = BLOCKS.register("stellar_jungle_tree_hanging_sign", () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, SHADOW_PINE_SIGN_MATERIAL));
	public static final DeferredBlock<WallHangingSignBlock> STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN = BLOCKS.register("stellar_jungle_tree_wall_hanging_sign", () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_HANGING_SIGN.get()).overrideLootTable(STELLAR_JUNGLE_TREE_HANGING_SIGN.get().getLootTable())));
	public static final DeferredBlock<CeilingHangingSignBlock> SKY_CACTUS_FIBER_HANGING_SIGN = BLOCKS.register("sky_cactus_fiber_hanging_sign", () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, SKY_CACTUS_FIBER_SIGN_MATERIAL));
	public static final DeferredBlock<WallHangingSignBlock> SKY_CACTUS_FIBER_WALL_HANGING_SIGN = BLOCKS.register("sky_cactus_fiber_wall_hanging_sign", () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_HANGING_SIGN.get()).overrideLootTable(SKY_CACTUS_FIBER_HANGING_SIGN.get().getLootTable())));
	public static final DeferredBlock<CeilingHangingSignBlock> GRAY_SHROOM_HANGING_SIGN = BLOCKS.register("gray_shroom_hanging_sign", () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.GRAY_SHROOM, SKY_CACTUS_FIBER_SIGN_MATERIAL));
	public static final DeferredBlock<WallHangingSignBlock> GRAY_SHROOM_WALL_HANGING_SIGN = BLOCKS.register("gray_shroom_wall_hanging_sign", () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.GRAY_SHROOM, BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_HANGING_SIGN.get()).overrideLootTable(GRAY_SHROOM_HANGING_SIGN.get().getLootTable())));

	//crafting tables
	public static final DeferredBlock<CraftingTableBlock> AERIAL_TREE_CRAFTING_TABLE = BLOCKS.register("aerial_tree_crafting_table", () -> new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<CraftingTableBlock> GOLDEN_BEECH_CRAFTING_TABLE = BLOCKS.register("golden_beech_crafting_table", () -> new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<CraftingTableBlock> COPPER_PINE_CRAFTING_TABLE = BLOCKS.register("copper_pine_crafting_table", () -> new AerialHellCraftingTableBlock(COPPER_PINE_MATERIAL));
	public static final DeferredBlock<CraftingTableBlock> LAPIS_ROBINIA_CRAFTING_TABLE = BLOCKS.register("lapis_robinia_crafting_table", () -> new AerialHellCraftingTableBlock(COPPER_PINE_MATERIAL));
	public static final DeferredBlock<CraftingTableBlock> SHADOW_PINE_CRAFTING_TABLE = BLOCKS.register("shadow_pine_crafting_table", () -> new AerialHellCraftingTableBlock(SHADOW_PINE_MATERIAL));
	public static final DeferredBlock<CraftingTableBlock> STELLAR_JUNGLE_TREE_CRAFTING_TABLE = BLOCKS.register("stellar_jungle_tree_crafting_table", () -> new AerialHellCraftingTableBlock(COPPER_PINE_MATERIAL));
	public static final DeferredBlock<CraftingTableBlock> SKY_CACTUS_FIBER_CRAFTING_TABLE = BLOCKS.register("sky_cactus_fiber_crafting_table", () -> new AerialHellCraftingTableBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final DeferredBlock<CraftingTableBlock> GRAY_SHROOM_CRAFTING_TABLE = BLOCKS.register("gray_shroom_crafting_table", () -> new AerialHellCraftingTableBlock(SHROOM_MATERIAL));

	//barrels
	public static final DeferredBlock<AerialHellBarrelBlock> AERIAL_TREE_BARREL = BLOCKS.register("aerial_tree_barrel", () -> new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<AerialHellBarrelBlock> GOLDEN_BEECH_BARREL = BLOCKS.register("golden_beech_barrel", () -> new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<AerialHellBarrelBlock> COPPER_PINE_BARREL = BLOCKS.register("copper_pine_barrel", () -> new AerialHellBarrelBlock(COPPER_PINE_MATERIAL));
	public static final DeferredBlock<AerialHellBarrelBlock> LAPIS_ROBINIA_BARREL = BLOCKS.register("lapis_robinia_barrel", () -> new AerialHellBarrelBlock(COPPER_PINE_MATERIAL));
	public static final DeferredBlock<AerialHellBarrelBlock> SHADOW_PINE_BARREL = BLOCKS.register("shadow_pine_barrel", () -> new AerialHellBarrelBlock(SHADOW_PINE_MATERIAL));
	public static final DeferredBlock<AerialHellBarrelBlock> STELLAR_JUNGLE_TREE_BARREL = BLOCKS.register("stellar_jungle_tree_barrel", () -> new AerialHellBarrelBlock(COPPER_PINE_MATERIAL));
	public static final DeferredBlock<AerialHellBarrelBlock> SKY_CACTUS_FIBER_BARREL = BLOCKS.register("sky_cactus_fiber_barrel", () -> new AerialHellBarrelBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final DeferredBlock<AerialHellBarrelBlock> GRAY_SHROOM_BARREL = BLOCKS.register("gray_shroom_barrel", () -> new AerialHellBarrelBlock(SHROOM_MATERIAL));

	//composters
	public static final DeferredBlock<ComposterBlock> AERIAL_TREE_COMPOSTER = BLOCKS.register("aerial_tree_composter", () -> new ComposterBlock(AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<ComposterBlock> GOLDEN_BEECH_COMPOSTER = BLOCKS.register("golden_beech_composter", () -> new ComposterBlock(AERIAL_TREE_MATERIAL));
	public static final DeferredBlock<ComposterBlock> COPPER_PINE_COMPOSTER = BLOCKS.register("copper_pine_composter", () -> new ComposterBlock(COPPER_PINE_MATERIAL));
	public static final DeferredBlock<ComposterBlock> LAPIS_ROBINIA_COMPOSTER = BLOCKS.register("lapis_robinia_composter", () -> new ComposterBlock(COPPER_PINE_MATERIAL));
	public static final DeferredBlock<ComposterBlock> SHADOW_PINE_COMPOSTER = BLOCKS.register("shadow_pine_composter", () -> new ComposterBlock(SHADOW_PINE_MATERIAL));
	public static final DeferredBlock<ComposterBlock> STELLAR_JUNGLE_TREE_COMPOSTER = BLOCKS.register("stellar_jungle_tree_composter", () -> new ComposterBlock(COPPER_PINE_MATERIAL));
	public static final DeferredBlock<ComposterBlock> SKY_CACTUS_FIBER_COMPOSTER = BLOCKS.register("sky_cactus_fiber_composter", () -> new ComposterBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final DeferredBlock<ComposterBlock> GRAY_SHROOM_COMPOSTER = BLOCKS.register("gray_shroom_composter", () -> new ComposterBlock(SHROOM_MATERIAL));

	//decorative
	public static final DeferredBlock<RotatedPillarBlock> AERIAL_TREE_VINE_ROPE_SPOOL = BLOCKS.register("aerial_tree_vine_rope_spool", () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> GOLDEN_BEECH_VINE_ROPE_SPOOL = BLOCKS.register("golden_beech_vine_rope_spool", () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> COPPER_PINE_VINE_ROPE_SPOOL = BLOCKS.register("copper_pine_vine_rope_spool", () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> LAPIS_ROBINIA_VINE_ROPE_SPOOL = BLOCKS.register("lapis_robinia_vine_rope_spool", () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> SHADOW_PINE_VINE_ROPE_SPOOL = BLOCKS.register("shadow_pine_vine_rope_spool", () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL = BLOCKS.register("stellar_jungle_tree_vine_rope_spool", () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> SKY_CACTUS_FIBER_VINE_ROPE_SPOOL = BLOCKS.register("sky_cactus_fiber_vine_rope_spool", () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> GRAY_SHROOM_VINE_ROPE_SPOOL = BLOCKS.register("gray_shroom_vine_rope_spool", () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));

	//fluids
	public static final DeferredBlock<LiquidBlock> LIQUID_OF_THE_GODS = BLOCKS.register("liquid_of_the_gods", () -> new AerialHellFluidBlock(AerialHellFluids.LIQUID_OF_THE_GODS_SOURCE.get(), BlockBehaviour.Properties.of().replaceable().lightLevel((state) -> 8)));
}
