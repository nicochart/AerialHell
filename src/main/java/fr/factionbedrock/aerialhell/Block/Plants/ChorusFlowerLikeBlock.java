package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
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
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ChorusFlowerLikeBlock extends ChorusFlowerBlock
{
    public ChorusFlowerLikeBlock(ChorusPlantLikeBlock plantBlock, BlockBehaviour.Properties settings) {super(plantBlock, settings);}

    @Override public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
    {
        BlockState belowstate = world.getBlockState(pos.below());
        if (belowstate.is(Blocks.END_STONE)) {return false;}
        if (belowstate.is(AerialHellTags.Blocks.STELLAR_DIRT)) {return true;}
        else {return super.canSurvive(state, world, pos);}
    }

    @Override public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rand)
    {
        BlockPos blockpos = pos.above();
        if (world.isEmptyBlock(blockpos) && blockpos.getY() < world.getMaxY())
        {
            int i = state.getValue(AGE);
            if (i < 5)
            {
                boolean flag = false;
                boolean flag1 = false;
                BlockState blockstate = world.getBlockState(pos.below());
                if (isValidGround(blockstate)) {flag = true;}
                else if (blockstate.is(this.plant))
                {
                    int j = 1;

                    for(int k = 0; k < 4; ++k)
                    {
                        BlockState blockstate1 = world.getBlockState(pos.below(j + 1));
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

                if (flag && allNeighborsEmpty(world, blockpos, (Direction)null) && world.isEmptyBlock(pos.above(2)))
                {
                    world.setBlock(pos, ChorusPlantLikeBlock.getStateForPlacement(world, pos, this.plant.defaultBlockState()), 2);
                    this.placeGrownFlower(world, blockpos, i);
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
                        if (world.isEmptyBlock(blockpos1) && world.isEmptyBlock(blockpos1.below()) && allNeighborsEmpty(world, blockpos1, direction.getOpposite()))
                        {
                            this.placeGrownFlower(world, blockpos1, i + 1);
                            flag2 = true;
                        }
                    }

                    if (flag2) {world.setBlock(pos, ChorusPlantLikeBlock.getStateForPlacement(world, pos, this.plant.defaultBlockState()), 2);}
                    else {this.placeDeadFlower(world, pos);}
                }
                else {this.placeDeadFlower(world, pos);}
            }
        }
    }

    private boolean isValidGround(BlockState state) {return state.is(AerialHellTags.Blocks.STELLAR_DIRT);}

    private void placeGrownFlower(Level world, BlockPos pos, int p_51664_)
    {
        world.setBlock(pos, this.defaultBlockState().setValue(AGE, Integer.valueOf(p_51664_)), 2);
        world.levelEvent(LevelEvent.SOUND_CHORUS_GROW, pos, 0);
    }

    private void placeDeadFlower(ServerLevel world, BlockPos pos)
    {
        world.setBlock(pos, this.defaultBlockState().setValue(AGE, Integer.valueOf(5)), 2);
        world.levelEvent(LevelEvent.SOUND_CHORUS_DEATH, pos, 0);
    }

    private static boolean allNeighborsEmpty(LevelReader world, BlockPos pos, @Nullable Direction directionIn)
    {
        for(Direction direction : Direction.Plane.HORIZONTAL)
        {
            if (direction != directionIn && !world.isEmptyBlock(pos.relative(direction))) {return false;}
        }
        return true;
    }

    public static void generatePlant(LevelAccessor world, BlockPos pos, RandomSource rand, int size)
    {
        world.setBlock(pos, ChorusPlantLikeBlock.getStateForPlacement(world, pos, AerialHellBlocks.FULL_MOON_PLANT.defaultBlockState()), 2);
        growTreeRecursive(world, pos, rand, pos, size, 0);
    }

    private static void growTreeRecursive(LevelAccessor world, BlockPos pos1, RandomSource rand, BlockPos pos2, int size, int iteration)
    {
        ChorusPlantLikeBlock plantBlock = AerialHellBlocks.FULL_MOON_PLANT;
        int i = rand.nextInt(4) + 1;
        if (iteration == 0) {++i;}

        for(int j = 0; j < i; ++j)
        {
            BlockPos blockpos = pos1.above(j + 1);
            if (!allNeighborsEmpty(world, blockpos, null)) {return;}
            world.setBlock(blockpos, ChorusPlantLikeBlock.getStateForPlacement(world, blockpos, plantBlock.defaultBlockState()), 2);
            world.setBlock(blockpos.below(), ChorusPlantLikeBlock.getStateForPlacement(world, blockpos.below(), plantBlock.defaultBlockState()), 2);
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
                if (Math.abs(blockpos1.getX() - pos2.getX()) < size && Math.abs(blockpos1.getZ() - pos2.getZ()) < size && world.isEmptyBlock(blockpos1) && world.isEmptyBlock(blockpos1.below()) && allNeighborsEmpty(world, blockpos1, direction.getOpposite()))
                {
                    flag = true;
                    world.setBlock(blockpos1, ChorusPlantLikeBlock.getStateForPlacement(world, blockpos1, plantBlock.defaultBlockState()), 2);
                    world.setBlock(blockpos1.relative(direction.getOpposite()), ChorusPlantLikeBlock.getStateForPlacement(world, blockpos1.relative(direction.getOpposite()), plantBlock.defaultBlockState()), 2);
                    growTreeRecursive(world, blockpos1, rand, pos2, size, iteration + 1);
                }
            }
        }
        if (!flag) {world.setBlock(pos1.above(i), AerialHellBlocks.FULL_MOON_FLOWER.defaultBlockState().setValue(AGE, Integer.valueOf(5)), 2);}
    }
}
