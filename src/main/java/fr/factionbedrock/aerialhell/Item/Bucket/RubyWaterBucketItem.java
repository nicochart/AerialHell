package fr.factionbedrock.aerialhell.Item.Bucket;

import net.minecraft.core.Direction;
import net.minecraft.item.Item;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.phys.HitResult;

public class RubyWaterBucketItem extends Item
{
    public RubyWaterBucketItem(Item.Settings settings)
    {
        super(settings);
    }

    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn)
    {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.NONE);
        if (blockhitresult.getType() == HitResult.Type.MISS)
        {
            return InteractionResultHolder.pass(itemstack);
        }
        else if (blockhitresult.getType() != HitResult.Type.BLOCK)
        {
            return InteractionResultHolder.pass(itemstack);
        }
        else
        {
            BlockPos blockpos = blockhitresult.getBlockPos();
            Direction direction = blockhitresult.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);
            if (worldIn.mayInteract(playerIn, blockpos) && playerIn.mayUseItemAt(blockpos1, direction, itemstack))
            {
                BlockState blockstate = worldIn.getBlockState(blockpos);
                BlockPos blockpos2 = canBlockContainFluid(worldIn, blockpos, blockstate) ? blockpos : blockpos1;
                if (this.tryPlaceContainedLiquid(playerIn, worldIn, blockpos2, blockhitresult))
                {
                    return InteractionResultHolder.sidedSuccess(!playerIn.getAbilities().instabuild ? new ItemStack(AerialHellBlocksAndItems.RUBY_BUCKET.get()) : itemstack, worldIn.isClientSide());
                }
                else
                {
                    return InteractionResultHolder.fail(itemstack);
                }
            }
            else
            {
                return InteractionResultHolder.fail(itemstack);
            }
        }
    }

    public boolean tryPlaceContainedLiquid(@Nullable Player player, Level worldIn, BlockPos posIn, @Nullable BlockHitResult rayTrace)
    {
        BlockState blockstate = worldIn.getBlockState(posIn);
        Block block = blockstate.getBlock();
        boolean flag = blockstate.canBeReplaced(Fluids.WATER);
        boolean flag1 = blockstate.isAir() || flag || block instanceof LiquidBlockContainer && ((LiquidBlockContainer)block).canPlaceLiquid(player, worldIn, posIn, blockstate, Fluids.WATER);
        if (!flag1)
        {
            return rayTrace != null && this.tryPlaceContainedLiquid(player, worldIn, rayTrace.getBlockPos().relative(rayTrace.getDirection()), (BlockHitResult)null);
        }
        else if (worldIn.dimensionType().ultraWarm())
        {
            int i = posIn.getX();
            int j = posIn.getY();
            int k = posIn.getZ();
            worldIn.playSound(player, posIn, SoundEvents.FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.random.nextFloat() - worldIn.random.nextFloat()) * 0.8F);

            for(int l = 0; l < 8; ++l)
            {
                worldIn.addParticle(ParticleTypes.LARGE_SMOKE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0D, 0.0D, 0.0D);
            }

            return true;
        }
        else if (block instanceof LiquidBlockContainer && ((LiquidBlockContainer)block).canPlaceLiquid(player, worldIn,posIn,blockstate, Fluids.WATER))
        {
            ((LiquidBlockContainer)block).placeLiquid(worldIn, posIn, blockstate, ((FlowingFluid)Fluids.WATER).getSource(false));
            this.playEmptySound(player, worldIn, posIn);
            return true;
        }
        else
        {
            if (!worldIn.isClientSide() && flag && !blockstate.liquid())
            {
                worldIn.destroyBlock(posIn, true);
            }

            if (!worldIn.setBlockState(posIn, Fluids.WATER.defaultFluidState().createLegacyBlock(), 11) && !blockstate.getFluidState().isSource())
            {
                return false;
            }
            else
            {
                this.playEmptySound(player, worldIn, posIn);
                return true;
            }
        }
    }

    private boolean canBlockContainFluid(Level worldIn, BlockPos posIn, BlockState blockstate)
    {
        return blockstate.getBlock() instanceof LiquidBlockContainer && ((LiquidBlockContainer)blockstate.getBlock()).canPlaceLiquid(null, worldIn, posIn, blockstate, Fluids.WATER);
    }

    protected void playEmptySound(@Nullable Player player, LevelAccessor worldIn, BlockPos pos)
    {
        SoundEvent soundevent = SoundEvents.BUCKET_EMPTY;
        worldIn.playSound(player, pos, soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}