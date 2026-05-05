package fr.factionbedrock.aerialhell.Event.Listeners;

import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowAutomatonEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Shadow.ShadowTrollEntity;
import fr.factionbedrock.aerialhell.Item.Ability.DamageUseSituationInfo;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.*;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import javax.annotation.Nullable;

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
		@Nullable Entity sourceEntity = damageSource.getEntity();
		LivingEntity target = event.getEntity();

		MutableFloat damageMultiplier = new MutableFloat(1.0F);

		if (sourceEntity instanceof LivingEntity attacker)
		{
			ItemHelper.forEachAerialHellItem(EntityHelper.getEquippepItemStackList(attacker), (ahItem, equippedItemStack) ->
			{
				//attacker is the ah item owner, dealing damage to target (enemy)
				ahItem.onDealDamage(equippedItemStack.stack(), attacker, equippedItemStack.slot(), new DamageUseSituationInfo(target, damageSource, new FieldAccessor<>(damageMultiplier::get, damageMultiplier::set)));
			});
		}

		ItemHelper.forEachAerialHellItem(EntityHelper.getEquippepItemStackList(target), (ahItem, equippedItemStack) ->
		{
			//target is the ah item owner, receiving damage from attacker (enemy)
			ahItem.onTakeDamage(equippedItemStack.stack(), target, equippedItemStack.slot(), new DamageUseSituationInfo(sourceEntity, damageSource, new FieldAccessor<>(damageMultiplier::get, damageMultiplier::set)));
		});

		DebugHelper.sendDebugMessage(target.level(), "multiplier = "+damageMultiplier.get());
		event.setAmount(event.getAmount() * damageMultiplier.get());

		applyEffectsDueToPotionEffects(event, damageSource, target);
    }

    public static void onPlayerHarvest(PlayerEvent.BreakSpeed event)
    {
		Player player = event.getEntity();
		ItemStack selectedItemStack = player.getInventory().getSelectedItem();
		BlockState state = event.getState();
		float speed = event.getOriginalSpeed();

		//player on fire and mining with any arsonist item
		if (state != null && selectedItemStack.is(AerialHellTags.Items.ARSONIST) && player.getRemainingFireTicks() > 0) {
			event.setNewSpeed(speed * 2.0F);
		}

		//player mining stellar stone with stellar stone breaker
		else if (selectedItemStack.getItem() == AerialHellItems.STELLAR_STONE_BREAKER.get() && state.getBlock() == AerialHellBlocks.STELLAR_STONE.get()) {
			event.setNewSpeed(speed * 2.0F);
		}

		//player mining a block that needs lunar tool
		if (state != null && state.is(AerialHellTags.Blocks.NEEDS_LUNAR_TOOL))
		{
			if (!BlockHelper.isItemCorrectForHarvesting(state, selectedItemStack.getItem()))
			{
				if (state.is(AerialHellBlocks.VOLUCITE_ORE) && !player.level().isClientSide())
				{
					player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 40, 0));
				}
			}
		}

		if (state != null && state.is(AerialHellBlocks.EYE_SHADOW_PINE_LOG.get()) && !EntityHelper.isLivingEntityShadowImmune(player) && !player.isCreative())
		{
			player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 30, 0));
			player.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 30, 0));
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

	public static void applyEffectsDueToPotionEffects(LivingIncomingDamageEvent event, DamageSource damageSource, LivingEntity target)
	{
		float amount = event.getAmount();
		if ((damageSource.is(DamageTypes.ON_FIRE) || damageSource.is(DamageTypes.ON_FIRE) || damageSource.is(DamageTypes.LAVA)) && target.hasEffect(AerialHellMobEffects.GOD.getDelegate())) {/*event.setCanceled(true); TODO*/event.setAmount(0);} //target with Gods Effect has Fire Resistance
		if (EntityHelper.isLivingEntityVulnerable(target))
		{
			int multiplier = target.getEffect(AerialHellMobEffects.VULNERABILITY.getDelegate()).getAmplifier() + 1;
			event.setAmount(amount * 2.0F * multiplier); //*2 *multiplier damage if target is vulnerable
			if (event.getSource().getEntity() instanceof LilithEntity) {event.setAmount(amount * 1.5F * multiplier);} //total *3 *multiplier if source is Lilith boss
		}
	}
}
