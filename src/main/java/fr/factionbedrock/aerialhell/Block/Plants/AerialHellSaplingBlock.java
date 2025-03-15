package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.block.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class AerialHellSaplingBlock extends SaplingBlock
{
	private final RegistryKey<ConfiguredFeature<?, ?>> giantTreeFeatureKey;
	@Nullable private final RegistryKey<ConfiguredFeature<?, ?>> hugeTreeFeatureKey;
	private final float hugeChange;

	public AerialHellSaplingBlock(SaplingGenerator treeIn, AbstractBlock.Settings settings, RegistryKey<ConfiguredFeature<?, ?>> giantTreeFeatureKey, RegistryKey<ConfiguredFeature<?, ?>> hugeTreeFeatureKey, float hugeChance)
	{
		super(treeIn, settings);
		this.giantTreeFeatureKey = giantTreeFeatureKey;
		this.hugeTreeFeatureKey = hugeTreeFeatureKey;
		this.hugeChange = hugeChance;
	}

	public AerialHellSaplingBlock(SaplingGenerator treeIn, AbstractBlock.Settings settings, RegistryKey<ConfiguredFeature<?, ?>> giantTreeFeatureKey)
	{
		this(treeIn, settings, giantTreeFeatureKey, null, 0.0F);
	}

	@Override public void generate(ServerWorld serverWorld, BlockPos pos, BlockState state, Random rand)
	{
		if (state.get(STAGE) == 0) {serverWorld.setBlockState(pos, state.cycle(STAGE), 4);}
		else
		{
			GiantTreeGenerationDirection giantGenerationDirection = getGiantTreeDirection(serverWorld, pos, state);
			if (giantGenerationDirection != GiantTreeGenerationDirection.NONE)
			{
				ConfiguredFeature<?, ?> configuredfeature = getGiantTreeCFeature(serverWorld, rand);
				BlockPos generationPos = getGenerationPos(pos, giantGenerationDirection);
				if (configuredfeature.generate(serverWorld, serverWorld.getChunkManager().getChunkGenerator(), rand, generationPos)) {removeSaplingsAroundPos(serverWorld, generationPos);}
				else {serverWorld.setBlockState(pos, state);}
			}
			else {super.generate(serverWorld, pos, state, rand);}
		}
	}

	private BlockPos getGenerationPos(BlockPos saplingPos, GiantTreeGenerationDirection generationDirection)
	{
		BlockPos offsetVector = generationDirection.getOffsetVector();
		return offsetVector != null ? saplingPos.add(offsetVector): saplingPos;
	}

	public ConfiguredFeature<?, ?> getGiantTreeCFeature(ServerWorld world, Random rand)
	{
		RegistryKey<ConfiguredFeature<?, ?>> cfKey = (hugeTreeFeatureKey != null && rand.nextFloat() < hugeChange) ? hugeTreeFeatureKey : giantTreeFeatureKey;
		return world.getRegistryManager().getOrThrow(RegistryKeys.CONFIGURED_FEATURE).getOptional(cfKey).orElse(null).value(); //TODO check
	}

	//Returns the direction of the generation of the giant tree (NONE if there is no generation possible)
	public GiantTreeGenerationDirection getGiantTreeDirection(ServerWorld serverWorld, BlockPos pos, BlockState state)
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

	private boolean checkGiantTreeGenerationDirection(ServerWorld serverWorld, BlockPos pos, GiantTreeGenerationDirection direction)
	{
		AerialHellSaplingBlock sapling = (AerialHellSaplingBlock) this.asBlock();
		if (direction.getOffsetVector() == null) {return false;}
		BlockPos centerPos = pos.add(direction.getOffsetVector());
		for (int x=-1; x<=1; x++)
		{
			for (int z=-1; z<=1; z++)
			{
				if (serverWorld.getBlockState(centerPos.add(x, 0, z)).getBlock() != sapling) {return false;}
			}
		}
		return true;
	}

	private void removeSaplingsAroundPos(ServerWorld serverWorld, BlockPos centerPos)
	{
		AerialHellSaplingBlock sapling = (AerialHellSaplingBlock) this.asBlock();
		for (int x=-1; x<=1; x++)
		{
			for (int z=-1; z<=1; z++)
			{
				if (serverWorld.getBlockState(centerPos.add(x, 0, z)).getBlock() == sapling)
				{
					serverWorld.setBlockState(centerPos.add(x, 0, z), Blocks.AIR.getDefaultState(), 2);
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
