package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class LightningShurikenEntity extends AbstractShurikenEntity
{
	public LightningShurikenEntity(EntityType<? extends LightningShurikenEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public LightningShurikenEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.LIGHTNING_SHURIKEN.get(), x, y, z, worldIn);
	}

	public LightningShurikenEntity(LivingEntity shooter, World worldIn)
	{
		super(AerialHellEntities.LIGHTNING_SHURIKEN.get(), shooter, worldIn);
	}

	public LightningShurikenEntity(World worldIn)
	{
		super(AerialHellEntities.LIGHTNING_SHURIKEN.get(), worldIn);
	}

	public LightningShurikenEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(AerialHellEntities.LIGHTNING_SHURIKEN.get(), worldIn);
	}
	
	@Override
	protected float getKnifeDamage()
	{
		return 2.0F;
	}
	
	@Override
	protected void applyEntityImpactEffet(Entity entity) {}
	
	@Override
	protected void onImpact(RayTraceResult result)
	{
		if (this.world.isRemote) {return;}
		
		if (result.getType() != RayTraceResult.Type.MISS && this.world instanceof ServerWorld)
		{
			LightningBoltEntity lightningBolt = EntityType.LIGHTNING_BOLT.create(this.world);
			lightningBolt.setPosition(this.getPosX(), this.getPosY(), this.getPosZ());
			this.world.addEntity(lightningBolt);
		}
		super.onImpact(result);
	}

	@Override
	protected Item getDefaultItem()
	{
		return AerialHellBlocksAndItems.LIGHTNING_SHURIKEN.get();
	}
}
