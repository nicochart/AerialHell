package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import org.jetbrains.annotations.Nullable;

public class BerserkAxeItem extends EffectAxeItem
{
	private int weight_ticks;

	public BerserkAxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Properties settings)
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
    public InteractionResult use(Level world, Player player, InteractionHand hand)
    {
		ItemStack heldItem = player.getItemInHand(hand);
		Random rand = new Random();
		int cooldown = Math.min(getStatus() + 1, 3) * 200;
		
		if (weight_ticks >= 200) {this.weight_ticks -= 200;}
		else {this.weight_ticks = 0;}
		
		for (int i=0 ; i<20; i++)
		{
			world.addParticle(ParticleTypes.SMOKE, player.getX() + 4*(rand.nextFloat() - 0.5F), player.getY() + 4*rand.nextFloat(), player.getZ() + 4*(rand.nextFloat() - 0.5F), 0.0D, 0.0D, 0.0D);
		}
		player.playSound(SoundEvents.RAVAGER_ROAR, 1.0F, 0.5F + rand.nextFloat());
		if (world.isClientSide()) //TODO update this dirty code
		{
			Vec3 forward = player.getForward().multiply(1.7,1.3,1.7);
			if (forward.y < 1) {forward = new Vec3(forward.x, 1, forward.z);}
			player.setDeltaMovement(player.getDeltaMovement().add(forward));
		}

		player.getCooldowns().addCooldown(heldItem, cooldown);
		heldItem.hurtAndBreak(1, player, hand);
        return InteractionResult.CONSUME;
	}
	
	@Override public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker)
	{
		this.increaseWeight();
		super.hurtEnemy(stack, target, attacker);
	}

	@Override public boolean canDestroyBlock(ItemStack stack, BlockState state, Level world, BlockPos pos, LivingEntity user) {return !EntityHelper.isCreativePlayer(user);}

	@Override public void inventoryTick(ItemStack stack, ServerLevel world, Entity entity, @Nullable EquipmentSlot slot)
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
	
	private void giveEntityEffect(Level world, Entity entityIn)
	{
		if (!world.isClientSide() && entityIn instanceof LivingEntity)
		{
			LivingEntity livingEntityIn = (LivingEntity) entityIn;
			int weight = this.getStatus();
			if (weight == 0)
			{
				livingEntityIn.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 22, 1, false, false));
				livingEntityIn.addEffect(new MobEffectInstance(MobEffects.SPEED, 22, 1, false, false));
				livingEntityIn.addEffect(new MobEffectInstance(MobEffects.HASTE, 22, 1, false, false));
			}
			else if (weight == 1)
			{
				livingEntityIn.addEffect(new MobEffectInstance(MobEffects.SPEED, 22, 0, false, false));
				livingEntityIn.addEffect(new MobEffectInstance(MobEffects.HASTE, 22, 0, false, false));
			}
			else if (weight == 2)
			{
				livingEntityIn.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 22, 0, false, false));
			}
			else if (weight == 3)
			{
				livingEntityIn.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 22, 0, false, false));
				livingEntityIn.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 22, 1, false, false));
			}
			else //(weight == 4)
			{
				livingEntityIn.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 22, 0, false, false));
				livingEntityIn.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 22, 1, false, false));
				livingEntityIn.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 22, 3, false, false));
			}
		}
	}

	@Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type)
	{
		ItemHelper.appendBerserkAxeItemTooltip(this.getDescriptionId(), textConsumer, Integer.toString(getStatus()));
	}
}
