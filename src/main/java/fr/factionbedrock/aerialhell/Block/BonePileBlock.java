package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.ShadowPineBarrelMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ShadowSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ShadowTrollEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import java.util.Random;

public class BonePileBlock extends SnowBlock
{
    public BonePileBlock(Properties properties) {super(properties);}

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
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {}

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
        if (!world.isRemote())
        {
            if (newLayerNumber > 0) {world.setBlockState(pos, world.getBlockState(pos).with(LAYERS, newLayerNumber));}
            else {world.setBlockState(pos, Blocks.AIR.getDefaultState());}
        }
        entityIn.playSound(SoundEvents.BLOCK_BONE_BLOCK_BREAK, 0.2F, 0.9F + world.rand.nextFloat() * 0.1F);
        entityIn.entityDropItem(new ItemStack(AerialHellBlocksAndItems.MUD_BONE.get()));
    }

    private boolean canEntityWalkDestroy(World world, BlockPos pos, Entity walkingEntity)
    {
        if (walkingEntity instanceof LivingEntity)
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
            if (currentLayerNumber > 3) {return currentLayerNumber - 1 + rand.nextInt(2);}
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
