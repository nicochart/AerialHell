package fr.factionbedrock.aerialhell.World;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellPOI;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.BlockUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.entity.ai.village.poi.PoiManager.Occupancy;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.levelgen.Heightmap.Types;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

//Copy of net.minecraft.world.level.portal.PortalForcer, edited for Aerial Hell
public class AerialHellTeleporter
{
    protected final ServerLevel level;
    private final BlockState frameState = AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK.get().defaultBlockState();

    public AerialHellTeleporter(ServerLevel level) {this.level = level;}

    public Optional<BlockPos> findClosestPortalPosition(BlockPos approximateExitPos, WorldBorder worldBorder)
    {
        PoiManager poiManager = this.level.getPoiManager();
        int radius = 128;
        poiManager.ensureLoadedAndValid(this.level, approximateExitPos, radius);

        Stream<PoiRecord> poiStream = poiManager.getInSquare((type) -> type.is(AerialHellPOI.AERIAL_HELL_PORTAL_POI), approximateExitPos, radius, Occupancy.ANY);

        return poiStream.map(PoiRecord::getPos).filter(worldBorder::isWithinBounds).filter((pos) -> this.level.hasChunkAt(pos) && this.level.getBlockState(pos).hasProperty(BlockStateProperties.HORIZONTAL_AXIS) && this.level.getBlockState(pos).is(AerialHellBlocks.AERIAL_HELL_PORTAL.get())).min(Comparator.comparingDouble((BlockPos p) -> p.distSqr(approximateExitPos)).thenComparingInt(Vec3i::getY));
    }

