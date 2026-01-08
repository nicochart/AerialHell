package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.VoluciteGolemModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.AerialHellGolemRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.VoluciteGolemEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class VoluciteGolemRender extends MobRenderer<VoluciteGolemEntity, AerialHellGolemRenderState, VoluciteGolemModel>
{
	private static String name = "volucite_golem";
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public VoluciteGolemRender(EntityRendererProvider.Context context)
    {
        super(context, new VoluciteGolemModel(context.bakeLayer(AerialHellModelLayers.VOLUCITE_GOLEM)), 0.6f);
    }

    @Override public AerialHellGolemRenderState createRenderState() {return new AerialHellGolemRenderState();}

    @Override public void extractRenderState(VoluciteGolemEntity entity, AerialHellGolemRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.attackTimer = entity.attackTimer;
    }

    @Override public Identifier getTextureLocation(AerialHellGolemRenderState renderState) {return TEXTURE;}
}