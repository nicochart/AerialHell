package fr.factionbedrock.aerialhell.Block;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class ArsonistBlock extends Block
{
	public ArsonistBlock(Properties properties)
	{
		super(properties);
	}
	
	public void stepOn(Level worldIn, BlockPos pos, Entity entityIn)
	{
		if (entityIn instanceof LivingEntity)
		{
			if (!entityIn.fireImmune() && !EnchantmentHelper.hasFrostWalker((LivingEntity)entityIn))
			{
		         entityIn.hurt(DamageSource.HOT_FLOOR, 1.0F);
			}
			entityIn.setSecondsOnFire(2);
		}
	}
}
