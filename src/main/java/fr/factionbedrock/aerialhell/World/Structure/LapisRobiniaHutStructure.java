package fr.factionbedrock.aerialhell.World.Structure;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.Optional;

public class LapisRobiniaHutStructure extends AbstractClassicLittleStructure
{
    private final static int MIN_GEN_HEIGHT = 50, MAX_GEN_HEIGHT = 190, Y_OFFSET = -1;

    public LapisRobiniaHutStructure() {super(LapisRobiniaHutStructure::getPiecesGenerator);}

    private static Optional<PieceGenerator<JigsawConfiguration>> getPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context)
    {
        if (!isFeatureChunk(context)) {return Optional.empty();}
        else {return createPiecesGenerator(context);}
    }

    private static boolean isFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context)
    {
        return isClassicLittleStructureFeatureChunk(context, MIN_GEN_HEIGHT, MAX_GEN_HEIGHT);
    }

    private static Optional<PieceGenerator<JigsawConfiguration>> createPiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context)
    {
        BlockPos blockpos = context.chunkPos().getMiddleBlockPosition(Y_OFFSET);

        return AbstractClassicLittleStructure.createClassicLittleStructurePiecesGenerator(context, blockpos);
    }
}