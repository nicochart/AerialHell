package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.MudGolemModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.MudGolemRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudGolemEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MudGolemRender extends MobRenderer<MudGolemEntity, MudGolemRenderState, MudGolemModel>
{
	private static String name = "mud_golem";
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public MudGolemRender(EntityRendererProvider.Context context)
    {
        super(context, new MudGolemModel(context.bakeLayer(AerialHellModelLayers.MUD_GOLEM)), 0.6f);
    }

    @Override public MudGolemRenderState createRenderState() {return new MudGolemRenderState();}

    @Override public void extractRenderState(MudGolemEntity entity, MudGolemRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.attackTimer = entity.attackTimer;
    }

    @Override public ResourceLocation getTextureLocation(MudGolemRenderState renderState) {return TEXTURE;}
}