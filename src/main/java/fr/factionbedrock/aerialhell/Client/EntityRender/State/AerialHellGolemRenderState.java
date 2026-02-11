package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class AerialHellGolemRenderState extends LivingEntityRenderState
{
    public int attackTimer;
    public Vec3 beamTargetPosition;
    public Vec3 eyePosition;
    @Nullable public Identifier beamTexture;
}
