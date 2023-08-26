package fr.factionbedrock.aerialhell.Block.SolidEther;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;

public class SolidEtherBlock extends HalfTransparentBlock
{
	protected final static VoxelShape SOLID_ETHER_COLLISION_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 0.02, 16.0);
	protected final static VoxelShape EMPTY_SHAPE = Shapes.empty();
	
	public SolidEtherBlock(BlockBehaviour.Properties properties)
	{
		super(properties.isRedstoneConductor((state, reader, pos) -> false).isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> true));
		this.registerDefaultState(this.defaultBlockState());
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		super.createBlockStateDefinition(builder);
	}
	
	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity)
	{
		entity.fallDistance = 0.0F;
		if (entity.getDeltaMovement().y < 0.0)
		{
			if (entity instanceof LivingEntity) {if (!EntityHelper.isFeatheryEntity(entity)) {entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.96, 0.002, 0.96));}}
			else {entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.85, 0.002, 0.85));}
		}
	}
	
	@Override
	public boolean onDestroyedByPlayer(BlockState state, Level worldIn, BlockPos pos, Player player, boolean willHarvest, FluidState fluid)
	{
		boolean flag = super.onDestroyedByPlayer(state, worldIn, pos, player, willHarvest, fluid);
		if (flag && !(this == AerialHellBlocksAndItems.WHITE_SOLID_ETHER.get()))
		{
			worldIn.setBlockAndUpdate(pos, AerialHellBlocksAndItems.WHITE_SOLID_ETHER.get().defaultBlockState());
		}
		return flag;
    }

	@Override public boolean useShapeForLightOcclusion(BlockState state) {return true;}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		if (context instanceof EntityCollisionContext)
		{
			Entity entity = ((EntityCollisionContext)context).getEntity();
			if (canEntityCollide(entity)) {return SOLID_ETHER_COLLISION_SHAPE;}
		}
		return EMPTY_SHAPE;
	}

	protected boolean canEntityCollide(Entity entity) {return !EntityHelper.isImmuneToSolidEtherCollision(entity);}

	@Override public VoxelShape getVisualShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context) {return Shapes.empty();}
}