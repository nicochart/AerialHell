package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class AerialHellSaplingBlock extends SaplingBlock
{
	private final ResourceKey<ConfiguredFeature<?, ?>> giantTreeFeatureKey;
	@Nullable private final ResourceKey<ConfiguredFeature<?, ?>> hugeTreeFeatureKey;
	private final float hugeChange;

	public AerialHellSaplingBlock(TreeGrower treeIn, BlockBehaviour.Properties settings, ResourceKey<ConfiguredFeature<?, ?>> giantTreeFeatureKey, ResourceKey<ConfiguredFeature<?, ?>> hugeTreeFeatureKey, float hugeChance)
	{
		super(treeIn, settings);
		this.giantTreeFeatureKey = giantTreeFeatureKey;
		this.hugeTreeFeatureKey = hugeTreeFeatureKey;
		this.hugeChange = hugeChance;
	}

	public AerialHellSaplingBlock(TreeGrower treeIn, BlockBehaviour.Properties settings, ResourceKey<ConfiguredFeature<?, ?>> giantTreeFeatureKey)
	{
		this(treeIn, settings, giantTreeFeatureKey, null, 0.0F);
	}

	@Override public void advanceTree(ServerLevel serverWorld, BlockPos pos, BlockState state, RandomSource rand)
	{
		if (state.getValue(STAGE) == 0) {serverWorld.setBlock(pos, state.cycle(STAGE), 4);}
		else
		{
			GiantTreeGenerationDirection giantGenerationDirection = getGiantTreeDirection(serverWorld, pos, state);
			if (giantGenerationDirection != GiantTreeGenerationDirection.NONE)
			{
				ConfiguredFeature<?, ?> configuredfeature = getGiantTreeCFeature(serverWorld, rand);
				BlockPos generationPos = getGenerationPos(pos, giantGenerationDirection);
				if (configuredfeature.place(serverWorld, serverWorld.getChunkSource().getGenerator(), rand, generationPos)) {removeSaplingsAroundPos(serverWorld, generationPos);}
				else {serverWorld.setBlockAndUpdate(pos, state);}
			}
			else {super.advanceTree(serverWorld, pos, state, rand);}
		}
	}

	private BlockPos getGenerationPos(BlockPos saplingPos, GiantTreeGenerationDirection generationDirection)
	{
		BlockPos offsetVector = generationDirection.getOffsetVector();
		return offsetVector != null ? saplingPos.offset(offsetVector): saplingPos;
	}

	public ConfiguredFeature<?, ?> getGiantTreeCFeature(ServerLevel world, RandomSource rand)
	{
		ResourceKey<ConfiguredFeature<?, ?>> cfKey = (hugeTreeFeatureKey != null && rand.nextFloat() < hugeChange) ? hugeTreeFeatureKey : giantTreeFeatureKey;
		return world.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE).get(cfKey).orElse(null).value(); //TODO check
	}

	//Returns the direction of the generation of the giant tree (NONE if there is no generation possible)
	public GiantTreeGenerationDirection getGiantTreeDirection(ServerLevel serverWorld, BlockPos pos, BlockState state)
	{
		if (checkGiantTreeGenerationDirection(serverWorld, pos, GiantTreeGenerationDirection.MIDDLE)) {return GiantTreeGenerationDirection.MIDDLE;}
		else if (checkGiantTreeGenerationDirection(serverWorld, pos, GiantTreeGenerationDirection.NORTH)) {return GiantTreeGenerationDirection.NORTH;}
		else if (checkGiantTreeGenerationDirection(serverWorld, pos, GiantTreeGenerationDirection.SOUTH)) {return GiantTreeGenerationDirection.SOUTH;}
		else if (checkGiantTreeGenerationDirection(serverWorld, pos, GiantTreeGenerationDirection.WEST)) {return GiantTreeGenerationDirection.WEST;}
		else if (checkGiantTreeGenerationDirection(serverWorld, pos, GiantTreeGenerationDirection.EAST)) {return GiantTreeGenerationDirection.EAST;}
		else if (checkGiantTreeGenerationDirection(serverWorld, pos, GiantTreeGenerationDirection.NORTH_EAST)) {return GiantTreeGenerationDirection.NORTH_EAST;}
		else if (checkGiantTreeGenerationDirection(serverWorld, pos, GiantTreeGenerationDirection.NORTH_WEST)) {return GiantTreeGenerationDirection.NORTH_WEST;}
		else if (checkGiantTreeGenerationDirection(serverWorld, pos, GiantTreeGenerationDirection.SOUTH_EAST)) {return GiantTreeGenerationDirection.SOUTH_EAST;}
		else if (checkGiantTreeGenerationDirection(serverWorld, pos, GiantTreeGenerationDirection.SOUTH_WEST)) {return GiantTreeGenerationDirection.SOUTH_WEST;}
		else {return GiantTreeGenerationDirection.NONE;}
	}

	private boolean checkGiantTreeGenerationDirection(ServerLevel serverWorld, BlockPos pos, GiantTreeGenerationDirection direction)
	{
		AerialHellSaplingBlock sapling = (AerialHellSaplingBlock) this.asBlock();
		if (direction.getOffsetVector() == null) {return false;}
		BlockPos centerPos = pos.offset(direction.getOffsetVector());
		for (int x=-1; x<=1; x++)
		{
			for (int z=-1; z<=1; z++)
			{
				if (serverWorld.getBlockState(centerPos.offset(x, 0, z)).getBlock() != sapling) {return false;}
			}
		}
		return true;
	}

	private void removeSaplingsAroundPos(ServerLevel serverWorld, BlockPos centerPos)
	{
		AerialHellSaplingBlock sapling = (AerialHellSaplingBlock) this.asBlock();
		for (int x=-1; x<=1; x++)
		{
			for (int z=-1; z<=1; z++)
			{
				if (serverWorld.getBlockState(centerPos.offset(x, 0, z)).getBlock() == sapling)
				{
					serverWorld.setBlock(centerPos.offset(x, 0, z), Blocks.AIR.defaultBlockState(), 2);
				}
			}
		}
	}

	public static class GiantTreeGenerationDirection
	{
		public static GiantTreeGenerationDirection NONE = new GiantTreeGenerationDirection(null);
		public static GiantTreeGenerationDirection MIDDLE = new GiantTreeGenerationDirection(new BlockPos(0, 0, 0));
		public static GiantTreeGenerationDirection NORTH = new GiantTreeGenerationDirection(new BlockPos(0, 0, -1));
		public static GiantTreeGenerationDirection SOUTH = new GiantTreeGenerationDirection(new BlockPos(0, 0, 1));
		public static GiantTreeGenerationDirection EAST = new GiantTreeGenerationDirection(new BlockPos(1, 0, 0));
		public static GiantTreeGenerationDirection WEST = new GiantTreeGenerationDirection(new BlockPos(-1, 0, 0));
		public static GiantTreeGenerationDirection NORTH_EAST = new GiantTreeGenerationDirection(new BlockPos(1, 0, -1));
		public static GiantTreeGenerationDirection NORTH_WEST = new GiantTreeGenerationDirection(new BlockPos(-1, 0, -1));
		public static GiantTreeGenerationDirection SOUTH_EAST = new GiantTreeGenerationDirection(new BlockPos(1, 0, 1));
		public static GiantTreeGenerationDirection SOUTH_WEST = new GiantTreeGenerationDirection(new BlockPos(-1, 0, 1));

		@Nullable private final BlockPos offsetVector;
		private GiantTreeGenerationDirection(@Nullable BlockPos offsetVector) {this.offsetVector = offsetVector;}

		@Nullable public BlockPos getOffsetVector() {return offsetVector;}
		public GiantTreeGenerationDirection getOpposite()
		{
			if (this == MIDDLE) {return MIDDLE;}
			else if (this == NORTH) {return SOUTH;}
			else if (this == SOUTH) {return NORTH;}
			else if (this == EAST) {return WEST;}
			else if (this == WEST) {return EAST;}
			else if (this == NORTH_EAST) {return SOUTH_WEST;}
			else if (this == NORTH_WEST) {return SOUTH_EAST;}
			else if (this == SOUTH_EAST) {return NORTH_WEST;}
			else if (this == SOUTH_WEST) {return NORTH_EAST;}
			else /*if (this == NONE)*/ {return NONE;}
		}
	}
}
