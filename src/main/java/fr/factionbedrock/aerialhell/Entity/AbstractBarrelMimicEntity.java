package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public abstract class AbstractBarrelMimicEntity extends AbstractMimicEntity
{
	public AbstractBarrelMimicEntity(EntityType<? extends AbstractBarrelMimicEntity> type, World worldIn) {super(type, worldIn);}

	@Override protected SoundEvent getDeathSound() {return SoundEvents.BLOCK_BARREL_CLOSE;}
}
