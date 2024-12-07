package fr.factionbedrock.aerialhell.Entity.Monster.Mud;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;

public class MudSpectralGolemEntity extends MudGolemEntity implements MudSpectralEntity
{
	public MudSpectralGolemEntity(EntityType<? extends HostileEntity> type, World world) {super(type, world);}

	public static DefaultAttributeContainer.Builder registerAttributes()
	{
		return MudSpectralEntity.createSpectralAttributes(15.0D, 3.0D, 5.0D, 0.25D, 24.0D);
	}

	@Override
	public void handleStatus(byte id)
	{
		if (id == 5) {this.popDisappearingParticles(this, 17);}
		else {super.handleStatus(id);}
	}

	@Override public void tick()
	{
		super.tick();
		this.spectralEntityTick(this);
	}

	public int getMaxTicksExisting() {return 500;}
}