package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.SandySheepModel;
import fr.factionbedrock.aerialhell.Entity.Passive.SandySheepEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class SandySheepRender extends MobEntityRenderer<SandySheepEntity, SandySheepModel>
{
	private static String name = "sandy_sheep";
    private static final Identifier SANDYSHEEP_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/"+ name +"/" + name + ".png");
    
    public SandySheepRender(EntityRendererFactory.Context context)
    {
        super(context, new SandySheepModel(context.getPart(AerialHellModelLayers.SANDY_SHEEP)), 0.75F);
    }

    @Override public Identifier getTexture(SandySheepEntity entity)
    {
        return SANDYSHEEP_TEXTURE;
    }
}