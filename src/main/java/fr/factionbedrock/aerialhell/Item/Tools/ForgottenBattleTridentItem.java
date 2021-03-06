package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ForgottenBattleTridentItem extends AerialHellSwordItem
{	
	public ForgottenBattleTridentItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float movementSpeedIn, Properties builderIn)
	{
		super(tier, attackDamageIn, attackSpeedIn, movementSpeedIn, 0.0F, builderIn);
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
		ItemStack heldItem = playerIn.getHeldItem(handIn);
		Random rand = new Random();

		for (int i=0 ; i<20; i++)
		{
			worldIn.addParticle(ParticleTypes.DRIPPING_WATER, playerIn.getPosX() + 4*(rand.nextFloat() - 0.5F), playerIn.getPosY() + 4*rand.nextFloat(), playerIn.getPosZ() + 4*(rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
		}
		playerIn.playSound(AerialHellSoundEvents.ITEM_FORGOTTEN_BATTLE_TRIDENT_USE.get(), 1.0F, 1.5F);
		
		if (!worldIn.isRemote)
		{
			playerIn.addPotionEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 120, 0));
			playerIn.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 120, 0));
			playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 120, 0));
			playerIn.addPotionEffect(new EffectInstance(Effects.STRENGTH, 300, 0));
		}
		playerIn.getCooldownTracker().setCooldown(this, 540);
		heldItem.damageItem(1, playerIn, (player) -> {player.sendBreakAnimation(playerIn.getActiveHand());});
		return ActionResult.resultConsume(heldItem);
    }
	
	@Override @OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(this.getDescription().mergeStyle(TextFormatting.GRAY));
	}

	@OnlyIn(Dist.CLIENT)
	public IFormattableTextComponent getDescription()
	{
		return new TranslationTextComponent(this.getTranslationKey() + ".desc");
	}
}
