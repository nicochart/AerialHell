package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EffectShovelItem extends AerialHellShovelItem
{	
	private int timer;

	public EffectShovelItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Settings settings)
	{
		this(toolMaterial, attackDamage, attackSpeed, 0.0F, 0.0F, settings);
	}

	public EffectShovelItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Settings settings)
	{
		super(toolMaterial, attackDamage, attackSpeed, movementSpeed, maxHealth, settings);
	}

	@Override public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot)
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
    public ActionResult use(World world, PlayerEntity player, Hand hand)
    {
		ItemStack heldItem = player.getStackInHand(hand);
		Random rand = new Random();
		if (this == AerialHellItems.VOLUCITE_SHOVEL)
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, world, player, hand, rand, false)) {return ActionResult.CONSUME;}
			else {return ActionResult.PASS;}
		}
		else if (this == AerialHellItems.MAGMA_CUBE_SHOVEL)
		{
			EffectToolHelper.applyJumpBoostEffect(this, heldItem, world, player, hand, rand, 100, 2);
			return ActionResult.CONSUME;
		}
		else {return super.use(world, player, hand);}
    }
}
