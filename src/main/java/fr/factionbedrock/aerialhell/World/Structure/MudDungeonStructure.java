package fr.factionbedrock.aerialhell.World.Structure;
/*
import java.util.Optional;

import fr.factionbedrock.aerialhell.Util.StructureHelper;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;

public class MudDungeonStructure extends AbstractAerialHellStructure
{
    private final static int MIN_GEN_HEIGHT = 20, MAX_GEN_HEIGHT = 50;

    public MudDungeonStructure() {super(MudDungeonStructure::getPiecesGenerator);}

    private static Optional<PieceGenerator<JigsawConfiguration>> getPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context)
    {
        if (!isFeatureChunk(context)) {return Optional.empty();}
        else {return createPiecesGenerator(context);}
    }

    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context)
    {
        ChunkGenerator chunkGenerator = context.chunkGenerator(); ChunkPos chunkpos = context.chunkPos(); LevelHeightAccessor level = context.heightAccessor(); long seed = context.seed();
        BlockPos centerOfChunk = chunkpos.getMiddleBlockPosition(0);

        int landHeight = chunkGenerator.getBaseHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Types.WORLD_SURFACE_WG, level);
        if (landHeight < MAX_GEN_HEIGHT) {return false;}
        if (StructureHelper.hasShadowCatacombsNearby(chunkGenerator, seed, chunkpos.x, chunkpos.z, 2, true)) {return false;}

        NoiseColumn columnOfBlocks = chunkGenerator.getBaseColumn(centerOfChunk.getX(), centerOfChunk.getZ(), level);
        return columnHasPercentOfNonAirBlocks(columnOfBlocks, 0.25F);
    }

    private static boolean columnHasPercentOfNonAirBlocks(NoiseColumn column, float part)
    {
        int count = 0;
        for (int y=MIN_GEN_HEIGHT; y<MAX_GEN_HEIGHT; y++)
        {
            if (!column.getBlock(y).isAir())
            {
                count++;
                if (count > (MAX_GEN_HEIGHT - MIN_GEN_HEIGHT) * part) {return true;}
            }
        }
        return false;
    }

    private static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context)
    {
        WorldgenRandom random = new WorldgenRandom(new LegacyRandomSource(context.seed()));
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(0);
        blockpos = moveInsideHeights(blockpos, MIN_GEN_HEIGHT, MAX_GEN_HEIGHT, random);

        Optional<PieceGenerator<JigsawConfiguration>> structurePiecesGenerator =
                JigsawPlacement.addPieces(
                        context,
                        PoolElementStructurePiece::new,
                        blockpos, // structure pos
                        false,
                        false //true = use terrain height as base, and adds blockpos y to it
                );

        return structurePiecesGenerator;
    }
}*/