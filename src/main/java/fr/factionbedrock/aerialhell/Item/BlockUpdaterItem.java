package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Block.StandingAndWall.AerialHellWallTorchBlock;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class BlockUpdaterItem extends WithInformationItem
{
    public BlockUpdaterItem(Properties prop) {super(prop);}

    @Override public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot)
    {
        BlockPos origin = entity.blockPosition();
        if (slot == EquipmentSlot.MAINHAND) {this.replaceBlocks(level, origin);}
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
        Iterator<Holder<Block>> replace_in = BuiltInRegistries.BLOCK.get(AerialHellTags.Blocks.REPLACE_IN).get().iterator();
        Iterator<Holder<Block>> replace_out = BuiltInRegistries.BLOCK.get(AerialHellTags.Blocks.REPLACE_OUT).get().iterator();

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
        level.setBlockAndUpdate(pos, nextBlockState);
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
            return nextBlock.defaultBlockState().setValue(LeavesBlock.DISTANCE, previousBlockState.getValue(LeavesBlock.DISTANCE)).setValue(LeavesBlock.PERSISTENT, previousBlockState.getValue(LeavesBlock.PERSISTENT));
        }
        else if (nextBlock instanceof StairBlock && previousBlock instanceof StairBlock)
        {
            return nextBlock.defaultBlockState().setValue(StairBlock.FACING, previousBlockState.getValue(StairBlock.FACING)).setValue(StairBlock.HALF, previousBlockState.getValue(StairBlock.HALF)).setValue(StairBlock.SHAPE, previousBlockState.getValue(StairBlock.SHAPE));
        }
        else if (nextBlock instanceof SlabBlock && previousBlock instanceof SlabBlock)
        {
            return nextBlock.defaultBlockState().setValue(SlabBlock.TYPE, previousBlockState.getValue(SlabBlock.TYPE));
        }
        else if (nextBlock instanceof WallBlock && previousBlock instanceof WallBlock)
        {
            return nextBlock.defaultBlockState().setValue(WallBlock.UP, previousBlockState.getValue(WallBlock.UP)).setValue(WallBlock.NORTH, previousBlockState.getValue(WallBlock.NORTH)).setValue(WallBlock.SOUTH, previousBlockState.getValue(WallBlock.SOUTH)).setValue(WallBlock.WEST, previousBlockState.getValue(WallBlock.WEST)).setValue(WallBlock.EAST, previousBlockState.getValue(WallBlock.EAST)).setValue(WallBlock.WATERLOGGED, previousBlockState.getValue(WallBlock.WATERLOGGED));
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
        else if (nextBlock instanceof BarrelBlock && previousBlock instanceof BarrelBlock)
        {
            return nextBlock.defaultBlockState().setValue(BarrelBlock.FACING, previousBlockState.getValue(BarrelBlock.FACING)).setValue(BarrelBlock.OPEN, previousBlockState.getValue(BarrelBlock.OPEN));
        }
        else if (nextBlock instanceof ChestBlock && previousBlock instanceof ChestBlock)
        {
            return nextBlock.defaultBlockState().setValue(ChestBlock.FACING, previousBlockState.getValue(ChestBlock.FACING)).setValue(ChestBlock.TYPE, previousBlockState.getValue(ChestBlock.TYPE)).setValue(ChestBlock.WATERLOGGED, previousBlockState.getValue(ChestBlock.WATERLOGGED));
        }
        else
        {
            return nextBlock.defaultBlockState();
        }
    }
}
