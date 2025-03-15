package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

public class KodamaRenderState extends LivingEntityRenderState
{
    public Identifier texture;
    public float scale;
    public int faceId;
    public long dayTime;
    public float rattleHeadRotZAmplitude;
    public float rattlingTiltAngle;
    public float maxRattlingTiltAngle;
    public int forcedAlphaBonus;
}
