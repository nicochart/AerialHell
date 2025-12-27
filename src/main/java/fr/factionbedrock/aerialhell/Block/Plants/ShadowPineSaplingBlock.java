package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class ShadowPineSaplingBlock extends AerialHellSaplingBlock
{
	public ShadowPineSaplingBlock(SaplingGenerator treeIn, AbstractBlock.Settings settings, RegistryKey<ConfiguredFeature<?, ?>> giantTreeFeatureKey, RegistryKey<ConfiguredFeature<?, ?>> hugeTreeFeatureKey, float hugeChance)
	{
		super(treeIn, settings, giantTreeFeatureKey, hugeTreeFeatureKey, hugeChance);
	}

	public ShadowPineSaplingBlock(SaplingGenerator treeIn, AbstractBlock.Settings settings, RegistryKey<ConfiguredFeature<?, ?>> giantTreeFeatureKey)
	{
		super(treeIn, settings, giantTreeFeatureKey);
	}
	
	@Override public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler, boolean intersects)
	{
		if (!world.isClient() && entity instanceof LivingEntity)
    	{
			LivingEntity livingEntity = (LivingEntity) entity;
			if (!EntityHelper.isImmuneToSomeShadowDamage(livingEntity)) //Not Damage Immune && Not Shadow Immune
			{
				((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 40, 0));
				((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 40, 0));
			}
    	}
	}
}
