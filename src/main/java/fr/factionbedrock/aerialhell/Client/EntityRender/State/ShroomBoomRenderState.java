package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.renderer.entity.state.UndeadRenderState;
import net.minecraft.resources.Identifier;

public class ShroomBoomRenderState extends UndeadRenderState
{
    public Identifier texture;
    public float swelling;
    public int attackTime;
    public boolean isAggressive;
}
