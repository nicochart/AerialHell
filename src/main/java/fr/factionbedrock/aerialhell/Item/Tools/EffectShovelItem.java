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

public class EffectShovelItem extends AerialHellShovelItem
{	
	private int timer;
	
	public EffectShovelItem(Tier tier, Properties builderIn)
	{
		super(tier, builderIn);
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
					if (this == AerialHellBlocksAndItems.MAGMA_CUBE_SHOVEL.get())
					{
						EffectToolHelper.PassiveEffects.applyMagmaCubeEffect((LivingEntity)entityIn);
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
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn)
    {
		ItemStack heldItem = playerIn.getItemInHand(handIn);
		Random rand = new Random();
		if (this == AerialHellBlocksAndItems.VOLUCITE_SHOVEL.get())
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, worldIn, playerIn, handIn, rand, false)) {return InteractionResultHolder.consume(heldItem);}
			else {return InteractionResultHolder.pass(heldItem);}
		}
		else if (this == AerialHellBlocksAndItems.MAGMA_CUBE_SHOVEL.get())
		{
			EffectToolHelper.applyJumpBoostEffect(this, heldItem, worldIn, playerIn, handIn, rand, 100, 2);
			return InteractionResultHolder.consume(heldItem);
		}
		else {return super.use(worldIn, playerIn, handIn);}
    }
}
