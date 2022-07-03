package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class BramblesBlock extends AerialHellTallGrassBlock
{
	public BramblesBlock(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
		entityIn.setMotionMultiplier(state, new Vector3d((double)0.8F, 0.75D, (double)0.8F));
		if (!worldIn.isRemote && entityIn instanceof LivingEntity)
    	{
			if (entityIn.getType() != AerialHellEntities.SANDY_SHEEP_TYPE && entityIn.getType() != AerialHellEntities.FOREST_CATERPILLAR_TYPE)
			{
				((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.POISON, 40, 0));
				((LivingEntity) entityIn).attackEntityFrom(new DamageSource("brambles_thorns"), 1.0F);
			}
    	}
	}
}
