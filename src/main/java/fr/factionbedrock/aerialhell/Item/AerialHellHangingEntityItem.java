package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Entity.AerialHellPaintingEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

/* Copy of net.minecraft.item.HangingEntityItem but for Aerial Hell paintings */

public class AerialHellHangingEntityItem extends Item
{
    public AerialHellHangingEntityItem(Item.Properties properties)
    {
        super(properties);
    }

    public InteractionResult useOn(UseOnContext context)
    {
        /*BlockPos blockpos = context.getClickedPos(); TODO
        Direction direction = context.getClickedFace();
        BlockPos blockpos1 = blockpos.relative(direction);
        Player playerentity = context.getPlayer();
        ItemStack itemstack = context.getItemInHand();
        if (playerentity != null && !this.canPlace(playerentity, direction, itemstack, blockpos1)) {return InteractionResult.FAIL;}
        else
        {
            Level world = context.getLevel();
            HangingEntity painting = new AerialHellPaintingEntity(world, blockpos1, direction);
            CompoundTag compoundnbt = itemstack.getTag();
            if (compoundnbt != null)
            {
                EntityType.updateCustomEntityTag(world, playerentity, painting, compoundnbt);
            }
            if (painting.survives())
            {
                if (!world.isClientSide())
                {
                    painting.playPlacementSound();
                    world.addFreshEntity(painting);
                }
                itemstack.shrink(1);
                return InteractionResult.sidedSuccess(world.isClientSide());
            }
            return InteractionResult.CONSUME;
        }*/ return InteractionResult.FAIL;
    }

    protected boolean canPlace(Player playerIn, Direction directionIn, ItemStack itemStackIn, BlockPos posIn)
    {
        return !directionIn.getAxis().isVertical() && playerIn.mayUseItemAt(posIn, directionIn, itemStackIn);
    }
}
