package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import fr.factionbedrock.aerialhell.World.Features.Config.SingleBlockNeedingSupportConfig;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import org.jetbrains.annotations.Nullable;

public class SingleBlockNeedingSupportFeature extends Feature<SingleBlockNeedingSupportConfig>
{
	private static final int MAX_TRIES = 25;
	public SingleBlockNeedingSupportFeature(Codec<SingleBlockNeedingSupportConfig> codec) {super(codec);}

	@Override public boolean generate(FeatureContext<SingleBlockNeedingSupportConfig> context)
	{
		BlockStateProvider block = context.getConfig().block();
		BlockPos pos = findPosForPlacement(context);
		if (pos == null) {return false;}
		else
		{
			context.getWorld().setBlockState(pos, block.get(context.getRandom(), pos), 0);
			return true;
		}
	}
	
	@Nullable protected BlockPos findPosForPlacement(FeatureContext<SingleBlockNeedingSupportConfig> context)
	{
		Block support = context.getConfig().support().get(context.getRandom(), context.getOrigin()).getBlock();
		int maxTries = context.getConfig().maxTries();
		BlockPos testedPos, featureCenter = FeatureHelper.getFeatureCenter(context);
		StructureWorldAccess level = context.getWorld();

		for (int i=0; i<maxTries; i++)
		{
			testedPos = FeatureHelper.getRandomPosInFeatureRegion(featureCenter, context.getRandom(), 23, 30);
			while (level.getBlockState(testedPos).isOf(support)) {testedPos = testedPos.up();}
			if (hasSupportToGenerate(support, level, testedPos)) {return testedPos;}
		}
		return null;
	}
	
	private boolean hasSupportToGenerate(Block support, StructureWorldAccess level, BlockPos pos)
	{
		return level.isAir(pos) && level.getBlockState(pos.down()).isOf(support);
	}
}