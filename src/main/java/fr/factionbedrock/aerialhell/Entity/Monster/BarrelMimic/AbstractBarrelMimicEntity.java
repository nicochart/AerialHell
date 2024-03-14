package fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic;

import fr.factionbedrock.aerialhell.Entity.AbstractMimicEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;

public abstract class AbstractBarrelMimicEntity extends AbstractMimicEntity
{
	public AbstractBarrelMimicEntity(EntityType<? extends AbstractBarrelMimicEntity> type, Level worldIn) {super(type, worldIn);}

	@Override protected SoundEvent getDeathSound() {return SoundEvents.BARREL_CLOSE;}
}
