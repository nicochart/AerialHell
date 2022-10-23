package fr.factionbedrock.aerialhell.World.Features;

import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellStructures;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.block.Blocks;
import net.minecraft.block.HugeMushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class GiantGanodermaApplanatumFeature extends Feature<NoFeatureConfig>
{
    public GiantGanodermaApplanatumFeature(Codec<NoFeatureConfig> codec) {super(codec);}

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
		boolean canGenerate = (
            (!reader.getBlockState(pos.up(3)).getBlock().equals(Blocks.AIR)) &&
            ((reader.getBlockState(pos.north(2)).getBlock().equals(Blocks.AIR) ^ reader.getBlockState(pos.south(2)).getBlock().equals(Blocks.AIR)) || (reader.getBlockState(pos.west(2)).getBlock().equals(Blocks.AIR) ^ reader.getBlockState(pos.east(2)).getBlock().equals(Blocks.AIR))) &&
            (reader.getBlockState(pos).isIn(AerialHellTags.Blocks.STELLAR_STONE) || reader.getBlockState(pos).getBlock() == AerialHellBlocksAndItems.STELLAR_DIRT.get()));
		
		boolean generatesInDungeon = (
			reader.func_241827_a(SectionPos.from(pos), AerialHellStructures.GOLDEN_NETHER_PRISON_STRUCTURE.get()).findAny().isPresent() ||
			reader.func_241827_a(SectionPos.from(pos), AerialHellStructures.MUD_DUNGEON_STRUCTURE.get()).findAny().isPresent() ||
			reader.func_241827_a(SectionPos.from(pos), AerialHellStructures.LUNATIC_TEMPLE_STRUCTURE.get()).findAny().isPresent());
		
        if (canGenerate && !generatesInDungeon)
        {
        	generateCap(reader, rand, pos);
        	return true;
        }
        return false;
    }
    
    protected void generateCap(ISeedReader reader, Random rand, BlockPos blockPos)
    {
    	BlockPos.Mutable placementPos = new BlockPos.Mutable();
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
                        downInEll = isPosInsideEllipsis(pos.down(),a,b,c);
                        northInEll = isPosInsideEllipsis(pos.north(),a,b,c);
                        southInEll = isPosInsideEllipsis(pos.south(),a,b,c);
                        westInEll = isPosInsideEllipsis(pos.west(),a,b,c);
                        eastInEll = isPosInsideEllipsis(pos.east(),a,b,c);
                        if (downInEll || northInEll || southInEll || westInEll || eastInEll) //if pos is at ellipsis border : place cap block
                        {
                        	isUpCap = isCapBlockPos(pos.up(), a, b, c);
                        	placementPos.setPos(blockPos.add(pos));
                            if (reader.getBlockState(placementPos).canBeReplacedByLeaves(reader, placementPos))
                            {
                            	reader.setBlockState(placementPos, AerialHellBlocksAndItems.GIANT_GANODERMA_APPLANATUM_BLOCK.get().getDefaultState()
                                		.with(HugeMushroomBlock.NORTH, !northInEll)
                                		.with(HugeMushroomBlock.SOUTH, !southInEll)
                                		.with(HugeMushroomBlock.WEST, !westInEll)
                                		.with(HugeMushroomBlock.EAST, !eastInEll)
                                		.with(HugeMushroomBlock.DOWN, !(downInEll || y==0))
                                		.with(HugeMushroomBlock.UP, !isUpCap), 0);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private int getRandomRadius(Random rand, boolean isHuge)
    {
    	return isHuge ? (int) (5 + rand.nextFloat() * 5) : (int) (2 + rand.nextFloat() * 4);
    }
    
    private int getRandomHeight(Random rand, boolean isHuge)
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
    	if (isPosInsideEllipsis(pos.down(),a,b,c)) {return true;} //downInEll
    	if (isPosInsideEllipsis(pos.north(),a,b,c)) {return true;} //northInEll
    	if (isPosInsideEllipsis(pos.south(),a,b,c)) {return true;} //southInEll
    	if (isPosInsideEllipsis(pos.west(),a,b,c)) {return true;} //westInEll
    	if (isPosInsideEllipsis(pos.east(),a,b,c)) {return true;} //eastInEll
        return false;
    }
}