package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.jspecify.annotations.Nullable;

public class VoluciteGolemRenderState extends AerialHellGolemRenderState
{
    public Vec3d beamTargetPosition;
    public Vec3d eyePosition;
    @Nullable public Identifier beamTexture;
}
