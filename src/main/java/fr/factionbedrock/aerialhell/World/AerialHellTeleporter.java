package fr.factionbedrock.aerialhell.World;

import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellPOI;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.TicketType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.ai.village.poi.PoiRecord;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.Heightmap;
//import net.minecraft.world.level.portal.PortalInfo;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
//import net.neoforged.neoforge.common.util.ITeleporter;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.Optional;

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

public class AerialHellTeleporter
{

    protected final ServerLevel level;
    private final BlockState frame = AerialHellBlocksAndItems.STELLAR_PORTAL_FRAME_BLOCK.get().getDefaultState();

    public AerialHellTeleporter(ServerLevel pLevel) {this.level = pLevel;}

    public Optional<BlockPos> findClosestPortalPosition(BlockPos exitPos, boolean isUndergarden, WorldBorder worldBorder)
    {
        PoiManager poimanager = this.level.getPoiManager();
        int i = isUndergarden ? 16 : 128;
        poimanager.ensureLoadedAndValid(this.level, exitPos, i);
        return poimanager.getInSquare(p_230634_ -> p_230634_.is(AerialHellPOI.AERIAL_HELL_PORTAL_POI), exitPos, i, PoiManager.Occupancy.ANY)
                .map(PoiRecord::getPos)
                .filter(worldBorder::isWithinBounds)
                .filter(p_352047_ -> this.level.getBlockState(p_352047_).hasProperty(BlockStateProperties.HORIZONTAL_AXIS))
                .min(Comparator.<BlockPos>comparingDouble(p_352046_ -> p_352046_.getSquaredDistance(exitPos)).thenComparingInt(Vec3i::getY));
    }

    public Optional<BlockUtil.FoundRectangle> createPortal(BlockPos pos, Direction.Axis axis)
    {
        Direction direction = Direction.get(Direction.AxisDirection.POSITIVE, axis);
        double d0 = -1.0;
        BlockPos blockPos = null;
        double d1 = -1.0;
        BlockPos blockPos1 = null;
        WorldBorder worldBorder = this.level.getWorldBorder();
        int i = Math.min(this.level.getMaxBuildHeight(), this.level.getBottomY() + this.level.getLogicalHeight()) - 1;
        BlockPos.Mutable mutablePos = pos.mutable();

        for (BlockPos.Mutable mutablePos1 : BlockPos.spiralAround(pos, 16, Direction.EAST, Direction.SOUTH)) {
            int k = Math.min(i, this.level.getHeight(Heightmap.Types.MOTION_BLOCKING, mutablePos1.getX(), mutablePos1.getZ()));
            if (worldBorder.isWithinBounds(mutablePos1) && worldBorder.isWithinBounds(mutablePos1.move(direction, 1))) {
                mutablePos1.move(direction.getOpposite(), 1);

                for (int l = k; l >= this.level.getBottomY(); l--) {
                    mutablePos1.setY(l);
                    if (this.canPortalReplaceBlock(mutablePos1)) {
                        int i1 = l;

                        while (l > this.level.getBottomY() && this.canPortalReplaceBlock(mutablePos1.move(Direction.DOWN))) {
                            l--;
                        }

                        if (l + 4 <= i) {
                            int j1 = i1 - l;
                            if (j1 <= 0 || j1 >= 3) {
                                mutablePos1.setY(l);
                                if (this.canHostFrame(mutablePos1, mutablePos, direction, 0)) {
                                    double d2 = pos.getSquaredDistance(mutablePos1);
                                    if (this.canHostFrame(mutablePos1, mutablePos, direction, -1)
                                            && this.canHostFrame(mutablePos1, mutablePos, direction, 1)
                                            && (d0 == -1.0 || d0 > d2)) {
                                        d0 = d2;
                                        blockPos = mutablePos1.immutable();
                                    }

                                    if (d0 == -1.0 && (d1 == -1.0 || d1 > d2)) {
                                        d1 = d2;
                                        blockPos1 = mutablePos1.immutable();
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
            int k1 = Math.max(this.level.getBottomY() - -1, 70);
            int i2 = i - 9;
            if (i2 < k1) {return Optional.empty();}

            blockPos = new BlockPos(pos.getX() - direction.getStepX() * 1, MathHelper.clamp(pos.getY(), k1, i2), pos.getZ() - direction.getStepZ() * 1).immutable();
            blockPos = worldBorder.clampToBounds(blockPos);
            Direction direction1 = direction.rotateYClockwise();

            for (int i3 = -1; i3 < 2; i3++)
            {
                for (int j3 = 0; j3 < 2; j3++)
                {
                    for (int k3 = -1; k3 < 3; k3++)
                    {
                        BlockState blockstate1 = k3 < 0 ? frame : Blocks.AIR.getDefaultState();
                        mutablePos.setWithOffset(blockPos, j3 * direction.getStepX() + i3 * direction1.getStepX(), k3, j3 * direction.getStepZ() + i3 * direction1.getStepZ());
                        this.level.setBlockState(mutablePos, blockstate1);
                    }
                }
            }
        }

        for (int l1 = -1; l1 < 3; l1++) {
            for (int j2 = -1; j2 < 4; j2++) {
                if (l1 == -1 || l1 == 2 || j2 == -1 || j2 == 3) {
                    mutablePos.setWithOffset(blockPos, l1 * direction.getStepX(), j2, l1 * direction.getStepZ());
                    this.level.setBlockState(mutablePos, frame, 3);
                }
            }
        }

        BlockState blockstate = AerialHellBlocksAndItems.AERIAL_HELL_PORTAL.get().getDefaultState().with(NetherPortalBlock.AXIS, axis);

        for (int k2 = 0; k2 < 2; k2++)
        {
            for (int l2 = 0; l2 < 3; l2++)
            {
                mutablePos.setWithOffset(blockPos, k2 * direction.getStepX(), l2, k2 * direction.getStepZ());
                this.level.setBlockState(mutablePos, blockstate, 18);
            }
        }

        return Optional.of(new BlockUtil.FoundRectangle(blockPos.immutable(), 2, 3));
    }

    private boolean canPortalReplaceBlock(BlockPos.Mutable pos)
    {
        BlockState blockstate = this.level.getBlockState(pos);
        return blockstate.canBeReplaced() && blockstate.getFluidState().isEmpty();
    }

    private boolean canHostFrame(BlockPos originalPos, BlockPos.Mutable offsetPos, Direction direction, int pOffsetScale)
    {
        Direction directionClockWise = direction.rotateYClockwise();

        for (int i = -1; i < 3; i++)
        {
            for (int j = -1; j < 4; j++)
            {
                offsetPos.setWithOffset(originalPos, directionClockWise.getStepX() * i + directionClockWise.getStepX() * pOffsetScale, j, directionClockWise.getStepZ() * i + directionClockWise.getStepZ() * pOffsetScale);
                if (j < 0 && !this.level.getBlockState(offsetPos).isSolid()) {return false;}
                if (j >= 0 && !this.canPortalReplaceBlock(offsetPos)) {return false;}
            }
        }
        return true;
    }
}