package fr.factionbedrock.aerialhell.Block;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ArsonistBlock extends Block
{
	public ArsonistBlock(Properties properties)
	{
		super(properties);
	}
	
	@Override public void stepOn(Level level, BlockPos pos, BlockState state, Entity entityIn)
	{
		if (entityIn instanceof LivingEntity)
		{
			if (!entityIn.fireImmune() && !EnchantmentHelper.hasFrostWalker((LivingEntity)entityIn))
			{
		         entityIn.hurt(level.damageSources().hotFloor(), 1.0F);
			}
			entityIn.setSecondsOnFire(2);
		}
	}
}
