package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BlowpipeItem extends Item
{
	private float arrowVelocity;
	
    public BlowpipeItem(Item.Settings settings, float arrowVelocity)
    {
        super(settings);
        this.arrowVelocity = arrowVelocity;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        boolean isCreative = user.getAbilities().creativeMode;
        ItemStack heldItem = user.getStackInHand(hand);
        ItemStack ammo = this.findAmmo(user);
        if(ammo.isEmpty() && !isCreative)
        {
        	return TypedActionResult.fail(heldItem);
        }

        if (!world.isClient())
        {
            AerialArrowItem arrowItem;
            if(isCreative && ammo.isEmpty())
            {
                arrowItem = (AerialArrowItem) AerialHellItems.RUBY_BLOWPIPE_ARROW;
            }
            else
            {
                arrowItem = (AerialArrowItem) ammo.getItem();
            }
            AbstractAerialArrowEntity arrow = arrowItem.createArrow(world, ammo, user);
            if (arrowItem == AerialHellItems.VOLUCITE_BLOWPIPE_ARROW) {arrow.setNoGravity(true);}
            
            arrow.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, arrowVelocity, 1.0F);
            if (isCreative)
            {
                arrow.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
            }
            else
            {
                arrow.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
            }
            world.spawnEntity(arrow);
        }
        world.playSound(user, user.getX(), user.getY(), user.getZ(), AerialHellSoundEvents.ENTITY_VOLUCITE_BLOWPIPE_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (user.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!isCreative)
        {
            ammo.decrement(1);
            if (ammo.isEmpty()) {user.getInventory().removeOne(ammo);}
            heldItem.damage(1, user, LivingEntity.getSlotForHand(hand));
        }
        user.getItemCooldownManager().set(this, 12);
        return TypedActionResult.consume(heldItem);
    }

    private ItemStack findAmmo(PlayerEntity player) //copy of player.getProjectileType but adapted to blowpipe use
    {
        Inventory inv = player.getInventory();
        for (int i = 0; i < inv.size(); i++)
        {
            ItemStack stack = inv.getStack(i);
            if (stack == ItemStack.EMPTY) {continue;}
            else {if(stack.isIn(AerialHellTags.Items.BLOWPIPE_ARROWS)) {return stack;}}
        }
        return ItemStack.EMPTY;
    }
}