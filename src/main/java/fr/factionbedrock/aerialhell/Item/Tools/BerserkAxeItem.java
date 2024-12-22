package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BerserkAxeItem extends EffectAxeItem
{
	private int weight_ticks;
	
	public BerserkAxeItem(ToolMaterial toolMaterial, Item.Settings settings)
	{
		super(toolMaterial, settings);
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
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
		ItemStack heldItem = player.getStackInHand(hand);
		Random rand = new Random();
		int cooldown = Math.min(getStatus() + 1, 3) * 200;
		
		if (weight_ticks >= 200) {this.weight_ticks -= 200;}
		else {this.weight_ticks = 0;}
		
		for (int i=0 ; i<20; i++)
		{
			world.addParticle(ParticleTypes.SMOKE, player.getX() + 4*(rand.nextFloat() - 0.5F), player.getY() + 4*rand.nextFloat(), player.getZ() + 4*(rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
		}
		player.playSound(SoundEvents.ENTITY_RAVAGER_ROAR, 1.0F, 0.5F + rand.nextFloat());
		if (world.isClient()) //TODO update this dirty code
		{
			Vec3d forward = player.getRotationVecClient().multiply(1.7,1.3,1.7);
			if (forward.y < 1) {forward = new Vec3d(forward.x, 1, forward.z);}
			player.setVelocity(player.getVelocity().add(forward));
		}

		player.getItemCooldownManager().set(this, cooldown);
		heldItem.damage(1, player, LivingEntity.getSlotForHand(hand));
        return TypedActionResult.consume(heldItem);
	}
	
	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker)
	{
		this.increaseWeight();
		return super.postHit(stack, target, attacker);
	}
	
	@Override
	public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity player)
	{
		return !player.isCreative();
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		if (this.weight_ticks > 800)
		{
			this.weight_ticks--;
		}
		else if (this.weight_ticks > 0)
		{
			this.weight_ticks-=2;
		}
		if (isSelected) {giveEntityEffect(world, entity);}
	}
	
	private void giveEntityEffect(World world, Entity entityIn)
	{
		if (!world.isClient() && entityIn instanceof LivingEntity)
		{
			LivingEntity livingEntityIn = (LivingEntity) entityIn;
			int weight = this.getStatus();
			if (weight == 0)
			{
				livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 22, 1, false, false));
				livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 22, 1, false, false));
				livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 22, 1, false, false));
			}
			else if (weight == 1)
			{
				livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 22, 0, false, false));
				livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 22, 0, false, false));
			}
			else if (weight == 2)
			{
				livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 22, 0, false, false));
			}
			else if (weight == 3)
			{
				livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 22, 0, false, false));
				livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 22, 1, false, false));
			}
			else //(weight == 4)
			{
				livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 22, 0, false, false));
				livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 22, 1, false, false));
				livingEntityIn.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 22, 3, false, false));
			}
		}
	}

	@Override public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type)
	{
		tooltip.add(this.getDescription().append(Integer.toString(getStatus())).formatted(Formatting.GRAY));
	}

	public MutableText getDescription() {return Text.translatable(this.getTranslationKey()+".desc");}

	@Override public boolean canRepair(ItemStack stack, ItemStack ingredient) {return false;}
}
