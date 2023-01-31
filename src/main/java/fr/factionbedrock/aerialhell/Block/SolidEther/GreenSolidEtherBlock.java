package fr.factionbedrock.aerialhell.Block.SolidEther;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GreenSolidEtherBlock extends SolidEtherBlock
{
	public GreenSolidEtherBlock(AbstractBlock.Properties properties) {super(properties);}
	
	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity)
	{
		super.onEntityCollision(state, world, pos, entity);
		if (!world.isRemote() && entity instanceof LivingEntity)
		{
			((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 10, 5, false, false));
		}
	}
}