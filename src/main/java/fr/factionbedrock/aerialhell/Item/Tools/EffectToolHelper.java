package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Projectile.LunaticProjectileEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;

public class EffectToolHelper
{
	public static void addParticleOnPlayer(int count, SimpleParticleType particleType, Player playerIn, Level worldIn, Random rand)
	{
		int i;
		for (i=0 ; i<count; i++)
		{
			worldIn.addParticle(particleType, playerIn.getX() + 4*(rand.nextFloat() - 0.5F), playerIn.getY() + 4*rand.nextFloat(), playerIn.getZ() + 4*(rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
		}
	}
	
	public static void setDamageAndCooldown(Item ItemIn, ItemStack heldItem, Player playerIn, InteractionHand hand, int cooldown)
	{
		playerIn.getCooldowns().addCooldown(heldItem, cooldown);
		heldItem.hurtAndBreak(1, playerIn, LivingEntity.getSlotForHand(hand));
	}
	
	public static void applyFullVolucitePower(Item ItemIn, ItemStack heldItem, Level worldIn, Player playerIn, InteractionHand hand, Random rand)
	{
		addParticleOnPlayer(20, ParticleTypes.CLOUD, playerIn, worldIn, rand);
		playerIn.playSound(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0F, 1.5F);
		if (!worldIn.isClientSide())
		{
			playerIn.addEffect(new MobEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.getDelegate(), 100, 1));
			playerIn.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 0));
		}
		setDamageAndCooldown(ItemIn, heldItem, playerIn, hand, 250);
	}
	
	public static void applyHalfVolucitePower(Item ItemIn, ItemStack heldItem, Level worldIn, Player playerIn, InteractionHand hand, Random rand)
	{
		addParticleOnPlayer(20, ParticleTypes.CLOUD, playerIn, worldIn, rand);
		playerIn.playSound(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0F, 1.6F);
		if (!worldIn.isClientSide()) {playerIn.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 80, 0));}
		setDamageAndCooldown(ItemIn, heldItem, playerIn, hand, 250);
	}
	
	public static boolean tryToApplyVolucitePower(Item ItemIn, ItemStack heldItem, Level worldIn, Player playerIn, InteractionHand hand, Random rand, boolean canApplyFullPower)
	{
		int count_volucite = 0, count_heavy = 0;
		for (ItemStack armorStack : EntityHelper.getEquippedHumanoidArmorItemList(playerIn))
		{
			if (armorStack.is(AerialHellTags.Items.VOLUCITE_STUFF)) {count_volucite++;}
			if (armorStack.is(AerialHellTags.Items.OBSIDIAN_STUFF) || armorStack.is(AerialHellTags.Items.ARSONIST_STUFF)) {count_heavy++;}
		}
		if (canApplyFullPower && count_volucite >= 4) {applyFullVolucitePower(ItemIn, heldItem, worldIn, playerIn, hand, rand); return true;} //full volucite armor : full volucite power
		else if (count_heavy == 0 || count_volucite > 0) {applyHalfVolucitePower(ItemIn, heldItem, worldIn, playerIn, hand, rand); return true;} //not too heavy : half volucite power
		else {return false;} //any heavy armor piece equiped and no volucite armor piece equiped : no effect
	}
	
	public static void applyNinjaEffect(Item ItemIn, ItemStack heldItem, Level worldIn, Player playerIn, Random rand, InteractionHand hand, int cooldown)
	{
		addParticleOnPlayer(20, ParticleTypes.CLOUD, playerIn, worldIn, rand);
		playerIn.playSound(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0F, 1.6F);
		if (!worldIn.isClientSide())
		{
			playerIn.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 200, 0));
			playerIn.addEffect(new MobEffectInstance(MobEffects.SPEED, 120, 0));
		}
		setDamageAndCooldown(ItemIn, heldItem, playerIn, hand, cooldown);
	}

	public static void applyReaperWalkEffect(Item ItemIn, ItemStack heldItem, Level worldIn, Player playerIn, InteractionHand hand, Random rand, int cooldown)
	{
		addParticleOnPlayer(20, AerialHellParticleTypes.SHADOW_LIGHT.get(), playerIn, worldIn, rand);
		playerIn.playSound(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0F, 1.6F);
		if (!worldIn.isClientSide())
		{
			playerIn.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 200, 0));
			playerIn.addEffect(new MobEffectInstance(MobEffects.SPEED, 120, 0));
			playerIn.addEffect(new MobEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY.getDelegate(), 120, 0));
		}
		setDamageAndCooldown(ItemIn, heldItem, playerIn, hand, cooldown);
	}
	
	public static void applyRandomEffect(Item ItemIn, ItemStack heldItem, Level worldIn, Player playerIn, InteractionHand hand, Random rand)
	{
		addParticleOnPlayer(20, ParticleTypes.ENCHANT, playerIn, worldIn, rand);
		playerIn.playSound(SoundEvents.ENCHANTMENT_TABLE_USE, 1.0F, 1.5F);
		
		if (!worldIn.isClientSide())
		{
			if (rand.nextFloat() < 0.25)
			{
				playerIn.addEffect(new MobEffectInstance(MobEffects.SPEED, 750, 0));
			}
			else if (rand.nextFloat() < 0.3333)
			{
				playerIn.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 750, 0));
			}
			else if (rand.nextFloat() < 0.5)
			{
				playerIn.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 750, 0));
			}
			else
			{
				playerIn.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 750, 0));
				playerIn.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0));
			}
		}
		setDamageAndCooldown(ItemIn, heldItem, playerIn, hand, 900);
	}
	
	public static void applyLunaticLight(Item ItemIn, ItemStack heldItem, Level worldIn, Player playerIn, InteractionHand hand, Random rand, int baseCooldown)
	{
		int count = 0;
		for (ItemStack armorStack : EntityHelper.getEquippedHumanoidArmorItemList(playerIn)) {if (armorStack.is(AerialHellTags.Items.LUNATIC_STUFF)) {count++;}}
		int cooldown = count == 4 ? baseCooldown/2 : baseCooldown;
		if (!worldIn.isClientSide())
        {
			LunaticProjectileEntity lunaticProjectileEntity = new LunaticProjectileEntity(worldIn, playerIn, playerIn.getLookAngle().x, playerIn.getLookAngle().y, playerIn.getLookAngle().z, 0.7f, 0);
			lunaticProjectileEntity.setPos(playerIn.getX(), playerIn.getY(0.5D) + 0.5D, playerIn.getZ());
            worldIn.addFreshEntity(lunaticProjectileEntity);
            playerIn.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, cooldown/2, 2));
        }
		setDamageAndCooldown(ItemIn, heldItem, playerIn, hand, cooldown);
	}
	
	public static boolean tryRemovingPoisonAndWitherEffect(Item ItemIn, ItemStack heldItem, Level worldIn, Player playerIn, InteractionHand hand, Random rand) //returns true if any effect is removed
	{
		boolean playerHasPoison = (playerIn.hasEffect(MobEffects.POISON));
		boolean playerHasWither = (playerIn.hasEffect(MobEffects.WITHER));
		if (!playerHasPoison && !playerHasWither) {return false;}
		else
		{
			playerIn.playSound(SoundEvents.GENERIC_DRINK.value(), 1.0F, 1.5F + 0.4F * rand.nextFloat());
			
			if (playerHasPoison) {playerIn.removeEffect(MobEffects.POISON);}
			if (playerHasWither) {playerIn.removeEffect(MobEffects.WITHER);}
			setDamageAndCooldown(ItemIn, heldItem, playerIn, hand, 900);
	        return true;
		}
	}
	
	public static boolean tryEatingTool(Item ItemIn, ItemStack heldItem, Level worldIn, Player playerIn, InteractionHand hand, Random rand) //return true if eat success
	{
		if (playerIn.canEat(false))
		{
			playerIn.playSound(SoundEvents.GENERIC_EAT.value(), 1.0F, 0.5F + rand.nextFloat());
			if (!worldIn.isClientSide())
			{
				playerIn.addEffect(new MobEffectInstance(MobEffects.SATURATION, 1, 0));
				playerIn.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 0));
			}
			setDamageAndCooldown(ItemIn, heldItem, playerIn, hand, 20);
			return true;
		}
		else{return false;}
	}
	
	public static void PlayerLiftoff(Item ItemIn, ItemStack heldItem, Level worldIn, Player playerIn, InteractionHand hand, Random rand)
	{
		addParticleOnPlayer(20, ParticleTypes.EXPLOSION, playerIn, worldIn, rand);
		playerIn.playSound(SoundEvents.GENERIC_EXPLODE.value(), 1.0F, 0.5F + rand.nextFloat());
		int cooldown;
		if (playerIn.isShiftKeyDown()) //armored glass
		{
			if (!worldIn.isClientSide())
			{
				playerIn.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 200, 1));
				playerIn.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 100, 0));
			}
			cooldown = 400;
		}
		else //lift-off
		{
			if (worldIn.isClientSide()) {playerIn.setDeltaMovement(playerIn.getDeltaMovement().add(0, 2, 0));}
			else  {playerIn.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200, 2));}
			cooldown = 600;
		}
		setDamageAndCooldown(ItemIn, heldItem, playerIn, hand, cooldown);
	}
	
	public static void applyFireResistanceEffect(Item ItemIn, ItemStack heldItem, Level worldIn, Player playerIn, InteractionHand hand, Random rand, int duration, int base_cooldown)
	{
		addParticleOnPlayer(20, ParticleTypes.FLAME, playerIn, worldIn, rand);
		playerIn.playSound(SoundEvents.GENERIC_EXTINGUISH_FIRE, 1.0F, 0.5F + rand.nextFloat());
		int cooldown = base_cooldown,count = 0;
		for (ItemStack armorStack : EntityHelper.getEquippedHumanoidArmorItemList(playerIn)) {if (armorStack.is(AerialHellTags.Items.ARSONIST_STUFF)) {count++;}}
		if (count >= 4) {cooldown/=2;}
		if (!worldIn.isClientSide())
		{
			playerIn.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, duration, 0));
		}
		setDamageAndCooldown(ItemIn, heldItem, playerIn, hand, cooldown);
	}
	
	public static void applyJumpBoostEffect(Item ItemIn, ItemStack heldItem, Level worldIn, Player playerIn, InteractionHand hand, Random rand, int duration, int amplifier)
	{
		addParticleOnPlayer(20, ParticleTypes.CRIMSON_SPORE, playerIn, worldIn, rand);
		playerIn.playSound(SoundEvents.PARROT_IMITATE_MAGMA_CUBE, 1.0F, 0.5F + rand.nextFloat());
		if (!worldIn.isClientSide())
		{
			playerIn.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, duration, amplifier));
		}
		setDamageAndCooldown(ItemIn, heldItem, playerIn, hand, 400);
	}
	
	public static class PassiveEffects
	{
		public static void applyMagmaCubeEffect(LivingEntity entityIn) {((LivingEntity) entityIn).addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 400, 0));}
		public static void applyGodEffect(LivingEntity entityIn) {((LivingEntity) entityIn).addEffect(new MobEffectInstance(AerialHellMobEffects.GOD.getDelegate(), 400, 0));}
	}
}
