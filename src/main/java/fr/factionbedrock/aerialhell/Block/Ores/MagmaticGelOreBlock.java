package fr.factionbedrock.aerialhell.Block.Ores;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MagmaticGelOreBlock extends AerialHellOreBlock
{	
	public MagmaticGelOreBlock(int minExpDropped, int maxExpDropped, AbstractBlock.Settings settings)
	{
		super(minExpDropped, maxExpDropped, settings);
	}
	
	@Override
	public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity)
	{
		boolean creaPlayer = (entity instanceof PlayerEntity && ((PlayerEntity) entity).isCreative());
		if (!world.isClient() && entity instanceof LivingEntity && !entity.isSneaking() && !creaPlayer)
		{
			((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 32, 1));
		}
	}
}
