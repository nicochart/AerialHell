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
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;

public class BlowpipeItem extends Item
{
    public BlowpipeItem(Supplier<Item> ammo, Properties builder)
    {
        super(builder);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        boolean isCreative = playerIn.abilities.isCreativeMode;
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        ItemStack ammo = this.findAmmo(playerIn);
        if(ammo.isEmpty() && !isCreative)
        {
        	return ActionResult.resultFail(heldItem);
        }

        if (!worldIn.isRemote)
        {
            AerialArrowItem arrowItem;
            if(isCreative && ammo.isEmpty())
            {
                arrowItem = (AerialArrowItem) AerialHellBlocksAndItems.RUBY_BLOWPIPE_ARROW.get();
            }
            else
            {
                arrowItem = (AerialArrowItem) ammo.getItem();
            }
            AbstractAerialArrowEntity arrow = arrowItem.createArrow(worldIn, ammo, playerIn);
            if (arrowItem == AerialHellBlocksAndItems.VOLUCITE_BLOWPIPE_ARROW.get()) {arrow.setNoGravity(true);}
            
            arrow.func_234612_a_(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.7F, 1.0F);
            if (isCreative)
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
        if (!isCreative)
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
                if(stack.getItem().isIn(AerialHellTags.Items.BLOWPIPE_ARROWS))
                {
                    return stack;
                }
            }
        }
        return ItemStack.EMPTY;
    }
}