package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;

public class EffectShovelItem extends AerialHellShovelItem
{	
	private int timer;
	
	public EffectShovelItem(Tier toolMaterial, Item.Properties settings)
	{
		super(toolMaterial, settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected)
	{
		if (!world.isClientSide() && timer <= 0)
		{
			if (entity instanceof LivingEntity livingEntity)
			{
				if (livingEntity.getMainHandItem().getItem() == this || livingEntity.getOffhandItem().getItem() == this)
				{
					if (this == AerialHellItems.MAGMA_CUBE_SHOVEL)
					{
						EffectToolHelper.PassiveEffects.applyMagmaCubeEffect((LivingEntity)entity);
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
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
		ItemStack heldItem = player.getItemInHand(hand);
		Random rand = new Random();
		if (this == AerialHellItems.VOLUCITE_SHOVEL)
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, world, player, hand, rand, false)) {return InteractionResultHolder.consume(heldItem);}
			else {return InteractionResultHolder.pass(heldItem);}
		}
		else if (this == AerialHellItems.MAGMA_CUBE_SHOVEL)
		{
			EffectToolHelper.applyJumpBoostEffect(this, heldItem, world, player, hand, rand, 100, 2);
			return InteractionResultHolder.consume(heldItem);
		}
		else {return super.use(world, player, hand);}
    }
}
