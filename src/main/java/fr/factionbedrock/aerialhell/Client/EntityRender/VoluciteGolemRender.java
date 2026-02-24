package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.VoluciteGolemModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteGolemRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.VoluciteGolem.VoluciteGolemEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class VoluciteGolemRender extends MobEntityRenderer<VoluciteGolemEntity, VoluciteGolemRenderState, VoluciteGolemModel>
{
	private static String name = "volucite_golem";
    private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public VoluciteGolemRender(EntityRendererFactory.Context context)
    {
        super(context, new VoluciteGolemModel(context.getPart(AerialHellModelLayers.VOLUCITE_GOLEM)), 0.6f);
    }

    @Override public VoluciteGolemRenderState createRenderState() {return new VoluciteGolemRenderState();}

    @Override public void updateRenderState(VoluciteGolemEntity entity, VoluciteGolemRenderState renderState, float partialTick)
    {
        super.updateRenderState(entity, renderState, partialTick);
        renderState.attackTimer = entity.attackTimer;
        renderState.eyePosition = entity.getCameraPosVec(partialTick);
    }

    @Override public Identifier getTexture(VoluciteGolemRenderState renderState) {return TEXTURE;}
}