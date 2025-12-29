package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.ShroomBoomModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ShroomBoomRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.ShroomBoomEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

//see net.minecraft.client.renderer.entity.CreeperRenderer
public class ShroomBoomRender extends MobRenderer<ShroomBoomEntity, ShroomBoomRenderState, ShroomBoomModel>
{	
	private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/shroomboom/shroomboom.png");
	
	public ShroomBoomRender(EntityRendererProvider.Context context) {super(context, new ShroomBoomModel(context.bakeLayer(AerialHellModelLayers.SHROOMBOOM)), 0.5F);}

	@Override public ShroomBoomRenderState createRenderState() {return new ShroomBoomRenderState();}

	@Override public void extractRenderState(ShroomBoomEntity entity, ShroomBoomRenderState renderState, float partialTick)
	{
		super.extractRenderState(entity, renderState, partialTick);
		renderState.texture = TEXTURE;
		renderState.swelling = entity.getSwelling(partialTick);
		renderState.attackTime = entity.swingTime; //TODO
		renderState.isAggressive = entity.isAggressive();
	}

	@Override protected void scale(ShroomBoomRenderState renderState, PoseStack poseStack)
	{
		float f = renderState.swelling;
	    float f1 = 1.0F + Mth.sin(f * 100.0F) * f * 0.01F;
	    f = Mth.clamp(f, 0.0F, 1.0F);
	    f = f * f;
	    f = f * f;
	    float f2 = (1.0F + f * 0.4F) * f1;
	    float f3 = (1.0F + f * 0.1F) / f1;
	    poseStack.scale(f2, f3, f2);
	}

	@Override protected float getWhiteOverlayProgress(ShroomBoomRenderState renderState)
	{
		float f = renderState.swelling;
	    return (int)(f * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(f, 0.5F, 1.0F);
	}

	@Override public Identifier getTextureLocation(ShroomBoomRenderState renderState) {return renderState.texture;}
}