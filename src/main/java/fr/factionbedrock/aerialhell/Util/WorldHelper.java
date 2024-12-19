package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldHelper
{
    public static void doAerialHellNeighborUpdate(World world, BlockPos pos)
    {
        if (world.getDimensionEntry().matchesId(AerialHellDimensions.AERIAL_HELL_DIMENSION.getValue()))
        {
            FluidState fluidstate = world.getFluidState(pos);
            BlockState blockstate = world.getBlockState(pos);

            if (world.getFluidState(pos).isIn(FluidTags.LAVA))
            {
                if (fluidstate.isIn(AerialHellTags.Fluids.CRYSTALLIZABLE))
                {
                    world.setBlockState(pos, AerialHellBlocks.CRYSTAL_BLOCK.getDefaultState());
                    world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0F, 1.0F);
                }
                else if (blockstate.getBlock().equals(Blocks.MAGMA_BLOCK))
                {
                    world.setBlockState(pos, AerialHellBlocks.MAGMATIC_GEL_ORE.getDefaultState());
                    world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0F, 1.0F);
                }
                else if (blockstate.getBlock().equals(Blocks.FIRE) || blockstate.getBlock().equals(Blocks.SOUL_FIRE))
                {
                    world.setBlockState(pos, AerialHellBlocks.CRYSTALLIZED_FIRE.getDefaultState());
                    world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    if (world.getBlockState(pos.down()).getBlock() == Blocks.AIR)
                    {
                        world.breakBlock(pos, true);
                    }
                }
                else if (blockstate.getBlock().equals(Blocks.TORCH) || blockstate.getBlock().equals(Blocks.WALL_TORCH) || blockstate.isIn(AerialHellTags.Blocks.OVERWORLD_LANTERN))
                {
                    world.breakBlock(pos, true);
                    world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0F, 1.0F);
                }
            }
        }
    }
}
