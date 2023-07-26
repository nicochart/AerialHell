package fr.factionbedrock.aerialhell.Block.Plants;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellDamageTypes;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BramblesBlock extends AerialHellTallGrassBlock
{
	public BramblesBlock(Properties properties) {super(properties);}
	
	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn)
	{
		entityIn.makeStuckInBlock(state, new Vec3((double)0.8F, 0.75D, (double)0.8F));
		if (!level.isClientSide() && entityIn instanceof LivingEntity)
    	{
			if (!EntityHelper.isImmuneToBramblesDamage(entityIn))
			{
				if (this == AerialHellBlocksAndItems.SHADOW_BRAMBLES.get())
				{
					((LivingEntity) entityIn).addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0));
					((LivingEntity) entityIn).hurt(AerialHellDamageTypes.getDamageSource(level, AerialHellDamageTypes.BRAMBLES_THORNS), 1.0F);
				}
				else
				{
					((LivingEntity) entityIn).addEffect(new MobEffectInstance(MobEffects.POISON, 40, 0));
					((LivingEntity) entityIn).hurt(AerialHellDamageTypes.getDamageSource(level, AerialHellDamageTypes.BRAMBLES_THORNS), 1.0F);
				}
			}
    	}
	}
}
