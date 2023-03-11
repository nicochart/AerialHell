package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class VoluciteShurikenEntity extends AbstractShurikenEntity
{
	private int ticksLiving;
	
	public VoluciteShurikenEntity(EntityType<? extends VoluciteShurikenEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
		this.ticksLiving = 0;
	}

	public VoluciteShurikenEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.VOLUCITE_SHURIKEN.get(), x, y, z, worldIn);
	}

	public VoluciteShurikenEntity(LivingEntity shooter, World worldIn)
	{
		super(AerialHellEntities.VOLUCITE_SHURIKEN.get(), shooter, worldIn);
	}

	public VoluciteShurikenEntity(World worldIn)
	{
		super(AerialHellEntities.VOLUCITE_SHURIKEN.get(), worldIn);
	}

	public VoluciteShurikenEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
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
        	this.setMotion(this.getMotion().getX(),this.getMotion().getY()-0.01,this.getMotion().getZ());
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
        	((LivingEntity) entity).addPotionEffect(new EffectInstance(new EffectInstance(Effects.LEVITATION, 20, 1, true, false)));
        }
	}

	@Override
	protected Item getDefaultItem()
	{
		return AerialHellBlocksAndItems.VOLUCITE_SHURIKEN.get();
	}	
}
