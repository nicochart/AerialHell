package fr.factionbedrock.aerialhell.Item.Bucket;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public class RubyWaterBucketItem extends Item
{
    public RubyWaterBucketItem(Item.Properties settings)
    {
        super(settings);
    }

    public InteractionResult use(Level world, Player user, InteractionHand hand)
    {
        ItemStack itemstack = user.getItemInHand(hand);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(world, user, ClipContext.Fluid.NONE);
        if (blockhitresult.getType() == HitResult.Type.MISS)
        {
            return InteractionResult.PASS;
        }
        else if (blockhitresult.getType() != HitResult.Type.BLOCK)
        {
            return InteractionResult.PASS;
        }
        else
        {
            BlockPos blockpos = blockhitresult.getBlockPos();
            Direction direction = blockhitresult.getDirection();
            BlockPos blockpos1 = blockpos.relative(direction);
            if (world.mayInteract(user, blockpos) && user.mayUseItemAt(blockpos1, direction, itemstack))
            {
                BlockState blockstate = world.getBlockState(blockpos);
                BlockPos blockpos2 = canBlockContainFluid(world, blockpos, blockstate) ? blockpos : blockpos1;
                if (this.tryPlaceContainedLiquid(user, world, blockpos2, blockhitresult))
                {
                    return user.hasInfiniteMaterials() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS.heldItemTransformedTo(new ItemStack(AerialHellItems.RUBY_BUCKET));
                }
                else
                {
                    return InteractionResult.FAIL;
                }
            }
            else
            {
                return InteractionResult.FAIL;
            }
        }
    }

    public boolean tryPlaceContainedLiquid(@Nullable Player player, Level world, BlockPos pos, @Nullable BlockHitResult rayTrace)
    {
        BlockState blockstate = world.getBlockState(pos);
        Block block = blockstate.getBlock();
        boolean flag = blockstate.canBeReplaced(Fluids.WATER);
        boolean flag1 = blockstate.isAir() || flag || block instanceof LiquidBlockContainer fluidFillableBlock && fluidFillableBlock.canPlaceLiquid(player, world, pos, blockstate, Fluids.WATER);
        if (!flag1)
        {
            return rayTrace != null && this.tryPlaceContainedLiquid(player, world, rayTrace.getBlockPos().relative(rayTrace.getDirection()), (BlockHitResult)null);
        }
        else if (world.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, pos))
        {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            world.playSound(player, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);

            for(int l = 0; l < 8; ++l)
            {
                world.addParticle(ParticleTypes.LARGE_SMOKE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0D, 0.0D, 0.0D);
            }

            return true;
        }
        else if (block instanceof LiquidBlockContainer fluidFillableBlock && fluidFillableBlock.canPlaceLiquid(player, world,pos,blockstate, Fluids.WATER))
        {
            fluidFillableBlock.placeLiquid(world, pos, blockstate, Fluids.WATER.getSource(false));
            this.playEmptySound(player, world, pos);
            return true;
        }
        else
        {
            if (!world.isClientSide() && flag && !blockstate.liquid())
            {
                world.destroyBlock(pos, true);
            }

            if (!world.setBlock(pos, Fluids.WATER.defaultFluidState().createLegacyBlock(), 11) && !blockstate.getFluidState().isSource())
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

    private boolean canBlockContainFluid(Level world, BlockPos posIn, BlockState blockstate)
    {
        return blockstate.getBlock() instanceof LiquidBlockContainer fluidFillableBlock && fluidFillableBlock.canPlaceLiquid(null, world, posIn, blockstate, Fluids.WATER);
    }

    protected void playEmptySound(@Nullable Player player, LevelAccessor world, BlockPos pos)
    {
        SoundEvent soundevent = SoundEvents.BUCKET_EMPTY;
        world.playSound(player, pos, soundevent, SoundSource.BLOCKS, 1.0F, 1.0F);
    }
}