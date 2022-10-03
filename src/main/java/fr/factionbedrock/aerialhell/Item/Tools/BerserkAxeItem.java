package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BerserkAxeItem extends EffectAxeItem
{
	private int weight_ticks;
	
	public BerserkAxeItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, float movementSpeedIn,	float maxHealthIn, Properties builderIn)
	{
		super(tier, attackDamageIn, attackSpeedIn, movementSpeedIn, maxHealthIn, builderIn);
		this.weight_ticks = 0;
	}
	
	public int getStatus()
	{
		if (weight_ticks == 0)
		{
			return 0;
		}
		else if (weight_ticks <= 200)
		{
			return 1;
		}
		else if (weight_ticks <= 600)
		{
			return 2;
		}
		else if (weight_ticks <= 800)
		{
			return 3;
		}
		else
		{
			return 4;
		}
	}
	
	protected void increaseWeight()
	{
		if (weight_ticks <= 200)
		{
			weight_ticks += 100;
		}
		else if (weight_ticks <= 1200)
		{
			weight_ticks += 200;
		}
	}
	
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
		ItemStack heldItem = playerIn.getHeldItem(handIn);
		Random rand = new Random();
		int cooldown = Math.min(getStatus() + 1, 3) * 200;
		
		if (weight_ticks >= 200) {this.weight_ticks -= 200;}
		else {this.weight_ticks = 0;}
		
		for (int i=0 ; i<20; i++)
		{
			worldIn.addParticle(ParticleTypes.SMOKE, playerIn.getPosX() + 4*(rand.nextFloat() - 0.5F), playerIn.getPosY() + 4*rand.nextFloat(), playerIn.getPosZ() + 4*(rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
		}
		playerIn.playSound(SoundEvents.ENTITY_RAVAGER_ROAR, 1.0F, 0.5F + rand.nextFloat());
		if (worldIn.isRemote)
		{
			Vector3d forward = playerIn.getForward().mul(1.7,1.3,1.7);
			if (forward.getY() < 1) {forward = new Vector3d(forward.getX(), 1, forward.getZ());}
			playerIn.setMotion(playerIn.getMotion().add(forward));
		}
		
		playerIn.getCooldownTracker().setCooldown(this, cooldown);
		heldItem.damageItem(1, playerIn, (player) -> {player.sendBreakAnimation(playerIn.getActiveHand());});
        return ActionResult.resultConsume(heldItem);
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
	{
		
		this.increaseWeight();
		return super.hitEntity(stack, target, attacker);
	}
	
	@Override
	public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player)
	{
		return !player.isCreative();
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if (this.weight_ticks > 800)
		{
			this.weight_ticks--;
		}
		else if (this.weight_ticks > 0)
		{
			this.weight_ticks-=2;
		}
		if (isSelected) {giveEntityEffect(worldIn, entityIn);}
	}
	
	private void giveEntityEffect(World worldIn, Entity entityIn)
	{
		if (!worldIn.isRemote && entityIn instanceof LivingEntity)
		{
			LivingEntity livingEntityIn = (LivingEntity) entityIn;
			int weight = this.getStatus();
			if (weight == 0)
			{
				livingEntityIn.addPotionEffect(new EffectInstance(Effects.STRENGTH, 22, 1, false, false));
				livingEntityIn.addPotionEffect(new EffectInstance(Effects.SPEED, 22, 1, false, false));
				livingEntityIn.addPotionEffect(new EffectInstance(Effects.HASTE, 22, 1, false, false));
			}
			else if (weight == 1)
			{
				livingEntityIn.addPotionEffect(new EffectInstance(Effects.SPEED, 22, 0, false, false));
				livingEntityIn.addPotionEffect(new EffectInstance(Effects.HASTE, 22, 0, false, false));
			}
			else if (weight == 2)
			{
				livingEntityIn.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 22, 0, false, false));
			}
			else if (weight == 3)
			{
				livingEntityIn.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 22, 0, false, false));
				livingEntityIn.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 22, 1, false, false));
			}
			else //(weight == 4)
			{
				livingEntityIn.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 22, 0, false, false));
				livingEntityIn.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 22, 1, false, false));
				livingEntityIn.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 22, 3, false, false));
			}
		}
	}
	
	@Override @OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(this.getDescription().appendString(Integer.toString(getStatus())).mergeStyle(TextFormatting.GRAY));
	}

	@Override @OnlyIn(Dist.CLIENT)
	public IFormattableTextComponent getDescription()
	{
		return new TranslationTextComponent(this.getTranslationKey() + ".desc");
	}
}
