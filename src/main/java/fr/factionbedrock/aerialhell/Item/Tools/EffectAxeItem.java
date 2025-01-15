package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;

public class EffectAxeItem extends AerialHellAxeItem
{
	public EffectAxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Properties properties)
	{
		this(toolMaterial, attackDamage, attackSpeed, 0.0F, 0.0F, properties);
	}

	public EffectAxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Properties properties)
	{
		super(toolMaterial, attackDamage, attackSpeed, movementSpeed, maxHealth, properties);
	}
	
	@Override
    public InteractionResult use(Level worldIn, Player playerIn, InteractionHand handIn)
    {
		ItemStack heldItem = playerIn.getItemInHand(handIn);
		Random rand = new Random();
		
		if (this == AerialHellBlocksAndItems.VOLUCITE_AXE.get())
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, worldIn, playerIn, handIn, rand, true)) {return InteractionResult.CONSUME;}
			else {return InteractionResult.PASS;}
		}
		else if (this == AerialHellBlocksAndItems.AXE_OF_LIGHT.get())
		{
			EffectToolHelper.applyLunaticLight(this, heldItem, worldIn, playerIn, handIn, rand, 320);
		    return InteractionResult.CONSUME;
		}
		else
		{
			return super.use(worldIn, playerIn, handIn);
		}
    }
}
