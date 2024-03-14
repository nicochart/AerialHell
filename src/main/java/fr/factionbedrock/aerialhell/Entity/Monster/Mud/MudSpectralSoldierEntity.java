package fr.factionbedrock.aerialhell.Entity.Monster.Mud;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MudSpectralSoldierEntity extends MudSoldierEntity implements MudSpectralEntity
{
	public MudSpectralSoldierEntity(EntityType<? extends MudSpectralSoldierEntity> type, Level level) {super(type, level);}
	
	public static AttributeSupplier.Builder registerAttributes()
    {
		return MudSpectralEntity.createSpectralAttributes(10.0D, 0.0D, 3.0D, 0.20D, 24.0D);
    }

	@Override @OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id)
	{
		if (id == 5) {this.popDisappearingParticles(this, 10);}
		else {super.handleEntityEvent(id);}
	}

	@Override public void tick()
	{
		super.tick();
		this.spectralEntityTick(this);
	}
	
	public int getMaxTicksExisting() {return 500;}
}
