package fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class GreenSolidEtherBlock extends SolidEtherBlock
{
	public GreenSolidEtherBlock(BlockBehaviour.Properties settings) {super(settings);}

	@Override public void livingEntityInside(BlockState state, Level world, BlockPos pos, LivingEntity entity)
	{
		super.livingEntityInside(state, world, pos, entity);
		if (!world.isClientSide())
		{
			entity.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 10, 5, false, false));
		}
	}
}