package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.MapCodec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;

import java.util.List;

public record DanglingChainFeature() implements Feature, DungeonSensitiveFeatureCheck
{
    public static final MapCodec<DanglingChainFeature> CODEC = MapCodec.unit(DanglingChainFeature::new);

    @Override public MapCodec<DanglingChainFeature> codec() {return CODEC;}

    private static enum LinkDirection{NORTH_SOUTH, WEST_EAST}

	@Override public List<ResourceKey<Feature>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.DANGLING_CHAIN_LIST;}

	@Override public boolean place(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin)
	{
    	boolean canGenerate = level.getBlockState(origin.below()).getBlock().equals(Blocks.AIR)
    		&& level.getBlockState(origin).is(AerialHellTags.Blocks.STELLAR_STONE)
    		&& hasAnyStoneBlockAbove(origin.north(4).west(4), level, 10)
    		&& hasAnyStoneBlockAbove(origin.north(4).east(4), level, 10)
    		&& hasAnyStoneBlockAbove(origin.south(4).west(4), level, 10)
    		&& hasAnyStoneBlockAbove(origin.south(4).east(4), level, 10);

        if (canGenerate && this.isDungeonSensitiveValid(level, origin))
        {
        	BlockPos placementPos;
        	int chance_malus = 0;
        	placementPos = origin.above(5);
        	LinkDirection linkDirection = (random.nextInt(2) == 0) ? LinkDirection.NORTH_SOUTH : LinkDirection.WEST_EAST;
        	generateChainLink(level, random, placementPos, linkDirection);
        	while (random.nextInt(10) > (0 + chance_malus) && placementPos.getY() > 20)
        	{
        		chance_malus+=1;
				placementPos = placementPos.below(5);
        		linkDirection = (linkDirection == LinkDirection.NORTH_SOUTH) ? LinkDirection.WEST_EAST : LinkDirection.NORTH_SOUTH;
        		generateChainLink(level, random, placementPos, linkDirection);
        	}
			placementPos = placementPos.below(5);
    		linkDirection = (linkDirection == LinkDirection.NORTH_SOUTH) ? LinkDirection.WEST_EAST : LinkDirection.NORTH_SOUTH;
    		generateLastLink(level, random, placementPos, linkDirection);
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