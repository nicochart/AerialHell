package fr.factionbedrock.aerialhell.Item.Bucket;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;

public class RubyLiquidOfGodsBucketItem extends Item
{
	public RubyLiquidOfGodsBucketItem(Properties properties)
    {
        super(properties);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, RayTraceContext.FluidMode.NONE);
        if (raytraceresult.getType() == RayTraceResult.Type.MISS)
        {
            return ActionResult.resultPass(itemstack);
        }
        else if (raytraceresult.getType() != RayTraceResult.Type.BLOCK)
        {
            return ActionResult.resultPass(itemstack);
        }
        else
        {
            BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)raytraceresult;
            BlockPos blockpos = blockraytraceresult.getPos();
            Direction direction = blockraytraceresult.getFace();
            BlockPos blockpos1 = blockpos.offset(direction);
            if (worldIn.isBlockModifiable(playerIn, blockpos) && playerIn.canPlayerEdit(blockpos1, direction, itemstack))
            {
                if (this.tryPlaceContainedLiquid(playerIn, worldIn, blockpos1, blockraytraceresult))
                {
                    return ActionResult.func_233538_a_(!playerIn.abilities.isCreativeMode ? new ItemStack(AerialHellBlocksAndItems.RUBY_BUCKET.get()) : itemstack, worldIn.isRemote());
                }
                else
                {
                    return ActionResult.resultFail(itemstack);
                }
            }
            else
            {
                return ActionResult.resultFail(itemstack);
            }
        }
    }

    public boolean tryPlaceContainedLiquid(@Nullable PlayerEntity player, World worldIn, BlockPos posIn, @Nullable BlockRayTraceResult rayTrace)
    {
        BlockState blockstate = worldIn.getBlockState(posIn);
        Material material = blockstate.getMaterial();
        if (!(blockstate.isAir() || blockstate.isReplaceable(Fluids.WATER)))
        {
            return rayTrace != null && this.tryPlaceContainedLiquid(player, worldIn, rayTrace.getPos().offset(rayTrace.getFace()), (BlockRayTraceResult)null);
        }
        else
        {
            if (!worldIn.isRemote && blockstate.isReplaceable(Fluids.WATER) && !material.isLiquid())
            {
                worldIn.destroyBlock(posIn, true);
            }

            if (!worldIn.setBlockState(posIn, AerialHellBlocksAndItems.LIQUID_OF_THE_GODS.get().getDefaultState().getBlockState(), 11) && !blockstate.getFluidState().isSource())
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

    protected void playEmptySound(@Nullable PlayerEntity player, IWorld worldIn, BlockPos pos)
    {
        SoundEvent soundevent = Fluids.LAVA.getAttributes().getEmptySound();
        worldIn.playSound(player, pos, soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }
}