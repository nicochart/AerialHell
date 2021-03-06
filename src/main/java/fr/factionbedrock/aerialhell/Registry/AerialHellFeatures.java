package fr.factionbedrock.aerialhell.Registry;

import java.util.OptionalInt;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.World.GenAerialHellOres;
import fr.factionbedrock.aerialhell.World.Features.AerialHellLakeFeature;
import fr.factionbedrock.aerialhell.World.Features.BlueSolidEtherCloudFeature;
import fr.factionbedrock.aerialhell.World.Features.CrystalBlobFeature;
import fr.factionbedrock.aerialhell.World.Features.CrystallizedFireFeature;
import fr.factionbedrock.aerialhell.World.Features.GiantCrystalBlobFeature;
import fr.factionbedrock.aerialhell.World.Features.GoldenSolidEtherCloudFeature;
import fr.factionbedrock.aerialhell.World.Features.GreenSolidEtherCloudFeature;
import fr.factionbedrock.aerialhell.World.Features.SkyCactusFeature;
import fr.factionbedrock.aerialhell.World.Features.SlipperySandSolidEtherFeature;
import fr.factionbedrock.aerialhell.World.Features.StellarCoarseFloorEllipsoidFeature;
import fr.factionbedrock.aerialhell.World.Features.StellarStoneCrystalBlobFeature;
import fr.factionbedrock.aerialhell.World.Features.SlipperySandFeature;
import fr.factionbedrock.aerialhell.World.Features.WhiteSolidEtherCloudFeature;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.DoublePlantBlockPlacer;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.MegaPineFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.GiantTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class AerialHellFeatures
{
	public static final class Configs
	{
		public static final BlockClusterFeatureConfig STELLAR_GRASS_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(AerialHellBlocksAndItems.STELLAR_GRASS.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(32).build();
		public static final BlockClusterFeatureConfig STELLAR_GRASS_BALL_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(AerialHellBlocksAndItems.STELLAR_GRASS_BALL.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(32).build();
		public static final BlockClusterFeatureConfig BRAMBLES_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(AerialHellBlocksAndItems.BRAMBLES.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(32).build();
		public static final BlockClusterFeatureConfig STELLAR_FERN_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(AerialHellBlocksAndItems.STELLAR_FERN.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(32).build();
		public static final BlockClusterFeatureConfig STELLAR_TALL_GRASS_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(AerialHellBlocksAndItems.STELLAR_TALL_GRASS.get().getDefaultState()), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();
		public static final BlockClusterFeatureConfig STELLAR_TALL_FERN_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(AerialHellBlocksAndItems.STELLAR_TALL_FERN.get().getDefaultState()), new DoublePlantBlockPlacer())).tries(64).func_227317_b_().build();
		public static final BlockClusterFeatureConfig STELLAR_DEAD_BUSH_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(AerialHellBlocksAndItems.STELLAR_DEAD_BUSH.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(4).build();
		public static final BlockClusterFeatureConfig AERIAL_BERRY_BUSH_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(AerialHellBlocksAndItems.AERIAL_BERRY_BUSH.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(8).build();
		public static final BlockClusterFeatureConfig SHADOW_GRASS_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(AerialHellBlocksAndItems.SHADOW_GRASS.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(32).build();
		public static final BlockClusterFeatureConfig SHADOW_GRASS_BALL_PATCH_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(AerialHellBlocksAndItems.SHADOW_GRASS_BALL.get().getDefaultState()), SimpleBlockPlacer.PLACER)).tries(32).build();
		
		public static final BlockClusterFeatureConfig AERIAL_HELL_BELLFLOWERS_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider())
                .addWeightedBlockstate(AerialHellBlocksAndItems.BELLFLOWER.get().getDefaultState(), 1), SimpleBlockPlacer.PLACER))
					.tries(64).whitelist(ImmutableSet.of(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get())).build();
		
		public static final BlockClusterFeatureConfig AERIAL_HELL_FLOWERS_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider())
                .addWeightedBlockstate(AerialHellBlocksAndItems.BLUE_FLOWER.get().getDefaultState(), 1)
                .addWeightedBlockstate(AerialHellBlocksAndItems.BLACK_ROSE.get().getDefaultState(), 1)
                .addWeightedBlockstate(AerialHellBlocksAndItems.AERIAL_BERRY_BUSH.get().getDefaultState(), 1), SimpleBlockPlacer.PLACER))
					.tries(64).whitelist(ImmutableSet.of(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get())).build();
		
		public static final BaseTreeFeatureConfig AERIAL_TREE_BASIC_CONFIG = (new BaseTreeFeatureConfig.Builder(
			    new SimpleBlockStateProvider(AerialHellBlocksAndItems.AERIAL_TREE_LOG.get().getDefaultState()),
			    new SimpleBlockStateProvider(AerialHellBlocksAndItems.AERIAL_TREE_LEAVES.get().getDefaultState()),
			    new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3), //rayon,d?calage,hauteur		func_242252_a()=fixed()
			    new StraightTrunkPlacer(4, 2, 0), //hauteur de base, randomizer1, randomizer2
			    new TwoLayerFeature(1, 0, 1)
			    	)).setIgnoreVines().build();
		
		public static final BaseTreeFeatureConfig AERIAL_TREE_FOREST_CONFIG = (new BaseTreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(AerialHellBlocksAndItems.AERIAL_TREE_LOG.get().getDefaultState()),
		        new SimpleBlockStateProvider(AerialHellBlocksAndItems.AERIAL_TREE_LEAVES.get().getDefaultState()),
		        new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3),
		        new StraightTrunkPlacer(5, 3, 0),
		        new TwoLayerFeature(1, 0, 1)
		        	)).setIgnoreVines().build();
		
		public static final BaseTreeFeatureConfig COPPER_PINE_CONFIG = (new BaseTreeFeatureConfig.Builder(
			    new SimpleBlockStateProvider(AerialHellBlocksAndItems.COPPER_PINE_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(AerialHellBlocksAndItems.COPPER_PINE_LEAVES.get().getDefaultState()),
                new SpruceFoliagePlacer(FeatureSpread.func_242253_a(3, 1), FeatureSpread.func_242253_a(0, 2), FeatureSpread.func_242253_a(1, 1)), //rayon,d?calage,hauteur		func_242252_a()=fixed()   2,1 0,2 1,1 sapin basique
                new StraightTrunkPlacer(5, 2, 1),
                new TwoLayerFeature(3, 0, 2)
                	)).setIgnoreVines().build();
		
		public static final BaseTreeFeatureConfig LAPIS_ROBINIA_CONFIG = (new BaseTreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(AerialHellBlocksAndItems.LAPIS_ROBINIA_LOG.get().getDefaultState()),
				new SimpleBlockStateProvider(AerialHellBlocksAndItems.LAPIS_ROBINIA_LEAVES.get().getDefaultState()),
				new AcaciaFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0)),
				new ForkyTrunkPlacer(5, 2, 2),
				new TwoLayerFeature(1, 0, 2)
					)).setIgnoreVines().build();
		
		public static final BaseTreeFeatureConfig GOLDEN_BEECH_CONFIG = (new BaseTreeFeatureConfig.Builder(
			    new SimpleBlockStateProvider(AerialHellBlocksAndItems.GOLDEN_BEECH_LOG.get().getDefaultState()),
			    new SimpleBlockStateProvider(AerialHellBlocksAndItems.GOLDEN_BEECH_LEAVES.get().getDefaultState()),
			    new FancyFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(4), 4),
			    new FancyTrunkPlacer(3, 11, 0),
			    new TwoLayerFeature(0, 0, 0, OptionalInt.of(4))
			    	)).setIgnoreVines().func_236702_a_(Heightmap.Type.MOTION_BLOCKING).build();
		
		public static final BaseTreeFeatureConfig SHADOW_PINE_CONFIG = (new BaseTreeFeatureConfig.Builder(
			    new SimpleBlockStateProvider(AerialHellBlocksAndItems.SHADOW_PINE_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(AerialHellBlocksAndItems.SHADOW_PINE_LEAVES.get().getDefaultState()),
                new SpruceFoliagePlacer(FeatureSpread.func_242253_a(2, 1), FeatureSpread.func_242253_a(0, 2), FeatureSpread.func_242253_a(1, 1)),
                new StraightTrunkPlacer(6, 2, 1),
                new TwoLayerFeature(3, 0, 2)
                	)).setIgnoreVines().build();
		
		public static final BaseTreeFeatureConfig PURPLE_SHADOW_PINE_CONFIG = (new BaseTreeFeatureConfig.Builder(
			    new SimpleBlockStateProvider(AerialHellBlocksAndItems.SHADOW_PINE_LOG.get().getDefaultState()),
                new SimpleBlockStateProvider(AerialHellBlocksAndItems.PURPLE_SHADOW_PINE_LEAVES.get().getDefaultState()),
                new SpruceFoliagePlacer(FeatureSpread.func_242253_a(2, 1), FeatureSpread.func_242253_a(0, 2), FeatureSpread.func_242253_a(1, 1)),
                new StraightTrunkPlacer(6, 2, 1),
                new TwoLayerFeature(3, 0, 2)
                	)).setIgnoreVines().build();
		
		public static final BaseTreeFeatureConfig MEGA_SHADOW_PINE_CONFIG = (new BaseTreeFeatureConfig.Builder(
		    	new SimpleBlockStateProvider(AerialHellBlocksAndItems.SHADOW_PINE_LOG.get().getDefaultState()),
		    	new SimpleBlockStateProvider(AerialHellBlocksAndItems.SHADOW_PINE_LEAVES.get().getDefaultState()),
		    	new MegaPineFoliagePlacer(FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(0), FeatureSpread.func_242253_a(3, 4)),
		    	new GiantTrunkPlacer(13, 2, 14),
		    	new TwoLayerFeature(1, 1, 2)
					)).setDecorators(ImmutableList.of(new AlterGroundTreeDecorator(new SimpleBlockStateProvider(AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get().getDefaultState())))).build();
		
		public static final BaseTreeFeatureConfig MEGA_PURPLE_SHADOW_PINE_CONFIG = (new BaseTreeFeatureConfig.Builder(
				new SimpleBlockStateProvider(AerialHellBlocksAndItems.SHADOW_PINE_LOG.get().getDefaultState()),
				new SimpleBlockStateProvider(AerialHellBlocksAndItems.PURPLE_SHADOW_PINE_LEAVES.get().getDefaultState()),
				new MegaPineFoliagePlacer(FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(0), FeatureSpread.func_242253_a(13, 4)),
				new GiantTrunkPlacer(13, 2, 14),
				new TwoLayerFeature(1, 1, 2)
					)).setDecorators(ImmutableList.of(new AlterGroundTreeDecorator(new SimpleBlockStateProvider(AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get().getDefaultState())))).build();
		
		public static final BaseTreeFeatureConfig CRYSTALLIZED_TREE_BASIC_CONFIG = (new BaseTreeFeatureConfig.Builder(
			    new SimpleBlockStateProvider(AerialHellBlocksAndItems.AERIAL_TREE_LOG.get().getDefaultState()),
			    new SimpleBlockStateProvider(AerialHellBlocksAndItems.CRYSTALLIZED_LEAVES.get().getDefaultState()),
			    new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(0), 3), //rayon,d?calage,hauteur		func_242252_a()=fixed()
			    new StraightTrunkPlacer(4, 2, 0), //hauteur de base, randomizer1, randomizer2
			    new TwoLayerFeature(1, 0, 1)
			    	)).setIgnoreVines().build();
			    
	}
	
	public static StructureFeature<?, ?> CONFIGURED_OVERWORLD_ABANDONNED_PORTAL_STRUCTURE = AerialHellStructures.OVERWORLD_ABANDONNED_PORTAL_STRUCTURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);
	public static StructureFeature<?, ?> CONFIGURED_MUD_DUNGEON_STRUCTURE = AerialHellStructures.MUD_DUNGEON_STRUCTURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);
	public static StructureFeature<?, ?> CONFIGURED_LUNATIC_TEMPLE_STRUCTURE = AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);
	public static StructureFeature<?, ?> CONFIGURED_GOLDEN_NETHER_PRISON_STRUCTURE = AerialHellStructures.GOLDEN_NETHER_PRISON_STRUCTURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);
	public static StructureFeature<?, ?> CONFIGURED_STELLAR_STONE_BRICKS_TOWER_STRUCTURE = AerialHellStructures.STELLAR_STONE_BRICKS_TOWER_STRUCTURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);
	public static StructureFeature<?, ?> CONFIGURED_COPPER_PINE_COTTAGE_STRUCTURE = AerialHellStructures.COPPER_PINE_COTTAGE_STRUCTURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);
	public static StructureFeature<?, ?> CONFIGURED_SLIPPERY_SAND_OCEAN_ABANDONNED_STRUCTURE = AerialHellStructures.SLIPPERY_SAND_OCEAN_ABANDONNED_STRUCTURE.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG);
    
    public static ConfiguredFeature<?, ?> AERIAL_HELL_WATER_LAKE = new AerialHellLakeFeature(BlockStateFeatureConfig.field_236455_a_).withConfiguration(new BlockStateFeatureConfig(Blocks.WATER.getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(4)));
    
    public static ConfiguredFeature<?, ?> STELLAR_GRASS = Feature.RANDOM_PATCH.withConfiguration(Configs.STELLAR_GRASS_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(2);
    public static ConfiguredFeature<?, ?> STELLAR_GRASS_BALL = Feature.RANDOM_PATCH.withConfiguration(Configs.STELLAR_GRASS_BALL_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(2);
    public static ConfiguredFeature<?, ?> BRAMBLES = Feature.RANDOM_PATCH.withConfiguration(Configs.BRAMBLES_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(2);
    public static ConfiguredFeature<?, ?> STELLAR_FERN = Feature.RANDOM_PATCH.withConfiguration(Configs.STELLAR_FERN_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(2);
    public static ConfiguredFeature<?, ?> STELLAR_TALL_GRASS = Feature.RANDOM_PATCH.withConfiguration(Configs.STELLAR_TALL_GRASS_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(2);
    public static ConfiguredFeature<?, ?> STELLAR_TALL_FERN = Feature.RANDOM_PATCH.withConfiguration(Configs.STELLAR_TALL_FERN_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(2);
    public static ConfiguredFeature<?, ?> STELLAR_DEAD_BUSH = Feature.RANDOM_PATCH.withConfiguration(Configs.STELLAR_DEAD_BUSH_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT);
    public static ConfiguredFeature<?, ?> AERIAL_BERRY_BUSH_PATCH = Feature.RANDOM_PATCH.withConfiguration(Configs.AERIAL_BERRY_BUSH_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(2);
    public static ConfiguredFeature<?, ?> SHADOW_GRASS = Feature.RANDOM_PATCH.withConfiguration(Configs.SHADOW_GRASS_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(2);
    public static ConfiguredFeature<?, ?> SHADOW_GRASS_BALL = Feature.RANDOM_PATCH.withConfiguration(Configs.SHADOW_GRASS_BALL_PATCH_CONFIG).withPlacement(Features.Placements.PATCH_PLACEMENT).func_242731_b(2);
    
    public static ConfiguredFeature<?, ?> AERIAL_HELL_FLOWERS = Feature.FLOWER.withConfiguration(Configs.AERIAL_HELL_FLOWERS_CONFIG).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(2);
    public static ConfiguredFeature<?, ?> AERIAL_HELL_BELLFLOWERS = Feature.FLOWER.withConfiguration(Configs.AERIAL_HELL_BELLFLOWERS_CONFIG).withPlacement(Features.Placements.VEGETATION_PLACEMENT).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).func_242731_b(10);
    public static ConfiguredFeature<?, ?> AERIAL_TREE_BASIC = Feature.TREE.withConfiguration(Configs.AERIAL_TREE_BASIC_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT);
    public static ConfiguredFeature<?, ?> AERIAL_TREE_FOREST = Feature.TREE.withConfiguration(Configs.AERIAL_TREE_FOREST_CONFIG).range(256).square().func_242731_b(10);
    public static ConfiguredFeature<?, ?> COPPER_PINE_BASIC = Feature.TREE.withConfiguration(Configs.COPPER_PINE_CONFIG).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.01F, 1)));
    public static ConfiguredFeature<?, ?> COPPER_PINE_FOREST = Feature.TREE.withConfiguration(Configs.COPPER_PINE_CONFIG).range(256).square().func_242731_b(8);
    public static ConfiguredFeature<?, ?> LAPIS_ROBINIA_RARE = Feature.TREE.withConfiguration(Configs.LAPIS_ROBINIA_CONFIG).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.01F, 1)));
    public static ConfiguredFeature<?, ?> LAPIS_ROBINIA_BASIC = Feature.TREE.withConfiguration(Configs.LAPIS_ROBINIA_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT);
    public static ConfiguredFeature<?, ?> SHADOW_PINE_BASIC = Feature.TREE.withConfiguration(Configs.SHADOW_PINE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT);
    public static ConfiguredFeature<?, ?> PURPLE_SHADOW_PINE_BASIC = Feature.TREE.withConfiguration(Configs.PURPLE_SHADOW_PINE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT);
    public static ConfiguredFeature<?, ?> SHADOW_PINE_FOREST = Feature.TREE.withConfiguration(Configs.SHADOW_PINE_CONFIG).range(256).square().func_242731_b(5);
    public static ConfiguredFeature<?, ?> PURPLE_SHADOW_PINE_FOREST = Feature.TREE.withConfiguration(Configs.PURPLE_SHADOW_PINE_CONFIG).range(256).square().func_242731_b(5);
    public static ConfiguredFeature<?, ?> MEGA_SHADOW_PINE_BASIC = Feature.TREE.withConfiguration(Configs.MEGA_SHADOW_PINE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT);
    public static ConfiguredFeature<?, ?> MEGA_PURPLE_SHADOW_PINE_BASIC = Feature.TREE.withConfiguration(Configs.MEGA_PURPLE_SHADOW_PINE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT);
    public static ConfiguredFeature<?, ?> GOLDEN_BEECH_RARE = Feature.TREE.withConfiguration(Configs.GOLDEN_BEECH_CONFIG).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.01F, 1)));
    public static ConfiguredFeature<?, ?> GOLDEN_BEECH_BASIC = Feature.TREE.withConfiguration(Configs.GOLDEN_BEECH_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT);
    public static ConfiguredFeature<?, ?> CRYSTALLIZED_TREE_BASIC = Feature.TREE.withConfiguration(Configs.CRYSTALLIZED_TREE_BASIC_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT);
    
    public static ConfiguredFeature<?, ?> SKY_CACTUS_PLAIN = new SkyCactusFeature(NoFeatureConfig.field_236558_a_).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(256).square().func_242731_b(50);
    public static ConfiguredFeature<?, ?> SKY_CACTUS_OCEAN = new SkyCactusFeature(NoFeatureConfig.field_236558_a_).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(256).square().func_242731_b(25);
    		
    public static ConfiguredFeature<?, ?> WHITE_SOLID_ETHER = new WhiteSolidEtherCloudFeature(NoFeatureConfig.field_236558_a_).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(60).square().chance(24);
    public static ConfiguredFeature<?, ?> BLUE_SOLID_ETHER = new BlueSolidEtherCloudFeature(NoFeatureConfig.field_236558_a_).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(140).square().chance(28);
    public static ConfiguredFeature<?, ?> GOLDEN_SOLID_ETHER = new GoldenSolidEtherCloudFeature(NoFeatureConfig.field_236558_a_).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(190).square().chance(32);
    public static ConfiguredFeature<?, ?> GREEN_SOLID_ETHER = new GreenSolidEtherCloudFeature(NoFeatureConfig.field_236558_a_).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(160).square().chance(5);
    public static ConfiguredFeature<?, ?> SLIPPERY_SAND_SOLID_ETHER = new SlipperySandSolidEtherFeature(NoFeatureConfig.field_236558_a_).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(256).square().func_242731_b(20);
    
    public static ConfiguredFeature<?, ?> STELLAR_COARSE_FLOOR_ELLIPSOID = new StellarCoarseFloorEllipsoidFeature(NoFeatureConfig.field_236558_a_).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(256).square().func_242731_b(100);
    public static ConfiguredFeature<?, ?> SLIPPERY_SAND = new SlipperySandFeature(NoFeatureConfig.field_236558_a_).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(256).square().func_242731_b(20);
    public static ConfiguredFeature<?, ?> CRYSTAL_BLOB = new CrystalBlobFeature(NoFeatureConfig.field_236558_a_).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(256).square().func_242731_b(10);
    public static ConfiguredFeature<?, ?> STELLAR_STONE_CRYSTAL_BLOB = new StellarStoneCrystalBlobFeature(NoFeatureConfig.field_236558_a_).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(256).square().func_242731_b(100);
    public static ConfiguredFeature<?, ?> CRYSTALLIZED_FIRE = new CrystallizedFireFeature(NoFeatureConfig.field_236558_a_).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).range(256).square().func_242731_b(30);
    public static ConfiguredFeature<?, ?> GIANT_CRYSTAL_BLOB = new GiantCrystalBlobFeature(NoFeatureConfig.field_236558_a_).withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT);
    
    /*
    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT) = placement basique
    .range(256).square().func_242731_b(1) = semble similaire au placement basique
  	.range(256).square().func_242731_b(10) = beaucoup beaucoup d'arbres (vraiment beaucoup)
    */
    
    public static void registerConfiguredFeaturesAndStructures()
    {
        Registry<StructureFeature<?, ?>> STregistry = WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE;
        Registry<ConfiguredFeature<?, ?>> CFregistry = WorldGenRegistries.CONFIGURED_FEATURE;
        Registry.register(STregistry, new ResourceLocation(AerialHell.MODID, "configured_overworld_abandonned_portal_structure"), CONFIGURED_OVERWORLD_ABANDONNED_PORTAL_STRUCTURE);
        Registry.register(STregistry, new ResourceLocation(AerialHell.MODID, "configured_mud_dungeon_structure"), CONFIGURED_MUD_DUNGEON_STRUCTURE);
        Registry.register(STregistry, new ResourceLocation(AerialHell.MODID, "configured_lunatic_temple_structure"), CONFIGURED_LUNATIC_TEMPLE_STRUCTURE);
        Registry.register(STregistry, new ResourceLocation(AerialHell.MODID, "configured_golden_nether_prison_structure"), CONFIGURED_GOLDEN_NETHER_PRISON_STRUCTURE);
        Registry.register(STregistry, new ResourceLocation(AerialHell.MODID, "configured_stellar_stone_bricks_tower_structure"), CONFIGURED_STELLAR_STONE_BRICKS_TOWER_STRUCTURE);
        Registry.register(STregistry, new ResourceLocation(AerialHell.MODID, "configured_copper_pine_cottage_structure"), CONFIGURED_COPPER_PINE_COTTAGE_STRUCTURE);
        Registry.register(STregistry, new ResourceLocation(AerialHell.MODID, "configured_slippery_sand_ocean_abandonned_structure"), CONFIGURED_SLIPPERY_SAND_OCEAN_ABANDONNED_STRUCTURE);
        
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "stellar_portal_frame_ore"), GenAerialHellOres.STELLAR_PORTAL_FRAME_ORE);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "iron_stellar_ore"), GenAerialHellOres.IRON_STELLAR_ORE);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "gold_stellar_ore"), GenAerialHellOres.GOLD_STELLAR_ORE);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "diamond_stellar_ore"), GenAerialHellOres.DIAMOND_STELLAR_ORE);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "fluorite_ore"), GenAerialHellOres.FLUORITE_ORE);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "ruby_ore"), GenAerialHellOres.RUBY_ORE);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "azurite_ore"), GenAerialHellOres.AZURITE_ORE);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "volucite_ore"), GenAerialHellOres.VOLUCITE_ORE);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "obsidian_ore"), GenAerialHellOres.OBSIDIAN_ORE);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "glaucophanite_ore"), GenAerialHellOres.GLAUCOPHANITE_ORE);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "stellar_dirt_ore"), GenAerialHellOres.STELLAR_DIRT_ORE);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "stellar_coarse_dirt_ore"), GenAerialHellOres.STELLAR_COARSE_DIRT_ORE);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "stellar_clay_ore"), GenAerialHellOres.STELLAR_CLAY_ORE);
        
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "aerial_hell_water_lake"), AERIAL_HELL_WATER_LAKE);
        
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "stellar_grass"), STELLAR_GRASS);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "stellar_grass_ball"), STELLAR_GRASS_BALL);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "brambles"), BRAMBLES);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "stellar_tall_grass"), STELLAR_TALL_GRASS);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "stellar_fern"), STELLAR_FERN);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "stellar_tall_fern"), STELLAR_TALL_FERN);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "stellar_dead_bush"), STELLAR_DEAD_BUSH);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "aerial_berry_bush_patch"), AERIAL_BERRY_BUSH_PATCH);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "shadow_grass"), SHADOW_GRASS);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "shadow_grass_ball"), SHADOW_GRASS_BALL);
        
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "aerial_hell_flowers"), AERIAL_HELL_FLOWERS);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "aerial_hell_bellflowers"), AERIAL_HELL_BELLFLOWERS);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "aerial_tree_basic"), AERIAL_TREE_BASIC);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "aerial_tree_forest"), AERIAL_TREE_FOREST);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "copper_pine_basic"), COPPER_PINE_BASIC);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "copper_pine_forest"), COPPER_PINE_FOREST);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "lapis_robinia_rare"), LAPIS_ROBINIA_RARE);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "lapis_robinia_basic"), LAPIS_ROBINIA_BASIC);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "golden_beech_rare"), GOLDEN_BEECH_RARE);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "golden_beech_basic"), GOLDEN_BEECH_BASIC);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "shadow_pine_basic"), SHADOW_PINE_BASIC);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "purple_shadow_pine_basic"), PURPLE_SHADOW_PINE_BASIC);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "shadow_pine_forest"), SHADOW_PINE_FOREST);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "purple_shadow_pine_forest"), PURPLE_SHADOW_PINE_FOREST);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "mega_shadow_pine_basic"), MEGA_SHADOW_PINE_BASIC);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "mega_purple_shadow_pine_basic"), MEGA_PURPLE_SHADOW_PINE_BASIC);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "crystallized_tree_basic"), CRYSTALLIZED_TREE_BASIC);
        
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "sky_cactus_plain"), SKY_CACTUS_PLAIN);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "sky_cactus_ocean"), SKY_CACTUS_OCEAN);
        
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "white_solid_ether"), WHITE_SOLID_ETHER);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "blue_solid_ether"), BLUE_SOLID_ETHER);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "golden_solid_ether"), GOLDEN_SOLID_ETHER);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "green_solid_ether"), GOLDEN_SOLID_ETHER);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "slippery_sand_solid_ether"), SLIPPERY_SAND_SOLID_ETHER);
        
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "stellar_coarse_floor_ellipsoid"), STELLAR_COARSE_FLOOR_ELLIPSOID);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "slippery_sand"), SLIPPERY_SAND);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "crystal_blob"), CRYSTAL_BLOB);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "stellar_stone_crystal_blob"), STELLAR_STONE_CRYSTAL_BLOB);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "cristallized_fire"), CRYSTALLIZED_FIRE);
        Registry.register(CFregistry, new ResourceLocation(AerialHell.MODID, "giant_crystal_blob"), GIANT_CRYSTAL_BLOB);
        
        FlatGenerationSettings.STRUCTURES.put(AerialHellStructures.MUD_DUNGEON_STRUCTURE.get(), CONFIGURED_MUD_DUNGEON_STRUCTURE);
    }
}