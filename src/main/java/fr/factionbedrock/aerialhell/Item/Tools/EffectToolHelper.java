package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Entity.Projectile.LunaticProjectileEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class EffectToolHelper
{
	public static void addParticleOnPlayer(int count, BasicParticleType particleType, PlayerEntity playerIn, World worldIn, Random rand)
	{
		int i;
		for (i=0 ; i<count; i++)
		{
			worldIn.addParticle(particleType, playerIn.getPosX() + 4*(rand.nextFloat() - 0.5F), playerIn.getPosY() + 4*rand.nextFloat(), playerIn.getPosZ() + 4*(rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
		}
	}
	
	public static void setDamageAndCooldown(Item ItemIn, ItemStack heldItem, PlayerEntity playerIn, int cooldown)
	{
		playerIn.getCooldownTracker().setCooldown(ItemIn, cooldown);
		heldItem.damageItem(1, playerIn, (player) -> {player.sendBreakAnimation(playerIn.getActiveHand());});
	}
	
	public static void applyFullVolucitePower(Item ItemIn, ItemStack heldItem, World worldIn, PlayerEntity playerIn, Random rand)
	{
		addParticleOnPlayer(20, ParticleTypes.CLOUD, playerIn, worldIn, rand);
		playerIn.playSound(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, 1.0F, 1.5F);
		if (!worldIn.isRemote)
		{
			playerIn.addPotionEffect(new EffectInstance(AerialHellPotionEffects.HEAD_IN_THE_CLOUDS.get(), 100, 0));
			playerIn.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 120, 0));
		}
		setDamageAndCooldown(ItemIn, heldItem, playerIn, 250);
	}
	
	public static void applyHalfVolucitePower(Item ItemIn, ItemStack heldItem, World worldIn, PlayerEntity playerIn, Random rand)
	{
		addParticleOnPlayer(20, ParticleTypes.CLOUD, playerIn, worldIn, rand);
		playerIn.playSound(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, 1.0F, 1.6F);
		if (!worldIn.isRemote) {playerIn.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 80, 0));}
		setDamageAndCooldown(ItemIn, heldItem, playerIn, 250);
	}
	
	public static boolean tryToApplyVolucitePower(Item ItemIn, ItemStack heldItem, World worldIn, PlayerEntity playerIn, Random rand)
	{
		int count_volucite = 0, count_heavy = 0;
		for (ItemStack armorStack : playerIn.getArmorInventoryList())
		{
			if (armorStack.getItem().isIn(AerialHellTags.Items.VOLUCITE_STUFF)) {count_volucite++;}
			if (armorStack.getItem().isIn(AerialHellTags.Items.OBSIDIAN_STUFF) || armorStack.getItem().isIn(AerialHellTags.Items.ARSONIST_STUFF)) {count_heavy++;}
		}
		if (count_volucite >= 4) {applyFullVolucitePower(ItemIn, heldItem, worldIn, playerIn, rand); return true;} //full volucite armor : full volucite power
		else if (count_heavy == 0 || count_volucite > 0) {applyHalfVolucitePower(ItemIn, heldItem, worldIn, playerIn, rand); return true;} //not too heavy : half volucite power
		else {return false;} //any heavy armor piece equiped and no volucite armor piece equiped : no effect
	}
	
	public static void applyNinjaEffect(Item ItemIn, ItemStack heldItem, World worldIn, PlayerEntity playerIn, Random rand)
	{
		addParticleOnPlayer(20, ParticleTypes.CLOUD, playerIn, worldIn, rand);
		playerIn.playSound(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, 1.0F, 1.6F);
		if (!worldIn.isRemote)
		{
			playerIn.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 200, 0));
			playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 120, 0));
		}
		setDamageAndCooldown(ItemIn, heldItem, playerIn, 400);
	}
	
	public static void applyRandomEffect(Item ItemIn, ItemStack heldItem, World worldIn, PlayerEntity playerIn, Random rand)
	{
		addParticleOnPlayer(20, ParticleTypes.ENCHANT, playerIn, worldIn, rand);
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
		setDamageAndCooldown(ItemIn, heldItem, playerIn, 900);
	}
	
	public static void applyLunaticLight(Item ItemIn, ItemStack heldItem, World worldIn, PlayerEntity playerIn, Random rand)
	{
		int count = 0;
		for (ItemStack armorStack : playerIn.getArmorInventoryList()) {if (armorStack.getItem().isIn(AerialHellTags.Items.LUNATIC_STUFF)) {count++;}}
		int cooldown = count == 4 ? 80 : 160;
		if (!worldIn.isRemote)
        {
			LunaticProjectileEntity lunaticProjectileEntity = new LunaticProjectileEntity(worldIn, playerIn, playerIn.getLookVec().x, playerIn.getLookVec().y, playerIn.getLookVec().z, 0.7f, 0);
			lunaticProjectileEntity.setPosition(playerIn.getPosX(), playerIn.getPosYHeight(0.5D) + 0.5D, playerIn.getPosZ());
            worldIn.addEntity(lunaticProjectileEntity);
            playerIn.addPotionEffect(new EffectInstance(Effects.WEAKNESS, cooldown/2, 2));
        }
		setDamageAndCooldown(ItemIn, heldItem, playerIn, cooldown);
	}
	
	public static boolean tryRemovingPoisonAndWitherEffect(Item ItemIn, ItemStack heldItem, World worldIn, PlayerEntity playerIn, Random rand) //returns true if any effect is removed
	{
		boolean playerHasPoison = (playerIn.isPotionActive(Effects.POISON));
		boolean playerHasWither = (playerIn.isPotionActive(Effects.WITHER));
		if (!playerHasPoison && !playerHasWither) {return false;}
		else
		{
			playerIn.playSound(SoundEvents.ENTITY_GENERIC_DRINK, 1.0F, 1.5F + 0.4F * rand.nextFloat());
			
			if (playerHasPoison) {playerIn.removePotionEffect(Effects.POISON);}
			if (playerHasWither) {playerIn.removePotionEffect(Effects.WITHER);}
			setDamageAndCooldown(ItemIn, heldItem, playerIn, 900);
	        return true;
		}
	}
	
	public static boolean tryEatingTool(Item ItemIn, ItemStack heldItem, World worldIn, PlayerEntity playerIn, Random rand) //return true if eat success
	{
		if (playerIn.canEat(false))
		{
			playerIn.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1.0F, 0.5F + rand.nextFloat());
			if (!worldIn.isRemote)
			{
				playerIn.addPotionEffect(new EffectInstance(Effects.SATURATION, 1, 0));
				playerIn.addPotionEffect(new EffectInstance(Effects.REGENERATION, 40, 0));
			}
			setDamageAndCooldown(ItemIn, heldItem, playerIn, 20);
			return true;
		}
		else{return false;}
	}
	
	public static void PlayerLiftoff(Item ItemIn, ItemStack heldItem, World worldIn, PlayerEntity playerIn, Random rand)
	{
		addParticleOnPlayer(20, ParticleTypes.EXPLOSION, playerIn, worldIn, rand);
		playerIn.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0F, 0.5F + rand.nextFloat());
		int cooldown;
		if (playerIn.isSneaking()) //armored glass
		{
			if (!worldIn.isRemote)
			{
				playerIn.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 200, 1));
				playerIn.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 0));
			}
			cooldown = 400;
		}
		else //lift-off
		{
			if (worldIn.isRemote) {playerIn.setMotion(playerIn.getMotion().add(0, 2, 0));}
			else  {playerIn.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 200, 2));}
			cooldown = 600;
		}
		setDamageAndCooldown(ItemIn, heldItem, playerIn, cooldown);
	}
}
