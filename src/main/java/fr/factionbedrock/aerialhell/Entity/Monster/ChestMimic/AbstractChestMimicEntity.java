package fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic;

import fr.factionbedrock.aerialhell.Entity.AbstractMimicEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.Level;

public abstract class AbstractChestMimicEntity extends AbstractMimicEntity
{
	public float mouthOpeningAmplitude;
	public float mouthOpeningFrequencyMalus;
	
	public AbstractChestMimicEntity(EntityType<? extends AbstractChestMimicEntity> type, Level worldIn)
	{
		super(type, worldIn);
		this.SetRandomMouthOpeningAmplitudeAndFrequency();
	}
	
	public void SetRandomMouthOpeningAmplitudeAndFrequency()
	{
		float max_amplitude = 0.7F;
		float min_amplitude = 0.3F;
		this.mouthOpeningAmplitude = min_amplitude + (random.nextFloat() * (max_amplitude - min_amplitude));
		float min_frequency_malus = 5.0F;
		float max_frequency_malus = 11.0F;
		this.mouthOpeningFrequencyMalus = min_frequency_malus + (random.nextFloat() * (max_frequency_malus - min_frequency_malus));
	}

	@Override protected SoundEvent getDeathSound()
	{
		return SoundEvents.CHEST_CLOSE;
	}
}
