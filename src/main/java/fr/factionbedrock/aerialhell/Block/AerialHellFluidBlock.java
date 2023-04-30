package fr.factionbedrock.aerialhell.Block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import com.mojang.math.Vector3d;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellFluids;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;

public class AerialHellFluidBlock extends LiquidBlock {

	public AerialHellFluidBlock(Supplier<? extends FlowingFluid> supplier, Properties properties)
	{
        super(supplier, properties.noCollission().strength(100F).noDrops());
    }
	
	private void triggerMixEffects(LevelAccessor worldIn, BlockPos pos) {worldIn.levelEvent(1501, pos, 0);} //fizz in FluidBlock
	
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		reactWithNeighbors(worldIn, pos, state);
		super.onPlace(state, worldIn, pos, oldState, isMoving);
	}
	
	@Override
	public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving)
	{
		reactWithNeighbors(worldIn, pos, state);
	}
	
	private void reactWithNeighbors(Level worldIn, BlockPos pos, BlockState state)
	{
		if(this.getFluid() == AerialHellFluids.LIQUID_OF_THE_GODS_SOURCE.get() || this.getFluid() == AerialHellFluids.LIQUID_OF_THE_GODS_FLOWING.get())
		{
			for(Direction direction : Direction.values())
			{
				if (direction != Direction.DOWN)
				{
					BlockPos blockpos = pos.relative(direction);
	                if (worldIn.getFluidState(blockpos).is(FluidTags.WATER) || worldIn.getFluidState(blockpos).is(FluidTags.LAVA))
	                {
	                	Block block = worldIn.getFluidState(pos).isSource() ? AerialHellBlocksAndItems.STELLAR_PORTAL_FRAME_BLOCK.get() /*AerialHellBlocksAndItems.STELLAR_OBSIDIAN ??*/ : AerialHellBlocksAndItems.STELLAR_STONE.get();
	                    worldIn.setBlockAndUpdate(pos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(worldIn, pos, pos, block.defaultBlockState()));
	                    this.triggerMixEffects(worldIn, pos);
	                }
	            }
	        }
		}
	}
	
    @Override
    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn)
    {
        if(this.getFluid() == AerialHellFluids.LIQUID_OF_THE_GODS_SOURCE.get() || this.getFluid() == AerialHellFluids.LIQUID_OF_THE_GODS_FLOWING.get())
        {
        	entityIn.fallDistance = 0.0F;
    		if (!entityIn.getDeltaMovement().equals(Vec3.ZERO))
    		{
    			if (entityIn.getDeltaMovement().y < 0)
    			{
    				entityIn.setDeltaMovement(entityIn.getDeltaMovement().multiply(0.01, 0.05, 0.01));
    			}
    			else
    			{
    				entityIn.setDeltaMovement(entityIn.getDeltaMovement().multiply(0.01, 1, 0.01));
    			}
    		}
    		
            if(entityIn.isAlive() && entityIn instanceof LivingEntity)
            {
            	if (!(entityIn instanceof TornSpiritEntity || entityIn instanceof ChainedGodEntity || ((LivingEntity) entityIn).hasEffect(AerialHellMobEffects.GOD.get())))
            	{
            		entityIn.hurt(new DamageSource("god_blessing").bypassArmor(), 1.5F);//.setFire(10);
            	}
                LivingEntity livingEntity = (LivingEntity) entityIn;
                if (livingEntity.isCrouching())
                {
                	livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().x, 0.2, livingEntity.getDeltaMovement().z);
                }
            }
        }
    }
    
    @Override public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {return true;}
}