package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AzuriteShurikenEntity extends AbstractShurikenEntity
{
	public AzuriteShurikenEntity(EntityType<? extends AzuriteShurikenEntity> entityTypeIn, World world)
	{
		super(entityTypeIn, world);
	}

	public AzuriteShurikenEntity(World world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.AZURITE_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public AzuriteShurikenEntity(double x, double y, double z, World world, ItemStack itemStack)
	{
		super(AerialHellEntities.AZURITE_SHURIKEN, x, y, z, world, itemStack);
	}

	public AzuriteShurikenEntity(LivingEntity shooter, World world, ItemStack itemStack)
	{
		super(AerialHellEntities.AZURITE_SHURIKEN, shooter, world, itemStack);
	}

	public AzuriteShurikenEntity(World world)
	{
		super(AerialHellEntities.AZURITE_SHURIKEN, world);
	}

	/*public AzuriteShurikenEntity(PlayMessages.SpawnEntity packet, Level worldIn)
	{
		super(AerialHellEntities.AZURITE_SHURIKEN.get(), worldIn);
	}*/

	@Override protected float getKnifeDamage() {return 9.0F;}
	@Override protected void applyEntityImpactEffet(Entity entity) {}
	@Override protected Item getDefaultItem() {return AerialHellItems.AZURITE_SHURIKEN;}
}
