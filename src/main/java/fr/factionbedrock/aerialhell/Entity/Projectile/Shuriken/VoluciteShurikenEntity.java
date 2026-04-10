package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class VoluciteShurikenEntity extends AbstractShurikenEntity
{
	private int ticksLiving;
	
	public VoluciteShurikenEntity(EntityType<? extends VoluciteShurikenEntity> entityTypeIn, Level world)
	{
		super(entityTypeIn, world);
		this.ticksLiving = 0;
	}

	public VoluciteShurikenEntity(Level world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.VOLUCITE_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public VoluciteShurikenEntity(double x, double y, double z, Level world, ItemStack itemStack)
	{
		super(AerialHellEntities.VOLUCITE_SHURIKEN, x, y, z, world, itemStack);
	}

	public VoluciteShurikenEntity(LivingEntity shooter, Level world, ItemStack itemStack)
	{
		super(AerialHellEntities.VOLUCITE_SHURIKEN, shooter, world, itemStack);
	}

	public VoluciteShurikenEntity(Level world)
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
        	this.setDeltaMovement(this.getDeltaMovement().x,this.getDeltaMovement().y-0.01,this.getDeltaMovement().z);
        }
        ++this.ticksLiving;
    }
	
	@Override protected float getKnifeDamage() {return 13.0F;}
	@Override protected void applyEntityImpactEffet(Entity entity)
	{
		if (entity instanceof LivingEntity)
        {
        	((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20, 1, true, false));
        }
	}
	@Override protected Item getDefaultItem() {return AerialHellItems.VOLUCITE_SHURIKEN;}
}
