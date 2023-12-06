package fr.factionbedrock.aerialhell.Util;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

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
