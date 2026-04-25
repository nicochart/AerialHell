package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

public class ForgottenBattleTridentItem extends AerialHellToolItem
{	
	public ForgottenBattleTridentItem(Properties properties) {super(properties);}
	
	@Override public InteractionResult use(Level level, Player player, InteractionHand hand)
    {
		ItemStack heldItem = player.getItemInHand(hand);
		Random rand = new Random();

		for (int i=0 ; i<20; i++)
		{
			level.addParticle(ParticleTypes.DRIPPING_WATER, player.getX() + 4*(rand.nextFloat() - 0.5F), player.getY() + 4*rand.nextFloat(), player.getZ() + 4*(rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
		}
		player.playSound(AerialHellSoundEvents.ITEM_FORGOTTEN_BATTLE_TRIDENT_USE.get(), 1.0F, 1.5F);
		
		if (!level.isClientSide())
		{
			player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 120, 0));
			player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 120, 0));
			player.addEffect(new MobEffectInstance(MobEffects.SPEED, 120, 0));
			player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 300, 0));
		}
		player.getCooldowns().addCooldown(heldItem, 540);
		heldItem.hurtAndBreak(1, player, hand);
		return InteractionResult.CONSUME;
    }

	@Override public void appendHoverText(ItemStack stack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag)
	{
		tooltipAdder.accept(this.getDescription().withStyle(ChatFormatting.GRAY));
	}

	public MutableComponent getDescription() {return Component.translatable(this.getDescriptionId() + ".desc");}
}
