package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class VoluciteGolemRenderState extends AerialHellGolemRenderState
{
    public Vec3 beamTargetPosition;
    public Vec3 eyePosition;
    @Nullable public ResourceLocation beamTexture;
}
