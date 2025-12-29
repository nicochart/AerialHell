package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class AutomatonRenderState extends LivingEntityRenderState
{
    public Identifier texture;
    public float scale;
    public int attackTimer;
}
