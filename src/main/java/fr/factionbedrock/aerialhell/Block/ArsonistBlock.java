package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public class ArsonistBlock extends Block
{
	public ArsonistBlock(Properties properties)
	{
		super(properties);
	}
	
	@Override public void stepOn(Level level, BlockPos pos, BlockState state, Entity entityIn)
	{
		if (entityIn instanceof LivingEntity livingEntity)
		{
			if (!entityIn.fireImmune() && !EntityHelper.hasEnchantment(livingEntity, Enchantments.FROST_WALKER))
			{
		         entityIn.hurt(level.damageSources().hotFloor(), 1.0F);
			}
			entityIn.igniteForSeconds(2);
		}
	}
}
