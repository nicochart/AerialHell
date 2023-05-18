package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;

import fr.factionbedrock.aerialhell.Util.BlockHelper;
import fr.factionbedrock.aerialhell.World.AerialHellTeleporter;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import javax.annotation.Nullable;

import java.util.Random;

@EventBusSubscriber(modid = AerialHell.MODID)
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
		AerialHellPortalBlock.Size portalSize = this.isPortal(level, pos);
		if (portalSize != null && !onTrySpawnPortal(level, pos, portalSize))
		{
			portalSize.placePortalBlocks();
			return true;
		}
		else {return false;}
	}

	public static boolean onTrySpawnPortal(LevelAccessor world, BlockPos pos, AerialHellPortalBlock.Size size)
	{
		return MinecraftForge.EVENT_BUS.post(new PortalSpawnEvent(world, pos, world.getBlockState(pos), size));
	}

	@Cancelable
	public static class PortalSpawnEvent extends BlockEvent
	{
		private final AerialHellPortalBlock.Size size;

		public PortalSpawnEvent(LevelAccessor world, BlockPos pos, BlockState state, AerialHellPortalBlock.Size size)
		{
			super(world, pos, state);
			this.size = size;
		}

		public AerialHellPortalBlock.Size getPortalSize() {return size;}
	}

	@Nullable
	public AerialHellPortalBlock.Size isPortal(LevelAccessor level, BlockPos pos)
	{
		AerialHellPortalBlock.Size sizeX = new Size(level, pos, Direction.Axis.X);
		if (sizeX.isValid() && sizeX.portalBlockCount == 0) {return sizeX;}
		else
		{
			AerialHellPortalBlock.Size sizeZ = new Size(level, pos, Direction.Axis.Z);
			return sizeZ.isValid() && sizeZ.portalBlockCount == 0 ? sizeZ : null;
		}
	}

	@Override
	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos)
	{
		Direction.Axis direction$axis = facing.getAxis();
		Direction.Axis direction$axis1 = stateIn.getValue(AXIS);
		boolean flag = direction$axis1 != direction$axis && direction$axis.isHorizontal();
		return !flag && facingState.getBlock() != this && !(new Size(level, currentPos, direction$axis1)).validatePortal() ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, level, currentPos, facingPos);
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity)
	{
		if(!entity.isPassenger() && !entity.isVehicle() && entity.canChangeDimensions())
		{
			if(entity.isOnPortalCooldown()) {entity.setPortalCooldown();}
			else
			{
				if(!entity.level.isClientSide && !pos.equals(entity.portalEntrancePos)) {entity.portalEntrancePos = pos.immutable();}
				Level entityWorld = entity.level;
				if(entityWorld != null)
				{
					MinecraftServer minecraftserver = entityWorld.getServer();
					ResourceKey<Level> destination = entity.level.dimension() == AerialHellDimensions.AERIAL_HELL_DIMENSION ? Level.OVERWORLD : AerialHellDimensions.AERIAL_HELL_DIMENSION;
					if(minecraftserver != null) {
						ServerLevel destinationWorld = minecraftserver.getLevel(destination);
						if(destinationWorld != null && minecraftserver.isNetherEnabled() && !entity.isPassenger()) {
							entity.level.getProfiler().push("aerialhell_portal");
							entity.setPortalCooldown();
							entity.changeDimension(destinationWorld, new AerialHellTeleporter(destinationWorld));
							entity.level.getProfiler().pop();
						}
					}
				}
			}
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override public void animateTick(BlockState state, Level level, BlockPos pos, Random random)
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

	@Override public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {return ItemStack.EMPTY;}

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

	public static class Size
	{
		private final LevelAccessor level;
		private final Direction.Axis axis;
		private final Direction rightDir;
		private final Direction leftDir;
		private int portalBlockCount;
		@Nullable
		private BlockPos bottomLeft;
		private int height;
		private int width;

		public Size(LevelAccessor level, BlockPos pos, Direction.Axis axis)
		{
			this.level = level;
			this.axis = axis;
			if (axis == Direction.Axis.X)
			{
				this.leftDir = Direction.EAST;
				this.rightDir = Direction.WEST;
			}
			else
			{
				this.leftDir = Direction.NORTH;
				this.rightDir = Direction.SOUTH;
			}

			for (BlockPos blockpos = pos; pos.getY() > blockpos.getY() - 21 && pos.getY() > 0 && this.canConnect(level.getBlockState(pos.below())); pos = pos.below()) {}

			int i = this.getDistanceUntilEdge(pos, this.leftDir) - 1;
			if (i >= 0)
			{
				this.bottomLeft = pos.relative(this.leftDir, i);
				this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
				if (this.width < 2 || this.width > 21)
				{
					this.bottomLeft = null;
					this.width = 0;
				}
			}
			if (this.bottomLeft != null) {this.height = this.calculatePortalHeight();}
		}

		protected int getDistanceUntilEdge(BlockPos pos, Direction directionIn)
		{
			int distance; BlockState state;
			for(distance = 0; distance < 22; ++distance)
			{
				BlockPos blockpos = pos.relative(directionIn, distance);
				state = this.level.getBlockState(blockpos.below());
				if(!this.canConnect(this.level.getBlockState(blockpos)) || !BlockHelper.isAerialHellPortalFrameBlock(state)) {break;}
			}

			BlockPos framePos = pos.relative(directionIn, distance);
			state = this.level.getBlockState(framePos);
			return BlockHelper.isAerialHellPortalFrameBlock(state) ? distance : 0;
		}

		public int getHeight() {return this.height;}

		public int getWidth() {return this.width;}

		protected int calculatePortalHeight()
		{
			label:
			for(this.height = 0; this.height < 21; ++this.height)
			{
				for(int i = 0; i < this.width; ++i)
				{
					BlockPos blockpos = this.bottomLeft.relative(this.rightDir, i).above(this.height);
					BlockState blockstate = this.level.getBlockState(blockpos);
					if (!this.canConnect(blockstate)) {break label;}

					Block block = blockstate.getBlock();
					if (block == AerialHellBlocksAndItems.AERIAL_HELL_PORTAL.get()) {++this.portalBlockCount;}

					if (i == 0)
					{
						BlockState state = level.getBlockState(blockpos.relative(this.leftDir));
						if (!BlockHelper.isAerialHellPortalFrameBlock(state)) {break label;}
					}
					else if (i == this.width - 1)
					{
						BlockState state = level.getBlockState(blockpos.relative(this.rightDir));
						if (!BlockHelper.isAerialHellPortalFrameBlock(state)) {break label;}
					}
				}
			}

			for(int j = 0; j < this.width; ++j)
			{
				BlockState state = level.getBlockState(this.bottomLeft.relative(this.rightDir, j).above(this.height));
				if (!BlockHelper.isAerialHellPortalFrameBlock(state)) {this.height = 0; break;}
			}

			if (this.height <= 21 && this.height >= 3) {return this.height;}
			else
			{
				this.bottomLeft = null;
				this.width = 0;
				this.height = 0;
				return 0;
			}
		}

		protected boolean canConnect(BlockState pos)
		{
			Block block = pos.getBlock();
			return pos.isAir() || block == AerialHellBlocksAndItems.AERIAL_HELL_PORTAL.get();
		}

		public boolean isValid()
		{
			return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
		}

		public void placePortalBlocks()
		{
			for(int i = 0; i < this.width; ++i)
			{
				BlockPos blockpos = this.bottomLeft.relative(this.rightDir, i);

				for(int j = 0; j < this.height; ++j)
				{
					this.level.setBlock(blockpos.above(j), AerialHellBlocksAndItems.AERIAL_HELL_PORTAL.get().defaultBlockState().setValue(AerialHellPortalBlock.AXIS, this.axis), 18);
				}
			}
		}

		private boolean isPortalCountValidForSize() {return this.portalBlockCount >= this.width * this.height;}

		public boolean validatePortal() {return this.isValid() && this.isPortalCountValidForSize();}
	}
}