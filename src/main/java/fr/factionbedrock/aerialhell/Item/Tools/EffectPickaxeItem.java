package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class EffectPickaxeItem extends AerialHellPickaxeItem
{
	private int timer;
	
	public EffectPickaxeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float movementSpeedIn, float maxHealthIn, Properties builderIn)
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
					if (this == AerialHellBlocksAndItems.MAGMA_CUBE_PICKAXE.get())
					{
						((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 400, 0));
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
		if (this == AerialHellBlocksAndItems.VOLUCITE_PICKAXE.get())
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, worldIn, playerIn, rand, false)) {return ActionResult.resultConsume(heldItem);}
			else {return ActionResult.resultPass(heldItem);}
		}
		else if (this == AerialHellBlocksAndItems.MAGMA_CUBE_PICKAXE.get())
		{
			EffectToolHelper.applyJumpBoostEffect(this, heldItem, worldIn, playerIn, rand, 100, 2);
			return ActionResult.resultConsume(heldItem);
		}
		else {return super.onItemRightClick(worldIn, playerIn, handIn);}
    }
}
