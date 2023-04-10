package fr.factionbedrock.aerialhell.Entity;

import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public abstract class AbstractChestMimicEntity extends AbstractMimicEntity
{
	public float mouthOpeningAmplitude;
	public float mouthOpeningFrequencyMalus;
	
	public AbstractChestMimicEntity(EntityType<? extends AbstractChestMimicEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.SetRandomMouthOpeningAmplitudeAndFrequency();
	}
	
	public void SetRandomMouthOpeningAmplitudeAndFrequency()
	{
		float max_amplitude = 0.7F;
		float min_amplitude = 0.3F;
		this.mouthOpeningAmplitude = min_amplitude + (rand.nextFloat() * (max_amplitude - min_amplitude));
		float min_frequency_malus = 5.0F;
		float max_frequency_malus = 11.0F;
		this.mouthOpeningFrequencyMalus = min_frequency_malus + (rand.nextFloat() * (max_frequency_malus - min_frequency_malus));
	}

	@Override protected SoundEvent getDeathSound()
	{
		return SoundEvents.BLOCK_CHEST_CLOSE;
	}
}
