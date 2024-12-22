package fr.factionbedrock.aerialhell.World.Structure;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.StructureLiquidSettings;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.structure.pool.alias.StructurePoolAliasBinding;
import net.minecraft.structure.pool.alias.StructurePoolAliasLookup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.HeightContext;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.structure.DimensionPadding;
import net.minecraft.world.gen.structure.Structure;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public abstract class AbstractAerialHellStructure extends Structure
{
    protected static final int MIN_STRUCTURE_SIZE = 0, MAX_STRUCTURE_SIZE = 50;
    protected static final int MIN_STRUCTURE_DISTANCE_FROM_CENTER = 1, MAX_STRUCTURE_DISTANCE_FROM_CENTER = 256;

    protected final RegistryEntry<StructurePool> startPool;
    protected final Optional<Identifier> startJigsawName;
    protected final int size;
    protected final HeightProvider startHeight;
    protected final Optional<Heightmap.Type> projectStartToHeightmap;
    protected final int maxDistanceFromCenter;
    protected final List<StructurePoolAliasBinding> poolAliases;

    public AbstractAerialHellStructure(Structure.Config config, RegistryEntry<StructurePool> startPool, Optional<Identifier> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Type> projectStartToHeightmap, int maxDistanceFromCenter, List<StructurePoolAliasBinding> poolAliasBindingList)
    {
        super(config);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
        this.poolAliases = poolAliasBindingList;
    }

    protected abstract boolean isStructureChunk(Structure.Context context);

    protected enum ChunkCoordinateType{CHUNK_MIN_COORDINATE, CHUNK_MAX_COORDINATE, CHUNK_MIDDLE_COORDINATE, MEAN, MEAN_IGNORE_OUTLIER}

    protected static int getTerrainHeight(Structure.Context context)
    {
        return getTerrainHeight(context, ChunkCoordinateType.MEAN_IGNORE_OUTLIER);
    }

    protected static int getTerrainHeight(Structure.Context context, ChunkCoordinateType type)
    {
        ChunkPos chunkpos = context.chunkPos();
        if (type == ChunkCoordinateType.CHUNK_MIN_COORDINATE)
        {
            return getTerrainHeight(context, chunkpos.getStartX(), chunkpos.getStartZ());
        }
        else if (type == ChunkCoordinateType.CHUNK_MAX_COORDINATE)
        {
            return getTerrainHeight(context, chunkpos.getEndX(), chunkpos.getEndZ());
        }
        else if (type == ChunkCoordinateType.CHUNK_MIDDLE_COORDINATE)
        {
            return getTerrainHeight(context, chunkpos.getCenterX(), chunkpos.getCenterZ());
        }
        else if (type == ChunkCoordinateType.MEAN || type == ChunkCoordinateType.MEAN_IGNORE_OUTLIER)
        {
            int xMin = chunkpos.getStartX(), xMax = chunkpos.getEndX();
            int zMin = chunkpos.getStartZ(), zMax = chunkpos.getEndZ();
            int xMiddle = chunkpos.getCenterX(), zMiddle = chunkpos.getCenterZ();

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

        return getTerrainHeight(context, chunkpos.getCenterX(), chunkpos.getCenterZ());
    }

    protected static int getTerrainHeight(Structure.Context context, int posX, int posZ)
    {
        return context.chunkGenerator().getHeightInGround(
                posX,
                posZ,
                Heightmap.Type.WORLD_SURFACE_WG,
                context.world(),
                context.noiseConfig());
    }

    @Override public Optional<Structure.StructurePosition> getStructurePosition(Structure.Context context)
    {
        if (!this.isStructureChunk(context)) {return Optional.empty();}

        BlockPos structureCenter = findStructureCenter(context);
        if (structureCenter == null) {return Optional.empty();}

        Optional<Structure.StructurePosition> structurePiecesGenerator =
                StructurePoolBasedGenerator.generate(
                        context, // Used for StructurePoolBasedGenerator to get all the proper behaviors done.
                        this.startPool, // The starting pool to use to create the structure layout from
                        this.startJigsawName, // Can be used to only spawn from one Jigsaw block. But we don't need to worry about this.
                        this.size, // How deep a branch of pieces can go away from center piece. (5 means branches cannot be longer than 5 pieces from center piece)
                        structureCenter, // Where to spawn the structure.
                        false, //"useExpansionHack" (set it false)
                        Optional.empty(), //this.projectStartToHeightmap is now manually override by findStructureCenter(context).
                        this.maxDistanceFromCenter, // Maximum limit for how far pieces can spawn from center. You cannot set this bigger than 128 or else pieces gets cutoff.
                        StructurePoolAliasLookup.EMPTY, // Optional thing that allows swapping a template pool with another per structure json instance. We don't need this but see vanilla JigsawStructure class for how to wire it up if you want it.
                        DimensionPadding.NONE, // dimensionPadding - Optional thing to prevent generating too close to the bottom or top of the dimension.
                        StructureLiquidSettings.IGNORE_WATERLOGGING); // liquidSettings - Optional thing to control whether the structure will be waterlogged when replacing pre-existing water in the world.

        return structurePiecesGenerator;
    }

    @Nullable protected BlockPos findStructureCenter(Structure.Context context)
    {
        ChunkPos chunkPos = context.chunkPos();

        int sampledStartHeight = this.startHeight.get(context.random(), new HeightContext(context.chunkGenerator(), context.world()));
        //sampledStartHeight corresponds to :
        //effective startY if this.projectStartToHeightmap.isEmpty()
        //y offset to apply if this.projectStartToHeightmap.isPresent()

        int startY = this.projectStartToHeightmap.isPresent() ? getTerrainHeight(context) + sampledStartHeight : sampledStartHeight;

        return new BlockPos(chunkPos.getCenterX(), startY, chunkPos.getCenterZ());
    }
}