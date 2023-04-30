package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.AbstractChestMimicEntity;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.*;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import fr.factionbedrock.aerialhell.BlockEntity.ChestMimicBlockEntity;

import java.util.function.BiPredicate;

public class ChestMimicBlock extends BaseEntityBlock implements SimpleWaterloggedBlock
{
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final EnumProperty<ChestType> TYPE = BlockStateProperties.CHEST_TYPE;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	protected static final VoxelShape SHAPE_NORTH = Block.box(1.0D, 0.0D, 0.0D, 15.0D, 14.0D, 15.0D);
	protected static final VoxelShape SHAPE_SOUTH = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 16.0D);
	protected static final VoxelShape SHAPE_WEST = Block.box(0.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);
	protected static final VoxelShape SHAPE_EAST = Block.box(1.0D, 0.0D, 1.0D, 16.0D, 14.0D, 15.0D);
	protected static final VoxelShape SHAPE_SINGLE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);

	public ChestMimicBlock(BlockBehaviour.Properties builder)
	{
		super(builder);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TYPE, ChestType.SINGLE).setValue(WATERLOGGED, Boolean.valueOf(false)));
	}

	@Override
	public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit)
	{
		if (!ChestBlock.isChestBlockedAt(worldIn, pos))
		{
			if (worldIn.isClientSide()) {addSpawnParticle(worldIn, pos);}
			else
			{
				worldIn.playSound(null, pos, SoundEvents.CHEST_OPEN, SoundSource.BLOCKS, 0.5F, 0.7F + 0.5F * worldIn.random.nextFloat());
				revealMimic(state, worldIn, pos);
				worldIn.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
			}
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public void spawnAfterBreak(BlockState state, ServerLevel worldIn, BlockPos pos, ItemStack stack)
	{
		super.spawnAfterBreak(state, worldIn, pos, stack);
		revealMimic(state, worldIn, pos);
	}

	private void revealMimic(BlockState state, Level worldIn, BlockPos pos)
	{
		float angle = state.getValue(FACING).toYRot();
		AbstractChestMimicEntity chestMimic = getNewChestMimicEntity(worldIn);
		chestMimic.absMoveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, angle, 0.0F);
		chestMimic.setYHeadRot(angle);
		worldIn.addFreshEntity(chestMimic);
	}

	public void addSpawnParticle(Level worldIn, BlockPos pos)
	{
		for(int i = 0; i < 20; ++i)
		{
			double dx = worldIn.random.nextGaussian() * 0.04D, dy = worldIn.random.nextGaussian() * 0.04D, dz = worldIn.random.nextGaussian() * 0.04D;
			double x = pos.getX() + 0.5F + dx * 10.0D, y = pos.getY() + 0.5F + dy * 10.0D, z = pos.getZ() + 0.5F + dz * 10.0D;
			worldIn.addParticle(this.getMimicSpawnParticle(), x, y, z, dx * 10.0D, dy * 10.0D, dz * 10.0D);
		}
	}

	private SimpleParticleType getMimicSpawnParticle()
	{
		if (this == AerialHellBlocksAndItems.AERIAL_TREE_CHEST_MIMIC.get()) {return AerialHellParticleTypes.OSCILLATOR.get();}
		else if (this == AerialHellBlocksAndItems.COPPER_PINE_CHEST_MIMIC.get()) {return AerialHellParticleTypes.COPPER_PINE_LEAVES.get();}
		else if (this == AerialHellBlocksAndItems.GOLDEN_BEECH_CHEST_MIMIC.get()) {return AerialHellParticleTypes.LUNATIC_PARTICLE.get();}
		else /*if (this == AerialHellBlocksAndItems.SKY_CACTUS_FIBER_CHEST_MIMIC.get())*/ {return AerialHellParticleTypes.LUNATIC_PARTICLE.get();}
	}

	private AbstractChestMimicEntity getNewChestMimicEntity(Level worldIn)
	{
		if (this == AerialHellBlocksAndItems.AERIAL_TREE_CHEST_MIMIC.get()) {return new AerialTreeChestMimicEntity(AerialHellEntities.AERIAL_TREE_MIMIC.get(), worldIn);}
		else if (this == AerialHellBlocksAndItems.COPPER_PINE_CHEST_MIMIC.get()) {return new CopperPineChestMimicEntity(AerialHellEntities.COPPER_PINE_MIMIC.get(), worldIn);}
		else if (this == AerialHellBlocksAndItems.GOLDEN_BEECH_CHEST_MIMIC.get()) {return new GoldenBeechChestMimicEntity(AerialHellEntities.GOLDEN_BEECH_MIMIC.get(), worldIn);}
		else /*if (this == AerialHellBlocksAndItems.SKY_CACTUS_FIBER_CHEST_MIMIC.get())*/ {return new SkyCactusFiberChestMimicEntity(AerialHellEntities.SKY_CACTUS_FIBER_MIMIC.get(), worldIn);}
	}

	@Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new ChestMimicBlockEntity(pos, state);}
	@Override public BlockState rotate(BlockState state, Rotation rot) {return state.setValue(FACING, rot.rotate(state.getValue(FACING)));}
	@Override public BlockState mirror(BlockState state, Mirror mirrorIn) {return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));}

	/*Copied from vanilla ChestBlock class*/
	@Override
	public RenderShape getRenderShape(BlockState state)
	{
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) //updatePostPlacement (snapshot mappings)
	{
		if (stateIn.getValue(WATERLOGGED))
		{
			worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}

		if (facingState.is(this) && facing.getAxis().isHorizontal())
		{
			ChestType chesttype = facingState.getValue(TYPE);
			if (stateIn.getValue(TYPE) == ChestType.SINGLE && chesttype != ChestType.SINGLE && stateIn.getValue(FACING) == facingState.getValue(FACING) && getDirectionToAttached(facingState) == facing.getOpposite())
			{
				return stateIn.setValue(TYPE, chesttype.getOpposite());
			}
		} else if (getDirectionToAttached(stateIn) == facing)
		{
			return stateIn.setValue(TYPE, ChestType.SINGLE);
		}
		return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		if (state.getValue(TYPE) == ChestType.SINGLE)
		{
			return SHAPE_SINGLE;
		} else {
			switch(getDirectionToAttached(state))
			{
				case NORTH:
				default:
					return SHAPE_NORTH;
				case SOUTH:
					return SHAPE_SOUTH;
				case WEST:
					return SHAPE_WEST;
				case EAST:
					return SHAPE_EAST;
			}
		}
	}

	public static Direction getDirectionToAttached(BlockState state)
	{
		Direction direction = state.getValue(FACING);
		return state.getValue(TYPE) == ChestType.LEFT ? direction.getClockWise() : direction.getCounterClockWise();
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context)
	{
		ChestType chesttype = ChestType.SINGLE;
		Direction direction = context.getHorizontalDirection().getOpposite();
		FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
		boolean flag = context.isSecondaryUseActive();
		Direction direction1 = context.getClickedFace();
		if (direction1.getAxis().isHorizontal() && flag)
		{
			Direction direction2 = this.getDirectionToAttach(context, direction1.getOpposite());
			if (direction2 != null && direction2.getAxis() != direction1.getAxis())
			{
				direction = direction2;
				chesttype = direction2.getCounterClockWise() == direction1.getOpposite() ? ChestType.RIGHT : ChestType.LEFT;
			}
		}

		if (chesttype == ChestType.SINGLE && !flag)
		{
			if (direction == this.getDirectionToAttach(context, direction.getClockWise())) {chesttype = ChestType.LEFT;}
			else if (direction == this.getDirectionToAttach(context, direction.getCounterClockWise())) {chesttype = ChestType.RIGHT;}
		}

		return this.defaultBlockState().setValue(FACING, direction).setValue(TYPE, chesttype).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
	}

	@Override
	public FluidState getFluidState(BlockState state)
	{
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Nullable
	private Direction getDirectionToAttach(BlockPlaceContext context, Direction direction)
	{
		BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(direction));
		return blockstate.is(this) && blockstate.getValue(TYPE) == ChestType.SINGLE ? blockstate.getValue(FACING) : null;
	}

	public DoubleBlockCombiner.NeighborCombineResult<? extends ChestMimicBlockEntity> combine(BlockState state, Level world, BlockPos pos, boolean override)
	{
		BiPredicate<LevelAccessor, BlockPos> bipredicate;
		if (override) {bipredicate = (worldIn, posIn) -> false;}
		else {bipredicate = ChestBlock::isChestBlockedAt;}

		return DoubleBlockCombiner.combineWithNeigbour(AerialHellBlockEntities.CHEST_MIMIC.get(), ChestBlock::getBlockType, ChestBlock::getConnectedDirection, FACING, state, world, pos, bipredicate);
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(FACING, TYPE, WATERLOGGED);}
	@Override public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {return false;}


}