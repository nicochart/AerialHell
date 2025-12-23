package fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther;

import fr.factionbedrock.aerialhell.Block.CollisionCondition.CollisionConditionHalfTransparentBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.Level;

public class SolidEtherBlock extends CollisionConditionHalfTransparentBlock
{
	protected final static VoxelShape SOLID_ETHER_COLLISION_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 0.02, 16.0);
	
	public SolidEtherBlock(BlockBehaviour.Properties properties)
	{
		super(properties);
		this.registerDefaultState(this.defaultBlockState());
	}

	@Override public void livingEntityInside(BlockState state, Level level, BlockPos pos, LivingEntity entity)
	{
		if (!EntityHelper.isFeatheryEntity(entity)) {super.livingEntityInside(state, level, pos, entity);}
	}

	@Override
	public boolean onDestroyedByPlayer(BlockState state, Level worldIn, BlockPos pos, Player player, ItemStack toolStack, boolean willHarvest, FluidState fluid)
	{
		boolean flag = super.onDestroyedByPlayer(state, worldIn, pos, player, toolStack, willHarvest, fluid);
		if (flag && !(this == AerialHellBlocks.WHITE_SOLID_ETHER.get()))
		{
			worldIn.setBlockAndUpdate(pos, AerialHellBlocks.WHITE_SOLID_ETHER.get().defaultBlockState());
		}
		return flag;
    }

	@Override protected boolean canEntityCollide(Entity entity) {return !EntityHelper.isImmuneToSolidEtherCollision(entity);}
	@Override protected VoxelShape getCollidingShape() {return SOLID_ETHER_COLLISION_SHAPE;}
}