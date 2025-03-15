package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalGolemCrystalModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalGolemModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.CrystalGolemCrystalLayer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.CrystalGolemRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.CrystalGolemEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CrystalGolemRender extends MobEntityRenderer<CrystalGolemEntity, CrystalGolemRenderState, CrystalGolemModel<CrystalGolemRenderState>>
{
	private static String name = "crystal_golem";
    private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public CrystalGolemRender(EntityRendererFactory.Context context)
    {
        super(context, new CrystalGolemModel<CrystalGolemRenderState>(context.getPart(AerialHellModelLayers.CRYSTAL_GOLEM)), 0.6f);
        this.addFeature(new CrystalGolemCrystalLayer<CrystalGolemRenderState,CrystalGolemModel<CrystalGolemRenderState>>(this, new CrystalGolemCrystalModel<>(context.getPart(AerialHellModelLayers.CRYSTAL_GOLEM_CRYSTAL))));
    }

    @Override public CrystalGolemRenderState createRenderState() {return new CrystalGolemRenderState();}

    @Override protected void scale(CrystalGolemRenderState renderState, MatrixStack poseStack)
    {
        float f = 0.65F;
        poseStack.scale(f, f, f);
    }

    @Override public void updateRenderState(CrystalGolemEntity entity, CrystalGolemRenderState renderState, float f)
    {
        super.updateRenderState(entity, renderState, f);
        renderState.texture = getTexture(entity);
        renderState.attackTimer = entity.attackTimer;
    }
    
    public Identifier getTexture(CrystalGolemEntity entity) {return TEXTURE;}

    @Override public Identifier getTexture(CrystalGolemRenderState renderState) {return renderState.texture;}
}