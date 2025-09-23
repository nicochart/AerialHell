package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellConfiguredFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.List;

public class DanglingChainFeature extends Feature<DefaultFeatureConfig> implements DungeonSensitiveFeatureCheck
{
    public DanglingChainFeature(Codec<DefaultFeatureConfig> codec) {super(codec);}

	@Override public List<RegistryKey<ConfiguredFeature<?, ?>>> getAssociatedConfiguredFeatures() {return AerialHellConfiguredFeatures.Lists.DANGLING_CHAIN_LIST;}

    private static enum LinkDirection{NORTH_SOUTH, WEST_EAST}

	@Override public boolean generate(FeatureContext<DefaultFeatureConfig> context)
	{
		BlockPos blockPos = context.getOrigin(); StructureWorldAccess reader = context.getWorld(); Random rand = context.getRandom(); ChunkGenerator generator = context.getGenerator();
    	boolean canGenerate = reader.getBlockState(blockPos.down()).getBlock().equals(Blocks.AIR)
    		&& reader.getBlockState(blockPos).isIn(AerialHellTags.Blocks.STELLAR_STONE)
    		&& hasAnyStoneBlockAbove(blockPos.north(4).west(4), reader, 10)
    		&& hasAnyStoneBlockAbove(blockPos.north(4).east(4), reader, 10)
    		&& hasAnyStoneBlockAbove(blockPos.south(4).west(4), reader, 10)
    		&& hasAnyStoneBlockAbove(blockPos.south(4).east(4), reader, 10);
		
        if (canGenerate && this.isDungeonSensitiveValid(context))
        {
        	BlockPos placementPos;
        	int chance_malus = 0;
        	placementPos = blockPos.up(5);
        	LinkDirection linkDirection = (rand.nextInt(2) == 0) ? LinkDirection.NORTH_SOUTH : LinkDirection.WEST_EAST;
        	generateChainLink(reader, rand, placementPos, linkDirection);
        	while (rand.nextInt(10) > (0 + chance_malus) && placementPos.getY() > 20)
        	{
        		chance_malus+=1;
				placementPos = placementPos.down(5);
        		linkDirection = (linkDirection == LinkDirection.NORTH_SOUTH) ? LinkDirection.WEST_EAST : LinkDirection.NORTH_SOUTH;
        		generateChainLink(reader, rand, placementPos, linkDirection);
        	}
			placementPos = placementPos.down(5);
    		linkDirection = (linkDirection == LinkDirection.NORTH_SOUTH) ? LinkDirection.WEST_EAST : LinkDirection.NORTH_SOUTH;
    		generateLastLink(reader, rand, placementPos, linkDirection);
        	return true;
        }
        return false;
    }
    
    protected void generateChainLink(StructureWorldAccess reader, Random rand, BlockPos blockPos, LinkDirection direction)
    {
    	int i,y;
    	if (direction == LinkDirection.NORTH_SOUTH)
    	{
    		for (i=-2; i<3; i++)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.add(i, 0, 0), getRandomBlockstateToPlace(rand));
    			this.setBlockStateIfPossible(reader, blockPos.add(i, -6, 0), getRandomBlockstateToPlace(rand));
    		}
    		for (y=-1; y>-6; y--)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.add(-2, y, 0), getRandomBlockstateToPlace(rand));
    			this.setBlockStateIfPossible(reader, blockPos.add(2, y, 0), getRandomBlockstateToPlace(rand));
    		}
    	}
    	else
    	{
    		for (i=-2; i<3; i++)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.add(0, 0, i), getRandomBlockstateToPlace(rand));
    			this.setBlockStateIfPossible(reader, blockPos.add(0, -6, i), getRandomBlockstateToPlace(rand));
    		}
    		for (y=-1; y>-6; y--)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.add(0, y, -2), getRandomBlockstateToPlace(rand));
    			this.setBlockStateIfPossible(reader, blockPos.add(0, y, 2), getRandomBlockstateToPlace(rand));
    		}
    	}
    }
    
    protected void generateLastLink(StructureWorldAccess reader, Random rand, BlockPos blockPos, LinkDirection direction)
    {
    	int i,y;
    	if (direction == LinkDirection.NORTH_SOUTH)
    	{
    		for (i=-2; i<3; i++)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.add(i, 0, 0), getRandomBlockstateToPlace(rand));
    		}
    		this.setBlockStateIfPossible(reader, blockPos.add(2, -1, 0), getRandomBlockstateToPlace(rand));
    		this.setBlockStateIfPossible(reader, blockPos.add(-2, -1, 0), getRandomBlockstateToPlace(rand));
    		for (y=-2; y>-6; y--)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.add(-2, y, 0), getRandomBlockstateToPlace(rand));
    			if (rand.nextInt(3) == 0) {break;}
    		}
    		for (y=-1; y>-6; y--)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.add(2, y, 0), getRandomBlockstateToPlace(rand));
    			if (rand.nextInt(3) == 0) {break;}
    		}
    	}
    	else
    	{
    		for (i=-2; i<3; i++)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.add(0, 0, i), getRandomBlockstateToPlace(rand));
    		}
    		this.setBlockStateIfPossible(reader, blockPos.add(0, -1, 2), getRandomBlockstateToPlace(rand));
    		this.setBlockStateIfPossible(reader, blockPos.add(0, -1, -2), getRandomBlockstateToPlace(rand));
    		for (y=-2; y>-6; y--)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.add(0, y, -2), getRandomBlockstateToPlace(rand));
    			if (rand.nextInt(3) == 0) {break;}
    		}
    		for (y=-1; y>-6; y--)
    		{
    			this.setBlockStateIfPossible(reader, blockPos.add(0, y, 2), getRandomBlockstateToPlace(rand));
    			if (rand.nextInt(3) == 0) {break;}
    		}
    	}
    }
    
    private BlockState getRandomBlockstateToPlace(Random rand)
    {
    	return (rand.nextInt(4) == 0) ? AerialHellBlocks.MOSSY_STELLAR_COBBLESTONE.getDefaultState() : AerialHellBlocks.STELLAR_STONE_BRICKS.getDefaultState();
    }
    
    private void setBlockStateIfPossible(StructureWorldAccess reader, BlockPos blockPos, BlockState state)
    {
    	if (this.isReplaceable(reader, blockPos)) {reader.setBlockState(blockPos, state, 0);}
    }
    
    private boolean isReplaceable(StructureWorldAccess reader, BlockPos blockPos)
    {
    	BlockState previousBlock = reader.getBlockState(blockPos);
    	if (previousBlock.isAir() || previousBlock.isIn(AerialHellTags.Blocks.STELLAR_STONE) || previousBlock.isIn(AerialHellTags.Blocks.FEATURE_CAN_REPLACE) || previousBlock.isIn(AerialHellTags.Blocks.STELLAR_DIRT)) {return true;}
    	else {return false;}
    }
    
    private boolean hasAnyStoneBlockAbove(BlockPos pos, StructureWorldAccess reader, int yMaxDistance)
	{
		for (BlockPos blockpos = pos.up(); blockpos.getY() < pos.getY() + yMaxDistance; blockpos = blockpos.up())
		{
			if (reader.getBlockState(blockpos).isIn(AerialHellTags.Blocks.STELLAR_STONE)) {return true;}
		}
		return false;
	}
}