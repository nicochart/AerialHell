package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalSlimeModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.CrystalSlimeGelAndCrystalLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.CrystalSlimeEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CrystalSlimeRender extends MobEntityRenderer<CrystalSlimeEntity, CrystalSlimeModel>
{	
	private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/crystal_slime/crystal_slime.png");
	
	public CrystalSlimeRender(EntityRendererFactory.Context context)
	{
		super(context, new CrystalSlimeModel(context.getPart(AerialHellModelLayers.CRYSTAL_SLIME),false), 0.3F);
		this.addFeature(new CrystalSlimeGelAndCrystalLayer(this, new CrystalSlimeModel(context.getPart(AerialHellModelLayers.CRYSTAL_SLIME),true)));
	}
	
	@Override
	protected void scale(CrystalSlimeEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		float f = 0.879F;
		matrixStackIn.scale(f, f, f);
		float f1 = 2; //entitylivingbaseIn.getSlimeSize() isn't working;
		float f2 = 0.0F;
		float f3 = 1.0F / (f2 + 1.0F);
		matrixStackIn.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}
	
	@Override
	public Identifier getTexture(CrystalSlimeEntity entity)
	{
		return TEXTURE;
	}
}