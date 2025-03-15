package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

public class ShroomBoomRenderState extends LivingEntityRenderState
{
    public Identifier texture;
    public float swelling;
    public int attackTime;
    public boolean isAggressive;
}
