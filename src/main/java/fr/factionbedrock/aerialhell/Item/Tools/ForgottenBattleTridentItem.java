package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;
import java.util.function.Consumer;

import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ToolMaterial;
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

public class ForgottenBattleTridentItem extends AerialHellSwordItem
{	
	public ForgottenBattleTridentItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Properties properties)
	{
		super(toolMaterial, attackDamage, attackSpeed, movementSpeed, maxHealth, properties);
	}
	
	@Override
    public InteractionResult use(Level worldIn, Player playerIn, InteractionHand handIn)
    {
		ItemStack heldItem = playerIn.getItemInHand(handIn);
		Random rand = new Random();

		for (int i=0 ; i<20; i++)
		{
			worldIn.addParticle(ParticleTypes.DRIPPING_WATER, playerIn.getX() + 4*(rand.nextFloat() - 0.5F), playerIn.getY() + 4*rand.nextFloat(), playerIn.getZ() + 4*(rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
		}
		playerIn.playSound(AerialHellSoundEvents.ITEM_FORGOTTEN_BATTLE_TRIDENT_USE.get(), 1.0F, 1.5F);
		
		if (!worldIn.isClientSide())
		{
			playerIn.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 120, 0));
			playerIn.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 120, 0));
			playerIn.addEffect(new MobEffectInstance(MobEffects.SPEED, 120, 0));
			playerIn.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 300, 0));
		}
		playerIn.getCooldowns().addCooldown(heldItem, 540);
		heldItem.hurtAndBreak(1, playerIn, handIn);
		return InteractionResult.CONSUME;
    }

	@Override public void appendHoverText(ItemStack stack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag)
	{
		tooltipAdder.accept(this.getDescription().withStyle(ChatFormatting.GRAY));
	}

	public MutableComponent getDescription()
	{
		return Component.translatable(this.getDescriptionId() + ".desc");
	}
}
