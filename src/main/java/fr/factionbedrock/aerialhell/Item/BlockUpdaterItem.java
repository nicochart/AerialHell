package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Block.StandingAndWall.AerialHellWallTorchBlock;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Iterator;

public class BlockUpdaterItem extends WithInformationItem
{
    public BlockUpdaterItem(Properties prop) {super(prop);}

    public void inventoryTick(ItemStack stack, Level level, Entity entity, int itemSlot, boolean isSelected)
    {
        BlockPos origin = entity.blockPosition();
        if (isSelected) {this.replaceBlocks(level, origin);}
    }

    protected void replaceBlocks(Level level, BlockPos origin)
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
                    if (setPos.distSqr(origin) < range * range && level.getBlockState(setPos).is(AerialHellTags.Blocks.REPLACE_IN))
                    {
                        this.replaceBlock(level, setPos);
                    }
                }
            }
        }
    }

    protected void replaceBlock(Level level, BlockPos pos)
    {
        Iterator<Block> replace_in = ForgeRegistries.BLOCKS.tags().getTag(AerialHellTags.Blocks.REPLACE_IN).iterator();
        Iterator<Block> replace_out = ForgeRegistries.BLOCKS.tags().getTag(AerialHellTags.Blocks.REPLACE_OUT).iterator();

        BlockState previousBlockState = level.getBlockState(pos);
        Block previousBlock = null, nextBlock = null;

        while(previousBlockState.getBlock() != previousBlock)
        {
            if (!replace_in.hasNext() || !replace_out.hasNext()) {return;}
            previousBlock = replace_in.next();
            nextBlock = replace_out.next();
        }

        level.setBlockAndUpdate(pos, this.getNextBlockState(previousBlockState, nextBlock));
    }

    protected BlockState getNextBlockState(BlockState previousBlockState, Block nextBlock)
    {
        Block previousBlock = previousBlockState.getBlock();
        if (nextBlock instanceof StairBlock && previousBlock instanceof StairBlock)
        {
            return nextBlock.defaultBlockState().setValue(StairBlock.FACING, previousBlockState.getValue(StairBlock.FACING)).setValue(StairBlock.HALF, previousBlockState.getValue(StairBlock.HALF)).setValue(StairBlock.SHAPE, previousBlockState.getValue(StairBlock.SHAPE));
        }
        else if (nextBlock instanceof SlabBlock && previousBlock instanceof SlabBlock)
        {
            return nextBlock.defaultBlockState().setValue(SlabBlock.TYPE, previousBlockState.getValue(SlabBlock.TYPE));
        }
        else if (nextBlock instanceof WallBlock && previousBlock instanceof WallBlock)
        {
            return nextBlock.defaultBlockState().setValue(WallBlock.UP, previousBlockState.getValue(WallBlock.UP)).setValue(WallBlock.NORTH_WALL, previousBlockState.getValue(WallBlock.NORTH_WALL)).setValue(WallBlock.SOUTH_WALL, previousBlockState.getValue(WallBlock.SOUTH_WALL)).setValue(WallBlock.WEST_WALL, previousBlockState.getValue(WallBlock.WEST_WALL)).setValue(WallBlock.EAST_WALL, previousBlockState.getValue(WallBlock.EAST_WALL)).setValue(WallBlock.WATERLOGGED, previousBlockState.getValue(WallBlock.WATERLOGGED));
        }
        else if (nextBlock instanceof FenceBlock && previousBlock instanceof FenceBlock)
        {
            return nextBlock.defaultBlockState().setValue(FenceBlock.NORTH, previousBlockState.getValue(FenceBlock.NORTH)).setValue(FenceBlock.EAST, previousBlockState.getValue(FenceBlock.EAST)).setValue(FenceBlock.WEST, previousBlockState.getValue(FenceBlock.WEST)).setValue(FenceBlock.SOUTH, previousBlockState.getValue(FenceBlock.SOUTH));
        }
        else if (nextBlock instanceof FenceGateBlock && previousBlock instanceof FenceGateBlock)
        {
            return nextBlock.defaultBlockState().setValue(FenceGateBlock.FACING, previousBlockState.getValue(FenceGateBlock.FACING)).setValue(FenceGateBlock.OPEN, previousBlockState.getValue(FenceGateBlock.OPEN)).setValue(FenceGateBlock.POWERED, previousBlockState.getValue(FenceGateBlock.POWERED)).setValue(FenceGateBlock.IN_WALL, previousBlockState.getValue(FenceGateBlock.IN_WALL));
        }
        else if (nextBlock instanceof RotatedPillarBlock && previousBlock instanceof RotatedPillarBlock)
        {
            return nextBlock.defaultBlockState().setValue(RotatedPillarBlock.AXIS, previousBlockState.getValue(RotatedPillarBlock.AXIS));
        }
        else if (nextBlock instanceof TrapDoorBlock && previousBlock instanceof TrapDoorBlock)
        {
            return nextBlock.defaultBlockState().setValue(TrapDoorBlock.OPEN, previousBlockState.getValue(TrapDoorBlock.OPEN)).setValue(TrapDoorBlock.HALF, previousBlockState.getValue(TrapDoorBlock.HALF)).setValue(TrapDoorBlock.POWERED, previousBlockState.getValue(TrapDoorBlock.POWERED)).setValue(TrapDoorBlock.FACING, previousBlockState.getValue(TrapDoorBlock.FACING));
        }
        else if (nextBlock instanceof DoorBlock && previousBlock instanceof DoorBlock)
        {
            return nextBlock.defaultBlockState().setValue(DoorBlock.FACING, previousBlockState.getValue(DoorBlock.FACING)).setValue(DoorBlock.OPEN, previousBlockState.getValue(DoorBlock.OPEN)).setValue(DoorBlock.HINGE, previousBlockState.getValue(DoorBlock.HINGE)).setValue(DoorBlock.POWERED, previousBlockState.getValue(DoorBlock.POWERED)).setValue(DoorBlock.HALF, previousBlockState.getValue(DoorBlock.HALF));
        }
        else if (nextBlock instanceof ButtonBlock && previousBlock instanceof ButtonBlock)
        {
            return nextBlock.defaultBlockState().setValue(ButtonBlock.POWERED, previousBlockState.getValue(ButtonBlock.POWERED)).setValue(ButtonBlock.FACE, previousBlockState.getValue(ButtonBlock.FACE)).setValue(ButtonBlock.FACING, previousBlockState.getValue(ButtonBlock.FACING));
        }
        else if (nextBlock instanceof PressurePlateBlock && previousBlock instanceof PressurePlateBlock)
        {
            return nextBlock.defaultBlockState().setValue(PressurePlateBlock.POWERED, previousBlockState.getValue(PressurePlateBlock.POWERED));
        }
        else if (nextBlock instanceof ComposterBlock && previousBlock instanceof ComposterBlock)
        {
            return nextBlock.defaultBlockState().setValue(ComposterBlock.LEVEL, previousBlockState.getValue(ComposterBlock.LEVEL));
        }
        else if (nextBlock instanceof LadderBlock && previousBlock instanceof LadderBlock)
        {
            return nextBlock.defaultBlockState().setValue(LadderBlock.FACING, previousBlockState.getValue(LadderBlock.FACING)).setValue(LadderBlock.WATERLOGGED, previousBlockState.getValue(LadderBlock.WATERLOGGED));
        }
        else if ((nextBlock instanceof WallTorchBlock || nextBlock instanceof AerialHellWallTorchBlock) && (previousBlock instanceof WallTorchBlock || previousBlock instanceof AerialHellWallTorchBlock))
        {
            return nextBlock.defaultBlockState().setValue(nextBlock instanceof WallTorchBlock ? WallTorchBlock.FACING : AerialHellWallTorchBlock.HORIZONTAL_FACING, previousBlockState.getValue(previousBlock instanceof WallTorchBlock ? WallTorchBlock.FACING : AerialHellWallTorchBlock.HORIZONTAL_FACING));
        }
        else if (nextBlock instanceof StandingSignBlock && previousBlock instanceof StandingSignBlock)
        {
            return nextBlock.defaultBlockState().setValue(StandingSignBlock.ROTATION, previousBlockState.getValue(StandingSignBlock.ROTATION)).setValue(StandingSignBlock.WATERLOGGED, previousBlockState.getValue(StandingSignBlock.WATERLOGGED));
        }
        else if (nextBlock instanceof WallSignBlock && previousBlock instanceof WallSignBlock)
        {
            return nextBlock.defaultBlockState().setValue(WallSignBlock.FACING, previousBlockState.getValue(WallSignBlock.FACING)).setValue(WallSignBlock.WATERLOGGED, previousBlockState.getValue(WallSignBlock.WATERLOGGED));
        }
        else
        {
            return nextBlock.defaultBlockState();
        }
    }
}
