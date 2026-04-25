package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellDimensions;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import fr.factionbedrock.aerialhell.World.AerialHellTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.BlockUtil;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AerialHellPortalBlock extends Block implements Portal
{
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    protected static final VoxelShape X_AABB = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
    protected static final VoxelShape Z_AABB = Block.box(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

    public AerialHellPortalBlock(Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    @Override protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
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

    public boolean trySpawnPortal(LevelAccessor world, BlockPos pos)
    {
        AerialHellPortalBlock.AerialHellPortalShape size = this.isPortal(world, pos);
        if (size != null)
        {
            size.createPortalBlocks(world);
            return true;
        }
        else {return false;}
    }

    @Nullable public AerialHellPortalBlock.AerialHellPortalShape isPortal(LevelAccessor world, BlockPos pos)
    {
        AerialHellPortalBlock.AerialHellPortalShape portalShape = new AerialHellPortalShape(world, pos, Direction.Axis.X);
        if (portalShape.isValid() && portalShape.numPortalBlocks == 0) {return portalShape;}
        else
        {
            AerialHellPortalBlock.AerialHellPortalShape portalShape2 = new AerialHellPortalShape(world, pos, Direction.Axis.Z);
            return portalShape2.isValid() && portalShape2.numPortalBlocks == 0 ? portalShape2 : null;
        }
    }

    @Override public BlockState updateShape(BlockState stateIn, LevelReader world, ScheduledTickAccess tickView, BlockPos currentPos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource rand)
    {
        Direction.Axis direction$axis = facing.getAxis();
        Direction.Axis direction$axis1 = stateIn.getValue(AXIS);
        boolean flag = direction$axis1 != direction$axis && direction$axis.isHorizontal();
        return !flag && facingState.getBlock() != this && !(new AerialHellPortalShape(world, currentPos, direction$axis1)).isComplete() ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, world, tickView, currentPos, facing, facingPos, facingState, rand);
    }

    @Override protected void entityInside(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler, boolean intersects)
    {
        if(entity.canUsePortal(false))
        {
            if (entity instanceof LivingEntity livingEntity && EntityHelper.isLivingEntityOnPortalCooldown(livingEntity))
            {
                EntityHelper.setAfterTeleportationEffect(livingEntity, !EntityHelper.isCreativePlayer(entity) ? 110 : 20);
            }
            else if (entity.isOnPortalCooldown()) {entity.setPortalCooldown();}
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
                    @Nullable LivingEntity firstPassenger = entity.getFirstPassenger() instanceof LivingEntity livingEntity ? livingEntity : null;
                    if (firstPassenger != null && EntityHelper.isLivingEntityOnPortalCooldown(firstPassenger))
                    {
                        EntityHelper.setAfterTeleportationEffect(firstPassenger, !EntityHelper.isCreativePlayer(entity) ? 110 : 20);
                    }
                    else
                    {
                        EntityHelper.tryTeleportEntityWithAerialHellPortal(entity, this, pos);
                        entity.setPortalCooldown();
                        if (firstPassenger != null) {EntityHelper.setAfterTeleportationEffect(firstPassenger, !EntityHelper.isCreativePlayer(entity) ? 110 : 20);}
                    }
                }
            }
        }
    }

    @Nullable @Override
    public TeleportTransition getPortalDestination(ServerLevel world, Entity entity, BlockPos pos)
    {
        ResourceKey<Level> resourcekey = world.dimension() == AerialHellDimensions.AERIAL_HELL_DIMENSION ? Level.OVERWORLD : AerialHellDimensions.AERIAL_HELL_DIMENSION;
        ServerLevel serverWorld = world.getServer().getLevel(resourcekey);
        if (serverWorld == null) {return null;}
        else
        {
            boolean flag = serverWorld.dimension() == AerialHellDimensions.AERIAL_HELL_DIMENSION;
            WorldBorder worldborder = serverWorld.getWorldBorder();
            double d0 = DimensionType.getTeleportationScale(world.dimensionType(), serverWorld.dimensionType());
            BlockPos blockpos = worldborder.clampToBounds(entity.getX() * d0, entity.getY(), entity.getZ() * d0);
            return this.getOrCreateExitPortalTarget(serverWorld, entity, pos, blockpos, flag, worldborder);
        }
    }

    @Nullable private TeleportTransition getOrCreateExitPortalTarget(ServerLevel world, Entity entity, BlockPos pos, BlockPos exitPos, boolean isNether, WorldBorder worldBorder)
    {
        AerialHellTeleporter portalForcer = new AerialHellTeleporter(world);
        Optional<BlockPos> optional = portalForcer.findClosestPortalPosition(exitPos, isNether, worldBorder);
        BlockUtil.FoundRectangle blockutil$foundrectangle;
        TeleportTransition.PostTeleportTransition postTransition;
        if (optional.isPresent())
        {
            BlockPos blockpos = optional.get();
            BlockState blockstate = world.getBlockState(blockpos);
            blockutil$foundrectangle = BlockUtil.getLargestRectangleAround(
                    blockpos,
                    blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS),
                    21,
                    Direction.Axis.Y,
                    21,
                    p_351970_ -> world.getBlockState(p_351970_) == blockstate
            );
            postTransition = TeleportTransition.PLAY_PORTAL_SOUND.then(entityx -> entityx.placePortalTicket(blockpos));
        }
        else
        {
            Direction.Axis direction$axis = entity.level().getBlockState(pos).getOptionalValue(AXIS).orElse(Direction.Axis.X);
            Optional<BlockUtil.FoundRectangle> optional1 = portalForcer.createPortal(exitPos, direction$axis);
            if (optional1.isEmpty())
            {
                AerialHell.LOGGER.error("Unable to create a portal, likely target out of worldborder");
                return null;
            }

            blockutil$foundrectangle = optional1.get();
            postTransition = TeleportTransition.PLAY_PORTAL_SOUND.then(TeleportTransition.PLACE_PORTAL_TICKET);
        }

        return getExitPortalTarget(entity, pos, blockutil$foundrectangle, world, postTransition);
    }

    private static TeleportTransition getExitPortalTarget(Entity entity, BlockPos pos, BlockUtil.FoundRectangle rectangle, ServerLevel world, TeleportTransition.PostTeleportTransition postTeleportTarget)
    {
        BlockState blockstate = entity.level().getBlockState(pos);
        Direction.Axis direction$axis;
        Vec3 vec3d;
        if (blockstate.hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
        {
            direction$axis = blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS);
            BlockUtil.FoundRectangle blockutil$foundrectangle = BlockUtil.getLargestRectangleAround(
                    pos, direction$axis, 21, Direction.Axis.Y, 21, p_351016_ -> entity.level().getBlockState(p_351016_) == blockstate
            );
            vec3d = entity.getRelativePortalPosition(direction$axis, blockutil$foundrectangle);
        }
        else
        {
            direction$axis = Direction.Axis.X;
            vec3d = new Vec3(0.5, 0.0, 0.0);
        }

        return createTeleportTarget(world, rectangle, direction$axis, vec3d, entity, postTeleportTarget);
    }

    private static TeleportTransition createTeleportTarget(ServerLevel level, BlockUtil.FoundRectangle exitPortalRectangle, Direction.Axis axis, Vec3 offset, Entity entity, TeleportTransition.PostTeleportTransition postTeleportTarget)
    {
        BlockPos blockpos = exitPortalRectangle.minCorner;
        BlockState blockstate = level.getBlockState(blockpos);
        Direction.Axis direction$axis = blockstate.getOptionalValue(BlockStateProperties.HORIZONTAL_AXIS).orElse(Direction.Axis.X);
        double width = exitPortalRectangle.axis1Size;
        double height = exitPortalRectangle.axis2Size;
        EntityDimensions entitydimensions = entity.getDimensions(entity.getPose());
        int i = axis == direction$axis ? 0 : 90;
        Vec3 speed = entity.getDeltaMovement();
        Vec3 vec3 = axis == direction$axis ? speed : new Vec3(speed.z, speed.y, -speed.x);
        double d2 = (double)entitydimensions.width() / 2.0 + (width - (double)entitydimensions.width()) * offset.x();
        double d3 = (height - (double)entitydimensions.height()) * offset.y();
        double d4 = 0.5 + offset.z();
        boolean flag = direction$axis == Direction.Axis.X;
        Vec3 vec31 = new Vec3((double)blockpos.getX() + (flag ? d2 : d4), (double)blockpos.getY() + d3, (double)blockpos.getZ() + (flag ? d4 : d2));
        Vec3 vec32 = AerialHellPortalShape.findCollisionFreePosition(vec31, level, entity, entitydimensions);
        return new TeleportTransition(level, vec32, vec3, entity.getVisualRotationYInDegrees() + (float)i, entity.getXRot(), postTeleportTarget);
    }

    @Override public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random)
    {
        if (random.nextInt(100) == 0)
        {
            world.playLocalSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, AerialHellSoundEvents.BLOCK_AERIAL_HELL_PORTAL_AMBIENT, SoundSource.BLOCKS, 0.6F, 0.9F + random.nextFloat() * 0.2F, false);
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
            if (!world.getBlockState(pos.west()).is(this) && !world.getBlockState(pos.east()).is(this))
            {
                x = (double)pos.getX() + 0.5D + 0.25D * (double)j;
                xSpeed = random.nextFloat() * 2.0F * (float)j;
            }
            else
            {
                z = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
                zSpeed = random.nextFloat() * 2.0F * (float)j;
            }

            world.addParticle(AerialHellParticleTypes.AERIAL_HELL_PORTAL, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }

    @Override protected ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state, boolean includeData) {return ItemStack.EMPTY;}

    @Override protected BlockState rotate(BlockState state, Rotation rotation)
    {
        switch (rotation)
        {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch ((Direction.Axis)state.getValue(AXIS))
                {
                    case X:
                        return state.setValue(AXIS, Direction.Axis.Z);
                    case Z:
                        return state.setValue(AXIS, Direction.Axis.X);
                    default:
                        return state;
                }
            default:
                return state;
        }
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(AXIS);}

    public static class AerialHellPortalShape extends PortalShape
    {
        private static final int MIN_WIDTH = 1;
        public static final int MAX_WIDTH = 21;
        private static final int MIN_HEIGHT = 2;
        public static final int MAX_HEIGHT = 21;
        private static final BlockBehaviour.StatePredicate IS_VALID_FRAME_BLOCK = (state, getter, pos) -> state.is(AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK);
        private final LevelReader level;
        private final Direction.Axis axis;
        private final Direction rightDir;
        private int numPortalBlocks;
        private BlockPos bottomLeft;
        private int height;
        private final int width;

        public AerialHellPortalShape(LevelReader level, BlockPos bottomLeftPos, Direction.Axis axis)
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
            for (int i = Math.max(this.level.getMinY(), pos.getY() - MAX_HEIGHT); pos.getY() > i && isEmpty(this.level.getBlockState(pos.below())); pos = pos.below()){}

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
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

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
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
            int i = this.getDistanceUntilTop(mutable);
            return i >= MIN_HEIGHT && i <= MAX_HEIGHT && this.hasTopFrame(mutable, i) ? i : 0;
        }

        private boolean hasTopFrame(BlockPos.MutableBlockPos pos, int height)
        {
            for (int i = 0; i < this.width; ++i)
            {
                BlockPos.MutableBlockPos mutablePos = pos.set(this.bottomLeft).move(Direction.UP, height).move(this.rightDir, i);
                if (!IS_VALID_FRAME_BLOCK.test(this.level.getBlockState(mutablePos), this.level, mutablePos)) {return false;}
            }
            return true;
        }

        private int getDistanceUntilTop(BlockPos.MutableBlockPos pos)
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

                    if (blockstate.is(AerialHellBlocks.AERIAL_HELL_PORTAL)) {++this.numPortalBlocks;}
                }
            }
            return MAX_HEIGHT;
        }

        private static boolean isEmpty(BlockState state) {return state.isAir() || state.is(AerialHellBlocks.AERIAL_HELL_PORTAL);}
        public boolean isValid() {return this.bottomLeft != null && this.width >= MIN_WIDTH && this.width <= MAX_WIDTH && this.height >= MIN_HEIGHT && this.height <= MAX_HEIGHT;}

        public void createPortalBlocks(LevelAccessor world)
        {
            BlockState blockstate = AerialHellBlocks.AERIAL_HELL_PORTAL.defaultBlockState().setValue(NetherPortalBlock.AXIS, this.axis);
            BlockPos.betweenClosed(this.bottomLeft, this.bottomLeft.relative(Direction.UP, this.height - 1).relative(this.rightDir, this.width - 1)).forEach(pos -> world.setBlock(pos, blockstate, 18));
        }

        public boolean isComplete() {return this.isValid() && this.numPortalBlocks == this.width * this.height;}
    }
}
