package fr.factionbedrock.aerialhell.Entity.Monster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;

public class MudSpectralSoldierEntity extends MudSoldierEntity
{
	public MudSpectralSoldierEntity(EntityType<? extends MudSpectralSoldierEntity> type, Level world)
    {
        super(type, world);
    }
	
	public static AttributeSupplier.Builder registerAttributes()
    {
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 10.0D)
				.add(Attributes.FOLLOW_RANGE, 24.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.20D)
				.add(Attributes.ATTACK_DAMAGE, 3.0D);
    }
	
	@Override
	public void tick()
	{
		super.tick();
		if (this.tickCount > getMaxTicksExisting() - 2 && this.level.isClientSide())
		{
			for (int i=0; i<10; i++) this.level.addParticle(ParticleTypes.LARGE_SMOKE, this.getX() + random.nextFloat() - 0.5, this.getY() + 2 * random.nextFloat(), this.getZ() + random.nextFloat(), 0.5 * (random.nextFloat()) - 0.5, 0.3D, 0.5 * (random.nextFloat() - 0.5));
		}
		if (this.tickCount > getMaxTicksExisting())
		{
			this.removeAfterChangingDimensions();
		}
	}
	
	public int getMaxTicksExisting()
	{
		return 500;
	}
}
