package fr.factionbedrock.aerialhell.Block.CollisionCondition.SolidEther;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GreenSolidEtherBlock extends SolidEtherBlock
{
	public GreenSolidEtherBlock(AbstractBlock.Settings settings) {super(settings);}

	@Override public void livingEntityInside(BlockState state, World world, BlockPos pos, LivingEntity entity)
	{
		super.livingEntityInside(state, world, pos, entity);
		if (!world.isClient())
		{
			entity.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 10, 5, false, false));
		}
	}
}