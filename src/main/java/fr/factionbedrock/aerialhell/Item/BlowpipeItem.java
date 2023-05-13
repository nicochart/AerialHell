package fr.factionbedrock.aerialhell.Item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;

public class BlowpipeItem extends Item
{
	private float arrowVelocity;
	
    public BlowpipeItem(Properties builder, float arrowVelocity)
    {
        super(builder);
        this.arrowVelocity = arrowVelocity;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn)
    {
        boolean isCreative = playerIn.getAbilities().instabuild;
        ItemStack heldItem = playerIn.getItemInHand(handIn);
        ItemStack ammo = this.findAmmo(playerIn);
        if(ammo.isEmpty() && !isCreative)
        {
        	return InteractionResultHolder.fail(heldItem);
        }

        if (!worldIn.isClientSide())
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
            
            arrow.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, arrowVelocity, 1.0F);
            if (isCreative)
            {
                arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            }
            else
            {
                arrow.pickup = AbstractArrow.Pickup.ALLOWED;
            }
            worldIn.addFreshEntity(arrow);
        }
        worldIn.playSound(playerIn, playerIn.getX(), playerIn.getY(), playerIn.getZ(), AerialHellSoundEvents.ENTITY_VOLUCITE_BLOWPIPE_SHOOT.get(), SoundSource.PLAYERS, 1.0F, 1.0F / (playerIn.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!isCreative)
        {
            ammo.shrink(1);
            if (ammo.isEmpty()) {playerIn.getInventory().removeItem(ammo);}
            heldItem.hurtAndBreak(1, playerIn, (player) -> {player.broadcastBreakEvent(playerIn.getUsedItemHand());});
        }
        playerIn.getCooldowns().addCooldown(this, 12);
        return InteractionResultHolder.consume(heldItem);
    }

    private ItemStack findAmmo(Player player) //copy of player.getProjectile but adapted to blowpipe use
    {
        Inventory inv = player.getInventory();
        for (int i = 0; i < inv.getContainerSize(); i++)
        {
            ItemStack stack = inv.getItem(i);
            if (stack == ItemStack.EMPTY) {continue;}
            else {if(stack.is(AerialHellTags.Items.BLOWPIPE_ARROWS)) {return stack;}}
        }
        return ItemStack.EMPTY;
    }
}