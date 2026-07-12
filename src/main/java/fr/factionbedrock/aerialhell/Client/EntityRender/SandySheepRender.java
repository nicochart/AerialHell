package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.SandySheepModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.SandySheepRenderState;
import fr.factionbedrock.aerialhell.Entity.Passive.SandySheepEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SandySheepRender extends MobRenderer<SandySheepEntity, SandySheepRenderState, SandySheepModel>
{
	private static String name = "sandy_sheep";
    private static final ResourceLocation SANDYSHEEP_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/"+ name +"/" + name + ".png");
    
    public SandySheepRender(EntityRendererProvider.Context context)
    {
        super(context, new SandySheepModel(context.bakeLayer(AerialHellModelLayers.SANDY_SHEEP)), 0.75F);
    }

    @Override public SandySheepRenderState createRenderState() {return new SandySheepRenderState();}

    @Override public void extractRenderState(SandySheepEntity entity, SandySheepRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.hasWool = entity.hasWool();
        renderState.isBaby = entity.isBaby();
    }

    @Override public ResourceLocation getTextureLocation(SandySheepRenderState renderState) {return SANDYSHEEP_TEXTURE;}
}