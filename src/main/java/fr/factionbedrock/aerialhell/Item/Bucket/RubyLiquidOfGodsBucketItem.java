package fr.factionbedrock.aerialhell.Item.Bucket;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;

public class RubyLiquidOfGodsBucketItem extends Item
{
	public RubyLiquidOfGodsBucketItem(Item.Properties settings)
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
                if (this.tryPlaceContainedLiquid(user, world, blockpos1, blockhitresult))
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

    public boolean tryPlaceContainedLiquid(@Nullable Player player, Level world, BlockPos posIn, @Nullable BlockHitResult rayTrace)
    {
        BlockState blockstate = world.getBlockState(posIn);
        if (!(blockstate.isAir() || blockstate.canBeReplaced(Fluids.WATER)))
        {
            return rayTrace != null && this.tryPlaceContainedLiquid(player, world, rayTrace.getBlockPos().relative(rayTrace.getDirection()), (BlockHitResult)null);
        }
        else
        {
            if (!world.isClientSide() && blockstate.canBeReplaced(Fluids.WATER) && !blockstate.liquid())
            {
                world.destroyBlock(posIn, true);
            }
            if (!world.setBlock(posIn, AerialHellBlocks.LIQUID_OF_THE_GODS.defaultBlockState(), 11) && !blockstate.getFluidState().isSource())
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

    protected void playEmptySound(@Nullable Player player, LevelAccessor world, BlockPos pos)
    {
        SoundEvent soundevent = SoundEvents.BUCKET_EMPTY_LAVA;
        world.playSound(player, pos, soundevent, SoundSource.BLOCKS, 1.0F, 1.0F);
    }
}