package fr.factionbedrock.aerialhell.Registry;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

import java.util.function.ToIntFunction;

import com.google.common.collect.ImmutableMap;

import fr.factionbedrock.aerialhell.Block.*;
import fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther.*;
import fr.factionbedrock.aerialhell.Block.DirtAndVariants.*;
import fr.factionbedrock.aerialhell.Block.DungeonCores.*;
import fr.factionbedrock.aerialhell.Block.Furnaces.*;
import fr.factionbedrock.aerialhell.Block.Mimics.*;
import fr.factionbedrock.aerialhell.Block.Ores.*;
import fr.factionbedrock.aerialhell.Block.Plants.*;
import fr.factionbedrock.aerialhell.Block.Plants.Bushes.*;
import fr.factionbedrock.aerialhell.Block.Plants.Vines.*;
import fr.factionbedrock.aerialhell.Block.CollisionCondition.*;
import fr.factionbedrock.aerialhell.Block.StandingAndWall.*;
import fr.factionbedrock.aerialhell.Block.Trophies.BottomSlabLikeTrophyBlock;
import fr.factionbedrock.aerialhell.Item.*;
import fr.factionbedrock.aerialhell.Item.Bucket.*;
import fr.factionbedrock.aerialhell.Item.Material.*;
import fr.factionbedrock.aerialhell.Item.Shuriken.*;
import fr.factionbedrock.aerialhell.Item.Tools.*;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellRarities;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellTreeGrowers;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.core.Direction;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.ForgeSpawnEggItem;

public class AerialHellBlocksAndItems
{
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

