package fr.factionbedrock.aerialhell.World.Features.Util.GiantTree.PosLists;

import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class FoliagePosList
{
    private FoliageInfo foliage1;
    @Nullable
    private FoliageInfo foliage2;
    @Nullable private FoliageInfo foliage3;
    @Nullable private FoliageInfo foliage4;

    public FoliagePosList(BlockPos foliagePos, int xzSize, int ySize) {this(new FoliageInfo(foliagePos, xzSize, ySize));}
    public FoliagePosList(FoliageInfo foliage1) {this(foliage1, null);}
    public FoliagePosList(FoliageInfo foliage1, FoliageInfo foliage2) {this(foliage1, foliage2, null);}
    public FoliagePosList(FoliageInfo foliage1, FoliageInfo foliage2, FoliageInfo foliage3) {this(foliage1, foliage2, foliage3, null);}

    public FoliagePosList(FoliageInfo foliage1, FoliageInfo foliage2, FoliageInfo foliage3, FoliageInfo foliage4) {this.foliage1 = foliage1; this.foliage2 = foliage2; this.foliage3 = foliage3; this.foliage4 = foliage4;}

    public FoliageInfo getFoliage1() {return foliage1;}
    @Nullable public FoliageInfo getFoliage2() {return foliage2;}
    @Nullable public FoliageInfo getFoliage3() {return foliage3;}
    @Nullable public FoliageInfo getFoliage4() {return foliage4;}

    public boolean addFoliageInfo(BlockPos foliagePos, int xzSize, int ySize)
    {
        if (foliage2 == null) {foliage2 = new FoliageInfo(foliagePos, xzSize, ySize); return true;}
        else if (foliage3 == null) {foliage3 = new FoliageInfo(foliagePos, xzSize, ySize); return true;}
        else if (foliage4 == null) {foliage4 = new FoliageInfo(foliagePos, xzSize, ySize); return true;}
        else {return false;}
    }

    public boolean addFoliageInfo(FoliageInfo foliageInfo)
    {
        if (foliage2 == null) {foliage2 = foliageInfo;; return true;}
        else if (foliage3 == null) {foliage3 = foliageInfo; return true;}
        else if (foliage4 == null) {foliage4 = foliageInfo; return true;}
        else {return false;}
    }

    public static class FoliageInfo
    {
        private final BlockPos foliagePos;
        private final int xzSize;
        private final int ySize;

        public FoliageInfo(BlockPos foliagePos, int xzSize, int ySize)
        {
            this.foliagePos = foliagePos;
            this.xzSize = xzSize;
            this.ySize = ySize;
        }

        public BlockPos getFoliagePos() {return foliagePos;}
        public int getXzSize() {return xzSize;}
        public int getySize() {return ySize;}
    }
}
