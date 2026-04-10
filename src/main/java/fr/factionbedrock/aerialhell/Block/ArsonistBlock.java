package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ArsonistBlock extends Block
{
	public ArsonistBlock(BlockBehaviour.Properties settings)
	{
		super(settings);
	}
	
	@Override public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity)
	{
		if (entity instanceof LivingEntity livingEntity)
		{
			if (world instanceof ServerLevel serverWorld && !entity.fireImmune() && !EntityHelper.hasEnchantment(livingEntity, Enchantments.FROST_WALKER))
			{
				entity.hurtServer(serverWorld, world.damageSources().hotFloor(), 1.0F);
			}
			entity.igniteForSeconds(2);
		}
	}
}
