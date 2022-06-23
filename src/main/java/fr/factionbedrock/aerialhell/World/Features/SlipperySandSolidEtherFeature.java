package fr.factionbedrock.aerialhell.World.Features;

import java.util.Random;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellStructures;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class SlipperySandSolidEtherFeature extends Feature<NoFeatureConfig>
{
    public SlipperySandSolidEtherFeature(Codec<NoFeatureConfig> codec)
    {
        super(codec);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
		boolean canGenerate = (
                (reader.getBlockState(pos.west(3)).getBlock().equals(Blocks.AIR) ||
                 reader.getBlockState(pos.north(3)).getBlock().equals(Blocks.AIR) ||
                 reader.getBlockState(pos.south(3)).getBlock().equals(Blocks.AIR) ||
                 reader.getBlockState(pos.east(3)).getBlock().equals(Blocks.AIR)) &&
                (reader.getBlockState(pos).getBlock() == AerialHellBlocksAndItems.STELLAR_STONE.get()) ||
                 reader.getBlockState(pos).getBlock() == AerialHellBlocksAndItems.SLIPPERY_SAND.get());
		
		boolean generatesInDungeon = (
    			reader.func_241827_a(SectionPos.from(pos), AerialHellStructures.GOLDEN_NETHER_PRISON_STRUCTURE.get()).findAny().isPresent() ||
    			reader.func_241827_a(SectionPos.from(pos), AerialHellStructures.MUD_DUNGEON_STRUCTURE.get()).findAny().isPresent() ||
    			reader.func_241827_a(SectionPos.from(pos), AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get()).findAny().isPresent());
    	
        if (canGenerate && !generatesInDungeon)
        {
        	/*Size decision*/
        	int minSize,maxSize;
        	if (rand.nextDouble() > 0.8) //grand (20% des cas)
        	{
        		minSize=4;maxSize=6;
        	}
        	else //petit (80% des cas)
        	{
        		minSize=3;maxSize=4;
        	}
        	int sizeX = minSize + (int)(rand.nextDouble() * ((maxSize - minSize) + 1));
        	int sizeZ = minSize + (int)(rand.nextDouble() * ((maxSize - minSize) + 1));
        	/*Placement*/
            for(int x = pos.getX() - sizeX; x < pos.getX() + sizeX+1; x++) {
                for(int z = pos.getZ() - sizeZ; z < pos.getZ() + sizeZ+1; z++)
                {
                    BlockPos newPos = new BlockPos(x, pos.getY(), z);

                    if((x - pos.getX()) * (x - pos.getX()) + (z - pos.getZ()) * (z - pos.getZ()) < sizeX*sizeZ+rand.nextInt(4))
                    {
                        reader.setBlockState(newPos, AerialHellBlocksAndItems.SLIPPERY_SAND_SOLID_ETHER.get().getDefaultState(), 1);
                    }
                }
            }
        }
        return false;
    }
}
