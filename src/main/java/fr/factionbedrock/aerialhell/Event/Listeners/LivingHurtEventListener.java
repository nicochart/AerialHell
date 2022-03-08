package fr.factionbedrock.aerialhell.Event.Listeners;

import fr.factionbedrock.aerialhell.Registry.AerialHellTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class LivingHurtEventListener
{
	@SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event)
    {
		Entity sourceEntity = event.getSource().getTrueSource();
		LivingEntity target = event.getEntityLiving();
		
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
			}
			
			if (source instanceof PlayerEntity)
			{
				Item mainHandItem = ((PlayerEntity) source).getHeldItemMainhand().getItem();
				if (mainHandItem.isIn(AerialHellTags.Items.MAGMATIC_GEL)) //source (player) attacking LivingEntity with any magmatic gel tool
				{
					target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 120, 1, true, false)));
				}
			}
			else
			{
				Iterable<ItemStack> handStuff = source.getHeldEquipment();
				for (ItemStack handItemStack : handStuff)
				{
					if (handItemStack.getItem().isIn(AerialHellTags.Items.MAGMATIC_GEL)) //source (any living entity) attacking LivingEntity with any magmatic gel tool
					{
						target.addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 120, 1, true, false)));
					}
				}
			}
		}
    }
}
