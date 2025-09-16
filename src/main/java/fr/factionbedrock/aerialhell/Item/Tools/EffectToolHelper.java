package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellParticleTypes;
import fr.factionbedrock.aerialhell.Entity.Projectile.LunaticProjectileEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EffectToolHelper
{
	public static void addParticleOnPlayer(int count, SimpleParticleType particleType, PlayerEntity player, World world, Random rand)
	{
		int i;
		for (i=0 ; i<count; i++)
		{
			world.addParticleClient(particleType, player.getX() + 4*(rand.nextFloat() - 0.5F), player.getY() + 4*rand.nextFloat(), player.getZ() + 4*(rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
		}
	}
	
	public static void setDamageAndCooldown(Item item, ItemStack heldItem, PlayerEntity player, Hand hand, int cooldown)
	{
		player.getItemCooldownManager().set(heldItem, cooldown);
		heldItem.damage(1, player, LivingEntity.getSlotForHand(hand));
	}
	
	public static void applyFullVolucitePower(Item item, ItemStack heldItem, World world, PlayerEntity player, Hand hand, Random rand)
	{
		addParticleOnPlayer(20, ParticleTypes.CLOUD, player, world, rand);
		player.playSound(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, 1.0F, 1.5F);
		if (!world.isClient())
		{
			player.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 100, 1));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 120, 0));
		}
		setDamageAndCooldown(item, heldItem, player, hand, 250);
	}
	
	public static void applyHalfVolucitePower(Item item, ItemStack heldItem, World world, PlayerEntity player, Hand hand, Random rand)
	{
		addParticleOnPlayer(20, ParticleTypes.CLOUD, player, world, rand);
		player.playSound(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, 1.0F, 1.6F);
		if (!world.isClient()) {player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 80, 0));}
		setDamageAndCooldown(item, heldItem, player, hand, 250);
	}
	
	public static boolean tryToApplyVolucitePower(Item item, ItemStack heldItem, World world, PlayerEntity player, Hand hand, Random rand, boolean canApplyFullPower)
	{
		int count_volucite = 0, count_heavy = 0;
		for (ItemStack armorStack : EntityHelper.getEquippedHumanoidArmorItemList(player))
		{
			if (armorStack.isIn(AerialHellTags.Items.VOLUCITE_STUFF)) {count_volucite++;}
			if (armorStack.isIn(AerialHellTags.Items.OBSIDIAN_STUFF) || armorStack.isIn(AerialHellTags.Items.ARSONIST_STUFF)) {count_heavy++;}
		}
		if (canApplyFullPower && count_volucite >= 4) {applyFullVolucitePower(item, heldItem, world, player, hand, rand); return true;} //full volucite armor : full volucite power
		else if (count_heavy == 0 || count_volucite > 0) {applyHalfVolucitePower(item, heldItem, world, player, hand, rand); return true;} //not too heavy : half volucite power
		else {return false;} //any heavy armor piece equiped and no volucite armor piece equiped : no effect
	}
	
	public static void applyNinjaEffect(Item item, ItemStack heldItem, World world, PlayerEntity player, Random rand, Hand hand, int cooldown)
	{
		addParticleOnPlayer(20, ParticleTypes.CLOUD, player, world, rand);
		player.playSound(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, 1.0F, 1.6F);
		if (!world.isClient())
		{
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 200, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 120, 0));
		}
		setDamageAndCooldown(item, heldItem, player, hand, cooldown);
	}

	public static void applyReaperWalkEffect(Item item, ItemStack heldItem, World world, PlayerEntity player, Hand hand, Random rand, int cooldown)
	{
		addParticleOnPlayer(20, AerialHellParticleTypes.SHADOW_LIGHT, player, world, rand);
		player.playSound(SoundEvents.ENTITY_ILLUSIONER_CAST_SPELL, 1.0F, 1.6F);
		if (!world.isClient())
		{
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 200, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 120, 0));
			player.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY, 120, 0));
		}
		setDamageAndCooldown(item, heldItem, player, hand, cooldown);
	}
	
	public static void applyRandomEffect(Item item, ItemStack heldItem, World world, PlayerEntity player, Hand hand, Random rand)
	{
		addParticleOnPlayer(20, ParticleTypes.ENCHANT, player, world, rand);
		player.playSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 1.0F, 1.5F);
		
		if (!world.isClient())
		{
			if (rand.nextFloat() < 0.25)
			{
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 750, 0));
			}
			else if (rand.nextFloat() < 0.3333)
			{
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 750, 0));
			}
			else if (rand.nextFloat() < 0.5)
			{
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 750, 0));
			}
			else
			{
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.UNLUCK, 750, 0));
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 60, 0));
			}
		}
		setDamageAndCooldown(item, heldItem, player, hand, 900);
	}
	
	public static void applyLunaticLight(Item item, ItemStack heldItem, World world, PlayerEntity player, Hand hand, Random rand, int baseCooldown)
	{
		int count = 0;
		for (ItemStack armorStack : EntityHelper.getEquippedHumanoidArmorItemList(player)) {if (armorStack.isIn(AerialHellTags.Items.LUNATIC_STUFF)) {count++;}}
		int cooldown = count == 4 ? baseCooldown/2 : baseCooldown;
		if (!world.isClient())
        {
			LunaticProjectileEntity lunaticProjectileEntity = new LunaticProjectileEntity(world, player, player.getRotationVector().x, player.getRotationVector().y, player.getRotationVector().z, 0.7f, 0);
			lunaticProjectileEntity.setPos(player.getX(), player.getBodyY(0.5D) + 0.5D, player.getZ());
            world.spawnEntity(lunaticProjectileEntity);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, cooldown/2, 2));
        }
		setDamageAndCooldown(item, heldItem, player, hand, cooldown);
	}
	
	public static boolean tryRemovingPoisonAndWitherEffect(Item item, ItemStack heldItem, World world, PlayerEntity player, Hand hand, Random rand) //returns true if any effect is removed
	{
		boolean playerHasPoison = (player.hasStatusEffect(StatusEffects.POISON));
		boolean playerHasWither = (player.hasStatusEffect(StatusEffects.WITHER));
		if (!playerHasPoison && !playerHasWither) {return false;}
		else
		{
			player.playSound(SoundEvents.ENTITY_GENERIC_DRINK.value(), 1.0F, 1.5F + 0.4F * rand.nextFloat());
			
			if (playerHasPoison) {player.removeStatusEffect(StatusEffects.POISON);}
			if (playerHasWither) {player.removeStatusEffect(StatusEffects.WITHER);}
			setDamageAndCooldown(item, heldItem, player, hand, 900);
	        return true;
		}
	}
	
	public static boolean tryEatingTool(Item item, ItemStack heldItem, World world, PlayerEntity player, Hand hand, Random rand) //return true if eat success
	{
		if (player.canConsume(false))
		{
			player.playSound(SoundEvents.ENTITY_GENERIC_EAT.value(), 1.0F, 0.5F + rand.nextFloat());
			if (!world.isClient())
			{
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 1, 0));
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 40, 0));
			}
			setDamageAndCooldown(item, heldItem, player, hand, 20);
			return true;
		}
		else{return false;}
	}
	
	public static void PlayerLiftoff(Item item, ItemStack heldItem, World world, PlayerEntity player, Hand hand, Random rand)
	{
		addParticleOnPlayer(20, ParticleTypes.EXPLOSION, player, world, rand);
		player.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE.value(), 1.0F, 0.5F + rand.nextFloat());
		int cooldown;
		if (player.isSneaking()) //armored glass
		{
			if (!world.isClient())
			{
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 1));
				player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 0));
			}
			cooldown = 400;
		}
		else //lift-off
		{
			if (world.isClient()) {player.setVelocity(player.getVelocity().add(0, 2, 0));}
			else  {player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200, 2));}
			cooldown = 600;
		}
		setDamageAndCooldown(item, heldItem, player, hand, cooldown);
	}
	
	public static void applyFireResistanceEffect(Item item, ItemStack heldItem, World world, PlayerEntity player, Hand hand, Random rand, int duration, int base_cooldown)
	{
		addParticleOnPlayer(20, ParticleTypes.FLAME, player, world, rand);
		player.playSound(SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, 1.0F, 0.5F + rand.nextFloat());
		int cooldown = base_cooldown,count = 0;
		for (ItemStack armorStack : EntityHelper.getEquippedHumanoidArmorItemList(player)) {if (armorStack.isIn(AerialHellTags.Items.ARSONIST_STUFF)) {count++;}}
		if (count >= 4) {cooldown/=2;}
		if (!world.isClient())
		{
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, duration, 0));
		}
		setDamageAndCooldown(item, heldItem, player, hand, cooldown);
	}
	
	public static void applyJumpBoostEffect(Item item, ItemStack heldItem, World world, PlayerEntity player, Hand hand, Random rand, int duration, int amplifier)
	{
		addParticleOnPlayer(20, ParticleTypes.CRIMSON_SPORE, player, world, rand);
		player.playSound(SoundEvents.ENTITY_PARROT_IMITATE_MAGMA_CUBE, 1.0F, 0.5F + rand.nextFloat());
		if (!world.isClient())
		{
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, duration, amplifier));
		}
		setDamageAndCooldown(item, heldItem, player, hand, 400);
	}
	
	public static class PassiveEffects
	{
		public static void applyMagmaCubeEffect(LivingEntity entity) {((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 400, 0));}
		public static void applyGodEffect(LivingEntity entity) {((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.GOD, 400, 0));}
	}
}
