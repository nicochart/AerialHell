package fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class BlueSolidEtherBlock extends SolidEtherBlock
{
	public BlueSolidEtherBlock(BlockBehaviour.Properties settings) {super(settings);}

	@Override public void livingEntityInside(BlockState state, Level world, BlockPos pos, LivingEntity entity)
	{
		super.livingEntityInside(state, world, pos, entity);
		if (!world.isClientSide())
		{
			if (canEntityCollide(entity))
			{
				entity.addEffect(new MobEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 10, 0, false, false));
			}
			else
			{
				entity.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 10, 3, false, false));
			}
		}
	}
}