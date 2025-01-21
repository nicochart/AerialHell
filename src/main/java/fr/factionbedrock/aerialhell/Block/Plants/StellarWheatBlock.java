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
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;

public class StellarWheatBlock extends CropBlock
{
    public StellarWheatBlock(Properties prop) {super(prop);}

    @Override protected boolean mayPlaceOn(BlockState state, BlockGetter blockGetter, BlockPos pos) {return state.is(AerialHellBlocks.STELLAR_FARMLAND.get());}

    @Override protected ItemLike getBaseSeedId() {return AerialHellItems.STELLAR_WHEAT_SEEDS.get();}

    @Override protected void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom)
    {
        if (!pLevel.isAreaLoaded(pPos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (pLevel.getRawBrightness(pPos, 0) >= 9)
        {
            int i = this.getAge(pState);
            if (i < this.getMaxAge())
            {
                float f = getGrowthSpeedFromMoisture(pState, pLevel, pPos);
                if (net.neoforged.neoforge.common.CommonHooks.canCropGrow(pLevel, pPos, pState, pRandom.nextInt((int)(25.0F / f) + 1) == 0))
                {
                    pLevel.setBlock(pPos, this.getStateForAge(i + 1), 2);
                    net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(pLevel, pPos, pState);
                }
            }
        }
    }

    //copy of net.minecraft.world.level.block.CropBlock getGrowthSpeed method, edited to clarify
    protected static float getGrowthSpeedFromMoisture(BlockState blockstate, BlockGetter world, BlockPos pos)
    {
        Block block = blockstate.getBlock();
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
                    if (blockState.getValue(FarmBlock.MOISTURE) > 0) {bonusMoisture = 3.0F;}
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
