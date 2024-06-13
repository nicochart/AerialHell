package fr.factionbedrock.aerialhell.World.Structure;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractAerialHellStructure extends Structure
{
    protected static final int MIN_STRUCTURE_SIZE = 0, MAX_STRUCTURE_SIZE = 50;
    protected static final int MIN_STRUCTURE_DISTANCE_FROM_CENTER = 1, MAX_STRUCTURE_DISTANCE_FROM_CENTER = 256;

    protected final Holder<StructureTemplatePool> startPool;
    protected final Optional<ResourceLocation> startJigsawName;
    protected final int size;
    protected final HeightProvider startHeight;
    protected final Optional<Heightmap.Types> projectStartToHeightmap;
    protected final int maxDistanceFromCenter;

    public AbstractAerialHellStructure(Structure.StructureSettings config, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter)
    {
        super(config);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }

    protected abstract boolean isStructureChunk(Structure.GenerationContext context);

    protected enum ChunkCoordinateType{CHUNK_MIN_COORDINATE, CHUNK_MAX_COORDINATE, CHUNK_MIDDLE_COORDINATE, MEAN, MEAN_IGNORE_OUTLIER}

    protected static int getTerrainHeight(Structure.GenerationContext context)
    {
        return getTerrainHeight(context, ChunkCoordinateType.MEAN_IGNORE_OUTLIER);
    }

    protected static int getTerrainHeight(Structure.GenerationContext context, ChunkCoordinateType type)
    {
        ChunkPos chunkpos = context.chunkPos();
        if (type == ChunkCoordinateType.CHUNK_MIN_COORDINATE)
        {
            return getTerrainHeight(context, chunkpos.getMinBlockX(), chunkpos.getMinBlockZ());
        }
        else if (type == ChunkCoordinateType.CHUNK_MAX_COORDINATE)
        {
            return getTerrainHeight(context, chunkpos.getMaxBlockX(), chunkpos.getMaxBlockZ());
        }
        else if (type == ChunkCoordinateType.CHUNK_MIDDLE_COORDINATE)
        {
            return getTerrainHeight(context, chunkpos.getMiddleBlockX(), chunkpos.getMiddleBlockZ());
        }
        else if (type == ChunkCoordinateType.MEAN || type == ChunkCoordinateType.MEAN_IGNORE_OUTLIER)
        {
            int xMin = chunkpos.getMinBlockX(), xMax = chunkpos.getMaxBlockX();
            int zMin = chunkpos.getMinBlockZ(), zMax = chunkpos.getMaxBlockZ();
            int xMiddle = chunkpos.getMiddleBlockX(), zMiddle = chunkpos.getMiddleBlockZ();

            int NorthWestHeight = getTerrainHeight(context, xMin, zMin);
            int SouthEastHeight = getTerrainHeight(context, xMax, zMax);
            int NorthEastHeight = getTerrainHeight(context, xMax, zMin);
            int SouthWestHeight = getTerrainHeight(context, xMin, zMax);
            int MiddleHeight = getTerrainHeight(context, xMiddle, zMiddle);

            if (type == ChunkCoordinateType.MEAN_IGNORE_OUTLIER)
            {
                boolean ignoreNorthWest = NorthWestHeight < 10;
                boolean ignoreSouthEast = SouthEastHeight < 10;
                boolean ignoreNorthEast = NorthEastHeight < 10;
                boolean ignoreSouthWest = SouthWestHeight < 10;
                boolean ignoreMiddle = MiddleHeight < 10;

                if (ignoreNorthWest && ignoreSouthEast && ignoreNorthEast && ignoreSouthWest && ignoreMiddle) {return MiddleHeight;}

                int referenceHeight = ignoreMiddle ? ignoreNorthWest ? ignoreSouthEast ? ignoreNorthEast ? SouthWestHeight : NorthEastHeight : SouthEastHeight: NorthWestHeight : MiddleHeight;
                if (referenceHeight == NorthWestHeight) {ignoreNorthWest = true;}
                else if (referenceHeight == SouthEastHeight) {ignoreSouthEast = true;}
                else if (referenceHeight == NorthEastHeight) {ignoreNorthEast = true;}
                else if (referenceHeight == SouthWestHeight) {ignoreSouthWest = true;}
                else if (referenceHeight == MiddleHeight) {ignoreMiddle = true;}

                int heightSum = referenceHeight;
                int count = 1;

                if (!ignoreNorthWest && Math.abs(referenceHeight - NorthWestHeight) < 10) {heightSum += NorthWestHeight; count++;}
                if (!ignoreSouthEast && Math.abs(referenceHeight - SouthEastHeight) < 10) {heightSum += SouthEastHeight; count++;}
                if (!ignoreNorthEast && Math.abs(referenceHeight - NorthEastHeight) < 10) {heightSum += NorthEastHeight; count++;}
                if (!ignoreSouthWest && Math.abs(referenceHeight - SouthWestHeight) < 10) {heightSum += SouthWestHeight; count++;}
                if (!ignoreMiddle && Math.abs(referenceHeight - MiddleHeight) < 10) {heightSum += MiddleHeight; count++;}

                return heightSum / count;
            }
            else //if type == MEAN
            {
                //unreliable because there can be heights in the void (y = -1).
                return (NorthWestHeight + SouthEastHeight + NorthEastHeight + SouthWestHeight + MiddleHeight) / 5;
            }
        }

        return getTerrainHeight(context, chunkpos.getMiddleBlockX(), chunkpos.getMiddleBlockZ());
    }

    protected static int getTerrainHeight(Structure.GenerationContext context, int posX, int posZ)
    {
        return context.chunkGenerator().getFirstOccupiedHeight(
                posX,
                posZ,
                Heightmap.Types.WORLD_SURFACE_WG,
                context.heightAccessor(),
                context.randomState());
    }

    @Override
    public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext context)
    {
        if (!this.isStructureChunk(context)) {return Optional.empty();}

        BlockPos structureCenter = findStructureCenter(context);
        if (structureCenter == null) {return Optional.empty();}

        Optional<Structure.GenerationStub> structurePiecesGenerator =
                JigsawPlacement.addPieces(
                        context,
                        this.startPool,
                        this.startJigsawName,
                        this.size, //jigsaw block "level"
                        structureCenter, //structure center
                        false, //"useExpansionHack" (set it false)
                        Optional.empty(), //this.projectStartToHeightmap is now manually override by findStructureCenter(context).
                        this.maxDistanceFromCenter);

        return structurePiecesGenerator;
    }

    @Nullable protected BlockPos findStructureCenter(Structure.GenerationContext context)
    {
        ChunkPos chunkPos = context.chunkPos();

        int sampledStartHeight = this.startHeight.sample(context.random(), new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor()));
        //sampledStartHeight corresponds to :
        //effective startY if this.projectStartToHeightmap.isEmpty()
        //y offset to apply if this.projectStartToHeightmap.isPresent()

        int startY = this.projectStartToHeightmap.isPresent() ? getTerrainHeight(context) + sampledStartHeight : sampledStartHeight;

        return new BlockPos(chunkPos.getMiddleBlockX(), startY, chunkPos.getMiddleBlockZ());
    }
}