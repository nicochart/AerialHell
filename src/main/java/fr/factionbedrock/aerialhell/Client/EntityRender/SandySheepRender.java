package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.SandySheepModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.SandySheepRenderState;
import fr.factionbedrock.aerialhell.Entity.Passive.SandySheepEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class SandySheepRender extends MobEntityRenderer<SandySheepEntity, SandySheepRenderState, SandySheepModel>
{
	private static String name = "sandy_sheep";
    private static final Identifier SANDYSHEEP_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/"+ name +"/" + name + ".png");
    
    public SandySheepRender(EntityRendererFactory.Context context)
    {
        super(context, new SandySheepModel(context.getPart(AerialHellModelLayers.SANDY_SHEEP)), 0.75F);
    }

    @Override public SandySheepRenderState createRenderState() {return new SandySheepRenderState();}

    @Override public void updateRenderState(SandySheepEntity entity, SandySheepRenderState renderState, float partialTick)
    {
        super.updateRenderState(entity, renderState, partialTick);
        renderState.hasWool = entity.hasWool();
        renderState.baby = entity.isBaby();
    }

    @Override public Identifier getTexture(SandySheepRenderState renderState) {return SANDYSHEEP_TEXTURE;}
}