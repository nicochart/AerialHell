package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GiantGanodermaApplanatumFeature extends Feature<NoneFeatureConfiguration>
{
    public GiantGanodermaApplanatumFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

    @Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
    {
        BlockPos pos = context.origin(); WorldGenLevel reader = context.level(); RandomSource rand = context.random();
		boolean canGenerate = (
            (!reader.getBlockState(pos.above(3)).getBlock().equals(Blocks.AIR)) &&
            ((reader.getBlockState(pos.north(2)).getBlock().equals(Blocks.AIR) ^ reader.getBlockState(pos.south(2)).getBlock().equals(Blocks.AIR)) || (reader.getBlockState(pos.west(2)).getBlock().equals(Blocks.AIR) ^ reader.getBlockState(pos.east(2)).getBlock().equals(Blocks.AIR))) &&
            (reader.getBlockState(pos).is(AerialHellTags.Blocks.STELLAR_STONE) || reader.getBlockState(pos).getBlock() == AerialHellBlocks.STELLAR_DIRT.get()));
		
		boolean generatesInDungeon = FeatureHelper.isFeatureGeneratingNextToDungeon(context);
		
        if (canGenerate && !generatesInDungeon)
        {
        	generateCap(reader, rand, pos);
        	return true;
        }
        return false;
    }
    
    protected void generateCap(WorldGenLevel reader, RandomSource rand, BlockPos blockPos)
    {
    	BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos();
    	boolean downInEll,isUpCap,northInEll,southInEll,westInEll,eastInEll;
    	int a,b,c; //ellipsis semi-axes length
    	boolean isHuge = rand.nextInt(11) == 0;
    	a = getRandomRadius(rand, isHuge); //radius 1
    	b = getRandomHeight(rand, isHuge);//capHeight
    	c = getRandomRadius(rand, isHuge); //radius 2
    	int bonus = 1;
        for (int x = - a - bonus; x <= a + bonus; x++)
        {
        	for (int y = 0; y <= b + bonus; y++)
            {
                for (int z = - c - bonus; z <= c + bonus; z++)
                {
                    BlockPos pos = new BlockPos(x, y, z);
                    if (!this.isPosInsideEllipsis(pos, a, b, c))
                    {
                        downInEll = isPosInsideEllipsis(pos.below(),a,b,c);
                        northInEll = isPosInsideEllipsis(pos.north(),a,b,c);
                        southInEll = isPosInsideEllipsis(pos.south(),a,b,c);
                        westInEll = isPosInsideEllipsis(pos.west(),a,b,c);
                        eastInEll = isPosInsideEllipsis(pos.east(),a,b,c);
                        if (downInEll || northInEll || southInEll || westInEll || eastInEll) //if pos is at ellipsis border : place cap block
                        {
                        	isUpCap = isCapBlockPos(pos.above(), a, b, c);
                        	placementPos.set(blockPos.offset(pos));
                            if (FeatureHelper.isReplaceableByLogOrLeavesFeature(reader, placementPos, true))
                            {
                            	reader.setBlock(placementPos, AerialHellBlocks.GIANT_GANODERMA_APPLANATUM_BLOCK.get().defaultBlockState()
                                		.setValue(HugeMushroomBlock.NORTH, !northInEll)
                                		.setValue(HugeMushroomBlock.SOUTH, !southInEll)
                                		.setValue(HugeMushroomBlock.WEST, !westInEll)
                                		.setValue(HugeMushroomBlock.EAST, !eastInEll)
                                		.setValue(HugeMushroomBlock.DOWN, !(downInEll || y==0))
                                		.setValue(HugeMushroomBlock.UP, !isUpCap), 0);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private int getRandomRadius(RandomSource rand, boolean isHuge)
    {
    	return isHuge ? (int) (5 + rand.nextFloat() * 5) : (int) (2 + rand.nextFloat() * 4);
    }
    
    private int getRandomHeight(RandomSource rand, boolean isHuge)
    {
    	int bonus = rand.nextInt(8) == 0 ? 1 : 0;
    	return 2 + (isHuge ? bonus+1 : bonus);
    }
    
    private boolean isPosInsideEllipsis(BlockPos pos, float a, float b, float c)
    {
        float x = pos.getX() - 0.5F;
        float y = pos.getY();
        float z = pos.getZ() - 0.5F;
        return x*x/(a*a) + y*y/(b*b) + z*z/(c*c) < 1.0F;
    }
    
    private boolean isCapBlockPos(BlockPos pos, float a, float b, float c) //ellipsis border detection
    {
    	if (this.isPosInsideEllipsis(pos, a, b, c)) {return false;}
    	if (isPosInsideEllipsis(pos.below(),a,b,c)) {return true;} //downInEll
    	if (isPosInsideEllipsis(pos.north(),a,b,c)) {return true;} //northInEll
    	if (isPosInsideEllipsis(pos.south(),a,b,c)) {return true;} //southInEll
    	if (isPosInsideEllipsis(pos.west(),a,b,c)) {return true;} //westInEll
    	if (isPosInsideEllipsis(pos.east(),a,b,c)) {return true;} //eastInEll
        return false;
    }
}