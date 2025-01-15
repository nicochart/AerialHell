package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class ShroomBoomRenderState extends LivingEntityRenderState
{
    public ResourceLocation texture;
    public float swelling;
    public int attackTime;
    public boolean isAggressive;
}
