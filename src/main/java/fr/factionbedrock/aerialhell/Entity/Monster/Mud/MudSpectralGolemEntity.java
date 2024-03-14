package fr.factionbedrock.aerialhell.Entity.Monster.Mud;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MudSpectralGolemEntity extends MudGolemEntity implements MudSpectralEntity
{
	public MudSpectralGolemEntity(EntityType<? extends Monster> type, Level level) {super(type, level);}

	public static AttributeSupplier.Builder registerAttributes()
	{
		return MudSpectralEntity.createSpectralAttributes(15.0D, 3.0D, 5.0D, 0.25D, 24.0D);
	}

	@Override @OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id)
	{
		if (id == 5) {this.popDisappearingParticles(this, 17);}
		else {super.handleEntityEvent(id);}
	}

	@Override public void tick()
	{
		super.tick();
		this.spectralEntityTick(this);
	}

	public int getMaxTicksExisting() {return 500;}
}