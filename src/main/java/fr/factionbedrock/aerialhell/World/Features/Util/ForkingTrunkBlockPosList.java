package fr.factionbedrock.aerialhell.World.Features.Util;

import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class ForkingTrunkBlockPosList
{
    private final BlockPos forkPos;
    @Nullable private final BlockPos forkPos2;
    @Nullable private final BlockPos forkPos3;
    @Nullable private final BlockPos forkPos4;
    private final BlockPos endPos; //where foliage should be generated

    public ForkingTrunkBlockPosList(BlockPos forkPos, BlockPos endPos)
    {
        this(forkPos, null, endPos);
    }

    public ForkingTrunkBlockPosList(BlockPos forkPos, @Nullable BlockPos forkPos2, BlockPos endPos)
    {
        this(forkPos, forkPos2, null, endPos);
    }

    public ForkingTrunkBlockPosList(BlockPos forkPos, @Nullable BlockPos forkPos2, @Nullable BlockPos forkPos3, BlockPos endPos)
    {
        this(forkPos, forkPos2, forkPos3, null, endPos);
    }

    public ForkingTrunkBlockPosList(BlockPos forkPos, @Nullable BlockPos forkPos2, @Nullable BlockPos forkPos3, @Nullable BlockPos forkPos4, BlockPos endPos)
    {
        this.forkPos = forkPos; this.forkPos2 = forkPos2; this.forkPos3 = forkPos3; this.forkPos4 = forkPos4; this.endPos = endPos;
    }

    public BlockPos getForkPos() {return this.forkPos;}
    @Nullable public BlockPos get2ndForkPos() {return this.forkPos2;}
    @Nullable public BlockPos get3rdForkPos() {return this.forkPos3;}
    @Nullable public BlockPos get4thForkPos() {return this.forkPos4;}
    public BlockPos getEndPos() {return this.endPos;}
}
