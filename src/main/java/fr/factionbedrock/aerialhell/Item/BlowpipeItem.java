package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BlowpipeItem extends Item
{
	private float arrowVelocity;
	
    public BlowpipeItem(Item.Properties settings, float arrowVelocity)
    {
        super(settings);
        this.arrowVelocity = arrowVelocity;
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand)
    {
        boolean isCreative = user.getAbilities().instabuild;
        ItemStack heldItem = user.getItemInHand(hand);
        ItemStack ammo = this.findAmmo(user);
        if(ammo.isEmpty() && !isCreative)
        {
        	return InteractionResult.FAIL;
        }

        if (!world.isClientSide())
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
            
            arrow.shootFromRotation(user, user.getXRot(), user.getYRot(), 0.0F, arrowVelocity, 1.0F);
            if (isCreative)
            {
                arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            }
            else
            {
                arrow.pickup = AbstractArrow.Pickup.ALLOWED;
            }
            world.addFreshEntity(arrow);
        }
        world.playSound(user, user.getX(), user.getY(), user.getZ(), AerialHellSoundEvents.ENTITY_VOLUCITE_BLOWPIPE_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (user.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!isCreative)
        {
            ammo.shrink(1);
            if (ammo.isEmpty()) {user.getInventory().removeItem(ammo);}
            heldItem.hurtAndBreak(1, user, hand);
        }
        user.getCooldowns().addCooldown(user.getItemInHand(hand), 12);
        return InteractionResult.CONSUME;
    }

    private ItemStack findAmmo(Player player) //copy of player.getProjectileType but adapted to blowpipe use
    {
        Container inv = player.getInventory();
        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            ItemStack stack = inv.getItem(i);
            if (stack == ItemStack.EMPTY) {continue;}
            else {if(stack.is(AerialHellTags.Items.BLOWPIPE_ARROWS)) {return stack;}}
        }
        return ItemStack.EMPTY;
    }
}