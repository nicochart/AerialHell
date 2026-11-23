package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class VoluciteWardenHealBeaconRenderState extends LivingEntityRenderState
{
    public int beaconState;
    public List<Vec3> healBeamVectors = List.of(); //only while healing
}
