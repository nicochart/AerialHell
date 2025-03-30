package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;

public class StellarCropBlock extends CropBlock
{
    public StellarCropBlock(AbstractBlock.Settings settings) {super(settings);}

    @Override protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {return floor.isOf(AerialHellBlocks.STELLAR_FARMLAND);}

    @Override protected ItemConvertible getSeedsItem() {return this == AerialHellBlocks.STELLAR_WHEAT ? AerialHellItems.STELLAR_WHEAT_SEEDS : AerialHellItems.BLUE_MEANIE_SPORES;}

    @Override protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {
        if (world.getBaseLightLevel(pos, 0) >= 9)
        {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                float f = getAvailableMoisture(this, world, pos);
                if (random.nextInt((int)(25.0F / f) + 1) == 0) {world.setBlockState(pos, this.withAge(i + 1), Block.NOTIFY_LISTENERS);}
            }
        }
    }

    //copy of net.minecraft.block.CropBlock method of same name, edited to clarify
    protected static float getAvailableMoisture(Block block, BlockView world, BlockPos pos)
    {
        float moisture = 1.0F;
        BlockPos blockPos = pos.down();

        for (int x = -1; x <= 1; x++)
        {
            for (int z = -1; z <= 1; z++)
            {
                float bonusMoisture = 0.0F;
                BlockState blockState = world.getBlockState(blockPos.add(x, 0, z));
                if (blockState.isOf(AerialHellBlocks.STELLAR_FARMLAND))
                {
                    bonusMoisture = 1.0F;
                    if (blockState.get(FarmlandBlock.MOISTURE) > 0) {bonusMoisture = 3.0F;}
                }

                if (x != 0 || z != 0) {bonusMoisture /= 4.0F;}
                moisture += bonusMoisture;
            }
        }

        boolean northIsSameBlock = world.getBlockState(pos.north()).isOf(block);
        boolean southIsSameBlock = world.getBlockState(pos.south()).isOf(block);
        boolean westIsSameBlock = world.getBlockState(pos.west()).isOf(block);
        boolean eastIsSameBlock = world.getBlockState(pos.east()).isOf(block);
        boolean westOrEastIsSameBlock = westIsSameBlock || eastIsSameBlock;
        boolean northOrSouthIsSameBlock = northIsSameBlock || southIsSameBlock;
        if (westOrEastIsSameBlock && northOrSouthIsSameBlock) {moisture /= 2.0F;}
        else
        {
            boolean northWestIsSameBlock = world.getBlockState(pos.north().west()).isOf(block);
            boolean southWestIsSameBlock = world.getBlockState(pos.south().west()).isOf(block);
            boolean northEastIsSameBlock = world.getBlockState(pos.north().east()).isOf(block);
            boolean southEastIsSameBlock = world.getBlockState(pos.south().east()).isOf(block);
            boolean sameBlockInAnyCorner = northWestIsSameBlock || northEastIsSameBlock || southEastIsSameBlock || southWestIsSameBlock;
            if (sameBlockInAnyCorner) {moisture /= 2.0F;}
        }

        return moisture;
    }
}
