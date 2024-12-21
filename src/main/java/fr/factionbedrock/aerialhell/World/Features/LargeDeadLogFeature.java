package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Block.LargeDeadLogBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class LargeDeadLogFeature extends Feature<DefaultFeatureConfig>
{
	private final Supplier<LargeDeadLogBlock> block;
	public LargeDeadLogFeature(Codec<DefaultFeatureConfig> codec, Supplier<LargeDeadLogBlock> block) {super(codec); this.block = block;}

	@Override public boolean generate(FeatureContext<DefaultFeatureConfig> context)
	{
		BlockPos pos = findPosForPlacement(context);
		if (pos == null) {return false;}
		else
		{
			generate(context, pos);
			return true;
		}
	}

	private void generate(FeatureContext<DefaultFeatureConfig> context, BlockPos pos)
	{
		Random rand = context.getRandom(); StructureWorldAccess level = context.getWorld();
		Direction generationDirection = rand.nextInt(2) == 0 ? Direction.WEST : Direction.NORTH;
		int lenght = 3 + rand.nextInt(5);

		BlockPos.Mutable placementPos = pos.mutableCopy();
		int percentageSupported = percentageOfLogThatHaveSupport(level, pos, generationDirection, lenght);
		if (percentageSupported < 50 && percentageOfLogThatHaveSupport(level, pos.down(), generationDirection, lenght) > percentageSupported) {placementPos.move(Direction.DOWN, 1);}

		for (int i=0; i<lenght; i++)
		{
			place4blocks(context, placementPos, generationDirection);
			placementPos.move(generationDirection, 1);
		}
	}

	protected void place4blocks(FeatureContext<DefaultFeatureConfig> context, BlockPos.Mutable pos, Direction generationDirection)
	{
		StructureWorldAccess level = context.getWorld(); Random rand = context.getRandom();
		BlockState aboveLeft = block.get().getStateForPlacement(generationDirection.rotateYCounterclockwise(), BlockHalf.TOP);
		BlockState aboveRight = block.get().getStateForPlacement(generationDirection.rotateYClockwise(), BlockHalf.TOP);
		BlockState belowLeft = block.get().getStateForPlacement(generationDirection.rotateYCounterclockwise(), BlockHalf.BOTTOM);
		BlockState belowRight = block.get().getStateForPlacement(generationDirection.rotateYClockwise(), BlockHalf.BOTTOM);

		tryPlacingBlock(level, pos, belowRight, rand);
		pos.move(generationDirection.rotateYCounterclockwise(), 1);
		tryPlacingBlock(level, pos, belowLeft, rand);
		pos.move(Direction.UP, 1);
		tryPlacingBlock(level, pos, aboveLeft, rand);
		pos.move(generationDirection.rotateYClockwise(), 1);
		tryPlacingBlock(level, pos, aboveRight, rand);
		pos.move(Direction.DOWN, 1);
	}

	@Nullable protected BlockPos findPosForPlacement(FeatureContext<DefaultFeatureConfig> context)
	{
		BlockPos testedPos, featureCenter = FeatureHelper.getFeatureCenter(context);
		StructureWorldAccess level = context.getWorld();
		if (hasSupportToGenerate(level, context.getOrigin())) {return context.getOrigin();}
		int t, i;
		for (t=0; t<10; t++)
		{
			i=0; testedPos = FeatureHelper.getRandomPosInFeatureRegion(featureCenter, context.getRandom(), 12, 20);
			while (level.getBlockState(testedPos.down()).isAir() && i++ < 10 && testedPos.getY() > 10) {testedPos = testedPos.down();}
			if (hasSupportToGenerate(level, testedPos)) {return testedPos;}
		}
		return null;
	}

	private int percentageOfLogThatHaveSupport(StructureWorldAccess level, BlockPos pos, Direction generationDirection, int lenght)
	{
		int i, count = 0, reachableTotal = lenght*2;
		for (i=0; i<lenght; i++)
		{
			if (hasSupportToGenerate(level, pos.offset(generationDirection, i))) {count++;}
			if (hasSupportToGenerate(level, pos.offset(generationDirection, i).offset(generationDirection.rotateYCounterclockwise(), 1))) {count++;}
		}
		return (int) (100.0 * count / reachableTotal);
	}

	private boolean hasSupportToGenerate(StructureWorldAccess level, BlockPos pos)
	{
		return level.isAir(pos) && level.getBlockState(pos.down()).isIn(AerialHellTags.Blocks.STELLAR_DIRT);
	}

	private void tryPlacingBlock(StructureWorldAccess level, BlockPos.Mutable pos, BlockState state, Random rand) {if (isReplaceable(level, pos) || isPossiblyReplaceable(level, pos, rand)) {level.setBlockState(pos, state, 0);}}

	private boolean isReplaceable(StructureWorldAccess level, BlockPos blockPos)
	{
		BlockState previousBlock = level.getBlockState(blockPos);
		return previousBlock.isAir() || previousBlock.isIn(AerialHellTags.Blocks.FEATURE_CAN_REPLACE);
	}

	private boolean isPossiblyReplaceable(StructureWorldAccess level, BlockPos blockPos, Random rand)
	{
		BlockState previousBlock = level.getBlockState(blockPos);
		return (previousBlock.isOf(AerialHellBlocks.STELLAR_GRASS_BLOCK) && (rand.nextInt(2) == 0)) || ((previousBlock.isIn(AerialHellTags.Blocks.STELLAR_DIRT)) && (rand.nextInt(3) == 0));
	}
}