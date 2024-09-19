package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellBiomes;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeResolver;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.lighting.LightEngine;
import net.neoforged.neoforge.common.Tags;
import org.apache.commons.lang3.mutable.MutableInt;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BlockHelper
{
    public static boolean isAerialHellPortalFrameBlock(BlockState blockstate)
    {
        return blockstate.is(AerialHellBlocksAndItems.STELLAR_PORTAL_FRAME_BLOCK.get());
    }

    public static Block getAerialHellPortalFrameBlock()
    {
        return AerialHellBlocksAndItems.STELLAR_PORTAL_FRAME_BLOCK.get();
    }

    public static boolean isItemMiningLevelSufficentForHarvesting(BlockState state, Item item)
    {
        int miningLevel = ItemHelper.getItemMiningLevel(item);
        if (state.is(Tags.Blocks.NEEDS_NETHERITE_TOOL) && miningLevel < 4) {return false;}
        else if (state.is(BlockTags.NEEDS_DIAMOND_TOOL) && miningLevel < 3) {return false;}
        else if (state.is(BlockTags.NEEDS_IRON_TOOL) && miningLevel < 2) {return false;}
        else if (state.is(BlockTags.NEEDS_STONE_TOOL) && miningLevel < 1) {return false;}
        return true;
    }

    /* ---- Functions copied from SpreadingSnowyDirtBlock class ---- */
    public static boolean canBeGrass(BlockState state, LevelReader worldReader, BlockPos pos)
    {
        BlockPos blockpos = pos.above();
        BlockState blockstate = worldReader.getBlockState(blockpos);
        if (blockstate.is(Blocks.SNOW) && blockstate.getValue(SnowLayerBlock.LAYERS) == 1) {return true;}
        else if (blockstate.getFluidState().getAmount() == 8) {return false;}
        else
        {
            int i = LightEngine.getLightBlockInto(worldReader, state, pos, blockstate, blockpos, Direction.UP, blockstate.getLightBlock(worldReader, blockpos));
            return i < worldReader.getMaxLightLevel();
        }
    }

    public static boolean grassCanPropagate(BlockState state, LevelReader worldReader, BlockPos pos)
    {
        BlockPos blockpos = pos.above();
        return canBeGrass(state, worldReader, pos) && !worldReader.getFluidState(blockpos).is(FluidTags.WATER);
    }

    public enum CorruptionType{STONE, GRASS, ANY}

    public static void tryCorrupt(ServerLevel level, BlockPos pos, RandomSource rand, float chanceMultiplier)
    {
        if (rand.nextFloat() > getCorruptChance(level, pos) * chanceMultiplier) {return;}
        if (BlockHelper.canBeCorrupted(level, pos, BlockHelper.CorruptionType.STONE)) {if (BlockHelper.corrupt(level, pos, CorruptionType.STONE)) {return ;}}
        if (BlockHelper.canBeCorrupted(level, pos, CorruptionType.GRASS)) {if (BlockHelper.corrupt(level, pos, CorruptionType.GRASS)) {return ;}}
    }

    public static float getCorruptChance(ServerLevel level, BlockPos pos)
    {
        Holder<Biome> biome = getInitialBiomeAtPos(level, pos);
        if (biome.is(AerialHellTags.Biomes.IS_SHADOW)) {return 1.0F;}
        else if (biome.is(AerialHellTags.Biomes.IS_CRYSTAL)) {return 0.0F;}
        else if (biome.is(AerialHellTags.Biomes.IS_AERIAL_HELL)) {return 0.1F;}
        else {return 0.0F;}
    }

    public static boolean corrupt(ServerLevel level, BlockPos pos, CorruptionType corruptionType)
    {
        BlockState beforeState = level.getBlockState(pos);
        @Nullable BlockState corruptedState = null;
        if (corruptionType == CorruptionType.STONE || corruptionType == CorruptionType.ANY)
        {
            if (beforeState.is(AerialHellBlocksAndItems.STELLAR_STONE.get()))
            {
                corruptedState = AerialHellBlocksAndItems.SHADOW_STONE.get().defaultBlockState();
            }
        }
        if (corruptionType == CorruptionType.GRASS || corruptionType == CorruptionType.ANY)
        {
            if (beforeState.is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get()))
            {
                corruptedState = AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get().defaultBlockState();
            }
        }

        if (corruptedState != null)
        {
            level.setBlockAndUpdate(pos, corruptedState);
            corruptBiome(level, pos, 1);
            return true;
        }
        else {return false;}
    }

    //see net.minecraft.gametest.framework.GameTestHelper setBiome method and net.minecraft.server.commands.FillBiomeCommand fill method
    public static void corruptBiome(ServerLevel level, BlockPos pos, int radius)
    {
        BoundingBox boundingbox = getQuantizedBoundingBox(pos, radius);

        List<ChunkAccess> list = getChunkAccessListForBoundingBox(level, boundingbox);
        if (!list.isEmpty())
        {
            Holder<Biome> biome = getBiome(level, AerialHellBiomes.SHADOW_PLAIN);
            Holder<Biome> baseBiome = getInitialBiomeAtPos(level, pos); //initial worldgen biome
            Holder<Biome> currentBiome = getCurrentBiomeAtPos(level, pos); //current biome
            if (currentBiome.is(AerialHellTags.Biomes.IS_SHADOW)) {return;} //biome is already shadow
            else if (baseBiome.is(AerialHellTags.Biomes.IS_SHADOW)) {biome = baseBiome;} //biome initially was shadow but is currently not

            for (ChunkAccess chunk : list)
            {
                chunk.fillBiomesFromNoise(makeBiomeResolver(new MutableInt(0), chunk, boundingbox, biome, b -> true), level.getChunkSource().randomState().sampler());
                chunk.setUnsaved(true);
            }

            level.getChunkSource().chunkMap.resendBiomesForChunks(list);
        }
    }

    public static void uncorrupt(ServerLevel level, BlockPos pos)
    {
        BlockState beforeState = level.getBlockState(pos);
        BlockState corruptedState;
        if (beforeState.is(AerialHellBlocksAndItems.SHADOW_STONE.get()))
        {
            corruptedState = AerialHellBlocksAndItems.STELLAR_STONE.get().defaultBlockState();
        }
        else /*if (beforeState.is(AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get()))*/
        {
            corruptedState = AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get().defaultBlockState();
        }
        level.setBlock(pos, corruptedState, 3); //flag 1 | 2 = 3, to get client update and send neighborChange
        uncorruptBiome(level, pos, 1);
    }

    //see net.minecraft.gametest.framework.GameTestHelper setBiome method and net.minecraft.server.commands.FillBiomeCommand fill method
    public static void uncorruptBiome(ServerLevel level, BlockPos pos, int radius)
    {
        BoundingBox boundingbox = getQuantizedBoundingBox(pos, radius);

        List<ChunkAccess> list = getChunkAccessListForBoundingBox(level, boundingbox);
        if (!list.isEmpty())
        {
            Holder<Biome> biome = getInitialBiomeAtPos(level, pos);
            if (biome.is(AerialHellTags.Biomes.IS_SHADOW)) //initial biome is shadow, need to find another biome to set
            {
                if (biome.is(AerialHellBiomes.SHADOW_FOREST)) {biome = getBiome(level, AerialHellBiomes.COPPER_PINE_FOREST);}
                else {biome = getBiome(level, AerialHellBiomes.AERIAL_HELL_PLAINS);}
            }

            for (ChunkAccess chunk : list)
            {
                chunk.fillBiomesFromNoise(makeBiomeResolver(new MutableInt(0), chunk, boundingbox, biome, b -> true), level.getChunkSource().randomState().sampler());
                chunk.setUnsaved(true);
            }

            level.getChunkSource().chunkMap.resendBiomesForChunks(list);
        }
    }

    public static boolean canBeCorrupted(LevelReader level, BlockPos pos, CorruptionType corruptionType)
    {
        boolean isGrassType = corruptionType == CorruptionType.GRASS || corruptionType == CorruptionType.ANY;
        boolean isStoneType = corruptionType == CorruptionType.GRASS ||  corruptionType == CorruptionType.ANY;
        if (surroundingsPreventCorruption(level, pos, corruptionType)) {return false;}
        else
        {
            return isCorrupted(level, pos) || ((level.getBlockState(pos).is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get()) && isGrassType)
                                           || (level.getBlockState(pos).is(AerialHellBlocksAndItems.STELLAR_STONE.get()) && isStoneType));
        }
    }

    public static boolean surroundingsPreventCorruption(LevelReader level, BlockPos pos, CorruptionType corruptionType)
    {
        if (hasNearbyFluoriteBlock(level, pos)) {return true;}

        if (corruptionType == CorruptionType.STONE)
        {
            return false;
        }
        else if (corruptionType == CorruptionType.GRASS)
        {
            boolean isAlreadyGrass = false;
            if (level.getBlockState(pos).is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get())) {isAlreadyGrass = true;}
            return !isAlreadyGrass && !canBeGrass(level.getBlockState(pos), level, pos);
        }
        else /*if (corruptionType == CorruptionType.ANY)*/
        {
            return surroundingsPreventCorruption(level, pos, CorruptionType.STONE) && surroundingsPreventCorruption(level, pos, CorruptionType.GRASS);
        }
    }

    private static boolean hasNearbyFluoriteBlock(LevelReader level, BlockPos pos)
    {
        for (int x=-1; x<=1; x++)
        {
            for (int y=-1; y<=1; y++)
            {
                for (int z=-1; z<=1; z++)
                {
                    if (level.getBlockState(pos.offset(x, y, z)).is(AerialHellBlocksAndItems.FLUORITE_BLOCK.get()))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isCorrupted(LevelReader level, BlockPos pos)
    {
        return level.getBlockState(pos).is(AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get()) || level.getBlockState(pos).is(AerialHellBlocksAndItems.SHADOW_STONE.get());
    }

    public static boolean isSurroundingCorrupted(LevelReader level, BlockPos pos)
    {
        for(int dx=-1; dx<=1; dx++)
        {
            for(int dy=-1; dy<=1; dy++)
            {
                for(int dz=-1; dz<=1; dz++)
                {
                    if (canBeCorrupted(level, pos, CorruptionType.ANY) && !isCorrupted(level, pos)) {return false;}
                }
            }
        }
        return true;
    }

    public static Holder<Biome> getBiome(ServerLevel level, ResourceKey<Biome> biomeKey)
    {
        return level.registryAccess().registryOrThrow(Registries.BIOME).getHolderOrThrow(biomeKey);
    }

    public static Holder<Biome> getCurrentBiomeAtPos(ServerLevel level, BlockPos pos)
    {
        return level.getNoiseBiome(pos.getX() >> 2, pos.getY() >> 2, pos.getZ() >> 2);
    }

    public static Holder<Biome> getInitialBiomeAtPos(ServerLevel level, BlockPos pos) //return the worldgen biome, before any corruption
    {
        return level.getChunkSource().getGenerator().getBiomeSource().getNoiseBiome(pos.getX() >> 2, pos.getY() >> 2, pos.getZ() >> 2,level.getChunkSource().randomState().sampler());
    }

    //copy of net.minecraft.server.commands.FillBiomeCommand makeResolver method
    private static BiomeResolver makeBiomeResolver(MutableInt biomeEntries, ChunkAccess chunk, BoundingBox targetRegion, Holder<Biome> replacementBiome, Predicate<Holder<Biome>> filter)
    {
        return (x, y, z, sampler) ->
        {
            int i = QuartPos.toBlock(x);
            int j = QuartPos.toBlock(y);
            int k = QuartPos.toBlock(z);
            Holder<Biome> holder = chunk.getNoiseBiome(x, y, z);
            if (targetRegion.isInside(i, j, k) && filter.test(holder))
            {
                biomeEntries.increment();
                return replacementBiome;
            } else {return holder;}
        };
    }

    private static int quantize(int blockCoordinate) {return QuartPos.toBlock(QuartPos.fromBlock(blockCoordinate));}

    private static BoundingBox getQuantizedBoundingBox(BlockPos pos, int radius)
    {
        BlockPos pos1 = new BlockPos(quantize(pos.getX() - radius), quantize(pos.getY() - radius), quantize(pos.getZ() - radius));
        BlockPos pos2 = new BlockPos(quantize(pos.getX() + radius), quantize(pos.getY() + radius), quantize(pos.getZ() + radius));
        return BoundingBox.fromCorners(pos1, pos2);
    }

    private static List<ChunkAccess> getChunkAccessListForBoundingBox(ServerLevel level, BoundingBox boundingbox)
    {
        List<ChunkAccess> list = new ArrayList<>();
        for (int z = SectionPos.blockToSectionCoord(boundingbox.minZ()); z <= SectionPos.blockToSectionCoord(boundingbox.maxZ()); z++)
        {
            for (int x = SectionPos.blockToSectionCoord(boundingbox.minX()); x <= SectionPos.blockToSectionCoord(boundingbox.maxX()); x++)
            {
                ChunkAccess chunkaccess = level.getChunk(x, z, ChunkStatus.FULL, true);
                if (chunkaccess == null) {/*should not happen*/}
                else {list.add(chunkaccess);}
            }
        }
        return list;
    }

    public static boolean hasAnySolidBlockAbove(LevelReader reader, BlockPos pos)
    {
        for (BlockPos blockpos = pos.above(); blockpos.getY() < 256; blockpos = blockpos.above())
        {
            if (!reader.isEmptyBlock(blockpos) && reader.getBlockState(blockpos).isSolidRender(reader, pos)) {return true;}
        }
        return false;
    }

    public static boolean hasAnySolidSurfaceAbove(LevelReader reader, BlockPos pos, int radius)
    {
        return BlockHelper.hasAnySolidBlockAbove(reader, pos) && hasAnySolidBlockAbove(reader, pos.offset(radius, 0, radius)) && hasAnySolidBlockAbove(reader, pos.offset(radius, 0, -radius)) && hasAnySolidBlockAbove(reader, pos.offset(-radius, 0, radius)) && hasAnySolidBlockAbove(reader, pos.offset(-radius, 0, -radius));
    }
}
