package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class EffectHoeItem extends AerialHellHoeItem
{
	public EffectHoeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Properties properties)
	{
		this(toolMaterial, attackDamage, attackSpeed, 0.0F, 0.0F, properties);
	}

	public EffectHoeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Properties properties)
	{
		super(toolMaterial, attackDamage, attackSpeed, movementSpeed, maxHealth, properties);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot) {}
	
	@Override
    public InteractionResult use(Level level, Player playerIn, InteractionHand handIn)
    {
		ItemStack heldItem = playerIn.getItemInHand(handIn);
		Random rand = new Random();
		if (this == AerialHellItems.VOLUCITE_HOE.get())
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, level, playerIn, handIn, rand, false)) {return InteractionResult.CONSUME;}
			else {return InteractionResult.PASS;}
		}
		else if (this == AerialHellItems.REAPER_SCYTHE.get())
		{
			EffectToolHelper.applyReaperWalkEffect(this, heldItem, level, playerIn, handIn, rand, 600);
	        return InteractionResult.CONSUME;
		}
		else {return super.use(level, playerIn, handIn);}
    }
}
