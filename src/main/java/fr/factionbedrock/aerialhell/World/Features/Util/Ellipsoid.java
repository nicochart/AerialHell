package fr.factionbedrock.aerialhell.World.Features.Util;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

import java.util.function.Supplier;

public class Ellipsoid
{
    protected final Supplier<Block> block;
    protected final FeaturePlaceContext<?> context;
    protected final EllipsoidParameters ellipsoidParams;
    protected final Types.EllipsoidType ellipsoidType;
    protected final BlockPos centerPos;

    public Ellipsoid(FeaturePlaceContext<?> context, Supplier<Block> block, EllipsoidParameters ellipsoidParams, BlockPos centerPos, Types.EllipsoidType type)
    {
        this.context = context;
        this.block = block;
        this.ellipsoidParams = ellipsoidParams;
        this.ellipsoidType = type;
        this.centerPos = centerPos;
    }

    public void generate()
    {
        BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos();
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
        BlockPos.MutableBlockPos placementPos = new BlockPos.MutableBlockPos();
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

    protected void generateInnerLoop(BlockPos.MutableBlockPos placementPos, int x, int y, int z, boolean generateBorder)
    {
        if (generateBorder)
        {
            if (!this.isPosInsideEllipsoid(x, y, z))
            {
                if (isPosAtEllipsoidBorder(x, y, z)) //if pos is at ellipsis border : try to place block
                {
                    placementPos.set(centerPos.offset(x, y, z));
                    tryPlacingBlock(context, placementPos);
                }
            }
        }
        else
        {
            if (this.isPosInsideEllipsoid(x, y, z))
            {
                placementPos.set(centerPos.offset(x, y, z));
                tryPlacingBlock(context, placementPos);
            }
        }
    }

    public BlockState getStateToPlace(BlockPos pos) {return block.get().defaultBlockState();}

    public boolean isPosInsideEllipsoid(float xPos, float yPos, float zPos)
    {
        float x = xPos + this.ellipsoidType.horizontalCenterOffset;
        float y = yPos;
        float z = zPos + this.ellipsoidType.horizontalCenterOffset;
        return x*x/(ellipsoidParams.xSize()*ellipsoidParams.xSize()) + y*y/(ellipsoidParams.ySize()*ellipsoidParams.ySize()) + z*z/(ellipsoidParams.zSize()*ellipsoidParams.zSize()) < 1.0F;
    }

    public boolean isPosInsideGeneratedEllipsoidPart(float xPos, float yPos, float zPos) //if generate(centerPos) is called
    {
        if (xPos < ellipsoidParams.xForMin || xPos > ellipsoidParams.xForMax || yPos < ellipsoidParams.yForMin || yPos > ellipsoidParams.yForMax || zPos < ellipsoidParams.zForMin || zPos > ellipsoidParams.zForMax) {return false;}
        float x = xPos + this.ellipsoidType.horizontalCenterOffset;
        float y = yPos;
        float z = zPos + this.ellipsoidType.horizontalCenterOffset;
        return x*x/(ellipsoidParams.xSize()*ellipsoidParams.xSize()) + y*y/(ellipsoidParams.ySize()*ellipsoidParams.ySize()) + z*z/(ellipsoidParams.zSize()*ellipsoidParams.zSize()) < 1.0F;
    }

    public boolean isPosAtEllipsoidBorder(float x, float y, float z)
    {
        boolean upInEll,downInEll,northInEll,southInEll,westInEll,eastInEll;
        upInEll = isPosInsideEllipsoid(x, y + 1, z);
        downInEll = isPosInsideEllipsoid(x, y - 1, z);
        southInEll = isPosInsideEllipsoid(x, y, z + 1);
        northInEll = isPosInsideEllipsoid(x, y, z - 1);
        eastInEll = isPosInsideEllipsoid(x + 1, y, z);
        westInEll = isPosInsideEllipsoid(x - 1, y, z);
        return upInEll || downInEll || southInEll || northInEll || eastInEll || westInEll;
    }

    protected void tryPlacingBlock(FeaturePlaceContext<?> context, BlockPos.MutableBlockPos pos)
    {
        WorldGenLevel level = context.level();
        if (isReplaceable(level, pos)) {level.setBlock(pos, this.getStateToPlace(pos), 0);}
    }

    protected boolean isReplaceable(WorldGenLevel reader, BlockPos blockPos)
    {
        BlockState previousBlock = reader.getBlockState(blockPos);
        return previousBlock.isAir() || previousBlock.is(AerialHellTags.Blocks.FEATURE_CAN_REPLACE);
    }

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
