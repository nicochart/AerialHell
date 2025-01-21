package fr.factionbedrock.aerialhell.Item.Bucket;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellFluids;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class RubyBucketItem extends Item
{
    public RubyBucketItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public InteractionResult use(Level worldIn, Player playerIn, InteractionHand handIn)
    {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.SOURCE_ONLY);
        if (blockhitresult.getType() != HitResult.Type.BLOCK)
        {
            return InteractionResult.PASS;
        }
        else
        {
            BlockPos blockpos = blockhitresult.getBlockPos();
            Direction direction = blockhitresult.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);
            if (worldIn.mayInteract(playerIn, blockpos) && playerIn.mayUseItemAt(blockpos1, direction, itemstack))
            {
                BlockState blockstate1 = worldIn.getBlockState(blockpos);
                if (blockstate1.getBlock() instanceof BucketPickup)
                {
                    //BucketPickup bucketpickup = (BucketPickup)blockstate1.getBlock();
                    Fluid fluid = worldIn.getFluidState(blockpos).getType();
                    if (fluid == Fluids.WATER)
                    {
                        playPickupSound(fluid, playerIn);
                        worldIn.setBlockAndUpdate(blockpos, Blocks.AIR.defaultBlockState());
                        ItemStack afterPickupHandItemStack = this.fillBucket(itemstack, playerIn, new ItemStack(AerialHellItems.RUBY_WATER_BUCKET.get()));
                        return InteractionResult.SUCCESS;
                    }
                    else if (fluid == AerialHellFluids.LIQUID_OF_THE_GODS_SOURCE.get())
                    {
                        playPickupSound(fluid, playerIn);
                        worldIn.setBlockAndUpdate(blockpos, Blocks.AIR.defaultBlockState());
                        ItemStack afterPickupHandItemStack = this.fillBucket(itemstack, playerIn, new ItemStack(AerialHellItems.RUBY_LIQUID_OF_GODS_BUCKET.get()));
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        return InteractionResult.FAIL;
    }

    public ItemStack fillBucket(ItemStack emptyBucket, Player player, ItemStack filledBucket)
    {
        boolean creative = player.isCreative();
        if (creative)
        {
            player.getInventory().add(filledBucket);
            return emptyBucket;
        }
        else
        {
            emptyBucket.shrink(1);
            if (emptyBucket.isEmpty())
            {
                return filledBucket;
            }
            else
            {
                if (!player.getInventory().add(filledBucket)) {player.drop(filledBucket, false);}
                return emptyBucket;
            }
        }
    }

    private void playPickupSound(Fluid fluid, Player player)
    {
        SoundEvent soundevent = SoundEvents.BUCKET_FILL;
        player.playSound(soundevent, 1.0F, 1.0F);
    }
}