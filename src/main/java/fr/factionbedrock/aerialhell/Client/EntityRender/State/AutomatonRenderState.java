package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

public class AutomatonRenderState extends LivingEntityRenderState
{
    public Identifier texture;
    public float scale;
    public int attackTimer;
}
