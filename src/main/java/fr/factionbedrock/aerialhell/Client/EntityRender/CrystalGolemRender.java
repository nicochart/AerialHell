package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalGolemCrystalModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalGolemModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.CrystalGolemCrystalLayer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.CrystalGolemRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.CrystalGolemEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class CrystalGolemRender extends MobRenderer<CrystalGolemEntity, CrystalGolemRenderState, CrystalGolemModel<CrystalGolemRenderState>>
{
	private static String name = "crystal_golem";
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public CrystalGolemRender(EntityRendererProvider.Context context)
    {
        super(context, new CrystalGolemModel<CrystalGolemRenderState>(context.bakeLayer(AerialHellModelLayers.CRYSTAL_GOLEM)), 0.6f);
        this.addLayer(new CrystalGolemCrystalLayer<CrystalGolemRenderState,CrystalGolemModel<CrystalGolemRenderState>>(this, new CrystalGolemCrystalModel<>(context.bakeLayer(AerialHellModelLayers.CRYSTAL_GOLEM_CRYSTAL))));
    }

    @Override public CrystalGolemRenderState createRenderState() {return new CrystalGolemRenderState();}

    @Override protected void scale(CrystalGolemRenderState renderState, PoseStack poseStack)
    {
        float f = 0.65F;
        poseStack.scale(f, f, f);
    }

    @Override public void extractRenderState(CrystalGolemEntity entity, CrystalGolemRenderState renderState, float f)
    {
        super.extractRenderState(entity, renderState, f);
        renderState.texture = getTextureLocation(entity);
        renderState.attackTimer = entity.attackTimer;
    }
    
    public Identifier getTextureLocation(CrystalGolemEntity entity) {return TEXTURE;}

    @Override public Identifier getTextureLocation(CrystalGolemRenderState renderState) {return renderState.texture;}
}