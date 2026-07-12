package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class AutomatonRenderState extends LivingEntityRenderState
{
    public ResourceLocation texture;
    public float scale;
    public int attackTimer;
}
