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
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CaveVinesBlock;
import net.minecraft.world.level.block.CaveVinesPlantBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.UntintedParticleLeavesBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import java.util.function.ToIntFunction;

public class AerialHellBlocks
{
    public static void registerAxeStrippingBlocks()
    {
        AxeItem.STRIPPABLES = ImmutableMap.<Block, Block>builder()
                .putAll(AxeItem.STRIPPABLES)
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
    public static BlockBehaviour.Properties AERIAL_TREE_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.5F, 2.5F).sound(SoundType.WOOD);
    public static BlockBehaviour.Properties COPPER_PINE_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(4.5F, 4.5F).sound(SoundType.WOOD);
    public static BlockBehaviour.Properties SHADOW_PINE_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(4.0F, 4.0F).sound(SoundType.WOOD);
    public static BlockBehaviour.Properties SHROOM_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(3.5F, 3.5F).sound(SoundType.HARD_CROP);
    public static BlockBehaviour.Properties AERIAL_TREE_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion().noCollision();
    public static BlockBehaviour.Properties COPPER_PINE_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(4.5F, 4.5F).sound(SoundType.WOOD).noOcclusion().noCollision();
    public static BlockBehaviour.Properties SHADOW_PINE_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(4.0F, 4.0F).sound(SoundType.WOOD).noOcclusion().noCollision();
    public static BlockBehaviour.Properties SKY_CACTUS_FIBER_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.5F, 2.5F).sound(SoundType.WOOD);
    public static BlockBehaviour.Properties SKY_CACTUS_FIBER_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion().noCollision();
    public static BlockBehaviour.Properties SHROOM_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(3.5F, 3.5F).sound(SoundType.WOOD).noOcclusion().noCollision();
    public static BlockBehaviour.Properties MUD_CHEST_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(20.0F, 1000.0F).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion();
    public static BlockBehaviour.Properties LUNATIC_CHEST_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).strength(30.0F, 1000.0F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion();
    public static BlockBehaviour.Properties VOLUCITE_CHEST_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(50.0F, 1200.0F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion();
    public static BlockBehaviour.Properties GOLDEN_NETHER_CHEST_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(50.0F, 1200.0F).sound(SoundType.BASALT).requiresCorrectToolForDrops().noOcclusion();
    public static BlockBehaviour.Properties METAL_NOTSOLID_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(10.0F, 2.0F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion();


    //portal
    public static final AerialHellPortalBlock AERIAL_HELL_PORTAL = register(Keys.AERIAL_HELL_PORTAL.identifier().getPath(), new AerialHellPortalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_PORTAL).setId(Keys.AERIAL_HELL_PORTAL).noOcclusion().isViewBlocking((state, blockview, pos) -> false).lightLevel((state) -> 10).mapColor(DyeColor.GREEN)));
    public static final Block STELLAR_PORTAL_FRAME_BLOCK = register(Keys.STELLAR_PORTAL_FRAME_BLOCK.identifier().getPath(), new Block(BlockBehaviour.Properties.of().setId(Keys.STELLAR_PORTAL_FRAME_BLOCK).mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(25.0F, 600.0F)));
    public static final Block STELLAR_PORTAL_FRAME_ORE = register(Keys.STELLAR_PORTAL_FRAME_ORE.identifier().getPath(), new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().setId(Keys.STELLAR_PORTAL_FRAME_ORE).strength(25.0F, 600.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block DEEPSLATE_STELLAR_PORTAL_FRAME_ORE = register(Keys.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE.identifier().getPath(), new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().setId(Keys.DEEPSLATE_STELLAR_PORTAL_FRAME_ORE).strength(30.0F, 600.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));

    //torch
    public static final Block CRYSTALLIZED_TORCH = register(Keys.CRYSTALLIZED_TORCH.identifier().getPath(), new AerialHellTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).setId(Keys.CRYSTALLIZED_TORCH)));
    public static final Block CRYSTALLIZED_WALL_TORCH = register(Keys.CRYSTALLIZED_WALL_TORCH.identifier().getPath(), new AerialHellWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).setId(Keys.CRYSTALLIZED_WALL_TORCH).overrideLootTable(CRYSTALLIZED_TORCH.getLootTable())));
    public static final Block FLUORITE_TORCH = register(Keys.FLUORITE_TORCH.identifier().getPath(), new AerialHellTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).setId(Keys.FLUORITE_TORCH)));
    public static final Block FLUORITE_WALL_TORCH = register(Keys.FLUORITE_WALL_TORCH.identifier().getPath(), new AerialHellWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).setId(Keys.FLUORITE_WALL_TORCH).overrideLootTable(FLUORITE_TORCH.getLootTable())));
    public static final Block VOLUCITE_TORCH = register(Keys.VOLUCITE_TORCH.identifier().getPath(), new AerialHellTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).setId(Keys.VOLUCITE_TORCH).lightLevel((state) -> {return 9;})));
    public static final Block VOLUCITE_WALL_TORCH = register(Keys.VOLUCITE_WALL_TORCH.identifier().getPath(), new AerialHellWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).setId(Keys.VOLUCITE_WALL_TORCH).overrideLootTable(VOLUCITE_TORCH.getLootTable()).lightLevel((state) -> {return 9;})));
    public static final Block SHADOW_TORCH = register(Keys.SHADOW_TORCH.identifier().getPath(), new AerialHellTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).setId(Keys.SHADOW_TORCH).lightLevel((state) -> {return 9;})));
    public static final Block SHADOW_WALL_TORCH = register(Keys.SHADOW_WALL_TORCH.identifier().getPath(), new AerialHellWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).setId(Keys.SHADOW_WALL_TORCH).overrideLootTable(SHADOW_TORCH.getLootTable()).lightLevel((state) -> {return 9;})));

    //lanterns
    public static final Block CRYSTALLIZED_LANTERN = register(Keys.CRYSTALLIZED_LANTERN.identifier().getPath(), new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.CRYSTALLIZED_LANTERN)));
    public static final Block FLUORITE_LANTERN = register(Keys.FLUORITE_LANTERN.identifier().getPath(), new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.FLUORITE_LANTERN)));
    public static final Block RUBY_LANTERN = register(Keys.RUBY_LANTERN.identifier().getPath(), new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.RUBY_LANTERN)));
    public static final Block RUBY_CRYSTALLIZED_LANTERN = register(Keys.RUBY_CRYSTALLIZED_LANTERN.identifier().getPath(), new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.RUBY_CRYSTALLIZED_LANTERN)));
    public static final Block RUBY_FLUORITE_LANTERN = register(Keys.RUBY_FLUORITE_LANTERN.identifier().getPath(), new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.RUBY_FLUORITE_LANTERN)));
    public static final Block VOLUCITE_LANTERN = register(Keys.VOLUCITE_LANTERN.identifier().getPath(), new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.VOLUCITE_LANTERN)));
    public static final Block VOLUCITE_CRYSTALLIZED_LANTERN = register(Keys.VOLUCITE_CRYSTALLIZED_LANTERN.identifier().getPath(), new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.VOLUCITE_CRYSTALLIZED_LANTERN)));
    public static final Block VOLUCITE_FLUORITE_LANTERN = register(Keys.VOLUCITE_FLUORITE_LANTERN.identifier().getPath(), new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.VOLUCITE_FLUORITE_LANTERN)));
    public static final Block LUNATIC_LANTERN = register(Keys.LUNATIC_LANTERN.identifier().getPath(), new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.LUNATIC_LANTERN)));
    public static final Block SHADOW_LANTERN = register(Keys.SHADOW_LANTERN.identifier().getPath(), new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_LANTERN).setId(Keys.SHADOW_LANTERN)));

    //chains
    public static final ChainBlock RUBY_CHAIN = register(Keys.RUBY_CHAIN.identifier().getPath(), new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_CHAIN).setId(Keys.RUBY_CHAIN)));
    public static final ChainBlock VOLUCITE_CHAIN = register(Keys.VOLUCITE_CHAIN.identifier().getPath(), new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_CHAIN).setId(Keys.VOLUCITE_CHAIN)));
    public static final ChainBlock LUNATIC_CHAIN = register(Keys.LUNATIC_CHAIN.identifier().getPath(), new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_CHAIN).setId(Keys.LUNATIC_CHAIN)));
    public static final ChainBlock SHADOW_CHAIN = register(Keys.SHADOW_CHAIN.identifier().getPath(), new ShadowChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_CHAIN).setId(Keys.SHADOW_CHAIN)));

    //grass & dirt
    public static final StellarGrassBlock STELLAR_GRASS_BLOCK = register(Keys.STELLAR_GRASS_BLOCK.identifier().getPath(), new StellarGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).setId(Keys.STELLAR_GRASS_BLOCK)));
    public static final Block CHISELED_STELLAR_GRASS_BLOCK = register(Keys.CHISELED_STELLAR_GRASS_BLOCK.identifier().getPath(), new StellarGrassBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_GRASS_BLOCK).setId(Keys.CHISELED_STELLAR_GRASS_BLOCK)));
    public static final Block STELLAR_DIRT = register(Keys.STELLAR_DIRT.identifier().getPath(), new StellarDirtBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).setId(Keys.STELLAR_DIRT)));
    public static final Block STELLAR_COARSE_DIRT = register(Keys.STELLAR_COARSE_DIRT.identifier().getPath(), new StellarDirtBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COARSE_DIRT).setId(Keys.STELLAR_COARSE_DIRT)));
    public static final Block STELLAR_FARMLAND = register(Keys.STELLAR_FARMLAND.identifier().getPath(), new StellarFarmBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).setId(Keys.STELLAR_FARMLAND).randomTicks().strength(0.6F).sound(SoundType.GRAVEL).isViewBlocking((state, blockgetter, pos) -> true).isSuffocating((state, blockgetter, pos) -> true)));
    public static final Block STELLAR_DIRT_PATH = register(Keys.STELLAR_DIRT_PATH.identifier().getPath(), new StellarDirtPathBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT_PATH).setId(Keys.STELLAR_DIRT_PATH)));
    public static final Block STELLAR_PODZOL = register(Keys.STELLAR_PODZOL.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL).setId(Keys.STELLAR_PODZOL)));
    public static final Block STELLAR_CRYSTAL_PODZOL = register(Keys.STELLAR_CRYSTAL_PODZOL.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL).setId(Keys.STELLAR_CRYSTAL_PODZOL)));
    public static final Block CHISELED_STELLAR_DIRT = register(Keys.CHISELED_STELLAR_DIRT.identifier().getPath(), new StellarDirtBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_DIRT).setId(Keys.CHISELED_STELLAR_DIRT)));
    public static final ShadowGrassBlock SHADOW_GRASS_BLOCK = register(Keys.SHADOW_GRASS_BLOCK.identifier().getPath(), new ShadowGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK).setId(Keys.SHADOW_GRASS_BLOCK)));

    //slippery sand
    public static final Block SLIPPERY_SAND = register(Keys.SLIPPERY_SAND.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).setId(Keys.SLIPPERY_SAND).friction(1.025F)));
    public static final Block SLIPPERY_SAND_STONE = register(Keys.SLIPPERY_SAND_STONE.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).setId(Keys.SLIPPERY_SAND_STONE).friction(1.01F)));
    public static final Block SLIPPERY_SAND_STONE_BRICKS = register(Keys.SLIPPERY_SAND_STONE_BRICKS.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE).setId(Keys.SLIPPERY_SAND_STONE_BRICKS).friction(1.005F)));
    public static final Block CUT_SLIPPERY_SAND_STONE = register(Keys.CUT_SLIPPERY_SAND_STONE.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE).setId(Keys.CUT_SLIPPERY_SAND_STONE).friction(1.005F)));
    public static final Block CRACKED_SLIPPERY_SAND_STONE_BRICKS = register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE).setId(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS).friction(1.003F)));

    //giant root
    public static final RotatedPillarBlock GIANT_ROOT = register(Keys.GIANT_ROOT.identifier().getPath(), new RotatedPillarBlock(AERIAL_TREE_MATERIAL.setId(Keys.GIANT_ROOT)));

    //aerial_tree
    public static final ShiftableLogBlock AERIAL_TREE_LOG = register(Keys.AERIAL_TREE_LOG.identifier().getPath(), new ShiftableLogBlock(AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_LOG), () -> AerialHellBlocks.SHADOW_AERIAL_TREE_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final RotatedPillarBlock STRIPPED_AERIAL_TREE_LOG = register(Keys.STRIPPED_AERIAL_TREE_LOG.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG).setId(Keys.STRIPPED_AERIAL_TREE_LOG)));
    public static final RotatedPillarBlock AERIAL_TREE_WOOD = register(Keys.AERIAL_TREE_WOOD.identifier().getPath(), new RotatedPillarBlock(AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_WOOD)));
    public static final RotatedPillarBlock STRIPPED_AERIAL_TREE_WOOD = register(Keys.STRIPPED_AERIAL_TREE_WOOD.identifier().getPath(), new RotatedPillarBlock(AERIAL_TREE_MATERIAL.setId(Keys.STRIPPED_AERIAL_TREE_WOOD)));
    public static final ShiftableLeavesBlock AERIAL_TREE_LEAVES = register(Keys.AERIAL_TREE_LEAVES.identifier().getPath(), new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(Keys.AERIAL_TREE_LEAVES), () -> AerialHellBlocks.SHADOW_AERIAL_TREE_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block AERIAL_TREE_PLANKS = register(Keys.AERIAL_TREE_PLANKS.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG).setId(Keys.AERIAL_TREE_PLANKS)));
    public static final Block CHISELED_AERIAL_TREE_PLANKS = register(Keys.CHISELED_AERIAL_TREE_PLANKS.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS).setId(Keys.CHISELED_AERIAL_TREE_PLANKS)));
    public static final Block AERIAL_TREE_BOOKSHELF = register(Keys.AERIAL_TREE_BOOKSHELF.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS).setId(Keys.AERIAL_TREE_BOOKSHELF)));
    public static final SaplingBlock AERIAL_TREE_SAPLING = register(Keys.AERIAL_TREE_SAPLING.identifier().getPath(), new AerialHellSaplingBlock(AerialHellTreeGrowers.AERIAL_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).setId(Keys.AERIAL_TREE_SAPLING), AerialHellConfiguredFeatures.GIANT_AERIAL_TREE));

    //petrified aerial tree
    public static final Block PETRIFIED_AERIAL_TREE_LOG = register(Keys.PETRIFIED_AERIAL_TREE_LOG.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG).setId(Keys.PETRIFIED_AERIAL_TREE_LOG)));

    //golden beech
    public static final ShiftableLogBlock GOLDEN_BEECH_LOG = register(Keys.GOLDEN_BEECH_LOG.identifier().getPath(), new ShiftableLogBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG).setId(Keys.GOLDEN_BEECH_LOG), () -> AerialHellBlocks.SHADOW_GOLDEN_BEECH_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final Block STRIPPED_GOLDEN_BEECH_LOG = register(Keys.STRIPPED_GOLDEN_BEECH_LOG.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG).setId(Keys.STRIPPED_GOLDEN_BEECH_LOG)));
    public static final Block GOLDEN_BEECH_WOOD = register(Keys.GOLDEN_BEECH_WOOD.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG).setId(Keys.GOLDEN_BEECH_WOOD)));
    public static final Block STRIPPED_GOLDEN_BEECH_WOOD = register(Keys.STRIPPED_GOLDEN_BEECH_WOOD.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG).setId(Keys.STRIPPED_GOLDEN_BEECH_WOOD)));
    public static final Block GOLDEN_BEECH_PLANKS = register(Keys.GOLDEN_BEECH_PLANKS.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG).setId(Keys.GOLDEN_BEECH_PLANKS)));
    public static final Block CHISELED_GOLDEN_BEECH_PLANKS = register(Keys.CHISELED_GOLDEN_BEECH_PLANKS.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS).setId(Keys.CHISELED_GOLDEN_BEECH_PLANKS)));
    public static final ShiftableLeavesBlock GOLDEN_BEECH_LEAVES = register(Keys.GOLDEN_BEECH_LEAVES.identifier().getPath(), new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(Keys.GOLDEN_BEECH_LEAVES), () -> AerialHellBlocks.SHADOW_GOLDEN_BEECH_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block GOLDEN_BEECH_BOOKSHELF = register(Keys.GOLDEN_BEECH_BOOKSHELF.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS).setId(Keys.GOLDEN_BEECH_BOOKSHELF)));
    public static final Block GOLDEN_BEECH_SAPLING = register(Keys.GOLDEN_BEECH_SAPLING.identifier().getPath(), new SaplingBlock(AerialHellTreeGrowers.GOLDEN_BEECH, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).setId(Keys.GOLDEN_BEECH_SAPLING)));

    //copper pine
    public static final ShiftableLogBlock COPPER_PINE_LOG = register(Keys.COPPER_PINE_LOG.identifier().getPath(), new ShiftableLogBlock(COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_LOG), () -> AerialHellBlocks.SHADOW_COPPER_PINE_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final RotatedPillarBlock STRIPPED_COPPER_PINE_LOG = register(Keys.STRIPPED_COPPER_PINE_LOG.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG).setId(Keys.STRIPPED_COPPER_PINE_LOG)));
    public static final RotatedPillarBlock COPPER_PINE_WOOD = register(Keys.COPPER_PINE_WOOD.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG).setId(Keys.COPPER_PINE_WOOD)));
    public static final RotatedPillarBlock STRIPPED_COPPER_PINE_WOOD = register(Keys.STRIPPED_COPPER_PINE_WOOD.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG).setId(Keys.STRIPPED_COPPER_PINE_WOOD)));
    public static final Block COPPER_PINE_PLANKS = register(Keys.COPPER_PINE_PLANKS.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG).setId(Keys.COPPER_PINE_PLANKS)));
    public static final ShiftableLeavesBlock COPPER_PINE_LEAVES = register(Keys.COPPER_PINE_LEAVES.identifier().getPath(), new LeavesWithAmbientParticlesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(Keys.COPPER_PINE_LEAVES), () -> AerialHellBlocks.SHADOW_COPPER_PINE_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block COPPER_PINE_BOOKSHELF = register(Keys.COPPER_PINE_BOOKSHELF.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_PLANKS).setId(Keys.COPPER_PINE_BOOKSHELF)));
    public static final SaplingBlock COPPER_PINE_SAPLING = register(Keys.COPPER_PINE_SAPLING.identifier().getPath(), new AerialHellSaplingBlock(AerialHellTreeGrowers.COPPER_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).setId(Keys.COPPER_PINE_SAPLING), AerialHellConfiguredFeatures.GIANT_COPPER_PINE, AerialHellConfiguredFeatures.HUGE_COPPER_PINE, 0.1F));

    //lapis robinia
    public static final ShiftableLogBlock LAPIS_ROBINIA_LOG = register(Keys.LAPIS_ROBINIA_LOG.identifier().getPath(), new ShiftableLogBlock(COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_LOG), () -> AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final EffectLogBlock ENCHANTED_LAPIS_ROBINIA_LOG = register(Keys.ENCHANTED_LAPIS_ROBINIA_LOG.identifier().getPath(), new EffectLogBlock(COPPER_PINE_MATERIAL.setId(Keys.ENCHANTED_LAPIS_ROBINIA_LOG), () -> AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final RotatedPillarBlock STRIPPED_LAPIS_ROBINIA_LOG = register(Keys.STRIPPED_LAPIS_ROBINIA_LOG.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG).setId(Keys.STRIPPED_LAPIS_ROBINIA_LOG)));
    public static final RotatedPillarBlock LAPIS_ROBINIA_WOOD = register(Keys.LAPIS_ROBINIA_WOOD.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG).setId(Keys.LAPIS_ROBINIA_WOOD)));
    public static final RotatedPillarBlock STRIPPED_LAPIS_ROBINIA_WOOD = register(Keys.STRIPPED_LAPIS_ROBINIA_WOOD.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG).setId(Keys.STRIPPED_LAPIS_ROBINIA_WOOD)));
    public static final ShiftableLeavesBlock LAPIS_ROBINIA_LEAVES = register(Keys.LAPIS_ROBINIA_LEAVES.identifier().getPath(), new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(Keys.LAPIS_ROBINIA_LEAVES), () -> AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block LAPIS_ROBINIA_PLANKS = register(Keys.LAPIS_ROBINIA_PLANKS.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG).setId(Keys.LAPIS_ROBINIA_PLANKS)));
    public static final Block LAPIS_ROBINIA_BOOKSHELF = register(Keys.LAPIS_ROBINIA_BOOKSHELF.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_PLANKS).setId(Keys.LAPIS_ROBINIA_BOOKSHELF)));
    public static final SaplingBlock LAPIS_ROBINIA_SAPLING = register(Keys.LAPIS_ROBINIA_SAPLING.identifier().getPath(), new AerialHellSaplingBlock(AerialHellTreeGrowers.LAPIS_ROBINIA, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).setId(Keys.LAPIS_ROBINIA_SAPLING), AerialHellConfiguredFeatures.GIANT_LAPIS_ROBINIA));

    //shadow_pine
    public static final ShiftableLogBlock SHADOW_PINE_LOG = register(Keys.SHADOW_PINE_LOG.identifier().getPath(), new ShadowLogBlock(SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_LOG), () -> AerialHellBlocks.HOLLOW_SHADOW_PINE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLogBlock EYE_SHADOW_PINE_LOG = register(Keys.EYE_SHADOW_PINE_LOG.identifier().getPath(), new ShadowEffectLogBlock(SHADOW_PINE_MATERIAL.setId(Keys.EYE_SHADOW_PINE_LOG), () -> AerialHellBlocks.HOLLOW_SHADOW_PINE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final RotatedPillarBlock STRIPPED_SHADOW_PINE_LOG = register(Keys.STRIPPED_SHADOW_PINE_LOG.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG).setId(Keys.STRIPPED_SHADOW_PINE_LOG)));
    public static final RotatedPillarBlock SHADOW_PINE_WOOD = register(Keys.SHADOW_PINE_WOOD.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG).setId(Keys.SHADOW_PINE_WOOD)));
    public static final RotatedPillarBlock STRIPPED_SHADOW_PINE_WOOD = register(Keys.STRIPPED_SHADOW_PINE_WOOD.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG).setId(Keys.STRIPPED_SHADOW_PINE_WOOD)));
    public static final ShiftableLeavesBlock SHADOW_PINE_LEAVES = register(Keys.SHADOW_PINE_LEAVES.identifier().getPath(), new ShadowLeavesWithParticlesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(Keys.SHADOW_PINE_LEAVES), () -> AerialHellBlocks.HOLLOW_SHADOW_PINE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock PURPLE_SHADOW_PINE_LEAVES = register(Keys.PURPLE_SHADOW_PINE_LEAVES.identifier().getPath(), new ShadowLeavesWithParticlesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(Keys.PURPLE_SHADOW_PINE_LEAVES), () -> AerialHellBlocks.HOLLOW_PURPLE_SHADOW_PINE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final Block SHADOW_PINE_PLANKS = register(Keys.SHADOW_PINE_PLANKS.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG).setId(Keys.SHADOW_PINE_PLANKS)));
    public static final Block SHADOW_PINE_BOOKSHELF = register(Keys.SHADOW_PINE_BOOKSHELF.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_PLANKS).setId(Keys.SHADOW_PINE_BOOKSHELF)));
    public static final SaplingBlock SHADOW_PINE_SAPLING = register(Keys.SHADOW_PINE_SAPLING.identifier().getPath(), new ShadowPineSaplingBlock(AerialHellTreeGrowers.SHADOW_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).setId(Keys.SHADOW_PINE_SAPLING), AerialHellConfiguredFeatures.GIANT_SHADOW_PINE, AerialHellConfiguredFeatures.HUGE_SHADOW_PINE, 0.1F));
    public static final SaplingBlock PURPLE_SHADOW_PINE_SAPLING = register(Keys.PURPLE_SHADOW_PINE_SAPLING.identifier().getPath(), new ShadowPineSaplingBlock(AerialHellTreeGrowers.PURPLE_SHADOW_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).setId(Keys.PURPLE_SHADOW_PINE_SAPLING), AerialHellConfiguredFeatures.GIANT_PURPLE_SHADOW_PINE, AerialHellConfiguredFeatures.HUGE_PURPLE_SHADOW_PINE, 0.1F));

    //stellar jungle tree
    public static final ShiftableLogBlock STELLAR_JUNGLE_TREE_LOG = register(Keys.STELLAR_JUNGLE_TREE_LOG.identifier().getPath(), new ShiftableLogBlock(COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_LOG), () -> AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final RotatedPillarBlock STRIPPED_STELLAR_JUNGLE_TREE_LOG = register(Keys.STRIPPED_STELLAR_JUNGLE_TREE_LOG.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG).setId(Keys.STRIPPED_STELLAR_JUNGLE_TREE_LOG)));
    public static final RotatedPillarBlock STELLAR_JUNGLE_TREE_WOOD = register(Keys.STELLAR_JUNGLE_TREE_WOOD.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG).setId(Keys.STELLAR_JUNGLE_TREE_WOOD)));
    public static final RotatedPillarBlock STRIPPED_STELLAR_JUNGLE_TREE_WOOD = register(Keys.STRIPPED_STELLAR_JUNGLE_TREE_WOOD.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG).setId(Keys.STRIPPED_STELLAR_JUNGLE_TREE_WOOD)));
    public static final ShiftableLeavesBlock STELLAR_JUNGLE_TREE_LEAVES = register(Keys.STELLAR_JUNGLE_TREE_LEAVES.identifier().getPath(), new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(Keys.STELLAR_JUNGLE_TREE_LEAVES), () -> AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block STELLAR_JUNGLE_TREE_PLANKS = register(Keys.STELLAR_JUNGLE_TREE_PLANKS.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG).setId(Keys.STELLAR_JUNGLE_TREE_PLANKS)));
    public static final Block STELLAR_JUNGLE_TREE_BOOKSHELF = register(Keys.STELLAR_JUNGLE_TREE_BOOKSHELF.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_PLANKS).setId(Keys.STELLAR_JUNGLE_TREE_BOOKSHELF)));
    public static final SaplingBlock STELLAR_JUNGLE_TREE_SAPLING = register(Keys.STELLAR_JUNGLE_TREE_SAPLING.identifier().getPath(), new AerialHellSaplingBlock(AerialHellTreeGrowers.STELLAR_JUNGLE_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).setId(Keys.STELLAR_JUNGLE_TREE_SAPLING), AerialHellConfiguredFeatures.GIANT_STELLAR_JUNGLE_TREE));
    public static final LargeDeadLogBlock DEAD_STELLAR_JUNGLE_TREE_LOG = register(Keys.DEAD_STELLAR_JUNGLE_TREE_LOG.identifier().getPath(), new LargeDeadLogBlock(STELLAR_JUNGLE_TREE_PLANKS.defaultBlockState(), COPPER_PINE_MATERIAL.setId(Keys.DEAD_STELLAR_JUNGLE_TREE_LOG)));

    //shroom
    public static final RotatedPillarBlock GIANT_CORTINARIUS_VIOLACEUS_STEM = register(Keys.GIANT_CORTINARIUS_VIOLACEUS_STEM.identifier().getPath(), new RotatedPillarBlock(SHROOM_MATERIAL.setId(Keys.GIANT_CORTINARIUS_VIOLACEUS_STEM)));
    public static final RotatedPillarBlock STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM = register(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM).setId(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM)));
    public static final RotatedPillarBlock GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = register(Keys.GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM).setId(Keys.GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM)));
    public static final RotatedPillarBlock STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = register(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM).setId(Keys.STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM)));
    public static final Block GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK = register(Keys.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK.identifier().getPath(), new Block(BlockBehaviour.Properties.of().setId(Keys.GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK).mapColor(MapColor.TERRACOTTA_BLUE).strength(0.5F).sound(SoundType.HARD_CROP)));
    public static final Block GIANT_CORTINARIUS_VIOLACEUS_LIGHT = register(Keys.GIANT_CORTINARIUS_VIOLACEUS_LIGHT.identifier().getPath(), new Block(BlockBehaviour.Properties.of().setId(Keys.GIANT_CORTINARIUS_VIOLACEUS_LIGHT).mapColor(MapColor.COLOR_PURPLE).strength(1.0F).sound(SoundType.SHROOMLIGHT).lightLevel((state) -> {return 15;})));
    public static final FungusBlock CORTINARIUS_VIOLACEUS = register(Keys.CORTINARIUS_VIOLACEUS.identifier().getPath(), new AerialHellFungusBlock(AerialHellConfiguredFeatures.GIANT_CORTINARIUS_VIOLACEUS_PLANTED, STELLAR_GRASS_BLOCK, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FUNGUS).setId(Keys.CORTINARIUS_VIOLACEUS)));
    public static final Block GLOWING_BOLETUS = register(Keys.GLOWING_BOLETUS.identifier().getPath(), new AerialHellTallShroomBlock(BlockBehaviour.Properties.of().setId(Keys.GLOWING_BOLETUS).mapColor(MapColor.COLOR_GREEN).noCollision().lightLevel((state) -> {return 9;}).instabreak().sound(SoundType.GLOW_LICHEN), true));
    public static final Block TALL_GLOWING_BOLETUS = register(Keys.TALL_GLOWING_BOLETUS.identifier().getPath(), new DoubleShroomBlock(BlockBehaviour.Properties.of().setId(Keys.TALL_GLOWING_BOLETUS).mapColor(MapColor.COLOR_GREEN).noCollision().lightLevel((state) -> {return 11;}).instabreak().sound(SoundType.GLOW_LICHEN)));
    public static final Block BLUE_MEANIE_CLUSTER = register(Keys.BLUE_MEANIE_CLUSTER.identifier().getPath(), new TallShroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH).setId(Keys.BLUE_MEANIE_CLUSTER)));
    public static final Block GIANT_ROOT_SHROOM = register(Keys.GIANT_ROOT_SHROOM.identifier().getPath(), new AerialHellTallShroomBlock(BlockBehaviour.Properties.of().setId(Keys.GIANT_ROOT_SHROOM).mapColor(MapColor.COLOR_GREEN).noCollision().instabreak().sound(SoundType.NETHER_WART), false));

    public static final RotatedPillarBlock GIANT_VERDIGRIS_AGARIC_STEM = register(Keys.GIANT_VERDIGRIS_AGARIC_STEM.identifier().getPath(), new RotatedPillarBlock(SHROOM_MATERIAL.setId(Keys.GIANT_VERDIGRIS_AGARIC_STEM)));
    public static final RotatedPillarBlock STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM = register(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM).setId(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM)));
    public static final RotatedPillarBlock GIANT_VERDIGRIS_AGARIC_BARK_STEM = register(Keys.GIANT_VERDIGRIS_AGARIC_BARK_STEM.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM).setId(Keys.GIANT_VERDIGRIS_AGARIC_BARK_STEM)));
    public static final RotatedPillarBlock STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM = register(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM).setId(Keys.STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM)));
    public static final Block GIANT_VERDIGRIS_AGARIC_CAP_BLOCK = register(Keys.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK.identifier().getPath(), new HugeMushroomBlock(BlockBehaviour.Properties.of().setId(Keys.GIANT_VERDIGRIS_AGARIC_CAP_BLOCK).mapColor(MapColor.TERRACOTTA_BLUE).lightLevel((state) -> {return 10;}).strength(0.4F).sound(SoundType.HARD_CROP)));
    public static final MushroomBlock VERDIGRIS_AGARIC = register(Keys.VERDIGRIS_AGARIC.identifier().getPath(), new AerialHellMushroomBlock(AerialHellConfiguredFeatures.GIANT_VERDIGRIS_AGARIC, BlockBehaviour.Properties.of().setId(Keys.VERDIGRIS_AGARIC).mapColor(MapColor.COLOR_GRAY).noCollision().randomTicks().instabreak().sound(SoundType.GRASS)));

    public static final Block GIANT_GANODERMA_APPLANATUM_BLOCK = register(Keys.GIANT_GANODERMA_APPLANATUM_BLOCK.identifier().getPath(), new HugeMushroomBlock(BlockBehaviour.Properties.of().setId(Keys.GIANT_GANODERMA_APPLANATUM_BLOCK).mapColor(MapColor.COLOR_BROWN).strength(0.4F).sound(SoundType.HARD_CROP)));

    public static final Block GRAY_SHROOM_PLANKS = register(Keys.GRAY_SHROOM_PLANKS.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM).setId(Keys.GRAY_SHROOM_PLANKS)));
    public static final Block GRAY_SHROOM_BOOKSHELF = register(Keys.GRAY_SHROOM_BOOKSHELF.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_PLANKS).setId(Keys.GRAY_SHROOM_BOOKSHELF)));

    //shadow corrupted / uncorrupted variants
    public static final ShadowLogBlock SHADOW_AERIAL_TREE_LOG = register(Keys.SHADOW_AERIAL_TREE_LOG.identifier().getPath(), new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG).setId(Keys.SHADOW_AERIAL_TREE_LOG), () -> AERIAL_TREE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShadowLogBlock SHADOW_GOLDEN_BEECH_LOG = register(Keys.SHADOW_GOLDEN_BEECH_LOG.identifier().getPath(), new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG).setId(Keys.SHADOW_GOLDEN_BEECH_LOG), () -> GOLDEN_BEECH_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShadowLogBlock SHADOW_COPPER_PINE_LOG = register(Keys.SHADOW_COPPER_PINE_LOG.identifier().getPath(), new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG).setId(Keys.SHADOW_COPPER_PINE_LOG), () -> COPPER_PINE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShadowLogBlock SHADOW_LAPIS_ROBINIA_LOG = register(Keys.SHADOW_LAPIS_ROBINIA_LOG.identifier().getPath(), new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG).setId(Keys.SHADOW_LAPIS_ROBINIA_LOG), () -> LAPIS_ROBINIA_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShadowLogBlock SHADOW_STELLAR_JUNGLE_TREE_LOG = register(Keys.SHADOW_STELLAR_JUNGLE_TREE_LOG.identifier().getPath(), new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG).setId(Keys.SHADOW_STELLAR_JUNGLE_TREE_LOG), () -> STELLAR_JUNGLE_TREE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLogBlock HOLLOW_SHADOW_PINE_LOG = register(Keys.HOLLOW_SHADOW_PINE_LOG.identifier().getPath(), new ShiftableLogBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG).setId(Keys.HOLLOW_SHADOW_PINE_LOG), () -> SHADOW_PINE_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final ShiftableLeavesBlock SHADOW_AERIAL_TREE_LEAVES = register(Keys.SHADOW_AERIAL_TREE_LEAVES.identifier().getPath(), new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LEAVES).setId(Keys.SHADOW_AERIAL_TREE_LEAVES), () -> AerialHellBlocks.AERIAL_TREE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock SHADOW_GOLDEN_BEECH_LEAVES = register(Keys.SHADOW_GOLDEN_BEECH_LEAVES.identifier().getPath(), new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LEAVES).setId(Keys.SHADOW_GOLDEN_BEECH_LEAVES), () -> AerialHellBlocks.GOLDEN_BEECH_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock SHADOW_COPPER_PINE_LEAVES = register(Keys.SHADOW_COPPER_PINE_LEAVES.identifier().getPath(), new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LEAVES).setId(Keys.SHADOW_COPPER_PINE_LEAVES), () -> AerialHellBlocks.COPPER_PINE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock SHADOW_LAPIS_ROBINIA_LEAVES = register(Keys.SHADOW_LAPIS_ROBINIA_LEAVES.identifier().getPath(), new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LEAVES).setId(Keys.SHADOW_LAPIS_ROBINIA_LEAVES), () -> AerialHellBlocks.LAPIS_ROBINIA_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock SHADOW_STELLAR_JUNGLE_TREE_LEAVES = register(Keys.SHADOW_STELLAR_JUNGLE_TREE_LEAVES.identifier().getPath(), new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LEAVES).setId(Keys.SHADOW_STELLAR_JUNGLE_TREE_LEAVES), () -> AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock HOLLOW_SHADOW_PINE_LEAVES = register(Keys.HOLLOW_SHADOW_PINE_LEAVES.identifier().getPath(), new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LEAVES).setId(Keys.HOLLOW_SHADOW_PINE_LEAVES), () -> AerialHellBlocks.SHADOW_PINE_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final ShiftableLeavesBlock HOLLOW_PURPLE_SHADOW_PINE_LEAVES = register(Keys.HOLLOW_PURPLE_SHADOW_PINE_LEAVES.identifier().getPath(), new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(PURPLE_SHADOW_PINE_LEAVES).setId(Keys.HOLLOW_PURPLE_SHADOW_PINE_LEAVES), () -> AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES, BiomeShifter.ShiftType.CORRUPT));

    //ladder
    public static final LadderBlock SKY_LADDER = register(Keys.SKY_LADDER.identifier().getPath(), new LadderBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS).setId(Keys.SKY_LADDER).noOcclusion()));

    //natural blocks and items
    public static final Block STELLAR_STONE = register(Keys.STELLAR_STONE.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(Keys.STELLAR_STONE)));
    public static final Block STELLAR_COBBLESTONE = register(Keys.STELLAR_COBBLESTONE.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE).setId(Keys.STELLAR_COBBLESTONE)));
    public static final Block STELLAR_STONE_BRICKS = register(Keys.STELLAR_STONE_BRICKS.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).setId(Keys.STELLAR_STONE_BRICKS).strength(0.5F, 10.0F)));
    public static final Block MOSSY_STELLAR_STONE = register(Keys.MOSSY_STELLAR_STONE.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE).setId(Keys.MOSSY_STELLAR_STONE)));
    public static final Block MOSSY_STELLAR_COBBLESTONE = register(Keys.MOSSY_STELLAR_COBBLESTONE.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE).setId(Keys.MOSSY_STELLAR_COBBLESTONE)));
    public static final Block STELLAR_CLAY = register(Keys.STELLAR_CLAY.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CLAY).setId(Keys.STELLAR_CLAY)));
    public static final Block GLAUCOPHANITE = register(Keys.GLAUCOPHANITE.identifier().getPath(), new Block(BlockBehaviour.Properties.of().setId(Keys.GLAUCOPHANITE).strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block POLISHED_GLAUCOPHANITE = register(Keys.POLISHED_GLAUCOPHANITE.identifier().getPath(), new Block(BlockBehaviour.Properties.of().setId(Keys.POLISHED_GLAUCOPHANITE).strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block SHADOW_STONE = register(Keys.SHADOW_STONE.identifier().getPath(), new ShadowStoneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).setId(Keys.SHADOW_STONE)));

    //crystal
    public static final Block CRYSTAL_BLOCK = register(Keys.CRYSTAL_BLOCK.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).setId(Keys.CRYSTAL_BLOCK).lightLevel((state) -> 14)));
    public static final Block CRYSTAL_BRICKS = register(Keys.CRYSTAL_BRICKS.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).setId(Keys.CRYSTAL_BRICKS).lightLevel((state) -> 9)));
    public static final Block CRYSTAL_BRICKS_SLAB = register(Keys.CRYSTAL_BRICKS_SLAB.identifier().getPath(), new SlabBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BRICKS).setId(Keys.CRYSTAL_BRICKS_SLAB).noOcclusion()));
    public static final Block CRYSTAL_BRICKS_STAIRS = register(Keys.CRYSTAL_BRICKS_STAIRS.identifier().getPath(), new StairBlock(CRYSTAL_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BRICKS).setId(Keys.CRYSTAL_BRICKS_STAIRS)));
    public static final Block CRYSTAL_BRICKS_WALL = register(Keys.CRYSTAL_BRICKS_WALL.identifier().getPath(), new WallBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BRICKS).setId(Keys.CRYSTAL_BRICKS_WALL)));
    public static final Block STELLAR_STONE_CRYSTAL_BLOCK = register(Keys.STELLAR_STONE_CRYSTAL_BLOCK.identifier().getPath(), new BasicShiftableRenderBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BLOCK).setId(Keys.STELLAR_STONE_CRYSTAL_BLOCK).lightLevel((state) -> 13)));
    public static final Block SHADOW_CRYSTAL_BLOCK = register(Keys.SHADOW_CRYSTAL_BLOCK.identifier().getPath(), new BasicShadowSpreaderBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BLOCK).setId(Keys.SHADOW_CRYSTAL_BLOCK).lightLevel((state) -> 12)));
    public static final Block CRYSTALLIZED_LEAVES = register(Keys.CRYSTALLIZED_LEAVES.identifier().getPath(), new UntintedParticleLeavesBlock(0.1F, AerialHellParticleTypes.FALLING_CRYSTALLIZED_LEAVES, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).setId(Keys.CRYSTALLIZED_LEAVES).lightLevel((state) -> 12)));
    public static final Block CRYSTALLIZED_FIRE = register(Keys.CRYSTALLIZED_FIRE.identifier().getPath(), new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).setId(Keys.CRYSTALLIZED_FIRE).lightLevel((state) -> 12).instabreak()));

    //glass and glass pane
    public static final Block SLIPPERY_SAND_GLASS = register(Keys.SLIPPERY_SAND_GLASS.identifier().getPath(), new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).setId(Keys.SLIPPERY_SAND_GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final Block RED_SLIPPERY_SAND_GLASS = register(Keys.RED_SLIPPERY_SAND_GLASS.identifier().getPath(), new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).setId(Keys.RED_SLIPPERY_SAND_GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final Block BLACK_SLIPPERY_SAND_GLASS = register(Keys.BLACK_SLIPPERY_SAND_GLASS.identifier().getPath(), new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).setId(Keys.BLACK_SLIPPERY_SAND_GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final Block BLUE_SLIPPERY_SAND_GLASS = register(Keys.BLUE_SLIPPERY_SAND_GLASS.identifier().getPath(), new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).setId(Keys.BLUE_SLIPPERY_SAND_GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final Block GREEN_SLIPPERY_SAND_GLASS = register(Keys.GREEN_SLIPPERY_SAND_GLASS.identifier().getPath(), new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).setId(Keys.GREEN_SLIPPERY_SAND_GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final Block SLIPPERY_SAND_GLASS_PANE = register(Keys.SLIPPERY_SAND_GLASS_PANE.identifier().getPath(), new StainedGlassPaneBlock(DyeColor.YELLOW, BlockBehaviour.Properties.of().setId(Keys.SLIPPERY_SAND_GLASS_PANE).friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
    public static final Block RED_SLIPPERY_SAND_GLASS_PANE = register(Keys.RED_SLIPPERY_SAND_GLASS_PANE.identifier().getPath(), new StainedGlassPaneBlock(DyeColor.RED, BlockBehaviour.Properties.of().setId(Keys.RED_SLIPPERY_SAND_GLASS_PANE).friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
    public static final Block BLACK_SLIPPERY_SAND_GLASS_PANE = register(Keys.BLACK_SLIPPERY_SAND_GLASS_PANE.identifier().getPath(), new StainedGlassPaneBlock(DyeColor.BLACK, BlockBehaviour.Properties.of().setId(Keys.BLACK_SLIPPERY_SAND_GLASS_PANE).friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
    public static final Block BLUE_SLIPPERY_SAND_GLASS_PANE = register(Keys.BLUE_SLIPPERY_SAND_GLASS_PANE.identifier().getPath(), new StainedGlassPaneBlock(DyeColor.BLUE, BlockBehaviour.Properties.of().setId(Keys.BLUE_SLIPPERY_SAND_GLASS_PANE).friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
    public static final Block GREEN_SLIPPERY_SAND_GLASS_PANE = register(Keys.GREEN_SLIPPERY_SAND_GLASS_PANE.identifier().getPath(), new StainedGlassPaneBlock(DyeColor.GREEN, BlockBehaviour.Properties.of().setId(Keys.GREEN_SLIPPERY_SAND_GLASS_PANE).friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));

    //ghost boat
    public static final Block GHOST_BOAT_PLANKS = register(Keys.GHOST_BOAT_PLANKS.identifier().getPath(), new GhostBoatBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_PLANKS).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final GhostBoatRotatedPillarBlock GHOST_BOAT_LOG = register(Keys.GHOST_BOAT_LOG.identifier().getPath(), new GhostBoatRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG).setId(Keys.GHOST_BOAT_LOG).noOcclusion()));
    public static final GhostBoatRotatedPillarBlock GHOST_BOAT_WOOD = register(Keys.GHOST_BOAT_WOOD.identifier().getPath(), new GhostBoatRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GHOST_BOAT_LOG).setId(Keys.GHOST_BOAT_WOOD).noOcclusion()));
    public static final SlabBlock GHOST_BOAT_SLAB = register(Keys.GHOST_BOAT_SLAB.identifier().getPath(), new GhostBoatSlabBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_SLAB).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final StairBlock GHOST_BOAT_STAIRS = register(Keys.GHOST_BOAT_STAIRS.identifier().getPath(), new GhostBoatStairBlock(GHOST_BOAT_PLANKS.defaultBlockState(), BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_STAIRS).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final FenceBlock GHOST_BOAT_FENCE = register(Keys.GHOST_BOAT_FENCE.identifier().getPath(), new GhostBoatFenceBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_FENCE).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final FenceGateBlock GHOST_BOAT_GATE = register(Keys.GHOST_BOAT_GATE.identifier().getPath(), new GhostBoatFenceGateBlock(AerialHellWoodTypes.AERIAL_TREE, BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_GATE).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final DoorBlock GHOST_BOAT_DOOR = register(Keys.GHOST_BOAT_DOOR.identifier().getPath(), new GhostBoatDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_DOOR).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final TrapDoorBlock GHOST_BOAT_TRAPDOOR = register(Keys.GHOST_BOAT_TRAPDOOR.identifier().getPath(), new GhostBoatTrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_TRAPDOOR).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final ChestBlock GHOST_BOAT_CHEST = register(Keys.GHOST_BOAT_CHEST.identifier().getPath(), new GhostBoatChestBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_CHEST).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final Block GHOST_BOAT_WOOL = register(Keys.GHOST_BOAT_WOOL.identifier().getPath(), new GhostBoatBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_WOOL).mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL).noOcclusion()));
    public static final Block GHOST_STELLAR_COBBLESTONE = register(Keys.GHOST_STELLAR_COBBLESTONE.identifier().getPath(), new GhostBoatBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_STELLAR_COBBLESTONE).strength(2.5F, 2.5F).requiresCorrectToolForDrops().sound(SoundType.STONE).noOcclusion()));
    public static final Block GHOST_RUBY_BLOCK = register(Keys.GHOST_RUBY_BLOCK.identifier().getPath(), new GhostBoatBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_RUBY_BLOCK).strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));
    public static final Block GHOST_FLUORITE_BLOCK = register(Keys.GHOST_FLUORITE_BLOCK.identifier().getPath(), new GhostBoatBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_FLUORITE_BLOCK).strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));
    public static final Block GHOST_AZURITE_BLOCK = register(Keys.GHOST_AZURITE_BLOCK.identifier().getPath(), new GhostBoatBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).setId(Keys.GHOST_AZURITE_BLOCK).noOcclusion()));
    public static final Block GHOST_GOLD_BLOCK = register(Keys.GHOST_GOLD_BLOCK.identifier().getPath(), new GhostBoatBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).setId(Keys.GHOST_GOLD_BLOCK).noOcclusion()));
    public static final AerialHellBarrelBlock GHOST_BOAT_BARREL = register(Keys.GHOST_BOAT_BARREL.identifier().getPath(), new GhostBoatBarrelBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_BARREL).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final CraftingTableBlock GHOST_BOAT_CRAFTING_TABLE = register(Keys.GHOST_BOAT_CRAFTING_TABLE.identifier().getPath(), new GhostBoatCraftingTableBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_CRAFTING_TABLE).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final RotatedPillarBlock GHOST_BOAT_VINE_ROPE_SPOOL = register(Keys.GHOST_BOAT_VINE_ROPE_SPOOL.identifier().getPath(), new GhostBoatVineRopeSpoolBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_BOAT_VINE_ROPE_SPOOL).noOcclusion().mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
    public static final Block GHOST_LANTERN = register(Keys.GHOST_LANTERN.identifier().getPath(), new GhostLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN).setId(Keys.GHOST_LANTERN)));

    //other condition condition blocks
    public static final Block INTANGIBLE_TEMPORARY_BLOCK = register(Keys.INTANGIBLE_TEMPORARY_BLOCK.identifier().getPath(), new IntangibleTemporaryBlock(BlockBehaviour.Properties.of().setId(Keys.INTANGIBLE_TEMPORARY_BLOCK).strength(2.0F, 3600000.0F).noLootTable().pushReaction(PushReaction.IGNORE).sound(SoundType.GLASS).lightLevel((state) -> 7).noOcclusion()));

    //reactors
    public static final Block WEAK_LIGHT_REACTOR = register(Keys.WEAK_LIGHT_REACTOR.identifier().getPath(), new ReactorBlock(BlockBehaviour.Properties.of().setId(Keys.WEAK_LIGHT_REACTOR).strength(5.0F, 100.0F).pushReaction(PushReaction.IGNORE).sound(SoundType.STONE).noOcclusion(), 32, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.BROKEN_WEAK_LIGHT_REACTOR));
    public static final Block HIGH_POWER_LIGHT_REACTOR = register(Keys.HIGH_POWER_LIGHT_REACTOR.identifier().getPath(), new ReactorBlock(BlockBehaviour.Properties.of().setId(Keys.HIGH_POWER_LIGHT_REACTOR).strength(5.0F, 100.0F).pushReaction(PushReaction.IGNORE).sound(SoundType.STONE).noOcclusion(), 58, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.BROKEN_HIGH_POWER_LIGHT_REACTOR));
    public static final Block WEAK_SHADOW_REACTOR = register(Keys.WEAK_SHADOW_REACTOR.identifier().getPath(), new ReactorBlock(BlockBehaviour.Properties.of().setId(Keys.WEAK_SHADOW_REACTOR).strength(5.0F, 100.0F).pushReaction(PushReaction.IGNORE).sound(SoundType.STONE).noOcclusion(), 26, BiomeShifter.ShiftType.CORRUPT, () -> AerialHellBlocks.BROKEN_WEAK_SHADOW_REACTOR));
    public static final Block HIGH_POWER_SHADOW_REACTOR = register(Keys.HIGH_POWER_SHADOW_REACTOR.identifier().getPath(), new ReactorBlock(BlockBehaviour.Properties.of().setId(Keys.HIGH_POWER_SHADOW_REACTOR).strength(5.0F, 100.0F).pushReaction(PushReaction.IGNORE).sound(SoundType.STONE).noOcclusion(), 60, BiomeShifter.ShiftType.CORRUPT, () -> AerialHellBlocks.BROKEN_HIGH_POWER_SHADOW_REACTOR));

    public static final Block BROKEN_WEAK_LIGHT_REACTOR = register(Keys.BROKEN_WEAK_LIGHT_REACTOR.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(WEAK_LIGHT_REACTOR).setId(Keys.BROKEN_WEAK_LIGHT_REACTOR)));
    public static final Block BROKEN_HIGH_POWER_LIGHT_REACTOR = register(Keys.BROKEN_HIGH_POWER_LIGHT_REACTOR.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(HIGH_POWER_LIGHT_REACTOR).setId(Keys.BROKEN_HIGH_POWER_LIGHT_REACTOR)));
    public static final Block BROKEN_WEAK_SHADOW_REACTOR = register(Keys.BROKEN_WEAK_SHADOW_REACTOR.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(WEAK_SHADOW_REACTOR).setId(Keys.BROKEN_WEAK_SHADOW_REACTOR)));
    public static final Block BROKEN_HIGH_POWER_SHADOW_REACTOR = register(Keys.BROKEN_HIGH_POWER_SHADOW_REACTOR.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(HIGH_POWER_SHADOW_REACTOR).setId(Keys.BROKEN_HIGH_POWER_SHADOW_REACTOR)));

    //solid_ethers
    public static final Block WHITE_SOLID_ETHER = register(Keys.WHITE_SOLID_ETHER.identifier().getPath(), new SolidEtherBlock(BlockBehaviour.Properties.of().setId(Keys.WHITE_SOLID_ETHER).strength(0.2F).sound(SoundType.WOOL).noOcclusion()));
    public static final Block BLUE_SOLID_ETHER = register(Keys.BLUE_SOLID_ETHER.identifier().getPath(), new BlueSolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER).setId(Keys.BLUE_SOLID_ETHER)));
    public static final Block GOLDEN_SOLID_ETHER = register(Keys.GOLDEN_SOLID_ETHER.identifier().getPath(), new SolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER).setId(Keys.GOLDEN_SOLID_ETHER)));
    public static final Block GREEN_SOLID_ETHER = register(Keys.GREEN_SOLID_ETHER.identifier().getPath(), new GreenSolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER).setId(Keys.GREEN_SOLID_ETHER)));
    public static final Block PURPLE_SOLID_ETHER = register(Keys.PURPLE_SOLID_ETHER.identifier().getPath(), new PurpleSolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER).setId(Keys.PURPLE_SOLID_ETHER)));

    //dungeons blocks
    public static final Block MUD_BRICKS = register(Keys.MUD_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.MUD_BRICKS).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block CRACKED_MUD_BRICKS = register(Keys.CRACKED_MUD_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS).setId(Keys.CRACKED_MUD_BRICKS)));
    public static final Block MOSSY_MUD_BRICKS = register(Keys.MOSSY_MUD_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS).setId(Keys.MOSSY_MUD_BRICKS)));
    public static final Block CHISELED_MUD_BRICKS = register(Keys.CHISELED_MUD_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS).setId(Keys.CHISELED_MUD_BRICKS)));
    public static final Block LIGHT_MUD_BRICKS = register(Keys.LIGHT_MUD_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS).setId(Keys.LIGHT_MUD_BRICKS).lightLevel((state) -> 11)));
    public static final Block CRACKED_LIGHT_MUD_BRICKS = register(Keys.CRACKED_LIGHT_MUD_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS).setId(Keys.CRACKED_LIGHT_MUD_BRICKS)));
    public static final Block LUNATIC_STONE = register(Keys.LUNATIC_STONE.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.LUNATIC_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block DARK_LUNATIC_STONE = register(Keys.DARK_LUNATIC_STONE.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.DARK_LUNATIC_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block ROOF_LUNATIC_STONE = register(Keys.ROOF_LUNATIC_STONE.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.ROOF_LUNATIC_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block CRACKED_LUNATIC_STONE = register(Keys.CRACKED_LUNATIC_STONE.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.CRACKED_LUNATIC_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block CHISELED_LUNATIC_STONE = register(Keys.CHISELED_LUNATIC_STONE.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.CHISELED_LUNATIC_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block LIGHT_LUNATIC_STONE = register(Keys.LIGHT_LUNATIC_STONE.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE).setId(Keys.LIGHT_LUNATIC_STONE).lightLevel((state) -> 11)));
    public static final Block ROOF_LIGHT_LUNATIC_STONE = register(Keys.ROOF_LIGHT_LUNATIC_STONE.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE).setId(Keys.ROOF_LIGHT_LUNATIC_STONE).lightLevel((state) -> 11)));
    public static final Block CRACKED_LIGHT_LUNATIC_STONE = register(Keys.CRACKED_LIGHT_LUNATIC_STONE.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE).setId(Keys.CRACKED_LIGHT_LUNATIC_STONE)));
    public static final Block SHADOW_CATACOMBS_BRICKS = register(Keys.SHADOW_CATACOMBS_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.SHADOW_CATACOMBS_BRICKS).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block CRACKED_SHADOW_CATACOMBS_BRICKS = register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS).setId(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS)));
    public static final Block MOSSY_SHADOW_CATACOMBS_BRICKS = register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS).setId(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS)));
    public static final Block CHISELED_SHADOW_CATACOMBS_BRICKS = register(Keys.CHISELED_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS).setId(Keys.CHISELED_SHADOW_CATACOMBS_BRICKS)));
    public static final Block BONE_SHADOW_CATACOMBS_BRICKS = register(Keys.BONE_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS).setId(Keys.BONE_SHADOW_CATACOMBS_BRICKS).sound(SoundType.BONE_BLOCK)));
    public static final Block SKULL_SHADOW_CATACOMBS_BRICKS = register(Keys.SKULL_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS).setId(Keys.SKULL_SHADOW_CATACOMBS_BRICKS).sound(SoundType.BONE_BLOCK)));
    public static final Block LIGHT_SHADOW_CATACOMBS_BRICKS = register(Keys.LIGHT_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS).setId(Keys.LIGHT_SHADOW_CATACOMBS_BRICKS).lightLevel((state) -> 11)));
    public static final Block CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS = register(Keys.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS).setId(Keys.CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS)));
    public static final Block GOLDEN_NETHER_BRICKS  = register(Keys.GOLDEN_NETHER_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.GOLDEN_NETHER_BRICKS).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block CRACKED_GOLDEN_NETHER_BRICKS  = register(Keys.CRACKED_GOLDEN_NETHER_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.CRACKED_GOLDEN_NETHER_BRICKS).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block CHISELED_GOLDEN_NETHER_BRICKS  = register(Keys.CHISELED_GOLDEN_NETHER_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.CHISELED_GOLDEN_NETHER_BRICKS).strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block LIGHT_GOLDEN_NETHER_BRICKS = register(Keys.LIGHT_GOLDEN_NETHER_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS).setId(Keys.LIGHT_GOLDEN_NETHER_BRICKS).lightLevel((state) -> 11)));
    public static final Block CRACKED_LIGHT_GOLDEN_NETHER_BRICKS = register(Keys.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS).setId(Keys.CRACKED_LIGHT_GOLDEN_NETHER_BRICKS)));
    public static final RotatedPillarBlock LUNATIC_PILLAR = register(Keys.LUNATIC_PILLAR.identifier().getPath(), new CoreProtectedRotatedPillarBlock(BlockBehaviour.Properties.of().setId(Keys.LUNATIC_PILLAR).mapColor(MapColor.SNOW).strength(2.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RotatedPillarBlock LUNATIC_PILLAR_TOP = register(Keys.LUNATIC_PILLAR_TOP.identifier().getPath(), new CoreProtectedRotatedPillarBlock(BlockBehaviour.Properties.of().setId(Keys.LUNATIC_PILLAR_TOP).mapColor(MapColor.SNOW).strength(2.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final Block VOLUCITE_STONE = register(Keys.VOLUCITE_STONE.identifier().getPath(), new VoluciteStoneBlock(BlockBehaviour.Properties.of().setId(Keys.VOLUCITE_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE).lightLevel(getLightValueLit(AerialHellBooleanProperties.SELF_LUMINESCENT, 8))));
    public static final Block CRACKED_VOLUCITE_STONE = register(Keys.CRACKED_VOLUCITE_STONE.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.CRACKED_VOLUCITE_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE)));
    public static final Block CHISELED_VOLUCITE_STONE = register(Keys.CHISELED_VOLUCITE_STONE.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.CHISELED_VOLUCITE_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE)));
    public static final Block LIGHT_VOLUCITE_STONE = register(Keys.LIGHT_VOLUCITE_STONE.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.LIGHT_VOLUCITE_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE).lightLevel((state) -> 13)));
    public static final Block CRACKED_LIGHT_VOLUCITE_STONE = register(Keys.CRACKED_LIGHT_VOLUCITE_STONE.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.of().setId(Keys.CRACKED_LIGHT_VOLUCITE_STONE).strength(2.0F, 6.0F).sound(SoundType.STONE)));

    //dungeon cores
    public static final Block MUD_DUNGEON_CORE = register(Keys.MUD_DUNGEON_CORE.identifier().getPath(), new DungeonCoreBlock(BlockBehaviour.Properties.of().setId(Keys.MUD_DUNGEON_CORE).strength(30.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 181));
    public static final Block LUNATIC_DUNGEON_CORE = register(Keys.LUNATIC_DUNGEON_CORE.identifier().getPath(), new DungeonCoreBlock(BlockBehaviour.Properties.of().setId(Keys.LUNATIC_DUNGEON_CORE).strength(40.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 181));
    public static final Block SHADOW_CATACOMBS_DUNGEON_CORE = register(Keys.SHADOW_CATACOMBS_DUNGEON_CORE.identifier().getPath(), new DungeonCoreBlock(BlockBehaviour.Properties.of().setId(Keys.SHADOW_CATACOMBS_DUNGEON_CORE).strength(30.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 181));
    public static final Block GOLDEN_NETHER_DUNGEON_CORE = register(Keys.GOLDEN_NETHER_DUNGEON_CORE.identifier().getPath(), new DungeonCoreBlock(BlockBehaviour.Properties.of().setId(Keys.GOLDEN_NETHER_DUNGEON_CORE).strength(50.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 101));
    public static final Block VOLUCITE_DUNGEON_CORE = register(Keys.VOLUCITE_DUNGEON_CORE.identifier().getPath(), new DungeonCoreBlock(BlockBehaviour.Properties.of().setId(Keys.VOLUCITE_DUNGEON_CORE).strength(50.0F, 1200.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 101));

    //dungeons slabs, stairs & walls
    public static final SlabBlock MUD_BRICKS_SLAB = register(Keys.MUD_BRICKS_SLAB.identifier().getPath(), new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS).setId(Keys.MUD_BRICKS_SLAB)));
    public static final StairBlock MUD_BRICKS_STAIRS = register(Keys.MUD_BRICKS_STAIRS.identifier().getPath(), new CoreProtectedStairsBlock(MUD_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS).setId(Keys.MUD_BRICKS_STAIRS)));
    public static final WallBlock MUD_BRICKS_WALL = register(Keys.MUD_BRICKS_WALL.identifier().getPath(), new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS).setId(Keys.MUD_BRICKS_WALL)));
    public static final SlabBlock CRACKED_MUD_BRICKS_SLAB = register(Keys.CRACKED_MUD_BRICKS_SLAB.identifier().getPath(), new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_MUD_BRICKS).setId(Keys.CRACKED_MUD_BRICKS_SLAB)));
    public static final StairBlock CRACKED_MUD_BRICKS_STAIRS = register(Keys.CRACKED_MUD_BRICKS_STAIRS.identifier().getPath(), new CoreProtectedStairsBlock(CRACKED_MUD_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS).setId(Keys.CRACKED_MUD_BRICKS_STAIRS)));
    public static final WallBlock CRACKED_MUD_BRICKS_WALL = register(Keys.CRACKED_MUD_BRICKS_WALL.identifier().getPath(), new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_MUD_BRICKS).setId(Keys.CRACKED_MUD_BRICKS_WALL)));
    public static final SlabBlock MOSSY_MUD_BRICKS_SLAB = register(Keys.MOSSY_MUD_BRICKS_SLAB.identifier().getPath(), new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS).setId(Keys.MOSSY_MUD_BRICKS_SLAB)));
    public static final StairBlock MOSSY_MUD_BRICKS_STAIRS = register(Keys.MOSSY_MUD_BRICKS_STAIRS.identifier().getPath(), new CoreProtectedStairsBlock(MOSSY_MUD_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS).setId(Keys.MOSSY_MUD_BRICKS_STAIRS)));
    public static final WallBlock MOSSY_MUD_BRICKS_WALL = register(Keys.MOSSY_MUD_BRICKS_WALL.identifier().getPath(), new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS).setId(Keys.MOSSY_MUD_BRICKS_WALL)));
    public static final SlabBlock VOLUCITE_STONE_SLAB = register(Keys.VOLUCITE_STONE_SLAB.identifier().getPath(), new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE).setId(Keys.VOLUCITE_STONE_SLAB)));
    public static final StairBlock VOLUCITE_STONE_STAIRS = register(Keys.VOLUCITE_STONE_STAIRS.identifier().getPath(), new CoreProtectedStairsBlock(VOLUCITE_STONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE).setId(Keys.VOLUCITE_STONE_STAIRS)));
    public static final WallBlock VOLUCITE_STONE_WALL = register(Keys.VOLUCITE_STONE_WALL.identifier().getPath(), new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE).setId(Keys.VOLUCITE_STONE_WALL)));
    public static final SlabBlock CRACKED_VOLUCITE_STONE_SLAB = register(Keys.CRACKED_VOLUCITE_STONE_SLAB.identifier().getPath(), new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE).setId(Keys.CRACKED_VOLUCITE_STONE_SLAB)));
    public static final StairBlock CRACKED_VOLUCITE_STONE_STAIRS = register(Keys.CRACKED_VOLUCITE_STONE_STAIRS.identifier().getPath(), new CoreProtectedStairsBlock(CRACKED_VOLUCITE_STONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE).setId(Keys.CRACKED_VOLUCITE_STONE_STAIRS)));
    public static final WallBlock CRACKED_VOLUCITE_STONE_WALL = register(Keys.CRACKED_VOLUCITE_STONE_WALL.identifier().getPath(), new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE).setId(Keys.CRACKED_VOLUCITE_STONE_WALL)));
    public static final SlabBlock LUNATIC_STONE_SLAB = register(Keys.LUNATIC_STONE_SLAB.identifier().getPath(), new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE).setId(Keys.LUNATIC_STONE_SLAB)));
    public static final StairBlock LUNATIC_STONE_STAIRS = register(Keys.LUNATIC_STONE_STAIRS.identifier().getPath(), new CoreProtectedStairsBlock(LUNATIC_STONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE).setId(Keys.LUNATIC_STONE_STAIRS)));
    public static final WallBlock LUNATIC_STONE_WALL = register(Keys.LUNATIC_STONE_WALL.identifier().getPath(), new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE).setId(Keys.LUNATIC_STONE_WALL)));
    public static final SlabBlock DARK_LUNATIC_STONE_SLAB = register(Keys.DARK_LUNATIC_STONE_SLAB.identifier().getPath(), new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(DARK_LUNATIC_STONE).setId(Keys.DARK_LUNATIC_STONE_SLAB)));
    public static final StairBlock DARK_LUNATIC_STONE_STAIRS = register(Keys.DARK_LUNATIC_STONE_STAIRS.identifier().getPath(), new CoreProtectedStairsBlock(DARK_LUNATIC_STONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(DARK_LUNATIC_STONE).setId(Keys.DARK_LUNATIC_STONE_STAIRS)));
    public static final WallBlock DARK_LUNATIC_STONE_WALL = register(Keys.DARK_LUNATIC_STONE_WALL.identifier().getPath(), new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(DARK_LUNATIC_STONE).setId(Keys.DARK_LUNATIC_STONE_WALL)));
    public static final SlabBlock CRACKED_LUNATIC_STONE_SLAB = register(Keys.CRACKED_LUNATIC_STONE_SLAB.identifier().getPath(), new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_LUNATIC_STONE).setId(Keys.CRACKED_LUNATIC_STONE_SLAB)));
    public static final StairBlock CRACKED_LUNATIC_STONE_STAIRS = register(Keys.CRACKED_LUNATIC_STONE_STAIRS.identifier().getPath(), new CoreProtectedStairsBlock(CRACKED_LUNATIC_STONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CRACKED_LUNATIC_STONE).setId(Keys.CRACKED_LUNATIC_STONE_STAIRS)));
    public static final WallBlock CRACKED_LUNATIC_STONE_WALL = register(Keys.CRACKED_LUNATIC_STONE_WALL.identifier().getPath(), new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_LUNATIC_STONE).setId(Keys.CRACKED_LUNATIC_STONE_WALL)));
    public static final SlabBlock SHADOW_CATACOMBS_BRICKS_SLAB = register(Keys.SHADOW_CATACOMBS_BRICKS_SLAB.identifier().getPath(), new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS).setId(Keys.SHADOW_CATACOMBS_BRICKS_SLAB)));
    public static final StairBlock SHADOW_CATACOMBS_BRICKS_STAIRS = register(Keys.SHADOW_CATACOMBS_BRICKS_STAIRS.identifier().getPath(), new CoreProtectedStairsBlock(SHADOW_CATACOMBS_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS).setId(Keys.SHADOW_CATACOMBS_BRICKS_STAIRS)));
    public static final WallBlock SHADOW_CATACOMBS_BRICKS_WALL = register(Keys.SHADOW_CATACOMBS_BRICKS_WALL.identifier().getPath(), new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS).setId(Keys.SHADOW_CATACOMBS_BRICKS_WALL)));
    public static final SlabBlock CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB = register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB.identifier().getPath(), new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_SHADOW_CATACOMBS_BRICKS).setId(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB)));
    public static final StairBlock CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS = register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS.identifier().getPath(), new CoreProtectedStairsBlock(CRACKED_SHADOW_CATACOMBS_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CRACKED_SHADOW_CATACOMBS_BRICKS).setId(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS)));
    public static final WallBlock CRACKED_SHADOW_CATACOMBS_BRICKS_WALL = register(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL.identifier().getPath(), new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_SHADOW_CATACOMBS_BRICKS).setId(Keys.CRACKED_SHADOW_CATACOMBS_BRICKS_WALL)));
    public static final SlabBlock MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB = register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB.identifier().getPath(), new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_SHADOW_CATACOMBS_BRICKS).setId(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB)));
    public static final StairBlock MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS = register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS.identifier().getPath(), new CoreProtectedStairsBlock(MOSSY_SHADOW_CATACOMBS_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_SHADOW_CATACOMBS_BRICKS).setId(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS)));
    public static final WallBlock MOSSY_SHADOW_CATACOMBS_BRICKS_WALL = register(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL.identifier().getPath(), new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_SHADOW_CATACOMBS_BRICKS).setId(Keys.MOSSY_SHADOW_CATACOMBS_BRICKS_WALL)));
    public static final IronBarsBlock SHADOW_BARS = register(Keys.SHADOW_BARS.identifier().getPath(), new ShadowBarsBlock(METAL_NOTSOLID_MATERIAL.setId(Keys.SHADOW_BARS)));
    public static final SlabBlock GOLDEN_NETHER_BRICKS_SLAB = register(Keys.GOLDEN_NETHER_BRICKS_SLAB.identifier().getPath(), new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS).setId(Keys.GOLDEN_NETHER_BRICKS_SLAB)));
    public static final StairBlock GOLDEN_NETHER_BRICKS_STAIRS = register(Keys.GOLDEN_NETHER_BRICKS_STAIRS.identifier().getPath(), new CoreProtectedStairsBlock(GOLDEN_NETHER_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS).setId(Keys.GOLDEN_NETHER_BRICKS_STAIRS)));
    public static final WallBlock GOLDEN_NETHER_BRICKS_WALL = register(Keys.GOLDEN_NETHER_BRICKS_WALL.identifier().getPath(), new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS).setId(Keys.GOLDEN_NETHER_BRICKS_WALL)));
    public static final SlabBlock CRACKED_GOLDEN_NETHER_BRICKS_SLAB = register(Keys.CRACKED_GOLDEN_NETHER_BRICKS_SLAB.identifier().getPath(), new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_GOLDEN_NETHER_BRICKS).setId(Keys.CRACKED_GOLDEN_NETHER_BRICKS_SLAB)));
    public static final StairBlock CRACKED_GOLDEN_NETHER_BRICKS_STAIRS = register(Keys.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS.identifier().getPath(), new CoreProtectedStairsBlock(CRACKED_GOLDEN_NETHER_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS).setId(Keys.CRACKED_GOLDEN_NETHER_BRICKS_STAIRS)));
    public static final WallBlock CRACKED_GOLDEN_NETHER_BRICKS_WALL = register(Keys.CRACKED_GOLDEN_NETHER_BRICKS_WALL.identifier().getPath(), new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_GOLDEN_NETHER_BRICKS).setId(Keys.CRACKED_GOLDEN_NETHER_BRICKS_WALL)));

    //smoky quartz
    public static final Block SMOKY_QUARTZ_BLOCK = register(Keys.SMOKY_QUARTZ_BLOCK.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).setId(Keys.SMOKY_QUARTZ_BLOCK)));
    public static final Block SMOOTH_SMOKY_QUARTZ = register(Keys.SMOOTH_SMOKY_QUARTZ.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK).setId(Keys.SMOOTH_SMOKY_QUARTZ)));
    public static final Block CHISELED_SMOKY_QUARTZ_BLOCK = register(Keys.CHISELED_SMOKY_QUARTZ_BLOCK.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK).setId(Keys.CHISELED_SMOKY_QUARTZ_BLOCK)));
    public static final Block SMOKY_QUARTZ_BRICKS = register(Keys.SMOKY_QUARTZ_BRICKS.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK).setId(Keys.SMOKY_QUARTZ_BRICKS)));
    public static final RotatedPillarBlock SMOKY_QUARTZ_PILLAR = register(Keys.SMOKY_QUARTZ_PILLAR.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK).setId(Keys.SMOKY_QUARTZ_PILLAR)));
    public static final SlabBlock SMOKY_QUARTZ_SLAB = register(Keys.SMOKY_QUARTZ_SLAB.identifier().getPath(), new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK).setId(Keys.SMOKY_QUARTZ_SLAB)));
    public static final SlabBlock SMOOTH_SMOKY_QUARTZ_SLAB = register(Keys.SMOOTH_SMOKY_QUARTZ_SLAB.identifier().getPath(), new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK).setId(Keys.SMOOTH_SMOKY_QUARTZ_SLAB)));
    public static final StairBlock SMOKY_QUARTZ_STAIRS = register(Keys.SMOKY_QUARTZ_STAIRS.identifier().getPath(), new StairBlock(SMOKY_QUARTZ_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK).setId(Keys.SMOKY_QUARTZ_STAIRS)));
    public static final StairBlock SMOOTH_SMOKY_QUARTZ_STAIRS = register(Keys.SMOOTH_SMOKY_QUARTZ_STAIRS.identifier().getPath(), new StairBlock(SMOOTH_SMOKY_QUARTZ.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK).setId(Keys.SMOOTH_SMOKY_QUARTZ_STAIRS)));

    //dungeon trapped blocks
    public static final Block TRAPPED_MUD_BRICKS = register(Keys.TRAPPED_MUD_BRICKS.identifier().getPath(), new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).setId(Keys.TRAPPED_MUD_BRICKS)));
    public static final Block TRAPPED_LIGHT_MUD_BRICKS = register(Keys.TRAPPED_LIGHT_MUD_BRICKS.identifier().getPath(), new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).setId(Keys.TRAPPED_LIGHT_MUD_BRICKS).lightLevel((state) -> 11)));
    public static final Block TRAPPED_LUNATIC_STONE = register(Keys.TRAPPED_LUNATIC_STONE.identifier().getPath(), new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).setId(Keys.TRAPPED_LUNATIC_STONE)));
    public static final Block TRAPPED_LIGHT_LUNATIC_STONE = register(Keys.TRAPPED_LIGHT_LUNATIC_STONE.identifier().getPath(), new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).setId(Keys.TRAPPED_LIGHT_LUNATIC_STONE).lightLevel((state) -> 11)));
    public static final Block TRAPPED_GOLDEN_NETHER_BRICKS = register(Keys.TRAPPED_GOLDEN_NETHER_BRICKS.identifier().getPath(), new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).setId(Keys.TRAPPED_GOLDEN_NETHER_BRICKS)));
    public static final Block TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS = register(Keys.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS.identifier().getPath(), new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).setId(Keys.TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS).lightLevel((state) -> 11)));

    //dungeon other blocks, loots
    public static final RotatedPillarBlock MUD_BONE_BLOCK = register(Keys.MUD_BONE_BLOCK.identifier().getPath(), new RotatedPillarBlock(BlockBehaviour.Properties.of().setId(Keys.MUD_BONE_BLOCK).mapColor(MapColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(2.5F).sound(SoundType.BONE_BLOCK)));
    public static final Block MUD_BONE_PILE_BLOCK = register(Keys.MUD_BONE_PILE_BLOCK.identifier().getPath(), new BonePileBlock(BlockBehaviour.Properties.of().setId(Keys.MUD_BONE_PILE_BLOCK).mapColor(MapColor.COLOR_BROWN).randomTicks().strength(2.5F).sound(SoundType.BONE_BLOCK)));
    public static final Block THORNY_COBWEB = register(Keys.THORNY_COBWEB.identifier().getPath(), new ThornyWebBlock(BlockBehaviour.Properties.of().setId(Keys.THORNY_COBWEB).noCollision().requiresCorrectToolForDrops().strength(8.0F)));
    public static final Block AERIAL_NETHERRACK = register(Keys.AERIAL_NETHERRACK.identifier().getPath(), new Block(BlockBehaviour.Properties.of().setId(Keys.AERIAL_NETHERRACK).mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops().strength(6.0F, 8.0F)));
    public static final SlabBlock AERIAL_NETHERRACK_SLAB = register(Keys.AERIAL_NETHERRACK_SLAB.identifier().getPath(), new SlabBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_NETHERRACK).setId(Keys.AERIAL_NETHERRACK_SLAB)));
    public static final StairBlock AERIAL_NETHERRACK_STAIRS = register(Keys.AERIAL_NETHERRACK_STAIRS.identifier().getPath(), new StairBlock(AERIAL_NETHERRACK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(AERIAL_NETHERRACK).setId(Keys.AERIAL_NETHERRACK_STAIRS)));
    public static final WallBlock AERIAL_NETHERRACK_WALL = register(Keys.AERIAL_NETHERRACK_WALL.identifier().getPath(), new WallBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_NETHERRACK).setId(Keys.AERIAL_NETHERRACK_WALL)));

    //dungeon bookshelfs
    public static final Block MUD_BOOKSHELF = register(Keys.MUD_BOOKSHELF.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS).setId(Keys.MUD_BOOKSHELF)));
    public static final Block LUNATIC_BOOKSHELF = register(Keys.LUNATIC_BOOKSHELF.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE).setId(Keys.LUNATIC_BOOKSHELF)));
    public static final Block GOLDEN_NETHER_BOOKSHELF = register(Keys.GOLDEN_NETHER_BOOKSHELF.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS).setId(Keys.GOLDEN_NETHER_BOOKSHELF)));
    public static final Block SHADOW_CATACOMBS_BOOKSHELF = register(Keys.SHADOW_CATACOMBS_BOOKSHELF.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS).setId(Keys.SHADOW_CATACOMBS_BOOKSHELF)));
    public static final Block VOLUCITE_BOOKSHELF = register(Keys.VOLUCITE_BOOKSHELF.identifier().getPath(), new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE).setId(Keys.VOLUCITE_BOOKSHELF)));

    //glyph blocks
    public static final Block MUD_GLYPH_BLOCK = register(Keys.MUD_GLYPH_BLOCK.identifier().getPath(), new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS).setId(Keys.MUD_GLYPH_BLOCK).lightLevel((state) -> 9)));
    public static final Block LUNATIC_GLYPH_BLOCK = register(Keys.LUNATIC_GLYPH_BLOCK.identifier().getPath(), new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE).setId(Keys.LUNATIC_GLYPH_BLOCK).lightLevel((state) -> 9)));
    public static final Block GOLDEN_NETHER_PRISON_GLYPH_BLOCK = register(Keys.GOLDEN_NETHER_PRISON_GLYPH_BLOCK.identifier().getPath(), new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS).setId(Keys.GOLDEN_NETHER_PRISON_GLYPH_BLOCK).lightLevel((state) -> 9)));
    public static final Block VOLUCITE_GLYPH_BLOCK = register(Keys.VOLUCITE_GLYPH_BLOCK.identifier().getPath(), new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE).setId(Keys.VOLUCITE_GLYPH_BLOCK).lightLevel((state) -> 9)));
    public static final Block SHADOW_CATACOMBS_GLYPH_BLOCK = register(Keys.SHADOW_CATACOMBS_GLYPH_BLOCK.identifier().getPath(), new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS).setId(Keys.SHADOW_CATACOMBS_GLYPH_BLOCK).lightLevel((state) -> 9)));

    //trophies
    public static final Block MUD_CYCLE_MAGE_TROPHY = register(Keys.MUD_CYCLE_MAGE_TROPHY.identifier().getPath(), new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE).setId(Keys.MUD_CYCLE_MAGE_TROPHY).requiresCorrectToolForDrops()));
    public static final Block LUNAR_PRIEST_TROPHY = register(Keys.LUNAR_PRIEST_TROPHY.identifier().getPath(), new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE).setId(Keys.LUNAR_PRIEST_TROPHY).requiresCorrectToolForDrops()));
    public static final Block LILITH_TROPHY = register(Keys.LILITH_TROPHY.identifier().getPath(), new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE).setId(Keys.LILITH_TROPHY).requiresCorrectToolForDrops()));
    public static final Block CHAINED_GOD_TROPHY = register(Keys.CHAINED_GOD_TROPHY.identifier().getPath(), new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS).setId(Keys.CHAINED_GOD_TROPHY).requiresCorrectToolForDrops()));

    //ores
    public static final Block IRON_STELLAR_ORE = register(Keys.IRON_STELLAR_ORE.identifier().getPath(), new AerialHellOreBlock(0, 2, BlockBehaviour.Properties.of().setId(Keys.IRON_STELLAR_ORE).strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block GOLD_STELLAR_ORE = register(Keys.GOLD_STELLAR_ORE.identifier().getPath(), new AerialHellOreBlock(0, 2, BlockBehaviour.Properties.of().setId(Keys.GOLD_STELLAR_ORE).strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block DIAMOND_STELLAR_ORE = register(Keys.DIAMOND_STELLAR_ORE.identifier().getPath(), new AerialHellOreBlock(3, 5, BlockBehaviour.Properties.of().setId(Keys.DIAMOND_STELLAR_ORE).strength(5.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block FLUORITE_ORE = register(Keys.FLUORITE_ORE.identifier().getPath(), new BiomeShifterOreBlock(0, 2, BlockBehaviour.Properties.of().setId(Keys.FLUORITE_ORE).strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 2, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.SMOKY_QUARTZ_ORE));
    public static final Block MAGMATIC_GEL_ORE = register(Keys.MAGMATIC_GEL_ORE.identifier().getPath(), new MagmaticGelOreBlock(0, 2, BlockBehaviour.Properties.of().setId(Keys.MAGMATIC_GEL_ORE).strength(3.0F).sound(SoundType.STONE).lightLevel(s -> 4).requiresCorrectToolForDrops()));
    public static final Block RUBY_ORE = register(Keys.RUBY_ORE.identifier().getPath(), new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().setId(Keys.RUBY_ORE).strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block AZURITE_ORE = register(Keys.AZURITE_ORE.identifier().getPath(), new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().setId(Keys.AZURITE_ORE).strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block VOLUCITE_ORE = register(Keys.VOLUCITE_ORE.identifier().getPath(), new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().setId(Keys.VOLUCITE_ORE).strength(5.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block OBSIDIAN_ORE = register(Keys.OBSIDIAN_ORE.identifier().getPath(), new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().setId(Keys.OBSIDIAN_ORE).strength(5.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block SMOKY_QUARTZ_ORE = register(Keys.SMOKY_QUARTZ_ORE.identifier().getPath(), new AerialHellOreBlock(1, 3, BlockBehaviour.Properties.of().setId(Keys.SMOKY_QUARTZ_ORE).strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final Block RAW_RUBY_BLOCK = register(Keys.RAW_RUBY_BLOCK.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).setId(Keys.RAW_RUBY_BLOCK).requiresCorrectToolForDrops()));
    public static final Block RAW_AZURITE_BLOCK = register(Keys.RAW_AZURITE_BLOCK.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).setId(Keys.RAW_AZURITE_BLOCK).requiresCorrectToolForDrops()));
    public static final Block RAW_VOLUCITE_BLOCK = register(Keys.RAW_VOLUCITE_BLOCK.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).setId(Keys.RAW_VOLUCITE_BLOCK).requiresCorrectToolForDrops()));

    public static final Block FLUORITE_BLOCK = register(Keys.FLUORITE_BLOCK.identifier().getPath(), new BiomeShifterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).setId(Keys.FLUORITE_BLOCK).requiresCorrectToolForDrops(), 7, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.SMOKY_QUARTZ_BLOCK));
    public static final Block MAGMATIC_GEL_BLOCK = register(Keys.MAGMATIC_GEL_BLOCK.identifier().getPath(), new MagmaticGelBlock(BlockBehaviour.Properties.of().setId(Keys.MAGMATIC_GEL_BLOCK).strength(1.0F, 1600.0F).randomTicks().sound(SoundType.GLASS).noOcclusion().requiresCorrectToolForDrops().isViewBlocking((state, reader, pos) -> false)));
    public static final Block RUBY_BLOCK = register(Keys.RUBY_BLOCK.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).setId(Keys.RUBY_BLOCK).requiresCorrectToolForDrops()));
    public static final Block AZURITE_BLOCK = register(Keys.AZURITE_BLOCK.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).setId(Keys.AZURITE_BLOCK).requiresCorrectToolForDrops()));
    public static final Block VOLUCITE_BLOCK = register(Keys.VOLUCITE_BLOCK.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).setId(Keys.VOLUCITE_BLOCK).requiresCorrectToolForDrops()));

    //legendary ores blocks
    public static final Block ARSONIST_BLOCK = register(Keys.ARSONIST_BLOCK.identifier().getPath(), new ArsonistBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).setId(Keys.ARSONIST_BLOCK).requiresCorrectToolForDrops().lightLevel((state) -> 9)));
    public static final Block LUNATIC_CRYSTAL_BLOCK = register(Keys.LUNATIC_CRYSTAL_BLOCK.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).setId(Keys.LUNATIC_CRYSTAL_BLOCK).sound(SoundType.GLASS).requiresCorrectToolForDrops().lightLevel((state) -> 9)));
    public static final Block CURSED_CRYSAL_BLOCK = register(Keys.CURSED_CRYSAL_BLOCK.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).setId(Keys.CURSED_CRYSAL_BLOCK).sound(SoundType.GLASS).requiresCorrectToolForDrops().lightLevel((state) -> 9)));

    //cactus
    public static final SkyCactusBlock SKY_CACTUS = register(Keys.SKY_CACTUS.identifier().getPath(), new SkyCactusBlock(BlockBehaviour.Properties.of().setId(Keys.SKY_CACTUS).mapColor(MapColor.COLOR_CYAN).strength(0.4F).sound(SoundType.WOOL).randomTicks()));
    public static final Block SKY_CACTUS_FIBER_PLANKS = register(Keys.SKY_CACTUS_FIBER_PLANKS.identifier().getPath(), new Block(SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_PLANKS)));
    public static final Block SKY_CACTUS_FIBER_BOOKSHELF = register(Keys.SKY_CACTUS_FIBER_BOOKSHELF.identifier().getPath(), new Block(BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_PLANKS).setId(Keys.SKY_CACTUS_FIBER_BOOKSHELF)));
    public static final SkyCactusBlock VIBRANT_SKY_CACTUS = register(Keys.VIBRANT_SKY_CACTUS.identifier().getPath(), new SkyCactusBlock(BlockBehaviour.Properties.of().setId(Keys.VIBRANT_SKY_CACTUS).mapColor(MapColor.COLOR_BLUE).strength(0.4F).sound(SoundType.WOOL).randomTicks().lightLevel(s -> 15).noOcclusion()));
    public static final Block VIBRANT_SKY_CACTUS_FIBER_LANTERN = register(Keys.VIBRANT_SKY_CACTUS_FIBER_LANTERN.identifier().getPath(), new Block(BlockBehaviour.Properties.of().setId(Keys.VIBRANT_SKY_CACTUS_FIBER_LANTERN).mapColor(MapColor.SNOW).strength(0.5F).sound(SoundType.GLASS).noOcclusion().lightLevel(s -> 15)));

    //bushes
    public static final Block AERIAL_BERRY_BUSH = register(Keys.AERIAL_BERRY_BUSH.identifier().getPath(), new AerialBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH).setId(Keys.AERIAL_BERRY_BUSH)));
    public static final Block VIBRANT_AERIAL_BERRY_BUSH = register(Keys.VIBRANT_AERIAL_BERRY_BUSH.identifier().getPath(), new VibrantAerialBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH).setId(Keys.VIBRANT_AERIAL_BERRY_BUSH)));

    //crops
    public static final Block STELLAR_WHEAT = register(Keys.STELLAR_WHEAT.identifier().getPath(), new StellarCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).setId(Keys.STELLAR_WHEAT)));
    public static final Block BLUE_MEANIE_CROP = register(Keys.BLUE_MEANIE_CROP.identifier().getPath(), new StellarCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT).setId(Keys.BLUE_MEANIE_CROP)));

    //vertical growing plants
    public static final VerticalGrowingPlantBlock CLIMBING_VINE = register(Keys.CLIMBING_VINE.identifier().getPath(), new VerticalGrowingPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE).setId(Keys.CLIMBING_VINE), 4));
    public static final VerticalGrowingPlantBlock STELLAR_SUGAR_CANE = register(Keys.STELLAR_SUGAR_CANE.identifier().getPath(), new VerticalGrowingPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE).setId(Keys.STELLAR_SUGAR_CANE), 5));

    //chorus like
    public static final ChorusPlantLikeBlock FULL_MOON_PLANT = register(Keys.FULL_MOON_PLANT.identifier().getPath(), new ChorusPlantLikeBlock(BlockBehaviour.Properties.of().setId(Keys.FULL_MOON_PLANT).mapColor(MapColor.COLOR_PURPLE).forceSolidOff().strength(0.4F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY).lightLevel((state) -> 10)));
    public static final ChorusFlowerLikeBlock FULL_MOON_FLOWER = register(Keys.FULL_MOON_FLOWER.identifier().getPath(), new ChorusFlowerLikeBlock(FULL_MOON_PLANT, BlockBehaviour.Properties.of().setId(Keys.FULL_MOON_FLOWER).mapColor(MapColor.COLOR_PURPLE).forceSolidOff().randomTicks().strength(0.4F).sound(SoundType.WOOD).noOcclusion().isValidSpawn((state, blockGetter, pos, entitytype) -> false).pushReaction(PushReaction.DESTROY).isRedstoneConductor((state, blockGetter, pos) -> false).lightLevel((state) -> 15)));

    //vines
    public static final CaveVinesBlock GLOWING_STICK_FRUIT_VINES = register(Keys.GLOWING_STICK_FRUIT_VINES.identifier().getPath(), new AerialHellCaveVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAVE_VINES).setId(Keys.GLOWING_STICK_FRUIT_VINES)));
    public static final CaveVinesPlantBlock GLOWING_STICK_FRUIT_VINES_PLANT = register(Keys.GLOWING_STICK_FRUIT_VINES_PLANT.identifier().getPath(), new AerialHellCaveVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAVE_VINES_PLANT).setId(Keys.GLOWING_STICK_FRUIT_VINES_PLANT)));
    public static final CaveVinesBlock BLOSSOMING_VINES = register(Keys.BLOSSOMING_VINES.identifier().getPath(), new AerialHellCaveVinesBlock(BlockBehaviour.Properties.of().setId(Keys.BLOSSOMING_VINES).randomTicks().noCollision().instabreak().sound(SoundType.CAVE_VINES)));
    public static final CaveVinesPlantBlock BLOSSOMING_VINES_PLANT = register(Keys.BLOSSOMING_VINES_PLANT.identifier().getPath(), new AerialHellCaveVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(BLOSSOMING_VINES).setId(Keys.BLOSSOMING_VINES_PLANT)));
    public static final AerialHellTwistingVinesBlock LAZULI_ROOTS = register(Keys.LAZULI_ROOTS.identifier().getPath(), new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES).setId(Keys.LAZULI_ROOTS)));
    public static final AerialHellTwistingVinesPlantBlock LAZULI_ROOTS_PLANT = register(Keys.LAZULI_ROOTS_PLANT.identifier().getPath(), new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT).setId(Keys.LAZULI_ROOTS_PLANT)));
    public static final AerialHellTwistingVinesBlock STELLAR_ROOTS = register(Keys.STELLAR_ROOTS.identifier().getPath(), new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES).setId(Keys.STELLAR_ROOTS)));
    public static final AerialHellTwistingVinesPlantBlock STELLAR_ROOTS_PLANT = register(Keys.STELLAR_ROOTS_PLANT.identifier().getPath(), new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT).setId(Keys.STELLAR_ROOTS_PLANT)));
    public static final AerialHellTwistingVinesBlock DEAD_ROOTS = register(Keys.DEAD_ROOTS.identifier().getPath(), new DeadRootsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES).setId(Keys.DEAD_ROOTS)));
    public static final AerialHellTwistingVinesPlantBlock DEAD_ROOTS_PLANT = register(Keys.DEAD_ROOTS_PLANT.identifier().getPath(), new DeadRootsPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT).setId(Keys.DEAD_ROOTS_PLANT)));
    public static final AerialHellTwistingVinesBlock GLOWING_ROOTS = register(Keys.GLOWING_ROOTS.identifier().getPath(), new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES).lightLevel((state) -> 9).setId(Keys.GLOWING_ROOTS)));
    public static final AerialHellTwistingVinesPlantBlock GLOWING_ROOTS_PLANT = register(Keys.GLOWING_ROOTS_PLANT.identifier().getPath(), new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT).setId(Keys.GLOWING_ROOTS_PLANT).lightLevel((state) -> 14)));
    public static final AerialHellTwistingVinesBlock SHADOW_GLOWING_ROOTS = register(Keys.SHADOW_GLOWING_ROOTS.identifier().getPath(), new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES).setId(Keys.SHADOW_GLOWING_ROOTS).lightLevel((state) -> 8)));
    public static final AerialHellTwistingVinesPlantBlock SHADOW_GLOWING_ROOTS_PLANT = register(Keys.SHADOW_GLOWING_ROOTS_PLANT.identifier().getPath(), new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT).setId(Keys.SHADOW_GLOWING_ROOTS_PLANT).lightLevel((state) -> 13)));

    //grass
    public static final Block STELLAR_GRASS = register(Keys.STELLAR_GRASS.identifier().getPath(), new ShiftableRenderTallGrassBlock(BlockBehaviour.Properties.of().setId(Keys.STELLAR_GRASS).mapColor(MapColor.COLOR_GREEN).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
    public static final Block STELLAR_GRASS_BALL = register(Keys.STELLAR_GRASS_BALL.identifier().getPath(), new ShiftableRenderTallGrassBlock(BlockBehaviour.Properties.of().setId(Keys.STELLAR_GRASS_BALL).mapColor(MapColor.COLOR_GREEN).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
    public static final Block STELLAR_FERN = register(Keys.STELLAR_FERN.identifier().getPath(), new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().setId(Keys.STELLAR_FERN).mapColor(MapColor.COLOR_GREEN).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
    public static final Block STELLAR_TALL_GRASS = register(Keys.STELLAR_TALL_GRASS.identifier().getPath(), new DoublePlantBlock(BlockBehaviour.Properties.of().setId(Keys.STELLAR_TALL_GRASS).mapColor(MapColor.COLOR_GREEN).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
    public static final Block STELLAR_TALL_FERN = register(Keys.STELLAR_TALL_FERN.identifier().getPath(), new DoublePlantBlock(BlockBehaviour.Properties.of().setId(Keys.STELLAR_TALL_FERN).mapColor(MapColor.COLOR_GREEN).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
    public static final VerticalGrowingPlantBlock STELLAR_VERY_TALL_GRASS = register(Keys.STELLAR_VERY_TALL_GRASS.identifier().getPath(), new VerticalGrowingPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE).setId(Keys.STELLAR_VERY_TALL_GRASS), 3));
    public static final Block BLUISH_FERN = register(Keys.BLUISH_FERN.identifier().getPath(), new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().setId(Keys.BLUISH_FERN).mapColor(MapColor.COLOR_GREEN).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
    public static final Block TALL_BLUISH_FERN = register(Keys.TALL_BLUISH_FERN.identifier().getPath(), new DoublePlantBlock(BlockBehaviour.Properties.of().setId(Keys.TALL_BLUISH_FERN).mapColor(MapColor.COLOR_GREEN).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
    public static final Block POLYCHROME_FERN = register(Keys.POLYCHROME_FERN.identifier().getPath(), new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().setId(Keys.POLYCHROME_FERN).mapColor(MapColor.COLOR_GREEN).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
    public static final Block TALL_POLYCHROME_FERN = register(Keys.TALL_POLYCHROME_FERN.identifier().getPath(), new DoublePlantBlock(BlockBehaviour.Properties.of().setId(Keys.TALL_POLYCHROME_FERN).mapColor(MapColor.COLOR_GREEN).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
    public static final Block STELLAR_DEAD_BUSH = register(Keys.STELLAR_DEAD_BUSH.identifier().getPath(), new AerialHellDeadBushBlock(BlockBehaviour.Properties.of().setId(Keys.STELLAR_DEAD_BUSH).mapColor(MapColor.COLOR_GREEN).replaceable().mapColor(MapColor.COLOR_BROWN).noCollision().instabreak().sound(SoundType.GRASS)));
    public static final Block BRAMBLES = register(Keys.BRAMBLES.identifier().getPath(), new BramblesBlock(BlockBehaviour.Properties.of().setId(Keys.BRAMBLES).mapColor(MapColor.COLOR_GREEN).noCollision().strength(0.5F).sound(SoundType.GRASS)));
    public static final Block SHADOW_BRAMBLES = register(Keys.SHADOW_BRAMBLES.identifier().getPath(), new BramblesBlock(BlockBehaviour.Properties.of().setId(Keys.SHADOW_BRAMBLES).mapColor(MapColor.COLOR_GREEN).noCollision().strength(0.5F).sound(SoundType.GRASS)));
    public static final Block SHADOW_GRASS = register(Keys.SHADOW_GRASS.identifier().getPath(), new ShadowPlantBlock(BlockBehaviour.Properties.of().setId(Keys.SHADOW_GRASS).mapColor(MapColor.COLOR_GREEN).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
    public static final Block SHADOW_GRASS_BALL = register(Keys.SHADOW_GRASS_BALL.identifier().getPath(), new ShadowPlantBlock(BlockBehaviour.Properties.of().setId(Keys.SHADOW_GRASS_BALL).mapColor(MapColor.COLOR_GREEN).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
    public static final Block PURPLISH_STELLAR_GRASS = register(Keys.PURPLISH_STELLAR_GRASS.identifier().getPath(), new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().setId(Keys.PURPLISH_STELLAR_GRASS).mapColor(MapColor.COLOR_GREEN).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
    public static final Block STELLAR_CLOVERS = register(Keys.STELLAR_CLOVERS.identifier().getPath(), new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().setId(Keys.STELLAR_CLOVERS).mapColor(MapColor.COLOR_GREEN).replaceable().noCollision().instabreak().sound(SoundType.GRASS)));
    public static final Block GLOWING_STELLAR_GRASS = register(Keys.GLOWING_STELLAR_GRASS.identifier().getPath(), new GlowingStellarTallGrass(BlockBehaviour.Properties.of().setId(Keys.GLOWING_STELLAR_GRASS).mapColor(MapColor.COLOR_GREEN).replaceable().randomTicks().noCollision().lightLevel((state) -> {return state.getValue(BlockStateProperties.LIT) ? 10 : 0;}).instabreak().sound(SoundType.GRASS)));

    //flowers
    public static final Block BLUE_FLOWER = register(Keys.BLUE_FLOWER.identifier().getPath(), new FlowerBlock(MobEffects.BLINDNESS, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION).setId(Keys.BLUE_FLOWER)));
    public static final Block BLACK_ROSE = register(Keys.BLACK_ROSE.identifier().getPath(), new FlowerBlock(MobEffects.SLOW_FALLING, 12, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION).setId(Keys.BLACK_ROSE)));
    public static final Block BELLFLOWER = register(Keys.BELLFLOWER.identifier().getPath(), new FlowerBlock(MobEffects.MINING_FATIGUE, 12, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION).setId(Keys.BELLFLOWER)));

    //potted things
    public static final FlowerPotBlock POTTED_BLUE_FLOWER = register(Keys.POTTED_BLUE_FLOWER.identifier().getPath(), new FlowerPotBlock(BLUE_FLOWER, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_BLUE_FLOWER)));
    public static final FlowerPotBlock POTTED_BLACK_ROSE = register(Keys.POTTED_BLACK_ROSE.identifier().getPath(), new FlowerPotBlock(BLACK_ROSE, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_BLACK_ROSE)));
    public static final FlowerPotBlock POTTED_BELLFLOWER = register(Keys.POTTED_BELLFLOWER.identifier().getPath(), new FlowerPotBlock(BELLFLOWER, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_BELLFLOWER)));
    public static final FlowerPotBlock POTTED_STELLAR_FERN = register(Keys.POTTED_STELLAR_FERN.identifier().getPath(), new FlowerPotBlock(STELLAR_FERN, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_STELLAR_FERN)));
    public static final FlowerPotBlock POTTED_STELLAR_DEAD_BUSH = register(Keys.POTTED_STELLAR_DEAD_BUSH.identifier().getPath(), new FlowerPotBlock(STELLAR_DEAD_BUSH, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_STELLAR_DEAD_BUSH)));
    public static final FlowerPotBlock POTTED_SKY_CACTUS = register(Keys.POTTED_SKY_CACTUS.identifier().getPath(), new FlowerPotBlock(SKY_CACTUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_SKY_CACTUS)));
    public static final FlowerPotBlock POTTED_VIBRANT_SKY_CACTUS = register(Keys.POTTED_VIBRANT_SKY_CACTUS.identifier().getPath(), new FlowerPotBlock(VIBRANT_SKY_CACTUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_VIBRANT_SKY_CACTUS)));
    public static final FlowerPotBlock POTTED_AERIAL_TREE_SAPLING = register(Keys.POTTED_AERIAL_TREE_SAPLING.identifier().getPath(), new FlowerPotBlock(AERIAL_TREE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_AERIAL_TREE_SAPLING)));
    public static final FlowerPotBlock POTTED_GOLDEN_BEECH_SAPLING = register(Keys.POTTED_GOLDEN_BEECH_SAPLING.identifier().getPath(), new FlowerPotBlock(GOLDEN_BEECH_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_GOLDEN_BEECH_SAPLING)));
    public static final FlowerPotBlock POTTED_COPPER_PINE_SAPLING = register(Keys.POTTED_COPPER_PINE_SAPLING.identifier().getPath(), new FlowerPotBlock(COPPER_PINE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_COPPER_PINE_SAPLING)));
    public static final FlowerPotBlock POTTED_LAPIS_ROBINIA_SAPLING = register(Keys.POTTED_LAPIS_ROBINIA_SAPLING.identifier().getPath(), new FlowerPotBlock(LAPIS_ROBINIA_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_LAPIS_ROBINIA_SAPLING)));
    public static final FlowerPotBlock POTTED_SHADOW_PINE_SAPLING = register(Keys.POTTED_SHADOW_PINE_SAPLING.identifier().getPath(), new FlowerPotBlock(SHADOW_PINE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_SHADOW_PINE_SAPLING)));
    public static final FlowerPotBlock POTTED_PURPLE_SHADOW_PINE_SAPLING = register(Keys.POTTED_PURPLE_SHADOW_PINE_SAPLING.identifier().getPath(), new FlowerPotBlock(PURPLE_SHADOW_PINE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_PURPLE_SHADOW_PINE_SAPLING)));
    public static final FlowerPotBlock POTTED_STELLAR_JUNGLE_TREE_SAPLING = register(Keys.POTTED_STELLAR_JUNGLE_TREE_SAPLING.identifier().getPath(), new FlowerPotBlock(STELLAR_JUNGLE_TREE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_STELLAR_JUNGLE_TREE_SAPLING)));
    public static final FlowerPotBlock POTTED_CORTINARIUS_VIOLACEUS = register(Keys.POTTED_CORTINARIUS_VIOLACEUS.identifier().getPath(), new FlowerPotBlock(CORTINARIUS_VIOLACEUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_CORTINARIUS_VIOLACEUS)));
    public static final FlowerPotBlock POTTED_VERDIGRIS_AGARIC = register(Keys.POTTED_VERDIGRIS_AGARIC.identifier().getPath(), new FlowerPotBlock(VERDIGRIS_AGARIC, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_VERDIGRIS_AGARIC)));
    public static final FlowerPotBlock POTTED_VINE_BLOSSOM = register(Keys.POTTED_VINE_BLOSSOM.identifier().getPath(), new FlowerPotBlock(BLOSSOMING_VINES, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_VINE_BLOSSOM)));
    public static final FlowerPotBlock POTTED_GLOWING_BOLETUS = register(Keys.POTTED_GLOWING_BOLETUS.identifier().getPath(), new FlowerPotBlock(GLOWING_BOLETUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).setId(Keys.POTTED_GLOWING_BOLETUS).lightLevel((state) -> 9)));

    //with gui
    public static final Block OSCILLATOR = register(Keys.OSCILLATOR.identifier().getPath(), new OscillatorBlock(BlockBehaviour.Properties.of().setId(Keys.OSCILLATOR).strength(2.0F).sound(SoundType.STONE)));
    public static final Block FREEZER = register(Keys.FREEZER.identifier().getPath(), new FreezerBlock(BlockBehaviour.Properties.of().setId(Keys.FREEZER).strength(2.0F).sound(SoundType.STONE)));
    public static final Block STELLAR_FURNACE = register(Keys.STELLAR_FURNACE.identifier().getPath(), new StellarFurnaceBlock(BlockBehaviour.Properties.of().setId(Keys.STELLAR_FURNACE).requiresCorrectToolForDrops().strength(3.5F).lightLevel(getLightValueLit(BlockStateProperties.LIT, 13))));
    public static final Block GHOST_STELLAR_FURNACE = register(Keys.GHOST_STELLAR_FURNACE.identifier().getPath(), new GhostStellarFurnaceBlock(BlockBehaviour.Properties.of().setId(Keys.GHOST_STELLAR_FURNACE).requiresCorrectToolForDrops().noOcclusion().strength(3.5F).lightLevel(getLightValueLit(BlockStateProperties.LIT, 13))));

    private static ToIntFunction<BlockState> getLightValueLit(BooleanProperty property, int lightValue) {return (state) -> {return state.getValue(property) ? lightValue : 0;};}

    //chests
    public static final ChestBlock AERIAL_TREE_CHEST = register(Keys.AERIAL_TREE_CHEST.identifier().getPath(), new AerialHellChestBlock(AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_CHEST)));
    public static final ChestBlock GOLDEN_BEECH_CHEST = register(Keys.GOLDEN_BEECH_CHEST.identifier().getPath(), new AerialHellChestBlock(AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_CHEST)));
    public static final ChestBlock COPPER_PINE_CHEST = register(Keys.COPPER_PINE_CHEST.identifier().getPath(), new AerialHellChestBlock(COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_CHEST)));
    public static final ChestBlock LAPIS_ROBINIA_CHEST = register(Keys.LAPIS_ROBINIA_CHEST.identifier().getPath(), new AerialHellChestBlock(COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_CHEST)));
    public static final ChestBlock SHADOW_PINE_CHEST = register(Keys.SHADOW_PINE_CHEST.identifier().getPath(), new AerialHellChestBlock(SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_CHEST)));
    public static final ChestBlock STELLAR_JUNGLE_TREE_CHEST = register(Keys.STELLAR_JUNGLE_TREE_CHEST.identifier().getPath(), new AerialHellChestBlock(COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_CHEST)));
    public static final ChestBlock SKY_CACTUS_FIBER_CHEST = register(Keys.SKY_CACTUS_FIBER_CHEST.identifier().getPath(), new AerialHellChestBlock(SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_CHEST)));
    public static final ChestBlock GRAY_SHROOM_CHEST = register(Keys.GRAY_SHROOM_CHEST.identifier().getPath(), new AerialHellChestBlock(SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_CHEST)));
    public static final ChestBlock MUD_CHEST = register(Keys.MUD_CHEST.identifier().getPath(), new CoreProtectedChestBlock(MUD_CHEST_MATERIAL.setId(Keys.MUD_CHEST)));
    public static final ChestBlock LUNATIC_CHEST = register(Keys.LUNATIC_CHEST.identifier().getPath(), new CoreProtectedChestBlock(LUNATIC_CHEST_MATERIAL.setId(Keys.LUNATIC_CHEST)));
    public static final ChestBlock VOLUCITE_CHEST = register(Keys.VOLUCITE_CHEST.identifier().getPath(), new CoreProtectedChestBlock(VOLUCITE_CHEST_MATERIAL.setId(Keys.VOLUCITE_CHEST)));
    public static final ChestBlock SHADOW_CATACOMBS_CHEST = register(Keys.SHADOW_CATACOMBS_CHEST.identifier().getPath(), new CoreProtectedChestBlock(MUD_CHEST_MATERIAL.setId(Keys.SHADOW_CATACOMBS_CHEST)));
    public static final ChestBlock GOLDEN_NETHER_CHEST = register(Keys.GOLDEN_NETHER_CHEST.identifier().getPath(), new CoreProtectedChestBlock(GOLDEN_NETHER_CHEST_MATERIAL.setId(Keys.GOLDEN_NETHER_CHEST)));

    //chest mimics
    public static final Block AERIAL_TREE_CHEST_MIMIC = register(Keys.AERIAL_TREE_CHEST_MIMIC.identifier().getPath(), new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST).setId(Keys.AERIAL_TREE_CHEST_MIMIC)));
    public static final Block GOLDEN_BEECH_CHEST_MIMIC = register(Keys.GOLDEN_BEECH_CHEST_MIMIC.identifier().getPath(), new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST).setId(Keys.GOLDEN_BEECH_CHEST_MIMIC)));
    public static final Block COPPER_PINE_CHEST_MIMIC = register(Keys.COPPER_PINE_CHEST_MIMIC.identifier().getPath(), new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST).setId(Keys.COPPER_PINE_CHEST_MIMIC)));
    public static final Block SKY_CACTUS_FIBER_CHEST_MIMIC = register(Keys.SKY_CACTUS_FIBER_CHEST_MIMIC.identifier().getPath(), new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST).setId(Keys.SKY_CACTUS_FIBER_CHEST_MIMIC)));

    //barrel mimics
    public static final Block SHADOW_PINE_BARREL_MIMIC = register(Keys.SHADOW_PINE_BARREL_MIMIC.identifier().getPath(), new BarrelMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL).setId(Keys.SHADOW_PINE_BARREL_MIMIC)));

    //fences, bars or walls
    public static final FenceBlock AERIAL_TREE_FENCE = register(Keys.AERIAL_TREE_FENCE.identifier().getPath(), new FenceBlock(AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_FENCE)));
    public static final FenceBlock GOLDEN_BEECH_FENCE = register(Keys.GOLDEN_BEECH_FENCE.identifier().getPath(), new FenceBlock(AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_FENCE)));
    public static final FenceBlock COPPER_PINE_FENCE = register(Keys.COPPER_PINE_FENCE.identifier().getPath(), new FenceBlock(COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_FENCE)));
    public static final FenceBlock LAPIS_ROBINIA_FENCE = register(Keys.LAPIS_ROBINIA_FENCE.identifier().getPath(), new FenceBlock(COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_FENCE)));
    public static final FenceBlock SHADOW_PINE_FENCE = register(Keys.SHADOW_PINE_FENCE.identifier().getPath(), new FenceBlock(SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_FENCE)));
    public static final FenceBlock STELLAR_JUNGLE_TREE_FENCE = register(Keys.STELLAR_JUNGLE_TREE_FENCE.identifier().getPath(), new FenceBlock(COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_FENCE)));
    public static final FenceBlock SKY_CACTUS_FIBER_FENCE = register(Keys.SKY_CACTUS_FIBER_FENCE.identifier().getPath(), new FenceBlock(SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_FENCE)));
    public static final FenceBlock GRAY_SHROOM_FENCE = register(Keys.GRAY_SHROOM_FENCE.identifier().getPath(), new FenceBlock(SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_FENCE)));
    public static final IronBarsBlock RUBY_BARS = register(Keys.RUBY_BARS.identifier().getPath(), new IronBarsBlock(METAL_NOTSOLID_MATERIAL.setId(Keys.RUBY_BARS)));
    public static final WallBlock STELLAR_STONE_WALL = register(Keys.STELLAR_STONE_WALL.identifier().getPath(), new WallBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE).setId(Keys.STELLAR_STONE_WALL)));
    public static final WallBlock STELLAR_COBBLESTONE_WALL = register(Keys.STELLAR_COBBLESTONE_WALL.identifier().getPath(), new WallBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE).setId(Keys.STELLAR_COBBLESTONE_WALL)));
    public static final WallBlock STELLAR_STONE_BRICKS_WALL = register(Keys.STELLAR_STONE_BRICKS_WALL.identifier().getPath(), new WallBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS).setId(Keys.STELLAR_STONE_BRICKS_WALL)));
    public static final WallBlock MOSSY_STELLAR_STONE_WALL = register(Keys.MOSSY_STELLAR_STONE_WALL.identifier().getPath(), new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_STONE).setId(Keys.MOSSY_STELLAR_STONE_WALL)));
    public static final WallBlock MOSSY_STELLAR_COBBLESTONE_WALL = register(Keys.MOSSY_STELLAR_COBBLESTONE_WALL.identifier().getPath(), new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_COBBLESTONE).setId(Keys.MOSSY_STELLAR_COBBLESTONE_WALL)));
    public static final WallBlock SLIPPERY_SAND_STONE_WALL = register(Keys.SLIPPERY_SAND_STONE_WALL.identifier().getPath(), new WallBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE).setId(Keys.SLIPPERY_SAND_STONE_WALL)));
    public static final WallBlock SLIPPERY_SAND_STONE_BRICKS_WALL = register(Keys.SLIPPERY_SAND_STONE_BRICKS_WALL.identifier().getPath(), new WallBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS).setId(Keys.SLIPPERY_SAND_STONE_BRICKS_WALL)));
    public static final WallBlock CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL = register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL.identifier().getPath(), new WallBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS).setId(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL)));
    public static final WallBlock GLAUCOPHANITE_WALL = register(Keys.GLAUCOPHANITE_WALL.identifier().getPath(), new WallBlock(BlockBehaviour.Properties.ofFullCopy(GLAUCOPHANITE).setId(Keys.GLAUCOPHANITE_WALL)));
    public static final WallBlock POLISHED_GLAUCOPHANITE_WALL = register(Keys.POLISHED_GLAUCOPHANITE_WALL.identifier().getPath(), new WallBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_GLAUCOPHANITE).setId(Keys.POLISHED_GLAUCOPHANITE_WALL)));
    public static final WallBlock MAGMATIC_GEL_WALL = register(Keys.MAGMATIC_GEL_WALL.identifier().getPath(), new WallBlock(BlockBehaviour.Properties.ofFullCopy(MAGMATIC_GEL_BLOCK).setId(Keys.MAGMATIC_GEL_WALL)));

    //gates
    public static final FenceGateBlock AERIAL_TREE_GATE = register(Keys.AERIAL_TREE_GATE.identifier().getPath(), new FenceGateBlock(AerialHellWoodTypes.AERIAL_TREE, AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_GATE)));
    public static final FenceGateBlock GOLDEN_BEECH_GATE = register(Keys.GOLDEN_BEECH_GATE.identifier().getPath(), new FenceGateBlock(AerialHellWoodTypes.GOLDEN_BEECH, AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_GATE)));
    public static final FenceGateBlock COPPER_PINE_GATE = register(Keys.COPPER_PINE_GATE.identifier().getPath(), new FenceGateBlock(AerialHellWoodTypes.COPPER_PINE, COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_GATE)));
    public static final FenceGateBlock LAPIS_ROBINIA_GATE = register(Keys.LAPIS_ROBINIA_GATE.identifier().getPath(), new FenceGateBlock(AerialHellWoodTypes.LAPIS_ROBINIA, COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_GATE)));
    public static final FenceGateBlock SHADOW_PINE_GATE = register(Keys.SHADOW_PINE_GATE.identifier().getPath(), new FenceGateBlock(AerialHellWoodTypes.SHADOW_PINE, SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_GATE)));
    public static final FenceGateBlock STELLAR_JUNGLE_TREE_GATE = register(Keys.STELLAR_JUNGLE_TREE_GATE.identifier().getPath(), new FenceGateBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_GATE)));
    public static final FenceGateBlock SKY_CACTUS_FIBER_GATE = register(Keys.SKY_CACTUS_FIBER_GATE.identifier().getPath(), new FenceGateBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_GATE)));
    public static final FenceGateBlock GRAY_SHROOM_GATE = register(Keys.GRAY_SHROOM_GATE.identifier().getPath(), new FenceGateBlock(AerialHellWoodTypes.GRAY_SHROOM, SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_GATE)));

    //doors
    public static final DoorBlock AERIAL_TREE_DOOR = register(Keys.AERIAL_TREE_DOOR.identifier().getPath(), new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS).setId(Keys.AERIAL_TREE_DOOR).noOcclusion()));
    public static final DoorBlock GOLDEN_BEECH_DOOR = register(Keys.GOLDEN_BEECH_DOOR.identifier().getPath(), new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS).setId(Keys.GOLDEN_BEECH_DOOR).noOcclusion()));
    public static final DoorBlock COPPER_PINE_DOOR = register(Keys.COPPER_PINE_DOOR.identifier().getPath(), new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_PLANKS).setId(Keys.COPPER_PINE_DOOR).noOcclusion()));
    public static final DoorBlock LAPIS_ROBINIA_DOOR = register(Keys.LAPIS_ROBINIA_DOOR.identifier().getPath(), new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_PLANKS).setId(Keys.LAPIS_ROBINIA_DOOR).noOcclusion()));
    public static final DoorBlock SHADOW_PINE_DOOR = register(Keys.SHADOW_PINE_DOOR.identifier().getPath(), new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_PLANKS).setId(Keys.SHADOW_PINE_DOOR).noOcclusion()));
    public static final DoorBlock STELLAR_JUNGLE_TREE_DOOR = register(Keys.STELLAR_JUNGLE_TREE_DOOR.identifier().getPath(), new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_PLANKS).setId(Keys.STELLAR_JUNGLE_TREE_DOOR).noOcclusion()));
    public static final DoorBlock SKY_CACTUS_FIBER_DOOR = register(Keys.SKY_CACTUS_FIBER_DOOR.identifier().getPath(), new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_PLANKS).setId(Keys.SKY_CACTUS_FIBER_DOOR).noOcclusion()));
    public static final DoorBlock GRAY_SHROOM_DOOR = register(Keys.GRAY_SHROOM_DOOR.identifier().getPath(), new DoorBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_PLANKS).setId(Keys.GRAY_SHROOM_DOOR).noOcclusion()));
    public static final DoorBlock RUBY_DOOR = register(Keys.RUBY_DOOR.identifier().getPath(), new DoorBlock(BlockSetType.IRON, METAL_NOTSOLID_MATERIAL.setId(Keys.RUBY_DOOR)));

    //trapdoors
    public static final TrapDoorBlock AERIAL_TREE_TRAPDOOR = register(Keys.AERIAL_TREE_TRAPDOOR.identifier().getPath(), new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS).setId(Keys.AERIAL_TREE_TRAPDOOR).noOcclusion()));
    public static final TrapDoorBlock GOLDEN_BEECH_TRAPDOOR = register(Keys.GOLDEN_BEECH_TRAPDOOR.identifier().getPath(), new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS).setId(Keys.GOLDEN_BEECH_TRAPDOOR).noOcclusion()));
    public static final TrapDoorBlock COPPER_PINE_TRAPDOOR = register(Keys.COPPER_PINE_TRAPDOOR.identifier().getPath(), new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_PLANKS).setId(Keys.COPPER_PINE_TRAPDOOR).noOcclusion()));
    public static final TrapDoorBlock LAPIS_ROBINIA_TRAPDOOR = register(Keys.LAPIS_ROBINIA_TRAPDOOR.identifier().getPath(), new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_PLANKS).setId(Keys.LAPIS_ROBINIA_TRAPDOOR).noOcclusion()));
    public static final TrapDoorBlock SHADOW_PINE_TRAPDOOR = register(Keys.SHADOW_PINE_TRAPDOOR.identifier().getPath(), new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_PLANKS).setId(Keys.SHADOW_PINE_TRAPDOOR).noOcclusion()));
    public static final TrapDoorBlock STELLAR_JUNGLE_TREE_TRAPDOOR = register(Keys.STELLAR_JUNGLE_TREE_TRAPDOOR.identifier().getPath(), new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_PLANKS).setId(Keys.STELLAR_JUNGLE_TREE_TRAPDOOR).noOcclusion()));
    public static final TrapDoorBlock SKY_CACTUS_FIBER_TRAPDOOR = register(Keys.SKY_CACTUS_FIBER_TRAPDOOR.identifier().getPath(), new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_PLANKS).setId(Keys.SKY_CACTUS_FIBER_TRAPDOOR).noOcclusion()));
    public static final TrapDoorBlock GRAY_SHROOM_TRAPDOOR = register(Keys.GRAY_SHROOM_TRAPDOOR.identifier().getPath(), new TrapDoorBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_PLANKS).setId(Keys.GRAY_SHROOM_TRAPDOOR).noOcclusion()));
    public static final TrapDoorBlock RUBY_TRAPDOOR = register(Keys.RUBY_TRAPDOOR.identifier().getPath(), new TrapDoorBlock(BlockSetType.IRON, METAL_NOTSOLID_MATERIAL.setId(Keys.RUBY_TRAPDOOR)));

    //buttons
    public static final ButtonBlock STELLAR_STONE_BUTTON = register(Keys.STELLAR_STONE_BUTTON.identifier().getPath(), new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE).setId(Keys.STELLAR_STONE_BUTTON)));
    public static final ButtonBlock STELLAR_COBBLESTONE_BUTTON = register(Keys.STELLAR_COBBLESTONE_BUTTON.identifier().getPath(), new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE).setId(Keys.STELLAR_COBBLESTONE_BUTTON)));
    public static final ButtonBlock STELLAR_STONE_BRICKS_BUTTON = register(Keys.STELLAR_STONE_BRICKS_BUTTON.identifier().getPath(), new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS).setId(Keys.STELLAR_STONE_BRICKS_BUTTON)));
    public static final ButtonBlock SLIPPERY_SAND_STONE_BUTTON = register(Keys.SLIPPERY_SAND_STONE_BUTTON.identifier().getPath(), new ButtonBlock(BlockSetType.STONE, 30, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE).setId(Keys.SLIPPERY_SAND_STONE_BUTTON)));
    public static final ButtonBlock SLIPPERY_SAND_STONE_BRICKS_BUTTON = register(Keys.SLIPPERY_SAND_STONE_BRICKS_BUTTON.identifier().getPath(), new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS).setId(Keys.SLIPPERY_SAND_STONE_BRICKS_BUTTON)));
    public static final ButtonBlock AERIAL_TREE_BUTTON = register(Keys.AERIAL_TREE_BUTTON.identifier().getPath(), new ButtonBlock(BlockSetType.OAK, 30, AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_BUTTON)));
    public static final ButtonBlock GOLDEN_BEECH_BUTTON = register(Keys.GOLDEN_BEECH_BUTTON.identifier().getPath(), new ButtonBlock(BlockSetType.OAK, 30, AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_BUTTON)));
    public static final ButtonBlock COPPER_PINE_BUTTON = register(Keys.COPPER_PINE_BUTTON.identifier().getPath(), new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_BUTTON)));
    public static final ButtonBlock LAPIS_ROBINIA_BUTTON = register(Keys.LAPIS_ROBINIA_BUTTON.identifier().getPath(), new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_BUTTON)));
    public static final ButtonBlock SHADOW_PINE_BUTTON = register(Keys.SHADOW_PINE_BUTTON.identifier().getPath(), new ButtonBlock(BlockSetType.OAK, 30, SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_BUTTON)));
    public static final ButtonBlock STELLAR_JUNGLE_TREE_BUTTON = register(Keys.STELLAR_JUNGLE_TREE_BUTTON.identifier().getPath(), new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_BUTTON)));
    public static final ButtonBlock SKY_CACTUS_FIBER_BUTTON = register(Keys.SKY_CACTUS_FIBER_BUTTON.identifier().getPath(), new ButtonBlock(BlockSetType.OAK, 30, SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_BUTTON)));
    public static final ButtonBlock GRAY_SHROOM_BUTTON = register(Keys.GRAY_SHROOM_BUTTON.identifier().getPath(), new ButtonBlock(BlockSetType.CRIMSON, 30, SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_BUTTON)));
    public static final ButtonBlock GLAUCOPHANITE_BUTTON = register(Keys.GLAUCOPHANITE_BUTTON.identifier().getPath(), new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(GLAUCOPHANITE).setId(Keys.GLAUCOPHANITE_BUTTON)));

    //pressure plates
    public static final PressurePlateBlock STELLAR_STONE_PRESSURE_PLATE = register(Keys.STELLAR_STONE_PRESSURE_PLATE.identifier().getPath(), new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE).setId(Keys.STELLAR_STONE_PRESSURE_PLATE)));
    public static final PressurePlateBlock STELLAR_COBBLESTONE_PRESSURE_PLATE = register(Keys.STELLAR_COBBLESTONE_PRESSURE_PLATE.identifier().getPath(), new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE).setId(Keys.STELLAR_COBBLESTONE_PRESSURE_PLATE)));
    public static final PressurePlateBlock STELLAR_STONE_BRICKS_PRESSURE_PLATE = register(Keys.STELLAR_STONE_BRICKS_PRESSURE_PLATE.identifier().getPath(), new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS).setId(Keys.STELLAR_STONE_BRICKS_PRESSURE_PLATE)));
    public static final PressurePlateBlock SLIPPERY_SAND_STONE_PRESSURE_PLATE = register(Keys.SLIPPERY_SAND_STONE_PRESSURE_PLATE.identifier().getPath(), new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE).setId(Keys.SLIPPERY_SAND_STONE_PRESSURE_PLATE)));
    public static final PressurePlateBlock SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE = register(Keys.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE.identifier().getPath(), new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS).setId(Keys.SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE)));
    public static final PressurePlateBlock AERIAL_TREE_PRESSURE_PLATE = register(Keys.AERIAL_TREE_PRESSURE_PLATE.identifier().getPath(), new PressurePlateBlock(BlockSetType.OAK, AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_PRESSURE_PLATE)));
    public static final PressurePlateBlock GOLDEN_BEECH_PRESSURE_PLATE = register(Keys.GOLDEN_BEECH_PRESSURE_PLATE.identifier().getPath(), new PressurePlateBlock(BlockSetType.OAK, AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_PRESSURE_PLATE)));
    public static final PressurePlateBlock COPPER_PINE_PRESSURE_PLATE = register(Keys.COPPER_PINE_PRESSURE_PLATE.identifier().getPath(), new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_PRESSURE_PLATE)));
    public static final PressurePlateBlock LAPIS_ROBINIA_PRESSURE_PLATE = register(Keys.LAPIS_ROBINIA_PRESSURE_PLATE.identifier().getPath(), new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_PRESSURE_PLATE)));
    public static final PressurePlateBlock SHADOW_PINE_PRESSURE_PLATE = register(Keys.SHADOW_PINE_PRESSURE_PLATE.identifier().getPath(), new PressurePlateBlock(BlockSetType.OAK, SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_PRESSURE_PLATE)));
    public static final PressurePlateBlock STELLAR_JUNGLE_TREE_PRESSURE_PLATE = register(Keys.STELLAR_JUNGLE_TREE_PRESSURE_PLATE.identifier().getPath(), new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_PRESSURE_PLATE)));
    public static final PressurePlateBlock SKY_CACTUS_FIBER_PRESSURE_PLATE = register(Keys.SKY_CACTUS_FIBER_PRESSURE_PLATE.identifier().getPath(), new PressurePlateBlock(BlockSetType.OAK, SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_PRESSURE_PLATE)));
    public static final PressurePlateBlock GRAY_SHROOM_PRESSURE_PLATE = register(Keys.GRAY_SHROOM_PRESSURE_PLATE.identifier().getPath(), new PressurePlateBlock(BlockSetType.CRIMSON, SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_PRESSURE_PLATE)));
    public static final PressurePlateBlock GLAUCOPHANITE_PRESSURE_PLATE = register(Keys.GLAUCOPHANITE_PRESSURE_PLATE.identifier().getPath(), new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(GLAUCOPHANITE).setId(Keys.GLAUCOPHANITE_PRESSURE_PLATE)));

    //slabs
    public static final SlabBlock AERIAL_TREE_SLAB = register(Keys.AERIAL_TREE_SLAB.identifier().getPath(), new SlabBlock(AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_SLAB)));
    public static final SlabBlock GOLDEN_BEECH_SLAB = register(Keys.GOLDEN_BEECH_SLAB.identifier().getPath(), new SlabBlock(AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_SLAB)));
    public static final SlabBlock COPPER_PINE_SLAB = register(Keys.COPPER_PINE_SLAB.identifier().getPath(), new SlabBlock(COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_SLAB)));
    public static final SlabBlock LAPIS_ROBINIA_SLAB = register(Keys.LAPIS_ROBINIA_SLAB.identifier().getPath(), new SlabBlock(COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_SLAB)));
    public static final SlabBlock SHADOW_PINE_SLAB = register(Keys.SHADOW_PINE_SLAB.identifier().getPath(), new SlabBlock(SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_SLAB)));
    public static final SlabBlock STELLAR_JUNGLE_TREE_SLAB = register(Keys.STELLAR_JUNGLE_TREE_SLAB.identifier().getPath(), new SlabBlock(COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_SLAB)));
    public static final SlabBlock SKY_CACTUS_FIBER_SLAB = register(Keys.SKY_CACTUS_FIBER_SLAB.identifier().getPath(), new SlabBlock(SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_SLAB)));
    public static final SlabBlock GRAY_SHROOM_SLAB = register(Keys.GRAY_SHROOM_SLAB.identifier().getPath(), new SlabBlock(SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_SLAB)));
    public static final SlabBlock STELLAR_STONE_SLAB = register(Keys.STELLAR_STONE_SLAB.identifier().getPath(), new SlabBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE).setId(Keys.STELLAR_STONE_SLAB)));
    public static final SlabBlock STELLAR_COBBLESTONE_SLAB = register(Keys.STELLAR_COBBLESTONE_SLAB.identifier().getPath(), new SlabBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE).setId(Keys.STELLAR_COBBLESTONE_SLAB)));
    public static final SlabBlock STELLAR_STONE_BRICKS_SLAB = register(Keys.STELLAR_STONE_BRICKS_SLAB.identifier().getPath(), new SlabBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS).setId(Keys.STELLAR_STONE_BRICKS_SLAB)));
    public static final SlabBlock MOSSY_STELLAR_STONE_SLAB = register(Keys.MOSSY_STELLAR_STONE_SLAB.identifier().getPath(), new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_STONE).setId(Keys.MOSSY_STELLAR_STONE_SLAB)));
    public static final SlabBlock MOSSY_STELLAR_COBBLESTONE_SLAB = register(Keys.MOSSY_STELLAR_COBBLESTONE_SLAB.identifier().getPath(), new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_COBBLESTONE).setId(Keys.MOSSY_STELLAR_COBBLESTONE_SLAB)));
    public static final SlabBlock SLIPPERY_SAND_STONE_SLAB = register(Keys.SLIPPERY_SAND_STONE_SLAB.identifier().getPath(), new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE).setId(Keys.SLIPPERY_SAND_STONE_SLAB)));
    public static final SlabBlock SLIPPERY_SAND_STONE_BRICKS_SLAB = register(Keys.SLIPPERY_SAND_STONE_BRICKS_SLAB.identifier().getPath(), new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS).setId(Keys.SLIPPERY_SAND_STONE_BRICKS_SLAB)));
    public static final SlabBlock CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB = register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB.identifier().getPath(), new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS).setId(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB)));
    public static final SlabBlock POLISHED_GLAUCOPHANITE_SLAB = register(Keys.POLISHED_GLAUCOPHANITE_SLAB.identifier().getPath(), new SlabBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_GLAUCOPHANITE).setId(Keys.POLISHED_GLAUCOPHANITE_SLAB)));
    public static final SlabBlock MAGMATIC_GEL_SLAB = register(Keys.MAGMATIC_GEL_SLAB.identifier().getPath(), new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MAGMATIC_GEL_BLOCK).setId(Keys.MAGMATIC_GEL_SLAB)));

    //stairs
    public static final StairBlock AERIAL_TREE_STAIRS = register(Keys.AERIAL_TREE_STAIRS.identifier().getPath(), new StairBlock(AERIAL_TREE_PLANKS.defaultBlockState(), AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_STAIRS)));
    public static final StairBlock GOLDEN_BEECH_STAIRS = register(Keys.GOLDEN_BEECH_STAIRS.identifier().getPath(), new StairBlock(GOLDEN_BEECH_PLANKS.defaultBlockState(), AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_STAIRS)));
    public static final StairBlock COPPER_PINE_STAIRS = register(Keys.COPPER_PINE_STAIRS.identifier().getPath(), new StairBlock(COPPER_PINE_PLANKS.defaultBlockState(), COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_STAIRS)));
    public static final StairBlock LAPIS_ROBINIA_STAIRS = register(Keys.LAPIS_ROBINIA_STAIRS.identifier().getPath(), new StairBlock(LAPIS_ROBINIA_PLANKS.defaultBlockState(), COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_STAIRS)));
    public static final StairBlock SHADOW_PINE_STAIRS = register(Keys.SHADOW_PINE_STAIRS.identifier().getPath(), new StairBlock(SHADOW_PINE_PLANKS.defaultBlockState(), SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_STAIRS)));
    public static final StairBlock STELLAR_JUNGLE_TREE_STAIRS = register(Keys.STELLAR_JUNGLE_TREE_STAIRS.identifier().getPath(), new StairBlock(STELLAR_JUNGLE_TREE_PLANKS.defaultBlockState(), COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_STAIRS)));
    public static final StairBlock SKY_CACTUS_FIBER_STAIRS = register(Keys.SKY_CACTUS_FIBER_STAIRS.identifier().getPath(), new StairBlock(SKY_CACTUS_FIBER_PLANKS.defaultBlockState(), SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_STAIRS)));
    public static final StairBlock GRAY_SHROOM_STAIRS = register(Keys.GRAY_SHROOM_STAIRS.identifier().getPath(), new StairBlock(GRAY_SHROOM_PLANKS.defaultBlockState(), SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_STAIRS)));
    public static final StairBlock STELLAR_STONE_STAIRS = register(Keys.STELLAR_STONE_STAIRS.identifier().getPath(), new StairBlock(STELLAR_STONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE).setId(Keys.STELLAR_STONE_STAIRS)));
    public static final StairBlock STELLAR_COBBLESTONE_STAIRS = register(Keys.STELLAR_COBBLESTONE_STAIRS.identifier().getPath(), new StairBlock(STELLAR_COBBLESTONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE).setId(Keys.STELLAR_COBBLESTONE_STAIRS)));
    public static final StairBlock STELLAR_STONE_BRICKS_STAIRS = register(Keys.STELLAR_STONE_BRICKS_STAIRS.identifier().getPath(), new StairBlock(STELLAR_STONE_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS).setId(Keys.STELLAR_STONE_BRICKS_STAIRS)));
    public static final StairBlock MOSSY_STELLAR_STONE_STAIRS = register(Keys.MOSSY_STELLAR_STONE_STAIRS.identifier().getPath(), new StairBlock(MOSSY_STELLAR_STONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_STONE).setId(Keys.MOSSY_STELLAR_STONE_STAIRS)));
    public static final StairBlock MOSSY_STELLAR_COBBLESTONE_STAIRS = register(Keys.MOSSY_STELLAR_COBBLESTONE_STAIRS.identifier().getPath(), new StairBlock(MOSSY_STELLAR_COBBLESTONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_COBBLESTONE).setId(Keys.MOSSY_STELLAR_COBBLESTONE_STAIRS)));
    public static final StairBlock SLIPPERY_SAND_STONE_STAIRS = register(Keys.SLIPPERY_SAND_STONE_STAIRS.identifier().getPath(), new StairBlock(SLIPPERY_SAND_STONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE).setId(Keys.SLIPPERY_SAND_STONE_STAIRS)));
    public static final StairBlock SLIPPERY_SAND_STONE_BRICKS_STAIRS = register(Keys.SLIPPERY_SAND_STONE_BRICKS_STAIRS.identifier().getPath(), new StairBlock(SLIPPERY_SAND_STONE_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS).setId(Keys.SLIPPERY_SAND_STONE_BRICKS_STAIRS)));
    public static final StairBlock CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS = register(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS.identifier().getPath(), new StairBlock(SLIPPERY_SAND_STONE_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS).setId(Keys.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS)));
    public static final StairBlock POLISHED_GLAUCOPHANITE_STAIRS = register(Keys.POLISHED_GLAUCOPHANITE_STAIRS.identifier().getPath(), new StairBlock(POLISHED_GLAUCOPHANITE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(POLISHED_GLAUCOPHANITE).setId(Keys.POLISHED_GLAUCOPHANITE_STAIRS)));
    public static final StairBlock MAGMATIC_GEL_STAIRS = register(Keys.MAGMATIC_GEL_STAIRS.identifier().getPath(), new StairBlock(MAGMATIC_GEL_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MAGMATIC_GEL_BLOCK).setId(Keys.MAGMATIC_GEL_STAIRS)));

    //signs
    public static final AerialHellStandingSignBlock AERIAL_TREE_STANDING_SIGN = register(Keys.AERIAL_TREE_STANDING_SIGN.identifier().getPath(), new AerialHellStandingSignBlock(AERIAL_TREE_SIGN_MATERIAL.setId(Keys.AERIAL_TREE_STANDING_SIGN), AerialHellWoodTypes.AERIAL_TREE));
    public static final AerialHellWallSignBlock AERIAL_TREE_WALL_SIGN = register(Keys.AERIAL_TREE_WALL_SIGN.identifier().getPath(), new AerialHellWallSignBlock(AERIAL_TREE_SIGN_MATERIAL.setId(Keys.AERIAL_TREE_WALL_SIGN), AerialHellWoodTypes.AERIAL_TREE));
    public static final AerialHellStandingSignBlock GOLDEN_BEECH_STANDING_SIGN = register(Keys.GOLDEN_BEECH_STANDING_SIGN.identifier().getPath(), new AerialHellStandingSignBlock(AERIAL_TREE_SIGN_MATERIAL.setId(Keys.GOLDEN_BEECH_STANDING_SIGN), AerialHellWoodTypes.GOLDEN_BEECH));
    public static final AerialHellWallSignBlock GOLDEN_BEECH_WALL_SIGN = register(Keys.GOLDEN_BEECH_WALL_SIGN.identifier().getPath(), new AerialHellWallSignBlock(AERIAL_TREE_SIGN_MATERIAL.setId(Keys.GOLDEN_BEECH_WALL_SIGN), AerialHellWoodTypes.GOLDEN_BEECH));
    public static final AerialHellStandingSignBlock COPPER_PINE_STANDING_SIGN = register(Keys.COPPER_PINE_STANDING_SIGN.identifier().getPath(), new AerialHellStandingSignBlock(COPPER_PINE_SIGN_MATERIAL.setId(Keys.COPPER_PINE_STANDING_SIGN), AerialHellWoodTypes.COPPER_PINE));
    public static final AerialHellWallSignBlock COPPER_PINE_WALL_SIGN = register(Keys.COPPER_PINE_WALL_SIGN.identifier().getPath(), new AerialHellWallSignBlock(COPPER_PINE_SIGN_MATERIAL.setId(Keys.COPPER_PINE_WALL_SIGN), AerialHellWoodTypes.COPPER_PINE));
    public static final AerialHellStandingSignBlock LAPIS_ROBINIA_STANDING_SIGN = register(Keys.LAPIS_ROBINIA_STANDING_SIGN.identifier().getPath(), new AerialHellStandingSignBlock(COPPER_PINE_SIGN_MATERIAL.setId(Keys.LAPIS_ROBINIA_STANDING_SIGN), AerialHellWoodTypes.LAPIS_ROBINIA));
    public static final AerialHellWallSignBlock LAPIS_ROBINIA_WALL_SIGN = register(Keys.LAPIS_ROBINIA_WALL_SIGN.identifier().getPath(), new AerialHellWallSignBlock(COPPER_PINE_SIGN_MATERIAL.setId(Keys.LAPIS_ROBINIA_WALL_SIGN), AerialHellWoodTypes.LAPIS_ROBINIA));
    public static final AerialHellStandingSignBlock SHADOW_PINE_STANDING_SIGN = register(Keys.SHADOW_PINE_STANDING_SIGN.identifier().getPath(), new AerialHellStandingSignBlock(SHADOW_PINE_SIGN_MATERIAL.setId(Keys.SHADOW_PINE_STANDING_SIGN), AerialHellWoodTypes.SHADOW_PINE));
    public static final AerialHellWallSignBlock SHADOW_PINE_WALL_SIGN = register(Keys.SHADOW_PINE_WALL_SIGN.identifier().getPath(), new AerialHellWallSignBlock(SHADOW_PINE_SIGN_MATERIAL.setId(Keys.SHADOW_PINE_WALL_SIGN), AerialHellWoodTypes.SHADOW_PINE));
    public static final AerialHellStandingSignBlock STELLAR_JUNGLE_TREE_STANDING_SIGN = register(Keys.STELLAR_JUNGLE_TREE_STANDING_SIGN.identifier().getPath(), new AerialHellStandingSignBlock(COPPER_PINE_SIGN_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_STANDING_SIGN), AerialHellWoodTypes.STELLAR_JUNGLE_TREE));
    public static final AerialHellWallSignBlock STELLAR_JUNGLE_TREE_WALL_SIGN = register(Keys.STELLAR_JUNGLE_TREE_WALL_SIGN.identifier().getPath(), new AerialHellWallSignBlock(COPPER_PINE_SIGN_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_WALL_SIGN), AerialHellWoodTypes.STELLAR_JUNGLE_TREE));
    public static final AerialHellStandingSignBlock SKY_CACTUS_FIBER_STANDING_SIGN = register(Keys.SKY_CACTUS_FIBER_STANDING_SIGN.identifier().getPath(), new AerialHellStandingSignBlock(SKY_CACTUS_FIBER_SIGN_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_STANDING_SIGN), AerialHellWoodTypes.SKY_CACTUS_FIBER));
    public static final AerialHellWallSignBlock SKY_CACTUS_FIBER_WALL_SIGN = register(Keys.SKY_CACTUS_FIBER_WALL_SIGN.identifier().getPath(), new AerialHellWallSignBlock(SKY_CACTUS_FIBER_SIGN_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_WALL_SIGN), AerialHellWoodTypes.SKY_CACTUS_FIBER));
    public static final AerialHellStandingSignBlock GRAY_SHROOM_STANDING_SIGN = register(Keys.GRAY_SHROOM_STANDING_SIGN.identifier().getPath(), new AerialHellStandingSignBlock(SHROOM_SIGN_MATERIAL.setId(Keys.GRAY_SHROOM_STANDING_SIGN), AerialHellWoodTypes.GRAY_SHROOM));
    public static final AerialHellWallSignBlock GRAY_SHROOM_WALL_SIGN = register(Keys.GRAY_SHROOM_WALL_SIGN.identifier().getPath(), new AerialHellWallSignBlock(SHROOM_SIGN_MATERIAL.setId(Keys.GRAY_SHROOM_WALL_SIGN), AerialHellWoodTypes.GRAY_SHROOM));

    //hanging signs
    public static final CeilingHangingSignBlock AERIAL_TREE_HANGING_SIGN = register(Keys.AERIAL_TREE_HANGING_SIGN.identifier().getPath(), new AerialHellHangingSignBlock(AerialHellWoodTypes.AERIAL_TREE, AERIAL_TREE_SIGN_MATERIAL.setId(Keys.AERIAL_TREE_HANGING_SIGN)));
    public static final WallHangingSignBlock AERIAL_TREE_WALL_HANGING_SIGN = register(Keys.AERIAL_TREE_WALL_HANGING_SIGN.identifier().getPath(), new AerialHellWallHangingSignBlock(AerialHellWoodTypes.AERIAL_TREE, BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_HANGING_SIGN).setId(Keys.AERIAL_TREE_WALL_HANGING_SIGN).overrideLootTable(AERIAL_TREE_HANGING_SIGN.getLootTable())));
    public static final CeilingHangingSignBlock GOLDEN_BEECH_HANGING_SIGN = register(Keys.GOLDEN_BEECH_HANGING_SIGN.identifier().getPath(), new AerialHellHangingSignBlock(AerialHellWoodTypes.GOLDEN_BEECH, AERIAL_TREE_SIGN_MATERIAL.setId(Keys.GOLDEN_BEECH_HANGING_SIGN)));
    public static final WallHangingSignBlock GOLDEN_BEECH_WALL_HANGING_SIGN = register(Keys.GOLDEN_BEECH_WALL_HANGING_SIGN.identifier().getPath(), new AerialHellWallHangingSignBlock(AerialHellWoodTypes.GOLDEN_BEECH, BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_HANGING_SIGN).setId(Keys.GOLDEN_BEECH_WALL_HANGING_SIGN).overrideLootTable(GOLDEN_BEECH_HANGING_SIGN.getLootTable())));
    public static final CeilingHangingSignBlock COPPER_PINE_HANGING_SIGN = register(Keys.COPPER_PINE_HANGING_SIGN.identifier().getPath(), new AerialHellHangingSignBlock(AerialHellWoodTypes.COPPER_PINE, COPPER_PINE_SIGN_MATERIAL.setId(Keys.COPPER_PINE_HANGING_SIGN)));
    public static final WallHangingSignBlock COPPER_PINE_WALL_HANGING_SIGN = register(Keys.COPPER_PINE_WALL_HANGING_SIGN.identifier().getPath(), new AerialHellWallHangingSignBlock(AerialHellWoodTypes.COPPER_PINE, BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_HANGING_SIGN).setId(Keys.COPPER_PINE_WALL_HANGING_SIGN).overrideLootTable(COPPER_PINE_HANGING_SIGN.getLootTable())));
    public static final CeilingHangingSignBlock LAPIS_ROBINIA_HANGING_SIGN = register(Keys.LAPIS_ROBINIA_HANGING_SIGN.identifier().getPath(), new AerialHellHangingSignBlock(AerialHellWoodTypes.LAPIS_ROBINIA, COPPER_PINE_SIGN_MATERIAL.setId(Keys.LAPIS_ROBINIA_HANGING_SIGN)));
    public static final WallHangingSignBlock LAPIS_ROBINIA_WALL_HANGING_SIGN = register(Keys.LAPIS_ROBINIA_WALL_HANGING_SIGN.identifier().getPath(), new AerialHellWallHangingSignBlock(AerialHellWoodTypes.LAPIS_ROBINIA, BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_HANGING_SIGN).setId(Keys.LAPIS_ROBINIA_WALL_HANGING_SIGN).overrideLootTable(LAPIS_ROBINIA_HANGING_SIGN.getLootTable())));
    public static final CeilingHangingSignBlock SHADOW_PINE_HANGING_SIGN = register(Keys.SHADOW_PINE_HANGING_SIGN.identifier().getPath(), new AerialHellHangingSignBlock(AerialHellWoodTypes.SHADOW_PINE, SHADOW_PINE_SIGN_MATERIAL.setId(Keys.SHADOW_PINE_HANGING_SIGN)));
    public static final WallHangingSignBlock SHADOW_PINE_WALL_HANGING_SIGN = register(Keys.SHADOW_PINE_WALL_HANGING_SIGN.identifier().getPath(), new AerialHellWallHangingSignBlock(AerialHellWoodTypes.SHADOW_PINE, BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_HANGING_SIGN).setId(Keys.SHADOW_PINE_WALL_HANGING_SIGN).overrideLootTable(SHADOW_PINE_HANGING_SIGN.getLootTable())));
    public static final CeilingHangingSignBlock STELLAR_JUNGLE_TREE_HANGING_SIGN = register(Keys.STELLAR_JUNGLE_TREE_HANGING_SIGN.identifier().getPath(), new AerialHellHangingSignBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, SHADOW_PINE_SIGN_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_HANGING_SIGN)));
    public static final WallHangingSignBlock STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN = register(Keys.STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN.identifier().getPath(), new AerialHellWallHangingSignBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_HANGING_SIGN).setId(Keys.STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN).overrideLootTable(STELLAR_JUNGLE_TREE_HANGING_SIGN.getLootTable())));
    public static final CeilingHangingSignBlock SKY_CACTUS_FIBER_HANGING_SIGN = register(Keys.SKY_CACTUS_FIBER_HANGING_SIGN.identifier().getPath(), new AerialHellHangingSignBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, SKY_CACTUS_FIBER_SIGN_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_HANGING_SIGN)));
    public static final WallHangingSignBlock SKY_CACTUS_FIBER_WALL_HANGING_SIGN = register(Keys.SKY_CACTUS_FIBER_WALL_HANGING_SIGN.identifier().getPath(), new AerialHellWallHangingSignBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_HANGING_SIGN).setId(Keys.SKY_CACTUS_FIBER_WALL_HANGING_SIGN).overrideLootTable(SKY_CACTUS_FIBER_HANGING_SIGN.getLootTable())));
    public static final CeilingHangingSignBlock GRAY_SHROOM_HANGING_SIGN = register(Keys.GRAY_SHROOM_HANGING_SIGN.identifier().getPath(), new AerialHellHangingSignBlock(AerialHellWoodTypes.GRAY_SHROOM, SKY_CACTUS_FIBER_SIGN_MATERIAL.setId(Keys.GRAY_SHROOM_HANGING_SIGN)));
    public static final WallHangingSignBlock GRAY_SHROOM_WALL_HANGING_SIGN = register(Keys.GRAY_SHROOM_WALL_HANGING_SIGN.identifier().getPath(), new AerialHellWallHangingSignBlock(AerialHellWoodTypes.GRAY_SHROOM, BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_HANGING_SIGN).setId(Keys.GRAY_SHROOM_WALL_HANGING_SIGN).overrideLootTable(GRAY_SHROOM_HANGING_SIGN.getLootTable())));

    //crafting tables
    public static final CraftingTableBlock AERIAL_TREE_CRAFTING_TABLE = register(Keys.AERIAL_TREE_CRAFTING_TABLE.identifier().getPath(), new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_CRAFTING_TABLE)));
    public static final CraftingTableBlock GOLDEN_BEECH_CRAFTING_TABLE = register(Keys.GOLDEN_BEECH_CRAFTING_TABLE.identifier().getPath(), new AerialHellCraftingTableBlock(AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_CRAFTING_TABLE)));
    public static final CraftingTableBlock COPPER_PINE_CRAFTING_TABLE = register(Keys.COPPER_PINE_CRAFTING_TABLE.identifier().getPath(), new AerialHellCraftingTableBlock(COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_CRAFTING_TABLE)));
    public static final CraftingTableBlock LAPIS_ROBINIA_CRAFTING_TABLE = register(Keys.LAPIS_ROBINIA_CRAFTING_TABLE.identifier().getPath(), new AerialHellCraftingTableBlock(COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_CRAFTING_TABLE)));
    public static final CraftingTableBlock SHADOW_PINE_CRAFTING_TABLE = register(Keys.SHADOW_PINE_CRAFTING_TABLE.identifier().getPath(), new AerialHellCraftingTableBlock(SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_CRAFTING_TABLE)));
    public static final CraftingTableBlock STELLAR_JUNGLE_TREE_CRAFTING_TABLE = register(Keys.STELLAR_JUNGLE_TREE_CRAFTING_TABLE.identifier().getPath(), new AerialHellCraftingTableBlock(COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_CRAFTING_TABLE)));
    public static final CraftingTableBlock SKY_CACTUS_FIBER_CRAFTING_TABLE = register(Keys.SKY_CACTUS_FIBER_CRAFTING_TABLE.identifier().getPath(), new AerialHellCraftingTableBlock(SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_CRAFTING_TABLE)));
    public static final CraftingTableBlock GRAY_SHROOM_CRAFTING_TABLE = register(Keys.GRAY_SHROOM_CRAFTING_TABLE.identifier().getPath(), new AerialHellCraftingTableBlock(SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_CRAFTING_TABLE)));

    //barrels
    public static final AerialHellBarrelBlock AERIAL_TREE_BARREL = register(Keys.AERIAL_TREE_BARREL.identifier().getPath(), new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_BARREL)));
    public static final AerialHellBarrelBlock GOLDEN_BEECH_BARREL = register(Keys.GOLDEN_BEECH_BARREL.identifier().getPath(), new AerialHellBarrelBlock(AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_BARREL)));
    public static final AerialHellBarrelBlock COPPER_PINE_BARREL = register(Keys.COPPER_PINE_BARREL.identifier().getPath(), new AerialHellBarrelBlock(COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_BARREL)));
    public static final AerialHellBarrelBlock LAPIS_ROBINIA_BARREL = register(Keys.LAPIS_ROBINIA_BARREL.identifier().getPath(), new AerialHellBarrelBlock(COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_BARREL)));
    public static final AerialHellBarrelBlock SHADOW_PINE_BARREL = register(Keys.SHADOW_PINE_BARREL.identifier().getPath(), new AerialHellBarrelBlock(SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_BARREL)));
    public static final AerialHellBarrelBlock STELLAR_JUNGLE_TREE_BARREL = register(Keys.STELLAR_JUNGLE_TREE_BARREL.identifier().getPath(), new AerialHellBarrelBlock(COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_BARREL)));
    public static final AerialHellBarrelBlock SKY_CACTUS_FIBER_BARREL = register(Keys.SKY_CACTUS_FIBER_BARREL.identifier().getPath(), new AerialHellBarrelBlock(SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_BARREL)));
    public static final AerialHellBarrelBlock GRAY_SHROOM_BARREL = register(Keys.GRAY_SHROOM_BARREL.identifier().getPath(), new AerialHellBarrelBlock(SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_BARREL)));

    //composters
    public static final ComposterBlock AERIAL_TREE_COMPOSTER = register(Keys.AERIAL_TREE_COMPOSTER.identifier().getPath(), new ComposterBlock(AERIAL_TREE_MATERIAL.setId(Keys.AERIAL_TREE_COMPOSTER)));
    public static final ComposterBlock GOLDEN_BEECH_COMPOSTER = register(Keys.GOLDEN_BEECH_COMPOSTER.identifier().getPath(), new ComposterBlock(AERIAL_TREE_MATERIAL.setId(Keys.GOLDEN_BEECH_COMPOSTER)));
    public static final ComposterBlock COPPER_PINE_COMPOSTER = register(Keys.COPPER_PINE_COMPOSTER.identifier().getPath(), new ComposterBlock(COPPER_PINE_MATERIAL.setId(Keys.COPPER_PINE_COMPOSTER)));
    public static final ComposterBlock LAPIS_ROBINIA_COMPOSTER = register(Keys.LAPIS_ROBINIA_COMPOSTER.identifier().getPath(), new ComposterBlock(COPPER_PINE_MATERIAL.setId(Keys.LAPIS_ROBINIA_COMPOSTER)));
    public static final ComposterBlock SHADOW_PINE_COMPOSTER = register(Keys.SHADOW_PINE_COMPOSTER.identifier().getPath(), new ComposterBlock(SHADOW_PINE_MATERIAL.setId(Keys.SHADOW_PINE_COMPOSTER)));
    public static final ComposterBlock STELLAR_JUNGLE_TREE_COMPOSTER = register(Keys.STELLAR_JUNGLE_TREE_COMPOSTER.identifier().getPath(), new ComposterBlock(COPPER_PINE_MATERIAL.setId(Keys.STELLAR_JUNGLE_TREE_COMPOSTER)));
    public static final ComposterBlock SKY_CACTUS_FIBER_COMPOSTER = register(Keys.SKY_CACTUS_FIBER_COMPOSTER.identifier().getPath(), new ComposterBlock(SKY_CACTUS_FIBER_MATERIAL.setId(Keys.SKY_CACTUS_FIBER_COMPOSTER)));
    public static final ComposterBlock GRAY_SHROOM_COMPOSTER = register(Keys.GRAY_SHROOM_COMPOSTER.identifier().getPath(), new ComposterBlock(SHROOM_MATERIAL.setId(Keys.GRAY_SHROOM_COMPOSTER)));

    //decorative
    public static final RotatedPillarBlock AERIAL_TREE_VINE_ROPE_SPOOL = register(Keys.AERIAL_TREE_VINE_ROPE_SPOOL.identifier().getPath(), new VineRopeSpoolBlock(BlockBehaviour.Properties.of().setId(Keys.AERIAL_TREE_VINE_ROPE_SPOOL).noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
    public static final RotatedPillarBlock GOLDEN_BEECH_VINE_ROPE_SPOOL = register(Keys.GOLDEN_BEECH_VINE_ROPE_SPOOL.identifier().getPath(), new VineRopeSpoolBlock(BlockBehaviour.Properties.of().setId(Keys.GOLDEN_BEECH_VINE_ROPE_SPOOL).noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
    public static final RotatedPillarBlock COPPER_PINE_VINE_ROPE_SPOOL = register(Keys.COPPER_PINE_VINE_ROPE_SPOOL.identifier().getPath(), new VineRopeSpoolBlock(BlockBehaviour.Properties.of().setId(Keys.COPPER_PINE_VINE_ROPE_SPOOL).noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
    public static final RotatedPillarBlock LAPIS_ROBINIA_VINE_ROPE_SPOOL = register(Keys.LAPIS_ROBINIA_VINE_ROPE_SPOOL.identifier().getPath(), new VineRopeSpoolBlock(BlockBehaviour.Properties.of().setId(Keys.LAPIS_ROBINIA_VINE_ROPE_SPOOL).noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
    public static final RotatedPillarBlock SHADOW_PINE_VINE_ROPE_SPOOL = register(Keys.SHADOW_PINE_VINE_ROPE_SPOOL.identifier().getPath(), new VineRopeSpoolBlock(BlockBehaviour.Properties.of().setId(Keys.SHADOW_PINE_VINE_ROPE_SPOOL).noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
    public static final RotatedPillarBlock STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL = register(Keys.STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL.identifier().getPath(), new VineRopeSpoolBlock(BlockBehaviour.Properties.of().setId(Keys.STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL).noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
    public static final RotatedPillarBlock SKY_CACTUS_FIBER_VINE_ROPE_SPOOL = register(Keys.SKY_CACTUS_FIBER_VINE_ROPE_SPOOL.identifier().getPath(), new VineRopeSpoolBlock(BlockBehaviour.Properties.of().setId(Keys.SKY_CACTUS_FIBER_VINE_ROPE_SPOOL).noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
    public static final RotatedPillarBlock GRAY_SHROOM_VINE_ROPE_SPOOL = register(Keys.GRAY_SHROOM_VINE_ROPE_SPOOL.identifier().getPath(), new VineRopeSpoolBlock(BlockBehaviour.Properties.of().setId(Keys.GRAY_SHROOM_VINE_ROPE_SPOOL).noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));

    //fluids
    public static final LiquidBlock LIQUID_OF_THE_GODS = register(Keys.LIQUID_OF_THE_GODS.identifier().getPath(), new AerialHellFluidBlock(AerialHellFluids.LIQUID_OF_THE_GODS_STILL, BlockBehaviour.Properties.of().setId(Keys.LIQUID_OF_THE_GODS).replaceable().lightLevel((state) -> 8)));

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
            return ResourceKey.create(Registries.BLOCK, AerialHell.id(name));
        }
    }

    public static <T extends Block> T register(String name, T block) {return Registry.register(BuiltInRegistries.BLOCK, AerialHell.id(name), block);}

    public static void load() {}
}
