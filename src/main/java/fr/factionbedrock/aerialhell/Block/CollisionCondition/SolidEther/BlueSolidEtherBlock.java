package fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlueSolidEtherBlock extends SolidEtherBlock
{
	public BlueSolidEtherBlock(AbstractBlock.Settings settings) {super(settings);}

	@Override public void livingEntityInside(BlockState state, World world, BlockPos pos, LivingEntity entity)
	{
		super.livingEntityInside(state, world, pos, entity);
		if (!world.isClient())
		{
			if (canEntityCollide(entity))
			{
				entity.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 10, 0, false, false));
			}
			else
			{
				entity.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 10, 3, false, false));
			}
		}
	}
}