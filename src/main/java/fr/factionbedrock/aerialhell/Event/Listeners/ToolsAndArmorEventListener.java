package fr.factionbedrock.aerialhell.Event.Listeners;

import java.util.UUID;

import fr.factionbedrock.aerialhell.Entity.Bosses.LilithEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.LunaticPriestEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ToolsAndArmorEventListener
{
	@SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event)
    {
		DamageSource damageSource = event.getSource();
		Entity sourceEntity = damageSource.getTrueSource();
		LivingEntity target = event.getEntityLiving();
		
		applyEffectsDueToPotionEffects(event, damageSource, target);
		
		if (target instanceof PlayerEntity) //*2 damage if target has glass cannon sword
		{
			Item targetMainHandItem = ((PlayerEntity) target).getHeldItemMainhand().getItem();
			applyEffectsBasedOnTargetHandEquippedItem(event, targetMainHandItem, target);
		}
		else
		{
			Iterable<ItemStack> handStuff = target.getHeldEquipment();
			for (ItemStack handItemStack : handStuff)
			{
				applyEffectsBasedOnTargetHandEquippedItem(event, handItemStack.getItem(), target);
			}
		}	
		
		if (sourceEntity instanceof LivingEntity)
		{
			LivingEntity source = (LivingEntity) sourceEntity;
			Iterable<ItemStack> stuff = target.getArmorInventoryList();
			applyEffectsBasedOnTargetEquippedArmor(event, stuff, source, target);
			
			if (source instanceof PlayerEntity)
			{
				Item mainHandItem = ((PlayerEntity) source).getHeldItemMainhand().getItem();
				applyEffectsBasedOnSourceHandEquippedItem(event, mainHandItem, source, target);
			}
			else
			{
				Iterable<ItemStack> handStuff = source.getHeldEquipment();
				for (ItemStack handItemStack : handStuff)
				{
					applyEffectsBasedOnSourceHandEquippedItem(event, handItemStack.getItem(), source, target);
				}
			}
		}
    }
	
	@SubscribeEvent
    public static void onPlayerHarvest(PlayerEvent.BreakSpeed event)
    {
		PlayerEntity player = event.getPlayer();
		BlockState state = event.getState();
		float speed = event.getOriginalSpeed();
		
		//player on fire and mining with any arsonist item
		if (state != null && player.getHeldItemMainhand().getItem().isIn(AerialHellTags.Items.ARSONIST) && player.getFireTimer() > 0)
		{
			event.setNewSpeed(speed * 2.0F);
		}
		
		//player mining stellar stone with stellar stone breaker
		else if (player.getHeldItemMainhand().getItem() == AerialHellBlocksAndItems.STELLAR_STONE_BREAKER.get() && state.getBlock() == AerialHellBlocksAndItems.STELLAR_STONE.get())
		{
			event.setNewSpeed(speed * 2.0F);
		}
    }
	
	@SubscribeEvent
	public static void addReach(ItemAttributeModifierEvent event)
	{
		Item item = event.getItemStack().getItem();
		if ((item == AerialHellBlocksAndItems.REAPER_SCYTHE.get() || item == AerialHellBlocksAndItems.FORGOTTEN_BATTLE_TRIDENT.get()) && event.getSlotType() == EquipmentSlotType.MAINHAND)
		{
			event.addModifier(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(UUID.fromString("6127DB5B-1AE8-4030-940E-512C1F160890"), "Tool modifier", 2.0, AttributeModifier.Operation.ADDITION));
		}
	}
	
	
	
	public static void applyEffectsDueToPotionEffects(LivingHurtEvent event, DamageSource damageSource, LivingEntity target)
	{
		if (damageSource.isFireDamage() && target.isPotionActive(AerialHellPotionEffects.GOD.get())) {event.setCanceled(true);} //target with Gods Effect has Fire Resistance
	}
	
	public static void applyEffectsBasedOnTargetHandEquippedItem(LivingHurtEvent event, Item targetEquippedItem, LivingEntity target)
	{
		float amount = event.getAmount();
		if (targetEquippedItem == AerialHellBlocksAndItems.GLASS_CANON_SWORD.get()) {event.setAmount(amount * 2.0F);} //*2 damage if target has glass cannon sword
		if (EntityHelper.isLivingEntityVulnerable(target))
		{
			int multiplier = target.getActivePotionEffect(AerialHellPotionEffects.VULNERABILITY.get()).getAmplifier() + 1;
			event.setAmount(amount * 2.0F * multiplier); //*2 *multiplier damage if target is vulnerable
			if (event.getSource().getTrueSource() instanceof LilithEntity) {event.setAmount(amount * 2.0F * multiplier);} //total *4 *multiplier if source is Lilith boss
		}
	}
	
	public static void applyEffectsBasedOnTargetEquippedArmor(LivingHurtEvent event, Iterable<ItemStack> armorStuff, LivingEntity source, LivingEntity target)
	{
		float amount = event.getAmount();
		for (ItemStack armorStack : armorStuff)
		{
			if (armorStack.getItem().isIn(AerialHellTags.Items.MAGMATIC_GEL)) //target equipped of any magmatic gel armor
			{
				if (source instanceof PlayerEntity)
				{
					if (!((PlayerEntity) source).isCreative())
					{
						source.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 120, 1, true, false)));
					}
				}
				else
				{
					source.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 120, 1, true, false)));
				}
			}
			if (armorStack.getItem().isIn(AerialHellTags.Items.ARSONIST)) //target equipped of any arsonist armor
			{
				source.setFire(5);
				if (target.getFireTimer() > 0) //damage reduction if player with arsonist armor is on fire
				{
					event.setAmount(amount * 0.93F);
				}
			}
		}
	}
	
	public static void applyEffectsBasedOnSourceHandEquippedItem(LivingHurtEvent event, Item sourceEquippedItem, LivingEntity source, LivingEntity target)
	{
		float amount = event.getAmount();
		if (sourceEquippedItem.isIn(AerialHellTags.Items.MAGMATIC_GEL)) //source attacking target with any magmatic gel tool
		{
			int count = 0;
			for (ItemStack armorStack : source.getArmorInventoryList()) {if (armorStack.getItem().isIn(AerialHellTags.Items.MAGMATIC_GEL)) {count++;}}
			int amplifier = count == 4 ? 1 : 0;
			target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 120, amplifier, true, false)));
		}
		else if (sourceEquippedItem == AerialHellBlocksAndItems.ABSOLUTE_ZERO_SWORD.get()) //source attacking target with absolute zero sword
		{
			target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 100, 2, true, false)));
		}
		else if (sourceEquippedItem.isIn(AerialHellTags.Items.ARSONIST)) //source attacking target with any arsonist tool
		{
			target.setFire(5);
			if (source.getFireTimer() > 0)
			{
				event.setAmount(amount * 1.5F); //damage bonus when on fire
			}
		}
		else if (sourceEquippedItem == AerialHellBlocksAndItems.DISLOYAL_SWORD.get()) //source attacking target with disloyal sword
		{
			target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 100, 0, true, false)));
			target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.WEAKNESS, 100, 0, true, false)));
			target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.MINING_FATIGUE, 100, 0, true, false)));
		}
		else if (sourceEquippedItem == AerialHellBlocksAndItems.GOD_SWORD.get()) //source attacking target with god sword
		{
			target.setFire(5);
		}
		else if (sourceEquippedItem == AerialHellBlocksAndItems.REAPER_SCYTHE.get()) //source attacking target with reaper scythe
		{
			if (!EntityHelper.isLivingEntityShadowImmune(target))
			{
				target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.BLINDNESS, 100, 0, true, false)));
				target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.WEAKNESS, 100, 1, true, false)));
				target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 100, 1, true, false)));
				target.addPotionEffect(new EffectInstance(new EffectInstance(AerialHellPotionEffects.VULNERABILITY.get(), 70, 0, true, false)));
			}
			else
			{
				source.addPotionEffect(new EffectInstance(new EffectInstance(Effects.WITHER, 80, 2, true, false)));
			}
			source.addPotionEffect(new EffectInstance(new EffectInstance(AerialHellPotionEffects.VULNERABILITY.get(), 60, 0, true, false)));
		}
		else if (sourceEquippedItem == AerialHellBlocksAndItems.CURSED_SWORD.get() || sourceEquippedItem == AerialHellBlocksAndItems.CURSED_AXE.get()) //source attacking target with cursed tool
		{
			float damage_return_amount;
			if (EntityHelper.isLivingEntityShadowImmune(source) || EntityHelper.isLivingEntityVulnerable(target)) {damage_return_amount = amount / 4;}
			else {damage_return_amount = amount / 2;}
			source.attackEntityFrom(new DamageSource("cursed_tool"), damage_return_amount);
			if (!EntityHelper.isLivingEntityShadowImmune(target))
			{
				if (EntityHelper.isLightEntity(target) && !(target instanceof LunaticPriestEntity))
				{
					target.addPotionEffect(new EffectInstance(AerialHellPotionEffects.VULNERABILITY.get(), 40, 1));
				}
				else
				{
					target.addPotionEffect(new EffectInstance(AerialHellPotionEffects.VULNERABILITY.get(), 40, 0));
				}

			}
		}
		else if (sourceEquippedItem == AerialHellBlocksAndItems.NETHERIAN_KING_SWORD.get() && source.getEntityWorld().getDimensionKey() == World.THE_NETHER)
		{
			event.setAmount(amount * 2.0F);
		}
	}
}
