package fr.factionbedrock.aerialhell.World;

import fr.factionbedrock.aerialhell.Block.AerialHellPortalBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellPOI;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.Heightmap;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.poi.PointOfInterest;
import net.minecraft.world.poi.PointOfInterestStorage;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

//edited copy of net.minecraft.world.dimension.PortalForcer
public class AerialHellTeleporter
{
    protected final ServerWorld world;
    private final BlockState frame = AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK.getDefaultState();

    public AerialHellTeleporter(ServerWorld pLevel) {this.world = pLevel;}

    public Optional<BlockPos> findClosestPortalPosition(BlockPos exitPos, boolean isNether, WorldBorder worldBorder)
    {
        PointOfInterestStorage poiStorage = this.world.getPointOfInterestStorage();
        int i = isNether ? 16 : 128;
        poiStorage.preloadChunks(this.world, exitPos, i);
        Stream poses = poiStorage.getInSquare(poiType -> poiType.matchesKey(AerialHellPOI.Keys.AERIAL_HELL_PORTAL_POI), exitPos, i, PointOfInterestStorage.OccupationStatus.ANY).map(PointOfInterest::getPos);
        return poiStorage.getInSquare(poiType -> poiType.matchesKey(AerialHellPOI.Keys.AERIAL_HELL_PORTAL_POI), exitPos, i, PointOfInterestStorage.OccupationStatus.ANY)
                .map(PointOfInterest::getPos)
                .filter(worldBorder::contains)
                .filter(p_352047_ -> this.world.getBlockState(p_352047_).contains(Properties.HORIZONTAL_AXIS))
                .min(Comparator.<BlockPos>comparingDouble(pos -> pos.getSquaredDistance(exitPos)).thenComparingInt(Vec3i::getY));
    }

    public Optional<BlockLocating.Rectangle> createPortal(BlockPos pos, Direction.Axis axis)
    {
        Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        double d0 = -1.0;
        BlockPos blockPos = null;
        double d1 = -1.0;
        BlockPos blockPos1 = null;
        WorldBorder worldBorder = this.world.getWorldBorder();
        int i = Math.min(this.world.getTopYInclusive(), this.world.getBottomY() + this.world.getLogicalHeight()) - 1;
        BlockPos.Mutable mutable = pos.mutableCopy();

        for (BlockPos.Mutable mutable1 : BlockPos.iterateInSquare(pos, 16, Direction.EAST, Direction.SOUTH))
        {
            int k = Math.min(i, this.world.getTopY(Heightmap.Type.MOTION_BLOCKING, mutable1.getX(), mutable1.getZ()));
            if (worldBorder.contains(mutable1) && worldBorder.contains(mutable1.move(direction, 1))) {
                mutable1.move(direction.getOpposite(), 1);

                for (int l = k; l >= this.world.getBottomY(); l--)
                {
                    mutable1.setY(l);
                    if (this.canPortalReplaceBlock(mutable1))
                    {
                        int i1 = l;

                        while (l > this.world.getBottomY() && this.canPortalReplaceBlock(mutable1.move(Direction.DOWN))) {l--;}

                        if (l + 4 <= i)
                        {
                            int j1 = i1 - l;
                            if (j1 <= 0 || j1 >= 3)
                            {
                                mutable1.setY(l);
                                if (this.canHostFrame(mutable1, mutable, direction, 0))
                                {
                                    double d2 = pos.getSquaredDistance(mutable1);
                                    if (this.canHostFrame(mutable1, mutable, direction, -1) && this.canHostFrame(mutable1, mutable, direction, 1) && (d0 == -1.0 || d0 > d2))
                                    {
                                        d0 = d2;
                                        blockPos = mutable1.toImmutable();
                                    }

                                    if (d0 == -1.0 && (d1 == -1.0 || d1 > d2))
                                    {
                                        d1 = d2;
                                        blockPos1 = mutable1.toImmutable();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (d0 == -1.0 && d1 != -1.0)
        {
            blockPos = blockPos1;
            d0 = d1;
        }

        if (d0 == -1.0)
        {
            int k1 = Math.max(this.world.getBottomY() - -1, 70);
            int i2 = i - 9;
            if (i2 < k1) {return Optional.empty();}

            blockPos = new BlockPos(pos.getX() - direction.getOffsetX(), MathHelper.clamp(pos.getY(), k1, i2), pos.getZ() - direction.getOffsetZ()).toImmutable();
            blockPos = worldBorder.clampFloored(blockPos);
            Direction direction1 = direction.rotateYClockwise();

            for (int i3 = -1; i3 < 2; i3++)
            {
                for (int j3 = 0; j3 < 2; j3++)
                {
                    for (int k3 = -1; k3 < 3; k3++)
                    {
                        BlockState blockstate1 = k3 < 0 ? frame : Blocks.AIR.getDefaultState();
                        mutable.set(blockPos, j3 * direction.getOffsetX() + i3 * direction1.getOffsetX(), k3, j3 * direction.getOffsetZ() + i3 * direction1.getOffsetZ());
                        this.world.setBlockState(mutable, blockstate1);
                    }
                }
            }
        }

        for (int l1 = -1; l1 < 3; l1++)
        {
            for (int j2 = -1; j2 < 4; j2++)
            {
                if (l1 == -1 || l1 == 2 || j2 == -1 || j2 == 3)
                {
                    mutable.set(blockPos, l1 * direction.getOffsetX(), j2, l1 * direction.getOffsetZ());
                    this.world.setBlockState(mutable, frame, 3);
                }
            }
        }

        BlockState blockstate = AerialHellBlocks.AERIAL_HELL_PORTAL.getDefaultState().with(AerialHellPortalBlock.AXIS, axis);

        for (int k2 = 0; k2 < 2; k2++)
        {
            for (int l2 = 0; l2 < 3; l2++)
            {
                mutable.set(blockPos, k2 * direction.getOffsetX(), l2, k2 * direction.getOffsetZ());
                this.world.setBlockState(mutable, blockstate, 18);
            }
        }

        return Optional.of(new BlockLocating.Rectangle(blockPos.toImmutable(), 2, 3));
    }

    private boolean canPortalReplaceBlock(BlockPos.Mutable pos)
    {
        BlockState blockstate = this.world.getBlockState(pos);
        return blockstate.isReplaceable() && blockstate.getFluidState().isEmpty();
    }

    private boolean canHostFrame(BlockPos originalPos, BlockPos.Mutable offsetPos, Direction direction, int pOffsetScale)
    {
        Direction directionClockWise = direction.rotateYClockwise();

        for (int i = -1; i < 3; i++)
        {
            for (int j = -1; j < 4; j++)
            {
                offsetPos.set(originalPos, directionClockWise.getOffsetX() * i + directionClockWise.getOffsetX() * pOffsetScale, j, directionClockWise.getOffsetZ() * i + directionClockWise.getOffsetZ() * pOffsetScale);
                if (j < 0 && !this.world.getBlockState(offsetPos).isSolid()) {return false;}
                if (j >= 0 && !this.canPortalReplaceBlock(offsetPos)) {return false;}
            }
        }
        return true;
    }
}