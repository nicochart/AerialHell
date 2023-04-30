package fr.factionbedrock.aerialhell.Block.SolidEther;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class GreenSolidEtherBlock extends SolidEtherBlock
{
	public GreenSolidEtherBlock(BlockBehaviour.Properties properties) {super(properties);}
	
	@Override
	public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity)
	{
		super.entityInside(state, world, pos, entity);
		if (!world.isClientSide() && entity instanceof LivingEntity)
		{
			((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.JUMP, 10, 5, false, false));
		}
	}
}