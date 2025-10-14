package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Block.ShiftableLeavesBlock;
import fr.factionbedrock.aerialhell.Block.ShiftableLogBlock;
import fr.factionbedrock.aerialhell.BlockEntity.BiomeShifter;
import fr.factionbedrock.aerialhell.BlockEntity.IntangibleTemporaryBlockEntity;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Registry.Worldgen.AerialHellBiomes;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.StructureBlockBlockEntity;
import net.minecraft.block.enums.StructureBlockMode;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeCoords;
import net.minecraft.world.biome.source.BiomeSupplier;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import net.minecraft.world.chunk.light.ChunkLightProvider;
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
        return blockstate.isOf(AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK);
    }

    public static Block getAerialHellPortalFrameBlock()
    {
        return AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK;
    }

    public static boolean isItemCorrectForHarvesting(BlockState state, Item item)
    {
        ToolComponent tool = item.components.get(DataComponentTypes.TOOL);
        if (tool != null)
        {
            return tool.isCorrectForDrops(state);
        }

        return !(state.isIn(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                || state.isIn(BlockTags.INCORRECT_FOR_STONE_TOOL)
                || state.isIn(BlockTags.INCORRECT_FOR_IRON_TOOL)
                || state.isIn(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                || state.isIn(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                || state.isIn(BlockTags.INCORRECT_FOR_NETHERITE_TOOL));
    }

    /* ---- Functions copied from SpreadableBlock class ---- */
    public static boolean canBeGrass(BlockState state, WorldView world, BlockPos pos)
    {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(Blocks.SNOW) && blockState.get(SnowBlock.LAYERS) == 1) {return true;}
        else if (blockState.getFluidState().getLevel() == 8) {return false;}
        else
        {
            int i = ChunkLightProvider.getRealisticOpacity(state, blockState, Direction.UP, blockState.getOpacity());
            return i < 15;
        }
    }

    public static boolean grassCanPropagate(BlockState state, ServerWorld world, BlockPos pos)
    {
        BlockPos blockpos = pos.up();
        return canBeGrass(state, world, pos) && !world.getFluidState(blockpos).isIn(FluidTags.WATER);
    }

    public enum CorruptionType{GRASS, OTHER, ANY}

    public static boolean tryCorrupt(ServerWorld world, BlockPos pos, Random rand)
    {
        return tryCorrupt(world, pos, rand, 1.0F);
    }

    public static boolean tryCorrupt(ServerWorld world, BlockPos pos, Random rand, float chanceMultiplier)
    {
        float chance = rand.nextFloat();
        if (chance < getCorruptChance(world, pos, CorruptionType.OTHER, chanceMultiplier) && BlockHelper.corrupt(world, pos, CorruptionType.OTHER)) {return true;}
        if (chance < getCorruptChance(world, pos, CorruptionType.GRASS, chanceMultiplier) && BlockHelper.corrupt(world, pos, CorruptionType.GRASS)) {return true;}
        return BlockHelper.canBeCorrupted(world, pos, BlockHelper.CorruptionType.ANY);
    }

    public static float getCorruptChance(ServerWorld world, BlockPos pos, BlockHelper.CorruptionType type, float chance_multiplier)
    {
        if (!BlockHelper.canBeCorrupted(world, pos, type)) {return 0.0F;}

        float custom_multiplier = LoadedConfigParams.SHADOW_SPREAD_SPEED_MULTIPLIER;
        float type_multiplier = (type == CorruptionType.ANY || type == CorruptionType.OTHER) ? 0.4F : 1.0F;
        float multiplier = chance_multiplier * custom_multiplier * type_multiplier;
        RegistryEntry<Biome> biome = getInitialBiomeAtPos(world, pos);
        if (biome.isIn(AerialHellTags.Biomes.IS_SHADOW)) {return multiplier * 1.8F;}
        else if (biome.isIn(AerialHellTags.Biomes.IS_CRYSTAL)) {return 0.0F;}
        else if (biome.isIn(AerialHellTags.Biomes.IS_AERIAL_HELL)) {return multiplier * 0.8F;}
        else {return 0.0F;}
    }

    public static boolean corrupt(ServerWorld world, BlockPos pos, CorruptionType corruptionType)
    {
        boolean mayCorruptAbove = false, mayCorruptBelow = false;
        BlockState beforeState = world.getBlockState(pos);
        @Nullable BlockState corruptedState = null;
        if (corruptionType == CorruptionType.OTHER || corruptionType == CorruptionType.ANY)
        {
            if (beforeState.isOf(AerialHellBlocks.STELLAR_STONE))
            {
                corruptedState = AerialHellBlocks.SHADOW_STONE.getDefaultState();
            }
            else if (beforeState.isOf(AerialHellBlocks.STELLAR_STONE_CRYSTAL_BLOCK))
            {
                corruptedState = AerialHellBlocks.SHADOW_CRYSTAL_BLOCK.getDefaultState();
            }
            else if (beforeState.getBlock() instanceof ShiftableLogBlock)
            {
                corruptedState = ShiftableLogBlock.getShiftedState(beforeState);
            }
            else if (beforeState.getBlock() instanceof ShiftableLeavesBlock)
            {
                corruptedState = ShiftableLeavesBlock.getShiftedState(beforeState);
            }
            else if (beforeState.isOf(AerialHellBlocks.STELLAR_GRASS))
            {
                corruptedState = AerialHellBlocks.SHADOW_GRASS.getDefaultState();
            }
            else if (beforeState.isOf(AerialHellBlocks.STELLAR_GRASS_BALL))
            {
                corruptedState = AerialHellBlocks.SHADOW_GRASS_BALL.getDefaultState();
            }
            else if (beforeState.isOf(AerialHellBlocks.BRAMBLES))
            {
                corruptedState = AerialHellBlocks.SHADOW_BRAMBLES.getDefaultState();
            }
            else if (beforeState.isOf(AerialHellBlocks.GLOWING_ROOTS))
            {
                BlockState aboveState = world.getBlockState(pos.up()), belowState = world.getBlockState(pos.up());
                boolean aboveIsAlreadyCorrupted = aboveState.isOf(AerialHellBlocks.SHADOW_GLOWING_ROOTS) || aboveState.isOf(AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT);
                BlockState defaultState = aboveIsAlreadyCorrupted ? AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT.getDefaultState() : AerialHellBlocks.SHADOW_GLOWING_ROOTS.getDefaultState();
                corruptedState =  aboveIsAlreadyCorrupted ? defaultState : defaultState.with(AbstractPlantStemBlock.AGE, beforeState.get(AbstractPlantStemBlock.AGE));
                mayCorruptBelow = aboveState.isIn(AerialHellTags.Blocks.ROOTS); mayCorruptAbove = !aboveIsAlreadyCorrupted && belowState.isIn(AerialHellTags.Blocks.ROOTS);
            }
            else if (beforeState.isOf(AerialHellBlocks.GLOWING_ROOTS_PLANT))
            {
                corruptedState = AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT.getDefaultState();
                mayCorruptBelow = world.getBlockState(pos.down()).isIn(AerialHellTags.Blocks.ROOTS); mayCorruptAbove = world.getBlockState(pos.up()).isIn(AerialHellTags.Blocks.ROOTS);
            }
        }
        if (corruptionType == CorruptionType.GRASS || corruptionType == CorruptionType.ANY)
        {
            if (beforeState.isOf(AerialHellBlocks.STELLAR_GRASS_BLOCK))
            {
                corruptedState = AerialHellBlocks.SHADOW_GRASS_BLOCK.getDefaultState();
                mayCorruptAbove = true;
            }
        }

        if (corruptedState != null)
        {
            world.setBlockState(pos, corruptedState);
            corruptBiome(world, pos, 1);
            if (mayCorruptBelow && canBeCorrupted(world, pos.down(), CorruptionType.OTHER)) {corrupt(world, pos.down(), CorruptionType.OTHER);}
            if (mayCorruptAbove && canBeCorrupted(world, pos.up(), CorruptionType.OTHER)) {corrupt(world, pos.up(), CorruptionType.OTHER);}
            return true;
        }
        else {return false;}
    }

    //see net.minecraft.gametest.framework.GameTestHelper setBiome method and net.minecraft.server.commands.FillBiomeCommand fill method
    public static void corruptBiome(ServerWorld world, BlockPos pos, int radius)
    {
        BlockBox boundingbox = getQuantizedBoundingBox(pos, radius);

        List<Chunk> list = getChunkAccessListForBoundingBox(world, boundingbox);
        if (!list.isEmpty())
        {
            RegistryEntry<Biome> biome = getBiome(world, AerialHellBiomes.SHADOW_PLAIN);
            RegistryEntry<Biome> baseBiome = getInitialBiomeAtPos(world, pos); //initial worldgen biome
            RegistryEntry<Biome> currentBiome = getCurrentBiomeAtPos(world, pos); //current biome
            if (currentBiome.isIn(AerialHellTags.Biomes.IS_SHADOW)) {return;} //biome is already shadow
            else if (baseBiome.isIn(AerialHellTags.Biomes.IS_SHADOW)) {biome = baseBiome;} //biome initially was shadow but is currently not

            for (Chunk chunk : list)
            {
                chunk.populateBiomes(makeBiomeResolver(new MutableInt(0), chunk, boundingbox, biome, b -> true), world.getChunkManager().getNoiseConfig().getMultiNoiseSampler());
                chunk.markNeedsSaving();
            }

            world.getChunkManager().chunkLoadingManager.sendChunkBiomePackets(list);
        }
    }

    public static boolean uncorrupt(ServerWorld world, BlockPos pos)
    {
        boolean mayUncorruptAbove = false, mayUncorruptBelow = false;
        BlockState beforeState = world.getBlockState(pos);
        @Nullable BlockState uncorruptedState = null;
        if (beforeState.isOf(AerialHellBlocks.SHADOW_STONE))
        {
            uncorruptedState = AerialHellBlocks.STELLAR_STONE.getDefaultState();
        }
        else if (beforeState.isOf(AerialHellBlocks.SHADOW_GRASS_BLOCK))
        {
            uncorruptedState = AerialHellBlocks.STELLAR_GRASS_BLOCK.getDefaultState();
        }
        else if (beforeState.isOf(AerialHellBlocks.SHADOW_CRYSTAL_BLOCK))
        {
            uncorruptedState = AerialHellBlocks.STELLAR_STONE_CRYSTAL_BLOCK.getDefaultState();
        }
        else if (beforeState.getBlock() instanceof ShiftableLogBlock)
        {
            uncorruptedState = ShiftableLogBlock.getShiftedState(beforeState);
        }
        else if (beforeState.getBlock() instanceof ShiftableLeavesBlock)
        {
            uncorruptedState = ShiftableLeavesBlock.getShiftedState(beforeState);
        }
        else if (beforeState.isOf(AerialHellBlocks.SHADOW_GRASS))
        {
            uncorruptedState = AerialHellBlocks.STELLAR_GRASS.getDefaultState();
            mayUncorruptAbove = true;
        }
        else if (beforeState.isOf(AerialHellBlocks.SHADOW_GRASS_BALL))
        {
            uncorruptedState = AerialHellBlocks.STELLAR_GRASS_BALL.getDefaultState();
        }
        else if (beforeState.isOf(AerialHellBlocks.SHADOW_BRAMBLES))
        {
            uncorruptedState = AerialHellBlocks.BRAMBLES.getDefaultState();
        }
        else if (beforeState.isOf(AerialHellBlocks.SHADOW_GLOWING_ROOTS))
        {
            BlockState aboveState = world.getBlockState(pos.up()), belowState = world.getBlockState(pos.up());
            boolean aboveIsAlreadyUncorrupted = aboveState.isOf(AerialHellBlocks.GLOWING_ROOTS) || aboveState.isOf(AerialHellBlocks.GLOWING_ROOTS_PLANT);
            BlockState defaultState = aboveIsAlreadyUncorrupted ? AerialHellBlocks.GLOWING_ROOTS_PLANT.getDefaultState() : AerialHellBlocks.GLOWING_ROOTS.getDefaultState();
            uncorruptedState =  aboveIsAlreadyUncorrupted ? defaultState : defaultState.with(AbstractPlantStemBlock.AGE, beforeState.get(AbstractPlantStemBlock.AGE));
            mayUncorruptBelow = aboveState.isIn(AerialHellTags.Blocks.ROOTS); mayUncorruptAbove = !aboveIsAlreadyUncorrupted && belowState.isIn(AerialHellTags.Blocks.ROOTS);
        }
        else if (beforeState.isOf(AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT))
        {
            uncorruptedState = AerialHellBlocks.GLOWING_ROOTS_PLANT.getDefaultState();
            mayUncorruptBelow = world.getBlockState(pos.down()).isIn(AerialHellTags.Blocks.ROOTS); mayUncorruptAbove = world.getBlockState(pos.up()).isIn(AerialHellTags.Blocks.ROOTS);
        }
        if (uncorruptedState != null)
        {
            world.setBlockState(pos, uncorruptedState, 3); //flag 1 | 2 = 3, to get client update and send neighborChange
            uncorruptBiome(world, pos, 1);
            if (mayUncorruptBelow && isCorrupted(world, pos.down())) {uncorrupt(world, pos.down());}
            if (mayUncorruptAbove && isCorrupted(world, pos.up())) {uncorrupt(world, pos.up());}
            return true;
        }
        return false;
    }

    //see net.minecraft.gametest.framework.GameTestHelper setBiome method and net.minecraft.server.commands.FillBiomeCommand fill method
    public static void uncorruptBiome(ServerWorld world, BlockPos pos, int radius)
    {
        BlockBox boundingbox = getQuantizedBoundingBox(pos, radius);

        List<Chunk> list = getChunkAccessListForBoundingBox(world, boundingbox);
        if (!list.isEmpty())
        {
            RegistryEntry<Biome> biome = getInitialBiomeAtPos(world, pos);
            if (biome.isIn(AerialHellTags.Biomes.IS_SHADOW)) //initial biome is shadow, need to find another biome to set
            {
                if (biome.matchesKey(AerialHellBiomes.SHADOW_FOREST)) {biome = getBiome(world, AerialHellBiomes.COPPER_PINE_FOREST);}
                else {biome = getBiome(world, AerialHellBiomes.AERIAL_HELL_PLAINS);}
            }

            for (Chunk chunk : list)
            {
                chunk.populateBiomes(makeBiomeResolver(new MutableInt(0), chunk, boundingbox, biome, b -> true), world.getChunkManager().getNoiseConfig().getMultiNoiseSampler());
                chunk.markNeedsSaving();
            }

            world.getChunkManager().chunkLoadingManager.sendChunkBiomePackets(list);
        }
    }

    public static boolean shiftBiomeShifterBlock(ServerWorld world, BlockPos pos, BiomeShifter.ShiftType shiftType)
    {
        @Nullable BlockState uncorruptedState = null;
        if (world.getBlockEntity(pos) instanceof BiomeShifter biomeShifter)
        {
            Supplier<Block> block = biomeShifter.getShiftedOrBrokenVariant();
            if (block == null) {return false;} //should never happen, but happened one time with fluorite ore, for a unknown reason
            uncorruptedState = block.get().getDefaultState();
        }

        if (uncorruptedState != null)
        {
            world.setBlockState(pos, uncorruptedState, 3); //flag 1 | 2 = 3, to get client update and send neighborChange
            if (shiftType == BiomeShifter.ShiftType.CORRUPT) {corruptBiome(world, pos, 1);}
            else /*if (shiftType == BiomeShifter.ShiftType.UNCORRUPT)*/ {uncorruptBiome(world, pos, 1);}

            return true;
        }
        return false;
    }

    public static boolean canAnyNeighborBeCorrupted(WorldView world, BlockPos centerPos, CorruptionType corruptionType)
    {
        return canBeCorrupted(world, centerPos.up(), corruptionType)
                || canBeCorrupted(world, centerPos.down(), corruptionType)
                || canBeCorrupted(world, centerPos.north(), corruptionType)
                || canBeCorrupted(world, centerPos.south(), corruptionType)
                || canBeCorrupted(world, centerPos.east(), corruptionType)
                || canBeCorrupted(world, centerPos.west(), corruptionType);
    }

    public static boolean canBeCorrupted(WorldView world, BlockPos pos, CorruptionType corruptionType)
    {
        boolean isGrassType = corruptionType == CorruptionType.GRASS || corruptionType == CorruptionType.ANY;
        boolean isOtherType = corruptionType == CorruptionType.OTHER ||  corruptionType == CorruptionType.ANY;
        if (surroundingsPreventCorruption(world, pos, corruptionType)) {return false;}
        else
        {
            return isCorrupted(world, pos) || ((world.getBlockState(pos).isOf(AerialHellBlocks.STELLAR_GRASS_BLOCK) && isGrassType)
                                           || (world.getBlockState(pos).getBlock() instanceof ShiftableLogBlock logBlock && logBlock.getShiftType() == BiomeShifter.ShiftType.CORRUPT && isOtherType)
                                           || (world.getBlockState(pos).getBlock() instanceof ShiftableLeavesBlock leavesBlock && leavesBlock.getShiftType() == BiomeShifter.ShiftType.CORRUPT && isOtherType)
                                           || (world.getBlockState(pos).isOf(AerialHellBlocks.STELLAR_STONE) && isOtherType)
                                           || (world.getBlockState(pos).isOf(AerialHellBlocks.STELLAR_STONE_CRYSTAL_BLOCK) && isOtherType)
                                           || (world.getBlockState(pos).isOf(AerialHellBlocks.STELLAR_GRASS) && isOtherType)
                                           || (world.getBlockState(pos).isOf(AerialHellBlocks.STELLAR_GRASS_BALL) && isOtherType)
                                           || (world.getBlockState(pos).isOf(AerialHellBlocks.BRAMBLES) && isOtherType)
                                           || (world.getBlockState(pos).isOf(AerialHellBlocks.GLOWING_ROOTS) && isOtherType)
                                           || (world.getBlockState(pos).isOf(AerialHellBlocks.GLOWING_ROOTS_PLANT) && isOtherType));
        }
    }

    public static boolean surroundingsPreventCorruption(WorldView world, BlockPos pos, CorruptionType corruptionType)
    {
        if (corruptionType == CorruptionType.OTHER)
        {
            return false;
        }
        else if (corruptionType == CorruptionType.GRASS)
        {
            boolean isAlreadyGrass = false;
            if (world.getBlockState(pos).isOf(AerialHellBlocks.STELLAR_GRASS_BLOCK)) {isAlreadyGrass = true;}
            return !isAlreadyGrass && !canBeGrass(world.getBlockState(pos), world, pos);
        }
        else /*if (corruptionType == CorruptionType.ANY)*/
        {
            return surroundingsPreventCorruption(world, pos, CorruptionType.OTHER) && surroundingsPreventCorruption(world, pos, CorruptionType.GRASS);
        }
    }

    public static boolean isCorrupted(WorldView world, BlockPos pos)
    {
        return world.getBlockState(pos).isOf(AerialHellBlocks.SHADOW_GRASS_BLOCK)
            || world.getBlockState(pos).isOf(AerialHellBlocks.SHADOW_STONE)
            || world.getBlockState(pos).isOf(AerialHellBlocks.SHADOW_CRYSTAL_BLOCK)
            || (world.getBlockState(pos).getBlock() instanceof ShiftableLogBlock logBlock && logBlock.getShiftType() == BiomeShifter.ShiftType.UNCORRUPT)
            || (world.getBlockState(pos).getBlock() instanceof ShiftableLeavesBlock leavesBlock && leavesBlock.getShiftType() == BiomeShifter.ShiftType.UNCORRUPT)
            || world.getBlockState(pos).isOf(AerialHellBlocks.SHADOW_GRASS)
            || world.getBlockState(pos).isOf(AerialHellBlocks.SHADOW_GRASS_BALL)
            || world.getBlockState(pos).isOf(AerialHellBlocks.SHADOW_BRAMBLES)
            || world.getBlockState(pos).isOf(AerialHellBlocks.SHADOW_GLOWING_ROOTS)
            || world.getBlockState(pos).isOf(AerialHellBlocks.SHADOW_GLOWING_ROOTS_PLANT);
    }

    public static boolean isSurroundingCorrupted(WorldView level, BlockPos pos)
    {
        for(int dx=-1; dx<=1; dx++)
        {
            for(int dy=-1; dy<=1; dy++)
            {
                for(int dz=-1; dz<=1; dz++)
                {
                    if (canBeCorrupted(level, pos.add(dx, dy, dz), CorruptionType.ANY) && !isCorrupted(level, pos.add(dx, dy, dz))) {return false;}
                }
            }
        }
        return true;
    }

    public static RegistryEntry.Reference<Biome> getBiome(ServerWorld world, RegistryKey<Biome> biomeKey)
    {
        return world.getRegistryManager().getOrThrow(RegistryKeys.BIOME).getOrThrow(biomeKey);
    }

    public static RegistryEntry<Biome> getCurrentBiomeAtPos(ServerWorld world, BlockPos pos)
    {
        return world.getBiomeForNoiseGen(pos.getX() >> 2, pos.getY() >> 2, pos.getZ() >> 2);
    }

    public static RegistryEntry<Biome> getInitialBiomeAtPos(ServerWorld world, BlockPos pos) //return the worldgen biome, before any corruption
    {
        return world.getChunkManager().getChunkGenerator().getBiomeSource().getBiome(pos.getX() >> 2, pos.getY() >> 2, pos.getZ() >> 2,world.getChunkManager().getNoiseConfig().getMultiNoiseSampler());
    }

    //copy of net.minecraft.server.command.FillBiomeCommand createBiomeSupplier method
    private static BiomeSupplier makeBiomeResolver(MutableInt counter, Chunk chunk, BlockBox box, RegistryEntry<Biome> biome, Predicate<RegistryEntry<Biome>> filter)
    {
        return (x, y, z, noise) ->
        {
            int i = BiomeCoords.toBlock(x);
            int j = BiomeCoords.toBlock(y);
            int k = BiomeCoords.toBlock(z);
            RegistryEntry<Biome> registryEntry2 = chunk.getBiomeForNoiseGen(x, y, z);
            if (box.contains(i, j, k) && filter.test(registryEntry2))
            {
                counter.increment();
                return biome;
            }
            else {return registryEntry2;}
        };
    }

    private static int quantize(int blockCoordinate) {return BiomeCoords.toBlock(BiomeCoords.fromBlock(blockCoordinate));}

    public static BlockBox getQuantizedBoundingBox(BlockPos pos, int radius)
    {
        BlockPos pos1 = new BlockPos(quantize(pos.getX() - radius), quantize(pos.getY() - radius), quantize(pos.getZ() - radius));
        BlockPos pos2 = new BlockPos(quantize(pos.getX() + radius), quantize(pos.getY() + radius), quantize(pos.getZ() + radius));
        return BlockBox.create(pos1, pos2);
    }

    public static List<Chunk> getChunkAccessListForBoundingBox(ServerWorld world, BlockBox boundingbox)
    {
        List<Chunk> list = new ArrayList();

        for (int k = ChunkSectionPos.getSectionCoord(boundingbox.getMinZ()); k <= ChunkSectionPos.getSectionCoord(boundingbox.getMaxZ()); k++)
        {
            for (int l = ChunkSectionPos.getSectionCoord(boundingbox.getMinX()); l <= ChunkSectionPos.getSectionCoord(boundingbox.getMaxX()); l++)
            {
                Chunk chunk = world.getChunk(l, k, ChunkStatus.FULL, false);
                if (chunk == null) {/*should not happen*/}
                list.add(chunk);
            }
        }
        return list;
    }

    public static boolean hasAnySolidBlockAbove(WorldView world, BlockPos pos)
    {
        for (BlockPos blockpos = pos.up(); blockpos.getY() < 256; blockpos = blockpos.up())
        {
            if (!world.isAir(blockpos) && world.getBlockState(blockpos).isSolidBlock(world, pos)) {return true;}
        }
        return false;
    }

    public static boolean hasAnySolidSurfaceAbove(WorldView world, BlockPos pos, int radius)
    {
        return BlockHelper.hasAnySolidBlockAbove(world, pos) && hasAnySolidBlockAbove(world, pos.add(radius, 0, radius)) && hasAnySolidBlockAbove(world, pos.add(radius, 0, -radius)) && hasAnySolidBlockAbove(world, pos.add(-radius, 0, radius)) && hasAnySolidBlockAbove(world, pos.add(-radius, 0, -radius));
    }

    public static void setIntangibleTemporaryBlockEntityBeforeState(WorldView level, BlockPos pos, @Nullable BlockState state)
    {
        BlockEntity blockentity = level.getBlockEntity(pos);
        if (blockentity instanceof IntangibleTemporaryBlockEntity intangibleblockentity) {intangibleblockentity.setBeforeState(state);}
    }

    public static boolean isPosInsideStructureBlockZone(BlockPos pos, List<StructureBlockBlockEntity> structureBlockEntities)
    {
        for (StructureBlockBlockEntity structureBlockEntity : structureBlockEntities)
        {
            BlockPos offset = structureBlockEntity.getOffset();
            BlockPos origin = structureBlockEntity.getPos().add(offset);
            Vec3i size = structureBlockEntity.getSize();
            StructureBlockMode mode = structureBlockEntity.getMode();

            //ignore current iteration if structure block is not in "save" mode
            if (mode != StructureBlockMode.SAVE) continue;

            BlockPos min = origin;
            BlockPos max = origin.add(size.getX() - 1, size.getY() - 1, size.getZ() - 1);

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
