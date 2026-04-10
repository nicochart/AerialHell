package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class LightningShurikenEntity extends AbstractShurikenEntity
{
	public LightningShurikenEntity(EntityType<? extends LightningShurikenEntity> entityTypeIn, Level world)
	{
		super(entityTypeIn, world);
	}

	public LightningShurikenEntity(Level world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.LIGHTNING_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public LightningShurikenEntity(double x, double y, double z, Level world, ItemStack itemStack)
	{
		super(AerialHellEntities.LIGHTNING_SHURIKEN, x, y, z, world, itemStack);
	}

	public LightningShurikenEntity(LivingEntity shooter, Level world, ItemStack itemStack)
	{
		super(AerialHellEntities.LIGHTNING_SHURIKEN, shooter, world, itemStack);
	}

	public LightningShurikenEntity(Level world)
	{
		super(AerialHellEntities.LIGHTNING_SHURIKEN, world);
	}

	/*public LightningShurikenEntity(PlayMessages.SpawnEntity packet, World world)
	{
		super(AerialHellEntities.LIGHTNING_SHURIKEN, world);
	}*/
	
	@Override protected float getKnifeDamage() {return 2.0F;}
	@Override protected void applyEntityImpactEffet(Entity entity) {}
	
	@Override protected void onHit(HitResult result)
	{
		if (this.level().isClientSide()) {return;}
		
		if (result.getType() != HitResult.Type.MISS && this.level() instanceof ServerLevel)
		{
			LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(this.level(), EntitySpawnReason.TRIGGERED);
			lightningBolt.setPosRaw(this.getX(), this.getY(), this.getZ());
			this.level().addFreshEntity(lightningBolt);
		}
		super.onHit(result);
	}

	@Override protected Item getDefaultItem() {return AerialHellItems.LIGHTNING_SHURIKEN;}
}
