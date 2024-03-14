package fr.factionbedrock.aerialhell.Block.Ores;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class MagmaticGelOreBlock extends AerialHellOreBlock
{	
	public MagmaticGelOreBlock(int minExpDropped, int maxExpDropped, BlockBehaviour.Properties properties)
	{
		super(minExpDropped, maxExpDropped, properties);
	}
	
	@Override
	public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity)
	{
		boolean creaPlayer = (entity instanceof Player && ((Player) entity).isCreative());
		if (!world.isClientSide() && entity instanceof LivingEntity && !entity.isSteppingCarefully() && !creaPlayer)
		{
			((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 32, 1));
		}
	}
}
