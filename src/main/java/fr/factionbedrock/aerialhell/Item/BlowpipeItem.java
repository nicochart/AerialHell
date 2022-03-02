package fr.factionbedrock.aerialhell.Item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.function.Supplier;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;

public class BlowpipeItem extends Item
{
    protected final Supplier<Item> ammoType;
    public BlowpipeItem(Supplier<Item> ammo, Properties builder)
    {
        super(builder);
        this.ammoType = ammo;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        boolean flag = playerIn.abilities.isCreativeMode;
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        ItemStack ammo = this.findAmmo(playerIn);
        if(ammo.isEmpty() && !flag)
        {
        	return ActionResult.resultFail(heldItem);
        }

        if (!worldIn.isRemote)
        {
            AerialArrowItem arrowItem;
            if(flag && ammo.isEmpty())
            {
                arrowItem = (AerialArrowItem) ammoType.get();
            }
            else
            {
                arrowItem = (AerialArrowItem) ammo.getItem();
            }
            AbstractAerialArrowEntity arrow = arrowItem.createArrow(worldIn, ammo, playerIn);
            arrow.setNoGravity(true);
            arrow.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 1.0F);
            if (flag)
            {
                arrow.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
            }
            else
            {
                arrow.pickupStatus = AbstractArrowEntity.PickupStatus.ALLOWED;
            }
            worldIn.addEntity(arrow);
        }
        worldIn.playSound(playerIn, playerIn.getPosition(), AerialHellSoundEvents.ENTITY_VOLUCITE_BLOWPIPE_SHOOT.get(), SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 0.8F));
        if (!flag)
        {
            ammo.shrink(1);
            if (ammo.isEmpty())
            {
                playerIn.inventory.deleteStack(ammo);
            }
        }
        playerIn.getCooldownTracker().setCooldown(this, 10);
        return ActionResult.resultConsume(heldItem);
    }

    private ItemStack findAmmo(PlayerEntity player)
    {
        IInventory inv = player.inventory;
        for (int i = 0; i < inv.getSizeInventory(); i++)
        {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack == ItemStack.EMPTY)
            {
                continue;
            }
            else
            {
                if(stack.getItem() == this.ammoType.get())
                {
                    return stack;
                }
            }
        }
        return ItemStack.EMPTY;
    }
}