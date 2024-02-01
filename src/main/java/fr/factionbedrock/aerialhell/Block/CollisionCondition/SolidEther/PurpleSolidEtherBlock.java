package fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class PurpleSolidEtherBlock extends SolidEtherBlock
{
	public PurpleSolidEtherBlock(BlockBehaviour.Properties properties) {super(properties);}

	@Override public void livingEntityInside(BlockState state, Level level, BlockPos pos, LivingEntity entity)
	{
		super.livingEntityInside(state, level, pos, entity);
		if (!level.isClientSide())
		{
			if (canEntityCollide(entity))
			{
				entity.addEffect(new MobEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY.get(), 30, 0, true, true));
			}
			else if (!EntityHelper.isLivingEntityShadowImmune((LivingEntity)entity))
			{
				entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0, false, false));
			}
		}
	}
}