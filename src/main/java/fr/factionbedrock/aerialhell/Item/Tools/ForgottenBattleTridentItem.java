package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;
import java.util.function.Consumer;

import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class ForgottenBattleTridentItem extends AerialHellSwordItem
{
	public ForgottenBattleTridentItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Settings settings)
	{
		super(toolMaterial, attackDamage, attackSpeed, movementSpeed, maxHealth, settings);
	}
	
	@Override
    public ActionResult use(World world, PlayerEntity player, Hand hand)
    {
		ItemStack heldItem = player.getStackInHand(hand);
		Random rand = new Random();

		for (int i=0 ; i<20; i++)
		{
			world.addParticleClient(ParticleTypes.DRIPPING_WATER, player.getX() + 4*(rand.nextFloat() - 0.5F), player.getY() + 4*rand.nextFloat(), player.getZ() + 4*(rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
		}
		player.playSound(AerialHellSoundEvents.ITEM_FORGOTTEN_BATTLE_TRIDENT_USE, 1.0F, 1.5F);
		
		if (!world.isClient())
		{
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 120, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 120, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 120, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 300, 0));
		}
		player.getItemCooldownManager().set(heldItem, 540);
		heldItem.damage(1, player, LivingEntity.getSlotForHand(hand));
		return ActionResult.CONSUME;
    }

	@Override public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type)
	{
		ItemHelper.appendItemTooltip(this.getTranslationKey(), textConsumer);
	}
}
