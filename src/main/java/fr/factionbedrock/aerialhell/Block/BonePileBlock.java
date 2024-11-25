package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class BonePileBlock extends SnowBlock
{
    private static final int MAX_WAIT_TIMER = 10;
    public static final IntProperty WALK_DESTROY_TIMER = IntProperty.of("walk_destroy_timer", 0, MAX_WAIT_TIMER);
    public BonePileBlock(AbstractBlock.Settings settings)
    {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(WALK_DESTROY_TIMER, Integer.valueOf(0)).with(LAYERS, Integer.valueOf(1)));
    }

    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {builder.add(WALK_DESTROY_TIMER, LAYERS);}

    private int getRandomWalkDestroyTimer(Random rand) {return (int) (0.25 * MAX_WAIT_TIMER) + rand.nextInt((int) (0.75F * MAX_WAIT_TIMER));}

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos)
    {
        BlockState blockstate = world.getBlockState(pos.down());
        if (!blockstate.isOf(Blocks.BARRIER)) {
            if (!blockstate.isOf(Blocks.HONEY_BLOCK) && !blockstate.isOf(Blocks.SOUL_SAND)) {
                return Block.isFaceFullSquare(blockstate.getCollisionShape(world, pos.down()), Direction.UP) || blockstate.getBlock() == this && blockstate.get(LAYERS) == 8;
            } else {return true;}
        } else {return false;}
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {/* unused yet, decreasing the "timer" only when player walks is great
        BlockState blockState = world.getBlockState(pos);
        if (!world.isClient() && blockState.get(WALK_DESTROY_TIMER) > 0 && world.random.nextInt(10) == 0)
        {
            world.setBlockState(pos, world.getBlockState(pos).with(LAYERS, blockState.get(LAYERS)).with(WALK_DESTROY_TIMER, blockState.get(WALK_DESTROY_TIMER) - 1));
        }
    */}

    @Override public boolean hasRandomTicks(BlockState state) {/*return state.get(WALK_DESTROY_TIMER) > 0;*/return false;}

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity)
    {
        if (canEntityWalkDestroy(world, pos, entity))
        {
            boolean downBlockStateIsBonePile = isBonePileBlockState(world, pos.down());
            boolean topBlockStateIsBonePile = isBonePileBlockState(world, pos.up());
            BlockPos posToUpdate = pos;
            if (topBlockStateIsBonePile) {posToUpdate = pos.up(); downBlockStateIsBonePile=true;}
            int currentLayerNumber = world.getBlockState(posToUpdate).get(LAYERS);
            int newLayerNumber = getNewLayerNumber(currentLayerNumber, downBlockStateIsBonePile, world.random);
            updateLayerNumber(world, posToUpdate, newLayerNumber, entity);

        }
    }

    private void updateLayerNumber(World world, BlockPos pos, int newLayerNumber, Entity entityIn)
    {
        if (newLayerNumber > 0)
        {
            if (!world.isClient()) {world.setBlockState(pos, world.getBlockState(pos).with(LAYERS, newLayerNumber).with(WALK_DESTROY_TIMER, getRandomWalkDestroyTimer(world.random)));}
        }
        else
        {
            if (!world.isClient()) {world.setBlockState(pos, Blocks.AIR.getDefaultState());}
        }
        if (!world.isClient()) {if (newLayerNumber < 2 || world.random.nextInt(4) == 0) {entityIn.dropStack(new ItemStack(AerialHellItems.MUD_BONE));}}
        else {entityIn.playSound(AerialHellSoundEvents.BLOCK_BONE_PILE_STEP_BREAK, 0.5F, 0.9F + world.random.nextFloat() * 0.3F);}
    }

    private boolean canEntityWalkDestroy(World world, BlockPos pos, Entity walkingEntity)
    {
        BlockState blockState = world.getBlockState(pos);
        if (blockState.get(WALK_DESTROY_TIMER) > 0)
        {
            world.setBlockState(pos, world.getBlockState(pos).with(LAYERS, blockState.get(LAYERS)).with(WALK_DESTROY_TIMER, blockState.get(WALK_DESTROY_TIMER) - 1));
            return false;
        }
        else if (walkingEntity instanceof LivingEntity)
        {
            LivingEntity walkingLEntity = (LivingEntity)walkingEntity;
            if (!EntityHelper.isShadowEntity(walkingLEntity) && !EntityHelper.isFeatheryEntity(walkingLEntity))
            {
                boolean topBlockStateIsBonePile = isBonePileBlockState(world, pos.up());
                boolean posBlockStateIsBonePile = isBonePileBlockState(world, pos);
                boolean downBlockStateIsBonePile = isBonePileBlockState(world, pos.down());
                int posLayerNumber = posBlockStateIsBonePile ? world.getBlockState(pos).get(LAYERS) : 0;
                int topLayerNumber = topBlockStateIsBonePile ? world.getBlockState(pos.up()).get(LAYERS) : 0;
                if (!(topBlockStateIsBonePile && topLayerNumber > 2) && (posLayerNumber > 1 || downBlockStateIsBonePile)) {return true;}
            }
        }
        return false;
    }

    private boolean isBonePileBlockState(World world, BlockPos pos) {return world.getBlockState(pos).isOf(this);}

    private int getNewLayerNumber(int currentLayerNumber, boolean downBlockStateIsBonePile, Random rand)
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