    public Optional<BlockUtil.FoundRectangle> createPortal(BlockPos origin, Direction.Axis portalAxis)
    {
        Direction direction = Direction.get(AxisDirection.POSITIVE, portalAxis);
        double closestFullDistanceSqr = -1.0;
        BlockPos closestFullPosition = null;
        double closestPartialDistanceSqr = -1.0;
        BlockPos closestPartialPosition = null;
        WorldBorder worldBorder = this.level.getWorldBorder();
        int maxPlaceableY = Math.min(this.level.getMaxY(), this.level.getMinY() + this.level.getLogicalHeight() - 1);
        BlockPos.MutableBlockPos mutable = origin.mutable();

        int centerChunkX = origin.getX() >> 4;
        int centerChunkZ = origin.getZ() >> 4;
        for (int xOffset = -1; xOffset <= 1; xOffset++)
        {
            for (int zOffset = -1; zOffset <= 1; zOffset++)
            {
                this.level.getChunk(centerChunkX + xOffset, centerChunkZ + zOffset, ChunkStatus.FULL, true);
            }
        }

        for (BlockPos.MutableBlockPos columnPos : BlockPos.spiralAround(origin, 16, Direction.EAST, Direction.SOUTH))
        {
            if (!worldBorder.isWithinBounds(columnPos) || !this.level.hasChunkAt(columnPos)) {continue;}
            int height = Math.min(maxPlaceableY, this.level.getHeight(Types.MOTION_BLOCKING, columnPos.getX(), columnPos.getZ()));
            if (worldBorder.isWithinBounds(columnPos) && worldBorder.isWithinBounds(columnPos.move(direction, 1))) {
                columnPos.move(direction.getOpposite(), 1);

                for (int y = height; y >= this.level.getMinY(); --y)
                {
                    columnPos.setY(y);
                    if (this.canPortalReplaceBlock(columnPos))
                    {
                        int firstEmptyY = y;
                        while (y > this.level.getMinY() && this.canPortalReplaceBlock(columnPos.move(Direction.DOWN))) {y--;}

                        if (y + 4 <= maxPlaceableY)
                        {
                            int deltaY = firstEmptyY - y;
                            if (deltaY <= 0 || deltaY >= 3)
                            {
                                columnPos.setY(y);
                                if (this.canHostFrame(columnPos, mutable, direction, 0))
                                {
                                    double distance = origin.distSqr(columnPos);
                                    if (this.canHostFrame(columnPos, mutable, direction, -1) && this.canHostFrame(columnPos, mutable, direction, 1) && (closestFullDistanceSqr == -1.0 || closestFullDistanceSqr > distance))
                                    {
                                        closestFullDistanceSqr = distance;
                                        closestFullPosition = columnPos.immutable();
                                    }

                                    if (closestFullDistanceSqr == -1.0 && (closestPartialDistanceSqr == -1.0 || closestPartialDistanceSqr > distance))
                                    {
                                        closestPartialDistanceSqr = distance;
                                        closestPartialPosition = columnPos.immutable();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (closestFullDistanceSqr == -1.0 && closestPartialDistanceSqr != -1.0)
        {
            closestFullPosition = closestPartialPosition;
            closestFullDistanceSqr = closestPartialDistanceSqr;
        }

        if (closestFullDistanceSqr == -1.0)
        {
            int minStartY = Math.max(this.level.getMinY() + 1, 70);
            int maxStartY = maxPlaceableY - 9;
            if (maxStartY < minStartY) {return Optional.empty();}

            closestFullPosition = (new BlockPos(origin.getX() - direction.getStepX() * 1, Mth.clamp(origin.getY(), minStartY, maxStartY), origin.getZ() - direction.getStepZ() * 1)).immutable();
            closestFullPosition = worldBorder.clampToBounds(closestFullPosition);
            Direction clockWise = direction.getClockWise();

            for (int box = -1; box < 2; ++box)
            {
                for (int width = 0; width < 2; ++width)
                {
                    for (int height = -1; height < 3; ++height)
                    {
                        BlockState blockState = height < 0 ? frameState : Blocks.AIR.defaultBlockState();
                        mutable.setWithOffset(closestFullPosition, width * direction.getStepX() + box * clockWise.getStepX(), height, width * direction.getStepZ() + box * clockWise.getStepZ());
                        this.level.setBlockAndUpdate(mutable, blockState);
                    }
                }
            }
        }

        for (int width = -1; width < 3; ++width)
        {
            for (int height = -1; height < 4; ++height)
            {
                if (width == -1 || width == 2 || height == -1 || height == 3)
                {
                    mutable.setWithOffset(closestFullPosition, width * direction.getStepX(), height, width * direction.getStepZ());
                    this.level.setBlockAndUpdate(mutable, frameState);
                }
            }
        }

        BlockState portalBlockState = AerialHellBlocks.AERIAL_HELL_PORTAL.get().defaultBlockState().setValue(NetherPortalBlock.AXIS, portalAxis);

        for (int width = 0; width < 2; ++width)
        {
            for (int height = 0; height < 3; ++height)
            {
                mutable.setWithOffset(closestFullPosition, width * direction.getStepX(), height, width * direction.getStepZ());
                this.level.setBlock(mutable, portalBlockState, 3);
            }
        }

        return Optional.of(new BlockUtil.FoundRectangle(closestFullPosition.immutable(), 2, 3));
    }

    private boolean canPortalReplaceBlock(BlockPos.MutableBlockPos pos)
    {
        if (!this.level.hasChunkAt(pos)) {return false;}
        BlockState blockState = this.level.getBlockState(pos);
        return blockState.canBeReplaced() && blockState.getFluidState().isEmpty();
    }

    private boolean canHostFrame(BlockPos origin, BlockPos.MutableBlockPos mutable, Direction direction, int offset)
    {
        Direction clockWise = direction.getClockWise();

        for (int width = -1; width < 3; ++width)
        {
            for (int height = -1; height < 4; ++height)
            {
                mutable.setWithOffset(origin, direction.getStepX() * width + clockWise.getStepX() * offset, height, direction.getStepZ() * width + clockWise.getStepZ() * offset);

                if (!this.level.hasChunkAt(mutable)) {return false;}
                if (height < 0 && !this.level.getBlockState(mutable).isSolid()) {return false;}
                if (height >= 0 && !this.canPortalReplaceBlock(mutable)) {return false;}
            }
        }

        return true;
    }
}