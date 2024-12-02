package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.List;
import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ForgottenBattleTridentItem extends AerialHellSwordItem
{	
	public ForgottenBattleTridentItem(ToolMaterial toolMaterial, Item.Settings settings) {super(toolMaterial, settings);}
	
	@Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
		ItemStack heldItem = player.getStackInHand(hand);
		Random rand = new Random();

		for (int i=0 ; i<20; i++)
		{
			world.addParticle(ParticleTypes.DRIPPING_WATER, player.getX() + 4*(rand.nextFloat() - 0.5F), player.getY() + 4*rand.nextFloat(), player.getZ() + 4*(rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
		}
		player.playSound(AerialHellSoundEvents.ITEM_FORGOTTEN_BATTLE_TRIDENT_USE, 1.0F, 1.5F);
		
		if (!world.isClient())
		{
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 120, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 120, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 120, 0));
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 300, 0));
		}
		player.getItemCooldownManager().set(this, 540);
		heldItem.damage(1, player, LivingEntity.getSlotForHand(hand));
		return TypedActionResult.consume(heldItem);
    }

	@Override public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type)
	{
		tooltip.add(this.getDescription().formatted(Formatting.GRAY));
	}

	public MutableText getDescription() {return Text.translatable(this.getTranslationKey()+".desc");}
}
