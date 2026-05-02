package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class FlamingShurikenEntity extends ShurikenEntity
{
	public FlamingShurikenEntity(EntityType<? extends FlamingShurikenEntity> entityType, Level level, float damage)
	{
		super(entityType, level, damage);
	}

	@Override protected void applyEntityImpactEffect(HitResult result)
	{
		if (result.getType() == HitResult.Type.ENTITY)
		{
			((EntityHitResult)result).getEntity().igniteForSeconds(5);
		}
	}
}
