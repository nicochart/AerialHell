package fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class GreenSolidEtherBlock extends SolidEtherBlock
{
	public GreenSolidEtherBlock(BlockBehaviour.Properties properties) {super(properties);}
	
	@Override public void livingEntityInside(BlockState state, Level level, BlockPos pos, LivingEntity entity)
	{
		super.livingEntityInside(state, level, pos, entity);
		if (!level.isClientSide())
		{
			entity.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 10, 5, false, false));
		}
	}
}