	public static void registerCompostableItems() {
		ComposterBlock.COMPOSTABLES.put(STELLAR_GRASS_ITEM.get().asItem(), 0.1F);
		ComposterBlock.COMPOSTABLES.put(STELLAR_TALL_GRASS_ITEM.get().asItem(), 0.2F);
		ComposterBlock.COMPOSTABLES.put(STELLAR_GRASS_BALL_ITEM.get().asItem(), 0.2F);
		ComposterBlock.COMPOSTABLES.put(STELLAR_DEAD_BUSH_ITEM.get().asItem(), 0.1F);
		ComposterBlock.COMPOSTABLES.put(BLUE_FLOWER_ITEM.get().asItem(), 0.2F);
		ComposterBlock.COMPOSTABLES.put(BLACK_ROSE_ITEM.get().asItem(), 0.2F);
		ComposterBlock.COMPOSTABLES.put(BELLFLOWER_ITEM.get().asItem(), 0.2F);
		ComposterBlock.COMPOSTABLES.put(AERIAL_BERRY.get().asItem(), 0.5F);
		ComposterBlock.COMPOSTABLES.put(VIBRANT_AERIAL_BERRY.get().asItem(), 0.85F);
		ComposterBlock.COMPOSTABLES.put(FROZEN_AERIAL_BERRY.get().asItem(), 0.85F);
		ComposterBlock.COMPOSTABLES.put(AERIAL_BERRY_SEEDS.get().asItem(), 0.1F);
		ComposterBlock.COMPOSTABLES.put(VIBRANT_AERIAL_BERRY_SEEDS.get().asItem(), 0.2F);
		ComposterBlock.COMPOSTABLES.put(COPPER_PINE_CONE.get().asItem(), 0.5F);
		ComposterBlock.COMPOSTABLES.put(AERIAL_TREE_LEAVES_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(AERIAL_TREE_SAPLING_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(GOLDEN_BEECH_LEAVES_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(GOLDEN_BEECH_SAPLING_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(COPPER_PINE_LEAVES_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(COPPER_PINE_SAPLING_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(LAPIS_ROBINIA_SAPLING_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(LAPIS_ROBINIA_LEAVES_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(SHADOW_PINE_SAPLING_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(SHADOW_PINE_LEAVES_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(STELLAR_JUNGLE_TREE_SAPLING_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(STELLAR_JUNGLE_TREE_LEAVES_ITEM.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(CORTINARIUS_VIOLACEUS.get().asItem(), 0.3F);
		ComposterBlock.COMPOSTABLES.put(GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.get().asItem(), 0.2F);
		ComposterBlock.COMPOSTABLES.put(SKY_CACTUS_FIBER.get().asItem(), 0.1F);
		ComposterBlock.COMPOSTABLES.put(SKY_CACTUS_ITEM.get().asItem(), 0.4F);
		ComposterBlock.COMPOSTABLES.put(VIBRANT_SKY_CACTUS_FIBER.get().asItem(), 0.2F);
		ComposterBlock.COMPOSTABLES.put(VIBRANT_SKY_CACTUS_ITEM.get().asItem(), 0.8F);
	}

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
	public static final RegistryObject<AerialHellPortalBlock> AERIAL_HELL_PORTAL = BLOCKS.register("aerial_hell_portal", () -> new AerialHellPortalBlock(BlockBehaviour.Properties.of().sound(SoundType.GLASS).strength(-1F).noCollission().lightLevel((state) -> 10).noLootTable()));
	public static final RegistryObject<Block> STELLAR_PORTAL_FRAME_BLOCK = BLOCKS.register("stellar_portal_frame_block", () -> new Block((BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).requiresCorrectToolForDrops().strength(25.0F, 600.0F))));
	public static final RegistryObject<Block> STELLAR_PORTAL_FRAME_ORE = BLOCKS.register("stellar_portal_frame_ore",() -> new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().strength(25.0F, 600.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> DEEPSLATE_STELLAR_PORTAL_FRAME_ORE = BLOCKS.register("deepslate_stellar_portal_frame_ore",() -> new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().strength(30.0F, 600.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Item> STELLAR_PORTAL_FRAME_BLOCK_ITEM = ITEMS.register("stellar_portal_frame_block", () -> new BlockItem(STELLAR_PORTAL_FRAME_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_PORTAL_FRAME_ORE_ITEM = ITEMS.register("stellar_portal_frame_ore", () -> new BlockItem(STELLAR_PORTAL_FRAME_ORE.get(), new Item.Properties()));
	public static final RegistryObject<Item> DEEPSLATE_STELLAR_PORTAL_FRAME_ORE_ITEM = ITEMS.register("deepslate_stellar_portal_frame_ore", () -> new BlockItem(DEEPSLATE_STELLAR_PORTAL_FRAME_ORE.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_PORTAL_FRAME_BRICK = ITEMS.register("stellar_portal_frame_brick", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_LIGHTER = ITEMS.register("stellar_lighter", () -> new StellarLighterItem(new Item.Properties().stacksTo(1).durability(4)));

	//torch
	public static final RegistryObject<Block> FLUORITE_WALL_TORCH = BLOCKS.register("fluorite_wall_torch", () -> new AerialHellWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH)));
	public static final RegistryObject<Block> FLUORITE_TORCH = BLOCKS.register("fluorite_torch", () -> new AerialHellTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH)));
	public static final RegistryObject<Item> FLUORITE_TORCH_ITEM = ITEMS.register("fluorite_torch", () -> new StandingAndWallBlockItem(FLUORITE_TORCH.get(), FLUORITE_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));
	public static final RegistryObject<Block> VOLUCITE_WALL_TORCH = BLOCKS.register("volucite_wall_torch", () -> new AerialHellWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).lightLevel((state) -> {return 9;})));
	public static final RegistryObject<Block> VOLUCITE_TORCH = BLOCKS.register("volucite_torch", () -> new AerialHellTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).lightLevel((state) -> {return 9;})));
	public static final RegistryObject<Item> VOLUCITE_TORCH_ITEM = ITEMS.register("volucite_torch", () -> new StandingAndWallBlockItem(VOLUCITE_TORCH.get(), VOLUCITE_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));
	public static final RegistryObject<Block> SHADOW_WALL_TORCH = BLOCKS.register("shadow_wall_torch", () -> new AerialHellWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).lightLevel((state) -> {return 9;})));
	public static final RegistryObject<Block> SHADOW_TORCH = BLOCKS.register("shadow_torch", () -> new AerialHellTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).lightLevel((state) -> {return 9;})));
	public static final RegistryObject<Item> SHADOW_TORCH_ITEM = ITEMS.register("shadow_torch", () -> new StandingAndWallBlockItem(SHADOW_TORCH.get(), SHADOW_WALL_TORCH.get(), new Item.Properties(), Direction.DOWN));

	//lanterns
	public static final RegistryObject<Block> FLUORITE_LANTERN = BLOCKS.register("fluorite_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
	public static final RegistryObject<Block> RUBY_LANTERN = BLOCKS.register("ruby_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
	public static final RegistryObject<Block> RUBY_FLUORITE_LANTERN = BLOCKS.register("ruby_fluorite_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
	public static final RegistryObject<Block> VOLUCITE_LANTERN = BLOCKS.register("volucite_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
	public static final RegistryObject<Block> VOLUCITE_FLUORITE_LANTERN = BLOCKS.register("volucite_fluorite_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
	public static final RegistryObject<Block> LUNATIC_LANTERN = BLOCKS.register("lunatic_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
	public static final RegistryObject<Block> SHADOW_LANTERN = BLOCKS.register("shadow_lantern", () -> new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_LANTERN)));
	public static final RegistryObject<Item> FLUORITE_LANTERN_ITEM = ITEMS.register("fluorite_lantern", () -> new BlockItem(FLUORITE_LANTERN.get(), new Item.Properties()));
	public static final RegistryObject<Item> RUBY_LANTERN_ITEM = ITEMS.register("ruby_lantern", () -> new BlockItem(RUBY_LANTERN.get(), new Item.Properties()));
	public static final RegistryObject<Item> RUBY_FLUORITE_LANTERN_ITEM = ITEMS.register("ruby_fluorite_lantern", () -> new BlockItem(RUBY_FLUORITE_LANTERN.get(), new Item.Properties()));
	public static final RegistryObject<Item> VOLUCITE_LANTERN_ITEM = ITEMS.register("volucite_lantern", () -> new BlockItem(VOLUCITE_LANTERN.get(), new Item.Properties()));
	public static final RegistryObject<Item> VOLUCITE_FLUORITE_LANTERN_ITEM = ITEMS.register("volucite_fluorite_lantern", () -> new BlockItem(VOLUCITE_FLUORITE_LANTERN.get(), new Item.Properties()));
	public static final RegistryObject<Item> LUNATIC_LANTERN_ITEM = ITEMS.register("lunatic_lantern", () -> new BlockItem(LUNATIC_LANTERN.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHADOW_LANTERN_ITEM = ITEMS.register("shadow_lantern", () -> new BlockItem(SHADOW_LANTERN.get(), new Item.Properties()));

	//chains
	public static final RegistryObject<ChainBlock> RUBY_CHAIN = BLOCKS.register("ruby_chain", () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
	public static final RegistryObject<ChainBlock> VOLUCITE_CHAIN = BLOCKS.register("volucite_chain", () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
	public static final RegistryObject<ChainBlock> LUNATIC_CHAIN = BLOCKS.register("lunatic_chain", () -> new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
	public static final RegistryObject<ChainBlock> SHADOW_CHAIN = BLOCKS.register("shadow_chain", () -> new ShadowChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
	public static final RegistryObject<Item> RUBY_CHAIN_ITEM = ITEMS.register("ruby_chain", () -> new BlockItem(RUBY_CHAIN.get(), new Item.Properties()));
	public static final RegistryObject<Item> VOLUCITE_CHAIN_ITEM = ITEMS.register("volucite_chain", () -> new BlockItem(VOLUCITE_CHAIN.get(), new Item.Properties()));
	public static final RegistryObject<Item> LUNATIC_CHAIN_ITEM = ITEMS.register("lunatic_chain", () -> new BlockItem(LUNATIC_CHAIN.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHADOW_CHAIN_ITEM = ITEMS.register("shadow_chain", () -> new BlockItem(SHADOW_CHAIN.get(), new Item.Properties()));

	//grass & dirt
	public static final RegistryObject<Block> STELLAR_GRASS_BLOCK = BLOCKS.register("stellar_grass_block", () -> new StellarGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)));
	public static final RegistryObject<Block> CHISELED_STELLAR_GRASS_BLOCK = BLOCKS.register("chiseled_stellar_grass_block", () -> new StellarGrassBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_GRASS_BLOCK.get())));
	public static final RegistryObject<Block> STELLAR_DIRT = BLOCKS.register("stellar_dirt", () -> new StellarDirtBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)));
	public static final RegistryObject<Block> STELLAR_COARSE_DIRT = BLOCKS.register("stellar_coarse_dirt", () -> new StellarDirtBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COARSE_DIRT)));
	public static final RegistryObject<Block> STELLAR_FARMLAND = BLOCKS.register("stellar_farmland", () -> new StellarFarmBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).randomTicks().strength(0.6F).sound(SoundType.GRAVEL).isViewBlocking((state, blockgetter, pos) -> true).isSuffocating((state, blockgetter, pos) -> true)));
	public static final RegistryObject<Block> STELLAR_DIRT_PATH = BLOCKS.register("stellar_dirt_path", () -> new StellarDirtPathBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT_PATH)));
	public static final RegistryObject<Block> STELLAR_PODZOL = BLOCKS.register("stellar_podzol", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL)));
	public static final RegistryObject<Block> STELLAR_CRYSTAL_PODZOL = BLOCKS.register("stellar_crystal_podzol", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL)));
	public static final RegistryObject<Block> CHISELED_STELLAR_DIRT = BLOCKS.register("chiseled_stellar_dirt", () -> new StellarDirtBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_DIRT.get())));
	public static final RegistryObject<Block> SHADOW_GRASS_BLOCK = BLOCKS.register("shadow_grass_block", () -> new ShadowGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)));
	public static final RegistryObject<Item> STELLAR_GRASS_BLOCK_ITEM = ITEMS.register("stellar_grass_block", () -> new BlockItem(STELLAR_GRASS_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHISELED_STELLAR_GRASS_BLOCK_ITEM = ITEMS.register("chiseled_stellar_grass_block", () -> new BlockItem(CHISELED_STELLAR_GRASS_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_DIRT_ITEM = ITEMS.register("stellar_dirt", () -> new BlockItem(STELLAR_DIRT.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_COARSE_DIRT_ITEM = ITEMS.register("stellar_coarse_dirt", () -> new BlockItem(STELLAR_COARSE_DIRT.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_FARMLAND_ITEM = ITEMS.register("stellar_farmland", () -> new BlockItem(STELLAR_FARMLAND.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_DIRT_PATH_ITEM = ITEMS.register("stellar_dirt_path", () -> new BlockItem(STELLAR_DIRT_PATH.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_PODZOL_ITEM = ITEMS.register("stellar_podzol", () -> new BlockItem(STELLAR_PODZOL.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_CRYSTAL_PODZOL_ITEM = ITEMS.register("stellar_crystal_podzol", () -> new BlockItem(STELLAR_CRYSTAL_PODZOL.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHISELED_STELLAR_DIRT_ITEM = ITEMS.register("chiseled_stellar_dirt", () -> new BlockItem(CHISELED_STELLAR_DIRT.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHADOW_GRASS_BLOCK_ITEM = ITEMS.register("shadow_grass_block", () -> new BlockItem(SHADOW_GRASS_BLOCK.get(), new Item.Properties()));

	//slippery sand
	public static final RegistryObject<Block> SLIPPERY_SAND = BLOCKS.register("slippery_sand", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).friction(1.025F)));
	public static final RegistryObject<Block> SLIPPERY_SAND_STONE = BLOCKS.register("slippery_sand_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).friction(1.01F)));
	public static final RegistryObject<Block> SLIPPERY_SAND_STONE_BRICKS = BLOCKS.register("slippery_sand_stone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get()).friction(1.005F)));
	public static final RegistryObject<Block> CUT_SLIPPERY_SAND_STONE = BLOCKS.register("cut_slippery_sand_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get()).friction(1.005F)));
	public static final RegistryObject<Block> CRACKED_SLIPPERY_SAND_STONE_BRICKS = BLOCKS.register("cracked_slippery_sand_stone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get()).friction(1.003F)));
	public static final RegistryObject<Item> SLIPPERY_SAND_ITEM = ITEMS.register("slippery_sand", () -> new BlockItem(SLIPPERY_SAND.get(), new Item.Properties()));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_ITEM = ITEMS.register("slippery_sand_stone", () -> new BlockItem(SLIPPERY_SAND_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_BRICKS_ITEM = ITEMS.register("slippery_sand_stone_bricks", () -> new BlockItem(SLIPPERY_SAND_STONE_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> CUT_SLIPPERY_SAND_STONE_ITEM = ITEMS.register("cut_slippery_sand_stone", () -> new BlockItem(CUT_SLIPPERY_SAND_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS_ITEM = ITEMS.register("cracked_slippery_sand_stone_bricks", () -> new BlockItem(CRACKED_SLIPPERY_SAND_STONE_BRICKS.get(), new Item.Properties()));

	//giant root
	public static final RegistryObject<RotatedPillarBlock> GIANT_ROOT = BLOCKS.register("giant_root", () -> new RotatedPillarBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GIANT_ROOT_ITEM = ITEMS.register("giant_root", () -> new BurnableBlockItem(GIANT_ROOT.get(), new Item.Properties(), 300));

    //aerial_tree
	public static final RegistryObject<RotatedPillarBlock> AERIAL_TREE_LOG = BLOCKS.register("aerial_tree_log", () -> new RotatedPillarBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_AERIAL_TREE_LOG = BLOCKS.register("stripped_aerial_tree_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get())));
	public static final RegistryObject<RotatedPillarBlock> AERIAL_TREE_WOOD = BLOCKS.register("aerial_tree_wood", () -> new RotatedPillarBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_AERIAL_TREE_WOOD = BLOCKS.register("stripped_aerial_tree_wood", () -> new RotatedPillarBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Block> AERIAL_TREE_LEAVES = BLOCKS.register("aerial_tree_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> AERIAL_TREE_PLANKS = BLOCKS.register("aerial_tree_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get())));
	public static final RegistryObject<Block> CHISELED_AERIAL_TREE_PLANKS = BLOCKS.register("chiseled_aerial_tree_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS.get())));
	public static final RegistryObject<Block> AERIAL_TREE_BOOKSHELF = BLOCKS.register("aerial_tree_bookshelf", () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS.get())));
	public static final RegistryObject<SaplingBlock> AERIAL_TREE_SAPLING = BLOCKS.register("aerial_tree_sapling", () -> new AerialHellSaplingBlock(AerialHellTreeGrowers.AERIAL_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_AERIAL_TREE));
	public static final RegistryObject<Item> AERIAL_TREE_LOG_ITEM = ITEMS.register("aerial_tree_log", () -> new BurnableBlockItem(AERIAL_TREE_LOG.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> STRIPPED_AERIAL_TREE_LOG_ITEM = ITEMS.register("stripped_aerial_tree_log", () -> new BurnableBlockItem(STRIPPED_AERIAL_TREE_LOG.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> AERIAL_TREE_WOOD_ITEM = ITEMS.register("aerial_tree_wood", () -> new BurnableBlockItem(AERIAL_TREE_WOOD.get(), new Item.Properties(), 210));
	public static final RegistryObject<Item> STRIPPED_AERIAL_TREE_WOOD_ITEM = ITEMS.register("stripped_aerial_tree_wood", () -> new BurnableBlockItem(STRIPPED_AERIAL_TREE_WOOD.get(), new Item.Properties(), 210));
	public static final RegistryObject<Item> AERIAL_TREE_LEAVES_ITEM = ITEMS.register("aerial_tree_leaves", () -> new BlockItem(AERIAL_TREE_LEAVES.get(), new Item.Properties()));
	public static final RegistryObject<Item> AERIAL_TREE_PLANKS_ITEM = ITEMS.register("aerial_tree_planks", () -> new BurnableBlockItem(AERIAL_TREE_PLANKS.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> CHISELED_AERIAL_TREE_PLANKS_ITEM = ITEMS.register("chiseled_aerial_tree_planks", () -> new BurnableBlockItem(CHISELED_AERIAL_TREE_PLANKS.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> AERIAL_TREE_BOOKSHELF_ITEM = ITEMS.register("aerial_tree_bookshelf", () -> new BlockItem(AERIAL_TREE_BOOKSHELF.get(), new Item.Properties()));
	public static final RegistryObject<Item> AERIAL_TREE_SAPLING_ITEM = ITEMS.register("aerial_tree_sapling", () -> new BlockItem(AERIAL_TREE_SAPLING.get(), new Item.Properties()));

	//petrified aerial tree
	public static final RegistryObject<RotatedPillarBlock> PETRIFIED_AERIAL_TREE_LOG = BLOCKS.register("petrified_aerial_tree_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get())));
	public static final RegistryObject<Item> PETRIFIED_AERIAL_TREE_LOG_ITEM = ITEMS.register("petrified_aerial_tree_log", () -> new BurnableBlockItem(PETRIFIED_AERIAL_TREE_LOG.get(), new Item.Properties(), 600));

	//golden beech
	public static final RegistryObject<RotatedPillarBlock> GOLDEN_BEECH_LOG = BLOCKS.register("golden_beech_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get())));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_GOLDEN_BEECH_LOG = BLOCKS.register("stripped_golden_beech_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG.get())));
	public static final RegistryObject<RotatedPillarBlock> GOLDEN_BEECH_WOOD = BLOCKS.register("golden_beech_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG.get())));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_GOLDEN_BEECH_WOOD = BLOCKS.register("stripped_golden_beech_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG.get())));
	public static final RegistryObject<Block> GOLDEN_BEECH_PLANKS = BLOCKS.register("golden_beech_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG.get())));
	public static final RegistryObject<Block> CHISELED_GOLDEN_BEECH_PLANKS = BLOCKS.register("chiseled_golden_beech_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS.get())));
	public static final RegistryObject<Block> GOLDEN_BEECH_LEAVES = BLOCKS.register("golden_beech_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> GOLDEN_BEECH_BOOKSHELF = BLOCKS.register("golden_beech_bookshelf", () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS.get())));
	public static final RegistryObject<SaplingBlock> GOLDEN_BEECH_SAPLING = BLOCKS.register("golden_beech_sapling", () -> new SaplingBlock(AerialHellTreeGrowers.GOLDEN_BEECH, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
	public static final RegistryObject<Item> GOLDEN_BEECH_LOG_ITEM = ITEMS.register("golden_beech_log", () -> new BurnableBlockItem(GOLDEN_BEECH_LOG.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> STRIPPED_GOLDEN_BEECH_LOG_ITEM = ITEMS.register("stripped_golden_beech_log", () -> new BurnableBlockItem(STRIPPED_GOLDEN_BEECH_LOG.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> GOLDEN_BEECH_WOOD_ITEM = ITEMS.register("golden_beech_wood", () -> new BurnableBlockItem(GOLDEN_BEECH_WOOD.get(), new Item.Properties(), 210));
	public static final RegistryObject<Item> STRIPPED_GOLDEN_BEECH_WOOD_ITEM = ITEMS.register("stripped_golden_beech_wood", () -> new BurnableBlockItem(STRIPPED_GOLDEN_BEECH_WOOD.get(), new Item.Properties(), 210));
	public static final RegistryObject<Item> GOLDEN_BEECH_PLANKS_ITEM = ITEMS.register("golden_beech_planks", () -> new BurnableBlockItem(GOLDEN_BEECH_PLANKS.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> CHISELED_GOLDEN_BEECH_PLANKS_ITEM = ITEMS.register("chiseled_golden_beech_planks", () -> new BurnableBlockItem(CHISELED_GOLDEN_BEECH_PLANKS.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> GOLDEN_BEECH_LEAVES_ITEM = ITEMS.register("golden_beech_leaves", () -> new BlockItem(GOLDEN_BEECH_LEAVES.get(), new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_BEECH_BOOKSHELF_ITEM = ITEMS.register("golden_beech_bookshelf", () -> new BlockItem(GOLDEN_BEECH_BOOKSHELF.get(), new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_BEECH_SAPLING_ITEM = ITEMS.register("golden_beech_sapling", () -> new BlockItem(GOLDEN_BEECH_SAPLING.get(), new Item.Properties()));

	//cropper pine
	public static final RegistryObject<RotatedPillarBlock> COPPER_PINE_LOG = BLOCKS.register("copper_pine_log", () -> new RotatedPillarBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_COPPER_PINE_LOG = BLOCKS.register("stripped_copper_pine_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG.get())));
	public static final RegistryObject<RotatedPillarBlock> COPPER_PINE_WOOD = BLOCKS.register("copper_pine_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG.get())));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_COPPER_PINE_WOOD = BLOCKS.register("stripped_copper_pine_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG.get())));
	public static final RegistryObject<Block> COPPER_PINE_PLANKS = BLOCKS.register("copper_pine_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG.get())));
	public static final RegistryObject<Block> COPPER_PINE_LEAVES = BLOCKS.register("copper_pine_leaves", () -> new LeavesWithAmbientParticlesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> COPPER_PINE_BOOKSHELF = BLOCKS.register("copper_pine_bookshelf", () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_PLANKS.get())));
	public static final RegistryObject<SaplingBlock> COPPER_PINE_SAPLING = BLOCKS.register("copper_pine_sapling", () -> new AerialHellSaplingBlock(AerialHellTreeGrowers.COPPER_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_COPPER_PINE, AerialHellConfiguredFeatures.HUGE_COPPER_PINE, 0.1F));
	public static final RegistryObject<Item> COPPER_PINE_LOG_ITEM = ITEMS.register("copper_pine_log", () -> new BurnableBlockItem(COPPER_PINE_LOG.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> STRIPPED_COPPER_PINE_LOG_ITEM = ITEMS.register("stripped_copper_pine_log", () -> new BurnableBlockItem(STRIPPED_COPPER_PINE_LOG.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> COPPER_PINE_WOOD_ITEM = ITEMS.register("copper_pine_wood", () -> new BurnableBlockItem(COPPER_PINE_WOOD.get(), new Item.Properties(), 210));
	public static final RegistryObject<Item> STRIPPED_COPPER_PINE_WOOD_ITEM = ITEMS.register("stripped_copper_pine_wood", () -> new BurnableBlockItem(STRIPPED_COPPER_PINE_WOOD.get(), new Item.Properties(), 210));
	public static final RegistryObject<Item> COPPER_PINE_PLANKS_ITEM = ITEMS.register("copper_pine_planks", () -> new BurnableBlockItem(COPPER_PINE_PLANKS.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> COPPER_PINE_LEAVES_ITEM = ITEMS.register("copper_pine_leaves", () -> new BlockItem(COPPER_PINE_LEAVES.get(), new Item.Properties()));
	public static final RegistryObject<Item> COPPER_PINE_BOOKSHELF_ITEM = ITEMS.register("copper_pine_bookshelf", () -> new BlockItem(COPPER_PINE_BOOKSHELF.get(), new Item.Properties()));
	public static final RegistryObject<Item> COPPER_PINE_SAPLING_ITEM = ITEMS.register("copper_pine_sapling", () -> new BlockItem(COPPER_PINE_SAPLING.get(), new Item.Properties()));

	//lapis robinia
	public static final RegistryObject<RotatedPillarBlock> LAPIS_ROBINIA_LOG = BLOCKS.register("lapis_robinia_log", () -> new RotatedPillarBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<RotatedPillarBlock> ENCHANTED_LAPIS_ROBINIA_LOG = BLOCKS.register("enchanted_lapis_robinia_log", () -> new EffectLogBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_LAPIS_ROBINIA_LOG = BLOCKS.register("stripped_lapis_robinia_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG.get())));
	public static final RegistryObject<RotatedPillarBlock> LAPIS_ROBINIA_WOOD = BLOCKS.register("lapis_robinia_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG.get())));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_LAPIS_ROBINIA_WOOD = BLOCKS.register("stripped_lapis_robinia_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG.get())));
	public static final RegistryObject<Block> LAPIS_ROBINIA_LEAVES = BLOCKS.register("lapis_robinia_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> LAPIS_ROBINIA_PLANKS = BLOCKS.register("lapis_robinia_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG.get())));
	public static final RegistryObject<Block> LAPIS_ROBINIA_BOOKSHELF = BLOCKS.register("lapis_robinia_bookshelf", () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_PLANKS.get())));
	public static final RegistryObject<SaplingBlock> LAPIS_ROBINIA_SAPLING = BLOCKS.register("lapis_robinia_sapling", () -> new AerialHellSaplingBlock(AerialHellTreeGrowers.LAPIS_ROBINIA, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_LAPIS_ROBINIA));
	public static final RegistryObject<Item> LAPIS_ROBINIA_LOG_ITEM = ITEMS.register("lapis_robinia_log", () -> new BurnableBlockItem(LAPIS_ROBINIA_LOG.get(), new Item.Properties(), 400));
	public static final RegistryObject<Item> ENCHANTED_LAPIS_ROBINIA_LOG_ITEM = ITEMS.register("enchanted_lapis_robinia_log", () -> new BurnableBlockItem(ENCHANTED_LAPIS_ROBINIA_LOG.get(), new Item.Properties(), 400));
	public static final RegistryObject<Item> STRIPPED_LAPIS_ROBINIA_LOG_ITEM = ITEMS.register("stripped_lapis_robinia_log", () -> new BurnableBlockItem(STRIPPED_LAPIS_ROBINIA_LOG.get(), new Item.Properties(), 400));
	public static final RegistryObject<Item> LAPIS_ROBINIA_WOOD_ITEM = ITEMS.register("lapis_robinia_wood", () -> new BurnableBlockItem(LAPIS_ROBINIA_WOOD.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> STRIPPED_LAPIS_ROBINIA_WOOD_ITEM = ITEMS.register("stripped_lapis_robinia_wood", () -> new BurnableBlockItem(STRIPPED_LAPIS_ROBINIA_WOOD.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> LAPIS_ROBINIA_LEAVES_ITEM = ITEMS.register("lapis_robinia_leaves", () -> new BlockItem(LAPIS_ROBINIA_LEAVES.get(), new Item.Properties()));
	public static final RegistryObject<Item> LAPIS_ROBINIA_PLANKS_ITEM = ITEMS.register("lapis_robinia_planks", () -> new BurnableBlockItem(LAPIS_ROBINIA_PLANKS.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> LAPIS_ROBINIA_BOOKSHELF_ITEM = ITEMS.register("lapis_robinia_bookshelf", () -> new BlockItem(LAPIS_ROBINIA_BOOKSHELF.get(), new Item.Properties()));
	public static final RegistryObject<Item> LAPIS_ROBINIA_SAPLING_ITEM = ITEMS.register("lapis_robinia_sapling", () -> new BlockItem(LAPIS_ROBINIA_SAPLING.get(), new Item.Properties()));

	//shadow_pine
	public static final RegistryObject<RotatedPillarBlock> SHADOW_PINE_LOG = BLOCKS.register("shadow_pine_log", () -> new RotatedPillarBlock(SHADOW_PINE_MATERIAL));
	public static final RegistryObject<RotatedPillarBlock> EYE_SHADOW_PINE_LOG = BLOCKS.register("eye_shadow_pine_log", () -> new EffectLogBlock(SHADOW_PINE_MATERIAL));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_SHADOW_PINE_LOG = BLOCKS.register("stripped_shadow_pine_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG.get())));
	public static final RegistryObject<RotatedPillarBlock> SHADOW_PINE_WOOD = BLOCKS.register("shadow_pine_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG.get())));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_SHADOW_PINE_WOOD = BLOCKS.register("stripped_shadow_pine_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG.get())));
	public static final RegistryObject<Block> SHADOW_PINE_LEAVES = BLOCKS.register("shadow_pine_leaves", () -> new LeavesWithAmbientParticlesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> PURPLE_SHADOW_PINE_LEAVES = BLOCKS.register("purple_shadow_pine_leaves", () -> new LeavesWithAmbientParticlesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> SHADOW_PINE_PLANKS = BLOCKS.register("shadow_pine_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG.get())));
	public static final RegistryObject<Block> SHADOW_PINE_BOOKSHELF = BLOCKS.register("shadow_pine_bookshelf", () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_PLANKS.get())));
	public static final RegistryObject<SaplingBlock> SHADOW_PINE_SAPLING = BLOCKS.register("shadow_pine_sapling", () -> new ShadowPineSaplingBlock(AerialHellTreeGrowers.SHADOW_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_SHADOW_PINE, AerialHellConfiguredFeatures.HUGE_SHADOW_PINE, 0.1F));
	public static final RegistryObject<SaplingBlock> PURPLE_SHADOW_PINE_SAPLING = BLOCKS.register("purple_shadow_pine_sapling", () -> new ShadowPineSaplingBlock(AerialHellTreeGrowers.PURPLE_SHADOW_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_PURPLE_SHADOW_PINE, AerialHellConfiguredFeatures.HUGE_PURPLE_SHADOW_PINE, 0.1F));
	public static final RegistryObject<Item> SHADOW_PINE_LOG_ITEM = ITEMS.register("shadow_pine_log", () -> new BurnableBlockItem(SHADOW_PINE_LOG.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> EYE_SHADOW_PINE_LOG_ITEM = ITEMS.register("eye_shadow_pine_log", () -> new BurnableBlockItem(EYE_SHADOW_PINE_LOG.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> STRIPPED_SHADOW_PINE_LOG_ITEM = ITEMS.register("stripped_shadow_pine_log", () -> new BurnableBlockItem(STRIPPED_SHADOW_PINE_LOG.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> SHADOW_PINE_WOOD_ITEM = ITEMS.register("shadow_pine_wood", () -> new BurnableBlockItem(SHADOW_PINE_WOOD.get(), new Item.Properties(), 210));
	public static final RegistryObject<Item> STRIPPED_SHADOW_PINE_WOOD_ITEM = ITEMS.register("stripped_shadow_pine_wood", () -> new BurnableBlockItem(STRIPPED_SHADOW_PINE_WOOD.get(), new Item.Properties(), 210));
	public static final RegistryObject<Item> SHADOW_PINE_LEAVES_ITEM = ITEMS.register("shadow_pine_leaves", () -> new BlockItem(SHADOW_PINE_LEAVES.get(), new Item.Properties()));
	public static final RegistryObject<Item> PURPLE_SHADOW_PINE_LEAVES_ITEM = ITEMS.register("purple_shadow_pine_leaves", () -> new BlockItem(PURPLE_SHADOW_PINE_LEAVES.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHADOW_PINE_PLANKS_ITEM = ITEMS.register("shadow_pine_planks", () -> new BurnableBlockItem(SHADOW_PINE_PLANKS.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> SHADOW_PINE_BOOKSHELF_ITEM = ITEMS.register("shadow_pine_bookshelf", () -> new BlockItem(SHADOW_PINE_BOOKSHELF.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHADOW_PINE_SAPLING_ITEM = ITEMS.register("shadow_pine_sapling", () -> new BlockItem(SHADOW_PINE_SAPLING.get(), new Item.Properties()));
	public static final RegistryObject<Item> PURPLE_SHADOW_PINE_SAPLING_ITEM = ITEMS.register("purple_shadow_pine_sapling", () -> new BlockItem(PURPLE_SHADOW_PINE_SAPLING.get(), new Item.Properties()));

	//stellar jungle tree
	public static final RegistryObject<RotatedPillarBlock> STELLAR_JUNGLE_TREE_LOG = BLOCKS.register("stellar_jungle_tree_log", () -> new RotatedPillarBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_STELLAR_JUNGLE_TREE_LOG = BLOCKS.register("stripped_stellar_jungle_tree_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG.get())));
	public static final RegistryObject<RotatedPillarBlock> STELLAR_JUNGLE_TREE_WOOD = BLOCKS.register("stellar_jungle_tree_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG.get())));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_STELLAR_JUNGLE_TREE_WOOD = BLOCKS.register("stripped_stellar_jungle_tree_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG.get())));
	public static final RegistryObject<Block> STELLAR_JUNGLE_TREE_LEAVES = BLOCKS.register("stellar_jungle_tree_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> STELLAR_JUNGLE_TREE_PLANKS = BLOCKS.register("stellar_jungle_tree_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG.get())));
	public static final RegistryObject<Block> STELLAR_JUNGLE_TREE_BOOKSHELF = BLOCKS.register("stellar_jungle_tree_bookshelf", () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_PLANKS.get())));
	public static final RegistryObject<SaplingBlock> STELLAR_JUNGLE_TREE_SAPLING = BLOCKS.register("stellar_jungle_tree_sapling", () -> new AerialHellSaplingBlock(AerialHellTreeGrowers.STELLAR_JUNGLE_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_STELLAR_JUNGLE_TREE));
	public static final RegistryObject<LargeDeadLogBlock> DEAD_STELLAR_JUNGLE_TREE_LOG = BLOCKS.register("dead_stellar_jungle_tree_log", () -> new LargeDeadLogBlock(STELLAR_JUNGLE_TREE_PLANKS.get().defaultBlockState(), COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_LOG_ITEM = ITEMS.register("stellar_jungle_tree_log", () -> new BurnableBlockItem(STELLAR_JUNGLE_TREE_LOG.get(), new Item.Properties(), 400));
	public static final RegistryObject<Item> STRIPPED_STELLAR_JUNGLE_TREE_LOG_ITEM = ITEMS.register("stripped_stellar_jungle_tree_log", () -> new BurnableBlockItem(STRIPPED_STELLAR_JUNGLE_TREE_LOG.get(), new Item.Properties(), 400));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_WOOD_ITEM = ITEMS.register("stellar_jungle_tree_wood", () -> new BurnableBlockItem(STELLAR_JUNGLE_TREE_WOOD.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> STRIPPED_STELLAR_JUNGLE_TREE_WOOD_ITEM = ITEMS.register("stripped_stellar_jungle_tree_wood", () -> new BurnableBlockItem(STRIPPED_STELLAR_JUNGLE_TREE_WOOD.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_LEAVES_ITEM = ITEMS.register("stellar_jungle_tree_leaves", () -> new BlockItem(STELLAR_JUNGLE_TREE_LEAVES.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_PLANKS_ITEM = ITEMS.register("stellar_jungle_tree_planks", () -> new BurnableBlockItem(STELLAR_JUNGLE_TREE_PLANKS.get(), new Item.Properties(), 300));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_BOOKSHELF_ITEM = ITEMS.register("stellar_jungle_tree_bookshelf", () -> new BlockItem(STELLAR_JUNGLE_TREE_BOOKSHELF.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_SAPLING_ITEM = ITEMS.register("stellar_jungle_tree_sapling", () -> new BlockItem(STELLAR_JUNGLE_TREE_SAPLING.get(), new Item.Properties()));
	public static final RegistryObject<Item> DEAD_STELLAR_JUNGLE_TREE_LOG_ITEM = ITEMS.register("dead_stellar_jungle_tree_log", () -> new BurnableBlockItem(DEAD_STELLAR_JUNGLE_TREE_LOG.get(), new Item.Properties(), 300));

	//shroom
	public static final RegistryObject<RotatedPillarBlock> GIANT_CORTINARIUS_VIOLACEUS_STEM = BLOCKS.register("giant_cortinarius_violaceus_stem", () -> new RotatedPillarBlock(SHROOM_MATERIAL));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM = BLOCKS.register("stripped_giant_cortinarius_violaceus_stem", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get())));
	public static final RegistryObject<RotatedPillarBlock> GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = BLOCKS.register("giant_cortinarius_violaceus_bark_stem", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get())));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = BLOCKS.register("stripped_giant_cortinarius_violaceus_bark_stem", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get())));
	public static final RegistryObject<Block> GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK = BLOCKS.register("giant_cortinarius_violaceus_cap_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).strength(0.5F).sound(SoundType.STEM)));
	public static final RegistryObject<Block> GIANT_CORTINARIUS_VIOLACEUS_LIGHT = BLOCKS.register("giant_cortinarius_violaceus_light", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(1.0F).sound(SoundType.SHROOMLIGHT).lightLevel((state) -> {return 15;})));
	public static final RegistryObject<FungusBlock> CORTINARIUS_VIOLACEUS = BLOCKS.register("cortinarius_violaceus", () -> new AerialHellFungusBlock(AerialHellConfiguredFeatures.GIANT_CORTINARIUS_VIOLACEUS_PLANTED, STELLAR_GRASS_BLOCK.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FUNGUS)));
	public static final RegistryObject<Item> GIANT_CORTINARIUS_VIOLACEUS_STEM_ITEM = ITEMS.register("giant_cortinarius_violaceus_stem", () -> new BurnableBlockItem(GIANT_CORTINARIUS_VIOLACEUS_STEM.get(), new Item.Properties(), 100));
	public static final RegistryObject<Item> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM_ITEM = ITEMS.register("stripped_giant_cortinarius_violaceus_stem", () -> new BurnableBlockItem(STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM.get(), new Item.Properties(), 100));
	public static final RegistryObject<Item> GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM_ITEM = ITEMS.register("giant_cortinarius_violaceus_bark_stem", () -> new BurnableBlockItem(GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.get(), new Item.Properties(), 100));
	public static final RegistryObject<Item> STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM_ITEM = ITEMS.register("stripped_giant_cortinarius_violaceus_bark_stem", () -> new BurnableBlockItem(STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.get(), new Item.Properties(), 100));
	public static final RegistryObject<Item> GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK_ITEM = ITEMS.register("giant_cortinarius_violaceus_cap_block", () -> new BlockItem(GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> GIANT_CORTINARIUS_VIOLACEUS_LIGHT_ITEM = ITEMS.register("giant_cortinarius_violaceus_light", () -> new BlockItem(GIANT_CORTINARIUS_VIOLACEUS_LIGHT.get(), new Item.Properties()));
	public static final RegistryObject<Item> CORTINARIUS_VIOLACEUS_ITEM = ITEMS.register("cortinarius_violaceus", () -> new BlockItem(CORTINARIUS_VIOLACEUS.get(), new Item.Properties()));
	public static final RegistryObject<Block> GLOWING_BOLETUS = BLOCKS.register("glowing_boletus", () -> new AerialHellTallShroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().lightLevel((state) -> {return 9;}).instabreak().sound(SoundType.GLOW_LICHEN), true));
	public static final RegistryObject<Block> TALL_GLOWING_BOLETUS = BLOCKS.register("tall_glowing_boletus", () -> new DoubleShroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().lightLevel((state) -> {return 11;}).instabreak().sound(SoundType.GLOW_LICHEN)));
	public static final RegistryObject<Block> BLUE_MEANIE_CLUSTER = BLOCKS.register("blue_meanie_cluster", () -> new TallShroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)));
	public static final RegistryObject<Block> GIANT_ROOT_SHROOM = BLOCKS.register("giant_root_shroom", () -> new AerialHellTallShroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.NETHER_WART), false));

	public static final RegistryObject<RotatedPillarBlock> GIANT_VERDIGRIS_AGARIC_STEM = BLOCKS.register("giant_verdigris_agaric_stem", () -> new RotatedPillarBlock(SHROOM_MATERIAL));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM = BLOCKS.register("stripped_giant_verdigris_agaric_stem", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get())));
	public static final RegistryObject<RotatedPillarBlock> GIANT_VERDIGRIS_AGARIC_BARK_STEM = BLOCKS.register("giant_verdigris_agaric_bark_stem", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get())));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM = BLOCKS.register("stripped_giant_verdigris_agaric_bark_stem", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get())));
	public static final RegistryObject<Block> GIANT_VERDIGRIS_AGARIC_CAP_BLOCK = BLOCKS.register("giant_verdigris_agaric_cap_block", () -> new HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).lightLevel((state) -> {return 10;}).strength(0.4F).sound(SoundType.STEM)));
	public static final RegistryObject<MushroomBlock> VERDIGRIS_AGARIC = BLOCKS.register("verdigris_agaric", () -> new AerialHellMushroomBlock(AerialHellConfiguredFeatures.GIANT_VERDIGRIS_AGARIC, BlockBehaviour.Properties.of().mapColor(MapColor.CLAY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Item> GIANT_VERDIGRIS_AGARIC_STEM_ITEM = ITEMS.register("giant_verdigris_agaric_stem", () -> new BurnableBlockItem(GIANT_VERDIGRIS_AGARIC_STEM.get(), new Item.Properties(), 100));
	public static final RegistryObject<Item> STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM_ITEM = ITEMS.register("stripped_giant_verdigris_agaric_stem", () -> new BurnableBlockItem(STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM.get(), new Item.Properties(), 100));
	public static final RegistryObject<Item> GIANT_VERDIGRIS_AGARIC_BARK_STEM_ITEM = ITEMS.register("giant_verdigris_agaric_bark_stem", () -> new BurnableBlockItem(GIANT_VERDIGRIS_AGARIC_BARK_STEM.get(), new Item.Properties(), 75));
	public static final RegistryObject<Item> STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM_ITEM = ITEMS.register("stripped_giant_verdigris_agaric_bark_stem", () -> new BurnableBlockItem(STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM.get(), new Item.Properties(), 75));
	public static final RegistryObject<Item> GIANT_VERDIGRIS_AGARIC_CAP_BLOCK_ITEM = ITEMS.register("giant_verdigris_agaric_cap_block", () -> new BlockItem(GIANT_VERDIGRIS_AGARIC_CAP_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> VERDIGRIS_AGARIC_ITEM = ITEMS.register("verdigris_agaric", () -> new BlockItem(VERDIGRIS_AGARIC.get(), new Item.Properties()));
	public static final RegistryObject<Item> GLOWING_BOLETUS_ITEM = ITEMS.register("glowing_boletus", () -> new BlockItem(GLOWING_BOLETUS.get(), new Item.Properties()));
	public static final RegistryObject<Item> TALL_GLOWING_BOLETUS_ITEM = ITEMS.register("tall_glowing_boletus", () -> new BlockItem(TALL_GLOWING_BOLETUS.get(), new Item.Properties()));
	public static final RegistryObject<Item> BLUE_MEANIE_CLUSTER_ITEM = ITEMS.register("blue_meanie_cluster", () -> new BlockItem(BLUE_MEANIE_CLUSTER.get(), new Item.Properties()));
	public static final RegistryObject<Item> GIANT_ROOT_SHROOM_ITEM = ITEMS.register("giant_root_shroom", () -> new BlockItem(GIANT_ROOT_SHROOM.get(), new Item.Properties()));

	public static final RegistryObject<Block> GIANT_GANODERMA_APPLANATUM_BLOCK = BLOCKS.register("giant_ganoderma_applanatum_block", () -> new HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.4F).sound(SoundType.STEM)));
	public static final RegistryObject<Item> GIANT_GANODERMA_APPLANATUM_BLOCK_ITEM = ITEMS.register("giant_ganoderma_applanatum_block", () -> new BlockItem(GIANT_GANODERMA_APPLANATUM_BLOCK.get(), new Item.Properties()));

	public static final RegistryObject<Block> GRAY_SHROOM_PLANKS = BLOCKS.register("gray_shroom_planks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM.get())));
	public static final RegistryObject<Block> GRAY_SHROOM_BOOKSHELF = BLOCKS.register("gray_shroom_bookshelf", () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_PLANKS.get())));
	public static final RegistryObject<Item> GRAY_SHROOM_PLANKS_ITEM = ITEMS.register("gray_shroom_planks", () -> new BurnableBlockItem(GRAY_SHROOM_PLANKS.get(), new Item.Properties(), 100));
	public static final RegistryObject<Item> GRAY_SHROOM_BOOKSHELF_ITEM = ITEMS.register("gray_shroom_bookshelf", () -> new BlockItem(GRAY_SHROOM_BOOKSHELF.get(), new Item.Properties()));

	//ladder
	public static final RegistryObject<LadderBlock> SKY_LADDER = BLOCKS.register("sky_ladder", () -> new LadderBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<Item> SKY_LADDER_ITEM = ITEMS.register("sky_ladder", () -> new BlockItem(SKY_LADDER.get(), new Item.Properties()));

	//natural blocks and items
	public static final RegistryObject<Block> STELLAR_STONE = BLOCKS.register("stellar_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
	public static final RegistryObject<Block> STELLAR_COBBLESTONE = BLOCKS.register("stellar_cobblestone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
	public static final RegistryObject<Block> STELLAR_STONE_BRICKS = BLOCKS.register("stellar_stone_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).strength(0.5F, 10.0F)));
	public static final RegistryObject<Block> MOSSY_STELLAR_STONE = BLOCKS.register("mossy_stellar_stone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get())));
	public static final RegistryObject<Block> MOSSY_STELLAR_COBBLESTONE = BLOCKS.register("mossy_stellar_cobblestone", () -> new Block(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get())));
	public static final RegistryObject<Block> STELLAR_CLAY = BLOCKS.register("stellar_clay", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CLAY)));
	public static final RegistryObject<Block> GLAUCOPHANITE = BLOCKS.register("glaucophanite",() -> new Block(BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> POLISHED_GLAUCOPHANITE = BLOCKS.register("polished_glaucophanite",() -> new Block(BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Item> STELLAR_STONE_ITEM = ITEMS.register("stellar_stone", () -> new BlockItem(STELLAR_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_COBBLESTONE_ITEM = ITEMS.register("stellar_cobblestone", () -> new BlockItem(STELLAR_COBBLESTONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> MOSSY_STELLAR_STONE_ITEM = ITEMS.register("mossy_stellar_stone", () -> new BlockItem(MOSSY_STELLAR_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> MOSSY_STELLAR_COBBLESTONE_ITEM = ITEMS.register("mossy_stellar_cobblestone", () -> new BlockItem(MOSSY_STELLAR_COBBLESTONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_CLAY_ITEM = ITEMS.register("stellar_clay", () -> new BlockItem(STELLAR_CLAY.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_STONE_BRICKS_ITEM = ITEMS.register("stellar_stone_bricks", () -> new BlockItem(STELLAR_STONE_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> GLAUCOPHANITE_ITEM = ITEMS.register("glaucophanite", () -> new BlockItem(GLAUCOPHANITE.get(), new Item.Properties()));
	public static final RegistryObject<Item> POLISHED_GLAUCOPHANITE_ITEM = ITEMS.register("polished_glaucophanite", () -> new BlockItem(POLISHED_GLAUCOPHANITE.get(), new Item.Properties()));

	//crystal
	public static final RegistryObject<Block> CRYSTAL_BLOCK = BLOCKS.register("crystal_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).lightLevel((state) -> 14)));
	public static final RegistryObject<Item> CRYSTAL_BLOCK_ITEM = ITEMS.register("crystal_block", () -> new BlockItem(CRYSTAL_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Block> CRYSTAL_BRICKS = BLOCKS.register("crystal_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).lightLevel((state) -> 9)));
	public static final RegistryObject<Item> CRYSTAL_BRICKS_ITEM = ITEMS.register("crystal_bricks", () -> new BlockItem(CRYSTAL_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<SlabBlock> CRYSTAL_BRICKS_SLAB = BLOCKS.register("crystal_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BRICKS.get())));
	public static final RegistryObject<Item> CRYSTAL_BRICKS_SLAB_ITEM = ITEMS.register("crystal_bricks_slab", () -> new BlockItem(CRYSTAL_BRICKS_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<StairBlock> CRYSTAL_BRICKS_STAIRS = BLOCKS.register("crystal_bricks_stairs", () -> new StairBlock(CRYSTAL_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BRICKS.get())));
	public static final RegistryObject<Item> CRYSTAL_BRICKS_STAIRS_ITEM = ITEMS.register("crystal_bricks_stairs", () -> new BlockItem(CRYSTAL_BRICKS_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<WallBlock> CRYSTAL_BRICKS_WALL = BLOCKS.register("crystal_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BRICKS.get())));
	public static final RegistryObject<Item> CRYSTAL_BRICKS_WALL_ITEM = ITEMS.register("crystal_bricks_wall", () -> new BlockItem(CRYSTAL_BRICKS_WALL.get(), new Item.Properties()));
	public static final RegistryObject<Block> STELLAR_STONE_CRYSTAL_BLOCK = BLOCKS.register("stellar_stone_crystal_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BLOCK.get()).lightLevel((state) -> 13)));
	public static final RegistryObject<Item> STELLAR_STONE_CRYSTAL_BLOCK_ITEM = ITEMS.register("stellar_stone_crystal_block", () -> new BlockItem(STELLAR_STONE_CRYSTAL_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Block> SHADOW_CRYSTAL_BLOCK = BLOCKS.register("shadow_crystal_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BLOCK.get()).lightLevel((state) -> 12)));
	public static final RegistryObject<Item> SHADOW_CRYSTAL_BLOCK_ITEM = ITEMS.register("shadow_crystal_block", () -> new BlockItem(SHADOW_CRYSTAL_BLOCK.get(), new Item.Properties().rarity(AerialHellRarities.CORRUPTED)));
	public static final RegistryObject<Block> CRYSTALLIZED_LEAVES = BLOCKS.register("crystallized_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).lightLevel((state) -> 12)));
	public static final RegistryObject<Block> CRYSTALLIZED_FIRE = BLOCKS.register("crystallized_fire", () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).lightLevel((state) -> 12).instabreak()));
	public static final RegistryObject<Item> CRYSTALLIZED_LEAVES_ITEM = ITEMS.register("crystallized_leaves", () -> new BlockItem(CRYSTALLIZED_LEAVES.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRYSTAL = ITEMS.register("crystal", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_STONE_CRYSTAL = ITEMS.register("stellar_stone_crystal", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> SHADOW_CRYSTAL = ITEMS.register("shadow_crystal", () -> new Item(new Item.Properties().rarity(AerialHellRarities.CORRUPTED)));

	//glass and glass pane
	public static final RegistryObject<Block> SLIPPERY_SAND_GLASS = BLOCKS.register("slippery_sand_glass", () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
	public static final RegistryObject<Block> RED_SLIPPERY_SAND_GLASS = BLOCKS.register("red_slippery_sand_glass", () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
	public static final RegistryObject<Block> BLACK_SLIPPERY_SAND_GLASS = BLOCKS.register("black_slippery_sand_glass", () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
	public static final RegistryObject<Block> BLUE_SLIPPERY_SAND_GLASS = BLOCKS.register("blue_slippery_sand_glass", () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
	public static final RegistryObject<Block> GREEN_SLIPPERY_SAND_GLASS = BLOCKS.register("green_slippery_sand_glass", () -> new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
	public static final RegistryObject<Block> SLIPPERY_SAND_GLASS_PANE = BLOCKS.register("slippery_sand_glass_pane", () -> new StainedGlassPaneBlock(DyeColor.YELLOW, BlockBehaviour.Properties.of().friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
	public static final RegistryObject<Block> RED_SLIPPERY_SAND_GLASS_PANE = BLOCKS.register("red_slippery_sand_glass_pane", () -> new StainedGlassPaneBlock(DyeColor.RED, BlockBehaviour.Properties.of().friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
	public static final RegistryObject<Block> BLACK_SLIPPERY_SAND_GLASS_PANE = BLOCKS.register("black_slippery_sand_glass_pane", () -> new StainedGlassPaneBlock(DyeColor.BLACK, BlockBehaviour.Properties.of().friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
	public static final RegistryObject<Block> BLUE_SLIPPERY_SAND_GLASS_PANE = BLOCKS.register("blue_slippery_sand_glass_pane", () -> new StainedGlassPaneBlock(DyeColor.BLUE, BlockBehaviour.Properties.of().friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
	public static final RegistryObject<Block> GREEN_SLIPPERY_SAND_GLASS_PANE = BLOCKS.register("green_slippery_sand_glass_pane", () -> new StainedGlassPaneBlock(DyeColor.GREEN, BlockBehaviour.Properties.of().friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
	public static final RegistryObject<Item> SLIPPERY_SAND_GLASS_ITEM = ITEMS.register("slippery_sand_glass", () -> new BlockItem(SLIPPERY_SAND_GLASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> RED_SLIPPERY_SAND_GLASS_ITEM = ITEMS.register("red_slippery_sand_glass", () -> new BlockItem(RED_SLIPPERY_SAND_GLASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> BLACK_SLIPPERY_SAND_GLASS_ITEM = ITEMS.register("black_slippery_sand_glass", () -> new BlockItem(BLACK_SLIPPERY_SAND_GLASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> BLUE_SLIPPERY_SAND_GLASS_ITEM = ITEMS.register("blue_slippery_sand_glass", () -> new BlockItem(BLUE_SLIPPERY_SAND_GLASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> GREEN_SLIPPERY_SAND_GLASS_ITEM = ITEMS.register("green_slippery_sand_glass", () -> new BlockItem(GREEN_SLIPPERY_SAND_GLASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> SLIPPERY_SAND_GLASS_PANE_ITEM = ITEMS.register("slippery_sand_glass_pane", () -> new BlockItem(SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties()));
	public static final RegistryObject<Item> RED_SLIPPERY_SAND_GLASS_PANE_ITEM = ITEMS.register("red_slippery_sand_glass_pane", () -> new BlockItem(RED_SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties()));
	public static final RegistryObject<Item> BLACK_SLIPPERY_SAND_GLASS_PANE_ITEM = ITEMS.register("black_slippery_sand_glass_pane", () -> new BlockItem(BLACK_SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties()));
	public static final RegistryObject<Item> BLUE_SLIPPERY_SAND_GLASS_PANE_ITEM = ITEMS.register("blue_slippery_sand_glass_pane", () -> new BlockItem(BLUE_SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties()));
	public static final RegistryObject<Item> GREEN_SLIPPERY_SAND_GLASS_PANE_ITEM = ITEMS.register("green_slippery_sand_glass_pane", () -> new BlockItem(GREEN_SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties()));

	//ghost boat
	public static final RegistryObject<Block> GHOST_BOAT_PLANKS = BLOCKS.register("ghost_boat_planks", () -> new GhostBoatBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<GhostBoatRotatedPillarBlock> GHOST_BOAT_LOG = BLOCKS.register("ghost_boat_log", () -> new GhostBoatRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG.get()).noOcclusion()));
	public static final RegistryObject<SlabBlock> GHOST_BOAT_SLAB = BLOCKS.register("ghost_boat_slab", () -> new GhostBoatSlabBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<StairBlock> GHOST_BOAT_STAIRS = BLOCKS.register("ghost_boat_stairs", () -> new GhostBoatStairBlock(GHOST_BOAT_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<FenceBlock> GHOST_BOAT_FENCE = BLOCKS.register("ghost_boat_fence", () -> new GhostBoatFenceBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<FenceGateBlock> GHOST_BOAT_GATE = BLOCKS.register("ghost_boat_gate", () -> new GhostBoatFenceGateBlock(AerialHellWoodTypes.AERIAL_TREE, BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<DoorBlock> GHOST_BOAT_DOOR = BLOCKS.register("ghost_boat_door", () -> new GhostBoatDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<TrapDoorBlock> GHOST_BOAT_TRAPDOOR = BLOCKS.register("ghost_boat_trapdoor", () -> new GhostBoatTrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<ChestBlock> GHOST_BOAT_CHEST = BLOCKS.register("ghost_boat_chest", () -> new GhostBoatChestBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<Block> GHOST_BOAT_WOOL = BLOCKS.register("ghost_boat_wool", () -> new GhostBoatBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL).noOcclusion()));
	public static final RegistryObject<Block> GHOST_STELLAR_COBBLESTONE = BLOCKS.register("ghost_stellar_cobblestone", () -> new GhostBoatBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).requiresCorrectToolForDrops().sound(SoundType.STONE).noOcclusion()));
	public static final RegistryObject<Block> GHOST_RUBY_BLOCK = BLOCKS.register("ghost_ruby_block", () -> new GhostBoatBlock(BlockBehaviour.Properties.of().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));
	public static final RegistryObject<Block> GHOST_FLUORITE_BLOCK = BLOCKS.register("ghost_fluorite_block", () -> new GhostBoatBlock(BlockBehaviour.Properties.of().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));
	public static final RegistryObject<AerialHellBarrelBlock> GHOST_BOAT_BARREL = BLOCKS.register("ghost_boat_barrel", () -> new GhostBoatBarrelBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<CraftingTableBlock> GHOST_BOAT_CRAFTING_TABLE = BLOCKS.register("ghost_boat_crafting_table", () -> new GhostBoatCraftingTableBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
	public static final RegistryObject<RotatedPillarBlock> GHOST_BOAT_VINE_ROPE_SPOOL = BLOCKS.register("ghost_boat_vine_rope_spool", () -> new GhostBoatVineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final RegistryObject<Block> GHOST_LANTERN = BLOCKS.register("ghost_lantern", () -> new GhostLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));

	public static final RegistryObject<Item> GHOST_BOAT_PLANKS_ITEM = ITEMS.register("ghost_boat_planks", () -> new BlockItem(GHOST_BOAT_PLANKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> GHOST_BOAT_LOG_ITEM = ITEMS.register("ghost_boat_log", () -> new BlockItem(GHOST_BOAT_LOG.get(), new Item.Properties()));
	public static final RegistryObject<Item> GHOST_BOAT_SLAB_ITEM = ITEMS.register("ghost_boat_slab", () -> new BlockItem(GHOST_BOAT_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<Item> GHOST_BOAT_STAIRS_ITEM = ITEMS.register("ghost_boat_stairs", () -> new BlockItem(GHOST_BOAT_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<Item> GHOST_BOAT_FENCE_ITEM = ITEMS.register("ghost_boat_fence", () -> new BlockItem(GHOST_BOAT_FENCE.get(), new Item.Properties()));
	public static final RegistryObject<Item> GHOST_BOAT_GATE_ITEM = ITEMS.register("ghost_boat_gate", () -> new BlockItem(GHOST_BOAT_GATE.get(), new Item.Properties()));
	public static final RegistryObject<Item> GHOST_BOAT_DOOR_ITEM = ITEMS.register("ghost_boat_door", () -> new BlockItem(GHOST_BOAT_DOOR.get(), new Item.Properties()));
	public static final RegistryObject<Item> GHOST_BOAT_TRAPDOOR_ITEM = ITEMS.register("ghost_boat_trapdoor", () -> new BlockItem(GHOST_BOAT_TRAPDOOR.get(), new Item.Properties()));
	public static final RegistryObject<Item> GHOST_BOAT_CHEST_ITEM = ITEMS.register("ghost_boat_chest", () -> new ChestBlockItem(GHOST_BOAT_CHEST.get(), new Item.Properties()));
	public static final RegistryObject<Item> GHOST_BOAT_WOOL_ITEM = ITEMS.register("ghost_boat_wool", () -> new BlockItem(GHOST_BOAT_WOOL.get(), new Item.Properties()));
	public static final RegistryObject<Item> GHOST_STELLAR_COBBLESTONE_ITEM = ITEMS.register("ghost_stellar_cobblestone", () -> new BlockItem(GHOST_STELLAR_COBBLESTONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> GHOST_RUBY_BLOCK_ITEM = ITEMS.register("ghost_ruby_block", () -> new BlockItem(GHOST_RUBY_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> GHOST_FLUORITE_BLOCK_ITEM = ITEMS.register("ghost_fluorite_block", () -> new BlockItem(GHOST_FLUORITE_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> GHOST_BOAT_BARREL_ITEM = ITEMS.register("ghost_boat_barrel", () -> new BlockItem(GHOST_BOAT_BARREL.get(), new Item.Properties()));
	public static final RegistryObject<Item> GHOST_BOAT_CRAFTING_TABLE_ITEM = ITEMS.register("ghost_boat_crafting_table", () -> new BlockItem(GHOST_BOAT_CRAFTING_TABLE.get(), new Item.Properties()));
	public static final RegistryObject<Item> GHOST_BOAT_VINE_ROPE_SPOOL_ITEM = ITEMS.register("ghost_boat_vine_rope_spool", () -> new BlockItem(GHOST_BOAT_VINE_ROPE_SPOOL.get(), new Item.Properties()));
	public static final RegistryObject<Item> GHOST_LANTERN_ITEM = ITEMS.register("ghost_lantern", () -> new BlockItem(GHOST_LANTERN.get(), new Item.Properties()));

	//solid_ethers
	public static final RegistryObject<Block> WHITE_SOLID_ETHER = BLOCKS.register("white_solid_ether", () -> new SolidEtherBlock(BlockBehaviour.Properties.of().strength(0.2F).sound(SoundType.WOOL).noOcclusion()));
	public static final RegistryObject<Block> BLUE_SOLID_ETHER = BLOCKS.register("blue_solid_ether", () -> new BlueSolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER.get())));
	public static final RegistryObject<Block> GOLDEN_SOLID_ETHER = BLOCKS.register("golden_solid_ether", () -> new SolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER.get())));
	public static final RegistryObject<Block> GREEN_SOLID_ETHER = BLOCKS.register("green_solid_ether", () -> new GreenSolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER.get())));
	public static final RegistryObject<Block> PURPLE_SOLID_ETHER = BLOCKS.register("purple_solid_ether", () -> new PurpleSolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER.get())));

	public static final RegistryObject<Item> WHITE_SOLID_ETHER_ITEM = ITEMS.register("white_solid_ether", () -> new BlockItem(WHITE_SOLID_ETHER.get(), new Item.Properties()));
	public static final RegistryObject<Item> BLUE_SOLID_ETHER_ITEM = ITEMS.register("blue_solid_ether", () -> new BlockItem(BLUE_SOLID_ETHER.get(), new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_SOLID_ETHER_ITEM = ITEMS.register("golden_solid_ether", () -> new BlockItem(GOLDEN_SOLID_ETHER.get(), new Item.Properties()));
	public static final RegistryObject<Item> GREEN_SOLID_ETHER_ITEM = ITEMS.register("green_solid_ether", () -> new BlockItem(GREEN_SOLID_ETHER.get(), new Item.Properties()));
	public static final RegistryObject<Item> PURPLE_SOLID_ETHER_ITEM = ITEMS.register("purple_solid_ether", () -> new BlockItem(PURPLE_SOLID_ETHER.get(), new Item.Properties()));

	//dungeons blocks
	public static final RegistryObject<Block> MUD_BRICKS = BLOCKS.register("mud_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> CRACKED_MUD_BRICKS = BLOCKS.register("cracked_mud_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get())));
	public static final RegistryObject<Block> MOSSY_MUD_BRICKS = BLOCKS.register("mossy_mud_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get())));
	public static final RegistryObject<Block> CHISELED_MUD_BRICKS = BLOCKS.register("chiseled_mud_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get())));
	public static final RegistryObject<Block> LIGHT_MUD_BRICKS = BLOCKS.register("light_mud_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get()).lightLevel((state) -> 11)));
	public static final RegistryObject<Block> CRACKED_LIGHT_MUD_BRICKS = BLOCKS.register("cracked_light_mud_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get())));
	public static final RegistryObject<Block> LUNATIC_STONE = BLOCKS.register("lunatic_stone", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> DARK_LUNATIC_STONE = BLOCKS.register("dark_lunatic_stone", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> ROOF_LUNATIC_STONE = BLOCKS.register("roof_lunatic_stone", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> CRACKED_LUNATIC_STONE = BLOCKS.register("cracked_lunatic_stone", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> CHISELED_LUNATIC_STONE = BLOCKS.register("chiseled_lunatic_stone", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> LIGHT_LUNATIC_STONE = BLOCKS.register("light_lunatic_stone", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).lightLevel((state) -> 11)));
	public static final RegistryObject<Block> ROOF_LIGHT_LUNATIC_STONE = BLOCKS.register("roof_light_lunatic_stone", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).lightLevel((state) -> 11)));
	public static final RegistryObject<Block> CRACKED_LIGHT_LUNATIC_STONE = BLOCKS.register("cracked_light_lunatic_stone", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get())));
	public static final RegistryObject<Block> SHADOW_CATACOMBS_BRICKS = BLOCKS.register("shadow_catacombs_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> CRACKED_SHADOW_CATACOMBS_BRICKS = BLOCKS.register("cracked_shadow_catacombs_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get())));
	public static final RegistryObject<Block> MOSSY_SHADOW_CATACOMBS_BRICKS = BLOCKS.register("mossy_shadow_catacombs_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get())));
	public static final RegistryObject<Block> CHISELED_SHADOW_CATACOMBS_BRICKS = BLOCKS.register("chiseled_shadow_catacombs_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get())));
	public static final RegistryObject<Block> BONE_SHADOW_CATACOMBS_BRICKS = BLOCKS.register("bone_shadow_catacombs_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).sound(SoundType.BONE_BLOCK)));
	public static final RegistryObject<Block> SKULL_SHADOW_CATACOMBS_BRICKS = BLOCKS.register("skull_shadow_catacombs_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).sound(SoundType.BONE_BLOCK)));
	public static final RegistryObject<Block> LIGHT_SHADOW_CATACOMBS_BRICKS = BLOCKS.register("light_shadow_catacombs_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).lightLevel((state) -> 11)));
	public static final RegistryObject<Block> CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS = BLOCKS.register("cracked_light_shadow_catacombs_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get())));
	public static final RegistryObject<Block> GOLDEN_NETHER_BRICKS  = BLOCKS.register("golden_nether_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> CRACKED_GOLDEN_NETHER_BRICKS  = BLOCKS.register("cracked_golden_nether_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> CHISELED_GOLDEN_NETHER_BRICKS  = BLOCKS.register("chiseled_golden_nether_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> LIGHT_GOLDEN_NETHER_BRICKS = BLOCKS.register("light_golden_nether_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get()).lightLevel((state) -> 11)));
	public static final RegistryObject<Block> CRACKED_LIGHT_GOLDEN_NETHER_BRICKS = BLOCKS.register("cracked_light_golden_nether_bricks", () -> new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get())));
	public static final RegistryObject<RotatedPillarBlock> LUNATIC_PILLAR = BLOCKS.register("lunatic_pillar", () -> new CoreProtectedRotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(2.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	public static final RegistryObject<RotatedPillarBlock> LUNATIC_PILLAR_TOP = BLOCKS.register("lunatic_pillar_top", () -> new CoreProtectedRotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(2.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> VOLUCITE_STONE = BLOCKS.register("volucite_stone", () -> new CoreProtectedBlock(Block.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> CRACKED_VOLUCITE_STONE = BLOCKS.register("cracked_volucite_stone", () -> new CoreProtectedBlock(Block.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> CHISELED_VOLUCITE_STONE = BLOCKS.register("chiseled_volucite_stone", () -> new CoreProtectedBlock(Block.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> LIGHT_VOLUCITE_STONE = BLOCKS.register("light_volucite_stone", () -> new CoreProtectedBlock(Block.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).lightLevel((state) -> 11)));
	public static final RegistryObject<Block> CRACKED_LIGHT_VOLUCITE_STONE = BLOCKS.register("cracked_light_volucite_stone", () -> new CoreProtectedBlock(Block.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE)));

	public static final RegistryObject<Item> MUD_BRICKS_ITEM = ITEMS.register("mud_bricks", () -> new BlockItem(MUD_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_MUD_BRICKS_ITEM = ITEMS.register("cracked_mud_bricks", () -> new BlockItem(CRACKED_MUD_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> MOSSY_MUD_BRICKS_ITEM = ITEMS.register("mossy_mud_bricks", () -> new BlockItem(MOSSY_MUD_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHISELED_MUD_BRICKS_ITEM = ITEMS.register("chiseled_mud_bricks", () -> new BlockItem(CHISELED_MUD_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> LIGHT_MUD_BRICKS_ITEM = ITEMS.register("light_mud_bricks", () -> new BlockItem(LIGHT_MUD_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_LIGHT_MUD_BRICKS_ITEM = ITEMS.register("cracked_light_mud_bricks", () -> new BlockItem(CRACKED_LIGHT_MUD_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> LUNATIC_STONE_ITEM = ITEMS.register("lunatic_stone", () -> new BlockItem(LUNATIC_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> DARK_LUNATIC_STONE_ITEM = ITEMS.register("dark_lunatic_stone", () -> new BlockItem(DARK_LUNATIC_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> ROOF_LUNATIC_STONE_ITEM = ITEMS.register("roof_lunatic_stone", () -> new BlockItem(ROOF_LUNATIC_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_LUNATIC_STONE_ITEM = ITEMS.register("cracked_lunatic_stone", () -> new BlockItem(CRACKED_LUNATIC_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHISELED_LUNATIC_STONE_ITEM = ITEMS.register("chiseled_lunatic_stone", () -> new BlockItem(CHISELED_LUNATIC_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> LIGHT_LUNATIC_STONE_ITEM = ITEMS.register("light_lunatic_stone", () -> new BlockItem(LIGHT_LUNATIC_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> ROOF_LIGHT_LUNATIC_STONE_ITEM = ITEMS.register("roof_light_lunatic_stone", () -> new BlockItem(ROOF_LIGHT_LUNATIC_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_LIGHT_LUNATIC_STONE_ITEM = ITEMS.register("cracked_light_lunatic_stone", () -> new BlockItem(CRACKED_LIGHT_LUNATIC_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHADOW_CATACOMBS_BRICKS_ITEM = ITEMS.register("shadow_catacombs_bricks", () -> new BlockItem(SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_SHADOW_CATACOMBS_BRICKS_ITEM = ITEMS.register("cracked_shadow_catacombs_bricks", () -> new BlockItem(CRACKED_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> MOSSY_SHADOW_CATACOMBS_BRICKS_ITEM = ITEMS.register("mossy_shadow_catacombs_bricks", () -> new BlockItem(MOSSY_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHISELED_SHADOW_CATACOMBS_BRICKS_ITEM = ITEMS.register("chiseled_shadow_catacombs_bricks", () -> new BlockItem(CHISELED_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> BONE_SHADOW_CATACOMBS_BRICKS_ITEM = ITEMS.register("bone_shadow_catacombs_bricks", () -> new BlockItem(BONE_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> SKULL_SHADOW_CATACOMBS_BRICKS_ITEM = ITEMS.register("skull_shadow_catacombs_bricks", () -> new BlockItem(SKULL_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> LIGHT_SHADOW_CATACOMBS_BRICKS_ITEM = ITEMS.register("light_shadow_catacombs_bricks", () -> new BlockItem(LIGHT_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS_ITEM = ITEMS.register("cracked_light_shadow_catacombs_bricks", () -> new BlockItem(CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_NETHER_BRICKS_ITEM = ITEMS.register("golden_nether_bricks", () -> new BlockItem(GOLDEN_NETHER_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_GOLDEN_NETHER_BRICKS_ITEM = ITEMS.register("cracked_golden_nether_bricks", () -> new BlockItem(CRACKED_GOLDEN_NETHER_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHISELED_GOLDEN_NETHER_BRICKS_ITEM = ITEMS.register("chiseled_golden_nether_bricks", () -> new BlockItem(CHISELED_GOLDEN_NETHER_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> LIGHT_GOLDEN_NETHER_BRICKS_ITEM = ITEMS.register("light_golden_nether_bricks", () -> new BlockItem(LIGHT_GOLDEN_NETHER_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_LIGHT_GOLDEN_NETHER_BRICKS_ITEM = ITEMS.register("cracked_light_golden_nether_bricks", () -> new BlockItem(CRACKED_LIGHT_GOLDEN_NETHER_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> LUNATIC_PILLAR_ITEM = ITEMS.register("lunatic_pillar", () -> new BlockItem(LUNATIC_PILLAR.get(), new Item.Properties()));
	public static final RegistryObject<Item> LUNATIC_PILLAR_TOP_ITEM = ITEMS.register("lunatic_pillar_top", () -> new BlockItem(LUNATIC_PILLAR_TOP.get(), new Item.Properties()));
	public static final RegistryObject<Item> VOLUCITE_STONE_ITEM = ITEMS.register("volucite_stone", () -> new BlockItem(VOLUCITE_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_VOLUCITE_STONE_ITEM = ITEMS.register("cracked_volucite_stone", () -> new BlockItem(CRACKED_VOLUCITE_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHISELED_VOLUCITE_STONE_ITEM = ITEMS.register("chiseled_volucite_stone", () -> new BlockItem(CHISELED_VOLUCITE_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> LIGHT_VOLUCITE_STONE_ITEM = ITEMS.register("light_volucite_stone", () -> new BlockItem(LIGHT_VOLUCITE_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_LIGHT_VOLUCITE_STONE_ITEM = ITEMS.register("cracked_light_volucite_stone", () -> new BlockItem(CRACKED_LIGHT_VOLUCITE_STONE.get(), new Item.Properties()));

	//dungeon cores
	public static final RegistryObject<Block> MUD_DUNGEON_CORE = BLOCKS.register("mud_dungeon_core", () -> new DungeonCoreBlock(BlockBehaviour.Properties.of().strength(30.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 181));
	public static final RegistryObject<Block> LUNATIC_DUNGEON_CORE = BLOCKS.register("lunatic_dungeon_core", () -> new DungeonCoreBlock(BlockBehaviour.Properties.of().strength(40.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 181));
	public static final RegistryObject<Block> SHADOW_CATACOMBS_DUNGEON_CORE = BLOCKS.register("shadow_catacombs_dungeon_core", () -> new DungeonCoreBlock(BlockBehaviour.Properties.of().strength(30.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 181));
	public static final RegistryObject<Block> GOLDEN_NETHER_DUNGEON_CORE = BLOCKS.register("golden_nether_dungeon_core", () -> new DungeonCoreBlock(BlockBehaviour.Properties.of().strength(50.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 101));
	public static final RegistryObject<Block> VOLUCITE_DUNGEON_CORE = BLOCKS.register("volucite_dungeon_core", () -> new DungeonCoreBlock(BlockBehaviour.Properties.of().strength(50.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 101));

	public static final RegistryObject<Item> MUD_DUNGEON_CORE_ITEM = ITEMS.register("mud_dungeon_core", () -> new BlockItem(MUD_DUNGEON_CORE.get(), new Item.Properties()));
	public static final RegistryObject<Item> LUNATIC_DUNGEON_CORE_ITEM = ITEMS.register("lunatic_dungeon_core", () -> new BlockItem(LUNATIC_DUNGEON_CORE.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHADOW_CATACOMBS_DUNGEON_CORE_ITEM = ITEMS.register("shadow_catacombs_dungeon_core", () -> new BlockItem(SHADOW_CATACOMBS_DUNGEON_CORE.get(), new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_NETHER_DUNGEON_CORE_ITEM = ITEMS.register("golden_nether_dungeon_core", () -> new BlockItem(GOLDEN_NETHER_DUNGEON_CORE.get(), new Item.Properties()));
	public static final RegistryObject<Item> VOLUCITE_DUNGEON_CORE_ITEM = ITEMS.register("volucite_dungeon_core", () -> new BlockItem(VOLUCITE_DUNGEON_CORE.get(), new Item.Properties()));

	//dungeons slabs, stairs & walls
	public static final RegistryObject<SlabBlock> MUD_BRICKS_SLAB = BLOCKS.register("mud_bricks_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get())));
	public static final RegistryObject<StairBlock> MUD_BRICKS_STAIRS = BLOCKS.register("mud_bricks_stairs", () -> new CoreProtectedStairsBlock(MUD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get())));
	public static final RegistryObject<WallBlock> MUD_BRICKS_WALL = BLOCKS.register("mud_bricks_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get())));
	public static final RegistryObject<SlabBlock> CRACKED_MUD_BRICKS_SLAB = BLOCKS.register("cracked_mud_bricks_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_MUD_BRICKS.get())));
	public static final RegistryObject<StairBlock> CRACKED_MUD_BRICKS_STAIRS = BLOCKS.register("cracked_mud_bricks_stairs", () -> new CoreProtectedStairsBlock(CRACKED_MUD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get())));
	public static final RegistryObject<WallBlock> CRACKED_MUD_BRICKS_WALL = BLOCKS.register("cracked_mud_bricks_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_MUD_BRICKS.get())));
	public static final RegistryObject<SlabBlock> MOSSY_MUD_BRICKS_SLAB = BLOCKS.register("mossy_mud_bricks_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS.get())));
	public static final RegistryObject<StairBlock> MOSSY_MUD_BRICKS_STAIRS = BLOCKS.register("mossy_mud_bricks_stairs", () -> new CoreProtectedStairsBlock(MOSSY_MUD_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS.get())));
	public static final RegistryObject<WallBlock> MOSSY_MUD_BRICKS_WALL = BLOCKS.register("mossy_mud_bricks_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS.get())));
	public static final RegistryObject<SlabBlock> VOLUCITE_STONE_SLAB = BLOCKS.register("volucite_stone_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get())));
	public static final RegistryObject<StairBlock> VOLUCITE_STONE_STAIRS = BLOCKS.register("volucite_stone_stairs", () -> new CoreProtectedStairsBlock(VOLUCITE_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> VOLUCITE_STONE_WALL = BLOCKS.register("volucite_stone_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get())));
	public static final RegistryObject<SlabBlock> CRACKED_VOLUCITE_STONE_SLAB = BLOCKS.register("cracked_volucite_stone_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE.get())));
	public static final RegistryObject<StairBlock> CRACKED_VOLUCITE_STONE_STAIRS = BLOCKS.register("cracked_volucite_stone_stairs", () -> new CoreProtectedStairsBlock(CRACKED_VOLUCITE_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> CRACKED_VOLUCITE_STONE_WALL = BLOCKS.register("cracked_volucite_stone_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE.get())));
	public static final RegistryObject<SlabBlock> LUNATIC_STONE_SLAB = BLOCKS.register("lunatic_stone_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get())));
	public static final RegistryObject<StairBlock> LUNATIC_STONE_STAIRS = BLOCKS.register("lunatic_stone_stairs", () -> new CoreProtectedStairsBlock(LUNATIC_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get())));
	public static final RegistryObject<WallBlock> LUNATIC_STONE_WALL = BLOCKS.register("lunatic_stone_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get())));
	public static final RegistryObject<SlabBlock> DARK_LUNATIC_STONE_SLAB = BLOCKS.register("dark_lunatic_stone_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(DARK_LUNATIC_STONE.get())));
	public static final RegistryObject<StairBlock> DARK_LUNATIC_STONE_STAIRS = BLOCKS.register("dark_lunatic_stone_stairs", () -> new CoreProtectedStairsBlock(DARK_LUNATIC_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(DARK_LUNATIC_STONE.get())));
	public static final RegistryObject<WallBlock> DARK_LUNATIC_STONE_WALL = BLOCKS.register("dark_lunatic_stone_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(DARK_LUNATIC_STONE.get())));
	public static final RegistryObject<SlabBlock> CRACKED_LUNATIC_STONE_SLAB = BLOCKS.register("cracked_lunatic_stone_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_LUNATIC_STONE.get())));
	public static final RegistryObject<StairBlock> CRACKED_LUNATIC_STONE_STAIRS = BLOCKS.register("cracked_lunatic_stone_stairs", () -> new CoreProtectedStairsBlock(CRACKED_LUNATIC_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> CRACKED_LUNATIC_STONE_WALL = BLOCKS.register("cracked_lunatic_stone_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_LUNATIC_STONE.get())));
	public static final RegistryObject<SlabBlock> SHADOW_CATACOMBS_BRICKS_SLAB = BLOCKS.register("shadow_catacombs_bricks_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get())));
	public static final RegistryObject<StairBlock> SHADOW_CATACOMBS_BRICKS_STAIRS = BLOCKS.register("shadow_catacombs_bricks_stairs", () -> new CoreProtectedStairsBlock(SHADOW_CATACOMBS_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get())));
	public static final RegistryObject<WallBlock> SHADOW_CATACOMBS_BRICKS_WALL = BLOCKS.register("shadow_catacombs_bricks_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get())));
	public static final RegistryObject<SlabBlock> CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB = BLOCKS.register("cracked_shadow_catacombs_bricks_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_SHADOW_CATACOMBS_BRICKS.get())));
	public static final RegistryObject<StairBlock> CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS = BLOCKS.register("cracked_shadow_catacombs_bricks_stairs", () -> new CoreProtectedStairsBlock(CRACKED_SHADOW_CATACOMBS_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> CRACKED_SHADOW_CATACOMBS_BRICKS_WALL = BLOCKS.register("cracked_shadow_catacombs_bricks_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_SHADOW_CATACOMBS_BRICKS.get())));
	public static final RegistryObject<SlabBlock> MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB = BLOCKS.register("mossy_shadow_catacombs_bricks_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_SHADOW_CATACOMBS_BRICKS.get())));
	public static final RegistryObject<StairBlock> MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS = BLOCKS.register("mossy_shadow_catacombs_bricks_stairs", () -> new CoreProtectedStairsBlock(MOSSY_SHADOW_CATACOMBS_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_SHADOW_CATACOMBS_BRICKS.get())));
	public static final RegistryObject<WallBlock> MOSSY_SHADOW_CATACOMBS_BRICKS_WALL = BLOCKS.register("mossy_shadow_catacombs_bricks_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_SHADOW_CATACOMBS_BRICKS.get())));
	public static final RegistryObject<IronBarsBlock> SHADOW_BARS = BLOCKS.register("shadow_bars", () -> new ShadowBarsBlock(METAL_NOTSOLID_MATERIAL));
	public static final RegistryObject<SlabBlock> GOLDEN_NETHER_BRICKS_SLAB = BLOCKS.register("golden_nether_bricks_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get())));
	public static final RegistryObject<StairBlock> GOLDEN_NETHER_BRICKS_STAIRS = BLOCKS.register("golden_nether_bricks_stairs", () -> new CoreProtectedStairsBlock(GOLDEN_NETHER_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> GOLDEN_NETHER_BRICKS_WALL = BLOCKS.register("golden_nether_bricks_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get())));
	public static final RegistryObject<SlabBlock> CRACKED_GOLDEN_NETHER_BRICKS_SLAB = BLOCKS.register("cracked_golden_nether_bricks_slab", () -> new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_GOLDEN_NETHER_BRICKS.get())));
	public static final RegistryObject<StairBlock> CRACKED_GOLDEN_NETHER_BRICKS_STAIRS = BLOCKS.register("cracked_golden_nether_bricks_stairs", () -> new CoreProtectedStairsBlock(CRACKED_GOLDEN_NETHER_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> CRACKED_GOLDEN_NETHER_BRICKS_WALL = BLOCKS.register("cracked_golden_nether_bricks_wall", () -> new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_GOLDEN_NETHER_BRICKS.get())));

	public static final RegistryObject<Item> MUD_BRICKS_SLAB_ITEM = ITEMS.register("mud_bricks_slab", () -> new BlockItem(MUD_BRICKS_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<Item> MUD_BRICKS_STAIRS_ITEM = ITEMS.register("mud_bricks_stairs", () -> new BlockItem(MUD_BRICKS_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<Item> MUD_BRICKS_WALL_ITEM = ITEMS.register("mud_bricks_wall", () -> new BlockItem(MUD_BRICKS_WALL.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_MUD_BRICKS_SLAB_ITEM = ITEMS.register("cracked_mud_bricks_slab", () -> new BlockItem(CRACKED_MUD_BRICKS_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_MUD_BRICKS_STAIRS_ITEM = ITEMS.register("cracked_mud_bricks_stairs", () -> new BlockItem(CRACKED_MUD_BRICKS_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_MUD_BRICKS_WALL_ITEM = ITEMS.register("cracked_mud_bricks_wall", () -> new BlockItem(CRACKED_MUD_BRICKS_WALL.get(), new Item.Properties()));
	public static final RegistryObject<Item> MOSSY_MUD_BRICKS_SLAB_ITEM = ITEMS.register("mossy_mud_bricks_slab", () -> new BlockItem(MOSSY_MUD_BRICKS_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<Item> MOSSY_MUD_BRICKS_STAIRS_ITEM = ITEMS.register("mossy_mud_bricks_stairs", () -> new BlockItem(MOSSY_MUD_BRICKS_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<Item> MOSSY_MUD_BRICKS_WALL_ITEM = ITEMS.register("mossy_mud_bricks_wall", () -> new BlockItem(MOSSY_MUD_BRICKS_WALL.get(), new Item.Properties()));
	public static final RegistryObject<Item> VOLUCITE_STONE_SLAB_ITEM = ITEMS.register("volucite_stone_slab", () -> new BlockItem(VOLUCITE_STONE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<Item> VOLUCITE_STONE_STAIRS_ITEM = ITEMS.register("volucite_stone_stairs", () -> new BlockItem(VOLUCITE_STONE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<Item> VOLUCITE_STONE_WALL_ITEM = ITEMS.register("volucite_stone_wall", () -> new BlockItem(VOLUCITE_STONE_WALL.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_VOLUCITE_STONE_SLAB_ITEM = ITEMS.register("cracked_volucite_stone_slab", () -> new BlockItem(CRACKED_VOLUCITE_STONE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_VOLUCITE_STONE_STAIRS_ITEM = ITEMS.register("cracked_volucite_stone_stairs", () -> new BlockItem(CRACKED_VOLUCITE_STONE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_VOLUCITE_STONE_WALL_ITEM = ITEMS.register("cracked_volucite_stone_wall", () -> new BlockItem(CRACKED_VOLUCITE_STONE_WALL.get(), new Item.Properties()));
	public static final RegistryObject<Item> LUNATIC_STONE_SLAB_ITEM = ITEMS.register("lunatic_stone_slab", () -> new BlockItem(LUNATIC_STONE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<Item> LUNATIC_STONE_STAIRS_ITEM = ITEMS.register("lunatic_stone_stairs", () -> new BlockItem(LUNATIC_STONE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<Item> LUNATIC_STONE_WALL_ITEM = ITEMS.register("lunatic_stone_wall", () -> new BlockItem(LUNATIC_STONE_WALL.get(), new Item.Properties()));
	public static final RegistryObject<Item> DARK_LUNATIC_STONE_SLAB_ITEM = ITEMS.register("dark_lunatic_stone_slab", () -> new BlockItem(DARK_LUNATIC_STONE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<Item> DARK_LUNATIC_STONE_STAIRS_ITEM = ITEMS.register("dark_lunatic_stone_stairs", () -> new BlockItem(DARK_LUNATIC_STONE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<Item> DARK_LUNATIC_STONE_WALL_ITEM = ITEMS.register("dark_lunatic_stone_wall", () -> new BlockItem(DARK_LUNATIC_STONE_WALL.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_LUNATIC_STONE_SLAB_ITEM = ITEMS.register("cracked_lunatic_stone_slab", () -> new BlockItem(CRACKED_LUNATIC_STONE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_LUNATIC_STONE_STAIRS_ITEM = ITEMS.register("cracked_lunatic_stone_stairs", () -> new BlockItem(CRACKED_LUNATIC_STONE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_LUNATIC_STONE_WALL_ITEM = ITEMS.register("cracked_lunatic_stone_wall", () -> new BlockItem(CRACKED_LUNATIC_STONE_WALL.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHADOW_CATACOMBS_BRICKS_SLAB_ITEM = ITEMS.register("shadow_catacombs_bricks_slab", () -> new BlockItem(SHADOW_CATACOMBS_BRICKS_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHADOW_CATACOMBS_BRICKS_STAIRS_ITEM = ITEMS.register("shadow_catacombs_bricks_stairs", () -> new BlockItem(SHADOW_CATACOMBS_BRICKS_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHADOW_CATACOMBS_BRICKS_WALL_ITEM = ITEMS.register("shadow_catacombs_bricks_wall", () -> new BlockItem(SHADOW_CATACOMBS_BRICKS_WALL.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB_ITEM = ITEMS.register("cracked_shadow_catacombs_bricks_slab", () -> new BlockItem(CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS_ITEM = ITEMS.register("cracked_shadow_catacombs_bricks_stairs", () -> new BlockItem(CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_SHADOW_CATACOMBS_BRICKS_WALL_ITEM = ITEMS.register("cracked_shadow_catacombs_bricks_wall", () -> new BlockItem(CRACKED_SHADOW_CATACOMBS_BRICKS_WALL.get(), new Item.Properties()));
	public static final RegistryObject<Item> MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB_ITEM = ITEMS.register("mossy_shadow_catacombs_bricks_slab", () -> new BlockItem(MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<Item> MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS_ITEM = ITEMS.register("mossy_shadow_catacombs_bricks_stairs", () -> new BlockItem(MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<Item> MOSSY_SHADOW_CATACOMBS_BRICKS_WALL_ITEM = ITEMS.register("mossy_shadow_catacombs_bricks_wall", () -> new BlockItem(MOSSY_SHADOW_CATACOMBS_BRICKS_WALL.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHADOW_BARS_ITEM = ITEMS.register("shadow_bars", () -> new BlockItem(SHADOW_BARS.get(), new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_NETHER_BRICKS_SLAB_ITEM = ITEMS.register("golden_nether_bricks_slab", () -> new BlockItem(GOLDEN_NETHER_BRICKS_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_NETHER_BRICKS_STAIRS_ITEM = ITEMS.register("golden_nether_bricks_stairs", () -> new BlockItem(GOLDEN_NETHER_BRICKS_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_NETHER_BRICKS_WALL_ITEM = ITEMS.register("golden_nether_bricks_wall", () -> new BlockItem(GOLDEN_NETHER_BRICKS_WALL.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_GOLDEN_NETHER_BRICKS_SLAB_ITEM = ITEMS.register("cracked_golden_nether_bricks_slab", () -> new BlockItem(CRACKED_GOLDEN_NETHER_BRICKS_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_GOLDEN_NETHER_BRICKS_STAIRS_ITEM = ITEMS.register("cracked_golden_nether_bricks_stairs", () -> new BlockItem(CRACKED_GOLDEN_NETHER_BRICKS_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<Item> CRACKED_GOLDEN_NETHER_BRICKS_WALL_ITEM = ITEMS.register("cracked_golden_nether_bricks_wall", () -> new BlockItem(CRACKED_GOLDEN_NETHER_BRICKS_WALL.get(), new Item.Properties()));

	//smoky quartz
	public static final RegistryObject<Item> SMOKY_QUARTZ = ITEMS.register("smoky_quartz",() -> new Item(new Item.Properties()));
	public static final RegistryObject<Block> SMOKY_QUARTZ_BLOCK = BLOCKS.register("smoky_quartz_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)));
	public static final RegistryObject<Block> SMOOTH_SMOKY_QUARTZ = BLOCKS.register("smooth_smoky_quartz", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get())));
	public static final RegistryObject<Block> CHISELED_SMOKY_QUARTZ_BLOCK = BLOCKS.register("chiseled_smoky_quartz_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get())));
	public static final RegistryObject<Block> SMOKY_QUARTZ_BRICKS = BLOCKS.register("smoky_quartz_bricks", () -> new Block(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get())));
	public static final RegistryObject<RotatedPillarBlock> SMOKY_QUARTZ_PILLAR = BLOCKS.register("smoky_quartz_pillar", () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get())));
	public static final RegistryObject<SlabBlock> SMOKY_QUARTZ_SLAB = BLOCKS.register("smoky_quartz_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get())));
	public static final RegistryObject<SlabBlock> SMOOTH_SMOKY_QUARTZ_SLAB = BLOCKS.register("smooth_smoky_quartz_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get())));
	public static final RegistryObject<StairBlock> SMOKY_QUARTZ_STAIRS = BLOCKS.register("smoky_quartz_stairs", () -> new StairBlock(SMOKY_QUARTZ_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get())));
	public static final RegistryObject<StairBlock> SMOOTH_SMOKY_QUARTZ_STAIRS = BLOCKS.register("smooth_smoky_quartz_stairs", () -> new StairBlock(SMOOTH_SMOKY_QUARTZ.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK.get())));

	public static final RegistryObject<Item> SMOKY_QUARTZ_BLOCK_ITEM = ITEMS.register("smoky_quartz_block", () -> new BlockItem(SMOKY_QUARTZ_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> SMOOTH_SMOKY_QUARTZ_ITEM = ITEMS.register("smooth_smoky_quartz", () -> new BlockItem(SMOOTH_SMOKY_QUARTZ.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHISELED_SMOKY_QUARTZ_BLOCK_ITEM = ITEMS.register("chiseled_smoky_quartz_block", () -> new BlockItem(CHISELED_SMOKY_QUARTZ_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> SMOKY_QUARTZ_BRICKS_ITEM = ITEMS.register("smoky_quartz_bricks", () -> new BlockItem(SMOKY_QUARTZ_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> SMOKY_QUARTZ_PILLAR_ITEM = ITEMS.register("smoky_quartz_pillar", () -> new BlockItem(SMOKY_QUARTZ_PILLAR.get(), new Item.Properties()));
	public static final RegistryObject<Item> SMOKY_QUARTZ_SLAB_ITEM = ITEMS.register("smoky_quartz_slab", () -> new BlockItem(SMOKY_QUARTZ_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<Item> SMOOTH_SMOKY_QUARTZ_SLAB_ITEM = ITEMS.register("smooth_smoky_quartz_slab", () -> new BlockItem(SMOOTH_SMOKY_QUARTZ_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<Item> SMOKY_QUARTZ_STAIRS_ITEM = ITEMS.register("smoky_quartz_stairs", () -> new BlockItem(SMOKY_QUARTZ_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<Item> SMOOTH_SMOKY_QUARTZ_STAIRS_ITEM = ITEMS.register("smooth_smoky_quartz_stairs", () -> new BlockItem(SMOOTH_SMOKY_QUARTZ_STAIRS.get(), new Item.Properties()));

	//dungeon trapped blocks
	public static final RegistryObject<Block> TRAPPED_MUD_BRICKS = BLOCKS.register("trapped_mud_bricks", () -> new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)));
	public static final RegistryObject<Block> TRAPPED_LIGHT_MUD_BRICKS = BLOCKS.register("trapped_light_mud_bricks", () -> new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).lightLevel((state) -> 11)));
	public static final RegistryObject<Block> TRAPPED_LUNATIC_STONE = BLOCKS.register("trapped_lunatic_stone", () -> new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)));
	public static final RegistryObject<Block> TRAPPED_LIGHT_LUNATIC_STONE = BLOCKS.register("trapped_light_lunatic_stone", () -> new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).lightLevel((state) -> 11)));
	public static final RegistryObject<Block> TRAPPED_GOLDEN_NETHER_BRICKS = BLOCKS.register("trapped_golden_nether_bricks", () -> new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)));
	public static final RegistryObject<Block> TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS = BLOCKS.register("trapped_light_golden_nether_bricks", () -> new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).lightLevel((state) -> 11)));

	public static final RegistryObject<Item> TRAPPED_MUD_BRICKS_ITEM = ITEMS.register("trapped_mud_bricks", () -> new BlockItem(TRAPPED_MUD_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> TRAPPED_LIGHT_MUD_BRICKS_ITEM = ITEMS.register("trapped_light_mud_bricks", () -> new BlockItem(TRAPPED_LIGHT_MUD_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> TRAPPED_LUNATIC_STONE_ITEM = ITEMS.register("trapped_lunatic_stone", () -> new BlockItem(TRAPPED_LUNATIC_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> TRAPPED_LIGHT_LUNATIC_STONE_ITEM = ITEMS.register("trapped_light_lunatic_stone", () -> new BlockItem(TRAPPED_LIGHT_LUNATIC_STONE.get(), new Item.Properties()));
	public static final RegistryObject<Item> TRAPPED_GOLDEN_NETHER_BRICKS_ITEM = ITEMS.register("trapped_golden_nether_bricks", () -> new BlockItem(TRAPPED_GOLDEN_NETHER_BRICKS.get(), new Item.Properties()));
	public static final RegistryObject<Item> TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS_ITEM = ITEMS.register("trapped_light_golden_nether_bricks", () -> new BlockItem(TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS.get(), new Item.Properties()));

	//dungeon other blocks, loots
	public static final RegistryObject<RotatedPillarBlock> MUD_BONE_BLOCK = BLOCKS.register("mud_bone_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).requiresCorrectToolForDrops().strength(2.5F).sound(SoundType.BONE_BLOCK)));
	public static final RegistryObject<Item> MUD_BONE_BLOCK_ITEM = ITEMS.register("mud_bone_block", () -> new BlockItem(MUD_BONE_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Block> MUD_BONE_PILE_BLOCK = BLOCKS.register("mud_bone_pile_block", () -> new BonePileBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIRT).randomTicks().strength(2.5F).sound(SoundType.BONE_BLOCK)));
	public static final RegistryObject<Item> MUD_BONE_PILE_BLOCK_ITEM = ITEMS.register("mud_bone_pile_block", () -> new BlockItem(MUD_BONE_PILE_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> MUD_BONE = ITEMS.register("mud_bone",() -> new Item(new Item.Properties()));
	public static final RegistryObject<Block> THORNY_COBWEB = BLOCKS.register("thorny_cobweb", () -> new ThornyWebBlock(BlockBehaviour.Properties.of().noCollission().requiresCorrectToolForDrops().strength(8.0F)));
	public static final RegistryObject<Item> THORNY_COBWEB_ITEM = ITEMS.register("thorny_cobweb", () -> new BlockItem(THORNY_COBWEB.get(), new Item.Properties()));
	public static final RegistryObject<Block> AERIAL_NETHERRACK = BLOCKS.register("aerial_netherrack", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.NETHER).requiresCorrectToolForDrops().strength(6.0F, 8.0F)));
	public static final RegistryObject<Item> AERIAL_NETHERRACK_ITEM = ITEMS.register("aerial_netherrack", () -> new BlockItem(AERIAL_NETHERRACK.get(), new Item.Properties()));
	public static final RegistryObject<SlabBlock> AERIAL_NETHERRACK_SLAB = BLOCKS.register("aerial_netherrack_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_NETHERRACK.get())));
	public static final RegistryObject<Item> AERIAL_NETHERRACK_SLAB_ITEM = ITEMS.register("aerial_netherrack_slab", () -> new BlockItem(AERIAL_NETHERRACK_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<StairBlock> AERIAL_NETHERRACK_STAIRS = BLOCKS.register("aerial_netherrack_stairs", () -> new StairBlock(AERIAL_NETHERRACK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(AERIAL_NETHERRACK.get())));
	public static final RegistryObject<Item> AERIAL_NETHERRACK_STAIRS_ITEM = ITEMS.register("aerial_netherrack_stairs", () -> new BlockItem(AERIAL_NETHERRACK_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<WallBlock> AERIAL_NETHERRACK_WALL = BLOCKS.register("aerial_netherrack_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_NETHERRACK.get())));
	public static final RegistryObject<Item> AERIAL_NETHERRACK_WALL_ITEM = ITEMS.register("aerial_netherrack_wall", () -> new BlockItem(AERIAL_NETHERRACK_WALL.get(), new Item.Properties()));

	//dungeon bookshelfs
	public static final RegistryObject<Block> MUD_BOOKSHELF = BLOCKS.register("mud_bookshelf", () -> new CoreProtectedBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get())));
	public static final RegistryObject<Block> LUNATIC_BOOKSHELF = BLOCKS.register("lunatic_bookshelf", () -> new CoreProtectedBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get())));
	public static final RegistryObject<Block> GOLDEN_NETHER_BOOKSHELF = BLOCKS.register("golden_nether_bookshelf", () -> new CoreProtectedBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get())));
	public static final RegistryObject<Block> SHADOW_CATACOMBS_BOOKSHELF = BLOCKS.register("shadow_catacombs_bookshelf", () -> new CoreProtectedBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get())));
	public static final RegistryObject<Block> VOLUCITE_BOOKSHELF = BLOCKS.register("volucite_bookshelf", () -> new CoreProtectedBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get())));
	public static final RegistryObject<Item> MUD_BOOKSHELF_ITEM = ITEMS.register("mud_bookshelf", () -> new BlockItem(MUD_BOOKSHELF.get(), new Item.Properties()));
	public static final RegistryObject<Item> LUNATIC_BOOKSHELF_ITEM = ITEMS.register("lunatic_bookshelf", () -> new BlockItem(LUNATIC_BOOKSHELF.get(), new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_NETHER_BOOKSHELF_ITEM = ITEMS.register("golden_nether_bookshelf", () -> new BlockItem(GOLDEN_NETHER_BOOKSHELF.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHADOW_CATACOMBS_BOOKSHELF_ITEM = ITEMS.register("shadow_catacombs_bookshelf", () -> new BlockItem(SHADOW_CATACOMBS_BOOKSHELF.get(), new Item.Properties()));
	public static final RegistryObject<Item> VOLUCITE_BOOKSHELF_ITEM = ITEMS.register("volucite_bookshelf", () -> new BlockItem(VOLUCITE_BOOKSHELF.get(), new Item.Properties()));

	//glyph blocks
	public static final RegistryObject<Block> MUD_GLYPH_BLOCK = BLOCKS.register("mud_glyph_block", () -> new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS.get()).lightLevel((state) -> 9)));
	public static final RegistryObject<Item> MUD_GLYPH_BLOCK_ITEM = ITEMS.register("mud_glyph_block", () -> new BlockItem(MUD_GLYPH_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Block> LUNATIC_GLYPH_BLOCK = BLOCKS.register("lunatic_glyph_block", () -> new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).lightLevel((state) -> 9)));
	public static final RegistryObject<Item> LUNATIC_GLYPH_BLOCK_ITEM = ITEMS.register("lunatic_glyph_block", () -> new BlockItem(LUNATIC_GLYPH_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Block> GOLDEN_NETHER_PRISON_GLYPH_BLOCK = BLOCKS.register("golden_nether_prison_glyph_block", () -> new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get()).lightLevel((state) -> 9)));
	public static final RegistryObject<Item> GOLDEN_NETHER_PRISON_GLYPH_BLOCK_ITEM = ITEMS.register("golden_nether_prison_glyph_block", () -> new BlockItem(GOLDEN_NETHER_PRISON_GLYPH_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Block> VOLUCITE_GLYPH_BLOCK = BLOCKS.register("volucite_glyph_block", () -> new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE.get()).lightLevel((state) -> 9)));
	public static final RegistryObject<Item> VOLUCITE_GLYPH_BLOCK_ITEM = ITEMS.register("volucite_glyph_block", () -> new BlockItem(VOLUCITE_GLYPH_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Block> SHADOW_CATACOMBS_GLYPH_BLOCK = BLOCKS.register("shadow_catacombs_glyph_block", () -> new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS.get()).lightLevel((state) -> 9)));
	public static final RegistryObject<Item> SHADOW_CATACOMBS_GLYPH_BLOCK_ITEM = ITEMS.register("shadow_catacombs_glyph_block", () -> new BlockItem(SHADOW_CATACOMBS_GLYPH_BLOCK.get(), new Item.Properties()));

	//trophies
	public static final RegistryObject<Block> MUD_CYCLE_MAGE_TROPHY = BLOCKS.register("mud_cycle_mage_trophy", () -> new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> LUNAR_PRIEST_TROPHY = BLOCKS.register("lunar_priest_trophy", () -> new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> LILITH_TROPHY = BLOCKS.register("lilith_trophy", () -> new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE.get()).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> CHAINED_GOD_TROPHY = BLOCKS.register("chained_god_trophy", () -> new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS.get()).requiresCorrectToolForDrops()));
	public static final RegistryObject<Item> MUD_CYCLE_MAGE_TROPHY_ITEM = ITEMS.register("mud_cycle_mage_trophy", () -> new BlockItem(MUD_CYCLE_MAGE_TROPHY.get(), new Item.Properties()));
	public static final RegistryObject<Item> LUNAR_PRIEST_TROPHY_ITEM = ITEMS.register("lunar_priest_trophy", () -> new BlockItem(LUNAR_PRIEST_TROPHY.get(), new Item.Properties()));
	public static final RegistryObject<Item> LILITH_TROPHY_ITEM = ITEMS.register("lilith_trophy", () -> new BlockItem(LILITH_TROPHY.get(), new Item.Properties()));
	public static final RegistryObject<Item> CHAINED_GOD_TROPHY_ITEM = ITEMS.register("chained_god_trophy", () -> new BlockItem(CHAINED_GOD_TROPHY.get(), new Item.Properties()));

	//ores
	public static final RegistryObject<Block> IRON_STELLAR_ORE = BLOCKS.register("iron_stellar_ore",() -> new AerialHellOreBlock(0, 2, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> GOLD_STELLAR_ORE = BLOCKS.register("gold_stellar_ore",() -> new AerialHellOreBlock(0, 2, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> DIAMOND_STELLAR_ORE = BLOCKS.register("diamond_stellar_ore",() -> new AerialHellOreBlock(3, 5, BlockBehaviour.Properties.of().strength(5.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> FLUORITE_ORE = BLOCKS.register("fluorite_ore",() -> new AerialHellOreBlock(0, 2, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> MAGMATIC_GEL_ORE = BLOCKS.register("magmatic_gel_ore",() -> new MagmaticGelOreBlock(0, 2, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).lightLevel(s -> 4).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> RUBY_ORE = BLOCKS.register("ruby_ore",() -> new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> AZURITE_ORE = BLOCKS.register("azurite_ore",() -> new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> VOLUCITE_ORE = BLOCKS.register("volucite_ore",() -> new VoluciteOreBlock(0, 0, BlockBehaviour.Properties.of().strength(5.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> OBSIDIAN_ORE = BLOCKS.register("obsidian_ore",() -> new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().strength(5.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> SMOKY_QUARTZ_ORE = BLOCKS.register("smoky_quartz_ore",() -> new AerialHellOreBlock(1, 3, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
	public static final RegistryObject<Item> IRON_STELLAR_ORE_ITEM = ITEMS.register("iron_stellar_ore", () -> new BlockItem(IRON_STELLAR_ORE.get(), new Item.Properties()));
	public static final RegistryObject<Item> GOLD_STELLAR_ORE_ITEM = ITEMS.register("gold_stellar_ore", () -> new BlockItem(GOLD_STELLAR_ORE.get(), new Item.Properties()));
	public static final RegistryObject<Item> DIAMOND_STELLAR_ORE_ITEM = ITEMS.register("diamond_stellar_ore", () -> new BlockItem(DIAMOND_STELLAR_ORE.get(), new Item.Properties()));
	public static final RegistryObject<Item> FLUORITE_ORE_ITEM = ITEMS.register("fluorite_ore", () -> new BlockItem(FLUORITE_ORE.get(), new Item.Properties()));
	public static final RegistryObject<Item> MAGMATIC_GEL_ORE_ITEM = ITEMS.register("magmatic_gel_ore", () -> new BlockItem(MAGMATIC_GEL_ORE.get(), new Item.Properties()));
	public static final RegistryObject<Item> RUBY_ORE_ITEM = ITEMS.register("ruby_ore", () -> new BlockItem(RUBY_ORE.get(), new Item.Properties()));
	public static final RegistryObject<Item> AZURITE_ORE_ITEM = ITEMS.register("azurite_ore", () -> new BlockItem(AZURITE_ORE.get(), new Item.Properties()));
	public static final RegistryObject<Item> VOLUCITE_ORE_ITEM = ITEMS.register("volucite_ore", () -> new BlockItem(VOLUCITE_ORE.get(), new Item.Properties().rarity(AerialHellRarities.VIBRANT)));
	public static final RegistryObject<Item> OBSIDIAN_ORE_ITEM = ITEMS.register("obsidian_ore", () -> new BlockItem(OBSIDIAN_ORE.get(), new Item.Properties().rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> SMOKY_QUARTZ_ORE_ITEM = ITEMS.register("smoky_quartz_ore", () -> new BlockItem(SMOKY_QUARTZ_ORE.get(), new Item.Properties().rarity(Rarity.EPIC)));

	public static final RegistryObject<Block> RAW_RUBY_BLOCK = BLOCKS.register("raw_ruby_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> RAW_AZURITE_BLOCK = BLOCKS.register("raw_azurite_crystal_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> RAW_VOLUCITE_BLOCK = BLOCKS.register("raw_volucite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).requiresCorrectToolForDrops()));
	public static final RegistryObject<Item> RAW_RUBY_BLOCK_ITEM = ITEMS.register("raw_ruby_block", () -> new BlockItem(RAW_RUBY_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> RAW_AZURITE_BLOCK_ITEM = ITEMS.register("raw_azurite_crystal_block", () -> new BlockItem(RAW_AZURITE_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> RAW_VOLUCITE_BLOCK_ITEM = ITEMS.register("raw_volucite_block", () -> new BlockItem(RAW_VOLUCITE_BLOCK.get(), new Item.Properties()));

	public static final RegistryObject<Item> RAW_RUBY = ITEMS.register("raw_ruby", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> RAW_AZURITE = ITEMS.register("raw_azurite_crystal", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> RAW_VOLUCITE = ITEMS.register("raw_volucite", () -> new Item(new Item.Properties()));

	public static final RegistryObject<Block> FLUORITE_BLOCK = BLOCKS.register("fluorite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> MAGMATIC_GEL_BLOCK = BLOCKS.register("magmatic_gel_block", () -> new MagmaticGelBlock(BlockBehaviour.Properties.of().strength(1.0F, 1600.0F).randomTicks().sound(SoundType.GLASS).noOcclusion().requiresCorrectToolForDrops().isViewBlocking((state, reader, pos) -> false)));
	public static final RegistryObject<Block> RUBY_BLOCK = BLOCKS.register("ruby_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> AZURITE_BLOCK = BLOCKS.register("azurite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
	public static final RegistryObject<Block> VOLUCITE_BLOCK = BLOCKS.register("volucite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).requiresCorrectToolForDrops()));
	public static final RegistryObject<Item> FLUORITE_BLOCK_ITEM = ITEMS.register("fluorite_block", () -> new BlockItem(FLUORITE_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> MAGMATIC_GEL_BLOCK_ITEM = ITEMS.register("magmatic_gel_block", () -> new BlockItem(MAGMATIC_GEL_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> RUBY_BLOCK_ITEM = ITEMS.register("ruby_block", () -> new BlockItem(RUBY_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> AZURITE_BLOCK_ITEM = ITEMS.register("azurite_block", () -> new BlockItem(AZURITE_BLOCK.get(), new Item.Properties()));
	public static final RegistryObject<Item> VOLUCITE_BLOCK_ITEM = ITEMS.register("volucite_block", () -> new BlockItem(VOLUCITE_BLOCK.get(), new Item.Properties()));

	public static final RegistryObject<Item> FLUORITE = ITEMS.register("fluorite", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> MAGMATIC_GEL = ITEMS.register("magmatic_gel",() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> AZURITE_CRYSTAL = ITEMS.register("azurite_crystal", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> VOLUCITE_VIBRANT = ITEMS.register("volucite_vibrant", () -> new VoluciteVibrantItem(new Item.Properties().rarity(AerialHellRarities.VIBRANT)));

	public static final RegistryObject<Item> OVERHEATED_RUBY = ITEMS.register("overheated_ruby", () -> new WithInformationItem(new Item.Properties()));
	public static final RegistryObject<Item> OVERHEATED_VOLUCITE = ITEMS.register("overheated_volucite", () -> new WithInformationItem(new Item.Properties()));

	//legendary ores
	public static final RegistryObject<Item> ARSONIST_INGOT = ITEMS.register("arsonist_ingot", () -> new Item(new Item.Properties().rarity(AerialHellRarities.LEGENDARY).fireResistant()));
	public static final RegistryObject<Item> LUNATIC_CRYSTAL = ITEMS.register("lunatic_crystal", () -> new Item(new Item.Properties().rarity(AerialHellRarities.LEGENDARY)));
	public static final RegistryObject<Item> OBSIDIAN_SHARD = ITEMS.register("obsidian_shard", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> CURSED_CRYSAL = ITEMS.register("cursed_crystal", () -> new Item(new Item.Properties().rarity(AerialHellRarities.CORRUPTED)));

	public static final RegistryObject<Block> ARSONIST_BLOCK = BLOCKS.register("arsonist_block", () -> new ArsonistBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).requiresCorrectToolForDrops().lightLevel((state) -> 9)));
	public static final RegistryObject<Item> ARSONIST_BLOCK_ITEM = ITEMS.register("arsonist_block", () -> new BlockItem(ARSONIST_BLOCK.get(), new Item.Properties().rarity(AerialHellRarities.LEGENDARY).fireResistant()));
	public static final RegistryObject<Block> LUNATIC_CRYSTAL_BLOCK = BLOCKS.register("lunatic_crystal_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).sound(SoundType.GLASS).requiresCorrectToolForDrops().lightLevel((state) -> 9)));
	public static final RegistryObject<Item> LUNATIC_CRYSTAL_BLOCK_ITEM = ITEMS.register("lunatic_crystal_block", () -> new BlockItem(LUNATIC_CRYSTAL_BLOCK.get(), new Item.Properties().rarity(AerialHellRarities.LEGENDARY)));
	public static final RegistryObject<Block> CURSED_CRYSAL_BLOCK = BLOCKS.register("cursed_crystal_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).sound(SoundType.GLASS).requiresCorrectToolForDrops().lightLevel((state) -> 9)));
	public static final RegistryObject<Item> CURSED_CRYSAL_BLOCK_ITEM = ITEMS.register("cursed_crystal_block", () -> new BlockItem(CURSED_CRYSAL_BLOCK.get(), new Item.Properties().rarity(AerialHellRarities.CORRUPTED)));

	//cactus
	public static final RegistryObject<SkyCactusBlock> SKY_CACTUS = BLOCKS.register("sky_cactus", () -> new SkyCactusBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(0.4F).sound(SoundType.WOOL).randomTicks()));
	public static final RegistryObject<Item> SKY_CACTUS_ITEM = ITEMS.register("sky_cactus", () -> new BlockItem(SKY_CACTUS.get(), new Item.Properties()));
	public static final RegistryObject<Block> SKY_CACTUS_FIBER_PLANKS = BLOCKS.register("sky_cactus_fiber_planks", () -> new Block(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_PLANKS_ITEM = ITEMS.register("sky_cactus_fiber_planks", () -> new BlockItem(SKY_CACTUS_FIBER_PLANKS.get(), new Item.Properties()));
	public static final RegistryObject<Block> SKY_CACTUS_FIBER_BOOKSHELF = BLOCKS.register("sky_cactus_fiber_bookshelf", () -> new AerialHellBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_PLANKS.get())));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_BOOKSHELF_ITEM = ITEMS.register("sky_cactus_fiber_bookshelf", () -> new BlockItem(SKY_CACTUS_FIBER_BOOKSHELF.get(), new Item.Properties()));
	public static final RegistryObject<SkyCactusBlock> VIBRANT_SKY_CACTUS = BLOCKS.register("vibrant_sky_cactus", () -> new SkyCactusBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(0.4F).sound(SoundType.WOOL).randomTicks().lightLevel(s -> 15).noOcclusion()));
	public static final RegistryObject<Item> VIBRANT_SKY_CACTUS_ITEM = ITEMS.register("vibrant_sky_cactus", () -> new BlockItem(VIBRANT_SKY_CACTUS.get(), new Item.Properties().rarity(AerialHellRarities.VIBRANT)));
	public static final RegistryObject<Block> VIBRANT_SKY_CACTUS_FIBER_LANTERN = BLOCKS.register("vibrant_sky_cactus_fiber_lantern", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.QUARTZ).strength(0.5F).sound(SoundType.GLASS).noOcclusion().lightLevel(s -> 15)));
	public static final RegistryObject<Item> VIBRANT_SKY_CACTUS_FIBER_LANTERN_ITEM = ITEMS.register("vibrant_sky_cactus_fiber_lantern", () -> new BlockItem(VIBRANT_SKY_CACTUS_FIBER_LANTERN.get(), new Item.Properties().rarity(AerialHellRarities.VIBRANT)));

	//bushes
	public static final RegistryObject<Block> AERIAL_BERRY_BUSH = BLOCKS.register("aerial_berry_bush", () -> new AerialBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));
	public static final RegistryObject<Item> AERIAL_BERRY_SEEDS = ITEMS.register("aerial_berry_seeds",() -> new ItemNameBlockItem(AERIAL_BERRY_BUSH.get(), new Item.Properties()));
	public static final RegistryObject<Block> VIBRANT_AERIAL_BERRY_BUSH = BLOCKS.register("vibrant_aerial_berry_bush", () -> new VibrantAerialBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));
	public static final RegistryObject<Item> VIBRANT_AERIAL_BERRY_SEEDS = ITEMS.register("vibrant_aerial_berry_seeds",() -> new ItemNameBlockItem(VIBRANT_AERIAL_BERRY_BUSH.get(), new Item.Properties().rarity(AerialHellRarities.VIBRANT)));

	//crops
	public static final RegistryObject<Block> STELLAR_WHEAT = BLOCKS.register("stellar_wheat", () -> new StellarWheatBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));
	public static final RegistryObject<Item> STELLAR_WHEAT_SEEDS = ITEMS.register("stellar_wheat_seeds",() -> new ItemNameBlockItem(STELLAR_WHEAT.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_WHEAT_ITEM = ITEMS.register("stellar_wheat",() -> new Item(new Item.Properties()));

	//Vertical growing plants
	public static final RegistryObject<VerticalGrowingPlantBlock> CLIMBING_VINE = BLOCKS.register("climbing_vine", () -> new VerticalGrowingPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE), 4));
	public static final RegistryObject<Item> CLIMBING_VINE_ITEM = ITEMS.register("climbing_vine",() -> new BlockItem(CLIMBING_VINE.get(), new Item.Properties()));
	public static final RegistryObject<VerticalGrowingPlantBlock> STELLAR_SUGAR_CANE = BLOCKS.register("stellar_sugar_cane", () -> new VerticalGrowingPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE), 5));
	public static final RegistryObject<Item> STELLAR_SUGAR_CANE_ITEM = ITEMS.register("stellar_sugar_cane",() -> new BlockItem(STELLAR_SUGAR_CANE.get(), new Item.Properties()));

	//chorus like
	public static final RegistryObject<ChorusPlantLikeBlock> FULL_MOON_PLANT = BLOCKS.register("full_moon_plant", () -> new ChorusPlantLikeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).forceSolidOff().strength(0.4F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY).lightLevel((state) -> 10)));
	public static final RegistryObject<ChorusFlowerLikeBlock> FULL_MOON_FLOWER = BLOCKS.register("full_moon_flower", () -> new ChorusFlowerLikeBlock(FULL_MOON_PLANT.get(), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).forceSolidOff().randomTicks().strength(0.4F).sound(SoundType.WOOD).noOcclusion().isValidSpawn((state, blockGetter, pos, entitytype) -> false).pushReaction(PushReaction.DESTROY).isRedstoneConductor((state, blockGetter, pos) -> false).lightLevel((state) -> 15)));
	public static final RegistryObject<Item> FULL_MOON_FLOWER_ITEM = ITEMS.register("full_moon_flower",() -> new BlockItem(FULL_MOON_FLOWER.get(), new Item.Properties()));

	//vines
	public static final RegistryObject<CaveVinesBlock> GLOWING_STICK_FRUIT_VINES = BLOCKS.register("glowing_stick_fruit_vines", () -> new AerialHellCaveVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAVE_VINES)));
	public static final RegistryObject<CaveVinesPlantBlock> GLOWING_STICK_FRUIT_VINES_PLANT = BLOCKS.register("glowing_stick_fruit_vines_plant", () -> new AerialHellCaveVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAVE_VINES_PLANT)));
	public static final RegistryObject<CaveVinesBlock> BLOSSOMING_VINES = BLOCKS.register("blossoming_vines", () -> new AerialHellCaveVinesBlock(BlockBehaviour.Properties.of().randomTicks().noCollission().instabreak().sound(SoundType.CAVE_VINES)));
	public static final RegistryObject<CaveVinesPlantBlock> BLOSSOMING_VINES_PLANT = BLOCKS.register("blossoming_vines_plant", () -> new AerialHellCaveVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(BLOSSOMING_VINES.get())));
	public static final RegistryObject<Item> VINE_BLOSSOM = ITEMS.register("vine_blossom",() -> new ItemNameBlockItem(BLOSSOMING_VINES.get(), new Item.Properties()));
	public static final RegistryObject<AerialHellTwistingVinesBlock> LAZULI_ROOTS = BLOCKS.register("lazuli_roots", () -> new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES)));
	public static final RegistryObject<AerialHellTwistingVinesPlantBlock> LAZULI_ROOTS_PLANT = BLOCKS.register("lazuli_roots_plant", () -> new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT)));
	public static final RegistryObject<Item> LAZULI_ROOTS_ITEM = ITEMS.register("lazuli_roots", () -> new BlockItem(LAZULI_ROOTS.get(), new Item.Properties()));
	public static final RegistryObject<AerialHellTwistingVinesBlock> STELLAR_ROOTS = BLOCKS.register("stellar_roots", () -> new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES)));
	public static final RegistryObject<AerialHellTwistingVinesPlantBlock> STELLAR_ROOTS_PLANT = BLOCKS.register("stellar_roots_plant", () -> new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT)));
	public static final RegistryObject<Item> STELLAR_ROOTS_ITEM = ITEMS.register("stellar_roots", () -> new BlockItem(STELLAR_ROOTS.get(), new Item.Properties()));
	public static final RegistryObject<AerialHellTwistingVinesBlock> DEAD_ROOTS = BLOCKS.register("dead_roots", () -> new DeadRootsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES)));
	public static final RegistryObject<AerialHellTwistingVinesPlantBlock> DEAD_ROOTS_PLANT = BLOCKS.register("dead_roots_plant", () -> new DeadRootsPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT)));
	public static final RegistryObject<Item> DEAD_ROOTS_ITEM = ITEMS.register("dead_roots", () -> new BlockItem(DEAD_ROOTS.get(), new Item.Properties()));
	public static final RegistryObject<AerialHellTwistingVinesBlock> GLOWING_ROOTS = BLOCKS.register("glowing_roots", () -> new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES).lightLevel((state) -> 9)));
	public static final RegistryObject<AerialHellTwistingVinesPlantBlock> GLOWING_ROOTS_PLANT = BLOCKS.register("glowing_roots_plant", () -> new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT).lightLevel((state) -> 14)));
	public static final RegistryObject<Item> GLOWING_ROOTS_ITEM = ITEMS.register("glowing_roots", () -> new BlockItem(GLOWING_ROOTS.get(), new Item.Properties()));

	//grass
	public static final RegistryObject<Block> STELLAR_GRASS = BLOCKS.register("stellar_grass", () -> new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> STELLAR_GRASS_BALL = BLOCKS.register("stellar_grass_ball", () -> new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> STELLAR_FERN = BLOCKS.register("stellar_fern", () -> new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> STELLAR_TALL_GRASS = BLOCKS.register("stellar_tall_grass", () -> new DoublePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> STELLAR_TALL_FERN = BLOCKS.register("stellar_tall_fern", () -> new DoublePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<VerticalGrowingPlantBlock> STELLAR_VERY_TALL_GRASS = BLOCKS.register("stellar_very_tall_grass", () -> new VerticalGrowingPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE), 3));
	public static final RegistryObject<Block> BLUISH_FERN = BLOCKS.register("bluish_fern", () -> new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> TALL_BLUISH_FERN = BLOCKS.register("tall_bluish_fern", () -> new DoublePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> POLYCHROME_FERN = BLOCKS.register("polychrome_fern", () -> new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> TALL_POLYCHROME_FERN = BLOCKS.register("tall_polychrome_fern", () -> new DoublePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> STELLAR_DEAD_BUSH = BLOCKS.register("stellar_dead_bush", () -> new AerialHellDeadBushBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).mapColor(MapColor.WOOD).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> BRAMBLES = BLOCKS.register("brambles", () -> new BramblesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().strength(0.5F).sound(SoundType.GRASS)));
	public static final RegistryObject<Block> SHADOW_BRAMBLES = BLOCKS.register("shadow_brambles", () -> new BramblesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().strength(0.5F).sound(SoundType.GRASS)));
	public static final RegistryObject<Block> SHADOW_GRASS = BLOCKS.register("shadow_grass", () -> new ShadowPlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> SHADOW_GRASS_BALL = BLOCKS.register("shadow_grass_ball", () -> new ShadowPlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> PURPLISH_STELLAR_GRASS = BLOCKS.register("purplish_stellar_grass", () -> new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> STELLAR_CLOVERS = BLOCKS.register("stellar_clovers", () -> new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Block> GLOWING_STELLAR_GRASS = BLOCKS.register("glowing_stellar_grass", () -> new GlowingStellarTallGrass(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollission().lightLevel((state) -> {return state.getValue(BlockStateProperties.LIT) ? 10 : 0;}).instabreak().sound(SoundType.GRASS)));
	public static final RegistryObject<Item> STELLAR_GRASS_ITEM = ITEMS.register("stellar_grass", () -> new BlockItem(STELLAR_GRASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_GRASS_BALL_ITEM = ITEMS.register("stellar_grass_ball", () -> new BlockItem(STELLAR_GRASS_BALL.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_FERN_ITEM = ITEMS.register("stellar_fern", () -> new BlockItem(STELLAR_FERN.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_TALL_GRASS_ITEM = ITEMS.register("stellar_tall_grass", () -> new BlockItem(STELLAR_TALL_GRASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_TALL_FERN_ITEM = ITEMS.register("stellar_tall_fern", () -> new BlockItem(STELLAR_TALL_FERN.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_VERY_TALL_GRASS_ITEM = ITEMS.register("stellar_very_tall_grass",() -> new BlockItem(STELLAR_VERY_TALL_GRASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> BLUISH_FERN_ITEM = ITEMS.register("bluish_fern", () -> new BlockItem(BLUISH_FERN.get(), new Item.Properties()));
	public static final RegistryObject<Item> TALL_BLUISH_FERN_ITEM = ITEMS.register("tall_bluish_fern", () -> new BlockItem(TALL_BLUISH_FERN.get(), new Item.Properties()));
	public static final RegistryObject<Item> POLYCHROME_FERN_ITEM = ITEMS.register("polychrome_fern", () -> new BlockItem(POLYCHROME_FERN.get(), new Item.Properties()));
	public static final RegistryObject<Item> TALL_POLYCHROME_FERN_ITEM = ITEMS.register("tall_polychrome_fern", () -> new BlockItem(TALL_POLYCHROME_FERN.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_DEAD_BUSH_ITEM = ITEMS.register("stellar_dead_bush", () -> new BlockItem(STELLAR_DEAD_BUSH.get(), new Item.Properties()));
	public static final RegistryObject<Item> BRAMBLES_ITEM = ITEMS.register("brambles", () -> new BlockItem(BRAMBLES.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHADOW_BRAMBLES_ITEM = ITEMS.register("shadow_brambles", () -> new BlockItem(SHADOW_BRAMBLES.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHADOW_GRASS_ITEM = ITEMS.register("shadow_grass", () -> new BlockItem(SHADOW_GRASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> SHADOW_GRASS_BALL_ITEM = ITEMS.register("shadow_grass_ball", () -> new BlockItem(SHADOW_GRASS_BALL.get(), new Item.Properties()));
	public static final RegistryObject<Item> PURPLISH_STELLAR_GRASS_ITEM = ITEMS.register("purplish_stellar_grass", () -> new BlockItem(PURPLISH_STELLAR_GRASS.get(), new Item.Properties()));
	public static final RegistryObject<Item> STELLAR_CLOVERS_ITEM = ITEMS.register("stellar_clovers", () -> new BlockItem(STELLAR_CLOVERS.get(), new Item.Properties()));
	public static final RegistryObject<Item> GLOWING_STELLAR_GRASS_ITEM = ITEMS.register("glowing_stellar_grass", () -> new BlockItem(GLOWING_STELLAR_GRASS.get(), new Item.Properties()));

	//flowers
	public static final RegistryObject<Block> BLUE_FLOWER = BLOCKS.register("blue_flower", () -> new FlowerBlock(MobEffects.BLINDNESS, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));
	public static final RegistryObject<Item> BLUE_FLOWER_ITEM = ITEMS.register("blue_flower", () -> new BlockItem(BLUE_FLOWER.get(), new Item.Properties()));

	public static final RegistryObject<Block> BLACK_ROSE = BLOCKS.register("black_rose", () -> new FlowerBlock(MobEffects.SLOW_FALLING, 12, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));
	public static final RegistryObject<Item> BLACK_ROSE_ITEM = ITEMS.register("black_rose", () -> new BlockItem(BLACK_ROSE.get(), new Item.Properties()));

	public static final RegistryObject<Block> BELLFLOWER = BLOCKS.register("bellflower", () -> new FlowerBlock(MobEffects.DIG_SLOWDOWN, 12, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));
	public static final RegistryObject<Item> BELLFLOWER_ITEM = ITEMS.register("bellflower", () -> new BlockItem(BELLFLOWER.get(), new Item.Properties()));

	//potted things
	public static final RegistryObject<FlowerPotBlock> POTTED_BLUE_FLOWER = BLOCKS.register("potted_blue_flower", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLUE_FLOWER, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_BLACK_ROSE = BLOCKS.register("potted_black_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLACK_ROSE, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_BELLFLOWER = BLOCKS.register("potted_bellflower", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BELLFLOWER, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_STELLAR_FERN = BLOCKS.register("potted_stellar_fern", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, STELLAR_FERN, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_STELLAR_DEAD_BUSH = BLOCKS.register("potted_stellar_dead_bush", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, STELLAR_DEAD_BUSH, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_SKY_CACTUS = BLOCKS.register("potted_sky_cactus", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SKY_CACTUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_VIBRANT_SKY_CACTUS = BLOCKS.register("potted_vibrant_sky_cactus", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, VIBRANT_SKY_CACTUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_AERIAL_TREE_SAPLING = BLOCKS.register("potted_aerial_tree_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, AERIAL_TREE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_GOLDEN_BEECH_SAPLING = BLOCKS.register("potted_golden_beech_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GOLDEN_BEECH_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_COPPER_PINE_SAPLING = BLOCKS.register("potted_copper_pine_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, COPPER_PINE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_LAPIS_ROBINIA_SAPLING = BLOCKS.register("potted_lapis_robinia_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, LAPIS_ROBINIA_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_SHADOW_PINE_SAPLING = BLOCKS.register("potted_shadow_pine_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SHADOW_PINE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_PURPLE_SHADOW_PINE_SAPLING = BLOCKS.register("potted_purple_shadow_pine_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, PURPLE_SHADOW_PINE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_STELLAR_JUNGLE_TREE_SAPLING = BLOCKS.register("potted_stellar_jungle_tree_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, STELLAR_JUNGLE_TREE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_CORTINARIUS_VIOLACEUS = BLOCKS.register("potted_cortinarius_violaceus", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CORTINARIUS_VIOLACEUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_VERDIGRIS_AGARIC = BLOCKS.register("potted_verdigris_agaric", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, VERDIGRIS_AGARIC, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_VINE_BLOSSOM = BLOCKS.register("potted_vine_blossom", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLOSSOMING_VINES, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_GLOWING_BOLETUS = BLOCKS.register("potted_glowing_boletus", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GLOWING_BOLETUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).lightLevel((state) -> 9)));

	//with gui
	public static final RegistryObject<Block> OSCILLATOR = BLOCKS.register("oscillator", () -> new OscillatorBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Item> OSCILLATOR_ITEM = ITEMS.register("oscillator", () -> new BlockItem(OSCILLATOR.get(), new Item.Properties()));

	public static final RegistryObject<Block> FREEZER = BLOCKS.register("freezer",() -> new FreezerBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Item> FREEZER_ITEM = ITEMS.register("freezer", () -> new BlockItem(FREEZER.get(), new Item.Properties()));

	public static final RegistryObject<Block> STELLAR_FURNACE = BLOCKS.register("stellar_furnace", () -> new StellarFurnaceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.5F).lightLevel(getLightValueLit(13))));
	public static final RegistryObject<Item> STELLAR_FURNACE_ITEM = ITEMS.register("stellar_furnace", () -> new BlockItem(STELLAR_FURNACE.get(), new Item.Properties()));

	public static final RegistryObject<Block> GHOST_STELLAR_FURNACE = BLOCKS.register("ghost_stellar_furnace", () -> new GhostStellarFurnaceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().noOcclusion().strength(3.5F).lightLevel(getLightValueLit(13))));
	public static final RegistryObject<Item> GHOST_STELLAR_FURNACE_ITEM = ITEMS.register("ghost_stellar_furnace", () -> new BlockItem(GHOST_STELLAR_FURNACE.get(), new Item.Properties()));

	private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {return (state) -> {return state.getValue(BlockStateProperties.LIT) ? lightValue : 0;};}

	//chests
	public static final RegistryObject<ChestBlock> AERIAL_TREE_CHEST = BLOCKS.register("aerial_tree_chest", () -> new AerialHellChestBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_CHEST_ITEM = ITEMS.register("aerial_tree_chest", () -> new ChestBlockItem(AERIAL_TREE_CHEST.get(), new Item.Properties()));
	public static final RegistryObject<ChestBlock> GOLDEN_BEECH_CHEST = BLOCKS.register("golden_beech_chest", () -> new AerialHellChestBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_ITEM = ITEMS.register("golden_beech_chest", () -> new ChestBlockItem(GOLDEN_BEECH_CHEST.get(), new Item.Properties()));
	public static final RegistryObject<ChestBlock> COPPER_PINE_CHEST = BLOCKS.register("copper_pine_chest", () -> new AerialHellChestBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_ITEM = ITEMS.register("copper_pine_chest", () -> new ChestBlockItem(COPPER_PINE_CHEST.get(), new Item.Properties()));
	public static final RegistryObject<ChestBlock> LAPIS_ROBINIA_CHEST = BLOCKS.register("lapis_robinia_chest", () -> new AerialHellChestBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_CHEST_ITEM = ITEMS.register("lapis_robinia_chest", () -> new ChestBlockItem(LAPIS_ROBINIA_CHEST.get(), new Item.Properties()));
	public static final RegistryObject<ChestBlock> SHADOW_PINE_CHEST = BLOCKS.register("shadow_pine_chest", () -> new AerialHellChestBlock(SHADOW_PINE_MATERIAL));
	public static final RegistryObject<Item> SHADOW_PINE_CHEST_ITEM = ITEMS.register("shadow_pine_chest", () -> new ChestBlockItem(SHADOW_PINE_CHEST.get(), new Item.Properties()));
	public static final RegistryObject<ChestBlock> STELLAR_JUNGLE_TREE_CHEST = BLOCKS.register("stellar_jungle_tree_chest", () -> new AerialHellChestBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_CHEST_ITEM = ITEMS.register("stellar_jungle_tree_chest", () -> new ChestBlockItem(STELLAR_JUNGLE_TREE_CHEST.get(), new Item.Properties()));
	public static final RegistryObject<ChestBlock> SKY_CACTUS_FIBER_CHEST = BLOCKS.register("sky_cactus_fiber_chest", () -> new AerialHellChestBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_CHEST_ITEM = ITEMS.register("sky_cactus_fiber_chest", () -> new ChestBlockItem(SKY_CACTUS_FIBER_CHEST.get(), new Item.Properties()));
	public static final RegistryObject<ChestBlock> GRAY_SHROOM_CHEST = BLOCKS.register("gray_shroom_chest", () -> new AerialHellChestBlock(SHROOM_MATERIAL));
	public static final RegistryObject<Item> GRAY_SHROOM_CHEST_ITEM = ITEMS.register("gray_shroom_chest", () -> new ChestBlockItem(GRAY_SHROOM_CHEST.get(), new Item.Properties()));
	public static final RegistryObject<ChestBlock> MUD_CHEST = BLOCKS.register("mud_chest", () -> new CoreProtectedChestBlock(MUD_CHEST_MATERIAL));
	public static final RegistryObject<Item> MUD_CHEST_ITEM = ITEMS.register("mud_chest", () -> new ChestBlockItem(MUD_CHEST.get(), new Item.Properties()));
	public static final RegistryObject<ChestBlock> LUNATIC_CHEST = BLOCKS.register("lunatic_chest", () -> new CoreProtectedChestBlock(LUNATIC_CHEST_MATERIAL));
	public static final RegistryObject<Item> LUNATIC_CHEST_ITEM = ITEMS.register("lunatic_chest", () -> new ChestBlockItem(LUNATIC_CHEST.get(), new Item.Properties()));
	public static final RegistryObject<ChestBlock> VOLUCITE_CHEST = BLOCKS.register("volucite_chest", () -> new CoreProtectedChestBlock(VOLUCITE_CHEST_MATERIAL));
	public static final RegistryObject<Item> VOLUCITE_CHEST_ITEM = ITEMS.register("volucite_chest", () -> new ChestBlockItem(VOLUCITE_CHEST.get(), new Item.Properties()));
	public static final RegistryObject<ChestBlock> SHADOW_CATACOMBS_CHEST = BLOCKS.register("shadow_catacombs_chest", () -> new CoreProtectedChestBlock(MUD_CHEST_MATERIAL));
	public static final RegistryObject<Item> SHADOW_CATACOMBS_CHEST_ITEM = ITEMS.register("shadow_catacombs_chest", () -> new ChestBlockItem(SHADOW_CATACOMBS_CHEST.get(), new Item.Properties()));
	public static final RegistryObject<ChestBlock> GOLDEN_NETHER_CHEST = BLOCKS.register("golden_nether_chest", () -> new CoreProtectedChestBlock(GOLDEN_NETHER_CHEST_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_NETHER_CHEST_ITEM = ITEMS.register("golden_nether_chest", () -> new ChestBlockItem(GOLDEN_NETHER_CHEST.get(), new Item.Properties()));

	//chest mimics
	public static final RegistryObject<Block> AERIAL_TREE_CHEST_MIMIC = BLOCKS.register("aerial_tree_chest_mimic", () -> new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
	public static final RegistryObject<Block> GOLDEN_BEECH_CHEST_MIMIC = BLOCKS.register("golden_beech_chest_mimic", () -> new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
	public static final RegistryObject<Block> COPPER_PINE_CHEST_MIMIC = BLOCKS.register("copper_pine_chest_mimic", () -> new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
	public static final RegistryObject<Block> SKY_CACTUS_FIBER_CHEST_MIMIC = BLOCKS.register("sky_cactus_fiber_chest_mimic", () -> new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));

	public static final RegistryObject<Item> AERIAL_TREE_CHEST_MIMIC_ITEM = ITEMS.register("aerial_tree_chest_mimic", () -> new BlockItem(AERIAL_TREE_CHEST_MIMIC.get(), new Item.Properties()));
	public static final RegistryObject<Item> GOLDEN_BEECH_CHEST_MIMIC_ITEM = ITEMS.register("golden_beech_chest_mimic", () -> new BlockItem(GOLDEN_BEECH_CHEST_MIMIC.get(), new Item.Properties()));
	public static final RegistryObject<Item> COPPER_PINE_CHEST_MIMIC_ITEM = ITEMS.register("copper_pine_chest_mimic", () -> new BlockItem(COPPER_PINE_CHEST_MIMIC.get(), new Item.Properties()));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_CHEST_MIMIC_ITEM = ITEMS.register("sky_cactus_fiber_chest_mimic", () -> new BlockItem(SKY_CACTUS_FIBER_CHEST_MIMIC.get(), new Item.Properties()));

	//barrel mimics
	public static final RegistryObject<Block> SHADOW_PINE_BARREL_MIMIC = BLOCKS.register("shadow_pine_barrel_mimic", () -> new BarrelMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL)));

	public static final RegistryObject<Item> SHADOW_PINE_BARREL_MIMIC_ITEM = ITEMS.register("shadow_pine_barrel_mimic", () -> new BlockItem(SHADOW_PINE_BARREL_MIMIC.get(), new Item.Properties()));

	//fences, bars or walls
	public static final RegistryObject<FenceBlock> AERIAL_TREE_FENCE = BLOCKS.register("aerial_tree_fence", () -> new FenceBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_FENCE_ITEM = ITEMS.register("aerial_tree_fence", () -> new BurnableBlockItem(AERIAL_TREE_FENCE.get(), new Item.Properties(), 300));
	public static final RegistryObject<FenceBlock> GOLDEN_BEECH_FENCE = BLOCKS.register("golden_beech_fence", () -> new FenceBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_FENCE_ITEM = ITEMS.register("golden_beech_fence", () -> new BurnableBlockItem(GOLDEN_BEECH_FENCE.get(), new Item.Properties(), 300));
	public static final RegistryObject<FenceBlock> COPPER_PINE_FENCE = BLOCKS.register("copper_pine_fence", () -> new FenceBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_FENCE_ITEM = ITEMS.register("copper_pine_fence", () -> new BurnableBlockItem(COPPER_PINE_FENCE.get(), new Item.Properties(), 300));
	public static final RegistryObject<FenceBlock> LAPIS_ROBINIA_FENCE = BLOCKS.register("lapis_robinia_fence", () -> new FenceBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_FENCE_ITEM = ITEMS.register("lapis_robinia_fence", () -> new BurnableBlockItem(LAPIS_ROBINIA_FENCE.get(), new Item.Properties(), 300));
	public static final RegistryObject<FenceBlock> SHADOW_PINE_FENCE = BLOCKS.register("shadow_pine_fence", () -> new FenceBlock(SHADOW_PINE_MATERIAL));
	public static final RegistryObject<Item> SHADOW_PINE_FENCE_ITEM = ITEMS.register("shadow_pine_fence", () -> new BurnableBlockItem(SHADOW_PINE_FENCE.get(), new Item.Properties(), 300));
	public static final RegistryObject<FenceBlock> STELLAR_JUNGLE_TREE_FENCE = BLOCKS.register("stellar_jungle_tree_fence", () -> new FenceBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_FENCE_ITEM = ITEMS.register("stellar_jungle_tree_fence", () -> new BurnableBlockItem(STELLAR_JUNGLE_TREE_FENCE.get(), new Item.Properties(), 300));
	public static final RegistryObject<FenceBlock> SKY_CACTUS_FIBER_FENCE = BLOCKS.register("sky_cactus_fiber_fence", () -> new FenceBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_FENCE_ITEM = ITEMS.register("sky_cactus_fiber_fence", () -> new BlockItem(SKY_CACTUS_FIBER_FENCE.get(), new Item.Properties()));
	public static final RegistryObject<FenceBlock> GRAY_SHROOM_FENCE = BLOCKS.register("gray_shroom_fence", () -> new FenceBlock(SHROOM_MATERIAL));
	public static final RegistryObject<Item> GRAY_SHROOM_FENCE_ITEM = ITEMS.register("gray_shroom_fence", () -> new BurnableBlockItem(GRAY_SHROOM_FENCE.get(), new Item.Properties(), 100));
	public static final RegistryObject<IronBarsBlock> RUBY_BARS = BLOCKS.register("ruby_bars", () -> new IronBarsBlock(METAL_NOTSOLID_MATERIAL));
	public static final RegistryObject<Item> RUBY_BARS_ITEM = ITEMS.register("ruby_bars", () -> new BlockItem(RUBY_BARS.get(), new Item.Properties()));
	public static final RegistryObject<WallBlock> STELLAR_STONE_WALL = BLOCKS.register("stellar_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get())));
	public static final RegistryObject<Item> STELLAR_STONE_WALL_ITEM = ITEMS.register("stellar_stone_wall", () -> new BlockItem(STELLAR_STONE_WALL.get(), new Item.Properties()));
	public static final RegistryObject<WallBlock> STELLAR_COBBLESTONE_WALL = BLOCKS.register("stellar_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE.get())));
	public static final RegistryObject<Item> STELLAR_COBBLESTONE_WALL_ITEM = ITEMS.register("stellar_cobblestone_wall", () -> new BlockItem(STELLAR_COBBLESTONE_WALL.get(), new Item.Properties()));
	public static final RegistryObject<WallBlock> STELLAR_STONE_BRICKS_WALL = BLOCKS.register("stellar_stone_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS.get())));
	public static final RegistryObject<Item> STELLAR_STONE_BRICKS_WALL_ITEM = ITEMS.register("stellar_stone_bricks_wall", () -> new BlockItem(STELLAR_STONE_BRICKS_WALL.get(), new Item.Properties()));
	public static final RegistryObject<WallBlock> MOSSY_STELLAR_STONE_WALL = BLOCKS.register("mossy_stellar_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_STONE.get())));
	public static final RegistryObject<Item> MOSSY_STELLAR_STONE_WALL_ITEM = ITEMS.register("mossy_stellar_stone_wall", () -> new BlockItem(MOSSY_STELLAR_STONE_WALL.get(), new Item.Properties()));
	public static final RegistryObject<WallBlock> MOSSY_STELLAR_COBBLESTONE_WALL = BLOCKS.register("mossy_stellar_cobblestone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_COBBLESTONE.get())));
	public static final RegistryObject<Item> MOSSY_STELLAR_COBBLESTONE_WALL_ITEM = ITEMS.register("mossy_stellar_cobblestone_wall", () -> new BlockItem(MOSSY_STELLAR_COBBLESTONE_WALL.get(), new Item.Properties()));
	public static final RegistryObject<WallBlock> SLIPPERY_SAND_STONE_WALL = BLOCKS.register("slippery_sand_stone_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_WALL_ITEM = ITEMS.register("slippery_sand_stone_wall", () -> new BlockItem(SLIPPERY_SAND_STONE_WALL.get(), new Item.Properties()));
	public static final RegistryObject<WallBlock> SLIPPERY_SAND_STONE_BRICKS_WALL = BLOCKS.register("slippery_sand_stone_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_BRICKS_WALL_ITEM = ITEMS.register("slippery_sand_stone_bricks_wall", () -> new BlockItem(SLIPPERY_SAND_STONE_BRICKS_WALL.get(), new Item.Properties()));
	public static final RegistryObject<WallBlock> CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL = BLOCKS.register("cracked_slippery_sand_stone_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final RegistryObject<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL_ITEM = ITEMS.register("cracked_slippery_sand_stone_bricks_wall", () -> new BlockItem(CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL.get(), new Item.Properties()));
	public static final RegistryObject<WallBlock> GLAUCOPHANITE_WALL = BLOCKS.register("glaucophanite_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(GLAUCOPHANITE.get())));
	public static final RegistryObject<Item> GLAUCOPHANITE_WALL_ITEM = ITEMS.register("glaucophanite_wall", () -> new BlockItem(GLAUCOPHANITE_WALL.get(), new Item.Properties()));
	public static final RegistryObject<WallBlock> POLISHED_GLAUCOPHANITE_WALL = BLOCKS.register("polished_glaucophanite_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_GLAUCOPHANITE.get())));
	public static final RegistryObject<Item> POLISHED_GLAUCOPHANITE_WALL_ITEM = ITEMS.register("polished_glaucophanite_wall", () -> new BlockItem(POLISHED_GLAUCOPHANITE_WALL.get(), new Item.Properties()));
	public static final RegistryObject<WallBlock> MAGMATIC_GEL_WALL = BLOCKS.register("magmatic_gel_wall", () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(MAGMATIC_GEL_BLOCK.get())));
	public static final RegistryObject<Item> MAGMATIC_GEL_WALL_ITEM = ITEMS.register("magmatic_gel_wall", () -> new BlockItem(MAGMATIC_GEL_WALL.get(), new Item.Properties()));

	//gates
	public static final RegistryObject<FenceGateBlock> AERIAL_TREE_GATE = BLOCKS.register("aerial_tree_gate", () -> new FenceGateBlock(AerialHellWoodTypes.AERIAL_TREE, AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_GATE_ITEM = ITEMS.register("aerial_tree_gate", () -> new BurnableBlockItem(AERIAL_TREE_GATE.get(), new Item.Properties(), 300));
	public static final RegistryObject<FenceGateBlock> GOLDEN_BEECH_GATE = BLOCKS.register("golden_beech_gate", () -> new FenceGateBlock(AerialHellWoodTypes.GOLDEN_BEECH, AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_GATE_ITEM = ITEMS.register("golden_beech_gate", () -> new BurnableBlockItem(GOLDEN_BEECH_GATE.get(), new Item.Properties(), 300));
	public static final RegistryObject<FenceGateBlock> COPPER_PINE_GATE = BLOCKS.register("copper_pine_gate", () -> new FenceGateBlock(AerialHellWoodTypes.COPPER_PINE, COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_GATE_ITEM = ITEMS.register("copper_pine_gate", () -> new BurnableBlockItem(COPPER_PINE_GATE.get(), new Item.Properties(), 300));
	public static final RegistryObject<FenceGateBlock> LAPIS_ROBINIA_GATE = BLOCKS.register("lapis_robinia_gate", () -> new FenceGateBlock(AerialHellWoodTypes.LAPIS_ROBINIA, COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_GATE_ITEM = ITEMS.register("lapis_robinia_gate", () -> new BurnableBlockItem(LAPIS_ROBINIA_GATE.get(), new Item.Properties(), 300));
	public static final RegistryObject<FenceGateBlock> SHADOW_PINE_GATE = BLOCKS.register("shadow_pine_gate", () -> new FenceGateBlock(AerialHellWoodTypes.SHADOW_PINE, SHADOW_PINE_MATERIAL));
	public static final RegistryObject<Item> SHADOW_PINE_GATE_ITEM = ITEMS.register("shadow_pine_gate", () -> new BurnableBlockItem(SHADOW_PINE_GATE.get(), new Item.Properties(), 300));
	public static final RegistryObject<FenceGateBlock> STELLAR_JUNGLE_TREE_GATE = BLOCKS.register("stellar_jungle_tree_gate", () -> new FenceGateBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_GATE_ITEM = ITEMS.register("stellar_jungle_tree_gate", () -> new BurnableBlockItem(STELLAR_JUNGLE_TREE_GATE.get(), new Item.Properties(), 300));
	public static final RegistryObject<FenceGateBlock> SKY_CACTUS_FIBER_GATE = BLOCKS.register("sky_cactus_fiber_gate", () -> new FenceGateBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_GATE_ITEM = ITEMS.register("sky_cactus_fiber_gate", () -> new BlockItem(SKY_CACTUS_FIBER_GATE.get(), new Item.Properties()));
	public static final RegistryObject<FenceGateBlock> GRAY_SHROOM_GATE = BLOCKS.register("gray_shroom_gate", () -> new FenceGateBlock(AerialHellWoodTypes.GRAY_SHROOM, SHROOM_MATERIAL));
	public static final RegistryObject<Item> GRAY_SHROOM_GATE_ITEM = ITEMS.register("gray_shroom_gate", () -> new BurnableBlockItem(GRAY_SHROOM_GATE.get(), new Item.Properties(), 100));

	//doors
	public static final RegistryObject<DoorBlock> AERIAL_TREE_DOOR = BLOCKS.register("aerial_tree_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<Item> AERIAL_TREE_DOOR_ITEM = ITEMS.register("aerial_tree_door", () -> new BlockItem(AERIAL_TREE_DOOR.get(), new Item.Properties()));
	public static final RegistryObject<DoorBlock> GOLDEN_BEECH_DOOR = BLOCKS.register("golden_beech_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<Item> GOLDEN_BEECH_DOOR_ITEM = ITEMS.register("golden_beech_door", () -> new BlockItem(GOLDEN_BEECH_DOOR.get(), new Item.Properties()));
	public static final RegistryObject<DoorBlock> COPPER_PINE_DOOR = BLOCKS.register("copper_pine_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<Item> COPPER_PINE_DOOR_ITEM = ITEMS.register("copper_pine_door", () -> new BlockItem(COPPER_PINE_DOOR.get(), new Item.Properties()));
	public static final RegistryObject<DoorBlock> LAPIS_ROBINIA_DOOR = BLOCKS.register("lapis_robinia_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<Item> LAPIS_ROBINIA_DOOR_ITEM = ITEMS.register("lapis_robinia_door", () -> new BlockItem(LAPIS_ROBINIA_DOOR.get(), new Item.Properties()));
	public static final RegistryObject<DoorBlock> SHADOW_PINE_DOOR = BLOCKS.register("shadow_pine_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<Item> SHADOW_PINE_DOOR_ITEM = ITEMS.register("shadow_pine_door", () -> new BlockItem(SHADOW_PINE_DOOR.get(), new Item.Properties()));
	public static final RegistryObject<DoorBlock> STELLAR_JUNGLE_TREE_DOOR = BLOCKS.register("stellar_jungle_tree_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_DOOR_ITEM = ITEMS.register("stellar_jungle_tree_door", () -> new BlockItem(STELLAR_JUNGLE_TREE_DOOR.get(), new Item.Properties()));
	public static final RegistryObject<DoorBlock> SKY_CACTUS_FIBER_DOOR = BLOCKS.register("sky_cactus_fiber_door", () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_DOOR_ITEM = ITEMS.register("sky_cactus_fiber_door", () -> new BlockItem(SKY_CACTUS_FIBER_DOOR.get(), new Item.Properties()));
	public static final RegistryObject<DoorBlock> GRAY_SHROOM_DOOR = BLOCKS.register("gray_shroom_door", () -> new DoorBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<Item> GRAY_SHROOM_DOOR_ITEM = ITEMS.register("gray_shroom_door", () -> new BlockItem(GRAY_SHROOM_DOOR.get(), new Item.Properties()));
	public static final RegistryObject<DoorBlock> RUBY_DOOR = BLOCKS.register("ruby_door", () -> new DoorBlock(BlockSetType.IRON, METAL_NOTSOLID_MATERIAL));
	public static final RegistryObject<Item> RUBY_DOOR_ITEM = ITEMS.register("ruby_door", () -> new BlockItem(RUBY_DOOR.get(), new Item.Properties()));

	//trapdoors
	public static final RegistryObject<TrapDoorBlock> AERIAL_TREE_TRAPDOOR = BLOCKS.register("aerial_tree_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<Item> AERIAL_TREE_TRAPDOOR_ITEM = ITEMS.register("aerial_tree_trapdoor", () -> new BlockItem(AERIAL_TREE_TRAPDOOR.get(), new Item.Properties()));
	public static final RegistryObject<TrapDoorBlock> GOLDEN_BEECH_TRAPDOOR = BLOCKS.register("golden_beech_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<Item> GOLDEN_BEECH_TRAPDOOR_ITEM = ITEMS.register("golden_beech_trapdoor", () -> new BlockItem(GOLDEN_BEECH_TRAPDOOR.get(), new Item.Properties()));
	public static final RegistryObject<TrapDoorBlock> COPPER_PINE_TRAPDOOR = BLOCKS.register("copper_pine_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<Item> COPPER_PINE_TRAPDOOR_ITEM = ITEMS.register("copper_pine_trapdoor", () -> new BlockItem(COPPER_PINE_TRAPDOOR.get(), new Item.Properties()));
	public static final RegistryObject<TrapDoorBlock> LAPIS_ROBINIA_TRAPDOOR = BLOCKS.register("lapis_robinia_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<Item> LAPIS_ROBINIA_TRAPDOOR_ITEM = ITEMS.register("lapis_robinia_trapdoor", () -> new BlockItem(LAPIS_ROBINIA_TRAPDOOR.get(), new Item.Properties()));
	public static final RegistryObject<TrapDoorBlock> SHADOW_PINE_TRAPDOOR = BLOCKS.register("shadow_pine_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<Item> SHADOW_PINE_TRAPDOOR_ITEM = ITEMS.register("shadow_pine_trapdoor", () -> new BlockItem(SHADOW_PINE_TRAPDOOR.get(), new Item.Properties()));
	public static final RegistryObject<TrapDoorBlock> STELLAR_JUNGLE_TREE_TRAPDOOR = BLOCKS.register("stellar_jungle_tree_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_TRAPDOOR_ITEM = ITEMS.register("stellar_jungle_tree_trapdoor", () -> new BlockItem(STELLAR_JUNGLE_TREE_TRAPDOOR.get(), new Item.Properties()));
	public static final RegistryObject<TrapDoorBlock> SKY_CACTUS_FIBER_TRAPDOOR = BLOCKS.register("sky_cactus_fiber_trapdoor", () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_TRAPDOOR_ITEM = ITEMS.register("sky_cactus_fiber_trapdoor", () -> new BlockItem( SKY_CACTUS_FIBER_TRAPDOOR.get(), new Item.Properties()));
	public static final RegistryObject<TrapDoorBlock> GRAY_SHROOM_TRAPDOOR = BLOCKS.register("gray_shroom_trapdoor", () -> new TrapDoorBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_PLANKS.get()).noOcclusion()));
	public static final RegistryObject<Item> GRAY_SHROOM_TRAPDOOR_ITEM = ITEMS.register("gray_shroom_trapdoor", () -> new BlockItem(GRAY_SHROOM_TRAPDOOR.get(), new Item.Properties()));
	public static final RegistryObject<TrapDoorBlock> RUBY_TRAPDOOR = BLOCKS.register("ruby_trapdoor", () -> new TrapDoorBlock(BlockSetType.IRON, METAL_NOTSOLID_MATERIAL));
	public static final RegistryObject<Item> RUBY_TRAPDOOR_ITEM = ITEMS.register("ruby_trapdoor", () -> new BlockItem(RUBY_TRAPDOOR.get(), new Item.Properties()));

	//buttons
	public static final RegistryObject<ButtonBlock> STELLAR_STONE_BUTTON = BLOCKS.register("stellar_stone_button", () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get())));
	public static final RegistryObject<Item> STELLAR_STONE_BUTTON_ITEM = ITEMS.register("stellar_stone_button", () -> new BlockItem(STELLAR_STONE_BUTTON.get(), new Item.Properties()));
	public static final RegistryObject<ButtonBlock> STELLAR_COBBLESTONE_BUTTON = BLOCKS.register("stellar_cobblestone_button", () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE.get())));
	public static final RegistryObject<Item> STELLAR_COBBLESTONE_BUTTON_ITEM = ITEMS.register("stellar_cobblestone_button", () -> new BlockItem(STELLAR_COBBLESTONE_BUTTON.get(), new Item.Properties()));
	public static final RegistryObject<ButtonBlock> STELLAR_STONE_BRICKS_BUTTON = BLOCKS.register("stellar_stone_bricks_button", () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS.get())));
	public static final RegistryObject<Item> STELLAR_STONE_BRICKS_BUTTON_ITEM = ITEMS.register("stellar_stone_bricks_button", () -> new BlockItem(STELLAR_STONE_BRICKS_BUTTON.get(), new Item.Properties()));
	public static final RegistryObject<ButtonBlock> SLIPPERY_SAND_STONE_BUTTON = BLOCKS.register("slippery_sand_stone_button", () -> new ButtonBlock(BlockSetType.STONE, 30, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_BUTTON_ITEM = ITEMS.register("slippery_sand_stone_button", () -> new BlockItem(SLIPPERY_SAND_STONE_BUTTON.get(), new Item.Properties()));
	public static final RegistryObject<ButtonBlock> SLIPPERY_SAND_STONE_BRICKS_BUTTON = BLOCKS.register("slippery_sand_stone_bricks_button", () -> new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_BRICKS_BUTTON_ITEM = ITEMS.register("slippery_sand_stone_bricks_button", () -> new BlockItem(SLIPPERY_SAND_STONE_BRICKS_BUTTON.get(), new Item.Properties()));
	public static final RegistryObject<ButtonBlock> AERIAL_TREE_BUTTON = BLOCKS.register("aerial_tree_button", () -> new ButtonBlock(BlockSetType.OAK, 30, AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_BUTTON_ITEM = ITEMS.register("aerial_tree_button", () -> new BurnableBlockItem(AERIAL_TREE_BUTTON.get(), new Item.Properties(), 100));
	public static final RegistryObject<ButtonBlock> GOLDEN_BEECH_BUTTON = BLOCKS.register("golden_beech_button", () -> new ButtonBlock(BlockSetType.OAK, 30, AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_BUTTON_ITEM = ITEMS.register("golden_beech_button", () -> new BurnableBlockItem(GOLDEN_BEECH_BUTTON.get(), new Item.Properties(), 100));
	public static final RegistryObject<ButtonBlock> COPPER_PINE_BUTTON = BLOCKS.register("copper_pine_button", () -> new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_BUTTON_ITEM = ITEMS.register("copper_pine_button", () -> new BurnableBlockItem(COPPER_PINE_BUTTON.get(), new Item.Properties(), 100));
	public static final RegistryObject<ButtonBlock> LAPIS_ROBINIA_BUTTON = BLOCKS.register("lapis_robinia_button", () -> new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_BUTTON_ITEM = ITEMS.register("lapis_robinia_button", () -> new BurnableBlockItem(LAPIS_ROBINIA_BUTTON.get(), new Item.Properties(), 100));
	public static final RegistryObject<ButtonBlock> SHADOW_PINE_BUTTON = BLOCKS.register("shadow_pine_button", () -> new ButtonBlock(BlockSetType.OAK, 30, SHADOW_PINE_MATERIAL));
	public static final RegistryObject<Item> SHADOW_PINE_BUTTON_ITEM = ITEMS.register("shadow_pine_button", () -> new BurnableBlockItem(SHADOW_PINE_BUTTON.get(), new Item.Properties(), 100));
	public static final RegistryObject<ButtonBlock> STELLAR_JUNGLE_TREE_BUTTON = BLOCKS.register("stellar_jungle_tree_button", () -> new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_BUTTON_ITEM = ITEMS.register("stellar_jungle_tree_button", () -> new BurnableBlockItem(STELLAR_JUNGLE_TREE_BUTTON.get(), new Item.Properties(), 100));
	public static final RegistryObject<ButtonBlock> SKY_CACTUS_FIBER_BUTTON = BLOCKS.register("sky_cactus_fiber_button", () -> new ButtonBlock(BlockSetType.OAK, 30, SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_BUTTON_ITEM = ITEMS.register("sky_cactus_fiber_button", () -> new BlockItem(SKY_CACTUS_FIBER_BUTTON.get(), new Item.Properties()));
	public static final RegistryObject<ButtonBlock> GRAY_SHROOM_BUTTON = BLOCKS.register("gray_shroom_button", () -> new ButtonBlock(BlockSetType.CRIMSON, 30, SHROOM_MATERIAL));
	public static final RegistryObject<Item> GRAY_SHROOM_BUTTON_ITEM = ITEMS.register("gray_shroom_button", () -> new BurnableBlockItem(GRAY_SHROOM_BUTTON.get(), new Item.Properties(), 30));
	public static final RegistryObject<ButtonBlock> GLAUCOPHANITE_BUTTON = BLOCKS.register("glaucophanite_button", () -> new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(GLAUCOPHANITE.get())));
	public static final RegistryObject<Item> GLAUCOPHANITE_BUTTON_ITEM = ITEMS.register("glaucophanite_button", () -> new BlockItem(GLAUCOPHANITE_BUTTON.get(), new Item.Properties()));

	//pressure plates
	public static final RegistryObject<PressurePlateBlock> STELLAR_STONE_PRESSURE_PLATE = BLOCKS.register("stellar_stone_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get())));
	public static final RegistryObject<Item> STELLAR_STONE_PRESSURE_PLATE_ITEM = ITEMS.register("stellar_stone_pressure_plate", () -> new BlockItem(STELLAR_STONE_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final RegistryObject<PressurePlateBlock> STELLAR_COBBLESTONE_PRESSURE_PLATE = BLOCKS.register("stellar_cobblestone_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE.get())));
	public static final RegistryObject<Item> STELLAR_COBBLESTONE_PRESSURE_PLATE_ITEM = ITEMS.register("stellar_cobblestone_pressure_plate", () -> new BlockItem(STELLAR_COBBLESTONE_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final RegistryObject<PressurePlateBlock> STELLAR_STONE_BRICKS_PRESSURE_PLATE = BLOCKS.register("stellar_stone_bricks_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS.get())));
	public static final RegistryObject<Item> STELLAR_STONE_BRICKS_PRESSURE_PLATE_ITEM = ITEMS.register("stellar_stone_bricks_pressure_plate", () -> new BlockItem(STELLAR_STONE_BRICKS_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final RegistryObject<PressurePlateBlock> SLIPPERY_SAND_STONE_PRESSURE_PLATE = BLOCKS.register("slippery_sand_stone_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_PRESSURE_PLATE_ITEM = ITEMS.register("slippery_sand_stone_pressure_plate", () -> new BlockItem(SLIPPERY_SAND_STONE_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final RegistryObject<PressurePlateBlock> SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE = BLOCKS.register("slippery_sand_stone_bricks_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE_ITEM = ITEMS.register("slippery_sand_stone_bricks_pressure_plate", () -> new BlockItem(SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final RegistryObject<PressurePlateBlock> AERIAL_TREE_PRESSURE_PLATE = BLOCKS.register("aerial_tree_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_PRESSURE_PLATE_ITEM = ITEMS.register("aerial_tree_pressure_plate", () -> new BurnableBlockItem(AERIAL_TREE_PRESSURE_PLATE.get(), new Item.Properties(), 300));
	public static final RegistryObject<PressurePlateBlock> GOLDEN_BEECH_PRESSURE_PLATE = BLOCKS.register("golden_beech_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_PRESSURE_PLATE_ITEM = ITEMS.register("golden_beech_pressure_plate", () -> new BurnableBlockItem(GOLDEN_BEECH_PRESSURE_PLATE.get(), new Item.Properties(), 300));
	public static final RegistryObject<PressurePlateBlock> COPPER_PINE_PRESSURE_PLATE = BLOCKS.register("copper_pine_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_PRESSURE_PLATE_ITEM = ITEMS.register("copper_pine_pressure_plate", () -> new BurnableBlockItem(COPPER_PINE_PRESSURE_PLATE.get(), new Item.Properties(), 300));
	public static final RegistryObject<PressurePlateBlock> LAPIS_ROBINIA_PRESSURE_PLATE = BLOCKS.register("lapis_robinia_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_PRESSURE_PLATE_ITEM = ITEMS.register("lapis_robinia_pressure_plate", () -> new BurnableBlockItem(LAPIS_ROBINIA_PRESSURE_PLATE.get(), new Item.Properties(), 300));
	public static final RegistryObject<PressurePlateBlock> SHADOW_PINE_PRESSURE_PLATE = BLOCKS.register("shadow_pine_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, SHADOW_PINE_MATERIAL));
	public static final RegistryObject<Item> SHADOW_PINE_PRESSURE_PLATE_ITEM = ITEMS.register("shadow_pine_pressure_plate", () -> new BurnableBlockItem(SHADOW_PINE_PRESSURE_PLATE.get(), new Item.Properties(), 300));
	public static final RegistryObject<PressurePlateBlock> STELLAR_JUNGLE_TREE_PRESSURE_PLATE = BLOCKS.register("stellar_jungle_tree_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_PRESSURE_PLATE_ITEM = ITEMS.register("stellar_jungle_tree_pressure_plate", () -> new BurnableBlockItem(STELLAR_JUNGLE_TREE_PRESSURE_PLATE.get(), new Item.Properties(), 300));
	public static final RegistryObject<PressurePlateBlock> SKY_CACTUS_FIBER_PRESSURE_PLATE = BLOCKS.register("sky_cactus_fiber_pressure_plate", () -> new PressurePlateBlock(BlockSetType.OAK, SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_PRESSURE_PLATE_ITEM = ITEMS.register("sky_cactus_fiber_pressure_plate", () -> new BlockItem(SKY_CACTUS_FIBER_PRESSURE_PLATE.get(), new Item.Properties()));
	public static final RegistryObject<PressurePlateBlock> GRAY_SHROOM_PRESSURE_PLATE = BLOCKS.register("gray_shroom_pressure_plate", () -> new PressurePlateBlock(BlockSetType.CRIMSON, SHROOM_MATERIAL));
	public static final RegistryObject<Item> GRAY_SHROOM_PRESSURE_PLATE_ITEM = ITEMS.register("gray_shroom_pressure_plate", () -> new BurnableBlockItem(GRAY_SHROOM_PRESSURE_PLATE.get(), new Item.Properties(), 100));
	public static final RegistryObject<PressurePlateBlock> GLAUCOPHANITE_PRESSURE_PLATE = BLOCKS.register("glaucophanite_pressure_plate", () -> new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(GLAUCOPHANITE.get())));
	public static final RegistryObject<Item> GLAUCOPHANITE_PRESSURE_PLATE_ITEM = ITEMS.register("glaucophanite_pressure_plate", () -> new BlockItem(GLAUCOPHANITE_PRESSURE_PLATE.get(), new Item.Properties()));

	//slabs
	public static final RegistryObject<SlabBlock> AERIAL_TREE_SLAB = BLOCKS.register("aerial_tree_slab", () -> new SlabBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_SLAB_ITEM = ITEMS.register("aerial_tree_slab", () -> new BurnableBlockItem(AERIAL_TREE_SLAB.get(), new Item.Properties(), 150));
	public static final RegistryObject<SlabBlock> GOLDEN_BEECH_SLAB = BLOCKS.register("golden_beech_slab", () -> new SlabBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_SLAB_ITEM = ITEMS.register("golden_beech_slab", () -> new BurnableBlockItem(GOLDEN_BEECH_SLAB.get(), new Item.Properties(), 150));
	public static final RegistryObject<SlabBlock> COPPER_PINE_SLAB = BLOCKS.register("copper_pine_slab", () -> new SlabBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_SLAB_ITEM = ITEMS.register("copper_pine_slab", () -> new BurnableBlockItem(COPPER_PINE_SLAB.get(), new Item.Properties(), 150));
	public static final RegistryObject<SlabBlock> LAPIS_ROBINIA_SLAB = BLOCKS.register("lapis_robinia_slab", () -> new SlabBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_SLAB_ITEM = ITEMS.register("lapis_robinia_slab", () -> new BurnableBlockItem(LAPIS_ROBINIA_SLAB.get(), new Item.Properties(), 150));
	public static final RegistryObject<SlabBlock> SHADOW_PINE_SLAB = BLOCKS.register("shadow_pine_slab", () -> new SlabBlock(SHADOW_PINE_MATERIAL));
	public static final RegistryObject<Item> SHADOW_PINE_SLAB_ITEM = ITEMS.register("shadow_pine_slab", () -> new BurnableBlockItem(SHADOW_PINE_SLAB.get(), new Item.Properties(), 150));
	public static final RegistryObject<SlabBlock> STELLAR_JUNGLE_TREE_SLAB = BLOCKS.register("stellar_jungle_tree_slab", () -> new SlabBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_SLAB_ITEM = ITEMS.register("stellar_jungle_tree_slab", () -> new BurnableBlockItem(STELLAR_JUNGLE_TREE_SLAB.get(), new Item.Properties(), 150));
	public static final RegistryObject<SlabBlock> SKY_CACTUS_FIBER_SLAB = BLOCKS.register("sky_cactus_fiber_slab", () -> new SlabBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_SLAB_ITEM = ITEMS.register("sky_cactus_fiber_slab", () -> new BlockItem(SKY_CACTUS_FIBER_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<SlabBlock> GRAY_SHROOM_SLAB = BLOCKS.register("gray_shroom_slab", () -> new SlabBlock(SHROOM_MATERIAL));
	public static final RegistryObject<Item> GRAY_SHROOM_SLAB_ITEM = ITEMS.register("gray_shroom_slab", () -> new BurnableBlockItem(GRAY_SHROOM_SLAB.get(), new Item.Properties(), 50));
	public static final RegistryObject<SlabBlock> STELLAR_STONE_SLAB = BLOCKS.register("stellar_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get())));
	public static final RegistryObject<Item> STELLAR_STONE_SLAB_ITEM = ITEMS.register("stellar_stone_slab", () -> new BlockItem(STELLAR_STONE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<SlabBlock> STELLAR_COBBLESTONE_SLAB = BLOCKS.register("stellar_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE.get())));
	public static final RegistryObject<Item> STELLAR_COBBLESTONE_SLAB_ITEM = ITEMS.register("stellar_cobblestone_slab", () -> new BlockItem(STELLAR_COBBLESTONE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<SlabBlock> STELLAR_STONE_BRICKS_SLAB = BLOCKS.register("stellar_stone_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS.get())));
	public static final RegistryObject<Item> STELLAR_STONE_BRICKS_SLAB_ITEM = ITEMS.register("stellar_stone_bricks_slab", () -> new BlockItem(STELLAR_STONE_BRICKS_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<SlabBlock> MOSSY_STELLAR_STONE_SLAB = BLOCKS.register("mossy_stellar_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_STONE.get())));
	public static final RegistryObject<Item> MOSSY_STELLAR_STONE_SLAB_ITEM = ITEMS.register("mossy_stellar_stone_slab", () -> new BlockItem(MOSSY_STELLAR_STONE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<SlabBlock> MOSSY_STELLAR_COBBLESTONE_SLAB = BLOCKS.register("mossy_stellar_cobblestone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_COBBLESTONE.get())));
	public static final RegistryObject<Item> MOSSY_STELLAR_COBBLESTONE_SLAB_ITEM = ITEMS.register("mossy_stellar_cobblestone_slab", () -> new BlockItem(MOSSY_STELLAR_COBBLESTONE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<SlabBlock> SLIPPERY_SAND_STONE_SLAB = BLOCKS.register("slippery_sand_stone_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_SLAB_ITEM = ITEMS.register("slippery_sand_stone_slab", () -> new BlockItem(SLIPPERY_SAND_STONE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<SlabBlock> SLIPPERY_SAND_STONE_BRICKS_SLAB = BLOCKS.register("slippery_sand_stone_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_BRICKS_SLAB_ITEM = ITEMS.register("slippery_sand_stone_bricks_slab", () -> new BlockItem(SLIPPERY_SAND_STONE_BRICKS_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<SlabBlock> CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB = BLOCKS.register("cracked_slippery_sand_stone_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final RegistryObject<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB_ITEM = ITEMS.register("cracked_slippery_sand_stone_bricks_slab", () -> new BlockItem(CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<SlabBlock> POLISHED_GLAUCOPHANITE_SLAB = BLOCKS.register("polished_glaucophanite_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_GLAUCOPHANITE.get())));
	public static final RegistryObject<Item> POLISHED_GLAUCOPHANITE_SLAB_ITEM = ITEMS.register("polished_glaucophanite_slab", () -> new BlockItem(POLISHED_GLAUCOPHANITE_SLAB.get(), new Item.Properties()));
	public static final RegistryObject<SlabBlock> MAGMATIC_GEL_SLAB = BLOCKS.register("magmatic_gel_slab", () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MAGMATIC_GEL_BLOCK.get())));
	public static final RegistryObject<Item> MAGMATIC_GEL_SLAB_ITEM = ITEMS.register("magmatic_gel_slab", () -> new BlockItem(MAGMATIC_GEL_SLAB.get(), new Item.Properties()));

	//stairs
	public static final RegistryObject<StairBlock> AERIAL_TREE_STAIRS = BLOCKS.register("aerial_tree_stairs", () -> new StairBlock(AERIAL_TREE_PLANKS.get().defaultBlockState(), AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_STAIRS_ITEM = ITEMS.register("aerial_tree_stairs", () -> new BurnableBlockItem(AERIAL_TREE_STAIRS.get(), new Item.Properties(), 300));
	public static final RegistryObject<StairBlock> GOLDEN_BEECH_STAIRS = BLOCKS.register("golden_beech_stairs", () -> new StairBlock(GOLDEN_BEECH_PLANKS.get().defaultBlockState(), AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_STAIRS_ITEM = ITEMS.register("golden_beech_stairs", () -> new BurnableBlockItem(GOLDEN_BEECH_STAIRS.get(), new Item.Properties(), 300));
	public static final RegistryObject<StairBlock> COPPER_PINE_STAIRS = BLOCKS.register("copper_pine_stairs", () -> new StairBlock(COPPER_PINE_PLANKS.get().defaultBlockState(), COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_STAIRS_ITEM = ITEMS.register("copper_pine_stairs", () -> new BurnableBlockItem(COPPER_PINE_STAIRS.get(), new Item.Properties(), 300));
	public static final RegistryObject<StairBlock> LAPIS_ROBINIA_STAIRS = BLOCKS.register("lapis_robinia_stairs", () -> new StairBlock(LAPIS_ROBINIA_PLANKS.get().defaultBlockState(), COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_STAIRS_ITEM = ITEMS.register("lapis_robinia_stairs", () -> new BurnableBlockItem(LAPIS_ROBINIA_STAIRS.get(), new Item.Properties(), 300));
	public static final RegistryObject<StairBlock> SHADOW_PINE_STAIRS = BLOCKS.register("shadow_pine_stairs", () -> new StairBlock(SHADOW_PINE_PLANKS.get().defaultBlockState(), SHADOW_PINE_MATERIAL));
	public static final RegistryObject<Item> SHADOW_PINE_STAIRS_ITEM = ITEMS.register("shadow_pine_stairs", () -> new BurnableBlockItem(SHADOW_PINE_STAIRS.get(), new Item.Properties(), 300));
	public static final RegistryObject<StairBlock> STELLAR_JUNGLE_TREE_STAIRS = BLOCKS.register("stellar_jungle_tree_stairs", () -> new StairBlock(STELLAR_JUNGLE_TREE_PLANKS.get().defaultBlockState(), COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_STAIRS_ITEM = ITEMS.register("stellar_jungle_tree_stairs", () -> new BurnableBlockItem(STELLAR_JUNGLE_TREE_STAIRS.get(), new Item.Properties(), 300));
	public static final RegistryObject<StairBlock> SKY_CACTUS_FIBER_STAIRS = BLOCKS.register("sky_cactus_fiber_stairs", () -> new StairBlock(SKY_CACTUS_FIBER_PLANKS.get().defaultBlockState(), SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_STAIRS_ITEM = ITEMS.register("sky_cactus_fiber_stairs", () -> new BlockItem(SKY_CACTUS_FIBER_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<StairBlock> GRAY_SHROOM_STAIRS = BLOCKS.register("gray_shroom_stairs", () -> new StairBlock(GRAY_SHROOM_PLANKS.get().defaultBlockState(), SHROOM_MATERIAL));
	public static final RegistryObject<Item> GRAY_SHROOM_STAIRS_ITEM = ITEMS.register("gray_shroom_stairs", () -> new BurnableBlockItem(GRAY_SHROOM_STAIRS.get(), new Item.Properties(), 100));
	public static final RegistryObject<StairBlock> STELLAR_STONE_STAIRS = BLOCKS.register("stellar_stone_stairs", () -> new StairBlock(STELLAR_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE.get())));
	public static final RegistryObject<Item> STELLAR_STONE_STAIRS_ITEM = ITEMS.register("stellar_stone_stairs", () -> new BlockItem(STELLAR_STONE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<StairBlock> STELLAR_COBBLESTONE_STAIRS = BLOCKS.register("stellar_cobblestone_stairs", () -> new StairBlock(STELLAR_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE.get())));
	public static final RegistryObject<Item> STELLAR_COBBLESTONE_STAIRS_ITEM = ITEMS.register("stellar_cobblestone_stairs", () -> new BlockItem(STELLAR_COBBLESTONE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<StairBlock> STELLAR_STONE_BRICKS_STAIRS = BLOCKS.register("stellar_stone_bricks_stairs", () -> new StairBlock(STELLAR_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS.get())));
	public static final RegistryObject<Item> STELLAR_STONE_BRICKS_STAIRS_ITEM = ITEMS.register("stellar_stone_bricks_stairs", () -> new BlockItem(STELLAR_STONE_BRICKS_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<StairBlock> MOSSY_STELLAR_STONE_STAIRS = BLOCKS.register("mossy_stellar_stone_stairs", () -> new StairBlock(MOSSY_STELLAR_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_STONE.get())));
	public static final RegistryObject<Item> MOSSY_STELLAR_STONE_STAIRS_ITEM = ITEMS.register("mossy_stellar_stone_stairs", () -> new BlockItem(MOSSY_STELLAR_STONE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<StairBlock> MOSSY_STELLAR_COBBLESTONE_STAIRS = BLOCKS.register("mossy_stellar_cobblestone_stairs", () -> new StairBlock(MOSSY_STELLAR_COBBLESTONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_COBBLESTONE.get())));
	public static final RegistryObject<Item> MOSSY_STELLAR_COBBLESTONE_STAIRS_ITEM = ITEMS.register("mossy_stellar_cobblestone_stairs", () -> new BlockItem(MOSSY_STELLAR_COBBLESTONE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<StairBlock> SLIPPERY_SAND_STONE_STAIRS = BLOCKS.register("slippery_sand_stone_stairs", () -> new StairBlock(SLIPPERY_SAND_STONE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_STAIRS_ITEM = ITEMS.register("slippery_sand_stone_stairs", () -> new BlockItem(SLIPPERY_SAND_STONE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<StairBlock> SLIPPERY_SAND_STONE_BRICKS_STAIRS = BLOCKS.register("slippery_sand_stone_bricks_stairs", () -> new StairBlock(SLIPPERY_SAND_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_BRICKS_STAIRS_ITEM = ITEMS.register("slippery_sand_stone_bricks_stairs", () -> new BlockItem(SLIPPERY_SAND_STONE_BRICKS_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<StairBlock> CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS = BLOCKS.register("cracked_slippery_sand_stone_bricks_stairs", () -> new StairBlock(SLIPPERY_SAND_STONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final RegistryObject<Item> CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS_ITEM = ITEMS.register("cracked_slippery_sand_stone_bricks_stairs", () -> new BlockItem(CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<StairBlock> POLISHED_GLAUCOPHANITE_STAIRS = BLOCKS.register("polished_glaucophanite_stairs", () -> new StairBlock(POLISHED_GLAUCOPHANITE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(POLISHED_GLAUCOPHANITE.get())));
	public static final RegistryObject<Item> POLISHED_GLAUCOPHANITE_STAIRS_ITEM = ITEMS.register("polished_glaucophanite_stairs", () -> new BlockItem(POLISHED_GLAUCOPHANITE_STAIRS.get(), new Item.Properties()));
	public static final RegistryObject<StairBlock> MAGMATIC_GEL_STAIRS = BLOCKS.register("magmatic_gel_stairs", () -> new StairBlock(MAGMATIC_GEL_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MAGMATIC_GEL_BLOCK.get())));
	public static final RegistryObject<Item> MAGMATIC_GEL_STAIRS_ITEM = ITEMS.register("magmatic_gel_stairs", () -> new BlockItem(MAGMATIC_GEL_STAIRS.get(), new Item.Properties()));

	//signs
	public static final RegistryObject<AerialHellStandingSignBlock> AERIAL_TREE_STANDING_SIGN = BLOCKS.register("aerial_tree_sign", () -> new AerialHellStandingSignBlock(AERIAL_TREE_SIGN_MATERIAL, AerialHellWoodTypes.AERIAL_TREE));
	public static final RegistryObject<AerialHellWallSignBlock> AERIAL_TREE_WALL_SIGN = BLOCKS.register("aerial_tree_wall_sign", () -> new AerialHellWallSignBlock(AERIAL_TREE_SIGN_MATERIAL, AerialHellWoodTypes.AERIAL_TREE));
	public static final RegistryObject<Item> AERIAL_TREE_SIGN_ITEM = ITEMS.register("aerial_tree_sign", () -> new SignItem(new Item.Properties(), AERIAL_TREE_STANDING_SIGN.get(), AERIAL_TREE_WALL_SIGN.get()));
	public static final RegistryObject<AerialHellStandingSignBlock> GOLDEN_BEECH_STANDING_SIGN = BLOCKS.register("golden_beech_sign", () -> new AerialHellStandingSignBlock(AERIAL_TREE_SIGN_MATERIAL, AerialHellWoodTypes.GOLDEN_BEECH));
	public static final RegistryObject<AerialHellWallSignBlock> GOLDEN_BEECH_WALL_SIGN = BLOCKS.register("golden_beech_wall_sign", () -> new AerialHellWallSignBlock(AERIAL_TREE_SIGN_MATERIAL, AerialHellWoodTypes.GOLDEN_BEECH));
	public static final RegistryObject<Item> GOLDEN_BEECH_SIGN_ITEM = ITEMS.register("golden_beech_sign", () -> new SignItem(new Item.Properties(), GOLDEN_BEECH_STANDING_SIGN.get(), GOLDEN_BEECH_WALL_SIGN.get()));
	public static final RegistryObject<AerialHellStandingSignBlock> COPPER_PINE_STANDING_SIGN = BLOCKS.register("copper_pine_sign", () -> new AerialHellStandingSignBlock(COPPER_PINE_SIGN_MATERIAL, AerialHellWoodTypes.COPPER_PINE));
	public static final RegistryObject<AerialHellWallSignBlock> COPPER_PINE_WALL_SIGN = BLOCKS.register("copper_pine_wall_sign", () -> new AerialHellWallSignBlock(COPPER_PINE_SIGN_MATERIAL, AerialHellWoodTypes.COPPER_PINE));
	public static final RegistryObject<Item> COPPER_PINE_SIGN_ITEM = ITEMS.register("copper_pine_sign", () -> new SignItem(new Item.Properties(), COPPER_PINE_STANDING_SIGN.get(), COPPER_PINE_WALL_SIGN.get()));
	public static final RegistryObject<AerialHellStandingSignBlock> LAPIS_ROBINIA_STANDING_SIGN = BLOCKS.register("lapis_robinia_sign", () -> new AerialHellStandingSignBlock(COPPER_PINE_SIGN_MATERIAL, AerialHellWoodTypes.LAPIS_ROBINIA));
	public static final RegistryObject<AerialHellWallSignBlock> LAPIS_ROBINIA_WALL_SIGN = BLOCKS.register("lapis_robinia_wall_sign", () -> new AerialHellWallSignBlock(COPPER_PINE_SIGN_MATERIAL, AerialHellWoodTypes.LAPIS_ROBINIA));
	public static final RegistryObject<Item> LAPIS_ROBINIA_SIGN_ITEM = ITEMS.register("lapis_robinia_sign", () -> new SignItem(new Item.Properties(), LAPIS_ROBINIA_STANDING_SIGN.get(), LAPIS_ROBINIA_WALL_SIGN.get()));
	public static final RegistryObject<AerialHellStandingSignBlock> SHADOW_PINE_STANDING_SIGN = BLOCKS.register("shadow_pine_sign", () -> new AerialHellStandingSignBlock(SHADOW_PINE_SIGN_MATERIAL, AerialHellWoodTypes.SHADOW_PINE));
	public static final RegistryObject<AerialHellWallSignBlock> SHADOW_PINE_WALL_SIGN = BLOCKS.register("shadow_pine_wall_sign", () -> new AerialHellWallSignBlock(SHADOW_PINE_SIGN_MATERIAL, AerialHellWoodTypes.SHADOW_PINE));
	public static final RegistryObject<Item> SHADOW_PINE_SIGN_ITEM = ITEMS.register("shadow_pine_sign", () -> new SignItem(new Item.Properties(), SHADOW_PINE_STANDING_SIGN.get(), SHADOW_PINE_WALL_SIGN.get()));
	public static final RegistryObject<AerialHellStandingSignBlock> STELLAR_JUNGLE_TREE_STANDING_SIGN = BLOCKS.register("stellar_jungle_tree_sign", () -> new AerialHellStandingSignBlock(COPPER_PINE_SIGN_MATERIAL, AerialHellWoodTypes.STELLAR_JUNGLE_TREE));
	public static final RegistryObject<AerialHellWallSignBlock> STELLAR_JUNGLE_TREE_WALL_SIGN = BLOCKS.register("stellar_jungle_tree_wall_sign", () -> new AerialHellWallSignBlock(COPPER_PINE_SIGN_MATERIAL, AerialHellWoodTypes.STELLAR_JUNGLE_TREE));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_SIGN_ITEM = ITEMS.register("stellar_jungle_tree_sign", () -> new SignItem(new Item.Properties(), STELLAR_JUNGLE_TREE_STANDING_SIGN.get(), STELLAR_JUNGLE_TREE_WALL_SIGN.get()));
	public static final RegistryObject<AerialHellStandingSignBlock> SKY_CACTUS_FIBER_STANDING_SIGN = BLOCKS.register("sky_cactus_fiber_sign", () -> new AerialHellStandingSignBlock(SKY_CACTUS_FIBER_SIGN_MATERIAL, AerialHellWoodTypes.SKY_CACTUS_FIBER));
	public static final RegistryObject<AerialHellWallSignBlock> SKY_CACTUS_FIBER_WALL_SIGN = BLOCKS.register("sky_cactus_fiber_wall_sign", () -> new AerialHellWallSignBlock(SKY_CACTUS_FIBER_SIGN_MATERIAL, AerialHellWoodTypes.SKY_CACTUS_FIBER));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_SIGN_ITEM = ITEMS.register("sky_cactus_fiber_sign", () -> new SignItem(new Item.Properties(), SKY_CACTUS_FIBER_STANDING_SIGN.get(), SKY_CACTUS_FIBER_WALL_SIGN.get()));
	public static final RegistryObject<AerialHellStandingSignBlock> GRAY_SHROOM_STANDING_SIGN = BLOCKS.register("gray_shroom_sign", () -> new AerialHellStandingSignBlock(SHROOM_SIGN_MATERIAL, AerialHellWoodTypes.GRAY_SHROOM));
	public static final RegistryObject<AerialHellWallSignBlock> GRAY_SHROOM_WALL_SIGN = BLOCKS.register("gray_shroom_wall_sign", () -> new AerialHellWallSignBlock(SHROOM_SIGN_MATERIAL, AerialHellWoodTypes.GRAY_SHROOM));
	public static final RegistryObject<Item> GRAY_SHROOM_SIGN_ITEM = ITEMS.register("gray_shroom_sign", () -> new SignItem(new Item.Properties(), GRAY_SHROOM_STANDING_SIGN.get(), GRAY_SHROOM_WALL_SIGN.get()));

	//hanging signs
	public static final RegistryObject<CeilingHangingSignBlock> AERIAL_TREE_HANGING_SIGN = BLOCKS.register("aerial_tree_hanging_sign", () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.AERIAL_TREE, AERIAL_TREE_SIGN_MATERIAL));
	public static final RegistryObject<WallHangingSignBlock> AERIAL_TREE_WALL_HANGING_SIGN = BLOCKS.register("aerial_tree_wall_hanging_sign", () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.AERIAL_TREE, BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_HANGING_SIGN.get()).dropsLike(AERIAL_TREE_HANGING_SIGN.get())));
	public static final RegistryObject<Item> AERIAL_TREE_HANGING_SIGN_ITEM = ITEMS.register("aerial_tree_hanging_sign", () -> new HangingSignItem(AERIAL_TREE_HANGING_SIGN.get(), AERIAL_TREE_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
	public static final RegistryObject<CeilingHangingSignBlock> GOLDEN_BEECH_HANGING_SIGN = BLOCKS.register("golden_beech_hanging_sign", () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.GOLDEN_BEECH, AERIAL_TREE_SIGN_MATERIAL));
	public static final RegistryObject<WallHangingSignBlock> GOLDEN_BEECH_WALL_HANGING_SIGN = BLOCKS.register("golden_beech_wall_hanging_sign", () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.GOLDEN_BEECH, BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_HANGING_SIGN.get()).dropsLike(GOLDEN_BEECH_HANGING_SIGN.get())));
	public static final RegistryObject<Item> GOLDEN_BEECH_HANGING_SIGN_ITEM = ITEMS.register("golden_beech_hanging_sign", () -> new HangingSignItem(GOLDEN_BEECH_HANGING_SIGN.get(), GOLDEN_BEECH_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
	public static final RegistryObject<CeilingHangingSignBlock> COPPER_PINE_HANGING_SIGN = BLOCKS.register("copper_pine_hanging_sign", () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.COPPER_PINE, COPPER_PINE_SIGN_MATERIAL));
	public static final RegistryObject<WallHangingSignBlock> COPPER_PINE_WALL_HANGING_SIGN = BLOCKS.register("copper_pine_wall_hanging_sign", () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.COPPER_PINE, BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_HANGING_SIGN.get()).dropsLike(COPPER_PINE_HANGING_SIGN.get())));
	public static final RegistryObject<Item> COPPER_PINE_HANGING_SIGN_ITEM = ITEMS.register("copper_pine_hanging_sign", () -> new HangingSignItem(COPPER_PINE_HANGING_SIGN.get(), COPPER_PINE_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
	public static final RegistryObject<CeilingHangingSignBlock> LAPIS_ROBINIA_HANGING_SIGN = BLOCKS.register("lapis_robinia_hanging_sign", () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.LAPIS_ROBINIA, COPPER_PINE_SIGN_MATERIAL));
	public static final RegistryObject<WallHangingSignBlock> LAPIS_ROBINIA_WALL_HANGING_SIGN = BLOCKS.register("lapis_robinia_wall_hanging_sign", () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.LAPIS_ROBINIA, BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_HANGING_SIGN.get()).dropsLike(LAPIS_ROBINIA_HANGING_SIGN.get())));
	public static final RegistryObject<Item> LAPIS_ROBINIA_HANGING_SIGN_ITEM = ITEMS.register("lapis_robinia_hanging_sign", () -> new HangingSignItem(LAPIS_ROBINIA_HANGING_SIGN.get(), LAPIS_ROBINIA_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
	public static final RegistryObject<CeilingHangingSignBlock> SHADOW_PINE_HANGING_SIGN = BLOCKS.register("shadow_pine_hanging_sign", () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.SHADOW_PINE, SHADOW_PINE_SIGN_MATERIAL));
	public static final RegistryObject<WallHangingSignBlock> SHADOW_PINE_WALL_HANGING_SIGN = BLOCKS.register("shadow_pine_wall_hanging_sign", () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.SHADOW_PINE, BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_HANGING_SIGN.get()).dropsLike(SHADOW_PINE_HANGING_SIGN.get())));
	public static final RegistryObject<Item> SHADOW_PINE_HANGING_SIGN_ITEM = ITEMS.register("shadow_pine_hanging_sign", () -> new HangingSignItem(SHADOW_PINE_HANGING_SIGN.get(), SHADOW_PINE_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
	public static final RegistryObject<CeilingHangingSignBlock> STELLAR_JUNGLE_TREE_HANGING_SIGN = BLOCKS.register("stellar_jungle_tree_hanging_sign", () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, SHADOW_PINE_SIGN_MATERIAL));
	public static final RegistryObject<WallHangingSignBlock> STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN = BLOCKS.register("stellar_jungle_tree_wall_hanging_sign", () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_HANGING_SIGN.get()).dropsLike(STELLAR_JUNGLE_TREE_HANGING_SIGN.get())));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_HANGING_SIGN_ITEM = ITEMS.register("stellar_jungle_tree_hanging_sign", () -> new HangingSignItem(STELLAR_JUNGLE_TREE_HANGING_SIGN.get(), STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
	public static final RegistryObject<CeilingHangingSignBlock> SKY_CACTUS_FIBER_HANGING_SIGN = BLOCKS.register("sky_cactus_fiber_hanging_sign", () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, SKY_CACTUS_FIBER_SIGN_MATERIAL));
	public static final RegistryObject<WallHangingSignBlock> SKY_CACTUS_FIBER_WALL_HANGING_SIGN = BLOCKS.register("sky_cactus_fiber_wall_hanging_sign", () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_HANGING_SIGN.get()).dropsLike(SKY_CACTUS_FIBER_HANGING_SIGN.get())));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_HANGING_SIGN_ITEM = ITEMS.register("sky_cactus_fiber_hanging_sign", () -> new HangingSignItem(SKY_CACTUS_FIBER_HANGING_SIGN.get(), SKY_CACTUS_FIBER_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));
	public static final RegistryObject<CeilingHangingSignBlock> GRAY_SHROOM_HANGING_SIGN = BLOCKS.register("gray_shroom_hanging_sign", () -> new AerialHellHangingSignBlock(AerialHellWoodTypes.GRAY_SHROOM, SKY_CACTUS_FIBER_SIGN_MATERIAL));
	public static final RegistryObject<WallHangingSignBlock> GRAY_SHROOM_WALL_HANGING_SIGN = BLOCKS.register("gray_shroom_wall_hanging_sign", () -> new AerialHellWallHangingSignBlock(AerialHellWoodTypes.GRAY_SHROOM, BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_HANGING_SIGN.get()).dropsLike(GRAY_SHROOM_HANGING_SIGN.get())));
	public static final RegistryObject<Item> GRAY_SHROOM_HANGING_SIGN_ITEM = ITEMS.register("gray_shroom_hanging_sign", () -> new HangingSignItem(GRAY_SHROOM_HANGING_SIGN.get(), GRAY_SHROOM_WALL_HANGING_SIGN.get(), (new Item.Properties()).stacksTo(16)));

	//crafting tables
	public static final RegistryObject<CraftingTableBlock> AERIAL_TREE_CRAFTING_TABLE = BLOCKS.register("aerial_tree_crafting_table", () -> new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_CRAFTING_TABLE_ITEM = ITEMS.register("aerial_tree_crafting_table", () -> new BurnableBlockItem(AERIAL_TREE_CRAFTING_TABLE.get(), new Item.Properties(), 300));
	public static final RegistryObject<CraftingTableBlock> GOLDEN_BEECH_CRAFTING_TABLE = BLOCKS.register("golden_beech_crafting_table", () -> new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_CRAFTING_TABLE_ITEM = ITEMS.register("golden_beech_crafting_table", () -> new BurnableBlockItem(GOLDEN_BEECH_CRAFTING_TABLE.get(), new Item.Properties(), 300));
	public static final RegistryObject<CraftingTableBlock> COPPER_PINE_CRAFTING_TABLE = BLOCKS.register("copper_pine_crafting_table", () -> new AerialHellCraftingTableBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_CRAFTING_TABLE_ITEM = ITEMS.register("copper_pine_crafting_table", () -> new BurnableBlockItem(COPPER_PINE_CRAFTING_TABLE.get(), new Item.Properties(), 300));
	public static final RegistryObject<CraftingTableBlock> LAPIS_ROBINIA_CRAFTING_TABLE = BLOCKS.register("lapis_robinia_crafting_table", () -> new AerialHellCraftingTableBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_CRAFTING_TABLE_ITEM = ITEMS.register("lapis_robinia_crafting_table", () -> new BurnableBlockItem(LAPIS_ROBINIA_CRAFTING_TABLE.get(), new Item.Properties(), 300));
	public static final RegistryObject<CraftingTableBlock> SHADOW_PINE_CRAFTING_TABLE = BLOCKS.register("shadow_pine_crafting_table", () -> new AerialHellCraftingTableBlock(SHADOW_PINE_MATERIAL));
	public static final RegistryObject<Item> SHADOW_PINE_CRAFTING_TABLE_ITEM = ITEMS.register("shadow_pine_crafting_table", () -> new BurnableBlockItem(SHADOW_PINE_CRAFTING_TABLE.get(), new Item.Properties(), 300));
	public static final RegistryObject<CraftingTableBlock> STELLAR_JUNGLE_TREE_CRAFTING_TABLE = BLOCKS.register("stellar_jungle_tree_crafting_table", () -> new AerialHellCraftingTableBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_CRAFTING_TABLE_ITEM = ITEMS.register("stellar_jungle_tree_crafting_table", () -> new BurnableBlockItem(STELLAR_JUNGLE_TREE_CRAFTING_TABLE.get(), new Item.Properties(), 300));
	public static final RegistryObject<CraftingTableBlock> SKY_CACTUS_FIBER_CRAFTING_TABLE = BLOCKS.register("sky_cactus_fiber_crafting_table", () -> new AerialHellCraftingTableBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_CRAFTING_TABLE_ITEM = ITEMS.register("sky_cactus_fiber_crafting_table", () -> new BlockItem(SKY_CACTUS_FIBER_CRAFTING_TABLE.get(), new Item.Properties()));
	public static final RegistryObject<CraftingTableBlock> GRAY_SHROOM_CRAFTING_TABLE = BLOCKS.register("gray_shroom_crafting_table", () -> new AerialHellCraftingTableBlock(SHROOM_MATERIAL));
	public static final RegistryObject<Item> GRAY_SHROOM_CRAFTING_TABLE_ITEM = ITEMS.register("gray_shroom_crafting_table", () -> new BurnableBlockItem(GRAY_SHROOM_CRAFTING_TABLE.get(), new Item.Properties(), 100));

	//barrels
	public static final RegistryObject<AerialHellBarrelBlock> AERIAL_TREE_BARREL = BLOCKS.register("aerial_tree_barrel", () -> new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_BARREL_ITEM = ITEMS.register("aerial_tree_barrel", () -> new BurnableBlockItem(AERIAL_TREE_BARREL.get(), new Item.Properties(), 300));
	public static final RegistryObject<AerialHellBarrelBlock> GOLDEN_BEECH_BARREL = BLOCKS.register("golden_beech_barrel", () -> new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_BARREL_ITEM = ITEMS.register("golden_beech_barrel", () -> new BurnableBlockItem(GOLDEN_BEECH_BARREL.get(), new Item.Properties(), 300));
	public static final RegistryObject<AerialHellBarrelBlock> COPPER_PINE_BARREL = BLOCKS.register("copper_pine_barrel", () -> new AerialHellBarrelBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_BARREL_ITEM = ITEMS.register("copper_pine_barrel", () -> new BurnableBlockItem(COPPER_PINE_BARREL.get(), new Item.Properties(), 300));
	public static final RegistryObject<AerialHellBarrelBlock> LAPIS_ROBINIA_BARREL = BLOCKS.register("lapis_robinia_barrel", () -> new AerialHellBarrelBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_BARREL_ITEM = ITEMS.register("lapis_robinia_barrel", () -> new BurnableBlockItem(LAPIS_ROBINIA_BARREL.get(), new Item.Properties(), 300));
	public static final RegistryObject<AerialHellBarrelBlock> SHADOW_PINE_BARREL = BLOCKS.register("shadow_pine_barrel", () -> new AerialHellBarrelBlock(SHADOW_PINE_MATERIAL));
	public static final RegistryObject<Item> SHADOW_PINE_BARREL_ITEM = ITEMS.register("shadow_pine_barrel", () -> new BurnableBlockItem(SHADOW_PINE_BARREL.get(), new Item.Properties(), 300));
	public static final RegistryObject<AerialHellBarrelBlock> STELLAR_JUNGLE_TREE_BARREL = BLOCKS.register("stellar_jungle_tree_barrel", () -> new AerialHellBarrelBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_BARREL_ITEM = ITEMS.register("stellar_jungle_tree_barrel", () -> new BurnableBlockItem(STELLAR_JUNGLE_TREE_BARREL.get(), new Item.Properties(), 300));
	public static final RegistryObject<AerialHellBarrelBlock> SKY_CACTUS_FIBER_BARREL = BLOCKS.register("sky_cactus_fiber_barrel", () -> new AerialHellBarrelBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_BARREL_ITEM = ITEMS.register("sky_cactus_fiber_barrel", () -> new BlockItem(SKY_CACTUS_FIBER_BARREL.get(), new Item.Properties()));
	public static final RegistryObject<AerialHellBarrelBlock> GRAY_SHROOM_BARREL = BLOCKS.register("gray_shroom_barrel", () -> new AerialHellBarrelBlock(SHROOM_MATERIAL));
	public static final RegistryObject<Item> GRAY_SHROOM_BARREL_ITEM = ITEMS.register("gray_shroom_barrel", () -> new BurnableBlockItem(GRAY_SHROOM_BARREL.get(), new Item.Properties(), 100));

	//composters
	public static final RegistryObject<ComposterBlock> AERIAL_TREE_COMPOSTER = BLOCKS.register("aerial_tree_composter", () -> new ComposterBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_COMPOSTER_ITEM = ITEMS.register("aerial_tree_composter", () -> new BurnableBlockItem(AERIAL_TREE_COMPOSTER.get(), new Item.Properties(), 300));
	public static final RegistryObject<ComposterBlock> GOLDEN_BEECH_COMPOSTER = BLOCKS.register("golden_beech_composter", () -> new ComposterBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_COMPOSTER_ITEM = ITEMS.register("golden_beech_composter", () -> new BurnableBlockItem(GOLDEN_BEECH_COMPOSTER.get(), new Item.Properties(), 300));
	public static final RegistryObject<ComposterBlock> COPPER_PINE_COMPOSTER = BLOCKS.register("copper_pine_composter", () -> new ComposterBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_COMPOSTER_ITEM = ITEMS.register("copper_pine_composter", () -> new BurnableBlockItem(COPPER_PINE_COMPOSTER.get(), new Item.Properties(), 300));
	public static final RegistryObject<ComposterBlock> LAPIS_ROBINIA_COMPOSTER = BLOCKS.register("lapis_robinia_composter", () -> new ComposterBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_COMPOSTER_ITEM = ITEMS.register("lapis_robinia_composter", () -> new BurnableBlockItem(LAPIS_ROBINIA_COMPOSTER.get(), new Item.Properties(), 300));
	public static final RegistryObject<ComposterBlock> SHADOW_PINE_COMPOSTER = BLOCKS.register("shadow_pine_composter", () -> new ComposterBlock(SHADOW_PINE_MATERIAL));
	public static final RegistryObject<Item> SHADOW_PINE_COMPOSTER_ITEM = ITEMS.register("shadow_pine_composter", () -> new BurnableBlockItem(SHADOW_PINE_COMPOSTER.get(), new Item.Properties(), 300));
	public static final RegistryObject<ComposterBlock> STELLAR_JUNGLE_TREE_COMPOSTER = BLOCKS.register("stellar_jungle_tree_composter", () -> new ComposterBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_COMPOSTER_ITEM = ITEMS.register("stellar_jungle_tree_composter", () -> new BurnableBlockItem(STELLAR_JUNGLE_TREE_COMPOSTER.get(), new Item.Properties(), 300));
	public static final RegistryObject<ComposterBlock> SKY_CACTUS_FIBER_COMPOSTER = BLOCKS.register("sky_cactus_fiber_composter", () -> new ComposterBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_COMPOSTER_ITEM = ITEMS.register("sky_cactus_fiber_composter", () -> new BlockItem(SKY_CACTUS_FIBER_COMPOSTER.get(), new Item.Properties()));
	public static final RegistryObject<ComposterBlock> GRAY_SHROOM_COMPOSTER = BLOCKS.register("gray_shroom_composter", () -> new ComposterBlock(SHROOM_MATERIAL));
	public static final RegistryObject<Item> GRAY_SHROOM_COMPOSTER_ITEM = ITEMS.register("gray_shroom_composter", () -> new BurnableBlockItem(GRAY_SHROOM_COMPOSTER.get(), new Item.Properties(), 100));

	//decorative
	public static final RegistryObject<RotatedPillarBlock> AERIAL_TREE_VINE_ROPE_SPOOL = BLOCKS.register("aerial_tree_vine_rope_spool", () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final RegistryObject<Item> AERIAL_TREE_VINE_ROPE_SPOOL_ITEM = ITEMS.register("aerial_tree_vine_rope_spool", () -> new BlockItem(AERIAL_TREE_VINE_ROPE_SPOOL.get(), new Item.Properties()));
	public static final RegistryObject<RotatedPillarBlock> GOLDEN_BEECH_VINE_ROPE_SPOOL = BLOCKS.register("golden_beech_vine_rope_spool", () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final RegistryObject<Item> GOLDEN_BEECH_VINE_ROPE_SPOOL_ITEM = ITEMS.register("golden_beech_vine_rope_spool", () -> new BlockItem(GOLDEN_BEECH_VINE_ROPE_SPOOL.get(), new Item.Properties()));
	public static final RegistryObject<RotatedPillarBlock> COPPER_PINE_VINE_ROPE_SPOOL = BLOCKS.register("copper_pine_vine_rope_spool", () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final RegistryObject<Item> COPPER_PINE_VINE_ROPE_SPOOL_ITEM = ITEMS.register("copper_pine_vine_rope_spool", () -> new BlockItem(COPPER_PINE_VINE_ROPE_SPOOL.get(), new Item.Properties()));
	public static final RegistryObject<RotatedPillarBlock> LAPIS_ROBINIA_VINE_ROPE_SPOOL = BLOCKS.register("lapis_robinia_vine_rope_spool", () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final RegistryObject<Item> LAPIS_ROBINIA_VINE_ROPE_SPOOL_ITEM = ITEMS.register("lapis_robinia_vine_rope_spool", () -> new BlockItem(LAPIS_ROBINIA_VINE_ROPE_SPOOL.get(), new Item.Properties()));
	public static final RegistryObject<RotatedPillarBlock> SHADOW_PINE_VINE_ROPE_SPOOL = BLOCKS.register("shadow_pine_vine_rope_spool", () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final RegistryObject<Item> SHADOW_PINE_VINE_ROPE_SPOOL_ITEM = ITEMS.register("shadow_pine_vine_rope_spool", () -> new BlockItem(SHADOW_PINE_VINE_ROPE_SPOOL.get(), new Item.Properties()));
	public static final RegistryObject<RotatedPillarBlock> STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL = BLOCKS.register("stellar_jungle_tree_vine_rope_spool", () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final RegistryObject<Item> STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL_ITEM = ITEMS.register("stellar_jungle_tree_vine_rope_spool", () -> new BlockItem(STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL.get(), new Item.Properties()));
	public static final RegistryObject<RotatedPillarBlock> SKY_CACTUS_FIBER_VINE_ROPE_SPOOL = BLOCKS.register("sky_cactus_fiber_vine_rope_spool", () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_VINE_ROPE_SPOOL_ITEM = ITEMS.register("sky_cactus_fiber_vine_rope_spool", () -> new BlockItem(SKY_CACTUS_FIBER_VINE_ROPE_SPOOL.get(), new Item.Properties()));
	public static final RegistryObject<RotatedPillarBlock> GRAY_SHROOM_VINE_ROPE_SPOOL = BLOCKS.register("gray_shroom_vine_rope_spool", () -> new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
	public static final RegistryObject<Item> GRAY_SHROOM_VINE_ROPE_SPOOL_ITEM = ITEMS.register("gray_shroom_vine_rope_spool", () -> new BlockItem(GRAY_SHROOM_VINE_ROPE_SPOOL.get(), new Item.Properties()));

	//item for crafts
	public static final RegistryObject<Item> SKY_STICK = ITEMS.register("sky_stick",() -> new BurnableItem(new Item.Properties(), 100));
	public static final RegistryObject<Item> SKY_BOWL = ITEMS.register("sky_bowl",() -> new BurnableItem(new Item.Properties(), 200));
	public static final RegistryObject<Item> SHADOW_SHARD = ITEMS.register("shadow_shard",() -> new Item(new Item.Properties().rarity(AerialHellRarities.CORRUPTED)));
	public static final RegistryObject<Item> ROTTEN_LEATHER = ITEMS.register("rotten_leather",() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> VENOMOUS_SNAKE_SKIN = ITEMS.register("venomous_snake_skin",() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> ARSONIST_UPGRADE_SMITHING_TEMPLATE = ITEMS.register("arsonist_upgrade_smithing_template", () -> ItemHelper.SmithingTemplate.createUpgradeTemplate("arsonist"));

	//projectile item
	public static final RegistryObject<Item> STELLAR_EGG = ITEMS.register("stellar_egg",() -> new StellarEggItem(new Item.Properties().stacksTo(16)));

	//shurikens
	public static final RegistryObject<Item> IRON_SHURIKEN = ITEMS.register("iron_shuriken", () -> new IronShurikenItem());
	public static final RegistryObject<Item> GOLD_SHURIKEN = ITEMS.register("gold_shuriken", () -> new GoldShurikenItem());
	public static final RegistryObject<Item> DIAMOND_SHURIKEN = ITEMS.register("diamond_shuriken", () -> new DiamondShurikenItem());
	public static final RegistryObject<Item> NETHERITE_SHURIKEN = ITEMS.register("netherite_shuriken", () -> new NetheriteShurikenItem());
	public static final RegistryObject<Item> RUBY_SHURIKEN = ITEMS.register("ruby_shuriken", () -> new RubyShurikenItem());
	public static final RegistryObject<Item> AZURITE_SHURIKEN = ITEMS.register("azurite_shuriken", () -> new AzuriteShurikenItem());
	public static final RegistryObject<Item> MAGMATIC_GEL_SHURIKEN = ITEMS.register("magmatic_gel_shuriken", () -> new MagmaticGelShurikenItem());
	public static final RegistryObject<Item> VOLUCITE_SHURIKEN = ITEMS.register("volucite_shuriken", () -> new VoluciteShurikenItem());
	public static final RegistryObject<Item> OBSIDIAN_SHURIKEN = ITEMS.register("obsidian_shuriken", () -> new ObsidianShurikenItem());
	public static final RegistryObject<Item> LUNATIC_CRYSTAL_SHURIKEN = ITEMS.register("lunatic_crystal_shuriken", () -> new LunaticCrystalShurikenItem());
	public static final RegistryObject<Item> ARSONIST_SHURIKEN = ITEMS.register("arsonist_shuriken", () -> new ArsonistShurikenItem());
	public static final RegistryObject<Item> LIGHTNING_SHURIKEN = ITEMS.register("lightning_shuriken", () -> new LightningShurikenItem());

	//food
	public static final RegistryObject<Item> AERIAL_BERRY = ITEMS.register("aerial_berry",() -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build())));
	public static final RegistryObject<Item> ROASTED_AERIAL_BERRY = ITEMS.register("roasted_aerial_berry",() -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.4F).build())));
	public static final RegistryObject<Item> VIBRANT_AERIAL_BERRY = ITEMS.register("vibrant_aerial_berry",() -> new Item(new Item.Properties().rarity(AerialHellRarities.VIBRANT).food(new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).build())));
	public static final RegistryObject<Item> FROZEN_AERIAL_BERRY = ITEMS.register("frozen_aerial_berry", () -> new FoodWithEffectItem(6, 0.8F, AerialHellRarities.FROZEN, () -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 310, 0), () -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 210, 0)));
	public static final RegistryObject<Item> STELLAR_BREAD = ITEMS.register("stellar_bread",() -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(0.6F).build())));
	public static final RegistryObject<Item> FROZEN_MUTTON = ITEMS.register("frozen_mutton", () -> new FoodWithEffectItem(6, 0.8F, AerialHellRarities.FROZEN, () -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 310, 0), () -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 210, 0)));
	public static final RegistryObject<Item> VIBRANT_CHICKEN = ITEMS.register("vibrant_chicken", () -> new FoodWithEffectItem(6, 0.8F, AerialHellRarities.VIBRANT, () -> new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 0)));
	public static final RegistryObject<Item> FROZEN_CHICKEN = ITEMS.register("frozen_chicken", () -> new FoodWithEffectItem(6, 0.8F, AerialHellRarities.FROZEN, () -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 310, 0), () -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 210, 1), () -> new MobEffectInstance(MobEffects.HUNGER, 80, 0)));
	public static final RegistryObject<Item> RUBY_AERIAL_BERRY = ITEMS.register("ruby_aerial_berry", () -> new FoodWithEffectItem(6, 0.8F, Rarity.RARE, () -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 2400, 0)));
	public static final RegistryObject<Item> VOLUCITE_AERIAL_BERRY = ITEMS.register("volucite_aerial_berry", () -> new FoodWithEffectItem(6, 0.8F, AerialHellRarities.VIBRANT, () -> new MobEffectInstance(MobEffects.SLOW_FALLING, 2400, 2)));
	public static final RegistryObject<Item> GLOWING_STICK_FRUIT = ITEMS.register("glowing_stick_fruit",() -> new ItemNameBlockItem(GLOWING_STICK_FRUIT_VINES.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.2F).build())));
	public static final RegistryObject<Item> VIBRANT_GLOWING_STICK_FRUIT = ITEMS.register("vibrant_glowing_stick_fruit", () -> new Item(new Item.Properties().rarity(AerialHellRarities.VIBRANT).food(new FoodProperties.Builder().nutrition(4).saturationMod(0.4F).build())));
	public static final RegistryObject<Item> FROZEN_GLOWING_STICK_FRUIT = ITEMS.register("frozen_glowing_stick_fruit", () -> new FoodWithEffectItem(4, 0.4F, AerialHellRarities.FROZEN, () -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 0), () -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 180, 0)));
	public static final RegistryObject<Item> CORTINARIUS_VIOLACEUS_PIECE = ITEMS.register("cortinarius_violaceus_piece", () -> new FoodWithEffectItem(1, 0.1F, Rarity.COMMON, () -> new MobEffectInstance(MobEffects.CONFUSION, 100, 0)));
	public static final RegistryObject<Item> GANODERMA_APPLANATUM_PIECE = ITEMS.register("ganoderma_applanatum_piece", () -> new FoodWithEffectItem(1, 0.1F, Rarity.COMMON, () -> new MobEffectInstance(MobEffects.HUNGER, 100, 0)));
	public static final RegistryObject<Item> DARK_SHADOW_FRUIT = ITEMS.register("dark_shadow_fruit", () -> new FoodWithEffectItem(2, 0.2F, Rarity.COMMON, () -> new MobEffectInstance(MobEffects.BLINDNESS, 20, 0), () -> new MobEffectInstance(MobEffects.NIGHT_VISION, 120, 0)));
	public static final RegistryObject<Item> PURPLE_SHADOW_FRUIT = ITEMS.register("purple_shadow_fruit", () -> new FoodWithEffectItem(2, 0.2F, Rarity.COMMON, () -> new MobEffectInstance(MobEffects.BLINDNESS, 20, 0), () -> new MobEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY.get(), 80, 0)));
	public static final RegistryObject<Item> SHADOW_FRUIT_STEW = ITEMS.register("shadow_fruit_stew", () -> new SkySoupItem(2, 0.2F, AerialHellRarities.CORRUPTED, () -> new MobEffectInstance(MobEffects.BLINDNESS, 20, 0), () -> new MobEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY.get(), 1200, 0)));
	public static final RegistryObject<Item> SOLID_ETHER_SOUP = ITEMS.register("solid_ether_soup", () -> new SkySoupItem(2, 0.2F, Rarity.COMMON, () -> new MobEffectInstance(MobEffects.SLOW_FALLING, 160, 0), () -> new MobEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.get(), 160, 0)));
	public static final RegistryObject<Item> VIBRANT_SOLID_ETHER_SOUP = ITEMS.register("vibrant_solid_ether_soup", () -> new SkySoupItem(4, 0.2F, AerialHellRarities.VIBRANT, () -> new MobEffectInstance(MobEffects.SLOW_FALLING, 160, 0), () -> new MobEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.get(), 160, 1)));
	public static final RegistryObject<Item> FROZEN_SOLID_ETHER_SOUP = ITEMS.register("frozen_solid_ether_soup", () -> new SkySoupItem(4, 0.2F, AerialHellRarities.FROZEN, () -> new MobEffectInstance(MobEffects.SLOW_FALLING, 180, 0), () -> new MobEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.get(), 160, 0), () -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 210, 0)));
	public static final RegistryObject<Item> SHADOW_SPIDER_EYE = ITEMS.register("shadow_spider_eye", () -> new FoodWithEffectItem(2, 0.2F, AerialHellRarities.CORRUPTED, () -> new MobEffectInstance(MobEffects.BLINDNESS, 20, 0), () -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 0)));
	public static final RegistryObject<Item> PHANTOM_MEAT = ITEMS.register("phantom_meat", () -> new FoodWithEffectItem(5, 0.8F, Rarity.UNCOMMON, () -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 120, 0)));
	public static final RegistryObject<Item> VIBRANT_PHANTOM_MEAT = ITEMS.register("vibrant_phantom_meat", () -> new FoodWithEffectItem(8, 0.8F, AerialHellRarities.VIBRANT, () -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 1)));
	public static final RegistryObject<Item> FROZEN_PHANTOM_MEAT = ITEMS.register("frozen_phantom_meat", () -> new FoodWithEffectItem(6, 0.6F, AerialHellRarities.FROZEN, () -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 800, 1)));
    public static final RegistryObject<Item> COOKED_PHANTOM_MEAT = ITEMS.register("cooked_phantom_meat",() -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(10).saturationMod(0.9F).meat().build())));
	public static final RegistryObject<Item> TURTLE_MEAT = ITEMS.register("turtle_meat", () -> new Item(new Item.Properties().food(Foods.BEEF)));
	public static final RegistryObject<Item> VIBRANT_TURTLE_MEAT = ITEMS.register("vibrant_turtle_meat", () -> new FoodWithEffectItem(7, 0.7F, AerialHellRarities.VIBRANT, () -> new MobEffectInstance(MobEffects.SLOW_FALLING, 400, 0)));
	public static final RegistryObject<Item> FROZEN_TURTLE_MEAT = ITEMS.register("frozen_turtle_meat", () -> new FoodWithEffectItem(6, 0.6F, AerialHellRarities.FROZEN, () -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400, 0)));
	public static final RegistryObject<Item> COOKED_TURTLE_MEAT = ITEMS.register("cooked_turtle_meat",() -> new Item(new Item.Properties().food(Foods.COOKED_BEEF)));
	public static final RegistryObject<Item> GODS_VOLUCITE_AERIAL_BERRY = ITEMS.register("gods_volucite_aerial_berry", () -> new GodsVoluciteBerryItem());
	public static final RegistryObject<Item> COPPER_PINE_CONE = ITEMS.register("copper_pine_cone",() -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.4F).build())));
	public static final RegistryObject<Item> AZURITE_COPPER_PINE_CONE = ITEMS.register("azurite_copper_pine_cone", () -> new FoodWithEffectItem(4, 0.4F, Rarity.COMMON, () -> new MobEffectInstance(MobEffects.DIG_SPEED, 400, 0)));
	public static final RegistryObject<Item> PHOENIX_FEATHER = ITEMS.register("phoenix_feather", () -> new FoodWithEffectItem(6, 0.8F, AerialHellRarities.LEGENDARY, () -> new MobEffectInstance(MobEffects.SLOW_FALLING, 1200, 0), () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0)));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER = ITEMS.register("sky_cactus_fiber",() -> new Item(new Item.Properties().food(new FoodProperties.Builder().fast().nutrition(1).saturationMod(0.1F).build())));
	public static final RegistryObject<Item> VIBRANT_SKY_CACTUS_FIBER = ITEMS.register("vibrant_sky_cactus_fiber",() -> new Item(new Item.Properties().rarity(AerialHellRarities.VIBRANT).food(new FoodProperties.Builder().fast().nutrition(4).saturationMod(0.3F).build())));
	public static final RegistryObject<Item> WHITE_SOLID_ETHER_FRAGMENT = ITEMS.register("white_solid_ether_fragment",() -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).build())));
	public static final RegistryObject<Item> BLUE_SOLID_ETHER_FRAGMENT = ITEMS.register("blue_solid_ether_fragment", () -> new FoodWithEffectItem(1, 0.1F, Rarity.UNCOMMON, () -> new MobEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.get(), 90, 0)));
	public static final RegistryObject<Item> GOLDEN_SOLID_ETHER_FRAGMENT = ITEMS.register("golden_solid_ether_fragment", () -> new FoodWithEffectItem(1, 0.1F, Rarity.UNCOMMON, () -> new MobEffectInstance(MobEffects.SLOW_FALLING, 110, 0)));
	public static final RegistryObject<Item> GREEN_SOLID_ETHER_FRAGMENT = ITEMS.register("green_solid_ether_fragment", () -> new FoodWithEffectItem(1, 0.1F, Rarity.UNCOMMON, () -> new MobEffectInstance(MobEffects.JUMP, 90, 1)));
	public static final RegistryObject<Item> PURPLE_SOLID_ETHER_FRAGMENT = ITEMS.register("purple_solid_ether_fragment",() -> new FoodWithEffectItem(1, 0.1F, AerialHellRarities.CORRUPTED, () -> new MobEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY.get(), 90, 1)));
	public static final RegistryObject<Item> GOLDEN_NETHER_MEAT_PIECE = ITEMS.register("golden_nether_meat_piece", () -> new FoodWithEffectItem(1, 0.1F, Rarity.UNCOMMON, () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 110, 0)));
	public static final RegistryObject<Item> GOLDEN_NETHER_STEAK = ITEMS.register("golden_nether_steak", () -> new FoodWithEffectItem(4, 0.4F, Rarity.UNCOMMON, () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 500, 0)));
	public static final RegistryObject<Item> VIBRANT_GOLDEN_NETHER_STEAK = ITEMS.register("vibrant_golden_nether_steak", () -> new FoodWithEffectItem(6, 0.8F, AerialHellRarities.VIBRANT, () -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1000, 0)));

	//buckets
	public static final RegistryObject<Item> IRON_LIQUID_OF_GODS_BUCKET = ITEMS.register("iron_liquid_of_gods_bucket", () -> new BucketItem(AerialHellFluids.LIQUID_OF_THE_GODS_SOURCE, (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> RUBY_LIQUID_OF_GODS_BUCKET = ITEMS.register("ruby_liquid_of_gods_bucket", () -> new RubyLiquidOfGodsBucketItem(new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> RUBY_BUCKET = ITEMS.register("ruby_bucket", () -> new RubyBucketItem(new Item.Properties().stacksTo(16)));
	public static final RegistryObject<Item> RUBY_WATER_BUCKET = ITEMS.register("ruby_water_bucket", () -> new RubyWaterBucketItem(new Item.Properties().stacksTo(1)));
	public static final RegistryObject<Item> RUBY_MILK_BUCKET = ITEMS.register("ruby_milk_bucket", () -> new RubyMilkBucketItem(new Item.Properties().stacksTo(1)));

	//arrows & bows
	public static final RegistryObject<Item> RUBY_BLOWPIPE_ARROW = ITEMS.register("ruby_blowpipe_arrow", () -> new AerialArrowItem(new Item.Properties()));
	public static final RegistryObject<Item> VOLUCITE_BLOWPIPE_ARROW = ITEMS.register("volucite_blowpipe_arrow", () -> new AerialArrowItem(new Item.Properties().rarity(AerialHellRarities.VIBRANT)));

	public static final RegistryObject<Item> RUBY_BLOWPIPE = ITEMS.register("ruby_blowpipe", () -> new BlowpipeItem(new Item.Properties().stacksTo(1).durability(200), 1.7F));
	public static final RegistryObject<Item> VOLUCITE_BLOWPIPE = ITEMS.register("volucite_blowpipe", () -> new BlowpipeItem(new Item.Properties().rarity(AerialHellRarities.VIBRANT).stacksTo(1).durability(400), 2.4F));

	//music discs
	public static final RegistryObject<Item> MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP = ITEMS.register("music_disc_aerial_hell_theme_tommaup", () -> new RecordItem(1, () -> AerialHellSoundEvents.MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP.get(), new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 2860));
	public static final RegistryObject<Item> MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER = ITEMS.register("music_disc_sweden_andreas_zoeller", () -> new RecordItem(1, () -> AerialHellSoundEvents.MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER.get(), new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3740));
	public static final RegistryObject<Item> MUSIC_DISC_ENTHUSIAST_TOURS = ITEMS.register("music_disc_enthusiast_tours", () -> new RecordItem(1, () -> AerialHellSoundEvents.MUSIC_DISC_ENTHUSIAST_TOURS.get(), new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3380));
	public static final RegistryObject<Item> MUSIC_DISC_BMINOR_ARULO = ITEMS.register("music_disc_bminor_arulo", () -> new RecordItem(1, () -> AerialHellSoundEvents.MUSIC_DISC_BMINOR_ARULO.get(), new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 2160));
	public static final RegistryObject<Item> MUSIC_DISC_RETRO_EXPLORATION_TOMMAUP = ITEMS.register("music_disc_retro_exploration_tommaup", () -> new RecordItem(1, () -> AerialHellSoundEvents.MUSIC_DISC_RETRO_EXPLORATION_TOMMAUP.get(), new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 3240));

	//fluids
    public static final RegistryObject<LiquidBlock> LIQUID_OF_THE_GODS = BLOCKS.register("liquid_of_the_gods", () -> new AerialHellFluidBlock(AerialHellFluids.LIQUID_OF_THE_GODS_SOURCE, BlockBehaviour.Properties.of().replaceable().lightLevel((state) -> 8)));

    //tools
    public static final RegistryObject<PickaxeItem> SKY_WOOD_PICKAXE = ITEMS.register("sky_wood_pickaxe", () -> new PickaxeItem(ToolMaterials.sky_wood, 1, -2.8F, (new Item.Properties())));
    public static final RegistryObject<PickaxeItem> STELLAR_STONE_PICKAXE = ITEMS.register("stellar_stone_pickaxe", () -> new PickaxeItem(ToolMaterials.stellar_stone, 1, -2.8F, (new Item.Properties())));
    public static final RegistryObject<PickaxeItem> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe", () -> new PickaxeItem(ToolMaterials.ruby, 1, -2.8F, (new Item.Properties())));
    public static final RegistryObject<PickaxeItem> AZURITE_PICKAXE = ITEMS.register("azurite_pickaxe", () -> new PickaxeItem(ToolMaterials.azurite, 1, -2.8F, (new Item.Properties())));
    public static final RegistryObject<PickaxeItem> MAGMATIC_GEL_PICKAXE = ITEMS.register("magmatic_gel_pickaxe", () -> new PickaxeItem(ToolMaterials.magmatic_gel, 1, -2.8F, (new Item.Properties())));
    public static final RegistryObject<PickaxeItem> OBSIDIAN_PICKAXE = ITEMS.register("obsidian_pickaxe", () -> new PickaxeItem(ToolMaterials.obsidian, 1, -2.8F, (new Item.Properties()).rarity(Rarity.EPIC)));
    public static final RegistryObject<PickaxeItem> VOLUCITE_PICKAXE = ITEMS.register("volucite_pickaxe", () -> new EffectPickaxeItem(ToolMaterials.volucite, 1, -2.8F, 0.0F, 0.0F, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT)));
    public static final RegistryObject<PickaxeItem> LUNATIC_PICKAXE = ITEMS.register("lunatic_pickaxe", () -> new PickaxeItem(ToolMaterials.lunatic, 1, -2.8F, (new Item.Properties()).rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<PickaxeItem> ARSONIST_PICKAXE = ITEMS.register("arsonist_pickaxe", () -> new PickaxeItem(ToolMaterials.arsonist, 1, -2.8F, (new Item.Properties()).rarity(AerialHellRarities.MYTHICAL).fireResistant()));

    public static final RegistryObject<PickaxeItem> MAGMA_CUBE_PICKAXE = ITEMS.register("magma_cube_pickaxe", () -> new EffectPickaxeItem(ToolMaterials.obsidian, 1, -2.8F, 0.0F, 0.0F, (new Item.Properties()).rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<PickaxeItem> STELLAR_STONE_BREAKER = ITEMS.register("stellar_stone_breaker", () -> new AerialHellPickaxeItem(ToolMaterials.breaker, 1, -2.8F, 0.0F, 0.0F, (new Item.Properties()).rarity(Rarity.EPIC)));

    public static final RegistryObject<ShovelItem> SKY_WOOD_SHOVEL = ITEMS.register("sky_wood_shovel", () -> new ShovelItem(ToolMaterials.sky_wood, 1.5F, -3F, (new Item.Properties())));
    public static final RegistryObject<ShovelItem> STELLAR_STONE_SHOVEL = ITEMS.register("stellar_stone_shovel", () -> new ShovelItem(ToolMaterials.stellar_stone, 1.5F, -3F, (new Item.Properties())));
    public static final RegistryObject<ShovelItem> RUBY_SHOVEL = ITEMS.register("ruby_shovel", () -> new ShovelItem(ToolMaterials.ruby, 1.5F, -3F, (new Item.Properties())));
    public static final RegistryObject<ShovelItem> AZURITE_SHOVEL = ITEMS.register("azurite_shovel", () -> new ShovelItem(ToolMaterials.azurite, 1.5F, -3F, (new Item.Properties())));
    public static final RegistryObject<ShovelItem> MAGMATIC_GEL_SHOVEL = ITEMS.register("magmatic_gel_shovel", () -> new ShovelItem(ToolMaterials.magmatic_gel, 1.5F, -3F, (new Item.Properties())));
    public static final RegistryObject<ShovelItem> OBSIDIAN_SHOVEL = ITEMS.register("obsidian_shovel", () -> new ShovelItem(ToolMaterials.obsidian, 1.5F, -3F, (new Item.Properties()).rarity(Rarity.EPIC)));
    public static final RegistryObject<ShovelItem> VOLUCITE_SHOVEL = ITEMS.register("volucite_shovel", () -> new EffectShovelItem(ToolMaterials.volucite, 1.5F, -3F, 0.0F, 0.0F, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT)));
    public static final RegistryObject<ShovelItem> LUNATIC_SHOVEL = ITEMS.register("lunatic_shovel", () -> new ShovelItem(ToolMaterials.lunatic, 1.5F, -3F, (new Item.Properties()).rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<ShovelItem> ARSONIST_SHOVEL = ITEMS.register("arsonist_shovel", () -> new ShovelItem(ToolMaterials.arsonist, 1.5F, -3F, (new Item.Properties()).rarity(AerialHellRarities.MYTHICAL).fireResistant()));

    public static final RegistryObject<ShovelItem> MAGMA_CUBE_SHOVEL = ITEMS.register("magma_cube_shovel", () -> new EffectShovelItem(ToolMaterials.obsidian, 1.5F, -3F, 0.0F, 0.0F, (new Item.Properties()).rarity(AerialHellRarities.LEGENDARY)));

    public static final RegistryObject<AxeItem> SKY_WOOD_AXE = ITEMS.register("sky_wood_axe", () -> new AxeItem(ToolMaterials.sky_wood, 6, -3.1F, (new Item.Properties())));
    public static final RegistryObject<AxeItem> STELLAR_STONE_AXE = ITEMS.register("stellar_stone_axe", () -> new AxeItem(ToolMaterials.stellar_stone, 6, -3.1F, (new Item.Properties())));
    public static final RegistryObject<AxeItem> RUBY_AXE = ITEMS.register("ruby_axe", () -> new AxeItem(ToolMaterials.ruby, 6, -3.1F, (new Item.Properties())));
    public static final RegistryObject<AxeItem> AZURITE_AXE = ITEMS.register("azurite_axe", () -> new AxeItem(ToolMaterials.azurite, 6, -3.1F, (new Item.Properties())));
    public static final RegistryObject<AxeItem> MAGMATIC_GEL_AXE = ITEMS.register("magmatic_gel_axe", () -> new AxeItem(ToolMaterials.magmatic_gel, 6, -3.1F, (new Item.Properties())));
    public static final RegistryObject<AxeItem> OBSIDIAN_AXE = ITEMS.register("obsidian_axe", () -> new AxeItem(ToolMaterials.obsidian, 6, -3.1F, (new Item.Properties()).rarity(Rarity.EPIC)));
    public static final RegistryObject<AxeItem> VOLUCITE_AXE = ITEMS.register("volucite_axe", () -> new EffectAxeItem(ToolMaterials.volucite, 6, -3.1F, 0.0F, 0.0F, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT)));
    public static final RegistryObject<AxeItem> LUNATIC_AXE = ITEMS.register("lunatic_axe", () -> new AxeItem(ToolMaterials.lunatic, 6, -3.1F, (new Item.Properties()).rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<AxeItem> ARSONIST_AXE = ITEMS.register("arsonist_axe", () -> new AxeItem(ToolMaterials.arsonist, 6, -3.1F, (new Item.Properties()).rarity(AerialHellRarities.MYTHICAL).fireResistant()));

    public static final RegistryObject<AxeItem> HEAVY_AXE = ITEMS.register("heavy_axe", () -> new AerialHellAxeItem(ToolMaterials.heavy, 6, -3.5F, -0.30F, 0.0F, (new Item.Properties()).setNoRepair().rarity(Rarity.EPIC)));
    public static final RegistryObject<AxeItem> AXE_OF_LIGHT = ITEMS.register("axe_of_light", () -> new EffectAxeItem(ToolMaterials.lunatic, 5, -3.1F, 0.0F, 0.0F, (new Item.Properties()).rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<AxeItem> CURSED_AXE = ITEMS.register("cursed_axe", () -> new AerialHellAxeItem(ToolMaterials.shadow, 2, -3.2F, 0.0F, 0.0F, (new Item.Properties()).rarity(AerialHellRarities.CORRUPTED)));
    public static final RegistryObject<AxeItem> BERSERK_AXE = ITEMS.register("berserk_axe", () -> new BerserkAxeItem(ToolMaterials.arsonist, 4, -2.5F, 0.05F, 0.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.MYTHICAL)));

    public static final RegistryObject<HoeItem> SKY_WOOD_HOE = ITEMS.register("sky_wood_hoe", () -> new HoeItem(ToolMaterials.sky_wood, -3, 0.0F, (new Item.Properties())));
    public static final RegistryObject<HoeItem> STELLAR_STONE_HOE = ITEMS.register("stellar_stone_hoe", () -> new HoeItem(ToolMaterials.stellar_stone, -3, 0.0F, (new Item.Properties())));
    public static final RegistryObject<HoeItem> RUBY_HOE = ITEMS.register("ruby_hoe", () -> new HoeItem(ToolMaterials.ruby, -3, 0.0F, (new Item.Properties())));
    public static final RegistryObject<HoeItem> AZURITE_HOE = ITEMS.register("azurite_hoe", () -> new HoeItem(ToolMaterials.azurite, -3, 0.0F, (new Item.Properties())));
    public static final RegistryObject<HoeItem> MAGMATIC_GEL_HOE = ITEMS.register("magmatic_gel_hoe", () -> new HoeItem(ToolMaterials.magmatic_gel, 0, 0.0F, (new Item.Properties())));
    public static final RegistryObject<HoeItem> OBSIDIAN_HOE = ITEMS.register("obsidian_hoe", () -> new HoeItem(ToolMaterials.obsidian, -3, 0.0F, (new Item.Properties()).rarity(Rarity.EPIC)));
    public static final RegistryObject<HoeItem> VOLUCITE_HOE = ITEMS.register("volucite_hoe", () -> new EffectHoeItem(ToolMaterials.volucite, -3, 0.0F, 0.0F, 0.0F, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT)));
    public static final RegistryObject<HoeItem> LUNATIC_HOE = ITEMS.register("lunatic_hoe", () -> new HoeItem(ToolMaterials.lunatic, -1, 0.0F, (new Item.Properties()).rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<HoeItem> ARSONIST_HOE = ITEMS.register("arsonist_hoe", () -> new HoeItem(ToolMaterials.arsonist, -1, 0.0F, (new Item.Properties()).rarity(AerialHellRarities.MYTHICAL).fireResistant()));

    public static final RegistryObject<HoeItem> REAPER_SCYTHE = ITEMS.register("reaper_scythe", () -> new EffectHoeItem(ToolMaterials.shadow, 4, -2.8F, 0.0F, 0.0F, (new Item.Properties()).rarity(AerialHellRarities.MYTHICAL).fireResistant()));

    //weapons
    public static final RegistryObject<SwordItem> SKY_WOOD_SWORD = ITEMS.register("sky_wood_sword", () -> new SwordItem(ToolMaterials.sky_wood, 3, -2.4F, (new Item.Properties())));
    public static final RegistryObject<SwordItem> STELLAR_STONE_SWORD = ITEMS.register("stellar_stone_sword", () -> new SwordItem(ToolMaterials.stellar_stone, 3, -2.4F, (new Item.Properties())));
    public static final RegistryObject<SwordItem> RUBY_SWORD = ITEMS.register("ruby_sword", () -> new SwordItem(ToolMaterials.ruby, 3, -2.4F, (new Item.Properties())));
    public static final RegistryObject<SwordItem> AZURITE_SWORD = ITEMS.register("azurite_sword", () -> new SwordItem(ToolMaterials.azurite, 3, -2.4F, (new Item.Properties())));
    public static final RegistryObject<SwordItem> MAGMATIC_GEL_SWORD = ITEMS.register("magmatic_gel_sword", () -> new SwordItem(ToolMaterials.magmatic_gel, 3, -2.4F, (new Item.Properties())));
    public static final RegistryObject<SwordItem> OBSIDIAN_SWORD = ITEMS.register("obsidian_sword", () -> new SwordItem(ToolMaterials.obsidian, 3, -2.4F, (new Item.Properties()).rarity(Rarity.EPIC)));
    public static final RegistryObject<SwordItem> VOLUCITE_SWORD = ITEMS.register("volucite_sword", () -> new EffectSwordItem(ToolMaterials.volucite, 3, -2.4F, 0.0F, 0.0F, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT)));
    public static final RegistryObject<SwordItem> LUNATIC_SWORD = ITEMS.register("lunatic_sword", () -> new SwordItem(ToolMaterials.lunatic, 3, -2.4F, (new Item.Properties()).rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<SwordItem> ARSONIST_SWORD = ITEMS.register("arsonist_sword", () -> new AerialHellSwordItem(ToolMaterials.arsonist, 3, -2.4F, 0.0F, 0.0F, (new Item.Properties()).rarity(AerialHellRarities.MYTHICAL).fireResistant()));

    public static final RegistryObject<SwordItem> HEAVY_SWORD = ITEMS.register("heavy_sword", () -> new AerialHellSwordItem(ToolMaterials.heavy, 3, -2.7F, -0.30F, 0.0F, (new Item.Properties()).setNoRepair().rarity(Rarity.EPIC)));
    public static final RegistryObject<SwordItem> HEALTH_BOOST_SWORD = ITEMS.register("health_boost_sword", () -> new AerialHellSwordItem(ToolMaterials.lunatic, 2, -2.4F, 0.0F, 4.0F, (new Item.Properties()).setNoRepair().rarity(Rarity.EPIC)));
    public static final RegistryObject<SwordItem> NINJA_SWORD = ITEMS.register("ninja_sword", () -> new EffectSwordItem(ToolMaterials.obsidian, 2, -1.6F, 0.15F, 0.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<SwordItem> NINJA_MASTER_SWORD = ITEMS.register("ninja_master_sword", () -> new EffectSwordItem(ToolMaterials.obsidian, 4, -1.6F, 0.15F, 0.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.MYTHICAL)));
    public static final RegistryObject<SwordItem> GLOUTON_SWORD = ITEMS.register("glouton_sword", () -> new EffectSwordItem(ToolMaterials.ruby, 4, -2.4F, 0.0F, 0.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<SwordItem> RANDOM_SWORD = ITEMS.register("random_sword", () -> new EffectSwordItem(ToolMaterials.ruby, 3, -2.4F, 0.0F, 0.0F, (new Item.Properties()).setNoRepair().rarity(Rarity.EPIC)));
    public static final RegistryObject<SwordItem> DISLOYAL_SWORD = ITEMS.register("disloyal_sword", () -> new AerialHellSwordItem(ToolMaterials.lunatic, 2, -2.4F, 0.0F, 0.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<SwordItem> CURSED_SWORD = ITEMS.register("cursed_sword", () -> new AerialHellSwordItem(ToolMaterials.shadow, 1, -2.5F, 0.0F, 0.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.CORRUPTED)));
    public static final RegistryObject<SwordItem> ABSOLUTE_ZERO_SWORD = ITEMS.register("absolute_zero_sword", () -> new AerialHellSwordItem(ToolMaterials.lunatic, 2, -2.4F, 0.0F, 0.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.MYTHICAL)));
    public static final RegistryObject<SwordItem> SWORD_OF_LIGHT = ITEMS.register("sword_of_light", () -> new EffectSwordItem(ToolMaterials.lunatic, 2, -2.4F, 0.0F, 0.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<SwordItem> ANTIDOTE_SWORD = ITEMS.register("antidote_sword", () -> new EffectSwordItem(ToolMaterials.ruby, 4, -2.4F, 0.0F, 0.0F, (new Item.Properties()).setNoRepair().rarity(Rarity.EPIC)));
    public static final RegistryObject<SwordItem> NETHERIAN_KING_SWORD = ITEMS.register("netherian_king_sword", () -> new EffectSwordItem(ToolMaterials.obsidian, 1, -2.4F, 0.0F, 0.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<SwordItem> GLASS_CANON_SWORD = ITEMS.register("glass_canon_sword", () -> new EffectSwordItem(ToolMaterials.arsonist, 7, -1.6F, 0.0F, 0.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.MYTHICAL)));
    public static final RegistryObject<SwordItem> GOD_SWORD = ITEMS.register("god_sword", () -> new EffectSwordItem(ToolMaterials.arsonist, 3, -2.4F, 0.0F, 0.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.MYTHICAL)));

    public static final RegistryObject<SwordItem> FORGOTTEN_BATTLE_TRIDENT = ITEMS.register("forgotten_battle_trident", () -> new ForgottenBattleTridentItem(ToolMaterials.volucite, 3, -2.9F, 0.2F, (new Item.Properties()).durability(1000).rarity(AerialHellRarities.LEGENDARY)));

    //armor
    public static final RegistryObject<ArmorItem> RUBY_HELMET = ITEMS.register("ruby_helmet", () -> new ArmorItem(AerialHellArmorMaterials.ruby, ArmorItem.Type.HELMET, (new Item.Properties())));
    public static final RegistryObject<ArmorItem> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate", () -> new ArmorItem(AerialHellArmorMaterials.ruby, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
    public static final RegistryObject<ArmorItem> RUBY_LEGGINGS = ITEMS.register("ruby_leggings", () -> new ArmorItem(AerialHellArmorMaterials.ruby, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
    public static final RegistryObject<ArmorItem> RUBY_BOOTS = ITEMS.register("ruby_boots", () -> new ArmorItem(AerialHellArmorMaterials.ruby, ArmorItem.Type.BOOTS, (new Item.Properties())));

    public static final RegistryObject<ArmorItem> AZURITE_HELMET = ITEMS.register("azurite_helmet", () -> new ArmorItem(AerialHellArmorMaterials.azurite, ArmorItem.Type.HELMET, (new Item.Properties())));
    public static final RegistryObject<ArmorItem> AZURITE_CHESTPLATE = ITEMS.register("azurite_chestplate", () -> new ArmorItem(AerialHellArmorMaterials.azurite, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
    public static final RegistryObject<ArmorItem> AZURITE_LEGGINGS = ITEMS.register("azurite_leggings", () -> new ArmorItem(AerialHellArmorMaterials.azurite, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
    public static final RegistryObject<ArmorItem> AZURITE_BOOTS = ITEMS.register("azurite_boots", () -> new ArmorItem(AerialHellArmorMaterials.azurite, ArmorItem.Type.BOOTS, (new Item.Properties())));

    public static final RegistryObject<ArmorItem> OBSIDIAN_HELMET = ITEMS.register("obsidian_helmet", () -> new ArmorItem(AerialHellArmorMaterials.obsidian, ArmorItem.Type.HELMET, (new Item.Properties()).rarity(Rarity.EPIC)));
    public static final RegistryObject<ArmorItem> OBSIDIAN_CHESTPLATE = ITEMS.register("obsidian_chestplate", () -> new ArmorItem(AerialHellArmorMaterials.obsidian, ArmorItem.Type.CHESTPLATE, (new Item.Properties()).rarity(Rarity.EPIC)));
    public static final RegistryObject<ArmorItem> OBSIDIAN_LEGGINGS = ITEMS.register("obsidian_leggings", () -> new ArmorItem(AerialHellArmorMaterials.obsidian, ArmorItem.Type.LEGGINGS, (new Item.Properties()).rarity(Rarity.EPIC)));
    public static final RegistryObject<ArmorItem> OBSIDIAN_BOOTS = ITEMS.register("obsidian_boots", () -> new ArmorItem(AerialHellArmorMaterials.obsidian, ArmorItem.Type.BOOTS, (new Item.Properties()).rarity(Rarity.EPIC)));

    public static final RegistryObject<ArmorItem> VOLUCITE_HELMET = ITEMS.register("volucite_helmet", () -> new ArmorItem(AerialHellArmorMaterials.volucite, ArmorItem.Type.HELMET, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT)));
    public static final RegistryObject<ArmorItem> VOLUCITE_CHESTPLATE = ITEMS.register("volucite_chestplate", () -> new ArmorItem(AerialHellArmorMaterials.volucite, ArmorItem.Type.CHESTPLATE, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT)));
    public static final RegistryObject<ArmorItem> VOLUCITE_LEGGINGS = ITEMS.register("volucite_leggings", () -> new ArmorItem(AerialHellArmorMaterials.volucite, ArmorItem.Type.LEGGINGS, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT)));
    public static final RegistryObject<ArmorItem> VOLUCITE_BOOTS = ITEMS.register("volucite_boots", () -> new ArmorItem(AerialHellArmorMaterials.volucite, ArmorItem.Type.BOOTS, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT)));

    public static final RegistryObject<ArmorItem> MAGMATIC_GEL_HELMET = ITEMS.register("magmatic_gel_helmet", () -> new ArmorItem(AerialHellArmorMaterials.magmatic_gel, ArmorItem.Type.HELMET, (new Item.Properties())));
    public static final RegistryObject<ArmorItem> MAGMATIC_GEL_CHESTPLATE = ITEMS.register("magmatic_gel_chestplate", () -> new ArmorItem(AerialHellArmorMaterials.magmatic_gel, ArmorItem.Type.CHESTPLATE, (new Item.Properties())));
    public static final RegistryObject<ArmorItem> MAGMATIC_GEL_LEGGINGS = ITEMS.register("magmatic_gel_leggings", () -> new ArmorItem(AerialHellArmorMaterials.magmatic_gel, ArmorItem.Type.LEGGINGS, (new Item.Properties())));
    public static final RegistryObject<ArmorItem> MAGMATIC_GEL_BOOTS = ITEMS.register("magmatic_gel_boots", () -> new ArmorItem(AerialHellArmorMaterials.magmatic_gel, ArmorItem.Type.BOOTS, (new Item.Properties())));

    public static final RegistryObject<ArmorItem> LUNATIC_HELMET = ITEMS.register("lunatic_helmet", () -> new ArmorItem(AerialHellArmorMaterials.lunatic, ArmorItem.Type.HELMET, (new Item.Properties()).rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<ArmorItem> LUNATIC_CHESTPLATE = ITEMS.register("lunatic_chestplate", () -> new ArmorItem(AerialHellArmorMaterials.lunatic, ArmorItem.Type.CHESTPLATE, (new Item.Properties()).rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<ArmorItem> LUNATIC_LEGGINGS = ITEMS.register("lunatic_leggings", () -> new ArmorItem(AerialHellArmorMaterials.lunatic, ArmorItem.Type.LEGGINGS, (new Item.Properties()).rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<ArmorItem> LUNATIC_BOOTS = ITEMS.register("lunatic_boots", () -> new ArmorItem(AerialHellArmorMaterials.lunatic, ArmorItem.Type.BOOTS, (new Item.Properties()).rarity(AerialHellRarities.LEGENDARY)));

    public static final RegistryObject<ArmorItem> ARSONIST_HELMET = ITEMS.register("arsonist_helmet", () -> new ArmorItem(AerialHellArmorMaterials.arsonist, ArmorItem.Type.HELMET, (new Item.Properties()).rarity(AerialHellRarities.MYTHICAL).fireResistant()));
    public static final RegistryObject<ArmorItem> ARSONIST_CHESTPLATE = ITEMS.register("arsonist_chestplate", () -> new ArmorItem(AerialHellArmorMaterials.arsonist, ArmorItem.Type.CHESTPLATE, (new Item.Properties()).rarity(AerialHellRarities.MYTHICAL).fireResistant()));
    public static final RegistryObject<ArmorItem> ARSONIST_LEGGINGS = ITEMS.register("arsonist_leggings", () -> new ArmorItem(AerialHellArmorMaterials.arsonist, ArmorItem.Type.LEGGINGS, (new Item.Properties()).rarity(AerialHellRarities.MYTHICAL).fireResistant()));
    public static final RegistryObject<ArmorItem> ARSONIST_BOOTS = ITEMS.register("arsonist_boots", () -> new ArmorItem(AerialHellArmorMaterials.arsonist, ArmorItem.Type.BOOTS, (new Item.Properties()).rarity(AerialHellRarities.MYTHICAL).fireResistant()));

	public static final RegistryObject<ArmorItem> SHADOW_HELMET = ITEMS.register("shadow_helmet", () -> new ArmorItem(AerialHellArmorMaterials.shadow, ArmorItem.Type.HELMET, (new Item.Properties()).rarity(AerialHellRarities.CORRUPTED)));
	public static final RegistryObject<ArmorItem> SHADOW_CHESTPLATE = ITEMS.register("shadow_chestplate", () -> new AerialHellArmorItem(AerialHellArmorMaterials.shadow, ArmorItem.Type.CHESTPLATE, (new Item.Properties()).rarity(AerialHellRarities.CORRUPTED)));
	public static final RegistryObject<ArmorItem> SHADOW_LEGGINGS = ITEMS.register("shadow_leggings", () -> new ArmorItem(AerialHellArmorMaterials.shadow, ArmorItem.Type.LEGGINGS, (new Item.Properties()).rarity(AerialHellRarities.CORRUPTED)));
	public static final RegistryObject<ArmorItem> SHADOW_BOOTS = ITEMS.register("shadow_boots", () -> new ArmorItem(AerialHellArmorMaterials.shadow, ArmorItem.Type.BOOTS, (new Item.Properties()).rarity(AerialHellRarities.CORRUPTED)));

	//effect totems
    public static final RegistryObject<Item> REGENERATION_TOTEM = ITEMS.register("regeneration_totem", () -> new EffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<Item> SPEED_TOTEM = ITEMS.register("speed_totem", () -> new EffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<Item> SPEED_II_TOTEM = ITEMS.register("speed_ii_totem", () -> new EnchantedEffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.MYTHICAL)));
    public static final RegistryObject<Item> NIGHT_VISION_TOTEM = ITEMS.register("night_vision_totem", () -> new EffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<Item> AGILITY_TOTEM = ITEMS.register("agility_totem", () -> new EnchantedEffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.MYTHICAL)));
    public static final RegistryObject<Item> HERO_TOTEM = ITEMS.register("hero_totem", () -> new EffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.MYTHICAL)));
    public static final RegistryObject<Item> HEAD_IN_THE_CLOUDS_TOTEM = ITEMS.register("head_in_the_clouds_totem", () -> new EffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.VIBRANT)));
    public static final RegistryObject<Item> GOD_TOTEM = ITEMS.register("god_totem", () -> new EnchantedEffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.MYTHICAL).fireResistant()));
    public static final RegistryObject<Item> CURSED_TOTEM = ITEMS.register("cursed_totem", () -> new EffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.MYTHICAL)));
    public static final RegistryObject<Item> SHADOW_TOTEM = ITEMS.register("shadow_totem", () -> new EffectTotemItem(new Item.Properties().stacksTo(1).rarity(AerialHellRarities.CORRUPTED)));

    //spawn eggs
    public static final RegistryObject<Item> STELLAR_STONE_AUTOMATON_SPAWN_EGG = ITEMS.register("stellar_stone_automaton_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.STELLAR_STONE_AUTOMATON.get(), 16382457, 16382457, new Item.Properties().rarity(AerialHellRarities.VIBRANT)));
    public static final RegistryObject<Item> EVIL_COW_SPAWN_EGG = ITEMS.register("evil_cow_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.EVIL_COW.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> CORTINARIUS_COW_SPAWN_EGG = ITEMS.register("cortinarius_cow_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.CORTINARIUS_COW.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> STELLAR_ENT_SPAWN_EGG = ITEMS.register("stellar_ent_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.STELLAR_ENT.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> VENOMOUS_SNAKE_SPAWN_EGG = ITEMS.register("venomous_snake_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.VENOMOUS_SNAKE.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> WORM_SPAWN_EGG = ITEMS.register("worm_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.WORM.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> STELLAR_CHICKEN_SPAWN_EGG = ITEMS.register("stellar_chicken_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.STELLAR_CHICKEN.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> STELLAR_BOAR_SPAWN_EGG = ITEMS.register("stellar_boar_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.STELLAR_BOAR.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> SHROOMBOOM_SPAWN_EGG = ITEMS.register("shroomboom_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.SHROOMBOOM.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> VERDIGRIS_ZOMBIE_SPAWN_EGG = ITEMS.register("verdigris_zombie_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.VERDIGRIS_ZOMBIE.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> MUMMY_SPAWN_EGG = ITEMS.register("mummy_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.MUMMY.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> SLIME_PIRATE_SPAWN_EGG = ITEMS.register("slime_pirate_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.SLIME_PIRATE.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> SLIME_NINJA_PIRATE_SPAWN_EGG = ITEMS.register("slime_ninja_pirate_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.SLIME_NINJA_PIRATE.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> GHOST_SLIME_PIRATE_SPAWN_EGG = ITEMS.register("ghost_slime_pirate_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.GHOST_SLIME_PIRATE.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> GHOST_SLIME_NINJA_PIRATE_SPAWN_EGG = ITEMS.register("ghost_slime_ninja_pirate_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.GHOST_SLIME_NINJA_PIRATE.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> SANDY_SHEEP_SPAWN_EGG = ITEMS.register("sandy_sheep_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.SANDY_SHEEP.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
	public static final RegistryObject<Item> GLIDING_TURTLE_SPAWN_EGG = ITEMS.register("gliding_turtle_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.GLIDING_TURTLE.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> FAT_PHANTOM_SPAWN_EGG = ITEMS.register("fat_phantom_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.FAT_PHANTOM.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> KODAMA_SPAWN_EGG = ITEMS.register("kodama_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.KODAMA.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> MUD_GOLEM_SPAWN_EGG = ITEMS.register("mud_golem_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.MUD_GOLEM.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> MUD_SOLDIER_SPAWN_EGG = ITEMS.register("mud_soldier_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.MUD_SOLDIER.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> MUD_CYCLE_MAGE_SPAWN_EGG = ITEMS.register("mud_cycle_mage_spawn_egg", () -> new BossSpawnEggItem(() -> AerialHellEntities.MUD_CYCLE_MAGE.get(), 16382457, 16382457, new Item.Properties().rarity(AerialHellRarities.LEGENDARY)));
    public static final RegistryObject<Item> HELL_SPIDER_SPAWN_EGG = ITEMS.register("hell_spider_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.HELL_SPIDER.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> CRYSTAL_GOLEM_SPAWN_EGG = ITEMS.register("crystal_golem_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.CRYSTAL_GOLEM.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> CRYSTAL_SLIME_SPAWN_EGG = ITEMS.register("crystal_slime_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.CRYSTAL_SLIME.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> CRYSTAL_SPIDER_SPAWN_EGG = ITEMS.register("crystal_spider_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.CRYSTAL_SPIDER.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> LUNATIC_PRIEST_SPAWN_EGG = ITEMS.register("lunatic_priest_spawn_egg", () -> new BossSpawnEggItem(() -> AerialHellEntities.LUNATIC_PRIEST.get(), 16382457, 16382457, new Item.Properties().rarity(AerialHellRarities.MYTHICAL)));
    public static final RegistryObject<Item> CRYSTAL_CATERPILLAR_SPAWN_EGG = ITEMS.register("crystal_caterpillar_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.CRYSTAL_CATERPILLAR.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> FOREST_CATERPILLAR_SPAWN_EGG = ITEMS.register("forest_caterpillar_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.FOREST_CATERPILLAR.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> TORN_SPIRIT_SPAWN_EGG = ITEMS.register("torn_spirit_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.TORN_SPIRIT.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> CHAINED_GOD_SPAWN_EGG = ITEMS.register("chained_god_spawn_egg", () -> new BossSpawnEggItem(() -> AerialHellEntities.CHAINED_GOD.get(), 16382457, 16382457, new Item.Properties().rarity(AerialHellRarities.MYTHICAL)));
    public static final RegistryObject<Item> ICE_SPIRIT_SPAWN_EGG = ITEMS.register("ice_spirit_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.ICE_SPIRIT.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> FIRE_SPIRIT_SPAWN_EGG = ITEMS.register("fire_spirit_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.FIRE_SPIRIT.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> ELECTRO_SPIRIT_SPAWN_EGG = ITEMS.register("electro_spirit_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.ELECTRO_SPIRIT.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> FLYING_JELLYFISH_SPAWN_EGG = ITEMS.register("flying_jellyfish_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.FLYING_JELLYFISH.get(), 16382457, 16382457, new Item.Properties().rarity(Rarity.COMMON)));
    public static final RegistryObject<Item> SHADOW_FLYING_SKULL_SPAWN_EGG = ITEMS.register("shadow_flying_skull_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.SHADOW_FLYING_SKULL.get(), 16382457, 16382457, new Item.Properties().rarity(AerialHellRarities.CORRUPTED)));
    public static final RegistryObject<Item> SHADOW_TROLL_SPAWN_EGG = ITEMS.register("shadow_troll_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.SHADOW_TROLL.get(), 16382457, 16382457, new Item.Properties().rarity(AerialHellRarities.CORRUPTED)));
    public static final RegistryObject<Item> SHADOW_AUTOMATON_SPAWN_EGG = ITEMS.register("shadow_automaton_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.SHADOW_AUTOMATON.get(), 16382457, 16382457, new Item.Properties().rarity(AerialHellRarities.CORRUPTED)));
    public static final RegistryObject<Item> SHADOW_SPIDER_SPAWN_EGG = ITEMS.register("shadow_spider_spawn_egg", () -> new ForgeSpawnEggItem(() -> AerialHellEntities.SHADOW_SPIDER.get(), 16382457, 16382457, new Item.Properties().rarity(AerialHellRarities.CORRUPTED)));
    public static final RegistryObject<Item> LILITH_SPAWN_EGG = ITEMS.register("lilith_spawn_egg", () -> new BossSpawnEggItem(() -> AerialHellEntities.LILITH.get(), 16382457, 16382457, new Item.Properties().rarity(AerialHellRarities.CORRUPTED)));

	public static final RegistryObject<Item> AERIAL_HELL_PAINTING = ITEMS.register("aerial_hell_painting", () -> new AerialHellHangingEntityItem(() -> AerialHellEntities.AERIAL_HELL_PAINTING.get(), new Item.Properties()));

    //build items
    public static final RegistryObject<Item> BLOCK_UPDATER = ITEMS.register("block_updater", () -> new BlockUpdaterItem(new Item.Properties()));
    public static final RegistryObject<Item> BLOCK_CRACKER = ITEMS.register("block_cracker", () -> new BlockCrackerItem(new Item.Properties()));
}
