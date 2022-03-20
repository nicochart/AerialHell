package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class EffectSwordItem extends AerialHellSwordItem
{
	private int timer;
	
	public EffectSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float movementSpeedIn, float maxHealthIn, Properties builderIn)
	{
		super(tier, attackDamageIn, attackSpeedIn, movementSpeedIn, maxHealthIn, builderIn);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if (!worldIn.isRemote && timer <= 0)
		{
			if (entityIn instanceof LivingEntity)
			{
				if (((LivingEntity) entityIn).getHeldItemOffhand().getItem() == this || ((LivingEntity) entityIn).getHeldItemMainhand().getItem() == this)
				{
					if (this == AerialHellBlocksAndItems.GOD_SWORD.get())
					{
						((LivingEntity) entityIn).addPotionEffect(new EffectInstance(AerialHellPotionEffects.GOD.get(), 400, 0));
					}
				}
			}
			timer = 200;
		}
		else if (timer > -10)
		{
			timer--;
		}
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
		ItemStack heldItem = playerIn.getHeldItem(handIn);
		Random rand = new Random();
		if (this == AerialHellBlocksAndItems.NINJA_SWORD.get())
		{
			for (int i=0 ; i<20; i++)
			{
				worldIn.addParticle(ParticleTypes.CLOUD, playerIn.getPosX() + 4*(rand.nextFloat() - 0.5F), playerIn.getPosY() + 4*rand.nextFloat(), playerIn.getPosZ() + 4*(rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
			}
			playerIn.playSound(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, 1.0F, 1.5F);
			
			if (!worldIn.isRemote)
			{
				playerIn.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 200, 0));
				playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 120, 0));
			}
			playerIn.getCooldownTracker().setCooldown(this, 400);
			heldItem.damageItem(1, playerIn, (player) -> {player.sendBreakAnimation(playerIn.getActiveHand());});
	        return ActionResult.resultConsume(heldItem);
		}
		else if (this == AerialHellBlocksAndItems.RANDOM_SWORD.get())
		{
			for (int i=0 ; i<20; i++)
			{
				worldIn.addParticle(ParticleTypes.ENCHANT, playerIn.getPosX() + 4*(rand.nextFloat() - 0.5F), playerIn.getPosY() + 4*rand.nextFloat(), playerIn.getPosZ() + 4*(rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
			}
			playerIn.playSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 1.0F, 1.5F);
			
			if (!worldIn.isRemote)
			{
				if (rand.nextFloat() < 0.25)
				{
					playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 750, 0));
				}
				else if (rand.nextFloat() < 0.3333)
				{
					playerIn.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 750, 0));
				}
				else if (rand.nextFloat() < 0.5)
				{
					playerIn.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 750, 0));
				}
				else
				{
					playerIn.addPotionEffect(new EffectInstance(Effects.UNLUCK, 750, 0));
					playerIn.addPotionEffect(new EffectInstance(Effects.POISON, 60, 0));
				}
			}
			playerIn.getCooldownTracker().setCooldown(this, 900);
			heldItem.damageItem(1, playerIn, (player) -> {player.sendBreakAnimation(playerIn.getActiveHand());});
	        return ActionResult.resultConsume(heldItem);
		}
		else if (this == AerialHellBlocksAndItems.ANTIDOTE_SWORD.get())
		{
			if (playerIn.getActivePotionEffect(Effects.POISON) == null && playerIn.getActivePotionEffect(Effects.WITHER) == null)
			{
				return ActionResult.resultPass(heldItem);
			}
			else
			{
				playerIn.playSound(SoundEvents.ENTITY_GENERIC_DRINK, 1.0F, 1.5F + 0.4F * rand.nextFloat());
				
				if (playerIn.getActivePotionEffect(Effects.POISON) != null)
				{
					playerIn.removePotionEffect(Effects.POISON);
				}
				if (playerIn.getActivePotionEffect(Effects.WITHER) != null)
				{
					playerIn.removePotionEffect(Effects.WITHER);
				}
				playerIn.getCooldownTracker().setCooldown(this, 900);
				heldItem.damageItem(1, playerIn, (player) -> {player.sendBreakAnimation(playerIn.getActiveHand());});
		        return ActionResult.resultConsume(heldItem);
			}
		}
		else if (this == AerialHellBlocksAndItems.GLOUTON_SWORD.get())
		{
			if (playerIn.canEat(false))
			{
				playerIn.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1.0F, 0.5F + rand.nextFloat());
				if (!worldIn.isRemote)
				{
					playerIn.addPotionEffect(new EffectInstance(Effects.SATURATION, 1, 0));
				}
				playerIn.getCooldownTracker().setCooldown(this, 20);
				heldItem.damageItem(1, playerIn, (player) -> {player.sendBreakAnimation(playerIn.getActiveHand());});
				
				return ActionResult.resultConsume(heldItem);
			}
			else
			{
				return ActionResult.resultPass(heldItem);
			}
		}
		else if (this == AerialHellBlocksAndItems.GLASS_CANON_SWORD.get())
		{
			for (int i=0 ; i<20; i++)
			{
				worldIn.addParticle(ParticleTypes.EXPLOSION, playerIn.getPosX() + 4*(rand.nextFloat() - 0.5F), playerIn.getPosY() + 4*rand.nextFloat(), playerIn.getPosZ() + 4*(rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
			}
			playerIn.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0F, 0.5F + rand.nextFloat());
			
			if (worldIn.isRemote)
			{
				playerIn.setMotion(playerIn.getMotion().add(0, 2, 0));
			}
			else //!worldIn.isRemote
			{
				playerIn.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 200, 2));
			}
			playerIn.getCooldownTracker().setCooldown(this, 600);
			heldItem.damageItem(1, playerIn, (player) -> {player.sendBreakAnimation(playerIn.getActiveHand());});
	        return ActionResult.resultConsume(heldItem);
		}
		else
		{
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
    }
}
