package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import org.jetbrains.annotations.Nullable;

public class EffectSwordItem extends AerialHellSwordItem
{
	private int timer;

	public EffectSwordItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Properties settings)
	{
		this(toolMaterial, attackDamage, attackSpeed, 0.0F, 0.0F, settings);
	}

	public EffectSwordItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Properties settings)
	{
		super(toolMaterial, attackDamage, attackSpeed, movementSpeed, maxHealth, settings);
	}
	
	@Override public void inventoryTick(ItemStack stack, ServerLevel world, Entity entity, @Nullable EquipmentSlot slot)
	{
		if (!world.isClientSide() && timer <= 0)
		{
			if (entity instanceof LivingEntity livingEntity)
			{
				if (livingEntity.getMainHandItem().getItem() == this || livingEntity.getOffhandItem().getItem() == this)
				{
					if (this == AerialHellItems.GOD_SWORD) {EffectToolHelper.PassiveEffects.applyGodEffect((LivingEntity)entity);}
				}
			}
			timer = 200;
		}
		else if (timer > -10)
		{
			timer--;
		}
	}
	
	@Override
    public InteractionResult use(Level world, Player player, InteractionHand hand)
    {
		ItemStack heldItem = player.getItemInHand(hand);
		Random rand = new Random();
		if (this == AerialHellItems.VOLUCITE_SWORD)
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, world, player, hand, rand, true)) {return InteractionResult.CONSUME;}
			else {return InteractionResult.PASS;}
		}
		else if (this == AerialHellItems.NINJA_SWORD)
		{
			EffectToolHelper.applyNinjaEffect(this, heldItem, world, player, rand, hand,400);
	        return InteractionResult.CONSUME;
		}
		else if (this == AerialHellItems.NINJA_MASTER_SWORD)
		{
			EffectToolHelper.applyNinjaEffect(this, heldItem, world, player, rand, hand, 340);
	        return InteractionResult.CONSUME;
		}
		else if (this == AerialHellItems.RANDOM_SWORD)
		{
			EffectToolHelper.applyRandomEffect(this, heldItem, world, player, hand, rand);
	        return InteractionResult.CONSUME;
		}
		else if (this == AerialHellItems.SWORD_OF_LIGHT)
		{
			EffectToolHelper.applyLunaticLight(this, heldItem, world, player, hand, rand, 160);
		    return InteractionResult.CONSUME;
		}
		else if (this == AerialHellItems.ANTIDOTE_SWORD)
		{
			if (EffectToolHelper.tryRemovingPoisonAndWitherEffect(this, heldItem, world, player, hand, rand)) {return InteractionResult.CONSUME;}
			else {return InteractionResult.PASS;}
		}
		else if (this == AerialHellItems.GLOUTON_SWORD)
		{
			if (EffectToolHelper.tryEatingTool(this, heldItem, world, player, hand, rand)) {return InteractionResult.CONSUME;}
			else {return InteractionResult.PASS;}
		}
		else if (this == AerialHellItems.NETHERIAN_KING_SWORD)
		{
			EffectToolHelper.applyFireResistanceEffect(this, heldItem, world, player, hand, rand, 200, 600);
		    return InteractionResult.CONSUME;
		}
		else if (this == AerialHellItems.GLASS_CANON_SWORD)
		{
			EffectToolHelper.PlayerLiftoff(this, heldItem, world, player, hand, rand);
	        return InteractionResult.CONSUME;
		}
		else {return super.use(world, player, hand);}
    }
}
