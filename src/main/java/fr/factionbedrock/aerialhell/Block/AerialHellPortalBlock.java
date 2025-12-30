package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import fr.factionbedrock.aerialhell.World.AerialHellTeleporter;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.*;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.NetherPortal;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AerialHellPortalBlock extends Block implements Portal
{
    public static final EnumProperty<Direction.Axis> AXIS = Properties.HORIZONTAL_AXIS;
    protected static final VoxelShape X_AABB = Block.createCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.createCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    public AerialHellPortalBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AXIS, Direction.Axis.X));
    }

    @Override protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        switch(state.get(AXIS))
        {
            case Z:
                return Z_AABB;
            case X:
            default:
                return X_AABB;
        }
    }

    public boolean trySpawnPortal(WorldAccess world, BlockPos pos)
    {
        AerialHellPortalBlock.AerialHellPortalShape size = this.isPortal(world, pos);
        if (size != null)
        {
            size.createPortalBlocks(world);
            return true;
        }
        else {return false;}
    }

    @Nullable public AerialHellPortalBlock.AerialHellPortalShape isPortal(WorldAccess world, BlockPos pos)
    {
        AerialHellPortalBlock.AerialHellPortalShape portalShape = new AerialHellPortalShape(world, pos, Direction.Axis.X);
        if (portalShape.isValid() && portalShape.numPortalBlocks == 0) {return portalShape;}
        else
        {
            AerialHellPortalBlock.AerialHellPortalShape portalShape2 = new AerialHellPortalShape(world, pos, Direction.Axis.Z);
            return portalShape2.isValid() && portalShape2.numPortalBlocks == 0 ? portalShape2 : null;
        }
    }

    @Override public BlockState getStateForNeighborUpdate(BlockState stateIn, WorldView world, ScheduledTickView tickView, BlockPos currentPos, Direction facing, BlockPos facingPos, BlockState facingState, Random rand)
    {
        Direction.Axis direction$axis = facing.getAxis();
        Direction.Axis direction$axis1 = stateIn.get(AXIS);
        boolean flag = direction$axis1 != direction$axis && direction$axis.isHorizontal();
        return !flag && facingState.getBlock() != this && !(new AerialHellPortalShape(world, currentPos, direction$axis1)).isComplete() ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(stateIn, world, tickView, currentPos, facing, facingPos, facingState, rand);
    }

    @Override protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler, boolean intersects)
    {
        if(entity.canUsePortals(false))
        {
            if (entity instanceof LivingEntity livingEntity && EntityHelper.isLivingEntityOnPortalCooldown(livingEntity))
            {
                EntityHelper.setAfterTeleportationEffect((LivingEntity)entity, !EntityHelper.isCreativePlayer(entity) ? 110 : 20);
            }
            else if (entity.hasPortalCooldown()) {entity.resetPortalCooldown();}
            else
            {
                if (entity instanceof LivingEntity livingEntity)
                {
                    if (!EntityHelper.isCreativePlayer(entity))
                    {
                        if (EntityHelper.isLivingEntityUnderAerialHellPortalEffect(livingEntity))
                        {
                            if (EntityHelper.isLivingEntityReadyToTeleport(livingEntity))
                            {
                                EntityHelper.tryTeleportEntityWithAerialHellPortal(entity, this, pos);
                                EntityHelper.setAfterTeleportationEffect(livingEntity, 110);
                            }
                        }
                        else if (EntityHelper.shouldLivingEntityHavePortalEffect(livingEntity))
                        {
                            EntityHelper.setAerialHellPortalEffect(livingEntity);
                        }
                    }
                    else
                    {
                        EntityHelper.tryTeleportEntityWithAerialHellPortal(entity, this, pos);
                        EntityHelper.setAfterTeleportationEffect(livingEntity, 20);
                    }
                }
                else
                {
                    EntityHelper.tryTeleportEntityWithAerialHellPortal(entity, this, pos);
                }
            }
        }
    }

    @Nullable @Override
    public TeleportTarget createTeleportTarget(ServerWorld world, Entity entity, BlockPos pos)
    {
        RegistryKey<World> resourcekey = world.getRegistryKey() == AerialHellDimensions.AERIAL_HELL_DIMENSION ? World.OVERWORLD : AerialHellDimensions.AERIAL_HELL_DIMENSION;
        ServerWorld serverWorld = world.getServer().getWorld(resourcekey);
        if (serverWorld == null) {return null;}
        else
        {
            boolean flag = serverWorld.getRegistryKey() == AerialHellDimensions.AERIAL_HELL_DIMENSION;
            WorldBorder worldborder = serverWorld.getWorldBorder();
            double d0 = DimensionType.getCoordinateScaleFactor(world.getDimension(), serverWorld.getDimension());
            BlockPos blockpos = worldborder.clampFloored(entity.getX() * d0, entity.getY(), entity.getZ() * d0);
            return this.getOrCreateExitPortalTarget(serverWorld, entity, pos, blockpos, flag, worldborder);
        }
    }

    @Nullable private TeleportTarget getOrCreateExitPortalTarget(ServerWorld world, Entity entity, BlockPos pos, BlockPos exitPos, boolean isNether, WorldBorder worldBorder)
    {
        AerialHellTeleporter portalForcer = new AerialHellTeleporter(world);
        Optional<BlockPos> optional = portalForcer.findClosestPortalPosition(exitPos, isNether, worldBorder); //TODO the problem is here (portal never found)
        BlockLocating.Rectangle blockutil$foundrectangle;
        TeleportTarget.PostDimensionTransition postTransition;
        if (optional.isPresent())
        {
            System.out.println("Other portal found");
            BlockPos blockpos = optional.get();
            BlockState blockstate = world.getBlockState(blockpos);
            blockutil$foundrectangle = BlockLocating.getLargestRectangle(
                    blockpos,
                    blockstate.get(Properties.HORIZONTAL_AXIS),
                    21,
                    Direction.Axis.Y,
                    21,
                    p_351970_ -> world.getBlockState(p_351970_) == blockstate
            );
            postTransition = TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET.then(entityx -> entityx.addPortalChunkTicketAt(blockpos));
        }
        else
        {
            System.out.println("No portal found");
            Direction.Axis direction$axis = entity.getEntityWorld().getBlockState(pos).getOrEmpty(AXIS).orElse(Direction.Axis.X);
            Optional<BlockLocating.Rectangle> optional1 = portalForcer.createPortal(exitPos, direction$axis);
            if (optional1.isEmpty())
            {
                AerialHell.LOGGER.error("Unable to create a portal, likely target out of worldborder");
                return null;
            }

            blockutil$foundrectangle = optional1.get();
            postTransition = TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET.then(TeleportTarget.ADD_PORTAL_CHUNK_TICKET);
        }

        return getExitPortalTarget(entity, pos, blockutil$foundrectangle, world, postTransition);
    }

    private static TeleportTarget getExitPortalTarget(Entity entity, BlockPos pos, BlockLocating.Rectangle rectangle, ServerWorld world, TeleportTarget.PostDimensionTransition postTeleportTarget)
    {
        BlockState blockstate = entity.getEntityWorld().getBlockState(pos);
        Direction.Axis direction$axis;
        Vec3d vec3d;
        if (blockstate.contains(Properties.HORIZONTAL_AXIS))
        {
            direction$axis = blockstate.get(Properties.HORIZONTAL_AXIS);
            BlockLocating.Rectangle blockutil$foundrectangle = BlockLocating.getLargestRectangle(
                    pos, direction$axis, 21, Direction.Axis.Y, 21, p_351016_ -> entity.getEntityWorld().getBlockState(p_351016_) == blockstate
            );
            vec3d = entity.positionInPortal(direction$axis, blockutil$foundrectangle);
        }
        else
        {
            direction$axis = Direction.Axis.X;
            vec3d = new Vec3d(0.5, 0.0, 0.0);
        }

        return createTeleportTarget(world, rectangle, direction$axis, vec3d, entity, postTeleportTarget);
    }

    private static TeleportTarget createTeleportTarget(ServerWorld level, BlockLocating.Rectangle exitPortalRectangle, Direction.Axis axis, Vec3d offset, Entity entity, TeleportTarget.PostDimensionTransition postTeleportTarget)
    {
        BlockPos blockpos = exitPortalRectangle.lowerLeft;
        BlockState blockstate = level.getBlockState(blockpos);
        Direction.Axis direction$axis = blockstate.getOrEmpty(Properties.HORIZONTAL_AXIS).orElse(Direction.Axis.X);
        double width = exitPortalRectangle.width;
        double height = exitPortalRectangle.height;
        EntityDimensions entitydimensions = entity.getDimensions(entity.getPose());
        int i = axis == direction$axis ? 0 : 90;
        Vec3d speed = entity.getVelocity();
        Vec3d vec3 = axis == direction$axis ? speed : new Vec3d(speed.z, speed.y, -speed.x);
        double d2 = (double)entitydimensions.width() / 2.0 + (width - (double)entitydimensions.width()) * offset.getX();
        double d3 = (height - (double)entitydimensions.height()) * offset.getY();
        double d4 = 0.5 + offset.getZ();
        boolean flag = direction$axis == Direction.Axis.X;
        Vec3d vec31 = new Vec3d((double)blockpos.getX() + (flag ? d2 : d4), (double)blockpos.getY() + d3, (double)blockpos.getZ() + (flag ? d4 : d2));
        Vec3d vec32 = AerialHellPortalShape.findOpenPosition(vec31, level, entity, entitydimensions);
        return new TeleportTarget(level, vec32, vec3, entity.getBodyYaw() + (float)i, entity.getPitch(), postTeleportTarget);
    }

    @Override public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random)
    {
        if (random.nextInt(100) == 0)
        {
            world.playSoundClient((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, AerialHellSoundEvents.BLOCK_AERIAL_HELL_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.6F, 0.9F + random.nextFloat() * 0.2F, false);
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
            if (!world.getBlockState(pos.west()).isOf(this) && !world.getBlockState(pos.east()).isOf(this))
            {
                x = (double)pos.getX() + 0.5D + 0.25D * (double)j;
                xSpeed = random.nextFloat() * 2.0F * (float)j;
            }
            else
            {
                z = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
                zSpeed = random.nextFloat() * 2.0F * (float)j;
            }

            world.addParticleClient(AerialHellParticleTypes.AERIAL_HELL_PORTAL, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }

    @Override protected ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state, boolean includeData) {return ItemStack.EMPTY;}

    @Override protected BlockState rotate(BlockState state, BlockRotation rotation)
    {
        switch (rotation)
        {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch ((Direction.Axis)state.get(AXIS))
                {
                    case X:
                        return state.with(AXIS, Direction.Axis.Z);
                    case Z:
                        return state.with(AXIS, Direction.Axis.X);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {builder.add(AXIS);}

    public static class AerialHellPortalShape extends NetherPortal
    {
        private static final int MIN_WIDTH = 1;
        public static final int MAX_WIDTH = 21;
        private static final int MIN_HEIGHT = 2;
        public static final int MAX_HEIGHT = 21;
        private static final AbstractBlock.ContextPredicate IS_VALID_FRAME_BLOCK = (state, getter, pos) -> state.isOf(AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK);
        private final WorldView level;
        private final Direction.Axis axis;
        private final Direction rightDir;
        private int numPortalBlocks;
        private BlockPos bottomLeft;
        private int height;
        private final int width;

        public AerialHellPortalShape(WorldView level, BlockPos bottomLeftPos, Direction.Axis axis)
        {
            super(axis, 0, Direction.WEST, bottomLeftPos, 0, 0);
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
            for (int i = Math.max(this.level.getBottomY(), pos.getY() - MAX_HEIGHT); pos.getY() > i && isEmpty(this.level.getBlockState(pos.down())); pos = pos.down()){}

            Direction direction = this.rightDir.getOpposite();
            int j = this.getDistanceUntilEdgeAboveFrame(pos, direction) - 1;
            return j < 0 ? null : pos.offset(direction, j);
        }

        private int calculateWidth()
        {
            int i = this.getDistanceUntilEdgeAboveFrame(this.bottomLeft, this.rightDir);
            return i >= MIN_WIDTH && i <= MAX_WIDTH ? i : 0;
        }

        private int getDistanceUntilEdgeAboveFrame(BlockPos pos, Direction direction)
        {
            BlockPos.Mutable mutable = new BlockPos.Mutable();

            for (int i = 0; i <= MAX_WIDTH; ++i)
            {
                mutable.set(pos).move(direction, i);
                BlockState blockstate = this.level.getBlockState(mutable);
                if (!isEmpty(blockstate)) {if (IS_VALID_FRAME_BLOCK.test(blockstate, this.level, mutable)) {return i;} break;}

                BlockState blockstate1 = this.level.getBlockState(mutable.move(Direction.DOWN));
                if (!IS_VALID_FRAME_BLOCK.test(blockstate1, this.level, mutable)) {break;}
            }
            return 0;
        }

        private int calculateHeight()
        {
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            int i = this.getDistanceUntilTop(mutable);
            return i >= MIN_HEIGHT && i <= MAX_HEIGHT && this.hasTopFrame(mutable, i) ? i : 0;
        }

        private boolean hasTopFrame(BlockPos.Mutable pos, int height)
        {
            for (int i = 0; i < this.width; ++i)
            {
                BlockPos.Mutable mutablePos = pos.set(this.bottomLeft).move(Direction.UP, height).move(this.rightDir, i);
                if (!IS_VALID_FRAME_BLOCK.test(this.level.getBlockState(mutablePos), this.level, mutablePos)) {return false;}
            }
            return true;
        }

        private int getDistanceUntilTop(BlockPos.Mutable pos)
        {
            for (int i = 0; i < MAX_HEIGHT; ++i)
            {
                pos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, -1);
                if (!IS_VALID_FRAME_BLOCK.test(this.level.getBlockState(pos), this.level, pos)) {return i;}

                pos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, this.width);
                if (!IS_VALID_FRAME_BLOCK.test(this.level.getBlockState(pos), this.level, pos)) {return i;}

                for (int j = 0; j < this.width; ++j)
                {
                    pos.set(this.bottomLeft).move(Direction.UP, i).move(this.rightDir, j);
                    BlockState blockstate = this.level.getBlockState(pos);
                    if (!isEmpty(blockstate)) {return i;}

                    if (blockstate.isOf(AerialHellBlocks.AERIAL_HELL_PORTAL)) {++this.numPortalBlocks;}
                }
            }
            return MAX_HEIGHT;
        }

        private static boolean isEmpty(BlockState state) {return state.isAir() || state.isOf(AerialHellBlocks.AERIAL_HELL_PORTAL);}
        public boolean isValid() {return this.bottomLeft != null && this.width >= MIN_WIDTH && this.width <= MAX_WIDTH && this.height >= MIN_HEIGHT && this.height <= MAX_HEIGHT;}

        public void createPortalBlocks(WorldAccess world)
        {
            BlockState blockstate = AerialHellBlocks.AERIAL_HELL_PORTAL.getDefaultState().with(NetherPortalBlock.AXIS, this.axis);
            BlockPos.iterate(this.bottomLeft, this.bottomLeft.offset(Direction.UP, this.height - 1).offset(this.rightDir, this.width - 1)).forEach(pos -> world.setBlockState(pos, blockstate, 18));
        }

        public boolean isComplete() {return this.isValid() && this.numPortalBlocks == this.width * this.height;}
    }
}
