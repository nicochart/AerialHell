package fr.factionbedrock.aerialhell.Item.Bucket;

import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.phys.HitResult;

public class RubyLiquidOfGodsBucketItem extends Item
{
	public RubyLiquidOfGodsBucketItem(Properties properties)
    {
        super(properties);
    }

    public InteractionResult use(Level worldIn, Player playerIn, InteractionHand handIn)
    {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(worldIn, playerIn, ClipContext.Fluid.NONE);
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
            if (worldIn.mayInteract(playerIn, blockpos) && playerIn.mayUseItemAt(blockpos1, direction, itemstack))
            {
                if (this.tryPlaceContainedLiquid(playerIn, worldIn, blockpos1, blockhitresult))
                {
                    return InteractionResult.SUCCESS;
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

    public boolean tryPlaceContainedLiquid(@Nullable Player player, Level worldIn, BlockPos posIn, @Nullable BlockHitResult rayTrace)
    {
        BlockState blockstate = worldIn.getBlockState(posIn);
        if (!(blockstate.isAir() || blockstate.canBeReplaced(Fluids.WATER)))
        {
            return rayTrace != null && this.tryPlaceContainedLiquid(player, worldIn, rayTrace.getBlockPos().relative(rayTrace.getDirection()), (BlockHitResult)null);
        }
        else
        {
            if (!worldIn.isClientSide() && blockstate.canBeReplaced(Fluids.WATER) && !blockstate.liquid())
            {
                worldIn.destroyBlock(posIn, true);
            }
            if (!worldIn.setBlock(posIn, AerialHellBlocksAndItems.LIQUID_OF_THE_GODS.get().defaultBlockState(), 11) && !blockstate.getFluidState().isSource())
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

    protected void playEmptySound(@Nullable Player player, LevelAccessor worldIn, BlockPos pos)
    {
        SoundEvent soundevent = SoundEvents.BUCKET_EMPTY_LAVA;
        worldIn.playSound(player, pos, soundevent, SoundSource.BLOCKS, 1.0F, 1.0F);
    }
}