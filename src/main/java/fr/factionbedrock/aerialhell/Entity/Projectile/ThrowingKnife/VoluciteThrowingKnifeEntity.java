package fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife;

import fr.factionbedrock.aerialhell.Entity.AbtractThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
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
	protected void onImpact(RayTraceResult result)
	{
		if (this.world.isRemote)
		{
			return;
		}
		if (result != null && result.getType() != RayTraceResult.Type.MISS && this.world instanceof ServerWorld && result.getType() == RayTraceResult.Type.ENTITY)
		{
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            entity.attackEntityFrom(new DamageSource("throwing_knife_hit"), 9.0F);
            entity.setMotion(entity.getMotion().add(this.getMotion().x / 2, 0.3F, this.getMotion().z / 2));
		}
		
		this.remove();
	}

	@Override
	protected Item getDefaultItem()
	{
		return AerialHellBlocksAndItems.VOLUCITE_THROWING_KNIFE.get();
	}	
}
