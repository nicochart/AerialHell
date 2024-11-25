package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Block.DungeonCores.*;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.core.BlockPos;
import net.minecraft.item.Item;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class BlockCrackerItem extends WithInformationItem
{
    public BlockCrackerItem(Item.Settings settings) {super(settings);}

    @Override public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        BlockPos origin = player.blockPosition();
        if (player.isCreative())
        {
            this.crackRandomBlocks(level, origin, 0.25F);
            player.playSound(SoundEvents.TURTLE_EGG_CRACK, 1.0F, 0.1F);
            player.getCooldowns().addCooldown(this, 10);
            return InteractionResultHolder.consume(player.getItemInHand(hand));
        }
        else
        {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
    }

    protected void crackRandomBlocks(Level level, BlockPos origin, float chance)
    {
        BlockPos setPos;
        int x, y, z, range = 5;
        for (x=-range - 1; x<=range + 1; x++)
        {
            for (y=-range - 1; y<=range + 1; y++)
            {
                for (z=-range - 1; z<=range + 1; z++)
                {
                    setPos = origin.offset(x, y, z);
                    if (setPos.distSqr(origin) < range * range && level.getRandom().nextFloat() < chance)
                    {
                        this.tryCrackingBlock(level, setPos);
                    }
                }
            }
        }
    }

    protected void tryCrackingBlock(Level level, BlockPos pos)
    {
        BlockState previousBlockState = level.getBlockState(pos);
        @Nullable BlockState nextBlockState = getNextBlockState(previousBlockState);

        if (nextBlockState != null) {level.setBlockState(pos, nextBlockState);}
    }

    @Nullable protected BlockState getNextBlockState(BlockState previousBlockState)
    {
        Block previousBlock = previousBlockState.getBlock();
        @Nullable Block nextBlock = getNextBlock(previousBlock);
        if (nextBlock == null) {return null;}

        if (previousBlock instanceof SlabBlock)
        {
            return nextBlock.getDefaultState().with(SlabBlock.TYPE, previousBlockState.get(SlabBlock.TYPE));
        }
        else if (previousBlock instanceof StairBlock)
        {
            return nextBlock.getDefaultState().with(StairBlock.FACING, previousBlockState.get(StairBlock.FACING)).setValue(StairBlock.HALF, previousBlockState.get(StairBlock.HALF)).setValue(StairBlock.SHAPE, previousBlockState.get(StairBlock.SHAPE));
        }
        else if (previousBlock instanceof WallBlock)
        {
            return nextBlock.getDefaultState().with(WallBlock.UP, previousBlockState.get(WallBlock.UP)).setValue(WallBlock.NORTH_WALL, previousBlockState.get(WallBlock.NORTH_WALL)).setValue(WallBlock.SOUTH_WALL, previousBlockState.get(WallBlock.SOUTH_WALL)).setValue(WallBlock.WEST_WALL, previousBlockState.get(WallBlock.WEST_WALL)).setValue(WallBlock.EAST_WALL, previousBlockState.get(WallBlock.EAST_WALL)).setValue(WallBlock.WATERLOGGED, previousBlockState.get(WallBlock.WATERLOGGED));
        }
        else
        {
            return nextBlock.getDefaultState();
        }
    }

    @Nullable protected Block getNextBlock(Block previousBlock)
    {
        if (previousBlock instanceof CoreProtectedSlabBlock previousCoreProtectedSlabBlock) {return previousCoreProtectedSlabBlock.getCrackedVariant();}
        else if (previousBlock instanceof CoreProtectedStairsBlock previousCoreProtectedStairsBlock) {return previousCoreProtectedStairsBlock.getCrackedVariant();}
        else if (previousBlock instanceof CoreProtectedWallBlock previousCoreProtectedWallBlock) {return previousCoreProtectedWallBlock.getCrackedVariant();}
        else if (previousBlock instanceof CoreProtectedBlock previousCoreProtectedBlock) {return previousCoreProtectedBlock.getCrackedVariant();}
        else if (previousBlock == AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_BRICKS.get()) {return AerialHellBlocksAndItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS.get();}
        else if (previousBlock == AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_BRICKS_SLAB.get()) {return AerialHellBlocksAndItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB.get();}
        else if (previousBlock == AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_BRICKS_STAIRS.get()) {return AerialHellBlocksAndItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS.get();}
        else if (previousBlock == AerialHellBlocksAndItems.SLIPPERY_SAND_STONE_BRICKS_WALL.get()) {return AerialHellBlocksAndItems.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL.get();}
        return null;
    }
}
