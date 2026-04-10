package fr.factionbedrock.aerialhell.World;

import fr.factionbedrock.aerialhell.Block.AerialHellPortalBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellPOI;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.BlockUtil;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.levelgen.Heightmap;

//edited copy of net.minecraft.world.dimension.PortalForcer
public class AerialHellTeleporter
{
    protected final ServerLevel world;
    private final BlockState frame = AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK.defaultBlockState();

    public AerialHellTeleporter(ServerLevel pLevel) {this.world = pLevel;}

    public Optional<BlockPos> findClosestPortalPosition(BlockPos exitPos, boolean isNether, WorldBorder worldBorder)
    {
        PoiManager poiStorage = this.world.getPoiManager();
        int i = isNether ? 16 : 128;
        poiStorage.ensureLoadedAndValid(this.world, exitPos, i);
        Stream poses = poiStorage.getInSquare(poiType -> poiType.is(AerialHellPOI.Keys.AERIAL_HELL_PORTAL_POI), exitPos, i, PoiManager.Occupancy.ANY).map(PoiRecord::getPos);
        return poiStorage.getInSquare(poiType -> poiType.is(AerialHellPOI.Keys.AERIAL_HELL_PORTAL_POI), exitPos, i, PoiManager.Occupancy.ANY)
                .map(PoiRecord::getPos)
                .filter(worldBorder::isWithinBounds)
                .filter(p_352047_ -> this.world.getBlockState(p_352047_).hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
                .min(Comparator.<BlockPos>comparingDouble(pos -> pos.distSqr(exitPos)).thenComparingInt(Vec3i::getY));
    }

    public Optional<BlockUtil.FoundRectangle> createPortal(BlockPos pos, Direction.Axis axis)
    {
        Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        double d0 = -1.0;
        BlockPos blockPos = null;
        double d1 = -1.0;
        BlockPos blockPos1 = null;
        WorldBorder worldBorder = this.world.getWorldBorder();
        int i = Math.min(this.world.getMaxY(), this.world.getMinY() + this.world.getLogicalHeight()) - 1;
        BlockPos.MutableBlockPos mutable = pos.mutable();

        for (BlockPos.MutableBlockPos mutable1 : BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH))
        {
            int k = Math.min(i, this.world.getHeight(Heightmap.Types.MOTION_BLOCKING, mutable1.getX(), mutable1.getZ()));
            if (worldBorder.isWithinBounds(mutable1) && worldBorder.isWithinBounds(mutable1.move(direction, 1))) {
                mutable1.move(direction.getOpposite(), 1);

                for (int l = k; l >= this.world.getMinY(); l--)
                {
                    mutable1.setY(l);
                    if (this.canPortalReplaceBlock(mutable1))
                    {
                        int i1 = l;

                        while (l > this.world.getMinY() && this.canPortalReplaceBlock(mutable1.move(Direction.DOWN))) {l--;}

                        if (l + 4 <= i)
                        {
                            int j1 = i1 - l;
                            if (j1 <= 0 || j1 >= 3)
                            {
                                mutable1.setY(l);
                                if (this.canHostFrame(mutable1, mutable, direction, 0))
                                {
                                    double d2 = pos.distSqr(mutable1);
                                    if (this.canHostFrame(mutable1, mutable, direction, -1) && this.canHostFrame(mutable1, mutable, direction, 1) && (d0 == -1.0 || d0 > d2))
                                    {
                                        d0 = d2;
                                        blockPos = mutable1.immutable();
                                    }

                                    if (d0 == -1.0 && (d1 == -1.0 || d1 > d2))
                                    {
                                        d1 = d2;
                                        blockPos1 = mutable1.immutable();
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
            int k1 = Math.max(this.world.getMinY() - -1, 70);
            int i2 = i - 9;
            if (i2 < k1) {return Optional.empty();}

            blockPos = new BlockPos(pos.getX() - direction.getStepX(), Mth.clamp(pos.getY(), k1, i2), pos.getZ() - direction.getStepZ()).immutable();
            blockPos = worldBorder.clampToBounds(blockPos);
            Direction direction1 = direction.getClockWise();

            for (int i3 = -1; i3 < 2; i3++)
            {
                for (int j3 = 0; j3 < 2; j3++)
                {
                    for (int k3 = -1; k3 < 3; k3++)
                    {
                        BlockState blockstate1 = k3 < 0 ? frame : Blocks.AIR.defaultBlockState();
                        mutable.setWithOffset(blockPos, j3 * direction.getStepX() + i3 * direction1.getStepX(), k3, j3 * direction.getStepZ() + i3 * direction1.getStepZ());
                        this.world.setBlockAndUpdate(mutable, blockstate1);
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
                    mutable.setWithOffset(blockPos, l1 * direction.getStepX(), j2, l1 * direction.getStepZ());
                    this.world.setBlock(mutable, frame, 3);
                }
            }
        }

        BlockState blockstate = AerialHellBlocks.AERIAL_HELL_PORTAL.defaultBlockState().setValue(AerialHellPortalBlock.AXIS, axis);

        for (int k2 = 0; k2 < 2; k2++)
        {
            for (int l2 = 0; l2 < 3; l2++)
            {
                mutable.setWithOffset(blockPos, k2 * direction.getStepX(), l2, k2 * direction.getStepZ());
                this.world.setBlock(mutable, blockstate, 18);
            }
        }

        return Optional.of(new BlockUtil.FoundRectangle(blockPos.immutable(), 2, 3));
    }

    private boolean canPortalReplaceBlock(BlockPos.MutableBlockPos pos)
    {
        BlockState blockstate = this.world.getBlockState(pos);
        return blockstate.canBeReplaced() && blockstate.getFluidState().isEmpty();
    }

    private boolean canHostFrame(BlockPos originalPos, BlockPos.MutableBlockPos offsetPos, Direction direction, int pOffsetScale)
    {
        Direction directionClockWise = direction.getClockWise();

        for (int i = -1; i < 3; i++)
        {
            for (int j = -1; j < 4; j++)
            {
                offsetPos.setWithOffset(originalPos, directionClockWise.getStepX() * i + directionClockWise.getStepX() * pOffsetScale, j, directionClockWise.getStepZ() * i + directionClockWise.getStepZ() * pOffsetScale);
                if (j < 0 && !this.world.getBlockState(offsetPos).isSolid()) {return false;}
                if (j >= 0 && !this.canPortalReplaceBlock(offsetPos)) {return false;}
            }
        }
        return true;
    }
}