package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ShadowPineSaplingBlock extends AerialHellSaplingBlock
{
	public ShadowPineSaplingBlock(TreeGrower treeIn, BlockBehaviour.Properties settings, ResourceKey<ConfiguredFeature<?, ?>> giantTreeFeatureKey, ResourceKey<ConfiguredFeature<?, ?>> hugeTreeFeatureKey, float hugeChance)
	{
		super(treeIn, settings, giantTreeFeatureKey, hugeTreeFeatureKey, hugeChance);
	}

	public ShadowPineSaplingBlock(TreeGrower treeIn, BlockBehaviour.Properties settings, ResourceKey<ConfiguredFeature<?, ?>> giantTreeFeatureKey)
	{
		super(treeIn, settings, giantTreeFeatureKey);
	}
	
	@Override public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler, boolean intersects)
	{
		if (!world.isClientSide() && entity instanceof LivingEntity)
    	{
			LivingEntity livingEntity = (LivingEntity) entity;
			if (!EntityHelper.isImmuneToSomeShadowDamage(livingEntity)) //Not Damage Immune && Not Shadow Immune
			{
				((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.WITHER, 40, 0));
				((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0));
			}
    	}
	}
}
