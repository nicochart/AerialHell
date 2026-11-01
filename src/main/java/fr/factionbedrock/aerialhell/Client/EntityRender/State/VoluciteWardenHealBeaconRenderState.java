package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class VoluciteWardenHealBeaconRenderState extends LivingEntityRenderState
{
    public int beaconState;
    @Nullable public Vec3 healBeamVector; //from beacon core to warden core, only while healing
}
