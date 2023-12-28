package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Block.LargeDeadLogBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class LargeDeadLogFeature extends Feature<NoneFeatureConfiguration>
{
	private Supplier<LargeDeadLogBlock> block;
	public LargeDeadLogFeature(Codec<NoneFeatureConfiguration> codec, Supplier<LargeDeadLogBlock> block) {super(codec); this.block = block;}

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		BlockPos pos = findPosForPlacement(context);
		if (pos == null) {return false;}
		else
		{
			generate(context, pos);
			return true;
		}
	}

	private void generate(FeaturePlaceContext<NoneFeatureConfiguration> context, BlockPos pos)
	{
		RandomSource rand = context.random(); WorldGenLevel level = context.level();
		Direction generationDirection = rand.nextInt(2) == 0 ? Direction.WEST : Direction.NORTH;
		int lenght = 3 + rand.nextInt(5);

		BlockPos.MutableBlockPos placementPos = pos.mutable();
		for (int i=0; i<lenght; i++)
		{
			place4blocks(level, placementPos, generationDirection);
			placementPos.move(generationDirection, 1);
		}
	}

	protected void place4blocks(WorldGenLevel level, BlockPos.MutableBlockPos pos, Direction generationDirection)
	{
		BlockState aboveLeft = block.get().getStateForPlacement(generationDirection.getCounterClockWise(), Half.TOP);
		BlockState aboveRight = block.get().getStateForPlacement(generationDirection.getClockWise(), Half.TOP);
		BlockState belowLeft = block.get().getStateForPlacement(generationDirection.getCounterClockWise(), Half.BOTTOM);
		BlockState belowRight = block.get().getStateForPlacement(generationDirection.getClockWise(), Half.BOTTOM);

		tryPlacingBlock(level, pos, belowRight);
		pos.move(generationDirection.getCounterClockWise(), 1);
		tryPlacingBlock(level, pos, belowLeft);
		pos.move(Direction.UP, 1);
		tryPlacingBlock(level, pos, aboveLeft);
		pos.move(generationDirection.getClockWise(), 1);
		tryPlacingBlock(level, pos, aboveRight);
		pos.move(Direction.DOWN, 1);
	}

	@Nullable protected BlockPos findPosForPlacement(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		BlockPos testedPos, featureCenter = FeatureHelper.getFeatureCenter(context);
		WorldGenLevel level = context.level();
		if (hasSupportToGenerate(level, context.origin())) {return context.origin();}
		int t, i;
		for (t=0; t<10; t++)
		{
			i=0; testedPos = FeatureHelper.getRandomPosInFeatureRegion(featureCenter, context.random(), 12, 20);
			while (level.getBlockState(testedPos.below()).isAir() && i++ < 10 && testedPos.getY() > 10) {testedPos = testedPos.below();}
			if (hasSupportToGenerate(level, testedPos)) {return testedPos;}
		}
		return null;
	}

	private boolean hasSupportToGenerate(WorldGenLevel level, BlockPos pos)
	{
		return level.isEmptyBlock(pos) && level.getBlockState(pos.below()).is(AerialHellTags.Blocks.STELLAR_DIRT);
	}

	private void tryPlacingBlock(WorldGenLevel level, BlockPos.MutableBlockPos pos, BlockState state) {if (isReplaceable(level, pos)) {level.setBlock(pos, state, 0);}}

	private boolean isReplaceable(WorldGenLevel level, BlockPos blockPos)
	{
		BlockState previousBlock = level.getBlockState(blockPos);
		return previousBlock.isAir() || previousBlock.is(AerialHellTags.Blocks.FEATURE_CAN_REPLACE) || previousBlock.is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get());
	}
}