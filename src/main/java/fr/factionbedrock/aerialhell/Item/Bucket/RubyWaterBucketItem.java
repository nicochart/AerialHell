package fr.factionbedrock.aerialhell.Item.Bucket;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidFillable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
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

public class RubyWaterBucketItem extends Item
{
    public RubyWaterBucketItem(Item.Settings settings)
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
            if (world.canPlayerModifyAt(user, blockpos) && user.canPlaceOn(blockpos1, direction, itemstack))
            {
                BlockState blockstate = world.getBlockState(blockpos);
                BlockPos blockpos2 = canBlockContainFluid(world, blockpos, blockstate) ? blockpos : blockpos1;
                if (this.tryPlaceContainedLiquid(user, world, blockpos2, blockhitresult))
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

    public boolean tryPlaceContainedLiquid(@Nullable PlayerEntity player, World world, BlockPos pos, @Nullable BlockHitResult rayTrace)
    {
        BlockState blockstate = world.getBlockState(pos);
        Block block = blockstate.getBlock();
        boolean flag = blockstate.canBucketPlace(Fluids.WATER);
        boolean flag1 = blockstate.isAir() || flag || block instanceof FluidFillable fluidFillableBlock && fluidFillableBlock.canFillWithFluid(player, world, pos, blockstate, Fluids.WATER);
        if (!flag1)
        {
            return rayTrace != null && this.tryPlaceContainedLiquid(player, world, rayTrace.getBlockPos().offset(rayTrace.getSide()), (BlockHitResult)null);
        }
        else if (world.getDimension().ultrawarm())
        {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);

            for(int l = 0; l < 8; ++l)
            {
                world.addParticle(ParticleTypes.LARGE_SMOKE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0D, 0.0D, 0.0D);
            }

            return true;
        }
        else if (block instanceof FluidFillable fluidFillableBlock && fluidFillableBlock.canFillWithFluid(player, world,pos,blockstate, Fluids.WATER))
        {
            fluidFillableBlock.tryFillWithFluid(world, pos, blockstate, Fluids.WATER.getStill(false));
            this.playEmptySound(player, world, pos);
            return true;
        }
        else
        {
            if (!world.isClient() && flag && !blockstate.isLiquid())
            {
                world.breakBlock(pos, true);
            }

            if (!world.setBlockState(pos, Fluids.WATER.getDefaultState().getBlockState(), 11) && !blockstate.getFluidState().isStill())
            {
                return false;
            }
            else
            {
                this.playEmptySound(player, world, pos);
                return true;
            }
        }
    }

    private boolean canBlockContainFluid(World world, BlockPos posIn, BlockState blockstate)
    {
        return blockstate.getBlock() instanceof FluidFillable fluidFillableBlock && fluidFillableBlock.canFillWithFluid(null, world, posIn, blockstate, Fluids.WATER);
    }

    protected void playEmptySound(@Nullable PlayerEntity player, WorldAccess world, BlockPos pos)
    {
        SoundEvent soundevent = SoundEvents.ITEM_BUCKET_EMPTY;
        world.playSound(player, pos, soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}