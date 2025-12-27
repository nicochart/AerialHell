package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.entity.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class LightningShurikenEntity extends AbstractShurikenEntity
{
	public LightningShurikenEntity(EntityType<? extends LightningShurikenEntity> entityTypeIn, World world)
	{
		super(entityTypeIn, world);
	}

	public LightningShurikenEntity(World world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.LIGHTNING_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public LightningShurikenEntity(double x, double y, double z, World world, ItemStack itemStack)
	{
		super(AerialHellEntities.LIGHTNING_SHURIKEN, x, y, z, world, itemStack);
	}

	public LightningShurikenEntity(LivingEntity shooter, World world, ItemStack itemStack)
	{
		super(AerialHellEntities.LIGHTNING_SHURIKEN, shooter, world, itemStack);
	}

	public LightningShurikenEntity(World world)
	{
		super(AerialHellEntities.LIGHTNING_SHURIKEN, world);
	}

	/*public LightningShurikenEntity(PlayMessages.SpawnEntity packet, World world)
	{
		super(AerialHellEntities.LIGHTNING_SHURIKEN, world);
	}*/
	
	@Override protected float getKnifeDamage() {return 2.0F;}
	@Override protected void applyEntityImpactEffet(Entity entity) {}
	
	@Override protected void onCollision(HitResult result)
	{
		if (this.getEntityWorld().isClient()) {return;}
		
		if (result.getType() != HitResult.Type.MISS && this.getEntityWorld() instanceof ServerWorld)
		{
			LightningEntity lightningBolt = EntityType.LIGHTNING_BOLT.create(this.getEntityWorld(), SpawnReason.TRIGGERED);
			lightningBolt.setPos(this.getX(), this.getY(), this.getZ());
			this.getEntityWorld().spawnEntity(lightningBolt);
		}
		super.onCollision(result);
	}

	@Override protected Item getDefaultItem() {return AerialHellItems.LIGHTNING_SHURIKEN;}
}
