package fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife;

import fr.factionbedrock.aerialhell.Entity.AbtractThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class LightningThrowingKnifeEntity extends AbtractThrowingKnifeEntity
{
	public LightningThrowingKnifeEntity(EntityType<? extends LightningThrowingKnifeEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public LightningThrowingKnifeEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.LIGHTNING_THROWING_KNIFE.get(), x, y, z, worldIn);
	}

	public LightningThrowingKnifeEntity(LivingEntity owner, World worldIn)
	{
		super(AerialHellEntities.LIGHTNING_THROWING_KNIFE.get(), owner, worldIn);
	}

	public LightningThrowingKnifeEntity(World worldIn)
	{
		super(AerialHellEntities.LIGHTNING_THROWING_KNIFE.get(), worldIn);
	}

	public LightningThrowingKnifeEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(AerialHellEntities.LIGHTNING_THROWING_KNIFE.get(), worldIn);
	}

	@Override
	protected void onImpact(RayTraceResult result)
	{
		if (this.world.isRemote)
		{
			return;
		}
		
		if (result.getType() != RayTraceResult.Type.MISS && this.world instanceof ServerWorld)
		{
			LightningBoltEntity lightningBolt = EntityType.LIGHTNING_BOLT.create(this.world);
			lightningBolt.setPosition(this.getPosX(), this.getPosY(), this.getPosZ());
			this.world.addEntity(lightningBolt);
		}
		
		this.remove();
	}

	@Override
	protected Item getDefaultItem()
	{
		return AerialHellBlocksAndItems.LIGHTNING_THROWING_KNIFE.get();
	}
}
