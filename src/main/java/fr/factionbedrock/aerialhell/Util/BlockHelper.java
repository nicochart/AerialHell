package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Block.ShiftableLeavesBlock;
import fr.factionbedrock.aerialhell.Block.ShiftableLogBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.BlockEntity.IntangibleTemporaryBlockEntity;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.core.SectionPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeResolver;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.StructureMode;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.status.ChunkStatus;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.lighting.LightEngine;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BlockHelper
{
    public static boolean isAerialHellPortalFrameBlock(BlockState blockstate)
    {
        return blockstate.is(AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK);
    }

    public static Block getAerialHellPortalFrameBlock()
    {
        return AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK;
    }

    public static boolean isItemCorrectForHarvesting(BlockState state, Item item)
    {
        Tool tool = item.components.get(DataComponents.TOOL);
        if (tool != null)
        {
            return tool.isCorrectForDrops(state);
        }

        return !(state.is(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                || state.is(BlockTags.INCORRECT_FOR_STONE_TOOL)
                || state.is(BlockTags.INCORRECT_FOR_IRON_TOOL)
                || state.is(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                || state.is(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                || state.is(BlockTags.INCORRECT_FOR_NETHERITE_TOOL));
    }

    /* ---- Functions copied from SpreadableBlock class ---- */
    public static boolean canBeGrass(BlockState state, LevelReader world, BlockPos pos)
    {
        BlockPos blockPos = pos.above();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.is(Blocks.SNOW) && blockState.getValue(SnowLayerBlock.LAYERS) == 1) {return true;}
        else if (blockState.getFluidState().getAmount() == 8) {return false;}
        else
        {
            int i = LightEngine.getLightBlockInto(state, blockState, Direction.UP, blockState.getLightBlock());
            return i < 15;
        }
    }

    public static boolean grassCanPropagate(BlockState state, ServerLevel world, BlockPos pos)
    {
        BlockPos blockpos = pos.above();
        return canBeGrass(state, world, pos) && !world.getFluidState(blockpos).is(FluidTags.WATER);
    }

    public enum CorruptionType{GRASS, OTHER, ANY}

    public static boolean tryCorrupt(ServerLevel world, BlockPos pos, RandomSource rand)
    {
        return tryCorrupt(world, pos, rand, 1.0F);
    }

    public static boolean tryCorrupt(ServerLevel world, BlockPos pos, RandomSource rand, float chanceMultiplier)
    {
        float chance = rand.nextFloat();
        if (chance < getCorruptChance(world, pos, CorruptionType.OTHER, chanceMultiplier) && BlockHelper.corrupt(world, pos, CorruptionType.OTHER)) {return true;}
        if (chance < getCorruptChance(world, pos, CorruptionType.GRASS, chanceMultiplier) && BlockHelper.corrupt(world, pos, CorruptionType.GRASS)) {return true;}
        return BlockHelper.canBeCorrupted(world, pos, BlockHelper.CorruptionType.ANY);
    }

    public static float getCorruptChance(ServerLevel world, BlockPos pos, BlockHelper.CorruptionType type, float chance_multiplier)
    {
        if (!BlockHelper.canBeCorrupted(world, pos, type)) {return 0.0F;}

        float custom_multiplier = LoadedConfigParams.SHADOW_SPREAD_SPEED_MULTIPLIER;
        float type_multiplier = (type == CorruptionType.ANY || type == CorruptionType.OTHER) ? 0.4F : 1.0F;
        float multiplier = chance_multiplier * custom_multiplier * type_multiplier;
        Holder<Biome> biome = getInitialBiomeAtPos(world, pos);
        if (biome.is(AerialHellTags.Biomes.IS_SHADOW)) {return multiplier * 1.8F;}
        else if (biome.is(AerialHellTags.Biomes.IS_CRYSTAL)) {return 0.0F;}
        else if (biome.is(AerialHellTags.Biomes.IS_AERIAL_HELL)) {return multiplier * 0.8F;}
        else {return 0.0F;}
    }

    public static boolean corrupt(ServerLevel world, BlockPos pos, CorruptionType corruptionType)
    {
        boolean mayCorruptAbove = false, mayCorruptBelow = false;
        BlockState beforeState = world.getBlockState(pos);
        @Nullable BlockState corruptedState = null;
        if (corruptionType == CorruptionType.OTHER || corruptionType == CorruptionType.ANY)
        {
            if (beforeState.is(AerialHellBlocks.STELLAR_STONE))
            {
                corruptedState = AerialHellBlocks.SHADOW_STONE.defaultBlockState();
            }
            else if (beforeState.is(AerialHellBlocks.STELLAR_STONE_CRYSTAL_BLOCK))
            {
                corruptedState = AerialHellBlocks.SHADOW_CRYSTAL_BLOCK.defaultBlockState();
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
                corruptedState = AerialHellBlocks.SHADOW_GRASS.defaultBlockState();
            }
            else if (beforeState.is(AerialHellBlocks.STELLAR_GRASS_BALL))
            {
                corruptedState = AerialHellBlocks.SHADOW_GRASS_BALL.defaultBlockState();
            }
            else if (beforeState.is(AerialHellBlocks.BRAMBLES))
            {
                corruptedState = AerialHellBlocks.SHADOW_BRAMBLES.defaultBlockState();
            }
            else if (beforeState.is(AerialHellBlocks.GLOWING_ROOTS))
            {
                BlockState aboveState = world.getBlockState(pos.above()), belowState = world.getBlockState(pos.above());
                boolean aboveIsAlreadyCorrupted = aboveState.is(AerialHellBlocks.SHADOW_GLOWING_ROOTS) || aboveState.is(AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT);
                BlockState defaultState = aboveIsAlreadyCorrupted ? AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT.defaultBlockState() : AerialHellBlocks.SHADOW_GLOWING_ROOTS.defaultBlockState();
                corruptedState =  aboveIsAlreadyCorrupted ? defaultState : defaultState.setValue(GrowingPlantHeadBlock.AGE, beforeState.getValue(GrowingPlantHeadBlock.AGE));
                mayCorruptBelow = aboveState.is(AerialHellTags.Blocks.ROOTS); mayCorruptAbove = !aboveIsAlreadyCorrupted && belowState.is(AerialHellTags.Blocks.ROOTS);
            }
            else if (beforeState.is(AerialHellBlocks.GLOWING_ROOTS_PLANT))
            {
                corruptedState = AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT.defaultBlockState();
                mayCorruptBelow = world.getBlockState(pos.below()).is(AerialHellTags.Blocks.ROOTS); mayCorruptAbove = world.getBlockState(pos.above()).is(AerialHellTags.Blocks.ROOTS);
            }
        }
        if (corruptionType == CorruptionType.GRASS || corruptionType == CorruptionType.ANY)
        {
            if (beforeState.is(AerialHellBlocks.STELLAR_GRASS_BLOCK))
            {
                corruptedState = AerialHellBlocks.SHADOW_GRASS_BLOCK.defaultBlockState();
                mayCorruptAbove = true;
            }
        }

        if (corruptedState != null)
        {
            world.setBlockAndUpdate(pos, corruptedState);
            corruptBiome(world, pos, 1);
            if (mayCorruptBelow && canBeCorrupted(world, pos.below(), CorruptionType.OTHER)) {corrupt(world, pos.below(), CorruptionType.OTHER);}
            if (mayCorruptAbove && canBeCorrupted(world, pos.above(), CorruptionType.OTHER)) {corrupt(world, pos.above(), CorruptionType.OTHER);}
            return true;
        }
        else {return false;}
    }

    //see net.minecraft.gametest.framework.GameTestHelper setBiome method and net.minecraft.server.commands.FillBiomeCommand fill method
    public static void corruptBiome(ServerLevel world, BlockPos pos, int radius)
    {
        BoundingBox boundingbox = getQuantizedBoundingBox(pos, radius);

        List<ChunkAccess> list = getChunkAccessListForBoundingBox(world, boundingbox);
        if (!list.isEmpty())
        {
            Holder<Biome> biome = getBiome(world, AerialHellBiomes.SHADOW_PLAIN);
            Holder<Biome> baseBiome = getInitialBiomeAtPos(world, pos); //initial worldgen biome
            Holder<Biome> currentBiome = getCurrentBiomeAtPos(world, pos); //current biome
            if (currentBiome.is(AerialHellTags.Biomes.IS_SHADOW)) {return;} //biome is already shadow
            else if (baseBiome.is(AerialHellTags.Biomes.IS_SHADOW)) {biome = baseBiome;} //biome initially was shadow but is currently not

            for (ChunkAccess chunk : list)
            {
                chunk.fillBiomesFromNoise(makeBiomeResolver(new MutableInt(0), chunk, boundingbox, biome, b -> true), world.getChunkSource().randomState().sampler());
                chunk.markUnsaved();
            }

            world.getChunkSource().chunkMap.resendBiomesForChunks(list);
        }
    }

    public static boolean uncorrupt(ServerLevel world, BlockPos pos)
    {
        boolean mayUncorruptAbove = false, mayUncorruptBelow = false;
        BlockState beforeState = world.getBlockState(pos);
        @Nullable BlockState uncorruptedState = null;
        if (beforeState.is(AerialHellBlocks.SHADOW_STONE))
        {
            uncorruptedState = AerialHellBlocks.STELLAR_STONE.defaultBlockState();
        }
        else if (beforeState.is(AerialHellBlocks.SHADOW_GRASS_BLOCK))
        {
            uncorruptedState = AerialHellBlocks.STELLAR_GRASS_BLOCK.defaultBlockState();
        }
        else if (beforeState.is(AerialHellBlocks.SHADOW_CRYSTAL_BLOCK))
        {
            uncorruptedState = AerialHellBlocks.STELLAR_STONE_CRYSTAL_BLOCK.defaultBlockState();
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
            uncorruptedState = AerialHellBlocks.STELLAR_GRASS.defaultBlockState();
            mayUncorruptAbove = true;
        }
        else if (beforeState.is(AerialHellBlocks.SHADOW_GRASS_BALL))
        {
            uncorruptedState = AerialHellBlocks.STELLAR_GRASS_BALL.defaultBlockState();
        }
        else if (beforeState.is(AerialHellBlocks.SHADOW_BRAMBLES))
        {
            uncorruptedState = AerialHellBlocks.BRAMBLES.defaultBlockState();
        }
        else if (beforeState.is(AerialHellBlocks.SHADOW_GLOWING_ROOTS))
        {
            BlockState aboveState = world.getBlockState(pos.above()), belowState = world.getBlockState(pos.above());
            boolean aboveIsAlreadyUncorrupted = aboveState.is(AerialHellBlocks.GLOWING_ROOTS) || aboveState.is(AerialHellBlocks.GLOWING_ROOTS_PLANT);
            BlockState defaultState = aboveIsAlreadyUncorrupted ? AerialHellBlocks.GLOWING_ROOTS_PLANT.defaultBlockState() : AerialHellBlocks.GLOWING_ROOTS.defaultBlockState();
            uncorruptedState =  aboveIsAlreadyUncorrupted ? defaultState : defaultState.setValue(GrowingPlantHeadBlock.AGE, beforeState.getValue(GrowingPlantHeadBlock.AGE));
            mayUncorruptBelow = aboveState.is(AerialHellTags.Blocks.ROOTS); mayUncorruptAbove = !aboveIsAlreadyUncorrupted && belowState.is(AerialHellTags.Blocks.ROOTS);
        }
        else if (beforeState.is(AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT))
        {
            uncorruptedState = AerialHellBlocks.GLOWING_ROOTS_PLANT.defaultBlockState();
            mayUncorruptBelow = world.getBlockState(pos.below()).is(AerialHellTags.Blocks.ROOTS); mayUncorruptAbove = world.getBlockState(pos.above()).is(AerialHellTags.Blocks.ROOTS);
        }
        if (uncorruptedState != null)
        {
            world.setBlock(pos, uncorruptedState, 3); //flag 1 | 2 = 3, to get client update and send neighborChange
            uncorruptBiome(world, pos, 1);
            if (mayUncorruptBelow && isCorrupted(world, pos.below())) {uncorrupt(world, pos.below());}
            if (mayUncorruptAbove && isCorrupted(world, pos.above())) {uncorrupt(world, pos.above());}
            return true;
        }
        return false;
    }

    //see net.minecraft.gametest.framework.GameTestHelper setBiome method and net.minecraft.server.commands.FillBiomeCommand fill method
    public static void uncorruptBiome(ServerLevel world, BlockPos pos, int radius)
    {
        BoundingBox boundingbox = getQuantizedBoundingBox(pos, radius);

        List<ChunkAccess> list = getChunkAccessListForBoundingBox(world, boundingbox);
        if (!list.isEmpty())
        {
            Holder<Biome> biome = getInitialBiomeAtPos(world, pos);
            if (biome.is(AerialHellTags.Biomes.IS_SHADOW)) //initial biome is shadow, need to find another biome to set
            {
                if (biome.is(AerialHellBiomes.SHADOW_FOREST)) {biome = getBiome(world, AerialHellBiomes.COPPER_PINE_FOREST);}
                else {biome = getBiome(world, AerialHellBiomes.AERIAL_HELL_PLAINS);}
            }

            for (ChunkAccess chunk : list)
            {
                chunk.fillBiomesFromNoise(makeBiomeResolver(new MutableInt(0), chunk, boundingbox, biome, b -> true), world.getChunkSource().randomState().sampler());
                chunk.markUnsaved();
            }

            world.getChunkSource().chunkMap.resendBiomesForChunks(list);
        }
    }

    public static boolean shiftBiomeShifterBlock(ServerLevel world, BlockPos pos, BiomeShifter.ShiftType shiftType)
    {
        @Nullable BlockState uncorruptedState = null;
        if (world.getBlockEntity(pos) instanceof BiomeShifter biomeShifter)
        {
            Supplier<Block> block = biomeShifter.getShiftedOrBrokenVariant();
            if (block == null) {return false;} //should never happen, but happened one time with fluorite ore, for a unknown reason
            uncorruptedState = block.get().defaultBlockState();
        }

        if (uncorruptedState != null)
        {
            world.setBlock(pos, uncorruptedState, 3); //flag 1 | 2 = 3, to get client update and send neighborChange
            if (shiftType == BiomeShifter.ShiftType.CORRUPT) {corruptBiome(world, pos, 1);}
            else /*if (shiftType == BiomeShifter.ShiftType.UNCORRUPT)*/ {uncorruptBiome(world, pos, 1);}

            return true;
        }
        return false;
    }

    public static boolean canAnyNeighborBeCorrupted(LevelReader world, BlockPos centerPos, CorruptionType corruptionType)
    {
        return canBeCorrupted(world, centerPos.above(), corruptionType)
                || canBeCorrupted(world, centerPos.below(), corruptionType)
                || canBeCorrupted(world, centerPos.north(), corruptionType)
                || canBeCorrupted(world, centerPos.south(), corruptionType)
                || canBeCorrupted(world, centerPos.east(), corruptionType)
                || canBeCorrupted(world, centerPos.west(), corruptionType);
    }

    public static boolean canBeCorrupted(LevelReader world, BlockPos pos, CorruptionType corruptionType)
    {
        boolean isGrassType = corruptionType == CorruptionType.GRASS || corruptionType == CorruptionType.ANY;
        boolean isOtherType = corruptionType == CorruptionType.OTHER ||  corruptionType == CorruptionType.ANY;
        if (surroundingsPreventCorruption(world, pos, corruptionType)) {return false;}
        else
        {
            return isCorrupted(world, pos) || ((world.getBlockState(pos).is(AerialHellBlocks.STELLAR_GRASS_BLOCK) && isGrassType)
                                           || (world.getBlockState(pos).getBlock() instanceof ShiftableLogBlock logBlock && logBlock.getShiftType() == BiomeShifter.ShiftType.CORRUPT && isOtherType)
                                           || (world.getBlockState(pos).getBlock() instanceof ShiftableLeavesBlock leavesBlock && leavesBlock.getShiftType() == BiomeShifter.ShiftType.CORRUPT && isOtherType)
                                           || (world.getBlockState(pos).is(AerialHellBlocks.STELLAR_STONE) && isOtherType)
                                           || (world.getBlockState(pos).is(AerialHellBlocks.STELLAR_STONE_CRYSTAL_BLOCK) && isOtherType)
                                           || (world.getBlockState(pos).is(AerialHellBlocks.STELLAR_GRASS) && isOtherType)
                                           || (world.getBlockState(pos).is(AerialHellBlocks.STELLAR_GRASS_BALL) && isOtherType)
                                           || (world.getBlockState(pos).is(AerialHellBlocks.BRAMBLES) && isOtherType)
                                           || (world.getBlockState(pos).is(AerialHellBlocks.GLOWING_ROOTS) && isOtherType)
                                           || (world.getBlockState(pos).is(AerialHellBlocks.GLOWING_ROOTS_PLANT) && isOtherType));
        }
    }

    public static boolean surroundingsPreventCorruption(LevelReader world, BlockPos pos, CorruptionType corruptionType)
    {
        if (corruptionType == CorruptionType.OTHER)
        {
            return false;
        }
        else if (corruptionType == CorruptionType.GRASS)
        {
            boolean isAlreadyGrass = false;
            if (world.getBlockState(pos).is(AerialHellBlocks.STELLAR_GRASS_BLOCK)) {isAlreadyGrass = true;}
            return !isAlreadyGrass && !canBeGrass(world.getBlockState(pos), world, pos);
        }
        else /*if (corruptionType == CorruptionType.ANY)*/
        {
            return surroundingsPreventCorruption(world, pos, CorruptionType.OTHER) && surroundingsPreventCorruption(world, pos, CorruptionType.GRASS);
        }
    }

    public static boolean isCorrupted(LevelReader world, BlockPos pos)
    {
        return world.getBlockState(pos).is(AerialHellBlocks.SHADOW_GRASS_BLOCK)
            || world.getBlockState(pos).is(AerialHellBlocks.SHADOW_STONE)
            || world.getBlockState(pos).is(AerialHellBlocks.SHADOW_CRYSTAL_BLOCK)
            || (world.getBlockState(pos).getBlock() instanceof ShiftableLogBlock logBlock && logBlock.getShiftType() == BiomeShifter.ShiftType.UNCORRUPT)
            || (world.getBlockState(pos).getBlock() instanceof ShiftableLeavesBlock leavesBlock && leavesBlock.getShiftType() == BiomeShifter.ShiftType.UNCORRUPT)
            || world.getBlockState(pos).is(AerialHellBlocks.SHADOW_GRASS)
            || world.getBlockState(pos).is(AerialHellBlocks.SHADOW_GRASS_BALL)
            || world.getBlockState(pos).is(AerialHellBlocks.SHADOW_BRAMBLES)
            || world.getBlockState(pos).is(AerialHellBlocks.SHADOW_GLOWING_ROOTS)
            || world.getBlockState(pos).is(AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT);
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

    public static Holder.Reference<Biome> getBiome(ServerLevel world, ResourceKey<Biome> biomeKey)
    {
        return world.registryAccess().lookupOrThrow(Registries.BIOME).getOrThrow(biomeKey);
    }

    public static Holder<Biome> getCurrentBiomeAtPos(ServerLevel world, BlockPos pos)
    {
        return world.getNoiseBiome(pos.getX() >> 2, pos.getY() >> 2, pos.getZ() >> 2);
    }

    public static Holder<Biome> getInitialBiomeAtPos(ServerLevel world, BlockPos pos) //return the worldgen biome, before any corruption
    {
        return world.getChunkSource().getGenerator().getBiomeSource().getNoiseBiome(pos.getX() >> 2, pos.getY() >> 2, pos.getZ() >> 2,world.getChunkSource().randomState().sampler());
    }

    //copy of net.minecraft.server.command.FillBiomeCommand createBiomeSupplier method
    private static BiomeResolver makeBiomeResolver(MutableInt counter, ChunkAccess chunk, BoundingBox box, Holder<Biome> biome, Predicate<Holder<Biome>> filter)
    {
        return (x, y, z, noise) ->
        {
            int i = QuartPos.toBlock(x);
            int j = QuartPos.toBlock(y);
            int k = QuartPos.toBlock(z);
            Holder<Biome> registryEntry2 = chunk.getNoiseBiome(x, y, z);
            if (box.isInside(i, j, k) && filter.test(registryEntry2))
            {
                counter.increment();
                return biome;
            }
            else {return registryEntry2;}
        };
    }

    private static int quantize(int blockCoordinate) {return QuartPos.toBlock(QuartPos.fromBlock(blockCoordinate));}

    public static BoundingBox getQuantizedBoundingBox(BlockPos pos, int radius)
    {
        BlockPos pos1 = new BlockPos(quantize(pos.getX() - radius), quantize(pos.getY() - radius), quantize(pos.getZ() - radius));
        BlockPos pos2 = new BlockPos(quantize(pos.getX() + radius), quantize(pos.getY() + radius), quantize(pos.getZ() + radius));
        return BoundingBox.fromCorners(pos1, pos2);
    }

    public static List<ChunkAccess> getChunkAccessListForBoundingBox(ServerLevel world, BoundingBox boundingbox)
    {
        List<ChunkAccess> list = new ArrayList();

        for (int k = SectionPos.blockToSectionCoord(boundingbox.minZ()); k <= SectionPos.blockToSectionCoord(boundingbox.maxZ()); k++)
        {
            for (int l = SectionPos.blockToSectionCoord(boundingbox.minX()); l <= SectionPos.blockToSectionCoord(boundingbox.maxX()); l++)
            {
                ChunkAccess chunk = world.getChunk(l, k, ChunkStatus.FULL, false);
                if (chunk == null) {/*should not happen*/}
                list.add(chunk);
            }
        }
        return list;
    }

    public static boolean hasAnySolidBlockAbove(LevelReader world, BlockPos pos)
    {
        for (BlockPos blockpos = pos.above(); blockpos.getY() < 256; blockpos = blockpos.above())
        {
            if (!world.isEmptyBlock(blockpos) && world.getBlockState(blockpos).isRedstoneConductor(world, pos)) {return true;}
        }
        return false;
    }

    public static boolean hasAnySolidSurfaceAbove(LevelReader world, BlockPos pos, int radius)
    {
        return BlockHelper.hasAnySolidBlockAbove(world, pos) && hasAnySolidBlockAbove(world, pos.offset(radius, 0, radius)) && hasAnySolidBlockAbove(world, pos.offset(radius, 0, -radius)) && hasAnySolidBlockAbove(world, pos.offset(-radius, 0, radius)) && hasAnySolidBlockAbove(world, pos.offset(-radius, 0, -radius));
    }

    public static void setIntangibleTemporaryBlockEntityBeforeState(LevelReader level, BlockPos pos, @Nullable BlockState state)
    {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof IntangibleTemporaryBlockEntity intangibleblockentity) {intangibleblockentity.setBeforeState(state);}
    }

    public static boolean isPosInsideStructureBlockZone(BlockPos pos, List<StructureBlockEntity> structureBlockEntities)
    {
        for (StructureBlockEntity structureBlockEntity : structureBlockEntities)
        {
            BlockPos offset = structureBlockEntity.getStructurePos();
            BlockPos origin = structureBlockEntity.getBlockPos().offset(offset);
            Vec3i size = structureBlockEntity.getStructureSize();
            StructureMode mode = structureBlockEntity.getMode();

            //ignore current iteration if structure block is not in "save" mode
            if (mode != StructureMode.SAVE) continue;

            BlockPos min = origin;
            BlockPos max = origin.offset(size.getX() - 1, size.getY() - 1, size.getZ() - 1);

            int minX = Math.min(min.getX(), max.getX());
            int minY = Math.min(min.getY(), max.getY());
            int minZ = Math.min(min.getZ(), max.getZ());
            int maxX = Math.max(min.getX(), max.getX());
            int maxY = Math.max(min.getY(), max.getY());
            int maxZ = Math.max(min.getZ(), max.getZ());

            if (pos.getX() >= minX && pos.getX() <= maxX && pos.getY() >= minY && pos.getY() <= maxY && pos.getZ() >= minZ && pos.getZ() <= maxZ) {return true;}
        }
        return false;
    }
}
