package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.KodamaModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.KodamaSkinLayer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.KodamaRenderState;
import fr.factionbedrock.aerialhell.Entity.Passive.KodamaEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class KodamaRender<T extends KodamaEntity> extends MobRenderer<T, KodamaRenderState, KodamaModel<KodamaRenderState>>
{
	private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/kodama/kodama.png");
	private static boolean CREATURE_RENDER = false, EMPTY_RENDER = true;

	public KodamaRender(EntityRendererProvider.Context context)
	{
		super(context, new KodamaModel(context.bakeLayer(AerialHellModelLayers.KODAMA), EMPTY_RENDER), 0.0F);
		this.addLayer(new KodamaSkinLayer<>(this, new KodamaModel<KodamaRenderState>(context.bakeLayer(AerialHellModelLayers.KODAMA), CREATURE_RENDER)));
	}

	@Override public KodamaRenderState createRenderState() {return new KodamaRenderState();}

	@Override public void extractRenderState(T entity, KodamaRenderState renderState, float partialTick)
	{
		super.extractRenderState(entity, renderState, partialTick);
		renderState.texture = TEXTURE;
		renderState.scale = calculateScale(entity);
		renderState.faceId = entity.getFaceId();
		renderState.dayTime = entity.level().getDayTime() % 24000;
		renderState.rattleHeadRotZAmplitude = entity.rattleHeadRotZAmplitude;
		renderState.rattlingTiltAngle = entity.getRattlingTiltAngle();
		renderState.maxRattlingTiltAngle = entity.getMaxRattlingTiltAngle();
		renderState.forcedAlphaBonus = this.calculateForcedAlphaBonus(entity);
	}

	@Override public Identifier getTextureLocation(KodamaRenderState renderState) {return renderState.texture;}

	@Override protected void scale(KodamaRenderState renderState, PoseStack poseStack)
	{
		poseStack.scale(renderState.scale, renderState.scale, renderState.scale);
	}

	protected float calculateScale(T entityIn)
	{
		int sizeId = entityIn.getSizeId();
		return sizeId == 1 ? 0.45F : sizeId == 2 ? 0.5F : sizeId == 3 ? 0.55F : sizeId == 4 ? 0.6F : 0.65F;
	}

	private int calculateForcedAlphaBonus(T entity)
	{
		if (entity.timeForceInvisible > 0)
		{
			int transitionTime = entity.getMaxTimeForceInvisible() / 10;
			if (entity.timeForceInvisible > entity.getMaxTimeForceInvisible() - transitionTime)
			{
				return (int) (255 * (float) (entity.getMaxTimeForceInvisible() - entity.timeForceInvisible) / transitionTime);
			}
			else if (entity.timeForceInvisible > transitionTime) {return 255;}
			else //if (0 < entity.timeForceInvisible < transitionTime)
			{
				return (int) (255 * (float) entity.timeForceInvisible / transitionTime);
			}
		}
		else {return 0;}
	}
}