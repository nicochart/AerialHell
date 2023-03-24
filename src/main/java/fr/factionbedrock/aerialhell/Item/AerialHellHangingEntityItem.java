package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Entity.AerialHellPaintingEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.HangingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/* Copy of net.minecraft.item.HangingEntityItem but for Aerial Hell paintings */

public class AerialHellHangingEntityItem extends Item
{
    private final EntityType<? extends HangingEntity> hangingEntity;

    public AerialHellHangingEntityItem(EntityType<? extends HangingEntity> entityTypeIn, Item.Properties properties)
    {
        super(properties);
        this.hangingEntity = entityTypeIn;
    }

    public ActionResultType onItemUse(ItemUseContext context)
    {
        BlockPos blockpos = context.getPos();
        Direction direction = context.getFace();
        BlockPos blockpos1 = blockpos.offset(direction);
        PlayerEntity playerentity = context.getPlayer();
        ItemStack itemstack = context.getItem();
        if (playerentity != null && !this.canPlace(playerentity, direction, itemstack, blockpos1)) {return ActionResultType.FAIL;}
        else
        {
            World world = context.getWorld();
            HangingEntity hangingentity;
            if (this.hangingEntity == AerialHellEntities.AERIAL_HELL_PAINTING.get())
            {
                hangingentity = new AerialHellPaintingEntity(world, blockpos1, direction);
                CompoundNBT compoundnbt = itemstack.getTag();
                if (compoundnbt != null)
                {
                    EntityType.applyItemNBT(world, playerentity, hangingentity, compoundnbt);
                }
                if (hangingentity.onValidSurface())
                {
                    if (!world.isRemote)
                    {
                        hangingentity.playPlaceSound();
                        world.addEntity(hangingentity);
                    }
                    itemstack.shrink(1);
                    return ActionResultType.func_233537_a_(world.isRemote);
                }
            }
            return ActionResultType.CONSUME;
        }
    }

    protected boolean canPlace(PlayerEntity playerIn, Direction directionIn, ItemStack itemStackIn, BlockPos posIn)
    {
        return !directionIn.getAxis().isVertical() && playerIn.canPlayerEdit(posIn, directionIn, itemStackIn);
    }
}
