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
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
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
import net.minecraft.world.level.block.LeavesBlock;
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
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
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
    public static BlockBehaviour.Properties AERIAL_TREE_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion().noCollission();
    public static BlockBehaviour.Properties COPPER_PINE_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(4.5F, 4.5F).sound(SoundType.WOOD).noOcclusion().noCollission();
    public static BlockBehaviour.Properties SHADOW_PINE_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(4.0F, 4.0F).sound(SoundType.WOOD).noOcclusion().noCollission();
    public static BlockBehaviour.Properties SKY_CACTUS_FIBER_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.5F, 2.5F).sound(SoundType.WOOD);
    public static BlockBehaviour.Properties SKY_CACTUS_FIBER_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion().noCollission();
    public static BlockBehaviour.Properties SHROOM_SIGN_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(3.5F, 3.5F).sound(SoundType.WOOD).noOcclusion().noCollission();
    public static BlockBehaviour.Properties MUD_CHEST_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(20.0F, 1000.0F).sound(SoundType.STONE).requiresCorrectToolForDrops().noOcclusion();
    public static BlockBehaviour.Properties LUNATIC_CHEST_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_ORANGE).strength(30.0F, 1000.0F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion();
    public static BlockBehaviour.Properties VOLUCITE_CHEST_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(50.0F, 1200.0F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion();
    public static BlockBehaviour.Properties GOLDEN_NETHER_CHEST_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(50.0F, 1200.0F).sound(SoundType.BASALT).requiresCorrectToolForDrops().noOcclusion();
    public static BlockBehaviour.Properties METAL_NOTSOLID_MATERIAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(10.0F, 2.0F).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion();

    
    //portal
    public static final AerialHellPortalBlock AERIAL_HELL_PORTAL = register("aerial_hell_portal", new AerialHellPortalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_PORTAL).noOcclusion().isViewBlocking(( state, blockview, pos) -> false).lightLevel((state) -> 10).mapColor(DyeColor.GREEN)));
    public static final Block STELLAR_PORTAL_FRAME_BLOCK = register("stellar_portal_frame_block", new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).requiresCorrectToolForDrops().strength(25.0F, 600.0F)));
    public static final Block STELLAR_PORTAL_FRAME_ORE = register("stellar_portal_frame_ore", new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().strength(25.0F, 600.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block DEEPSLATE_STELLAR_PORTAL_FRAME_ORE = register("deepslate_stellar_portal_frame_ore", new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().strength(30.0F, 600.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));

    //torch
    public static final Block CRYSTALLIZED_TORCH = register("crystallized_torch", new AerialHellTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH)));
    public static final Block CRYSTALLIZED_WALL_TORCH = register("crystallized_wall_torch", new AerialHellWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).dropsLike(CRYSTALLIZED_TORCH)));
    public static final Block FLUORITE_TORCH = register("fluorite_torch", new AerialHellTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH)));
    public static final Block FLUORITE_WALL_TORCH = register("fluorite_wall_torch", new AerialHellWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).dropsLike(FLUORITE_TORCH)));
    public static final Block VOLUCITE_TORCH = register("volucite_torch", new AerialHellTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).lightLevel((state) -> {return 9;})));
    public static final Block VOLUCITE_WALL_TORCH = register("volucite_wall_torch", new AerialHellWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).dropsLike(VOLUCITE_TORCH).lightLevel((state) -> {return 9;})));
    public static final Block SHADOW_TORCH = register("shadow_torch", new AerialHellTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).lightLevel((state) -> {return 9;})));
    public static final Block SHADOW_WALL_TORCH = register("shadow_wall_torch", new AerialHellWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WALL_TORCH).dropsLike(SHADOW_TORCH).lightLevel((state) -> {return 9;})));

    //lanterns
    public static final Block CRYSTALLIZED_LANTERN = register("crystallized_lantern", new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Block FLUORITE_LANTERN = register("fluorite_lantern", new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Block RUBY_LANTERN = register("ruby_lantern", new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Block RUBY_CRYSTALLIZED_LANTERN = register("ruby_crystallized_lantern", new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Block RUBY_FLUORITE_LANTERN = register("ruby_fluorite_lantern", new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Block VOLUCITE_LANTERN = register("volucite_lantern", new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Block VOLUCITE_CRYSTALLIZED_LANTERN = register("volucite_crystallized_lantern", new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Block VOLUCITE_FLUORITE_LANTERN = register("volucite_fluorite_lantern", new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Block LUNATIC_LANTERN = register("lunatic_lantern", new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));
    public static final Block SHADOW_LANTERN = register("shadow_lantern", new LanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_LANTERN)));

    //chains
    public static final ChainBlock RUBY_CHAIN = register("ruby_chain", new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
    public static final ChainBlock VOLUCITE_CHAIN = register("volucite_chain", new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
    public static final ChainBlock LUNATIC_CHAIN = register("lunatic_chain", new ChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));
    public static final ChainBlock SHADOW_CHAIN = register("shadow_chain", new ShadowChainBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHAIN)));

    //grass & dirt
    public static final StellarGrassBlock STELLAR_GRASS_BLOCK = register("stellar_grass_block", new StellarGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)));
    public static final Block CHISELED_STELLAR_GRASS_BLOCK = register("chiseled_stellar_grass_block", new StellarGrassBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_GRASS_BLOCK)));
    public static final Block STELLAR_DIRT = register("stellar_dirt", new StellarDirtBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)));
    public static final Block STELLAR_COARSE_DIRT = register("stellar_coarse_dirt", new StellarDirtBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COARSE_DIRT)));
    public static final Block STELLAR_FARMLAND = register("stellar_farmland", new StellarFarmBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).randomTicks().strength(0.6F).sound(SoundType.GRAVEL).isViewBlocking((state, blockgetter, pos) -> true).isSuffocating((state, blockgetter, pos) -> true)));
    public static final Block STELLAR_DIRT_PATH = register("stellar_dirt_path", new StellarDirtPathBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT_PATH)));
    public static final Block STELLAR_PODZOL = register("stellar_podzol", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL)));
    public static final Block STELLAR_CRYSTAL_PODZOL = register("stellar_crystal_podzol", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL)));
    public static final Block CHISELED_STELLAR_DIRT = register("chiseled_stellar_dirt", new StellarDirtBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_DIRT)));
    public static final ShadowGrassBlock SHADOW_GRASS_BLOCK = register("shadow_grass_block", new ShadowGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)));

    //slippery sand
    public static final Block SLIPPERY_SAND = register("slippery_sand", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SAND).friction(1.025F)));
    public static final Block SLIPPERY_SAND_STONE = register("slippery_sand_stone", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).friction(1.01F)));
    public static final Block SLIPPERY_SAND_STONE_BRICKS = register("slippery_sand_stone_bricks", new Block(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE).friction(1.005F)));
    public static final Block CUT_SLIPPERY_SAND_STONE = register("cut_slippery_sand_stone", new Block(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE).friction(1.005F)));
    public static final Block CRACKED_SLIPPERY_SAND_STONE_BRICKS = register("cracked_slippery_sand_stone_bricks", new Block(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE).friction(1.003F)));

    //giant root
    public static final RotatedPillarBlock GIANT_ROOT = register("giant_root", new RotatedPillarBlock(AERIAL_TREE_MATERIAL));

    //aerial_tree
    public static final ShiftableLogBlock AERIAL_TREE_LOG = register("aerial_tree_log", new ShiftableLogBlock(AERIAL_TREE_MATERIAL, () -> AerialHellBlocks.SHADOW_AERIAL_TREE_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final RotatedPillarBlock STRIPPED_AERIAL_TREE_LOG = register("stripped_aerial_tree_log", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG)));
    public static final RotatedPillarBlock AERIAL_TREE_WOOD = register("aerial_tree_wood", new RotatedPillarBlock(AERIAL_TREE_MATERIAL));
    public static final RotatedPillarBlock STRIPPED_AERIAL_TREE_WOOD = register("stripped_aerial_tree_wood", new RotatedPillarBlock(AERIAL_TREE_MATERIAL));
    public static final ShiftableLeavesBlock AERIAL_TREE_LEAVES = register("aerial_tree_leaves", new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.SHADOW_AERIAL_TREE_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block AERIAL_TREE_PLANKS = register("aerial_tree_planks", new Block(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG)));
    public static final Block CHISELED_AERIAL_TREE_PLANKS = register("chiseled_aerial_tree_planks", new Block(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS)));
    public static final Block AERIAL_TREE_BOOKSHELF = register("aerial_tree_bookshelf", new Block(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS)));
    public static final SaplingBlock AERIAL_TREE_SAPLING = register("aerial_tree_sapling", new AerialHellSaplingBlock(AerialHellTreeGrowers.AERIAL_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_AERIAL_TREE));

    //petrified aerial tree
    public static final Block PETRIFIED_AERIAL_TREE_LOG = register("petrified_aerial_tree_log", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG)));

    //golden beech
    public static final ShiftableLogBlock GOLDEN_BEECH_LOG = register("golden_beech_log", new ShiftableLogBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG), () -> AerialHellBlocks.SHADOW_GOLDEN_BEECH_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final Block STRIPPED_GOLDEN_BEECH_LOG = register("stripped_golden_beech_log", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG)));
    public static final Block GOLDEN_BEECH_WOOD = register("golden_beech_wood", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG)));
    public static final Block STRIPPED_GOLDEN_BEECH_WOOD = register("stripped_golden_beech_wood", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG)));
    public static final Block GOLDEN_BEECH_PLANKS = register("golden_beech_planks", new Block(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG)));
    public static final Block CHISELED_GOLDEN_BEECH_PLANKS = register("chiseled_golden_beech_planks", new Block(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS)));
    public static final ShiftableLeavesBlock GOLDEN_BEECH_LEAVES = register("golden_beech_leaves", new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.SHADOW_GOLDEN_BEECH_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block GOLDEN_BEECH_BOOKSHELF = register("golden_beech_bookshelf", new Block(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS)));
    public static final Block GOLDEN_BEECH_SAPLING = register("golden_beech_sapling", new SaplingBlock(AerialHellTreeGrowers.GOLDEN_BEECH, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    //copper pine
    public static final ShiftableLogBlock COPPER_PINE_LOG = register("copper_pine_log", new ShiftableLogBlock(COPPER_PINE_MATERIAL, () -> AerialHellBlocks.SHADOW_COPPER_PINE_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final RotatedPillarBlock STRIPPED_COPPER_PINE_LOG = register("stripped_copper_pine_log", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG)));
    public static final RotatedPillarBlock COPPER_PINE_WOOD = register("copper_pine_wood", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG)));
    public static final RotatedPillarBlock STRIPPED_COPPER_PINE_WOOD = register("stripped_copper_pine_wood", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG)));
    public static final Block COPPER_PINE_PLANKS = register("copper_pine_planks", new Block(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG)));
    public static final ShiftableLeavesBlock COPPER_PINE_LEAVES = register("copper_pine_leaves", new LeavesWithAmbientParticlesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.SHADOW_COPPER_PINE_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block COPPER_PINE_BOOKSHELF = register("copper_pine_bookshelf", new Block(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_PLANKS)));
    public static final SaplingBlock COPPER_PINE_SAPLING = register("copper_pine_sapling", new AerialHellSaplingBlock(AerialHellTreeGrowers.COPPER_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_COPPER_PINE, AerialHellConfiguredFeatures.HUGE_COPPER_PINE, 0.1F));

    //lapis robinia
    public static final ShiftableLogBlock LAPIS_ROBINIA_LOG = register("lapis_robinia_log", new ShiftableLogBlock(COPPER_PINE_MATERIAL, () -> AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final EffectLogBlock ENCHANTED_LAPIS_ROBINIA_LOG = register("enchanted_lapis_robinia_log", new EffectLogBlock(COPPER_PINE_MATERIAL, () -> AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final RotatedPillarBlock STRIPPED_LAPIS_ROBINIA_LOG = register("stripped_lapis_robinia_log", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG)));
    public static final RotatedPillarBlock LAPIS_ROBINIA_WOOD = register("lapis_robinia_wood", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG)));
    public static final RotatedPillarBlock STRIPPED_LAPIS_ROBINIA_WOOD = register("stripped_lapis_robinia_wood", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG)));
    public static final ShiftableLeavesBlock LAPIS_ROBINIA_LEAVES = register("lapis_robinia_leaves", new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.SHADOW_LAPIS_ROBINIA_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block LAPIS_ROBINIA_PLANKS = register("lapis_robinia_planks", new Block(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG)));
    public static final Block LAPIS_ROBINIA_BOOKSHELF = register("lapis_robinia_bookshelf", new Block(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_PLANKS)));
    public static final SaplingBlock LAPIS_ROBINIA_SAPLING = register("lapis_robinia_sapling", new AerialHellSaplingBlock(AerialHellTreeGrowers.LAPIS_ROBINIA, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_LAPIS_ROBINIA));

    //shadow_pine
    public static final ShiftableLogBlock SHADOW_PINE_LOG = register("shadow_pine_log", new ShadowLogBlock(SHADOW_PINE_MATERIAL, () -> AerialHellBlocks.HOLLOW_SHADOW_PINE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLogBlock EYE_SHADOW_PINE_LOG = register("eye_shadow_pine_log", new ShadowEffectLogBlock(SHADOW_PINE_MATERIAL, () -> AerialHellBlocks.HOLLOW_SHADOW_PINE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final RotatedPillarBlock STRIPPED_SHADOW_PINE_LOG = register("stripped_shadow_pine_log", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG)));
    public static final RotatedPillarBlock SHADOW_PINE_WOOD = register("shadow_pine_wood", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG)));
    public static final RotatedPillarBlock STRIPPED_SHADOW_PINE_WOOD = register("stripped_shadow_pine_wood", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG)));
    public static final ShiftableLeavesBlock SHADOW_PINE_LEAVES = register("shadow_pine_leaves", new ShadowLeavesWithParticlesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.HOLLOW_SHADOW_PINE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock PURPLE_SHADOW_PINE_LEAVES = register("purple_shadow_pine_leaves", new ShadowLeavesWithParticlesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.HOLLOW_PURPLE_SHADOW_PINE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final Block SHADOW_PINE_PLANKS = register("shadow_pine_planks", new Block(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LOG)));
    public static final Block SHADOW_PINE_BOOKSHELF = register("shadow_pine_bookshelf", new Block(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_PLANKS)));
    public static final SaplingBlock SHADOW_PINE_SAPLING = register("shadow_pine_sapling", new ShadowPineSaplingBlock(AerialHellTreeGrowers.SHADOW_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_SHADOW_PINE, AerialHellConfiguredFeatures.HUGE_SHADOW_PINE, 0.1F));
    public static final SaplingBlock PURPLE_SHADOW_PINE_SAPLING = register("purple_shadow_pine_sapling", new ShadowPineSaplingBlock(AerialHellTreeGrowers.PURPLE_SHADOW_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_PURPLE_SHADOW_PINE, AerialHellConfiguredFeatures.HUGE_PURPLE_SHADOW_PINE, 0.1F));

    //stellar jungle tree
    public static final ShiftableLogBlock STELLAR_JUNGLE_TREE_LOG = register("stellar_jungle_tree_log", new ShiftableLogBlock(COPPER_PINE_MATERIAL, () -> AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final RotatedPillarBlock STRIPPED_STELLAR_JUNGLE_TREE_LOG = register("stripped_stellar_jungle_tree_log", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG)));
    public static final RotatedPillarBlock STELLAR_JUNGLE_TREE_WOOD = register("stellar_jungle_tree_wood", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG)));
    public static final RotatedPillarBlock STRIPPED_STELLAR_JUNGLE_TREE_WOOD = register("stripped_stellar_jungle_tree_wood", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG)));
    public static final ShiftableLeavesBlock STELLAR_JUNGLE_TREE_LEAVES = register("stellar_jungle_tree_leaves", new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), () -> AerialHellBlocks.SHADOW_STELLAR_JUNGLE_TREE_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final Block STELLAR_JUNGLE_TREE_PLANKS = register("stellar_jungle_tree_planks", new Block(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG)));
    public static final Block STELLAR_JUNGLE_TREE_BOOKSHELF = register("stellar_jungle_tree_bookshelf", new Block(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_PLANKS)));
    public static final SaplingBlock STELLAR_JUNGLE_TREE_SAPLING = register("stellar_jungle_tree_sapling", new AerialHellSaplingBlock(AerialHellTreeGrowers.STELLAR_JUNGLE_TREE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), AerialHellConfiguredFeatures.GIANT_STELLAR_JUNGLE_TREE));
    public static final LargeDeadLogBlock DEAD_STELLAR_JUNGLE_TREE_LOG = register("dead_stellar_jungle_tree_log", new LargeDeadLogBlock(STELLAR_JUNGLE_TREE_PLANKS.defaultBlockState(), COPPER_PINE_MATERIAL));

    //shroom
    public static final RotatedPillarBlock GIANT_CORTINARIUS_VIOLACEUS_STEM = register("giant_cortinarius_violaceus_stem", new RotatedPillarBlock(SHROOM_MATERIAL));
    public static final RotatedPillarBlock STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_STEM = register("stripped_giant_cortinarius_violaceus_stem", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM)));
    public static final RotatedPillarBlock GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = register("giant_cortinarius_violaceus_bark_stem", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM)));
    public static final RotatedPillarBlock STRIPPED_GIANT_CORTINARIUS_VIOLACEUS_BARK_STEM = register("stripped_giant_cortinarius_violaceus_bark_stem", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM)));
    public static final Block GIANT_CORTINARIUS_VIOLACEUS_CAP_BLOCK = register("giant_cortinarius_violaceus_cap_block", new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).strength(0.5F).sound(SoundType.HARD_CROP)));
    public static final Block GIANT_CORTINARIUS_VIOLACEUS_LIGHT = register("giant_cortinarius_violaceus_light", new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(1.0F).sound(SoundType.SHROOMLIGHT).lightLevel((state) -> {return 15;})));
    public static final FungusBlock CORTINARIUS_VIOLACEUS = register("cortinarius_violaceus", new AerialHellFungusBlock(AerialHellConfiguredFeatures.GIANT_CORTINARIUS_VIOLACEUS_PLANTED, STELLAR_GRASS_BLOCK, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FUNGUS)));
    public static final Block GLOWING_BOLETUS = register("glowing_boletus", new AerialHellTallShroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noCollission().lightLevel((state) -> {return 9;}).instabreak().sound(SoundType.GLOW_LICHEN), true));
    public static final Block TALL_GLOWING_BOLETUS = register("tall_glowing_boletus", new DoubleShroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noCollission().lightLevel((state) -> {return 11;}).instabreak().sound(SoundType.GLOW_LICHEN)));
    public static final Block BLUE_MEANIE_CLUSTER = register("blue_meanie_cluster", new TallShroomBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)));
    public static final Block GIANT_ROOT_SHROOM = register("giant_root_shroom", new AerialHellTallShroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noCollission().instabreak().sound(SoundType.NETHER_WART), false));

    public static final RotatedPillarBlock GIANT_VERDIGRIS_AGARIC_STEM = register("giant_verdigris_agaric_stem", new RotatedPillarBlock(SHROOM_MATERIAL));
    public static final RotatedPillarBlock STRIPPED_GIANT_VERDIGRIS_AGARIC_STEM = register("stripped_giant_verdigris_agaric_stem", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM)));
    public static final RotatedPillarBlock GIANT_VERDIGRIS_AGARIC_BARK_STEM = register("giant_verdigris_agaric_bark_stem", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM)));
    public static final RotatedPillarBlock STRIPPED_GIANT_VERDIGRIS_AGARIC_BARK_STEM = register("stripped_giant_verdigris_agaric_bark_stem", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM)));
    public static final Block GIANT_VERDIGRIS_AGARIC_CAP_BLOCK = register("giant_verdigris_agaric_cap_block", new HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BLUE).lightLevel((state) -> {return 10;}).strength(0.4F).sound(SoundType.HARD_CROP)));
    public static final MushroomBlock VERDIGRIS_AGARIC = register("verdigris_agaric", new AerialHellMushroomBlock(AerialHellConfiguredFeatures.GIANT_VERDIGRIS_AGARIC, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));

    public static final Block GIANT_GANODERMA_APPLANATUM_BLOCK = register("giant_ganoderma_applanatum_block", new HugeMushroomBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(0.4F).sound(SoundType.HARD_CROP)));

    public static final Block GRAY_SHROOM_PLANKS = register("gray_shroom_planks", new Block(BlockBehaviour.Properties.ofFullCopy(GIANT_CORTINARIUS_VIOLACEUS_STEM)));
    public static final Block GRAY_SHROOM_BOOKSHELF = register("gray_shroom_bookshelf", new Block(BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_PLANKS)));

    //shadow corrupted / uncorrupted variants
    public static final ShadowLogBlock SHADOW_AERIAL_TREE_LOG = register("shadow_aerial_tree_log", new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG), () -> AERIAL_TREE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShadowLogBlock SHADOW_GOLDEN_BEECH_LOG = register("shadow_golden_beech_log", new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LOG), () -> GOLDEN_BEECH_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShadowLogBlock SHADOW_COPPER_PINE_LOG = register("shadow_copper_pine_log", new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LOG), () -> COPPER_PINE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShadowLogBlock SHADOW_LAPIS_ROBINIA_LOG = register("shadow_lapis_robinia_log", new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LOG), () -> LAPIS_ROBINIA_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShadowLogBlock SHADOW_STELLAR_JUNGLE_TREE_LOG = register("shadow_stellar_jungle_tree_log", new ShadowLogBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LOG), () -> STELLAR_JUNGLE_TREE_LOG, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLogBlock HOLLOW_SHADOW_PINE_LOG = register("hollow_shadow_pine_log", new ShiftableLogBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG), () -> SHADOW_PINE_LOG, BiomeShifter.ShiftType.CORRUPT));
    public static final ShiftableLeavesBlock SHADOW_AERIAL_TREE_LEAVES = register("shadow_aerial_tree_leaves", new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LEAVES), () -> AerialHellBlocks.AERIAL_TREE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock SHADOW_GOLDEN_BEECH_LEAVES = register("shadow_golden_beech_leaves", new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_LEAVES), () -> AerialHellBlocks.GOLDEN_BEECH_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock SHADOW_COPPER_PINE_LEAVES = register("shadow_copper_pine_leaves", new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_LEAVES), () -> AerialHellBlocks.COPPER_PINE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock SHADOW_LAPIS_ROBINIA_LEAVES = register("shadow_lapis_robinia_leaves", new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_LEAVES), () -> AerialHellBlocks.LAPIS_ROBINIA_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock SHADOW_STELLAR_JUNGLE_TREE_LEAVES = register("shadow_stellar_jungle_tree_leaves", new ShadowLeavesBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_LEAVES), () -> AerialHellBlocks.STELLAR_JUNGLE_TREE_LEAVES, BiomeShifter.ShiftType.UNCORRUPT));
    public static final ShiftableLeavesBlock HOLLOW_SHADOW_PINE_LEAVES = register("hollow_shadow_pine_leaves", new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_LEAVES), () -> AerialHellBlocks.SHADOW_PINE_LEAVES, BiomeShifter.ShiftType.CORRUPT));
    public static final ShiftableLeavesBlock HOLLOW_PURPLE_SHADOW_PINE_LEAVES = register("hollow_purple_shadow_pine_leaves", new ShiftableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(PURPLE_SHADOW_PINE_LEAVES), () -> AerialHellBlocks.PURPLE_SHADOW_PINE_LEAVES, BiomeShifter.ShiftType.CORRUPT));

    //ladder
    public static final LadderBlock SKY_LADDER = register("sky_ladder", new LadderBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS).noOcclusion()));

    //natural blocks and items
    public static final Block STELLAR_STONE = register("stellar_stone", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));
    public static final Block STELLAR_COBBLESTONE = register("stellar_cobblestone", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE)));
    public static final Block STELLAR_STONE_BRICKS = register("stellar_stone_bricks", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).strength(0.5F, 10.0F)));
    public static final Block MOSSY_STELLAR_STONE = register("mossy_stellar_stone", new Block(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE)));
    public static final Block MOSSY_STELLAR_COBBLESTONE = register("mossy_stellar_cobblestone", new Block(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE)));
    public static final Block STELLAR_CLAY = register("stellar_clay", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CLAY)));
    public static final Block GLAUCOPHANITE = register("glaucophanite", new Block(BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block POLISHED_GLAUCOPHANITE = register("polished_glaucophanite", new Block(BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block SHADOW_STONE = register("shadow_stone", new ShadowStoneBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)));

    //crystal
    public static final Block CRYSTAL_BLOCK = register("crystal_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).lightLevel((state) -> 14)));
    public static final Block CRYSTAL_BRICKS = register("crystal_bricks", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).lightLevel((state) -> 9)));
    public static final Block CRYSTAL_BRICKS_SLAB = register("crystal_bricks_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BRICKS).noOcclusion()));
    public static final Block CRYSTAL_BRICKS_STAIRS = register("crystal_bricks_stairs", new StairBlock(CRYSTAL_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BRICKS)));
    public static final Block CRYSTAL_BRICKS_WALL = register("crystal_bricks_wall", new WallBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BRICKS)));
    public static final Block STELLAR_STONE_CRYSTAL_BLOCK = register("stellar_stone_crystal_block", new BasicShiftableRenderBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BLOCK).lightLevel((state) -> 13)));
    public static final Block SHADOW_CRYSTAL_BLOCK = register("shadow_crystal_block", new BasicShadowSpreaderBlock(BlockBehaviour.Properties.ofFullCopy(CRYSTAL_BLOCK).lightLevel((state) -> 12)));
    public static final Block CRYSTALLIZED_LEAVES = register("crystallized_leaves", new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).lightLevel((state) -> 12)));
    public static final Block CRYSTALLIZED_FIRE = register("crystallized_fire", new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).lightLevel((state) -> 12).instabreak()));

    //glass and glass pane
    public static final Block SLIPPERY_SAND_GLASS = register("slippery_sand_glass", new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final Block RED_SLIPPERY_SAND_GLASS = register("red_slippery_sand_glass", new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final Block BLACK_SLIPPERY_SAND_GLASS = register("black_slippery_sand_glass", new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final Block BLUE_SLIPPERY_SAND_GLASS = register("blue_slippery_sand_glass", new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final Block GREEN_SLIPPERY_SAND_GLASS = register("green_slippery_sand_glass", new TransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).friction(1.01F).isValidSpawn((state, reader, pos, entity) -> false).isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false)));
    public static final Block SLIPPERY_SAND_GLASS_PANE = register("slippery_sand_glass_pane", new StainedGlassPaneBlock(DyeColor.YELLOW, BlockBehaviour.Properties.of().friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
    public static final Block RED_SLIPPERY_SAND_GLASS_PANE = register("red_slippery_sand_glass_pane", new StainedGlassPaneBlock(DyeColor.RED, BlockBehaviour.Properties.of().friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
    public static final Block BLACK_SLIPPERY_SAND_GLASS_PANE = register("black_slippery_sand_glass_pane", new StainedGlassPaneBlock(DyeColor.BLACK, BlockBehaviour.Properties.of().friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
    public static final Block BLUE_SLIPPERY_SAND_GLASS_PANE = register("blue_slippery_sand_glass_pane", new StainedGlassPaneBlock(DyeColor.BLUE, BlockBehaviour.Properties.of().friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));
    public static final Block GREEN_SLIPPERY_SAND_GLASS_PANE = register("green_slippery_sand_glass_pane", new StainedGlassPaneBlock(DyeColor.GREEN, BlockBehaviour.Properties.of().friction(1.01F).strength(0.3F).sound(SoundType.GLASS).noOcclusion()));

    //ghost boat
    public static final Block GHOST_BOAT_PLANKS = register("ghost_boat_planks", new GhostBoatBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final GhostBoatRotatedPillarBlock GHOST_BOAT_LOG = register("ghost_boat_log", new GhostBoatRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_LOG).noOcclusion()));
    public static final GhostBoatRotatedPillarBlock GHOST_BOAT_WOOD = register("ghost_boat_wood", new GhostBoatRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(GHOST_BOAT_LOG).noOcclusion()));
    public static final SlabBlock GHOST_BOAT_SLAB = register("ghost_boat_slab", new GhostBoatSlabBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final StairBlock GHOST_BOAT_STAIRS = register("ghost_boat_stairs", new GhostBoatStairBlock(GHOST_BOAT_PLANKS.defaultBlockState(), BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final FenceBlock GHOST_BOAT_FENCE = register("ghost_boat_fence", new GhostBoatFenceBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final FenceGateBlock GHOST_BOAT_GATE = register("ghost_boat_gate", new GhostBoatFenceGateBlock(AerialHellWoodTypes.AERIAL_TREE, BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final DoorBlock GHOST_BOAT_DOOR = register("ghost_boat_door", new GhostBoatDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final TrapDoorBlock GHOST_BOAT_TRAPDOOR = register("ghost_boat_trapdoor", new GhostBoatTrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final ChestBlock GHOST_BOAT_CHEST = register("ghost_boat_chest", new GhostBoatChestBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final Block GHOST_BOAT_WOOL = register("ghost_boat_wool", new GhostBoatBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).instrument(NoteBlockInstrument.GUITAR).strength(0.8F).sound(SoundType.WOOL).noOcclusion()));
    public static final Block GHOST_STELLAR_COBBLESTONE = register("ghost_stellar_cobblestone", new GhostBoatBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).requiresCorrectToolForDrops().sound(SoundType.STONE).noOcclusion()));
    public static final Block GHOST_RUBY_BLOCK = register("ghost_ruby_block", new GhostBoatBlock(BlockBehaviour.Properties.of().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));
    public static final Block GHOST_FLUORITE_BLOCK = register("ghost_fluorite_block", new GhostBoatBlock(BlockBehaviour.Properties.of().strength(5.0F, 6.0F).sound(SoundType.METAL).noOcclusion()));
    public static final Block GHOST_AZURITE_BLOCK = register("ghost_azurite_block", new GhostBoatBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final Block GHOST_GOLD_BLOCK = register("ghost_gold_block", new GhostBoatBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GOLD_BLOCK).noOcclusion()));
    public static final AerialHellBarrelBlock GHOST_BOAT_BARREL = register("ghost_boat_barrel", new GhostBoatBarrelBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final CraftingTableBlock GHOST_BOAT_CRAFTING_TABLE = register("ghost_boat_crafting_table", new GhostBoatCraftingTableBlock(BlockBehaviour.Properties.of().strength(2.5F, 2.5F).sound(SoundType.WOOD).noOcclusion()));
    public static final RotatedPillarBlock GHOST_BOAT_VINE_ROPE_SPOOL = register("ghost_boat_vine_rope_spool", new GhostBoatVineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
    public static final Block GHOST_LANTERN = register("ghost_lantern", new GhostLanternBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN)));

    //other condition condition blocks
    public static final Block INTANGIBLE_TEMPORARY_BLOCK = register("intangible_temporary_block", new IntangibleTemporaryBlock(BlockBehaviour.Properties.of().strength(2.0F, 3600000.0F).noLootTable().pushReaction(PushReaction.IGNORE).sound(SoundType.GLASS).lightLevel((state) -> 7).noOcclusion()));

    //reactors
    public static final Block WEAK_LIGHT_REACTOR = register("weak_light_reactor", new ReactorBlock(BlockBehaviour.Properties.of().strength(5.0F, 100.0F).pushReaction(PushReaction.IGNORE).sound(SoundType.STONE).noOcclusion(), 32, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.BROKEN_WEAK_LIGHT_REACTOR));
    public static final Block HIGH_POWER_LIGHT_REACTOR = register("high_power_light_reactor", new ReactorBlock(BlockBehaviour.Properties.of().strength(5.0F, 100.0F).pushReaction(PushReaction.IGNORE).sound(SoundType.STONE).noOcclusion(), 58, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.BROKEN_HIGH_POWER_LIGHT_REACTOR));
    public static final Block WEAK_SHADOW_REACTOR = register("weak_shadow_reactor", new ReactorBlock(BlockBehaviour.Properties.of().strength(5.0F, 100.0F).pushReaction(PushReaction.IGNORE).sound(SoundType.STONE).noOcclusion(), 26, BiomeShifter.ShiftType.CORRUPT, () -> AerialHellBlocks.BROKEN_WEAK_SHADOW_REACTOR));
    public static final Block HIGH_POWER_SHADOW_REACTOR = register("high_power_shadow_reactor", new ReactorBlock(BlockBehaviour.Properties.of().strength(5.0F, 100.0F).pushReaction(PushReaction.IGNORE).sound(SoundType.STONE).noOcclusion(), 60, BiomeShifter.ShiftType.CORRUPT, () -> AerialHellBlocks.BROKEN_HIGH_POWER_SHADOW_REACTOR));

    public static final Block BROKEN_WEAK_LIGHT_REACTOR = register("broken_weak_light_reactor", new Block(BlockBehaviour.Properties.ofFullCopy(WEAK_LIGHT_REACTOR)));
    public static final Block BROKEN_HIGH_POWER_LIGHT_REACTOR = register("broken_high_power_light_reactor", new Block(BlockBehaviour.Properties.ofFullCopy(HIGH_POWER_LIGHT_REACTOR)));
    public static final Block BROKEN_WEAK_SHADOW_REACTOR = register("broken_weak_shadow_reactor", new Block(BlockBehaviour.Properties.ofFullCopy(WEAK_SHADOW_REACTOR)));
    public static final Block BROKEN_HIGH_POWER_SHADOW_REACTOR = register("broken_high_power_shadow_reactor", new Block(BlockBehaviour.Properties.ofFullCopy(HIGH_POWER_SHADOW_REACTOR)));

    //solid_ethers
    public static final Block WHITE_SOLID_ETHER = register("white_solid_ether", new SolidEtherBlock(BlockBehaviour.Properties.of().strength(0.2F).sound(SoundType.WOOL).noOcclusion()));
    public static final Block BLUE_SOLID_ETHER = register("blue_solid_ether", new BlueSolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER)));
    public static final Block GOLDEN_SOLID_ETHER = register("golden_solid_ether", new SolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER)));
    public static final Block GREEN_SOLID_ETHER = register("green_solid_ether", new GreenSolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER)));
    public static final Block PURPLE_SOLID_ETHER = register("purple_solid_ether", new PurpleSolidEtherBlock(BlockBehaviour.Properties.ofFullCopy(WHITE_SOLID_ETHER)));

    //dungeons blocks
    public static final Block MUD_BRICKS = register("mud_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block CRACKED_MUD_BRICKS = register("cracked_mud_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS)));
    public static final Block MOSSY_MUD_BRICKS = register("mossy_mud_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS)));
    public static final Block CHISELED_MUD_BRICKS = register("chiseled_mud_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS)));
    public static final Block LIGHT_MUD_BRICKS = register("light_mud_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS).lightLevel((state) -> 11)));
    public static final Block CRACKED_LIGHT_MUD_BRICKS = register("cracked_light_mud_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS)));
    public static final Block LUNATIC_STONE = register("lunatic_stone", new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block DARK_LUNATIC_STONE = register("dark_lunatic_stone", new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block ROOF_LUNATIC_STONE = register("roof_lunatic_stone", new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block CRACKED_LUNATIC_STONE = register("cracked_lunatic_stone", new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block CHISELED_LUNATIC_STONE = register("chiseled_lunatic_stone", new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block LIGHT_LUNATIC_STONE = register("light_lunatic_stone", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE).lightLevel((state) -> 11)));
    public static final Block ROOF_LIGHT_LUNATIC_STONE = register("roof_light_lunatic_stone", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE).lightLevel((state) -> 11)));
    public static final Block CRACKED_LIGHT_LUNATIC_STONE = register("cracked_light_lunatic_stone", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE)));
    public static final Block SHADOW_CATACOMBS_BRICKS = register("shadow_catacombs_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block CRACKED_SHADOW_CATACOMBS_BRICKS = register("cracked_shadow_catacombs_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS)));
    public static final Block MOSSY_SHADOW_CATACOMBS_BRICKS = register("mossy_shadow_catacombs_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS)));
    public static final Block CHISELED_SHADOW_CATACOMBS_BRICKS = register("chiseled_shadow_catacombs_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS)));
    public static final Block BONE_SHADOW_CATACOMBS_BRICKS = register("bone_shadow_catacombs_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS).sound(SoundType.BONE_BLOCK)));
    public static final Block SKULL_SHADOW_CATACOMBS_BRICKS = register("skull_shadow_catacombs_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS).sound(SoundType.BONE_BLOCK)));
    public static final Block LIGHT_SHADOW_CATACOMBS_BRICKS = register("light_shadow_catacombs_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS).lightLevel((state) -> 11)));
    public static final Block CRACKED_LIGHT_SHADOW_CATACOMBS_BRICKS = register("cracked_light_shadow_catacombs_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS)));
    public static final Block GOLDEN_NETHER_BRICKS  = register("golden_nether_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block CRACKED_GOLDEN_NETHER_BRICKS  = register("cracked_golden_nether_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block CHISELED_GOLDEN_NETHER_BRICKS  = register("chiseled_golden_nether_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block LIGHT_GOLDEN_NETHER_BRICKS = register("light_golden_nether_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS).lightLevel((state) -> 11)));
    public static final Block CRACKED_LIGHT_GOLDEN_NETHER_BRICKS = register("cracked_light_golden_nether_bricks", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS)));
    public static final RotatedPillarBlock LUNATIC_PILLAR = register("lunatic_pillar", new CoreProtectedRotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).strength(2.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final RotatedPillarBlock LUNATIC_PILLAR_TOP = register("lunatic_pillar_top", new CoreProtectedRotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).strength(2.0F, 6.0F).sound(SoundType.METAL).requiresCorrectToolForDrops()));
    public static final Block VOLUCITE_STONE = register("volucite_stone", new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE)));
    public static final Block CRACKED_VOLUCITE_STONE = register("cracked_volucite_stone", new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE)));
    public static final Block CHISELED_VOLUCITE_STONE = register("chiseled_volucite_stone", new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE)));
    public static final Block LIGHT_VOLUCITE_STONE = register("light_volucite_stone", new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE).lightLevel((state) -> 11)));
    public static final Block CRACKED_LIGHT_VOLUCITE_STONE = register("cracked_light_volucite_stone", new CoreProtectedBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE)));

    //dungeon cores
    public static final Block MUD_DUNGEON_CORE = register("mud_dungeon_core", new DungeonCoreBlock(BlockBehaviour.Properties.of().strength(30.0F, 1200.0F).pushReaction(PushReaction.BLOCK).sound(SoundType.STONE).requiresCorrectToolForDrops(), 181));
    public static final Block LUNATIC_DUNGEON_CORE = register("lunatic_dungeon_core", new DungeonCoreBlock(BlockBehaviour.Properties.of().strength(40.0F, 1200.0F).pushReaction(PushReaction.BLOCK).sound(SoundType.STONE).requiresCorrectToolForDrops(), 181));
    public static final Block SHADOW_CATACOMBS_DUNGEON_CORE = register("shadow_catacombs_dungeon_core", new DungeonCoreBlock(BlockBehaviour.Properties.of().strength(30.0F, 1200.0F).pushReaction(PushReaction.BLOCK).sound(SoundType.STONE).requiresCorrectToolForDrops(), 181));
    public static final Block GOLDEN_NETHER_DUNGEON_CORE = register("golden_nether_dungeon_core", new DungeonCoreBlock(BlockBehaviour.Properties.of().strength(50.0F, 1200.0F).pushReaction(PushReaction.BLOCK).sound(SoundType.STONE).requiresCorrectToolForDrops(), 101));
    public static final Block VOLUCITE_DUNGEON_CORE = register("volucite_dungeon_core", new DungeonCoreBlock(BlockBehaviour.Properties.of().strength(50.0F, 1200.0F).pushReaction(PushReaction.BLOCK).sound(SoundType.STONE).requiresCorrectToolForDrops(), 101));

    //dungeons slabs, stairs & walls
    public static final SlabBlock MUD_BRICKS_SLAB = register("mud_bricks_slab", new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS)));
    public static final StairBlock MUD_BRICKS_STAIRS = register("mud_bricks_stairs", new CoreProtectedStairsBlock(MUD_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS)));
    public static final WallBlock MUD_BRICKS_WALL = register("mud_bricks_wall", new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS)));
    public static final SlabBlock CRACKED_MUD_BRICKS_SLAB = register("cracked_mud_bricks_slab", new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_MUD_BRICKS)));
    public static final StairBlock CRACKED_MUD_BRICKS_STAIRS = register("cracked_mud_bricks_stairs", new CoreProtectedStairsBlock(CRACKED_MUD_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS)));
    public static final WallBlock CRACKED_MUD_BRICKS_WALL = register("cracked_mud_bricks_wall", new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_MUD_BRICKS)));
    public static final SlabBlock MOSSY_MUD_BRICKS_SLAB = register("mossy_mud_bricks_slab", new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS)));
    public static final StairBlock MOSSY_MUD_BRICKS_STAIRS = register("mossy_mud_bricks_stairs", new CoreProtectedStairsBlock(MOSSY_MUD_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS)));
    public static final WallBlock MOSSY_MUD_BRICKS_WALL = register("mossy_mud_bricks_wall", new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_MUD_BRICKS)));
    public static final SlabBlock VOLUCITE_STONE_SLAB = register("volucite_stone_slab", new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE)));
    public static final StairBlock VOLUCITE_STONE_STAIRS = register("volucite_stone_stairs", new CoreProtectedStairsBlock(VOLUCITE_STONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE)));
    public static final WallBlock VOLUCITE_STONE_WALL = register("volucite_stone_wall", new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE)));
    public static final SlabBlock CRACKED_VOLUCITE_STONE_SLAB = register("cracked_volucite_stone_slab", new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE)));
    public static final StairBlock CRACKED_VOLUCITE_STONE_STAIRS = register("cracked_volucite_stone_stairs", new CoreProtectedStairsBlock(CRACKED_VOLUCITE_STONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE)));
    public static final WallBlock CRACKED_VOLUCITE_STONE_WALL = register("cracked_volucite_stone_wall", new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_VOLUCITE_STONE)));
    public static final SlabBlock LUNATIC_STONE_SLAB = register("lunatic_stone_slab", new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE)));
    public static final StairBlock LUNATIC_STONE_STAIRS = register("lunatic_stone_stairs", new CoreProtectedStairsBlock(LUNATIC_STONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE)));
    public static final WallBlock LUNATIC_STONE_WALL = register("lunatic_stone_wall", new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE)));
    public static final SlabBlock DARK_LUNATIC_STONE_SLAB = register("dark_lunatic_stone_slab", new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(DARK_LUNATIC_STONE)));
    public static final StairBlock DARK_LUNATIC_STONE_STAIRS = register("dark_lunatic_stone_stairs", new CoreProtectedStairsBlock(DARK_LUNATIC_STONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(DARK_LUNATIC_STONE)));
    public static final WallBlock DARK_LUNATIC_STONE_WALL = register("dark_lunatic_stone_wall", new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(DARK_LUNATIC_STONE)));
    public static final SlabBlock CRACKED_LUNATIC_STONE_SLAB = register("cracked_lunatic_stone_slab", new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_LUNATIC_STONE)));
    public static final StairBlock CRACKED_LUNATIC_STONE_STAIRS = register("cracked_lunatic_stone_stairs", new CoreProtectedStairsBlock(CRACKED_LUNATIC_STONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE)));
    public static final WallBlock CRACKED_LUNATIC_STONE_WALL = register("cracked_lunatic_stone_wall", new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_LUNATIC_STONE)));
    public static final SlabBlock SHADOW_CATACOMBS_BRICKS_SLAB = register("shadow_catacombs_bricks_slab", new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS)));
    public static final StairBlock SHADOW_CATACOMBS_BRICKS_STAIRS = register("shadow_catacombs_bricks_stairs", new CoreProtectedStairsBlock(SHADOW_CATACOMBS_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS)));
    public static final WallBlock SHADOW_CATACOMBS_BRICKS_WALL = register("shadow_catacombs_bricks_wall", new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS)));
    public static final SlabBlock CRACKED_SHADOW_CATACOMBS_BRICKS_SLAB = register("cracked_shadow_catacombs_bricks_slab", new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_SHADOW_CATACOMBS_BRICKS)));
    public static final StairBlock CRACKED_SHADOW_CATACOMBS_BRICKS_STAIRS = register("cracked_shadow_catacombs_bricks_stairs", new CoreProtectedStairsBlock(CRACKED_SHADOW_CATACOMBS_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE)));
    public static final WallBlock CRACKED_SHADOW_CATACOMBS_BRICKS_WALL = register("cracked_shadow_catacombs_bricks_wall", new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_SHADOW_CATACOMBS_BRICKS)));
    public static final SlabBlock MOSSY_SHADOW_CATACOMBS_BRICKS_SLAB = register("mossy_shadow_catacombs_bricks_slab", new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_SHADOW_CATACOMBS_BRICKS)));
    public static final StairBlock MOSSY_SHADOW_CATACOMBS_BRICKS_STAIRS = register("mossy_shadow_catacombs_bricks_stairs", new CoreProtectedStairsBlock(MOSSY_SHADOW_CATACOMBS_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_SHADOW_CATACOMBS_BRICKS)));
    public static final WallBlock MOSSY_SHADOW_CATACOMBS_BRICKS_WALL = register("mossy_shadow_catacombs_bricks_wall", new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_SHADOW_CATACOMBS_BRICKS)));
    public static final IronBarsBlock SHADOW_BARS = register("shadow_bars", new ShadowBarsBlock(METAL_NOTSOLID_MATERIAL));
    public static final SlabBlock GOLDEN_NETHER_BRICKS_SLAB = register("golden_nether_bricks_slab", new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS)));
    public static final StairBlock GOLDEN_NETHER_BRICKS_STAIRS = register("golden_nether_bricks_stairs", new CoreProtectedStairsBlock(GOLDEN_NETHER_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE)));
    public static final WallBlock GOLDEN_NETHER_BRICKS_WALL = register("golden_nether_bricks_wall", new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS)));
    public static final SlabBlock CRACKED_GOLDEN_NETHER_BRICKS_SLAB = register("cracked_golden_nether_bricks_slab", new CoreProtectedSlabBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_GOLDEN_NETHER_BRICKS)));
    public static final StairBlock CRACKED_GOLDEN_NETHER_BRICKS_STAIRS = register("cracked_golden_nether_bricks_stairs", new CoreProtectedStairsBlock(CRACKED_GOLDEN_NETHER_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE)));
    public static final WallBlock CRACKED_GOLDEN_NETHER_BRICKS_WALL = register("cracked_golden_nether_bricks_wall", new CoreProtectedWallBlock(BlockBehaviour.Properties.ofFullCopy(CRACKED_GOLDEN_NETHER_BRICKS)));

    //smoky quartz
    public static final Block SMOKY_QUARTZ_BLOCK = register("smoky_quartz_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)));
    public static final Block SMOOTH_SMOKY_QUARTZ = register("smooth_smoky_quartz", new Block(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK)));
    public static final Block CHISELED_SMOKY_QUARTZ_BLOCK = register("chiseled_smoky_quartz_block", new Block(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK)));
    public static final Block SMOKY_QUARTZ_BRICKS = register("smoky_quartz_bricks", new Block(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK)));
    public static final RotatedPillarBlock SMOKY_QUARTZ_PILLAR = register("smoky_quartz_pillar", new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK)));
    public static final SlabBlock SMOKY_QUARTZ_SLAB = register("smoky_quartz_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK)));
    public static final SlabBlock SMOOTH_SMOKY_QUARTZ_SLAB = register("smooth_smoky_quartz_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK)));
    public static final StairBlock SMOKY_QUARTZ_STAIRS = register("smoky_quartz_stairs", new StairBlock(SMOKY_QUARTZ_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK)));
    public static final StairBlock SMOOTH_SMOKY_QUARTZ_STAIRS = register("smooth_smoky_quartz_stairs", new StairBlock(SMOOTH_SMOKY_QUARTZ.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SMOKY_QUARTZ_BLOCK)));

    //dungeon trapped blocks
    public static final Block TRAPPED_MUD_BRICKS = register("trapped_mud_bricks", new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)));
    public static final Block TRAPPED_LIGHT_MUD_BRICKS = register("trapped_light_mud_bricks", new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).lightLevel((state) -> 11)));
    public static final Block TRAPPED_LUNATIC_STONE = register("trapped_lunatic_stone", new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)));
    public static final Block TRAPPED_LIGHT_LUNATIC_STONE = register("trapped_light_lunatic_stone", new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).lightLevel((state) -> 11)));
    public static final Block TRAPPED_GOLDEN_NETHER_BRICKS = register("trapped_golden_nether_bricks", new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN)));
    public static final Block TRAPPED_LIGHT_GOLDEN_NETHER_BRICKS = register("trapped_light_golden_nether_bricks", new CoreProtectedTrappedBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OBSIDIAN).lightLevel((state) -> 11)));

    //dungeon other blocks, loots
    public static final RotatedPillarBlock MUD_BONE_BLOCK = register("mud_bone_block", new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).requiresCorrectToolForDrops().strength(2.5F).sound(SoundType.BONE_BLOCK)));
    public static final Block MUD_BONE_PILE_BLOCK = register("mud_bone_pile_block", new BonePileBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).randomTicks().strength(2.5F).sound(SoundType.BONE_BLOCK)));
    public static final Block THORNY_COBWEB = register("thorny_cobweb", new ThornyWebBlock(BlockBehaviour.Properties.of().noCollission().requiresCorrectToolForDrops().strength(8.0F)));
    public static final Block AERIAL_NETHERRACK = register("aerial_netherrack", new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).requiresCorrectToolForDrops().strength(6.0F, 8.0F)));
    public static final SlabBlock AERIAL_NETHERRACK_SLAB = register("aerial_netherrack_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_NETHERRACK)));
    public static final StairBlock AERIAL_NETHERRACK_STAIRS = register("aerial_netherrack_stairs", new StairBlock(AERIAL_NETHERRACK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(AERIAL_NETHERRACK)));
    public static final WallBlock AERIAL_NETHERRACK_WALL = register("aerial_netherrack_wall", new WallBlock(BlockBehaviour.Properties.ofFullCopy(AERIAL_NETHERRACK)));

    //dungeon bookshelfs
    public static final Block MUD_BOOKSHELF = register("mud_bookshelf", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS)));
    public static final Block LUNATIC_BOOKSHELF = register("lunatic_bookshelf", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE)));
    public static final Block GOLDEN_NETHER_BOOKSHELF = register("golden_nether_bookshelf", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS)));
    public static final Block SHADOW_CATACOMBS_BOOKSHELF = register("shadow_catacombs_bookshelf", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS)));
    public static final Block VOLUCITE_BOOKSHELF = register("volucite_bookshelf", new CoreProtectedBlock(BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE)));

    //glyph blocks
    public static final Block MUD_GLYPH_BLOCK = register("mud_glyph_block", new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(MUD_BRICKS).lightLevel((state) -> 9)));
    public static final Block LUNATIC_GLYPH_BLOCK = register("lunatic_glyph_block", new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE).lightLevel((state) -> 9)));
    public static final Block GOLDEN_NETHER_PRISON_GLYPH_BLOCK = register("golden_nether_prison_glyph_block", new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS).lightLevel((state) -> 9)));
    public static final Block VOLUCITE_GLYPH_BLOCK = register("volucite_glyph_block", new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(VOLUCITE_STONE).lightLevel((state) -> 9)));
    public static final Block SHADOW_CATACOMBS_GLYPH_BLOCK = register("shadow_catacombs_glyph_block", new CoreProtectedGlyphBlock(BlockBehaviour.Properties.ofFullCopy(SHADOW_CATACOMBS_BRICKS).lightLevel((state) -> 9)));

    //trophies
    public static final Block MUD_CYCLE_MAGE_TROPHY = register("mud_cycle_mage_trophy", new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE).requiresCorrectToolForDrops()));
    public static final Block LUNAR_PRIEST_TROPHY = register("lunar_priest_trophy", new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE).requiresCorrectToolForDrops()));
    public static final Block LILITH_TROPHY = register("lilith_trophy", new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(LUNATIC_STONE).requiresCorrectToolForDrops()));
    public static final Block CHAINED_GOD_TROPHY = register("chained_god_trophy", new BottomSlabLikeTrophyBlock(BlockBehaviour.Properties.ofFullCopy(GOLDEN_NETHER_BRICKS).requiresCorrectToolForDrops()));

    //ores
    public static final Block IRON_STELLAR_ORE = register("iron_stellar_ore", new AerialHellOreBlock(0, 2, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block GOLD_STELLAR_ORE = register("gold_stellar_ore", new AerialHellOreBlock(0, 2, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block DIAMOND_STELLAR_ORE = register("diamond_stellar_ore", new AerialHellOreBlock(3, 5, BlockBehaviour.Properties.of().strength(5.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block FLUORITE_ORE = register("fluorite_ore", new BiomeShifterOreBlock(0, 2, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops(), 2, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.SMOKY_QUARTZ_ORE));
    public static final Block MAGMATIC_GEL_ORE = register("magmatic_gel_ore", new MagmaticGelOreBlock(0, 2, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).lightLevel(s -> 4).requiresCorrectToolForDrops()));
    public static final Block RUBY_ORE = register("ruby_ore", new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block AZURITE_ORE = register("azurite_ore", new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block VOLUCITE_ORE = register("volucite_ore", new VoluciteOreBlock(0, 0, BlockBehaviour.Properties.of().strength(5.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block OBSIDIAN_ORE = register("obsidian_ore", new AerialHellOreBlock(0, 0, BlockBehaviour.Properties.of().strength(5.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));
    public static final Block SMOKY_QUARTZ_ORE = register("smoky_quartz_ore", new AerialHellOreBlock(1, 3, BlockBehaviour.Properties.of().strength(3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops()));

    public static final Block RAW_RUBY_BLOCK = register("raw_ruby_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final Block RAW_AZURITE_BLOCK = register("raw_azurite_crystal_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final Block RAW_VOLUCITE_BLOCK = register("raw_volucite_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).requiresCorrectToolForDrops()));

    public static final Block FLUORITE_BLOCK = register("fluorite_block", new BiomeShifterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops(), 7, BiomeShifter.ShiftType.UNCORRUPT, () -> AerialHellBlocks.SMOKY_QUARTZ_BLOCK));
    public static final Block MAGMATIC_GEL_BLOCK = register("magmatic_gel_block", new MagmaticGelBlock(BlockBehaviour.Properties.of().strength(1.0F, 1600.0F).randomTicks().sound(SoundType.GLASS).noOcclusion().requiresCorrectToolForDrops().isViewBlocking((state, reader, pos) -> false)));
    public static final Block RUBY_BLOCK = register("ruby_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final Block AZURITE_BLOCK = register("azurite_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops()));
    public static final Block VOLUCITE_BLOCK = register("volucite_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK).requiresCorrectToolForDrops()));

    //legendary ores blocks
    public static final Block ARSONIST_BLOCK = register("arsonist_block", new ArsonistBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).requiresCorrectToolForDrops().lightLevel((state) -> 9)));
    public static final Block LUNATIC_CRYSTAL_BLOCK = register("lunatic_crystal_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).sound(SoundType.GLASS).requiresCorrectToolForDrops().lightLevel((state) -> 9)));
    public static final Block CURSED_CRYSAL_BLOCK = register("cursed_crystal_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERITE_BLOCK).sound(SoundType.GLASS).requiresCorrectToolForDrops().lightLevel((state) -> 9)));

    //cactus
    public static final SkyCactusBlock SKY_CACTUS = register("sky_cactus", new SkyCactusBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(0.4F).sound(SoundType.WOOL).randomTicks()));
    public static final Block SKY_CACTUS_FIBER_PLANKS = register("sky_cactus_fiber_planks", new Block(SKY_CACTUS_FIBER_MATERIAL));
    public static final Block SKY_CACTUS_FIBER_BOOKSHELF = register("sky_cactus_fiber_bookshelf", new Block(BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_PLANKS)));
    public static final SkyCactusBlock VIBRANT_SKY_CACTUS = register("vibrant_sky_cactus", new SkyCactusBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(0.4F).sound(SoundType.WOOL).randomTicks().lightLevel(s -> 15).noOcclusion()));
    public static final Block VIBRANT_SKY_CACTUS_FIBER_LANTERN = register("vibrant_sky_cactus_fiber_lantern", new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW).strength(0.5F).sound(SoundType.GLASS).noOcclusion().lightLevel(s -> 15)));

    //bushes
    public static final Block AERIAL_BERRY_BUSH = register("aerial_berry_bush", new AerialBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));
    public static final Block VIBRANT_AERIAL_BERRY_BUSH = register("vibrant_aerial_berry_bush", new VibrantAerialBerryBushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    //crops
    public static final Block STELLAR_WHEAT = register("stellar_wheat", new StellarCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));
    public static final Block BLUE_MEANIE_CROP = register("blue_meanie_crop", new StellarCropBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT)));

    //vertical growing plants
    public static final VerticalGrowingPlantBlock CLIMBING_VINE = register("climbing_vine", new VerticalGrowingPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE), 4));
    public static final VerticalGrowingPlantBlock STELLAR_SUGAR_CANE = register("stellar_sugar_cane", new VerticalGrowingPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE), 5));

    //chorus like
    public static final ChorusPlantLikeBlock FULL_MOON_PLANT = register("full_moon_plant", new ChorusPlantLikeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).forceSolidOff().strength(0.4F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.DESTROY).lightLevel((state) -> 10)));
    public static final ChorusFlowerLikeBlock FULL_MOON_FLOWER = register("full_moon_flower", new ChorusFlowerLikeBlock(FULL_MOON_PLANT, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).forceSolidOff().randomTicks().strength(0.4F).sound(SoundType.WOOD).noOcclusion().isValidSpawn((state, blockGetter, pos, entitytype) -> false).pushReaction(PushReaction.DESTROY).isRedstoneConductor((state, blockGetter, pos) -> false).lightLevel((state) -> 15)));

    //vines
    public static final CaveVinesBlock GLOWING_STICK_FRUIT_VINES = register("glowing_stick_fruit_vines", new AerialHellCaveVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAVE_VINES)));
    public static final CaveVinesPlantBlock GLOWING_STICK_FRUIT_VINES_PLANT = register("glowing_stick_fruit_vines_plant", new AerialHellCaveVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAVE_VINES_PLANT)));
    public static final CaveVinesBlock BLOSSOMING_VINES = register("blossoming_vines", new AerialHellCaveVinesBlock(BlockBehaviour.Properties.of().randomTicks().noCollission().instabreak().sound(SoundType.CAVE_VINES)));
    public static final CaveVinesPlantBlock BLOSSOMING_VINES_PLANT = register("blossoming_vines_plant", new AerialHellCaveVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(BLOSSOMING_VINES)));
    public static final AerialHellTwistingVinesBlock LAZULI_ROOTS = register("lazuli_roots", new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES)));
    public static final AerialHellTwistingVinesPlantBlock LAZULI_ROOTS_PLANT = register("lazuli_roots_plant", new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT)));
    public static final AerialHellTwistingVinesBlock STELLAR_ROOTS = register("stellar_roots", new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES)));
    public static final AerialHellTwistingVinesPlantBlock STELLAR_ROOTS_PLANT = register("stellar_roots_plant", new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT)));
    public static final AerialHellTwistingVinesBlock DEAD_ROOTS = register("dead_roots", new DeadRootsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES)));
    public static final AerialHellTwistingVinesPlantBlock DEAD_ROOTS_PLANT = register("dead_roots_plant", new DeadRootsPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT)));
    public static final AerialHellTwistingVinesBlock GLOWING_ROOTS = register("glowing_roots", new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES).lightLevel((state) -> 9)));
    public static final AerialHellTwistingVinesPlantBlock GLOWING_ROOTS_PLANT = register("glowing_roots_plant", new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT).lightLevel((state) -> 14)));
    public static final AerialHellTwistingVinesBlock SHADOW_GLOWING_ROOTS = register("shadow_glowing_roots", new AerialHellTwistingVinesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES).lightLevel((state) -> 8)));
    public static final AerialHellTwistingVinesPlantBlock SHADOW_GLOWING_ROOTS_PLANT = register("shadow_glowing_roots_plant", new AerialHellTwistingVinesPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TWISTING_VINES_PLANT).lightLevel((state) -> 13)));

    //grass
    public static final Block STELLAR_GRASS = register("stellar_grass", new ShiftableRenderTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
    public static final Block STELLAR_GRASS_BALL = register("stellar_grass_ball", new ShiftableRenderTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
    public static final Block STELLAR_FERN = register("stellar_fern", new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
    public static final Block STELLAR_TALL_GRASS = register("stellar_tall_grass", new DoublePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
    public static final Block STELLAR_TALL_FERN = register("stellar_tall_fern", new DoublePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
    public static final VerticalGrowingPlantBlock STELLAR_VERY_TALL_GRASS = register("stellar_very_tall_grass", new VerticalGrowingPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE), 3));
    public static final Block BLUISH_FERN = register("bluish_fern", new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
    public static final Block TALL_BLUISH_FERN = register("tall_bluish_fern", new DoublePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
    public static final Block POLYCHROME_FERN = register("polychrome_fern", new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
    public static final Block TALL_POLYCHROME_FERN = register("tall_polychrome_fern", new DoublePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
    public static final Block STELLAR_DEAD_BUSH = register("stellar_dead_bush", new AerialHellDeadBushBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).replaceable().mapColor(MapColor.COLOR_BROWN).noCollission().instabreak().sound(SoundType.GRASS)));
    public static final Block BRAMBLES = register("brambles", new BramblesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noCollission().strength(0.5F).sound(SoundType.GRASS)));
    public static final Block SHADOW_BRAMBLES = register("shadow_brambles", new BramblesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).noCollission().strength(0.5F).sound(SoundType.GRASS)));
    public static final Block SHADOW_GRASS = register("shadow_grass", new ShadowPlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
    public static final Block SHADOW_GRASS_BALL = register("shadow_grass_ball", new ShadowPlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
    public static final Block PURPLISH_STELLAR_GRASS = register("purplish_stellar_grass", new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
    public static final Block STELLAR_CLOVERS = register("stellar_clovers", new AerialHellTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).replaceable().noCollission().instabreak().sound(SoundType.GRASS)));
    public static final Block GLOWING_STELLAR_GRASS = register("glowing_stellar_grass", new GlowingStellarTallGrass(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).replaceable().randomTicks().noCollission().lightLevel((state) -> {return state.getValue(BlockStateProperties.LIT) ? 10 : 0;}).instabreak().sound(SoundType.GRASS)));

    //flowers
    public static final Block BLUE_FLOWER = register("blue_flower", new FlowerBlock(MobEffects.BLINDNESS, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));
    public static final Block BLACK_ROSE = register("black_rose", new FlowerBlock(MobEffects.SLOW_FALLING, 12, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));
    public static final Block BELLFLOWER = register("bellflower", new FlowerBlock(MobEffects.DIG_SLOWDOWN, 12, BlockBehaviour.Properties.ofFullCopy(Blocks.DANDELION)));

    //potted things
    public static final FlowerPotBlock POTTED_BLUE_FLOWER = register("potted_blue_flower", new FlowerPotBlock(BLUE_FLOWER, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_BLACK_ROSE = register("potted_black_rose", new FlowerPotBlock(BLACK_ROSE, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_BELLFLOWER = register("potted_bellflower", new FlowerPotBlock(BELLFLOWER, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_STELLAR_FERN = register("potted_stellar_fern", new FlowerPotBlock(STELLAR_FERN, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_STELLAR_DEAD_BUSH = register("potted_stellar_dead_bush", new FlowerPotBlock(STELLAR_DEAD_BUSH, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_SKY_CACTUS = register("potted_sky_cactus", new FlowerPotBlock(SKY_CACTUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_VIBRANT_SKY_CACTUS = register("potted_vibrant_sky_cactus", new FlowerPotBlock(VIBRANT_SKY_CACTUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_AERIAL_TREE_SAPLING = register("potted_aerial_tree_sapling", new FlowerPotBlock(AERIAL_TREE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_GOLDEN_BEECH_SAPLING = register("potted_golden_beech_sapling", new FlowerPotBlock(GOLDEN_BEECH_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_COPPER_PINE_SAPLING = register("potted_copper_pine_sapling", new FlowerPotBlock(COPPER_PINE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_LAPIS_ROBINIA_SAPLING = register("potted_lapis_robinia_sapling", new FlowerPotBlock(LAPIS_ROBINIA_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_SHADOW_PINE_SAPLING = register("potted_shadow_pine_sapling", new FlowerPotBlock(SHADOW_PINE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_PURPLE_SHADOW_PINE_SAPLING = register("potted_purple_shadow_pine_sapling", new FlowerPotBlock(PURPLE_SHADOW_PINE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_STELLAR_JUNGLE_TREE_SAPLING = register("potted_stellar_jungle_tree_sapling", new FlowerPotBlock(STELLAR_JUNGLE_TREE_SAPLING, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_CORTINARIUS_VIOLACEUS = register("potted_cortinarius_violaceus", new FlowerPotBlock(CORTINARIUS_VIOLACEUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_VERDIGRIS_AGARIC = register("potted_verdigris_agaric", new FlowerPotBlock(VERDIGRIS_AGARIC, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_VINE_BLOSSOM = register("potted_vine_blossom", new FlowerPotBlock(BLOSSOMING_VINES, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT)));
    public static final FlowerPotBlock POTTED_GLOWING_BOLETUS = register("potted_glowing_boletus", new FlowerPotBlock(GLOWING_BOLETUS, BlockBehaviour.Properties.ofFullCopy(Blocks.FLOWER_POT).lightLevel((state) -> 9)));

    //with gui
    public static final Block OSCILLATOR = register("oscillator", new OscillatorBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.STONE)));
    public static final Block FREEZER = register("freezer", new FreezerBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.STONE)));
    public static final Block STELLAR_FURNACE = register("stellar_furnace", new StellarFurnaceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.5F).lightLevel(getLightValueLit(13))));
    public static final Block GHOST_STELLAR_FURNACE = register("ghost_stellar_furnace", new GhostStellarFurnaceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().noOcclusion().strength(3.5F).lightLevel(getLightValueLit(13))));

    private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {return (state) -> {return state.getValue(BlockStateProperties.LIT) ? lightValue : 0;};}

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
    public static final Block AERIAL_TREE_CHEST_MIMIC = register("aerial_tree_chest_mimic", new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
    public static final Block GOLDEN_BEECH_CHEST_MIMIC = register("golden_beech_chest_mimic", new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
    public static final Block COPPER_PINE_CHEST_MIMIC = register("copper_pine_chest_mimic", new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));
    public static final Block SKY_CACTUS_FIBER_CHEST_MIMIC = register("sky_cactus_fiber_chest_mimic", new ChestMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHEST)));

    //barrel mimics
    public static final Block SHADOW_PINE_BARREL_MIMIC = register("shadow_pine_barrel_mimic", new BarrelMimicBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL)));

    //fences, bars or walls
    public static final FenceBlock AERIAL_TREE_FENCE = register("aerial_tree_fence", new FenceBlock(AERIAL_TREE_MATERIAL));
    public static final FenceBlock GOLDEN_BEECH_FENCE = register("golden_beech_fence", new FenceBlock(AERIAL_TREE_MATERIAL));
    public static final FenceBlock COPPER_PINE_FENCE = register("copper_pine_fence", new FenceBlock(COPPER_PINE_MATERIAL));
    public static final FenceBlock LAPIS_ROBINIA_FENCE = register("lapis_robinia_fence", new FenceBlock(COPPER_PINE_MATERIAL));
    public static final FenceBlock SHADOW_PINE_FENCE = register("shadow_pine_fence", new FenceBlock(SHADOW_PINE_MATERIAL));
    public static final FenceBlock STELLAR_JUNGLE_TREE_FENCE = register("stellar_jungle_tree_fence", new FenceBlock(COPPER_PINE_MATERIAL));
    public static final FenceBlock SKY_CACTUS_FIBER_FENCE = register("sky_cactus_fiber_fence", new FenceBlock(SKY_CACTUS_FIBER_MATERIAL));
    public static final FenceBlock GRAY_SHROOM_FENCE = register("gray_shroom_fence", new FenceBlock(SHROOM_MATERIAL));
    public static final IronBarsBlock RUBY_BARS = register("ruby_bars", new IronBarsBlock(METAL_NOTSOLID_MATERIAL));
    public static final WallBlock STELLAR_STONE_WALL = register("stellar_stone_wall", new WallBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE)));
    public static final WallBlock STELLAR_COBBLESTONE_WALL = register("stellar_cobblestone_wall", new WallBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE)));
    public static final WallBlock STELLAR_STONE_BRICKS_WALL = register("stellar_stone_bricks_wall", new WallBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS)));
    public static final WallBlock MOSSY_STELLAR_STONE_WALL = register("mossy_stellar_stone_wall", new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_STONE)));
    public static final WallBlock MOSSY_STELLAR_COBBLESTONE_WALL = register("mossy_stellar_cobblestone_wall", new WallBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_COBBLESTONE)));
    public static final WallBlock SLIPPERY_SAND_STONE_WALL = register("slippery_sand_stone_wall", new WallBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE)));
    public static final WallBlock SLIPPERY_SAND_STONE_BRICKS_WALL = register("slippery_sand_stone_bricks_wall", new WallBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS)));
    public static final WallBlock CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL = register("cracked_slippery_sand_stone_bricks_wall", new WallBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS)));
    public static final WallBlock GLAUCOPHANITE_WALL = register("glaucophanite_wall", new WallBlock(BlockBehaviour.Properties.ofFullCopy(GLAUCOPHANITE)));
    public static final WallBlock POLISHED_GLAUCOPHANITE_WALL = register("polished_glaucophanite_wall", new WallBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_GLAUCOPHANITE)));
    public static final WallBlock MAGMATIC_GEL_WALL = register("magmatic_gel_wall", new WallBlock(BlockBehaviour.Properties.ofFullCopy(MAGMATIC_GEL_BLOCK)));

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
    public static final DoorBlock AERIAL_TREE_DOOR = register("aerial_tree_door", new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS).noOcclusion()));
    public static final DoorBlock GOLDEN_BEECH_DOOR = register("golden_beech_door", new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS).noOcclusion()));
    public static final DoorBlock COPPER_PINE_DOOR = register("copper_pine_door", new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_PLANKS).noOcclusion()));
    public static final DoorBlock LAPIS_ROBINIA_DOOR = register("lapis_robinia_door", new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_PLANKS).noOcclusion()));
    public static final DoorBlock SHADOW_PINE_DOOR = register("shadow_pine_door", new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_PLANKS).noOcclusion()));
    public static final DoorBlock STELLAR_JUNGLE_TREE_DOOR = register("stellar_jungle_tree_door", new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_PLANKS).noOcclusion()));
    public static final DoorBlock SKY_CACTUS_FIBER_DOOR = register("sky_cactus_fiber_door", new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_PLANKS).noOcclusion()));
    public static final DoorBlock GRAY_SHROOM_DOOR = register("gray_shroom_door", new DoorBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_PLANKS).noOcclusion()));
    public static final DoorBlock RUBY_DOOR = register("ruby_door", new DoorBlock(BlockSetType.IRON, METAL_NOTSOLID_MATERIAL));

    //trapdoors
    public static final TrapDoorBlock AERIAL_TREE_TRAPDOOR = register("aerial_tree_trapdoor", new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_PLANKS).noOcclusion()));
    public static final TrapDoorBlock GOLDEN_BEECH_TRAPDOOR = register("golden_beech_trapdoor", new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_PLANKS).noOcclusion()));
    public static final TrapDoorBlock COPPER_PINE_TRAPDOOR = register("copper_pine_trapdoor", new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_PLANKS).noOcclusion()));
    public static final TrapDoorBlock LAPIS_ROBINIA_TRAPDOOR = register("lapis_robinia_trapdoor", new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_PLANKS).noOcclusion()));
    public static final TrapDoorBlock SHADOW_PINE_TRAPDOOR = register("shadow_pine_trapdoor", new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_PLANKS).noOcclusion()));
    public static final TrapDoorBlock STELLAR_JUNGLE_TREE_TRAPDOOR = register("stellar_jungle_tree_trapdoor", new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_PLANKS).noOcclusion()));
    public static final TrapDoorBlock SKY_CACTUS_FIBER_TRAPDOOR = register("sky_cactus_fiber_trapdoor", new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_PLANKS).noOcclusion()));
    public static final TrapDoorBlock GRAY_SHROOM_TRAPDOOR = register("gray_shroom_trapdoor", new TrapDoorBlock(BlockSetType.CRIMSON, BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_PLANKS).noOcclusion()));
    public static final TrapDoorBlock RUBY_TRAPDOOR = register("ruby_trapdoor", new TrapDoorBlock(BlockSetType.IRON, METAL_NOTSOLID_MATERIAL));

    //buttons
    public static final ButtonBlock STELLAR_STONE_BUTTON = register("stellar_stone_button", new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE)));
    public static final ButtonBlock STELLAR_COBBLESTONE_BUTTON = register("stellar_cobblestone_button", new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE)));
    public static final ButtonBlock STELLAR_STONE_BRICKS_BUTTON = register("stellar_stone_bricks_button", new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS)));
    public static final ButtonBlock SLIPPERY_SAND_STONE_BUTTON = register("slippery_sand_stone_button", new ButtonBlock(BlockSetType.STONE, 30, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE)));
    public static final ButtonBlock SLIPPERY_SAND_STONE_BRICKS_BUTTON = register("slippery_sand_stone_bricks_button", new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS)));
    public static final ButtonBlock AERIAL_TREE_BUTTON = register("aerial_tree_button", new ButtonBlock(BlockSetType.OAK, 30, AERIAL_TREE_MATERIAL));
    public static final ButtonBlock GOLDEN_BEECH_BUTTON = register("golden_beech_button", new ButtonBlock(BlockSetType.OAK, 30, AERIAL_TREE_MATERIAL));
    public static final ButtonBlock COPPER_PINE_BUTTON = register("copper_pine_button", new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL));
    public static final ButtonBlock LAPIS_ROBINIA_BUTTON = register("lapis_robinia_button", new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL));
    public static final ButtonBlock SHADOW_PINE_BUTTON = register("shadow_pine_button", new ButtonBlock(BlockSetType.OAK, 30, SHADOW_PINE_MATERIAL));
    public static final ButtonBlock STELLAR_JUNGLE_TREE_BUTTON = register("stellar_jungle_tree_button", new ButtonBlock(BlockSetType.OAK, 30, COPPER_PINE_MATERIAL));
    public static final ButtonBlock SKY_CACTUS_FIBER_BUTTON = register("sky_cactus_fiber_button", new ButtonBlock(BlockSetType.OAK, 30, SKY_CACTUS_FIBER_MATERIAL));
    public static final ButtonBlock GRAY_SHROOM_BUTTON = register("gray_shroom_button", new ButtonBlock(BlockSetType.CRIMSON, 30, SHROOM_MATERIAL));
    public static final ButtonBlock GLAUCOPHANITE_BUTTON = register("glaucophanite_button", new ButtonBlock(BlockSetType.STONE, 20, BlockBehaviour.Properties.ofFullCopy(GLAUCOPHANITE)));

    //pressure plates
    public static final PressurePlateBlock STELLAR_STONE_PRESSURE_PLATE = register("stellar_stone_pressure_plate", new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE)));
    public static final PressurePlateBlock STELLAR_COBBLESTONE_PRESSURE_PLATE = register("stellar_cobblestone_pressure_plate", new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE)));
    public static final PressurePlateBlock STELLAR_STONE_BRICKS_PRESSURE_PLATE = register("stellar_stone_bricks_pressure_plate", new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS)));
    public static final PressurePlateBlock SLIPPERY_SAND_STONE_PRESSURE_PLATE = register("slippery_sand_stone_pressure_plate", new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE)));
    public static final PressurePlateBlock SLIPPERY_SAND_STONE_BRICKS_PRESSURE_PLATE = register("slippery_sand_stone_bricks_pressure_plate", new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS)));
    public static final PressurePlateBlock AERIAL_TREE_PRESSURE_PLATE = register("aerial_tree_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, AERIAL_TREE_MATERIAL));
    public static final PressurePlateBlock GOLDEN_BEECH_PRESSURE_PLATE = register("golden_beech_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, AERIAL_TREE_MATERIAL));
    public static final PressurePlateBlock COPPER_PINE_PRESSURE_PLATE = register("copper_pine_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL));
    public static final PressurePlateBlock LAPIS_ROBINIA_PRESSURE_PLATE = register("lapis_robinia_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL));
    public static final PressurePlateBlock SHADOW_PINE_PRESSURE_PLATE = register("shadow_pine_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, SHADOW_PINE_MATERIAL));
    public static final PressurePlateBlock STELLAR_JUNGLE_TREE_PRESSURE_PLATE = register("stellar_jungle_tree_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, COPPER_PINE_MATERIAL));
    public static final PressurePlateBlock SKY_CACTUS_FIBER_PRESSURE_PLATE = register("sky_cactus_fiber_pressure_plate", new PressurePlateBlock(BlockSetType.OAK, SKY_CACTUS_FIBER_MATERIAL));
    public static final PressurePlateBlock GRAY_SHROOM_PRESSURE_PLATE = register("gray_shroom_pressure_plate", new PressurePlateBlock(BlockSetType.CRIMSON, SHROOM_MATERIAL));
    public static final PressurePlateBlock GLAUCOPHANITE_PRESSURE_PLATE = register("glaucophanite_pressure_plate", new PressurePlateBlock(BlockSetType.STONE, BlockBehaviour.Properties.ofFullCopy(GLAUCOPHANITE)));

    //slabs
    public static final SlabBlock AERIAL_TREE_SLAB = register("aerial_tree_slab", new SlabBlock(AERIAL_TREE_MATERIAL));
    public static final SlabBlock GOLDEN_BEECH_SLAB = register("golden_beech_slab", new SlabBlock(AERIAL_TREE_MATERIAL));
    public static final SlabBlock COPPER_PINE_SLAB = register("copper_pine_slab", new SlabBlock(COPPER_PINE_MATERIAL));
    public static final SlabBlock LAPIS_ROBINIA_SLAB = register("lapis_robinia_slab", new SlabBlock(COPPER_PINE_MATERIAL));
    public static final SlabBlock SHADOW_PINE_SLAB = register("shadow_pine_slab", new SlabBlock(SHADOW_PINE_MATERIAL));
    public static final SlabBlock STELLAR_JUNGLE_TREE_SLAB = register("stellar_jungle_tree_slab", new SlabBlock(COPPER_PINE_MATERIAL));
    public static final SlabBlock SKY_CACTUS_FIBER_SLAB = register("sky_cactus_fiber_slab", new SlabBlock(SKY_CACTUS_FIBER_MATERIAL));
    public static final SlabBlock GRAY_SHROOM_SLAB = register("gray_shroom_slab", new SlabBlock(SHROOM_MATERIAL));
    public static final SlabBlock STELLAR_STONE_SLAB = register("stellar_stone_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE)));
    public static final SlabBlock STELLAR_COBBLESTONE_SLAB = register("stellar_cobblestone_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE)));
    public static final SlabBlock STELLAR_STONE_BRICKS_SLAB = register("stellar_stone_bricks_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS)));
    public static final SlabBlock MOSSY_STELLAR_STONE_SLAB = register("mossy_stellar_stone_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_STONE)));
    public static final SlabBlock MOSSY_STELLAR_COBBLESTONE_SLAB = register("mossy_stellar_cobblestone_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_COBBLESTONE)));
    public static final SlabBlock SLIPPERY_SAND_STONE_SLAB = register("slippery_sand_stone_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE)));
    public static final SlabBlock SLIPPERY_SAND_STONE_BRICKS_SLAB = register("slippery_sand_stone_bricks_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS)));
    public static final SlabBlock CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB = register("cracked_slippery_sand_stone_bricks_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS)));
    public static final SlabBlock POLISHED_GLAUCOPHANITE_SLAB = register("polished_glaucophanite_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(POLISHED_GLAUCOPHANITE)));
    public static final SlabBlock MAGMATIC_GEL_SLAB = register("magmatic_gel_slab", new SlabBlock(BlockBehaviour.Properties.ofFullCopy(MAGMATIC_GEL_BLOCK)));

    //stairs
    public static final StairBlock AERIAL_TREE_STAIRS = register("aerial_tree_stairs", new StairBlock(AERIAL_TREE_PLANKS.defaultBlockState(), AERIAL_TREE_MATERIAL));
    public static final StairBlock GOLDEN_BEECH_STAIRS = register("golden_beech_stairs", new StairBlock(GOLDEN_BEECH_PLANKS.defaultBlockState(), AERIAL_TREE_MATERIAL));
    public static final StairBlock COPPER_PINE_STAIRS = register("copper_pine_stairs", new StairBlock(COPPER_PINE_PLANKS.defaultBlockState(), COPPER_PINE_MATERIAL));
    public static final StairBlock LAPIS_ROBINIA_STAIRS = register("lapis_robinia_stairs", new StairBlock(LAPIS_ROBINIA_PLANKS.defaultBlockState(), COPPER_PINE_MATERIAL));
    public static final StairBlock SHADOW_PINE_STAIRS = register("shadow_pine_stairs", new StairBlock(SHADOW_PINE_PLANKS.defaultBlockState(), SHADOW_PINE_MATERIAL));
    public static final StairBlock STELLAR_JUNGLE_TREE_STAIRS = register("stellar_jungle_tree_stairs", new StairBlock(STELLAR_JUNGLE_TREE_PLANKS.defaultBlockState(), COPPER_PINE_MATERIAL));
    public static final StairBlock SKY_CACTUS_FIBER_STAIRS = register("sky_cactus_fiber_stairs", new StairBlock(SKY_CACTUS_FIBER_PLANKS.defaultBlockState(), SKY_CACTUS_FIBER_MATERIAL));
    public static final StairBlock GRAY_SHROOM_STAIRS = register("gray_shroom_stairs", new StairBlock(GRAY_SHROOM_PLANKS.defaultBlockState(), SHROOM_MATERIAL));
    public static final StairBlock STELLAR_STONE_STAIRS = register("stellar_stone_stairs", new StairBlock(STELLAR_STONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE)));
    public static final StairBlock STELLAR_COBBLESTONE_STAIRS = register("stellar_cobblestone_stairs", new StairBlock(STELLAR_COBBLESTONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(STELLAR_COBBLESTONE)));
    public static final StairBlock STELLAR_STONE_BRICKS_STAIRS = register("stellar_stone_bricks_stairs", new StairBlock(STELLAR_STONE_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(STELLAR_STONE_BRICKS)));
    public static final StairBlock MOSSY_STELLAR_STONE_STAIRS = register("mossy_stellar_stone_stairs", new StairBlock(MOSSY_STELLAR_STONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_STONE)));
    public static final StairBlock MOSSY_STELLAR_COBBLESTONE_STAIRS = register("mossy_stellar_cobblestone_stairs", new StairBlock(MOSSY_STELLAR_COBBLESTONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MOSSY_STELLAR_COBBLESTONE)));
    public static final StairBlock SLIPPERY_SAND_STONE_STAIRS = register("slippery_sand_stone_stairs", new StairBlock(SLIPPERY_SAND_STONE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE)));
    public static final StairBlock SLIPPERY_SAND_STONE_BRICKS_STAIRS = register("slippery_sand_stone_bricks_stairs", new StairBlock(SLIPPERY_SAND_STONE_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS)));
    public static final StairBlock CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS = register("cracked_slippery_sand_stone_bricks_stairs", new StairBlock(SLIPPERY_SAND_STONE_BRICKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SLIPPERY_SAND_STONE_BRICKS)));
    public static final StairBlock POLISHED_GLAUCOPHANITE_STAIRS = register("polished_glaucophanite_stairs", new StairBlock(POLISHED_GLAUCOPHANITE.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(POLISHED_GLAUCOPHANITE)));
    public static final StairBlock MAGMATIC_GEL_STAIRS = register("magmatic_gel_stairs", new StairBlock(MAGMATIC_GEL_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(MAGMATIC_GEL_BLOCK)));

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
    public static final CeilingHangingSignBlock AERIAL_TREE_HANGING_SIGN = register("aerial_tree_hanging_sign", new AerialHellHangingSignBlock(AerialHellWoodTypes.AERIAL_TREE, AERIAL_TREE_SIGN_MATERIAL));
    public static final WallHangingSignBlock AERIAL_TREE_WALL_HANGING_SIGN = register("aerial_tree_wall_hanging_sign", new AerialHellWallHangingSignBlock(AerialHellWoodTypes.AERIAL_TREE, BlockBehaviour.Properties.ofFullCopy(AERIAL_TREE_HANGING_SIGN).dropsLike(AERIAL_TREE_HANGING_SIGN)));
    public static final CeilingHangingSignBlock GOLDEN_BEECH_HANGING_SIGN = register("golden_beech_hanging_sign", new AerialHellHangingSignBlock(AerialHellWoodTypes.GOLDEN_BEECH, AERIAL_TREE_SIGN_MATERIAL));
    public static final WallHangingSignBlock GOLDEN_BEECH_WALL_HANGING_SIGN = register("golden_beech_wall_hanging_sign", new AerialHellWallHangingSignBlock(AerialHellWoodTypes.GOLDEN_BEECH, BlockBehaviour.Properties.ofFullCopy(GOLDEN_BEECH_HANGING_SIGN).dropsLike(GOLDEN_BEECH_HANGING_SIGN)));
    public static final CeilingHangingSignBlock COPPER_PINE_HANGING_SIGN = register("copper_pine_hanging_sign", new AerialHellHangingSignBlock(AerialHellWoodTypes.COPPER_PINE, COPPER_PINE_SIGN_MATERIAL));
    public static final WallHangingSignBlock COPPER_PINE_WALL_HANGING_SIGN = register("copper_pine_wall_hanging_sign", new AerialHellWallHangingSignBlock(AerialHellWoodTypes.COPPER_PINE, BlockBehaviour.Properties.ofFullCopy(COPPER_PINE_HANGING_SIGN).dropsLike(COPPER_PINE_HANGING_SIGN)));
    public static final CeilingHangingSignBlock LAPIS_ROBINIA_HANGING_SIGN = register("lapis_robinia_hanging_sign", new AerialHellHangingSignBlock(AerialHellWoodTypes.LAPIS_ROBINIA, COPPER_PINE_SIGN_MATERIAL));
    public static final WallHangingSignBlock LAPIS_ROBINIA_WALL_HANGING_SIGN = register("lapis_robinia_wall_hanging_sign", new AerialHellWallHangingSignBlock(AerialHellWoodTypes.LAPIS_ROBINIA, BlockBehaviour.Properties.ofFullCopy(LAPIS_ROBINIA_HANGING_SIGN).dropsLike(LAPIS_ROBINIA_HANGING_SIGN)));
    public static final CeilingHangingSignBlock SHADOW_PINE_HANGING_SIGN = register("shadow_pine_hanging_sign", new AerialHellHangingSignBlock(AerialHellWoodTypes.SHADOW_PINE, SHADOW_PINE_SIGN_MATERIAL));
    public static final WallHangingSignBlock SHADOW_PINE_WALL_HANGING_SIGN = register("shadow_pine_wall_hanging_sign", new AerialHellWallHangingSignBlock(AerialHellWoodTypes.SHADOW_PINE, BlockBehaviour.Properties.ofFullCopy(SHADOW_PINE_HANGING_SIGN).dropsLike(SHADOW_PINE_HANGING_SIGN)));
    public static final CeilingHangingSignBlock STELLAR_JUNGLE_TREE_HANGING_SIGN = register("stellar_jungle_tree_hanging_sign", new AerialHellHangingSignBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, SHADOW_PINE_SIGN_MATERIAL));
    public static final WallHangingSignBlock STELLAR_JUNGLE_TREE_WALL_HANGING_SIGN = register("stellar_jungle_tree_wall_hanging_sign", new AerialHellWallHangingSignBlock(AerialHellWoodTypes.STELLAR_JUNGLE_TREE, BlockBehaviour.Properties.ofFullCopy(STELLAR_JUNGLE_TREE_HANGING_SIGN).dropsLike(STELLAR_JUNGLE_TREE_HANGING_SIGN)));
    public static final CeilingHangingSignBlock SKY_CACTUS_FIBER_HANGING_SIGN = register("sky_cactus_fiber_hanging_sign", new AerialHellHangingSignBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, SKY_CACTUS_FIBER_SIGN_MATERIAL));
    public static final WallHangingSignBlock SKY_CACTUS_FIBER_WALL_HANGING_SIGN = register("sky_cactus_fiber_wall_hanging_sign", new AerialHellWallHangingSignBlock(AerialHellWoodTypes.SKY_CACTUS_FIBER, BlockBehaviour.Properties.ofFullCopy(SKY_CACTUS_FIBER_HANGING_SIGN).dropsLike(SKY_CACTUS_FIBER_HANGING_SIGN)));
    public static final CeilingHangingSignBlock GRAY_SHROOM_HANGING_SIGN = register("gray_shroom_hanging_sign", new AerialHellHangingSignBlock(AerialHellWoodTypes.GRAY_SHROOM, SKY_CACTUS_FIBER_SIGN_MATERIAL));
    public static final WallHangingSignBlock GRAY_SHROOM_WALL_HANGING_SIGN = register("gray_shroom_wall_hanging_sign", new AerialHellWallHangingSignBlock(AerialHellWoodTypes.GRAY_SHROOM, BlockBehaviour.Properties.ofFullCopy(GRAY_SHROOM_HANGING_SIGN).dropsLike(GRAY_SHROOM_HANGING_SIGN)));

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
    public static final RotatedPillarBlock AERIAL_TREE_VINE_ROPE_SPOOL = register("aerial_tree_vine_rope_spool", new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
    public static final RotatedPillarBlock GOLDEN_BEECH_VINE_ROPE_SPOOL = register("golden_beech_vine_rope_spool", new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
    public static final RotatedPillarBlock COPPER_PINE_VINE_ROPE_SPOOL = register("copper_pine_vine_rope_spool", new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
    public static final RotatedPillarBlock LAPIS_ROBINIA_VINE_ROPE_SPOOL = register("lapis_robinia_vine_rope_spool", new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
    public static final RotatedPillarBlock SHADOW_PINE_VINE_ROPE_SPOOL = register("shadow_pine_vine_rope_spool", new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
    public static final RotatedPillarBlock STELLAR_JUNGLE_TREE_VINE_ROPE_SPOOL = register("stellar_jungle_tree_vine_rope_spool", new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
    public static final RotatedPillarBlock SKY_CACTUS_FIBER_VINE_ROPE_SPOOL = register("sky_cactus_fiber_vine_rope_spool", new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));
    public static final RotatedPillarBlock GRAY_SHROOM_VINE_ROPE_SPOOL = register("gray_shroom_vine_rope_spool", new VineRopeSpoolBlock(BlockBehaviour.Properties.of().noOcclusion().isViewBlocking((state, blockGetter, pos) -> {return false;}).mapColor(MapColor.COLOR_BROWN).strength(1.2F).sound(SoundType.WOOD)));

    //fluids
    public static final LiquidBlock LIQUID_OF_THE_GODS = register("liquid_of_the_gods", new AerialHellFluidBlock(AerialHellFluids.LIQUID_OF_THE_GODS_STILL, BlockBehaviour.Properties.of().replaceable().lightLevel((state) -> 8)));

    public static <T extends Block> T register(String name, T block) {return Registry.register(BuiltInRegistries.BLOCK, AerialHell.id(name), block);}

    public static void load() {}
}
