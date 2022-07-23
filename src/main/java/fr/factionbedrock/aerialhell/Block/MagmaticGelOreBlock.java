package fr.factionbedrock.aerialhell.Block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MagmaticGelOreBlock extends AerialHellOreBlock
{	
	public MagmaticGelOreBlock(int minExpDropped, int maxExpDropped, AbstractBlock.Properties properties)
	{
		super(minExpDropped, maxExpDropped, properties);
	}
	
	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity)
	{
		boolean creaPlayer = (entity instanceof PlayerEntity && ((PlayerEntity) entity).isCreative());
		if (!world.isRemote() && entity instanceof LivingEntity && !creaPlayer)
		{
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 32, 1));
		}
	}
}
