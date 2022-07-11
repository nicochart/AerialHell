package fr.factionbedrock.aerialhell.Event.Listeners;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
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
		Entity sourceEntity = event.getSource().getTrueSource();
		LivingEntity target = event.getEntityLiving();
		float amount = event.getAmount(); //damage
		
		if (sourceEntity instanceof LivingEntity)
		{
			LivingEntity source = (LivingEntity) sourceEntity;
			Iterable<ItemStack> stuff = target.getArmorInventoryList();
			for (ItemStack armorStack : stuff)
			{
				if (armorStack.getItem().isIn(AerialHellTags.Items.MAGMATIC_GEL)) //source attacking LivingEntity equiped of any magmatic gel armor
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
				if (armorStack.getItem().isIn(AerialHellTags.Items.ARSONIST)) //source attacking LivingEntity equiped of any arsonist armor
				{
					source.setFire(5);
					if (target.getFireTimer() > 0) //damage reduction if player with arsonist armor is on fire
					{
						event.setAmount(amount * 0.93F);
					}
				}
			}
			
			if (source instanceof PlayerEntity)
			{
				Item mainHandItem = ((PlayerEntity) source).getHeldItemMainhand().getItem();
				if (mainHandItem.isIn(AerialHellTags.Items.MAGMATIC_GEL)) //source (player) attacking LivingEntity with any magmatic gel tool
				{
					int count = 0;
					for (ItemStack armorStack : source.getArmorInventoryList()) {if (armorStack.getItem().isIn(AerialHellTags.Items.MAGMATIC_GEL)) {count++;}}
					int amplifier = count == 4 ? 1 : 0;
					target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 120, amplifier, true, false)));
				}
				else if (mainHandItem == AerialHellBlocksAndItems.ABSOLUTE_ZERO_SWORD.get()) //source (player) attacking LivingEntity with absolute zero sword
				{
					target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 100, 2, true, false)));
				}
				else if (mainHandItem.isIn(AerialHellTags.Items.ARSONIST)) //source (player) attacking LivingEntity with any arsonist tool
				{
					target.setFire(5);
					if (source.getFireTimer() > 0)
					{
						event.setAmount(amount * 1.5F); //damage bonus when on fire
					}
				}
				else if (mainHandItem == AerialHellBlocksAndItems.DISLOYAL_SWORD.get()) //source (player) attacking LivingEntity with disloyal sword
				{
					target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 100, 0, true, false)));
					target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.WEAKNESS, 100, 0, true, false)));
					target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.MINING_FATIGUE, 100, 0, true, false)));
				}
				else if (mainHandItem == AerialHellBlocksAndItems.GOD_SWORD.get()) //source (player) attacking LivingEntity with god sword
				{
					target.setFire(5);
				}
			}
			else
			{
				Iterable<ItemStack> handStuff = source.getHeldEquipment();
				for (ItemStack handItemStack : handStuff)
				{
					if (handItemStack.getItem().isIn(AerialHellTags.Items.MAGMATIC_GEL)) //source (any living entity) attacking LivingEntity with any magmatic gel tool
					{
						int count = 0;
						for (ItemStack armorStack : source.getArmorInventoryList()) {if (armorStack.getItem().isIn(AerialHellTags.Items.MAGMATIC_GEL)) {count++;}}
						int amplifier = count == 4 ? 1 : 0;
						target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 120, amplifier, true, false)));
					}
					else if (handItemStack.getItem() == AerialHellBlocksAndItems.ABSOLUTE_ZERO_SWORD.get()) //source (any living entity) attacking LivingEntity with absolute zero sword
					{
						target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 100, 2, true, false)));
					}
					else if (handItemStack.getItem() == AerialHellBlocksAndItems.DISLOYAL_SWORD.get()) //source (any living entity) attacking LivingEntity with disloyal sword
					{
						target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 100, 0, true, false)));
						target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.WEAKNESS, 100, 0, true, false)));
						target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.MINING_FATIGUE, 100, 0, true, false)));
					}
					else if (handItemStack.getItem().isIn(AerialHellTags.Items.ARSONIST)) //source (any living entity) attacking LivingEntity with any arsonist tool
					{
						target.setFire(5);
						if (source.getFireTimer() > 0)
						{
							event.setAmount(amount * 1.5F); //damage bonus when on fire
						}
					}
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
    }
}
