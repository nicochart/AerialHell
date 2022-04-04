package fr.factionbedrock.aerialhell.Entity.Monster;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;

public class MudSpectralGolemEntity extends MudGolemEntity
{

	public MudSpectralGolemEntity(EntityType<? extends MonsterEntity> type, World world)
	{
		super(type, world);
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 15.0D)
                .createMutableAttribute(Attributes.ARMOR, 3.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 5.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D);
    }
	
	@Override
	public void tick()
	{
		super.tick();
		if (this.ticksExisted > getMaxTicksExisting() - 2)
		{
			for (int i=0; i<10; i++) this.world.addParticle(ParticleTypes.LARGE_SMOKE, this.getPosX() + rand.nextFloat() - 0.5, this.getPosY() + 2 * rand.nextFloat(), this.getPosZ() + rand.nextFloat(), 0.5 * (rand.nextFloat()) - 0.5, 0.3D, 0.5 * (rand.nextFloat() - 0.5));
		}
		if (this.ticksExisted > getMaxTicksExisting())
		{
			this.setDead();
		}
	}
	
	public int getMaxTicksExisting()
	{
		return 500;
	}
}