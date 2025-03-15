package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

public class ChainedGodRenderState extends LivingEntityRenderState
{
    public Identifier texture;
    public int attackTimer;
    public boolean freelyMoving;
}
