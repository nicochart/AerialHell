package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.MudGolemModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.AerialHellGolemRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudGolemEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class MudGolemRender extends MobRenderer<MudGolemEntity, AerialHellGolemRenderState, MudGolemModel>
{
	private static String name = "mud_golem";
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public MudGolemRender(EntityRendererProvider.Context context)
    {
        super(context, new MudGolemModel(context.bakeLayer(AerialHellModelLayers.MUD_GOLEM)), 0.6f);
    }

    @Override public AerialHellGolemRenderState createRenderState() {return new AerialHellGolemRenderState();}

    @Override public void extractRenderState(MudGolemEntity entity, AerialHellGolemRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.attackTimer = entity.attackTimer;
    }

    @Override public Identifier getTextureLocation(AerialHellGolemRenderState renderState) {return TEXTURE;}
}