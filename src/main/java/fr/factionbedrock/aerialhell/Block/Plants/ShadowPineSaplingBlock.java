package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ShadowPineSaplingBlock extends AerialHellSaplingBlock
{
	public ShadowPineSaplingBlock(SaplingGenerator treeIn, AbstractBlock.Settings settings, ResourceKey<ConfiguredFeature<?, ?>> giantTreeFeatureKey, ResourceKey<ConfiguredFeature<?, ?>> hugeTreeFeatureKey, float hugeChance)
	{
		super(treeIn, settings, giantTreeFeatureKey, hugeTreeFeatureKey, hugeChance);
	}

	public ShadowPineSaplingBlock(SaplingGenerator treeIn, AbstractBlock.Settings settings, ResourceKey<ConfiguredFeature<?, ?>> giantTreeFeatureKey)
	{
		super(treeIn, properties, giantTreeFeatureKey);
	}
	
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
