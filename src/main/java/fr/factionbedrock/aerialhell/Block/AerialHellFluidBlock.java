package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.Vec3;
import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import org.jetbrains.annotations.Nullable;

public class AerialHellFluidBlock extends LiquidBlock
{
	public AerialHellFluidBlock(FlowingFluid flowableFluid, BlockBehaviour.Properties settings)
	{
        super(flowableFluid, settings.noCollision().strength(100F).noLootTable());
    }
	
	private void triggerMixEffects(LevelAccessor world, BlockPos pos) {world.levelEvent(LevelEvent.LAVA_FIZZ, pos, 0);}
	
	@Override
	public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean notify)
	{
		reactWithNeighbors(world, pos, state);
		super.onPlace(state, world, pos, oldState, notify);
	}
	
	@Override
	public void neighborChanged(BlockState state, Level world, BlockPos pos, Block sourceBlock, @Nullable Orientation wireOrientation, boolean notify)
	{
		reactWithNeighbors(world, pos, state);
	}
	
	private void reactWithNeighbors(Level level, BlockPos pos, BlockState state)
	{
		if(this.fluid.is(AerialHellTags.Fluids.LIQUID_OF_THE_GODS))
		{
			for(Direction direction : Direction.values())
			{
				if (direction != Direction.DOWN)
				{
					BlockPos blockpos = pos.relative(direction);
	                if (level.getFluidState(blockpos).is(FluidTags.WATER) || level.getFluidState(blockpos).is(FluidTags.LAVA))
	                {
	                	Block block = level.getFluidState(pos).isSource() ? AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK /*AerialHellBlocksAndItems.STELLAR_OBSIDIAN ??*/ : AerialHellBlocks.STELLAR_STONE;
	                    level.setBlockAndUpdate(pos, block.defaultBlockState());
	                    this.triggerMixEffects(level, pos);
	                }
	            }
	        }
		}
	}

	@Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler, boolean intersects)
    {
		if(this.fluid.is(AerialHellTags.Fluids.LIQUID_OF_THE_GODS))
        {
        	entity.fallDistance = 0.0F;
    		if (!entity.getDeltaMovement().equals(Vec3.ZERO))
    		{
				double yFactor = entity.getDeltaMovement().y < 0 ? 0.05 : 1;
    			EntityHelper.multiplyDeltaMovement(entity, 0.01, yFactor);
    		}
    		
            if(entity.isAlive() && entity instanceof LivingEntity)
            {
            	if (world instanceof ServerLevel serverWorld && !(entity instanceof TornSpiritEntity || entity instanceof ChainedGodEntity || ((LivingEntity) entity).hasEffect(AerialHellMobEffects.GOD)))
            	{
            		entity.hurtServer(serverWorld, AerialHellDamageTypes.getDamageSource(world, AerialHellDamageTypes.GOD_BLESS), 1.5F);//.setFire(10);
            	}
                LivingEntity livingEntity = (LivingEntity) entity;
                if (livingEntity.isShiftKeyDown())
                {
                	livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().x, 0.2, livingEntity.getDeltaMovement().z);
                }
            }
        }
    }

	@Override public boolean isPathfindable(BlockState state, PathComputationType type) {return true;}
}