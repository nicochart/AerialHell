package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class HugeMushroomFeature extends Feature<BigMushroomFeatureConfig>
{
	public HugeMushroomFeature(Codec<BigMushroomFeatureConfig> config) {super(config);}

    @Override
    public boolean generate(@Nonnull ISeedReader world, @Nullable ChunkGenerator generator, @Nonnull Random rand, @Nonnull BlockPos pos, @Nonnull BigMushroomFeatureConfig config)
    {
        int stemSize = (rand.nextInt(6) == 0) ? rand.nextInt(8) + 5 : rand.nextInt(5) + 8; //shroom y size
        int capRadius = 3 + rand.nextInt(2); //horizontal width
        float yCapFactor = (rand.nextInt(2) == 0) ? 0.5F : 0.6F; //cap vertical size (0.0F : 0 block, no cap ; 1.0F : full size cap, the cap touches the floor)
        if (!this.canGrow(world, pos, stemSize, capRadius, config)) {return false;}
        else
        {
        	this.generateCap(world, rand, pos, stemSize, yCapFactor, capRadius, config);
        	this.generateStem(world, rand, pos, stemSize, config);
            return true;
        }
    }
    
    protected void generateCap(IWorld world, Random rand, BlockPos blockPos, int stemSize, float yCapFactor, int capRadius, BigMushroomFeatureConfig config)
    {
    	BlockPos.Mutable placementPos = new BlockPos.Mutable();
    	boolean downInEll,isUpCap,northInEll,southInEll,westInEll,eastInEll;
    	int capHeight = (int) (stemSize * yCapFactor);
    	int yCap = (int) (stemSize * (1.0F - yCapFactor));
    	BlockPos centerPos = blockPos.add(0, yCap, 0);
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
                        downInEll = isPosInsideEllipsis(pos.down(),a,b,c);
                        northInEll = isPosInsideEllipsis(pos.north(),a,b,c);
                        southInEll = isPosInsideEllipsis(pos.south(),a,b,c);
                        westInEll = isPosInsideEllipsis(pos.west(),a,b,c);
                        eastInEll = isPosInsideEllipsis(pos.east(),a,b,c);
                        if (downInEll || northInEll || southInEll || westInEll || eastInEll) //if pos is at ellipsis border : place cap block
                        {
                        	isUpCap = isCapBlockPos(pos.up(), a, b, c);
                        	placementPos.setPos(centerPos.add(pos));
                            if (world.getBlockState(placementPos).canBeReplacedByLeaves(world, placementPos))
                            {
                                this.setBlockState(world, placementPos, config.capProvider.getBlockState(rand, placementPos)
                                		.with(HugeMushroomBlock.NORTH, !northInEll)
                                		.with(HugeMushroomBlock.SOUTH, !southInEll)
                                		.with(HugeMushroomBlock.WEST, !westInEll)
                                		.with(HugeMushroomBlock.EAST, !eastInEll)
                                		.with(HugeMushroomBlock.UP, !isUpCap));
                            }
                        }
                    }
                }
            }
        }
    }
    
    protected void generateStem(IWorld world, Random rand, BlockPos blockPos, int stemSize, BigMushroomFeatureConfig config)
    {
    	BlockPos.Mutable placementPos = new BlockPos.Mutable();
        for(int y = 0; y < stemSize; ++y)
        {
            for (int x = 0; x < 2; x++)
            {
                for (int z = 0; z < 2; z++)
                {
                    placementPos.setPos(blockPos).move(x, y, z);
                    if (world.getBlockState(placementPos).canBeReplacedByLogs(world, placementPos) || world.getBlockState(placementPos).isIn(AerialHellBlocksAndItems.VERDIGRIS_AGARIC.get()))
                    {
                        this.setBlockState(world, placementPos, config.stemProvider.getBlockState(rand, blockPos));
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
    	if (isPosInsideEllipsis(pos.down(),a,b,c)) {return true;} //downInEll
    	if (isPosInsideEllipsis(pos.north(),a,b,c)) {return true;} //northInEll
    	if (isPosInsideEllipsis(pos.south(),a,b,c)) {return true;} //southInEll
    	if (isPosInsideEllipsis(pos.west(),a,b,c)) {return true;} //westInEll
    	if (isPosInsideEllipsis(pos.east(),a,b,c)) {return true;} //eastInEll
        return false;
    }
    
    
    protected boolean canGrow(IWorld world, BlockPos blockPos, int stemSize, int capRadius, BigMushroomFeatureConfig config)
    {
        return this.isValidGround(world, blockPos)
               && canPlaceStem(world, blockPos, stemSize, config)
        	   && blockPos.getY() >= blockPos.getY() && blockPos.getY() + stemSize + 1 < world.getHeight();
    }
    
    protected boolean canPlaceStem(IWorld world, BlockPos blockPos, int stemSize, BigMushroomFeatureConfig config)
    {
    	BlockPos.Mutable placementBlockPos = new BlockPos.Mutable();
        for (int x = 0; x < 2; x++)
        {
            for (int z = 0; z < 2; z++)
            {
            	for(int y = 0; y <= stemSize; y++)
            	{
                    placementBlockPos.setPos(blockPos).move(Direction.UP, y);
                    if (!world.getBlockState(placementBlockPos).canBeReplacedByLogs(world, placementBlockPos)) {return false;}
                }
            }
        }
        return true;
    }
    
    protected boolean isValidGround(IWorld world, BlockPos pos)
    {
    	BlockState blockState;
        for (int x = 0; x < 2; x++)
        {
            for (int z = 0; z < 2; z++)
            {
            	blockState = world.getBlockState(pos.add(x, -1, z));
                if (!(blockState.isIn(BlockTags.MUSHROOM_GROW_BLOCK))) {return false;}
            }
        }
        return true;
    }
}