package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChorusFlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class ChorusFlowerLikeBlock extends ChorusFlowerBlock
{
    public ChorusFlowerLikeBlock(ChorusPlantLikeBlock plantBlock, Properties prop) {super(plantBlock, prop);}

    @Override public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
    {
        BlockState belowstate = level.getBlockState(pos.below());
        if (belowstate.is(Blocks.END_STONE)) {return false;}
        if (belowstate.is(AerialHellTags.Blocks.STELLAR_DIRT)) {return true;}
        else {return super.canSurvive(state, level, pos);}
    }

    @Override public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource rand)
    {
        BlockPos blockpos = pos.above();
        if (level.isEmptyBlock(blockpos) && blockpos.getY() < level.getMaxY())
        {
            int i = state.getValue(AGE);
            if (i < 5 && net.neoforged.neoforge.common.CommonHooks.canCropGrow(level, blockpos, state, true))
            {
                boolean flag = false;
                boolean flag1 = false;
                BlockState blockstate = level.getBlockState(pos.below());
                if (isValidGround(blockstate)) {flag = true;}
                else if (blockstate.is(this.plant))
                {
                    int j = 1;

                    for(int k = 0; k < 4; ++k)
                    {
                        BlockState blockstate1 = level.getBlockState(pos.below(j + 1));
                        if (!blockstate1.is(this.plant))
                        {
                            if (isValidGround(blockstate1)) {flag1 = true;}
                            break;
                        }
                        ++j;
                    }

                    if (j < 2 || j <= rand.nextInt(flag1 ? 5 : 4)) {flag = true;}
                }
                else if (blockstate.isAir()) {flag = true;}

                if (flag && allNeighborsEmpty(level, blockpos, (Direction)null) && level.isEmptyBlock(pos.above(2)))
                {
                    level.setBlock(pos, ChorusPlantLikeBlock.getStateWithConnections(level, pos, this.plant.defaultBlockState()), 2);
                    this.placeGrownFlower(level, blockpos, i);
                }
                else if (i < 4)
                {
                    int l = rand.nextInt(4);
                    if (flag1) {++l;}

                    boolean flag2 = false;

                    for(int i1 = 0; i1 < l; ++i1)
                    {
                        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
                        BlockPos blockpos1 = pos.relative(direction);
                        if (level.isEmptyBlock(blockpos1) && level.isEmptyBlock(blockpos1.below()) && allNeighborsEmpty(level, blockpos1, direction.getOpposite()))
                        {
                            this.placeGrownFlower(level, blockpos1, i + 1);
                            flag2 = true;
                        }
                    }

                    if (flag2) {level.setBlock(pos, ChorusPlantLikeBlock.getStateWithConnections(level, pos, this.plant.defaultBlockState()), 2);}
                    else {this.placeDeadFlower(level, pos);}
                }
                else {this.placeDeadFlower(level, pos);}
                net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(level, pos, state);
            }
        }
    }

    private boolean isValidGround(BlockState state) {return state.is(AerialHellTags.Blocks.STELLAR_DIRT);}

    private void placeGrownFlower(Level p_51662_, BlockPos p_51663_, int p_51664_) {
        p_51662_.setBlock(p_51663_, this.defaultBlockState().setValue(AGE, Integer.valueOf(p_51664_)), 2);
        p_51662_.levelEvent(1033, p_51663_, 0);
    }

    private void placeDeadFlower(Level p_51659_, BlockPos p_51660_) {
        p_51659_.setBlock(p_51660_, this.defaultBlockState().setValue(AGE, Integer.valueOf(5)), 2);
        p_51659_.levelEvent(1034, p_51660_, 0);
    }

    private static boolean allNeighborsEmpty(LevelReader level, BlockPos pos, @Nullable Direction directionIn)
    {
        for(Direction direction : Direction.Plane.HORIZONTAL)
        {
            if (direction != directionIn && !level.isEmptyBlock(pos.relative(direction))) {return false;}
        }
        return true;
    }

    public static void generatePlant(LevelAccessor level, BlockPos pos, RandomSource rand, int size)
    {
        level.setBlock(pos, ChorusPlantLikeBlock.getStateForPlacement(level, pos, AerialHellBlocksAndItems.FULL_MOON_PLANT.get().defaultBlockState()), 2);
        growTreeRecursive(level, pos, rand, pos, size, 0);
    }

    private static void growTreeRecursive(LevelAccessor level, BlockPos pos1, RandomSource rand, BlockPos pos2, int size, int iteration)
    {
        ChorusPlantLikeBlock plantBlock = AerialHellBlocksAndItems.FULL_MOON_PLANT.get();
        int i = rand.nextInt(4) + 1;
        if (iteration == 0) {++i;}

        for(int j = 0; j < i; ++j)
        {
            BlockPos blockpos = pos1.above(j + 1);
            if (!allNeighborsEmpty(level, blockpos, null)) {return;}
            level.setBlock(blockpos, ChorusPlantLikeBlock.getStateForPlacement(level, blockpos, plantBlock.defaultBlockState()), 2);
            level.setBlock(blockpos.below(), ChorusPlantLikeBlock.getStateForPlacement(level, blockpos.below(), plantBlock.defaultBlockState()), 2);
        }

        boolean flag = false;
        if (iteration < 4)
        {
            int l = rand.nextInt(4);
            if (iteration == 0) {++l;}

            for(int k = 0; k < l; ++k)
            {
                Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(rand);
                BlockPos blockpos1 = pos1.above(i).relative(direction);
                if (Math.abs(blockpos1.getX() - pos2.getX()) < size && Math.abs(blockpos1.getZ() - pos2.getZ()) < size && level.isEmptyBlock(blockpos1) && level.isEmptyBlock(blockpos1.below()) && allNeighborsEmpty(level, blockpos1, direction.getOpposite()))
                {
                    flag = true;
                    level.setBlock(blockpos1, ChorusPlantLikeBlock.getStateForPlacement(level, blockpos1, plantBlock.defaultBlockState()), 2);
                    level.setBlock(blockpos1.relative(direction.getOpposite()), ChorusPlantLikeBlock.getStateForPlacement(level, blockpos1.relative(direction.getOpposite()), plantBlock.defaultBlockState()), 2);
                    growTreeRecursive(level, blockpos1, rand, pos2, size, iteration + 1);
                }
            }
        }
        if (!flag) {level.setBlock(pos1.above(i), AerialHellBlocksAndItems.FULL_MOON_FLOWER.get().defaultBlockState().setValue(AGE, Integer.valueOf(5)), 2);}
    }
}
