package fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class PurpleSolidEtherBlock extends SolidEtherBlock
{
	public PurpleSolidEtherBlock(BlockBehaviour.Properties settings) {super(settings);}

	@Override public void livingEntityInside(BlockState state, Level world, BlockPos pos, LivingEntity entity)
	{
		super.livingEntityInside(state, world, pos, entity);
		if (!world.isClientSide())
		{
			if (canEntityCollide(entity))
			{
				entity.addEffect(new MobEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY, 30, 0, true, true));
			}
			else if (!EntityHelper.isLivingEntityShadowImmune((LivingEntity)entity))
			{
				entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0, false, false));
			}
		}
	}
}