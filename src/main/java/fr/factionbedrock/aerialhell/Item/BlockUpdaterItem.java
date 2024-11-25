package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Block.StandingAndWall.AerialHellWallTorchBlock;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.item.Item;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Iterator;

public class BlockUpdaterItem extends WithInformationItem
{
    public BlockUpdaterItem(Item.Settings settings) {super(settings);}

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
        Iterator<Holder<Block>> replace_in = BuiltInRegistries.BLOCK.getTag(AerialHellTags.Blocks.REPLACE_IN).get().iterator();
        Iterator<Holder<Block>> replace_out = BuiltInRegistries.BLOCK.getTag(AerialHellTags.Blocks.REPLACE_OUT).get().iterator();

        BlockState previousBlockState = level.getBlockState(pos);
        Block previousBlock = null, nextBlock = null;

        while(previousBlockState.getBlock() != previousBlock)
        {
            if (!replace_in.hasNext() || !replace_out.hasNext()) {return;}
            previousBlock = replace_in.next().value();
            nextBlock = replace_out.next().value();
        }

        BlockState nextBlockState = this.getNextBlockState(previousBlockState, nextBlock);
        BlockEntity previousBlockEntity = level.getBlockEntity(pos);
        if (previousBlockEntity != null) {level.removeBlockEntity(pos); previousBlockEntity.setBlockState(nextBlockState);}
        level.setBlockState(pos, nextBlockState);
        if (previousBlock instanceof BaseEntityBlock && nextBlock instanceof BaseEntityBlock && previousBlockEntity != null)
        {
            level.setBlockEntity(previousBlockEntity);
        }
    }

    protected BlockState getNextBlockState(BlockState previousBlockState, Block nextBlock)
    {
        Block previousBlock = previousBlockState.getBlock();
        if (nextBlock instanceof LeavesBlock && previousBlock instanceof LeavesBlock)
        {
            return nextBlock.getDefaultState().with(LeavesBlock.DISTANCE, previousBlockState.get(LeavesBlock.DISTANCE)).setValue(LeavesBlock.PERSISTENT, previousBlockState.get(LeavesBlock.PERSISTENT));
        }
        else if (nextBlock instanceof StairBlock && previousBlock instanceof StairBlock)
        {
            return nextBlock.getDefaultState().with(StairBlock.FACING, previousBlockState.get(StairBlock.FACING)).setValue(StairBlock.HALF, previousBlockState.get(StairBlock.HALF)).setValue(StairBlock.SHAPE, previousBlockState.get(StairBlock.SHAPE));
        }
        else if (nextBlock instanceof SlabBlock && previousBlock instanceof SlabBlock)
        {
            return nextBlock.getDefaultState().with(SlabBlock.TYPE, previousBlockState.get(SlabBlock.TYPE));
        }
        else if (nextBlock instanceof WallBlock && previousBlock instanceof WallBlock)
        {
            return nextBlock.getDefaultState().with(WallBlock.UP, previousBlockState.get(WallBlock.UP)).setValue(WallBlock.NORTH_WALL, previousBlockState.get(WallBlock.NORTH_WALL)).setValue(WallBlock.SOUTH_WALL, previousBlockState.get(WallBlock.SOUTH_WALL)).setValue(WallBlock.WEST_WALL, previousBlockState.get(WallBlock.WEST_WALL)).setValue(WallBlock.EAST_WALL, previousBlockState.get(WallBlock.EAST_WALL)).setValue(WallBlock.WATERLOGGED, previousBlockState.get(WallBlock.WATERLOGGED));
        }
        else if (nextBlock instanceof FenceBlock && previousBlock instanceof FenceBlock)
        {
            return nextBlock.getDefaultState().with(FenceBlock.NORTH, previousBlockState.get(FenceBlock.NORTH)).setValue(FenceBlock.EAST, previousBlockState.get(FenceBlock.EAST)).setValue(FenceBlock.WEST, previousBlockState.get(FenceBlock.WEST)).setValue(FenceBlock.SOUTH, previousBlockState.get(FenceBlock.SOUTH));
        }
        else if (nextBlock instanceof FenceGateBlock && previousBlock instanceof FenceGateBlock)
        {
            return nextBlock.getDefaultState().with(FenceGateBlock.FACING, previousBlockState.get(FenceGateBlock.FACING)).setValue(FenceGateBlock.OPEN, previousBlockState.get(FenceGateBlock.OPEN)).setValue(FenceGateBlock.POWERED, previousBlockState.get(FenceGateBlock.POWERED)).setValue(FenceGateBlock.IN_WALL, previousBlockState.get(FenceGateBlock.IN_WALL));
        }
        else if (nextBlock instanceof RotatedPillarBlock && previousBlock instanceof RotatedPillarBlock)
        {
            return nextBlock.getDefaultState().with(RotatedPillarBlock.AXIS, previousBlockState.get(RotatedPillarBlock.AXIS));
        }
        else if (nextBlock instanceof TrapDoorBlock && previousBlock instanceof TrapDoorBlock)
        {
            return nextBlock.getDefaultState().with(TrapDoorBlock.OPEN, previousBlockState.get(TrapDoorBlock.OPEN)).setValue(TrapDoorBlock.HALF, previousBlockState.get(TrapDoorBlock.HALF)).setValue(TrapDoorBlock.POWERED, previousBlockState.get(TrapDoorBlock.POWERED)).setValue(TrapDoorBlock.FACING, previousBlockState.get(TrapDoorBlock.FACING));
        }
        else if (nextBlock instanceof DoorBlock && previousBlock instanceof DoorBlock)
        {
            return nextBlock.getDefaultState().with(DoorBlock.FACING, previousBlockState.get(DoorBlock.FACING)).setValue(DoorBlock.OPEN, previousBlockState.get(DoorBlock.OPEN)).setValue(DoorBlock.HINGE, previousBlockState.get(DoorBlock.HINGE)).setValue(DoorBlock.POWERED, previousBlockState.get(DoorBlock.POWERED)).setValue(DoorBlock.HALF, previousBlockState.get(DoorBlock.HALF));
        }
        else if (nextBlock instanceof ButtonBlock && previousBlock instanceof ButtonBlock)
        {
            return nextBlock.getDefaultState().with(ButtonBlock.POWERED, previousBlockState.get(ButtonBlock.POWERED)).setValue(ButtonBlock.FACE, previousBlockState.get(ButtonBlock.FACE)).setValue(ButtonBlock.FACING, previousBlockState.get(ButtonBlock.FACING));
        }
        else if (nextBlock instanceof PressurePlateBlock && previousBlock instanceof PressurePlateBlock)
        {
            return nextBlock.getDefaultState().with(PressurePlateBlock.POWERED, previousBlockState.get(PressurePlateBlock.POWERED));
        }
        else if (nextBlock instanceof ComposterBlock && previousBlock instanceof ComposterBlock)
        {
            return nextBlock.getDefaultState().with(ComposterBlock.LEVEL, previousBlockState.get(ComposterBlock.LEVEL));
        }
        else if (nextBlock instanceof LadderBlock && previousBlock instanceof LadderBlock)
        {
            return nextBlock.getDefaultState().with(LadderBlock.FACING, previousBlockState.get(LadderBlock.FACING)).setValue(LadderBlock.WATERLOGGED, previousBlockState.get(LadderBlock.WATERLOGGED));
        }
        else if ((nextBlock instanceof WallTorchBlock || nextBlock instanceof AerialHellWallTorchBlock) && (previousBlock instanceof WallTorchBlock || previousBlock instanceof AerialHellWallTorchBlock))
        {
            return nextBlock.getDefaultState().with(nextBlock instanceof WallTorchBlock ? WallTorchBlock.FACING : AerialHellWallTorchBlock.HORIZONTAL_FACING, previousBlockState.get(previousBlock instanceof WallTorchBlock ? WallTorchBlock.FACING : AerialHellWallTorchBlock.HORIZONTAL_FACING));
        }
        else if (nextBlock instanceof StandingSignBlock && previousBlock instanceof StandingSignBlock)
        {
            return nextBlock.getDefaultState().with(StandingSignBlock.ROTATION, previousBlockState.get(StandingSignBlock.ROTATION)).setValue(StandingSignBlock.WATERLOGGED, previousBlockState.get(StandingSignBlock.WATERLOGGED));
        }
        else if (nextBlock instanceof WallSignBlock && previousBlock instanceof WallSignBlock)
        {
            return nextBlock.getDefaultState().with(WallSignBlock.FACING, previousBlockState.get(WallSignBlock.FACING)).setValue(WallSignBlock.WATERLOGGED, previousBlockState.get(WallSignBlock.WATERLOGGED));
        }
        else if (nextBlock instanceof BarrelBlock && previousBlock instanceof BarrelBlock)
        {
            return nextBlock.getDefaultState().with(BarrelBlock.FACING, previousBlockState.get(BarrelBlock.FACING)).setValue(BarrelBlock.OPEN, previousBlockState.get(BarrelBlock.OPEN));
        }
        else if (nextBlock instanceof ChestBlock && previousBlock instanceof ChestBlock)
        {
            return nextBlock.getDefaultState().with(ChestBlock.FACING, previousBlockState.get(ChestBlock.FACING)).setValue(ChestBlock.TYPE, previousBlockState.get(ChestBlock.TYPE)).setValue(ChestBlock.WATERLOGGED, previousBlockState.get(ChestBlock.WATERLOGGED));
        }
        else
        {
            return nextBlock.getDefaultState();
        }
    }
}
