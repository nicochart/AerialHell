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

    protected static int getTerrainHeight(Structure.GenerationContext context)
    {
        ChunkPos chunkpos = context.chunkPos();
        return getTerrainHeight(context, chunkpos.getMinBlockX(), chunkpos.getMinBlockZ());
    }

    protected static int getTerrainHeight(Structure.GenerationContext context, int posX, int posZ)
    {
        return context.chunkGenerator().getFirstOccupiedHeight(
                posX,
                posZ,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                context.heightAccessor(),
                context.randomState());
    }

    @Override
    public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext context)
    {
        if (!this.isStructureChunk(context)) {return Optional.empty();}

        int startY = this.startHeight.sample(context.random(), new WorldGenerationContext(context.chunkGenerator(), context.heightAccessor()));

        ChunkPos chunkPos = context.chunkPos();
        BlockPos blockPos = new BlockPos(chunkPos.getMinBlockX(), startY, chunkPos.getMinBlockZ());

        Optional<Structure.GenerationStub> structurePiecesGenerator =
                JigsawPlacement.addPieces(
                        context,
                        this.startPool,
                        this.startJigsawName,
                        this.size, //jigsaw block "level"
                        blockPos, //structure center
                        false, //"useExpansionHack" (set it false)
                        this.projectStartToHeightmap,
                        this.maxDistanceFromCenter);

        return structurePiecesGenerator;
    }
}