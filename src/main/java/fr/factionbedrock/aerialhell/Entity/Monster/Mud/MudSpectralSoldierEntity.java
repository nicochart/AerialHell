package fr.factionbedrock.aerialhell.Entity.Monster.Mud;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.world.World;

public class MudSpectralSoldierEntity extends MudSoldierEntity implements MudSpectralEntity
{
	public MudSpectralSoldierEntity(EntityType<? extends MudSpectralSoldierEntity> type, World world) {super(type, world);}
	
	public static DefaultAttributeContainer.Builder registerAttributes()
    {
		return MudSpectralEntity.createSpectralAttributes(10.0D, 0.0D, 3.0D, 0.20D, 24.0D);
    }

	@Override
	public void handleStatus(byte id)
	{
		if (id == 5) {this.popDisappearingParticles(this, 10);}
		else {super.handleStatus(id);}
	}

	@Override public void tick()
	{
		super.tick();
		this.spectralEntityTick(this);
	}
	
	public int getMaxTicksExisting() {return 500;}
}
