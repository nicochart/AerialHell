package fr.factionbedrock.aerialhell.Registry;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

import com.google.common.collect.ImmutableMap;

import fr.factionbedrock.aerialhell.Block.MagmaticGelBlock;
import fr.factionbedrock.aerialhell.Block.AerialHellBarrelBlock;
import fr.factionbedrock.aerialhell.Block.SkyCactusBlock;
import fr.factionbedrock.aerialhell.Block.AerialHellCraftingTableBlock;
import fr.factionbedrock.aerialhell.Block.AerialHellDeadBushBlock;
import fr.factionbedrock.aerialhell.Block.StellarGrassBlock;
import fr.factionbedrock.aerialhell.Block.AerialHellPortalBlock;
import fr.factionbedrock.aerialhell.Block.AerialHellFluidBlock;
import fr.factionbedrock.aerialhell.Block.AerialHellOreBlock;
import fr.factionbedrock.aerialhell.Block.AerialHellSignBlock;
import fr.factionbedrock.aerialhell.Block.AerialHellTallGrassBlock;
import fr.factionbedrock.aerialhell.Block.AerialHellTrappedBlock;
import fr.factionbedrock.aerialhell.Block.VibratorBlock;
import fr.factionbedrock.aerialhell.Block.ChestMimicBlock;
import fr.factionbedrock.aerialhell.Block.CopperPineLeavesBlock;
import fr.factionbedrock.aerialhell.Block.FreezerBlock;
import fr.factionbedrock.aerialhell.Block.Bushes.AerialBerryBushBlock;
import fr.factionbedrock.aerialhell.Block.Bushes.VibrantAerialBerryBushBlock;
import fr.factionbedrock.aerialhell.Block.SolidEther.*;
import fr.factionbedrock.aerialhell.Client.TileEntityRenderer.AerialHellChestItemTileEntityRenderer;
import fr.factionbedrock.aerialhell.Item.BlueAercloudFragmentItem;
import fr.factionbedrock.aerialhell.Item.AerialArrowItem;
import fr.factionbedrock.aerialhell.Item.BlowpipeItem;
import fr.factionbedrock.aerialhell.Item.GoldenNetherMeatItem;
import fr.factionbedrock.aerialhell.Item.FrozenAerialBerryItem;
import fr.factionbedrock.aerialhell.Item.GoldenAercloudFragmentItem;
import fr.factionbedrock.aerialhell.Item.GreenAercloudFragmentItem;
import fr.factionbedrock.aerialhell.Item.Bucket.*;
import fr.factionbedrock.aerialhell.Item.Material.ArmorMaterials;
import fr.factionbedrock.aerialhell.Item.Material.ToolMaterials;
import fr.factionbedrock.aerialhell.Item.ThrowingKnife.DiamondThrowingKnifeItem;
import fr.factionbedrock.aerialhell.Item.ThrowingKnife.LightningThrowingKnifeItem;
import fr.factionbedrock.aerialhell.Item.ThrowingKnife.VoluciteThrowingKnifeItem;
import fr.factionbedrock.aerialhell.World.Tree.CopperPine;
import fr.factionbedrock.aerialhell.World.Tree.GoldenBeechTree;
import fr.factionbedrock.aerialhell.World.Tree.AerialTree;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.PressurePlateBlock.Sensitivity;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.WallOrFloorItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraftforge.common.ToolType;

public class AerialHellBlocksAndItems
{
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
	
	public static void registerCompostableItems()
	{
		ComposterBlock.registerCompostable(0.5F, AERIAL_BERRY.get());
		ComposterBlock.registerCompostable(0.85F, VIBRANT_AERIAL_BERRY.get());
		ComposterBlock.registerCompostable(0.85F, FROZEN_AERIAL_BERRY.get());
		ComposterBlock.registerCompostable(0.5F, COPPER_PINE_CONE.get());
		ComposterBlock.registerCompostable(0.3F, AERIAL_TREE_LEAVES_ITEM.get());
		ComposterBlock.registerCompostable(0.3F, AERIAL_TREE_SAPLING_ITEM.get());
		ComposterBlock.registerCompostable(0.3F, GOLDEN_BEECH_LEAVES_ITEM.get());
		ComposterBlock.registerCompostable(0.3F, GOLDEN_BEECH_SAPLING_ITEM.get());
		ComposterBlock.registerCompostable(0.3F, COPPER_PINE_LEAVES_ITEM.get());
		ComposterBlock.registerCompostable(0.3F, COPPER_PINE_SAPLING_ITEM.get());
		ComposterBlock.registerCompostable(0.1F, SKY_CACTUS_FIBER.get());
		ComposterBlock.registerCompostable(0.4F, SKY_CACTUS_ITEM.get());
		ComposterBlock.registerCompostable(0.2F, VIBRANT_SKY_CACTUS_FIBER.get());
		ComposterBlock.registerCompostable(0.8F, VIBRANT_SKY_CACTUS_ITEM.get());
	}
	
	public static void registerPots()
	{
		FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;

		pot.addPlant(BLUE_FLOWER.getId(), POTTED_BLUE_FLOWER);
		pot.addPlant(BLACK_ROSE.getId(), POTTED_BLACK_ROSE);
		pot.addPlant(STELLAR_FERN.getId(), POTTED_STELLAR_FERN);
		pot.addPlant(STELLAR_DEAD_BUSH.getId(), POTTED_STELLAR_DEAD_BUSH);
		pot.addPlant(SKY_CACTUS.getId(), POTTED_SKY_CACTUS);
		pot.addPlant(VIBRANT_SKY_CACTUS.getId(), POTTED_VIBRANT_SKY_CACTUS);
		pot.addPlant(AERIAL_TREE_SAPLING.getId(), POTTED_AERIAL_TREE_SAPLING);
		pot.addPlant(GOLDEN_BEECH_SAPLING.getId(), POTTED_GOLDEN_BEECH_SAPLING);
		pot.addPlant(COPPER_PINE_SAPLING.getId(), POTTED_COPPER_PINE_SAPLING);
	}
	
	public static void registerAxeStrippingBlocks()
	{
		AxeItem.BLOCK_STRIPPING_MAP = ImmutableMap.<Block, Block>builder()
				.putAll(AxeItem.BLOCK_STRIPPING_MAP)
				.put(AERIAL_TREE_LOG.get(), STRIPPED_AERIAL_TREE_LOG.get())
				.put(GOLDEN_BEECH_LOG.get(), STRIPPED_GOLDEN_BEECH_LOG.get())
				.put(COPPER_PINE_LOG.get(), STRIPPED_COPPER_PINE_LOG.get())
				.build();
	}
	
