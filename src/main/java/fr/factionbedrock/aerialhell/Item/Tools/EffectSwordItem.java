package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;

public class EffectSwordItem extends AerialHellSwordItem
{
	private int timer;
	
	public EffectSwordItem(Tier tier, int attackDamageIn, float attackSpeedIn, float movementSpeedIn, float maxHealthIn, Properties builderIn)
	{
		super(tier, attackDamageIn, attackSpeedIn, movementSpeedIn, maxHealthIn, builderIn);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if (!worldIn.isClientSide() && timer <= 0)
		{
			if (entityIn instanceof LivingEntity)
			{
				if (((LivingEntity) entityIn).getMainHandItem().getItem() == this || ((LivingEntity) entityIn).getOffhandItem().getItem() == this)
				{
					if (this == AerialHellBlocksAndItems.GOD_SWORD.get()) {EffectToolHelper.PassiveEffects.applyGodEffect((LivingEntity)entityIn);}
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
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn)
    {
		ItemStack heldItem = playerIn.getItemInHand(handIn);
		Random rand = new Random();
		if (this == AerialHellBlocksAndItems.VOLUCITE_SWORD.get())
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, worldIn, playerIn, rand, true)) {return InteractionResultHolder.consume(heldItem);}
			else {return InteractionResultHolder.pass(heldItem);}
		}
		else if (this == AerialHellBlocksAndItems.NINJA_SWORD.get())
		{
			EffectToolHelper.applyNinjaEffect(this, heldItem, worldIn, playerIn, rand, 400);
	        return InteractionResultHolder.consume(heldItem);
		}
		else if (this == AerialHellBlocksAndItems.NINJA_MASTER_SWORD.get())
		{
			EffectToolHelper.applyNinjaEffect(this, heldItem, worldIn, playerIn, rand, 340);
	        return InteractionResultHolder.consume(heldItem);
		}
		else if (this == AerialHellBlocksAndItems.RANDOM_SWORD.get())
		{
			EffectToolHelper.applyRandomEffect(this, heldItem, worldIn, playerIn, rand);
	        return InteractionResultHolder.consume(heldItem);
		}
		else if (this == AerialHellBlocksAndItems.SWORD_OF_LIGHT.get())
		{
			EffectToolHelper.applyLunaticLight(this, heldItem, worldIn, playerIn, rand, 160);
		    return InteractionResultHolder.consume(heldItem);
		}
		else if (this == AerialHellBlocksAndItems.ANTIDOTE_SWORD.get())
		{
			if (EffectToolHelper.tryRemovingPoisonAndWitherEffect(this, heldItem, worldIn, playerIn, rand)) {return InteractionResultHolder.consume(heldItem);}
			else {return InteractionResultHolder.pass(heldItem);}
		}
		else if (this == AerialHellBlocksAndItems.GLOUTON_SWORD.get())
		{
			if (EffectToolHelper.tryEatingTool(this, heldItem, worldIn, playerIn, rand)) {return InteractionResultHolder.consume(heldItem);}
			else {return InteractionResultHolder.pass(heldItem);}
		}
		else if (this == AerialHellBlocksAndItems.NETHERIAN_KING_SWORD.get())
		{
			EffectToolHelper.applyFireResistanceEffect(this, heldItem, worldIn, playerIn, rand, 200, 600);
		    return InteractionResultHolder.consume(heldItem);
		}
		else if (this == AerialHellBlocksAndItems.GLASS_CANON_SWORD.get())
		{
			EffectToolHelper.PlayerLiftoff(this, heldItem, worldIn, playerIn, rand);
	        return InteractionResultHolder.consume(heldItem);
		}
		else {return super.use(worldIn, playerIn, handIn);}
    }
}
