package fr.factionbedrock.aerialhell.Block;

import net.minecraft.block.BlockState;
import net.minecraft.block.WebBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class ThornyWebBlock extends WebBlock
{
	public ThornyWebBlock(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
		entityIn.setMotionMultiplier(state, new Vector3d(0.45D, 0.25D, 0.45D));
		if (entityIn instanceof LivingEntity)
		{
			((LivingEntity) entityIn).attackEntityFrom(new DamageSource("web_thorns"), 2.0F);
		}
	}
}
