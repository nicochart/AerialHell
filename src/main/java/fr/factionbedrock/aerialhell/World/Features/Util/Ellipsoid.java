package fr.factionbedrock.aerialhell.World.Features.Util;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.function.Supplier;

public class Ellipsoid
{
    protected final Supplier<Block> block;
    protected final FeatureContext<?> context;
    protected final EllipsoidParameters ellipsoidParams;
    protected final Types.EllipsoidType ellipsoidType;
    protected final BlockPos centerPos;

    public Ellipsoid(FeatureContext<?> context, Supplier<Block> block, EllipsoidParameters ellipsoidParams, BlockPos centerPos, Types.EllipsoidType type)
    {
        this.context = context;
        this.block = block;
        this.ellipsoidParams = ellipsoidParams;
        this.ellipsoidType = type;
        this.centerPos = centerPos;
    }

    public void generate()
    {
        BlockPos.Mutable placementPos = new BlockPos.Mutable();
        for (int y = ellipsoidParams.yForMin(); y <= ellipsoidParams.yForMax(); y++)
        {
            for (int x = ellipsoidParams.xForMin(); x <= ellipsoidParams.xForMax(); x++)
            {
                for (int z = ellipsoidParams.zForMin(); z <= ellipsoidParams.zForMax(); z++)
                {
                    generateInnerLoop(placementPos, x, y, z,false);
                }
            }
        }
    }

    public void generateOutsideBorder()
    {
        BlockPos.Mutable placementPos = new BlockPos.Mutable();
        for (int y = ellipsoidParams.yForMin(); y <= ellipsoidParams.yForMax(); y++)
        {
            for (int x = ellipsoidParams.xForMin(); x <= ellipsoidParams.xForMax(); x++)
            {
                for (int z = ellipsoidParams.zForMin(); z <= ellipsoidParams.zForMax(); z++)
                {
                    generateInnerLoop(placementPos, x, y, z,true);
                }
            }
        }
    }

    protected void generateInnerLoop(BlockPos.Mutable placementPos, int x, int y, int z, boolean generateBorder)
    {
        if (randomlyChooseToNotPlaceBlock(generateBorder)) {return;}
        if (generateBorder)
        {
            if (this.isPosAtEllipsoidOutsideBorder(x, y, z)) //if pos is at ellipsis border : try to place block
            {
                placementPos.set(getLevelPosFromEllipsoidPos(x, y, z));
                tryPlacingBlock(placementPos, new BlockPos(x, y, z));
            }
        }
        else
        {
            if (this.isPosInsideEllipsoid(x, y, z))
            {
                placementPos.set(getLevelPosFromEllipsoidPos(x, y, z));
                tryPlacingBlock(placementPos, new BlockPos(x, y, z));
            }
        }
    }

    public boolean randomlyChooseToNotPlaceBlock(boolean generateBorder)
    {
        return context.getRandom().nextFloat() > randomChanceToGenerateBlock(generateBorder);
    }

    public float randomChanceToGenerateBlock(boolean generateBorder) {return 1.0F;} //the block "should be placed", but you can use this to add additional random chance to not generate the block. (1.0F = 100 percent probability to be placed)

    public BlockState getStateForPlacement(BlockPos ellipsoidPos) {return block.get().getDefaultState();}

    public boolean isPosInsideEllipsoid(float xPos, float yPos, float zPos) {return isPosInsideEllipsoid(xPos, yPos, zPos, 1.0F);}

    public boolean isPosInsideEllipsoid(float xPos, float yPos, float zPos, float sizeMultiplicator) //default sizeMultiplicator = 1.0F
    {
        float x = xPos + this.ellipsoidType.horizontalCenterOffset;
        float y = yPos;
        float z = zPos + this.ellipsoidType.horizontalCenterOffset;
        return x*x/(ellipsoidParams.xSize()*ellipsoidParams.xSize()) + y*y/(ellipsoidParams.ySize()*ellipsoidParams.ySize()) + z*z/(ellipsoidParams.zSize()*ellipsoidParams.zSize()) < sizeMultiplicator;
    }

    public boolean isPosInsideGeneratedEllipsoidPart(float xPos, float yPos, float zPos) //if generate(centerPos) is called
    {
        if (xPos < ellipsoidParams.xForMin || xPos > ellipsoidParams.xForMax || yPos < ellipsoidParams.yForMin || yPos > ellipsoidParams.yForMax || zPos < ellipsoidParams.zForMin || zPos > ellipsoidParams.zForMax) {return false;}
        float x = xPos + this.ellipsoidType.horizontalCenterOffset;
        float y = yPos;
        float z = zPos + this.ellipsoidType.horizontalCenterOffset;
        return x*x/(ellipsoidParams.xSize()*ellipsoidParams.xSize()) + y*y/(ellipsoidParams.ySize()*ellipsoidParams.ySize()) + z*z/(ellipsoidParams.zSize()*ellipsoidParams.zSize()) < 1.0F;
    }

