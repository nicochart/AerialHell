package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.MudGolemModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.MudGolemRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudGolemEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class MudGolemRender extends MobEntityRenderer<MudGolemEntity, MudGolemRenderState, MudGolemModel>
{
	private static String name = "mud_golem";
    private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public MudGolemRender(EntityRendererFactory.Context context)
    {
        super(context, new MudGolemModel(context.getPart(AerialHellModelLayers.MUD_GOLEM)), 0.6f);
    }

    @Override public MudGolemRenderState createRenderState() {return new MudGolemRenderState();}

    @Override public void updateRenderState(MudGolemEntity entity, MudGolemRenderState renderState, float partialTick)
    {
        super.updateRenderState(entity, renderState, partialTick);
        renderState.attackTimer = entity.attackTimer;
    }

    @Override public Identifier getTexture(MudGolemRenderState renderState) {return TEXTURE;}
}