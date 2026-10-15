package fr.factionbedrock.aerialhell.World.Features.Util;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;

//replacement of vanilla FeaturePlaceContext, removed in 26.3 (features are now records holding their own config)
public record FeaturePlaceContext<FC>(WorldGenLevel level, ChunkGenerator chunkGenerator, RandomSource random, BlockPos origin, FC config) {}
