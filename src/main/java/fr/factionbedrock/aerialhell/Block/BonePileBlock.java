package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.ShadowPineBarrelMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ShadowSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ShadowTrollEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import java.util.Random;

public class BonePileBlock extends SnowBlock
{
    private static final int MAX_WAIT_TIMER = 10;
    public static final IntegerProperty WALK_DESTROY_TIMER = IntegerProperty.create("walk_destroy_timer", 0, MAX_WAIT_TIMER);
    public BonePileBlock(Properties properties)
    {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(WALK_DESTROY_TIMER, Integer.valueOf(0)).with(LAYERS, Integer.valueOf(1)));
    }

    @Override protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {builder.add(WALK_DESTROY_TIMER, LAYERS);}

    private int getRandomWalkDestroyTimer(Random rand) {return (int) (0.25 * MAX_WAIT_TIMER) + rand.nextInt((int) (0.75F * MAX_WAIT_TIMER));}

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        BlockState blockstate = worldIn.getBlockState(pos.down());
        if (!blockstate.isIn(Blocks.BARRIER)) {
            if (!blockstate.isIn(Blocks.HONEY_BLOCK) && !blockstate.isIn(Blocks.SOUL_SAND)) {
                return Block.doesSideFillSquare(blockstate.getCollisionShape(worldIn, pos.down()), Direction.UP) || blockstate.getBlock() == this && blockstate.get(LAYERS) == 8;
            } else {return true;}
        } else {return false;}
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random)
    {/* unused yet, decreasing the "timer" only when player walks is great
        BlockState blockState = world.getBlockState(pos);
        if (!world.isRemote() && blockState.get(WALK_DESTROY_TIMER) > 0 && world.rand.nextInt(10) == 0)
        {
            world.setBlockState(pos, world.getBlockState(pos).with(LAYERS, blockState.get(LAYERS)).with(WALK_DESTROY_TIMER, blockState.get(WALK_DESTROY_TIMER) - 1));
        }
    */}

    @Override public boolean ticksRandomly(BlockState state) {/*return state.get(WALK_DESTROY_TIMER) > 0;*/return false;}

    @Override
    public void onEntityWalk(World world, BlockPos pos, Entity entityIn)
    {
        if (canEntityWalkDestroy(world, pos, entityIn))
        {
            boolean downBlockStateIsBonePile = isBonePileBlockState(world, pos.down());
            boolean topBlockStateIsBonePile = isBonePileBlockState(world, pos.up());
            BlockPos posToUpdate = pos;
            if (topBlockStateIsBonePile) {posToUpdate = pos.up(); downBlockStateIsBonePile=true;}
            int currentLayerNumber = world.getBlockState(posToUpdate).get(LAYERS);
            int newLayerNumber = getNewLayerNumber(currentLayerNumber, downBlockStateIsBonePile, world.rand);
            updateLayerNumber(world, posToUpdate, newLayerNumber, entityIn);

        }
    }

    private void updateLayerNumber(World world, BlockPos pos, int newLayerNumber, Entity entityIn)
    {
        if (newLayerNumber > 0)
        {
            if (!world.isRemote()) {world.setBlockState(pos, world.getBlockState(pos).with(LAYERS, newLayerNumber).with(WALK_DESTROY_TIMER, getRandomWalkDestroyTimer(world.rand)));}
        }
        else
        {
            if (!world.isRemote) {world.setBlockState(pos, Blocks.AIR.getDefaultState());}
        }
        if (!world.isRemote) {if (newLayerNumber < 3 || world.rand.nextInt(3) == 0) {entityIn.entityDropItem(new ItemStack(AerialHellBlocksAndItems.MUD_BONE.get()));}}
        else {entityIn.playSound(AerialHellSoundEvents.BLOCK_BONE_PILE_STEP_BREAK.get(), 0.5F, 0.9F + world.rand.nextFloat() * 0.3F);}
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
            if (!isShadowEntity(walkingLEntity) && !isFeatheryEntity(walkingLEntity))
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

    private boolean isBonePileBlockState(World world, BlockPos pos) {return world.getBlockState(pos).isIn(this);}

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

    private boolean isShadowEntity(LivingEntity livingEntityIn)
    {
        if (
                livingEntityIn instanceof ShadowTrollEntity
                || livingEntityIn instanceof ShadowSpiderEntity
                || livingEntityIn instanceof ShadowPineBarrelMimicEntity
                || livingEntityIn instanceof TornSpiritEntity
                || livingEntityIn instanceof LilithEntity
            ) {return true;}
        return false;
    }

    private boolean isFeatheryEntity(LivingEntity livingEntityIn)
    {
        if (
                livingEntityIn instanceof SilverfishEntity
                || livingEntityIn instanceof FlyingEntity
           ) {return true;}
        return false;
    }
}
