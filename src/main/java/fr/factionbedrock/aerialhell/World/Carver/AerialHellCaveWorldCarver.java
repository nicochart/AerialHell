package fr.factionbedrock.aerialhell.World.Carver;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

import org.apache.commons.lang3.mutable.MutableBoolean;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellFluids;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class AerialHellCaveWorldCarver extends CaveWorldCarver
{

	public AerialHellCaveWorldCarver(Codec<ProbabilityConfig> probaConfig, int maxHeight)
	{
		super(probaConfig, maxHeight);
		this.carvableBlocks = ImmutableSet.of
		(
				AerialHellBlocksAndItems.STELLAR_STONE.get(),
				AerialHellBlocksAndItems.STELLAR_DIRT.get(),
				AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get(),
				AerialHellBlocksAndItems.GLAUCOPHANITE.get(),
				AerialHellBlocksAndItems.AZURITE_ORE.get(),
				AerialHellBlocksAndItems.FLUORITE_ORE.get(),
				AerialHellBlocksAndItems.RUBY_ORE.get(),
				AerialHellBlocksAndItems.VOLUCITE_ORE.get(),
				AerialHellBlocksAndItems.MAGMATIC_GEL_BLOCK.get()
        );
        this.carvableFluids = ImmutableSet.of
        (
                Fluids.WATER,
                AerialHellFluids.LIQUID_OF_THE_GODS_SOURCE.get()
        );
	}
	
	@Override //changed to generate stellar grass in the caves floor
    protected boolean carveBlock(IChunk chunk, Function<BlockPos, Biome> p_230358_2_, BitSet carvingMask, Random rand, BlockPos.Mutable pos5, BlockPos.Mutable pos6, BlockPos.Mutable pos7, int p_230358_8_, int p_230358_9_, int p_230358_10_, int posX, int posZ, int p_230358_13_, int posY, int p_230358_15_, MutableBoolean isSurface)
	{
        int i = p_230358_13_ | p_230358_15_ << 4 | posY << 8;
        if (carvingMask.get(i)) {return false;}
        else
        {
            carvingMask.set(i);
            pos5.setPos(posX, posY, posZ);
            BlockState blockstate = chunk.getBlockState(pos5);
            BlockState blockstate1 = chunk.getBlockState(pos6.setAndMove(pos5, Direction.UP));

            if (!this.canCarveBlock(blockstate, blockstate1)) {return false;}
            else
            {
                chunk.setBlockState(pos5, CAVE_AIR, false);
                pos7.setAndMove(pos5, Direction.DOWN);
                if (chunk.getBlockState(pos7.below()).isIn(AerialHellBlocksAndItems.STELLAR_STONE.get()))
                {
                    chunk.setBlockState(pos7, p_230358_2_.apply(pos5).getGenerationSettings().getSurfaceBuilderConfig().getTop(), false);
                }
                return true;
            }
        }
    }
	
	@Override
    protected int func_230357_a_()
	{
        return 12;
    }
}
