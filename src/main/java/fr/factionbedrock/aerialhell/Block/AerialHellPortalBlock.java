package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockEvent;

import javax.annotation.Nullable;

/*
 * I followed this tutorial : https://www.youtube.com/watch?v=CYdq8e-zDSo
 * Portal code from quek, The Undergarden mod owner : https://github.com/quek04/The-Undergarden
 */
/*
 * Copyright (c) 2019 quek
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class AerialHellPortalBlock extends Block
{
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
	protected static final VoxelShape X_AABB = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
	protected static final VoxelShape Z_AABB = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

	public AerialHellPortalBlock(BlockBehaviour.Properties properties)
	{
		super(properties);
		registerDefaultState(stateDefinition.any().setValue(AXIS, Direction.Axis.X));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
	{
		switch(state.getValue(AXIS))
		{
			case Z:
				return Z_AABB;
			case X:
			default:
				return X_AABB;
		}
	}

	public boolean trySpawnPortal(LevelAccessor level, BlockPos pos)
	{
		AerialHellPortalBlock.AerialHellPortalShape size = this.isPortal(level, pos);
		if (size != null && !this.isPortalSpawnCanceled(level, pos, size))
		{
			size.createPortalBlocks();
			return true;
		}
		else {return false;}
	}

	public boolean isPortalSpawnCanceled(LevelAccessor world, BlockPos pos, AerialHellPortalBlock.AerialHellPortalShape size)
	{
		return NeoForge.EVENT_BUS.post(new BlockEvent.PortalSpawnEvent(world, pos, world.getBlockState(pos), size)).isCanceled();
	}

	@Nullable public AerialHellPortalBlock.AerialHellPortalShape isPortal(LevelAccessor level, BlockPos pos)
	{
		AerialHellPortalBlock.AerialHellPortalShape UndergardenPortalBlock$size = new AerialHellPortalShape(level, pos, Direction.Axis.X);
		if (UndergardenPortalBlock$size.isValid() && UndergardenPortalBlock$size.numPortalBlocks == 0) {return UndergardenPortalBlock$size;}
		else
		{
			AerialHellPortalBlock.AerialHellPortalShape UndergardenPortalBlock$size1 = new AerialHellPortalShape(level, pos, Direction.Axis.Z);
			return UndergardenPortalBlock$size1.isValid() && UndergardenPortalBlock$size1.numPortalBlocks == 0 ? UndergardenPortalBlock$size1 : null;
		}
	}

	@Override public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos)
	{
		Direction.Axis direction$axis = facing.getAxis();
		Direction.Axis direction$axis1 = stateIn.getValue(AXIS);
		boolean flag = direction$axis1 != direction$axis && direction$axis.isHorizontal();
		return !flag && facingState.getBlock() != this && !(new AerialHellPortalShape(level, currentPos, direction$axis1)).isComplete() ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, level, currentPos, facingPos);
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity)
	{
		if(!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions())
		{
			if (entity instanceof LivingEntity livingEntity && EntityHelper.isLivingEntityOnPortalCooldown(livingEntity) && !EntityHelper.isCreativePlayer(entity))
			{
				EntityHelper.setAfterTeleportationEffect((LivingEntity)entity);
			}
			else if(entity.isOnPortalCooldown()) {entity.setPortalCooldown();}
			else
			{
				if (entity instanceof LivingEntity livingEntity && !EntityHelper.isCreativePlayer(entity))
				{
					if (EntityHelper.isLivingEntityUnderAerialHellPortalEffect(livingEntity))
					{
						if (EntityHelper.isLivingEntityReadyToTeleport(livingEntity))
						{
							EntityHelper.tryTeleportEntityWithAerialHellPortal(entity, pos);
							EntityHelper.setAfterTeleportationEffect(livingEntity);
						}
					}
					else if (EntityHelper.shouldLivingEntityHavePortalEffect(livingEntity))
					{
						EntityHelper.setAerialHellPortalEffect(livingEntity);
					}
				}
				else
				{
					EntityHelper.tryTeleportEntityWithAerialHellPortal(entity, pos);
				}
			}
		}
	}

	@Override public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random)
	{
		if (random.nextInt(100) == 0)
		{
			level.playLocalSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, AerialHellSoundEvents.BLOCK_AERIAL_HELL_PORTAL_AMBIENT.get(), SoundSource.BLOCKS, 0.6F, 0.9F + random.nextFloat() * 0.2F, false);
		}

		for(int i = 0; i < 4; ++i)
		{
			double x = (double)pos.getX() + random.nextDouble();
			double y = (double)pos.getY() + random.nextDouble();
			double z = (double)pos.getZ() + random.nextDouble();
			double xSpeed = ((double)random.nextFloat() - 0.5D) * 0.5D;
			double ySpeed = ((double)random.nextFloat() - 0.5D) * 0.5D;
			double zSpeed = ((double)random.nextFloat() - 0.5D) * 0.5D;
			int j = random.nextInt(2) * 2 - 1;
			if (!level.getBlockState(pos.west()).is(this) && !level.getBlockState(pos.east()).is(this))
			{
				x = (double)pos.getX() + 0.5D + 0.25D * (double)j;
				xSpeed = random.nextFloat() * 2.0F * (float)j;
			}
			else
			{
				z = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
				zSpeed = random.nextFloat() * 2.0F * (float)j;
			}

			level.addParticle(AerialHellParticleTypes.AERIAL_HELL_PORTAL.get(), x, y, z, xSpeed, ySpeed, zSpeed);
		}
	}

	@Override public ItemStack getCloneItemStack(BlockState state, HitResult target, LevelReader level, BlockPos pos, Player player) {return ItemStack.EMPTY;}

	@Override
	public BlockState rotate(BlockState state, Rotation rot)
	{
		switch(rot)
		{
			case COUNTERCLOCKWISE_90:
			case CLOCKWISE_90:
				switch(state.getValue(AXIS))
				{
					case Z:
						return state.setValue(AXIS, Direction.Axis.X);
					case X:
						return state.setValue(AXIS, Direction.Axis.Z);
					default:
						return state;
				}
			default:
				return state;
		}
	}

	@Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(AXIS);}

	//copy of Minecraft's PortalShape, frame and portal blocks changed accordingly
	public static class AerialHellPortalShape extends PortalShape
	{
		private static final int MIN_WIDTH = 1;
		public static final int MAX_WIDTH = 21;
		private static final int MIN_HEIGHT = 2;
		public static final int MAX_HEIGHT = 21;
		private static final BlockBehaviour.StatePredicate FRAME = (state, getter, pos) -> state.is(AerialHellBlocksAndItems.STELLAR_PORTAL_FRAME_BLOCK);
		private final LevelAccessor level;
		private final Direction.Axis axis;
		private final Direction rightDir;
		private int numPortalBlocks;
		private BlockPos bottomLeft;
		private int height;
		private final int width;

		public AerialHellPortalShape(LevelAccessor level, BlockPos bottomLeftPos, Direction.Axis axis)
		{
			super(level, bottomLeftPos, axis);
			this.level = level;
			this.axis = axis;
			this.rightDir = axis == Direction.Axis.X ? Direction.WEST : Direction.SOUTH;
			this.bottomLeft = this.calculateBottomLeft(bottomLeftPos);
			if (this.bottomLeft == null)
			{
				this.bottomLeft = bottomLeftPos;
				this.width = 1;
				this.height = 1;
			}
			else
			{
				this.width = this.calculateWidth();
				if (this.width > 0) {this.height = this.calculateHeight();}
			}
		}

		@Nullable
		private BlockPos calculateBottomLeft(BlockPos pos)
		{
			for (int i = Math.max(this.level.getMinBuildHeight(), pos.getY() - MAX_HEIGHT); pos.getY() > i && isEmpty(this.level.getBlockState(pos.below())); pos = pos.below()){}

			Direction direction = this.rightDir.getOpposite();
			int j = this.getDistanceUntilEdgeAboveFrame(pos, direction) - 1;
			return j < 0 ? null : pos.relative(direction, j);
		}

		private int calculateWidth()
		{
			int i = this.getDistanceUntilEdgeAboveFrame(this.bottomLeft, this.rightDir);
			return i >= MIN_WIDTH && i <= MAX_WIDTH ? i : 0;
		}

		private int getDistanceUntilEdgeAboveFrame(BlockPos pos, Direction direction)
		{
			BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

			for (int i = 0; i <= MAX_WIDTH; ++i)
			{
				mutablePos.set(pos).move(direction, i);
				BlockState blockstate = this.level.getBlockState(mutablePos);
				if (!isEmpty(blockstate)) {if (FRAME.test(blockstate, this.level, mutablePos)) {return i;} break;}

				BlockState blockstate1 = this.level.getBlockState(mutablePos.move(Direction.DOWN));
				if (!FRAME.test(blockstate1, this.level, mutablePos)) {break;}
			}
			return 0;
		}

		private int calculateHeight()
		{
			BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
			int i = this.getDistanceUntilTop(mutablePos);
			return i >= MIN_HEIGHT && i <= MAX_HEIGHT && this.hasTopFrame(mutablePos, i) ? i : 0;
		}

		private boolean hasTopFrame(BlockPos.MutableBlockPos pos, int height)
		{
			for (int i = 0; i < this.width; ++i)
			{
				BlockPos.MutableBlockPos mutablePos = pos.set(this.bottomLeft).move(Direction.UP, height).move(this.rightDir, i);
				if (!FRAME.test(this.level.getBlockState(mutablePos), this.level, mutablePos)) {return false;}
			}
			return true;
		}

		private int getDistanceUntilTop(BlockPos.MutableBlockPos pos)
		{
			for (int i = 0; i < MAX_HEIGHT; ++i)
			{
				pos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, -1);
				if (!FRAME.test(this.level.getBlockState(pos), this.level, pos)) {return i;}

				pos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, this.width);
				if (!FRAME.test(this.level.getBlockState(pos), this.level, pos)) {return i;}

				for (int j = 0; j < this.width; ++j)
				{
					pos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, j);
					BlockState blockstate = this.level.getBlockState(pos);
					if (!isEmpty(blockstate)) {return i;}

					if (blockstate.is(AerialHellBlocksAndItems.AERIAL_HELL_PORTAL.get())) {++this.numPortalBlocks;}
				}
			}
			return MAX_HEIGHT;
		}

		private static boolean isEmpty(BlockState state) {return state.isAir() || state.is(AerialHellBlocksAndItems.AERIAL_HELL_PORTAL.get());}
		public boolean isValid() {return this.bottomLeft != null && this.width >= MIN_WIDTH && this.width <= MAX_WIDTH && this.height >= MIN_HEIGHT && this.height <= MAX_HEIGHT;}

		public void createPortalBlocks()
		{
			BlockState blockstate = AerialHellBlocksAndItems.AERIAL_HELL_PORTAL.get().defaultBlockState().setValue(NetherPortalBlock.AXIS, this.axis);
			BlockPos.betweenClosed(this.bottomLeft, this.bottomLeft.relative(Direction.UP, this.height - 1).relative(this.rightDir, this.width - 1)).forEach(pos -> this.level.setBlock(pos, blockstate, 18));
		}

		public boolean isComplete() {return this.isValid() && this.numPortalBlocks == this.width * this.height;}
	}
}