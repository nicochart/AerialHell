package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FlowableFluid;

import fr.factionbedrock.aerialhell.Entity.Bosses.ChainedGodEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;

public class AerialHellFluidBlock extends FluidBlock
{
	public AerialHellFluidBlock(FlowableFluid flowableFluid, AbstractBlock.Settings settings)
	{
        super(flowableFluid, settings.noCollision().strength(100F).dropsNothing());
    }
	
	private void triggerMixEffects(WorldAccess world, BlockPos pos) {world.syncWorldEvent(WorldEvents.LAVA_EXTINGUISHED, pos, 0);}
	
	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify)
	{
		reactWithNeighbors(world, pos, state);
		super.onBlockAdded(state, world, pos, oldState, notify);
	}
	
	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify)
	{
		reactWithNeighbors(world, pos, state);
	}
	
	private void reactWithNeighbors(World level, BlockPos pos, BlockState state)
	{
		if(this.fluid.isIn(AerialHellTags.Fluids.LIQUID_OF_THE_GODS))
		{
			for(Direction direction : Direction.values())
			{
				if (direction != Direction.DOWN)
				{
					BlockPos blockpos = pos.offset(direction);
	                if (level.getFluidState(blockpos).isIn(FluidTags.WATER) || level.getFluidState(blockpos).isIn(FluidTags.LAVA))
	                {
	                	Block block = level.getFluidState(pos).isStill() ? AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK /*AerialHellBlocksAndItems.STELLAR_OBSIDIAN ??*/ : AerialHellBlocks.STELLAR_STONE;
	                    level.setBlockState(pos, block.getDefaultState());
	                    this.triggerMixEffects(level, pos);
	                }
	            }
	        }
		}
	}

	@Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler)
    {
		if(this.fluid.isIn(AerialHellTags.Fluids.LIQUID_OF_THE_GODS))
        {
        	entity.fallDistance = 0.0F;
    		if (!entity.getVelocity().equals(Vec3d.ZERO))
    		{
				double yFactor = entity.getVelocity().y < 0 ? 0.05 : 1;
    			EntityHelper.multiplyDeltaMovement(entity, 0.01, yFactor);
    		}
    		
            if(entity.isAlive() && entity instanceof LivingEntity)
            {
            	if (world instanceof ServerWorld serverWorld && !(entity instanceof TornSpiritEntity || entity instanceof ChainedGodEntity || ((LivingEntity) entity).hasStatusEffect(AerialHellMobEffects.GOD)))
            	{
            		entity.damage(serverWorld, AerialHellDamageTypes.getDamageSource(world, AerialHellDamageTypes.GOD_BLESS), 1.5F);//.setFire(10);
            	}
                LivingEntity livingEntity = (LivingEntity) entity;
                if (livingEntity.isSneaking())
                {
                	livingEntity.setVelocity(livingEntity.getVelocity().x, 0.2, livingEntity.getVelocity().z);
                }
            }
        }
    }

	@Override public boolean canPathfindThrough(BlockState state, NavigationType type) {return true;}
}