package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.List;
import java.util.Random;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.ItemHelper;

public class ForgottenBattleTridentItem extends AerialHellSwordItem
{	
	public ForgottenBattleTridentItem(Tier toolMaterial, Item.Properties settings) {super(toolMaterial, settings);}
	
	@Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
		ItemStack heldItem = player.getItemInHand(hand);
		Random rand = new Random();

		for (int i=0 ; i<20; i++)
		{
			world.addParticle(ParticleTypes.DRIPPING_WATER, player.getX() + 4*(rand.nextFloat() - 0.5F), player.getY() + 4*rand.nextFloat(), player.getZ() + 4*(rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
		}
		player.playSound(AerialHellSoundEvents.ITEM_FORGOTTEN_BATTLE_TRIDENT_USE, 1.0F, 1.5F);
		
		if (!world.isClientSide())
		{
			player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 120, 0));
			player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 120, 0));
			player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 0));
			player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 0));
		}
		player.getCooldowns().addCooldown(this, 540);
		heldItem.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
		return InteractionResultHolder.consume(heldItem);
    }

	@Override public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type)
	{
		ItemHelper.appendItemTooltip(this.getDescriptionId(), tooltip);
	}
}
