package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class ChainedGodRenderState extends LivingEntityRenderState
{
    public ResourceLocation texture;
    public int attackTimer;
    public boolean freelyMoving;
}
