package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class EffectShovelItem extends AerialHellShovelItem
{	
	private int timer;

	public EffectShovelItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Properties properties)
	{
		this(toolMaterial, attackDamage, attackSpeed, 0.0F, 0.0F, properties);
	}

	public EffectShovelItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Properties properties)
	{
		super(toolMaterial, attackDamage, attackSpeed, movementSpeed, maxHealth, properties);
	}

	@Override public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot)
	{
		if (!level.isClientSide() && timer <= 0)
		{
			if (entity instanceof LivingEntity)
			{
				if (((LivingEntity) entity).getMainHandItem().getItem() == this || ((LivingEntity) entity).getOffhandItem().getItem() == this)
				{
					if (this == AerialHellItems.MAGMA_CUBE_SHOVEL.get())
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
    public InteractionResult use(Level worldIn, Player playerIn, InteractionHand handIn)
    {
		ItemStack heldItem = playerIn.getItemInHand(handIn);
		Random rand = new Random();
		if (this == AerialHellItems.VOLUCITE_SHOVEL.get())
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, worldIn, playerIn, handIn, rand, false)) {return InteractionResult.CONSUME;}
			else {return InteractionResult.PASS;}
		}
		else if (this == AerialHellItems.MAGMA_CUBE_SHOVEL.get())
		{
			EffectToolHelper.applyJumpBoostEffect(this, heldItem, worldIn, playerIn, handIn, rand, 100, 2);
			return InteractionResult.CONSUME;
		}
		else {return super.use(worldIn, playerIn, handIn);}
    }
}
