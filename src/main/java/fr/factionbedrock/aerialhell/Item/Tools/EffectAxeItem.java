package fr.factionbedrock.aerialhell.Item.Tools;

import java.util.Random;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;

public class EffectAxeItem extends AerialHellAxeItem
{
	public EffectAxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Properties settings)
	{
		this(toolMaterial, attackDamage, attackSpeed, 0.0F, 0.0F, settings);
	}

	public EffectAxeItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, float movementSpeed, float maxHealth, Properties settings)
	{
		super(toolMaterial, attackDamage, attackSpeed, movementSpeed, maxHealth, settings);
	}
	
	@Override
    public InteractionResult use(Level world, Player player, InteractionHand hand)
    {
		ItemStack heldItem = player.getItemInHand(hand);
		Random rand = new Random();
		
		if (this == AerialHellItems.VOLUCITE_AXE)
		{
			if (EffectToolHelper.tryToApplyVolucitePower(this, heldItem, world, player, hand, rand, true)) {return InteractionResult.CONSUME;}
			else {return InteractionResult.PASS;}
		}
		else if (this == AerialHellItems.AXE_OF_LIGHT)
		{
			EffectToolHelper.applyLunaticLight(this, heldItem, world, player, hand, rand, 320);
		    return InteractionResult.CONSUME;
		}
		else
		{
			return super.use(world, player, hand);
		}
    }
}
