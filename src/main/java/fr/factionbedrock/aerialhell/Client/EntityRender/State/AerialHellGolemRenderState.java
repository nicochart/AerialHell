package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.phys.Vec3;

public class AerialHellGolemRenderState extends LivingEntityRenderState
{
    public int attackTimer;
    public Vec3 beamTargetPosition;
    public float attackTime;
    public Vec3 eyePosition;
    public float attackScale;
}
