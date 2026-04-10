package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class VoluciteGolemRenderState extends AerialHellGolemRenderState
{
    public Vec3 beamTargetPosition;
    public Vec3 eyePosition;
    @Nullable public Identifier beamTexture;
}
