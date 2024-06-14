package fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class BlueSolidEtherBlock extends SolidEtherBlock
{
	public BlueSolidEtherBlock(BlockBehaviour.Properties properties) {super(properties);}
	
	@Override public void livingEntityInside(BlockState state, Level level, BlockPos pos, LivingEntity entity)
	{
		super.livingEntityInside(state, level, pos, entity);
		if (!level.isClientSide())
		{
			if (canEntityCollide(entity))
			{
				entity.addEffect(new MobEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.getHolder().get(), 10, 0, false, false));
			}
			else
			{
				entity.addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 3, false, false));
			}
		}
	}
}