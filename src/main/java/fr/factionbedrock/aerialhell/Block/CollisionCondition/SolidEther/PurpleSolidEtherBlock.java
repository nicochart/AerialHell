package fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PurpleSolidEtherBlock extends SolidEtherBlock
{
	public PurpleSolidEtherBlock(AbstractBlock.Settings settings) {super(settings);}

	@Override public void livingEntityInside(BlockState state, World world, BlockPos pos, LivingEntity entity)
	{
		super.livingEntityInside(state, world, pos, entity);
		if (!world.isClient())
		{
			if (canEntityCollide(entity))
			{
				entity.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY, 30, 0, true, true));
			}
			else if (!EntityHelper.isLivingEntityShadowImmune((LivingEntity)entity))
			{
				entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 40, 0, false, false));
			}
		}
	}
}