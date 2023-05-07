package fr.factionbedrock.aerialhell.World.Structure;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.Util.StructureHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;

import java.util.Optional;

public abstract class AbstractClassicLittleStructure extends AbstractAerialHellStructure
{
    public AbstractClassicLittleStructure(PieceGeneratorSupplier<JigsawConfiguration> pieceGeneratorSupplier) {super(pieceGeneratorSupplier);}
    
    protected static boolean isClassicLittleStructureFeatureChunk(PieceGeneratorSupplier.Context<JigsawConfiguration> context, int minY, int maxY)
    {
        ChunkGenerator chunkGenerator = context.chunkGenerator(); long seed = context.seed(); ChunkPos chunkPos = context.chunkPos(); LevelHeightAccessor level = context.heightAccessor();
    	//cannot spawn next to another structure
    	if (StructureHelper.hasDungeonNearby(chunkGenerator, seed, chunkPos.x, chunkPos.z, 6)) {return false;}
        BlockPos centerOfChunk = chunkPos.getMiddleBlockPosition(0);

        int landHeight = chunkGenerator.getBaseHeight(centerOfChunk.getX(), centerOfChunk.getZ(), Heightmap.Types.WORLD_SURFACE_WG, level);
        return landHeight > minY && landHeight < maxY;
    }

    protected static Optional<PieceGenerator<JigsawConfiguration>> createClassicLittleStructurePiecesGenerator(PieceGeneratorSupplier.Context<JigsawConfiguration> context, BlockPos blockpos)
    {
        //blockpos must be set with y = Y_OFFSET from ground
        Optional<PieceGenerator<JigsawConfiguration>> structurePiecesGenerator =
                JigsawPlacement.addPieces(
                        context,
                        PoolElementStructurePiece::new,
                        blockpos, // structure pos
                        false,
                        true //true = use terrain height as base, and adds blockpos y to it
                );

        return structurePiecesGenerator;
    }
}