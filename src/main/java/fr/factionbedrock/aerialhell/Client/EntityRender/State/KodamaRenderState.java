package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class KodamaRenderState extends LivingEntityRenderState
{
    public ResourceLocation texture;
    public float scale;
    public int faceId;
    public long dayTime;
    public float rattleHeadRotZAmplitude;
    public float rattlingTiltAngle;
    public float maxRattlingTiltAngle;
    public int forcedAlphaBonus;
}
