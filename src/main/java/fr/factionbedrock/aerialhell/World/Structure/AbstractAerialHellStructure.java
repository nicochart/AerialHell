package fr.factionbedrock.aerialhell.World.Structure;
/*
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.PostPlacementProcessor;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Random;

public abstract class AbstractAerialHellStructure extends StructureFeature<JigsawConfiguration>
{
    private static final int MIN_STRUCTURE_SIZE = 0, MAX_STRUCTURE_SIZE = 50;
    public static final Codec<JigsawConfiguration> CODEC = RecordCodecBuilder.create((codec) -> {return codec.group(StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(JigsawConfiguration::startPool), Codec.intRange(MIN_STRUCTURE_SIZE, MAX_STRUCTURE_SIZE).fieldOf("size").forGetter(JigsawConfiguration::maxDepth)).apply(codec, JigsawConfiguration::new);});

    public AbstractAerialHellStructure(PieceGeneratorSupplier<JigsawConfiguration> pieceGeneratorSupplier)
    {
        super(CODEC, pieceGeneratorSupplier, PostPlacementProcessor.NONE);
    }

    @Override
    public GenerationStep.Decoration step() //What stage of generation your structure should be generated during
    {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    protected static BlockPos moveInsideHeights(BlockPos pos, int minY, int maxY, Random rand)
    {
        return new BlockPos(pos.getX(), minY + rand.nextInt(maxY - minY + 1), pos.getZ());
    }
}*/