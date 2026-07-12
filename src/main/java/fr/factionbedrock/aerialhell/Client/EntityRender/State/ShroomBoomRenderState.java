package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.ResourceLocation;

public class ShroomBoomRenderState extends HumanoidRenderState
{
    public ResourceLocation texture;
    public float swelling;
    public int attackTime;
    public boolean isAggressive;
}
