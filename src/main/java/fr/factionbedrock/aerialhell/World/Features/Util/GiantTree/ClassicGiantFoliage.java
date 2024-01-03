package fr.factionbedrock.aerialhell.World.Features.Util.GiantTree;

import fr.factionbedrock.aerialhell.World.Features.Util.Ellipsoid;
import fr.factionbedrock.aerialhell.World.Features.Util.SplineKnotsDeformedEllipsoid;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.function.Supplier;

public class ClassicGiantFoliage extends SplineKnotsDeformedEllipsoid
{
    public ClassicGiantFoliage(FeaturePlaceContext<?> context, Supplier<Block> block, EllipsoidParameters ellipsoidParams, BlockPos centerPos, Types.EllipsoidType type, int knotsNumber, KnotsParameters knotsParams, boolean generateBothDeformedAndNonDeformed) {super(context, block, ellipsoidParams, centerPos, type, knotsNumber, knotsParams, generateBothDeformedAndNonDeformed);}

    public void generateFoliage()
    {
        this.generate();
        this.generateOutsideBorder();
    }

    protected int getLeavesDistance(BlockPos ellipsoidPos)
    {
        int x = Math.abs(ellipsoidPos.getX()), y = Math.abs(ellipsoidPos.getY()), z = Math.abs(ellipsoidPos.getZ());
        float r = (float) x*x/(ellipsoidParams.xSize()*ellipsoidParams.xSize()) + (float) y*y/(ellipsoidParams.ySize()*ellipsoidParams.ySize()) + (float) z*z/(ellipsoidParams.zSize()*ellipsoidParams.zSize());

        if (r > 0.87) {return 7;}
        else if (r > 0.75) {return 6;}
        else if (r > 0.6) {return 5;}
        else if (r > 0.4) {return 4;}
        else if (r > 0.3) {return 3;}
        else if (r > 0.2) {return 2;}
        else {return 1;}
    }

    @Override public float randomChanceToGenerateBlock(boolean generatingBorder) {return generatingBorder ? 0.5F : 1.0F;}

    @Override protected void generateInnerLoop(BlockPos.MutableBlockPos placementPos, int x, int y, int z, boolean generateBorder)
    {
        if (y == this.ellipsoidParams.yForMin() && randomlyChooseToNotPlaceBlock(true)) {return;}
        else {super.generateInnerLoop(placementPos, x, y, z, generateBorder);}
    }

    public static Ellipsoid.EllipsoidParameters createClassicGiantFoliageEllipsoidParameters(int xzSize, int ySize)
    {
        return new Ellipsoid.EllipsoidParameters(xzSize, ySize, xzSize, - xzSize, xzSize, -1, ySize, - xzSize, xzSize, 1);
    }
}
