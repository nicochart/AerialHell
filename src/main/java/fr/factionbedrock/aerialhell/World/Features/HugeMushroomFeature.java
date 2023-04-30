package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;
import com.mojang.serialization.Codec;

import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

public class HugeMushroomFeature extends Feature<HugeMushroomFeatureConfiguration>
{
	public HugeMushroomFeature(Codec<HugeMushroomFeatureConfiguration> config) {super(config);}

    @Override public boolean place(FeaturePlaceContext<HugeMushroomFeatureConfiguration> context)
    {
        BlockPos pos = context.origin(); WorldGenLevel world = context.level(); Random rand = context.random(); HugeMushroomFeatureConfiguration config = context.config();
        int stemSize = (rand.nextInt(6) == 0) ? rand.nextInt(8) + 5 : rand.nextInt(5) + 8; //shroom y size
        int capRadius = 3 + rand.nextInt(2); //horizontal width
        float yCapFactor = (rand.nextInt(2) == 0) ? 0.5F : 0.6F; //cap vertical size (0.0F : 0 block, no cap ; 1.0F : full size cap, the cap touches the floor)
        if (!this.canGrow(config, world, pos, stemSize, capRadius)) {return false;}
        else
        {
        	this.generateCap(config, world, rand, pos, stemSize, yCapFactor, capRadius);
        	this.generateStem(config, world, rand, pos, stemSize);
            return true;
        }
    }
    
    protected void generateCap(HugeMushroomFeatureConfiguration config, LevelAccessor world, Random rand, BlockPos blockPos, int stemSize, float yCapFactor, int capRadius)
    {
    	BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos();
    	boolean downInEll,isUpCap,northInEll,southInEll,westInEll,eastInEll;
    	int capHeight = (int) (stemSize * yCapFactor);
    	int yCap = (int) (stemSize * (1.0F - yCapFactor));
    	BlockPos centerPos = blockPos.offset(0, yCap, 0);
    	float a,b,c; //ellipsis semi-axes length
    	a = c = capRadius; b = capHeight;
    	int bonus = 1;
        for (int y = 0; y <= capHeight + bonus; y++)
        {
            for (int x = - capRadius - bonus; x <= capRadius + bonus; x++)
            {
                for (int z = - capRadius - bonus; z <= capRadius + bonus; z++)
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
                        	placementPos.set(centerPos.offset(pos));
                            //if (world.getBlockState(placementPos).canBeReplacedByLeaves(world, placementPos)) TODO : verify before placement
                            {
                                this.setBlock(world, placementPos, config.capProvider.getState(rand, placementPos)
                                		.setValue(HugeMushroomBlock.NORTH, !northInEll)
                                		.setValue(HugeMushroomBlock.SOUTH, !southInEll)
                                		.setValue(HugeMushroomBlock.WEST, !westInEll)
                                		.setValue(HugeMushroomBlock.EAST, !eastInEll)
                                		.setValue(HugeMushroomBlock.UP, !isUpCap));
                            }
                        }
                    }
                }
            }
        }
    }
    
    protected void generateStem(HugeMushroomFeatureConfiguration config, LevelAccessor world, Random rand, BlockPos blockPos, int stemSize)
    {
    	BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos();
        for(int y = 0; y < stemSize; ++y)
        {
            for (int x = 0; x < 2; x++)
            {
                for (int z = 0; z < 2; z++)
                {
                    placementPos.set(blockPos).move(x, y, z);
                    if (true)//(world.getBlockState(placementPos).canBeReplacedByLogs(world, placementPos) || world.getBlockState(placementPos).is(AerialHellBlocksAndItems.VERDIGRIS_AGARIC.get()))
                    { //TODO verify
                        this.setBlock(world, placementPos, config.stemProvider.getState(rand, blockPos));
                    }
                }
            }
        }
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
    
    
    protected boolean canGrow(HugeMushroomFeatureConfiguration config, LevelAccessor world, BlockPos blockPos, int stemSize, int capRadius)
    {
        return this.mayPlaceOn(world, blockPos)
               && canPlaceStem(config, world, blockPos, stemSize)
        	   && blockPos.getY() >= blockPos.getY() && blockPos.getY() + stemSize + 1 < world.getHeight();
    }
    
    protected boolean canPlaceStem(HugeMushroomFeatureConfiguration config, LevelAccessor world, BlockPos blockPos, int stemSize)
    {
    	BlockPos.MutableBlockPos placementBlockPos = new BlockPos.MutableBlockPos();
        for (int x = 0; x < 2; x++)
        {
            for (int z = 0; z < 2; z++)
            {
            	for(int y = 0; y <= stemSize; y++)
            	{
                    placementBlockPos.set(blockPos).move(Direction.UP, y);
                    //if (!world.getBlockState(placementBlockPos).canBeReplacedByLogs(world, placementBlockPos)) {return false;} TODO : verify
                }
            }
        }
        return true;
    }
    
    protected boolean mayPlaceOn(LevelAccessor world, BlockPos pos)
    {
    	BlockState blockState;
        for (int x = 0; x < 2; x++)
        {
            for (int z = 0; z < 2; z++)
            {
            	blockState = world.getBlockState(pos.offset(x, -1, z));
                if (!(blockState.is(BlockTags.MUSHROOM_GROW_BLOCK))) {return false;}
            }
        }
        return true;
    }
}