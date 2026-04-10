package fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken;

import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LunaticCrystalShurikenEntity extends AbstractShurikenEntity
{
	public LunaticCrystalShurikenEntity(EntityType<? extends LunaticCrystalShurikenEntity> entityTypeIn, Level world)
	{
		super(entityTypeIn, world);
	}

	public LunaticCrystalShurikenEntity(Level world, LivingEntity shooter, double accelX, double accelY, double accelZ, float velocity, float inaccuracy, ItemStack itemStack)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN, world, shooter, accelX, accelY, accelZ, velocity, inaccuracy, itemStack);
	}

	public LunaticCrystalShurikenEntity(double x, double y, double z, Level world, ItemStack itemStack)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN, x, y, z, world, itemStack);
	}

	public LunaticCrystalShurikenEntity(LivingEntity shooter, Level world, ItemStack itemStack)
	{
		super(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN, shooter, world, itemStack);
	}

	public LunaticCrystalShurikenEntity(Level world)
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
