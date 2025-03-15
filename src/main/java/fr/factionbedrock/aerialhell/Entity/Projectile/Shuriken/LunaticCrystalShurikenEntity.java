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

public class LunaticCrystalShurikenEntity extends AbstractShurikenEntity
{
	public LunaticCrystalShurikenEntity(EntityType<? extends LunaticCrystalShurikenEntity> entityTypeIn, World world)
	{
		super(entityTypeIn, world);
	}

	public LunaticCrystalShurikenEntity(World world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public LunaticCrystalShurikenEntity(double x, double y, double z, World world, ItemStack itemStack)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN, x, y, z, world, itemStack);
	}

	public LunaticCrystalShurikenEntity(LivingEntity shooter, World world, ItemStack itemStack)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN, shooter, world, itemStack);
	}

	public LunaticCrystalShurikenEntity(World world)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN, world);
	}

	/*public LunaticCrystalShurikenEntity(PlayMessages.SpawnEntity packet, World world)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN, world);
	}*/

	@Override protected float getKnifeDamage() {return 12.0F;}
	@Override protected void applyEntityImpactEffet(Entity entity) {}
	@Override protected Item getDefaultItem() {return AerialHellItems.LUNATIC_CRYSTAL_SHURIKEN;}
}
