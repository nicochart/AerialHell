package fr.factionbedrock.aerialhell.Item.Tools;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class EffectSwordItem extends AerialHellSwordItem
{
	private int timer;
	
	public EffectSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float movementSpeedIn, float maxHealthIn, Properties builderIn)
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
					if (this == AerialHellBlocksAndItems.NINJA_SWORD.get())
					{
						((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 400, 0));
					}
					else if (this == AerialHellBlocksAndItems.GOD_SWORD.get())
					{
						((LivingEntity) entityIn).addPotionEffect(new EffectInstance(AerialHellPotionEffects.GOD.get(), 400, 0));
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
}
