package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.FeatureHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class DanglingChainFeature extends Feature<NoneFeatureConfiguration>
{
    public DanglingChainFeature(Codec<NoneFeatureConfiguration> codec) {super(codec);}

    private static enum LinkDirection{NORTH_SOUTH, WEST_EAST}

	@Override public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context)
	{
		BlockPos blockPos = context.origin(); WorldGenLevel reader = context.level(); RandomSource rand = context.random(); ChunkGenerator generator = context.chunkGenerator();
    	boolean canGenerate = reader.getBlockState(blockPos.below()).getBlock().equals(Blocks.AIR)
    		&& reader.getBlockState(blockPos).is(AerialHellTags.Blocks.STELLAR_STONE)
    		&& hasAnyStoneBlockAbove(blockPos.north(4).west(4), reader, 10)
    		&& hasAnyStoneBlockAbove(blockPos.north(4).east(4), reader, 10)
    		&& hasAnyStoneBlockAbove(blockPos.south(4).west(4), reader, 10)
    		&& hasAnyStoneBlockAbove(blockPos.south(4).east(4), reader, 10);
    	
		boolean generatesInDungeon = FeatureHelper.isFeatureGeneratingNextToDungeon(context);
		
        if (canGenerate && !generatesInDungeon)
        {
        	BlockPos placementPos;
        	int chance_malus = 0;
        	placementPos = blockPos.above(5);
        	LinkDirection linkDirection = (rand.nextInt(2) == 0) ? LinkDirection.NORTH_SOUTH : LinkDirection.WEST_EAST;
        	generateChainLink(reader, rand, placementPos, linkDirection);
        	while (rand.nextInt(10) > (0 + chance_malus) && placementPos.getY() > 20)
        	{
        		chance_malus+=1;
				placementPos = placementPos.below(5);
        		linkDirection = (linkDirection == LinkDirection.NORTH_SOUTH) ? LinkDirection.WEST_EAST : LinkDirection.NORTH_SOUTH;
        		generateChainLink(reader, rand, placementPos, linkDirection);
        	}
			placementPos = placementPos.below(5);
    		linkDirection = (linkDirection == LinkDirection.NORTH_SOUTH) ? LinkDirection.WEST_EAST : LinkDirection.NORTH_SOUTH;
    		generateLastLink(reader, rand, placementPos, linkDirection);
        	return true;
        }
        return false;
    }
    
    protected void generateChainLink(WorldGenLevel reader, RandomSource rand, BlockPos blockPos, LinkDirection direction)
    {
    	int i,y;
    	if (direction == LinkDirection.NORTH_SOUTH)
    	{
    		for (i=-2; i<3; i++)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.offset(i, 0, 0), getRandomBlockstateToPlace(rand));
    			this.setBlockStateIfPossible(reader, blockPos.offset(i, -6, 0), getRandomBlockstateToPlace(rand));
    		}
    		for (y=-1; y>-6; y--)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.offset(-2, y, 0), getRandomBlockstateToPlace(rand));
    			this.setBlockStateIfPossible(reader, blockPos.offset(2, y, 0), getRandomBlockstateToPlace(rand));
    		}
    	}
    	else
    	{
    		for (i=-2; i<3; i++)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.offset(0, 0, i), getRandomBlockstateToPlace(rand));
    			this.setBlockStateIfPossible(reader, blockPos.offset(0, -6, i), getRandomBlockstateToPlace(rand));
    		}
    		for (y=-1; y>-6; y--)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.offset(0, y, -2), getRandomBlockstateToPlace(rand));
    			this.setBlockStateIfPossible(reader, blockPos.offset(0, y, 2), getRandomBlockstateToPlace(rand));
    		}
    	}
    }
    
    protected void generateLastLink(WorldGenLevel reader, RandomSource rand, BlockPos blockPos, LinkDirection direction)
    {
    	int i,y;
    	if (direction == LinkDirection.NORTH_SOUTH)
    	{
    		for (i=-2; i<3; i++)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.offset(i, 0, 0), getRandomBlockstateToPlace(rand));
    		}
    		this.setBlockStateIfPossible(reader, blockPos.offset(2, -1, 0), getRandomBlockstateToPlace(rand));
    		this.setBlockStateIfPossible(reader, blockPos.offset(-2, -1, 0), getRandomBlockstateToPlace(rand));
    		for (y=-2; y>-6; y--)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.offset(-2, y, 0), getRandomBlockstateToPlace(rand));
    			if (rand.nextInt(3) == 0) {break;}
    		}
    		for (y=-1; y>-6; y--)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.offset(2, y, 0), getRandomBlockstateToPlace(rand));
    			if (rand.nextInt(3) == 0) {break;}
    		}
    	}
    	else
    	{
    		for (i=-2; i<3; i++)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.offset(0, 0, i), getRandomBlockstateToPlace(rand));
    		}
    		this.setBlockStateIfPossible(reader, blockPos.offset(0, -1, 2), getRandomBlockstateToPlace(rand));
    		this.setBlockStateIfPossible(reader, blockPos.offset(0, -1, -2), getRandomBlockstateToPlace(rand));
    		for (y=-2; y>-6; y--)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.offset(0, y, -2), getRandomBlockstateToPlace(rand));
    			if (rand.nextInt(3) == 0) {break;}
    		}
    		for (y=-1; y>-6; y--)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.offset(0, y, 2), getRandomBlockstateToPlace(rand));
    			if (rand.nextInt(3) == 0) {break;}
    		}
    	}
    }
    
    private BlockState getRandomBlockstateToPlace(RandomSource rand)
    {
    	return (rand.nextInt(4) == 0) ? AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE.get().defaultBlockState() : AerialHellBlocks.STELLAR_STONE_BRICKS.get().defaultBlockState();
    }
    
    private void setBlockStateIfPossible(WorldGenLevel reader, BlockPos blockPos, BlockState state)
    {
    	if (this.isReplaceable(reader, blockPos)) {reader.setBlock(blockPos, state, 0);}
    }
    
    private boolean isReplaceable(WorldGenLevel reader, BlockPos blockPos)
    {
    	BlockState previousBlock = reader.getBlockState(blockPos);
    	if (previousBlock.isAir() || previousBlock.is(AerialHellTags.Blocks.STELLAR_STONE) || previousBlock.is(AerialHellTags.Blocks.FEATURE_CAN_REPLACE) || previousBlock.is(AerialHellTags.Blocks.STELLAR_DIRT)) {return true;}
    	else {return false;}
    }
    
    private boolean hasAnyStoneBlockAbove(BlockPos pos, WorldGenLevel reader, int yMaxDistance)
	{
		for (BlockPos blockpos = pos.above(); blockpos.getY() < pos.getY() + yMaxDistance; blockpos = blockpos.above())
		{
			if (reader.getBlockState(blockpos).is(AerialHellTags.Blocks.STELLAR_STONE)) {return true;}
		}
		return false;
	}
}