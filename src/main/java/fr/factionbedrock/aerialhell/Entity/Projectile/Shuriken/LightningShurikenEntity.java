package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class LightningShurikenEntity extends ShurikenEntity
{
	public LightningShurikenEntity(EntityType<? extends LightningShurikenEntity> entityType, Level level, float damage)
	{
		super(entityType, level, damage);
	}
	
	@Override protected void applyEntityImpactEffect(HitResult result)
	{
		LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(this.level(), EntitySpawnReason.TRIGGERED);
		lightningBolt.setPos(this.getX(), this.getY(), this.getZ());
		this.level().addFreshEntity(lightningBolt);
	}
}
