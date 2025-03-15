package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalSlimeModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.CrystalSlimeGelAndCrystalLayer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.CrystalSlimeRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.CrystalSlimeEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class CrystalSlimeRender extends MobEntityRenderer<CrystalSlimeEntity, CrystalSlimeRenderState, CrystalSlimeModel>
{	
	private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/crystal_slime/crystal_slime.png");
	
	public CrystalSlimeRender(EntityRendererFactory.Context context)
	{
		super(context, new CrystalSlimeModel(context.getPart(AerialHellModelLayers.CRYSTAL_SLIME),false), 0.3F);
		this.addFeature(new CrystalSlimeGelAndCrystalLayer(this, new CrystalSlimeModel(context.getPart(AerialHellModelLayers.CRYSTAL_SLIME),true)));
	}

	@Override public CrystalSlimeRenderState createRenderState() {return new CrystalSlimeRenderState();}

	@Override protected void scale(CrystalSlimeRenderState renderState, MatrixStack poseStack)
	{
		float f = 0.879F;
		poseStack.scale(f, f, f);
		float f1 = 2; //entitylivingbaseIn.getSlimeSize() isn't working;
		float f2 = 0.0F;
		float f3 = 1.0F / (f2 + 1.0F);
		poseStack.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

	@Override public void updateRenderState(CrystalSlimeEntity entity, CrystalSlimeRenderState renderState, float f)
	{
		super.updateRenderState(entity, renderState, f);
		renderState.texture = getTexture(entity);
	}

	public Identifier getTexture(CrystalSlimeEntity entity)
	{
		return TEXTURE;
	}

	@Override public Identifier getTexture(CrystalSlimeRenderState renderState) {return renderState.texture;}
}