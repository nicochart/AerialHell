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
    public boolean shouldRenderUpperStructure; //specific to lower chest : upper chest plate & core ribs
    public boolean shouldRenderReactor; //specific to head : reactor under the head, when detached (final phase)
    public float awakeningProgress; //0 : never awakened warden, parts look like volucite stone (no awakened layers). awakened layers fade in up to 1 during the first awakening

    //specific to arm segments
    @Nullable public Vec3 beamTargetPosition;
    @Nullable public Vec3 beamStartPosition;
    public float maxBeamLength;
    public float beamScale;
    public float beamConvergenceAngle;
    public int beamColor;
    @Nullable public Identifier beamTexture;
}
