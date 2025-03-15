package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Block.DungeonCores.*;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlockCrackerItem extends WithInformationItem
{
    public BlockCrackerItem(Item.Settings settings) {super(settings);}

    @Override public ActionResult use(World world, PlayerEntity user, Hand hand)
    {
        BlockPos origin = user.getBlockPos();
        if (user.isCreative())
        {
            this.crackRandomBlocks(world, origin, 0.25F);
            user.playSound(SoundEvents.ENTITY_TURTLE_EGG_CRACK, 1.0F, 0.1F);
            user.getItemCooldownManager().set(user.getStackInHand(hand), 10);
            return ActionResult.CONSUME;
        }
        else
        {
            return ActionResult.PASS;
        }
    }

    protected void crackRandomBlocks(World world, BlockPos origin, float chance)
    {
        BlockPos setPos;
        int x, y, z, range = 5;
        for (x=-range - 1; x<=range + 1; x++)
        {
            for (y=-range - 1; y<=range + 1; y++)
            {
                for (z=-range - 1; z<=range + 1; z++)
                {
                    setPos = origin.add(x, y, z);
                    if (setPos.getSquaredDistance(origin) < range * range && world.getRandom().nextFloat() < chance)
                    {
                        this.tryCrackingBlock(world, setPos);
                    }
                }
            }
        }
    }

    protected void tryCrackingBlock(World world, BlockPos pos)
    {
        BlockState previousBlockState = world.getBlockState(pos);
        @Nullable BlockState nextBlockState = getNextBlockState(previousBlockState);

        if (nextBlockState != null) {world.setBlockState(pos, nextBlockState);}
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
        else if (previousBlock instanceof StairsBlock)
        {
            return nextBlock.getDefaultState().with(StairsBlock.FACING, previousBlockState.get(StairsBlock.FACING)).with(StairsBlock.HALF, previousBlockState.get(StairsBlock.HALF)).with(StairsBlock.SHAPE, previousBlockState.get(StairsBlock.SHAPE));
        }
        else if (previousBlock instanceof WallBlock)
        {
            return nextBlock.getDefaultState().with(WallBlock.UP, previousBlockState.get(WallBlock.UP)).with(WallBlock.NORTH_SHAPE, previousBlockState.get(WallBlock.NORTH_SHAPE)).with(WallBlock.SOUTH_SHAPE, previousBlockState.get(WallBlock.SOUTH_SHAPE)).with(WallBlock.WEST_SHAPE, previousBlockState.get(WallBlock.WEST_SHAPE)).with(WallBlock.EAST_SHAPE, previousBlockState.get(WallBlock.EAST_SHAPE)).with(WallBlock.WATERLOGGED, previousBlockState.get(WallBlock.WATERLOGGED));
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
        else if (previousBlock == AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS) {return AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS;}
        else if (previousBlock == AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_SLAB) {return AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_SLAB;}
        else if (previousBlock == AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_STAIRS) {return AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_STAIRS;}
        else if (previousBlock == AerialHellBlocks.SLIPPERY_SAND_STONE_BRICKS_WALL) {return AerialHellBlocks.CRACKED_SLIPPERY_SAND_STONE_BRICKS_WALL;}
        return null;
    }
}
