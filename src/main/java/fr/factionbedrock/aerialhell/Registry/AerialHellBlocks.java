package fr.factionbedrock.aerialhell.Registry;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

import java.util.function.ToIntFunction;

import com.google.common.collect.ImmutableMap;

import fr.factionbedrock.aerialhell.AerialHell;
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
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellTreeGrowers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
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
	public static BlockBehaviour.Properties AERIAL_TREE_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion().noCollision();
	public static BlockBehaviour.Properties COPPER_PINE_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(4.5F, 4.5F).sound(SoundType.WOOD).noOcclusion().noCollision();
	public static BlockBehaviour.Properties SHADOW_PINE_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(4.0F, 4.0F).sound(SoundType.WOOD).noOcclusion().noCollision();
	public static BlockBehaviour.Properties SKY_CACTUS_FIBER_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.5F, 2.5F).sound(SoundType.WOOD);
	public static BlockBehaviour.Properties SKY_CACTUS_FIBER_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion().noCollision();
	public static BlockBehaviour.Properties SHROOM_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(3.5F, 3.5F).sound(SoundType.WOOD).noOcclusion().noCollision();
	public static BlockBehaviour.Properties MUD_CHEST_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(20.0F, 1000.0F).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion();
	public static BlockBehaviour.Properties LUNATIC_CHEST_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).strength(30.0F, 1000.0F).sound(SoundType.NETHERITE_BLOCK).requiresCorrectToolForDrops().noOcclusion();
	public static BlockBehaviour.Properties VOLUCITE_CHEST_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).strength(50.0F, 1200.0F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion();
	public static BlockBehaviour.Properties GOLDEN_NETHER_CHEST_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).strength(50.0F, 1200.0F).sound(SoundType.BASALT).requiresCorrectToolForDrops().noOcclusion();
	public static BlockBehaviour.Properties METAL_NOTSOLID_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(10.0F, 2.0F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion();

	//portal
	public static final DeferredBlock<AerialHellPortalBlock> AERIAL_HELL_PORTAL = BLOCKS.register(Keys.AERIAL_HELL_PORTAL.identifier().getPath(), () -> new AerialHellPortalBlock(BlockBehaviour.Properties.of().setId(Keys.AERIAL_HELL_PORTAL).sound(SoundType.GLASS).strength(-1F).noCollision().lightLevel((state) -> 10).noLootTable()));
	public static final DeferredBlock<Block> STELLAR_PORTAL_FRAME_BLOCK = BLOCKS.register(Keys.STELLAR_PORTAL_FRAME_BLOCK.identifier().getPath(), () -> new Block((BlockBehaviour.Properties.of().setId(Keys.STELLAR_PORTAL_FRAME_BLOCK).mapColor(MapColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(25.0F, 600.0F))));
	public static final DeferredBlock<Block> STELLAR_PORTAL_FRAME_ORE = BLOCKS.register(Keys.STELLAR_PORTAL_FRAME_ORE.identifier().getPath(),() -> new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().setId(Keys.STELLAR_PORTAL_FRAME_ORE).strength(25.0F, 600.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DEEPSLATE_STELLAR_PORTAL_FRAME_ORE = BLOCKS.register(Keys.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE.identifier().getPath(),() -> new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().setId(Keys.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE).strength(30.0F, 600.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));

	//torch
	public static final DeferredBlock<Block> CRYSTALLIZED_TORCH = BLOCKS.register(Keys.CRYSTALLIZED_TORCH.identifier().getPath(), () -> new AerialHellTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).setId(Keys.CRYSTALLIZED_TORCH).lightLevel((state) -> {return 9;})));
	public static final DeferredBlock<Block> CRYSTALLIZED_WALL_TORCH = BLOCKS.register(Keys.CRYSTALLIZED_WALL_TORCH.identifier().getPath(), () -> new AerialHellWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).setId(Keys.CRYSTALLIZED_WALL_TORCH).overrideLootTable(CRYSTALLIZED_TORCH.get().getLootTable()).lightLevel((state) -> {return 9;})));
	public static final DeferredBlock<Block> FLUORITE_TORCH = BLOCKS.register(Keys.FLUORITE_TORCH.identifier().getPath(), () -> new AerialHellTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).setId(Keys.FLUORITE_TORCH)));
	public static final DeferredBlock<Block> FLUORITE_WALL_TORCH = BLOCKS.register(Keys.FLUORITE_WALL_TORCH.identifier().getPath(), () -> new AerialHellWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).setId(Keys.FLUORITE_WALL_TORCH).overrideLootTable(FLUORITE_TORCH.get().getLootTable())));
	public static final DeferredBlock<Block> VOLUCITE_TORCH = BLOCKS.register(Keys.VOLUCITE_TORCH.identifier().getPath(), () -> new AerialHellTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).setId(Keys.VOLUCITE_TORCH).lightLevel((state) -> {return 9;})));
	public static final DeferredBlock<Block> VOLUCITE_WALL_TORCH = BLOCKS.register(Keys.VOLUCITE_WALL_TORCH.identifier().getPath(), () -> new AerialHellWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).setId(Keys.VOLUCITE_WALL_TORCH).overrideLootTable(VOLUCITE_TORCH.get().getLootTable()).lightLevel((state) -> {return 9;})));
	public static final DeferredBlock<Block> SHADOW_TORCH = BLOCKS.register(Keys.SHADOW_TORCH.identifier().getPath(), () -> new AerialHellTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).setId(Keys.SHADOW_TORCH).lightLevel((state) -> {return 9;})));
	public static final DeferredBlock<Block> SHADOW_WALL_TORCH = BLOCKS.register(Keys.SHADOW_WALL_TORCH.identifier().getPath(), () -> new AerialHellWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).setId(Keys.SHADOW_WALL_TORCH).overrideLootTable(SHADOW_TORCH.get().getLootTable()).lightLevel((state) -> {return 9;})));

	//lanterns
	public static final DeferredBlock<Block> CRYSTALLIZED_LANTERN = BLOCKS.register(Keys.CRYSTALLIZED_LANTERN.identifier().getPath(), () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.CRYSTALLIZED_LANTERN)));
	public static final DeferredBlock<Block> FLUORITE_LANTERN = BLOCKS.register(Keys.FLUORITE_LANTERN.identifier().getPath(), () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.FLUORITE_LANTERN)));
	public static final DeferredBlock<Block> RUBY_LANTERN = BLOCKS.register(Keys.RUBY_LANTERN.identifier().getPath(), () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.RUBY_LANTERN)));
	public static final DeferredBlock<Block> RUBY_CRYSTALLIZED_LANTERN = BLOCKS.register(Keys.RUBY_CRYSTALLIZED_LANTERN.identifier().getPath(), () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.RUBY_CRYSTALLIZED_LANTERN)));
	public static final DeferredBlock<Block> RUBY_FLUORITE_LANTERN = BLOCKS.register(Keys.RUBY_FLUORITE_LANTERN.identifier().getPath(), () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.RUBY_FLUORITE_LANTERN)));
	public static final DeferredBlock<Block> VOLUCITE_LANTERN = BLOCKS.register(Keys.VOLUCITE_LANTERN.identifier().getPath(), () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.VOLUCITE_LANTERN)));
	public static final DeferredBlock<Block> VOLUCITE_CRYSTALLIZED_LANTERN = BLOCKS.register(Keys.VOLUCITE_CRYSTALLIZED_LANTERN.identifier().getPath(), () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.VOLUCITE_CRYSTALLIZED_LANTERN)));
	public static final DeferredBlock<Block> VOLUCITE_FLUORITE_LANTERN = BLOCKS.register(Keys.VOLUCITE_FLUORITE_LANTERN.identifier().getPath(), () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.VOLUCITE_FLUORITE_LANTERN)));
	public static final DeferredBlock<Block> LUNATIC_LANTERN = BLOCKS.register(Keys.LUNATIC_LANTERN.identifier().getPath(), () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.LUNATIC_LANTERN)));
	public static final DeferredBlock<Block> SHADOW_LANTERN = BLOCKS.register(Keys.SHADOW_LANTERN.identifier().getPath(), () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_LANTERN).setId(Keys.SHADOW_LANTERN)));

	//chains
	public static final DeferredBlock<ChainBlock> RUBY_CHAIN = BLOCKS.register(Keys.RUBY_CHAIN.identifier().getPath(), () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_CHAIN).setId(Keys.RUBY_CHAIN)));
	public static final DeferredBlock<ChainBlock> VOLUCITE_CHAIN = BLOCKS.register(Keys.VOLUCITE_CHAIN.identifier().getPath(), () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_CHAIN).setId(Keys.VOLUCITE_CHAIN)));
	public static final DeferredBlock<ChainBlock> LUNATIC_CHAIN = BLOCKS.register(Keys.LUNATIC_CHAIN.identifier().getPath(), () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_CHAIN).setId(Keys.LUNATIC_CHAIN)));
	public static final DeferredBlock<ChainBlock> SHADOW_CHAIN = BLOCKS.register(Keys.SHADOW_CHAIN.identifier().getPath(), () -> new ShadowChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_CHAIN).setId(Keys.SHADOW_CHAIN)));

	//grass & dirt
	public static final DeferredBlock<StellarGrassBlock> STELLAR_GRASS_BLOCK = BLOCKS.register(Keys.STELLAR_GRASS_BLOCK.identifier().getPath(), () -> new StellarGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).setId(Keys.STELLAR_GRASS_BLOCK)));
	public static final DeferredBlock<Block> CHISELED_STELLAR_GRASS_BLOCK = BLOCKS.register(Keys.CHISELED_STELLAR_GRASS_BLOCK.identifier().getPath(), () -> new StellarGrassBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_GRASS_BLOCK.get()).setId(Keys.CHISELED_STELLAR_GRASS_BLOCK)));
	public static final DeferredBlock<Block> STELLAR_DIRT = BLOCKS.register(Keys.STELLAR_DIRT.identifier().getPath(), () -> new StellarDirtBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).setId(Keys.STELLAR_DIRT)));
	public static final DeferredBlock<Block> STELLAR_COARSE_DIRT = BLOCKS.register(Keys.STELLAR_COARSE_DIRT.identifier().getPath(), () -> new StellarDirtBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COARSE_DIRT).setId(Keys.STELLAR_COARSE_DIRT)));
	public static final DeferredBlock<Block> STELLAR_FARMLAND = BLOCKS.register(Keys.STELLAR_FARMLAND.identifier().getPath(), () -> new StellarFarmBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).setId(Keys.STELLAR_FARMLAND).randomTicks().strength(0.6F).sound(SoundType.GRAVEL).isViewBlocking((state, blockgetter, pos) -> true).isSuffocating((state, blockgetter, pos) -> true)));
	public static final DeferredBlock<Block> STELLAR_DIRT_PATH = BLOCKS.register(Keys.STELLAR_DIRT_PATH.identifier().getPath(), () -> new StellarDirtPathBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT_PATH).setId(Keys.STELLAR_DIRT_PATH)));
	public static final DeferredBlock<Block> STELLAR_PODZOL = BLOCKS.register(Keys.STELLAR_PODZOL.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL).setId(Keys.STELLAR_PODZOL)));
	public static final DeferredBlock<Block> STELLAR_CRYSTAL_PODZOL = BLOCKS.register(Keys.STELLAR_CRYSTAL_PODZOL.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL).setId(Keys.STELLAR_CRYSTAL_PODZOL)));
	public static final DeferredBlock<Block> CHISELED_STELLAR_DIRT = BLOCKS.register(Keys.CHISELED_STELLAR_DIRT.identifier().getPath(), () -> new StellarDirtBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_DIRT.get()).setId(Keys.CHISELED_STELLAR_DIRT)));
	public static final DeferredBlock<ShadowGrassBlock> SHADOW_GRASS_BLOCK = BLOCKS.register(Keys.SHADOW_GRASS_BLOCK.identifier().getPath(), () -> new ShadowGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).setId(Keys.SHADOW_GRASS_BLOCK)));

	//slippery sand
	public static final DeferredBlock<Block> SLIPPERY_SAND = BLOCKS.register(Keys.SLIPPERY_SAND.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).setId(Keys.SLIPPERY_SAND).friction(1.025F)));
	public static final DeferredBlock<Block> SLIPPERY_SAND_STONE = BLOCKS.register(Keys.SLIPPERY_SAND_STONE.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).setId(Keys.SLIPPERY_SAND_STONE).friction(1.01F)));
	public static final DeferredBlock<Block> SLIPPERY_SAND_STONE_BRICKS = BLOCKS.register(Keys.SLIPPERY_SAND_STONE_BRICKS.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get()).setId(Keys.SLIPPERY_SAND_STONE_BRICKS).friction(1.005F)));
	public static final DeferredBlock<Block> CUT_SLIPPERY_SAND_STONE = BLOCKS.register(Keys.CUT_SLIPPERY_SAND_STONE.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get()).setId(Keys.CUT_SLIPPERY_SAND_STONE).friction(1.005F)));
	public static final DeferredBlock<Block> CRACKED_SLIPPERY_SAND_STONE_BRICKS = BLOCKS.register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get()).setId(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS).friction(1.003F)));

	//giant root
	public static final DeferredBlock<RotatedPillarBlock> GIANT_ROOT = BLOCKS.register(Keys.GIANT_ROOT.identifier().getPath(), () -> new RotatedPillarBlock(AERIAL_TREE_MATERIAL.setId(Keys.GIANT_ROOT)));

    //aerial_tree
	public static final DeferredBlock<ShiftableLogBlock> AERIAL_TREE_LOG = BLOCKS.register(Keys.AERIAL_TREE_LOG.identifier().getPath(), () -> new ShiftableLogBlock(AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_LOG), () -> AerialHellBlocks.SHADOW_AERIAL_TREE_LOG.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_AERIAL_TREE_LOG = BLOCKS.register(Keys.STRIPPED_AERIAL_TREE_LOG.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get()).setId(Keys.STRIPPED_AERIAL_TREE_LOG)));
	public static final DeferredBlock<RotatedPillarBlock> AERIAL_TREE_WOOD = BLOCKS.register(Keys.AERIAL_TREE_WOOD.identifier().getPath(), () -> new RotatedPillarBlock(AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_AERIAL_TREE_WOOD = BLOCKS.register(Keys.STRIPPED_AERIAL_TREE_WOOD.identifier().getPath(), () -> new RotatedPillarBlock(AERIAL_TREE_MATERIAL.setId(Keys.STRIPPED_AERIAL_TREE_WOOD)));
	public static final DeferredBlock<ShiftableLeavesBlock> AERIAL_TREE_LEAVES = BLOCKS.register(Keys.AERIAL_TREE_LEAVES.identifier().getPath(), () -> new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(Keys.AERIAL_TREE_LEAVES), () -> AerialHellBlocks.SHADOW_AERIAL_TREE_LEAVES.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<Block> AERIAL_TREE_PLANKS = BLOCKS.register(Keys.AERIAL_TREE_PLANKS.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get()).setId(Keys.AERIAL_TREE_PLANKS)));
	public static final DeferredBlock<Block> CHISELED_AERIAL_TREE_PLANKS = BLOCKS.register(Keys.CHISELED_AERIAL_TREE_PLANKS.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS.get()).setId(Keys.CHISELED_AERIAL_TREE_PLANKS)));
	public static final DeferredBlock<Block> AERIAL_TREE_BOOKSHELF = BLOCKS.register(Keys.AERIAL_TREE_BOOKSHELF.identifier().getPath(), () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS.get()).setId(Keys.AERIAL_TREE_BOOKSHELF)));
	public static final DeferredBlock<SaplingBlock> AERIAL_TREE_SAPLING = BLOCKS.register(Keys.AERIAL_TREE_SAPLING.identifier().getPath(), () -> new AerialHellSaplingBlock(AerialHellTreeGrowers.AERIAL_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).setId(Keys.AERIAL_TREE_SAPLING), AerialHellConfiguredFeatures.GIANT_AERIAL_TREE));

	//petrified aerial tree
	public static final DeferredBlock<RotatedPillarBlock> PETRIFIED_AERIAL_TREE_LOG = BLOCKS.register(Keys.PETRIFIED_AERIAL_TREE_LOG.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get()).setId(Keys.PETRIFIED_AERIAL_TREE_LOG)));

	//golden beech
	public static final DeferredBlock<ShiftableLogBlock> GOLDEN_BEECH_LOG = BLOCKS.register(Keys.GOLDEN_BEECH_LOG.identifier().getPath(), () -> new ShiftableLogBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get()).setId(Keys.GOLDEN_BEECH_LOG), () -> AerialHellBlocks.SHADOW_GOLDEN_BEECH_LOG.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GOLDEN_BEECH_LOG = BLOCKS.register(Keys.STRIPPED_GOLDEN_BEECH_LOG.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG.get()).setId(Keys.STRIPPED_GOLDEN_BEECH_LOG)));
	public static final DeferredBlock<RotatedPillarBlock> GOLDEN_BEECH_WOOD = BLOCKS.register(Keys.GOLDEN_BEECH_WOOD.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG.get()).setId(Keys.GOLDEN_BEECH_WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GOLDEN_BEECH_WOOD = BLOCKS.register(Keys.STRIPPED_GOLDEN_BEECH_WOOD.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG.get()).setId(Keys.STRIPPED_GOLDEN_BEECH_WOOD)));
	public static final DeferredBlock<Block> GOLDEN_BEECH_PLANKS = BLOCKS.register(Keys.GOLDEN_BEECH_PLANKS.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG.get()).setId(Keys.GOLDEN_BEECH_PLANKS)));
	public static final DeferredBlock<Block> CHISELED_GOLDEN_BEECH_PLANKS = BLOCKS.register(Keys.CHISELED_GOLDEN_BEECH_PLANKS.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS.get()).setId(Keys.CHISELED_GOLDEN_BEECH_PLANKS)));
	public static final DeferredBlock<ShiftableLeavesBlock> GOLDEN_BEECH_LEAVES = BLOCKS.register(Keys.GOLDEN_BEECH_LEAVES.identifier().getPath(), () -> new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(Keys.GOLDEN_BEECH_LEAVES), () -> AerialHellBlocks.SHADOW_GOLDEN_BEECH_LEAVES.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<Block> GOLDEN_BEECH_BOOKSHELF = BLOCKS.register(Keys.GOLDEN_BEECH_BOOKSHELF.identifier().getPath(), () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS.get()).setId(Keys.GOLDEN_BEECH_BOOKSHELF)));
	public static final DeferredBlock<SaplingBlock> GOLDEN_BEECH_SAPLING = BLOCKS.register(Keys.GOLDEN_BEECH_SAPLING.identifier().getPath(), () -> new SaplingBlock(AerialHellTreeGrowers.GOLDEN_BEECH, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).setId(Keys.GOLDEN_BEECH_SAPLING)));

	//cropper pine
	public static final DeferredBlock<ShiftableLogBlock> COPPER_PINE_LOG = BLOCKS.register(Keys.COPPER_PINE_LOG.identifier().getPath(), () -> new ShiftableLogBlock(COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_LOG), () -> AerialHellBlocks.SHADOW_COPPER_PINE_LOG.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_COPPER_PINE_LOG = BLOCKS.register(Keys.STRIPPED_COPPER_PINE_LOG.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG.get()).setId(Keys.STRIPPED_COPPER_PINE_LOG)));
	public static final DeferredBlock<RotatedPillarBlock> COPPER_PINE_WOOD = BLOCKS.register(Keys.COPPER_PINE_WOOD.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG.get()).setId(Keys.COPPER_PINE_WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_COPPER_PINE_WOOD = BLOCKS.register(Keys.STRIPPED_COPPER_PINE_WOOD.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG.get()).setId(Keys.STRIPPED_COPPER_PINE_WOOD)));
	public static final DeferredBlock<Block> COPPER_PINE_PLANKS = BLOCKS.register(Keys.COPPER_PINE_PLANKS.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG.get()).setId(Keys.COPPER_PINE_PLANKS)));
	public static final DeferredBlock<ShiftableLeavesBlock> COPPER_PINE_LEAVES = BLOCKS.register(Keys.COPPER_PINE_LEAVES.identifier().getPath(), () -> new LeavesWithAmbientParticlesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(Keys.COPPER_PINE_LEAVES), () -> AerialHellBlocks.SHADOW_COPPER_PINE_LEAVES.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<Block> COPPER_PINE_BOOKSHELF = BLOCKS.register(Keys.COPPER_PINE_BOOKSHELF.identifier().getPath(), () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_PLANKS.get()).setId(Keys.COPPER_PINE_BOOKSHELF)));
	public static final DeferredBlock<SaplingBlock> COPPER_PINE_SAPLING = BLOCKS.register(Keys.COPPER_PINE_SAPLING.identifier().getPath(), () -> new AerialHellSaplingBlock(AerialHellTreeGrowers.COPPER_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).setId(Keys.COPPER_PINE_SAPLING), AerialHellConfiguredFeatures.GIANT_COPPER_PINE, AerialHellConfiguredFeatures.HUGE_COPPER_PINE, 0.1F));

	//lapis robinia
	public static final DeferredBlock<ShiftableLogBlock> LAPIS_ROBINIA_LOG = BLOCKS.register(Keys.LAPIS_ROBINIA_LOG.identifier().getPath(), () -> new ShiftableLogBlock(COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_LOG), () -> AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LOG.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<EffectLogBlock> ENCHANTED_LAPIS_ROBINIA_LOG = BLOCKS.register(Keys.ENCHANTED_LAPIS_ROBINIA_LOG.identifier().getPath(), () -> new EffectLogBlock(COPPER_PINE_MATERIAL.setId(Keys.ENCHANTED_LAPIS_ROBINIA_LOG), () -> AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LOG.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_LAPIS_ROBINIA_LOG = BLOCKS.register(Keys.STRIPPED_LAPIS_ROBINIA_LOG.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG.get()).setId(Keys.STRIPPED_LAPIS_ROBINIA_LOG)));
	public static final DeferredBlock<RotatedPillarBlock> LAPIS_ROBINIA_WOOD = BLOCKS.register(Keys.LAPIS_ROBINIA_WOOD.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG.get()).setId(Keys.LAPIS_ROBINIA_WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_LAPIS_ROBINIA_WOOD = BLOCKS.register(Keys.STRIPPED_LAPIS_ROBINIA_WOOD.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG.get()).setId(Keys.STRIPPED_LAPIS_ROBINIA_WOOD)));
	public static final DeferredBlock<ShiftableLeavesBlock> LAPIS_ROBINIA_LEAVES = BLOCKS.register(Keys.LAPIS_ROBINIA_LEAVES.identifier().getPath(), () -> new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(Keys.LAPIS_ROBINIA_LEAVES), () -> AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LEAVES.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<Block> LAPIS_ROBINIA_PLANKS = BLOCKS.register(Keys.LAPIS_ROBINIA_PLANKS.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG.get()).setId(Keys.LAPIS_ROBINIA_PLANKS)));
	public static final DeferredBlock<Block> LAPIS_ROBINIA_BOOKSHELF = BLOCKS.register(Keys.LAPIS_ROBINIA_BOOKSHELF.identifier().getPath(), () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_PLANKS.get()).setId(Keys.LAPIS_ROBINIA_BOOKSHELF)));
	public static final DeferredBlock<SaplingBlock> LAPIS_ROBINIA_SAPLING = BLOCKS.register(Keys.LAPIS_ROBINIA_SAPLING.identifier().getPath(), () -> new AerialHellSaplingBlock(AerialHellTreeGrowers.LAPIS_ROBINIA, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).setId(Keys.LAPIS_ROBINIA_SAPLING), AerialHellConfiguredFeatures.GIANT_LAPIS_ROBINIA));

	//shadow_pine
	public static final DeferredBlock<ShiftableLogBlock> SHADOW_PINE_LOG = BLOCKS.register(Keys.SHADOW_PINE_LOG.identifier().getPath(), () -> new ShadowLogBlock(SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_LOG), () -> AerialHellBlocks.HOLLOW_SHADOW_PINE_LOG.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShiftableLogBlock> EYE_SHADOW_PINE_LOG = BLOCKS.register(Keys.EYE_SHADOW_PINE_LOG.identifier().getPath(), () -> new ShadowEffectLogBlock(SHADOW_PINE_MATERIAL.setId(Keys.EYE_SHADOW_PINE_LOG), () -> AerialHellBlocks.HOLLOW_SHADOW_PINE_LOG.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_SHADOW_PINE_LOG = BLOCKS.register(Keys.STRIPPED_SHADOW_PINE_LOG.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG.get()).setId(Keys.STRIPPED_SHADOW_PINE_LOG)));
	public static final DeferredBlock<RotatedPillarBlock> SHADOW_PINE_WOOD = BLOCKS.register(Keys.SHADOW_PINE_WOOD.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG.get()).setId(Keys.SHADOW_PINE_WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_SHADOW_PINE_WOOD = BLOCKS.register(Keys.STRIPPED_SHADOW_PINE_WOOD.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG.get()).setId(Keys.STRIPPED_SHADOW_PINE_WOOD)));
	public static final DeferredBlock<ShiftableLeavesBlock> SHADOW_PINE_LEAVES = BLOCKS.register(Keys.SHADOW_PINE_LEAVES.identifier().getPath(), () -> new ShadowLeavesWithParticlesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(Keys.SHADOW_PINE_LEAVES), () -> AerialHellBlocks.HOLLOW_SHADOW_PINE_LEAVES.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShiftableLeavesBlock> PURPLE_SHADOW_PINE_LEAVES = BLOCKS.register(Keys.PURPLE_SHADOW_PINE_LEAVES.identifier().getPath(), () -> new ShadowLeavesWithParticlesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(Keys.PURPLE_SHADOW_PINE_LEAVES), () -> AerialHellBlocks.HOLLOW_PURPLE_SHADOW_PINE_LEAVES.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<Block> SHADOW_PINE_PLANKS = BLOCKS.register(Keys.SHADOW_PINE_PLANKS.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG.get()).setId(Keys.SHADOW_PINE_PLANKS)));
	public static final DeferredBlock<Block> SHADOW_PINE_BOOKSHELF = BLOCKS.register(Keys.SHADOW_PINE_BOOKSHELF.identifier().getPath(), () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_PLANKS.get()).setId(Keys.SHADOW_PINE_BOOKSHELF)));
	public static final DeferredBlock<SaplingBlock> SHADOW_PINE_SAPLING = BLOCKS.register(Keys.SHADOW_PINE_SAPLING.identifier().getPath(), () -> new ShadowPineSaplingBlock(AerialHellTreeGrowers.SHADOW_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).setId(Keys.SHADOW_PINE_SAPLING), AerialHellConfiguredFeatures.GIANT_SHADOW_PINE, AerialHellConfiguredFeatures.HUGE_SHADOW_PINE, 0.1F));
	public static final DeferredBlock<SaplingBlock> PURPLE_SHADOW_PINE_SAPLING = BLOCKS.register(Keys.PURPLE_SHADOW_PINE_SAPLING.identifier().getPath(), () -> new ShadowPineSaplingBlock(AerialHellTreeGrowers.PURPLE_SHADOW_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).setId(Keys.PURPLE_SHADOW_PINE_SAPLING), AerialHellConfiguredFeatures.GIANT_PURPLE_SHADOW_PINE, AerialHellConfiguredFeatures.HUGE_PURPLE_SHADOW_PINE, 0.1F));

	//stellar jungle tree
	public static final DeferredBlock<ShiftableLogBlock> STELLAR_JUNGLE_TREE_LOG = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_LOG.identifier().getPath(), () -> new ShiftableLogBlock(COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_LOG), () -> AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LOG.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_STELLAR_JUNGLE_TREE_LOG = BLOCKS.register(Keys.STRIPPED_STELLAR_JUNGLE_TREE_LOG.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG.get()).setId(Keys.STRIPPED_STELLAR_JUNGLE_TREE_LOG)));
	public static final DeferredBlock<RotatedPillarBlock> STELLAR_JUNGLE_TREE_WOOD = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_WOOD.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG.get()).setId(Keys.STELLAR_JUNGLE_TREE_WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_STELLAR_JUNGLE_TREE_WOOD = BLOCKS.register(Keys.STRIPPED_STELLAR_JUNGLE_TREE_WOOD.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG.get()).setId(Keys.STRIPPED_STELLAR_JUNGLE_TREE_WOOD)));
	public static final DeferredBlock<ShiftableLeavesBlock> STELLAR_JUNGLE_TREE_LEAVES = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_LEAVES.identifier().getPath(), () -> new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(Keys.STELLAR_JUNGLE_TREE_LEAVES), () -> AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<Block> STELLAR_JUNGLE_TREE_PLANKS = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_PLANKS.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG.get()).setId(Keys.STELLAR_JUNGLE_TREE_PLANKS)));
	public static final DeferredBlock<Block> STELLAR_JUNGLE_TREE_BOOKSHELF = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_BOOKSHELF.identifier().getPath(), () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_PLANKS.get()).setId(Keys.STELLAR_JUNGLE_TREE_BOOKSHELF)));
	public static final DeferredBlock<SaplingBlock> STELLAR_JUNGLE_TREE_SAPLING = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_SAPLING.identifier().getPath(), () -> new AerialHellSaplingBlock(AerialHellTreeGrowers.STELLAR_JUNGLE_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).setId(Keys.STELLAR_JUNGLE_TREE_SAPLING), AerialHellConfiguredFeatures.GIANT_STELLAR_JUNGLE_TREE));
	public static final DeferredBlock<LargeDeadLogBlock> DEAD_STELLAR_JUNGLE_TREE_LOG = BLOCKS.register(Keys.DEAD_STELLAR_JUNGLE_TREE_LOG.identifier().getPath(), () -> new LargeDeadLogBlock(STELLAR_JUNGLE_TREE_PLANKS.get().defaultBlockState(), COPPER_PINE_MATERIAL.setId(Keys.DEAD_STELLAR_JUNGLE_TREE_LOG)));

	//shroom
	public static final DeferredBlock<RotatedPillarBlock> GIANT_CORTINARIUS_VIOLACEUS_STEM = BLOCKS.register(Keys.GIANT_CORTINARIUS_VIOLACEUS_STEM.identifier().getPath(), () -> new RotatedPillarBlock(SHROOM_MATERIAL.setId(Keys.GIANT_CORTINARIUS_VIOLACEUS_STEM)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM = BLOCKS.register(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get()).setId(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM)));
	public static final DeferredBlock<RotatedPillarBlock> GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = BLOCKS.register(Keys.GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get()).setId(Keys.GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = BLOCKS.register(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get()).setId(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM)));
	public static final DeferredBlock<Block> GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK = BLOCKS.register(Keys.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.of().setId(Keys.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK).mapColor(MapColor.TERRACOTTA_BLUE).strength(0.5F).sound(SoundType.STEM)));
	public static final DeferredBlock<Block> GIANT_CORTINARIUS_VIOLACEUS_LIGHT = BLOCKS.register(Keys.GIANT_CORTINARIUS_VIOLACEUS_LIGHT.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.of().setId(Keys.GIANT_CORTINARIUS_VIOLACEUS_LIGHT).mapColor(MapColor.COLOR_PURPLE).strength(1.0F).sound(SoundType.SHROOMLIGHT).lightLevel((state) -> {return 15;})));
	public static final DeferredBlock<FungusBlock> CORTINARIUS_VIOLACEUS = BLOCKS.register(Keys.CORTINARIUS_VIOLACEUS.identifier().getPath(), () -> new AerialHellFungusBlock(AerialHellConfiguredFeatures.GIANT_CORTINARIUS_VIOLACEUS_PLANTED, STELLAR_GRASS_BLOCK.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FUNGUS).setId(Keys.CORTINARIUS_VIOLACEUS)));
	public static final DeferredBlock<Block> GLOWING_BOLETUS = BLOCKS.register(Keys.GLOWING_BOLETUS.identifier().getPath(), () -> new AerialHellTallShroomBlock(BlockBehaviour.Properties.of().setId(Keys.GLOWING_BOLETUS).mapColor(MapColor.PLANT).noCollision().lightLevel((state) -> {return 9;}).instabreak().sound(SoundType.GLOW_LICHEN), true));
	public static final DeferredBlock<Block> TALL_GLOWING_BOLETUS = BLOCKS.register(Keys.TALL_GLOWING_BOLETUS.identifier().getPath(), () -> new DoubleShroomBlock(BlockBehaviour.Properties.of().setId(Keys.TALL_GLOWING_BOLETUS).mapColor(MapColor.PLANT).noCollision().lightLevel((state) -> {return 11;}).instabreak().sound(SoundType.GLOW_LICHEN)));
	public static final DeferredBlock<Block> BLUE_MEANIE_CLUSTER = BLOCKS.register(Keys.BLUE_MEANIE_CLUSTER.identifier().getPath(), () -> new TallShroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH).setId(Keys.BLUE_MEANIE_CLUSTER)));
	public static final DeferredBlock<Block> GIANT_ROOT_SHROOM = BLOCKS.register(Keys.GIANT_ROOT_SHROOM.identifier().getPath(), () -> new AerialHellTallShroomBlock(BlockBehaviour.Properties.of().setId(Keys.GIANT_ROOT_SHROOM).mapColor(MapColor.PLANT).noCollision().instabreak().sound(SoundType.NETHER_WART), false));

	public static final DeferredBlock<RotatedPillarBlock> GIANT_VERDIGRIS_AGARIC_STEM = BLOCKS.register(Keys.GIANT_VERDIGRIS_AGARIC_STEM.identifier().getPath(), () -> new RotatedPillarBlock(SHROOM_MATERIAL.setId(Keys.GIANT_VERDIGRIS_AGARIC_STEM)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM = BLOCKS.register(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get()).setId(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM)));
	public static final DeferredBlock<RotatedPillarBlock> GIANT_VERDIGRIS_AGARIC_BARK_STEM = BLOCKS.register(Keys.GIANT_VERDIGRIS_AGARIC_BARK_STEM.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get()).setId(Keys.GIANT_VERDIGRIS_AGARIC_BARK_STEM)));
	public static final DeferredBlock<RotatedPillarBlock> STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM = BLOCKS.register(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get()).setId(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM)));
	public static final DeferredBlock<Block> GIANT_VERDIGRIS_AGARIC_CAP_BLOCK = BLOCKS.register(Keys.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK.identifier().getPath(), () -> new HugeMushroomBlock(BlockBehaviour.Properties.of().setId(Keys.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK).mapColor(MapColor.TERRACOTTA_BLUE).lightLevel((state) -> {return 10;}).strength(0.4F).sound(SoundType.STEM)));
	public static final DeferredBlock<MushroomBlock> VERDIGRIS_AGARIC = BLOCKS.register(Keys.VERDIGRIS_AGARIC.identifier().getPath(), () -> new AerialHellMushroomBlock(AerialHellConfiguredFeatures.GIANT_VERDIGRIS_AGARIC, BlockBehaviour.Properties.of().setId(Keys.VERDIGRIS_AGARIC).mapColor(MapColor.CLAY).noCollision().randomTicks().instabreak().sound(SoundType.GRASS)));

	public static final DeferredBlock<Block> GIANT_GANODERMA_APPLANATUM_BLOCK = BLOCKS.register(Keys.GIANT_GANODERMA_APPLANATUM_BLOCK.identifier().getPath(), () -> new HugeMushroomBlock(BlockBehaviour.Properties.of().setId(Keys.GIANT_GANODERMA_APPLANATUM_BLOCK).mapColor(MapColor.COLOR_BROWN).strength(0.4F).sound(SoundType.STEM)));

	public static final DeferredBlock<Block> GRAY_SHROOM_PLANKS = BLOCKS.register(Keys.GRAY_SHROOM_PLANKS.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get()).setId(Keys.GRAY_SHROOM_PLANKS)));
	public static final DeferredBlock<Block> GRAY_SHROOM_BOOKSHELF = BLOCKS.register(Keys.GRAY_SHROOM_BOOKSHELF.identifier().getPath(), () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_PLANKS.get()).setId(Keys.GRAY_SHROOM_BOOKSHELF)));

	//shadow corrupted / uncorrupted variants
	public static final DeferredBlock<ShadowLogBlock> SHADOW_AERIAL_TREE_LOG = BLOCKS.register(Keys.SHADOW_AERIAL_TREE_LOG.identifier().getPath(), () -> new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get()).setId(Keys.SHADOW_AERIAL_TREE_LOG), () -> AERIAL_TREE_LOG.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShadowLogBlock> SHADOW_GOLDEN_BEECH_LOG = BLOCKS.register(Keys.SHADOW_GOLDEN_BEECH_LOG.identifier().getPath(), () -> new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG.get()).setId(Keys.SHADOW_GOLDEN_BEECH_LOG), () -> GOLDEN_BEECH_LOG.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShadowLogBlock> SHADOW_COPPER_PINE_LOG = BLOCKS.register(Keys.SHADOW_COPPER_PINE_LOG.identifier().getPath(), () -> new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG.get()).setId(Keys.SHADOW_COPPER_PINE_LOG), () -> COPPER_PINE_LOG.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShadowLogBlock> SHADOW_LAPIS_ROBINIA_LOG = BLOCKS.register(Keys.SHADOW_LAPIS_ROBINIA_LOG.identifier().getPath(), () -> new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG.get()).setId(Keys.SHADOW_LAPIS_ROBINIA_LOG), () -> LAPIS_ROBINIA_LOG.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShadowLogBlock> SHADOW_STELLAR_JUNGLE_TREE_LOG = BLOCKS.register(Keys.SHADOW_STELLAR_JUNGLE_TREE_LOG.identifier().getPath(), () -> new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG.get()).setId(Keys.SHADOW_STELLAR_JUNGLE_TREE_LOG), () -> STELLAR_JUNGLE_TREE_LOG.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShiftableLogBlock> HOLLOW_SHADOW_PINE_LOG = BLOCKS.register(Keys.HOLLOW_SHADOW_PINE_LOG.identifier().getPath(), () -> new ShiftableLogBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get()).setId(Keys.HOLLOW_SHADOW_PINE_LOG), () -> SHADOW_PINE_LOG.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<ShiftableLeavesBlock> SHADOW_AERIAL_TREE_LEAVES = BLOCKS.register(Keys.SHADOW_AERIAL_TREE_LEAVES.identifier().getPath(), () -> new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LEAVES.get()).setId(Keys.SHADOW_AERIAL_TREE_LEAVES), () -> AerialHellBlocks.AERIAL_TREE_LEAVES.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShiftableLeavesBlock> SHADOW_GOLDEN_BEECH_LEAVES = BLOCKS.register(Keys.SHADOW_GOLDEN_BEECH_LEAVES.identifier().getPath(), () -> new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LEAVES.get()).setId(Keys.SHADOW_GOLDEN_BEECH_LEAVES), () -> AerialHellBlocks.GOLDEN_BEECH_LEAVES.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShiftableLeavesBlock> SHADOW_COPPER_PINE_LEAVES = BLOCKS.register(Keys.SHADOW_COPPER_PINE_LEAVES.identifier().getPath(), () -> new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LEAVES.get()).setId(Keys.SHADOW_COPPER_PINE_LEAVES), () -> AerialHellBlocks.COPPER_PINE_LEAVES.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShiftableLeavesBlock> SHADOW_LAPIS_ROBINIA_LEAVES = BLOCKS.register(Keys.SHADOW_LAPIS_ROBINIA_LEAVES.identifier().getPath(), () -> new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LEAVES.get()).setId(Keys.SHADOW_LAPIS_ROBINIA_LEAVES), () -> AerialHellBlocks.LAPIS_ROBINIA_LEAVES.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShiftableLeavesBlock> SHADOW_STELLAR_JUNGLE_TREE_LEAVES = BLOCKS.register(Keys.SHADOW_STELLAR_JUNGLE_TREE_LEAVES.identifier().getPath(), () -> new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LEAVES.get()).setId(Keys.SHADOW_STELLAR_JUNGLE_TREE_LEAVES), () -> AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES.get(), BiomeShifter.ShiftType.UNCORRUPT));
	public static final DeferredBlock<ShiftableLeavesBlock> HOLLOW_SHADOW_PINE_LEAVES = BLOCKS.register(Keys.HOLLOW_SHADOW_PINE_LEAVES.identifier().getPath(), () -> new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LEAVES.get()).setId(Keys.HOLLOW_SHADOW_PINE_LEAVES), () -> AerialHellBlocks.SHADOW_PINE_LEAVES.get(), BiomeShifter.ShiftType.CORRUPT));
	public static final DeferredBlock<ShiftableLeavesBlock> HOLLOW_PURPLE_SHADOW_PINE_LEAVES = BLOCKS.register(Keys.HOLLOW_PURPLE_SHADOW_PINE_LEAVES.identifier().getPath(), () -> new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(PURPLE_SHADOW_PINE_LEAVES.get()).setId(Keys.HOLLOW_PURPLE_SHADOW_PINE_LEAVES), () -> AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES.get(), BiomeShifter.ShiftType.CORRUPT));

	//ladder
	public static final DeferredBlock<LadderBlock> SKY_LADDER = BLOCKS.register(Keys.SKY_LADDER.identifier().getPath(), () -> new LadderBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS.get()).setId(Keys.SKY_LADDER).noOcclusion()));

	//natural blocks and items
	public static final DeferredBlock<Block> STELLAR_STONE = BLOCKS.register(Keys.STELLAR_STONE.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(Keys.STELLAR_STONE)));
	public static final DeferredBlock<Block> STELLAR_COBBLESTONE = BLOCKS.register(Keys.STELLAR_COBBLESTONE.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE).setId(Keys.STELLAR_COBBLESTONE)));
	public static final DeferredBlock<Block> STELLAR_STONE_BRICKS = BLOCKS.register(Keys.STELLAR_STONE_BRICKS.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).setId(Keys.STELLAR_STONE_BRICKS).strength(0.5F, 10.0F)));
	public static final DeferredBlock<Block> MOSSY_STELLAR_STONE = BLOCKS.register(Keys.MOSSY_STELLAR_STONE.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get()).setId(Keys.MOSSY_STELLAR_STONE)));
	public static final DeferredBlock<Block> MOSSY_STELLAR_COBBLESTONE = BLOCKS.register(Keys.MOSSY_STELLAR_COBBLESTONE.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get()).setId(Keys.MOSSY_STELLAR_COBBLESTONE)));
	public static final DeferredBlock<Block> STELLAR_CLAY = BLOCKS.register(Keys.STELLAR_CLAY.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CLAY).setId(Keys.STELLAR_CLAY)));
	public static final DeferredBlock<Block> GLAUCOPHANITE = BLOCKS.register(Keys.GLAUCOPHANITE.identifier().getPath(),() -> new Block(BlockBehaviour.Properties.of().setId(Keys.GLAUCOPHANITE).strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> POLISHED_GLAUCOPHANITE = BLOCKS.register(Keys.POLISHED_GLAUCOPHANITE.identifier().getPath(),() -> new Block(BlockBehaviour.Properties.of().setId(Keys.POLISHED_GLAUCOPHANITE).strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> SHADOW_STONE = BLOCKS.register(Keys.SHADOW_STONE.identifier().getPath(), () -> new ShadowStoneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(Keys.SHADOW_STONE)));

	//crystal
	public static final DeferredBlock<Block> CRYSTAL_BLOCK = BLOCKS.register(Keys.CRYSTAL_BLOCK.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).setId(Keys.CRYSTAL_BLOCK).lightLevel((state) -> 14)));
	public static final DeferredBlock<Block> CRYSTAL_BRICKS = BLOCKS.register(Keys.CRYSTAL_BRICKS.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).setId(Keys.CRYSTAL_BRICKS).lightLevel((state) -> 9)));
	public static final DeferredBlock<SlabBlock> CRYSTAL_BRICKS_SLAB = BLOCKS.register(Keys.CRYSTAL_BRICKS_SLAB.identifier().getPath(), () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BRICKS.get()).setId(Keys.CRYSTAL_BRICKS_SLAB)));
	public static final DeferredBlock<StairBlock> CRYSTAL_BRICKS_STAIRS = BLOCKS.register(Keys.CRYSTAL_BRICKS_STAIRS.identifier().getPath(), () -> new StairBlock(CRYSTAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BRICKS.get()).setId(Keys.CRYSTAL_BRICKS_STAIRS)));
	public static final DeferredBlock<WallBlock> CRYSTAL_BRICKS_WALL = BLOCKS.register(Keys.CRYSTAL_BRICKS_WALL.identifier().getPath(), () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BRICKS.get()).setId(Keys.CRYSTAL_BRICKS_WALL)));
	public static final DeferredBlock<Block> STELLAR_STONE_CRYSTAL_BLOCK = BLOCKS.register(Keys.STELLAR_STONE_CRYSTAL_BLOCK.identifier().getPath(), () -> new BasicShiftableRenderBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BLOCK.get()).setId(Keys.STELLAR_STONE_CRYSTAL_BLOCK).lightLevel((state) -> 13)));
	public static final DeferredBlock<Block> SHADOW_CRYSTAL_BLOCK = BLOCKS.register(Keys.SHADOW_CRYSTAL_BLOCK.identifier().getPath(), () -> new BasicShadowSpreaderBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BLOCK.get()).setId(Keys.SHADOW_CRYSTAL_BLOCK).lightLevel((state) -> 12)));
	public static final DeferredBlock<Block> CRYSTALLIZED_LEAVES = BLOCKS.register(Keys.CRYSTALLIZED_LEAVES.identifier().getPath(), () -> new UntintedParticleLeavesBlock(0.1F, AerialHellParticleTypes.FALLING_CRYSTALLIZED_LEAVES.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(Keys.CRYSTALLIZED_LEAVES).lightLevel((state) -> 12)));
	public static final DeferredBlock<Block> CRYSTALLIZED_FIRE = BLOCKS.register(Keys.CRYSTALLIZED_FIRE.identifier().getPath(), () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).setId(Keys.CRYSTALLIZED_FIRE).lightLevel((state) -> 12).instabreak()));

	//glass and glass pane
	public static final DeferredBlock<Block> SLIPPERY_SAND_GLASS = BLOCKS.register(Keys.SLIPPERY_SAND_GLASS.identifier().getPath(), () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).setId(Keys.SLIPPERY_SAND_GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
	public static final DeferredBlock<Block> RED_SLIPPERY_SAND_GLASS = BLOCKS.register(Keys.RED_SLIPPERY_SAND_GLASS.identifier().getPath(), () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).setId(Keys.RED_SLIPPERY_SAND_GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
	public static final DeferredBlock<Block> BLACK_SLIPPERY_SAND_GLASS = BLOCKS.register(Keys.BLACK_SLIPPERY_SAND_GLASS.identifier().getPath(), () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).setId(Keys.BLACK_SLIPPERY_SAND_GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
	public static final DeferredBlock<Block> BLUE_SLIPPERY_SAND_GLASS = BLOCKS.register(Keys.BLUE_SLIPPERY_SAND_GLASS.identifier().getPath(), () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).setId(Keys.BLUE_SLIPPERY_SAND_GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
	public static final DeferredBlock<Block> GREEN_SLIPPERY_SAND_GLASS = BLOCKS.register(Keys.GREEN_SLIPPERY_SAND_GLASS.identifier().getPath(), () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).setId(Keys.GREEN_SLIPPERY_SAND_GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
	public static final DeferredBlock<Block> SLIPPERY_SAND_GLASS_PANE = BLOCKS.register(Keys.SLIPPERY_SAND_GLASS_PANE.identifier().getPath(), () -> new StainedGlassPaneBlock(DyeColor.YELLOW, BlockBehaviour.Properties.of().setId(Keys.SLIPPERY_SAND_GLASS_PANE).friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
	public static final DeferredBlock<Block> RED_SLIPPERY_SAND_GLASS_PANE = BLOCKS.register(Keys.RED_SLIPPERY_SAND_GLASS_PANE.identifier().getPath(), () -> new StainedGlassPaneBlock(DyeColor.RED, BlockBehaviour.Properties.of().setId(Keys.RED_SLIPPERY_SAND_GLASS_PANE).friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
	public static final DeferredBlock<Block> BLACK_SLIPPERY_SAND_GLASS_PANE = BLOCKS.register(Keys.BLACK_SLIPPERY_SAND_GLASS_PANE.identifier().getPath(), () -> new StainedGlassPaneBlock(DyeColor.BLACK, BlockBehaviour.Properties.of().setId(Keys.BLACK_SLIPPERY_SAND_GLASS_PANE).friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
	public static final DeferredBlock<Block> BLUE_SLIPPERY_SAND_GLASS_PANE = BLOCKS.register(Keys.BLUE_SLIPPERY_SAND_GLASS_PANE.identifier().getPath(), () -> new StainedGlassPaneBlock(DyeColor.BLUE, BlockBehaviour.Properties.of().setId(Keys.BLUE_SLIPPERY_SAND_GLASS_PANE).friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
	public static final DeferredBlock<Block> GREEN_SLIPPERY_SAND_GLASS_PANE = BLOCKS.register(Keys.GREEN_SLIPPERY_SAND_GLASS_PANE.identifier().getPath(), () -> new StainedGlassPaneBlock(DyeColor.GREEN, BlockBehaviour.Properties.of().setId(Keys.GREEN_SLIPPERY_SAND_GLASS_PANE).friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));

	//ghost boat
	public static final DeferredBlock<Block> GHOST_BOAT_PLANKS = BLOCKS.register(Keys.GHOST_BOAT_PLANKS.identifier().getPath(), () -> new GhostBoatBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_PLANKS).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<GhostBoatRotatedPillarBlock> GHOST_BOAT_LOG = BLOCKS.register(Keys.GHOST_BOAT_LOG.identifier().getPath(), () -> new GhostBoatRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get()).setId(Keys.GHOST_BOAT_LOG).noOcclusion()));
	public static final DeferredBlock<GhostBoatRotatedPillarBlock> GHOST_BOAT_WOOD = BLOCKS.register(Keys.GHOST_BOAT_WOOD.identifier().getPath(), () -> new GhostBoatRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GHOST_BOAT_LOG.get()).setId(Keys.GHOST_BOAT_WOOD).noOcclusion()));
	public static final DeferredBlock<SlabBlock> GHOST_BOAT_SLAB = BLOCKS.register(Keys.GHOST_BOAT_SLAB.identifier().getPath(), () -> new GhostBoatSlabBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_SLAB).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<StairBlock> GHOST_BOAT_STAIRS = BLOCKS.register(Keys.GHOST_BOAT_STAIRS.identifier().getPath(), () -> new GhostBoatStairBlock(GHOST_BOAT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_STAIRS).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<FenceBlock> GHOST_BOAT_FENCE = BLOCKS.register(Keys.GHOST_BOAT_FENCE.identifier().getPath(), () -> new GhostBoatFenceBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_FENCE).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<FenceGateBlock> GHOST_BOAT_GATE = BLOCKS.register(Keys.GHOST_BOAT_GATE.identifier().getPath(), () -> new GhostBoatFenceGateBlock(AerialHellWoodTypes.AERIAL_TREE, BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_GATE).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<DoorBlock> GHOST_BOAT_DOOR = BLOCKS.register(Keys.GHOST_BOAT_DOOR.identifier().getPath(), () -> new GhostBoatDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_DOOR).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<TrapDoorBlock> GHOST_BOAT_TRAPDOOR = BLOCKS.register(Keys.GHOST_BOAT_TRAPDOOR.identifier().getPath(), () -> new GhostBoatTrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_TRAPDOOR).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<ChestBlock> GHOST_BOAT_CHEST = BLOCKS.register(Keys.GHOST_BOAT_CHEST.identifier().getPath(), () -> new GhostBoatChestBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_CHEST).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<Block> GHOST_BOAT_WOOL = BLOCKS.register(Keys.GHOST_BOAT_WOOL.identifier().getPath(), () -> new GhostBoatBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_WOOL).mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL).noOcclusion()));
	public static final DeferredBlock<Block> GHOST_STELLAR_COBBLESTONE = BLOCKS.register(Keys.GHOST_STELLAR_COBBLESTONE.identifier().getPath(), () -> new GhostBoatBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_STELLAR_COBBLESTONE).strength(2.5F, 2.5F).requiresCorrectToolForDrops().sound(SoundType.STONE).noOcclusion()));
	public static final DeferredBlock<Block> GHOST_RUBY_BLOCK = BLOCKS.register(Keys.GHOST_RUBY_BLOCK.identifier().getPath(), () -> new GhostBoatBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_RUBY_BLOCK).strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));
	public static final DeferredBlock<Block> GHOST_FLUORITE_BLOCK = BLOCKS.register(Keys.GHOST_FLUORITE_BLOCK.identifier().getPath(), () -> new GhostBoatBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_FLUORITE_BLOCK).strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));
	public static final DeferredBlock<Block> GHOST_AZURITE_BLOCK = BLOCKS.register(Keys.GHOST_AZURITE_BLOCK.identifier().getPath(), () -> new GhostBoatBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).setId(Keys.GHOST_AZURITE_BLOCK).noOcclusion()));
	public static final DeferredBlock<Block> GHOST_GOLD_BLOCK = BLOCKS.register(Keys.GHOST_GOLD_BLOCK.identifier().getPath(), () -> new GhostBoatBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).setId(Keys.GHOST_GOLD_BLOCK).noOcclusion()));
	public static final DeferredBlock<AerialHellBarrelBlock> GHOST_BOAT_BARREL = BLOCKS.register(Keys.GHOST_BOAT_BARREL.identifier().getPath(), () -> new GhostBoatBarrelBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_BARREL).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<CraftingTableBlock> GHOST_BOAT_CRAFTING_TABLE = BLOCKS.register(Keys.GHOST_BOAT_CRAFTING_TABLE.identifier().getPath(), () -> new GhostBoatCraftingTableBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_CRAFTING_TABLE).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final DeferredBlock<RotatedPillarBlock> GHOST_BOAT_VINE_ROPE_SPOOL = BLOCKS.register(Keys.GHOST_BOAT_VINE_ROPE_SPOOL.identifier().getPath(), () -> new GhostBoatVineRopeSpoolBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_VINE_ROPE_SPOOL).noOcclusion().mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<Block> GHOST_LANTERN = BLOCKS.register(Keys.GHOST_LANTERN.identifier().getPath(), () -> new GhostLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.GHOST_LANTERN)));

	//other condition condition blocks
	public static final DeferredBlock<Block> INTANGIBLE_TEMPORARY_BLOCK = BLOCKS.register(Keys.INTANGIBLE_TEMPORARY_BLOCK.identifier().getPath(), () -> new IntangibleTemporaryBlock(BlockBehaviour.Properties.of().setId(Keys.INTANGIBLE_TEMPORARY_BLOCK).strength(2.0F, 3600000.0F).noLootTable().pushReaction(PushReaction.IGNORE).sound(SoundType.GLASS).lightLevel((state) -> 7).noOcclusion()));

	//reactors
	public static final DeferredBlock<Block> WEAK_LIGHT_REACTOR = BLOCKS.register(Keys.WEAK_LIGHT_REACTOR.identifier().getPath(), () -> new ReactorBlock(BlockBehaviour.Properties.of().setId(Keys.WEAK_LIGHT_REACTOR).strength(5.0F, 100.0F).pushReaction(PushReaction.IGNORE).sound(SoundType.STONE).noOcclusion(), 32, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.BROKEN_WEAK_LIGHT_REACTOR.get()));
	public static final DeferredBlock<Block> HIGH_POWER_LIGHT_REACTOR = BLOCKS.register(Keys.HIGH_POWER_LIGHT_REACTOR.identifier().getPath(), () -> new ReactorBlock(BlockBehaviour.Properties.of().setId(Keys.HIGH_POWER_LIGHT_REACTOR).strength(5.0F, 100.0F).pushReaction(PushReaction.IGNORE).sound(SoundType.STONE).noOcclusion(), 58, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.BROKEN_HIGH_POWER_LIGHT_REACTOR.get()));
	public static final DeferredBlock<Block> WEAK_SHADOW_REACTOR = BLOCKS.register(Keys.WEAK_SHADOW_REACTOR.identifier().getPath(), () -> new ReactorBlock(BlockBehaviour.Properties.of().setId(Keys.WEAK_SHADOW_REACTOR).strength(5.0F, 100.0F).pushReaction(PushReaction.IGNORE).sound(SoundType.STONE).noOcclusion(), 26, BiomeShifter.ShiftType.CORRUPT, () -> AerialHellBlocks.BROKEN_WEAK_SHADOW_REACTOR.get()));
	public static final DeferredBlock<Block> HIGH_POWER_SHADOW_REACTOR = BLOCKS.register(Keys.HIGH_POWER_SHADOW_REACTOR.identifier().getPath(), () -> new ReactorBlock(BlockBehaviour.Properties.of().setId(Keys.HIGH_POWER_SHADOW_REACTOR).strength(5.0F, 100.0F).pushReaction(PushReaction.IGNORE).sound(SoundType.STONE).noOcclusion(), 60, BiomeShifter.ShiftType.CORRUPT, () -> AerialHellBlocks.BROKEN_HIGH_POWER_SHADOW_REACTOR.get()));

	public static final DeferredBlock<Block> BROKEN_WEAK_LIGHT_REACTOR = BLOCKS.register(Keys.BROKEN_WEAK_LIGHT_REACTOR.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(WEAK_LIGHT_REACTOR.get()).setId(Keys.BROKEN_WEAK_LIGHT_REACTOR)));
	public static final DeferredBlock<Block> BROKEN_HIGH_POWER_LIGHT_REACTOR = BLOCKS.register(Keys.BROKEN_HIGH_POWER_LIGHT_REACTOR.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(HIGH_POWER_LIGHT_REACTOR.get()).setId(Keys.BROKEN_HIGH_POWER_LIGHT_REACTOR)));
	public static final DeferredBlock<Block> BROKEN_WEAK_SHADOW_REACTOR = BLOCKS.register(Keys.BROKEN_WEAK_SHADOW_REACTOR.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(WEAK_SHADOW_REACTOR.get()).setId(Keys.BROKEN_WEAK_SHADOW_REACTOR)));
	public static final DeferredBlock<Block> BROKEN_HIGH_POWER_SHADOW_REACTOR = BLOCKS.register(Keys.BROKEN_HIGH_POWER_SHADOW_REACTOR.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(HIGH_POWER_SHADOW_REACTOR.get()).setId(Keys.BROKEN_HIGH_POWER_SHADOW_REACTOR)));

	//solid_ethers
	public static final DeferredBlock<Block> WHITE_SOLID_ETHER = BLOCKS.register(Keys.WHITE_SOLID_ETHER.identifier().getPath(), () -> new SolidEtherBlock(BlockBehaviour.Properties.of().setId(Keys.WHITE_SOLID_ETHER).strength(0.2F).sound(SoundType.WOOL).noOcclusion()));
	public static final DeferredBlock<Block> BLUE_SOLID_ETHER = BLOCKS.register(Keys.BLUE_SOLID_ETHER.identifier().getPath(), () -> new BlueSolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER.get()).setId(Keys.BLUE_SOLID_ETHER)));
	public static final DeferredBlock<Block> GOLDEN_SOLID_ETHER = BLOCKS.register(Keys.GOLDEN_SOLID_ETHER.identifier().getPath(), () -> new SolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER.get()).setId(Keys.GOLDEN_SOLID_ETHER)));
	public static final DeferredBlock<Block> GREEN_SOLID_ETHER = BLOCKS.register(Keys.GREEN_SOLID_ETHER.identifier().getPath(), () -> new GreenSolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER.get()).setId(Keys.GREEN_SOLID_ETHER)));
	public static final DeferredBlock<Block> PURPLE_SOLID_ETHER = BLOCKS.register(Keys.PURPLE_SOLID_ETHER.identifier().getPath(), () -> new PurpleSolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER.get()).setId(Keys.PURPLE_SOLID_ETHER)));

	//dungeons blocks
	public static final DeferredBlock<Block> MUD_BRICKS = BLOCKS.register(Keys.MUD_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.MUD_BRICKS).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> CRACKED_MUD_BRICKS = BLOCKS.register(Keys.CRACKED_MUD_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get()).setId(Keys.CRACKED_MUD_BRICKS)));
	public static final DeferredBlock<Block> MOSSY_MUD_BRICKS = BLOCKS.register(Keys.MOSSY_MUD_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get()).setId(Keys.MOSSY_MUD_BRICKS)));
	public static final DeferredBlock<Block> CHISELED_MUD_BRICKS = BLOCKS.register(Keys.CHISELED_MUD_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get()).setId(Keys.CHISELED_MUD_BRICKS)));
	public static final DeferredBlock<Block> LIGHT_MUD_BRICKS = BLOCKS.register(Keys.LIGHT_MUD_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get()).setId(Keys.LIGHT_MUD_BRICKS).lightLevel((state) -> 11)));
	public static final DeferredBlock<Block> CRACKED_LIGHT_MUD_BRICKS = BLOCKS.register(Keys.CRACKED_LIGHT_MUD_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get()).setId(Keys.CRACKED_LIGHT_MUD_BRICKS)));
	public static final DeferredBlock<Block> LUNATIC_STONE = BLOCKS.register(Keys.LUNATIC_STONE.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.LUNATIC_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DARK_LUNATIC_STONE = BLOCKS.register(Keys.DARK_LUNATIC_STONE.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.DARK_LUNATIC_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> ROOF_LUNATIC_STONE = BLOCKS.register(Keys.ROOF_LUNATIC_STONE.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.ROOF_LUNATIC_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> CRACKED_LUNATIC_STONE = BLOCKS.register(Keys.CRACKED_LUNATIC_STONE.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.CRACKED_LUNATIC_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> CHISELED_LUNATIC_STONE = BLOCKS.register(Keys.CHISELED_LUNATIC_STONE.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.CHISELED_LUNATIC_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> LIGHT_LUNATIC_STONE = BLOCKS.register(Keys.LIGHT_LUNATIC_STONE.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).setId(Keys.LIGHT_LUNATIC_STONE).lightLevel((state) -> 11)));
	public static final DeferredBlock<Block> ROOF_LIGHT_LUNATIC_STONE = BLOCKS.register(Keys.ROOF_LIGHT_LUNATIC_STONE.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).setId(Keys.ROOF_LIGHT_LUNATIC_STONE).lightLevel((state) -> 11)));
	public static final DeferredBlock<Block> CRACKED_LIGHT_LUNATIC_STONE = BLOCKS.register(Keys.CRACKED_LIGHT_LUNATIC_STONE.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).setId(Keys.CRACKED_LIGHT_LUNATIC_STONE)));
	public static final DeferredBlock<Block> SHADOW_CATACOMBS_BRICKS = BLOCKS.register(Keys.SHADOW_CATACOMBS_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.SHADOW_CATACOMBS_BRICKS).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> CRACKED_SHADOW_CATACOMBS_BRICKS = BLOCKS.register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).setId(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS)));
	public static final DeferredBlock<Block> MOSSY_SHADOW_CATACOMBS_BRICKS = BLOCKS.register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).setId(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS)));
	public static final DeferredBlock<Block> CHISELED_SHADOW_CATACOMBS_BRICKS = BLOCKS.register(Keys.CHISELED_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).setId(Keys.CHISELED_SHADOW_CATACOMBS_BRICKS)));
	public static final DeferredBlock<Block> BONE_SHADOW_CATACOMBS_BRICKS = BLOCKS.register(Keys.BONE_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).setId(Keys.BONE_SHADOW_CATACOMBS_BRICKS).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> SKULL_SHADOW_CATACOMBS_BRICKS = BLOCKS.register(Keys.SKULL_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).setId(Keys.SKULL_SHADOW_CATACOMBS_BRICKS).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> LIGHT_SHADOW_CATACOMBS_BRICKS = BLOCKS.register(Keys.LIGHT_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).setId(Keys.LIGHT_SHADOW_CATACOMBS_BRICKS).lightLevel((state) -> 11)));
	public static final DeferredBlock<Block> CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS = BLOCKS.register(Keys.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).setId(Keys.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS)));
	public static final DeferredBlock<Block> GOLDEN_NETHER_BRICKS  = BLOCKS.register(Keys.GOLDEN_NETHER_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.GOLDEN_NETHER_BRICKS).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> CRACKED_GOLDEN_NETHER_BRICKS  = BLOCKS.register(Keys.CRACKED_GOLDEN_NETHER_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.CRACKED_GOLDEN_NETHER_BRICKS).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> CHISELED_GOLDEN_NETHER_BRICKS  = BLOCKS.register(Keys.CHISELED_GOLDEN_NETHER_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.CHISELED_GOLDEN_NETHER_BRICKS).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> LIGHT_GOLDEN_NETHER_BRICKS = BLOCKS.register(Keys.LIGHT_GOLDEN_NETHER_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get()).setId(Keys.LIGHT_GOLDEN_NETHER_BRICKS).lightLevel((state) -> 11)));
	public static final DeferredBlock<Block> CRACKED_LIGHT_GOLDEN_NETHER_BRICKS = BLOCKS.register(Keys.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS.identifier().getPath(), () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get()).setId(Keys.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS)));
	public static final DeferredBlock<RotatedPillarBlock> LUNATIC_PILLAR = BLOCKS.register(Keys.LUNATIC_PILLAR.identifier().getPath(), () -> new CoreProtectedRotatedPillarBlock(BlockBehaviour.Properties.of().setId(Keys.LUNATIC_PILLAR).mapColor(MapColor.QUARTZ).strength(2.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	public static final DeferredBlock<RotatedPillarBlock> LUNATIC_PILLAR_TOP = BLOCKS.register(Keys.LUNATIC_PILLAR_TOP.identifier().getPath(), () -> new CoreProtectedRotatedPillarBlock(BlockBehaviour.Properties.of().setId(Keys.LUNATIC_PILLAR_TOP).mapColor(MapColor.QUARTZ).strength(2.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> VOLUCITE_STONE = BLOCKS.register(Keys.VOLUCITE_STONE.identifier().getPath(), () -> new VoluciteStoneBlock(Block.Properties.of().setId(Keys.VOLUCITE_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE).lightLevel(getLightValueLit(AerialHellStateProperties.SELF_LUMINESCENT, 8))));
	public static final DeferredBlock<Block> CRACKED_VOLUCITE_STONE = BLOCKS.register(Keys.CRACKED_VOLUCITE_STONE.identifier().getPath(), () -> new CoreProtectedBlock(Block.Properties.of().setId(Keys.CRACKED_VOLUCITE_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<Block> CHISELED_VOLUCITE_STONE = BLOCKS.register(Keys.CHISELED_VOLUCITE_STONE.identifier().getPath(), () -> new CoreProtectedBlock(Block.Properties.of().setId(Keys.CHISELED_VOLUCITE_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<Block> LIGHT_VOLUCITE_STONE = BLOCKS.register(Keys.LIGHT_VOLUCITE_STONE.identifier().getPath(), () -> new CoreProtectedBlock(Block.Properties.of().setId(Keys.LIGHT_VOLUCITE_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE).lightLevel((state) -> 13)));
	public static final DeferredBlock<Block> CRACKED_LIGHT_VOLUCITE_STONE = BLOCKS.register(Keys.CRACKED_LIGHT_VOLUCITE_STONE.identifier().getPath(), () -> new CoreProtectedBlock(Block.Properties.of().setId(Keys.CRACKED_LIGHT_VOLUCITE_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE)));

	//dungeon cores
	public static final DeferredBlock<Block> MUD_DUNGEON_CORE = BLOCKS.register(Keys.MUD_DUNGEON_CORE.identifier().getPath(), () -> new DungeonCoreBlock(BlockBehaviour.Properties.of().setId(Keys.MUD_DUNGEON_CORE).strength(30.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 181));
	public static final DeferredBlock<Block> LUNATIC_DUNGEON_CORE = BLOCKS.register(Keys.LUNATIC_DUNGEON_CORE.identifier().getPath(), () -> new DungeonCoreBlock(BlockBehaviour.Properties.of().setId(Keys.LUNATIC_DUNGEON_CORE).strength(40.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 181));
	public static final DeferredBlock<Block> SHADOW_CATACOMBS_DUNGEON_CORE = BLOCKS.register(Keys.SHADOW_CATACOMBS_DUNGEON_CORE.identifier().getPath(), () -> new DungeonCoreBlock(BlockBehaviour.Properties.of().setId(Keys.SHADOW_CATACOMBS_DUNGEON_CORE).strength(30.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 181));
	public static final DeferredBlock<Block> GOLDEN_NETHER_DUNGEON_CORE = BLOCKS.register(Keys.GOLDEN_NETHER_DUNGEON_CORE.identifier().getPath(), () -> new DungeonCoreBlock(BlockBehaviour.Properties.of().setId(Keys.GOLDEN_NETHER_DUNGEON_CORE).strength(50.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 101));
	public static final DeferredBlock<Block> VOLUCITE_DUNGEON_CORE = BLOCKS.register(Keys.VOLUCITE_DUNGEON_CORE.identifier().getPath(), () -> new DungeonCoreBlock(BlockBehaviour.Properties.of().setId(Keys.VOLUCITE_DUNGEON_CORE).strength(50.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 101));

	//dungeons slabs, stairs & walls
	public static final DeferredBlock<SlabBlock> MUD_BRICKS_SLAB = BLOCKS.register(Keys.MUD_BRICKS_SLAB.identifier().getPath(), () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get()).setId(Keys.MUD_BRICKS_SLAB)));
	public static final DeferredBlock<StairBlock> MUD_BRICKS_STAIRS = BLOCKS.register(Keys.MUD_BRICKS_STAIRS.identifier().getPath(), () -> new CoreProtectedStairsBlock(MUD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get()).setId(Keys.MUD_BRICKS_STAIRS)));
	public static final DeferredBlock<WallBlock> MUD_BRICKS_WALL = BLOCKS.register(Keys.MUD_BRICKS_WALL.identifier().getPath(), () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get()).setId(Keys.MUD_BRICKS_WALL)));
	public static final DeferredBlock<SlabBlock> CRACKED_MUD_BRICKS_SLAB = BLOCKS.register(Keys.CRACKED_MUD_BRICKS_SLAB.identifier().getPath(), () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_MUD_BRICKS.get()).setId(Keys.CRACKED_MUD_BRICKS_SLAB)));
	public static final DeferredBlock<StairBlock> CRACKED_MUD_BRICKS_STAIRS = BLOCKS.register(Keys.CRACKED_MUD_BRICKS_STAIRS.identifier().getPath(), () -> new CoreProtectedStairsBlock(CRACKED_MUD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get()).setId(Keys.CRACKED_MUD_BRICKS_STAIRS)));
	public static final DeferredBlock<WallBlock> CRACKED_MUD_BRICKS_WALL = BLOCKS.register(Keys.CRACKED_MUD_BRICKS_WALL.identifier().getPath(), () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_MUD_BRICKS.get()).setId(Keys.CRACKED_MUD_BRICKS_WALL)));
	public static final DeferredBlock<SlabBlock> MOSSY_MUD_BRICKS_SLAB = BLOCKS.register(Keys.MOSSY_MUD_BRICKS_SLAB.identifier().getPath(), () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS.get()).setId(Keys.MOSSY_MUD_BRICKS_SLAB)));
	public static final DeferredBlock<StairBlock> MOSSY_MUD_BRICKS_STAIRS = BLOCKS.register(Keys.MOSSY_MUD_BRICKS_STAIRS.identifier().getPath(), () -> new CoreProtectedStairsBlock(MOSSY_MUD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS.get()).setId(Keys.MOSSY_MUD_BRICKS_STAIRS)));
	public static final DeferredBlock<WallBlock> MOSSY_MUD_BRICKS_WALL = BLOCKS.register(Keys.MOSSY_MUD_BRICKS_WALL.identifier().getPath(), () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS.get()).setId(Keys.MOSSY_MUD_BRICKS_WALL)));
	public static final DeferredBlock<SlabBlock> VOLUCITE_STONE_SLAB = BLOCKS.register(Keys.VOLUCITE_STONE_SLAB.identifier().getPath(), () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE.get()).setId(Keys.VOLUCITE_STONE_SLAB)));
	public static final DeferredBlock<StairBlock> VOLUCITE_STONE_STAIRS = BLOCKS.register(Keys.VOLUCITE_STONE_STAIRS.identifier().getPath(), () -> new CoreProtectedStairsBlock(VOLUCITE_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE.get()).setId(Keys.VOLUCITE_STONE_STAIRS)));
	public static final DeferredBlock<WallBlock> VOLUCITE_STONE_WALL = BLOCKS.register(Keys.VOLUCITE_STONE_WALL.identifier().getPath(), () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE.get()).setId(Keys.VOLUCITE_STONE_WALL)));
	public static final DeferredBlock<SlabBlock> CRACKED_VOLUCITE_STONE_SLAB = BLOCKS.register(Keys.CRACKED_VOLUCITE_STONE_SLAB.identifier().getPath(), () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE.get()).setId(Keys.CRACKED_VOLUCITE_STONE_SLAB)));
	public static final DeferredBlock<StairBlock> CRACKED_VOLUCITE_STONE_STAIRS = BLOCKS.register(Keys.CRACKED_VOLUCITE_STONE_STAIRS.identifier().getPath(), () -> new CoreProtectedStairsBlock(CRACKED_VOLUCITE_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE.get()).setId(Keys.CRACKED_VOLUCITE_STONE_STAIRS)));
	public static final DeferredBlock<WallBlock> CRACKED_VOLUCITE_STONE_WALL = BLOCKS.register(Keys.CRACKED_VOLUCITE_STONE_WALL.identifier().getPath(), () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE.get()).setId(Keys.CRACKED_VOLUCITE_STONE_WALL)));
	public static final DeferredBlock<SlabBlock> LUNATIC_STONE_SLAB = BLOCKS.register(Keys.LUNATIC_STONE_SLAB.identifier().getPath(), () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).setId(Keys.LUNATIC_STONE_SLAB)));
	public static final DeferredBlock<StairBlock> LUNATIC_STONE_STAIRS = BLOCKS.register(Keys.LUNATIC_STONE_STAIRS.identifier().getPath(), () -> new CoreProtectedStairsBlock(LUNATIC_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).setId(Keys.LUNATIC_STONE_STAIRS)));
	public static final DeferredBlock<WallBlock> LUNATIC_STONE_WALL = BLOCKS.register(Keys.LUNATIC_STONE_WALL.identifier().getPath(), () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).setId(Keys.LUNATIC_STONE_WALL)));
	public static final DeferredBlock<SlabBlock> DARK_LUNATIC_STONE_SLAB = BLOCKS.register(Keys.DARK_LUNATIC_STONE_SLAB.identifier().getPath(), () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(DARK_LUNATIC_STONE.get()).setId(Keys.DARK_LUNATIC_STONE_SLAB)));
	public static final DeferredBlock<StairBlock> DARK_LUNATIC_STONE_STAIRS = BLOCKS.register(Keys.DARK_LUNATIC_STONE_STAIRS.identifier().getPath(), () -> new CoreProtectedStairsBlock(DARK_LUNATIC_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(DARK_LUNATIC_STONE.get()).setId(Keys.DARK_LUNATIC_STONE_STAIRS)));
	public static final DeferredBlock<WallBlock> DARK_LUNATIC_STONE_WALL = BLOCKS.register(Keys.DARK_LUNATIC_STONE_WALL.identifier().getPath(), () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(DARK_LUNATIC_STONE.get()).setId(Keys.DARK_LUNATIC_STONE_WALL)));
	public static final DeferredBlock<SlabBlock> CRACKED_LUNATIC_STONE_SLAB = BLOCKS.register(Keys.CRACKED_LUNATIC_STONE_SLAB.identifier().getPath(), () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_LUNATIC_STONE.get()).setId(Keys.CRACKED_LUNATIC_STONE_SLAB)));
	public static final DeferredBlock<StairBlock> CRACKED_LUNATIC_STONE_STAIRS = BLOCKS.register(Keys.CRACKED_LUNATIC_STONE_STAIRS.identifier().getPath(), () -> new CoreProtectedStairsBlock(CRACKED_LUNATIC_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CRACKED_LUNATIC_STONE.get()).setId(Keys.CRACKED_LUNATIC_STONE_STAIRS)));
	public static final DeferredBlock<WallBlock> CRACKED_LUNATIC_STONE_WALL = BLOCKS.register(Keys.CRACKED_LUNATIC_STONE_WALL.identifier().getPath(), () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_LUNATIC_STONE.get()).setId(Keys.CRACKED_LUNATIC_STONE_WALL)));
	public static final DeferredBlock<SlabBlock> SHADOW_CATACOMBS_BRICKS_SLAB = BLOCKS.register(Keys.SHADOW_CATACOMBS_BRICKS_SLAB.identifier().getPath(), () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).setId(Keys.SHADOW_CATACOMBS_BRICKS_SLAB)));
	public static final DeferredBlock<StairBlock> SHADOW_CATACOMBS_BRICKS_STAIRS = BLOCKS.register(Keys.SHADOW_CATACOMBS_BRICKS_STAIRS.identifier().getPath(), () -> new CoreProtectedStairsBlock(SHADOW_CATACOMBS_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).setId(Keys.SHADOW_CATACOMBS_BRICKS_STAIRS)));
	public static final DeferredBlock<WallBlock> SHADOW_CATACOMBS_BRICKS_WALL = BLOCKS.register(Keys.SHADOW_CATACOMBS_BRICKS_WALL.identifier().getPath(), () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).setId(Keys.SHADOW_CATACOMBS_BRICKS_WALL)));
	public static final DeferredBlock<SlabBlock> CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB = BLOCKS.register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB.identifier().getPath(), () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_SHADOW_CATACOMBS_BRICKS.get()).setId(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB)));
	public static final DeferredBlock<StairBlock> CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS = BLOCKS.register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS.identifier().getPath(), () -> new CoreProtectedStairsBlock(CRACKED_SHADOW_CATACOMBS_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CRACKED_SHADOW_CATACOMBS_BRICKS.get()).setId(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS)));
	public static final DeferredBlock<WallBlock> CRACKED_SHADOW_CATACOMBS_BRICKS_WALL = BLOCKS.register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL.identifier().getPath(), () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_SHADOW_CATACOMBS_BRICKS.get()).setId(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL)));
	public static final DeferredBlock<SlabBlock> MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB = BLOCKS.register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB.identifier().getPath(), () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_SHADOW_CATACOMBS_BRICKS.get()).setId(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB)));
	public static final DeferredBlock<StairBlock> MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS = BLOCKS.register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS.identifier().getPath(), () -> new CoreProtectedStairsBlock(MOSSY_SHADOW_CATACOMBS_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_SHADOW_CATACOMBS_BRICKS.get()).setId(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS)));
	public static final DeferredBlock<WallBlock> MOSSY_SHADOW_CATACOMBS_BRICKS_WALL = BLOCKS.register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL.identifier().getPath(), () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_SHADOW_CATACOMBS_BRICKS.get()).setId(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL)));
	public static final DeferredBlock<IronBarsBlock> SHADOW_BARS = BLOCKS.register(Keys.SHADOW_BARS.identifier().getPath(), () -> new ShadowBarsBlock(METAL_NOTSOLID_MATERIAL.setId(Keys.SHADOW_BARS)));
	public static final DeferredBlock<SlabBlock> GOLDEN_NETHER_BRICKS_SLAB = BLOCKS.register(Keys.GOLDEN_NETHER_BRICKS_SLAB.identifier().getPath(), () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get()).setId(Keys.GOLDEN_NETHER_BRICKS_SLAB)));
	public static final DeferredBlock<StairBlock> GOLDEN_NETHER_BRICKS_STAIRS = BLOCKS.register(Keys.GOLDEN_NETHER_BRICKS_STAIRS.identifier().getPath(), () -> new CoreProtectedStairsBlock(GOLDEN_NETHER_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get()).setId(Keys.GOLDEN_NETHER_BRICKS_STAIRS)));
	public static final DeferredBlock<WallBlock> GOLDEN_NETHER_BRICKS_WALL = BLOCKS.register(Keys.GOLDEN_NETHER_BRICKS_WALL.identifier().getPath(), () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get()).setId(Keys.GOLDEN_NETHER_BRICKS_WALL)));
	public static final DeferredBlock<SlabBlock> CRACKED_GOLDEN_NETHER_BRICKS_SLAB = BLOCKS.register(Keys.CRACKED_GOLDEN_NETHER_BRICKS_SLAB.identifier().getPath(), () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_GOLDEN_NETHER_BRICKS.get()).setId(Keys.CRACKED_GOLDEN_NETHER_BRICKS_SLAB)));
	public static final DeferredBlock<StairBlock> CRACKED_GOLDEN_NETHER_BRICKS_STAIRS = BLOCKS.register(Keys.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS.identifier().getPath(), () -> new CoreProtectedStairsBlock(CRACKED_GOLDEN_NETHER_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CRACKED_GOLDEN_NETHER_BRICKS.get()).setId(Keys.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS)));
	public static final DeferredBlock<WallBlock> CRACKED_GOLDEN_NETHER_BRICKS_WALL = BLOCKS.register(Keys.CRACKED_GOLDEN_NETHER_BRICKS_WALL.identifier().getPath(), () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_GOLDEN_NETHER_BRICKS.get()).setId(Keys.CRACKED_GOLDEN_NETHER_BRICKS_WALL)));

	//smoky quartz
	public static final DeferredBlock<Block> SMOKY_QUARTZ_BLOCK = BLOCKS.register(Keys.SMOKY_QUARTZ_BLOCK.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).setId(Keys.SMOKY_QUARTZ_BLOCK)));
	public static final DeferredBlock<Block> SMOOTH_SMOKY_QUARTZ = BLOCKS.register(Keys.SMOOTH_SMOKY_QUARTZ.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get()).setId(Keys.SMOOTH_SMOKY_QUARTZ)));
	public static final DeferredBlock<Block> CHISELED_SMOKY_QUARTZ_BLOCK = BLOCKS.register(Keys.CHISELED_SMOKY_QUARTZ_BLOCK.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get()).setId(Keys.CHISELED_SMOKY_QUARTZ_BLOCK)));
	public static final DeferredBlock<Block> SMOKY_QUARTZ_BRICKS = BLOCKS.register(Keys.SMOKY_QUARTZ_BRICKS.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get()).setId(Keys.SMOKY_QUARTZ_BRICKS)));
	public static final DeferredBlock<RotatedPillarBlock> SMOKY_QUARTZ_PILLAR = BLOCKS.register(Keys.SMOKY_QUARTZ_PILLAR.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get()).setId(Keys.SMOKY_QUARTZ_PILLAR)));
	public static final DeferredBlock<SlabBlock> SMOKY_QUARTZ_SLAB = BLOCKS.register(Keys.SMOKY_QUARTZ_SLAB.identifier().getPath(), () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get()).setId(Keys.SMOKY_QUARTZ_SLAB)));
	public static final DeferredBlock<SlabBlock> SMOOTH_SMOKY_QUARTZ_SLAB = BLOCKS.register(Keys.SMOOTH_SMOKY_QUARTZ_SLAB.identifier().getPath(), () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get()).setId(Keys.SMOOTH_SMOKY_QUARTZ_SLAB)));
	public static final DeferredBlock<StairBlock> SMOKY_QUARTZ_STAIRS = BLOCKS.register(Keys.SMOKY_QUARTZ_STAIRS.identifier().getPath(), () -> new StairBlock(SMOKY_QUARTZ_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get()).setId(Keys.SMOKY_QUARTZ_STAIRS)));
	public static final DeferredBlock<StairBlock> SMOOTH_SMOKY_QUARTZ_STAIRS = BLOCKS.register(Keys.SMOOTH_SMOKY_QUARTZ_STAIRS.identifier().getPath(), () -> new StairBlock(SMOOTH_SMOKY_QUARTZ.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get()).setId(Keys.SMOOTH_SMOKY_QUARTZ_STAIRS)));

	//dungeon trapped blocks
	public static final DeferredBlock<Block> TRAPPED_MUD_BRICKS = BLOCKS.register(Keys.TRAPPED_MUD_BRICKS.identifier().getPath(), () -> new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).setId(Keys.TRAPPED_MUD_BRICKS)));
	public static final DeferredBlock<Block> TRAPPED_LIGHT_MUD_BRICKS = BLOCKS.register(Keys.TRAPPED_LIGHT_MUD_BRICKS.identifier().getPath(), () -> new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).setId(Keys.TRAPPED_LIGHT_MUD_BRICKS).lightLevel((state) -> 11)));
	public static final DeferredBlock<Block> TRAPPED_LUNATIC_STONE = BLOCKS.register(Keys.TRAPPED_LUNATIC_STONE.identifier().getPath(), () -> new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).setId(Keys.TRAPPED_LUNATIC_STONE)));
	public static final DeferredBlock<Block> TRAPPED_LIGHT_LUNATIC_STONE = BLOCKS.register(Keys.TRAPPED_LIGHT_LUNATIC_STONE.identifier().getPath(), () -> new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).setId(Keys.TRAPPED_LIGHT_LUNATIC_STONE).lightLevel((state) -> 11)));
	public static final DeferredBlock<Block> TRAPPED_GOLDEN_NETHER_BRICKS = BLOCKS.register(Keys.TRAPPED_GOLDEN_NETHER_BRICKS.identifier().getPath(), () -> new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).setId(Keys.TRAPPED_GOLDEN_NETHER_BRICKS)));
	public static final DeferredBlock<Block> TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS = BLOCKS.register(Keys.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS.identifier().getPath(), () -> new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).setId(Keys.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS).lightLevel((state) -> 11)));

	//dungeon other blocks, loots
	public static final DeferredBlock<RotatedPillarBlock> MUD_BONE_BLOCK = BLOCKS.register(Keys.MUD_BONE_BLOCK.identifier().getPath(), () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().setId(Keys.MUD_BONE_BLOCK).mapColor(MapColor.DIRT).requiresCorrectToolForDrops().strength(2.5F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> MUD_BONE_PILE_BLOCK = BLOCKS.register(Keys.MUD_BONE_PILE_BLOCK.identifier().getPath(), () -> new BonePileBlock(BlockBehaviour.Properties.of().setId(Keys.MUD_BONE_PILE_BLOCK).mapColor(MapColor.DIRT).randomTicks().strength(2.5F).sound(SoundType.BONE_BLOCK)));
	public static final DeferredBlock<Block> THORNY_COBWEB = BLOCKS.register(Keys.THORNY_COBWEB.identifier().getPath(), () -> new ThornyWebBlock(BlockBehaviour.Properties.of().setId(Keys.THORNY_COBWEB).noCollision().requiresCorrectToolForDrops().strength(8.0F)));
	public static final DeferredBlock<Block> AERIAL_NETHERRACK = BLOCKS.register(Keys.AERIAL_NETHERRACK.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.of().setId(Keys.AERIAL_NETHERRACK).mapColor(MapColor.NETHER).requiresCorrectToolForDrops().strength(6.0F, 8.0F)));
	public static final DeferredBlock<SlabBlock> AERIAL_NETHERRACK_SLAB = BLOCKS.register(Keys.AERIAL_NETHERRACK_SLAB.identifier().getPath(), () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_NETHERRACK.get()).setId(Keys.AERIAL_NETHERRACK_SLAB)));
	public static final DeferredBlock<StairBlock> AERIAL_NETHERRACK_STAIRS = BLOCKS.register(Keys.AERIAL_NETHERRACK_STAIRS.identifier().getPath(), () -> new StairBlock(AERIAL_NETHERRACK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(AERIAL_NETHERRACK.get()).setId(Keys.AERIAL_NETHERRACK_STAIRS)));
	public static final DeferredBlock<WallBlock> AERIAL_NETHERRACK_WALL = BLOCKS.register(Keys.AERIAL_NETHERRACK_WALL.identifier().getPath(), () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_NETHERRACK.get()).setId(Keys.AERIAL_NETHERRACK_WALL)));

	//dungeon bookshelfs
	public static final DeferredBlock<Block> MUD_BOOKSHELF = BLOCKS.register(Keys.MUD_BOOKSHELF.identifier().getPath(), () -> new CoreProtectedBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get()).setId(Keys.MUD_BOOKSHELF)));
	public static final DeferredBlock<Block> LUNATIC_BOOKSHELF = BLOCKS.register(Keys.LUNATIC_BOOKSHELF.identifier().getPath(), () -> new CoreProtectedBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).setId(Keys.LUNATIC_BOOKSHELF)));
	public static final DeferredBlock<Block> GOLDEN_NETHER_BOOKSHELF = BLOCKS.register(Keys.GOLDEN_NETHER_BOOKSHELF.identifier().getPath(), () -> new CoreProtectedBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get()).setId(Keys.GOLDEN_NETHER_BOOKSHELF)));
	public static final DeferredBlock<Block> SHADOW_CATACOMBS_BOOKSHELF = BLOCKS.register(Keys.SHADOW_CATACOMBS_BOOKSHELF.identifier().getPath(), () -> new CoreProtectedBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).setId(Keys.SHADOW_CATACOMBS_BOOKSHELF)));
	public static final DeferredBlock<Block> VOLUCITE_BOOKSHELF = BLOCKS.register(Keys.VOLUCITE_BOOKSHELF.identifier().getPath(), () -> new CoreProtectedBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE.get()).setId(Keys.VOLUCITE_BOOKSHELF)));

	//glyph blocks
	public static final DeferredBlock<Block> MUD_GLYPH_BLOCK = BLOCKS.register(Keys.MUD_GLYPH_BLOCK.identifier().getPath(), () -> new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get()).setId(Keys.MUD_GLYPH_BLOCK).lightLevel((state) -> 9)));
	public static final DeferredBlock<Block> LUNATIC_GLYPH_BLOCK = BLOCKS.register(Keys.LUNATIC_GLYPH_BLOCK.identifier().getPath(), () -> new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).setId(Keys.LUNATIC_GLYPH_BLOCK).lightLevel((state) -> 9)));
	public static final DeferredBlock<Block> GOLDEN_NETHER_PRISON_GLYPH_BLOCK = BLOCKS.register(Keys.GOLDEN_NETHER_PRISON_GLYPH_BLOCK.identifier().getPath(), () -> new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get()).setId(Keys.GOLDEN_NETHER_PRISON_GLYPH_BLOCK).lightLevel((state) -> 9)));
	public static final DeferredBlock<Block> VOLUCITE_GLYPH_BLOCK = BLOCKS.register(Keys.VOLUCITE_GLYPH_BLOCK.identifier().getPath(), () -> new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE.get()).setId(Keys.VOLUCITE_GLYPH_BLOCK).lightLevel((state) -> 9)));
	public static final DeferredBlock<Block> SHADOW_CATACOMBS_GLYPH_BLOCK = BLOCKS.register(Keys.SHADOW_CATACOMBS_GLYPH_BLOCK.identifier().getPath(), () -> new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).setId(Keys.SHADOW_CATACOMBS_GLYPH_BLOCK).lightLevel((state) -> 9)));

	//trophies
	public static final DeferredBlock<Block> MUD_CYCLE_MAGE_TROPHY = BLOCKS.register(Keys.MUD_CYCLE_MAGE_TROPHY.identifier().getPath(), () -> new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).setId(Keys.MUD_CYCLE_MAGE_TROPHY).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> LUNAR_PRIEST_TROPHY = BLOCKS.register(Keys.LUNAR_PRIEST_TROPHY.identifier().getPath(), () -> new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).setId(Keys.LUNAR_PRIEST_TROPHY).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> LILITH_TROPHY = BLOCKS.register(Keys.LILITH_TROPHY.identifier().getPath(), () -> new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).setId(Keys.LILITH_TROPHY).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> CHAINED_GOD_TROPHY = BLOCKS.register(Keys.CHAINED_GOD_TROPHY.identifier().getPath(), () -> new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get()).setId(Keys.CHAINED_GOD_TROPHY).requiresCorrectToolForDrops()));

	//ores
	public static final DeferredBlock<Block> IRON_STELLAR_ORE = BLOCKS.register(Keys.IRON_STELLAR_ORE.identifier().getPath(),() -> new AerialHellOreBlock(0, 2, BlockBehaviour.Properties.of().setId(Keys.IRON_STELLAR_ORE).strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> GOLD_STELLAR_ORE = BLOCKS.register(Keys.GOLD_STELLAR_ORE.identifier().getPath(),() -> new AerialHellOreBlock(0, 2, BlockBehaviour.Properties.of().setId(Keys.GOLD_STELLAR_ORE).strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> DIAMOND_STELLAR_ORE = BLOCKS.register(Keys.DIAMOND_STELLAR_ORE.identifier().getPath(),() -> new AerialHellOreBlock(3, 5, BlockBehaviour.Properties.of().setId(Keys.DIAMOND_STELLAR_ORE).strength(5.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> FLUORITE_ORE = BLOCKS.register(Keys.FLUORITE_ORE.identifier().getPath(),() -> new BiomeShifterOreBlock(0, 2, BlockBehaviour.Properties.of().setId(Keys.FLUORITE_ORE).strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 2, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.SMOKY_QUARTZ_ORE.get()));
	public static final DeferredBlock<Block> MAGMATIC_GEL_ORE = BLOCKS.register(Keys.MAGMATIC_GEL_ORE.identifier().getPath(),() -> new MagmaticGelOreBlock(0, 2, BlockBehaviour.Properties.of().setId(Keys.MAGMATIC_GEL_ORE).strength(3.0F).sound(SoundType.STONE).lightLevel(s -> 4).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> RUBY_ORE = BLOCKS.register(Keys.RUBY_ORE.identifier().getPath(),() -> new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().setId(Keys.RUBY_ORE).strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> AZURITE_ORE = BLOCKS.register(Keys.AZURITE_ORE.identifier().getPath(),() -> new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().setId(Keys.AZURITE_ORE).strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> VOLUCITE_ORE = BLOCKS.register(Keys.VOLUCITE_ORE.identifier().getPath(),() -> new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().setId(Keys.VOLUCITE_ORE).strength(5.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> OBSIDIAN_ORE = BLOCKS.register(Keys.OBSIDIAN_ORE.identifier().getPath(),() -> new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().setId(Keys.OBSIDIAN_ORE).strength(5.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> SMOKY_QUARTZ_ORE = BLOCKS.register(Keys.SMOKY_QUARTZ_ORE.identifier().getPath(),() -> new AerialHellOreBlock(1, 3, BlockBehaviour.Properties.of().setId(Keys.SMOKY_QUARTZ_ORE).strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));

	public static final DeferredBlock<Block> RAW_RUBY_BLOCK = BLOCKS.register(Keys.RAW_RUBY_BLOCK.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).setId(Keys.RAW_RUBY_BLOCK).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> RAW_AZURITE_BLOCK = BLOCKS.register(Keys.RAW_AZURITE_BLOCK.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).setId(Keys.RAW_AZURITE_BLOCK).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> RAW_VOLUCITE_BLOCK = BLOCKS.register(Keys.RAW_VOLUCITE_BLOCK.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).setId(Keys.RAW_VOLUCITE_BLOCK).requiresCorrectToolForDrops()));

	public static final DeferredBlock<Block> FLUORITE_BLOCK = BLOCKS.register(Keys.FLUORITE_BLOCK.identifier().getPath(), () -> new BiomeShifterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).setId(Keys.FLUORITE_BLOCK).requiresCorrectToolForDrops(), 7, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.SMOKY_QUARTZ_BLOCK.get()));
	public static final DeferredBlock<Block> MAGMATIC_GEL_BLOCK = BLOCKS.register(Keys.MAGMATIC_GEL_BLOCK.identifier().getPath(), () -> new MagmaticGelBlock(BlockBehaviour.Properties.of().setId(Keys.MAGMATIC_GEL_BLOCK).strength(1.0F, 1600.0F).randomTicks().sound(SoundType.GLASS).noOcclusion().requiresCorrectToolForDrops().isViewBlocking((state, reader, pos) -> false)));
	public static final DeferredBlock<Block> RUBY_BLOCK = BLOCKS.register(Keys.RUBY_BLOCK.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).setId(Keys.RUBY_BLOCK).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> AZURITE_BLOCK = BLOCKS.register(Keys.AZURITE_BLOCK.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).setId(Keys.AZURITE_BLOCK).requiresCorrectToolForDrops()));
	public static final DeferredBlock<Block> VOLUCITE_BLOCK = BLOCKS.register(Keys.VOLUCITE_BLOCK.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).setId(Keys.VOLUCITE_BLOCK).requiresCorrectToolForDrops()));

	//legendary ores
	public static final DeferredBlock<Block> ARSONIST_BLOCK = BLOCKS.register(Keys.ARSONIST_BLOCK.identifier().getPath(), () -> new ArsonistBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).setId(Keys.ARSONIST_BLOCK).requiresCorrectToolForDrops().lightLevel((state) -> 9)));
	public static final DeferredBlock<Block> LUNATIC_CRYSTAL_BLOCK = BLOCKS.register(Keys.LUNATIC_CRYSTAL_BLOCK.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).setId(Keys.LUNATIC_CRYSTAL_BLOCK).sound(SoundType.GLASS).requiresCorrectToolForDrops().lightLevel((state) -> 9)));
	public static final DeferredBlock<Block> CURSED_CRYSAL_BLOCK = BLOCKS.register(Keys.CURSED_CRYSAL_BLOCK.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).setId(Keys.CURSED_CRYSAL_BLOCK).sound(SoundType.GLASS).requiresCorrectToolForDrops().lightLevel((state) -> 9)));

	//cactus
	public static final DeferredBlock<SkyCactusBlock> SKY_CACTUS = BLOCKS.register(Keys.SKY_CACTUS.identifier().getPath(), () -> new SkyCactusBlock(BlockBehaviour.Properties.of().setId(Keys.SKY_CACTUS).mapColor(MapColor.COLOR_CYAN).strength(0.4F).sound(SoundType.WOOL).randomTicks()));
	public static final DeferredBlock<Block> SKY_CACTUS_FIBER_PLANKS = BLOCKS.register(Keys.SKY_CACTUS_FIBER_PLANKS.identifier().getPath(), () -> new Block(SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_PLANKS)));
	public static final DeferredBlock<Block> SKY_CACTUS_FIBER_BOOKSHELF = BLOCKS.register(Keys.SKY_CACTUS_FIBER_BOOKSHELF.identifier().getPath(), () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_PLANKS.get()).setId(Keys.SKY_CACTUS_FIBER_BOOKSHELF)));
	public static final DeferredBlock<SkyCactusBlock> VIBRANT_SKY_CACTUS = BLOCKS.register(Keys.VIBRANT_SKY_CACTUS.identifier().getPath(), () -> new SkyCactusBlock(BlockBehaviour.Properties.of().setId(Keys.VIBRANT_SKY_CACTUS).mapColor(MapColor.COLOR_BLUE).strength(0.4F).sound(SoundType.WOOL).randomTicks().lightLevel(s -> 15).noOcclusion()));
	public static final DeferredBlock<Block> VIBRANT_SKY_CACTUS_FIBER_LANTERN = BLOCKS.register(Keys.VIBRANT_SKY_CACTUS_FIBER_LANTERN.identifier().getPath(), () -> new Block(BlockBehaviour.Properties.of().setId(Keys.VIBRANT_SKY_CACTUS_FIBER_LANTERN).mapColor(MapColor.QUARTZ).strength(0.5F).sound(SoundType.GLASS).noOcclusion().lightLevel(s -> 15)));

	//bushes
	public static final DeferredBlock<Block> AERIAL_BERRY_BUSH = BLOCKS.register(Keys.AERIAL_BERRY_BUSH.identifier().getPath(), () -> new AerialBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH).setId(Keys.AERIAL_BERRY_BUSH)));
	public static final DeferredBlock<Block> VIBRANT_AERIAL_BERRY_BUSH = BLOCKS.register(Keys.VIBRANT_AERIAL_BERRY_BUSH.identifier().getPath(), () -> new VibrantAerialBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH).setId(Keys.VIBRANT_AERIAL_BERRY_BUSH)));

	//crops
	public static final DeferredBlock<Block> STELLAR_WHEAT = BLOCKS.register(Keys.STELLAR_WHEAT.identifier().getPath(), () -> new StellarCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).setId(Keys.STELLAR_WHEAT)));
	public static final DeferredBlock<Block> BLUE_MEANIE_CROP = BLOCKS.register(Keys.BLUE_MEANIE_CROP.identifier().getPath(), () -> new StellarCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).setId(Keys.BLUE_MEANIE_CROP)));

	//vertical growing plants
	public static final DeferredBlock<VerticalGrowingPlantBlock> CLIMBING_VINE = BLOCKS.register(Keys.CLIMBING_VINE.identifier().getPath(), () -> new VerticalGrowingPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE).setId(Keys.CLIMBING_VINE), 4));
	public static final DeferredBlock<VerticalGrowingPlantBlock> STELLAR_SUGAR_CANE = BLOCKS.register(Keys.STELLAR_SUGAR_CANE.identifier().getPath(), () -> new VerticalGrowingPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE).setId(Keys.STELLAR_SUGAR_CANE), 5));

	//chorus like
	public static final DeferredBlock<ChorusPlantLikeBlock> FULL_MOON_PLANT = BLOCKS.register(Keys.FULL_MOON_PLANT.identifier().getPath(), () -> new ChorusPlantLikeBlock(BlockBehaviour.Properties.of().setId(Keys.FULL_MOON_PLANT).mapColor(MapColor.COLOR_PURPLE).forceSolidOff().strength(0.4F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY).lightLevel((state) -> 10)));
	public static final DeferredBlock<ChorusFlowerLikeBlock> FULL_MOON_FLOWER = BLOCKS.register(Keys.FULL_MOON_FLOWER.identifier().getPath(), () -> new ChorusFlowerLikeBlock(FULL_MOON_PLANT.get(), BlockBehaviour.Properties.of().setId(Keys.FULL_MOON_FLOWER).mapColor(MapColor.COLOR_PURPLE).forceSolidOff().randomTicks().strength(0.4F).sound(SoundType.WOOD).noOcclusion().isValidSpawn((state, blockGetter, pos, entitytype) -> false).pushReaction(PushReaction.DESTROY).isRedstoneConductor((state, blockGetter, pos) -> false).lightLevel((state) -> 15)));

	//vines
	public static final DeferredBlock<CaveVinesBlock> GLOWING_STICK_FRUIT_VINES = BLOCKS.register(Keys.GLOWING_STICK_FRUIT_VINES.identifier().getPath(), () -> new AerialHellCaveVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAVE_VINES).setId(Keys.GLOWING_STICK_FRUIT_VINES)));
	public static final DeferredBlock<CaveVinesPlantBlock> GLOWING_STICK_FRUIT_VINES_PLANT = BLOCKS.register(Keys.GLOWING_STICK_FRUIT_VINES_PLANT.identifier().getPath(), () -> new AerialHellCaveVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAVE_VINES_PLANT).setId(Keys.GLOWING_STICK_FRUIT_VINES_PLANT)));
	public static final DeferredBlock<CaveVinesBlock> BLOSSOMING_VINES = BLOCKS.register(Keys.BLOSSOMING_VINES.identifier().getPath(), () -> new AerialHellCaveVinesBlock(BlockBehaviour.Properties.of().setId(Keys.BLOSSOMING_VINES).randomTicks().noCollision().instabreak().sound(SoundType.CAVE_VINES)));
	public static final DeferredBlock<CaveVinesPlantBlock> BLOSSOMING_VINES_PLANT = BLOCKS.register(Keys.BLOSSOMING_VINES_PLANT.identifier().getPath(), () -> new AerialHellCaveVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(BLOSSOMING_VINES.get()).setId(Keys.BLOSSOMING_VINES_PLANT)));
	public static final DeferredBlock<AerialHellTwistingVinesBlock> LAZULI_ROOTS = BLOCKS.register(Keys.LAZULI_ROOTS.identifier().getPath(), () -> new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES).setId(Keys.LAZULI_ROOTS)));
	public static final DeferredBlock<AerialHellTwistingVinesPlantBlock> LAZULI_ROOTS_PLANT = BLOCKS.register(Keys.LAZULI_ROOTS_PLANT.identifier().getPath(), () -> new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT).setId(Keys.LAZULI_ROOTS_PLANT)));
	public static final DeferredBlock<AerialHellTwistingVinesBlock> STELLAR_ROOTS = BLOCKS.register(Keys.STELLAR_ROOTS.identifier().getPath(), () -> new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES).setId(Keys.STELLAR_ROOTS)));
	public static final DeferredBlock<AerialHellTwistingVinesPlantBlock> STELLAR_ROOTS_PLANT = BLOCKS.register(Keys.STELLAR_ROOTS_PLANT.identifier().getPath(), () -> new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT).setId(Keys.STELLAR_ROOTS_PLANT)));
	public static final DeferredBlock<AerialHellTwistingVinesBlock> DEAD_ROOTS = BLOCKS.register(Keys.DEAD_ROOTS.identifier().getPath(), () -> new DeadRootsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES).setId(Keys.DEAD_ROOTS)));
	public static final DeferredBlock<AerialHellTwistingVinesPlantBlock> DEAD_ROOTS_PLANT = BLOCKS.register(Keys.DEAD_ROOTS_PLANT.identifier().getPath(), () -> new DeadRootsPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT).setId(Keys.DEAD_ROOTS_PLANT)));
	public static final DeferredBlock<AerialHellTwistingVinesBlock> GLOWING_ROOTS = BLOCKS.register(Keys.GLOWING_ROOTS.identifier().getPath(), () -> new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES).setId(Keys.GLOWING_ROOTS).lightLevel((state) -> 9)));
	public static final DeferredBlock<AerialHellTwistingVinesPlantBlock> GLOWING_ROOTS_PLANT = BLOCKS.register(Keys.GLOWING_ROOTS_PLANT.identifier().getPath(), () -> new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT).setId(Keys.GLOWING_ROOTS_PLANT).lightLevel((state) -> 14)));
	public static final DeferredBlock<AerialHellTwistingVinesBlock> SHADOW_GLOWING_ROOTS = BLOCKS.register(Keys.SHADOW_GLOWING_ROOTS.identifier().getPath(), () -> new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES).setId(Keys.SHADOW_GLOWING_ROOTS).lightLevel((state) -> 8)));
	public static final DeferredBlock<AerialHellTwistingVinesPlantBlock> SHADOW_GLOWING_ROOTS_PLANT = BLOCKS.register(Keys.SHADOW_GLOWING_ROOTS_PLANT.identifier().getPath(), () -> new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT).setId(Keys.SHADOW_GLOWING_ROOTS_PLANT).lightLevel((state) -> 13)));

	//grass
	public static final DeferredBlock<Block> STELLAR_GRASS = BLOCKS.register(Keys.STELLAR_GRASS.identifier().getPath(), () -> new ShiftableRenderTallGrassBlock(BlockBehaviour.Properties.of().setId(Keys.STELLAR_GRASS).mapColor(MapColor.PLANT).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> STELLAR_GRASS_BALL = BLOCKS.register(Keys.STELLAR_GRASS_BALL.identifier().getPath(), () -> new ShiftableRenderTallGrassBlock(BlockBehaviour.Properties.of().setId(Keys.STELLAR_GRASS_BALL).mapColor(MapColor.PLANT).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> STELLAR_FERN = BLOCKS.register(Keys.STELLAR_FERN.identifier().getPath(), () -> new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().setId(Keys.STELLAR_FERN).mapColor(MapColor.PLANT).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> STELLAR_TALL_GRASS = BLOCKS.register(Keys.STELLAR_TALL_GRASS.identifier().getPath(), () -> new DoublePlantBlock(BlockBehaviour.Properties.of().setId(Keys.STELLAR_TALL_GRASS).mapColor(MapColor.PLANT).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> STELLAR_TALL_FERN = BLOCKS.register(Keys.STELLAR_TALL_FERN.identifier().getPath(), () -> new DoublePlantBlock(BlockBehaviour.Properties.of().setId(Keys.STELLAR_TALL_FERN).mapColor(MapColor.PLANT).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<VerticalGrowingPlantBlock> STELLAR_VERY_TALL_GRASS = BLOCKS.register(Keys.STELLAR_VERY_TALL_GRASS.identifier().getPath(), () -> new VerticalGrowingPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE).setId(Keys.STELLAR_VERY_TALL_GRASS), 3));
	public static final DeferredBlock<Block> BLUISH_FERN = BLOCKS.register(Keys.BLUISH_FERN.identifier().getPath(), () -> new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().setId(Keys.BLUISH_FERN).mapColor(MapColor.PLANT).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> TALL_BLUISH_FERN = BLOCKS.register(Keys.TALL_BLUISH_FERN.identifier().getPath(), () -> new DoublePlantBlock(BlockBehaviour.Properties.of().setId(Keys.TALL_BLUISH_FERN).mapColor(MapColor.PLANT).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> POLYCHROME_FERN = BLOCKS.register(Keys.POLYCHROME_FERN.identifier().getPath(), () -> new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().setId(Keys.POLYCHROME_FERN).mapColor(MapColor.PLANT).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> TALL_POLYCHROME_FERN = BLOCKS.register(Keys.TALL_POLYCHROME_FERN.identifier().getPath(), () -> new DoublePlantBlock(BlockBehaviour.Properties.of().setId(Keys.TALL_POLYCHROME_FERN).mapColor(MapColor.PLANT).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> STELLAR_DEAD_BUSH = BLOCKS.register(Keys.STELLAR_DEAD_BUSH.identifier().getPath(), () -> new AerialHellDeadBushBlock(BlockBehaviour.Properties.of().setId(Keys.STELLAR_DEAD_BUSH).mapColor(MapColor.PLANT).replaceable().mapColor(MapColor.WOOD).noCollision().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> BRAMBLES = BLOCKS.register(Keys.BRAMBLES.identifier().getPath(), () -> new BramblesBlock(BlockBehaviour.Properties.of().setId(Keys.BRAMBLES).mapColor(MapColor.PLANT).noCollision().strength(0.5F).sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> SHADOW_BRAMBLES = BLOCKS.register(Keys.SHADOW_BRAMBLES.identifier().getPath(), () -> new BramblesBlock(BlockBehaviour.Properties.of().setId(Keys.SHADOW_BRAMBLES).mapColor(MapColor.PLANT).noCollision().strength(0.5F).sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> SHADOW_GRASS = BLOCKS.register(Keys.SHADOW_GRASS.identifier().getPath(), () -> new ShadowPlantBlock(BlockBehaviour.Properties.of().setId(Keys.SHADOW_GRASS).mapColor(MapColor.PLANT).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> SHADOW_GRASS_BALL = BLOCKS.register(Keys.SHADOW_GRASS_BALL.identifier().getPath(), () -> new ShadowPlantBlock(BlockBehaviour.Properties.of().setId(Keys.SHADOW_GRASS_BALL).mapColor(MapColor.PLANT).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> PURPLISH_STELLAR_GRASS = BLOCKS.register(Keys.PURPLISH_STELLAR_GRASS.identifier().getPath(), () -> new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().setId(Keys.PURPLISH_STELLAR_GRASS).mapColor(MapColor.PLANT).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> STELLAR_CLOVERS = BLOCKS.register(Keys.STELLAR_CLOVERS.identifier().getPath(), () -> new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().setId(Keys.STELLAR_CLOVERS).mapColor(MapColor.PLANT).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
	public static final DeferredBlock<Block> GLOWING_STELLAR_GRASS = BLOCKS.register(Keys.GLOWING_STELLAR_GRASS.identifier().getPath(), () -> new GlowingStellarTallGrass(BlockBehaviour.Properties.of().setId(Keys.GLOWING_STELLAR_GRASS).mapColor(MapColor.PLANT).replaceable().randomTicks().noCollision().lightLevel((state) -> {return state.getValue(BlockStateProperties.LIT) ? 10 : 0;}).instabreak().sound(SoundType.GRASS)));

	//flowers
	public static final DeferredBlock<Block> BLUE_FLOWER = BLOCKS.register(Keys.BLUE_FLOWER.identifier().getPath(), () -> new FlowerBlock(MobEffects.BLINDNESS, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION).setId(Keys.BLUE_FLOWER)));
	public static final DeferredBlock<Block> BLACK_ROSE = BLOCKS.register(Keys.BLACK_ROSE.identifier().getPath(), () -> new FlowerBlock(MobEffects.SLOW_FALLING, 12, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION).setId(Keys.BLACK_ROSE)));
	public static final DeferredBlock<Block> BELLFLOWER = BLOCKS.register(Keys.BELLFLOWER.identifier().getPath(), () -> new FlowerBlock(MobEffects.MINING_FATIGUE, 12, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION).setId(Keys.BELLFLOWER)));

	//potted things
	public static final DeferredBlock<FlowerPotBlock> POTTED_BLUE_FLOWER = BLOCKS.register(Keys.POTTED_BLUE_FLOWER.identifier().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLUE_FLOWER, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_BLUE_FLOWER)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_BLACK_ROSE = BLOCKS.register(Keys.POTTED_BLACK_ROSE.identifier().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLACK_ROSE, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_BLACK_ROSE)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_BELLFLOWER = BLOCKS.register(Keys.POTTED_BELLFLOWER.identifier().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BELLFLOWER, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_BELLFLOWER)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_STELLAR_FERN = BLOCKS.register(Keys.POTTED_STELLAR_FERN.identifier().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, STELLAR_FERN, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_STELLAR_FERN)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_STELLAR_DEAD_BUSH = BLOCKS.register(Keys.POTTED_STELLAR_DEAD_BUSH.identifier().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, STELLAR_DEAD_BUSH, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_STELLAR_DEAD_BUSH)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_SKY_CACTUS = BLOCKS.register(Keys.POTTED_SKY_CACTUS.identifier().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SKY_CACTUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_SKY_CACTUS)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_VIBRANT_SKY_CACTUS = BLOCKS.register(Keys.POTTED_VIBRANT_SKY_CACTUS.identifier().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, VIBRANT_SKY_CACTUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_VIBRANT_SKY_CACTUS)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_AERIAL_TREE_SAPLING = BLOCKS.register(Keys.POTTED_AERIAL_TREE_SAPLING.identifier().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, AERIAL_TREE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_AERIAL_TREE_SAPLING)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_GOLDEN_BEECH_SAPLING = BLOCKS.register(Keys.POTTED_GOLDEN_BEECH_SAPLING.identifier().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GOLDEN_BEECH_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_GOLDEN_BEECH_SAPLING)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_COPPER_PINE_SAPLING = BLOCKS.register(Keys.POTTED_COPPER_PINE_SAPLING.identifier().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, COPPER_PINE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_COPPER_PINE_SAPLING)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_LAPIS_ROBINIA_SAPLING = BLOCKS.register(Keys.POTTED_LAPIS_ROBINIA_SAPLING.identifier().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, LAPIS_ROBINIA_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_LAPIS_ROBINIA_SAPLING)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_SHADOW_PINE_SAPLING = BLOCKS.register(Keys.POTTED_SHADOW_PINE_SAPLING.identifier().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SHADOW_PINE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_SHADOW_PINE_SAPLING)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_PURPLE_SHADOW_PINE_SAPLING = BLOCKS.register(Keys.POTTED_PURPLE_SHADOW_PINE_SAPLING.identifier().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, PURPLE_SHADOW_PINE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_PURPLE_SHADOW_PINE_SAPLING)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_STELLAR_JUNGLE_TREE_SAPLING = BLOCKS.register(Keys.POTTED_STELLAR_JUNGLE_TREE_SAPLING.identifier().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, STELLAR_JUNGLE_TREE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_STELLAR_JUNGLE_TREE_SAPLING)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_CORTINARIUS_VIOLACEUS = BLOCKS.register(Keys.POTTED_CORTINARIUS_VIOLACEUS.identifier().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CORTINARIUS_VIOLACEUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_CORTINARIUS_VIOLACEUS)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_VERDIGRIS_AGARIC = BLOCKS.register(Keys.POTTED_VERDIGRIS_AGARIC.identifier().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, VERDIGRIS_AGARIC, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_VERDIGRIS_AGARIC)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_VINE_BLOSSOM = BLOCKS.register(Keys.POTTED_VINE_BLOSSOM.identifier().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLOSSOMING_VINES, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_VINE_BLOSSOM)));
	public static final DeferredBlock<FlowerPotBlock> POTTED_GLOWING_BOLETUS = BLOCKS.register(Keys.POTTED_GLOWING_BOLETUS.identifier().getPath(), () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GLOWING_BOLETUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_GLOWING_BOLETUS).lightLevel((state) -> 9)));

	//with gui
	public static final DeferredBlock<Block> OSCILLATOR = BLOCKS.register(Keys.OSCILLATOR.identifier().getPath(), () -> new OscillatorBlock(BlockBehaviour.Properties.of().setId(Keys.OSCILLATOR).strength(2.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<Block> FREEZER = BLOCKS.register(Keys.FREEZER.identifier().getPath(),() -> new FreezerBlock(BlockBehaviour.Properties.of().setId(Keys.FREEZER).strength(2.0F).sound(SoundType.STONE)));
	public static final DeferredBlock<Block> STELLAR_FURNACE = BLOCKS.register(Keys.STELLAR_FURNACE.identifier().getPath(), () -> new StellarFurnaceBlock(BlockBehaviour.Properties.of().setId(Keys.STELLAR_FURNACE).requiresCorrectToolForDrops().strength(3.5F).lightLevel(getLightValueLit(BlockStateProperties.LIT, 13))));
	public static final DeferredBlock<Block> GHOST_STELLAR_FURNACE = BLOCKS.register(Keys.GHOST_STELLAR_FURNACE.identifier().getPath(), () -> new GhostStellarFurnaceBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_STELLAR_FURNACE).requiresCorrectToolForDrops().noOcclusion().strength(3.5F).lightLevel(getLightValueLit(BlockStateProperties.LIT, 13))));

	private static ToIntFunction<BlockState> getLightValueLit(BooleanProperty property, int lightValue) {return (state) -> {return state.getValue(property) ? lightValue : 0;};}

	//chests
	public static final DeferredBlock<ChestBlock> AERIAL_TREE_CHEST = BLOCKS.register(Keys.AERIAL_TREE_CHEST.identifier().getPath(), () -> new AerialHellChestBlock(AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_CHEST)));
	public static final DeferredBlock<ChestBlock> GOLDEN_BEECH_CHEST = BLOCKS.register(Keys.GOLDEN_BEECH_CHEST.identifier().getPath(), () -> new AerialHellChestBlock(AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_CHEST)));
	public static final DeferredBlock<ChestBlock> COPPER_PINE_CHEST = BLOCKS.register(Keys.COPPER_PINE_CHEST.identifier().getPath(), () -> new AerialHellChestBlock(COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_CHEST)));
	public static final DeferredBlock<ChestBlock> LAPIS_ROBINIA_CHEST = BLOCKS.register(Keys.LAPIS_ROBINIA_CHEST.identifier().getPath(), () -> new AerialHellChestBlock(COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_CHEST)));
	public static final DeferredBlock<ChestBlock> SHADOW_PINE_CHEST = BLOCKS.register(Keys.SHADOW_PINE_CHEST.identifier().getPath(), () -> new AerialHellChestBlock(SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_CHEST)));
	public static final DeferredBlock<ChestBlock> STELLAR_JUNGLE_TREE_CHEST = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_CHEST.identifier().getPath(), () -> new AerialHellChestBlock(COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_CHEST)));
	public static final DeferredBlock<ChestBlock> SKY_CACTUS_FIBER_CHEST = BLOCKS.register(Keys.SKY_CACTUS_FIBER_CHEST.identifier().getPath(), () -> new AerialHellChestBlock(SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_CHEST)));
	public static final DeferredBlock<ChestBlock> GRAY_SHROOM_CHEST = BLOCKS.register(Keys.GRAY_SHROOM_CHEST.identifier().getPath(), () -> new AerialHellChestBlock(SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_CHEST)));
	public static final DeferredBlock<ChestBlock> MUD_CHEST = BLOCKS.register(Keys.MUD_CHEST.identifier().getPath(), () -> new CoreProtectedChestBlock(MUD_CHEST_MATERIAL.setId(Keys.MUD_CHEST)));
	public static final DeferredBlock<ChestBlock> LUNATIC_CHEST = BLOCKS.register(Keys.LUNATIC_CHEST.identifier().getPath(), () -> new CoreProtectedChestBlock(LUNATIC_CHEST_MATERIAL.setId(Keys.LUNATIC_CHEST)));
	public static final DeferredBlock<ChestBlock> VOLUCITE_CHEST = BLOCKS.register(Keys.VOLUCITE_CHEST.identifier().getPath(), () -> new CoreProtectedChestBlock(VOLUCITE_CHEST_MATERIAL.setId(Keys.VOLUCITE_CHEST)));
	public static final DeferredBlock<ChestBlock> SHADOW_CATACOMBS_CHEST = BLOCKS.register(Keys.SHADOW_CATACOMBS_CHEST.identifier().getPath(), () -> new CoreProtectedChestBlock(MUD_CHEST_MATERIAL.setId(Keys.SHADOW_CATACOMBS_CHEST)));
	public static final DeferredBlock<ChestBlock> GOLDEN_NETHER_CHEST = BLOCKS.register(Keys.GOLDEN_NETHER_CHEST.identifier().getPath(), () -> new CoreProtectedChestBlock(GOLDEN_NETHER_CHEST_MATERIAL.setId(Keys.GOLDEN_NETHER_CHEST)));

	//chest mimics
	public static final DeferredBlock<Block> AERIAL_TREE_CHEST_MIMIC = BLOCKS.register(Keys.AERIAL_TREE_CHEST_MIMIC.identifier().getPath(), () -> new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST).setId(Keys.AERIAL_TREE_CHEST_MIMIC)));
	public static final DeferredBlock<Block> GOLDEN_BEECH_CHEST_MIMIC = BLOCKS.register(Keys.GOLDEN_BEECH_CHEST_MIMIC.identifier().getPath(), () -> new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST).setId(Keys.GOLDEN_BEECH_CHEST_MIMIC)));
	public static final DeferredBlock<Block> COPPER_PINE_CHEST_MIMIC = BLOCKS.register(Keys.COPPER_PINE_CHEST_MIMIC.identifier().getPath(), () -> new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST).setId(Keys.COPPER_PINE_CHEST_MIMIC)));
	public static final DeferredBlock<Block> SKY_CACTUS_FIBER_CHEST_MIMIC = BLOCKS.register(Keys.SKY_CACTUS_FIBER_CHEST_MIMIC.identifier().getPath(), () -> new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST).setId(Keys.SKY_CACTUS_FIBER_CHEST_MIMIC)));

	//barrel mimics
	public static final DeferredBlock<Block> SHADOW_PINE_BARREL_MIMIC = BLOCKS.register(Keys.SHADOW_PINE_BARREL_MIMIC.identifier().getPath(), () -> new BarrelMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL).setId(Keys.SHADOW_PINE_BARREL_MIMIC)));

	//fences, bars or walls
	public static final DeferredBlock<FenceBlock> AERIAL_TREE_FENCE = BLOCKS.register(Keys.AERIAL_TREE_FENCE.identifier().getPath(), () -> new FenceBlock(AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_FENCE)));
	public static final DeferredBlock<FenceBlock> GOLDEN_BEECH_FENCE = BLOCKS.register(Keys.GOLDEN_BEECH_FENCE.identifier().getPath(), () -> new FenceBlock(AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_FENCE)));
	public static final DeferredBlock<FenceBlock> COPPER_PINE_FENCE = BLOCKS.register(Keys.COPPER_PINE_FENCE.identifier().getPath(), () -> new FenceBlock(COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_FENCE)));
	public static final DeferredBlock<FenceBlock> LAPIS_ROBINIA_FENCE = BLOCKS.register(Keys.LAPIS_ROBINIA_FENCE.identifier().getPath(), () -> new FenceBlock(COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_FENCE)));
	public static final DeferredBlock<FenceBlock> SHADOW_PINE_FENCE = BLOCKS.register(Keys.SHADOW_PINE_FENCE.identifier().getPath(), () -> new FenceBlock(SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_FENCE)));
	public static final DeferredBlock<FenceBlock> STELLAR_JUNGLE_TREE_FENCE = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_FENCE.identifier().getPath(), () -> new FenceBlock(COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_FENCE)));
	public static final DeferredBlock<FenceBlock> SKY_CACTUS_FIBER_FENCE = BLOCKS.register(Keys.SKY_CACTUS_FIBER_FENCE.identifier().getPath(), () -> new FenceBlock(SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_FENCE)));
	public static final DeferredBlock<FenceBlock> GRAY_SHROOM_FENCE = BLOCKS.register(Keys.GRAY_SHROOM_FENCE.identifier().getPath(), () -> new FenceBlock(SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_FENCE)));
	public static final DeferredBlock<IronBarsBlock> RUBY_BARS = BLOCKS.register(Keys.RUBY_BARS.identifier().getPath(), () -> new IronBarsBlock(METAL_NOTSOLID_MATERIAL.setId(Keys.RUBY_BARS)));
	public static final DeferredBlock<WallBlock> STELLAR_STONE_WALL = BLOCKS.register(Keys.STELLAR_STONE_WALL.identifier().getPath(), () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get()).setId(Keys.STELLAR_STONE_WALL)));
	public static final DeferredBlock<WallBlock> STELLAR_COBBLESTONE_WALL = BLOCKS.register(Keys.STELLAR_COBBLESTONE_WALL.identifier().getPath(), () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE.get()).setId(Keys.STELLAR_COBBLESTONE_WALL)));
	public static final DeferredBlock<WallBlock> STELLAR_STONE_BRICKS_WALL = BLOCKS.register(Keys.STELLAR_STONE_BRICKS_WALL.identifier().getPath(), () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS.get()).setId(Keys.STELLAR_STONE_BRICKS_WALL)));
	public static final DeferredBlock<WallBlock> MOSSY_STELLAR_STONE_WALL = BLOCKS.register(Keys.MOSSY_STELLAR_STONE_WALL.identifier().getPath(), () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_STONE.get()).setId(Keys.MOSSY_STELLAR_STONE_WALL)));
	public static final DeferredBlock<WallBlock> MOSSY_STELLAR_COBBLESTONE_WALL = BLOCKS.register(Keys.MOSSY_STELLAR_COBBLESTONE_WALL.identifier().getPath(), () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_COBBLESTONE.get()).setId(Keys.MOSSY_STELLAR_COBBLESTONE_WALL)));
	public static final DeferredBlock<WallBlock> SLIPPERY_SAND_STONE_WALL = BLOCKS.register(Keys.SLIPPERY_SAND_STONE_WALL.identifier().getPath(), () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get()).setId(Keys.SLIPPERY_SAND_STONE_WALL)));
	public static final DeferredBlock<WallBlock> SLIPPERY_SAND_STONE_BRICKS_WALL = BLOCKS.register(Keys.SLIPPERY_SAND_STONE_BRICKS_WALL.identifier().getPath(), () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get()).setId(Keys.SLIPPERY_SAND_STONE_BRICKS_WALL)));
	public static final DeferredBlock<WallBlock> CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL = BLOCKS.register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL.identifier().getPath(), () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get()).setId(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL)));
	public static final DeferredBlock<WallBlock> GLAUCOPHANITE_WALL = BLOCKS.register(Keys.GLAUCOPHANITE_WALL.identifier().getPath(), () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(GLAUCOPHANITE.get()).setId(Keys.GLAUCOPHANITE_WALL)));
	public static final DeferredBlock<WallBlock> POLISHED_GLAUCOPHANITE_WALL = BLOCKS.register(Keys.POLISHED_GLAUCOPHANITE_WALL.identifier().getPath(), () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_GLAUCOPHANITE.get()).setId(Keys.POLISHED_GLAUCOPHANITE_WALL)));
	public static final DeferredBlock<WallBlock> MAGMATIC_GEL_WALL = BLOCKS.register(Keys.MAGMATIC_GEL_WALL.identifier().getPath(), () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MAGMATIC_GEL_BLOCK.get()).setId(Keys.MAGMATIC_GEL_WALL)));

	//gates
	public static final DeferredBlock<FenceGateBlock> AERIAL_TREE_GATE = BLOCKS.register(Keys.AERIAL_TREE_GATE.identifier().getPath(), () -> new FenceGateBlock(AerialHellWoodTypes.AERIAL_TREE, AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_GATE)));
	public static final DeferredBlock<FenceGateBlock> GOLDEN_BEECH_GATE = BLOCKS.register(Keys.GOLDEN_BEECH_GATE.identifier().getPath(), () -> new FenceGateBlock(AerialHellWoodTypes.GOLDEN_BEECH, AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_GATE)));
	public static final DeferredBlock<FenceGateBlock> COPPER_PINE_GATE = BLOCKS.register(Keys.COPPER_PINE_GATE.identifier().getPath(), () -> new FenceGateBlock(AerialHellWoodTypes.COPPER_PINE, COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_GATE)));
	public static final DeferredBlock<FenceGateBlock> LAPIS_ROBINIA_GATE = BLOCKS.register(Keys.LAPIS_ROBINIA_GATE.identifier().getPath(), () -> new FenceGateBlock(AerialHellWoodTypes.LAPIS_ROBINIA, COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_GATE)));
	public static final DeferredBlock<FenceGateBlock> SHADOW_PINE_GATE = BLOCKS.register(Keys.SHADOW_PINE_GATE.identifier().getPath(), () -> new FenceGateBlock(AerialHellWoodTypes.SHADOW_PINE, SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_GATE)));
	public static final DeferredBlock<FenceGateBlock> STELLAR_JUNGLE_TREE_GATE = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_GATE.identifier().getPath(), () -> new FenceGateBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_GATE)));
	public static final DeferredBlock<FenceGateBlock> SKY_CACTUS_FIBER_GATE = BLOCKS.register(Keys.SKY_CACTUS_FIBER_GATE.identifier().getPath(), () -> new FenceGateBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_GATE)));
	public static final DeferredBlock<FenceGateBlock> GRAY_SHROOM_GATE = BLOCKS.register(Keys.GRAY_SHROOM_GATE.identifier().getPath(), () -> new FenceGateBlock(AerialHellWoodTypes.GRAY_SHROOM, SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_GATE)));

	//doors
	public static final DeferredBlock<DoorBlock> AERIAL_TREE_DOOR = BLOCKS.register(Keys.AERIAL_TREE_DOOR.identifier().getPath(), () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS.get()).setId(Keys.AERIAL_TREE_DOOR).noOcclusion()));
	public static final DeferredBlock<DoorBlock> GOLDEN_BEECH_DOOR = BLOCKS.register(Keys.GOLDEN_BEECH_DOOR.identifier().getPath(), () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS.get()).setId(Keys.GOLDEN_BEECH_DOOR).noOcclusion()));
	public static final DeferredBlock<DoorBlock> COPPER_PINE_DOOR = BLOCKS.register(Keys.COPPER_PINE_DOOR.identifier().getPath(), () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_PLANKS.get()).setId(Keys.COPPER_PINE_DOOR).noOcclusion()));
	public static final DeferredBlock<DoorBlock> LAPIS_ROBINIA_DOOR = BLOCKS.register(Keys.LAPIS_ROBINIA_DOOR.identifier().getPath(), () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_PLANKS.get()).setId(Keys.LAPIS_ROBINIA_DOOR).noOcclusion()));
	public static final DeferredBlock<DoorBlock> SHADOW_PINE_DOOR = BLOCKS.register(Keys.SHADOW_PINE_DOOR.identifier().getPath(), () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_PLANKS.get()).setId(Keys.SHADOW_PINE_DOOR).noOcclusion()));
	public static final DeferredBlock<DoorBlock> STELLAR_JUNGLE_TREE_DOOR = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_DOOR.identifier().getPath(), () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_PLANKS.get()).setId(Keys.STELLAR_JUNGLE_TREE_DOOR).noOcclusion()));
	public static final DeferredBlock<DoorBlock> SKY_CACTUS_FIBER_DOOR = BLOCKS.register(Keys.SKY_CACTUS_FIBER_DOOR.identifier().getPath(), () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_PLANKS.get()).setId(Keys.SKY_CACTUS_FIBER_DOOR).noOcclusion()));
	public static final DeferredBlock<DoorBlock> GRAY_SHROOM_DOOR = BLOCKS.register(Keys.GRAY_SHROOM_DOOR.identifier().getPath(), () -> new DoorBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_PLANKS.get()).setId(Keys.GRAY_SHROOM_DOOR).noOcclusion()));
	public static final DeferredBlock<DoorBlock> RUBY_DOOR = BLOCKS.register(Keys.RUBY_DOOR.identifier().getPath(), () -> new DoorBlock(BlockSetType.IRON, METAL_NOTSOLID_MATERIAL.setId(Keys.RUBY_DOOR)));

	//trapdoors
	public static final DeferredBlock<TrapDoorBlock> AERIAL_TREE_TRAPDOOR = BLOCKS.register(Keys.AERIAL_TREE_TRAPDOOR.identifier().getPath(), () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS.get()).setId(Keys.AERIAL_TREE_TRAPDOOR).noOcclusion()));
	public static final DeferredBlock<TrapDoorBlock> GOLDEN_BEECH_TRAPDOOR = BLOCKS.register(Keys.GOLDEN_BEECH_TRAPDOOR.identifier().getPath(), () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS.get()).setId(Keys.GOLDEN_BEECH_TRAPDOOR).noOcclusion()));
	public static final DeferredBlock<TrapDoorBlock> COPPER_PINE_TRAPDOOR = BLOCKS.register(Keys.COPPER_PINE_TRAPDOOR.identifier().getPath(), () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_PLANKS.get()).setId(Keys.COPPER_PINE_TRAPDOOR).noOcclusion()));
	public static final DeferredBlock<TrapDoorBlock> LAPIS_ROBINIA_TRAPDOOR = BLOCKS.register(Keys.LAPIS_ROBINIA_TRAPDOOR.identifier().getPath(), () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_PLANKS.get()).setId(Keys.LAPIS_ROBINIA_TRAPDOOR).noOcclusion()));
	public static final DeferredBlock<TrapDoorBlock> SHADOW_PINE_TRAPDOOR = BLOCKS.register(Keys.SHADOW_PINE_TRAPDOOR.identifier().getPath(), () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_PLANKS.get()).setId(Keys.SHADOW_PINE_TRAPDOOR).noOcclusion()));
	public static final DeferredBlock<TrapDoorBlock> STELLAR_JUNGLE_TREE_TRAPDOOR = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_TRAPDOOR.identifier().getPath(), () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_PLANKS.get()).setId(Keys.STELLAR_JUNGLE_TREE_TRAPDOOR).noOcclusion()));
	public static final DeferredBlock<TrapDoorBlock> SKY_CACTUS_FIBER_TRAPDOOR = BLOCKS.register(Keys.SKY_CACTUS_FIBER_TRAPDOOR.identifier().getPath(), () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_PLANKS.get()).setId(Keys.SKY_CACTUS_FIBER_TRAPDOOR).noOcclusion()));
	public static final DeferredBlock<TrapDoorBlock> GRAY_SHROOM_TRAPDOOR = BLOCKS.register(Keys.GRAY_SHROOM_TRAPDOOR.identifier().getPath(), () -> new TrapDoorBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_PLANKS.get()).setId(Keys.GRAY_SHROOM_TRAPDOOR).noOcclusion()));
	public static final DeferredBlock<TrapDoorBlock> RUBY_TRAPDOOR = BLOCKS.register(Keys.RUBY_TRAPDOOR.identifier().getPath(), () -> new TrapDoorBlock(BlockSetType.IRON, METAL_NOTSOLID_MATERIAL.setId(Keys.RUBY_TRAPDOOR)));

	//buttons
	public static final DeferredBlock<ButtonBlock> STELLAR_STONE_BUTTON = BLOCKS.register(Keys.STELLAR_STONE_BUTTON.identifier().getPath(), () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get()).setId(Keys.STELLAR_STONE_BUTTON)));
	public static final DeferredBlock<ButtonBlock> STELLAR_COBBLESTONE_BUTTON = BLOCKS.register(Keys.STELLAR_COBBLESTONE_BUTTON.identifier().getPath(), () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE.get()).setId(Keys.STELLAR_COBBLESTONE_BUTTON)));
	public static final DeferredBlock<ButtonBlock> STELLAR_STONE_BRICKS_BUTTON = BLOCKS.register(Keys.STELLAR_STONE_BRICKS_BUTTON.identifier().getPath(), () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS.get()).setId(Keys.STELLAR_STONE_BRICKS_BUTTON)));
	public static final DeferredBlock<ButtonBlock> SLIPPERY_SAND_STONE_BUTTON = BLOCKS.register(Keys.SLIPPERY_SAND_STONE_BUTTON.identifier().getPath(), () -> new ButtonBlock(BlockSetType.STONE, 30, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get()).setId(Keys.SLIPPERY_SAND_STONE_BUTTON)));
	public static final DeferredBlock<ButtonBlock> SLIPPERY_SAND_STONE_BRICKS_BUTTON = BLOCKS.register(Keys.SLIPPERY_SAND_STONE_BRICKS_BUTTON.identifier().getPath(), () -> new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get()).setId(Keys.SLIPPERY_SAND_STONE_BRICKS_BUTTON)));
	public static final DeferredBlock<ButtonBlock> AERIAL_TREE_BUTTON = BLOCKS.register(Keys.AERIAL_TREE_BUTTON.identifier().getPath(), () -> new ButtonBlock(BlockSetType.OAK, 30, AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_BUTTON)));
	public static final DeferredBlock<ButtonBlock> GOLDEN_BEECH_BUTTON = BLOCKS.register(Keys.GOLDEN_BEECH_BUTTON.identifier().getPath(), () -> new ButtonBlock(BlockSetType.OAK, 30, AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_BUTTON)));
	public static final DeferredBlock<ButtonBlock> COPPER_PINE_BUTTON = BLOCKS.register(Keys.COPPER_PINE_BUTTON.identifier().getPath(), () -> new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_BUTTON)));
	public static final DeferredBlock<ButtonBlock> LAPIS_ROBINIA_BUTTON = BLOCKS.register(Keys.LAPIS_ROBINIA_BUTTON.identifier().getPath(), () -> new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_BUTTON)));
	public static final DeferredBlock<ButtonBlock> SHADOW_PINE_BUTTON = BLOCKS.register(Keys.SHADOW_PINE_BUTTON.identifier().getPath(), () -> new ButtonBlock(BlockSetType.OAK, 30, SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_BUTTON)));
	public static final DeferredBlock<ButtonBlock> STELLAR_JUNGLE_TREE_BUTTON = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_BUTTON.identifier().getPath(), () -> new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_BUTTON)));
	public static final DeferredBlock<ButtonBlock> SKY_CACTUS_FIBER_BUTTON = BLOCKS.register(Keys.SKY_CACTUS_FIBER_BUTTON.identifier().getPath(), () -> new ButtonBlock(BlockSetType.OAK, 30, SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_BUTTON)));
	public static final DeferredBlock<ButtonBlock> GRAY_SHROOM_BUTTON = BLOCKS.register(Keys.GRAY_SHROOM_BUTTON.identifier().getPath(), () -> new ButtonBlock(BlockSetType.CRIMSON, 30, SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_BUTTON)));
	public static final DeferredBlock<ButtonBlock> GLAUCOPHANITE_BUTTON = BLOCKS.register(Keys.GLAUCOPHANITE_BUTTON.identifier().getPath(), () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(GLAUCOPHANITE.get()).setId(Keys.GLAUCOPHANITE_BUTTON)));

	//pressure plates
	public static final DeferredBlock<PressurePlateBlock> STELLAR_STONE_PRESSURE_PLATE = BLOCKS.register(Keys.STELLAR_STONE_PRESSURE_PLATE.identifier().getPath(), () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get()).setId(Keys.STELLAR_STONE_PRESSURE_PLATE)));
	public static final DeferredBlock<PressurePlateBlock> STELLAR_COBBLESTONE_PRESSURE_PLATE = BLOCKS.register(Keys.STELLAR_COBBLESTONE_PRESSURE_PLATE.identifier().getPath(), () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE.get()).setId(Keys.STELLAR_COBBLESTONE_PRESSURE_PLATE)));
	public static final DeferredBlock<PressurePlateBlock> STELLAR_STONE_BRICKS_PRESSURE_PLATE = BLOCKS.register(Keys.STELLAR_STONE_BRICKS_PRESSURE_PLATE.identifier().getPath(), () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS.get()).setId(Keys.STELLAR_STONE_BRICKS_PRESSURE_PLATE)));
	public static final DeferredBlock<PressurePlateBlock> SLIPPERY_SAND_STONE_PRESSURE_PLATE = BLOCKS.register(Keys.SLIPPERY_SAND_STONE_PRESSURE_PLATE.identifier().getPath(), () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get()).setId(Keys.SLIPPERY_SAND_STONE_PRESSURE_PLATE)));
	public static final DeferredBlock<PressurePlateBlock> SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE = BLOCKS.register(Keys.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE.identifier().getPath(), () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get()).setId(Keys.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE)));
	public static final DeferredBlock<PressurePlateBlock> AERIAL_TREE_PRESSURE_PLATE = BLOCKS.register(Keys.AERIAL_TREE_PRESSURE_PLATE.identifier().getPath(), () -> new PressurePlateBlock(BlockSetType.OAK, AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_PRESSURE_PLATE)));
	public static final DeferredBlock<PressurePlateBlock> GOLDEN_BEECH_PRESSURE_PLATE = BLOCKS.register(Keys.GOLDEN_BEECH_PRESSURE_PLATE.identifier().getPath(), () -> new PressurePlateBlock(BlockSetType.OAK, AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_PRESSURE_PLATE)));
	public static final DeferredBlock<PressurePlateBlock> COPPER_PINE_PRESSURE_PLATE = BLOCKS.register(Keys.COPPER_PINE_PRESSURE_PLATE.identifier().getPath(), () -> new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_PRESSURE_PLATE)));
	public static final DeferredBlock<PressurePlateBlock> LAPIS_ROBINIA_PRESSURE_PLATE = BLOCKS.register(Keys.LAPIS_ROBINIA_PRESSURE_PLATE.identifier().getPath(), () -> new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_PRESSURE_PLATE)));
	public static final DeferredBlock<PressurePlateBlock> SHADOW_PINE_PRESSURE_PLATE = BLOCKS.register(Keys.SHADOW_PINE_PRESSURE_PLATE.identifier().getPath(), () -> new PressurePlateBlock(BlockSetType.OAK, SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_PRESSURE_PLATE)));
	public static final DeferredBlock<PressurePlateBlock> STELLAR_JUNGLE_TREE_PRESSURE_PLATE = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_PRESSURE_PLATE.identifier().getPath(), () -> new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_PRESSURE_PLATE)));
	public static final DeferredBlock<PressurePlateBlock> SKY_CACTUS_FIBER_PRESSURE_PLATE = BLOCKS.register(Keys.SKY_CACTUS_FIBER_PRESSURE_PLATE.identifier().getPath(), () -> new PressurePlateBlock(BlockSetType.OAK, SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_PRESSURE_PLATE)));
	public static final DeferredBlock<PressurePlateBlock> GRAY_SHROOM_PRESSURE_PLATE = BLOCKS.register(Keys.GRAY_SHROOM_PRESSURE_PLATE.identifier().getPath(), () -> new PressurePlateBlock(BlockSetType.CRIMSON, SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_PRESSURE_PLATE)));
	public static final DeferredBlock<PressurePlateBlock> GLAUCOPHANITE_PRESSURE_PLATE = BLOCKS.register(Keys.GLAUCOPHANITE_PRESSURE_PLATE.identifier().getPath(), () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(GLAUCOPHANITE.get()).setId(Keys.GLAUCOPHANITE_PRESSURE_PLATE)));

	//slabs
	public static final DeferredBlock<SlabBlock> AERIAL_TREE_SLAB = BLOCKS.register(Keys.AERIAL_TREE_SLAB.identifier().getPath(), () -> new SlabBlock(AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_SLAB)));
	public static final DeferredBlock<SlabBlock> GOLDEN_BEECH_SLAB = BLOCKS.register(Keys.GOLDEN_BEECH_SLAB.identifier().getPath(), () -> new SlabBlock(AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_SLAB)));
	public static final DeferredBlock<SlabBlock> COPPER_PINE_SLAB = BLOCKS.register(Keys.COPPER_PINE_SLAB.identifier().getPath(), () -> new SlabBlock(COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_SLAB)));
	public static final DeferredBlock<SlabBlock> LAPIS_ROBINIA_SLAB = BLOCKS.register(Keys.LAPIS_ROBINIA_SLAB.identifier().getPath(), () -> new SlabBlock(COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_SLAB)));
	public static final DeferredBlock<SlabBlock> SHADOW_PINE_SLAB = BLOCKS.register(Keys.SHADOW_PINE_SLAB.identifier().getPath(), () -> new SlabBlock(SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_SLAB)));
	public static final DeferredBlock<SlabBlock> STELLAR_JUNGLE_TREE_SLAB = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_SLAB.identifier().getPath(), () -> new SlabBlock(COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_SLAB)));
	public static final DeferredBlock<SlabBlock> SKY_CACTUS_FIBER_SLAB = BLOCKS.register(Keys.SKY_CACTUS_FIBER_SLAB.identifier().getPath(), () -> new SlabBlock(SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_SLAB)));
	public static final DeferredBlock<SlabBlock> GRAY_SHROOM_SLAB = BLOCKS.register(Keys.GRAY_SHROOM_SLAB.identifier().getPath(), () -> new SlabBlock(SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_SLAB)));
	public static final DeferredBlock<SlabBlock> STELLAR_STONE_SLAB = BLOCKS.register(Keys.STELLAR_STONE_SLAB.identifier().getPath(), () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get()).setId(Keys.STELLAR_STONE_SLAB)));
	public static final DeferredBlock<SlabBlock> STELLAR_COBBLESTONE_SLAB = BLOCKS.register(Keys.STELLAR_COBBLESTONE_SLAB.identifier().getPath(), () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE.get()).setId(Keys.STELLAR_COBBLESTONE_SLAB)));
	public static final DeferredBlock<SlabBlock> STELLAR_STONE_BRICKS_SLAB = BLOCKS.register(Keys.STELLAR_STONE_BRICKS_SLAB.identifier().getPath(), () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS.get()).setId(Keys.STELLAR_STONE_BRICKS_SLAB)));
	public static final DeferredBlock<SlabBlock> MOSSY_STELLAR_STONE_SLAB = BLOCKS.register(Keys.MOSSY_STELLAR_STONE_SLAB.identifier().getPath(), () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_STONE.get()).setId(Keys.MOSSY_STELLAR_STONE_SLAB)));
	public static final DeferredBlock<SlabBlock> MOSSY_STELLAR_COBBLESTONE_SLAB = BLOCKS.register(Keys.MOSSY_STELLAR_COBBLESTONE_SLAB.identifier().getPath(), () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_COBBLESTONE.get()).setId(Keys.MOSSY_STELLAR_COBBLESTONE_SLAB)));
	public static final DeferredBlock<SlabBlock> SLIPPERY_SAND_STONE_SLAB = BLOCKS.register(Keys.SLIPPERY_SAND_STONE_SLAB.identifier().getPath(), () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get()).setId(Keys.SLIPPERY_SAND_STONE_SLAB)));
	public static final DeferredBlock<SlabBlock> SLIPPERY_SAND_STONE_BRICKS_SLAB = BLOCKS.register(Keys.SLIPPERY_SAND_STONE_BRICKS_SLAB.identifier().getPath(), () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get()).setId(Keys.SLIPPERY_SAND_STONE_BRICKS_SLAB)));
	public static final DeferredBlock<SlabBlock> CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB = BLOCKS.register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB.identifier().getPath(), () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get()).setId(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB)));
	public static final DeferredBlock<SlabBlock> POLISHED_GLAUCOPHANITE_SLAB = BLOCKS.register(Keys.POLISHED_GLAUCOPHANITE_SLAB.identifier().getPath(), () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_GLAUCOPHANITE.get()).setId(Keys.POLISHED_GLAUCOPHANITE_SLAB)));
	public static final DeferredBlock<SlabBlock> MAGMATIC_GEL_SLAB = BLOCKS.register(Keys.MAGMATIC_GEL_SLAB.identifier().getPath(), () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MAGMATIC_GEL_BLOCK.get()).setId(Keys.MAGMATIC_GEL_SLAB)));

	//stairs
	public static final DeferredBlock<StairBlock> AERIAL_TREE_STAIRS = BLOCKS.register(Keys.AERIAL_TREE_STAIRS.identifier().getPath(), () -> new StairBlock(AERIAL_TREE_PLANKS.get().defaultBlockState(), AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_STAIRS)));
	public static final DeferredBlock<StairBlock> GOLDEN_BEECH_STAIRS = BLOCKS.register(Keys.GOLDEN_BEECH_STAIRS.identifier().getPath(), () -> new StairBlock(GOLDEN_BEECH_PLANKS.get().defaultBlockState(), AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_STAIRS)));
	public static final DeferredBlock<StairBlock> COPPER_PINE_STAIRS = BLOCKS.register(Keys.COPPER_PINE_STAIRS.identifier().getPath(), () -> new StairBlock(COPPER_PINE_PLANKS.get().defaultBlockState(), COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_STAIRS)));
	public static final DeferredBlock<StairBlock> LAPIS_ROBINIA_STAIRS = BLOCKS.register(Keys.LAPIS_ROBINIA_STAIRS.identifier().getPath(), () -> new StairBlock(LAPIS_ROBINIA_PLANKS.get().defaultBlockState(), COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_STAIRS)));
	public static final DeferredBlock<StairBlock> SHADOW_PINE_STAIRS = BLOCKS.register(Keys.SHADOW_PINE_STAIRS.identifier().getPath(), () -> new StairBlock(SHADOW_PINE_PLANKS.get().defaultBlockState(), SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_STAIRS)));
	public static final DeferredBlock<StairBlock> STELLAR_JUNGLE_TREE_STAIRS = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_STAIRS.identifier().getPath(), () -> new StairBlock(STELLAR_JUNGLE_TREE_PLANKS.get().defaultBlockState(), COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_STAIRS)));
	public static final DeferredBlock<StairBlock> SKY_CACTUS_FIBER_STAIRS = BLOCKS.register(Keys.SKY_CACTUS_FIBER_STAIRS.identifier().getPath(), () -> new StairBlock(SKY_CACTUS_FIBER_PLANKS.get().defaultBlockState(), SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_STAIRS)));
	public static final DeferredBlock<StairBlock> GRAY_SHROOM_STAIRS = BLOCKS.register(Keys.GRAY_SHROOM_STAIRS.identifier().getPath(), () -> new StairBlock(GRAY_SHROOM_PLANKS.get().defaultBlockState(), SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_STAIRS)));
	public static final DeferredBlock<StairBlock> STELLAR_STONE_STAIRS = BLOCKS.register(Keys.STELLAR_STONE_STAIRS.identifier().getPath(), () -> new StairBlock(STELLAR_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get()).setId(Keys.STELLAR_STONE_STAIRS)));
	public static final DeferredBlock<StairBlock> STELLAR_COBBLESTONE_STAIRS = BLOCKS.register(Keys.STELLAR_COBBLESTONE_STAIRS.identifier().getPath(), () -> new StairBlock(STELLAR_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE.get()).setId(Keys.STELLAR_COBBLESTONE_STAIRS)));
	public static final DeferredBlock<StairBlock> STELLAR_STONE_BRICKS_STAIRS = BLOCKS.register(Keys.STELLAR_STONE_BRICKS_STAIRS.identifier().getPath(), () -> new StairBlock(STELLAR_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS.get()).setId(Keys.STELLAR_STONE_BRICKS_STAIRS)));
	public static final DeferredBlock<StairBlock> MOSSY_STELLAR_STONE_STAIRS = BLOCKS.register(Keys.MOSSY_STELLAR_STONE_STAIRS.identifier().getPath(), () -> new StairBlock(MOSSY_STELLAR_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_STONE.get()).setId(Keys.MOSSY_STELLAR_STONE_STAIRS)));
	public static final DeferredBlock<StairBlock> MOSSY_STELLAR_COBBLESTONE_STAIRS = BLOCKS.register(Keys.MOSSY_STELLAR_COBBLESTONE_STAIRS.identifier().getPath(), () -> new StairBlock(MOSSY_STELLAR_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_COBBLESTONE.get()).setId(Keys.MOSSY_STELLAR_COBBLESTONE_STAIRS)));
	public static final DeferredBlock<StairBlock> SLIPPERY_SAND_STONE_STAIRS = BLOCKS.register(Keys.SLIPPERY_SAND_STONE_STAIRS.identifier().getPath(), () -> new StairBlock(SLIPPERY_SAND_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get()).setId(Keys.SLIPPERY_SAND_STONE_STAIRS)));
	public static final DeferredBlock<StairBlock> SLIPPERY_SAND_STONE_BRICKS_STAIRS = BLOCKS.register(Keys.SLIPPERY_SAND_STONE_BRICKS_STAIRS.identifier().getPath(), () -> new StairBlock(SLIPPERY_SAND_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get()).setId(Keys.SLIPPERY_SAND_STONE_BRICKS_STAIRS)));
	public static final DeferredBlock<StairBlock> CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS = BLOCKS.register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS.identifier().getPath(), () -> new StairBlock(SLIPPERY_SAND_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get()).setId(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS)));
	public static final DeferredBlock<StairBlock> POLISHED_GLAUCOPHANITE_STAIRS = BLOCKS.register(Keys.POLISHED_GLAUCOPHANITE_STAIRS.identifier().getPath(), () -> new StairBlock(POLISHED_GLAUCOPHANITE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(POLISHED_GLAUCOPHANITE.get()).setId(Keys.POLISHED_GLAUCOPHANITE_STAIRS)));
	public static final DeferredBlock<StairBlock> MAGMATIC_GEL_STAIRS = BLOCKS.register(Keys.MAGMATIC_GEL_STAIRS.identifier().getPath(), () -> new StairBlock(MAGMATIC_GEL_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MAGMATIC_GEL_BLOCK.get()).setId(Keys.MAGMATIC_GEL_STAIRS)));

	//signs
	public static final DeferredBlock<AerialHellStandingSignBlock> AERIAL_TREE_STANDING_SIGN = BLOCKS.register(Keys.AERIAL_TREE_STANDING_SIGN.identifier().getPath(), () -> new AerialHellStandingSignBlock(AERIAL_TREE_SIGN_MATERIAL.setId(Keys.AERIAL_TREE_STANDING_SIGN), AerialHellWoodTypes.AERIAL_TREE));
	public static final DeferredBlock<AerialHellWallSignBlock> AERIAL_TREE_WALL_SIGN = BLOCKS.register(Keys.AERIAL_TREE_WALL_SIGN.identifier().getPath(), () -> new AerialHellWallSignBlock(AERIAL_TREE_SIGN_MATERIAL.setId(Keys.AERIAL_TREE_WALL_SIGN), AerialHellWoodTypes.AERIAL_TREE));
	public static final DeferredBlock<AerialHellStandingSignBlock> GOLDEN_BEECH_STANDING_SIGN = BLOCKS.register(Keys.GOLDEN_BEECH_STANDING_SIGN.identifier().getPath(), () -> new AerialHellStandingSignBlock(AERIAL_TREE_SIGN_MATERIAL.setId(Keys.GOLDEN_BEECH_STANDING_SIGN), AerialHellWoodTypes.GOLDEN_BEECH));
	public static final DeferredBlock<AerialHellWallSignBlock> GOLDEN_BEECH_WALL_SIGN = BLOCKS.register(Keys.GOLDEN_BEECH_WALL_SIGN.identifier().getPath(), () -> new AerialHellWallSignBlock(AERIAL_TREE_SIGN_MATERIAL.setId(Keys.GOLDEN_BEECH_WALL_SIGN), AerialHellWoodTypes.GOLDEN_BEECH));
	public static final DeferredBlock<AerialHellStandingSignBlock> COPPER_PINE_STANDING_SIGN = BLOCKS.register(Keys.COPPER_PINE_STANDING_SIGN.identifier().getPath(), () -> new AerialHellStandingSignBlock(COPPER_PINE_SIGN_MATERIAL.setId(Keys.COPPER_PINE_STANDING_SIGN), AerialHellWoodTypes.COPPER_PINE));
	public static final DeferredBlock<AerialHellWallSignBlock> COPPER_PINE_WALL_SIGN = BLOCKS.register(Keys.COPPER_PINE_WALL_SIGN.identifier().getPath(), () -> new AerialHellWallSignBlock(COPPER_PINE_SIGN_MATERIAL.setId(Keys.COPPER_PINE_WALL_SIGN), AerialHellWoodTypes.COPPER_PINE));
	public static final DeferredBlock<AerialHellStandingSignBlock> LAPIS_ROBINIA_STANDING_SIGN = BLOCKS.register(Keys.LAPIS_ROBINIA_STANDING_SIGN.identifier().getPath(), () -> new AerialHellStandingSignBlock(COPPER_PINE_SIGN_MATERIAL.setId(Keys.LAPIS_ROBINIA_STANDING_SIGN), AerialHellWoodTypes.LAPIS_ROBINIA));
	public static final DeferredBlock<AerialHellWallSignBlock> LAPIS_ROBINIA_WALL_SIGN = BLOCKS.register(Keys.LAPIS_ROBINIA_WALL_SIGN.identifier().getPath(), () -> new AerialHellWallSignBlock(COPPER_PINE_SIGN_MATERIAL.setId(Keys.LAPIS_ROBINIA_WALL_SIGN), AerialHellWoodTypes.LAPIS_ROBINIA));
	public static final DeferredBlock<AerialHellStandingSignBlock> SHADOW_PINE_STANDING_SIGN = BLOCKS.register(Keys.SHADOW_PINE_STANDING_SIGN.identifier().getPath(), () -> new AerialHellStandingSignBlock(SHADOW_PINE_SIGN_MATERIAL.setId(Keys.SHADOW_PINE_STANDING_SIGN), AerialHellWoodTypes.SHADOW_PINE));
	public static final DeferredBlock<AerialHellWallSignBlock> SHADOW_PINE_WALL_SIGN = BLOCKS.register(Keys.SHADOW_PINE_WALL_SIGN.identifier().getPath(), () -> new AerialHellWallSignBlock(SHADOW_PINE_SIGN_MATERIAL.setId(Keys.SHADOW_PINE_WALL_SIGN), AerialHellWoodTypes.SHADOW_PINE));
	public static final DeferredBlock<AerialHellStandingSignBlock> STELLAR_JUNGLE_TREE_STANDING_SIGN = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_STANDING_SIGN.identifier().getPath(), () -> new AerialHellStandingSignBlock(COPPER_PINE_SIGN_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_STANDING_SIGN), AerialHellWoodTypes.STELLAR_JUNGLE_TREE));
	public static final DeferredBlock<AerialHellWallSignBlock> STELLAR_JUNGLE_TREE_WALL_SIGN = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_WALL_SIGN.identifier().getPath(), () -> new AerialHellWallSignBlock(COPPER_PINE_SIGN_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_WALL_SIGN), AerialHellWoodTypes.STELLAR_JUNGLE_TREE));
	public static final DeferredBlock<AerialHellStandingSignBlock> SKY_CACTUS_FIBER_STANDING_SIGN = BLOCKS.register(Keys.SKY_CACTUS_FIBER_STANDING_SIGN.identifier().getPath(), () -> new AerialHellStandingSignBlock(SKY_CACTUS_FIBER_SIGN_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_STANDING_SIGN), AerialHellWoodTypes.SKY_CACTUS_FIBER));
	public static final DeferredBlock<AerialHellWallSignBlock> SKY_CACTUS_FIBER_WALL_SIGN = BLOCKS.register(Keys.SKY_CACTUS_FIBER_WALL_SIGN.identifier().getPath(), () -> new AerialHellWallSignBlock(SKY_CACTUS_FIBER_SIGN_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_WALL_SIGN), AerialHellWoodTypes.SKY_CACTUS_FIBER));
	public static final DeferredBlock<AerialHellStandingSignBlock> GRAY_SHROOM_STANDING_SIGN = BLOCKS.register(Keys.GRAY_SHROOM_STANDING_SIGN.identifier().getPath(), () -> new AerialHellStandingSignBlock(SHROOM_SIGN_MATERIAL.setId(Keys.GRAY_SHROOM_STANDING_SIGN), AerialHellWoodTypes.GRAY_SHROOM));
	public static final DeferredBlock<AerialHellWallSignBlock> GRAY_SHROOM_WALL_SIGN = BLOCKS.register(Keys.GRAY_SHROOM_WALL_SIGN.identifier().getPath(), () -> new AerialHellWallSignBlock(SHROOM_SIGN_MATERIAL.setId(Keys.GRAY_SHROOM_WALL_SIGN), AerialHellWoodTypes.GRAY_SHROOM));

	//hanging signs
	public static final DeferredBlock<CeilingHangingSignBlock> AERIAL_TREE_HANGING_SIGN = BLOCKS.register(Keys.AERIAL_TREE_HANGING_SIGN.identifier().getPath(), () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.AERIAL_TREE, AERIAL_TREE_SIGN_MATERIAL.setId(Keys.AERIAL_TREE_HANGING_SIGN)));
	public static final DeferredBlock<WallHangingSignBlock> AERIAL_TREE_WALL_HANGING_SIGN = BLOCKS.register(Keys.AERIAL_TREE_WALL_HANGING_SIGN.identifier().getPath(), () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.AERIAL_TREE, BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_HANGING_SIGN.get()).setId(Keys.AERIAL_TREE_WALL_HANGING_SIGN).overrideLootTable(AERIAL_TREE_HANGING_SIGN.get().getLootTable())));
	public static final DeferredBlock<CeilingHangingSignBlock> GOLDEN_BEECH_HANGING_SIGN = BLOCKS.register(Keys.GOLDEN_BEECH_HANGING_SIGN.identifier().getPath(), () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.GOLDEN_BEECH, AERIAL_TREE_SIGN_MATERIAL.setId(Keys.GOLDEN_BEECH_HANGING_SIGN)));
	public static final DeferredBlock<WallHangingSignBlock> GOLDEN_BEECH_WALL_HANGING_SIGN = BLOCKS.register(Keys.GOLDEN_BEECH_WALL_HANGING_SIGN.identifier().getPath(), () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.GOLDEN_BEECH, BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_HANGING_SIGN.get()).setId(Keys.GOLDEN_BEECH_WALL_HANGING_SIGN).overrideLootTable(GOLDEN_BEECH_HANGING_SIGN.get().getLootTable())));
	public static final DeferredBlock<CeilingHangingSignBlock> COPPER_PINE_HANGING_SIGN = BLOCKS.register(Keys.COPPER_PINE_HANGING_SIGN.identifier().getPath(), () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.COPPER_PINE, COPPER_PINE_SIGN_MATERIAL.setId(Keys.COPPER_PINE_HANGING_SIGN)));
	public static final DeferredBlock<WallHangingSignBlock> COPPER_PINE_WALL_HANGING_SIGN = BLOCKS.register(Keys.COPPER_PINE_WALL_HANGING_SIGN.identifier().getPath(), () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.COPPER_PINE, BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_HANGING_SIGN.get()).setId(Keys.COPPER_PINE_WALL_HANGING_SIGN).overrideLootTable(COPPER_PINE_HANGING_SIGN.get().getLootTable())));
	public static final DeferredBlock<CeilingHangingSignBlock> LAPIS_ROBINIA_HANGING_SIGN = BLOCKS.register(Keys.LAPIS_ROBINIA_HANGING_SIGN.identifier().getPath(), () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.LAPIS_ROBINIA, COPPER_PINE_SIGN_MATERIAL.setId(Keys.LAPIS_ROBINIA_HANGING_SIGN)));
	public static final DeferredBlock<WallHangingSignBlock> LAPIS_ROBINIA_WALL_HANGING_SIGN = BLOCKS.register(Keys.LAPIS_ROBINIA_WALL_HANGING_SIGN.identifier().getPath(), () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.LAPIS_ROBINIA, BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_HANGING_SIGN.get()).setId(Keys.LAPIS_ROBINIA_WALL_HANGING_SIGN).overrideLootTable(LAPIS_ROBINIA_HANGING_SIGN.get().getLootTable())));
	public static final DeferredBlock<CeilingHangingSignBlock> SHADOW_PINE_HANGING_SIGN = BLOCKS.register(Keys.SHADOW_PINE_HANGING_SIGN.identifier().getPath(), () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.SHADOW_PINE, SHADOW_PINE_SIGN_MATERIAL.setId(Keys.SHADOW_PINE_HANGING_SIGN)));
	public static final DeferredBlock<WallHangingSignBlock> SHADOW_PINE_WALL_HANGING_SIGN = BLOCKS.register(Keys.SHADOW_PINE_WALL_HANGING_SIGN.identifier().getPath(), () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.SHADOW_PINE, BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_HANGING_SIGN.get()).setId(Keys.SHADOW_PINE_WALL_HANGING_SIGN).overrideLootTable(SHADOW_PINE_HANGING_SIGN.get().getLootTable())));
	public static final DeferredBlock<CeilingHangingSignBlock> STELLAR_JUNGLE_TREE_HANGING_SIGN = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_HANGING_SIGN.identifier().getPath(), () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, SHADOW_PINE_SIGN_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_HANGING_SIGN)));
	public static final DeferredBlock<WallHangingSignBlock> STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN.identifier().getPath(), () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_HANGING_SIGN.get()).setId(Keys.STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN).overrideLootTable(STELLAR_JUNGLE_TREE_HANGING_SIGN.get().getLootTable())));
	public static final DeferredBlock<CeilingHangingSignBlock> SKY_CACTUS_FIBER_HANGING_SIGN = BLOCKS.register(Keys.SKY_CACTUS_FIBER_HANGING_SIGN.identifier().getPath(), () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, SKY_CACTUS_FIBER_SIGN_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_HANGING_SIGN)));
	public static final DeferredBlock<WallHangingSignBlock> SKY_CACTUS_FIBER_WALL_HANGING_SIGN = BLOCKS.register(Keys.SKY_CACTUS_FIBER_WALL_HANGING_SIGN.identifier().getPath(), () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_HANGING_SIGN.get()).setId(Keys.SKY_CACTUS_FIBER_WALL_HANGING_SIGN).overrideLootTable(SKY_CACTUS_FIBER_HANGING_SIGN.get().getLootTable())));
	public static final DeferredBlock<CeilingHangingSignBlock> GRAY_SHROOM_HANGING_SIGN = BLOCKS.register(Keys.GRAY_SHROOM_HANGING_SIGN.identifier().getPath(), () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.GRAY_SHROOM, SKY_CACTUS_FIBER_SIGN_MATERIAL.setId(Keys.GRAY_SHROOM_HANGING_SIGN)));
	public static final DeferredBlock<WallHangingSignBlock> GRAY_SHROOM_WALL_HANGING_SIGN = BLOCKS.register(Keys.GRAY_SHROOM_WALL_HANGING_SIGN.identifier().getPath(), () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.GRAY_SHROOM, BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_HANGING_SIGN.get()).setId(Keys.GRAY_SHROOM_WALL_HANGING_SIGN).overrideLootTable(GRAY_SHROOM_HANGING_SIGN.get().getLootTable())));

	//crafting tables
	public static final DeferredBlock<CraftingTableBlock> AERIAL_TREE_CRAFTING_TABLE = BLOCKS.register(Keys.AERIAL_TREE_CRAFTING_TABLE.identifier().getPath(), () -> new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_CRAFTING_TABLE)));
	public static final DeferredBlock<CraftingTableBlock> GOLDEN_BEECH_CRAFTING_TABLE = BLOCKS.register(Keys.GOLDEN_BEECH_CRAFTING_TABLE.identifier().getPath(), () -> new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_CRAFTING_TABLE)));
	public static final DeferredBlock<CraftingTableBlock> COPPER_PINE_CRAFTING_TABLE = BLOCKS.register(Keys.COPPER_PINE_CRAFTING_TABLE.identifier().getPath(), () -> new AerialHellCraftingTableBlock(COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_CRAFTING_TABLE)));
	public static final DeferredBlock<CraftingTableBlock> LAPIS_ROBINIA_CRAFTING_TABLE = BLOCKS.register(Keys.LAPIS_ROBINIA_CRAFTING_TABLE.identifier().getPath(), () -> new AerialHellCraftingTableBlock(COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_CRAFTING_TABLE)));
	public static final DeferredBlock<CraftingTableBlock> SHADOW_PINE_CRAFTING_TABLE = BLOCKS.register(Keys.SHADOW_PINE_CRAFTING_TABLE.identifier().getPath(), () -> new AerialHellCraftingTableBlock(SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_CRAFTING_TABLE)));
	public static final DeferredBlock<CraftingTableBlock> STELLAR_JUNGLE_TREE_CRAFTING_TABLE = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_CRAFTING_TABLE.identifier().getPath(), () -> new AerialHellCraftingTableBlock(COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_CRAFTING_TABLE)));
	public static final DeferredBlock<CraftingTableBlock> SKY_CACTUS_FIBER_CRAFTING_TABLE = BLOCKS.register(Keys.SKY_CACTUS_FIBER_CRAFTING_TABLE.identifier().getPath(), () -> new AerialHellCraftingTableBlock(SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_CRAFTING_TABLE)));
	public static final DeferredBlock<CraftingTableBlock> GRAY_SHROOM_CRAFTING_TABLE = BLOCKS.register(Keys.GRAY_SHROOM_CRAFTING_TABLE.identifier().getPath(), () -> new AerialHellCraftingTableBlock(SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_CRAFTING_TABLE)));

	//barrels
	public static final DeferredBlock<AerialHellBarrelBlock> AERIAL_TREE_BARREL = BLOCKS.register(Keys.AERIAL_TREE_BARREL.identifier().getPath(), () -> new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_BARREL)));
	public static final DeferredBlock<AerialHellBarrelBlock> GOLDEN_BEECH_BARREL = BLOCKS.register(Keys.GOLDEN_BEECH_BARREL.identifier().getPath(), () -> new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_BARREL)));
	public static final DeferredBlock<AerialHellBarrelBlock> COPPER_PINE_BARREL = BLOCKS.register(Keys.COPPER_PINE_BARREL.identifier().getPath(), () -> new AerialHellBarrelBlock(COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_BARREL)));
	public static final DeferredBlock<AerialHellBarrelBlock> LAPIS_ROBINIA_BARREL = BLOCKS.register(Keys.LAPIS_ROBINIA_BARREL.identifier().getPath(), () -> new AerialHellBarrelBlock(COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_BARREL)));
	public static final DeferredBlock<AerialHellBarrelBlock> SHADOW_PINE_BARREL = BLOCKS.register(Keys.SHADOW_PINE_BARREL.identifier().getPath(), () -> new AerialHellBarrelBlock(SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_BARREL)));
	public static final DeferredBlock<AerialHellBarrelBlock> STELLAR_JUNGLE_TREE_BARREL = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_BARREL.identifier().getPath(), () -> new AerialHellBarrelBlock(COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_BARREL)));
	public static final DeferredBlock<AerialHellBarrelBlock> SKY_CACTUS_FIBER_BARREL = BLOCKS.register(Keys.SKY_CACTUS_FIBER_BARREL.identifier().getPath(), () -> new AerialHellBarrelBlock(SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_BARREL)));
	public static final DeferredBlock<AerialHellBarrelBlock> GRAY_SHROOM_BARREL = BLOCKS.register(Keys.GRAY_SHROOM_BARREL.identifier().getPath(), () -> new AerialHellBarrelBlock(SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_BARREL)));

	//composters
	public static final DeferredBlock<ComposterBlock> AERIAL_TREE_COMPOSTER = BLOCKS.register(Keys.AERIAL_TREE_COMPOSTER.identifier().getPath(), () -> new ComposterBlock(AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_COMPOSTER)));
	public static final DeferredBlock<ComposterBlock> GOLDEN_BEECH_COMPOSTER = BLOCKS.register(Keys.GOLDEN_BEECH_COMPOSTER.identifier().getPath(), () -> new ComposterBlock(AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_COMPOSTER)));
	public static final DeferredBlock<ComposterBlock> COPPER_PINE_COMPOSTER = BLOCKS.register(Keys.COPPER_PINE_COMPOSTER.identifier().getPath(), () -> new ComposterBlock(COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_COMPOSTER)));
	public static final DeferredBlock<ComposterBlock> LAPIS_ROBINIA_COMPOSTER = BLOCKS.register(Keys.LAPIS_ROBINIA_COMPOSTER.identifier().getPath(), () -> new ComposterBlock(COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_COMPOSTER)));
	public static final DeferredBlock<ComposterBlock> SHADOW_PINE_COMPOSTER = BLOCKS.register(Keys.SHADOW_PINE_COMPOSTER.identifier().getPath(), () -> new ComposterBlock(SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_COMPOSTER)));
	public static final DeferredBlock<ComposterBlock> STELLAR_JUNGLE_TREE_COMPOSTER = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_COMPOSTER.identifier().getPath(), () -> new ComposterBlock(COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_COMPOSTER)));
	public static final DeferredBlock<ComposterBlock> SKY_CACTUS_FIBER_COMPOSTER = BLOCKS.register(Keys.SKY_CACTUS_FIBER_COMPOSTER.identifier().getPath(), () -> new ComposterBlock(SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_COMPOSTER)));
	public static final DeferredBlock<ComposterBlock> GRAY_SHROOM_COMPOSTER = BLOCKS.register(Keys.GRAY_SHROOM_COMPOSTER.identifier().getPath(), () -> new ComposterBlock(SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_COMPOSTER)));

	//decorative
	public static final DeferredBlock<RotatedPillarBlock> AERIAL_TREE_VINE_ROPE_SPOOL = BLOCKS.register(Keys.AERIAL_TREE_VINE_ROPE_SPOOL.identifier().getPath(), () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().setId(Keys.AERIAL_TREE_VINE_ROPE_SPOOL).noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> GOLDEN_BEECH_VINE_ROPE_SPOOL = BLOCKS.register(Keys.GOLDEN_BEECH_VINE_ROPE_SPOOL.identifier().getPath(), () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().setId(Keys.GOLDEN_BEECH_VINE_ROPE_SPOOL).noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> COPPER_PINE_VINE_ROPE_SPOOL = BLOCKS.register(Keys.COPPER_PINE_VINE_ROPE_SPOOL.identifier().getPath(), () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().setId(Keys.COPPER_PINE_VINE_ROPE_SPOOL).noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> LAPIS_ROBINIA_VINE_ROPE_SPOOL = BLOCKS.register(Keys.LAPIS_ROBINIA_VINE_ROPE_SPOOL.identifier().getPath(), () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().setId(Keys.LAPIS_ROBINIA_VINE_ROPE_SPOOL).noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> SHADOW_PINE_VINE_ROPE_SPOOL = BLOCKS.register(Keys.SHADOW_PINE_VINE_ROPE_SPOOL.identifier().getPath(), () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().setId(Keys.SHADOW_PINE_VINE_ROPE_SPOOL).noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL = BLOCKS.register(Keys.STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL.identifier().getPath(), () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().setId(Keys.STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL).noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> SKY_CACTUS_FIBER_VINE_ROPE_SPOOL = BLOCKS.register(Keys.SKY_CACTUS_FIBER_VINE_ROPE_SPOOL.identifier().getPath(), () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().setId(Keys.SKY_CACTUS_FIBER_VINE_ROPE_SPOOL).noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final DeferredBlock<RotatedPillarBlock> GRAY_SHROOM_VINE_ROPE_SPOOL = BLOCKS.register(Keys.GRAY_SHROOM_VINE_ROPE_SPOOL.identifier().getPath(), () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().setId(Keys.GRAY_SHROOM_VINE_ROPE_SPOOL).noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));

	//fluids
	public static final DeferredBlock<LiquidBlock> LIQUID_OF_THE_GODS = BLOCKS.register(Keys.LIQUID_OF_THE_GODS.identifier().getPath(), () -> new AerialHellFluidBlock(AerialHellFluids.LIQUID_OF_THE_GODS_SOURCE.get(), BlockBehaviour.Properties.of().setId(Keys.LIQUID_OF_THE_GODS).replaceable().lightLevel((state) -> 8)));

	public static class Keys
	{
		//portal
		public static final ResourceKey<Block> AERIAL_HELL_PORTAL = createKey("aerial_hell_portal");
		public static final ResourceKey<Block> STELLAR_PORTAL_FRAME_BLOCK = createKey("stellar_portal_frame_block");
		public static final ResourceKey<Block> STELLAR_PORTAL_FRAME_ORE = createKey("stellar_portal_frame_ore");
		public static final ResourceKey<Block> DEEPSLATE_STELLAR_PORTAL_FRAME_ORE = createKey("deepslate_stellar_portal_frame_ore");

		//torch
		public static final ResourceKey<Block> CRYSTALLIZED_WALL_TORCH = createKey("crystallized_wall_torch");
		public static final ResourceKey<Block> CRYSTALLIZED_TORCH = createKey("crystallized_torch");
		public static final ResourceKey<Block> FLUORITE_WALL_TORCH = createKey("fluorite_wall_torch");
		public static final ResourceKey<Block> FLUORITE_TORCH = createKey("fluorite_torch");
		public static final ResourceKey<Block> VOLUCITE_WALL_TORCH = createKey("volucite_wall_torch");
		public static final ResourceKey<Block> VOLUCITE_TORCH = createKey("volucite_torch");
		public static final ResourceKey<Block> SHADOW_WALL_TORCH = createKey("shadow_wall_torch");
		public static final ResourceKey<Block> SHADOW_TORCH = createKey("shadow_torch");

		//lanterns
		public static final ResourceKey<Block> FLUORITE_LANTERN = createKey("fluorite_lantern");
		public static final ResourceKey<Block> CRYSTALLIZED_LANTERN = createKey("crystallized_lantern");
		public static final ResourceKey<Block> RUBY_LANTERN = createKey("ruby_lantern");
		public static final ResourceKey<Block> RUBY_CRYSTALLIZED_LANTERN = createKey("ruby_crystallized_lantern");
		public static final ResourceKey<Block> RUBY_FLUORITE_LANTERN = createKey("ruby_fluorite_lantern");
		public static final ResourceKey<Block> VOLUCITE_LANTERN = createKey("volucite_lantern");
		public static final ResourceKey<Block> VOLUCITE_CRYSTALLIZED_LANTERN = createKey("volucite_crystallized_lantern");
		public static final ResourceKey<Block> VOLUCITE_FLUORITE_LANTERN = createKey("volucite_fluorite_lantern");
		public static final ResourceKey<Block> LUNATIC_LANTERN = createKey("lunatic_lantern");
		public static final ResourceKey<Block> SHADOW_LANTERN = createKey("shadow_lantern");

		//chains
		public static final ResourceKey<Block> RUBY_CHAIN = createKey("ruby_chain");
		public static final ResourceKey<Block> VOLUCITE_CHAIN = createKey("volucite_chain");
		public static final ResourceKey<Block> LUNATIC_CHAIN = createKey("lunatic_chain");
		public static final ResourceKey<Block> SHADOW_CHAIN = createKey("shadow_chain");

		//grass & dirt
		public static final ResourceKey<Block> STELLAR_GRASS_BLOCK = createKey("stellar_grass_block");
		public static final ResourceKey<Block> CHISELED_STELLAR_GRASS_BLOCK = createKey("chiseled_stellar_grass_block");
		public static final ResourceKey<Block> STELLAR_DIRT = createKey("stellar_dirt");
		public static final ResourceKey<Block> STELLAR_COARSE_DIRT = createKey("stellar_coarse_dirt");
		public static final ResourceKey<Block> STELLAR_FARMLAND = createKey("stellar_farmland");
		public static final ResourceKey<Block> STELLAR_DIRT_PATH = createKey("stellar_dirt_path");
		public static final ResourceKey<Block> STELLAR_PODZOL = createKey("stellar_podzol");
		public static final ResourceKey<Block> STELLAR_CRYSTAL_PODZOL = createKey("stellar_crystal_podzol");
		public static final ResourceKey<Block> CHISELED_STELLAR_DIRT = createKey("chiseled_stellar_dirt");
		public static final ResourceKey<Block> SHADOW_GRASS_BLOCK = createKey("shadow_grass_block");

		//slippery sand
		public static final ResourceKey<Block> SLIPPERY_SAND = createKey("slippery_sand");
		public static final ResourceKey<Block> SLIPPERY_SAND_STONE = createKey("slippery_sand_stone");
		public static final ResourceKey<Block> SLIPPERY_SAND_STONE_BRICKS = createKey("slippery_sand_stone_bricks");
		public static final ResourceKey<Block> CUT_SLIPPERY_SAND_STONE = createKey("cut_slippery_sand_stone");
		public static final ResourceKey<Block> CRACKED_SLIPPERY_SAND_STONE_BRICKS = createKey("cracked_slippery_sand_stone_bricks");

		//giant root
		public static final ResourceKey<Block> GIANT_ROOT = createKey("giant_root");

		//aerial_tree
		public static final ResourceKey<Block> AERIAL_TREE_LOG = createKey("aerial_tree_log");
		public static final ResourceKey<Block> STRIPPED_AERIAL_TREE_LOG = createKey("stripped_aerial_tree_log");
		public static final ResourceKey<Block> AERIAL_TREE_WOOD = createKey("aerial_tree_wood");
		public static final ResourceKey<Block> STRIPPED_AERIAL_TREE_WOOD = createKey("stripped_aerial_tree_wood");
		public static final ResourceKey<Block> AERIAL_TREE_LEAVES = createKey("aerial_tree_leaves");
		public static final ResourceKey<Block> AERIAL_TREE_PLANKS = createKey("aerial_tree_planks");
		public static final ResourceKey<Block> CHISELED_AERIAL_TREE_PLANKS = createKey("chiseled_aerial_tree_planks");
		public static final ResourceKey<Block> AERIAL_TREE_BOOKSHELF = createKey("aerial_tree_bookshelf");
		public static final ResourceKey<Block> AERIAL_TREE_SAPLING = createKey("aerial_tree_sapling");

		//petrified aerial tree
		public static final ResourceKey<Block> PETRIFIED_AERIAL_TREE_LOG = createKey("petrified_aerial_tree_log");

		//golden beech
		public static final ResourceKey<Block> GOLDEN_BEECH_LOG = createKey("golden_beech_log");
		public static final ResourceKey<Block> STRIPPED_GOLDEN_BEECH_LOG = createKey("stripped_golden_beech_log");
		public static final ResourceKey<Block> GOLDEN_BEECH_WOOD = createKey("golden_beech_wood");
		public static final ResourceKey<Block> STRIPPED_GOLDEN_BEECH_WOOD = createKey("stripped_golden_beech_wood");
		public static final ResourceKey<Block> GOLDEN_BEECH_PLANKS = createKey("golden_beech_planks");
		public static final ResourceKey<Block> CHISELED_GOLDEN_BEECH_PLANKS = createKey("chiseled_golden_beech_planks");
		public static final ResourceKey<Block> GOLDEN_BEECH_LEAVES = createKey("golden_beech_leaves");
		public static final ResourceKey<Block> GOLDEN_BEECH_BOOKSHELF = createKey("golden_beech_bookshelf");
		public static final ResourceKey<Block> GOLDEN_BEECH_SAPLING = createKey("golden_beech_sapling");

		//cropper pine
		public static final ResourceKey<Block> COPPER_PINE_LOG = createKey("copper_pine_log");
		public static final ResourceKey<Block> STRIPPED_COPPER_PINE_LOG = createKey("stripped_copper_pine_log");
		public static final ResourceKey<Block> COPPER_PINE_WOOD = createKey("copper_pine_wood");
		public static final ResourceKey<Block> STRIPPED_COPPER_PINE_WOOD = createKey("stripped_copper_pine_wood");
		public static final ResourceKey<Block> COPPER_PINE_PLANKS = createKey("copper_pine_planks");
		public static final ResourceKey<Block> COPPER_PINE_LEAVES = createKey("copper_pine_leaves");
		public static final ResourceKey<Block> COPPER_PINE_BOOKSHELF = createKey("copper_pine_bookshelf");
		public static final ResourceKey<Block> COPPER_PINE_SAPLING = createKey("copper_pine_sapling");

		//lapis robinia
		public static final ResourceKey<Block> LAPIS_ROBINIA_LOG = createKey("lapis_robinia_log");
		public static final ResourceKey<Block> ENCHANTED_LAPIS_ROBINIA_LOG = createKey("enchanted_lapis_robinia_log");
		public static final ResourceKey<Block> STRIPPED_LAPIS_ROBINIA_LOG = createKey("stripped_lapis_robinia_log");
		public static final ResourceKey<Block> LAPIS_ROBINIA_WOOD = createKey("lapis_robinia_wood");
		public static final ResourceKey<Block> STRIPPED_LAPIS_ROBINIA_WOOD = createKey("stripped_lapis_robinia_wood");
		public static final ResourceKey<Block> LAPIS_ROBINIA_LEAVES = createKey("lapis_robinia_leaves");
		public static final ResourceKey<Block> LAPIS_ROBINIA_PLANKS = createKey("lapis_robinia_planks");
		public static final ResourceKey<Block> LAPIS_ROBINIA_BOOKSHELF = createKey("lapis_robinia_bookshelf");
		public static final ResourceKey<Block> LAPIS_ROBINIA_SAPLING = createKey("lapis_robinia_sapling");

		//shadow_pine
		public static final ResourceKey<Block> SHADOW_PINE_LOG = createKey("shadow_pine_log");
		public static final ResourceKey<Block> EYE_SHADOW_PINE_LOG = createKey("eye_shadow_pine_log");
		public static final ResourceKey<Block> STRIPPED_SHADOW_PINE_LOG = createKey("stripped_shadow_pine_log");
		public static final ResourceKey<Block> SHADOW_PINE_WOOD = createKey("shadow_pine_wood");
		public static final ResourceKey<Block> STRIPPED_SHADOW_PINE_WOOD = createKey("stripped_shadow_pine_wood");
		public static final ResourceKey<Block> SHADOW_PINE_LEAVES = createKey("shadow_pine_leaves");
		public static final ResourceKey<Block> PURPLE_SHADOW_PINE_LEAVES = createKey("purple_shadow_pine_leaves");
		public static final ResourceKey<Block> SHADOW_PINE_PLANKS = createKey("shadow_pine_planks");
		public static final ResourceKey<Block> SHADOW_PINE_BOOKSHELF = createKey("shadow_pine_bookshelf");
		public static final ResourceKey<Block> SHADOW_PINE_SAPLING = createKey("shadow_pine_sapling");
		public static final ResourceKey<Block> PURPLE_SHADOW_PINE_SAPLING = createKey("purple_shadow_pine_sapling");

		//stellar jungle tree
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_LOG = createKey("stellar_jungle_tree_log");
		public static final ResourceKey<Block> STRIPPED_STELLAR_JUNGLE_TREE_LOG = createKey("stripped_stellar_jungle_tree_log");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_WOOD = createKey("stellar_jungle_tree_wood");
		public static final ResourceKey<Block> STRIPPED_STELLAR_JUNGLE_TREE_WOOD = createKey("stripped_stellar_jungle_tree_wood");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_LEAVES = createKey("stellar_jungle_tree_leaves");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_PLANKS = createKey("stellar_jungle_tree_planks");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_BOOKSHELF = createKey("stellar_jungle_tree_bookshelf");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_SAPLING = createKey("stellar_jungle_tree_sapling");
		public static final ResourceKey<Block> DEAD_STELLAR_JUNGLE_TREE_LOG = createKey("dead_stellar_jungle_tree_log");

		//shroom
		public static final ResourceKey<Block> GIANT_CORTINARIUS_VIOLACEUS_STEM = createKey("giant_cortinarius_violaceus_stem");
		public static final ResourceKey<Block> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM = createKey("stripped_giant_cortinarius_violaceus_stem");
		public static final ResourceKey<Block> GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = createKey("giant_cortinarius_violaceus_bark_stem");
		public static final ResourceKey<Block> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = createKey("stripped_giant_cortinarius_violaceus_bark_stem");
		public static final ResourceKey<Block> GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK = createKey("giant_cortinarius_violaceus_cap_block");
		public static final ResourceKey<Block> GIANT_CORTINARIUS_VIOLACEUS_LIGHT = createKey("giant_cortinarius_violaceus_light");
		public static final ResourceKey<Block> CORTINARIUS_VIOLACEUS = createKey("cortinarius_violaceus");
		public static final ResourceKey<Block> GLOWING_BOLETUS = createKey("glowing_boletus");
		public static final ResourceKey<Block> TALL_GLOWING_BOLETUS = createKey("tall_glowing_boletus");
		public static final ResourceKey<Block> BLUE_MEANIE_CLUSTER = createKey("blue_meanie_cluster");
		public static final ResourceKey<Block> GIANT_ROOT_SHROOM = createKey("giant_root_shroom");

		public static final ResourceKey<Block> GIANT_VERDIGRIS_AGARIC_STEM = createKey("giant_verdigris_agaric_stem");
		public static final ResourceKey<Block> STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM = createKey("stripped_giant_verdigris_agaric_stem");
		public static final ResourceKey<Block> GIANT_VERDIGRIS_AGARIC_BARK_STEM = createKey("giant_verdigris_agaric_bark_stem");
		public static final ResourceKey<Block> STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM = createKey("stripped_giant_verdigris_agaric_bark_stem");
		public static final ResourceKey<Block> GIANT_VERDIGRIS_AGARIC_CAP_BLOCK = createKey("giant_verdigris_agaric_cap_block");
		public static final ResourceKey<Block> VERDIGRIS_AGARIC = createKey("verdigris_agaric");

		public static final ResourceKey<Block> GIANT_GANODERMA_APPLANATUM_BLOCK = createKey("giant_ganoderma_applanatum_block");

		public static final ResourceKey<Block> GRAY_SHROOM_PLANKS = createKey("gray_shroom_planks");
		public static final ResourceKey<Block> GRAY_SHROOM_BOOKSHELF = createKey("gray_shroom_bookshelf");

		//shadow corrupted / uncorrupted variants
		public static final ResourceKey<Block> SHADOW_AERIAL_TREE_LOG = createKey("shadow_aerial_tree_log");
		public static final ResourceKey<Block> SHADOW_GOLDEN_BEECH_LOG = createKey("shadow_golden_beech_log");
		public static final ResourceKey<Block> SHADOW_COPPER_PINE_LOG = createKey("shadow_copper_pine_log");
		public static final ResourceKey<Block> SHADOW_LAPIS_ROBINIA_LOG = createKey("shadow_lapis_robinia_log");
		public static final ResourceKey<Block> SHADOW_STELLAR_JUNGLE_TREE_LOG = createKey("shadow_stellar_jungle_tree_log");
		public static final ResourceKey<Block> HOLLOW_SHADOW_PINE_LOG = createKey("hollow_shadow_pine_log");
		public static final ResourceKey<Block> SHADOW_AERIAL_TREE_LEAVES = createKey("shadow_aerial_tree_leaves");
		public static final ResourceKey<Block> SHADOW_GOLDEN_BEECH_LEAVES = createKey("shadow_golden_beech_leaves");
		public static final ResourceKey<Block> SHADOW_COPPER_PINE_LEAVES = createKey("shadow_copper_pine_leaves");
		public static final ResourceKey<Block> SHADOW_LAPIS_ROBINIA_LEAVES = createKey("shadow_lapis_robinia_leaves");
		public static final ResourceKey<Block> SHADOW_STELLAR_JUNGLE_TREE_LEAVES = createKey("shadow_stellar_jungle_tree_leaves");
		public static final ResourceKey<Block> HOLLOW_SHADOW_PINE_LEAVES = createKey("hollow_shadow_pine_leaves");
		public static final ResourceKey<Block> HOLLOW_PURPLE_SHADOW_PINE_LEAVES = createKey("hollow_purple_shadow_pine_leaves");

		//ladder
		public static final ResourceKey<Block> SKY_LADDER = createKey("sky_ladder");

		//natural blocks and items
		public static final ResourceKey<Block> STELLAR_STONE = createKey("stellar_stone");
		public static final ResourceKey<Block> STELLAR_COBBLESTONE = createKey("stellar_cobblestone");
		public static final ResourceKey<Block> STELLAR_STONE_BRICKS = createKey("stellar_stone_bricks");
		public static final ResourceKey<Block> MOSSY_STELLAR_STONE = createKey("mossy_stellar_stone");
		public static final ResourceKey<Block> MOSSY_STELLAR_COBBLESTONE = createKey("mossy_stellar_cobblestone");
		public static final ResourceKey<Block> STELLAR_CLAY = createKey("stellar_clay");
		public static final ResourceKey<Block> GLAUCOPHANITE = createKey("glaucophanite");
		public static final ResourceKey<Block> POLISHED_GLAUCOPHANITE = createKey("polished_glaucophanite");
		public static final ResourceKey<Block> SHADOW_STONE = createKey("shadow_stone");

		//crystal
		public static final ResourceKey<Block> CRYSTAL_BLOCK = createKey("crystal_block");
		public static final ResourceKey<Block> CRYSTAL_BRICKS = createKey("crystal_bricks");
		public static final ResourceKey<Block> CRYSTAL_BRICKS_SLAB = createKey("crystal_bricks_slab");
		public static final ResourceKey<Block> CRYSTAL_BRICKS_STAIRS = createKey("crystal_bricks_stairs");
		public static final ResourceKey<Block> CRYSTAL_BRICKS_WALL = createKey("crystal_bricks_wall");
		public static final ResourceKey<Block> STELLAR_STONE_CRYSTAL_BLOCK = createKey("stellar_stone_crystal_block");
		public static final ResourceKey<Block> SHADOW_CRYSTAL_BLOCK = createKey("shadow_crystal_block");
		public static final ResourceKey<Block> CRYSTALLIZED_LEAVES = createKey("crystallized_leaves");
		public static final ResourceKey<Block> CRYSTALLIZED_FIRE = createKey("crystallized_fire");

		//glass and glass pane
		public static final ResourceKey<Block> SLIPPERY_SAND_GLASS = createKey("slippery_sand_glass");
		public static final ResourceKey<Block> RED_SLIPPERY_SAND_GLASS = createKey("red_slippery_sand_glass");
		public static final ResourceKey<Block> BLACK_SLIPPERY_SAND_GLASS = createKey("black_slippery_sand_glass");
		public static final ResourceKey<Block> BLUE_SLIPPERY_SAND_GLASS = createKey("blue_slippery_sand_glass");
		public static final ResourceKey<Block> GREEN_SLIPPERY_SAND_GLASS = createKey("green_slippery_sand_glass");
		public static final ResourceKey<Block> SLIPPERY_SAND_GLASS_PANE = createKey("slippery_sand_glass_pane");
		public static final ResourceKey<Block> RED_SLIPPERY_SAND_GLASS_PANE = createKey("red_slippery_sand_glass_pane");
		public static final ResourceKey<Block> BLACK_SLIPPERY_SAND_GLASS_PANE = createKey("black_slippery_sand_glass_pane");
		public static final ResourceKey<Block> BLUE_SLIPPERY_SAND_GLASS_PANE = createKey("blue_slippery_sand_glass_pane");
		public static final ResourceKey<Block> GREEN_SLIPPERY_SAND_GLASS_PANE = createKey("green_slippery_sand_glass_pane");

		//ghost boat
		public static final ResourceKey<Block> GHOST_BOAT_PLANKS = createKey("ghost_boat_planks");
		public static final ResourceKey<Block> GHOST_BOAT_LOG = createKey("ghost_boat_log");
		public static final ResourceKey<Block> GHOST_BOAT_WOOD = createKey("ghost_boat_wood");
		public static final ResourceKey<Block> GHOST_BOAT_SLAB = createKey("ghost_boat_slab");
		public static final ResourceKey<Block> GHOST_BOAT_STAIRS = createKey("ghost_boat_stairs");
		public static final ResourceKey<Block> GHOST_BOAT_FENCE = createKey("ghost_boat_fence");
		public static final ResourceKey<Block> GHOST_BOAT_GATE = createKey("ghost_boat_gate");
		public static final ResourceKey<Block> GHOST_BOAT_DOOR = createKey("ghost_boat_door");
		public static final ResourceKey<Block> GHOST_BOAT_TRAPDOOR = createKey("ghost_boat_trapdoor");
		public static final ResourceKey<Block> GHOST_BOAT_CHEST = createKey("ghost_boat_chest");
		public static final ResourceKey<Block> GHOST_BOAT_WOOL = createKey("ghost_boat_wool");
		public static final ResourceKey<Block> GHOST_STELLAR_COBBLESTONE = createKey("ghost_stellar_cobblestone");
		public static final ResourceKey<Block> GHOST_RUBY_BLOCK = createKey("ghost_ruby_block");
		public static final ResourceKey<Block> GHOST_FLUORITE_BLOCK = createKey("ghost_fluorite_block");
		public static final ResourceKey<Block> GHOST_AZURITE_BLOCK = createKey("ghost_azurite_block");
		public static final ResourceKey<Block> GHOST_GOLD_BLOCK = createKey("ghost_gold_block");
		public static final ResourceKey<Block> GHOST_BOAT_BARREL = createKey("ghost_boat_barrel");
		public static final ResourceKey<Block> GHOST_BOAT_CRAFTING_TABLE = createKey("ghost_boat_crafting_table");
		public static final ResourceKey<Block> GHOST_BOAT_VINE_ROPE_SPOOL = createKey("ghost_boat_vine_rope_spool");
		public static final ResourceKey<Block> GHOST_LANTERN = createKey("ghost_lantern");

		//other condition condition blocks
		public static final ResourceKey<Block> INTANGIBLE_TEMPORARY_BLOCK = createKey("intangible_temporary_block");

		//reactors
		public static final ResourceKey<Block> WEAK_LIGHT_REACTOR = createKey("weak_light_reactor");
		public static final ResourceKey<Block> HIGH_POWER_LIGHT_REACTOR = createKey("high_power_light_reactor");
		public static final ResourceKey<Block> WEAK_SHADOW_REACTOR = createKey("weak_shadow_reactor");
		public static final ResourceKey<Block> HIGH_POWER_SHADOW_REACTOR = createKey("high_power_shadow_reactor");

		public static final ResourceKey<Block> BROKEN_WEAK_LIGHT_REACTOR = createKey("broken_weak_light_reactor");
		public static final ResourceKey<Block> BROKEN_HIGH_POWER_LIGHT_REACTOR = createKey("broken_high_power_light_reactor");
		public static final ResourceKey<Block> BROKEN_WEAK_SHADOW_REACTOR = createKey("broken_weak_shadow_reactor");
		public static final ResourceKey<Block> BROKEN_HIGH_POWER_SHADOW_REACTOR = createKey("broken_high_power_shadow_reactor");

		//solid_ethers
		public static final ResourceKey<Block> WHITE_SOLID_ETHER = createKey("white_solid_ether");
		public static final ResourceKey<Block> BLUE_SOLID_ETHER = createKey("blue_solid_ether");
		public static final ResourceKey<Block> GOLDEN_SOLID_ETHER = createKey("golden_solid_ether");
		public static final ResourceKey<Block> GREEN_SOLID_ETHER = createKey("green_solid_ether");
		public static final ResourceKey<Block> PURPLE_SOLID_ETHER = createKey("purple_solid_ether");

		//dungeons blocks
		public static final ResourceKey<Block> MUD_BRICKS = createKey("mud_bricks");
		public static final ResourceKey<Block> CRACKED_MUD_BRICKS = createKey("cracked_mud_bricks");
		public static final ResourceKey<Block> MOSSY_MUD_BRICKS = createKey("mossy_mud_bricks");
		public static final ResourceKey<Block> CHISELED_MUD_BRICKS = createKey("chiseled_mud_bricks");
		public static final ResourceKey<Block> LIGHT_MUD_BRICKS = createKey("light_mud_bricks");
		public static final ResourceKey<Block> CRACKED_LIGHT_MUD_BRICKS = createKey("cracked_light_mud_bricks");
		public static final ResourceKey<Block> LUNATIC_STONE = createKey("lunatic_stone");
		public static final ResourceKey<Block> DARK_LUNATIC_STONE = createKey("dark_lunatic_stone");
		public static final ResourceKey<Block> ROOF_LUNATIC_STONE = createKey("roof_lunatic_stone");
		public static final ResourceKey<Block> CRACKED_LUNATIC_STONE = createKey("cracked_lunatic_stone");
		public static final ResourceKey<Block> CHISELED_LUNATIC_STONE = createKey("chiseled_lunatic_stone");
		public static final ResourceKey<Block> LIGHT_LUNATIC_STONE = createKey("light_lunatic_stone");
		public static final ResourceKey<Block> ROOF_LIGHT_LUNATIC_STONE = createKey("roof_light_lunatic_stone");
		public static final ResourceKey<Block> CRACKED_LIGHT_LUNATIC_STONE = createKey("cracked_light_lunatic_stone");
		public static final ResourceKey<Block> SHADOW_CATACOMBS_BRICKS = createKey("shadow_catacombs_bricks");
		public static final ResourceKey<Block> CRACKED_SHADOW_CATACOMBS_BRICKS = createKey("cracked_shadow_catacombs_bricks");
		public static final ResourceKey<Block> MOSSY_SHADOW_CATACOMBS_BRICKS = createKey("mossy_shadow_catacombs_bricks");
		public static final ResourceKey<Block> CHISELED_SHADOW_CATACOMBS_BRICKS = createKey("chiseled_shadow_catacombs_bricks");
		public static final ResourceKey<Block> BONE_SHADOW_CATACOMBS_BRICKS = createKey("bone_shadow_catacombs_bricks");
		public static final ResourceKey<Block> SKULL_SHADOW_CATACOMBS_BRICKS = createKey("skull_shadow_catacombs_bricks");
		public static final ResourceKey<Block> LIGHT_SHADOW_CATACOMBS_BRICKS = createKey("light_shadow_catacombs_bricks");
		public static final ResourceKey<Block> CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS = createKey("cracked_light_shadow_catacombs_bricks");
		public static final ResourceKey<Block> GOLDEN_NETHER_BRICKS  = createKey("golden_nether_bricks");
		public static final ResourceKey<Block> CRACKED_GOLDEN_NETHER_BRICKS  = createKey("cracked_golden_nether_bricks");
		public static final ResourceKey<Block> CHISELED_GOLDEN_NETHER_BRICKS  = createKey("chiseled_golden_nether_bricks");
		public static final ResourceKey<Block> LIGHT_GOLDEN_NETHER_BRICKS = createKey("light_golden_nether_bricks");
		public static final ResourceKey<Block> CRACKED_LIGHT_GOLDEN_NETHER_BRICKS = createKey("cracked_light_golden_nether_bricks");
		public static final ResourceKey<Block> LUNATIC_PILLAR = createKey("lunatic_pillar");
		public static final ResourceKey<Block> LUNATIC_PILLAR_TOP = createKey("lunatic_pillar_top");
		public static final ResourceKey<Block> VOLUCITE_STONE = createKey("volucite_stone");
		public static final ResourceKey<Block> CRACKED_VOLUCITE_STONE = createKey("cracked_volucite_stone");
		public static final ResourceKey<Block> CHISELED_VOLUCITE_STONE = createKey("chiseled_volucite_stone");
		public static final ResourceKey<Block> LIGHT_VOLUCITE_STONE = createKey("light_volucite_stone");
		public static final ResourceKey<Block> CRACKED_LIGHT_VOLUCITE_STONE = createKey("cracked_light_volucite_stone");

		//dungeon cores
		public static final ResourceKey<Block> MUD_DUNGEON_CORE = createKey("mud_dungeon_core");
		public static final ResourceKey<Block> LUNATIC_DUNGEON_CORE = createKey("lunatic_dungeon_core");
		public static final ResourceKey<Block> SHADOW_CATACOMBS_DUNGEON_CORE = createKey("shadow_catacombs_dungeon_core");
		public static final ResourceKey<Block> GOLDEN_NETHER_DUNGEON_CORE = createKey("golden_nether_dungeon_core");
		public static final ResourceKey<Block> VOLUCITE_DUNGEON_CORE = createKey("volucite_dungeon_core");

		//dungeons slabs, stairs & walls
		public static final ResourceKey<Block> MUD_BRICKS_SLAB = createKey("mud_bricks_slab");
		public static final ResourceKey<Block> MUD_BRICKS_STAIRS = createKey("mud_bricks_stairs");
		public static final ResourceKey<Block> MUD_BRICKS_WALL = createKey("mud_bricks_wall");
		public static final ResourceKey<Block> CRACKED_MUD_BRICKS_SLAB = createKey("cracked_mud_bricks_slab");
		public static final ResourceKey<Block> CRACKED_MUD_BRICKS_STAIRS = createKey("cracked_mud_bricks_stairs");
		public static final ResourceKey<Block> CRACKED_MUD_BRICKS_WALL = createKey("cracked_mud_bricks_wall");
		public static final ResourceKey<Block> MOSSY_MUD_BRICKS_SLAB = createKey("mossy_mud_bricks_slab");
		public static final ResourceKey<Block> MOSSY_MUD_BRICKS_STAIRS = createKey("mossy_mud_bricks_stairs");
		public static final ResourceKey<Block> MOSSY_MUD_BRICKS_WALL = createKey("mossy_mud_bricks_wall");
		public static final ResourceKey<Block> VOLUCITE_STONE_SLAB = createKey("volucite_stone_slab");
		public static final ResourceKey<Block> VOLUCITE_STONE_STAIRS = createKey("volucite_stone_stairs");
		public static final ResourceKey<Block> VOLUCITE_STONE_WALL = createKey("volucite_stone_wall");
		public static final ResourceKey<Block> CRACKED_VOLUCITE_STONE_SLAB = createKey("cracked_volucite_stone_slab");
		public static final ResourceKey<Block> CRACKED_VOLUCITE_STONE_STAIRS = createKey("cracked_volucite_stone_stairs");
		public static final ResourceKey<Block> CRACKED_VOLUCITE_STONE_WALL = createKey("cracked_volucite_stone_wall");
		public static final ResourceKey<Block> LUNATIC_STONE_SLAB = createKey("lunatic_stone_slab");
		public static final ResourceKey<Block> LUNATIC_STONE_STAIRS = createKey("lunatic_stone_stairs");
		public static final ResourceKey<Block> LUNATIC_STONE_WALL = createKey("lunatic_stone_wall");
		public static final ResourceKey<Block> DARK_LUNATIC_STONE_SLAB = createKey("dark_lunatic_stone_slab");
		public static final ResourceKey<Block> DARK_LUNATIC_STONE_STAIRS = createKey("dark_lunatic_stone_stairs");
		public static final ResourceKey<Block> DARK_LUNATIC_STONE_WALL = createKey("dark_lunatic_stone_wall");
		public static final ResourceKey<Block> CRACKED_LUNATIC_STONE_SLAB = createKey("cracked_lunatic_stone_slab");
		public static final ResourceKey<Block> CRACKED_LUNATIC_STONE_STAIRS = createKey("cracked_lunatic_stone_stairs");
		public static final ResourceKey<Block> CRACKED_LUNATIC_STONE_WALL = createKey("cracked_lunatic_stone_wall");
		public static final ResourceKey<Block> SHADOW_CATACOMBS_BRICKS_SLAB = createKey("shadow_catacombs_bricks_slab");
		public static final ResourceKey<Block> SHADOW_CATACOMBS_BRICKS_STAIRS = createKey("shadow_catacombs_bricks_stairs");
		public static final ResourceKey<Block> SHADOW_CATACOMBS_BRICKS_WALL = createKey("shadow_catacombs_bricks_wall");
		public static final ResourceKey<Block> CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB = createKey("cracked_shadow_catacombs_bricks_slab");
		public static final ResourceKey<Block> CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS = createKey("cracked_shadow_catacombs_bricks_stairs");
		public static final ResourceKey<Block> CRACKED_SHADOW_CATACOMBS_BRICKS_WALL = createKey("cracked_shadow_catacombs_bricks_wall");
		public static final ResourceKey<Block> MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB = createKey("mossy_shadow_catacombs_bricks_slab");
		public static final ResourceKey<Block> MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS = createKey("mossy_shadow_catacombs_bricks_stairs");
		public static final ResourceKey<Block> MOSSY_SHADOW_CATACOMBS_BRICKS_WALL = createKey("mossy_shadow_catacombs_bricks_wall");
		public static final ResourceKey<Block> SHADOW_BARS = createKey("shadow_bars");
		public static final ResourceKey<Block> GOLDEN_NETHER_BRICKS_SLAB = createKey("golden_nether_bricks_slab");
		public static final ResourceKey<Block> GOLDEN_NETHER_BRICKS_STAIRS = createKey("golden_nether_bricks_stairs");
		public static final ResourceKey<Block> GOLDEN_NETHER_BRICKS_WALL = createKey("golden_nether_bricks_wall");
		public static final ResourceKey<Block> CRACKED_GOLDEN_NETHER_BRICKS_SLAB = createKey("cracked_golden_nether_bricks_slab");
		public static final ResourceKey<Block> CRACKED_GOLDEN_NETHER_BRICKS_STAIRS = createKey("cracked_golden_nether_bricks_stairs");
		public static final ResourceKey<Block> CRACKED_GOLDEN_NETHER_BRICKS_WALL = createKey("cracked_golden_nether_bricks_wall");

		//smoky quartz
		public static final ResourceKey<Block> SMOKY_QUARTZ_BLOCK = createKey("smoky_quartz_block");
		public static final ResourceKey<Block> SMOOTH_SMOKY_QUARTZ = createKey("smooth_smoky_quartz");
		public static final ResourceKey<Block> CHISELED_SMOKY_QUARTZ_BLOCK = createKey("chiseled_smoky_quartz_block");
		public static final ResourceKey<Block> SMOKY_QUARTZ_BRICKS = createKey("smoky_quartz_bricks");
		public static final ResourceKey<Block> SMOKY_QUARTZ_PILLAR = createKey("smoky_quartz_pillar");
		public static final ResourceKey<Block> SMOKY_QUARTZ_SLAB = createKey("smoky_quartz_slab");
		public static final ResourceKey<Block> SMOOTH_SMOKY_QUARTZ_SLAB = createKey("smooth_smoky_quartz_slab");
		public static final ResourceKey<Block> SMOKY_QUARTZ_STAIRS = createKey("smoky_quartz_stairs");
		public static final ResourceKey<Block> SMOOTH_SMOKY_QUARTZ_STAIRS = createKey("smooth_smoky_quartz_stairs");

		//dungeon trapped blocks
		public static final ResourceKey<Block> TRAPPED_MUD_BRICKS = createKey("trapped_mud_bricks");
		public static final ResourceKey<Block> TRAPPED_LIGHT_MUD_BRICKS = createKey("trapped_light_mud_bricks");
		public static final ResourceKey<Block> TRAPPED_LUNATIC_STONE = createKey("trapped_lunatic_stone");
		public static final ResourceKey<Block> TRAPPED_LIGHT_LUNATIC_STONE = createKey("trapped_light_lunatic_stone");
		public static final ResourceKey<Block> TRAPPED_GOLDEN_NETHER_BRICKS = createKey("trapped_golden_nether_bricks");
		public static final ResourceKey<Block> TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS = createKey("trapped_light_golden_nether_bricks");

		//dungeon other blocks, loots
		public static final ResourceKey<Block> MUD_BONE_BLOCK = createKey("mud_bone_block");
		public static final ResourceKey<Block> MUD_BONE_PILE_BLOCK = createKey("mud_bone_pile_block");
		public static final ResourceKey<Block> THORNY_COBWEB = createKey("thorny_cobweb");
		public static final ResourceKey<Block> AERIAL_NETHERRACK = createKey("aerial_netherrack");
		public static final ResourceKey<Block> AERIAL_NETHERRACK_SLAB = createKey("aerial_netherrack_slab");
		public static final ResourceKey<Block> AERIAL_NETHERRACK_STAIRS = createKey("aerial_netherrack_stairs");
		public static final ResourceKey<Block> AERIAL_NETHERRACK_WALL = createKey("aerial_netherrack_wall");

		//dungeon bookshelfs
		public static final ResourceKey<Block> MUD_BOOKSHELF = createKey("mud_bookshelf");
		public static final ResourceKey<Block> LUNATIC_BOOKSHELF = createKey("lunatic_bookshelf");
		public static final ResourceKey<Block> GOLDEN_NETHER_BOOKSHELF = createKey("golden_nether_bookshelf");
		public static final ResourceKey<Block> SHADOW_CATACOMBS_BOOKSHELF = createKey("shadow_catacombs_bookshelf");
		public static final ResourceKey<Block> VOLUCITE_BOOKSHELF = createKey("volucite_bookshelf");

		//glyph blocks
		public static final ResourceKey<Block> MUD_GLYPH_BLOCK = createKey("mud_glyph_block");
		public static final ResourceKey<Block> LUNATIC_GLYPH_BLOCK = createKey("lunatic_glyph_block");
		public static final ResourceKey<Block> GOLDEN_NETHER_PRISON_GLYPH_BLOCK = createKey("golden_nether_prison_glyph_block");
		public static final ResourceKey<Block> VOLUCITE_GLYPH_BLOCK = createKey("volucite_glyph_block");
		public static final ResourceKey<Block> SHADOW_CATACOMBS_GLYPH_BLOCK = createKey("shadow_catacombs_glyph_block");

		//trophies
		public static final ResourceKey<Block> MUD_CYCLE_MAGE_TROPHY = createKey("mud_cycle_mage_trophy");
		public static final ResourceKey<Block> LUNAR_PRIEST_TROPHY = createKey("lunar_priest_trophy");
		public static final ResourceKey<Block> LILITH_TROPHY = createKey("lilith_trophy");
		public static final ResourceKey<Block> CHAINED_GOD_TROPHY = createKey("chained_god_trophy");

		//ores
		public static final ResourceKey<Block> IRON_STELLAR_ORE = createKey("iron_stellar_ore");
		public static final ResourceKey<Block> GOLD_STELLAR_ORE = createKey("gold_stellar_ore");
		public static final ResourceKey<Block> DIAMOND_STELLAR_ORE = createKey("diamond_stellar_ore");
		public static final ResourceKey<Block> FLUORITE_ORE = createKey("fluorite_ore");
		public static final ResourceKey<Block> MAGMATIC_GEL_ORE = createKey("magmatic_gel_ore");
		public static final ResourceKey<Block> RUBY_ORE = createKey("ruby_ore");
		public static final ResourceKey<Block> AZURITE_ORE = createKey("azurite_ore");
		public static final ResourceKey<Block> VOLUCITE_ORE = createKey("volucite_ore");
		public static final ResourceKey<Block> OBSIDIAN_ORE = createKey("obsidian_ore");
		public static final ResourceKey<Block> SMOKY_QUARTZ_ORE = createKey("smoky_quartz_ore");

		public static final ResourceKey<Block> RAW_RUBY_BLOCK = createKey("raw_ruby_block");
		public static final ResourceKey<Block> RAW_AZURITE_BLOCK = createKey("raw_azurite_crystal_block");
		public static final ResourceKey<Block> RAW_VOLUCITE_BLOCK = createKey("raw_volucite_block");

		public static final ResourceKey<Block> FLUORITE_BLOCK = createKey("fluorite_block");
		public static final ResourceKey<Block> MAGMATIC_GEL_BLOCK = createKey("magmatic_gel_block");
		public static final ResourceKey<Block> RUBY_BLOCK = createKey("ruby_block");
		public static final ResourceKey<Block> AZURITE_BLOCK = createKey("azurite_block");
		public static final ResourceKey<Block> VOLUCITE_BLOCK = createKey("volucite_block");

		//legendary ores
		public static final ResourceKey<Block> ARSONIST_BLOCK = createKey("arsonist_block");
		public static final ResourceKey<Block> LUNATIC_CRYSTAL_BLOCK = createKey("lunatic_crystal_block");
		public static final ResourceKey<Block> CURSED_CRYSAL_BLOCK = createKey("cursed_crystal_block");

		//cactus
		public static final ResourceKey<Block> SKY_CACTUS = createKey("sky_cactus");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_PLANKS = createKey("sky_cactus_fiber_planks");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_BOOKSHELF = createKey("sky_cactus_fiber_bookshelf");
		public static final ResourceKey<Block> VIBRANT_SKY_CACTUS = createKey("vibrant_sky_cactus");
		public static final ResourceKey<Block> VIBRANT_SKY_CACTUS_FIBER_LANTERN = createKey("vibrant_sky_cactus_fiber_lantern");

		//bushes
		public static final ResourceKey<Block> AERIAL_BERRY_BUSH = createKey("aerial_berry_bush");
		public static final ResourceKey<Block> VIBRANT_AERIAL_BERRY_BUSH = createKey("vibrant_aerial_berry_bush");

		//crops
		public static final ResourceKey<Block> STELLAR_WHEAT = createKey("stellar_wheat");
		public static final ResourceKey<Block> BLUE_MEANIE_CROP = createKey("blue_meanie_crop");

		//vertical growing plants
		public static final ResourceKey<Block> CLIMBING_VINE = createKey("climbing_vine");
		public static final ResourceKey<Block> STELLAR_SUGAR_CANE = createKey("stellar_sugar_cane");

		//chorus like
		public static final ResourceKey<Block> FULL_MOON_PLANT = createKey("full_moon_plant");
		public static final ResourceKey<Block> FULL_MOON_FLOWER = createKey("full_moon_flower");

		//vines
		public static final ResourceKey<Block> GLOWING_STICK_FRUIT_VINES = createKey("glowing_stick_fruit_vines");
		public static final ResourceKey<Block> GLOWING_STICK_FRUIT_VINES_PLANT = createKey("glowing_stick_fruit_vines_plant");
		public static final ResourceKey<Block> BLOSSOMING_VINES = createKey("blossoming_vines");
		public static final ResourceKey<Block> BLOSSOMING_VINES_PLANT = createKey("blossoming_vines_plant");
		public static final ResourceKey<Block> LAZULI_ROOTS = createKey("lazuli_roots");
		public static final ResourceKey<Block> LAZULI_ROOTS_PLANT = createKey("lazuli_roots_plant");
		public static final ResourceKey<Block> STELLAR_ROOTS = createKey("stellar_roots");
		public static final ResourceKey<Block> STELLAR_ROOTS_PLANT = createKey("stellar_roots_plant");
		public static final ResourceKey<Block> DEAD_ROOTS = createKey("dead_roots");
		public static final ResourceKey<Block> DEAD_ROOTS_PLANT = createKey("dead_roots_plant");
		public static final ResourceKey<Block> GLOWING_ROOTS = createKey("glowing_roots");
		public static final ResourceKey<Block> GLOWING_ROOTS_PLANT = createKey("glowing_roots_plant");
		public static final ResourceKey<Block> SHADOW_GLOWING_ROOTS = createKey("shadow_glowing_roots");
		public static final ResourceKey<Block> SHADOW_GLOWING_ROOTS_PLANT = createKey("shadow_glowing_roots_plant");

		//grass
		public static final ResourceKey<Block> STELLAR_GRASS = createKey("stellar_grass");
		public static final ResourceKey<Block> STELLAR_GRASS_BALL = createKey("stellar_grass_ball");
		public static final ResourceKey<Block> STELLAR_FERN = createKey("stellar_fern");
		public static final ResourceKey<Block> STELLAR_TALL_GRASS = createKey("stellar_tall_grass");
		public static final ResourceKey<Block> STELLAR_TALL_FERN = createKey("stellar_tall_fern");
		public static final ResourceKey<Block> STELLAR_VERY_TALL_GRASS = createKey("stellar_very_tall_grass");
		public static final ResourceKey<Block> BLUISH_FERN = createKey("bluish_fern");
		public static final ResourceKey<Block> TALL_BLUISH_FERN = createKey("tall_bluish_fern");
		public static final ResourceKey<Block> POLYCHROME_FERN = createKey("polychrome_fern");
		public static final ResourceKey<Block> TALL_POLYCHROME_FERN = createKey("tall_polychrome_fern");
		public static final ResourceKey<Block> STELLAR_DEAD_BUSH = createKey("stellar_dead_bush");
		public static final ResourceKey<Block> BRAMBLES = createKey("brambles");
		public static final ResourceKey<Block> SHADOW_BRAMBLES = createKey("shadow_brambles");
		public static final ResourceKey<Block> SHADOW_GRASS = createKey("shadow_grass");
		public static final ResourceKey<Block> SHADOW_GRASS_BALL = createKey("shadow_grass_ball");
		public static final ResourceKey<Block> PURPLISH_STELLAR_GRASS = createKey("purplish_stellar_grass");
		public static final ResourceKey<Block> STELLAR_CLOVERS = createKey("stellar_clovers");
		public static final ResourceKey<Block> GLOWING_STELLAR_GRASS = createKey("glowing_stellar_grass");

		//flowers
		public static final ResourceKey<Block> BLUE_FLOWER = createKey("blue_flower");
		public static final ResourceKey<Block> BLACK_ROSE = createKey("black_rose");
		public static final ResourceKey<Block> BELLFLOWER = createKey("bellflower");

		//potted things
		public static final ResourceKey<Block> POTTED_BLUE_FLOWER = createKey("potted_blue_flower");
		public static final ResourceKey<Block> POTTED_BLACK_ROSE = createKey("potted_black_rose");
		public static final ResourceKey<Block> POTTED_BELLFLOWER = createKey("potted_bellflower");
		public static final ResourceKey<Block> POTTED_STELLAR_FERN = createKey("potted_stellar_fern");
		public static final ResourceKey<Block> POTTED_STELLAR_DEAD_BUSH = createKey("potted_stellar_dead_bush");
		public static final ResourceKey<Block> POTTED_SKY_CACTUS = createKey("potted_sky_cactus");
		public static final ResourceKey<Block> POTTED_VIBRANT_SKY_CACTUS = createKey("potted_vibrant_sky_cactus");
		public static final ResourceKey<Block> POTTED_AERIAL_TREE_SAPLING = createKey("potted_aerial_tree_sapling");
		public static final ResourceKey<Block> POTTED_GOLDEN_BEECH_SAPLING = createKey("potted_golden_beech_sapling");
		public static final ResourceKey<Block> POTTED_COPPER_PINE_SAPLING = createKey("potted_copper_pine_sapling");
		public static final ResourceKey<Block> POTTED_LAPIS_ROBINIA_SAPLING = createKey("potted_lapis_robinia_sapling");
		public static final ResourceKey<Block> POTTED_SHADOW_PINE_SAPLING = createKey("potted_shadow_pine_sapling");
		public static final ResourceKey<Block> POTTED_PURPLE_SHADOW_PINE_SAPLING = createKey("potted_purple_shadow_pine_sapling");
		public static final ResourceKey<Block> POTTED_STELLAR_JUNGLE_TREE_SAPLING = createKey("potted_stellar_jungle_tree_sapling");
		public static final ResourceKey<Block> POTTED_CORTINARIUS_VIOLACEUS = createKey("potted_cortinarius_violaceus");
		public static final ResourceKey<Block> POTTED_VERDIGRIS_AGARIC = createKey("potted_verdigris_agaric");
		public static final ResourceKey<Block> POTTED_VINE_BLOSSOM = createKey("potted_vine_blossom");
		public static final ResourceKey<Block> POTTED_GLOWING_BOLETUS = createKey("potted_glowing_boletus");

		//with gui
		public static final ResourceKey<Block> OSCILLATOR = createKey("oscillator");
		public static final ResourceKey<Block> FREEZER = createKey("freezer");
		public static final ResourceKey<Block> STELLAR_FURNACE = createKey("stellar_furnace");
		public static final ResourceKey<Block> GHOST_STELLAR_FURNACE = createKey("ghost_stellar_furnace");

		//chests
		public static final ResourceKey<Block> AERIAL_TREE_CHEST = createKey("aerial_tree_chest");
		public static final ResourceKey<Block> GOLDEN_BEECH_CHEST = createKey("golden_beech_chest");
		public static final ResourceKey<Block> COPPER_PINE_CHEST = createKey("copper_pine_chest");
		public static final ResourceKey<Block> LAPIS_ROBINIA_CHEST = createKey("lapis_robinia_chest");
		public static final ResourceKey<Block> SHADOW_PINE_CHEST = createKey("shadow_pine_chest");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_CHEST = createKey("stellar_jungle_tree_chest");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_CHEST = createKey("sky_cactus_fiber_chest");
		public static final ResourceKey<Block> GRAY_SHROOM_CHEST = createKey("gray_shroom_chest");
		public static final ResourceKey<Block> MUD_CHEST = createKey("mud_chest");
		public static final ResourceKey<Block> LUNATIC_CHEST = createKey("lunatic_chest");
		public static final ResourceKey<Block> VOLUCITE_CHEST = createKey("volucite_chest");
		public static final ResourceKey<Block> SHADOW_CATACOMBS_CHEST = createKey("shadow_catacombs_chest");
		public static final ResourceKey<Block> GOLDEN_NETHER_CHEST = createKey("golden_nether_chest");

		//chest mimics
		public static final ResourceKey<Block> AERIAL_TREE_CHEST_MIMIC = createKey("aerial_tree_chest_mimic");
		public static final ResourceKey<Block> GOLDEN_BEECH_CHEST_MIMIC = createKey("golden_beech_chest_mimic");
		public static final ResourceKey<Block> COPPER_PINE_CHEST_MIMIC = createKey("copper_pine_chest_mimic");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_CHEST_MIMIC = createKey("sky_cactus_fiber_chest_mimic");

		//barrel mimics
		public static final ResourceKey<Block> SHADOW_PINE_BARREL_MIMIC = createKey("shadow_pine_barrel_mimic");

		//fences, bars or walls
		public static final ResourceKey<Block> AERIAL_TREE_FENCE = createKey("aerial_tree_fence");
		public static final ResourceKey<Block> GOLDEN_BEECH_FENCE = createKey("golden_beech_fence");
		public static final ResourceKey<Block> COPPER_PINE_FENCE = createKey("copper_pine_fence");
		public static final ResourceKey<Block> LAPIS_ROBINIA_FENCE = createKey("lapis_robinia_fence");
		public static final ResourceKey<Block> SHADOW_PINE_FENCE = createKey("shadow_pine_fence");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_FENCE = createKey("stellar_jungle_tree_fence");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_FENCE = createKey("sky_cactus_fiber_fence");
		public static final ResourceKey<Block> GRAY_SHROOM_FENCE = createKey("gray_shroom_fence");
		public static final ResourceKey<Block> RUBY_BARS = createKey("ruby_bars");
		public static final ResourceKey<Block> STELLAR_STONE_WALL = createKey("stellar_stone_wall");
		public static final ResourceKey<Block> STELLAR_COBBLESTONE_WALL = createKey("stellar_cobblestone_wall");
		public static final ResourceKey<Block> STELLAR_STONE_BRICKS_WALL = createKey("stellar_stone_bricks_wall");
		public static final ResourceKey<Block> MOSSY_STELLAR_STONE_WALL = createKey("mossy_stellar_stone_wall");
		public static final ResourceKey<Block> MOSSY_STELLAR_COBBLESTONE_WALL = createKey("mossy_stellar_cobblestone_wall");
		public static final ResourceKey<Block> SLIPPERY_SAND_STONE_WALL = createKey("slippery_sand_stone_wall");
		public static final ResourceKey<Block> SLIPPERY_SAND_STONE_BRICKS_WALL = createKey("slippery_sand_stone_bricks_wall");
		public static final ResourceKey<Block> CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL = createKey("cracked_slippery_sand_stone_bricks_wall");
		public static final ResourceKey<Block> GLAUCOPHANITE_WALL = createKey("glaucophanite_wall");
		public static final ResourceKey<Block> POLISHED_GLAUCOPHANITE_WALL = createKey("polished_glaucophanite_wall");
		public static final ResourceKey<Block> MAGMATIC_GEL_WALL = createKey("magmatic_gel_wall");

		//gates
		public static final ResourceKey<Block> AERIAL_TREE_GATE = createKey("aerial_tree_gate");
		public static final ResourceKey<Block> GOLDEN_BEECH_GATE = createKey("golden_beech_gate");
		public static final ResourceKey<Block> COPPER_PINE_GATE = createKey("copper_pine_gate");
		public static final ResourceKey<Block> LAPIS_ROBINIA_GATE = createKey("lapis_robinia_gate");
		public static final ResourceKey<Block> SHADOW_PINE_GATE = createKey("shadow_pine_gate");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_GATE = createKey("stellar_jungle_tree_gate");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_GATE = createKey("sky_cactus_fiber_gate");
		public static final ResourceKey<Block> GRAY_SHROOM_GATE = createKey("gray_shroom_gate");

		//doors
		public static final ResourceKey<Block> AERIAL_TREE_DOOR = createKey("aerial_tree_door");
		public static final ResourceKey<Block> GOLDEN_BEECH_DOOR = createKey("golden_beech_door");
		public static final ResourceKey<Block> COPPER_PINE_DOOR = createKey("copper_pine_door");
		public static final ResourceKey<Block> LAPIS_ROBINIA_DOOR = createKey("lapis_robinia_door");
		public static final ResourceKey<Block> SHADOW_PINE_DOOR = createKey("shadow_pine_door");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_DOOR = createKey("stellar_jungle_tree_door");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_DOOR = createKey("sky_cactus_fiber_door");
		public static final ResourceKey<Block> GRAY_SHROOM_DOOR = createKey("gray_shroom_door");
		public static final ResourceKey<Block> RUBY_DOOR = createKey("ruby_door");

		//trapdoors
		public static final ResourceKey<Block> AERIAL_TREE_TRAPDOOR = createKey("aerial_tree_trapdoor");
		public static final ResourceKey<Block> GOLDEN_BEECH_TRAPDOOR = createKey("golden_beech_trapdoor");
		public static final ResourceKey<Block> COPPER_PINE_TRAPDOOR = createKey("copper_pine_trapdoor");
		public static final ResourceKey<Block> LAPIS_ROBINIA_TRAPDOOR = createKey("lapis_robinia_trapdoor");
		public static final ResourceKey<Block> SHADOW_PINE_TRAPDOOR = createKey("shadow_pine_trapdoor");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_TRAPDOOR = createKey("stellar_jungle_tree_trapdoor");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_TRAPDOOR = createKey("sky_cactus_fiber_trapdoor");
		public static final ResourceKey<Block> GRAY_SHROOM_TRAPDOOR = createKey("gray_shroom_trapdoor");
		public static final ResourceKey<Block> RUBY_TRAPDOOR = createKey("ruby_trapdoor");

		//buttons
		public static final ResourceKey<Block> STELLAR_STONE_BUTTON = createKey("stellar_stone_button");
		public static final ResourceKey<Block> STELLAR_COBBLESTONE_BUTTON = createKey("stellar_cobblestone_button");
		public static final ResourceKey<Block> STELLAR_STONE_BRICKS_BUTTON = createKey("stellar_stone_bricks_button");
		public static final ResourceKey<Block> SLIPPERY_SAND_STONE_BUTTON = createKey("slippery_sand_stone_button");
		public static final ResourceKey<Block> SLIPPERY_SAND_STONE_BRICKS_BUTTON = createKey("slippery_sand_stone_bricks_button");
		public static final ResourceKey<Block> AERIAL_TREE_BUTTON = createKey("aerial_tree_button");
		public static final ResourceKey<Block> GOLDEN_BEECH_BUTTON = createKey("golden_beech_button");
		public static final ResourceKey<Block> COPPER_PINE_BUTTON = createKey("copper_pine_button");
		public static final ResourceKey<Block> LAPIS_ROBINIA_BUTTON = createKey("lapis_robinia_button");
		public static final ResourceKey<Block> SHADOW_PINE_BUTTON = createKey("shadow_pine_button");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_BUTTON = createKey("stellar_jungle_tree_button");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_BUTTON = createKey("sky_cactus_fiber_button");
		public static final ResourceKey<Block> GRAY_SHROOM_BUTTON = createKey("gray_shroom_button");
		public static final ResourceKey<Block> GLAUCOPHANITE_BUTTON = createKey("glaucophanite_button");

		//pressure plates
		public static final ResourceKey<Block> STELLAR_STONE_PRESSURE_PLATE = createKey("stellar_stone_pressure_plate");
		public static final ResourceKey<Block> STELLAR_COBBLESTONE_PRESSURE_PLATE = createKey("stellar_cobblestone_pressure_plate");
		public static final ResourceKey<Block> STELLAR_STONE_BRICKS_PRESSURE_PLATE = createKey("stellar_stone_bricks_pressure_plate");
		public static final ResourceKey<Block> SLIPPERY_SAND_STONE_PRESSURE_PLATE = createKey("slippery_sand_stone_pressure_plate");
		public static final ResourceKey<Block> SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE = createKey("slippery_sand_stone_bricks_pressure_plate");
		public static final ResourceKey<Block> AERIAL_TREE_PRESSURE_PLATE = createKey("aerial_tree_pressure_plate");
		public static final ResourceKey<Block> GOLDEN_BEECH_PRESSURE_PLATE = createKey("golden_beech_pressure_plate");
		public static final ResourceKey<Block> COPPER_PINE_PRESSURE_PLATE = createKey("copper_pine_pressure_plate");
		public static final ResourceKey<Block> LAPIS_ROBINIA_PRESSURE_PLATE = createKey("lapis_robinia_pressure_plate");
		public static final ResourceKey<Block> SHADOW_PINE_PRESSURE_PLATE = createKey("shadow_pine_pressure_plate");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_PRESSURE_PLATE = createKey("stellar_jungle_tree_pressure_plate");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_PRESSURE_PLATE = createKey("sky_cactus_fiber_pressure_plate");
		public static final ResourceKey<Block> GRAY_SHROOM_PRESSURE_PLATE = createKey("gray_shroom_pressure_plate");
		public static final ResourceKey<Block> GLAUCOPHANITE_PRESSURE_PLATE = createKey("glaucophanite_pressure_plate");

		//slabs
		public static final ResourceKey<Block> AERIAL_TREE_SLAB = createKey("aerial_tree_slab");
		public static final ResourceKey<Block> GOLDEN_BEECH_SLAB = createKey("golden_beech_slab");
		public static final ResourceKey<Block> COPPER_PINE_SLAB = createKey("copper_pine_slab");
		public static final ResourceKey<Block> LAPIS_ROBINIA_SLAB = createKey("lapis_robinia_slab");
		public static final ResourceKey<Block> SHADOW_PINE_SLAB = createKey("shadow_pine_slab");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_SLAB = createKey("stellar_jungle_tree_slab");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_SLAB = createKey("sky_cactus_fiber_slab");
		public static final ResourceKey<Block> GRAY_SHROOM_SLAB = createKey("gray_shroom_slab");
		public static final ResourceKey<Block> STELLAR_STONE_SLAB = createKey("stellar_stone_slab");
		public static final ResourceKey<Block> STELLAR_COBBLESTONE_SLAB = createKey("stellar_cobblestone_slab");
		public static final ResourceKey<Block> STELLAR_STONE_BRICKS_SLAB = createKey("stellar_stone_bricks_slab");
		public static final ResourceKey<Block> MOSSY_STELLAR_STONE_SLAB = createKey("mossy_stellar_stone_slab");
		public static final ResourceKey<Block> MOSSY_STELLAR_COBBLESTONE_SLAB = createKey("mossy_stellar_cobblestone_slab");
		public static final ResourceKey<Block> SLIPPERY_SAND_STONE_SLAB = createKey("slippery_sand_stone_slab");
		public static final ResourceKey<Block> SLIPPERY_SAND_STONE_BRICKS_SLAB = createKey("slippery_sand_stone_bricks_slab");
		public static final ResourceKey<Block> CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB = createKey("cracked_slippery_sand_stone_bricks_slab");
		public static final ResourceKey<Block> POLISHED_GLAUCOPHANITE_SLAB = createKey("polished_glaucophanite_slab");
		public static final ResourceKey<Block> MAGMATIC_GEL_SLAB = createKey("magmatic_gel_slab");

		//stairs
		public static final ResourceKey<Block> AERIAL_TREE_STAIRS = createKey("aerial_tree_stairs");
		public static final ResourceKey<Block> GOLDEN_BEECH_STAIRS = createKey("golden_beech_stairs");
		public static final ResourceKey<Block> COPPER_PINE_STAIRS = createKey("copper_pine_stairs");
		public static final ResourceKey<Block> LAPIS_ROBINIA_STAIRS = createKey("lapis_robinia_stairs");
		public static final ResourceKey<Block> SHADOW_PINE_STAIRS = createKey("shadow_pine_stairs");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_STAIRS = createKey("stellar_jungle_tree_stairs");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_STAIRS = createKey("sky_cactus_fiber_stairs");
		public static final ResourceKey<Block> GRAY_SHROOM_STAIRS = createKey("gray_shroom_stairs");
		public static final ResourceKey<Block> STELLAR_STONE_STAIRS = createKey("stellar_stone_stairs");
		public static final ResourceKey<Block> STELLAR_COBBLESTONE_STAIRS = createKey("stellar_cobblestone_stairs");
		public static final ResourceKey<Block> STELLAR_STONE_BRICKS_STAIRS = createKey("stellar_stone_bricks_stairs");
		public static final ResourceKey<Block> MOSSY_STELLAR_STONE_STAIRS = createKey("mossy_stellar_stone_stairs");
		public static final ResourceKey<Block> MOSSY_STELLAR_COBBLESTONE_STAIRS = createKey("mossy_stellar_cobblestone_stairs");
		public static final ResourceKey<Block> SLIPPERY_SAND_STONE_STAIRS = createKey("slippery_sand_stone_stairs");
		public static final ResourceKey<Block> SLIPPERY_SAND_STONE_BRICKS_STAIRS = createKey("slippery_sand_stone_bricks_stairs");
		public static final ResourceKey<Block> CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS = createKey("cracked_slippery_sand_stone_bricks_stairs");
		public static final ResourceKey<Block> POLISHED_GLAUCOPHANITE_STAIRS = createKey("polished_glaucophanite_stairs");
		public static final ResourceKey<Block> MAGMATIC_GEL_STAIRS = createKey("magmatic_gel_stairs");

		//signs
		public static final ResourceKey<Block> AERIAL_TREE_STANDING_SIGN = createKey("aerial_tree_sign");
		public static final ResourceKey<Block> AERIAL_TREE_WALL_SIGN = createKey("aerial_tree_wall_sign");
		public static final ResourceKey<Block> GOLDEN_BEECH_STANDING_SIGN = createKey("golden_beech_sign");
		public static final ResourceKey<Block> GOLDEN_BEECH_WALL_SIGN = createKey("golden_beech_wall_sign");
		public static final ResourceKey<Block> COPPER_PINE_STANDING_SIGN = createKey("copper_pine_sign");
		public static final ResourceKey<Block> COPPER_PINE_WALL_SIGN = createKey("copper_pine_wall_sign");
		public static final ResourceKey<Block> LAPIS_ROBINIA_STANDING_SIGN = createKey("lapis_robinia_sign");
		public static final ResourceKey<Block> LAPIS_ROBINIA_WALL_SIGN = createKey("lapis_robinia_wall_sign");
		public static final ResourceKey<Block> SHADOW_PINE_STANDING_SIGN = createKey("shadow_pine_sign");
		public static final ResourceKey<Block> SHADOW_PINE_WALL_SIGN = createKey("shadow_pine_wall_sign");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_STANDING_SIGN = createKey("stellar_jungle_tree_sign");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_WALL_SIGN = createKey("stellar_jungle_tree_wall_sign");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_STANDING_SIGN = createKey("sky_cactus_fiber_sign");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_WALL_SIGN = createKey("sky_cactus_fiber_wall_sign");
		public static final ResourceKey<Block> GRAY_SHROOM_STANDING_SIGN = createKey("gray_shroom_sign");
		public static final ResourceKey<Block> GRAY_SHROOM_WALL_SIGN = createKey("gray_shroom_wall_sign");

		//hanging signs
		public static final ResourceKey<Block> AERIAL_TREE_HANGING_SIGN = createKey("aerial_tree_hanging_sign");
		public static final ResourceKey<Block> AERIAL_TREE_WALL_HANGING_SIGN = createKey("aerial_tree_wall_hanging_sign");
		public static final ResourceKey<Block> GOLDEN_BEECH_HANGING_SIGN = createKey("golden_beech_hanging_sign");
		public static final ResourceKey<Block> GOLDEN_BEECH_WALL_HANGING_SIGN = createKey("golden_beech_wall_hanging_sign");
		public static final ResourceKey<Block> COPPER_PINE_HANGING_SIGN = createKey("copper_pine_hanging_sign");
		public static final ResourceKey<Block> COPPER_PINE_WALL_HANGING_SIGN = createKey("copper_pine_wall_hanging_sign");
		public static final ResourceKey<Block> LAPIS_ROBINIA_HANGING_SIGN = createKey("lapis_robinia_hanging_sign");
		public static final ResourceKey<Block> LAPIS_ROBINIA_WALL_HANGING_SIGN = createKey("lapis_robinia_wall_hanging_sign");
		public static final ResourceKey<Block> SHADOW_PINE_HANGING_SIGN = createKey("shadow_pine_hanging_sign");
		public static final ResourceKey<Block> SHADOW_PINE_WALL_HANGING_SIGN = createKey("shadow_pine_wall_hanging_sign");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_HANGING_SIGN = createKey("stellar_jungle_tree_hanging_sign");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN = createKey("stellar_jungle_tree_wall_hanging_sign");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_HANGING_SIGN = createKey("sky_cactus_fiber_hanging_sign");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_WALL_HANGING_SIGN = createKey("sky_cactus_fiber_wall_hanging_sign");
		public static final ResourceKey<Block> GRAY_SHROOM_HANGING_SIGN = createKey("gray_shroom_hanging_sign");
		public static final ResourceKey<Block> GRAY_SHROOM_WALL_HANGING_SIGN = createKey("gray_shroom_wall_hanging_sign");

		//crafting tables
		public static final ResourceKey<Block> AERIAL_TREE_CRAFTING_TABLE = createKey("aerial_tree_crafting_table");
		public static final ResourceKey<Block> GOLDEN_BEECH_CRAFTING_TABLE = createKey("golden_beech_crafting_table");
		public static final ResourceKey<Block> COPPER_PINE_CRAFTING_TABLE = createKey("copper_pine_crafting_table");
		public static final ResourceKey<Block> LAPIS_ROBINIA_CRAFTING_TABLE = createKey("lapis_robinia_crafting_table");
		public static final ResourceKey<Block> SHADOW_PINE_CRAFTING_TABLE = createKey("shadow_pine_crafting_table");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_CRAFTING_TABLE = createKey("stellar_jungle_tree_crafting_table");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_CRAFTING_TABLE = createKey("sky_cactus_fiber_crafting_table");
		public static final ResourceKey<Block> GRAY_SHROOM_CRAFTING_TABLE = createKey("gray_shroom_crafting_table");

		//barrels
		public static final ResourceKey<Block> AERIAL_TREE_BARREL = createKey("aerial_tree_barrel");
		public static final ResourceKey<Block> GOLDEN_BEECH_BARREL = createKey("golden_beech_barrel");
		public static final ResourceKey<Block> COPPER_PINE_BARREL = createKey("copper_pine_barrel");
		public static final ResourceKey<Block> LAPIS_ROBINIA_BARREL = createKey("lapis_robinia_barrel");
		public static final ResourceKey<Block> SHADOW_PINE_BARREL = createKey("shadow_pine_barrel");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_BARREL = createKey("stellar_jungle_tree_barrel");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_BARREL = createKey("sky_cactus_fiber_barrel");
		public static final ResourceKey<Block> GRAY_SHROOM_BARREL = createKey("gray_shroom_barrel");

		//composters
		public static final ResourceKey<Block> AERIAL_TREE_COMPOSTER = createKey("aerial_tree_composter");
		public static final ResourceKey<Block> GOLDEN_BEECH_COMPOSTER = createKey("golden_beech_composter");
		public static final ResourceKey<Block> COPPER_PINE_COMPOSTER = createKey("copper_pine_composter");
		public static final ResourceKey<Block> LAPIS_ROBINIA_COMPOSTER = createKey("lapis_robinia_composter");
		public static final ResourceKey<Block> SHADOW_PINE_COMPOSTER = createKey("shadow_pine_composter");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_COMPOSTER = createKey("stellar_jungle_tree_composter");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_COMPOSTER = createKey("sky_cactus_fiber_composter");
		public static final ResourceKey<Block> GRAY_SHROOM_COMPOSTER = createKey("gray_shroom_composter");

		//decorative
		public static final ResourceKey<Block> AERIAL_TREE_VINE_ROPE_SPOOL = createKey("aerial_tree_vine_rope_spool");
		public static final ResourceKey<Block> GOLDEN_BEECH_VINE_ROPE_SPOOL = createKey("golden_beech_vine_rope_spool");
		public static final ResourceKey<Block> COPPER_PINE_VINE_ROPE_SPOOL = createKey("copper_pine_vine_rope_spool");
		public static final ResourceKey<Block> LAPIS_ROBINIA_VINE_ROPE_SPOOL = createKey("lapis_robinia_vine_rope_spool");
		public static final ResourceKey<Block> SHADOW_PINE_VINE_ROPE_SPOOL = createKey("shadow_pine_vine_rope_spool");
		public static final ResourceKey<Block> STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL = createKey("stellar_jungle_tree_vine_rope_spool");
		public static final ResourceKey<Block> SKY_CACTUS_FIBER_VINE_ROPE_SPOOL = createKey("sky_cactus_fiber_vine_rope_spool");
		public static final ResourceKey<Block> GRAY_SHROOM_VINE_ROPE_SPOOL = createKey("gray_shroom_vine_rope_spool");

		//fluids
		public static final ResourceKey<Block> LIQUID_OF_THE_GODS = createKey("liquid_of_the_gods");

		private static ResourceKey<Block> createKey(String name)
		{
			return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(AerialHell.MODID, name));
		}
	}
}
