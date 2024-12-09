package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.MudGolemModel;
import fr.factionbedrock.aerialhell.Entity.Monster.Mud.MudGolemEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class MudGolemRender extends MobEntityRenderer<MudGolemEntity, MudGolemModel<MudGolemEntity>>
{
	private static String name = "mud_golem";
    private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public MudGolemRender(EntityRendererFactory.Context context)
    {
        super(context, new MudGolemModel<MudGolemEntity>(context.getPart(AerialHellModelLayers.MUD_GOLEM)), 0.6f);
    }

    @Override
    public Identifier getTexture(MudGolemEntity entity) {return TEXTURE;}
}