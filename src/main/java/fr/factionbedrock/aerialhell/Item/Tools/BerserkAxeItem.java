package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.block.BlockState;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BerserkAxeItem extends EffectAxeItem
{
	private int weight_ticks;

	public BerserkAxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Settings settings)
	{
		super(toolMaterial, attackDamage, attackSpeed, 0.0F, 0.0F, settings);
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
    public ActionResult use(World world, PlayerEntity player, Hand hand)
    {
		ItemStack heldItem = player.getStackInHand(hand);
		Random rand = new Random();
		int cooldown = Math.min(getStatus() + 1, 3) * 200;
		
		if (weight_ticks >= 200) {this.weight_ticks -= 200;}
		else {this.weight_ticks = 0;}
		
		for (int i=0 ; i<20; i++)
		{
			world.addParticleClient(ParticleTypes.SMOKE, player.getX() + 4*(rand.nextFloat() - 0.5F), player.getY() + 4*rand.nextFloat(), player.getZ() + 4*(rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
		}
		player.playSound(SoundEvents.ENTITY_RAVAGER_ROAR, 1.0F, 0.5F + rand.nextFloat());
		if (world.isClient()) //TODO update this dirty code
		{
			Vec3d forward = player.getRotationVecClient().multiply(1.7,1.3,1.7);
			if (forward.y < 1) {forward = new Vec3d(forward.x, 1, forward.z);}
			player.setVelocity(player.getVelocity().add(forward));
		}

		player.getItemCooldownManager().set(heldItem, cooldown);
		heldItem.damage(1, player, LivingEntity.getSlotForHand(hand));
        return ActionResult.CONSUME;
	}
	
	@Override public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker)
	{
		this.increaseWeight();
		super.postHit(stack, target, attacker);
	}

	@Override public boolean canMine(ItemStack stack, BlockState state, World world, BlockPos pos, LivingEntity user) {return !EntityHelper.isCreativePlayer(user);}

	@Override public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot)
	{
		if (this.weight_ticks > 800)
		{
			this.weight_ticks--;
		}
		else if (this.weight_ticks > 0)
		{
			this.weight_ticks-=2;
		}
		if (slot == EquipmentSlot.MAINHAND) {giveEntityEffect(world, entity);}
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

	@Override public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type)
	{
		ItemHelper.appendBerserkAxeItemTooltip(this.getTranslationKey(), textConsumer, Integer.toString(getStatus()));
	}
}
