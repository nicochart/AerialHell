package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ForgottenBattleTridentItem extends AerialHellSwordItem
{	
	public ForgottenBattleTridentItem(Tier tier, int attackDamageIn, float attackSpeedIn, float movementSpeedIn, Properties builderIn)
	{
		super(tier, attackDamageIn, attackSpeedIn, movementSpeedIn, 0.0F, builderIn);
	}
	
	@Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn)
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
			playerIn.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 120, 0));
			playerIn.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 300, 0));
		}
		playerIn.getCooldowns().addCooldown(this, 540);
		heldItem.hurtAndBreak(1, playerIn, (player) -> {player.broadcastBreakEvent(playerIn.getUsedItemHand());});
		return InteractionResultHolder.consume(heldItem);
    }
	
	@Override @OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		tooltip.add(this.getDescription().withStyle(ChatFormatting.GRAY));
	}

	@OnlyIn(Dist.CLIENT)
	public TranslatableComponent getDescription()
	{
		return new TranslatableComponent(this.getDescriptionId() + ".desc");
	}
}
