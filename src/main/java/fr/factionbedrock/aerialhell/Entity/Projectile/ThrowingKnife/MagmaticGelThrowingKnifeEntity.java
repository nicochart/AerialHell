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
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.FMLPlayMessages;

public class MagmaticGelThrowingKnifeEntity extends AbtractThrowingKnifeEntity
{
	public MagmaticGelThrowingKnifeEntity(EntityType<? extends MagmaticGelThrowingKnifeEntity> entityTypeIn, World worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public MagmaticGelThrowingKnifeEntity(double x, double y, double z, World worldIn)
	{
		super(AerialHellEntities.MAGMATIC_GEL_THROWING_KNIFE.get(), x, y, z, worldIn);
	}

	public MagmaticGelThrowingKnifeEntity(LivingEntity owner, World worldIn)
	{
		super(AerialHellEntities.MAGMATIC_GEL_THROWING_KNIFE.get(), owner, worldIn);
	}

	public MagmaticGelThrowingKnifeEntity(World worldIn)
	{
		super(AerialHellEntities.MAGMATIC_GEL_THROWING_KNIFE.get(), worldIn);
	}

	public MagmaticGelThrowingKnifeEntity(FMLPlayMessages.SpawnEntity packet, World worldIn)
	{
		super(AerialHellEntities.MAGMATIC_GEL_THROWING_KNIFE.get(), worldIn);
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
            entity.attackEntityFrom(new DamageSource("throwing_knife_hit"), 8.0F);
            if (entity instanceof LivingEntity)
            {
            	((LivingEntity) entity).addPotionEffect(new EffectInstance(new EffectInstance(Effects.SLOWNESS, 80, 1, true, false)));
            }
            entity.setMotion(entity.getMotion().add(this.getMotion().x / 2, 0.3F, this.getMotion().z / 2));
		}
		
		this.remove();
	}

	@Override
	protected Item getDefaultItem()
	{
		return AerialHellBlocksAndItems.MAGMATIC_GEL_THROWING_KNIFE.get();
	}	
}
