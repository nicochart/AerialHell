package fr.factionbedrock.aerialhell.Block;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ArsonistBlock extends Block
{
	public ArsonistBlock(Properties properties)
	{
		super(properties);
	}
	
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
	{
		if (entityIn instanceof LivingEntity)
		{
			if (!entityIn.isImmuneToFire() && !EnchantmentHelper.hasFrostWalker((LivingEntity)entityIn))
			{
		         entityIn.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
			}
			entityIn.setFire(2);
		}
	}
}
