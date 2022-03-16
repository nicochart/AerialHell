package fr.factionbedrock.aerialhell.Event.Listeners;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellDimensions;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.fluid.FluidState;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class DimensionListener
{
    @SubscribeEvent
    public static void onBlockEvent(BlockEvent.NeighborNotifyEvent event)
    {
        if (event.getWorld() instanceof World)
        {
            World world = (World) event.getWorld();
            BlockPos pos = event.getPos();
            FluidState fluidstate = world.getFluidState(pos);
            BlockState blockstate = world.getBlockState(pos);
            if (world.getDimensionKey() == AerialHellDimensions.AERIAL_HELL_DIMENSION)
            {
	            if (fluidstate.getFluid().isIn(AerialHellTags.Fluids.CRYSTALLIZABLE))
	            {
	                world.setBlockState(pos, AerialHellBlocksAndItems.CRYSTAL_BLOCK.get().getDefaultState());
	                world.playSound(null, event.getPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0F, 1.0F);
	                event.setCanceled(true);
	            }
	            else if (blockstate.getBlock().equals(Blocks.MAGMA_BLOCK))
	            {
	            	world.setBlockState(pos, AerialHellBlocksAndItems.MAGMATIC_GEL_ORE.get().getDefaultState());
	            	world.playSound(null, event.getPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0F, 1.0F);
	            	event.setCanceled(true);
	            }
	            else if (blockstate.getBlock().equals(Blocks.FIRE) || blockstate.getBlock().equals(Blocks.SOUL_FIRE))
	            {
	            	world.setBlockState(pos, AerialHellBlocksAndItems.CRYSTALLIZED_FIRE.get().getDefaultState());
	            	world.playSound(null, event.getPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0F, 1.0F);
	            	if (world.getBlockState(pos.down()).getBlock() == Blocks.AIR)
	            	{
	            		world.destroyBlock(pos, true);
	            	}
	            	event.setCanceled(true);
	            }
	            else if (blockstate.getBlock().equals(Blocks.TORCH) || blockstate.getBlock().equals(Blocks.WALL_TORCH))
	            {
	            	world.destroyBlock(pos, true);
	            	world.playSound(null, event.getPos(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 1.0F, 1.0F);
	            	event.setCanceled(true);
	            }
            }
        }
    }
}