	//materials
	public static AbstractBlock.Properties AERIAL_TREE_MATERIAL = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.5F, 2.5F).sound(SoundType.WOOD).harvestTool(ToolType.AXE).notSolid();
	public static AbstractBlock.Properties AERIAL_TREE_SIGN_MATERIAL = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.5F, 2.5F).sound(SoundType.WOOD).harvestTool(ToolType.AXE).notSolid().doesNotBlockMovement();
	public static AbstractBlock.Properties SKY_CACTUS_FIBER_MATERIAL = AbstractBlock.Properties.create(Material.CACTUS, MaterialColor.CYAN).hardnessAndResistance(2.5F, 2.5F).sound(SoundType.WOOD).harvestTool(ToolType.AXE).notSolid();
	public static AbstractBlock.Properties SKY_CACTUS_FIBER_SIGN_MATERIAL = AbstractBlock.Properties.create(Material.CACTUS, MaterialColor.CYAN).hardnessAndResistance(2.5F, 2.5F).sound(SoundType.WOOD).harvestTool(ToolType.AXE).notSolid().doesNotBlockMovement();
	public static AbstractBlock.Properties MUD_CHEST_MATERIAL = AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).hardnessAndResistance(20.0F, 1000.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid();
	public static AbstractBlock.Properties LUNATIC_CHEST_MATERIAL = AbstractBlock.Properties.create(Material.ROCK, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(30.0F, 1000.0F).sound(SoundType.NETHERITE).harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid();
	public static AbstractBlock.Properties VOLUCITE_CHEST_MATERIAL = AbstractBlock.Properties.create(Material.IRON, MaterialColor.CLAY).hardnessAndResistance(50.0F, 1200.0F).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid();
	public static AbstractBlock.Properties GOLDEN_NETHER_CHEST_MATERIAL = AbstractBlock.Properties.create(Material.IRON, MaterialColor.NETHERRACK).hardnessAndResistance(50.0F, 1200.0F).sound(SoundType.BASALT).harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid();
	public static AbstractBlock.Properties RUBY_MATERIAL = AbstractBlock.Properties.create(Material.IRON, MaterialColor.PURPLE).hardnessAndResistance(10.0F, 2.0F).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid();
	
	//portal
	public static final RegistryObject<AerialHellPortalBlock> AERIAL_HELL_PORTAL = BLOCKS.register("aerial_hell_portal", () -> new AerialHellPortalBlock(AbstractBlock.Properties.from(Blocks.NETHER_PORTAL)));
    
	//bedrock blocks
	public static final RegistryObject<Block> GLOWING_BEDROCK_BLOCK = BLOCKS.register("glowing_bedrock_block", () -> new Block((AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).harvestTool(ToolType.PICKAXE).harvestLevel(3).setRequiresTool().hardnessAndResistance(50.0F, 1200.0F).setLightLevel((state) -> 10))));
	public static final RegistryObject<Block> BEDROCK_ORE = BLOCKS.register("bedrock_ore",() -> new AerialHellOreBlock(4, 6, AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(25.0F, 600.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(3).setRequiresTool()));
	public static final RegistryObject<Item> GLOWING_BEDROCK_BLOCK_ITEM = ITEMS.register("glowing_bedrock_block", () -> new BlockItem(GLOWING_BEDROCK_BLOCK.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_OVERWORLD)));
	public static final RegistryObject<Item> BEDROCK_ORE_ITEM = ITEMS.register("bedrock_ore", () -> new BlockItem(BEDROCK_ORE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_OVERWORLD)));
	public static final RegistryObject<Item> BEDROCK_SCRAP = ITEMS.register("bedrock_scrap", () -> new Item(new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_OVERWORLD)));
	public static final RegistryObject<Item> GLOWING_BEDROCK_INGOT = ITEMS.register("glowing_bedrock_ingot", () -> new Item(new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_OVERWORLD)));
	
	//torch
	public static final RegistryObject<Block> FLUORITE_WALL_TORCH = BLOCKS.register("fluorite_wall_torch", () -> new WallTorchBlock(AbstractBlock.Properties.from(Blocks.WALL_TORCH), ParticleTypes.SMOKE)); //AerialHellParticleTypes.GOLDEN_BEECH_LEAVES.get()
	public static final RegistryObject<Block> FLUORITE_TORCH = BLOCKS.register("fluorite_torch", () -> new TorchBlock(AbstractBlock.Properties.from(Blocks.TORCH), ParticleTypes.SMOKE)); //AerialHellParticleTypes.GOLDEN_BEECH_LEAVES.get()
	public static final RegistryObject<Item> FLUORITE_TORCH_ITEM = ITEMS.register("fluorite_torch", () -> new WallOrFloorItem(FLUORITE_TORCH.get(), FLUORITE_WALL_TORCH.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//ladder
	public static final RegistryObject<LadderBlock> SKY_LADDER = BLOCKS.register("sky_ladder", () -> new LadderBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> SKY_LADDER_ITEM = ITEMS.register("sky_ladder", () -> new BlockItem(SKY_LADDER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
    //aerial_tree
	public static final RegistryObject<RotatedPillarBlock> AERIAL_TREE_LOG = BLOCKS.register("aerial_tree_log", () -> new RotatedPillarBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_AERIAL_TREE_LOG = BLOCKS.register("stripped_aerial_tree_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(AERIAL_TREE_LOG.get()).harvestTool(ToolType.AXE)));
	public static final RegistryObject<Block> AERIAL_TREE_LEAVES = BLOCKS.register("aerial_tree_leaves", () -> new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> AERIAL_TREE_PLANKS = BLOCKS.register("aerial_tree_planks", () -> new Block(AbstractBlock.Properties.from(AERIAL_TREE_LOG.get())));
	public static final RegistryObject<Block> CHISELED_AERIAL_TREE_PLANKS = BLOCKS.register("chiseled_aerial_tree_planks", () -> new Block(AbstractBlock.Properties.from(AERIAL_TREE_PLANKS.get())));
	public static final RegistryObject<Block> AERIAL_TREE_BOOKSHELF = BLOCKS.register("aerial_tree_bookshelf", () -> new Block(AbstractBlock.Properties.from(AERIAL_TREE_PLANKS.get())));
	public static final RegistryObject<SaplingBlock> AERIAL_TREE_SAPLING = BLOCKS.register("aerial_tree_sapling", () -> new SaplingBlock(new AerialTree(), AbstractBlock.Properties.from(Blocks.OAK_SAPLING)));
	public static final RegistryObject<Item> AERIAL_TREE_LOG_ITEM = ITEMS.register("aerial_tree_log", () -> new BlockItem(AERIAL_TREE_LOG.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STRIPPED_AERIAL_TREE_LOG_ITEM = ITEMS.register("stripped_aerial_tree_log", () -> new BlockItem(STRIPPED_AERIAL_TREE_LOG.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> AERIAL_TREE_LEAVES_ITEM = ITEMS.register("aerial_tree_leaves", () -> new BlockItem(AERIAL_TREE_LEAVES.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> AERIAL_TREE_PLANKS_ITEM = ITEMS.register("aerial_tree_planks", () -> new BlockItem(AERIAL_TREE_PLANKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CHISELED_AERIAL_TREE_PLANKS_ITEM = ITEMS.register("chiseled_aerial_tree_planks", () -> new BlockItem(CHISELED_AERIAL_TREE_PLANKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> AERIAL_TREE_BOOKSHELF_ITEM = ITEMS.register("aerial_tree_bookshelf", () -> new BlockItem(AERIAL_TREE_BOOKSHELF.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> AERIAL_TREE_SAPLING_ITEM = ITEMS.register("aerial_tree_sapling", () -> new BlockItem(AERIAL_TREE_SAPLING.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//golden beech
	public static final RegistryObject<RotatedPillarBlock> GOLDEN_BEECH_LOG = BLOCKS.register("golden_beech_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(AERIAL_TREE_LOG.get())));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_GOLDEN_BEECH_LOG = BLOCKS.register("stripped_golden_beech_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(GOLDEN_BEECH_LOG.get()).harvestTool(ToolType.AXE)));
	public static final RegistryObject<Block> GOLDEN_BEECH_PLANKS = BLOCKS.register("golden_beech_planks", () -> new Block(AbstractBlock.Properties.from(GOLDEN_BEECH_LOG.get())));
	public static final RegistryObject<Block> CHISELED_GOLDEN_BEECH_PLANKS = BLOCKS.register("chiseled_golden_beech_planks", () -> new Block(AbstractBlock.Properties.from(GOLDEN_BEECH_PLANKS.get())));
	public static final RegistryObject<Block> GOLDEN_BEECH_LEAVES = BLOCKS.register("golden_beech_leaves", () -> new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> GOLDEN_BEECH_BOOKSHELF = BLOCKS.register("golden_beech_bookshelf", () -> new Block(AbstractBlock.Properties.from(GOLDEN_BEECH_PLANKS.get())));
	public static final RegistryObject<SaplingBlock> GOLDEN_BEECH_SAPLING = BLOCKS.register("golden_beech_sapling", () -> new SaplingBlock(new GoldenBeechTree(), AbstractBlock.Properties.from(Blocks.OAK_SAPLING)));
	public static final RegistryObject<Item> GOLDEN_BEECH_LOG_ITEM = ITEMS.register("golden_beech_log", () -> new BlockItem(GOLDEN_BEECH_LOG.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STRIPPED_GOLDEN_BEECH_LOG_ITEM = ITEMS.register("stripped_golden_beech_log", () -> new BlockItem(STRIPPED_GOLDEN_BEECH_LOG.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLDEN_BEECH_PLANKS_ITEM = ITEMS.register("golden_beech_planks", () -> new BlockItem(GOLDEN_BEECH_PLANKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CHISELED_GOLDEN_BEECH_PLANKS_ITEM = ITEMS.register("chiseled_golden_beech_planks", () -> new BlockItem(CHISELED_GOLDEN_BEECH_PLANKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLDEN_BEECH_LEAVES_ITEM = ITEMS.register("golden_beech_leaves", () -> new BlockItem(GOLDEN_BEECH_LEAVES.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLDEN_BEECH_BOOKSHELF_ITEM = ITEMS.register("golden_beech_bookshelf", () -> new BlockItem(GOLDEN_BEECH_BOOKSHELF.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLDEN_BEECH_SAPLING_ITEM = ITEMS.register("golden_beech_sapling", () -> new BlockItem(GOLDEN_BEECH_SAPLING.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//cropper pine
	public static final RegistryObject<RotatedPillarBlock> COPPER_PINE_LOG = BLOCKS.register("copper_pine_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(AERIAL_TREE_LOG.get())));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_COPPER_PINE_LOG = BLOCKS.register("stripped_copper_pine_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(COPPER_PINE_LOG.get()).harvestTool(ToolType.AXE)));
	public static final RegistryObject<Block> COPPER_PINE_PLANKS = BLOCKS.register("copper_pine_planks", () -> new Block(AbstractBlock.Properties.from(COPPER_PINE_LOG.get())));
	public static final RegistryObject<Block> COPPER_PINE_LEAVES = BLOCKS.register("copper_pine_leaves", () -> new CopperPineLeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES).harvestTool(ToolType.HOE)));
	public static final RegistryObject<Block> COPPER_PINE_BOOKSHELF = BLOCKS.register("copper_pine_bookshelf", () -> new Block(AbstractBlock.Properties.from(COPPER_PINE_PLANKS.get())));
	public static final RegistryObject<SaplingBlock> COPPER_PINE_SAPLING = BLOCKS.register("copper_pine_sapling", () -> new SaplingBlock(new CopperPine(), AbstractBlock.Properties.from(Blocks.OAK_SAPLING)));
	public static final RegistryObject<Item> COPPER_PINE_LOG_ITEM = ITEMS.register("copper_pine_log", () -> new BlockItem(COPPER_PINE_LOG.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STRIPPED_COPPER_PINE_LOG_ITEM = ITEMS.register("stripped_copper_pine_log", () -> new BlockItem(STRIPPED_COPPER_PINE_LOG.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> COPPER_PINE_PLANKS_ITEM = ITEMS.register("copper_pine_planks", () -> new BlockItem(COPPER_PINE_PLANKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> COPPER_PINE_LEAVES_ITEM = ITEMS.register("copper_pine_leaves", () -> new BlockItem(COPPER_PINE_LEAVES.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> COPPER_PINE_BOOKSHELF_ITEM = ITEMS.register("copper_pine_bookshelf", () -> new BlockItem(COPPER_PINE_BOOKSHELF.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> COPPER_PINE_SAPLING_ITEM = ITEMS.register("copper_pine_sapling", () -> new BlockItem(COPPER_PINE_SAPLING.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//stellar_stone & glaucophanite
	public static final RegistryObject<Block> STELLAR_STONE = BLOCKS.register("stellar_stone", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)));
	public static final RegistryObject<Block> STELLAR_STONE_BRICKS = BLOCKS.register("stellar_stone_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE_BRICKS).hardnessAndResistance(0.5F, 10.0F).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> MOSSY_STELLAR_STONE = BLOCKS.register("mossy_stellar_stone", () -> new Block(AbstractBlock.Properties.from(STELLAR_STONE.get())));
	public static final RegistryObject<Block> GLAUCOPHANITE = BLOCKS.register("glaucophanite",() -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> POLISHED_GLAUCOPHANITE = BLOCKS.register("polished_glaucophanite",() -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Item> STELLAR_STONE_ITEM = ITEMS.register("stellar_stone", () -> new BlockItem(STELLAR_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> MOSSY_STELLAR_STONE_ITEM = ITEMS.register("mossy_stellar_stone", () -> new BlockItem(MOSSY_STELLAR_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STELLAR_STONE_BRICKS_ITEM = ITEMS.register("stellar_stone_bricks", () -> new BlockItem(STELLAR_STONE_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GLAUCOPHANITE_ITEM = ITEMS.register("glaucophanite", () -> new BlockItem(GLAUCOPHANITE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> POLISHED_GLAUCOPHANITE_ITEM = ITEMS.register("polished_glaucophanite", () -> new BlockItem(POLISHED_GLAUCOPHANITE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//grass & dirt
	public static final RegistryObject<Block> STELLAR_GRASS_BLOCK = BLOCKS.register("stellar_grass_block", () -> new StellarGrassBlock(AbstractBlock.Properties.from(Blocks.GRASS_BLOCK).harvestTool(ToolType.SHOVEL)));
	public static final RegistryObject<Block> CHISELED_STELLAR_GRASS_BLOCK = BLOCKS.register("chiseled_stellar_grass_block", () -> new StellarGrassBlock(AbstractBlock.Properties.from(STELLAR_GRASS_BLOCK.get()).harvestTool(ToolType.SHOVEL)));
	public static final RegistryObject<Block> STELLAR_DIRT = BLOCKS.register("stellar_dirt", () -> new Block(AbstractBlock.Properties.from(Blocks.DIRT)));
	public static final RegistryObject<Block> CHISELED_STELLAR_DIRT = BLOCKS.register("chiseled_stellar_dirt", () -> new Block(AbstractBlock.Properties.from(STELLAR_DIRT.get())));
	public static final RegistryObject<Item> STELLAR_GRASS_BLOCK_ITEM = ITEMS.register("stellar_grass_block", () -> new BlockItem(STELLAR_GRASS_BLOCK.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CHISELED_STELLAR_GRASS_BLOCK_ITEM = ITEMS.register("chiseled_stellar_grass_block", () -> new BlockItem(CHISELED_STELLAR_GRASS_BLOCK.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STELLAR_DIRT_ITEM = ITEMS.register("stellar_dirt", () -> new BlockItem(STELLAR_DIRT.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CHISELED_STELLAR_DIRT_ITEM = ITEMS.register("chiseled_stellar_dirt", () -> new BlockItem(CHISELED_STELLAR_DIRT.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//slippery sand
	public static final RegistryObject<Block> SLIPPERY_SAND = BLOCKS.register("slippery_sand", () -> new Block(AbstractBlock.Properties.from(Blocks.SAND).harvestTool(ToolType.SHOVEL).slipperiness(1.03F)));
	public static final RegistryObject<Block> SLIPPERY_SAND_GLASS = BLOCKS.register("slippery_sand_glass", () -> new GlassBlock(AbstractBlock.Properties.from(Blocks.GLASS).slipperiness(1.1F).setLightLevel((state) -> 11).setAllowsSpawn((state, reader, pos, entity) -> false).setOpaque((state, reader, pos) -> false).setSuffocates((state, reader, pos) -> false).setBlocksVision((state, reader, pos) -> false)));
	public static final RegistryObject<Block> SLIPPERY_SAND_STONE = BLOCKS.register("slippery_sand_stone", () -> new Block(AbstractBlock.Properties.from(Blocks.SANDSTONE).harvestTool(ToolType.PICKAXE).slipperiness(1.01F)));
	public static final RegistryObject<Block> SLIPPERY_SAND_STONE_BRICKS = BLOCKS.register("slippery_sand_stone_bricks", () -> new Block(AbstractBlock.Properties.from(SLIPPERY_SAND_STONE.get()).harvestTool(ToolType.PICKAXE).slipperiness(1.005F)));
	public static final RegistryObject<Block> CUT_SLIPPERY_SAND_STONE = BLOCKS.register("cut_slippery_sand_stone", () -> new Block(AbstractBlock.Properties.from(SLIPPERY_SAND_STONE.get()).harvestTool(ToolType.PICKAXE).slipperiness(1.005F)));
	public static final RegistryObject<Item> SLIPPERY_SAND_ITEM = ITEMS.register("slippery_sand", () -> new BlockItem(SLIPPERY_SAND.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> SLIPPERY_SAND_GLASS_ITEM = ITEMS.register("slippery_sand_glass", () -> new BlockItem(SLIPPERY_SAND_GLASS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_ITEM = ITEMS.register("slippery_sand_stone", () -> new BlockItem(SLIPPERY_SAND_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_BRICKS_ITEM = ITEMS.register("slippery_sand_stone_bricks", () -> new BlockItem(SLIPPERY_SAND_STONE_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CUT_SLIPPERY_SAND_STONE_ITEM = ITEMS.register("cut_slippery_sand_stone", () -> new BlockItem(CUT_SLIPPERY_SAND_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//solid_ethers
	public static final RegistryObject<Block> WHITE_SOLID_ETHER = BLOCKS.register("white_solid_ether",	() -> new SolidEtherBlock(AbstractBlock.Properties.create(Material.ICE).hardnessAndResistance(0.2F).sound(SoundType.CLOTH).harvestTool(ToolType.HOE).notSolid()));
	public static final RegistryObject<Block> SLIPPERY_SAND_SOLID_ETHER = BLOCKS.register("slippery_sand_solid_ether",	() -> new SlipperySandSolidEtherBlock(AbstractBlock.Properties.create(Material.ICE).hardnessAndResistance(0.4F).sound(SoundType.SAND).harvestTool(ToolType.SHOVEL).slipperiness(1.03F).notSolid()));
	public static final RegistryObject<Block> BLUE_SOLID_ETHER = BLOCKS.register("blue_solid_ether",	() -> new BlueSolidEtherBlock(AbstractBlock.Properties.from(WHITE_SOLID_ETHER.get())));
	public static final RegistryObject<Block> GOLDEN_SOLID_ETHER = BLOCKS.register("golden_solid_ether",	() -> new GoldenSolidEtherBlock(AbstractBlock.Properties.from(WHITE_SOLID_ETHER.get())));
	public static final RegistryObject<Block> GREEN_SOLID_ETHER = BLOCKS.register("green_solid_ether",	() -> new GreenSolidEtherBlock(AbstractBlock.Properties.from(WHITE_SOLID_ETHER.get())));
	
	public static final RegistryObject<Item> WHITE_SOLID_ETHER_ITEM = ITEMS.register("white_solid_ether", () -> new BlockItem(WHITE_SOLID_ETHER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> SLIPPERY_SAND_SOLID_ETHER_ITEM = ITEMS.register("slippery_sand_solid_ether", () -> new BlockItem(SLIPPERY_SAND_SOLID_ETHER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> BLUE_SOLID_ETHER_ITEM = ITEMS.register("blue_solid_ether", () -> new BlockItem(BLUE_SOLID_ETHER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLDEN_SOLID_ETHER_ITEM = ITEMS.register("golden_solid_ether", () -> new BlockItem(GOLDEN_SOLID_ETHER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GREEN_SOLID_ETHER_ITEM = ITEMS.register("green_solid_ether", () -> new BlockItem(GREEN_SOLID_ETHER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));

	//dungeons blocks
	public static final RegistryObject<Block> MUD_BRICKS = BLOCKS.register("mud_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(0.5F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> CRACKED_MUD_BRICKS = BLOCKS.register("cracked_mud_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(0.5F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> CHISELED_MUD_BRICKS = BLOCKS.register("chiseled_mud_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(0.5F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> LIGHT_MUD_BRICKS = BLOCKS.register("light_mud_bricks", () -> new Block(AbstractBlock.Properties.from(MUD_BRICKS.get()).setLightLevel((state) -> 11)));
	public static final RegistryObject<Block> CRACKED_LIGHT_MUD_BRICKS = BLOCKS.register("cracked_light_mud_bricks", () -> new Block(AbstractBlock.Properties.from(MUD_BRICKS.get())));
	public static final RegistryObject<Block> LUNATIC_STONE = BLOCKS.register("lunatic_stone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(0.5F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> CRACKED_LUNATIC_STONE = BLOCKS.register("cracked_lunatic_stone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(0.5F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> CHISELED_LUNATIC_STONE = BLOCKS.register("chiseled_lunatic_stone", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(0.5F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> LIGHT_LUNATIC_STONE = BLOCKS.register("light_lunatic_stone", () -> new Block(AbstractBlock.Properties.from(LUNATIC_STONE.get()).setLightLevel((state) -> 11)));
	public static final RegistryObject<Block> CRACKED_LIGHT_LUNATIC_STONE = BLOCKS.register("cracked_light_lunatic_stone", () -> new Block(AbstractBlock.Properties.from(LUNATIC_STONE.get())));
	public static final RegistryObject<Block> GOLDEN_NETHER_BRICKS  = BLOCKS.register("golden_nether_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(0.5F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> CRACKED_GOLDEN_NETHER_BRICKS  = BLOCKS.register("cracked_golden_nether_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(0.5F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> CHISELED_GOLDEN_NETHER_BRICKS  = BLOCKS.register("chiseled_golden_nether_bricks", () -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(0.5F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> LIGHT_GOLDEN_NETHER_BRICKS = BLOCKS.register("light_golden_nether_bricks", () -> new Block(AbstractBlock.Properties.from(GOLDEN_NETHER_BRICKS.get()).setLightLevel((state) -> 11)));
	public static final RegistryObject<Block> CRACKED_LIGHT_GOLDEN_NETHER_BRICKS = BLOCKS.register("cracked_light_golden_nether_bricks", () -> new Block(AbstractBlock.Properties.from(GOLDEN_NETHER_BRICKS.get())));
	public static final RegistryObject<RotatedPillarBlock> LUNATIC_PILLAR = BLOCKS.register("lunatic_pillar", () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(0.5F).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<RotatedPillarBlock> LUNATIC_PILLAR_TOP = BLOCKS.register("lunatic_pillar_top", () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(0.5F).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> VOLUCITE_STONE = BLOCKS.register("volucite_stone", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5f).sound(SoundType.STONE)));
	public static final RegistryObject<Block> CRACKED_VOLUCITE_STONE = BLOCKS.register("cracked_volucite_stone", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5f).sound(SoundType.STONE)));
	public static final RegistryObject<Block> CHISELED_VOLUCITE_STONE = BLOCKS.register("chiseled_volucite_stone", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5f).sound(SoundType.STONE)));
	public static final RegistryObject<Block> LIGHT_VOLUCITE_STONE = BLOCKS.register("light_volucite_stone", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5f).sound(SoundType.STONE).setLightLevel((state) -> 11)));
	public static final RegistryObject<Block> CRACKED_LIGHT_VOLUCITE_STONE = BLOCKS.register("cracked_light_volucite_stone", () -> new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.5f).sound(SoundType.STONE)));

	public static final RegistryObject<Item> MUD_BRICKS_ITEM = ITEMS.register("mud_bricks", () -> new BlockItem(MUD_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_MUD_BRICKS_ITEM = ITEMS.register("cracked_mud_bricks", () -> new BlockItem(CRACKED_MUD_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CHISELED_MUD_BRICKS_ITEM = ITEMS.register("chiseled_mud_bricks", () -> new BlockItem(CHISELED_MUD_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> LIGHT_MUD_BRICKS_ITEM = ITEMS.register("light_mud_bricks", () -> new BlockItem(LIGHT_MUD_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_LIGHT_MUD_BRICKS_ITEM = ITEMS.register("cracked_light_mud_bricks", () -> new BlockItem(CRACKED_LIGHT_MUD_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> LUNATIC_STONE_ITEM = ITEMS.register("lunatic_stone", () -> new BlockItem(LUNATIC_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_LUNATIC_STONE_ITEM = ITEMS.register("cracked_lunatic_stone", () -> new BlockItem(CRACKED_LUNATIC_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CHISELED_LUNATIC_STONE_ITEM = ITEMS.register("chiseled_lunatic_stone", () -> new BlockItem(CHISELED_LUNATIC_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> LIGHT_LUNATIC_STONE_ITEM = ITEMS.register("light_lunatic_stone", () -> new BlockItem(LIGHT_LUNATIC_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_LIGHT_LUNATIC_STONE_ITEM = ITEMS.register("cracked_light_lunatic_stone", () -> new BlockItem(CRACKED_LIGHT_LUNATIC_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLDEN_NETHER_BRICKS_ITEM = ITEMS.register("golden_nether_bricks", () -> new BlockItem(GOLDEN_NETHER_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_GOLDEN_NETHER_BRICKS_ITEM = ITEMS.register("cracked_golden_nether_bricks", () -> new BlockItem(CRACKED_GOLDEN_NETHER_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CHISELED_GOLDEN_NETHER_BRICKS_ITEM = ITEMS.register("chiseled_golden_nether_bricks", () -> new BlockItem(CHISELED_GOLDEN_NETHER_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> LIGHT_GOLDEN_NETHER_BRICKS_ITEM = ITEMS.register("light_golden_nether_bricks", () -> new BlockItem(LIGHT_GOLDEN_NETHER_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_LIGHT_GOLDEN_NETHER_BRICKS_ITEM = ITEMS.register("cracked_light_golden_nether_bricks", () -> new BlockItem(CRACKED_LIGHT_GOLDEN_NETHER_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> LUNATIC_PILLAR_ITEM = ITEMS.register("lunatic_pillar", () -> new BlockItem(LUNATIC_PILLAR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> LUNATIC_PILLAR_TOP_ITEM = ITEMS.register("lunatic_pillar_top", () -> new BlockItem(LUNATIC_PILLAR_TOP.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> VOLUCITE_STONE_ITEM = ITEMS.register("volucite_stone", () -> new BlockItem(VOLUCITE_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_VOLUCITE_STONE_ITEM = ITEMS.register("cracked_volucite_stone", () -> new BlockItem(CRACKED_VOLUCITE_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CHISELED_VOLUCITE_STONE_ITEM = ITEMS.register("chiseled_volucite_stone", () -> new BlockItem(CHISELED_VOLUCITE_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> LIGHT_VOLUCITE_STONE_ITEM = ITEMS.register("light_volucite_stone", () -> new BlockItem(LIGHT_VOLUCITE_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_LIGHT_VOLUCITE_STONE_ITEM = ITEMS.register("cracked_light_volucite_stone", () -> new BlockItem(CRACKED_LIGHT_VOLUCITE_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//dongeons slabs, stairs & walls
	public static final RegistryObject<SlabBlock> MUD_BRICKS_SLAB = BLOCKS.register("mud_bricks_slab", () -> new SlabBlock(AbstractBlock.Properties.from(MUD_BRICKS.get())));
	public static final RegistryObject<StairsBlock> MUD_BRICKS_STAIRS = BLOCKS.register("mud_bricks_stairs", () -> new StairsBlock(() -> MUD_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> MUD_BRICKS_WALL = BLOCKS.register("mud_bricks_wall", () -> new WallBlock(AbstractBlock.Properties.from(MUD_BRICKS.get())));
	public static final RegistryObject<SlabBlock> CRACKED_MUD_BRICKS_SLAB = BLOCKS.register("cracked_mud_bricks_slab", () -> new SlabBlock(AbstractBlock.Properties.from(CRACKED_MUD_BRICKS.get())));
	public static final RegistryObject<StairsBlock> CRACKED_MUD_BRICKS_STAIRS = BLOCKS.register("cracked_mud_bricks_stairs", () -> new StairsBlock(() -> CRACKED_MUD_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> CRACKED_MUD_BRICKS_WALL = BLOCKS.register("cracked_mud_bricks_wall", () -> new WallBlock(AbstractBlock.Properties.from(CRACKED_MUD_BRICKS.get())));
	public static final RegistryObject<SlabBlock> VOLUCITE_STONE_SLAB = BLOCKS.register("volucite_stone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<StairsBlock> VOLUCITE_STONE_STAIRS = BLOCKS.register("volucite_stone_stairs", () -> new StairsBlock(() -> VOLUCITE_STONE.get().getDefaultState(), AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> VOLUCITE_STONE_WALL = BLOCKS.register("volucite_stone_wall", () -> new WallBlock(AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<SlabBlock> CRACKED_VOLUCITE_STONE_SLAB = BLOCKS.register("cracked_volucite_stone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(CRACKED_VOLUCITE_STONE.get())));
	public static final RegistryObject<StairsBlock> CRACKED_VOLUCITE_STONE_STAIRS = BLOCKS.register("cracked_volucite_stone_stairs", () -> new StairsBlock(() -> CRACKED_VOLUCITE_STONE.get().getDefaultState(), AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> CRACKED_VOLUCITE_STONE_WALL = BLOCKS.register("cracked_volucite_stone_wall", () -> new WallBlock(AbstractBlock.Properties.from(CRACKED_VOLUCITE_STONE.get())));
	public static final RegistryObject<SlabBlock> LUNATIC_STONE_SLAB = BLOCKS.register("lunatic_stone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(LUNATIC_STONE.get())));
	public static final RegistryObject<StairsBlock> LUNATIC_STONE_STAIRS = BLOCKS.register("lunatic_stone_stairs", () -> new StairsBlock(() -> LUNATIC_STONE.get().getDefaultState(), AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> LUNATIC_STONE_WALL = BLOCKS.register("lunatic_stone_wall", () -> new WallBlock(AbstractBlock.Properties.from(LUNATIC_STONE.get())));
	public static final RegistryObject<SlabBlock> CRACKED_LUNATIC_STONE_SLAB = BLOCKS.register("cracked_lunatic_stone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(CRACKED_LUNATIC_STONE.get())));
	public static final RegistryObject<StairsBlock> CRACKED_LUNATIC_STONE_STAIRS = BLOCKS.register("cracked_lunatic_stone_stairs", () -> new StairsBlock(() -> CRACKED_LUNATIC_STONE.get().getDefaultState(), AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> CRACKED_LUNATIC_STONE_WALL = BLOCKS.register("cracked_lunatic_stone_wall", () -> new WallBlock(AbstractBlock.Properties.from(CRACKED_LUNATIC_STONE.get())));
	public static final RegistryObject<SlabBlock> GOLDEN_NETHER_BRICKS_SLAB = BLOCKS.register("golden_nether_bricks_slab", () -> new SlabBlock(AbstractBlock.Properties.from(GOLDEN_NETHER_BRICKS.get())));
	public static final RegistryObject<StairsBlock> GOLDEN_NETHER_BRICKS_STAIRS = BLOCKS.register("golden_nether_bricks_stairs", () -> new StairsBlock(() -> GOLDEN_NETHER_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> GOLDEN_NETHER_BRICKS_WALL = BLOCKS.register("golden_nether_bricks_wall", () -> new WallBlock(AbstractBlock.Properties.from(GOLDEN_NETHER_BRICKS.get())));
	public static final RegistryObject<SlabBlock> CRACKED_GOLDEN_NETHER_BRICKS_SLAB = BLOCKS.register("cracked_golden_nether_bricks_slab", () -> new SlabBlock(AbstractBlock.Properties.from(CRACKED_GOLDEN_NETHER_BRICKS.get())));
	public static final RegistryObject<StairsBlock> CRACKED_GOLDEN_NETHER_BRICKS_STAIRS = BLOCKS.register("cracked_golden_nether_bricks_stairs", () -> new StairsBlock(() -> CRACKED_GOLDEN_NETHER_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> CRACKED_GOLDEN_NETHER_BRICKS_WALL = BLOCKS.register("cracked_golden_nether_bricks_wall", () -> new WallBlock(AbstractBlock.Properties.from(CRACKED_GOLDEN_NETHER_BRICKS.get())));

	public static final RegistryObject<Item> MUD_BRICKS_SLAB_ITEM = ITEMS.register("mud_bricks_slab", () -> new BlockItem(MUD_BRICKS_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> MUD_BRICKS_STAIRS_ITEM = ITEMS.register("mud_bricks_stairs", () -> new BlockItem(MUD_BRICKS_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> MUD_BRICKS_WALL_ITEM = ITEMS.register("mud_bricks_wall", () -> new BlockItem(MUD_BRICKS_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_MUD_BRICKS_SLAB_ITEM = ITEMS.register("cracked_mud_bricks_slab", () -> new BlockItem(CRACKED_MUD_BRICKS_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_MUD_BRICKS_STAIRS_ITEM = ITEMS.register("cracked_mud_bricks_stairs", () -> new BlockItem(CRACKED_MUD_BRICKS_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_MUD_BRICKS_WALL_ITEM = ITEMS.register("cracked_mud_bricks_wall", () -> new BlockItem(CRACKED_MUD_BRICKS_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> VOLUCITE_STONE_SLAB_ITEM = ITEMS.register("volucite_stone_slab", () -> new BlockItem(VOLUCITE_STONE_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> VOLUCITE_STONE_STAIRS_ITEM = ITEMS.register("volucite_stone_stairs", () -> new BlockItem(VOLUCITE_STONE_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> VOLUCITE_STONE_WALL_ITEM = ITEMS.register("volucite_stone_wall", () -> new BlockItem(VOLUCITE_STONE_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_VOLUCITE_STONE_SLAB_ITEM = ITEMS.register("cracked_volucite_stone_slab", () -> new BlockItem(CRACKED_VOLUCITE_STONE_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_VOLUCITE_STONE_STAIRS_ITEM = ITEMS.register("cracked_volucite_stone_stairs", () -> new BlockItem(CRACKED_VOLUCITE_STONE_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_VOLUCITE_STONE_WALL_ITEM = ITEMS.register("cracked_volucite_stone_wall", () -> new BlockItem(CRACKED_VOLUCITE_STONE_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> LUNATIC_STONE_SLAB_ITEM = ITEMS.register("lunatic_stone_slab", () -> new BlockItem(LUNATIC_STONE_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> LUNATIC_STONE_STAIRS_ITEM = ITEMS.register("lunatic_stone_stairs", () -> new BlockItem(LUNATIC_STONE_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> LUNATIC_STONE_WALL_ITEM = ITEMS.register("lunatic_stone_wall", () -> new BlockItem(LUNATIC_STONE_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_LUNATIC_STONE_SLAB_ITEM = ITEMS.register("cracked_lunatic_stone_slab", () -> new BlockItem(CRACKED_LUNATIC_STONE_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_LUNATIC_STONE_STAIRS_ITEM = ITEMS.register("cracked_lunatic_stone_stairs", () -> new BlockItem(CRACKED_LUNATIC_STONE_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_LUNATIC_STONE_WALL_ITEM = ITEMS.register("cracked_lunatic_stone_wall", () -> new BlockItem(CRACKED_LUNATIC_STONE_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLDEN_NETHER_BRICKS_SLAB_ITEM = ITEMS.register("golden_nether_bricks_slab", () -> new BlockItem(GOLDEN_NETHER_BRICKS_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLDEN_NETHER_BRICKS_STAIRS_ITEM = ITEMS.register("golden_nether_bricks_stairs", () -> new BlockItem(GOLDEN_NETHER_BRICKS_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLDEN_NETHER_BRICKS_WALL_ITEM = ITEMS.register("golden_nether_bricks_wall", () -> new BlockItem(GOLDEN_NETHER_BRICKS_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_GOLDEN_NETHER_BRICKS_SLAB_ITEM = ITEMS.register("cracked_golden_nether_bricks_slab", () -> new BlockItem(CRACKED_GOLDEN_NETHER_BRICKS_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_GOLDEN_NETHER_BRICKS_STAIRS_ITEM = ITEMS.register("cracked_golden_nether_bricks_stairs", () -> new BlockItem(CRACKED_GOLDEN_NETHER_BRICKS_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_GOLDEN_NETHER_BRICKS_WALL_ITEM = ITEMS.register("cracked_golden_nether_bricks_wall", () -> new BlockItem(CRACKED_GOLDEN_NETHER_BRICKS_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//Dongeon trapped blocks
	public static final RegistryObject<Block> TRAPPED_MUD_BRICKS = BLOCKS.register("trapped_mud_bricks", () -> new AerialHellTrappedBlock(AbstractBlock.Properties.from(Blocks.BEDROCK)));
	public static final RegistryObject<Block> TRAPPED_LIGHT_MUD_BRICKS = BLOCKS.register("trapped_light_mud_bricks", () -> new AerialHellTrappedBlock(AbstractBlock.Properties.from(Blocks.BEDROCK).setLightLevel((state) -> 11)));
	public static final RegistryObject<Block> TRAPPED_LUNATIC_STONE = BLOCKS.register("trapped_lunatic_stone", () -> new AerialHellTrappedBlock(AbstractBlock.Properties.from(Blocks.BEDROCK)));
	public static final RegistryObject<Block> TRAPPED_LIGHT_LUNATIC_STONE = BLOCKS.register("trapped_light_lunatic_stone", () -> new AerialHellTrappedBlock(AbstractBlock.Properties.from(Blocks.BEDROCK).setLightLevel((state) -> 11)));
	public static final RegistryObject<Block> TRAPPED_GOLDEN_NETHER_BRICKS = BLOCKS.register("trapped_golden_nether_bricks", () -> new AerialHellTrappedBlock(AbstractBlock.Properties.from(Blocks.BEDROCK)));
	public static final RegistryObject<Block> TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS = BLOCKS.register("trapped_light_golden_nether_bricks", () -> new AerialHellTrappedBlock(AbstractBlock.Properties.from(Blocks.BEDROCK).setLightLevel((state) -> 11)));
	
	public static final RegistryObject<Item> TRAPPED_MUD_BRICKS_ITEM = ITEMS.register("trapped_mud_bricks", () -> new BlockItem(TRAPPED_MUD_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> TRAPPED_LIGHT_MUD_BRICKS_ITEM = ITEMS.register("trapped_light_mud_bricks", () -> new BlockItem(TRAPPED_LIGHT_MUD_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> TRAPPED_LUNATIC_STONE_ITEM = ITEMS.register("trapped_lunatic_stone", () -> new BlockItem(TRAPPED_LUNATIC_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> TRAPPED_LIGHT_LUNATIC_STONE_ITEM = ITEMS.register("trapped_light_lunatic_stone", () -> new BlockItem(TRAPPED_LIGHT_LUNATIC_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> TRAPPED_GOLDEN_NETHER_BRICKS_ITEM = ITEMS.register("trapped_golden_nether_bricks", () -> new BlockItem(TRAPPED_GOLDEN_NETHER_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS_ITEM = ITEMS.register("trapped_light_golden_nether_bricks", () -> new BlockItem(TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));

	//Bookshelf
	public static final RegistryObject<Block> MUD_BOOKSHELF = BLOCKS.register("mud_bookshelf", () -> new Block(AbstractBlock.Properties.from(MUD_BRICKS.get())));
	public static final RegistryObject<Block> LUNATIC_BOOKSHELF = BLOCKS.register("lunatic_bookshelf", () -> new Block(AbstractBlock.Properties.from(LUNATIC_STONE.get())));
	public static final RegistryObject<Block> GOLDEN_NETHER_BOOKSHELF = BLOCKS.register("golden_nether_bookshelf", () -> new Block(AbstractBlock.Properties.from(GOLDEN_NETHER_BRICKS.get())));
	public static final RegistryObject<Block> VOLUCITE_BOOKSHELF = BLOCKS.register("volucite_bookshelf", () -> new Block(AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<Item> MUD_BOOKSHELF_ITEM = ITEMS.register("mud_bookshelf", () -> new BlockItem(MUD_BOOKSHELF.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> LUNATIC_BOOKSHELF_ITEM = ITEMS.register("lunatic_bookshelf", () -> new BlockItem(LUNATIC_BOOKSHELF.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLDEN_NETHER_BOOKSHELF_ITEM = ITEMS.register("golden_nether_bookshelf", () -> new BlockItem(GOLDEN_NETHER_BOOKSHELF.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> VOLUCITE_BOOKSHELF_ITEM = ITEMS.register("volucite_bookshelf", () -> new BlockItem(VOLUCITE_BOOKSHELF.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));

	//ores
	public static final RegistryObject<Block> FLUORITE_ORE = BLOCKS.register("fluorite_ore",() -> new AerialHellOreBlock(0, 2, AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(0).setRequiresTool()));
	public static final RegistryObject<Block> MAGMATIC_GEL_ORE = BLOCKS.register("magmatic_gel_ore",() -> new AerialHellOreBlock(0, 2, AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F).sound(SoundType.STONE).setLightLevel(s -> 4).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> RUBY_ORE = BLOCKS.register("ruby_ore",() -> new AerialHellOreBlock(3, 5, AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> AZURITE_ORE = BLOCKS.register("azurite_ore",() -> new AerialHellOreBlock(0, 0, AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> VOLUCITE_ORE = BLOCKS.register("volucite_ore",() -> new AerialHellOreBlock(0, 0, AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(5.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool()));
	public static final RegistryObject<Item> FLUORITE_ORE_ITEM = ITEMS.register("fluorite_ore", () -> new BlockItem(FLUORITE_ORE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> MAGMATIC_GEL_ORE_ITEM = ITEMS.register("magmatic_gel_ore", () -> new BlockItem(MAGMATIC_GEL_ORE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> RUBY_ORE_ITEM = ITEMS.register("ruby_ore", () -> new BlockItem(RUBY_ORE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> AZURITE_ORE_ITEM = ITEMS.register("azurite_ore", () -> new BlockItem(AZURITE_ORE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> VOLUCITE_ORE_ITEM = ITEMS.register("volucite_ore", () -> new BlockItem(VOLUCITE_ORE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	public static final RegistryObject<Block> FLUORITE_BLOCK = BLOCKS.register("fluorite_block", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> MAGMATIC_GEL_BLOCK = BLOCKS.register("magmatic_gel_block", () -> new MagmaticGelBlock(AbstractBlock.Properties.create(Material.ICE).hardnessAndResistance(1.0F, 1600.0F).tickRandomly().sound(SoundType.GLASS).notSolid().harvestTool(ToolType.PICKAXE).harvestLevel(3).setRequiresTool().setBlocksVision((state, reader, pos) -> false)));
	public static final RegistryObject<Block> RUBY_BLOCK = BLOCKS.register("ruby_block", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> AZURITE_BLOCK = BLOCKS.register("azurite_block", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> VOLUCITE_BLOCK = BLOCKS.register("volucite_block", () -> new Block(AbstractBlock.Properties.from(Blocks.IRON_BLOCK).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool()));
	public static final RegistryObject<Item> FLUORITE_BLOCK_ITEM = ITEMS.register("fluorite_block", () -> new BlockItem(FLUORITE_BLOCK.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> MAGMATIC_GEL_BLOCK_ITEM = ITEMS.register("magmatic_gel_block", () -> new BlockItem(MAGMATIC_GEL_BLOCK.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> RUBY_BLOCK_ITEM = ITEMS.register("ruby_block", () -> new BlockItem(RUBY_BLOCK.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> AZURITE_BLOCK_ITEM = ITEMS.register("azurite_block", () -> new BlockItem(AZURITE_BLOCK.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> VOLUCITE_BLOCK_ITEM = ITEMS.register("volucite_block", () -> new BlockItem(VOLUCITE_BLOCK.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	public static final RegistryObject<Item> FLUORITE = ITEMS.register("fluorite", () -> new Item(new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> MAGMATIC_GEL = ITEMS.register("magmatic_gel",() -> new Item(new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> RUBY = ITEMS.register("ruby", () -> new Item(new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> AZURITE_CRYSTAL = ITEMS.register("azurite_crystal", () -> new Item(new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> VOLUCITE_VIBRANT = ITEMS.register("volucite_vibrant", () -> new Item(new Item.Properties().rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//cactus
	public static final RegistryObject<SkyCactusBlock> SKY_CACTUS = BLOCKS.register("sky_cactus", () -> new SkyCactusBlock(AbstractBlock.Properties.create(Material.CACTUS, MaterialColor.CYAN).hardnessAndResistance(0.4F).sound(SoundType.CLOTH).harvestTool(ToolType.AXE).tickRandomly()));
	public static final RegistryObject<Item> SKY_CACTUS_ITEM = ITEMS.register("sky_cactus", () -> new BlockItem(SKY_CACTUS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Block> SKY_CACTUS_FIBER_PLANKS = BLOCKS.register("sky_cactus_fiber_planks", () -> new Block(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_PLANKS_ITEM = ITEMS.register("sky_cactus_fiber_planks", () -> new BlockItem(SKY_CACTUS_FIBER_PLANKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<SkyCactusBlock> VIBRANT_SKY_CACTUS = BLOCKS.register("vibrant_sky_cactus", () -> new SkyCactusBlock(AbstractBlock.Properties.create(Material.CACTUS, MaterialColor.BLUE).hardnessAndResistance(0.4F).sound(SoundType.CLOTH).harvestTool(ToolType.AXE).tickRandomly().setLightLevel(s -> 15).notSolid()));
	public static final RegistryObject<Item> VIBRANT_SKY_CACTUS_ITEM = ITEMS.register("vibrant_sky_cactus", () -> new BlockItem(VIBRANT_SKY_CACTUS.get(), new Item.Properties().rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Block> VIBRANT_SKY_CACTUS_FIBER_LANTERN = BLOCKS.register("vibrant_sky_cactus_fiber_lantern", () -> new Block(AbstractBlock.Properties.create(Material.GLASS, MaterialColor.QUARTZ).hardnessAndResistance(0.5F).sound(SoundType.GLASS).notSolid().setLightLevel(s -> 15)));
	public static final RegistryObject<Item> VIBRANT_SKY_CACTUS_FIBER_LANTERN_ITEM = ITEMS.register("vibrant_sky_cactus_fiber_lantern", () -> new BlockItem(VIBRANT_SKY_CACTUS_FIBER_LANTERN.get(), new Item.Properties().rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//bushes
	public static final RegistryObject<Block> AERIAL_BERRY_BUSH = BLOCKS.register("aerial_berry_bush", () -> new AerialBerryBushBlock(AbstractBlock.Properties.from(Blocks.SWEET_BERRY_BUSH)));
	public static final RegistryObject<Item> AERIAL_BERRY_SEEDS = ITEMS.register("aerial_berry_seeds",() -> new BlockNamedItem(AERIAL_BERRY_BUSH.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Block> VIBRANT_AERIAL_BERRY_BUSH = BLOCKS.register("vibrant_aerial_berry_bush", () -> new VibrantAerialBerryBushBlock(AbstractBlock.Properties.from(Blocks.SWEET_BERRY_BUSH)));
	public static final RegistryObject<Item> VIBRANT_AERIAL_BERRY_SEEDS = ITEMS.register("vibrant_aerial_berry_seeds",() -> new BlockNamedItem(VIBRANT_AERIAL_BERRY_BUSH.get(), new Item.Properties().rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//grass
	public static final RegistryObject<Block> STELLAR_GRASS = BLOCKS.register("stellar_grass", () -> new AerialHellTallGrassBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
	public static final RegistryObject<Block> STELLAR_FERN = BLOCKS.register("stellar_fern", () -> new AerialHellTallGrassBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
	public static final RegistryObject<Block> STELLAR_TALL_GRASS = BLOCKS.register("stellar_tall_grass", () -> new DoublePlantBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
	public static final RegistryObject<Block> STELLAR_TALL_FERN = BLOCKS.register("stellar_tall_fern", () -> new DoublePlantBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
	public static final RegistryObject<Block> STELLAR_DEAD_BUSH = BLOCKS.register("stellar_dead_bush", () -> new AerialHellDeadBushBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS, MaterialColor.WOOD).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
	public static final RegistryObject<Item> STELLAR_GRASS_ITEM = ITEMS.register("stellar_grass", () -> new BlockItem(STELLAR_GRASS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STELLAR_FERN_ITEM = ITEMS.register("stellar_fern", () -> new BlockItem(STELLAR_FERN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STELLAR_TALL_GRASS_ITEM = ITEMS.register("stellar_tall_grass", () -> new BlockItem(STELLAR_TALL_GRASS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STELLAR_TALL_FERN_ITEM = ITEMS.register("stellar_tall_fern", () -> new BlockItem(STELLAR_TALL_FERN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STELLAR_DEAD_BUSH_ITEM = ITEMS.register("stellar_dead_bush", () -> new BlockItem(STELLAR_DEAD_BUSH.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//flowers
	public static final RegistryObject<Block> BLUE_FLOWER = BLOCKS.register("blue_flower", () -> new FlowerBlock(Effects.BLINDNESS, 4, AbstractBlock.Properties.from(Blocks.DANDELION)));
	public static final RegistryObject<Item> BLUE_FLOWER_ITEM = ITEMS.register("blue_flower", () -> new BlockItem(BLUE_FLOWER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	public static final RegistryObject<Block> BLACK_ROSE = BLOCKS.register("black_rose", () -> new FlowerBlock(Effects.SLOW_FALLING, 12, AbstractBlock.Properties.from(Blocks.DANDELION)));
	public static final RegistryObject<Item> BLACK_ROSE_ITEM = ITEMS.register("black_rose", () -> new BlockItem(BLACK_ROSE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//potted things
	public static final RegistryObject<FlowerPotBlock> POTTED_BLUE_FLOWER = BLOCKS.register("potted_blue_flower", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLUE_FLOWER, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_BLACK_ROSE = BLOCKS.register("potted_black_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLACK_ROSE, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_STELLAR_FERN = BLOCKS.register("potted_stellar_fern", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, STELLAR_FERN, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_STELLAR_DEAD_BUSH = BLOCKS.register("potted_stellar_dead_bush", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, STELLAR_DEAD_BUSH, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_SKY_CACTUS = BLOCKS.register("potted_sky_cactus", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SKY_CACTUS, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_VIBRANT_SKY_CACTUS = BLOCKS.register("potted_vibrant_sky_cactus", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, VIBRANT_SKY_CACTUS, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_AERIAL_TREE_SAPLING = BLOCKS.register("potted_aerial_tree_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, AERIAL_TREE_SAPLING, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_GOLDEN_BEECH_SAPLING = BLOCKS.register("potted_golden_beech_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GOLDEN_BEECH_SAPLING, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_COPPER_PINE_SAPLING = BLOCKS.register("potted_copper_pine_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, COPPER_PINE_SAPLING, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	
	//with gui
	public static final RegistryObject<Block> VIBRATOR = BLOCKS.register("vibrator", () -> new VibratorBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(2.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Item> VIBRATOR_ITEM = ITEMS.register("vibrator", () -> new BlockItem(VIBRATOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	public static final RegistryObject<Block> FREEZER = BLOCKS.register("freezer",() -> new FreezerBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(2.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Item> FREEZER_ITEM = ITEMS.register("freezer", () -> new BlockItem(FREEZER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//chests
	public static final RegistryObject<ChestBlock> AERIAL_TREE_CHEST = BLOCKS.register("aerial_tree_chest", () -> new ChestBlock(AERIAL_TREE_MATERIAL,() -> AerialHellTileEntityTypes.CHEST.get()){public TileEntity createTileEntity(BlockState state,net.minecraft.world.IBlockReader world) {return AerialHellTileEntityTypes.CHEST.get().create();};});
	public static final RegistryObject<Item> AERIAL_TREE_CHEST_ITEM = ITEMS.register("aerial_tree_chest", () -> new BlockItem(AERIAL_TREE_CHEST.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).setISTER(() -> AerialHellChestItemTileEntityRenderer::new)));
	public static final RegistryObject<ChestBlock> GOLDEN_BEECH_CHEST = BLOCKS.register("golden_beech_chest", () -> new ChestBlock(AERIAL_TREE_MATERIAL,() -> AerialHellTileEntityTypes.CHEST.get()){public TileEntity createTileEntity(BlockState state,net.minecraft.world.IBlockReader world) {return AerialHellTileEntityTypes.CHEST.get().create();};});
	public static final RegistryObject<Item> GOLDEN_BEECH_ITEM = ITEMS.register("golden_beech_chest", () -> new BlockItem(GOLDEN_BEECH_CHEST.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).setISTER(() -> AerialHellChestItemTileEntityRenderer::new)));
	public static final RegistryObject<ChestBlock> COPPER_PINE_CHEST = BLOCKS.register("copper_pine_chest", () -> new ChestBlock(AERIAL_TREE_MATERIAL,() -> AerialHellTileEntityTypes.CHEST.get()){public TileEntity createTileEntity(BlockState state,net.minecraft.world.IBlockReader world) {return AerialHellTileEntityTypes.CHEST.get().create();};});
	public static final RegistryObject<Item> COPPER_PINE_ITEM = ITEMS.register("copper_pine_chest", () -> new BlockItem(COPPER_PINE_CHEST.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).setISTER(() -> AerialHellChestItemTileEntityRenderer::new)));
	public static final RegistryObject<ChestBlock> SKY_CACTUS_FIBER_CHEST = BLOCKS.register("sky_cactus_fiber_chest", () -> new ChestBlock(SKY_CACTUS_FIBER_MATERIAL,() -> AerialHellTileEntityTypes.CHEST.get()){public TileEntity createTileEntity(BlockState state,net.minecraft.world.IBlockReader world) {return AerialHellTileEntityTypes.CHEST.get().create();};});
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_CHEST_ITEM = ITEMS.register("sky_cactus_fiber_chest", () -> new BlockItem(SKY_CACTUS_FIBER_CHEST.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).setISTER(() -> AerialHellChestItemTileEntityRenderer::new)));
	public static final RegistryObject<ChestBlock> MUD_CHEST = BLOCKS.register("mud_chest", () -> new ChestBlock(MUD_CHEST_MATERIAL,() -> AerialHellTileEntityTypes.CHEST.get()){public TileEntity createTileEntity(BlockState state,net.minecraft.world.IBlockReader world) {return AerialHellTileEntityTypes.CHEST.get().create();};});
	public static final RegistryObject<Item> MUD_CHEST_ITEM = ITEMS.register("mud_chest", () -> new BlockItem(MUD_CHEST.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).setISTER(() -> AerialHellChestItemTileEntityRenderer::new)));
	public static final RegistryObject<ChestBlock> LUNATIC_CHEST = BLOCKS.register("lunatic_chest", () -> new ChestBlock(LUNATIC_CHEST_MATERIAL,() -> AerialHellTileEntityTypes.CHEST.get()){public TileEntity createTileEntity(BlockState state,net.minecraft.world.IBlockReader world) {return AerialHellTileEntityTypes.CHEST.get().create();};});
	public static final RegistryObject<Item> LUNATIC_CHEST_ITEM = ITEMS.register("lunatic_chest", () -> new BlockItem(LUNATIC_CHEST.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).setISTER(() -> AerialHellChestItemTileEntityRenderer::new)));
	public static final RegistryObject<ChestBlock> VOLUCITE_CHEST = BLOCKS.register("volucite_chest", () -> new ChestBlock(VOLUCITE_CHEST_MATERIAL,() -> AerialHellTileEntityTypes.CHEST.get()){public TileEntity createTileEntity(BlockState state,net.minecraft.world.IBlockReader world) {return AerialHellTileEntityTypes.CHEST.get().create();};});
	public static final RegistryObject<Item> VOLUCITE_CHEST_ITEM = ITEMS.register("volucite_chest", () -> new BlockItem(VOLUCITE_CHEST.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).setISTER(() -> AerialHellChestItemTileEntityRenderer::new)));
	public static final RegistryObject<ChestBlock> GOLDEN_NETHER_CHEST = BLOCKS.register("golden_nether_chest", () -> new ChestBlock(GOLDEN_NETHER_CHEST_MATERIAL,() -> AerialHellTileEntityTypes.CHEST.get()){public TileEntity createTileEntity(BlockState state,net.minecraft.world.IBlockReader world) {return AerialHellTileEntityTypes.CHEST.get().create();};});
	public static final RegistryObject<Item> GOLDEN_NETHER_CHEST_ITEM = ITEMS.register("golden_nether_chest", () -> new BlockItem(GOLDEN_NETHER_CHEST.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).setISTER(() -> AerialHellChestItemTileEntityRenderer::new)));
	
	//chest mimics
	public static final RegistryObject<Block> AERIAL_TREE_CHEST_MIMIC = BLOCKS.register("aerial_tree_chest_mimic", () -> new ChestMimicBlock(AbstractBlock.Properties.from(Blocks.CHEST).harvestTool(ToolType.AXE)));
	public static final RegistryObject<Block> GOLDEN_BEECH_CHEST_MIMIC = BLOCKS.register("golden_beech_chest_mimic", () -> new ChestMimicBlock(AbstractBlock.Properties.from(Blocks.CHEST).harvestTool(ToolType.AXE)));
	public static final RegistryObject<Block> COPPER_PINE_CHEST_MIMIC = BLOCKS.register("copper_pine_chest_mimic", () -> new ChestMimicBlock(AbstractBlock.Properties.from(Blocks.CHEST).harvestTool(ToolType.AXE)));
	public static final RegistryObject<Block> SKY_CACTUS_FIBER_CHEST_MIMIC = BLOCKS.register("sky_cactus_fiber_chest_mimic", () -> new ChestMimicBlock(AbstractBlock.Properties.from(Blocks.CHEST).harvestTool(ToolType.AXE)));
	
	public static final RegistryObject<Item> AERIAL_TREE_CHEST_MIMIC_ITEM = ITEMS.register("aerial_tree_chest_mimic", () -> new BlockItem(AERIAL_TREE_CHEST_MIMIC.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLDEN_BEECH_CHEST_MIMIC_ITEM = ITEMS.register("golden_beech_chest_mimic", () -> new BlockItem(GOLDEN_BEECH_CHEST_MIMIC.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> COPPER_PINE_CHEST_MIMIC_ITEM = ITEMS.register("copper_pine_chest_mimic", () -> new BlockItem(COPPER_PINE_CHEST_MIMIC.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_CHEST_MIMIC_ITEM = ITEMS.register("sky_cactus_fiber_chest_mimic", () -> new BlockItem(SKY_CACTUS_FIBER_CHEST_MIMIC.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//fences, bars or walls
	public static final RegistryObject<FenceBlock> AERIAL_TREE_FENCE = BLOCKS.register("aerial_tree_fence", () -> new FenceBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_FENCE_ITEM = ITEMS.register("aerial_tree_fence", () -> new BlockItem(AERIAL_TREE_FENCE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<FenceBlock> GOLDEN_BEECH_FENCE = BLOCKS.register("golden_beech_fence", () -> new FenceBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_FENCE_ITEM = ITEMS.register("golden_beech_fence", () -> new BlockItem(GOLDEN_BEECH_FENCE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<FenceBlock> COPPER_PINE_FENCE = BLOCKS.register("copper_pine_fence", () -> new FenceBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_FENCE_ITEM = ITEMS.register("copper_pine_fence", () -> new BlockItem(COPPER_PINE_FENCE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<FenceBlock> SKY_CACTUS_FIBER_FENCE = BLOCKS.register("sky_cactus_fiber_fence", () -> new FenceBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_FENCE_ITEM = ITEMS.register("sky_cactus_fiber_fence", () -> new BlockItem(SKY_CACTUS_FIBER_FENCE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<PaneBlock> RUBY_BARS = BLOCKS.register("ruby_bars", () -> new PaneBlock(RUBY_MATERIAL));
	public static final RegistryObject<Item> RUBY_BARS_ITEM = ITEMS.register("ruby_bars", () -> new BlockItem(RUBY_BARS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WallBlock> STELLAR_STONE_WALL = BLOCKS.register("stellar_stone_wall", () -> new WallBlock(AbstractBlock.Properties.from(STELLAR_STONE.get())));
	public static final RegistryObject<Item> STELLAR_STONE_WALL_ITEM = ITEMS.register("stellar_stone_wall", () -> new BlockItem(STELLAR_STONE_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WallBlock> STELLAR_STONE_BRICKS_WALL = BLOCKS.register("stellar_stone_bricks_wall", () -> new WallBlock(AbstractBlock.Properties.from(STELLAR_STONE_BRICKS.get())));
	public static final RegistryObject<Item> STELLAR_STONE_BRICKS_WALL_ITEM = ITEMS.register("stellar_stone_bricks_wall", () -> new BlockItem(STELLAR_STONE_BRICKS_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WallBlock> MOSSY_STELLAR_STONE_WALL = BLOCKS.register("mossy_stellar_stone_wall", () -> new WallBlock(AbstractBlock.Properties.from(MOSSY_STELLAR_STONE.get())));
	public static final RegistryObject<Item> MOSSY_STELLAR_STONE_WALL_ITEM = ITEMS.register("mossy_stellar_stone_wall", () -> new BlockItem(MOSSY_STELLAR_STONE_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WallBlock> SLIPPERY_SAND_STONE_WALL = BLOCKS.register("slippery_sand_stone_wall", () -> new WallBlock(AbstractBlock.Properties.from(SLIPPERY_SAND_STONE.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_WALL_ITEM = ITEMS.register("slippery_sand_stone_wall", () -> new BlockItem(SLIPPERY_SAND_STONE_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WallBlock> SLIPPERY_SAND_STONE_BRICKS_WALL = BLOCKS.register("slippery_sand_stone_bricks_wall", () -> new WallBlock(AbstractBlock.Properties.from(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_BRICKS_WALL_ITEM = ITEMS.register("slippery_sand_stone_bricks_wall", () -> new BlockItem(SLIPPERY_SAND_STONE_BRICKS_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WallBlock> GLAUCOPHANITE_WALL = BLOCKS.register("glaucophanite_wall", () -> new WallBlock(AbstractBlock.Properties.from(GLAUCOPHANITE.get())));
	public static final RegistryObject<Item> GLAUCOPHANITE_WALL_ITEM = ITEMS.register("glaucophanite_wall", () -> new BlockItem(GLAUCOPHANITE_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WallBlock> POLISHED_GLAUCOPHANITE_WALL = BLOCKS.register("polished_glaucophanite_wall", () -> new WallBlock(AbstractBlock.Properties.from(POLISHED_GLAUCOPHANITE.get())));
	public static final RegistryObject<Item> POLISHED_GLAUCOPHANITE_WALL_ITEM = ITEMS.register("polished_glaucophanite_wall", () -> new BlockItem(POLISHED_GLAUCOPHANITE_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WallBlock> MAGMATIC_GEL_WALL = BLOCKS.register("magmatic_gel_wall", () -> new WallBlock(AbstractBlock.Properties.from(MAGMATIC_GEL_BLOCK.get())));
	public static final RegistryObject<Item> MAGMATIC_GEL_WALL_ITEM = ITEMS.register("magmatic_gel_wall", () -> new BlockItem(MAGMATIC_GEL_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//gates
	public static final RegistryObject<FenceGateBlock> AERIAL_TREE_GATE = BLOCKS.register("aerial_tree_gate", () -> new FenceGateBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_GATE_ITEM = ITEMS.register("aerial_tree_gate", () -> new BlockItem(AERIAL_TREE_GATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<FenceGateBlock> GOLDEN_BEECH_GATE = BLOCKS.register("golden_beech_gate", () -> new FenceGateBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_GATE_ITEM = ITEMS.register("golden_beech_gate", () -> new BlockItem(GOLDEN_BEECH_GATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<FenceGateBlock> COPPER_PINE_GATE = BLOCKS.register("copper_pine_gate", () -> new FenceGateBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_GATE_ITEM = ITEMS.register("copper_pine_gate", () -> new BlockItem(COPPER_PINE_GATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<FenceGateBlock> SKY_CACTUS_FIBER_GATE = BLOCKS.register("sky_cactus_fiber_gate", () -> new FenceGateBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_GATE_ITEM = ITEMS.register("sky_cactus_fiber_gate", () -> new BlockItem(SKY_CACTUS_FIBER_GATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//doors
	public static final RegistryObject<DoorBlock> AERIAL_TREE_DOOR = BLOCKS.register("aerial_tree_door", () -> new DoorBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_DOOR_ITEM = ITEMS.register("aerial_tree_door", () -> new BlockItem(AERIAL_TREE_DOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<DoorBlock> GOLDEN_BEECH_DOOR = BLOCKS.register("golden_beech_door", () -> new DoorBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_DOOR_ITEM = ITEMS.register("golden_beech_door", () -> new BlockItem(GOLDEN_BEECH_DOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<DoorBlock> COPPER_PINE_DOOR = BLOCKS.register("copper_pine_door", () -> new DoorBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_DOOR_ITEM = ITEMS.register("copper_pine_door", () -> new BlockItem(COPPER_PINE_DOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<DoorBlock> SKY_CACTUS_FIBER_DOOR = BLOCKS.register("sky_cactus_fiber_door", () -> new DoorBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_DOOR_ITEM = ITEMS.register("sky_cactus_fiber_door", () -> new BlockItem(SKY_CACTUS_FIBER_DOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<DoorBlock> RUBY_DOOR = BLOCKS.register("ruby_door", () -> new DoorBlock(RUBY_MATERIAL));
	public static final RegistryObject<Item> RUBY_DOOR_ITEM = ITEMS.register("ruby_door", () -> new BlockItem(RUBY_DOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//trapdoors
	public static final RegistryObject<TrapDoorBlock> AERIAL_TREE_TRAPDOOR = BLOCKS.register("aerial_tree_trapdoor", () -> new TrapDoorBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_TRAPDOOR_ITEM = ITEMS.register("aerial_tree_trapdoor", () -> new BlockItem(AERIAL_TREE_TRAPDOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<TrapDoorBlock> GOLDEN_BEECH_TRAPDOOR = BLOCKS.register("golden_beech_trapdoor", () -> new TrapDoorBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_TRAPDOOR_ITEM = ITEMS.register("golden_beech_trapdoor", () -> new BlockItem(GOLDEN_BEECH_TRAPDOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<TrapDoorBlock> COPPER_PINE_TRAPDOOR = BLOCKS.register("copper_pine_trapdoor", () -> new TrapDoorBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_TRAPDOOR_ITEM = ITEMS.register("copper_pine_trapdoor", () -> new BlockItem(COPPER_PINE_TRAPDOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<TrapDoorBlock> SKY_CACTUS_FIBER_TRAPDOOR = BLOCKS.register("sky_cactus_fiber_trapdoor", () -> new TrapDoorBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_TRAPDOOR_ITEM = ITEMS.register("sky_cactus_fiber_trapdoor", () -> new BlockItem( SKY_CACTUS_FIBER_TRAPDOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<TrapDoorBlock> RUBY_TRAPDOOR = BLOCKS.register("ruby_trapdoor", () -> new TrapDoorBlock(RUBY_MATERIAL));
	public static final RegistryObject<Item> RUBY_TRAPDOOR_ITEM = ITEMS.register("ruby_trapdoor", () -> new BlockItem(RUBY_TRAPDOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//buttons
	public static final RegistryObject<WoodButtonBlock> STELLAR_STONE_BUTTON = BLOCKS.register("stellar_stone_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(STELLAR_STONE.get())));
	public static final RegistryObject<Item> STELLAR_STONE_BUTTON_ITEM = ITEMS.register("stellar_stone_button", () -> new BlockItem(STELLAR_STONE_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WoodButtonBlock> STELLAR_STONE_BRICKS_BUTTON = BLOCKS.register("stellar_stone_bricks_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(STELLAR_STONE_BRICKS.get())));
	public static final RegistryObject<Item> STELLAR_STONE_BRICKS_BUTTON_ITEM = ITEMS.register("stellar_stone_bricks_button", () -> new BlockItem(STELLAR_STONE_BRICKS_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WoodButtonBlock> SLIPPERY_SAND_STONE_BUTTON = BLOCKS.register("slippery_sand_stone_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(SLIPPERY_SAND_STONE.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_BUTTON_ITEM = ITEMS.register("slippery_sand_stone_button", () -> new BlockItem(SLIPPERY_SAND_STONE_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WoodButtonBlock> SLIPPERY_SAND_STONE_BRICKS_BUTTON = BLOCKS.register("slippery_sand_stone_bricks_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_BRICKS_BUTTON_ITEM = ITEMS.register("slippery_sand_stone_bricks_button", () -> new BlockItem(SLIPPERY_SAND_STONE_BRICKS_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WoodButtonBlock> AERIAL_TREE_BUTTON = BLOCKS.register("aerial_tree_button", () -> new WoodButtonBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_BUTTON_ITEM = ITEMS.register("aerial_tree_button", () -> new BlockItem(AERIAL_TREE_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WoodButtonBlock> GOLDEN_BEECH_BUTTON = BLOCKS.register("golden_beech_button", () -> new WoodButtonBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_BUTTON_ITEM = ITEMS.register("golden_beech_button", () -> new BlockItem(GOLDEN_BEECH_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WoodButtonBlock> COPPER_PINE_BUTTON = BLOCKS.register("copper_pine_button", () -> new WoodButtonBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_BUTTON_ITEM = ITEMS.register("copper_pine_button", () -> new BlockItem(COPPER_PINE_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WoodButtonBlock> SKY_CACTUS_FIBER_BUTTON = BLOCKS.register("sky_cactus_fiber_button", () -> new WoodButtonBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_BUTTON_ITEM = ITEMS.register("sky_cactus_fiber_button", () -> new BlockItem(SKY_CACTUS_FIBER_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WoodButtonBlock> GLAUCOPHANITE_BUTTON = BLOCKS.register("glaucophanite_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(GLAUCOPHANITE.get())));
	public static final RegistryObject<Item> GLAUCOPHANITE_BUTTON_ITEM = ITEMS.register("glaucophanite_button", () -> new BlockItem(GLAUCOPHANITE_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//pressure plates
	public static final RegistryObject<PressurePlateBlock> STELLAR_STONE_PRESSURE_PLATE = BLOCKS.register("stellar_stone_pressure_plate", () -> new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.from(STELLAR_STONE.get())));
	public static final RegistryObject<Item> STELLAR_STONE_PRESSURE_PLATE_ITEM = ITEMS.register("stellar_stone_pressure_plate", () -> new BlockItem(STELLAR_STONE_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<PressurePlateBlock> STELLAR_STONE_BRICKS_PRESSURE_PLATE = BLOCKS.register("stellar_stone_bricks_pressure_plate", () -> new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.from(STELLAR_STONE_BRICKS.get())));
	public static final RegistryObject<Item> STELLAR_STONE_BRICKS_PRESSURE_PLATE_ITEM = ITEMS.register("stellar_stone_bricks_pressure_plate", () -> new BlockItem(STELLAR_STONE_BRICKS_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<PressurePlateBlock> SLIPPERY_SAND_STONE_PRESSURE_PLATE = BLOCKS.register("slippery_sand_stone_pressure_plate", () -> new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.from(SLIPPERY_SAND_STONE.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_PRESSURE_PLATE_ITEM = ITEMS.register("slippery_sand_stone_pressure_plate", () -> new BlockItem(SLIPPERY_SAND_STONE_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<PressurePlateBlock> SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE = BLOCKS.register("slippery_sand_stone_bricks_pressure_plate", () -> new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.from(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE_ITEM = ITEMS.register("slippery_sand_stone_bricks_pressure_plate", () -> new BlockItem(SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<PressurePlateBlock> AERIAL_TREE_PRESSURE_PLATE = BLOCKS.register("aerial_tree_pressure_plate", () -> new PressurePlateBlock(Sensitivity.EVERYTHING, AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_PRESSURE_PLATE_ITEM = ITEMS.register("aerial_tree_pressure_plate", () -> new BlockItem(AERIAL_TREE_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<PressurePlateBlock> GOLDEN_BEECH_PRESSURE_PLATE = BLOCKS.register("golden_beech_pressure_plate", () -> new PressurePlateBlock(Sensitivity.EVERYTHING, AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_PRESSURE_PLATE_ITEM = ITEMS.register("golden_beech_pressure_plate", () -> new BlockItem(GOLDEN_BEECH_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<PressurePlateBlock> COPPER_PINE_PRESSURE_PLATE = BLOCKS.register("copper_pine_pressure_plate", () -> new PressurePlateBlock(Sensitivity.EVERYTHING, AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_PRESSURE_PLATE_ITEM = ITEMS.register("copper_pine_pressure_plate", () -> new BlockItem(COPPER_PINE_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<PressurePlateBlock> SKY_CACTUS_FIBER_PRESSURE_PLATE = BLOCKS.register("sky_cactus_fiber_pressure_plate", () -> new PressurePlateBlock(Sensitivity.EVERYTHING, SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_PRESSURE_PLATE_ITEM = ITEMS.register("sky_cactus_fiber_pressure_plate", () -> new BlockItem(SKY_CACTUS_FIBER_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<PressurePlateBlock> GLAUCOPHANITE_PRESSURE_PLATE = BLOCKS.register("glaucophanite_pressure_plate", () -> new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.from(GLAUCOPHANITE.get())));
	public static final RegistryObject<Item> GLAUCOPHANITE_PRESSURE_PLATE_ITEM = ITEMS.register("glaucophanite_pressure_plate", () -> new BlockItem(GLAUCOPHANITE_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//slabs
	public static final RegistryObject<SlabBlock> AERIAL_TREE_SLAB = BLOCKS.register("aerial_tree_slab", () -> new SlabBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_SLAB_ITEM = ITEMS.register("aerial_tree_slab", () -> new BlockItem(AERIAL_TREE_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<SlabBlock> GOLDEN_BEECH_SLAB = BLOCKS.register("golden_beech_slab", () -> new SlabBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_SLAB_ITEM = ITEMS.register("golden_beech_slab", () -> new BlockItem(GOLDEN_BEECH_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<SlabBlock> COPPER_PINE_SLAB = BLOCKS.register("copper_pine_slab", () -> new SlabBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_SLAB_ITEM = ITEMS.register("copper_pine_slab", () -> new BlockItem(COPPER_PINE_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<SlabBlock> SKY_CACTUS_FIBER_SLAB = BLOCKS.register("sky_cactus_fiber_slab", () -> new SlabBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_SLAB_ITEM = ITEMS.register("sky_cactus_fiber_slab", () -> new BlockItem(SKY_CACTUS_FIBER_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<SlabBlock> STELLAR_STONE_SLAB = BLOCKS.register("stellar_stone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(STELLAR_STONE.get())));
	public static final RegistryObject<Item> STELLAR_STONE_SLAB_ITEM = ITEMS.register("stellar_stone_slab", () -> new BlockItem(STELLAR_STONE_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<SlabBlock> STELLAR_STONE_BRICKS_SLAB = BLOCKS.register("stellar_stone_bricks_slab", () -> new SlabBlock(AbstractBlock.Properties.from(STELLAR_STONE_BRICKS.get())));
	public static final RegistryObject<Item> STELLAR_STONE_BRICKS_SLAB_ITEM = ITEMS.register("stellar_stone_bricks_slab", () -> new BlockItem(STELLAR_STONE_BRICKS_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<SlabBlock> MOSSY_STELLAR_STONE_SLAB = BLOCKS.register("mossy_stellar_stone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(MOSSY_STELLAR_STONE.get())));
	public static final RegistryObject<Item> MOSSY_STELLAR_STONE_SLAB_ITEM = ITEMS.register("mossy_stellar_stone_slab", () -> new BlockItem(MOSSY_STELLAR_STONE_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<SlabBlock> SLIPPERY_SAND_STONE_SLAB = BLOCKS.register("slippery_sand_stone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(SLIPPERY_SAND_STONE.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_SLAB_ITEM = ITEMS.register("slippery_sand_stone_slab", () -> new BlockItem(SLIPPERY_SAND_STONE_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<SlabBlock> SLIPPERY_SAND_STONE_BRICKS_SLAB = BLOCKS.register("slippery_sand_stone_bricks_slab", () -> new SlabBlock(AbstractBlock.Properties.from(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_BRICKS_SLAB_ITEM = ITEMS.register("slippery_sand_stone_bricks_slab", () -> new BlockItem(SLIPPERY_SAND_STONE_BRICKS_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<SlabBlock> POLISHED_GLAUCOPHANITE_SLAB = BLOCKS.register("polished_glaucophanite_slab", () -> new SlabBlock(AbstractBlock.Properties.from(POLISHED_GLAUCOPHANITE.get())));
	public static final RegistryObject<Item> POLISHED_GLAUCOPHANITE_SLAB_ITEM = ITEMS.register("polished_glaucophanite_slab", () -> new BlockItem(POLISHED_GLAUCOPHANITE_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<SlabBlock> MAGMATIC_GEL_SLAB = BLOCKS.register("magmatic_gel_slab", () -> new SlabBlock(AbstractBlock.Properties.from(MAGMATIC_GEL_BLOCK.get())));
	public static final RegistryObject<Item> MAGMATIC_GEL_SLAB_ITEM = ITEMS.register("magmatic_gel_slab", () -> new BlockItem(MAGMATIC_GEL_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//stairs
	public static final RegistryObject<StairsBlock> AERIAL_TREE_STAIRS = BLOCKS.register("aerial_tree_stairs", () -> new StairsBlock(() -> AERIAL_TREE_PLANKS.get().getDefaultState(), AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_STAIRS_ITEM = ITEMS.register("aerial_tree_stairs", () -> new BlockItem(AERIAL_TREE_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<StairsBlock> GOLDEN_BEECH_STAIRS = BLOCKS.register("golden_beech_stairs", () -> new StairsBlock(() -> GOLDEN_BEECH_PLANKS.get().getDefaultState(), AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_STAIRS_ITEM = ITEMS.register("golden_beech_stairs", () -> new BlockItem(GOLDEN_BEECH_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<StairsBlock> COPPER_PINE_STAIRS = BLOCKS.register("copper_pine_stairs", () -> new StairsBlock(() -> COPPER_PINE_PLANKS.get().getDefaultState(), AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_STAIRS_ITEM = ITEMS.register("copper_pine_stairs", () -> new BlockItem(COPPER_PINE_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<StairsBlock> SKY_CACTUS_FIBER_STAIRS = BLOCKS.register("sky_cactus_fiber_stairs", () -> new StairsBlock(() -> SKY_CACTUS_FIBER_PLANKS.get().getDefaultState(), SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_STAIRS_ITEM = ITEMS.register("sky_cactus_fiber_stairs", () -> new BlockItem(SKY_CACTUS_FIBER_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<StairsBlock> STELLAR_STONE_STAIRS = BLOCKS.register("stellar_stone_stairs", () -> new StairsBlock(() -> STELLAR_STONE.get().getDefaultState(), AbstractBlock.Properties.from(STELLAR_STONE.get())));
	public static final RegistryObject<Item> STELLAR_STONE_STAIRS_ITEM = ITEMS.register("stellar_stone_stairs", () -> new BlockItem(STELLAR_STONE_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<StairsBlock> STELLAR_STONE_BRICKS_STAIRS = BLOCKS.register("stellar_stone_bricks_stairs", () -> new StairsBlock(() -> STELLAR_STONE_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(STELLAR_STONE_BRICKS.get())));
	public static final RegistryObject<Item> STELLAR_STONE_BRICKS_STAIRS_ITEM = ITEMS.register("stellar_stone_bricks_stairs", () -> new BlockItem(STELLAR_STONE_BRICKS_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<StairsBlock> MOSSY_STELLAR_STONE_STAIRS = BLOCKS.register("mossy_stellar_stone_stairs", () -> new StairsBlock(() -> MOSSY_STELLAR_STONE.get().getDefaultState(), AbstractBlock.Properties.from(MOSSY_STELLAR_STONE.get())));
	public static final RegistryObject<Item> MOSSY_STELLAR_STONE_STAIRS_ITEM = ITEMS.register("mossy_stellar_stone_stairs", () -> new BlockItem(MOSSY_STELLAR_STONE_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<StairsBlock> SLIPPERY_SAND_STONE_STAIRS = BLOCKS.register("slippery_sand_stone_stairs", () -> new StairsBlock(() -> SLIPPERY_SAND_STONE.get().getDefaultState(), AbstractBlock.Properties.from(SLIPPERY_SAND_STONE.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_STAIRS_ITEM = ITEMS.register("slippery_sand_stone_stairs", () -> new BlockItem(SLIPPERY_SAND_STONE_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<StairsBlock> SLIPPERY_SAND_STONE_BRICKS_STAIRS = BLOCKS.register("slippery_sand_stone_bricks_stairs", () -> new StairsBlock(() -> SLIPPERY_SAND_STONE_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_BRICKS_STAIRS_ITEM = ITEMS.register("slippery_sand_stone_bricks_stairs", () -> new BlockItem(SLIPPERY_SAND_STONE_BRICKS_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<StairsBlock> POLISHED_GLAUCOPHANITE_STAIRS = BLOCKS.register("polished_glaucophanite_stairs", () -> new StairsBlock(() -> POLISHED_GLAUCOPHANITE.get().getDefaultState(), AbstractBlock.Properties.from(POLISHED_GLAUCOPHANITE.get())));
	public static final RegistryObject<Item> POLISHED_GLAUCOPHANITE_STAIRS_ITEM = ITEMS.register("polished_glaucophanite_stairs", () -> new BlockItem(POLISHED_GLAUCOPHANITE_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<StairsBlock> MAGMATIC_GEL_STAIRS = BLOCKS.register("magmatic_gel_stairs", () -> new StairsBlock(() -> MAGMATIC_GEL_BLOCK.get().getDefaultState(), AbstractBlock.Properties.from(MAGMATIC_GEL_BLOCK.get())));
	public static final RegistryObject<Item> MAGMATIC_GEL_STAIRS_ITEM = ITEMS.register("magmatic_gel_stairs", () -> new BlockItem(MAGMATIC_GEL_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//signs
	public static final RegistryObject<AerialHellSignBlock> AERIAL_TREE_SIGN = BLOCKS.register("aerial_tree_sign", () -> new AerialHellSignBlock(AERIAL_TREE_SIGN_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_SIGN_ITEM = ITEMS.register("aerial_tree_sign", () -> new BlockItem(AERIAL_TREE_SIGN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<AerialHellSignBlock> GOLDEN_BEECH_SIGN = BLOCKS.register("golden_beech_sign", () -> new AerialHellSignBlock(AERIAL_TREE_SIGN_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_SIGN_ITEM = ITEMS.register("golden_beech_sign", () -> new BlockItem(GOLDEN_BEECH_SIGN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<AerialHellSignBlock> COPPER_PINE_SIGN = BLOCKS.register("copper_pine_sign", () -> new AerialHellSignBlock(AERIAL_TREE_SIGN_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_SIGN_ITEM = ITEMS.register("copper_pine_sign", () -> new BlockItem(COPPER_PINE_SIGN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<AerialHellSignBlock> SKY_CACTUS_FIBER_SIGN = BLOCKS.register("sky_cactus_fiber_sign", () -> new AerialHellSignBlock(SKY_CACTUS_FIBER_SIGN_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_SIGN_ITEM = ITEMS.register("sky_cactus_fiber_sign", () -> new BlockItem(SKY_CACTUS_FIBER_SIGN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//crafting tables
	public static final RegistryObject<CraftingTableBlock> AERIAL_TREE_CRAFTING_TABLE = BLOCKS.register("aerial_tree_crafting_table", () -> new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_CRAFTING_TABLE_ITEM = ITEMS.register("aerial_tree_crafting_table", () -> new BlockItem(AERIAL_TREE_CRAFTING_TABLE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<CraftingTableBlock> GOLDEN_BEECH_CRAFTING_TABLE = BLOCKS.register("golden_beech_crafting_table", () -> new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_CRAFTING_TABLE_ITEM = ITEMS.register("golden_beech_crafting_table", () -> new BlockItem(GOLDEN_BEECH_CRAFTING_TABLE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<CraftingTableBlock> COPPER_PINE_CRAFTING_TABLE = BLOCKS.register("copper_pine_crafting_table", () -> new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_CRAFTING_TABLE_ITEM = ITEMS.register("copper_pine_crafting_table", () -> new BlockItem(COPPER_PINE_CRAFTING_TABLE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<CraftingTableBlock> SKY_CACTUS_FIBER_CRAFTING_TABLE = BLOCKS.register("sky_cactus_fiber_crafting_table", () -> new AerialHellCraftingTableBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_CRAFTING_TABLE_ITEM = ITEMS.register("sky_cactus_fiber_crafting_table", () -> new BlockItem(SKY_CACTUS_FIBER_CRAFTING_TABLE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//barrels
	public static final RegistryObject<AerialHellBarrelBlock> AERIAL_TREE_BARREL = BLOCKS.register("aerial_tree_barrel", () -> new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_BARREL_ITEM = ITEMS.register("aerial_tree_barrel", () -> new BlockItem(AERIAL_TREE_BARREL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<AerialHellBarrelBlock> GOLDEN_BEECH_BARREL = BLOCKS.register("golden_beech_barrel", () -> new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_BARREL_ITEM = ITEMS.register("golden_beech_barrel", () -> new BlockItem(GOLDEN_BEECH_BARREL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<AerialHellBarrelBlock> COPPER_PINE_BARREL = BLOCKS.register("copper_pine_barrel", () -> new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_BARREL_ITEM = ITEMS.register("copper_pine_barrel", () -> new BlockItem(COPPER_PINE_BARREL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<AerialHellBarrelBlock> SKY_CACTUS_FIBER_BARREL = BLOCKS.register("sky_cactus_fiber_barrel", () -> new AerialHellBarrelBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_BARREL_ITEM = ITEMS.register("sky_cactus_fiber_barrel", () -> new BlockItem(SKY_CACTUS_FIBER_BARREL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//composters
	public static final RegistryObject<ComposterBlock> AERIAL_TREE_COMPOSTER = BLOCKS.register("aerial_tree_composter", () -> new ComposterBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_COMPOSTER_ITEM = ITEMS.register("aerial_tree_composter", () -> new BlockItem(AERIAL_TREE_COMPOSTER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<ComposterBlock> GOLDEN_BEECH_COMPOSTER = BLOCKS.register("golden_beech_composter", () -> new ComposterBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_COMPOSTER_ITEM = ITEMS.register("golden_beech_composter", () -> new BlockItem(GOLDEN_BEECH_COMPOSTER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<ComposterBlock> COPPER_PINE_COMPOSTER = BLOCKS.register("copper_pine_composter", () -> new ComposterBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_COMPOSTER_ITEM = ITEMS.register("copper_pine_composter", () -> new BlockItem(COPPER_PINE_COMPOSTER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<ComposterBlock> SKY_CACTUS_FIBER_COMPOSTER = BLOCKS.register("sky_cactus_fiber_composter", () -> new ComposterBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_COMPOSTER_ITEM = ITEMS.register("sky_cactus_fiber_composter", () -> new BlockItem(SKY_CACTUS_FIBER_COMPOSTER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//item for crafts
	public static final RegistryObject<Item> SKY_STICK = ITEMS.register("sky_stick",() -> new Item(new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//dungeon items
	public static final RegistryObject<Item> DIAMOND_THROWING_KNIFE = ITEMS.register("diamond_throwing_knife", () -> new DiamondThrowingKnifeItem(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
	public static final RegistryObject<Item> VOLUCITE_THROWING_KNIFE = ITEMS.register("volucite_throwing_knife", () -> new VoluciteThrowingKnifeItem(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
	public static final RegistryObject<Item> LIGHTNING_THROWING_KNIFE = ITEMS.register("lightning_throwing_knife", () -> new LightningThrowingKnifeItem(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
	
	//food
	public static final RegistryObject<Item> AERIAL_BERRY = ITEMS.register("aerial_berry",() -> new Item(new Item.Properties().food(new Food.Builder().fastToEat().hunger(2).build()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> VIBRANT_AERIAL_BERRY = ITEMS.register("vibrant_aerial_berry",() -> new Item(new Item.Properties().rarity(AerialHellRarities.VIBRANT).food(new Food.Builder().fastToEat().hunger(6).build()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> FROZEN_AERIAL_BERRY = ITEMS.register("frozen_aerial_berry", () -> new FrozenAerialBerryItem(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
	public static final RegistryObject<Item> COPPER_PINE_CONE = ITEMS.register("copper_pine_cone",() -> new Item(new Item.Properties().food(new Food.Builder().fastToEat().hunger(0).build()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER = ITEMS.register("sky_cactus_fiber",() -> new Item(new Item.Properties().food(new Food.Builder().fastToEat().hunger(1).build()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> VIBRANT_SKY_CACTUS_FIBER = ITEMS.register("vibrant_sky_cactus_fiber",() -> new Item(new Item.Properties().rarity(AerialHellRarities.VIBRANT).food(new Food.Builder().fastToEat().hunger(4).build()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> WHITE_SOLID_ETHER_FRAGMENT = ITEMS.register("white_solid_ether_fragment",() -> new Item(new Item.Properties().food(new Food.Builder().hunger(1).build()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> BLUE_SOLID_ETHER_FRAGMENT = ITEMS.register("blue_solid_ether_fragment", () -> new BlueAercloudFragmentItem(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
	public static final RegistryObject<Item> GOLDEN_SOLID_ETHER_FRAGMENT = ITEMS.register("golden_solid_ether_fragment", () -> new GoldenAercloudFragmentItem(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
	public static final RegistryObject<Item> GREEN_SOLID_ETHER_FRAGMENT = ITEMS.register("green_solid_ether_fragment", () -> new GreenAercloudFragmentItem(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
	public static final RegistryObject<Item> GOLDEN_NETHER_MEAT_PIECE = ITEMS.register("golden_nether_meat_piece", () -> new GoldenNetherMeatItem(1,110,AerialHellItemGroups.AERIAL_HELL_DIMENSION, AerialHellRarities.VIBRANT));
	public static final RegistryObject<Item> GOLDEN_NETHER_STEAK = ITEMS.register("golden_nether_steak", () -> new GoldenNetherMeatItem(4,500,AerialHellItemGroups.AERIAL_HELL_DIMENSION, AerialHellRarities.VIBRANT));
	public static final RegistryObject<Item> VIBRANT_GOLDEN_NETHER_STEAK = ITEMS.register("vibrant_golden_nether_steak", () -> new GoldenNetherMeatItem(6,1000,AerialHellItemGroups.AERIAL_HELL_DIMENSION, AerialHellRarities.VIBRANT));
	
	//buckets
	public static final RegistryObject<Item> IRON_LIQUID_OF_GODS_BUCKET = ITEMS.register("iron_liquid_of_gods_bucket", () -> new BucketItem(AerialHellFluids.LIQUID_OF_THE_GODS_SOURCE, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).maxStackSize(1)));
    public static final RegistryObject<Item> RUBY_LIQUID_OF_GODS_BUCKET = ITEMS.register("ruby_liquid_of_gods_bucket", () -> new RubyLiquidOfGodsBucketItem(new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> RUBY_BUCKET = ITEMS.register("ruby_bucket", () -> new RubyBucketItem(new Item.Properties().maxStackSize(16).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> RUBY_WATER_BUCKET = ITEMS.register("ruby_water_bucket", () -> new RubyWaterBucketItem(new Item.Properties().containerItem(RUBY_BUCKET.get()).maxStackSize(1).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> RUBY_MILK_BUCKET = ITEMS.register("ruby_milk_bucket", () -> new RubyMilkBucketItem(new Item.Properties().containerItem(RUBY_BUCKET.get()).maxStackSize(1).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//arrows & bows
	public static final RegistryObject<Item> VOLUCITE_BLOWPIPE_ARROW = ITEMS.register("volucite_blowpipe_arrow", () -> new AerialArrowItem(new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	public static final RegistryObject<Item> VOLUCITE_BLOWPIPE = ITEMS.register("volucite_blowpipe", () -> new BlowpipeItem(VOLUCITE_BLOWPIPE_ARROW, new Item.Properties().maxStackSize(1).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	public static final RegistryObject<Item> MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER = ITEMS.register("music_disc_sweden_andreas_zoeller", () -> new MusicDiscItem(1, AerialHellSoundEvents.MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER, new Item.Properties().maxStackSize(1).rarity(Rarity.RARE).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//fluids
    public static final RegistryObject<FlowingFluidBlock> LIQUID_OF_THE_GODS = BLOCKS.register("liquid_of_the_gods", () -> new AerialHellFluidBlock(AerialHellFluids.LIQUID_OF_THE_GODS_SOURCE, AbstractBlock.Properties.create(Material.LAVA).setLightLevel((state) -> 8)));

    //tools
    public static final RegistryObject<PickaxeItem> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe", () -> new PickaxeItem(ToolMaterials.ruby, 1, -2.8F, (new Item.Properties()).addToolType(ToolType.PICKAXE, ToolMaterials.ruby.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<PickaxeItem> AZURITE_PICKAXE = ITEMS.register("azurite_pickaxe", () -> new PickaxeItem(ToolMaterials.azurite, 1, -2.8F, (new Item.Properties()).addToolType(ToolType.PICKAXE, ToolMaterials.azurite.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<PickaxeItem> VOLUCITE_PICKAXE = ITEMS.register("volucite_pickaxe", () -> new PickaxeItem(ToolMaterials.volucite, 1, -2.8F, (new Item.Properties()).addToolType(ToolType.PICKAXE, ToolMaterials.volucite.getHarvestLevel()).rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    
    public static final RegistryObject<ShovelItem> RUBY_SHOVEL = ITEMS.register("ruby_shovel", () -> new ShovelItem(ToolMaterials.ruby, 1.5F, -3F, (new Item.Properties()).addToolType(ToolType.SHOVEL, ToolMaterials.ruby.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ShovelItem> AZURITE_SHOVEL = ITEMS.register("azurite_shovel", () -> new ShovelItem(ToolMaterials.azurite, 1.5F, -3F, (new Item.Properties()).addToolType(ToolType.SHOVEL, ToolMaterials.azurite.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ShovelItem> VOLUCITE_SHOVEL = ITEMS.register("volucite_shovel", () -> new ShovelItem(ToolMaterials.volucite, 1.5F, -3F, (new Item.Properties()).addToolType(ToolType.SHOVEL, ToolMaterials.volucite.getHarvestLevel()).rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    
    public static final RegistryObject<AxeItem> RUBY_AXE = ITEMS.register("ruby_axe", () -> new AxeItem(ToolMaterials.ruby, 6, -3.1F, (new Item.Properties()).addToolType(ToolType.AXE, ToolMaterials.ruby.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<AxeItem> AZURITE_AXE = ITEMS.register("azurite_axe", () -> new AxeItem(ToolMaterials.azurite, 6, -3.1F, (new Item.Properties()).addToolType(ToolType.AXE, ToolMaterials.azurite.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<AxeItem> VOLUCITE_AXE = ITEMS.register("volucite_axe", () -> new AxeItem(ToolMaterials.volucite, 6, -3.1F, (new Item.Properties()).addToolType(ToolType.AXE, ToolMaterials.volucite.getHarvestLevel()).rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    
    public static final RegistryObject<HoeItem> RUBY_HOE = ITEMS.register("ruby_hoe", () -> new HoeItem(ToolMaterials.ruby, -3, 0.0F, (new Item.Properties()).addToolType(ToolType.HOE, ToolMaterials.ruby.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<HoeItem> AZURITE_HOE = ITEMS.register("azurite_hoe", () -> new HoeItem(ToolMaterials.azurite, -3, 0.0F, (new Item.Properties()).addToolType(ToolType.HOE, ToolMaterials.azurite.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<HoeItem> VOLUCITE_HOE = ITEMS.register("volucite_hoe", () -> new HoeItem(ToolMaterials.volucite, -3, 0.0F, (new Item.Properties()).addToolType(ToolType.HOE, ToolMaterials.volucite.getHarvestLevel()).rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    
    //weapons
    public static final RegistryObject<SwordItem> RUBY_SWORD = ITEMS.register("ruby_sword", () -> new SwordItem(ToolMaterials.ruby, 3, -2.4F, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> AZURITE_SWORD = ITEMS.register("azurite_sword", () -> new SwordItem(ToolMaterials.azurite, 3, -2.4F, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> VOLUCITE_SWORD = ITEMS.register("volucite_sword", () -> new SwordItem(ToolMaterials.volucite, 3, -2.4F, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));

    //armor
    public static final RegistryObject<ArmorItem> RUBY_HELMET = ITEMS.register("ruby_helmet", () -> new ArmorItem(ArmorMaterials.ruby, EquipmentSlotType.HEAD, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate", () -> new ArmorItem(ArmorMaterials.ruby, EquipmentSlotType.CHEST, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> RUBY_LEGGINGS = ITEMS.register("ruby_leggings", () -> new ArmorItem(ArmorMaterials.ruby, EquipmentSlotType.LEGS, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> RUBY_BOOTS = ITEMS.register("ruby_boots", () -> new ArmorItem(ArmorMaterials.ruby, EquipmentSlotType.FEET, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    
    public static final RegistryObject<ArmorItem> AZURITE_HELMET = ITEMS.register("azurite_helmet", () -> new ArmorItem(ArmorMaterials.azurite, EquipmentSlotType.HEAD, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> AZURITE_CHESTPLATE = ITEMS.register("azurite_chestplate", () -> new ArmorItem(ArmorMaterials.azurite, EquipmentSlotType.CHEST, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> AZURITE_LEGGINGS = ITEMS.register("azurite_leggings", () -> new ArmorItem(ArmorMaterials.azurite, EquipmentSlotType.LEGS, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> AZURITE_BOOTS = ITEMS.register("azurite_boots", () -> new ArmorItem(ArmorMaterials.azurite, EquipmentSlotType.FEET, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    
    public static final RegistryObject<ArmorItem> VOLUCITE_HELMET = ITEMS.register("volucite_helmet", () -> new ArmorItem(ArmorMaterials.volucite, EquipmentSlotType.HEAD, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> VOLUCITE_CHESTPLATE = ITEMS.register("volucite_chestplate", () -> new ArmorItem(ArmorMaterials.volucite, EquipmentSlotType.CHEST, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> VOLUCITE_LEGGINGS = ITEMS.register("volucite_leggings", () -> new ArmorItem(ArmorMaterials.volucite, EquipmentSlotType.LEGS, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> VOLUCITE_BOOTS = ITEMS.register("volucite_boots", () -> new ArmorItem(ArmorMaterials.volucite, EquipmentSlotType.FEET, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
}
