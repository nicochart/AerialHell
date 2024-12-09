package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalGolemCrystalModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalGolemModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.CrystalGolemCrystalLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.CrystalGolemEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CrystalGolemRender extends MobEntityRenderer<CrystalGolemEntity, CrystalGolemModel<CrystalGolemEntity>>
{
	private static String name = "crystal_golem";
    private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public CrystalGolemRender(EntityRendererFactory.Context context)
    {
        super(context, new CrystalGolemModel<CrystalGolemEntity>(context.getPart(AerialHellModelLayers.CRYSTAL_GOLEM)), 0.6f);
        this.addFeature(new CrystalGolemCrystalLayer<CrystalGolemEntity,CrystalGolemModel<CrystalGolemEntity>>(this, new CrystalGolemCrystalModel<>(context.getPart(AerialHellModelLayers.CRYSTAL_GOLEM_CRYSTAL))));
    }
    
    @Override
	protected void scale(CrystalGolemEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		float f = 0.65F;
		matrixStackIn.scale(f, f, f);
	}
    
    @Override public Identifier getTexture(CrystalGolemEntity entity) {return TEXTURE;}
}