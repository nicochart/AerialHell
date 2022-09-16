package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Entity.Monster.EvilCowEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ShadowSpiderEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ShadowTrollEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
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
	
	protected boolean isLivingEntityShadowImmune(LivingEntity entity) {return entity.getActivePotionEffect(AerialHellPotionEffects.SHADOW_IMMUNITY.get()) != null;} //return true if the entity has the SHADOW_IMMUNITY effect
	
	protected boolean isImmuneToDamage(Entity entityIn)
	{
		return (entityIn instanceof EvilCowEntity || entityIn instanceof ShadowTrollEntity || entityIn instanceof ShadowSpiderEntity);
	}
	
	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
		if (!worldIn.isRemote && entityIn instanceof LivingEntity)
    	{
			LivingEntity livingEntity = (LivingEntity) entityIn;
			if (!(isImmuneToDamage(livingEntity) || isLivingEntityShadowImmune(livingEntity))) //Not Damage Immunite && Not Shadow Immune
			{
				((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.WITHER, 40, 0));
				((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 40, 0));
			}
    	}
	}
}
