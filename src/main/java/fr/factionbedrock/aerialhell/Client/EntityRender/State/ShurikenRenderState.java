package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.util.Identifier;

public class ShurikenRenderState extends EntityRenderState
{
    public Identifier texture;
    public float YRot;
    public float pitchO;
    public float shurikenZRot;
    public final ItemRenderState item = new ItemRenderState();
}
