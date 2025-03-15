package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class VoluciteShurikenEntity extends AbstractShurikenEntity
{
	private int ticksLiving;
	
	public VoluciteShurikenEntity(EntityType<? extends VoluciteShurikenEntity> entityTypeIn, World world)
	{
		super(entityTypeIn, world);
		this.ticksLiving = 0;
	}

	public VoluciteShurikenEntity(World world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.VOLUCITE_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public VoluciteShurikenEntity(double x, double y, double z, World world, ItemStack itemStack)
	{
		super(AerialHellEntities.VOLUCITE_SHURIKEN, x, y, z, world, itemStack);
	}

	public VoluciteShurikenEntity(LivingEntity shooter, World world, ItemStack itemStack)
	{
		super(AerialHellEntities.VOLUCITE_SHURIKEN, shooter, world, itemStack);
	}

	public VoluciteShurikenEntity(World world)
	{
		super(AerialHellEntities.VOLUCITE_SHURIKEN, world);
	}

	/*public VoluciteShurikenEntity(PlayMessages.SpawnEntity packet, World world)
	{
		super(AerialHellEntities.VOLUCITE_SHURIKEN, world);
	}*/
	
	@Override
    public void tick()
    {
        super.tick();
        if (this.ticksLiving == 0)
        {
        	this.setNoGravity(true);
        }
        if (this.ticksLiving == 100)
        {
        	this.setNoGravity(false);
        }
        if (this.ticksLiving > 50 && this.ticksLiving < 100)
        {
        	this.setVelocity(this.getVelocity().x,this.getVelocity().y-0.01,this.getVelocity().z);
        }
        ++this.ticksLiving;
    }
	
	@Override protected float getKnifeDamage() {return 13.0F;}
	@Override protected void applyEntityImpactEffet(Entity entity)
	{
		if (entity instanceof LivingEntity)
        {
        	((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 20, 1, true, false));
        }
	}
	@Override protected Item getDefaultItem() {return AerialHellItems.VOLUCITE_SHURIKEN;}
}
