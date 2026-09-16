package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class VoluciteWardenPartRenderState extends LivingEntityRenderState
{
    public Identifier texture;
    public int walkAnimationDirection;
    public boolean shouldRender;

    @Nullable public Vec3 beamTargetPosition;
    @Nullable public Vec3 beamStartPosition;
    public float maxBeamLength;
    @Nullable public Identifier beamTexture;
}
