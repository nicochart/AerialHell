package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.trees.Tree;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ShadowPineSaplingBlock extends SaplingBlock
{
	public ShadowPineSaplingBlock(Tree treeIn, AbstractBlock.Properties properties)
	{
		super(treeIn, properties);
	}
	
	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
		if (!worldIn.isRemote && entityIn instanceof LivingEntity)
    	{
			if (entityIn.getType() != AerialHellEntities.SHADOW_TROLL_TYPE && entityIn.getType() != AerialHellEntities.MUD_SOLDIER_TYPE && entityIn.getType() != AerialHellEntities.HELL_SPIDER_TYPE)
			{
				((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.WITHER, 40, 0));
				((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 40, 0));
			}
    	}
	}
}
