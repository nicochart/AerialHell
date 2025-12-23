package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class AerialHellFluidBlock extends LiquidBlock {

	public AerialHellFluidBlock(FlowingFluid flowingFluid, Properties properties)
	{
        super(flowingFluid, properties.noCollision().strength(100F).noLootTable());
    }
	
	private void triggerMixEffects(LevelAccessor worldIn, BlockPos pos) {worldIn.levelEvent(1501, pos, 0);} //fizz in FluidBlock
	
	@Override
	public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		reactWithNeighbors(worldIn, pos, state);
		super.onPlace(state, worldIn, pos, oldState, isMoving);
	}
	
	@Override
	public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, @Nullable Orientation orientation, boolean isMoving)
	{
		reactWithNeighbors(worldIn, pos, state);
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
	                	Block block = level.getFluidState(pos).isSource() ? AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK.get() /*AerialHellBlocksAndItems.STELLAR_OBSIDIAN ??*/ : AerialHellBlocks.STELLAR_STONE.get();
	                    level.setBlockAndUpdate(pos, block.defaultBlockState());
	                    this.triggerMixEffects(level, pos);
	                }
	            }
	        }
		}
	}

	@Override protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier effectApplier, boolean intersects)
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
				if (!(entity instanceof TornSpiritEntity || entity instanceof ChainedGodEntity || ((LivingEntity) entity).hasEffect(AerialHellMobEffects.GOD.getDelegate())))
				{
					entity.hurt(AerialHellDamageTypes.getDamageSource(level, AerialHellDamageTypes.GOD_BLESS), 1.5F);//.setFire(10);
				}
				LivingEntity livingEntity = (LivingEntity) entity;
				if (livingEntity.isCrouching())
				{
					livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().x, 0.2, livingEntity.getDeltaMovement().z);
				}
			}
		}
	}
    
    @Override public boolean isPathfindable(BlockState state, PathComputationType type) {return true;}
}