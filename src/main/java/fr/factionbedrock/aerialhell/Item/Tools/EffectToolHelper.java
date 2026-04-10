package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Projectile.LunaticProjectileEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;

public class EffectToolHelper
{
	public static void addParticleOnPlayer(int count, SimpleParticleType particleType, Player player, Level world, Random rand)
	{
		int i;
		for (i=0 ; i<count; i++)
		{
			world.addParticle(particleType, player.getX() + 4*(rand.nextFloat() - 0.5F), player.getY() + 4*rand.nextFloat(), player.getZ() + 4*(rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
		}
	}
	
	public static void setDamageAndCooldown(Item item, ItemStack heldItem, Player player, InteractionHand hand, int cooldown)
	{
		player.getCooldowns().addCooldown(heldItem, cooldown);
		heldItem.hurtAndBreak(1, player, hand);
	}
	
	public static void applyFullVolucitePower(Item item, ItemStack heldItem, Level world, Player player, InteractionHand hand, Random rand)
	{
		addParticleOnPlayer(20, ParticleTypes.CLOUD, player, world, rand);
		player.playSound(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0F, 1.5F);
		if (!world.isClientSide())
		{
			player.addEffect(new MobEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 100, 1));
			player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 120, 0));
		}
		setDamageAndCooldown(item, heldItem, player, hand, 250);
	}
	
	public static void applyHalfVolucitePower(Item item, ItemStack heldItem, Level world, Player player, InteractionHand hand, Random rand)
	{
		addParticleOnPlayer(20, ParticleTypes.CLOUD, player, world, rand);
		player.playSound(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0F, 1.6F);
		if (!world.isClientSide()) {player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 80, 0));}
		setDamageAndCooldown(item, heldItem, player, hand, 250);
	}
	
	public static boolean tryToApplyVolucitePower(Item item, ItemStack heldItem, Level world, Player player, InteractionHand hand, Random rand, boolean canApplyFullPower)
	{
		int count_volucite = 0, count_heavy = 0;
		for (ItemStack armorStack : EntityHelper.getEquippedHumanoidArmorItemList(player))
		{
			if (armorStack.is(AerialHellTags.Items.VOLUCITE_STUFF)) {count_volucite++;}
			if (armorStack.is(AerialHellTags.Items.OBSIDIAN_STUFF) || armorStack.is(AerialHellTags.Items.ARSONIST_STUFF)) {count_heavy++;}
		}
		if (canApplyFullPower && count_volucite >= 4) {applyFullVolucitePower(item, heldItem, world, player, hand, rand); return true;} //full volucite armor : full volucite power
		else if (count_heavy == 0 || count_volucite > 0) {applyHalfVolucitePower(item, heldItem, world, player, hand, rand); return true;} //not too heavy : half volucite power
		else {return false;} //any heavy armor piece equiped and no volucite armor piece equiped : no effect
	}
	
	public static void applyNinjaEffect(Item item, ItemStack heldItem, Level world, Player player, Random rand, InteractionHand hand, int cooldown)
	{
		addParticleOnPlayer(20, ParticleTypes.CLOUD, player, world, rand);
		player.playSound(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0F, 1.6F);
		if (!world.isClientSide())
		{
			player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 200, 0));
			player.addEffect(new MobEffectInstance(MobEffects.SPEED, 120, 0));
		}
		setDamageAndCooldown(item, heldItem, player, hand, cooldown);
	}

	public static void applyReaperWalkEffect(Item item, ItemStack heldItem, Level world, Player player, InteractionHand hand, Random rand, int cooldown)
	{
		addParticleOnPlayer(20, AerialHellParticleTypes.SHADOW_LIGHT, player, world, rand);
		player.playSound(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0F, 1.6F);
		if (!world.isClientSide())
		{
			player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 200, 0));
			player.addEffect(new MobEffectInstance(MobEffects.SPEED, 120, 0));
			player.addEffect(new MobEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY, 120, 0));
		}
		setDamageAndCooldown(item, heldItem, player, hand, cooldown);
	}
	
	public static void applyRandomEffect(Item item, ItemStack heldItem, Level world, Player player, InteractionHand hand, Random rand)
	{
		addParticleOnPlayer(20, ParticleTypes.ENCHANT, player, world, rand);
		player.playSound(SoundEvents.ENCHANTMENT_TABLE_USE, 1.0F, 1.5F);
		
		if (!world.isClientSide())
		{
			if (rand.nextFloat() < 0.25)
			{
				player.addEffect(new MobEffectInstance(MobEffects.SPEED, 750, 0));
			}
			else if (rand.nextFloat() < 0.3333)
			{
				player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 750, 0));
			}
			else if (rand.nextFloat() < 0.5)
			{
				player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 750, 0));
			}
			else
			{
				player.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 750, 0));
				player.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0));
			}
		}
		setDamageAndCooldown(item, heldItem, player, hand, 900);
	}
	
	public static void applyLunaticLight(Item item, ItemStack heldItem, Level world, Player player, InteractionHand hand, Random rand, int baseCooldown)
	{
		int count = 0;
		for (ItemStack armorStack : EntityHelper.getEquippedHumanoidArmorItemList(player)) {if (armorStack.is(AerialHellTags.Items.LUNATIC_STUFF)) {count++;}}
		int cooldown = count == 4 ? baseCooldown/2 : baseCooldown;
		if (!world.isClientSide())
        {
			LunaticProjectileEntity lunaticProjectileEntity = new LunaticProjectileEntity(world, player, player.getLookAngle().x, player.getLookAngle().y, player.getLookAngle().z, 0.7f, 0);
			lunaticProjectileEntity.setPosRaw(player.getX(), player.getY(0.5D) + 0.5D, player.getZ());
            world.addFreshEntity(lunaticProjectileEntity);
            player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, cooldown/2, 2));
        }
		setDamageAndCooldown(item, heldItem, player, hand, cooldown);
	}
	
	public static boolean tryRemovingPoisonAndWitherEffect(Item item, ItemStack heldItem, Level world, Player player, InteractionHand hand, Random rand) //returns true if any effect is removed
	{
		boolean playerHasPoison = (player.hasEffect(MobEffects.POISON));
		boolean playerHasWither = (player.hasEffect(MobEffects.WITHER));
		if (!playerHasPoison && !playerHasWither) {return false;}
		else
		{
			player.playSound(SoundEvents.GENERIC_DRINK.value(), 1.0F, 1.5F + 0.4F * rand.nextFloat());
			
			if (playerHasPoison) {player.removeEffect(MobEffects.POISON);}
			if (playerHasWither) {player.removeEffect(MobEffects.WITHER);}
			setDamageAndCooldown(item, heldItem, player, hand, 900);
	        return true;
		}
	}
	
	public static boolean tryEatingTool(Item item, ItemStack heldItem, Level world, Player player, InteractionHand hand, Random rand) //return true if eat success
	{
		if (player.canEat(false))
		{
			player.playSound(SoundEvents.GENERIC_EAT.value(), 1.0F, 0.5F + rand.nextFloat());
			if (!world.isClientSide())
			{
				player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 1, 0));
				player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 0));
			}
			setDamageAndCooldown(item, heldItem, player, hand, 20);
			return true;
		}
		else{return false;}
	}
	
	public static void PlayerLiftoff(Item item, ItemStack heldItem, Level world, Player player, InteractionHand hand, Random rand)
	{
		addParticleOnPlayer(20, ParticleTypes.EXPLOSION, player, world, rand);
		player.playSound(SoundEvents.GENERIC_EXPLODE.value(), 1.0F, 0.5F + rand.nextFloat());
		int cooldown;
		if (player.isShiftKeyDown()) //armored glass
		{
			if (!world.isClientSide())
			{
				player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 200, 1));
				player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 100, 0));
			}
			cooldown = 400;
		}
		else //lift-off
		{
			if (world.isClientSide()) {player.setDeltaMovement(player.getDeltaMovement().add(0, 2, 0));}
			else  {player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200, 2));}
			cooldown = 600;
		}
		setDamageAndCooldown(item, heldItem, player, hand, cooldown);
	}
	
	public static void applyFireResistanceEffect(Item item, ItemStack heldItem, Level world, Player player, InteractionHand hand, Random rand, int duration, int base_cooldown)
	{
		addParticleOnPlayer(20, ParticleTypes.FLAME, player, world, rand);
		player.playSound(SoundEvents.GENERIC_EXTINGUISH_FIRE, 1.0F, 0.5F + rand.nextFloat());
		int cooldown = base_cooldown,count = 0;
		for (ItemStack armorStack : EntityHelper.getEquippedHumanoidArmorItemList(player)) {if (armorStack.is(AerialHellTags.Items.ARSONIST_STUFF)) {count++;}}
		if (count >= 4) {cooldown/=2;}
		if (!world.isClientSide())
		{
			player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, duration, 0));
		}
		setDamageAndCooldown(item, heldItem, player, hand, cooldown);
	}
	
	public static void applyJumpBoostEffect(Item item, ItemStack heldItem, Level world, Player player, InteractionHand hand, Random rand, int duration, int amplifier)
	{
		addParticleOnPlayer(20, ParticleTypes.CRIMSON_SPORE, player, world, rand);
		player.playSound(SoundEvents.PARROT_IMITATE_MAGMA_CUBE, 1.0F, 0.5F + rand.nextFloat());
		if (!world.isClientSide())
		{
			player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, duration, amplifier));
		}
		setDamageAndCooldown(item, heldItem, player, hand, 400);
	}
	
	public static class PassiveEffects
	{
		public static void applyMagmaCubeEffect(LivingEntity entity) {((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 400, 0));}
		public static void applyGodEffect(LivingEntity entity) {((LivingEntity) entity).addEffect(new MobEffectInstance(AerialHellMobEffects.GOD, 400, 0));}
	}
}
