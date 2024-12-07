package fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic;

import fr.factionbedrock.aerialhell.Entity.AbstractMimicEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public abstract class AbstractBarrelMimicEntity extends AbstractMimicEntity
{
	public AbstractBarrelMimicEntity(EntityType<? extends AbstractBarrelMimicEntity> type, World world) {super(type, world);}

	@Override protected SoundEvent getDeathSound() {return SoundEvents.BLOCK_BARREL_CLOSE;}
}
