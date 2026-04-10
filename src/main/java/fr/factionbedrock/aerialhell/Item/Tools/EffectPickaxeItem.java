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

public class EffectPickaxeItem extends AerialHellPickaxeItem
{
	private int timer;

	public EffectPickaxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Properties settings)
	{
		this(toolMaterial, attackDamage, attackSpeed, 0.0F, 0.0F, settings);
	}

	public EffectPickaxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Properties settings)
	{
		super(toolMaterial, attackDamage, attackSpeed, movementSpeed, maxHealth, settings);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, ServerLevel world, Entity entity, @Nullable EquipmentSlot slot)
	{
		if (!world.isClientSide() && timer <= 0)
		{
			if (entity instanceof LivingEntity livingEntity)
			{
				if (livingEntity.getMainHandItem().getItem() == this || ((LivingEntity) entity).getOffhandItem().getItem() == this)
				{
					if (this == AerialHellItems.MAGMA_CUBE_PICKAXE)
					{
						EffectToolHelper.PassiveEffects.applyMagmaCubeEffect(livingEntity);
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
    public InteractionResult use(Level world, Player player, InteractionHand hand)
    {
		ItemStack heldItem = player.getItemInHand(hand);
		Random rand = new Random();
		if (this == AerialHellItems.VOLUCITE_PICKAXE)
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, world, player, hand, rand, false)) {return InteractionResult.CONSUME;}
			else {return InteractionResult.PASS;}
		}
		else if (this == AerialHellItems.MAGMA_CUBE_PICKAXE)
		{
			EffectToolHelper.applyJumpBoostEffect(this, heldItem, world, player, hand, rand, 100, 2);
			return InteractionResult.CONSUME;
		}
		else {return super.use(world, player, hand);}
    }
}
