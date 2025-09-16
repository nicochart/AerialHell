package fr.factionbedrock.aerialhell.Item.Bucket;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
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
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class RubyLiquidOfGodsBucketItem extends Item
{
	public RubyLiquidOfGodsBucketItem(Item.Settings settings)
    {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack itemstack = user.getStackInHand(hand);
        BlockHitResult blockhitresult = raycast(world, user, RaycastContext.FluidHandling.NONE);
        if (blockhitresult.getType() == HitResult.Type.MISS)
        {
            return ActionResult.PASS;
        }
        else if (blockhitresult.getType() != HitResult.Type.BLOCK)
        {
            return ActionResult.PASS;
        }
        else
        {
            BlockPos blockpos = blockhitresult.getBlockPos();
            Direction direction = blockhitresult.getSide();
            BlockPos blockpos1 = blockpos.offset(direction);
            if (world.canEntityModifyAt(user, blockpos) && user.canPlaceOn(blockpos1, direction, itemstack))
            {
                if (this.tryPlaceContainedLiquid(user, world, blockpos1, blockhitresult))
                {
                    return user.isInCreativeMode() ? ActionResult.SUCCESS : ActionResult.SUCCESS.withNewHandStack(new ItemStack(AerialHellItems.RUBY_BUCKET));
                }
                else
                {
                    return ActionResult.FAIL;
                }
            }
            else
            {
                return ActionResult.FAIL;
            }
        }
    }

    public boolean tryPlaceContainedLiquid(@Nullable PlayerEntity player, World world, BlockPos posIn, @Nullable BlockHitResult rayTrace)
    {
        BlockState blockstate = world.getBlockState(posIn);
        if (!(blockstate.isAir() || blockstate.canBucketPlace(Fluids.WATER)))
        {
            return rayTrace != null && this.tryPlaceContainedLiquid(player, world, rayTrace.getBlockPos().offset(rayTrace.getSide()), (BlockHitResult)null);
        }
        else
        {
            if (!world.isClient() && blockstate.canBucketPlace(Fluids.WATER) && !blockstate.isLiquid())
            {
                world.breakBlock(posIn, true);
            }
            if (!world.setBlockState(posIn, AerialHellBlocks.LIQUID_OF_THE_GODS.getDefaultState(), 11) && !blockstate.getFluidState().isStill())
            {
                return false;
            }
            else
            {
                this.playEmptySound(player, world, posIn);
                return true;
            }
        }
    }

    protected void playEmptySound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos)
    {
        SoundEvent soundevent = SoundEvents.ITEM_BUCKET_EMPTY_LAVA;
        world.playSound(player, pos, soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}