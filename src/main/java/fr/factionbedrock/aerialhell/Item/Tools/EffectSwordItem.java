package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.List;
import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class EffectSwordItem extends AerialHellToolItem
{
	private int timer;

	public EffectSwordItem(Properties properties) {super(properties);}
	
	@Override public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot)
	{
		if (!level.isClientSide() && timer <= 0)
		{
			if (entity instanceof LivingEntity)
			{
				if (((LivingEntity) entity).getMainHandItem().getItem() == this || ((LivingEntity) entity).getOffhandItem().getItem() == this)
				{
					if (this == AerialHellItems.GOD_SWORD.get()) {EffectToolHelper.PassiveEffects.applyGodEffect((LivingEntity)entity);}
				}
			}
			timer = 200;
		}
		else if (timer > -10)
		{
			timer--;
		}
	}
	
	@Override public InteractionResult use(Level level, Player player, InteractionHand hand)
    {
		ItemStack heldItem = player.getItemInHand(hand);
		Random rand = new Random();
		if (this == AerialHellItems.VOLUCITE_SWORD.get())
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, level, player, hand, rand, true)) {return InteractionResult.CONSUME;}
			else {return InteractionResult.PASS;}
		}
		else if (this == AerialHellItems.NINJA_SWORD.get())
		{
			EffectToolHelper.applyNinjaEffect(this, heldItem, level, player, rand, hand,400);
	        return InteractionResult.CONSUME;
		}
		else if (this == AerialHellItems.NINJA_MASTER_SWORD.get())
		{
			EffectToolHelper.applyNinjaEffect(this, heldItem, level, player, rand, hand, 340);
	        return InteractionResult.CONSUME;
		}
		else if (this == AerialHellItems.RANDOM_SWORD.get())
		{
			EffectToolHelper.applyRandomEffect(this, heldItem, level, player, hand, rand);
	        return InteractionResult.CONSUME;
		}
		else if (this == AerialHellItems.SWORD_OF_LIGHT.get())
		{
			EffectToolHelper.applyLunaticLight(this, heldItem, level, player, hand, rand, 160);
		    return InteractionResult.CONSUME;
		}
		else if (this == AerialHellItems.ANTIDOTE_SWORD.get())
		{
			if (EffectToolHelper.tryRemovingPoisonAndWitherEffect(this, heldItem, level, player, hand, rand)) {return InteractionResult.CONSUME;}
			else {return InteractionResult.PASS;}
		}
		else if (this == AerialHellItems.GLOUTON_SWORD.get())
		{
			if (EffectToolHelper.tryEatingTool(this, heldItem, level, player, hand, rand)) {return InteractionResult.CONSUME;}
			else {return InteractionResult.PASS;}
		}
		else if (this == AerialHellItems.NETHERIAN_KING_SWORD.get())
		{
			EffectToolHelper.applyFireResistanceEffect(this, heldItem, level, player, hand, rand, 200, 600);
		    return InteractionResult.CONSUME;
		}
		else if (this == AerialHellItems.GLASS_CANON_SWORD.get())
		{
			EffectToolHelper.PlayerLiftoff(this, heldItem, level, player, hand, rand);
	        return InteractionResult.CONSUME;
		}
		else {return super.use(level, player, hand);}
    }
}
