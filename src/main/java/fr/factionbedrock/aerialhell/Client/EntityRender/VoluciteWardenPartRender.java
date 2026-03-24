package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.VoluciteWardenPartModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteWardenRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenChestPartEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenPartEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.MasterPartEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.PartInfo;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.Identifier;

public class VoluciteWardenPartRender extends MobRenderer<VoluciteWardenPartEntity, VoluciteWardenRenderState, VoluciteWardenPartModel>
{
	private final VoluciteWardenPartModel.Part part;

    public VoluciteWardenPartRender(EntityRendererProvider.Context context, VoluciteWardenPartModel.Part part)
	{
		super(context, new VoluciteWardenPartModel(context.bakeLayer(part.getModelLayerLocation()), part), 1.0F);
        this.part = part;
    }

	@Override public VoluciteWardenRenderState createRenderState() {return new VoluciteWardenRenderState();}

	@Override public void extractRenderState(VoluciteWardenPartEntity entity, VoluciteWardenRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);
		MasterPartEntity master = entity.getMaster();

		//left leg & right leg have -1, other parts (including right leg & left arm) have 1. The parameter is only used for legs in model animation.
		renderState.walkAnimationDirection = master instanceof VoluciteWardenEntity wardenMaster && (wardenMaster.recognizesLeftLegPart(entity) || wardenMaster.recognizesRightArmSegmentPart(entity)) ? 1 : -1;
		renderState.shouldRender = !(entity instanceof VoluciteWardenChestPartEntity chestPartEntity) || chestPartEntity.shouldRender();
		if (master instanceof VoluciteWardenEntity wardenMaster)
		{
			renderState.walkAnimationPos = wardenMaster.walkAnimation.position(f);
			renderState.walkAnimationSpeed = wardenMaster.walkAnimation.speed(f);
		}

		renderState.texture = getTextureLocation(entity, this.part);
	}

	@Override public void submit(VoluciteWardenRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState)
	{
		if (!renderState.shouldRender) {return;}
		super.submit(renderState, poseStack, submitNodeCollector, cameraRenderState);
	}

	private Identifier getTextureLocation(VoluciteWardenPartEntity entity, VoluciteWardenPartModel.Part part)
    {
		return getPartTextureLocation(part);
    }

	private Identifier getPartTextureLocation(VoluciteWardenPartModel.Part part) {return getPartTextureLocation(part, "");}
	private Identifier getPartTextureLocation(VoluciteWardenPartModel.Part part, String variant)
	{
		String variantString = variant.isEmpty() ? "" : "_" + variant;
		return Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/volucite_warden/" + part.getName() + variantString + ".png");
	}

	@Override public Identifier getTextureLocation(VoluciteWardenRenderState renderState) {return renderState.texture;}
}