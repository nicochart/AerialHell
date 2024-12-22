package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChorusFlowerBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class ChorusFlowerLikeBlock extends ChorusFlowerBlock
{
    public ChorusFlowerLikeBlock(ChorusPlantLikeBlock plantBlock, AbstractBlock.Settings settings) {super(plantBlock, settings);}

    @Override public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
    {
        BlockState belowstate = world.getBlockState(pos.down());
        if (belowstate.isOf(Blocks.END_STONE)) {return false;}
        if (belowstate.isIn(AerialHellTags.Blocks.STELLAR_DIRT)) {return true;}
        else {return super.canPlaceAt(state, world, pos);}
    }

    @Override public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand)
    {
        BlockPos blockpos = pos.up();
        if (world.isAir(blockpos) && blockpos.getY() < world.getTopY())
        {
            int i = state.get(AGE);
            if (i < 5)
            {
                boolean flag = false;
                boolean flag1 = false;
                BlockState blockstate = world.getBlockState(pos.down());
                if (isValidGround(blockstate)) {flag = true;}
                else if (blockstate.isOf(this.plantBlock))
                {
                    int j = 1;

                    for(int k = 0; k < 4; ++k)
                    {
                        BlockState blockstate1 = world.getBlockState(pos.down(j + 1));
                        if (!blockstate1.isOf(this.plantBlock))
                        {
                            if (isValidGround(blockstate1)) {flag1 = true;}
                            break;
                        }
                        ++j;
                    }

                    if (j < 2 || j <= rand.nextInt(flag1 ? 5 : 4)) {flag = true;}
                }
                else if (blockstate.isAir()) {flag = true;}

                if (flag && allNeighborsEmpty(world, blockpos, (Direction)null) && world.isAir(pos.up(2)))
                {
                    world.setBlockState(pos, ChorusPlantLikeBlock.getStateForPlacement(world, pos, this.plantBlock.getDefaultState()), 2);
                    this.placeGrownFlower(world, blockpos, i);
                }
                else if (i < 4)
                {
                    int l = rand.nextInt(4);
                    if (flag1) {++l;}

                    boolean flag2 = false;

                    for(int i1 = 0; i1 < l; ++i1)
                    {
                        Direction direction = Direction.Type.HORIZONTAL.random(rand);
                        BlockPos blockpos1 = pos.offset(direction);
                        if (world.isAir(blockpos1) && world.isAir(blockpos1.down()) && allNeighborsEmpty(world, blockpos1, direction.getOpposite()))
                        {
                            this.placeGrownFlower(world, blockpos1, i + 1);
                            flag2 = true;
                        }
                    }

                    if (flag2) {world.setBlockState(pos, ChorusPlantLikeBlock.getStateForPlacement(world, pos, this.plantBlock.getDefaultState()), 2);}
                    else {this.placeDeadFlower(world, pos);}
                }
                else {this.placeDeadFlower(world, pos);}
            }
        }
    }

    private boolean isValidGround(BlockState state) {return state.isIn(AerialHellTags.Blocks.STELLAR_DIRT);}

    private void placeGrownFlower(World world, BlockPos pos, int p_51664_)
    {
        world.setBlockState(pos, this.getDefaultState().with(AGE, Integer.valueOf(p_51664_)), 2);
        world.syncWorldEvent(WorldEvents.CHORUS_FLOWER_GROWS, pos, 0);
    }

    private void placeDeadFlower(ServerWorld world, BlockPos pos)
    {
        world.setBlockState(pos, this.getDefaultState().with(AGE, Integer.valueOf(5)), 2);
        world.syncWorldEvent(WorldEvents.CHORUS_FLOWER_DIES, pos, 0);
    }

    private static boolean allNeighborsEmpty(WorldView world, BlockPos pos, @Nullable Direction directionIn)
    {
        for(Direction direction : Direction.Type.HORIZONTAL)
        {
            if (direction != directionIn && !world.isAir(pos.offset(direction))) {return false;}
        }
        return true;
    }

    public static void generatePlant(WorldAccess world, BlockPos pos, Random rand, int size)
    {
        world.setBlockState(pos, ChorusPlantLikeBlock.getStateForPlacement(world, pos, AerialHellBlocks.FULL_MOON_PLANT.getDefaultState()), 2);
        growTreeRecursive(world, pos, rand, pos, size, 0);
    }

    private static void growTreeRecursive(WorldAccess world, BlockPos pos1, Random rand, BlockPos pos2, int size, int iteration)
    {
        ChorusPlantLikeBlock plantBlock = AerialHellBlocks.FULL_MOON_PLANT;
        int i = rand.nextInt(4) + 1;
        if (iteration == 0) {++i;}

        for(int j = 0; j < i; ++j)
        {
            BlockPos blockpos = pos1.up(j + 1);
            if (!allNeighborsEmpty(world, blockpos, null)) {return;}
            world.setBlockState(blockpos, ChorusPlantLikeBlock.getStateForPlacement(world, blockpos, plantBlock.getDefaultState()), 2);
            world.setBlockState(blockpos.down(), ChorusPlantLikeBlock.getStateForPlacement(world, blockpos.down(), plantBlock.getDefaultState()), 2);
        }

        boolean flag = false;
        if (iteration < 4)
        {
            int l = rand.nextInt(4);
            if (iteration == 0) {++l;}

            for(int k = 0; k < l; ++k)
            {
                Direction direction = Direction.Type.HORIZONTAL.random(rand);
                BlockPos blockpos1 = pos1.up(i).offset(direction);
                if (Math.abs(blockpos1.getX() - pos2.getX()) < size && Math.abs(blockpos1.getZ() - pos2.getZ()) < size && world.isAir(blockpos1) && world.isAir(blockpos1.down()) && allNeighborsEmpty(world, blockpos1, direction.getOpposite()))
                {
                    flag = true;
                    world.setBlockState(blockpos1, ChorusPlantLikeBlock.getStateForPlacement(world, blockpos1, plantBlock.getDefaultState()), 2);
                    world.setBlockState(blockpos1.offset(direction.getOpposite()), ChorusPlantLikeBlock.getStateForPlacement(world, blockpos1.offset(direction.getOpposite()), plantBlock.getDefaultState()), 2);
                    growTreeRecursive(world, blockpos1, rand, pos2, size, iteration + 1);
                }
            }
        }
        if (!flag) {world.setBlockState(pos1.up(i), AerialHellBlocks.FULL_MOON_FLOWER.getDefaultState().with(AGE, Integer.valueOf(5)), 2);}
    }
}
