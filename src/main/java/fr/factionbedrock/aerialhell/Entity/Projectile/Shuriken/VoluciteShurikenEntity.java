package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PlayMessages;

public class VoluciteShurikenEntity extends AbstractShurikenEntity
{
	private int ticksLiving;
	
	public VoluciteShurikenEntity(EntityType<? extends VoluciteShurikenEntity> entityTypeIn, Level worldIn)
	{
		super(entityTypeIn, worldIn);
		this.ticksLiving = 0;
	}

	public VoluciteShurikenEntity(double x, double y, double z, Level worldIn)
	{
		super(AerialHellEntities.VOLUCITE_SHURIKEN.get(), x, y, z, worldIn);
	}

	public VoluciteShurikenEntity(LivingEntity shooter, Level worldIn)
	{
		super(AerialHellEntities.VOLUCITE_SHURIKEN.get(), shooter, worldIn);
	}

	public VoluciteShurikenEntity(Level worldIn)
	{
		super(AerialHellEntities.VOLUCITE_SHURIKEN.get(), worldIn);
	}

	public VoluciteShurikenEntity(PlayMessages.SpawnEntity packet, Level worldIn)
	{
		super(AerialHellEntities.VOLUCITE_SHURIKEN.get(), worldIn);
	}
	
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
	
	@Override
	protected float getKnifeDamage()
	{
		return 13.0F;
	}
	
	@Override
	protected void applyEntityImpactEffet(Entity entity)
	{
		if (entity instanceof LivingEntity)
        {
        	((LivingEntity) entity).addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.LEVITATION, 20, 1, true, false)));
        }
	}

	@Override
	protected Item getDefaultItem()
	{
		return AerialHellBlocksAndItems.VOLUCITE_SHURIKEN.get();
	}	
}
