package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.ShroomBoomModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ShroomBoomRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.ShroomBoomEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

//see net.minecraft.client.renderer.entity.CreeperRenderer
public class ShroomBoomRender extends MobEntityRenderer<ShroomBoomEntity, ShroomBoomRenderState, ShroomBoomModel>
{	
	private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/shroomboom/shroomboom.png");
	
	public ShroomBoomRender(EntityRendererFactory.Context context) {super(context, new ShroomBoomModel(context.getPart(AerialHellModelLayers.SHROOMBOOM)), 0.5F);}

	@Override public ShroomBoomRenderState createRenderState() {return new ShroomBoomRenderState();}

	@Override public void updateRenderState(ShroomBoomEntity entity, ShroomBoomRenderState renderState, float partialTick)
	{
		super.updateRenderState(entity, renderState, partialTick);
		renderState.texture = TEXTURE;
		renderState.swelling = entity.getLerpedFuseTime(partialTick);
		renderState.attackTime = entity.handSwingTicks; //TODO
		renderState.isAggressive = entity.isAttacking();
	}

	@Override protected void scale(ShroomBoomRenderState renderState, MatrixStack matrixStack)
	{
		float f = renderState.swelling;
	    float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
	    f = MathHelper.clamp(f, 0.0F, 1.0F);
	    f = f * f;
	    f = f * f;
	    float f2 = (1.0F + f * 0.4F) * f1;
	    float f3 = (1.0F + f * 0.1F) / f1;
	    matrixStack.scale(f2, f3, f2);
	}

	@Override protected float getAnimationCounter(ShroomBoomRenderState renderState)
	{
		float f = renderState.swelling;
	    return (int)(f * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(f, 0.5F, 1.0F);
	}

	@Override public Identifier getTexture(ShroomBoomRenderState renderState) {return renderState.texture;}
}