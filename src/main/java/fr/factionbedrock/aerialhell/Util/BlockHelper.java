package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;
import net.neoforged.neoforge.common.Tags;

import javax.annotation.Nullable;

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

    public static void tryCorrupt(ServerLevel level, BlockPos pos, RandomSource rand)
    {
        if (rand.nextFloat() > getCorruptChance(level, pos)) {return;}
        if (BlockHelper.canBeCorrupted(level, pos, BlockHelper.CorruptionType.STONE)) {if (BlockHelper.corrupt(level, pos, CorruptionType.STONE)) {return ;}}
        if (BlockHelper.canBeCorrupted(level, pos, CorruptionType.GRASS)) {if (BlockHelper.corrupt(level, pos, CorruptionType.GRASS)) {return ;}}
    }

    public static float getCorruptChance(ServerLevel level, BlockPos pos)
    {
        if (level.getBiome(pos).is(AerialHellTags.Biomes.IS_SHADOW)) {return 1.0F;}
        else if (level.getBiome(pos).is(AerialHellTags.Biomes.IS_CRYSTAL)) {return 0.0F;}
        else if (level.getBiome(pos).is(AerialHellTags.Biomes.IS_AERIAL_HELL)) {return 0.1F;}
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
        if (corruptedState != null) {level.setBlockAndUpdate(pos, corruptedState); return true;}
        return false;
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
        level.setBlockAndUpdate(pos, corruptedState);
    }

    public static boolean canBeCorrupted(LevelReader level, BlockPos pos, CorruptionType corruptionType)
    {
        if (surroundingsPreventCorruption(level, pos, corruptionType)) {return false;}
        else
        {
            return isCorrupted(level, pos) || ((level.getBlockState(pos).is(AerialHellBlocksAndItems.STELLAR_GRASS_BLOCK.get()) && corruptionType == CorruptionType.GRASS)
                                           || (level.getBlockState(pos).is(AerialHellBlocksAndItems.STELLAR_STONE.get()) && corruptionType == CorruptionType.STONE));
        }
    }

    public static boolean surroundingsPreventCorruption(LevelReader level, BlockPos pos, CorruptionType corruptionType)
    {
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

    public static boolean isCorrupted(LevelReader level, BlockPos pos)
    {
        return level.getBlockState(pos).is(AerialHellBlocksAndItems.SHADOW_GRASS_BLOCK.get()) || level.getBlockState(pos).is(AerialHellBlocksAndItems.SHADOW_STONE.get());
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
