package fr.factionbedrock.aerialhell.Event.Listeners;

import java.util.UUID;

import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.CrystalGolemEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowAutomatonEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowTrollEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class ToolsAndArmorEventListener
{
	public static void onProjectileCollideWithEntity(ProjectileImpactEvent event)
	{
		Entity collidingEntity = event.getEntity();
		boolean isLightProjectile = EntityHelper.isLightProjectile(collidingEntity);

		if (event.getRayTraceResult().getType() == HitResult.Type.ENTITY)
		{
			Entity hitEntity = ((EntityHitResult) event.getRayTraceResult()).getEntity();
			if ((hitEntity instanceof ShadowTrollEntity || hitEntity instanceof ShadowAutomatonEntity) && !isLightProjectile) {event.setCanceled(true);}

			if (EntityHelper.isGhostEntity(hitEntity) && !isLightProjectile)
			{
				if (collidingEntity instanceof Projectile projectile && EntityHelper.isImmuneToGhostBlockCollision(projectile.getOwner())) {event.setCanceled(true);}
			}
		}
	}

    public static void onLivingDamageEvent(LivingIncomingDamageEvent event)
    {
		DamageSource damageSource = event.getSource();
		Entity sourceEntity = damageSource.getEntity();
		LivingEntity target = event.getEntity();

		applyEffectsDueToPotionEffects(event, damageSource, target);

		if (target instanceof Player) //*2 damage if target has glass cannon sword
		{
			Item targetMainHandItem = ((Player) target).getMainHandItem().getItem();
			applyEffectsBasedOnTargetHandEquippedItem(event, targetMainHandItem, target);
		} else {
			Iterable<ItemStack> handStuff = target.getHandSlots();
			for (ItemStack handItemStack : handStuff) {
				applyEffectsBasedOnTargetHandEquippedItem(event, handItemStack.getItem(), target);
			}
		}

		if (sourceEntity instanceof LivingEntity) {
			LivingEntity source = (LivingEntity) sourceEntity;
			Iterable<ItemStack> stuff = target.getArmorSlots();
			applyEffectsBasedOnTargetEquippedArmor(event, stuff, source, target);

			if (source instanceof Player) {
				ItemStack mainHandItemStack = ((Player) source).getMainHandItem();
				applyEffectsBasedOnSourceHandEquippedItem(event, mainHandItemStack, source, target);
				applyTraitorEffectIfNecessary((Player)source, target);
			} else {
				Iterable<ItemStack> handStuff = source.getHandSlots();
				for (ItemStack handItemStack : handStuff) {
					applyEffectsBasedOnSourceHandEquippedItem(event, handItemStack, source, target);
				}
			}
		}
    }

    public static void onPlayerHarvest(PlayerEvent.BreakSpeed event)
    {
		Player player = event.getEntity();
		ItemStack selectedItemStack = player.getInventory().getSelected();
		BlockState state = event.getState();
		float speed = event.getOriginalSpeed();

		//player on fire and mining with any arsonist item
		if (state != null && selectedItemStack.is(AerialHellTags.Items.ARSONIST) && player.getRemainingFireTicks() > 0) {
			event.setNewSpeed(speed * 2.0F);
		}

		//player mining stellar stone with stellar stone breaker
		else if (selectedItemStack.getItem() == AerialHellBlocksAndItems.STELLAR_STONE_BREAKER.get() && state.getBlock() == AerialHellBlocksAndItems.STELLAR_STONE.get()) {
			event.setNewSpeed(speed * 2.0F);
		}

		//player mining a block that needs lunar tool
		if (state != null && state.is(AerialHellTags.Blocks.NEEDS_LUNAR_TOOL)) {
			if (ItemHelper.getItemMiningLevel(selectedItemStack.getItem()) < 4) {
				event.setNewSpeed(Math.min(speed, 4.0F));
				if (!player.level().isClientSide()) {player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 40, 0));}
			}
		}

		if (state != null && state.is(AerialHellBlocksAndItems.EYE_SHADOW_PINE_LOG.get()) && !EntityHelper.isLivingEntityShadowImmune(player) && !player.isCreative())
		{
			player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 30, 0));
			player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 30, 0));
		}

		if (state != null && state.is(AerialHellTags.Blocks.GHOST_BLOCK))
		{
			if (EntityHelper.isImmuneToGhostBlockCollision(player)) {event.setNewSpeed(Math.min(speed, 0.1F));}
		}
    }

	/*public static void addReach(PlayerInteractEvent.LeftClickBlock event) TODO
	{
		Item item = event.getItemStack().getItem();
		if ((item == AerialHellBlocksAndItems.REAPER_SCYTHE.get() || item == AerialHellBlocksAndItems.FORGOTTEN_BATTLE_TRIDENT.get()) && event.getHand() == InteractionHand.MAIN_HAND)
		{
			Player player = event.getEntity();
			UUID modifierUUID = UUID.fromString("6127DB5B-1AE8-4030-940E-512C1F160890");
			AttributeInstance reachDistance = player.getAttribute(Attributes.BLOCK_INTERACTION_RANGE);
			if (reachDistance != null)
			{
				AttributeModifier modifier = reachDistance.getModifier(modifierUUID);
				double amount = 0;
				if (modifier != null) {amount = modifier.amount(); reachDistance.removeModifier(modifier);}
				AttributeModifier newModifier = new AttributeModifier(modifierUUID, "Tool modifier",amount + 2.0, AttributeModifier.Operation.ADD_VALUE);
			}
		}
	}*/

	public static void applyEffectsDueToPotionEffects(LivingIncomingDamageEvent event, DamageSource damageSource, LivingEntity target) {
		float amount = event.getAmount();
		if ((damageSource.is(DamageTypes.ON_FIRE) || damageSource.is(DamageTypes.ON_FIRE) || damageSource.is(DamageTypes.LAVA)) && target.hasEffect(AerialHellMobEffects.GOD.getDelegate())) {/*event.setCanceled(true); TODO*/event.setAmount(0);} //target with Gods Effect has Fire Resistance
		if (EntityHelper.isLivingEntityVulnerable(target)) {
			int multiplier = target.getEffect(AerialHellMobEffects.VULNERABILITY.getDelegate()).getAmplifier() + 1;
			event.setAmount(amount * 2.0F * multiplier); //*2 *multiplier damage if target is vulnerable
			if (event.getSource().getEntity() instanceof LilithEntity) {event.setAmount(amount * 1.5F * multiplier);} //total *3 *multiplier if source is Lilith boss
		}
	}

	public static void applyEffectsBasedOnTargetHandEquippedItem(LivingIncomingDamageEvent event, Item targetEquippedItem, LivingEntity target) {
		float amount = event.getAmount();
		if (targetEquippedItem == AerialHellBlocksAndItems.GLASS_CANON_SWORD.get()) {event.setAmount(amount * 2.0F);} //*2 damage if target has glass cannon sword
	}

	public static void applyEffectsBasedOnTargetEquippedArmor(LivingIncomingDamageEvent event, Iterable<ItemStack> armorStuff, LivingEntity source, LivingEntity target) {
		float amount = event.getAmount();
		for (ItemStack armorStack : armorStuff) {
			if (armorStack.is(AerialHellTags.Items.MAGMATIC_GEL)) //target equipped of any magmatic gel armor
			{
				if (source instanceof Player) {
					if (!((Player) source).isCreative()) {
						source.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 1, true, false));
					}
				} else {
					source.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, 1, true, false));
				}
			}
			if (armorStack.is(AerialHellTags.Items.ARSONIST)) //target equipped of any arsonist armor
			{
				source.igniteForSeconds(5);
				if (target.getRemainingFireTicks() > 0) //damage reduction if player with arsonist armor is on fire
				{
					event.setAmount(amount * 0.93F);
				}
			}
		}
	}

	public static void applyEffectsBasedOnSourceHandEquippedItem(LivingIncomingDamageEvent event, ItemStack sourceEquippedItemStack, LivingEntity source, LivingEntity target) {
		Item sourceEquippedItem = sourceEquippedItemStack.getItem();
		float amount = event.getAmount();
		if (sourceEquippedItemStack.is(AerialHellTags.Items.MAGMATIC_GEL)) //source attacking target with any magmatic gel tool
		{
			int count = 0;
			for (ItemStack armorStack : source.getArmorSlots()) {if (armorStack.is(AerialHellTags.Items.MAGMATIC_GEL)) {count++;}}
			int amplifier = count == 4 ? 1 : 0;
			target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120, amplifier, true, false));
		} else if (sourceEquippedItem == AerialHellBlocksAndItems.ABSOLUTE_ZERO_SWORD.get()) //source attacking target with absolute zero sword
		{
			target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2, true, false));
		} else if (sourceEquippedItemStack.is(AerialHellTags.Items.ARSONIST)) //source attacking target with any arsonist tool
		{
			target.igniteForSeconds(5);
			if (source.getRemainingFireTicks() > 0) {
				event.setAmount(amount * 1.5F); //damage bonus when on fire
			}
		} else if (sourceEquippedItem == AerialHellBlocksAndItems.DISLOYAL_SWORD.get()) //source attacking target with disloyal sword
		{
			target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0, true, false));
			target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0, true, false));
			target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 0, true, false));
		} else if (sourceEquippedItem == AerialHellBlocksAndItems.GOD_SWORD.get()) //source attacking target with god sword
		{
			target.igniteForSeconds(5);
		} else if (sourceEquippedItem == AerialHellBlocksAndItems.REAPER_SCYTHE.get()) //source attacking target with reaper scythe
		{
			if (!EntityHelper.isLivingEntityShadowImmune(target)) {
				target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 0, true, false));
				target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 1, true, false));
				target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1, true, false));
				target.addEffect(new MobEffectInstance(AerialHellMobEffects.VULNERABILITY.getDelegate(), 70, 0, true, false));
			} else {
				source.addEffect(new MobEffectInstance(MobEffects.WITHER, 80, 2, true, false));
			}
			source.addEffect(new MobEffectInstance(AerialHellMobEffects.VULNERABILITY.getDelegate(), 60, 0, true, false));
		} else if (sourceEquippedItem == AerialHellBlocksAndItems.CURSED_SWORD.get() || sourceEquippedItem == AerialHellBlocksAndItems.CURSED_AXE.get()) //source attacking target with cursed tool
		{
			float damage_return_amount;
			if (EntityHelper.isLivingEntityShadowImmune(source) || EntityHelper.isLivingEntityVulnerable(target)) {damage_return_amount = amount / 2;} else {damage_return_amount = amount;}
			source.hurt(AerialHellDamageTypes.getDamageSource(event.getEntity().level(), AerialHellDamageTypes.CURSED_TOOL), damage_return_amount);
			if (!EntityHelper.isLivingEntityShadowImmune(target)) {
				if (EntityHelper.isLightEntity(target) && !(target instanceof LunaticPriestEntity)) {
					target.addEffect(new MobEffectInstance(AerialHellMobEffects.VULNERABILITY.getDelegate(), 40, 1));
				} else {
					target.addEffect(new MobEffectInstance(AerialHellMobEffects.VULNERABILITY.getDelegate(), 40, 0));
				}
			}
		} else if (sourceEquippedItem == AerialHellBlocksAndItems.SWORD_OF_LIGHT.get() || sourceEquippedItem == AerialHellBlocksAndItems.AXE_OF_LIGHT.get() || sourceEquippedItem == AerialHellBlocksAndItems.LUNATIC_SWORD.get() || sourceEquippedItem == AerialHellBlocksAndItems.LUNATIC_AXE.get() || sourceEquippedItem == AerialHellBlocksAndItems.LUNATIC_HOE.get() || sourceEquippedItem == AerialHellBlocksAndItems.LUNATIC_SHOVEL.get() || sourceEquippedItem == AerialHellBlocksAndItems.LUNATIC_PICKAXE.get() || sourceEquippedItem == AerialHellBlocksAndItems.STELLAR_STONE_BREAKER.get()) //source attacking target with light tool
		{
			if (EntityHelper.isShadowEntity(target)) {
				if (sourceEquippedItem == AerialHellBlocksAndItems.SWORD_OF_LIGHT.get() || sourceEquippedItem == AerialHellBlocksAndItems.AXE_OF_LIGHT.get()) {event.setAmount(amount * 1.8F);} else {event.setAmount(amount * 1.4F);}
			}
		} else if (sourceEquippedItem == AerialHellBlocksAndItems.NETHERIAN_KING_SWORD.get() && source.level().dimension() == Level.NETHER) {
			event.setAmount(amount * 2.0F);
		}
	}

	public static void applyTraitorEffectIfNecessary(Player source, LivingEntity target) {
		if (source.isCreative()) {return;}
		if ((target instanceof CrystalGolemEntity || target instanceof LunaticPriestEntity) && EntityHelper.isLivingEntityMisleadingLunar(source)) {
			source.addEffect(new MobEffectInstance(AerialHellMobEffects.TRAITOR.getDelegate(), 12000, 0));
		} else if ((target instanceof ShadowAutomatonEntity || target instanceof LilithEntity) && EntityHelper.isLivingEntityMisleadingShadow(source)) {
			source.addEffect(new MobEffectInstance(AerialHellMobEffects.TRAITOR.getDelegate(), 12000, 0));
		}
	}
}
