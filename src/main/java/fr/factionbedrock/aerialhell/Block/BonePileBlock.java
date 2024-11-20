package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import java.util.Random;

public class BonePileBlock extends SnowBlock
{
    private static final int MAX_WAIT_TIMER = 10;
    public static final IntegerProperty WALK_DESTROY_TIMER = IntegerProperty.create("walk_destroy_timer", 0, MAX_WAIT_TIMER);
    public BonePileBlock(AbstractBlock.Settings settings)
    {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(WALK_DESTROY_TIMER, Integer.valueOf(0)).setValue(LAYERS, Integer.valueOf(1)));
    }

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(WALK_DESTROY_TIMER, LAYERS);}

    private int getRandomWalkDestroyTimer(RandomSource rand) {return (int) (0.25 * MAX_WAIT_TIMER) + rand.nextInt((int) (0.75F * MAX_WAIT_TIMER));}

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
    {
        BlockState blockstate = worldIn.getBlockState(pos.below());
        if (!blockstate.is(Blocks.BARRIER)) {
            if (!blockstate.is(Blocks.HONEY_BLOCK) && !blockstate.is(Blocks.SOUL_SAND)) {
                return Block.isFaceFull(blockstate.getCollisionShape(worldIn, pos.below()), Direction.UP) || blockstate.getBlock() == this && blockstate.getValue(LAYERS) == 8;
            } else {return true;}
        } else {return false;}
    }

    @Override
    public void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random)
    {/* unused yet, decreasing the "timer" only when player walks is great
        BlockState blockState = world.getBlockState(pos);
        if (!world.isClientSide() && blockState.get(WALK_DESTROY_TIMER) > 0 && world.random.nextInt(10) == 0)
        {
            world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(LAYERS, blockState.getValue(LAYERS)).setValue(WALK_DESTROY_TIMER, blockState.getValue(WALK_DESTROY_TIMER) - 1));
        }
    */}

    @Override public boolean isRandomlyTicking(BlockState state) {/*return state.getValue(WALK_DESTROY_TIMER) > 0;*/return false;}

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entityIn)
    {
        if (canEntityWalkDestroy(world, pos, entityIn))
        {
            boolean downBlockStateIsBonePile = isBonePileBlockState(world, pos.below());
            boolean topBlockStateIsBonePile = isBonePileBlockState(world, pos.above());
            BlockPos posToUpdate = pos;
            if (topBlockStateIsBonePile) {posToUpdate = pos.above(); downBlockStateIsBonePile=true;}
            int currentLayerNumber = world.getBlockState(posToUpdate).getValue(LAYERS);
            int newLayerNumber = getNewLayerNumber(currentLayerNumber, downBlockStateIsBonePile, world.random);
            updateLayerNumber(world, posToUpdate, newLayerNumber, entityIn);

        }
    }

    private void updateLayerNumber(Level world, BlockPos pos, int newLayerNumber, Entity entityIn)
    {
        if (newLayerNumber > 0)
        {
            if (!world.isClientSide()) {world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(LAYERS, newLayerNumber).setValue(WALK_DESTROY_TIMER, getRandomWalkDestroyTimer(world.random)));}
        }
        else
        {
            if (!world.isClientSide()) {world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());}
        }
        if (!world.isClientSide()) {if (newLayerNumber < 2 || world.random.nextInt(4) == 0) {entityIn.spawnAtLocation(new ItemStack(AerialHellBlocksAndItems.MUD_BONE.get()));}}
        else {entityIn.playSound(AerialHellSoundEvents.BLOCK_BONE_PILE_STEP_BREAK.get(), 0.5F, 0.9F + world.random.nextFloat() * 0.3F);}
    }

    private boolean canEntityWalkDestroy(Level world, BlockPos pos, Entity walkingEntity)
    {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.getValue(WALK_DESTROY_TIMER) > 0)
        {
            world.setBlockAndUpdate(pos, world.getBlockState(pos).setValue(LAYERS, blockState.getValue(LAYERS)).setValue(WALK_DESTROY_TIMER, blockState.getValue(WALK_DESTROY_TIMER) - 1));
            return false;
        }
        else if (walkingEntity instanceof LivingEntity)
        {
            LivingEntity walkingLEntity = (LivingEntity)walkingEntity;
            if (!EntityHelper.isShadowEntity(walkingLEntity) && !EntityHelper.isFeatheryEntity(walkingLEntity))
            {
                boolean topBlockStateIsBonePile = isBonePileBlockState(world, pos.above());
                boolean posBlockStateIsBonePile = isBonePileBlockState(world, pos);
                boolean downBlockStateIsBonePile = isBonePileBlockState(world, pos.below());
                int posLayerNumber = posBlockStateIsBonePile ? world.getBlockState(pos).getValue(LAYERS) : 0;
                int topLayerNumber = topBlockStateIsBonePile ? world.getBlockState(pos.above()).getValue(LAYERS) : 0;
                if (!(topBlockStateIsBonePile && topLayerNumber > 2) && (posLayerNumber > 1 || downBlockStateIsBonePile)) {return true;}
            }
        }
        return false;
    }

    private boolean isBonePileBlockState(Level world, BlockPos pos) {return world.getBlockState(pos).is(this);}

    private int getNewLayerNumber(int currentLayerNumber, boolean downBlockStateIsBonePile, RandomSource rand)
    {
        if (downBlockStateIsBonePile)
        {
            if (currentLayerNumber > 3)
            {
                int newLayerNumber = currentLayerNumber - 2 + rand.nextInt(3);
                if (newLayerNumber < 3 && newLayerNumber != 0) {newLayerNumber = 3;}
                return newLayerNumber;
            }
            else {return 0;}
        }
        else
        {
            if (currentLayerNumber > 3) {return currentLayerNumber - (1 + rand.nextInt(2));}
            else {return 1;}
        }
    }
}
