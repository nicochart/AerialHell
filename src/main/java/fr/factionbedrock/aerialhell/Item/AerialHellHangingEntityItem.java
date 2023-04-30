package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Entity.AerialHellPaintingEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

/* Copy of net.minecraft.item.HangingEntityItem but for Aerial Hell paintings */

public class AerialHellHangingEntityItem extends Item
{
    private final EntityType<? extends HangingEntity> hangingEntity;

    public AerialHellHangingEntityItem(EntityType<? extends HangingEntity> entityTypeIn, Item.Properties properties)
    {
        super(properties);
        this.hangingEntity = entityTypeIn;
    }

    public InteractionResult useOn(UseOnContext context)
    {
        BlockPos blockpos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        BlockPos blockpos1 = blockpos.relative(direction);
        Player playerentity = context.getPlayer();
        ItemStack itemstack = context.getItemInHand();
        if (playerentity != null && !this.canPlace(playerentity, direction, itemstack, blockpos1)) {return InteractionResult.FAIL;}
        else
        {
            Level world = context.getLevel();
            HangingEntity hangingentity;
            if (this.hangingEntity == AerialHellEntities.AERIAL_HELL_PAINTING.get())
            {
                hangingentity = new AerialHellPaintingEntity(world, blockpos1, direction);
                CompoundTag compoundnbt = itemstack.getTag();
                if (compoundnbt != null)
                {
                    EntityType.updateCustomEntityTag(world, playerentity, hangingentity, compoundnbt);
                }
                if (hangingentity.survives())
                {
                    if (!world.isClientSide())
                    {
                        hangingentity.playPlacementSound();
                        world.addFreshEntity(hangingentity);
                    }
                    itemstack.shrink(1);
                    return InteractionResult.sidedSuccess(world.isClientSide());
                }
            }
            return InteractionResult.CONSUME;
        }
    }

    protected boolean canPlace(Player playerIn, Direction directionIn, ItemStack itemStackIn, BlockPos posIn)
    {
        return !directionIn.getAxis().isVertical() && playerIn.mayUseItemAt(posIn, directionIn, itemStackIn);
    }
}
