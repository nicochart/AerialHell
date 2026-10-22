package fr.factionbedrock.aerialhell.Event.Listeners;

import com.mojang.blaze3d.vertex.*;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Block.DungeonCores.DungeonCoreBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.level.BlockEvent;

public class BlockEventListener
{
    public static void onNeighborNotifyEvent(BlockEvent.NeighborNotifyEvent event)
    {
        if (event.getLevel() instanceof Level world)
        {
            BlockPos pos = event.getPos();
            FluidState fluidstate = world.getFluidState(pos);
            BlockState blockstate = world.getBlockState(pos);
            if (world.dimension() == AerialHellDimensions.AERIAL_HELL_DIMENSION)
            {
	            if (fluidstate.getType().is(AerialHellTags.Fluids.CRYSTALLIZABLE))
	            {
	                world.setBlockAndUpdate(pos, AerialHellBlocks.CRYSTAL_BLOCK.get().defaultBlockState());
	                world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
	                event.setCanceled(true);
	            }
	            else if (blockstate.is(Blocks.MAGMA_BLOCK))
	            {
	            	world.setBlockAndUpdate(pos, AerialHellBlocks.MAGMATIC_GEL_ORE.get().defaultBlockState());
	            	world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
	            	event.setCanceled(true);
	            }
	            else if (blockstate.is(Blocks.FIRE))
	            {
	            	world.setBlockAndUpdate(pos, AerialHellBlocks.CRYSTALLIZED_FIRE.get().defaultBlockState());
	            	world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
	            	if (world.getBlockState(pos.below()).getBlock() == Blocks.AIR)
	            	{
	            		world.destroyBlock(pos, true);
	            	}
	            	event.setCanceled(true);
	            }
	            else if (blockstate.is(Blocks.TORCH))
	            {
                    world.setBlockAndUpdate(pos, AerialHellBlocks.CRYSTALLIZED_TORCH.get().defaultBlockState());
	            	world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
	            	event.setCanceled(true);
	            }
                else if (blockstate.is(Blocks.WALL_TORCH))
                {
                    world.setBlockAndUpdate(pos, AerialHellBlocks.CRYSTALLIZED_WALL_TORCH.get().defaultBlockState().setValue(WallTorchBlock.FACING, blockstate.getValue(WallTorchBlock.FACING)));
                    world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
                    event.setCanceled(true);
                }
                else if (blockstate.is(Blocks.LANTERN))
                {
                    world.setBlockAndUpdate(pos, AerialHellBlocks.CRYSTALLIZED_LANTERN.get().defaultBlockState().setValue(LanternBlock.HANGING, blockstate.getValue(LanternBlock.HANGING)).setValue(LanternBlock.WATERLOGGED, blockstate.getValue(LanternBlock.WATERLOGGED)));
                    world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
                    event.setCanceled(true);
                }
                else if (blockstate.is(AerialHellBlocks.RUBY_LANTERN))
                {
                    world.setBlockAndUpdate(pos, AerialHellBlocks.RUBY_CRYSTALLIZED_LANTERN.get().defaultBlockState().setValue(LanternBlock.HANGING, blockstate.getValue(LanternBlock.HANGING)).setValue(LanternBlock.WATERLOGGED, blockstate.getValue(LanternBlock.WATERLOGGED)));
                    world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
                    event.setCanceled(true);
                }
                else if (blockstate.is(AerialHellBlocks.VOLUCITE_LANTERN))
                {
                    world.setBlockAndUpdate(pos, AerialHellBlocks.VOLUCITE_CRYSTALLIZED_LANTERN.get().defaultBlockState().setValue(LanternBlock.HANGING, blockstate.getValue(LanternBlock.HANGING)).setValue(LanternBlock.WATERLOGGED, blockstate.getValue(LanternBlock.WATERLOGGED)));
                    world.playSound(null, event.getPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.PLAYERS, 1.0F, 1.0F);
                    event.setCanceled(true);
                }
            }
        }
    }

    public static void onEntityPlaceEvent(BlockEvent.EntityPlaceEvent event)
    {
    	if (event.getLevel() instanceof Level world)
        {
    		BlockPos pos = event.getPos();
            BlockState blockstate = world.getBlockState(pos);
        	if (blockstate.is(AerialHellTags.Blocks.DUNGEON_CORES))
            {
            	((DungeonCoreBlock) blockstate.getBlock()).setAreaProtected(world, pos, true);
            	world.playSound(null, event.getPos(), SoundEvents.BEACON_ACTIVATE, SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        }
    }
}