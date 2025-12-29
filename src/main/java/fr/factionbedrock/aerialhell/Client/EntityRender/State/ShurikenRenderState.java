package fr.factionbedrock.aerialhell.Client.EntityRender.State;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.resources.Identifier;

public class ShurikenRenderState extends EntityRenderState
{
    public Identifier texture;
    public float YRot;
    public float xRotO;
    public float shurikenZRot;
    public final ItemStackRenderState item = new ItemStackRenderState();
}
