package fr.factionbedrock.aerialhell.Item.Bucket;

import fr.factionbedrock.aerialhell.Registry.AerialHellFluids;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidDrainable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class RubyBucketItem extends Item
{
    public RubyBucketItem(Item.Settings settings)
    {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack itemstack = user.getStackInHand(hand);
        BlockHitResult blockhitresult = raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);
        if (blockhitresult.getType() != HitResult.Type.BLOCK)
        {
            return ActionResult.PASS;
        }
        else
        {
            BlockPos blockpos = blockhitresult.getBlockPos();
            Direction direction = blockhitresult.getSide();
            BlockPos blockpos1 = blockpos.offset(direction);
            if (world.canPlayerModifyAt(user, blockpos) && user.canPlaceOn(blockpos1, direction, itemstack))
            {
                BlockState blockstate1 = world.getBlockState(blockpos);
                if (blockstate1.getBlock() instanceof FluidDrainable)
                {
                    //BucketPickup bucketpickup = (BucketPickup)blockstate1.getBlock();
                    Fluid fluid = world.getFluidState(blockpos).getFluid();
                    if (fluid == Fluids.WATER)
                    {
                        playPickupSound(fluid, user);
                        world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                        ItemStack afterPickupHandItemStack = new ItemStack(AerialHellItems.RUBY_WATER_BUCKET);
                        return user.isCreative() ? ActionResult.SUCCESS : fillBucketFromStack(itemstack, user, afterPickupHandItemStack);
                    }
                    else if (fluid == AerialHellFluids.LIQUID_OF_THE_GODS_STILL)
                    {
                        playPickupSound(fluid, user);
                        world.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                        ItemStack afterPickupHandItemStack = new ItemStack(AerialHellItems.RUBY_LIQUID_OF_GODS_BUCKET);
                        return user.isCreative() ? ActionResult.SUCCESS : fillBucketFromStack(itemstack, user, afterPickupHandItemStack);
                    }
                }
            }
        }
        return ActionResult.FAIL;
    }

    public ActionResult fillBucketFromStack(ItemStack emptyBucket, PlayerEntity player, ItemStack filledBucket)
    {
        if (emptyBucket.getCount() > 1)
        {
            emptyBucket.decrement(1);
            if (!player.getInventory().insertStack(filledBucket)) {player.dropItem(filledBucket, false);}
            return ActionResult.SUCCESS;
        }
        else
        {
            return ActionResult.SUCCESS.withNewHandStack(filledBucket);
        }
    }

    private void playPickupSound(Fluid fluid, PlayerEntity player)
    {
        SoundEvent soundevent = SoundEvents.ITEM_BUCKET_FILL;
        player.playSound(soundevent, 1.0F, 1.0F);
    }
}