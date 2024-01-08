package fr.factionbedrock.aerialhell.Block.Plants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;

public class AerialHellSaplingBlock extends SaplingBlock
{
	private final ResourceKey<ConfiguredFeature<?, ?>> giantTreeFeatureKey;
	@Nullable private final ResourceKey<ConfiguredFeature<?, ?>> hugeTreeFeatureKey;
	private final float hugeChange;

	public AerialHellSaplingBlock(TreeGrower treeIn, Properties properties, ResourceKey<ConfiguredFeature<?, ?>> giantTreeFeatureKey, ResourceKey<ConfiguredFeature<?, ?>> hugeTreeFeatureKey, float hugeChance)
	{
		super(treeIn, properties);
		this.giantTreeFeatureKey = giantTreeFeatureKey;
		this.hugeTreeFeatureKey = hugeTreeFeatureKey;
		this.hugeChange = hugeChance;
	}

	public AerialHellSaplingBlock(TreeGrower treeIn, Properties properties, ResourceKey<ConfiguredFeature<?, ?>> giantTreeFeatureKey)
	{
		this(treeIn, properties, giantTreeFeatureKey, null, 0.0F);
	}

	@Override public void advanceTree(ServerLevel serverLevel, BlockPos pos, BlockState state, RandomSource rand)
	{
		if (state.getValue(STAGE) == 0) {serverLevel.setBlock(pos, state.cycle(STAGE), 4);}
		else
		{
			GiantTreeGenerationDirection giantGenerationDirection = getGiantTreeDirection(serverLevel, pos, state);
			if (giantGenerationDirection != GiantTreeGenerationDirection.NONE)
			{
				ConfiguredFeature<?, ?> configuredfeature = getGiantTreeCFeature(serverLevel, rand);
				BlockPos generationPos = getGenerationPos(pos, giantGenerationDirection);
				if (configuredfeature.place(serverLevel, serverLevel.getChunkSource().getGenerator(), rand, generationPos)) {removeSaplingsAroundPos(serverLevel, generationPos);}
				else {serverLevel.setBlockAndUpdate(pos, state);}
			}
			else {super.advanceTree(serverLevel, pos, state, rand);}
		}
	}

	private BlockPos getGenerationPos(BlockPos saplingPos, GiantTreeGenerationDirection generationDirection)
	{
		BlockPos offsetVector = generationDirection.getOffsetVector();
		return offsetVector != null ? saplingPos.offset(offsetVector): saplingPos;
	}

	public ConfiguredFeature<?, ?> getGiantTreeCFeature(ServerLevel level, RandomSource rand)
	{
		ResourceKey<ConfiguredFeature<?, ?>> cfKey = (hugeTreeFeatureKey != null && rand.nextFloat() < hugeChange) ? hugeTreeFeatureKey : giantTreeFeatureKey;
		return level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE).getHolder(cfKey).orElse(null).get();
	}

	//Returns the direction of the generation of the giant tree (NONE if there is no generation possible)
	public GiantTreeGenerationDirection getGiantTreeDirection(ServerLevel serverLevel, BlockPos pos, BlockState state)
	{
		if (checkGiantTreeGenerationDirection(serverLevel, pos, GiantTreeGenerationDirection.MIDDLE)) {return GiantTreeGenerationDirection.MIDDLE;}
		else if (checkGiantTreeGenerationDirection(serverLevel, pos, GiantTreeGenerationDirection.NORTH)) {return GiantTreeGenerationDirection.NORTH;}
		else if (checkGiantTreeGenerationDirection(serverLevel, pos, GiantTreeGenerationDirection.SOUTH)) {return GiantTreeGenerationDirection.SOUTH;}
		else if (checkGiantTreeGenerationDirection(serverLevel, pos, GiantTreeGenerationDirection.WEST)) {return GiantTreeGenerationDirection.WEST;}
		else if (checkGiantTreeGenerationDirection(serverLevel, pos, GiantTreeGenerationDirection.EAST)) {return GiantTreeGenerationDirection.EAST;}
		else if (checkGiantTreeGenerationDirection(serverLevel, pos, GiantTreeGenerationDirection.NORTH_EAST)) {return GiantTreeGenerationDirection.NORTH_EAST;}
		else if (checkGiantTreeGenerationDirection(serverLevel, pos, GiantTreeGenerationDirection.NORTH_WEST)) {return GiantTreeGenerationDirection.NORTH_WEST;}
		else if (checkGiantTreeGenerationDirection(serverLevel, pos, GiantTreeGenerationDirection.SOUTH_EAST)) {return GiantTreeGenerationDirection.SOUTH_EAST;}
		else if (checkGiantTreeGenerationDirection(serverLevel, pos, GiantTreeGenerationDirection.SOUTH_WEST)) {return GiantTreeGenerationDirection.SOUTH_WEST;}
		else {return GiantTreeGenerationDirection.NONE;}
	}

	private boolean checkGiantTreeGenerationDirection(ServerLevel serverLevel, BlockPos pos, GiantTreeGenerationDirection direction)
	{
		AerialHellSaplingBlock sapling = (AerialHellSaplingBlock) this.asBlock();
		if (direction.getOffsetVector() == null) {return false;}
		BlockPos centerPos = pos.offset(direction.getOffsetVector());
		for (int x=-1; x<=1; x++)
		{
			for (int z=-1; z<=1; z++)
			{
				if (serverLevel.getBlockState(centerPos.offset(x, 0, z)).getBlock() != sapling) {return false;}
			}
		}
		return true;
	}

	private void removeSaplingsAroundPos(ServerLevel serverLevel, BlockPos centerPos)
	{
		AerialHellSaplingBlock sapling = (AerialHellSaplingBlock) this.asBlock();
		for (int x=-1; x<=1; x++)
		{
			for (int z=-1; z<=1; z++)
			{
				if (serverLevel.getBlockState(centerPos.offset(x, 0, z)).getBlock() == sapling)
				{
					serverLevel.setBlock(centerPos.offset(x, 0, z), Blocks.AIR.defaultBlockState(), 2);
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