    public boolean isPosAtEllipsoidOutsideBorder(float x, float y, float z)
    {
        if (this.isPosInsideEllipsoid(x, y, z)) {return false;} //x, y, z pos is outside ellipsoid but at least one of adjacent pos is inside
        boolean upInEll,downInEll,northInEll,southInEll,westInEll,eastInEll;
        upInEll = isPosInsideEllipsoid(x, y + 1, z);
        downInEll = isPosInsideEllipsoid(x, y - 1, z);
        southInEll = isPosInsideEllipsoid(x, y, z + 1);
        northInEll = isPosInsideEllipsoid(x, y, z - 1);
        eastInEll = isPosInsideEllipsoid(x + 1, y, z);
        westInEll = isPosInsideEllipsoid(x - 1, y, z);
        return upInEll || downInEll || southInEll || northInEll || eastInEll || westInEll;
    }

    public boolean isPosAtEllipsoidInsideBorder(float x, float y, float z)
    {
        if (!this.isPosInsideEllipsoid(x, y, z)) {return false;} //x, y, z pos is inside ellipsoid but at least one of adjacent pos is outside
        boolean upOutEll,downOutEll,northOutEll,southOutEll,westOutEll,eastOutEll;
        upOutEll = !isPosInsideEllipsoid(x, y + 1, z);
        downOutEll = !isPosInsideEllipsoid(x, y - 1, z);
        southOutEll = !isPosInsideEllipsoid(x, y, z + 1);
        northOutEll = !isPosInsideEllipsoid(x, y, z - 1);
        eastOutEll = !isPosInsideEllipsoid(x + 1, y, z);
        westOutEll = !isPosInsideEllipsoid(x - 1, y, z);
        return upOutEll || downOutEll || southOutEll || northOutEll || eastOutEll || westOutEll;
    }

    protected boolean tryPlacingBlock(BlockPos.Mutable placementPos, BlockPos ellipsoidPos) //ellipsoidPos = offset from centerPos
    {
        StructureWorldAccess level = context.getWorld(); boolean isPlaceable = isReplaceable(level, placementPos);
        if (isPlaceable) {level.setBlockState(placementPos, this.getStateForPlacement(ellipsoidPos), 2);}
        return isPlaceable;
    }

    protected boolean isReplaceable(StructureWorldAccess reader, BlockPos blockPos)
    {
        BlockState previousBlock = reader.getBlockState(blockPos);
        return previousBlock.isAir() || previousBlock.isIn(AerialHellTags.Blocks.FEATURE_CAN_REPLACE);
    }

    public BlockPos getEllipsoidPosFromLevelPos(BlockPos levelPos)
    {
        return new BlockPos(levelPos.getX() - this.centerPos.getX(), levelPos.getY() - this.centerPos.getY(), levelPos.getZ() - this.centerPos.getZ());
    }

    public BlockPos getLevelPosFromEllipsoidPos(BlockPos ellipsoidPos) {return this.centerPos.add(ellipsoidPos);}
    public BlockPos getLevelPosFromEllipsoidPos(int x, int y, int z) {return this.centerPos.add(x, y, z);}

    public static class EllipsoidParameters
    {
        private final int xSize, ySize, zSize;
        private final int xForMin, xForMax, yForMin, yForMax, zForMin, zForMax;

        public EllipsoidParameters(int xSize, int ySize, int zSize, int bonusFor)
        {
            this(xSize, ySize, zSize, - xSize, xSize, - ySize, ySize, - zSize, zSize, bonusFor);
        }

        public EllipsoidParameters(int xSize, int ySize, int zSize, int xForMin, int xForMax, int yForMin, int yForMax, int zForMin, int zForMax, int bonusFor)
        {
            this(xSize, ySize, zSize, xForMin - bonusFor, xForMax - bonusFor, yForMin - bonusFor, yForMax + bonusFor, zForMin - bonusFor, zForMax + bonusFor);
        }

        public EllipsoidParameters(int xSize, int ySize, int zSize, int xForMin, int xForMax, int yForMin, int yForMax, int zForMin, int zForMax)
        {
            this.xSize = xSize; this.ySize = ySize; this.zSize = zSize; this.xForMin = xForMin; this.xForMax = xForMax; this.yForMin = yForMin; this.yForMax = yForMax; this.zForMin = zForMin; this.zForMax = zForMax;
        }

        public int xSize() {return xSize;}
        public int ySize() {return ySize;}
        public int zSize() {return zSize;}
        public int xForMin() {return xForMin;}
        public int xForMax() {return xForMax;}
        public int yForMin() {return yForMin;}
        public int yForMax() {return yForMax;}
        public int zForMin() {return zForMin;}
        public int zForMax() {return zForMax;}
    }

    public static class Types
    {
        public static EllipsoidType CENTER_1x1 = new EllipsoidType(0.0F);
        public static EllipsoidType CENTER_2x2 = new EllipsoidType(-0.5F);

        public static class EllipsoidType
        {
            public float horizontalCenterOffset;
            public EllipsoidType(float horizontalOffset) {this.horizontalCenterOffset = horizontalOffset;}
        }
    }
}
