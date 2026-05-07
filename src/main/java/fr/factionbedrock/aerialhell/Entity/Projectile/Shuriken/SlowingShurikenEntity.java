package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class SlowingShurikenEntity extends ShurikenEntity
{
	public SlowingShurikenEntity(EntityType<? extends SlowingShurikenEntity> entityType, Level level, float damage)
	{
		super(entityType, level, damage);
	}
	
	@Override protected void applyEntityImpactEffect(HitResult result)
	{
		if (result.getType() == HitResult.Type.ENTITY && ((EntityHitResult)result).getEntity() instanceof LivingEntity livingEntity)
		{
			livingEntity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 80, 1, true, false));
		}
	}
}
