package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public class WorldHelper
{
    public static void doAerialHellNeighborUpdate(Level world, BlockPos pos)
    {
        if (world.dimensionTypeRegistration().is(AerialHellDimensions.AERIAL_HELL_DIMENSION.location()))
        {
            FluidState fluidstate = world.getFluidState(pos);
            BlockState blockstate = world.getBlockState(pos);

            if (fluidstate.is(AerialHellTags.Fluids.CRYSTALLIZABLE))
            {
                world.setBlockAndUpdate(pos, AerialHellBlocks.CRYSTAL_BLOCK.defaultBlockState());
                world.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
            else if (blockstate.getBlock().equals(Blocks.MAGMA_BLOCK))
            {
                world.setBlockAndUpdate(pos, AerialHellBlocks.MAGMATIC_GEL_ORE.defaultBlockState());
                world.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
            else if (blockstate.getBlock().equals(Blocks.FIRE) || blockstate.getBlock().equals(Blocks.SOUL_FIRE))
            {
                world.setBlockAndUpdate(pos, AerialHellBlocks.CRYSTALLIZED_FIRE.defaultBlockState());
                world.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
                if (world.getBlockState(pos.below()).getBlock() == Blocks.AIR)
                {
                    world.destroyBlock(pos, true);
                }
            }
            else if (blockstate.getBlock().equals(Blocks.TORCH) || blockstate.getBlock().equals(Blocks.WALL_TORCH) || blockstate.is(AerialHellTags.Blocks.OVERWORLD_LANTERN))
            {
                world.destroyBlock(pos, true);
                world.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        }
    }
}
