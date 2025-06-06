package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Block.ShiftableLeavesBlock;
import fr.factionbedrock.aerialhell.Block.ShiftableLogBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellBiomes;
import net.minecraft.core.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeResolver;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.lighting.LightEngine;
import org.apache.commons.lang3.mutable.MutableInt;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BlockHelper
{
    public static boolean isAerialHellPortalFrameBlock(BlockState blockstate)
    {
        return blockstate.is(AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK.get());
    }

    public static Block getAerialHellPortalFrameBlock()
    {
        return AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK.get();
    }

    public static boolean isItemCorrectForHarvesting(BlockState state, Item item)
    {
        if (item instanceof DiggerItem diggerItem)
        {
            Tool tool = diggerItem.components().get(DataComponents.TOOL);
            if (tool != null)
            {
                return tool.isCorrectForDrops(state);
            }
        }

        return !(state.is(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                || state.is(BlockTags.INCORRECT_FOR_STONE_TOOL)
                || state.is(BlockTags.INCORRECT_FOR_IRON_TOOL)
                || state.is(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                || state.is(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                || state.is(BlockTags.INCORRECT_FOR_NETHERITE_TOOL));
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
            int i = LightEngine.getLightBlockInto(state, blockstate, Direction.UP, blockstate.getLightBlock());
            return i < 15;
        }
    }

    public static boolean grassCanPropagate(BlockState state, LevelReader worldReader, BlockPos pos)
    {
        BlockPos blockpos = pos.above();
        return canBeGrass(state, worldReader, pos) && !worldReader.getFluidState(blockpos).is(FluidTags.WATER);
    }

    public enum CorruptionType{GRASS, OTHER, ANY}

    public static boolean tryCorrupt(ServerLevel level, BlockPos pos, RandomSource rand)
    {
        return tryCorrupt(level, pos, rand, 1.0F);
    }

    public static boolean tryCorrupt(ServerLevel level, BlockPos pos, RandomSource rand, float chanceMultiplier)
    {
        float chance = rand.nextFloat();
        if (chance < getCorruptChance(level, pos, CorruptionType.OTHER, chanceMultiplier) && BlockHelper.corrupt(level, pos, CorruptionType.OTHER)) {return true;}
        if (chance < getCorruptChance(level, pos, CorruptionType.GRASS, chanceMultiplier) && BlockHelper.corrupt(level, pos, CorruptionType.GRASS)) {return true;}
        return BlockHelper.canBeCorrupted(level, pos, BlockHelper.CorruptionType.ANY);
    }

    public static float getCorruptChance(ServerLevel level, BlockPos pos, BlockHelper.CorruptionType type, float chance_multiplier)
    {
        if (!BlockHelper.canBeCorrupted(level, pos, type)) {return 0.0F;}

        float custom_multiplier = LoadedConfigParams.SHADOW_SPREAD_SPEED_MULTIPLIER;
        float type_multiplier = (type == CorruptionType.ANY || type == CorruptionType.OTHER) ? 0.4F : 1.0F;
        float multiplier = chance_multiplier * custom_multiplier * type_multiplier;
        Holder<Biome> biome = getInitialBiomeAtPos(level, pos);
        if (biome.is(AerialHellTags.Biomes.IS_SHADOW)) {return multiplier * 1.8F;}
        else if (biome.is(AerialHellTags.Biomes.IS_CRYSTAL)) {return 0.0F;}
        else if (biome.is(AerialHellTags.Biomes.IS_AERIAL_HELL)) {return multiplier * 0.8F;}
        else {return 0.0F;}
    }

    public static boolean corrupt(ServerLevel level, BlockPos pos, CorruptionType corruptionType)
    {
        boolean mayCorruptAbove = false, mayCorruptBelow = false;
        BlockState beforeState = level.getBlockState(pos);
        @Nullable BlockState corruptedState = null;
        if (corruptionType == CorruptionType.OTHER || corruptionType == CorruptionType.ANY)
        {
            if (beforeState.is(AerialHellBlocks.STELLAR_STONE.get()))
            {
                corruptedState = AerialHellBlocks.SHADOW_STONE.get().defaultBlockState();
            }
            else if (beforeState.is(AerialHellBlocks.STELLAR_STONE_CRYSTAL_BLOCK.get()))
            {
                corruptedState = AerialHellBlocks.SHADOW_CRYSTAL_BLOCK.get().defaultBlockState();
            }
            else if (beforeState.getBlock() instanceof ShiftableLogBlock)
            {
                corruptedState = ShiftableLogBlock.getShiftedState(beforeState);
            }
            else if (beforeState.getBlock() instanceof ShiftableLeavesBlock)
            {
                corruptedState = ShiftableLeavesBlock.getShiftedState(beforeState);
            }
            else if (beforeState.is(AerialHellBlocks.STELLAR_GRASS))
            {
                corruptedState = AerialHellBlocks.SHADOW_GRASS.get().defaultBlockState();
            }
            else if (beforeState.is(AerialHellBlocks.STELLAR_GRASS_BALL))
            {
                corruptedState = AerialHellBlocks.SHADOW_GRASS_BALL.get().defaultBlockState();
            }
            else if (beforeState.is(AerialHellBlocks.BRAMBLES))
            {
                corruptedState = AerialHellBlocks.SHADOW_BRAMBLES.get().defaultBlockState();
            }
            else if (beforeState.is(AerialHellBlocks.GLOWING_ROOTS))
            {
                BlockState aboveState = level.getBlockState(pos.above()), belowState = level.getBlockState(pos.above());
                boolean aboveIsAlreadyCorrupted = aboveState.is(AerialHellBlocks.SHADOW_GLOWING_ROOTS.get()) || aboveState.is(AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT.get());
                BlockState defaultState = aboveIsAlreadyCorrupted ? AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT.get().defaultBlockState() : AerialHellBlocks.SHADOW_GLOWING_ROOTS.get().defaultBlockState();
                corruptedState =  aboveIsAlreadyCorrupted ? defaultState : defaultState.setValue(GrowingPlantHeadBlock.AGE, beforeState.getValue(GrowingPlantHeadBlock.AGE));
                mayCorruptBelow = aboveState.is(AerialHellTags.Blocks.ROOTS); mayCorruptAbove = !aboveIsAlreadyCorrupted && belowState.is(AerialHellTags.Blocks.ROOTS);
            }
            else if (beforeState.is(AerialHellBlocks.GLOWING_ROOTS_PLANT))
            {
                corruptedState = AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT.get().defaultBlockState();
                mayCorruptBelow = level.getBlockState(pos.below()).is(AerialHellTags.Blocks.ROOTS); mayCorruptAbove = level.getBlockState(pos.above()).is(AerialHellTags.Blocks.ROOTS);
            }
        }
        if (corruptionType == CorruptionType.GRASS || corruptionType == CorruptionType.ANY)
        {
            if (beforeState.is(AerialHellBlocks.STELLAR_GRASS_BLOCK.get()))
            {
                corruptedState = AerialHellBlocks.SHADOW_GRASS_BLOCK.get().defaultBlockState();
                mayCorruptAbove = true;
            }
        }

        if (corruptedState != null)
        {
            level.setBlockAndUpdate(pos, corruptedState);
            corruptBiome(level, pos, 1);
            if (mayCorruptBelow && canBeCorrupted(level, pos.below(), CorruptionType.OTHER)) {corrupt(level, pos.below(), CorruptionType.OTHER);}
            if (mayCorruptAbove && canBeCorrupted(level, pos.above(), CorruptionType.OTHER)) {corrupt(level, pos.above(), CorruptionType.OTHER);}
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
                chunk.markUnsaved();
            }

            level.getChunkSource().chunkMap.resendBiomesForChunks(list);
        }
    }

    public static boolean uncorrupt(ServerLevel level, BlockPos pos)
    {
        boolean mayUncorruptAbove = false, mayUncorruptBelow = false;
        BlockState beforeState = level.getBlockState(pos);
        @Nullable BlockState uncorruptedState = null;
        if (beforeState.is(AerialHellBlocks.SHADOW_STONE.get()))
        {
            uncorruptedState = AerialHellBlocks.STELLAR_STONE.get().defaultBlockState();
        }
        else if (beforeState.is(AerialHellBlocks.SHADOW_GRASS_BLOCK.get()))
        {
            uncorruptedState = AerialHellBlocks.STELLAR_GRASS_BLOCK.get().defaultBlockState();
        }
        else if (beforeState.is(AerialHellBlocks.SHADOW_CRYSTAL_BLOCK.get()))
        {
            uncorruptedState = AerialHellBlocks.STELLAR_STONE_CRYSTAL_BLOCK.get().defaultBlockState();
        }
        else if (beforeState.getBlock() instanceof ShiftableLogBlock)
        {
            uncorruptedState = ShiftableLogBlock.getShiftedState(beforeState);
        }
        else if (beforeState.getBlock() instanceof ShiftableLeavesBlock)
        {
            uncorruptedState = ShiftableLeavesBlock.getShiftedState(beforeState);
        }
        else if (beforeState.is(AerialHellBlocks.SHADOW_GRASS))
        {
            uncorruptedState = AerialHellBlocks.STELLAR_GRASS.get().defaultBlockState();
            mayUncorruptAbove = true;
        }
        else if (beforeState.is(AerialHellBlocks.SHADOW_GRASS_BALL))
        {
            uncorruptedState = AerialHellBlocks.STELLAR_GRASS_BALL.get().defaultBlockState();
        }
        else if (beforeState.is(AerialHellBlocks.SHADOW_BRAMBLES))
        {
            uncorruptedState = AerialHellBlocks.BRAMBLES.get().defaultBlockState();
        }
        else if (beforeState.is(AerialHellBlocks.SHADOW_GLOWING_ROOTS))
        {
            BlockState aboveState = level.getBlockState(pos.above()), belowState = level.getBlockState(pos.above());
            boolean aboveIsAlreadyUncorrupted = aboveState.is(AerialHellBlocks.GLOWING_ROOTS.get()) || aboveState.is(AerialHellBlocks.GLOWING_ROOTS_PLANT.get());
            BlockState defaultState = aboveIsAlreadyUncorrupted ? AerialHellBlocks.GLOWING_ROOTS_PLANT.get().defaultBlockState() : AerialHellBlocks.GLOWING_ROOTS.get().defaultBlockState();
            uncorruptedState =  aboveIsAlreadyUncorrupted ? defaultState : defaultState.setValue(GrowingPlantHeadBlock.AGE, beforeState.getValue(GrowingPlantHeadBlock.AGE));
            mayUncorruptBelow = aboveState.is(AerialHellTags.Blocks.ROOTS); mayUncorruptAbove = !aboveIsAlreadyUncorrupted && belowState.is(AerialHellTags.Blocks.ROOTS);
        }
        else if (beforeState.is(AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT))
        {
            uncorruptedState = AerialHellBlocks.GLOWING_ROOTS_PLANT.get().defaultBlockState();
            mayUncorruptBelow = level.getBlockState(pos.below()).is(AerialHellTags.Blocks.ROOTS); mayUncorruptAbove = level.getBlockState(pos.above()).is(AerialHellTags.Blocks.ROOTS);
        }
        if (uncorruptedState != null)
        {
            level.setBlock(pos, uncorruptedState, 3); //flag 1 | 2 = 3, to get client update and send neighborChange
            uncorruptBiome(level, pos, 1);
            if (mayUncorruptBelow && isCorrupted(level, pos.below())) {uncorrupt(level, pos.below());}
            if (mayUncorruptAbove && isCorrupted(level, pos.above())) {uncorrupt(level, pos.above());}
            return true;
        }
        return false;
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
                chunk.markUnsaved();
            }

            level.getChunkSource().chunkMap.resendBiomesForChunks(list);
        }
    }

    public static boolean shiftBiomeShifterBlock(ServerLevel level, BlockPos pos, BiomeShifter.ShiftType shiftType)
    {
        @Nullable BlockState uncorruptedState = null;
        if (level.getBlockEntity(pos) instanceof BiomeShifter biomeShifter)
        {
            Supplier<Block> block = biomeShifter.getShiftedOrBrokenVariant();
            if (block == null) {return false;} //should never happen, but happened one time with fluorite ore, for a unknown reason
            uncorruptedState = block.get().defaultBlockState();
        }

        if (uncorruptedState != null)
        {
            level.setBlock(pos, uncorruptedState, 3); //flag 1 | 2 = 3, to get client update and send neighborChange
            if (shiftType == BiomeShifter.ShiftType.CORRUPT) {corruptBiome(level, pos, 1);}
            else /*if (shiftType == BiomeShifter.ShiftType.UNCORRUPT)*/ {uncorruptBiome(level, pos, 1);}

            return true;
        }
        return false;
    }

    public static boolean canAnyNeighborBeCorrupted(LevelReader level, BlockPos centerPos, CorruptionType corruptionType)
    {
        return canBeCorrupted(level, centerPos.above(), corruptionType)
                || canBeCorrupted(level, centerPos.below(), corruptionType)
                || canBeCorrupted(level, centerPos.north(), corruptionType)
                || canBeCorrupted(level, centerPos.south(), corruptionType)
                || canBeCorrupted(level, centerPos.east(), corruptionType)
                || canBeCorrupted(level, centerPos.west(), corruptionType);
    }

    public static boolean canBeCorrupted(LevelReader level, BlockPos pos, CorruptionType corruptionType)
    {
        boolean isGrassType = corruptionType == CorruptionType.GRASS || corruptionType == CorruptionType.ANY;
        boolean isOtherType = corruptionType == CorruptionType.OTHER ||  corruptionType == CorruptionType.ANY;
        if (surroundingsPreventCorruption(level, pos, corruptionType)) {return false;}
        else
        {
            return isCorrupted(level, pos) || ((level.getBlockState(pos).is(AerialHellBlocks.STELLAR_GRASS_BLOCK.get()) && isGrassType)
                                           || (level.getBlockState(pos).getBlock() instanceof ShiftableLogBlock logBlock && logBlock.getShiftType() == BiomeShifter.ShiftType.CORRUPT && isOtherType)
                                           || (level.getBlockState(pos).getBlock() instanceof ShiftableLeavesBlock leavesBlock && leavesBlock.getShiftType() == BiomeShifter.ShiftType.CORRUPT && isOtherType)
                                           || (level.getBlockState(pos).is(AerialHellBlocks.STELLAR_STONE.get()) && isOtherType)
                                           || (level.getBlockState(pos).is(AerialHellBlocks.STELLAR_STONE_CRYSTAL_BLOCK.get()) && isOtherType)
                                           || (level.getBlockState(pos).is(AerialHellBlocks.STELLAR_GRASS.get()) && isOtherType)
                                           || (level.getBlockState(pos).is(AerialHellBlocks.STELLAR_GRASS_BALL.get()) && isOtherType)
                                           || (level.getBlockState(pos).is(AerialHellBlocks.BRAMBLES.get()) && isOtherType)
                                           || (level.getBlockState(pos).is(AerialHellBlocks.GLOWING_ROOTS.get()) && isOtherType)
                                           || (level.getBlockState(pos).is(AerialHellBlocks.GLOWING_ROOTS_PLANT.get()) && isOtherType));
        }
    }

    public static boolean surroundingsPreventCorruption(LevelReader level, BlockPos pos, CorruptionType corruptionType)
    {
        if (corruptionType == CorruptionType.OTHER)
        {
            return false;
        }
        else if (corruptionType == CorruptionType.GRASS)
        {
            boolean isAlreadyGrass = false;
            if (level.getBlockState(pos).is(AerialHellBlocks.STELLAR_GRASS_BLOCK.get())) {isAlreadyGrass = true;}
            return !isAlreadyGrass && !canBeGrass(level.getBlockState(pos), level, pos);
        }
        else /*if (corruptionType == CorruptionType.ANY)*/
        {
            return surroundingsPreventCorruption(level, pos, CorruptionType.OTHER) && surroundingsPreventCorruption(level, pos, CorruptionType.GRASS);
        }
    }

    public static boolean isCorrupted(LevelReader level, BlockPos pos)
    {
        return level.getBlockState(pos).is(AerialHellBlocks.SHADOW_GRASS_BLOCK.get())
            || level.getBlockState(pos).is(AerialHellBlocks.SHADOW_STONE.get())
            || level.getBlockState(pos).is(AerialHellBlocks.SHADOW_CRYSTAL_BLOCK.get())
            || (level.getBlockState(pos).getBlock() instanceof ShiftableLogBlock logBlock && logBlock.getShiftType() == BiomeShifter.ShiftType.UNCORRUPT)
            || (level.getBlockState(pos).getBlock() instanceof ShiftableLeavesBlock leavesBlock && leavesBlock.getShiftType() == BiomeShifter.ShiftType.UNCORRUPT)
            || level.getBlockState(pos).is(AerialHellBlocks.SHADOW_GRASS.get())
            || level.getBlockState(pos).is(AerialHellBlocks.SHADOW_GRASS_BALL.get())
            || level.getBlockState(pos).is(AerialHellBlocks.SHADOW_BRAMBLES.get())
            || level.getBlockState(pos).is(AerialHellBlocks.SHADOW_GLOWING_ROOTS.get())
            || level.getBlockState(pos).is(AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT.get());
    }

    public static boolean isSurroundingCorrupted(LevelReader level, BlockPos pos)
    {
        for(int dx=-1; dx<=1; dx++)
        {
            for(int dy=-1; dy<=1; dy++)
            {
                for(int dz=-1; dz<=1; dz++)
                {
                    if (canBeCorrupted(level, pos.offset(dx, dy, dz), CorruptionType.ANY) && !isCorrupted(level, pos.offset(dx, dy, dz))) {return false;}
                }
            }
        }
        return true;
    }

    public static Holder<Biome> getBiome(ServerLevel level, ResourceKey<Biome> biomeKey)
    {
        return level.registryAccess().lookupOrThrow(Registries.BIOME).getOrThrow(biomeKey);
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

    public static BoundingBox getQuantizedBoundingBox(BlockPos pos, int radius)
    {
        BlockPos pos1 = new BlockPos(quantize(pos.getX() - radius), quantize(pos.getY() - radius), quantize(pos.getZ() - radius));
        BlockPos pos2 = new BlockPos(quantize(pos.getX() + radius), quantize(pos.getY() + radius), quantize(pos.getZ() + radius));
        return BoundingBox.fromCorners(pos1, pos2);
    }

    public static List<ChunkAccess> getChunkAccessListForBoundingBox(ServerLevel level, BoundingBox boundingbox)
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
            if (!reader.isEmptyBlock(blockpos) && reader.getBlockState(blockpos).isSolidRender()) {return true;}
        }
        return false;
    }

    public static boolean hasAnySolidSurfaceAbove(LevelReader reader, BlockPos pos, int radius)
    {
        return BlockHelper.hasAnySolidBlockAbove(reader, pos) && hasAnySolidBlockAbove(reader, pos.offset(radius, 0, radius)) && hasAnySolidBlockAbove(reader, pos.offset(radius, 0, -radius)) && hasAnySolidBlockAbove(reader, pos.offset(-radius, 0, radius)) && hasAnySolidBlockAbove(reader, pos.offset(-radius, 0, -radius));
    }
}
