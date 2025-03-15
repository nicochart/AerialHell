package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.KodamaModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.KodamaSkinLayer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.KodamaRenderState;
import fr.factionbedrock.aerialhell.Entity.Passive.KodamaEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class KodamaRender<T extends KodamaEntity> extends MobEntityRenderer<T, KodamaRenderState, KodamaModel<KodamaRenderState>>
{
	private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/kodama/kodama.png");
	private static boolean CREATURE_RENDER = false, EMPTY_RENDER = true;

	public KodamaRender(EntityRendererFactory.Context context)
	{
		super(context, new KodamaModel(context.getPart(AerialHellModelLayers.KODAMA), EMPTY_RENDER), 0.0F);
		this.addFeature(new KodamaSkinLayer<>(this, new KodamaModel<KodamaRenderState>(context.getPart(AerialHellModelLayers.KODAMA), CREATURE_RENDER)));
	}

	@Override public KodamaRenderState createRenderState() {return new KodamaRenderState();}

	@Override public void updateRenderState(T entity, KodamaRenderState renderState, float partialTick)
	{
		super.updateRenderState(entity, renderState, partialTick);
		renderState.texture = TEXTURE;
		renderState.scale = calculateScale(entity);
		renderState.faceId = entity.getFaceId();
		renderState.dayTime = entity.getWorld().getTimeOfDay() % 24000;
		renderState.rattleHeadRotZAmplitude = entity.rattleHeadRotZAmplitude;
		renderState.rattlingTiltAngle = entity.getRattlingTiltAngle();
		renderState.maxRattlingTiltAngle = entity.getMaxRattlingTiltAngle();
		renderState.forcedAlphaBonus = this.calculateForcedAlphaBonus(entity);
	}

	@Override public Identifier getTexture(KodamaRenderState renderState) {return renderState.texture;}

	@Override protected void scale(KodamaRenderState renderState, MatrixStack matrixStack)
	{
		matrixStack.scale(renderState.scale, renderState.scale, renderState.scale);
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