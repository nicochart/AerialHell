package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EffectSwordItem extends AerialHellSwordItem
{
	private int timer;
	
	public EffectSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float movementSpeedIn, float maxHealthIn, Properties builderIn)
	{
		super(tier, attackDamageIn, attackSpeedIn, movementSpeedIn, maxHealthIn, builderIn);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if (!worldIn.isRemote && timer <= 0)
		{
			if (entityIn instanceof LivingEntity)
			{
				if (((LivingEntity) entityIn).getHeldItemOffhand().getItem() == this || ((LivingEntity) entityIn).getHeldItemMainhand().getItem() == this)
				{
					if (this == AerialHellBlocksAndItems.GOD_SWORD.get())
					{
						((LivingEntity) entityIn).addPotionEffect(new EffectInstance(AerialHellPotionEffects.GOD.get(), 400, 0));
					}
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
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
		ItemStack heldItem = playerIn.getHeldItem(handIn);
		Random rand = new Random();
		if (this == AerialHellBlocksAndItems.VOLUCITE_SWORD.get())
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, worldIn, playerIn, rand)) {return ActionResult.resultConsume(heldItem);}
			else {return ActionResult.resultPass(heldItem);}
		}
		else if (this == AerialHellBlocksAndItems.NINJA_SWORD.get())
		{
			EffectToolHelper.applyNinjaEffect(this, heldItem, worldIn, playerIn, rand);
	        return ActionResult.resultConsume(heldItem);
		}
		else if (this == AerialHellBlocksAndItems.RANDOM_SWORD.get())
		{
			EffectToolHelper.applyRandomEffect(this, heldItem, worldIn, playerIn, rand);
	        return ActionResult.resultConsume(heldItem);
		}
		else if (this == AerialHellBlocksAndItems.SWORD_OF_LIGHT.get())
		{
			EffectToolHelper.applyLunaticLight(this, heldItem, worldIn, playerIn, rand);
		    return ActionResult.resultConsume(heldItem);
		}
		else if (this == AerialHellBlocksAndItems.ANTIDOTE_SWORD.get())
		{
			if (EffectToolHelper.tryRemovingPoisonAndWitherEffect(this, heldItem, worldIn, playerIn, rand)) {return ActionResult.resultConsume(heldItem);}
			else {return ActionResult.resultPass(heldItem);}
		}
		else if (this == AerialHellBlocksAndItems.GLOUTON_SWORD.get())
		{
			if (EffectToolHelper.tryEatingTool(this, heldItem, worldIn, playerIn, rand)) {return ActionResult.resultConsume(heldItem);}
			else {return ActionResult.resultPass(heldItem);}
		}
		else if (this == AerialHellBlocksAndItems.GLASS_CANON_SWORD.get())
		{
			EffectToolHelper.PlayerLiftoff(this, heldItem, worldIn, playerIn, rand);
	        return ActionResult.resultConsume(heldItem);
		}
		else {return super.onItemRightClick(worldIn, playerIn, handIn);}
    }
}
