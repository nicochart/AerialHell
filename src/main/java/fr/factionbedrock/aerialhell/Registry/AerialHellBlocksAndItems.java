package fr.factionbedrock.aerialhell.Registry;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

import java.util.function.ToIntFunction;

import com.google.common.collect.ImmutableMap;

import fr.factionbedrock.aerialhell.Block.MagmaticGelBlock;
import fr.factionbedrock.aerialhell.Block.MagmaticGelOreBlock;
import fr.factionbedrock.aerialhell.Block.AerialHellBarrelBlock;
import fr.factionbedrock.aerialhell.Block.AerialHellBookshelfBlock;
import fr.factionbedrock.aerialhell.Block.SkyCactusBlock;
import fr.factionbedrock.aerialhell.Block.AerialHellCraftingTableBlock;
import fr.factionbedrock.aerialhell.Block.AerialHellDeadBushBlock;
import fr.factionbedrock.aerialhell.Block.StellarGrassBlock;
import fr.factionbedrock.aerialhell.Block.ThornyWebBlock;
import fr.factionbedrock.aerialhell.Block.AerialHellPortalBlock;
import fr.factionbedrock.aerialhell.Block.AerialHellFluidBlock;
import fr.factionbedrock.aerialhell.Block.StellarFurnaceBlock;
import fr.factionbedrock.aerialhell.Block.AerialHellOreBlock;
import fr.factionbedrock.aerialhell.Block.AerialHellSignBlock;
import fr.factionbedrock.aerialhell.Block.AerialHellTallGrassBlock;
import fr.factionbedrock.aerialhell.Block.ArsonistBlock;
import fr.factionbedrock.aerialhell.Block.BramblesBlock;
import fr.factionbedrock.aerialhell.Block.VibratorBlock;
import fr.factionbedrock.aerialhell.Block.ChestMimicBlock;
import fr.factionbedrock.aerialhell.Block.CopperPineLeavesBlock;
import fr.factionbedrock.aerialhell.Block.FluoriteTorchBlock;
import fr.factionbedrock.aerialhell.Block.FluoriteWallTorchBlock;
import fr.factionbedrock.aerialhell.Block.FreezerBlock;
import fr.factionbedrock.aerialhell.Block.Bushes.AerialBerryBushBlock;
import fr.factionbedrock.aerialhell.Block.Bushes.VibrantAerialBerryBushBlock;
import fr.factionbedrock.aerialhell.Block.DungeonCores.CoreProtectedTrappedBlock;
import fr.factionbedrock.aerialhell.Block.DungeonCores.CoreProtectedBlock;
import fr.factionbedrock.aerialhell.Block.DungeonCores.CoreProtectedBookshelfBlock;
import fr.factionbedrock.aerialhell.Block.DungeonCores.CoreProtectedChestBlock;
import fr.factionbedrock.aerialhell.Block.DungeonCores.CoreProtectedRotatedPillarBlock;
import fr.factionbedrock.aerialhell.Block.DungeonCores.CoreProtectedSlabBlock;
import fr.factionbedrock.aerialhell.Block.DungeonCores.CoreProtectedStairsBlock;
import fr.factionbedrock.aerialhell.Block.DungeonCores.CoreProtectedWallBlock;
import fr.factionbedrock.aerialhell.Block.DungeonCores.DungeonCoreBlock;
import fr.factionbedrock.aerialhell.Block.SolidEther.*;
import fr.factionbedrock.aerialhell.Client.TileEntityRenderer.AerialHellChestItemTileEntityRenderer;
import fr.factionbedrock.aerialhell.Item.AerialArrowItem;
import fr.factionbedrock.aerialhell.Item.BurnableBlockItem;
import fr.factionbedrock.aerialhell.Item.BlowpipeItem;
import fr.factionbedrock.aerialhell.Item.BurnableItem;
import fr.factionbedrock.aerialhell.Item.GodsVoluciteBerryItem;
import fr.factionbedrock.aerialhell.Item.StellarLighterItem;
import fr.factionbedrock.aerialhell.Item.FoodWithEffectItem;
import fr.factionbedrock.aerialhell.Item.WithInformationItem;
import fr.factionbedrock.aerialhell.Item.EffectTotemItem;
import fr.factionbedrock.aerialhell.Item.EnchantedEffectTotemItem;
import fr.factionbedrock.aerialhell.Item.Bucket.*;
import fr.factionbedrock.aerialhell.Item.Material.ArmorMaterials;
import fr.factionbedrock.aerialhell.Item.Material.ToolMaterials;
import fr.factionbedrock.aerialhell.Item.ThrowingKnife.AzuriteThrowingKnifeItem;
import fr.factionbedrock.aerialhell.Item.ThrowingKnife.DiamondThrowingKnifeItem;
import fr.factionbedrock.aerialhell.Item.ThrowingKnife.GoldThrowingKnifeItem;
import fr.factionbedrock.aerialhell.Item.ThrowingKnife.IronThrowingKnifeItem;
import fr.factionbedrock.aerialhell.Item.ThrowingKnife.LightningThrowingKnifeItem;
import fr.factionbedrock.aerialhell.Item.ThrowingKnife.MagmaticGelThrowingKnifeItem;
import fr.factionbedrock.aerialhell.Item.ThrowingKnife.NetheriteThrowingKnifeItem;
import fr.factionbedrock.aerialhell.Item.ThrowingKnife.RubyThrowingKnifeItem;
import fr.factionbedrock.aerialhell.Item.ThrowingKnife.VoluciteThrowingKnifeItem;
import fr.factionbedrock.aerialhell.Item.Tools.AerialHellAxeItem;
import fr.factionbedrock.aerialhell.Item.Tools.AerialHellSwordItem;
import fr.factionbedrock.aerialhell.Item.Tools.BerserkAxeItem;
import fr.factionbedrock.aerialhell.Item.Tools.EffectSwordItem;
import fr.factionbedrock.aerialhell.Item.Tools.ForgottenBattleTridentItem;
import fr.factionbedrock.aerialhell.World.Tree.CopperPine;
import fr.factionbedrock.aerialhell.World.Tree.GoldenBeechTree;
import fr.factionbedrock.aerialhell.World.Tree.LapisRobinia;
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
import net.minecraft.block.LanternBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.PressurePlateBlock.Sensitivity;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.StoneButtonBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WallBlock;
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
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.BlockStateProperties;
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
		ComposterBlock.registerCompostable(0.1F, STELLAR_GRASS_ITEM.get());
		ComposterBlock.registerCompostable(0.2F, STELLAR_TALL_GRASS_ITEM.get());
		ComposterBlock.registerCompostable(0.2F, STELLAR_GRASS_BALL_ITEM.get());
		ComposterBlock.registerCompostable(0.1F, STELLAR_DEAD_BUSH_ITEM.get());
		ComposterBlock.registerCompostable(0.2F, BLUE_FLOWER_ITEM.get());
		ComposterBlock.registerCompostable(0.2F, BLACK_ROSE_ITEM.get());
		ComposterBlock.registerCompostable(0.2F, BELLFLOWER_ITEM.get());
		ComposterBlock.registerCompostable(0.5F, AERIAL_BERRY.get());
		ComposterBlock.registerCompostable(0.85F, VIBRANT_AERIAL_BERRY.get());
		ComposterBlock.registerCompostable(0.85F, FROZEN_AERIAL_BERRY.get());
		ComposterBlock.registerCompostable(0.1F, AERIAL_BERRY_SEEDS.get());
		ComposterBlock.registerCompostable(0.2F, VIBRANT_AERIAL_BERRY_SEEDS.get());
		ComposterBlock.registerCompostable(0.5F, COPPER_PINE_CONE.get());
		ComposterBlock.registerCompostable(0.3F, AERIAL_TREE_LEAVES_ITEM.get());
		ComposterBlock.registerCompostable(0.3F, AERIAL_TREE_SAPLING_ITEM.get());
		ComposterBlock.registerCompostable(0.3F, GOLDEN_BEECH_LEAVES_ITEM.get());
		ComposterBlock.registerCompostable(0.3F, GOLDEN_BEECH_SAPLING_ITEM.get());
		ComposterBlock.registerCompostable(0.3F, COPPER_PINE_LEAVES_ITEM.get());
		ComposterBlock.registerCompostable(0.3F, COPPER_PINE_SAPLING_ITEM.get());
		ComposterBlock.registerCompostable(0.3F, LAPIS_ROBINIA_SAPLING_ITEM.get());
		ComposterBlock.registerCompostable(0.3F, LAPIS_ROBINIA_LEAVES_ITEM.get());
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
		pot.addPlant(BELLFLOWER.getId(), POTTED_BELLFLOWER);
		pot.addPlant(STELLAR_FERN.getId(), POTTED_STELLAR_FERN);
		pot.addPlant(STELLAR_DEAD_BUSH.getId(), POTTED_STELLAR_DEAD_BUSH);
		pot.addPlant(SKY_CACTUS.getId(), POTTED_SKY_CACTUS);
		pot.addPlant(VIBRANT_SKY_CACTUS.getId(), POTTED_VIBRANT_SKY_CACTUS);
		pot.addPlant(AERIAL_TREE_SAPLING.getId(), POTTED_AERIAL_TREE_SAPLING);
		pot.addPlant(GOLDEN_BEECH_SAPLING.getId(), POTTED_GOLDEN_BEECH_SAPLING);
		pot.addPlant(COPPER_PINE_SAPLING.getId(), POTTED_COPPER_PINE_SAPLING);
		pot.addPlant(LAPIS_ROBINIA_SAPLING.getId(), POTTED_LAPIS_ROBINIA_SAPLING);
	}
	
	public static void registerAxeStrippingBlocks()
	{
		AxeItem.BLOCK_STRIPPING_MAP = ImmutableMap.<Block, Block>builder()
				.putAll(AxeItem.BLOCK_STRIPPING_MAP)
				.put(AERIAL_TREE_LOG.get(), STRIPPED_AERIAL_TREE_LOG.get())
				.put(GOLDEN_BEECH_LOG.get(), STRIPPED_GOLDEN_BEECH_LOG.get())
				.put(COPPER_PINE_LOG.get(), STRIPPED_COPPER_PINE_LOG.get())
				.put(LAPIS_ROBINIA_LOG.get(), STRIPPED_LAPIS_ROBINIA_LOG.get())
				.build();
	}
	
	//materials
	public static AbstractBlock.Properties AERIAL_TREE_MATERIAL = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.5F, 2.5F).sound(SoundType.WOOD).harvestTool(ToolType.AXE);
	public static AbstractBlock.Properties COPPER_PINE_MATERIAL = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(4.5F, 4.5F).sound(SoundType.WOOD).harvestTool(ToolType.AXE);
	public static AbstractBlock.Properties AERIAL_TREE_SIGN_MATERIAL = AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN).hardnessAndResistance(2.5F, 2.5F).sound(SoundType.WOOD).harvestTool(ToolType.AXE).notSolid().doesNotBlockMovement();
	public static AbstractBlock.Properties SKY_CACTUS_FIBER_MATERIAL = AbstractBlock.Properties.create(Material.CACTUS, MaterialColor.CYAN).hardnessAndResistance(2.5F, 2.5F).sound(SoundType.WOOD).harvestTool(ToolType.AXE);
	public static AbstractBlock.Properties SKY_CACTUS_FIBER_SIGN_MATERIAL = AbstractBlock.Properties.create(Material.CACTUS, MaterialColor.CYAN).hardnessAndResistance(2.5F, 2.5F).sound(SoundType.WOOD).harvestTool(ToolType.AXE).notSolid().doesNotBlockMovement();
	public static AbstractBlock.Properties MUD_CHEST_MATERIAL = AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).hardnessAndResistance(20.0F, 1000.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid();
	public static AbstractBlock.Properties LUNATIC_CHEST_MATERIAL = AbstractBlock.Properties.create(Material.ROCK, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(30.0F, 1000.0F).sound(SoundType.NETHERITE).harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid();
	public static AbstractBlock.Properties VOLUCITE_CHEST_MATERIAL = AbstractBlock.Properties.create(Material.IRON, MaterialColor.CLAY).hardnessAndResistance(50.0F, 1200.0F).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid();
	public static AbstractBlock.Properties GOLDEN_NETHER_CHEST_MATERIAL = AbstractBlock.Properties.create(Material.IRON, MaterialColor.NETHERRACK).hardnessAndResistance(50.0F, 1200.0F).sound(SoundType.BASALT).harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid();
	public static AbstractBlock.Properties RUBY_MATERIAL = AbstractBlock.Properties.create(Material.IRON, MaterialColor.PURPLE).hardnessAndResistance(10.0F, 2.0F).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid();
	
	//portal
	public static final RegistryObject<AerialHellPortalBlock> AERIAL_HELL_PORTAL = BLOCKS.register("aerial_hell_portal", () -> new AerialHellPortalBlock(AbstractBlock.Properties.from(Blocks.NETHER_PORTAL)));
	public static final RegistryObject<Block> STELLAR_PORTAL_FRAME_BLOCK = BLOCKS.register("stellar_portal_frame_block", () -> new Block((AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool().hardnessAndResistance(25.0F, 600.0F))));
	public static final RegistryObject<Block> STELLAR_PORTAL_FRAME_ORE = BLOCKS.register("stellar_portal_frame_ore",() -> new AerialHellOreBlock(0, 0, AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(25.0F, 600.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Item> STELLAR_PORTAL_FRAME_BLOCK_ITEM = ITEMS.register("stellar_portal_frame_block", () -> new BlockItem(STELLAR_PORTAL_FRAME_BLOCK.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_OVERWORLD)));
	public static final RegistryObject<Item> STELLAR_PORTAL_FRAME_ORE_ITEM = ITEMS.register("stellar_portal_frame_ore", () -> new BlockItem(STELLAR_PORTAL_FRAME_ORE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_OVERWORLD)));
	public static final RegistryObject<Item> STELLAR_PORTAL_FRAME_BRICK = ITEMS.register("stellar_portal_frame_brick", () -> new Item(new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_OVERWORLD)));
	public static final RegistryObject<Item> STELLAR_LIGHTER = ITEMS.register("stellar_lighter", () -> new StellarLighterItem(new Item.Properties().maxStackSize(1).maxDamage(4).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//torch
	public static final RegistryObject<Block> FLUORITE_WALL_TORCH = BLOCKS.register("fluorite_wall_torch", () -> new FluoriteWallTorchBlock(AbstractBlock.Properties.from(Blocks.WALL_TORCH)));
	public static final RegistryObject<Block> FLUORITE_TORCH = BLOCKS.register("fluorite_torch", () -> new FluoriteTorchBlock(AbstractBlock.Properties.from(Blocks.TORCH)));
	public static final RegistryObject<Item> FLUORITE_TORCH_ITEM = ITEMS.register("fluorite_torch", () -> new WallOrFloorItem(FLUORITE_TORCH.get(), FLUORITE_WALL_TORCH.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Block> VOLUCITE_WALL_TORCH = BLOCKS.register("volucite_wall_torch", () -> new FluoriteWallTorchBlock(AbstractBlock.Properties.from(Blocks.WALL_TORCH).setLightLevel((state) -> {return 9;})));
	public static final RegistryObject<Block> VOLUCITE_TORCH = BLOCKS.register("volucite_torch", () -> new FluoriteTorchBlock(AbstractBlock.Properties.from(Blocks.TORCH).setLightLevel((state) -> {return 9;})));
	public static final RegistryObject<Item> VOLUCITE_TORCH_ITEM = ITEMS.register("volucite_torch", () -> new WallOrFloorItem(VOLUCITE_TORCH.get(), VOLUCITE_WALL_TORCH.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//lanterns
	public static final RegistryObject<Block> FLUORITE_LANTERN = BLOCKS.register("fluorite_lantern", () -> new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
	public static final RegistryObject<Block> RUBY_LANTERN = BLOCKS.register("ruby_lantern", () -> new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
	public static final RegistryObject<Block> RUBY_FLUORITE_LANTERN = BLOCKS.register("ruby_fluorite_lantern", () -> new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
	public static final RegistryObject<Block> VOLUCITE_LANTERN = BLOCKS.register("volucite_lantern", () -> new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
	public static final RegistryObject<Block> VOLUCITE_FLUORITE_LANTERN = BLOCKS.register("volucite_fluorite_lantern", () -> new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
	public static final RegistryObject<Block> LUNATIC_LANTERN = BLOCKS.register("lunatic_lantern", () -> new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN)));
	public static final RegistryObject<Item> FLUORITE_LANTERN_ITEM = ITEMS.register("fluorite_lantern", () -> new BlockItem(FLUORITE_LANTERN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> RUBY_LANTERN_ITEM = ITEMS.register("ruby_lantern", () -> new BlockItem(RUBY_LANTERN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> RUBY_FLUORITE_LANTERN_ITEM = ITEMS.register("ruby_fluorite_lantern", () -> new BlockItem(RUBY_FLUORITE_LANTERN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> VOLUCITE_LANTERN_ITEM = ITEMS.register("volucite_lantern", () -> new BlockItem(VOLUCITE_LANTERN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> VOLUCITE_FLUORITE_LANTERN_ITEM = ITEMS.register("volucite_fluorite_lantern", () -> new BlockItem(VOLUCITE_FLUORITE_LANTERN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> LUNATIC_LANTERN_ITEM = ITEMS.register("lunatic_lantern", () -> new BlockItem(LUNATIC_LANTERN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
    //aerial_tree
	public static final RegistryObject<RotatedPillarBlock> AERIAL_TREE_LOG = BLOCKS.register("aerial_tree_log", () -> new RotatedPillarBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_AERIAL_TREE_LOG = BLOCKS.register("stripped_aerial_tree_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(AERIAL_TREE_LOG.get()).harvestTool(ToolType.AXE)));
	public static final RegistryObject<Block> AERIAL_TREE_LEAVES = BLOCKS.register("aerial_tree_leaves", () -> new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> AERIAL_TREE_PLANKS = BLOCKS.register("aerial_tree_planks", () -> new Block(AbstractBlock.Properties.from(AERIAL_TREE_LOG.get())));
	public static final RegistryObject<Block> CHISELED_AERIAL_TREE_PLANKS = BLOCKS.register("chiseled_aerial_tree_planks", () -> new Block(AbstractBlock.Properties.from(AERIAL_TREE_PLANKS.get())));
	public static final RegistryObject<Block> AERIAL_TREE_BOOKSHELF = BLOCKS.register("aerial_tree_bookshelf", () -> new AerialHellBookshelfBlock(AbstractBlock.Properties.from(AERIAL_TREE_PLANKS.get())));
	public static final RegistryObject<SaplingBlock> AERIAL_TREE_SAPLING = BLOCKS.register("aerial_tree_sapling", () -> new SaplingBlock(new AerialTree(), AbstractBlock.Properties.from(Blocks.OAK_SAPLING)));
	public static final RegistryObject<Item> AERIAL_TREE_LOG_ITEM = ITEMS.register("aerial_tree_log", () -> new BurnableBlockItem(AERIAL_TREE_LOG.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<Item> STRIPPED_AERIAL_TREE_LOG_ITEM = ITEMS.register("stripped_aerial_tree_log", () -> new BurnableBlockItem(STRIPPED_AERIAL_TREE_LOG.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<Item> AERIAL_TREE_LEAVES_ITEM = ITEMS.register("aerial_tree_leaves", () -> new BlockItem(AERIAL_TREE_LEAVES.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> AERIAL_TREE_PLANKS_ITEM = ITEMS.register("aerial_tree_planks", () -> new BurnableBlockItem(AERIAL_TREE_PLANKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<Item> CHISELED_AERIAL_TREE_PLANKS_ITEM = ITEMS.register("chiseled_aerial_tree_planks", () -> new BurnableBlockItem(CHISELED_AERIAL_TREE_PLANKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<Item> AERIAL_TREE_BOOKSHELF_ITEM = ITEMS.register("aerial_tree_bookshelf", () -> new BlockItem(AERIAL_TREE_BOOKSHELF.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> AERIAL_TREE_SAPLING_ITEM = ITEMS.register("aerial_tree_sapling", () -> new BlockItem(AERIAL_TREE_SAPLING.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//golden beech
	public static final RegistryObject<RotatedPillarBlock> GOLDEN_BEECH_LOG = BLOCKS.register("golden_beech_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(AERIAL_TREE_LOG.get())));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_GOLDEN_BEECH_LOG = BLOCKS.register("stripped_golden_beech_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(GOLDEN_BEECH_LOG.get()).harvestTool(ToolType.AXE)));
	public static final RegistryObject<Block> GOLDEN_BEECH_PLANKS = BLOCKS.register("golden_beech_planks", () -> new Block(AbstractBlock.Properties.from(GOLDEN_BEECH_LOG.get())));
	public static final RegistryObject<Block> CHISELED_GOLDEN_BEECH_PLANKS = BLOCKS.register("chiseled_golden_beech_planks", () -> new Block(AbstractBlock.Properties.from(GOLDEN_BEECH_PLANKS.get())));
	public static final RegistryObject<Block> GOLDEN_BEECH_LEAVES = BLOCKS.register("golden_beech_leaves", () -> new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> GOLDEN_BEECH_BOOKSHELF = BLOCKS.register("golden_beech_bookshelf", () -> new AerialHellBookshelfBlock(AbstractBlock.Properties.from(GOLDEN_BEECH_PLANKS.get())));
	public static final RegistryObject<SaplingBlock> GOLDEN_BEECH_SAPLING = BLOCKS.register("golden_beech_sapling", () -> new SaplingBlock(new GoldenBeechTree(), AbstractBlock.Properties.from(Blocks.OAK_SAPLING)));
	public static final RegistryObject<Item> GOLDEN_BEECH_LOG_ITEM = ITEMS.register("golden_beech_log", () -> new BurnableBlockItem(GOLDEN_BEECH_LOG.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<Item> STRIPPED_GOLDEN_BEECH_LOG_ITEM = ITEMS.register("stripped_golden_beech_log", () -> new BurnableBlockItem(STRIPPED_GOLDEN_BEECH_LOG.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<Item> GOLDEN_BEECH_PLANKS_ITEM = ITEMS.register("golden_beech_planks", () -> new BurnableBlockItem(GOLDEN_BEECH_PLANKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<Item> CHISELED_GOLDEN_BEECH_PLANKS_ITEM = ITEMS.register("chiseled_golden_beech_planks", () -> new BurnableBlockItem(CHISELED_GOLDEN_BEECH_PLANKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<Item> GOLDEN_BEECH_LEAVES_ITEM = ITEMS.register("golden_beech_leaves", () -> new BlockItem(GOLDEN_BEECH_LEAVES.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLDEN_BEECH_BOOKSHELF_ITEM = ITEMS.register("golden_beech_bookshelf", () -> new BlockItem(GOLDEN_BEECH_BOOKSHELF.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLDEN_BEECH_SAPLING_ITEM = ITEMS.register("golden_beech_sapling", () -> new BlockItem(GOLDEN_BEECH_SAPLING.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//cropper pine
	public static final RegistryObject<RotatedPillarBlock> COPPER_PINE_LOG = BLOCKS.register("copper_pine_log", () -> new RotatedPillarBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_COPPER_PINE_LOG = BLOCKS.register("stripped_copper_pine_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(COPPER_PINE_LOG.get()).harvestTool(ToolType.AXE)));
	public static final RegistryObject<Block> COPPER_PINE_PLANKS = BLOCKS.register("copper_pine_planks", () -> new Block(AbstractBlock.Properties.from(COPPER_PINE_LOG.get())));
	public static final RegistryObject<Block> COPPER_PINE_LEAVES = BLOCKS.register("copper_pine_leaves", () -> new CopperPineLeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES).harvestTool(ToolType.HOE)));
	public static final RegistryObject<Block> COPPER_PINE_BOOKSHELF = BLOCKS.register("copper_pine_bookshelf", () -> new AerialHellBookshelfBlock(AbstractBlock.Properties.from(COPPER_PINE_PLANKS.get())));
	public static final RegistryObject<SaplingBlock> COPPER_PINE_SAPLING = BLOCKS.register("copper_pine_sapling", () -> new SaplingBlock(new CopperPine(), AbstractBlock.Properties.from(Blocks.OAK_SAPLING)));
	public static final RegistryObject<Item> COPPER_PINE_LOG_ITEM = ITEMS.register("copper_pine_log", () -> new BurnableBlockItem(COPPER_PINE_LOG.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<Item> STRIPPED_COPPER_PINE_LOG_ITEM = ITEMS.register("stripped_copper_pine_log", () -> new BurnableBlockItem(STRIPPED_COPPER_PINE_LOG.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<Item> COPPER_PINE_PLANKS_ITEM = ITEMS.register("copper_pine_planks", () -> new BurnableBlockItem(COPPER_PINE_PLANKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<Item> COPPER_PINE_LEAVES_ITEM = ITEMS.register("copper_pine_leaves", () -> new BlockItem(COPPER_PINE_LEAVES.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> COPPER_PINE_BOOKSHELF_ITEM = ITEMS.register("copper_pine_bookshelf", () -> new BlockItem(COPPER_PINE_BOOKSHELF.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> COPPER_PINE_SAPLING_ITEM = ITEMS.register("copper_pine_sapling", () -> new BlockItem(COPPER_PINE_SAPLING.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//lapis robinia
	public static final RegistryObject<RotatedPillarBlock> LAPIS_ROBINIA_LOG = BLOCKS.register("lapis_robinia_log", () -> new RotatedPillarBlock(COPPER_PINE_MATERIAL));
	public static final RegistryObject<RotatedPillarBlock> STRIPPED_LAPIS_ROBINIA_LOG = BLOCKS.register("stripped_lapis_robinia_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(LAPIS_ROBINIA_LOG.get()).harvestTool(ToolType.AXE)));
	public static final RegistryObject<Block> LAPIS_ROBINIA_LEAVES = BLOCKS.register("lapis_robinia_leaves", () -> new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)));
	public static final RegistryObject<Block> LAPIS_ROBINIA_PLANKS = BLOCKS.register("lapis_robinia_planks", () -> new Block(AbstractBlock.Properties.from(LAPIS_ROBINIA_LOG.get())));
	public static final RegistryObject<Block> LAPIS_ROBINIA_BOOKSHELF = BLOCKS.register("lapis_robinia_bookshelf", () -> new AerialHellBookshelfBlock(AbstractBlock.Properties.from(LAPIS_ROBINIA_PLANKS.get())));
	public static final RegistryObject<SaplingBlock> LAPIS_ROBINIA_SAPLING = BLOCKS.register("lapis_robinia_sapling", () -> new SaplingBlock(new LapisRobinia(), AbstractBlock.Properties.from(Blocks.OAK_SAPLING)));
	public static final RegistryObject<Item> LAPIS_ROBINIA_LOG_ITEM = ITEMS.register("lapis_robinia_log", () -> new BurnableBlockItem(LAPIS_ROBINIA_LOG.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 400));
	public static final RegistryObject<Item> STRIPPED_LAPIS_ROBINIA_LOG_ITEM = ITEMS.register("stripped_lapis_robinia_log", () -> new BurnableBlockItem(STRIPPED_LAPIS_ROBINIA_LOG.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 400));
	public static final RegistryObject<Item> LAPIS_ROBINIA_LEAVES_ITEM = ITEMS.register("lapis_robinia_leaves", () -> new BlockItem(LAPIS_ROBINIA_LEAVES.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> LAPIS_ROBINIA_PLANKS_ITEM = ITEMS.register("lapis_robinia_planks", () -> new BurnableBlockItem(LAPIS_ROBINIA_PLANKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<Item> LAPIS_ROBINIA_BOOKSHELF_ITEM = ITEMS.register("lapis_robinia_bookshelf", () -> new BlockItem(LAPIS_ROBINIA_BOOKSHELF.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> LAPIS_ROBINIA_SAPLING_ITEM = ITEMS.register("lapis_robinia_sapling", () -> new BlockItem(LAPIS_ROBINIA_SAPLING.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//ladder
	public static final RegistryObject<LadderBlock> SKY_LADDER = BLOCKS.register("sky_ladder", () -> new LadderBlock(AbstractBlock.Properties.from(AERIAL_TREE_PLANKS.get()).notSolid()));
	public static final RegistryObject<Item> SKY_LADDER_ITEM = ITEMS.register("sky_ladder", () -> new BlockItem(SKY_LADDER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
		
	//natural blocks and items
	public static final RegistryObject<Block> STELLAR_STONE = BLOCKS.register("stellar_stone", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)));
	public static final RegistryObject<Block> STELLAR_COBBLESTONE = BLOCKS.register("stellar_cobblestone", () -> new Block(AbstractBlock.Properties.from(Blocks.COBBLESTONE)));
	public static final RegistryObject<Block> STELLAR_STONE_BRICKS = BLOCKS.register("stellar_stone_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE_BRICKS).hardnessAndResistance(0.5F, 10.0F).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Block> MOSSY_STELLAR_STONE = BLOCKS.register("mossy_stellar_stone", () -> new Block(AbstractBlock.Properties.from(STELLAR_STONE.get())));
	public static final RegistryObject<Block> MOSSY_STELLAR_COBBLESTONE = BLOCKS.register("mossy_stellar_cobblestone", () -> new Block(AbstractBlock.Properties.from(STELLAR_STONE.get())));
	public static final RegistryObject<Block> STELLAR_CLAY = BLOCKS.register("stellar_clay", () -> new Block(AbstractBlock.Properties.from(Blocks.CLAY).harvestTool(ToolType.SHOVEL)));
	public static final RegistryObject<Block> GLAUCOPHANITE = BLOCKS.register("glaucophanite",() -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> POLISHED_GLAUCOPHANITE = BLOCKS.register("polished_glaucophanite",() -> new Block(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Item> STELLAR_STONE_ITEM = ITEMS.register("stellar_stone", () -> new BlockItem(STELLAR_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STELLAR_COBBLESTONE_ITEM = ITEMS.register("stellar_cobblestone", () -> new BlockItem(STELLAR_COBBLESTONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> MOSSY_STELLAR_STONE_ITEM = ITEMS.register("mossy_stellar_stone", () -> new BlockItem(MOSSY_STELLAR_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> MOSSY_STELLAR_COBBLESTONE_ITEM = ITEMS.register("mossy_stellar_cobblestone", () -> new BlockItem(MOSSY_STELLAR_COBBLESTONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STELLAR_CLAY_ITEM = ITEMS.register("stellar_clay", () -> new BlockItem(STELLAR_CLAY.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STELLAR_STONE_BRICKS_ITEM = ITEMS.register("stellar_stone_bricks", () -> new BlockItem(STELLAR_STONE_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GLAUCOPHANITE_ITEM = ITEMS.register("glaucophanite", () -> new BlockItem(GLAUCOPHANITE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> POLISHED_GLAUCOPHANITE_ITEM = ITEMS.register("polished_glaucophanite", () -> new BlockItem(POLISHED_GLAUCOPHANITE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//crystal
	public static final RegistryObject<Block> CRYSTAL_BLOCK = BLOCKS.register("crystal_block", () -> new Block(AbstractBlock.Properties.from(Blocks.GLASS).setLightLevel((state) -> 14)));
	public static final RegistryObject<Item> CRYSTAL_BLOCK_ITEM = ITEMS.register("crystal_block", () -> new BlockItem(CRYSTAL_BLOCK.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Block> CRYSTAL_BRICKS = BLOCKS.register("crystal_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE_BRICKS).setLightLevel((state) -> 9)));
	public static final RegistryObject<Item> CRYSTAL_BRICKS_ITEM = ITEMS.register("crystal_bricks", () -> new BlockItem(CRYSTAL_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<SlabBlock> CRYSTAL_BRICKS_SLAB = BLOCKS.register("crystal_bricks_slab", () -> new SlabBlock(AbstractBlock.Properties.from(CRYSTAL_BRICKS.get())));
	public static final RegistryObject<Item> CRYSTAL_BRICKS_SLAB_ITEM = ITEMS.register("crystal_bricks_slab", () -> new BlockItem(CRYSTAL_BRICKS_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<StairsBlock> CRYSTAL_BRICKS_STAIRS = BLOCKS.register("crystal_bricks_stairs", () -> new StairsBlock(() -> CRYSTAL_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(CRYSTAL_BRICKS.get())));
	public static final RegistryObject<Item> CRYSTAL_BRICKS_STAIRS_ITEM = ITEMS.register("crystal_bricks_stairs", () -> new BlockItem(CRYSTAL_BRICKS_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WallBlock> CRYSTAL_BRICKS_WALL = BLOCKS.register("crystal_bricks_wall", () -> new WallBlock(AbstractBlock.Properties.from(CRYSTAL_BRICKS.get())));
	public static final RegistryObject<Item> CRYSTAL_BRICKS_WALL_ITEM = ITEMS.register("crystal_bricks_wall", () -> new BlockItem(CRYSTAL_BRICKS_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Block> STELLAR_STONE_CRYSTAL_BLOCK = BLOCKS.register("stellar_stone_crystal_block", () -> new Block(AbstractBlock.Properties.from(CRYSTAL_BLOCK.get()).setLightLevel((state) -> 13)));
	public static final RegistryObject<Item> STELLAR_STONE_CRYSTAL_BLOCK_ITEM = ITEMS.register("stellar_stone_crystal_block", () -> new BlockItem(STELLAR_STONE_CRYSTAL_BLOCK.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Block> CRYSTALLIZED_LEAVES = BLOCKS.register("crystallized_leaves", () -> new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES).setLightLevel((state) -> 12)));
	public static final RegistryObject<Block> CRYSTALLIZED_FIRE = BLOCKS.register("crystallized_fire", () -> new GlassBlock(AbstractBlock.Properties.from(Blocks.GLASS).setLightLevel((state) -> 12).zeroHardnessAndResistance()));
	public static final RegistryObject<Item> CRYSTALLIZED_LEAVES_ITEM = ITEMS.register("crystallized_leaves", () -> new BlockItem(CRYSTALLIZED_LEAVES.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRYSTAL = ITEMS.register("crystal", () -> new Item(new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STELLAR_STONE_CRYSTAL = ITEMS.register("stellar_stone_crystal", () -> new Item(new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//grass & dirt
	public static final RegistryObject<Block> STELLAR_GRASS_BLOCK = BLOCKS.register("stellar_grass_block", () -> new StellarGrassBlock(AbstractBlock.Properties.from(Blocks.GRASS_BLOCK).harvestTool(ToolType.SHOVEL)));
	public static final RegistryObject<Block> CHISELED_STELLAR_GRASS_BLOCK = BLOCKS.register("chiseled_stellar_grass_block", () -> new StellarGrassBlock(AbstractBlock.Properties.from(STELLAR_GRASS_BLOCK.get()).harvestTool(ToolType.SHOVEL)));
	public static final RegistryObject<Block> STELLAR_DIRT = BLOCKS.register("stellar_dirt", () -> new Block(AbstractBlock.Properties.from(Blocks.DIRT).harvestTool(ToolType.SHOVEL)));
	public static final RegistryObject<Block> STELLAR_COARSE_DIRT = BLOCKS.register("stellar_coarse_dirt", () -> new Block(AbstractBlock.Properties.from(Blocks.COARSE_DIRT).harvestTool(ToolType.SHOVEL)));
	public static final RegistryObject<Block> CHISELED_STELLAR_DIRT = BLOCKS.register("chiseled_stellar_dirt", () -> new Block(AbstractBlock.Properties.from(STELLAR_DIRT.get()).harvestTool(ToolType.SHOVEL)));
	public static final RegistryObject<Item> STELLAR_GRASS_BLOCK_ITEM = ITEMS.register("stellar_grass_block", () -> new BlockItem(STELLAR_GRASS_BLOCK.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CHISELED_STELLAR_GRASS_BLOCK_ITEM = ITEMS.register("chiseled_stellar_grass_block", () -> new BlockItem(CHISELED_STELLAR_GRASS_BLOCK.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STELLAR_DIRT_ITEM = ITEMS.register("stellar_dirt", () -> new BlockItem(STELLAR_DIRT.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STELLAR_COARSE_DIRT_ITEM = ITEMS.register("stellar_coarse_dirt", () -> new BlockItem(STELLAR_COARSE_DIRT.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CHISELED_STELLAR_DIRT_ITEM = ITEMS.register("chiseled_stellar_dirt", () -> new BlockItem(CHISELED_STELLAR_DIRT.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//slippery sand
	public static final RegistryObject<Block> SLIPPERY_SAND = BLOCKS.register("slippery_sand", () -> new Block(AbstractBlock.Properties.from(Blocks.SAND).harvestTool(ToolType.SHOVEL).slipperiness(1.025F)));
	public static final RegistryObject<Block> SLIPPERY_SAND_STONE = BLOCKS.register("slippery_sand_stone", () -> new Block(AbstractBlock.Properties.from(Blocks.SANDSTONE).harvestTool(ToolType.PICKAXE).slipperiness(1.01F)));
	public static final RegistryObject<Block> SLIPPERY_SAND_STONE_BRICKS = BLOCKS.register("slippery_sand_stone_bricks", () -> new Block(AbstractBlock.Properties.from(SLIPPERY_SAND_STONE.get()).harvestTool(ToolType.PICKAXE).slipperiness(1.005F)));
	public static final RegistryObject<Block> CUT_SLIPPERY_SAND_STONE = BLOCKS.register("cut_slippery_sand_stone", () -> new Block(AbstractBlock.Properties.from(SLIPPERY_SAND_STONE.get()).harvestTool(ToolType.PICKAXE).slipperiness(1.005F)));
	public static final RegistryObject<Item> SLIPPERY_SAND_ITEM = ITEMS.register("slippery_sand", () -> new BlockItem(SLIPPERY_SAND.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_ITEM = ITEMS.register("slippery_sand_stone", () -> new BlockItem(SLIPPERY_SAND_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_BRICKS_ITEM = ITEMS.register("slippery_sand_stone_bricks", () -> new BlockItem(SLIPPERY_SAND_STONE_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CUT_SLIPPERY_SAND_STONE_ITEM = ITEMS.register("cut_slippery_sand_stone", () -> new BlockItem(CUT_SLIPPERY_SAND_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//glass and glass pane
	public static final RegistryObject<Block> SLIPPERY_SAND_GLASS = BLOCKS.register("slippery_sand_glass", () -> new GlassBlock(AbstractBlock.Properties.from(Blocks.GLASS).slipperiness(1.01F).setAllowsSpawn((state, reader, pos, entity) -> false).setOpaque((state, reader, pos) -> false).setSuffocates((state, reader, pos) -> false).setBlocksVision((state, reader, pos) -> false)));
	public static final RegistryObject<Block> RED_SLIPPERY_SAND_GLASS = BLOCKS.register("red_slippery_sand_glass", () -> new GlassBlock(AbstractBlock.Properties.from(Blocks.GLASS).slipperiness(1.01F).setAllowsSpawn((state, reader, pos, entity) -> false).setOpaque((state, reader, pos) -> false).setSuffocates((state, reader, pos) -> false).setBlocksVision((state, reader, pos) -> false)));
	public static final RegistryObject<Block> BLACK_SLIPPERY_SAND_GLASS = BLOCKS.register("black_slippery_sand_glass", () -> new GlassBlock(AbstractBlock.Properties.from(Blocks.GLASS).slipperiness(1.01F).setAllowsSpawn((state, reader, pos, entity) -> false).setOpaque((state, reader, pos) -> false).setSuffocates((state, reader, pos) -> false).setBlocksVision((state, reader, pos) -> false)));
	public static final RegistryObject<Block> BLUE_SLIPPERY_SAND_GLASS = BLOCKS.register("blue_slippery_sand_glass", () -> new GlassBlock(AbstractBlock.Properties.from(Blocks.GLASS).slipperiness(1.01F).setAllowsSpawn((state, reader, pos, entity) -> false).setOpaque((state, reader, pos) -> false).setSuffocates((state, reader, pos) -> false).setBlocksVision((state, reader, pos) -> false)));
	public static final RegistryObject<Block> GREEN_SLIPPERY_SAND_GLASS = BLOCKS.register("green_slippery_sand_glass", () -> new GlassBlock(AbstractBlock.Properties.from(Blocks.GLASS).slipperiness(1.01F).setAllowsSpawn((state, reader, pos, entity) -> false).setOpaque((state, reader, pos) -> false).setSuffocates((state, reader, pos) -> false).setBlocksVision((state, reader, pos) -> false)));
	public static final RegistryObject<Block> SLIPPERY_SAND_GLASS_PANE = BLOCKS.register("slippery_sand_glass_pane", () -> new PaneBlock(AbstractBlock.Properties.create(Material.GLASS).slipperiness(1.01F).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
	public static final RegistryObject<Block> RED_SLIPPERY_SAND_GLASS_PANE = BLOCKS.register("red_slippery_sand_glass_pane", () -> new PaneBlock(AbstractBlock.Properties.create(Material.GLASS).slipperiness(1.01F).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
	public static final RegistryObject<Block> BLACK_SLIPPERY_SAND_GLASS_PANE = BLOCKS.register("black_slippery_sand_glass_pane", () -> new PaneBlock(AbstractBlock.Properties.create(Material.GLASS).slipperiness(1.01F).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
	public static final RegistryObject<Block> BLUE_SLIPPERY_SAND_GLASS_PANE = BLOCKS.register("blue_slippery_sand_glass_pane", () -> new PaneBlock(AbstractBlock.Properties.create(Material.GLASS).slipperiness(1.01F).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
	public static final RegistryObject<Block> GREEN_SLIPPERY_SAND_GLASS_PANE = BLOCKS.register("green_slippery_sand_glass_pane", () -> new PaneBlock(AbstractBlock.Properties.create(Material.GLASS).slipperiness(1.01F).hardnessAndResistance(0.3F).sound(SoundType.GLASS).notSolid()));
	public static final RegistryObject<Item> SLIPPERY_SAND_GLASS_ITEM = ITEMS.register("slippery_sand_glass", () -> new BlockItem(SLIPPERY_SAND_GLASS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> RED_SLIPPERY_SAND_GLASS_ITEM = ITEMS.register("red_slippery_sand_glass", () -> new BlockItem(RED_SLIPPERY_SAND_GLASS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> BLACK_SLIPPERY_SAND_GLASS_ITEM = ITEMS.register("black_slippery_sand_glass", () -> new BlockItem(BLACK_SLIPPERY_SAND_GLASS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> BLUE_SLIPPERY_SAND_GLASS_ITEM = ITEMS.register("blue_slippery_sand_glass", () -> new BlockItem(BLUE_SLIPPERY_SAND_GLASS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GREEN_SLIPPERY_SAND_GLASS_ITEM = ITEMS.register("green_slippery_sand_glass", () -> new BlockItem(GREEN_SLIPPERY_SAND_GLASS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> SLIPPERY_SAND_GLASS_PANE_ITEM = ITEMS.register("slippery_sand_glass_pane", () -> new BlockItem(SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> RED_SLIPPERY_SAND_GLASS_PANE_ITEM = ITEMS.register("red_slippery_sand_glass_pane", () -> new BlockItem(RED_SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> BLACK_SLIPPERY_SAND_GLASS_PANE_ITEM = ITEMS.register("black_slippery_sand_glass_pane", () -> new BlockItem(BLACK_SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> BLUE_SLIPPERY_SAND_GLASS_PANE_ITEM = ITEMS.register("blue_slippery_sand_glass_pane", () -> new BlockItem(BLUE_SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GREEN_SLIPPERY_SAND_GLASS_PANE_ITEM = ITEMS.register("green_slippery_sand_glass_pane", () -> new BlockItem(GREEN_SLIPPERY_SAND_GLASS_PANE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
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
	public static final RegistryObject<Block> MUD_BRICKS = BLOCKS.register("mud_bricks", () -> new CoreProtectedBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> CRACKED_MUD_BRICKS = BLOCKS.register("cracked_mud_bricks", () -> new CoreProtectedBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> CHISELED_MUD_BRICKS = BLOCKS.register("chiseled_mud_bricks", () -> new CoreProtectedBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> LIGHT_MUD_BRICKS = BLOCKS.register("light_mud_bricks", () -> new CoreProtectedBlock(AbstractBlock.Properties.from(MUD_BRICKS.get()).setLightLevel((state) -> 11)));
	public static final RegistryObject<Block> CRACKED_LIGHT_MUD_BRICKS = BLOCKS.register("cracked_light_mud_bricks", () -> new CoreProtectedBlock(AbstractBlock.Properties.from(MUD_BRICKS.get())));
	public static final RegistryObject<Block> LUNATIC_STONE = BLOCKS.register("lunatic_stone", () -> new CoreProtectedBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> DARK_LUNATIC_STONE = BLOCKS.register("dark_lunatic_stone", () -> new CoreProtectedBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> CRACKED_LUNATIC_STONE = BLOCKS.register("cracked_lunatic_stone", () -> new CoreProtectedBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> CHISELED_LUNATIC_STONE = BLOCKS.register("chiseled_lunatic_stone", () -> new CoreProtectedBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> LIGHT_LUNATIC_STONE = BLOCKS.register("light_lunatic_stone", () -> new CoreProtectedBlock(AbstractBlock.Properties.from(LUNATIC_STONE.get()).setLightLevel((state) -> 11)));
	public static final RegistryObject<Block> CRACKED_LIGHT_LUNATIC_STONE = BLOCKS.register("cracked_light_lunatic_stone", () -> new CoreProtectedBlock(AbstractBlock.Properties.from(LUNATIC_STONE.get())));
	public static final RegistryObject<Block> GOLDEN_NETHER_BRICKS  = BLOCKS.register("golden_nether_bricks", () -> new CoreProtectedBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> CRACKED_GOLDEN_NETHER_BRICKS  = BLOCKS.register("cracked_golden_nether_bricks", () -> new CoreProtectedBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> CHISELED_GOLDEN_NETHER_BRICKS  = BLOCKS.register("chiseled_golden_nether_bricks", () -> new CoreProtectedBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> LIGHT_GOLDEN_NETHER_BRICKS = BLOCKS.register("light_golden_nether_bricks", () -> new CoreProtectedBlock(AbstractBlock.Properties.from(GOLDEN_NETHER_BRICKS.get()).setLightLevel((state) -> 11)));
	public static final RegistryObject<Block> CRACKED_LIGHT_GOLDEN_NETHER_BRICKS = BLOCKS.register("cracked_light_golden_nether_bricks", () -> new CoreProtectedBlock(AbstractBlock.Properties.from(GOLDEN_NETHER_BRICKS.get())));
	public static final RegistryObject<RotatedPillarBlock> LUNATIC_PILLAR = BLOCKS.register("lunatic_pillar", () -> new CoreProtectedRotatedPillarBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<RotatedPillarBlock> LUNATIC_PILLAR_TOP = BLOCKS.register("lunatic_pillar_top", () -> new CoreProtectedRotatedPillarBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.QUARTZ).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> VOLUCITE_STONE = BLOCKS.register("volucite_stone", () -> new CoreProtectedBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> CRACKED_VOLUCITE_STONE = BLOCKS.register("cracked_volucite_stone", () -> new CoreProtectedBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> CHISELED_VOLUCITE_STONE = BLOCKS.register("chiseled_volucite_stone", () -> new CoreProtectedBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE)));
	public static final RegistryObject<Block> LIGHT_VOLUCITE_STONE = BLOCKS.register("light_volucite_stone", () -> new CoreProtectedBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE).setLightLevel((state) -> 11)));
	public static final RegistryObject<Block> CRACKED_LIGHT_VOLUCITE_STONE = BLOCKS.register("cracked_light_volucite_stone", () -> new CoreProtectedBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2.0F, 6.0F).sound(SoundType.STONE)));

	public static final RegistryObject<Item> MUD_BRICKS_ITEM = ITEMS.register("mud_bricks", () -> new BlockItem(MUD_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_MUD_BRICKS_ITEM = ITEMS.register("cracked_mud_bricks", () -> new BlockItem(CRACKED_MUD_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CHISELED_MUD_BRICKS_ITEM = ITEMS.register("chiseled_mud_bricks", () -> new BlockItem(CHISELED_MUD_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> LIGHT_MUD_BRICKS_ITEM = ITEMS.register("light_mud_bricks", () -> new BlockItem(LIGHT_MUD_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_LIGHT_MUD_BRICKS_ITEM = ITEMS.register("cracked_light_mud_bricks", () -> new BlockItem(CRACKED_LIGHT_MUD_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> LUNATIC_STONE_ITEM = ITEMS.register("lunatic_stone", () -> new BlockItem(LUNATIC_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> DARK_LUNATIC_STONE_ITEM = ITEMS.register("dark_lunatic_stone", () -> new BlockItem(DARK_LUNATIC_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
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
	
	//dungeon cores
	public static final RegistryObject<Block> MUD_DUNGEON_CORE = BLOCKS.register("mud_dungeon_core", () -> new DungeonCoreBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(30.0F, 1200.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(3).setRequiresTool(), 181));
	public static final RegistryObject<Block> LUNATIC_DUNGEON_CORE = BLOCKS.register("lunatic_dungeon_core", () -> new DungeonCoreBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(40.0F, 1200.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(3).setRequiresTool(), 181));
	public static final RegistryObject<Block> GOLDEN_NETHER_DUNGEON_CORE = BLOCKS.register("golden_nether_dungeon_core", () -> new DungeonCoreBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool(), 101));
	public static final RegistryObject<Block> VOLUCITE_DUNGEON_CORE = BLOCKS.register("volucite_dungeon_core", () -> new DungeonCoreBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(50.0F, 1200.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool(), 101));
	
	public static final RegistryObject<Item> MUD_DUNGEON_CORE_ITEM = ITEMS.register("mud_dungeon_core", () -> new BlockItem(MUD_DUNGEON_CORE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> LUNATIC_DUNGEON_CORE_ITEM = ITEMS.register("lunatic_dungeon_core", () -> new BlockItem(LUNATIC_DUNGEON_CORE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLDEN_NETHER_DUNGEON_CORE_ITEM = ITEMS.register("golden_nether_dungeon_core", () -> new BlockItem(GOLDEN_NETHER_DUNGEON_CORE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> VOLUCITE_DUNGEON_CORE_ITEM = ITEMS.register("volucite_dungeon_core", () -> new BlockItem(VOLUCITE_DUNGEON_CORE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//dungeons slabs, stairs & walls
	public static final RegistryObject<SlabBlock> MUD_BRICKS_SLAB = BLOCKS.register("mud_bricks_slab", () -> new CoreProtectedSlabBlock(AbstractBlock.Properties.from(MUD_BRICKS.get())));
	public static final RegistryObject<StairsBlock> MUD_BRICKS_STAIRS = BLOCKS.register("mud_bricks_stairs", () -> new CoreProtectedStairsBlock(() -> MUD_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> MUD_BRICKS_WALL = BLOCKS.register("mud_bricks_wall", () -> new CoreProtectedWallBlock(AbstractBlock.Properties.from(MUD_BRICKS.get())));
	public static final RegistryObject<SlabBlock> CRACKED_MUD_BRICKS_SLAB = BLOCKS.register("cracked_mud_bricks_slab", () -> new CoreProtectedSlabBlock(AbstractBlock.Properties.from(CRACKED_MUD_BRICKS.get())));
	public static final RegistryObject<StairsBlock> CRACKED_MUD_BRICKS_STAIRS = BLOCKS.register("cracked_mud_bricks_stairs", () -> new CoreProtectedStairsBlock(() -> CRACKED_MUD_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> CRACKED_MUD_BRICKS_WALL = BLOCKS.register("cracked_mud_bricks_wall", () -> new CoreProtectedWallBlock(AbstractBlock.Properties.from(CRACKED_MUD_BRICKS.get())));
	public static final RegistryObject<SlabBlock> VOLUCITE_STONE_SLAB = BLOCKS.register("volucite_stone_slab", () -> new CoreProtectedSlabBlock(AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<StairsBlock> VOLUCITE_STONE_STAIRS = BLOCKS.register("volucite_stone_stairs", () -> new CoreProtectedStairsBlock(() -> VOLUCITE_STONE.get().getDefaultState(), AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> VOLUCITE_STONE_WALL = BLOCKS.register("volucite_stone_wall", () -> new CoreProtectedWallBlock(AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<SlabBlock> CRACKED_VOLUCITE_STONE_SLAB = BLOCKS.register("cracked_volucite_stone_slab", () -> new CoreProtectedSlabBlock(AbstractBlock.Properties.from(CRACKED_VOLUCITE_STONE.get())));
	public static final RegistryObject<StairsBlock> CRACKED_VOLUCITE_STONE_STAIRS = BLOCKS.register("cracked_volucite_stone_stairs", () -> new CoreProtectedStairsBlock(() -> CRACKED_VOLUCITE_STONE.get().getDefaultState(), AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> CRACKED_VOLUCITE_STONE_WALL = BLOCKS.register("cracked_volucite_stone_wall", () -> new CoreProtectedWallBlock(AbstractBlock.Properties.from(CRACKED_VOLUCITE_STONE.get())));
	public static final RegistryObject<SlabBlock> LUNATIC_STONE_SLAB = BLOCKS.register("lunatic_stone_slab", () -> new CoreProtectedSlabBlock(AbstractBlock.Properties.from(LUNATIC_STONE.get())));
	public static final RegistryObject<StairsBlock> LUNATIC_STONE_STAIRS = BLOCKS.register("lunatic_stone_stairs", () -> new CoreProtectedStairsBlock(() -> LUNATIC_STONE.get().getDefaultState(), AbstractBlock.Properties.from(LUNATIC_STONE.get())));
	public static final RegistryObject<WallBlock> LUNATIC_STONE_WALL = BLOCKS.register("lunatic_stone_wall", () -> new CoreProtectedWallBlock(AbstractBlock.Properties.from(LUNATIC_STONE.get())));
	public static final RegistryObject<SlabBlock> DARK_LUNATIC_STONE_SLAB = BLOCKS.register("dark_lunatic_stone_slab", () -> new CoreProtectedSlabBlock(AbstractBlock.Properties.from(DARK_LUNATIC_STONE.get())));
	public static final RegistryObject<StairsBlock> DARK_LUNATIC_STONE_STAIRS = BLOCKS.register("dark_lunatic_stone_stairs", () -> new CoreProtectedStairsBlock(() -> DARK_LUNATIC_STONE.get().getDefaultState(), AbstractBlock.Properties.from(DARK_LUNATIC_STONE.get())));
	public static final RegistryObject<WallBlock> DARK_LUNATIC_STONE_WALL = BLOCKS.register("dark_lunatic_stone_wall", () -> new CoreProtectedWallBlock(AbstractBlock.Properties.from(DARK_LUNATIC_STONE.get())));
	public static final RegistryObject<SlabBlock> CRACKED_LUNATIC_STONE_SLAB = BLOCKS.register("cracked_lunatic_stone_slab", () -> new CoreProtectedSlabBlock(AbstractBlock.Properties.from(CRACKED_LUNATIC_STONE.get())));
	public static final RegistryObject<StairsBlock> CRACKED_LUNATIC_STONE_STAIRS = BLOCKS.register("cracked_lunatic_stone_stairs", () -> new CoreProtectedStairsBlock(() -> CRACKED_LUNATIC_STONE.get().getDefaultState(), AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> CRACKED_LUNATIC_STONE_WALL = BLOCKS.register("cracked_lunatic_stone_wall", () -> new CoreProtectedWallBlock(AbstractBlock.Properties.from(CRACKED_LUNATIC_STONE.get())));
	public static final RegistryObject<SlabBlock> GOLDEN_NETHER_BRICKS_SLAB = BLOCKS.register("golden_nether_bricks_slab", () -> new CoreProtectedSlabBlock(AbstractBlock.Properties.from(GOLDEN_NETHER_BRICKS.get())));
	public static final RegistryObject<StairsBlock> GOLDEN_NETHER_BRICKS_STAIRS = BLOCKS.register("golden_nether_bricks_stairs", () -> new CoreProtectedStairsBlock(() -> GOLDEN_NETHER_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> GOLDEN_NETHER_BRICKS_WALL = BLOCKS.register("golden_nether_bricks_wall", () -> new CoreProtectedWallBlock(AbstractBlock.Properties.from(GOLDEN_NETHER_BRICKS.get())));
	public static final RegistryObject<SlabBlock> CRACKED_GOLDEN_NETHER_BRICKS_SLAB = BLOCKS.register("cracked_golden_nether_bricks_slab", () -> new CoreProtectedSlabBlock(AbstractBlock.Properties.from(CRACKED_GOLDEN_NETHER_BRICKS.get())));
	public static final RegistryObject<StairsBlock> CRACKED_GOLDEN_NETHER_BRICKS_STAIRS = BLOCKS.register("cracked_golden_nether_bricks_stairs", () -> new CoreProtectedStairsBlock(() -> CRACKED_GOLDEN_NETHER_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<WallBlock> CRACKED_GOLDEN_NETHER_BRICKS_WALL = BLOCKS.register("cracked_golden_nether_bricks_wall", () -> new CoreProtectedWallBlock(AbstractBlock.Properties.from(CRACKED_GOLDEN_NETHER_BRICKS.get())));

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
	public static final RegistryObject<Item> DARK_LUNATIC_STONE_SLAB_ITEM = ITEMS.register("dark_lunatic_stone_slab", () -> new BlockItem(DARK_LUNATIC_STONE_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> DARK_LUNATIC_STONE_STAIRS_ITEM = ITEMS.register("dark_lunatic_stone_stairs", () -> new BlockItem(DARK_LUNATIC_STONE_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> DARK_LUNATIC_STONE_WALL_ITEM = ITEMS.register("dark_lunatic_stone_wall", () -> new BlockItem(DARK_LUNATIC_STONE_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_LUNATIC_STONE_SLAB_ITEM = ITEMS.register("cracked_lunatic_stone_slab", () -> new BlockItem(CRACKED_LUNATIC_STONE_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_LUNATIC_STONE_STAIRS_ITEM = ITEMS.register("cracked_lunatic_stone_stairs", () -> new BlockItem(CRACKED_LUNATIC_STONE_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_LUNATIC_STONE_WALL_ITEM = ITEMS.register("cracked_lunatic_stone_wall", () -> new BlockItem(CRACKED_LUNATIC_STONE_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLDEN_NETHER_BRICKS_SLAB_ITEM = ITEMS.register("golden_nether_bricks_slab", () -> new BlockItem(GOLDEN_NETHER_BRICKS_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLDEN_NETHER_BRICKS_STAIRS_ITEM = ITEMS.register("golden_nether_bricks_stairs", () -> new BlockItem(GOLDEN_NETHER_BRICKS_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLDEN_NETHER_BRICKS_WALL_ITEM = ITEMS.register("golden_nether_bricks_wall", () -> new BlockItem(GOLDEN_NETHER_BRICKS_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_GOLDEN_NETHER_BRICKS_SLAB_ITEM = ITEMS.register("cracked_golden_nether_bricks_slab", () -> new BlockItem(CRACKED_GOLDEN_NETHER_BRICKS_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_GOLDEN_NETHER_BRICKS_STAIRS_ITEM = ITEMS.register("cracked_golden_nether_bricks_stairs", () -> new BlockItem(CRACKED_GOLDEN_NETHER_BRICKS_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> CRACKED_GOLDEN_NETHER_BRICKS_WALL_ITEM = ITEMS.register("cracked_golden_nether_bricks_wall", () -> new BlockItem(CRACKED_GOLDEN_NETHER_BRICKS_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//dungeon trapped blocks
	public static final RegistryObject<Block> TRAPPED_MUD_BRICKS = BLOCKS.register("trapped_mud_bricks", () -> new CoreProtectedTrappedBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
	public static final RegistryObject<Block> TRAPPED_LIGHT_MUD_BRICKS = BLOCKS.register("trapped_light_mud_bricks", () -> new CoreProtectedTrappedBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN).setLightLevel((state) -> 11)));
	public static final RegistryObject<Block> TRAPPED_LUNATIC_STONE = BLOCKS.register("trapped_lunatic_stone", () -> new CoreProtectedTrappedBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
	public static final RegistryObject<Block> TRAPPED_LIGHT_LUNATIC_STONE = BLOCKS.register("trapped_light_lunatic_stone", () -> new CoreProtectedTrappedBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN).setLightLevel((state) -> 11)));
	public static final RegistryObject<Block> TRAPPED_GOLDEN_NETHER_BRICKS = BLOCKS.register("trapped_golden_nether_bricks", () -> new CoreProtectedTrappedBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN)));
	public static final RegistryObject<Block> TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS = BLOCKS.register("trapped_light_golden_nether_bricks", () -> new CoreProtectedTrappedBlock(AbstractBlock.Properties.from(Blocks.OBSIDIAN).setLightLevel((state) -> 11)));
	
	public static final RegistryObject<Item> TRAPPED_MUD_BRICKS_ITEM = ITEMS.register("trapped_mud_bricks", () -> new BlockItem(TRAPPED_MUD_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> TRAPPED_LIGHT_MUD_BRICKS_ITEM = ITEMS.register("trapped_light_mud_bricks", () -> new BlockItem(TRAPPED_LIGHT_MUD_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> TRAPPED_LUNATIC_STONE_ITEM = ITEMS.register("trapped_lunatic_stone", () -> new BlockItem(TRAPPED_LUNATIC_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> TRAPPED_LIGHT_LUNATIC_STONE_ITEM = ITEMS.register("trapped_light_lunatic_stone", () -> new BlockItem(TRAPPED_LIGHT_LUNATIC_STONE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> TRAPPED_GOLDEN_NETHER_BRICKS_ITEM = ITEMS.register("trapped_golden_nether_bricks", () -> new BlockItem(TRAPPED_GOLDEN_NETHER_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS_ITEM = ITEMS.register("trapped_light_golden_nether_bricks", () -> new BlockItem(TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//dungeon other blocks, loots
	public static final RegistryObject<RotatedPillarBlock> MUD_BONE_BLOCK = BLOCKS.register("mud_bone_block", () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.DIRT).setRequiresTool().hardnessAndResistance(2.5F).sound(SoundType.BONE)));
	public static final RegistryObject<Item> MUD_BONE_BLOCK_ITEM = ITEMS.register("mud_bone_block", () -> new BlockItem(MUD_BONE_BLOCK.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> MUD_BONE = ITEMS.register("mud_bone",() -> new Item(new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Block> THORNY_COBWEB = BLOCKS.register("thorny_cobweb", () -> new ThornyWebBlock(AbstractBlock.Properties.create(Material.WEB).doesNotBlockMovement().harvestTool(ToolType.AXE).setRequiresTool().hardnessAndResistance(8.0F)));
	public static final RegistryObject<Item> THORNY_COBWEB_ITEM = ITEMS.register("thorny_cobweb", () -> new BlockItem(THORNY_COBWEB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Block> AERIAL_NETHERRACK = BLOCKS.register("aerial_netherrack", () -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.NETHERRACK).setRequiresTool().hardnessAndResistance(6.0F, 8.0F)));
	public static final RegistryObject<Item> AERIAL_NETHERRACK_ITEM = ITEMS.register("aerial_netherrack", () -> new BlockItem(AERIAL_NETHERRACK.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<SlabBlock> AERIAL_NETHERRACK_SLAB = BLOCKS.register("aerial_netherrack_slab", () -> new SlabBlock(AbstractBlock.Properties.from(AERIAL_NETHERRACK.get())));
	public static final RegistryObject<Item> AERIAL_NETHERRACK_SLAB_ITEM = ITEMS.register("aerial_netherrack_slab", () -> new BlockItem(AERIAL_NETHERRACK_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<StairsBlock> AERIAL_NETHERRACK_STAIRS = BLOCKS.register("aerial_netherrack_stairs", () -> new StairsBlock(() -> AERIAL_NETHERRACK.get().getDefaultState(), AbstractBlock.Properties.from(AERIAL_NETHERRACK.get())));
	public static final RegistryObject<Item> AERIAL_NETHERRACK_STAIRS_ITEM = ITEMS.register("aerial_netherrack_stairs", () -> new BlockItem(AERIAL_NETHERRACK_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WallBlock> AERIAL_NETHERRACK_WALL = BLOCKS.register("aerial_netherrack_wall", () -> new WallBlock(AbstractBlock.Properties.from(AERIAL_NETHERRACK.get())));
	public static final RegistryObject<Item> AERIAL_NETHERRACK_WALL_ITEM = ITEMS.register("aerial_netherrack_wall", () -> new BlockItem(AERIAL_NETHERRACK_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//Bookshelf
	public static final RegistryObject<Block> MUD_BOOKSHELF = BLOCKS.register("mud_bookshelf", () -> new CoreProtectedBookshelfBlock(AbstractBlock.Properties.from(MUD_BRICKS.get())));
	public static final RegistryObject<Block> LUNATIC_BOOKSHELF = BLOCKS.register("lunatic_bookshelf", () -> new CoreProtectedBookshelfBlock(AbstractBlock.Properties.from(LUNATIC_STONE.get())));
	public static final RegistryObject<Block> GOLDEN_NETHER_BOOKSHELF = BLOCKS.register("golden_nether_bookshelf", () -> new CoreProtectedBookshelfBlock(AbstractBlock.Properties.from(GOLDEN_NETHER_BRICKS.get())));
	public static final RegistryObject<Block> VOLUCITE_BOOKSHELF = BLOCKS.register("volucite_bookshelf", () -> new CoreProtectedBookshelfBlock(AbstractBlock.Properties.from(VOLUCITE_STONE.get())));
	public static final RegistryObject<Item> MUD_BOOKSHELF_ITEM = ITEMS.register("mud_bookshelf", () -> new BlockItem(MUD_BOOKSHELF.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> LUNATIC_BOOKSHELF_ITEM = ITEMS.register("lunatic_bookshelf", () -> new BlockItem(LUNATIC_BOOKSHELF.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLDEN_NETHER_BOOKSHELF_ITEM = ITEMS.register("golden_nether_bookshelf", () -> new BlockItem(GOLDEN_NETHER_BOOKSHELF.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> VOLUCITE_BOOKSHELF_ITEM = ITEMS.register("volucite_bookshelf", () -> new BlockItem(VOLUCITE_BOOKSHELF.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));

	//ores
	public static final RegistryObject<Block> IRON_STELLAR_ORE = BLOCKS.register("iron_stellar_ore",() -> new AerialHellOreBlock(0, 2, AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> GOLD_STELLAR_ORE = BLOCKS.register("gold_stellar_ore",() -> new AerialHellOreBlock(0, 2, AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool()));
	public static final RegistryObject<Block> DIAMOND_STELLAR_ORE = BLOCKS.register("diamond_stellar_ore",() -> new AerialHellOreBlock(3, 5, AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(5.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool()));
	public static final RegistryObject<Block> FLUORITE_ORE = BLOCKS.register("fluorite_ore",() -> new AerialHellOreBlock(0, 2, AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(0).setRequiresTool()));
	public static final RegistryObject<Block> MAGMATIC_GEL_ORE = BLOCKS.register("magmatic_gel_ore",() -> new MagmaticGelOreBlock(0, 2, AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F).sound(SoundType.STONE).setLightLevel(s -> 4).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> RUBY_ORE = BLOCKS.register("ruby_ore",() -> new AerialHellOreBlock(0, 0, AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> AZURITE_ORE = BLOCKS.register("azurite_ore",() -> new AerialHellOreBlock(0, 0, AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(3.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool()));
	public static final RegistryObject<Block> VOLUCITE_ORE = BLOCKS.register("volucite_ore",() -> new AerialHellOreBlock(0, 0, AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(5.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(2).setRequiresTool()));
	public static final RegistryObject<Block> OBSIDIAN_ORE = BLOCKS.register("obsidian_ore",() -> new AerialHellOreBlock(0, 0, AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(5.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).harvestLevel(3).setRequiresTool()));
	public static final RegistryObject<Item> IRON_STELLAR_ORE_ITEM = ITEMS.register("iron_stellar_ore", () -> new BlockItem(IRON_STELLAR_ORE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GOLD_STELLAR_ORE_ITEM = ITEMS.register("gold_stellar_ore", () -> new BlockItem(GOLD_STELLAR_ORE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> DIAMOND_STELLAR_ORE_ITEM = ITEMS.register("diamond_stellar_ore", () -> new BlockItem(DIAMOND_STELLAR_ORE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> FLUORITE_ORE_ITEM = ITEMS.register("fluorite_ore", () -> new BlockItem(FLUORITE_ORE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> MAGMATIC_GEL_ORE_ITEM = ITEMS.register("magmatic_gel_ore", () -> new BlockItem(MAGMATIC_GEL_ORE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> RUBY_ORE_ITEM = ITEMS.register("ruby_ore", () -> new BlockItem(RUBY_ORE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> AZURITE_ORE_ITEM = ITEMS.register("azurite_ore", () -> new BlockItem(AZURITE_ORE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> VOLUCITE_ORE_ITEM = ITEMS.register("volucite_ore", () -> new BlockItem(VOLUCITE_ORE.get(), new Item.Properties().rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> OBSIDIAN_ORE_ITEM = ITEMS.register("obsidian_ore", () -> new BlockItem(OBSIDIAN_ORE.get(), new Item.Properties().rarity(Rarity.EPIC).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
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
	
	public static final RegistryObject<Item> OVERHEATED_RUBY = ITEMS.register("overheated_ruby", () -> new WithInformationItem(new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> OVERHEATED_VOLUCITE = ITEMS.register("overheated_volucite", () -> new WithInformationItem(new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//legendary ores
	public static final RegistryObject<Item> ARSONIST_INGOT = ITEMS.register("arsonist_ingot", () -> new Item(new Item.Properties().rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).isImmuneToFire()));
	public static final RegistryObject<Item> LUNATIC_CRYSTAL = ITEMS.register("lunatic_crystal", () -> new Item(new Item.Properties().rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> OBSIDIAN_SHARD = ITEMS.register("obsidian_shard", () -> new Item(new Item.Properties().rarity(Rarity.EPIC).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	public static final RegistryObject<Block> ARSONIST_BLOCK = BLOCKS.register("arsonist_block", () -> new ArsonistBlock(AbstractBlock.Properties.from(Blocks.NETHERITE_BLOCK).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool().setLightLevel((state) -> 9)));
	public static final RegistryObject<Item> ARSONIST_BLOCK_ITEM = ITEMS.register("arsonist_block", () -> new BlockItem(ARSONIST_BLOCK.get(), new Item.Properties().rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).isImmuneToFire()));
	public static final RegistryObject<Block> LUNATIC_CRYSTAL_BLOCK = BLOCKS.register("lunatic_crystal_block", () -> new Block(AbstractBlock.Properties.from(Blocks.NETHERITE_BLOCK).sound(SoundType.GLASS).harvestTool(ToolType.PICKAXE).harvestLevel(1).setRequiresTool().setLightLevel((state) -> 9)));
	public static final RegistryObject<Item> LUNATIC_CRYSTAL_BLOCK_ITEM = ITEMS.register("lunatic_crystal_block", () -> new BlockItem(LUNATIC_CRYSTAL_BLOCK.get(), new Item.Properties().rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
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
	public static final RegistryObject<Block> STELLAR_GRASS_BALL = BLOCKS.register("stellar_grass_ball", () -> new AerialHellTallGrassBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
	public static final RegistryObject<Block> STELLAR_FERN = BLOCKS.register("stellar_fern", () -> new AerialHellTallGrassBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
	public static final RegistryObject<Block> STELLAR_TALL_GRASS = BLOCKS.register("stellar_tall_grass", () -> new DoublePlantBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
	public static final RegistryObject<Block> STELLAR_TALL_FERN = BLOCKS.register("stellar_tall_fern", () -> new DoublePlantBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
	public static final RegistryObject<Block> STELLAR_DEAD_BUSH = BLOCKS.register("stellar_dead_bush", () -> new AerialHellDeadBushBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS, MaterialColor.WOOD).doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)));
	public static final RegistryObject<Block> BRAMBLES = BLOCKS.register("brambles", () -> new BramblesBlock(AbstractBlock.Properties.create(Material.TALL_PLANTS, MaterialColor.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.PLANT)));
	public static final RegistryObject<Item> STELLAR_GRASS_ITEM = ITEMS.register("stellar_grass", () -> new BlockItem(STELLAR_GRASS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STELLAR_GRASS_BALL_ITEM = ITEMS.register("stellar_grass_ball", () -> new BlockItem(STELLAR_GRASS_BALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STELLAR_FERN_ITEM = ITEMS.register("stellar_fern", () -> new BlockItem(STELLAR_FERN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STELLAR_TALL_GRASS_ITEM = ITEMS.register("stellar_tall_grass", () -> new BlockItem(STELLAR_TALL_GRASS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STELLAR_TALL_FERN_ITEM = ITEMS.register("stellar_tall_fern", () -> new BlockItem(STELLAR_TALL_FERN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> STELLAR_DEAD_BUSH_ITEM = ITEMS.register("stellar_dead_bush", () -> new BlockItem(STELLAR_DEAD_BUSH.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> BRAMBLES_ITEM = ITEMS.register("brambles", () -> new BlockItem(BRAMBLES.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//flowers
	public static final RegistryObject<Block> BLUE_FLOWER = BLOCKS.register("blue_flower", () -> new FlowerBlock(Effects.BLINDNESS, 4, AbstractBlock.Properties.from(Blocks.DANDELION)));
	public static final RegistryObject<Item> BLUE_FLOWER_ITEM = ITEMS.register("blue_flower", () -> new BlockItem(BLUE_FLOWER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	public static final RegistryObject<Block> BLACK_ROSE = BLOCKS.register("black_rose", () -> new FlowerBlock(Effects.SLOW_FALLING, 12, AbstractBlock.Properties.from(Blocks.DANDELION)));
	public static final RegistryObject<Item> BLACK_ROSE_ITEM = ITEMS.register("black_rose", () -> new BlockItem(BLACK_ROSE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	public static final RegistryObject<Block> BELLFLOWER = BLOCKS.register("bellflower", () -> new FlowerBlock(Effects.MINING_FATIGUE, 12, AbstractBlock.Properties.from(Blocks.DANDELION)));
	public static final RegistryObject<Item> BELLFLOWER_ITEM = ITEMS.register("bellflower", () -> new BlockItem(BELLFLOWER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//potted things
	public static final RegistryObject<FlowerPotBlock> POTTED_BLUE_FLOWER = BLOCKS.register("potted_blue_flower", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLUE_FLOWER, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_BLACK_ROSE = BLOCKS.register("potted_black_rose", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BLACK_ROSE, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_BELLFLOWER = BLOCKS.register("potted_bellflower", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, BELLFLOWER, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_STELLAR_FERN = BLOCKS.register("potted_stellar_fern", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, STELLAR_FERN, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_STELLAR_DEAD_BUSH = BLOCKS.register("potted_stellar_dead_bush", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, STELLAR_DEAD_BUSH, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_SKY_CACTUS = BLOCKS.register("potted_sky_cactus", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SKY_CACTUS, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_VIBRANT_SKY_CACTUS = BLOCKS.register("potted_vibrant_sky_cactus", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, VIBRANT_SKY_CACTUS, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_AERIAL_TREE_SAPLING = BLOCKS.register("potted_aerial_tree_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, AERIAL_TREE_SAPLING, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_GOLDEN_BEECH_SAPLING = BLOCKS.register("potted_golden_beech_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, GOLDEN_BEECH_SAPLING, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_COPPER_PINE_SAPLING = BLOCKS.register("potted_copper_pine_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, COPPER_PINE_SAPLING, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	public static final RegistryObject<FlowerPotBlock> POTTED_LAPIS_ROBINIA_SAPLING = BLOCKS.register("potted_lapis_robinia_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, LAPIS_ROBINIA_SAPLING, AbstractBlock.Properties.from(Blocks.FLOWER_POT)));
	
	//with gui
	public static final RegistryObject<Block> VIBRATOR = BLOCKS.register("vibrator", () -> new VibratorBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(2.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Item> VIBRATOR_ITEM = ITEMS.register("vibrator", () -> new BlockItem(VIBRATOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	public static final RegistryObject<Block> FREEZER = BLOCKS.register("freezer",() -> new FreezerBlock(AbstractBlock.Properties.create(Material.ROCK).hardnessAndResistance(2.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Item> FREEZER_ITEM = ITEMS.register("freezer", () -> new BlockItem(FREEZER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	public static final RegistryObject<Block> STELLAR_FURNACE = BLOCKS.register("stellar_furnace", () -> new StellarFurnaceBlock(AbstractBlock.Properties.create(Material.ROCK).setRequiresTool().hardnessAndResistance(3.5F).setLightLevel(getLightValueLit(13))));
	public static final RegistryObject<Item> STELLAR_FURNACE_ITEM = ITEMS.register("stellar_furnace", () -> new BlockItem(STELLAR_FURNACE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {return (state) -> {return state.get(BlockStateProperties.LIT) ? lightValue : 0;};}
	
	//chests
	public static final RegistryObject<ChestBlock> AERIAL_TREE_CHEST = BLOCKS.register("aerial_tree_chest", () -> new ChestBlock(AERIAL_TREE_MATERIAL,() -> AerialHellTileEntityTypes.CHEST.get()){public TileEntity createTileEntity(BlockState state,net.minecraft.world.IBlockReader world) {return AerialHellTileEntityTypes.CHEST.get().create();};});
	public static final RegistryObject<Item> AERIAL_TREE_CHEST_ITEM = ITEMS.register("aerial_tree_chest", () -> new BlockItem(AERIAL_TREE_CHEST.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).setISTER(() -> AerialHellChestItemTileEntityRenderer::new)));
	public static final RegistryObject<ChestBlock> GOLDEN_BEECH_CHEST = BLOCKS.register("golden_beech_chest", () -> new ChestBlock(AERIAL_TREE_MATERIAL,() -> AerialHellTileEntityTypes.CHEST.get()){public TileEntity createTileEntity(BlockState state,net.minecraft.world.IBlockReader world) {return AerialHellTileEntityTypes.CHEST.get().create();};});
	public static final RegistryObject<Item> GOLDEN_BEECH_ITEM = ITEMS.register("golden_beech_chest", () -> new BlockItem(GOLDEN_BEECH_CHEST.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).setISTER(() -> AerialHellChestItemTileEntityRenderer::new)));
	public static final RegistryObject<ChestBlock> COPPER_PINE_CHEST = BLOCKS.register("copper_pine_chest", () -> new ChestBlock(AERIAL_TREE_MATERIAL,() -> AerialHellTileEntityTypes.CHEST.get()){public TileEntity createTileEntity(BlockState state,net.minecraft.world.IBlockReader world) {return AerialHellTileEntityTypes.CHEST.get().create();};});
	public static final RegistryObject<Item> COPPER_PINE_ITEM = ITEMS.register("copper_pine_chest", () -> new BlockItem(COPPER_PINE_CHEST.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).setISTER(() -> AerialHellChestItemTileEntityRenderer::new)));
	public static final RegistryObject<ChestBlock> LAPIS_ROBINIA_CHEST = BLOCKS.register("lapis_robinia_chest", () -> new ChestBlock(AERIAL_TREE_MATERIAL,() -> AerialHellTileEntityTypes.CHEST.get()){public TileEntity createTileEntity(BlockState state,net.minecraft.world.IBlockReader world) {return AerialHellTileEntityTypes.CHEST.get().create();};});
	public static final RegistryObject<Item> LAPIS_ROBINIA_CHEST_ITEM = ITEMS.register("lapis_robinia_chest", () -> new BlockItem(LAPIS_ROBINIA_CHEST.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).setISTER(() -> AerialHellChestItemTileEntityRenderer::new)));
	public static final RegistryObject<ChestBlock> SKY_CACTUS_FIBER_CHEST = BLOCKS.register("sky_cactus_fiber_chest", () -> new ChestBlock(SKY_CACTUS_FIBER_MATERIAL,() -> AerialHellTileEntityTypes.CHEST.get()){public TileEntity createTileEntity(BlockState state,net.minecraft.world.IBlockReader world) {return AerialHellTileEntityTypes.CHEST.get().create();};});
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_CHEST_ITEM = ITEMS.register("sky_cactus_fiber_chest", () -> new BlockItem(SKY_CACTUS_FIBER_CHEST.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).setISTER(() -> AerialHellChestItemTileEntityRenderer::new)));
	public static final RegistryObject<ChestBlock> MUD_CHEST = BLOCKS.register("mud_chest", () -> new CoreProtectedChestBlock(MUD_CHEST_MATERIAL,() -> AerialHellTileEntityTypes.CHEST.get()){public TileEntity createTileEntity(BlockState state,net.minecraft.world.IBlockReader world) {return AerialHellTileEntityTypes.CHEST.get().create();};});
	public static final RegistryObject<Item> MUD_CHEST_ITEM = ITEMS.register("mud_chest", () -> new BlockItem(MUD_CHEST.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).setISTER(() -> AerialHellChestItemTileEntityRenderer::new)));
	public static final RegistryObject<ChestBlock> LUNATIC_CHEST = BLOCKS.register("lunatic_chest", () -> new CoreProtectedChestBlock(LUNATIC_CHEST_MATERIAL,() -> AerialHellTileEntityTypes.CHEST.get()){public TileEntity createTileEntity(BlockState state,net.minecraft.world.IBlockReader world) {return AerialHellTileEntityTypes.CHEST.get().create();};});
	public static final RegistryObject<Item> LUNATIC_CHEST_ITEM = ITEMS.register("lunatic_chest", () -> new BlockItem(LUNATIC_CHEST.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).setISTER(() -> AerialHellChestItemTileEntityRenderer::new)));
	public static final RegistryObject<ChestBlock> VOLUCITE_CHEST = BLOCKS.register("volucite_chest", () -> new CoreProtectedChestBlock(VOLUCITE_CHEST_MATERIAL,() -> AerialHellTileEntityTypes.CHEST.get()){public TileEntity createTileEntity(BlockState state,net.minecraft.world.IBlockReader world) {return AerialHellTileEntityTypes.CHEST.get().create();};});
	public static final RegistryObject<Item> VOLUCITE_CHEST_ITEM = ITEMS.register("volucite_chest", () -> new BlockItem(VOLUCITE_CHEST.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).setISTER(() -> AerialHellChestItemTileEntityRenderer::new)));
	public static final RegistryObject<ChestBlock> GOLDEN_NETHER_CHEST = BLOCKS.register("golden_nether_chest", () -> new CoreProtectedChestBlock(GOLDEN_NETHER_CHEST_MATERIAL,() -> AerialHellTileEntityTypes.CHEST.get()){public TileEntity createTileEntity(BlockState state,net.minecraft.world.IBlockReader world) {return AerialHellTileEntityTypes.CHEST.get().create();};});
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
	public static final RegistryObject<Item> AERIAL_TREE_FENCE_ITEM = ITEMS.register("aerial_tree_fence", () -> new BurnableBlockItem(AERIAL_TREE_FENCE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<FenceBlock> GOLDEN_BEECH_FENCE = BLOCKS.register("golden_beech_fence", () -> new FenceBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_FENCE_ITEM = ITEMS.register("golden_beech_fence", () -> new BurnableBlockItem(GOLDEN_BEECH_FENCE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<FenceBlock> COPPER_PINE_FENCE = BLOCKS.register("copper_pine_fence", () -> new FenceBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_FENCE_ITEM = ITEMS.register("copper_pine_fence", () -> new BurnableBlockItem(COPPER_PINE_FENCE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<FenceBlock> LAPIS_ROBINIA_FENCE = BLOCKS.register("lapis_robinia_fence", () -> new FenceBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_FENCE_ITEM = ITEMS.register("lapis_robinia_fence", () -> new BurnableBlockItem(LAPIS_ROBINIA_FENCE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<FenceBlock> SKY_CACTUS_FIBER_FENCE = BLOCKS.register("sky_cactus_fiber_fence", () -> new FenceBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_FENCE_ITEM = ITEMS.register("sky_cactus_fiber_fence", () -> new BlockItem(SKY_CACTUS_FIBER_FENCE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<PaneBlock> RUBY_BARS = BLOCKS.register("ruby_bars", () -> new PaneBlock(RUBY_MATERIAL));
	public static final RegistryObject<Item> RUBY_BARS_ITEM = ITEMS.register("ruby_bars", () -> new BlockItem(RUBY_BARS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WallBlock> STELLAR_STONE_WALL = BLOCKS.register("stellar_stone_wall", () -> new WallBlock(AbstractBlock.Properties.from(STELLAR_STONE.get())));
	public static final RegistryObject<Item> STELLAR_STONE_WALL_ITEM = ITEMS.register("stellar_stone_wall", () -> new BlockItem(STELLAR_STONE_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WallBlock> STELLAR_COBBLESTONE_WALL = BLOCKS.register("stellar_cobblestone_wall", () -> new WallBlock(AbstractBlock.Properties.from(STELLAR_COBBLESTONE.get())));
	public static final RegistryObject<Item> STELLAR_COBBLESTONE_WALL_ITEM = ITEMS.register("stellar_cobblestone_wall", () -> new BlockItem(STELLAR_COBBLESTONE_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WallBlock> STELLAR_STONE_BRICKS_WALL = BLOCKS.register("stellar_stone_bricks_wall", () -> new WallBlock(AbstractBlock.Properties.from(STELLAR_STONE_BRICKS.get())));
	public static final RegistryObject<Item> STELLAR_STONE_BRICKS_WALL_ITEM = ITEMS.register("stellar_stone_bricks_wall", () -> new BlockItem(STELLAR_STONE_BRICKS_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WallBlock> MOSSY_STELLAR_STONE_WALL = BLOCKS.register("mossy_stellar_stone_wall", () -> new WallBlock(AbstractBlock.Properties.from(MOSSY_STELLAR_STONE.get())));
	public static final RegistryObject<Item> MOSSY_STELLAR_STONE_WALL_ITEM = ITEMS.register("mossy_stellar_stone_wall", () -> new BlockItem(MOSSY_STELLAR_STONE_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WallBlock> MOSSY_STELLAR_COBBLESTONE_WALL = BLOCKS.register("mossy_stellar_cobblestone_wall", () -> new WallBlock(AbstractBlock.Properties.from(MOSSY_STELLAR_COBBLESTONE.get())));
	public static final RegistryObject<Item> MOSSY_STELLAR_COBBLESTONE_WALL_ITEM = ITEMS.register("mossy_stellar_cobblestone_wall", () -> new BlockItem(MOSSY_STELLAR_COBBLESTONE_WALL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
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
	public static final RegistryObject<Item> AERIAL_TREE_GATE_ITEM = ITEMS.register("aerial_tree_gate", () -> new BurnableBlockItem(AERIAL_TREE_GATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<FenceGateBlock> GOLDEN_BEECH_GATE = BLOCKS.register("golden_beech_gate", () -> new FenceGateBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_GATE_ITEM = ITEMS.register("golden_beech_gate", () -> new BurnableBlockItem(GOLDEN_BEECH_GATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<FenceGateBlock> COPPER_PINE_GATE = BLOCKS.register("copper_pine_gate", () -> new FenceGateBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_GATE_ITEM = ITEMS.register("copper_pine_gate", () -> new BurnableBlockItem(COPPER_PINE_GATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<FenceGateBlock> LAPIS_ROBINIA_GATE = BLOCKS.register("lapis_robinia_gate", () -> new FenceGateBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_GATE_ITEM = ITEMS.register("lapis_robinia_gate", () -> new BurnableBlockItem(LAPIS_ROBINIA_GATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<FenceGateBlock> SKY_CACTUS_FIBER_GATE = BLOCKS.register("sky_cactus_fiber_gate", () -> new FenceGateBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_GATE_ITEM = ITEMS.register("sky_cactus_fiber_gate", () -> new BlockItem(SKY_CACTUS_FIBER_GATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//doors
	public static final RegistryObject<DoorBlock> AERIAL_TREE_DOOR = BLOCKS.register("aerial_tree_door", () -> new DoorBlock(AbstractBlock.Properties.from(AERIAL_TREE_PLANKS.get()).notSolid()));
	public static final RegistryObject<Item> AERIAL_TREE_DOOR_ITEM = ITEMS.register("aerial_tree_door", () -> new BlockItem(AERIAL_TREE_DOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<DoorBlock> GOLDEN_BEECH_DOOR = BLOCKS.register("golden_beech_door", () -> new DoorBlock(AbstractBlock.Properties.from(GOLDEN_BEECH_PLANKS.get()).notSolid()));
	public static final RegistryObject<Item> GOLDEN_BEECH_DOOR_ITEM = ITEMS.register("golden_beech_door", () -> new BlockItem(GOLDEN_BEECH_DOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<DoorBlock> COPPER_PINE_DOOR = BLOCKS.register("copper_pine_door", () -> new DoorBlock(AbstractBlock.Properties.from(COPPER_PINE_PLANKS.get()).notSolid()));
	public static final RegistryObject<Item> COPPER_PINE_DOOR_ITEM = ITEMS.register("copper_pine_door", () -> new BlockItem(COPPER_PINE_DOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<DoorBlock> LAPIS_ROBINIA_DOOR = BLOCKS.register("lapis_robinia_door", () -> new DoorBlock(AbstractBlock.Properties.from(LAPIS_ROBINIA_PLANKS.get()).notSolid()));
	public static final RegistryObject<Item> LAPIS_ROBINIA_DOOR_ITEM = ITEMS.register("lapis_robinia_door", () -> new BlockItem(LAPIS_ROBINIA_DOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<DoorBlock> SKY_CACTUS_FIBER_DOOR = BLOCKS.register("sky_cactus_fiber_door", () -> new DoorBlock(AbstractBlock.Properties.from(SKY_CACTUS_FIBER_PLANKS.get()).notSolid()));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_DOOR_ITEM = ITEMS.register("sky_cactus_fiber_door", () -> new BlockItem(SKY_CACTUS_FIBER_DOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<DoorBlock> RUBY_DOOR = BLOCKS.register("ruby_door", () -> new DoorBlock(RUBY_MATERIAL));
	public static final RegistryObject<Item> RUBY_DOOR_ITEM = ITEMS.register("ruby_door", () -> new BlockItem(RUBY_DOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//trapdoors
	public static final RegistryObject<TrapDoorBlock> AERIAL_TREE_TRAPDOOR = BLOCKS.register("aerial_tree_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.from(AERIAL_TREE_PLANKS.get()).notSolid()));
	public static final RegistryObject<Item> AERIAL_TREE_TRAPDOOR_ITEM = ITEMS.register("aerial_tree_trapdoor", () -> new BlockItem(AERIAL_TREE_TRAPDOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<TrapDoorBlock> GOLDEN_BEECH_TRAPDOOR = BLOCKS.register("golden_beech_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.from(GOLDEN_BEECH_PLANKS.get()).notSolid()));
	public static final RegistryObject<Item> GOLDEN_BEECH_TRAPDOOR_ITEM = ITEMS.register("golden_beech_trapdoor", () -> new BlockItem(GOLDEN_BEECH_TRAPDOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<TrapDoorBlock> COPPER_PINE_TRAPDOOR = BLOCKS.register("copper_pine_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.from(COPPER_PINE_PLANKS.get()).notSolid()));
	public static final RegistryObject<Item> COPPER_PINE_TRAPDOOR_ITEM = ITEMS.register("copper_pine_trapdoor", () -> new BlockItem(COPPER_PINE_TRAPDOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<TrapDoorBlock> LAPIS_ROBINIA_TRAPDOOR = BLOCKS.register("lapis_robinia_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.from(LAPIS_ROBINIA_PLANKS.get()).notSolid()));
	public static final RegistryObject<Item> LAPIS_ROBINIA_TRAPDOOR_ITEM = ITEMS.register("lapis_robinia_trapdoor", () -> new BlockItem(LAPIS_ROBINIA_TRAPDOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<TrapDoorBlock> SKY_CACTUS_FIBER_TRAPDOOR = BLOCKS.register("sky_cactus_fiber_trapdoor", () -> new TrapDoorBlock(AbstractBlock.Properties.from(SKY_CACTUS_FIBER_PLANKS.get()).notSolid()));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_TRAPDOOR_ITEM = ITEMS.register("sky_cactus_fiber_trapdoor", () -> new BlockItem( SKY_CACTUS_FIBER_TRAPDOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<TrapDoorBlock> RUBY_TRAPDOOR = BLOCKS.register("ruby_trapdoor", () -> new TrapDoorBlock(RUBY_MATERIAL));
	public static final RegistryObject<Item> RUBY_TRAPDOOR_ITEM = ITEMS.register("ruby_trapdoor", () -> new BlockItem(RUBY_TRAPDOOR.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//buttons
	public static final RegistryObject<StoneButtonBlock> STELLAR_STONE_BUTTON = BLOCKS.register("stellar_stone_button", () -> new StoneButtonBlock(AbstractBlock.Properties.from(STELLAR_STONE.get())));
	public static final RegistryObject<Item> STELLAR_STONE_BUTTON_ITEM = ITEMS.register("stellar_stone_button", () -> new BlockItem(STELLAR_STONE_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<StoneButtonBlock> STELLAR_COBBLESTONE_BUTTON = BLOCKS.register("stellar_cobblestone_button", () -> new StoneButtonBlock(AbstractBlock.Properties.from(STELLAR_COBBLESTONE.get())));
	public static final RegistryObject<Item> STELLAR_COBBLESTONE_BUTTON_ITEM = ITEMS.register("stellar_cobblestone_button", () -> new BlockItem(STELLAR_COBBLESTONE_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WoodButtonBlock> STELLAR_STONE_BRICKS_BUTTON = BLOCKS.register("stellar_stone_bricks_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(STELLAR_STONE_BRICKS.get())));
	public static final RegistryObject<Item> STELLAR_STONE_BRICKS_BUTTON_ITEM = ITEMS.register("stellar_stone_bricks_button", () -> new BlockItem(STELLAR_STONE_BRICKS_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WoodButtonBlock> SLIPPERY_SAND_STONE_BUTTON = BLOCKS.register("slippery_sand_stone_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(SLIPPERY_SAND_STONE.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_BUTTON_ITEM = ITEMS.register("slippery_sand_stone_button", () -> new BlockItem(SLIPPERY_SAND_STONE_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WoodButtonBlock> SLIPPERY_SAND_STONE_BRICKS_BUTTON = BLOCKS.register("slippery_sand_stone_bricks_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_BRICKS_BUTTON_ITEM = ITEMS.register("slippery_sand_stone_bricks_button", () -> new BlockItem(SLIPPERY_SAND_STONE_BRICKS_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WoodButtonBlock> AERIAL_TREE_BUTTON = BLOCKS.register("aerial_tree_button", () -> new WoodButtonBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_BUTTON_ITEM = ITEMS.register("aerial_tree_button", () -> new BurnableBlockItem(AERIAL_TREE_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 100));
	public static final RegistryObject<WoodButtonBlock> GOLDEN_BEECH_BUTTON = BLOCKS.register("golden_beech_button", () -> new WoodButtonBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_BUTTON_ITEM = ITEMS.register("golden_beech_button", () -> new BurnableBlockItem(GOLDEN_BEECH_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 100));
	public static final RegistryObject<WoodButtonBlock> COPPER_PINE_BUTTON = BLOCKS.register("copper_pine_button", () -> new WoodButtonBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_BUTTON_ITEM = ITEMS.register("copper_pine_button", () -> new BurnableBlockItem(COPPER_PINE_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 100));
	public static final RegistryObject<WoodButtonBlock> LAPIS_ROBINIA_BUTTON = BLOCKS.register("lapis_robinia_button", () -> new WoodButtonBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_BUTTON_ITEM = ITEMS.register("lapis_robinia_button", () -> new BurnableBlockItem(LAPIS_ROBINIA_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 100));
	public static final RegistryObject<WoodButtonBlock> SKY_CACTUS_FIBER_BUTTON = BLOCKS.register("sky_cactus_fiber_button", () -> new WoodButtonBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_BUTTON_ITEM = ITEMS.register("sky_cactus_fiber_button", () -> new BlockItem(SKY_CACTUS_FIBER_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<WoodButtonBlock> GLAUCOPHANITE_BUTTON = BLOCKS.register("glaucophanite_button", () -> new WoodButtonBlock(AbstractBlock.Properties.from(GLAUCOPHANITE.get())));
	public static final RegistryObject<Item> GLAUCOPHANITE_BUTTON_ITEM = ITEMS.register("glaucophanite_button", () -> new BlockItem(GLAUCOPHANITE_BUTTON.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//pressure plates
	public static final RegistryObject<PressurePlateBlock> STELLAR_STONE_PRESSURE_PLATE = BLOCKS.register("stellar_stone_pressure_plate", () -> new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.from(STELLAR_STONE.get())));
	public static final RegistryObject<Item> STELLAR_STONE_PRESSURE_PLATE_ITEM = ITEMS.register("stellar_stone_pressure_plate", () -> new BlockItem(STELLAR_STONE_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<PressurePlateBlock> STELLAR_COBBLESTONE_PRESSURE_PLATE = BLOCKS.register("stellar_cobblestone_pressure_plate", () -> new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.from(STELLAR_COBBLESTONE.get())));
	public static final RegistryObject<Item> STELLAR_COBBLESTONE_PRESSURE_PLATE_ITEM = ITEMS.register("stellar_cobblestone_pressure_plate", () -> new BlockItem(STELLAR_COBBLESTONE_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<PressurePlateBlock> STELLAR_STONE_BRICKS_PRESSURE_PLATE = BLOCKS.register("stellar_stone_bricks_pressure_plate", () -> new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.from(STELLAR_STONE_BRICKS.get())));
	public static final RegistryObject<Item> STELLAR_STONE_BRICKS_PRESSURE_PLATE_ITEM = ITEMS.register("stellar_stone_bricks_pressure_plate", () -> new BlockItem(STELLAR_STONE_BRICKS_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<PressurePlateBlock> SLIPPERY_SAND_STONE_PRESSURE_PLATE = BLOCKS.register("slippery_sand_stone_pressure_plate", () -> new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.from(SLIPPERY_SAND_STONE.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_PRESSURE_PLATE_ITEM = ITEMS.register("slippery_sand_stone_pressure_plate", () -> new BlockItem(SLIPPERY_SAND_STONE_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<PressurePlateBlock> SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE = BLOCKS.register("slippery_sand_stone_bricks_pressure_plate", () -> new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.from(SLIPPERY_SAND_STONE_BRICKS.get())));
	public static final RegistryObject<Item> SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE_ITEM = ITEMS.register("slippery_sand_stone_bricks_pressure_plate", () -> new BlockItem(SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<PressurePlateBlock> AERIAL_TREE_PRESSURE_PLATE = BLOCKS.register("aerial_tree_pressure_plate", () -> new PressurePlateBlock(Sensitivity.EVERYTHING, AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_PRESSURE_PLATE_ITEM = ITEMS.register("aerial_tree_pressure_plate", () -> new BurnableBlockItem(AERIAL_TREE_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<PressurePlateBlock> GOLDEN_BEECH_PRESSURE_PLATE = BLOCKS.register("golden_beech_pressure_plate", () -> new PressurePlateBlock(Sensitivity.EVERYTHING, AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_PRESSURE_PLATE_ITEM = ITEMS.register("golden_beech_pressure_plate", () -> new BurnableBlockItem(GOLDEN_BEECH_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<PressurePlateBlock> COPPER_PINE_PRESSURE_PLATE = BLOCKS.register("copper_pine_pressure_plate", () -> new PressurePlateBlock(Sensitivity.EVERYTHING, AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_PRESSURE_PLATE_ITEM = ITEMS.register("copper_pine_pressure_plate", () -> new BurnableBlockItem(COPPER_PINE_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<PressurePlateBlock> LAPIS_ROBINIA_PRESSURE_PLATE = BLOCKS.register("lapis_robinia_pressure_plate", () -> new PressurePlateBlock(Sensitivity.EVERYTHING, AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_PRESSURE_PLATE_ITEM = ITEMS.register("lapis_robinia_pressure_plate", () -> new BurnableBlockItem(LAPIS_ROBINIA_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<PressurePlateBlock> SKY_CACTUS_FIBER_PRESSURE_PLATE = BLOCKS.register("sky_cactus_fiber_pressure_plate", () -> new PressurePlateBlock(Sensitivity.EVERYTHING, SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_PRESSURE_PLATE_ITEM = ITEMS.register("sky_cactus_fiber_pressure_plate", () -> new BlockItem(SKY_CACTUS_FIBER_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<PressurePlateBlock> GLAUCOPHANITE_PRESSURE_PLATE = BLOCKS.register("glaucophanite_pressure_plate", () -> new PressurePlateBlock(Sensitivity.MOBS, AbstractBlock.Properties.from(GLAUCOPHANITE.get())));
	public static final RegistryObject<Item> GLAUCOPHANITE_PRESSURE_PLATE_ITEM = ITEMS.register("glaucophanite_pressure_plate", () -> new BlockItem(GLAUCOPHANITE_PRESSURE_PLATE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//slabs
	public static final RegistryObject<SlabBlock> AERIAL_TREE_SLAB = BLOCKS.register("aerial_tree_slab", () -> new SlabBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_SLAB_ITEM = ITEMS.register("aerial_tree_slab", () -> new BurnableBlockItem(AERIAL_TREE_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 150));
	public static final RegistryObject<SlabBlock> GOLDEN_BEECH_SLAB = BLOCKS.register("golden_beech_slab", () -> new SlabBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_SLAB_ITEM = ITEMS.register("golden_beech_slab", () -> new BurnableBlockItem(GOLDEN_BEECH_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 150));
	public static final RegistryObject<SlabBlock> COPPER_PINE_SLAB = BLOCKS.register("copper_pine_slab", () -> new SlabBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_SLAB_ITEM = ITEMS.register("copper_pine_slab", () -> new BurnableBlockItem(COPPER_PINE_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 150));
	public static final RegistryObject<SlabBlock> LAPIS_ROBINIA_SLAB = BLOCKS.register("lapis_robinia_slab", () -> new SlabBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_SLAB_ITEM = ITEMS.register("lapis_robinia_slab", () -> new BurnableBlockItem(LAPIS_ROBINIA_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 150));
	public static final RegistryObject<SlabBlock> SKY_CACTUS_FIBER_SLAB = BLOCKS.register("sky_cactus_fiber_slab", () -> new SlabBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_SLAB_ITEM = ITEMS.register("sky_cactus_fiber_slab", () -> new BlockItem(SKY_CACTUS_FIBER_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<SlabBlock> STELLAR_STONE_SLAB = BLOCKS.register("stellar_stone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(STELLAR_STONE.get())));
	public static final RegistryObject<Item> STELLAR_STONE_SLAB_ITEM = ITEMS.register("stellar_stone_slab", () -> new BlockItem(STELLAR_STONE_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<SlabBlock> STELLAR_COBBLESTONE_SLAB = BLOCKS.register("stellar_cobblestone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(STELLAR_COBBLESTONE.get())));
	public static final RegistryObject<Item> STELLAR_COBBLESTONE_SLAB_ITEM = ITEMS.register("stellar_cobblestone_slab", () -> new BlockItem(STELLAR_COBBLESTONE_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<SlabBlock> STELLAR_STONE_BRICKS_SLAB = BLOCKS.register("stellar_stone_bricks_slab", () -> new SlabBlock(AbstractBlock.Properties.from(STELLAR_STONE_BRICKS.get())));
	public static final RegistryObject<Item> STELLAR_STONE_BRICKS_SLAB_ITEM = ITEMS.register("stellar_stone_bricks_slab", () -> new BlockItem(STELLAR_STONE_BRICKS_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<SlabBlock> MOSSY_STELLAR_STONE_SLAB = BLOCKS.register("mossy_stellar_stone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(MOSSY_STELLAR_STONE.get())));
	public static final RegistryObject<Item> MOSSY_STELLAR_STONE_SLAB_ITEM = ITEMS.register("mossy_stellar_stone_slab", () -> new BlockItem(MOSSY_STELLAR_STONE_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<SlabBlock> MOSSY_STELLAR_COBBLESTONE_SLAB = BLOCKS.register("mossy_stellar_cobblestone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(MOSSY_STELLAR_COBBLESTONE.get())));
	public static final RegistryObject<Item> MOSSY_STELLAR_COBBLESTONE_SLAB_ITEM = ITEMS.register("mossy_stellar_cobblestone_slab", () -> new BlockItem(MOSSY_STELLAR_COBBLESTONE_SLAB.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
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
	public static final RegistryObject<Item> AERIAL_TREE_STAIRS_ITEM = ITEMS.register("aerial_tree_stairs", () -> new BurnableBlockItem(AERIAL_TREE_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<StairsBlock> GOLDEN_BEECH_STAIRS = BLOCKS.register("golden_beech_stairs", () -> new StairsBlock(() -> GOLDEN_BEECH_PLANKS.get().getDefaultState(), AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_STAIRS_ITEM = ITEMS.register("golden_beech_stairs", () -> new BurnableBlockItem(GOLDEN_BEECH_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<StairsBlock> COPPER_PINE_STAIRS = BLOCKS.register("copper_pine_stairs", () -> new StairsBlock(() -> COPPER_PINE_PLANKS.get().getDefaultState(), AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_STAIRS_ITEM = ITEMS.register("copper_pine_stairs", () -> new BurnableBlockItem(COPPER_PINE_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<StairsBlock> LAPIS_ROBINIA_STAIRS = BLOCKS.register("lapis_robinia_stairs", () -> new StairsBlock(() -> LAPIS_ROBINIA_PLANKS.get().getDefaultState(), AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_STAIRS_ITEM = ITEMS.register("lapis_robinia_stairs", () -> new BurnableBlockItem(LAPIS_ROBINIA_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<StairsBlock> SKY_CACTUS_FIBER_STAIRS = BLOCKS.register("sky_cactus_fiber_stairs", () -> new StairsBlock(() -> SKY_CACTUS_FIBER_PLANKS.get().getDefaultState(), SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_STAIRS_ITEM = ITEMS.register("sky_cactus_fiber_stairs", () -> new BlockItem(SKY_CACTUS_FIBER_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<StairsBlock> STELLAR_STONE_STAIRS = BLOCKS.register("stellar_stone_stairs", () -> new StairsBlock(() -> STELLAR_STONE.get().getDefaultState(), AbstractBlock.Properties.from(STELLAR_STONE.get())));
	public static final RegistryObject<Item> STELLAR_STONE_STAIRS_ITEM = ITEMS.register("stellar_stone_stairs", () -> new BlockItem(STELLAR_STONE_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<StairsBlock> STELLAR_COBBLESTONE_STAIRS = BLOCKS.register("stellar_cobblestone_stairs", () -> new StairsBlock(() -> STELLAR_COBBLESTONE.get().getDefaultState(), AbstractBlock.Properties.from(STELLAR_COBBLESTONE.get())));
	public static final RegistryObject<Item> STELLAR_COBBLESTONE_STAIRS_ITEM = ITEMS.register("stellar_cobblestone_stairs", () -> new BlockItem(STELLAR_COBBLESTONE_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<StairsBlock> STELLAR_STONE_BRICKS_STAIRS = BLOCKS.register("stellar_stone_bricks_stairs", () -> new StairsBlock(() -> STELLAR_STONE_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(STELLAR_STONE_BRICKS.get())));
	public static final RegistryObject<Item> STELLAR_STONE_BRICKS_STAIRS_ITEM = ITEMS.register("stellar_stone_bricks_stairs", () -> new BlockItem(STELLAR_STONE_BRICKS_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<StairsBlock> MOSSY_STELLAR_STONE_STAIRS = BLOCKS.register("mossy_stellar_stone_stairs", () -> new StairsBlock(() -> MOSSY_STELLAR_STONE.get().getDefaultState(), AbstractBlock.Properties.from(MOSSY_STELLAR_STONE.get())));
	public static final RegistryObject<Item> MOSSY_STELLAR_STONE_STAIRS_ITEM = ITEMS.register("mossy_stellar_stone_stairs", () -> new BlockItem(MOSSY_STELLAR_STONE_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<StairsBlock> MOSSY_STELLAR_COBBLESTONE_STAIRS = BLOCKS.register("mossy_stellar_cobblestone_stairs", () -> new StairsBlock(() -> MOSSY_STELLAR_COBBLESTONE.get().getDefaultState(), AbstractBlock.Properties.from(MOSSY_STELLAR_COBBLESTONE.get())));
	public static final RegistryObject<Item> MOSSY_STELLAR_COBBLESTONE_STAIRS_ITEM = ITEMS.register("mossy_stellar_cobblestone_stairs", () -> new BlockItem(MOSSY_STELLAR_COBBLESTONE_STAIRS.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
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
	public static final RegistryObject<Item> AERIAL_TREE_SIGN_ITEM = ITEMS.register("aerial_tree_sign", () -> new BurnableBlockItem(AERIAL_TREE_SIGN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 200));
	public static final RegistryObject<AerialHellSignBlock> GOLDEN_BEECH_SIGN = BLOCKS.register("golden_beech_sign", () -> new AerialHellSignBlock(AERIAL_TREE_SIGN_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_SIGN_ITEM = ITEMS.register("golden_beech_sign", () -> new BurnableBlockItem(GOLDEN_BEECH_SIGN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 200));
	public static final RegistryObject<AerialHellSignBlock> COPPER_PINE_SIGN = BLOCKS.register("copper_pine_sign", () -> new AerialHellSignBlock(AERIAL_TREE_SIGN_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_SIGN_ITEM = ITEMS.register("copper_pine_sign", () -> new BurnableBlockItem(COPPER_PINE_SIGN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 200));
	public static final RegistryObject<AerialHellSignBlock> LAPIS_ROBINIA_SIGN = BLOCKS.register("lapis_robinia_sign", () -> new AerialHellSignBlock(AERIAL_TREE_SIGN_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_SIGN_ITEM = ITEMS.register("lapis_robinia_sign", () -> new BurnableBlockItem(LAPIS_ROBINIA_SIGN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 200));
	public static final RegistryObject<AerialHellSignBlock> SKY_CACTUS_FIBER_SIGN = BLOCKS.register("sky_cactus_fiber_sign", () -> new AerialHellSignBlock(SKY_CACTUS_FIBER_SIGN_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_SIGN_ITEM = ITEMS.register("sky_cactus_fiber_sign", () -> new BlockItem(SKY_CACTUS_FIBER_SIGN.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//crafting tables
	public static final RegistryObject<CraftingTableBlock> AERIAL_TREE_CRAFTING_TABLE = BLOCKS.register("aerial_tree_crafting_table", () -> new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_CRAFTING_TABLE_ITEM = ITEMS.register("aerial_tree_crafting_table", () -> new BurnableBlockItem(AERIAL_TREE_CRAFTING_TABLE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<CraftingTableBlock> GOLDEN_BEECH_CRAFTING_TABLE = BLOCKS.register("golden_beech_crafting_table", () -> new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_CRAFTING_TABLE_ITEM = ITEMS.register("golden_beech_crafting_table", () -> new BurnableBlockItem(GOLDEN_BEECH_CRAFTING_TABLE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<CraftingTableBlock> COPPER_PINE_CRAFTING_TABLE = BLOCKS.register("copper_pine_crafting_table", () -> new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_CRAFTING_TABLE_ITEM = ITEMS.register("copper_pine_crafting_table", () -> new BurnableBlockItem(COPPER_PINE_CRAFTING_TABLE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<CraftingTableBlock> LAPIS_ROBINIA_CRAFTING_TABLE = BLOCKS.register("lapis_robinia_crafting_table", () -> new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_CRAFTING_TABLE_ITEM = ITEMS.register("lapis_robinia_crafting_table", () -> new BurnableBlockItem(LAPIS_ROBINIA_CRAFTING_TABLE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<CraftingTableBlock> SKY_CACTUS_FIBER_CRAFTING_TABLE = BLOCKS.register("sky_cactus_fiber_crafting_table", () -> new AerialHellCraftingTableBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_CRAFTING_TABLE_ITEM = ITEMS.register("sky_cactus_fiber_crafting_table", () -> new BlockItem(SKY_CACTUS_FIBER_CRAFTING_TABLE.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//barrels
	public static final RegistryObject<AerialHellBarrelBlock> AERIAL_TREE_BARREL = BLOCKS.register("aerial_tree_barrel", () -> new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_BARREL_ITEM = ITEMS.register("aerial_tree_barrel", () -> new BurnableBlockItem(AERIAL_TREE_BARREL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<AerialHellBarrelBlock> GOLDEN_BEECH_BARREL = BLOCKS.register("golden_beech_barrel", () -> new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_BARREL_ITEM = ITEMS.register("golden_beech_barrel", () -> new BurnableBlockItem(GOLDEN_BEECH_BARREL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<AerialHellBarrelBlock> COPPER_PINE_BARREL = BLOCKS.register("copper_pine_barrel", () -> new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_BARREL_ITEM = ITEMS.register("copper_pine_barrel", () -> new BurnableBlockItem(COPPER_PINE_BARREL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<AerialHellBarrelBlock> LAPIS_ROBINIA_BARREL = BLOCKS.register("lapis_robinia_barrel", () -> new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_BARREL_ITEM = ITEMS.register("lapis_robinia_barrel", () -> new BurnableBlockItem(LAPIS_ROBINIA_BARREL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<AerialHellBarrelBlock> SKY_CACTUS_FIBER_BARREL = BLOCKS.register("sky_cactus_fiber_barrel", () -> new AerialHellBarrelBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_BARREL_ITEM = ITEMS.register("sky_cactus_fiber_barrel", () -> new BlockItem(SKY_CACTUS_FIBER_BARREL.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//composters
	public static final RegistryObject<ComposterBlock> AERIAL_TREE_COMPOSTER = BLOCKS.register("aerial_tree_composter", () -> new ComposterBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> AERIAL_TREE_COMPOSTER_ITEM = ITEMS.register("aerial_tree_composter", () -> new BurnableBlockItem(AERIAL_TREE_COMPOSTER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<ComposterBlock> GOLDEN_BEECH_COMPOSTER = BLOCKS.register("golden_beech_composter", () -> new ComposterBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> GOLDEN_BEECH_COMPOSTER_ITEM = ITEMS.register("golden_beech_composter", () -> new BurnableBlockItem(GOLDEN_BEECH_COMPOSTER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<ComposterBlock> COPPER_PINE_COMPOSTER = BLOCKS.register("copper_pine_composter", () -> new ComposterBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> COPPER_PINE_COMPOSTER_ITEM = ITEMS.register("copper_pine_composter", () -> new BurnableBlockItem(COPPER_PINE_COMPOSTER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<ComposterBlock> LAPIS_ROBINIA_COMPOSTER = BLOCKS.register("lapis_robinia_composter", () -> new ComposterBlock(AERIAL_TREE_MATERIAL));
	public static final RegistryObject<Item> LAPIS_ROBINIA_COMPOSTER_ITEM = ITEMS.register("lapis_robinia_composter", () -> new BurnableBlockItem(LAPIS_ROBINIA_COMPOSTER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 300));
	public static final RegistryObject<ComposterBlock> SKY_CACTUS_FIBER_COMPOSTER = BLOCKS.register("sky_cactus_fiber_composter", () -> new ComposterBlock(SKY_CACTUS_FIBER_MATERIAL));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER_COMPOSTER_ITEM = ITEMS.register("sky_cactus_fiber_composter", () -> new BlockItem(SKY_CACTUS_FIBER_COMPOSTER.get(), new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//item for crafts
	public static final RegistryObject<Item> SKY_STICK = ITEMS.register("sky_stick",() -> new BurnableItem(new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 100));
	
	//throwing knifes
	public static final RegistryObject<Item> IRON_THROWING_KNIFE = ITEMS.register("iron_throwing_knife", () -> new IronThrowingKnifeItem(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
	public static final RegistryObject<Item> GOLD_THROWING_KNIFE = ITEMS.register("gold_throwing_knife", () -> new GoldThrowingKnifeItem(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
	public static final RegistryObject<Item> DIAMOND_THROWING_KNIFE = ITEMS.register("diamond_throwing_knife", () -> new DiamondThrowingKnifeItem(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
	public static final RegistryObject<Item> NETHERITE_THROWING_KNIFE = ITEMS.register("netherite_throwing_knife", () -> new NetheriteThrowingKnifeItem(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
	public static final RegistryObject<Item> RUBY_THROWING_KNIFE = ITEMS.register("ruby_throwing_knife", () -> new RubyThrowingKnifeItem(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
	public static final RegistryObject<Item> AZURITE_THROWING_KNIFE = ITEMS.register("azurite_throwing_knife", () -> new AzuriteThrowingKnifeItem(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
	public static final RegistryObject<Item> MAGMATIC_GEL_THROWING_KNIFE = ITEMS.register("magmatic_gel_throwing_knife", () -> new MagmaticGelThrowingKnifeItem(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
	public static final RegistryObject<Item> VOLUCITE_THROWING_KNIFE = ITEMS.register("volucite_throwing_knife", () -> new VoluciteThrowingKnifeItem(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
	public static final RegistryObject<Item> LIGHTNING_THROWING_KNIFE = ITEMS.register("lightning_throwing_knife", () -> new LightningThrowingKnifeItem(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
	
	//food
	public static final RegistryObject<Item> AERIAL_BERRY = ITEMS.register("aerial_berry",() -> new Item(new Item.Properties().food(new Food.Builder().hunger(2).saturation(0.2F).build()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> ROASTED_AERIAL_BERRY = ITEMS.register("roasted_aerial_berry",() -> new Item(new Item.Properties().food(new Food.Builder().hunger(4).saturation(0.4F).build()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> VIBRANT_AERIAL_BERRY = ITEMS.register("vibrant_aerial_berry",() -> new Item(new Item.Properties().rarity(AerialHellRarities.VIBRANT).food(new Food.Builder().hunger(6).saturation(0.6F).build()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> FROZEN_AERIAL_BERRY = ITEMS.register("frozen_aerial_berry", () -> new FoodWithEffectItem(6, 0.8F, AerialHellRarities.FROZEN, AerialHellItemGroups.AERIAL_HELL_DIMENSION, () -> new EffectInstance(Effects.SLOWNESS, 310, 0), () -> new EffectInstance(Effects.RESISTANCE, 210, 0)));
	public static final RegistryObject<Item> FROZEN_MUTTON = ITEMS.register("frozen_mutton", () -> new FoodWithEffectItem(6, 0.8F, AerialHellRarities.FROZEN, AerialHellItemGroups.AERIAL_HELL_DIMENSION, () -> new EffectInstance(Effects.SLOWNESS, 310, 0), () -> new EffectInstance(Effects.RESISTANCE, 210, 0)));
	public static final RegistryObject<Item> RUBY_AERIAL_BERRY = ITEMS.register("ruby_aerial_berry", () -> new FoodWithEffectItem(6, 0.8F, Rarity.RARE, AerialHellItemGroups.AERIAL_HELL_DIMENSION, () -> new EffectInstance(Effects.HEALTH_BOOST, 2400, 0)));
	public static final RegistryObject<Item> VOLUCITE_AERIAL_BERRY = ITEMS.register("volucite_aerial_berry", () -> new FoodWithEffectItem(6, 0.8F, AerialHellRarities.VIBRANT, AerialHellItemGroups.AERIAL_HELL_DIMENSION, () -> new EffectInstance(Effects.SLOW_FALLING, 2400, 2)));
	public static final RegistryObject<Item> PHANTOM_MEAT = ITEMS.register("phantom_meat", () -> new FoodWithEffectItem(5, 0.8F, Rarity.UNCOMMON, AerialHellItemGroups.AERIAL_HELL_DIMENSION, () -> new EffectInstance(Effects.RESISTANCE, 120, 0)));
	public static final RegistryObject<Item> VIBRANT_PHANTOM_MEAT = ITEMS.register("vibrant_phantom_meat", () -> new FoodWithEffectItem(8, 0.8F, AerialHellRarities.VIBRANT, AerialHellItemGroups.AERIAL_HELL_DIMENSION, () -> new EffectInstance(Effects.RESISTANCE, 400, 1)));
	public static final RegistryObject<Item> FROZEN_PHANTOM_MEAT = ITEMS.register("frozen_phantom_meat", () -> new FoodWithEffectItem(6, 0.6F, AerialHellRarities.FROZEN, AerialHellItemGroups.AERIAL_HELL_DIMENSION, () -> new EffectInstance(Effects.RESISTANCE, 800, 1)));
    public static final RegistryObject<Item> COOKED_PHANTOM_MEAT = ITEMS.register("cooked_phantom_meat",() -> new Item(new Item.Properties().food(new Food.Builder().hunger(10).saturation(0.9F).meat().build()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> GODS_VOLUCITE_AERIAL_BERRY = ITEMS.register("gods_volucite_aerial_berry", () -> new GodsVoluciteBerryItem(AerialHellItemGroups.AERIAL_HELL_DIMENSION));
	public static final RegistryObject<Item> COPPER_PINE_CONE = ITEMS.register("copper_pine_cone",() -> new Item(new Item.Properties().food(new Food.Builder().hunger(4).saturation(0.4F).build()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> AZURITE_COPPER_PINE_CONE = ITEMS.register("azurite_copper_pine_cone", () -> new FoodWithEffectItem(4, 0.4F, Rarity.COMMON, AerialHellItemGroups.AERIAL_HELL_DIMENSION, () -> new EffectInstance(Effects.HASTE, 400, 0)));
	public static final RegistryObject<Item> SKY_CACTUS_FIBER = ITEMS.register("sky_cactus_fiber",() -> new Item(new Item.Properties().food(new Food.Builder().fastToEat().hunger(1).saturation(0.1F).build()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> VIBRANT_SKY_CACTUS_FIBER = ITEMS.register("vibrant_sky_cactus_fiber",() -> new Item(new Item.Properties().rarity(AerialHellRarities.VIBRANT).food(new Food.Builder().fastToEat().hunger(4).saturation(0.3F).build()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> WHITE_SOLID_ETHER_FRAGMENT = ITEMS.register("white_solid_ether_fragment",() -> new Item(new Item.Properties().food(new Food.Builder().hunger(1).saturation(0.1F).build()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> BLUE_SOLID_ETHER_FRAGMENT = ITEMS.register("blue_solid_ether_fragment", () -> new FoodWithEffectItem(1, 0.1F, Rarity.UNCOMMON, AerialHellItemGroups.AERIAL_HELL_DIMENSION, () -> new EffectInstance(AerialHellPotionEffects.HEAD_IN_THE_CLOUDS.get(), 90, 0)));
	public static final RegistryObject<Item> GOLDEN_SOLID_ETHER_FRAGMENT = ITEMS.register("golden_solid_ether_fragment", () -> new FoodWithEffectItem(1, 0.1F, Rarity.UNCOMMON, AerialHellItemGroups.AERIAL_HELL_DIMENSION, () -> new EffectInstance(Effects.SLOW_FALLING, 110, 0)));
	public static final RegistryObject<Item> GREEN_SOLID_ETHER_FRAGMENT = ITEMS.register("green_solid_ether_fragment", () -> new FoodWithEffectItem(1, 0.1F, Rarity.UNCOMMON, AerialHellItemGroups.AERIAL_HELL_DIMENSION, () -> new EffectInstance(Effects.JUMP_BOOST, 90, 1)));
	public static final RegistryObject<Item> GOLDEN_NETHER_MEAT_PIECE = ITEMS.register("golden_nether_meat_piece", () -> new FoodWithEffectItem(1, 0.1F, Rarity.UNCOMMON, AerialHellItemGroups.AERIAL_HELL_DIMENSION, () -> new EffectInstance(Effects.FIRE_RESISTANCE, 110, 0)));
	public static final RegistryObject<Item> GOLDEN_NETHER_STEAK = ITEMS.register("golden_nether_steak", () -> new FoodWithEffectItem(4, 0.4F, Rarity.UNCOMMON, AerialHellItemGroups.AERIAL_HELL_DIMENSION, () -> new EffectInstance(Effects.FIRE_RESISTANCE, 500, 0)));
	public static final RegistryObject<Item> VIBRANT_GOLDEN_NETHER_STEAK = ITEMS.register("vibrant_golden_nether_steak", () -> new FoodWithEffectItem(6, 0.8F, AerialHellRarities.VIBRANT, AerialHellItemGroups.AERIAL_HELL_DIMENSION, () -> new EffectInstance(Effects.FIRE_RESISTANCE, 1000, 0)));
	
	//buckets
	public static final RegistryObject<Item> IRON_LIQUID_OF_GODS_BUCKET = ITEMS.register("iron_liquid_of_gods_bucket", () -> new BucketItem(AerialHellFluids.LIQUID_OF_THE_GODS_SOURCE, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).maxStackSize(1)));
    public static final RegistryObject<Item> RUBY_LIQUID_OF_GODS_BUCKET = ITEMS.register("ruby_liquid_of_gods_bucket", () -> new RubyLiquidOfGodsBucketItem(new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> RUBY_BUCKET = ITEMS.register("ruby_bucket", () -> new RubyBucketItem(new Item.Properties().maxStackSize(16).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> RUBY_WATER_BUCKET = ITEMS.register("ruby_water_bucket", () -> new RubyWaterBucketItem(new Item.Properties().containerItem(RUBY_BUCKET.get()).maxStackSize(1).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> RUBY_MILK_BUCKET = ITEMS.register("ruby_milk_bucket", () -> new RubyMilkBucketItem(new Item.Properties().containerItem(RUBY_BUCKET.get()).maxStackSize(1).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//arrows & bows
	public static final RegistryObject<Item> RUBY_BLOWPIPE_ARROW = ITEMS.register("ruby_blowpipe_arrow", () -> new AerialArrowItem(new Item.Properties().group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> VOLUCITE_BLOWPIPE_ARROW = ITEMS.register("volucite_blowpipe_arrow", () -> new AerialArrowItem(new Item.Properties().rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	public static final RegistryObject<Item> RUBY_BLOWPIPE = ITEMS.register("ruby_blowpipe", () -> new BlowpipeItem(new Item.Properties().maxStackSize(1).maxDamage(200).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 1.7F));
	public static final RegistryObject<Item> VOLUCITE_BLOWPIPE = ITEMS.register("volucite_blowpipe", () -> new BlowpipeItem(new Item.Properties().rarity(AerialHellRarities.VIBRANT).maxStackSize(1).maxDamage(400).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION), 2.4F));
	
	//music discs
	public static final RegistryObject<Item> MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP = ITEMS.register("music_disc_aerial_hell_theme_tommaup", () -> new MusicDiscItem(1, AerialHellSoundEvents.MUSIC_DISC_AERIAL_HELL_THEME_TOMMAUP, new Item.Properties().maxStackSize(1).rarity(Rarity.RARE).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER = ITEMS.register("music_disc_sweden_andreas_zoeller", () -> new MusicDiscItem(1, AerialHellSoundEvents.MUSIC_DISC_SWEDEN_ANDREAS_ZOELLER, new Item.Properties().maxStackSize(1).rarity(Rarity.RARE).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	public static final RegistryObject<Item> MUSIC_DISC_ENTHUSIAST_TOURS = ITEMS.register("music_disc_enthusiast_tours", () -> new MusicDiscItem(1, AerialHellSoundEvents.MUSIC_DISC_ENTHUSIAST_TOURS, new Item.Properties().maxStackSize(1).rarity(Rarity.RARE).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
	
	//fluids
    public static final RegistryObject<FlowingFluidBlock> LIQUID_OF_THE_GODS = BLOCKS.register("liquid_of_the_gods", () -> new AerialHellFluidBlock(AerialHellFluids.LIQUID_OF_THE_GODS_SOURCE, AbstractBlock.Properties.create(Material.LAVA).setLightLevel((state) -> 8)));

    //tools
    public static final RegistryObject<PickaxeItem> SKY_WOOD_PICKAXE = ITEMS.register("sky_wood_pickaxe", () -> new PickaxeItem(ToolMaterials.sky_wood, 1, -2.8F, (new Item.Properties()).addToolType(ToolType.PICKAXE, ToolMaterials.sky_wood.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<PickaxeItem> STELLAR_STONE_PICKAXE = ITEMS.register("stellar_stone_pickaxe", () -> new PickaxeItem(ToolMaterials.stellar_stone, 1, -2.8F, (new Item.Properties()).addToolType(ToolType.PICKAXE, ToolMaterials.stellar_stone.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<PickaxeItem> RUBY_PICKAXE = ITEMS.register("ruby_pickaxe", () -> new PickaxeItem(ToolMaterials.ruby, 1, -2.8F, (new Item.Properties()).addToolType(ToolType.PICKAXE, ToolMaterials.ruby.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<PickaxeItem> AZURITE_PICKAXE = ITEMS.register("azurite_pickaxe", () -> new PickaxeItem(ToolMaterials.azurite, 1, -2.8F, (new Item.Properties()).addToolType(ToolType.PICKAXE, ToolMaterials.azurite.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<PickaxeItem> MAGMATIC_GEL_PICKAXE = ITEMS.register("magmatic_gel_pickaxe", () -> new PickaxeItem(ToolMaterials.magmatic_gel, 1, -2.8F, (new Item.Properties()).addToolType(ToolType.PICKAXE, ToolMaterials.magmatic_gel.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<PickaxeItem> OBSIDIAN_PICKAXE = ITEMS.register("obsidian_pickaxe", () -> new PickaxeItem(ToolMaterials.obsidian, 1, -2.8F, (new Item.Properties()).addToolType(ToolType.PICKAXE, ToolMaterials.obsidian.getHarvestLevel()).rarity(Rarity.EPIC).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<PickaxeItem> VOLUCITE_PICKAXE = ITEMS.register("volucite_pickaxe", () -> new PickaxeItem(ToolMaterials.volucite, 1, -2.8F, (new Item.Properties()).addToolType(ToolType.PICKAXE, ToolMaterials.volucite.getHarvestLevel()).rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<PickaxeItem> LUNATIC_PICKAXE = ITEMS.register("lunatic_pickaxe", () -> new PickaxeItem(ToolMaterials.lunatic, 1, -2.8F, (new Item.Properties()).addToolType(ToolType.PICKAXE, ToolMaterials.lunatic.getHarvestLevel()).rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<PickaxeItem> ARSONIST_PICKAXE = ITEMS.register("arsonist_pickaxe", () -> new PickaxeItem(ToolMaterials.arsonist, 1, -2.8F, (new Item.Properties()).addToolType(ToolType.PICKAXE, ToolMaterials.arsonist.getHarvestLevel()).rarity(AerialHellRarities.MYTHICAL).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).isImmuneToFire()));
    
    public static final RegistryObject<ShovelItem> SKY_WOOD_SHOVEL = ITEMS.register("sky_wood_shovel", () -> new ShovelItem(ToolMaterials.sky_wood, 1.5F, -3F, (new Item.Properties()).addToolType(ToolType.SHOVEL, ToolMaterials.sky_wood.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ShovelItem> STELLAR_STONE_SHOVEL = ITEMS.register("stellar_stone_shovel", () -> new ShovelItem(ToolMaterials.stellar_stone, 1.5F, -3F, (new Item.Properties()).addToolType(ToolType.SHOVEL, ToolMaterials.stellar_stone.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ShovelItem> RUBY_SHOVEL = ITEMS.register("ruby_shovel", () -> new ShovelItem(ToolMaterials.ruby, 1.5F, -3F, (new Item.Properties()).addToolType(ToolType.SHOVEL, ToolMaterials.ruby.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ShovelItem> AZURITE_SHOVEL = ITEMS.register("azurite_shovel", () -> new ShovelItem(ToolMaterials.azurite, 1.5F, -3F, (new Item.Properties()).addToolType(ToolType.SHOVEL, ToolMaterials.azurite.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ShovelItem> MAGMATIC_GEL_SHOVEL = ITEMS.register("magmatic_gel_shovel", () -> new ShovelItem(ToolMaterials.magmatic_gel, 1.5F, -3F, (new Item.Properties()).addToolType(ToolType.SHOVEL, ToolMaterials.magmatic_gel.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ShovelItem> OBSIDIAN_SHOVEL = ITEMS.register("obsidian_shovel", () -> new ShovelItem(ToolMaterials.obsidian, 1.5F, -3F, (new Item.Properties()).addToolType(ToolType.SHOVEL, ToolMaterials.obsidian.getHarvestLevel()).rarity(Rarity.EPIC).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ShovelItem> VOLUCITE_SHOVEL = ITEMS.register("volucite_shovel", () -> new ShovelItem(ToolMaterials.volucite, 1.5F, -3F, (new Item.Properties()).addToolType(ToolType.SHOVEL, ToolMaterials.volucite.getHarvestLevel()).rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ShovelItem> LUNATIC_SHOVEL = ITEMS.register("lunatic_shovel", () -> new ShovelItem(ToolMaterials.lunatic, 1.5F, -3F, (new Item.Properties()).addToolType(ToolType.SHOVEL, ToolMaterials.lunatic.getHarvestLevel()).rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ShovelItem> ARSONIST_SHOVEL = ITEMS.register("arsonist_shovel", () -> new ShovelItem(ToolMaterials.arsonist, 1.5F, -3F, (new Item.Properties()).addToolType(ToolType.SHOVEL, ToolMaterials.arsonist.getHarvestLevel()).rarity(AerialHellRarities.MYTHICAL).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).isImmuneToFire()));
    
    public static final RegistryObject<AxeItem> SKY_WOOD_AXE = ITEMS.register("sky_wood_axe", () -> new AxeItem(ToolMaterials.sky_wood, 6, -3.1F, (new Item.Properties()).addToolType(ToolType.AXE, ToolMaterials.sky_wood.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<AxeItem> STELLAR_STONE_AXE = ITEMS.register("stellar_stone_axe", () -> new AxeItem(ToolMaterials.stellar_stone, 6, -3.1F, (new Item.Properties()).addToolType(ToolType.AXE, ToolMaterials.stellar_stone.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<AxeItem> RUBY_AXE = ITEMS.register("ruby_axe", () -> new AxeItem(ToolMaterials.ruby, 6, -3.1F, (new Item.Properties()).addToolType(ToolType.AXE, ToolMaterials.ruby.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<AxeItem> AZURITE_AXE = ITEMS.register("azurite_axe", () -> new AxeItem(ToolMaterials.azurite, 6, -3.1F, (new Item.Properties()).addToolType(ToolType.AXE, ToolMaterials.azurite.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<AxeItem> MAGMATIC_GEL_AXE = ITEMS.register("magmatic_gel_axe", () -> new AxeItem(ToolMaterials.magmatic_gel, 6, -3.1F, (new Item.Properties()).addToolType(ToolType.AXE, ToolMaterials.magmatic_gel.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<AxeItem> OBSIDIAN_AXE = ITEMS.register("obsidian_axe", () -> new AxeItem(ToolMaterials.obsidian, 6, -3.1F, (new Item.Properties()).addToolType(ToolType.AXE, ToolMaterials.obsidian.getHarvestLevel()).rarity(Rarity.EPIC).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<AxeItem> VOLUCITE_AXE = ITEMS.register("volucite_axe", () -> new AxeItem(ToolMaterials.volucite, 6, -3.1F, (new Item.Properties()).addToolType(ToolType.AXE, ToolMaterials.volucite.getHarvestLevel()).rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<AxeItem> LUNATIC_AXE = ITEMS.register("lunatic_axe", () -> new AxeItem(ToolMaterials.lunatic, 6, -3.1F, (new Item.Properties()).addToolType(ToolType.AXE, ToolMaterials.lunatic.getHarvestLevel()).rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<AxeItem> ARSONIST_AXE = ITEMS.register("arsonist_axe", () -> new AxeItem(ToolMaterials.arsonist, 6, -3.1F, (new Item.Properties()).addToolType(ToolType.AXE, ToolMaterials.arsonist.getHarvestLevel()).rarity(AerialHellRarities.MYTHICAL).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).isImmuneToFire()));
    
    public static final RegistryObject<AxeItem> HEAVY_AXE = ITEMS.register("heavy_axe", () -> new AerialHellAxeItem(ToolMaterials.heavy, 6, -3.5F, -0.30F, 0.0F, (new Item.Properties()).setNoRepair().rarity(Rarity.EPIC).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<AxeItem> BERSERK_AXE = ITEMS.register("berserk_axe", () -> new BerserkAxeItem(ToolMaterials.volucite, 6, -2.9F, 0.05F, 0.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.MYTHICAL).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    
    public static final RegistryObject<HoeItem> SKY_WOOD_HOE = ITEMS.register("sky_wood_hoe", () -> new HoeItem(ToolMaterials.sky_wood, -3, 0.0F, (new Item.Properties()).addToolType(ToolType.HOE, ToolMaterials.sky_wood.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<HoeItem> STELLAR_STONE_HOE = ITEMS.register("stellar_stone_hoe", () -> new HoeItem(ToolMaterials.stellar_stone, -3, 0.0F, (new Item.Properties()).addToolType(ToolType.HOE, ToolMaterials.stellar_stone.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<HoeItem> RUBY_HOE = ITEMS.register("ruby_hoe", () -> new HoeItem(ToolMaterials.ruby, -3, 0.0F, (new Item.Properties()).addToolType(ToolType.HOE, ToolMaterials.ruby.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<HoeItem> AZURITE_HOE = ITEMS.register("azurite_hoe", () -> new HoeItem(ToolMaterials.azurite, -3, 0.0F, (new Item.Properties()).addToolType(ToolType.HOE, ToolMaterials.azurite.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<HoeItem> MAGMATIC_GEL_HOE = ITEMS.register("magmatic_gel_hoe", () -> new HoeItem(ToolMaterials.magmatic_gel, 0, 0.0F, (new Item.Properties()).addToolType(ToolType.HOE, ToolMaterials.magmatic_gel.getHarvestLevel()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<HoeItem> OBSIDIAN_HOE = ITEMS.register("obsidian_hoe", () -> new HoeItem(ToolMaterials.obsidian, -3, 0.0F, (new Item.Properties()).addToolType(ToolType.HOE, ToolMaterials.obsidian.getHarvestLevel()).rarity(Rarity.EPIC).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<HoeItem> VOLUCITE_HOE = ITEMS.register("volucite_hoe", () -> new HoeItem(ToolMaterials.volucite, -3, 0.0F, (new Item.Properties()).addToolType(ToolType.HOE, ToolMaterials.volucite.getHarvestLevel()).rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<HoeItem> LUNATIC_HOE = ITEMS.register("lunatic_hoe", () -> new HoeItem(ToolMaterials.lunatic, -1, 0.0F, (new Item.Properties()).addToolType(ToolType.HOE, ToolMaterials.lunatic.getHarvestLevel()).rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<HoeItem> ARSONIST_HOE = ITEMS.register("arsonist_hoe", () -> new HoeItem(ToolMaterials.arsonist, -1, 0.0F, (new Item.Properties()).addToolType(ToolType.HOE, ToolMaterials.arsonist.getHarvestLevel()).rarity(AerialHellRarities.MYTHICAL).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).isImmuneToFire()));
    
    //weapons
    public static final RegistryObject<SwordItem> SKY_WOOD_SWORD = ITEMS.register("sky_wood_sword", () -> new SwordItem(ToolMaterials.sky_wood, 3, -2.4F, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> STELLAR_STONE_SWORD = ITEMS.register("stellar_stone_sword", () -> new SwordItem(ToolMaterials.stellar_stone, 3, -2.4F, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> RUBY_SWORD = ITEMS.register("ruby_sword", () -> new SwordItem(ToolMaterials.ruby, 3, -2.4F, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> AZURITE_SWORD = ITEMS.register("azurite_sword", () -> new SwordItem(ToolMaterials.azurite, 3, -2.4F, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> MAGMATIC_GEL_SWORD = ITEMS.register("magmatic_gel_sword", () -> new SwordItem(ToolMaterials.magmatic_gel, 3, -2.4F, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> OBSIDIAN_SWORD = ITEMS.register("obsidian_sword", () -> new SwordItem(ToolMaterials.obsidian, 3, -2.4F, (new Item.Properties()).rarity(Rarity.EPIC).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> VOLUCITE_SWORD = ITEMS.register("volucite_sword", () -> new SwordItem(ToolMaterials.volucite, 3, -2.4F, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> LUNATIC_SWORD = ITEMS.register("lunatic_sword", () -> new SwordItem(ToolMaterials.lunatic, 3, -2.4F, (new Item.Properties()).rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> ARSONIST_SWORD = ITEMS.register("arsonist_sword", () -> new AerialHellSwordItem(ToolMaterials.arsonist, 3, -2.4F, 0.0F, 0.0F, (new Item.Properties()).rarity(AerialHellRarities.MYTHICAL).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).isImmuneToFire()));
    
    public static final RegistryObject<SwordItem> HEAVY_SWORD = ITEMS.register("heavy_sword", () -> new AerialHellSwordItem(ToolMaterials.heavy, 3, -2.7F, -0.30F, 0.0F, (new Item.Properties()).setNoRepair().rarity(Rarity.EPIC).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> HEALTH_BOOST_SWORD = ITEMS.register("health_boost_sword", () -> new AerialHellSwordItem(ToolMaterials.volucite, 4, -2.4F, 0.0F, 4.0F, (new Item.Properties()).setNoRepair().rarity(Rarity.EPIC).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> NINJA_SWORD = ITEMS.register("ninja_sword", () -> new EffectSwordItem(ToolMaterials.volucite, 2, -1.6F, 0.15F, 0.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> GLOUTON_SWORD = ITEMS.register("glouton_sword", () -> new EffectSwordItem(ToolMaterials.ruby, 4, -2.4F, 0.0F, 0.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> RANDOM_SWORD = ITEMS.register("random_sword", () -> new EffectSwordItem(ToolMaterials.ruby, 4, -2.4F, 0.0F, 0.0F, (new Item.Properties()).setNoRepair().rarity(Rarity.EPIC).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> DISLOYAL_SWORD = ITEMS.register("disloyal_sword", () -> new AerialHellSwordItem(ToolMaterials.volucite, 4, -2.4F, 0.0F, 0.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> ABSOLUTE_ZERO_SWORD = ITEMS.register("absolute_zero_sword", () -> new AerialHellSwordItem(ToolMaterials.volucite, 3, -2.4F, 0.0F, 0.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.MYTHICAL).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> SWORD_OF_LIGHT = ITEMS.register("sword_of_light", () -> new EffectSwordItem(ToolMaterials.volucite, 4, -2.4F, 0.0F, 0.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> ANTIDOTE_SWORD = ITEMS.register("antidote_sword", () -> new EffectSwordItem(ToolMaterials.ruby, 4, -2.4F, 0.0F, 0.0F, (new Item.Properties()).setNoRepair().rarity(Rarity.EPIC).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> GLASS_CANON_SWORD = ITEMS.register("glass_canon_sword", () -> new EffectSwordItem(ToolMaterials.volucite, 8, -1.6F, 0.0F, -10.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.MYTHICAL).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<SwordItem> GOD_SWORD = ITEMS.register("god_sword", () -> new EffectSwordItem(ToolMaterials.arsonist, 4, -2.4F, 0.0F, 0.0F, (new Item.Properties()).setNoRepair().rarity(AerialHellRarities.MYTHICAL).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    
    public static final RegistryObject<SwordItem> FORGOTTEN_BATTLE_TRIDENT = ITEMS.register("forgotten_battle_trident", () -> new ForgottenBattleTridentItem(ToolMaterials.volucite, 4, -2.9F, 0.2F, (new Item.Properties()).maxDamage(1000).rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    
    //armor
    public static final RegistryObject<ArmorItem> RUBY_HELMET = ITEMS.register("ruby_helmet", () -> new ArmorItem(ArmorMaterials.ruby, EquipmentSlotType.HEAD, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> RUBY_CHESTPLATE = ITEMS.register("ruby_chestplate", () -> new ArmorItem(ArmorMaterials.ruby, EquipmentSlotType.CHEST, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> RUBY_LEGGINGS = ITEMS.register("ruby_leggings", () -> new ArmorItem(ArmorMaterials.ruby, EquipmentSlotType.LEGS, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> RUBY_BOOTS = ITEMS.register("ruby_boots", () -> new ArmorItem(ArmorMaterials.ruby, EquipmentSlotType.FEET, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    
    public static final RegistryObject<ArmorItem> AZURITE_HELMET = ITEMS.register("azurite_helmet", () -> new ArmorItem(ArmorMaterials.azurite, EquipmentSlotType.HEAD, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> AZURITE_CHESTPLATE = ITEMS.register("azurite_chestplate", () -> new ArmorItem(ArmorMaterials.azurite, EquipmentSlotType.CHEST, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> AZURITE_LEGGINGS = ITEMS.register("azurite_leggings", () -> new ArmorItem(ArmorMaterials.azurite, EquipmentSlotType.LEGS, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> AZURITE_BOOTS = ITEMS.register("azurite_boots", () -> new ArmorItem(ArmorMaterials.azurite, EquipmentSlotType.FEET, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    
    public static final RegistryObject<ArmorItem> OBSIDIAN_HELMET = ITEMS.register("obsidian_helmet", () -> new ArmorItem(ArmorMaterials.obsidian, EquipmentSlotType.HEAD, (new Item.Properties()).rarity(Rarity.EPIC).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> OBSIDIAN_CHESTPLATE = ITEMS.register("obsidian_chestplate", () -> new ArmorItem(ArmorMaterials.obsidian, EquipmentSlotType.CHEST, (new Item.Properties()).rarity(Rarity.EPIC).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> OBSIDIAN_LEGGINGS = ITEMS.register("obsidian_leggings", () -> new ArmorItem(ArmorMaterials.obsidian, EquipmentSlotType.LEGS, (new Item.Properties()).rarity(Rarity.EPIC).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> OBSIDIAN_BOOTS = ITEMS.register("obsidian_boots", () -> new ArmorItem(ArmorMaterials.obsidian, EquipmentSlotType.FEET, (new Item.Properties()).rarity(Rarity.EPIC).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    
    public static final RegistryObject<ArmorItem> VOLUCITE_HELMET = ITEMS.register("volucite_helmet", () -> new ArmorItem(ArmorMaterials.volucite, EquipmentSlotType.HEAD, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> VOLUCITE_CHESTPLATE = ITEMS.register("volucite_chestplate", () -> new ArmorItem(ArmorMaterials.volucite, EquipmentSlotType.CHEST, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> VOLUCITE_LEGGINGS = ITEMS.register("volucite_leggings", () -> new ArmorItem(ArmorMaterials.volucite, EquipmentSlotType.LEGS, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> VOLUCITE_BOOTS = ITEMS.register("volucite_boots", () -> new ArmorItem(ArmorMaterials.volucite, EquipmentSlotType.FEET, (new Item.Properties()).rarity(AerialHellRarities.VIBRANT).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    
    public static final RegistryObject<ArmorItem> MAGMATIC_GEL_HELMET = ITEMS.register("magmatic_gel_helmet", () -> new ArmorItem(ArmorMaterials.magmatic_gel, EquipmentSlotType.HEAD, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> MAGMATIC_GEL_CHESTPLATE = ITEMS.register("magmatic_gel_chestplate", () -> new ArmorItem(ArmorMaterials.magmatic_gel, EquipmentSlotType.CHEST, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> MAGMATIC_GEL_LEGGINGS = ITEMS.register("magmatic_gel_leggings", () -> new ArmorItem(ArmorMaterials.magmatic_gel, EquipmentSlotType.LEGS, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> MAGMATIC_GEL_BOOTS = ITEMS.register("magmatic_gel_boots", () -> new ArmorItem(ArmorMaterials.magmatic_gel, EquipmentSlotType.FEET, (new Item.Properties()).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    
    public static final RegistryObject<ArmorItem> LUNATIC_HELMET = ITEMS.register("lunatic_helmet", () -> new ArmorItem(ArmorMaterials.lunatic, EquipmentSlotType.HEAD, (new Item.Properties()).rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> LUNATIC_CHESTPLATE = ITEMS.register("lunatic_chestplate", () -> new ArmorItem(ArmorMaterials.lunatic, EquipmentSlotType.CHEST, (new Item.Properties()).rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> LUNATIC_LEGGINGS = ITEMS.register("lunatic_leggings", () -> new ArmorItem(ArmorMaterials.lunatic, EquipmentSlotType.LEGS, (new Item.Properties()).rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<ArmorItem> LUNATIC_BOOTS = ITEMS.register("lunatic_boots", () -> new ArmorItem(ArmorMaterials.lunatic, EquipmentSlotType.FEET, (new Item.Properties()).rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    
    public static final RegistryObject<ArmorItem> ARSONIST_HELMET = ITEMS.register("arsonist_helmet", () -> new ArmorItem(ArmorMaterials.arsonist, EquipmentSlotType.HEAD, (new Item.Properties()).rarity(AerialHellRarities.MYTHICAL).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).isImmuneToFire()));
    public static final RegistryObject<ArmorItem> ARSONIST_CHESTPLATE = ITEMS.register("arsonist_chestplate", () -> new ArmorItem(ArmorMaterials.arsonist, EquipmentSlotType.CHEST, (new Item.Properties()).rarity(AerialHellRarities.MYTHICAL).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).isImmuneToFire()));
    public static final RegistryObject<ArmorItem> ARSONIST_LEGGINGS = ITEMS.register("arsonist_leggings", () -> new ArmorItem(ArmorMaterials.arsonist, EquipmentSlotType.LEGS, (new Item.Properties()).rarity(AerialHellRarities.MYTHICAL).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).isImmuneToFire()));
    public static final RegistryObject<ArmorItem> ARSONIST_BOOTS = ITEMS.register("arsonist_boots", () -> new ArmorItem(ArmorMaterials.arsonist, EquipmentSlotType.FEET, (new Item.Properties()).rarity(AerialHellRarities.MYTHICAL).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).isImmuneToFire()));

    //effect totems
    public static final RegistryObject<Item> REGENERATION_TOTEM = ITEMS.register("regeneration_totem", () -> new EffectTotemItem(new Item.Properties().maxStackSize(1).rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<Item> SPEED_TOTEM = ITEMS.register("speed_totem", () -> new EffectTotemItem(new Item.Properties().maxStackSize(1).rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<Item> SPEED_II_TOTEM = ITEMS.register("speed_ii_totem", () -> new EnchantedEffectTotemItem(new Item.Properties().maxStackSize(1).rarity(AerialHellRarities.MYTHICAL).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<Item> NIGHT_VISION_TOTEM = ITEMS.register("night_vision_totem", () -> new EffectTotemItem(new Item.Properties().maxStackSize(1).rarity(AerialHellRarities.LEGENDARY).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<Item> AGILITY_TOTEM = ITEMS.register("agility_totem", () -> new EnchantedEffectTotemItem(new Item.Properties().maxStackSize(1).rarity(AerialHellRarities.MYTHICAL).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<Item> HERO_TOTEM = ITEMS.register("hero_totem", () -> new EffectTotemItem(new Item.Properties().maxStackSize(1).rarity(AerialHellRarities.MYTHICAL).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION)));
    public static final RegistryObject<Item> GOD_TOTEM = ITEMS.register("god_totem", () -> new EnchantedEffectTotemItem(new Item.Properties().maxStackSize(1).rarity(AerialHellRarities.MYTHICAL).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION).isImmuneToFire()));
    public static final RegistryObject<Item> CURSED_TOTEM = ITEMS.register("cursed_totem", () -> new EffectTotemItem(new Item.Properties().maxStackSize(1).rarity(AerialHellRarities.MYTHICAL).group(AerialHellItemGroups.AERIAL_HELL_DIMENSION))); 
}
