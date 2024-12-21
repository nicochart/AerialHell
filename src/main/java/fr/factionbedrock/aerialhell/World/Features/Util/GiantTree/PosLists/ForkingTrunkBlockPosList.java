package fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.PosLists;

import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ForkingTrunkBlockPosList
{
    private final BlockPos forkPos;
    @Nullable
    private final BlockPos forkPos2;
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

    @Nullable public BlockPos getForkPos(int forkIndex)
    {
        if (forkIndex == 1) {return this.forkPos;}
        else if (forkIndex == 2) {return this.forkPos2;}
        else if (forkIndex == 3) {return this.forkPos3;}
        else if (forkIndex == 4) {return this.forkPos4;}
        else {return null;}
    }

    public BlockPos getEndPos() {return this.endPos;}
}
