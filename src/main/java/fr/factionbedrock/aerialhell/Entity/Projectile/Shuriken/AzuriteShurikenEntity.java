package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class AzuriteShurikenEntity extends AbstractShurikenEntity
{
	public AzuriteShurikenEntity(EntityType<? extends AzuriteShurikenEntity> entityTypeIn, Level worldIn)
	{
		super(entityTypeIn, worldIn);
	}

	public AzuriteShurikenEntity(Level level, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy)
	{
		super(AerialHellEntities.AZURITE_SHURIKEN.get(), level, shooter, accelX, accelY, accelZ, velocity, inaccuracy);
	}

	public AzuriteShurikenEntity(double x, double y, double z, Level worldIn)
	{
		super(AerialHellEntities.AZURITE_SHURIKEN.get(), x, y, z, worldIn);
	}

	public AzuriteShurikenEntity(LivingEntity shooter, Level worldIn)
	{
		super(AerialHellEntities.AZURITE_SHURIKEN.get(), shooter, worldIn);
	}

	public AzuriteShurikenEntity(Level worldIn)
	{
		super(AerialHellEntities.AZURITE_SHURIKEN.get(), worldIn);
	}

	/*public AzuriteShurikenEntity(PlayMessages.SpawnEntity packet, Level worldIn)
	{
		super(AerialHellEntities.AZURITE_SHURIKEN.get(), worldIn);
	}*/

	@Override
	protected float getKnifeDamage()
	{
		return 9.0F;
	}
	
	@Override
	protected void applyEntityImpactEffet(Entity entity) {}

	@Override
	protected Item getDefaultItem()
	{
		return AerialHellBlocksAndItems.AZURITE_SHURIKEN.get();
	}	
}
