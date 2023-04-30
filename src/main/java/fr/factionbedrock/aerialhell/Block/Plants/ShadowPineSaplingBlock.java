package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class ShadowPineSaplingBlock extends SaplingBlock
{
	public ShadowPineSaplingBlock(AbstractTreeGrower treeIn, BlockBehaviour.Properties properties) {super(treeIn, properties);}
	
	@Override
	public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn)
	{
		if (!worldIn.isClientSide() && entityIn instanceof LivingEntity)
    	{
			LivingEntity livingEntity = (LivingEntity) entityIn;
			if (!EntityHelper.isImmuneToSomeShadowDamage(livingEntity)) //Not Damage Immune && Not Shadow Immune
			{
				((LivingEntity) entityIn).addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 0));
				((LivingEntity) entityIn).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0));
			}
    	}
	}
}
