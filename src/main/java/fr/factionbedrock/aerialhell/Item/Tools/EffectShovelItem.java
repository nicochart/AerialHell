package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class EffectShovelItem extends AerialHellShovelItem
{	
	private int timer;
	
	public EffectShovelItem(ToolMaterial toolMaterial, Item.Settings settings)
	{
		super(toolMaterial, settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
	{
		if (!world.isClient() && timer <= 0)
		{
			if (entity instanceof LivingEntity livingEntity)
			{
				if (livingEntity.getMainHandStack().getItem() == this || livingEntity.getOffHandStack().getItem() == this)
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
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
		ItemStack heldItem = player.getStackInHand(hand);
		Random rand = new Random();
		if (this == AerialHellItems.VOLUCITE_SHOVEL)
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, world, player, hand, rand, false)) {return TypedActionResult.consume(heldItem);}
			else {return TypedActionResult.pass(heldItem);}
		}
		else if (this == AerialHellItems.MAGMA_CUBE_SHOVEL)
		{
			EffectToolHelper.applyJumpBoostEffect(this, heldItem, world, player, hand, rand, 100, 2);
			return TypedActionResult.consume(heldItem);
		}
		else {return super.use(world, player, hand);}
    }
}
