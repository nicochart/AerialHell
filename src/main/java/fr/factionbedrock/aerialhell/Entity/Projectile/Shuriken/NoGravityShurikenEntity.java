package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class NoGravityShurikenEntity extends ShurikenEntity
{
	private int ticksLiving;
	
	public NoGravityShurikenEntity(EntityType<? extends NoGravityShurikenEntity> entityType, Level level, float damage)
	{
		super(entityType, level, damage);
		this.ticksLiving = 0;
	}
	
	@Override public void tick()
    {
        super.tick();
        if (this.ticksLiving == 0) {this.setNoGravity(true);}
        if (this.ticksLiving == 100) {this.setNoGravity(false);}
        if (this.ticksLiving > 50 && this.ticksLiving < 100)
        {
        	this.setDeltaMovement(this.getDeltaMovement().x,this.getDeltaMovement().y-0.01,this.getDeltaMovement().z);
        }
        ++this.ticksLiving;
    }

	@Override protected void applyEntityImpactEffect(HitResult result)
	{
		if (result.getType() == HitResult.Type.ENTITY && ((EntityHitResult)result).getEntity() instanceof LivingEntity livingEntity)
		{
			livingEntity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20, 1, true, false));
		}
	}
}
