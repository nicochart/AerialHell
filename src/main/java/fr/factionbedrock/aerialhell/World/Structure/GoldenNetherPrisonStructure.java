package fr.factionbedrock.aerialhell.World.Structure;
/*
import java.util.Optional;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;

public class GoldenNetherPrisonStructure extends AbstractAerialHellStructure
{
    private final static int MIN_GEN_HEIGHT = 90, MAX_GEN_HEIGHT = 155;

    public GoldenNetherPrisonStructure() {super(GoldenNetherPrisonStructure::getPiecesGenerator);}

    private static Optional<PieceGenerator<JigsawConfiguration>> getPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context)
    {
        if (!isFeatureChunk(context)) {return Optional.empty();}
        else {return createPiecesGenerator(context);}
    }

    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context)
    {
        ChunkGenerator chunkGenerator = context.chunkGenerator();
        ChunkPos chunkpos = context.chunkPos();
        LevelHeightAccessor level = context.heightAccessor();
        long seed = context.seed();
        BlockPos centerOfChunk = chunkpos.getMiddleBlockPosition(0);

        int landHeight = chunkGenerator.getBaseHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Types.WORLD_SURFACE_WG, level);
        return landHeight < MIN_GEN_HEIGHT + MAX_GEN_HEIGHT / 2;
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