package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class StellarCropBlock extends CropBlock
{
    public StellarCropBlock(BlockBehaviour.Properties settings) {super(settings);}

    @Override protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {return floor.is(AerialHellBlocks.STELLAR_FARMLAND);}

    @Override protected ItemLike getBaseSeedId() {return this == AerialHellBlocks.STELLAR_WHEAT ? AerialHellItems.STELLAR_WHEAT_SEEDS : AerialHellItems.BLUE_MEANIE_SPORES;}

    @Override protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random)
    {
        if (world.getRawBrightness(pos, 0) >= 9)
        {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                float f = getGrowthSpeed(this, world, pos);
                if (random.nextInt((int)(25.0F / f) + 1) == 0) {world.setBlock(pos, this.getStateForAge(i + 1), Block.UPDATE_CLIENTS);}
            }
        }
    }

    //copy of net.minecraft.block.CropBlock method of same name, edited to clarify
    protected static float getGrowthSpeed(Block block, BlockGetter world, BlockPos pos)
    {
        float moisture = 1.0F;
        BlockPos blockPos = pos.below();

        for (int x = -1; x <= 1; x++)
        {
            for (int z = -1; z <= 1; z++)
            {
                float bonusMoisture = 0.0F;
                BlockState blockState = world.getBlockState(blockPos.offset(x, 0, z));
                if (blockState.is(AerialHellBlocks.STELLAR_FARMLAND))
                {
                    bonusMoisture = 1.0F;
                    if (blockState.getValue(FarmlandBlock.MOISTURE) > 0) {bonusMoisture = 3.0F;}
                }

                if (x != 0 || z != 0) {bonusMoisture /= 4.0F;}
                moisture += bonusMoisture;
            }
        }

        boolean northIsSameBlock = world.getBlockState(pos.north()).is(block);
        boolean southIsSameBlock = world.getBlockState(pos.south()).is(block);
        boolean westIsSameBlock = world.getBlockState(pos.west()).is(block);
        boolean eastIsSameBlock = world.getBlockState(pos.east()).is(block);
        boolean westOrEastIsSameBlock = westIsSameBlock || eastIsSameBlock;
        boolean northOrSouthIsSameBlock = northIsSameBlock || southIsSameBlock;
        if (westOrEastIsSameBlock && northOrSouthIsSameBlock) {moisture /= 2.0F;}
        else
        {
            boolean northWestIsSameBlock = world.getBlockState(pos.north().west()).is(block);
            boolean southWestIsSameBlock = world.getBlockState(pos.south().west()).is(block);
            boolean northEastIsSameBlock = world.getBlockState(pos.north().east()).is(block);
            boolean southEastIsSameBlock = world.getBlockState(pos.south().east()).is(block);
            boolean sameBlockInAnyCorner = northWestIsSameBlock || northEastIsSameBlock || southEastIsSameBlock || southWestIsSameBlock;
            if (sameBlockInAnyCorner) {moisture /= 2.0F;}
        }

        return moisture;
    }
}
