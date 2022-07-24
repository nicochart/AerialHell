package fr.factionbedrock.aerialhell.Block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.pathfinding.PathType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.function.Supplier;

import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellFluids;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;

public class AerialHellFluidBlock extends FlowingFluidBlock {

	public AerialHellFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties properties)
	{
        super(supplier, properties.doesNotBlockMovement().hardnessAndResistance(100F).noDrops());
    }
	
	private void triggerMixEffects(IWorld worldIn, BlockPos pos)
	{
	      worldIn.playEvent(1501, pos, 0);
	}
	
	@Override
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		reactWithNeighbors(worldIn, pos, state);
		super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
	}
	
	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving)
	{
		reactWithNeighbors(worldIn, pos, state);
	}
	
	private void reactWithNeighbors(World worldIn, BlockPos pos, BlockState state)
	{
		if(this.getFluid() == AerialHellFluids.LIQUID_OF_THE_GODS_SOURCE.get() || this.getFluid() == AerialHellFluids.LIQUID_OF_THE_GODS_FLOWING.get())
		{
			for(Direction direction : Direction.values())
			{
				if (direction != Direction.DOWN)
				{
					BlockPos blockpos = pos.offset(direction);
	                if (worldIn.getFluidState(blockpos).isTagged(FluidTags.WATER) || worldIn.getFluidState(blockpos).isTagged(FluidTags.LAVA))
	                {
	                	Block block = worldIn.getFluidState(pos).isSource() ? AerialHellBlocksAndItems.STELLAR_PORTAL_FRAME_BLOCK.get() /*AerialHellBlocksAndItems.STELLAR_OBSIDIAN ??*/ : AerialHellBlocksAndItems.STELLAR_STONE.get();
	                    worldIn.setBlockState(pos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, block.getDefaultState()));
	                    this.triggerMixEffects(worldIn, pos);
	                }
	            }
	        }
		}
	}
	
    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
    {
        if(this.getFluid() == AerialHellFluids.LIQUID_OF_THE_GODS_SOURCE.get() || this.getFluid() == AerialHellFluids.LIQUID_OF_THE_GODS_FLOWING.get())
        {
        	entityIn.fallDistance = 0.0F;
    		if (!entityIn.getMotion().equals(Vector3d.ZERO))
    		{
    			if (entityIn.getMotion().getY() < 0)
    			{
    				entityIn.setMotion(entityIn.getMotion().mul(0.01, 0.05, 0.01));
    			}
    			else
    			{
    				entityIn.setMotion(entityIn.getMotion().mul(0.01, 1, 0.01));
    			}
    		}
    		
            if(entityIn.isAlive() && entityIn instanceof LivingEntity)
            {
            	if (!(entityIn instanceof TornSpiritEntity || entityIn instanceof ChainedGodEntity || ((LivingEntity) entityIn).isPotionActive(AerialHellPotionEffects.GOD.get())))
            	{
            		entityIn.attackEntityFrom(new DamageSource("god_blessing").setDamageBypassesArmor(), 1.5F);//.setFire(10);
            	}
                LivingEntity livingEntity = (LivingEntity) entityIn;
                if (livingEntity.isCrouching())
                {
                	livingEntity.setMotion(livingEntity.getMotion().getX(), 0.2, livingEntity.getMotion().getZ());
                }
            }
        }
    }
    
    @Override
    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type)
    {
        return true;
    }
}