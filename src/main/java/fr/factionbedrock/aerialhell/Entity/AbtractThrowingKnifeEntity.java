package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class AbtractThrowingKnifeEntity extends ProjectileItemEntity
{
	public float throwingKnifeZRot; 
	
	public AbtractThrowingKnifeEntity(EntityType<? extends AbtractThrowingKnifeEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
		this.throwingKnifeZRot = -135;
	}

	public AbtractThrowingKnifeEntity(EntityType<? extends AbtractThrowingKnifeEntity> type, double x, double y, double z, World worldIn)
	{
		super(type, x, y, z, worldIn);
		this.throwingKnifeZRot = -135;
	}

	public AbtractThrowingKnifeEntity(EntityType<? extends AbtractThrowingKnifeEntity> type, LivingEntity owner, World worldIn)
	{
		super(type, owner, worldIn);
		this.throwingKnifeZRot = -135;
	}

	public AbtractThrowingKnifeEntity(EntityType<? extends AbtractThrowingKnifeEntity> type, FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(type, worldIn);
		this.throwingKnifeZRot = -135;
	}
	
	@Override
	public IPacket<?> createSpawnPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	abstract protected Item getDefaultItem();
}
