package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.SingleBlockNeedingSupportConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import javax.annotation.Nullable;

public class SingleBlockNeedingSupportFeature extends Feature<SingleBlockNeedingSupportConfig>
{
	private static final int MAX_TRIES = 25;
	public SingleBlockNeedingSupportFeature(Codec<SingleBlockNeedingSupportConfig> codec) {super(codec);}

	@Override public boolean place(FeaturePlaceContext<SingleBlockNeedingSupportConfig> context)
	{
		BlockStateProvider block = context.config().block();
		BlockPos pos = findPosForPlacement(context);
		if (pos == null) {return false;}
		else
		{
			context.level().setBlockState(pos, block.getState(context.random(), pos), 0);
			return true;
		}
	}
	
	@Nullable protected BlockPos findPosForPlacement(FeaturePlaceContext<SingleBlockNeedingSupportConfig> context)
	{
		Block support = context.config().support().getState(context.random(), context.origin()).getBlock();
		int maxTries = context.config().maxTries();
		BlockPos testedPos, featureCenter = FeatureHelper.getFeatureCenter(context);
		WorldGenLevel level = context.level();

		for (int i=0; i<maxTries; i++)
		{
			testedPos = FeatureHelper.getRandomPosInFeatureRegion(featureCenter, context.random(), 23, 30);
			while (level.getBlockState(testedPos).is(support)) {testedPos = testedPos.above();}
			if (hasSupportToGenerate(support, level, testedPos)) {return testedPos;}
		}
		return null;
	}
	
	private boolean hasSupportToGenerate(Block support, WorldGenLevel level, BlockPos pos)
	{
		return level.isEmptyBlock(pos) && level.getBlockState(pos.down()).is(support);
	}
}