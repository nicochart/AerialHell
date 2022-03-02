package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class SlipperySandFeature extends Feature<NoFeatureConfig>
{

    public SlipperySandFeature(Codec<NoFeatureConfig> codec)
    {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
		boolean canGenerate = (
                (reader.getBlockState(pos.north(3)).getBlock().equals(Blocks.AIR) ||
                 reader.getBlockState(pos.south(3)).getBlock().equals(Blocks.AIR) ||
                 reader.getBlockState(pos.west(3)).getBlock().equals(Blocks.AIR) ||
                 reader.getBlockState(pos.east(3)).getBlock().equals(Blocks.AIR)) &&
                	(reader.getBlockState(pos).isIn(AerialHellTags.Blocks.STELLAR_STONE) ||
                     reader.getBlockState(pos).getBlock() == AerialHellBlocksAndItems.STELLAR_DIRT.get()));
        if (canGenerate)
        {
        	boolean isBig;
        	int Min,Max;
        	if (rand.nextDouble() > 0.8) //grand (20% des cas)
            {
                Min=4;Max=7;
                isBig=true;
            }
            else //petit (80% des cas)
            {
                Min=3;Max=5;
                isBig=false;
            }
            int sizeX = Min + (int)(rand.nextDouble() * ((Max - Min) + 1));
            int sizeZ = Min + (int)(rand.nextDouble() * ((Max - Min) + 1));
            /*Placement du slippery_sand*/
            for(int x = pos.getX() - sizeX; x < pos.getX() + sizeX+1; x++)
            {
                for(int z = pos.getZ() - sizeZ; z < pos.getZ() + sizeZ+1; z++)
                {
                    BlockPos newPos = new BlockPos(x, pos.getY(), z);

                    if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ+rand.nextInt(3))
                    {
                    	Block previousBlock = reader.getBlockState(newPos).getBlock();
                    	if ( previousBlock == Blocks.AIR || previousBlock == AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get() || previousBlock == AerialHellBlocksAndItems.STELLAR_DIRT.get() || previousBlock == AerialHellBlocksAndItems.WHITE_SOLID_ETHER.get() || previousBlock == AerialHellBlocksAndItems.BLUE_SOLID_ETHER.get() || previousBlock == AerialHellBlocksAndItems.GOLDEN_SOLID_ETHER.get())
                    		reader.setBlockState(newPos, AerialHellBlocksAndItems.SLIPPERY_SAND.get().getDefaultState(), 0);
                    }
                }
            }
            if (isBig && rand.nextDouble() > 0.3) //deuxième couche
            {
            	if (rand.nextDouble() > 0.5) {sizeX--; sizeZ--;}
            	else {sizeX -= 2; sizeZ -= 2;}
            	
            	for(int x = pos.getX() - sizeX; x < pos.getX() + sizeX+1; x++)
                {
                    for(int z = pos.getZ() - sizeZ; z < pos.getZ() + sizeZ+1; z++)
                    {
                        BlockPos newPos = new BlockPos(x, pos.getY()+1, z);

                        if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ+rand.nextInt(3))
                        {
                        	Block previousBlock = reader.getBlockState(newPos).getBlock();
                        	if (previousBlock == Blocks.AIR || previousBlock == AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get() || previousBlock == AerialHellBlocksAndItems.STELLAR_DIRT.get() || previousBlock == AerialHellBlocksAndItems.WHITE_SOLID_ETHER.get() || previousBlock == AerialHellBlocksAndItems.BLUE_SOLID_ETHER.get() || previousBlock == AerialHellBlocksAndItems.GOLDEN_SOLID_ETHER.get())
                        		reader.setBlockState(newPos, AerialHellBlocksAndItems.SLIPPERY_SAND.get().getDefaultState(), 0);
                        }
                    }
                }
            }
        }
        return false;
    }
}