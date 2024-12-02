package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Block.StandingAndWall.AerialHellWallTorchBlock;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.Optional;

public class BlockUpdaterItem extends WithInformationItem
{
    public BlockUpdaterItem(Item.Settings settings) {super(settings);}

    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)
    {
        BlockPos origin = entity.getBlockPos();
        if (selected) {this.replaceBlocks(world, origin);}
    }

    protected void replaceBlocks(World world, BlockPos origin)
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
                    if (setPos.getSquaredDistance(origin) < range * range && world.getBlockState(setPos).isIn(AerialHellTags.Blocks.REPLACE_IN))
                    {
                        this.replaceBlock(world, setPos);
                    }
                }
            }
        }
    }

    protected void replaceBlock(World world, BlockPos pos)
    {
        Optional<RegistryEntryList.Named<Block>> OptionalInRegistryEntry = Registries.BLOCK.getEntryList(AerialHellTags.Blocks.REPLACE_IN);
        Optional<RegistryEntryList.Named<Block>> OptionalOutRegistryEntry = Registries.BLOCK.getEntryList(AerialHellTags.Blocks.REPLACE_OUT);
        if (OptionalInRegistryEntry.isEmpty() || OptionalOutRegistryEntry.isEmpty()) {return;}

        Iterator<RegistryEntry<Block>> replace_in = OptionalInRegistryEntry.get().iterator();
        Iterator<RegistryEntry<Block>> replace_out = OptionalOutRegistryEntry.get().iterator();


        BlockState previousBlockState = world.getBlockState(pos);
        Block previousBlock = null, nextBlock = null;

        while(previousBlockState.getBlock() != previousBlock)
        {
            if (!replace_in.hasNext() || !replace_out.hasNext()) {return;}
            previousBlock = replace_in.next().value();
            nextBlock = replace_out.next().value();
        }

        BlockState nextBlockState = this.getNextBlockState(previousBlockState, nextBlock);
        BlockEntity previousBlockEntity = world.getBlockEntity(pos);
        if (previousBlockEntity != null) {world.removeBlockEntity(pos);}
        world.setBlockState(pos, nextBlockState);
        if (previousBlock instanceof BlockWithEntity && nextBlock instanceof BlockWithEntity && previousBlockEntity != null)
        {
            world.addBlockEntity(previousBlockEntity);
        }
    }

    protected BlockState getNextBlockState(BlockState previousBlockState, Block nextBlock)
    {
        Block previousBlock = previousBlockState.getBlock();
        if (nextBlock instanceof LeavesBlock && previousBlock instanceof LeavesBlock)
        {
            return nextBlock.getDefaultState().with(LeavesBlock.DISTANCE, previousBlockState.get(LeavesBlock.DISTANCE)).with(LeavesBlock.PERSISTENT, previousBlockState.get(LeavesBlock.PERSISTENT));
        }
        else if (nextBlock instanceof StairsBlock && previousBlock instanceof StairsBlock)
        {
            return nextBlock.getDefaultState().with(StairsBlock.FACING, previousBlockState.get(StairsBlock.FACING)).with(StairsBlock.HALF, previousBlockState.get(StairsBlock.HALF)).with(StairsBlock.SHAPE, previousBlockState.get(StairsBlock.SHAPE));
        }
        else if (nextBlock instanceof SlabBlock && previousBlock instanceof SlabBlock)
        {
            return nextBlock.getDefaultState().with(SlabBlock.TYPE, previousBlockState.get(SlabBlock.TYPE));
        }
        else if (nextBlock instanceof WallBlock && previousBlock instanceof WallBlock)
        {
            return nextBlock.getDefaultState().with(WallBlock.UP, previousBlockState.get(WallBlock.UP)).with(WallBlock.NORTH_SHAPE, previousBlockState.get(WallBlock.NORTH_SHAPE)).with(WallBlock.SOUTH_SHAPE, previousBlockState.get(WallBlock.SOUTH_SHAPE)).with(WallBlock.WEST_SHAPE, previousBlockState.get(WallBlock.WEST_SHAPE)).with(WallBlock.EAST_SHAPE, previousBlockState.get(WallBlock.EAST_SHAPE)).with(WallBlock.WATERLOGGED, previousBlockState.get(WallBlock.WATERLOGGED));
        }
        else if (nextBlock instanceof FenceBlock && previousBlock instanceof FenceBlock)
        {
            return nextBlock.getDefaultState().with(FenceBlock.NORTH, previousBlockState.get(FenceBlock.NORTH)).with(FenceBlock.EAST, previousBlockState.get(FenceBlock.EAST)).with(FenceBlock.WEST, previousBlockState.get(FenceBlock.WEST)).with(FenceBlock.SOUTH, previousBlockState.get(FenceBlock.SOUTH));
        }
        else if (nextBlock instanceof FenceGateBlock && previousBlock instanceof FenceGateBlock)
        {
            return nextBlock.getDefaultState().with(FenceGateBlock.FACING, previousBlockState.get(FenceGateBlock.FACING)).with(FenceGateBlock.OPEN, previousBlockState.get(FenceGateBlock.OPEN)).with(FenceGateBlock.POWERED, previousBlockState.get(FenceGateBlock.POWERED)).with(FenceGateBlock.IN_WALL, previousBlockState.get(FenceGateBlock.IN_WALL));
        }
        else if (nextBlock instanceof PillarBlock && previousBlock instanceof PillarBlock)
        {
            return nextBlock.getDefaultState().with(PillarBlock.AXIS, previousBlockState.get(PillarBlock.AXIS));
        }
        else if (nextBlock instanceof TrapdoorBlock && previousBlock instanceof TrapdoorBlock)
        {
            return nextBlock.getDefaultState().with(TrapdoorBlock.OPEN, previousBlockState.get(TrapdoorBlock.OPEN)).with(TrapdoorBlock.HALF, previousBlockState.get(TrapdoorBlock.HALF)).with(TrapdoorBlock.POWERED, previousBlockState.get(TrapdoorBlock.POWERED)).with(TrapdoorBlock.FACING, previousBlockState.get(TrapdoorBlock.FACING));
        }
        else if (nextBlock instanceof DoorBlock && previousBlock instanceof DoorBlock)
        {
            return nextBlock.getDefaultState().with(DoorBlock.FACING, previousBlockState.get(DoorBlock.FACING)).with(DoorBlock.OPEN, previousBlockState.get(DoorBlock.OPEN)).with(DoorBlock.HINGE, previousBlockState.get(DoorBlock.HINGE)).with(DoorBlock.POWERED, previousBlockState.get(DoorBlock.POWERED)).with(DoorBlock.HALF, previousBlockState.get(DoorBlock.HALF));
        }
        else if (nextBlock instanceof ButtonBlock && previousBlock instanceof ButtonBlock)
        {
            return nextBlock.getDefaultState().with(ButtonBlock.POWERED, previousBlockState.get(ButtonBlock.POWERED)).with(ButtonBlock.FACE, previousBlockState.get(ButtonBlock.FACE)).with(ButtonBlock.FACING, previousBlockState.get(ButtonBlock.FACING));
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
            return nextBlock.getDefaultState().with(LadderBlock.FACING, previousBlockState.get(LadderBlock.FACING)).with(LadderBlock.WATERLOGGED, previousBlockState.get(LadderBlock.WATERLOGGED));
        }
        else if ((nextBlock instanceof WallTorchBlock || nextBlock instanceof AerialHellWallTorchBlock) && (previousBlock instanceof WallTorchBlock || previousBlock instanceof AerialHellWallTorchBlock))
        {
            return nextBlock.getDefaultState().with(nextBlock instanceof WallTorchBlock ? WallTorchBlock.FACING : AerialHellWallTorchBlock.HORIZONTAL_FACING, previousBlockState.get(previousBlock instanceof WallTorchBlock ? WallTorchBlock.FACING : AerialHellWallTorchBlock.HORIZONTAL_FACING));
        }
        else if (nextBlock instanceof SignBlock && previousBlock instanceof SignBlock)
        {
            return nextBlock.getDefaultState().with(SignBlock.ROTATION, previousBlockState.get(SignBlock.ROTATION)).with(SignBlock.WATERLOGGED, previousBlockState.get(SignBlock.WATERLOGGED));
        }
        else if (nextBlock instanceof WallSignBlock && previousBlock instanceof WallSignBlock)
        {
            return nextBlock.getDefaultState().with(WallSignBlock.FACING, previousBlockState.get(WallSignBlock.FACING)).with(WallSignBlock.WATERLOGGED, previousBlockState.get(WallSignBlock.WATERLOGGED));
        }
        else if (nextBlock instanceof BarrelBlock && previousBlock instanceof BarrelBlock)
        {
            return nextBlock.getDefaultState().with(BarrelBlock.FACING, previousBlockState.get(BarrelBlock.FACING)).with(BarrelBlock.OPEN, previousBlockState.get(BarrelBlock.OPEN));
        }
        else if (nextBlock instanceof ChestBlock && previousBlock instanceof ChestBlock)
        {
            return nextBlock.getDefaultState().with(ChestBlock.FACING, previousBlockState.get(ChestBlock.FACING)).with(ChestBlock.CHEST_TYPE, previousBlockState.get(ChestBlock.CHEST_TYPE)).with(ChestBlock.WATERLOGGED, previousBlockState.get(ChestBlock.WATERLOGGED));
        }
        else
        {
            return nextBlock.getDefaultState();
        }
    }
}
