package fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife;

import fr.factionbedrock.aerialhell.Entity.AbtractThrowingKnifeEntity;
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

public class VoluciteThrowingKnifeEntity extends AbtractThrowingKnifeEntity
{
	private int ticksLiving;
	
	public VoluciteThrowingKnifeEntity(EntityType<? extends VoluciteThrowingKnifeEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
		this.ticksLiving = 0;
	}

	public VoluciteThrowingKnifeEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.VOLUCITE_THROWING_KNIFE.get(), x, y, z, worldIn);
	}

	public VoluciteThrowingKnifeEntity(LivingEntity owner, World worldIn)
	{
		super(AerialHellEntities.VOLUCITE_THROWING_KNIFE.get(), owner, worldIn);
	}

	public VoluciteThrowingKnifeEntity(World worldIn)
	{
		super(AerialHellEntities.VOLUCITE_THROWING_KNIFE.get(), worldIn);
	}

	public VoluciteThrowingKnifeEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(AerialHellEntities.VOLUCITE_THROWING_KNIFE.get(), worldIn);
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
		return 11.0F;
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
		return AerialHellBlocksAndItems.VOLUCITE_THROWING_KNIFE.get();
	}	
}
