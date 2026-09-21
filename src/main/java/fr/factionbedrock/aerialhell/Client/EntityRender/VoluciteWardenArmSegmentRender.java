package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.Client.EntityModels.VoluciteWardenPartModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Helper.BeamRenderHelper;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteWardenPartRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenArmSegmentEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class VoluciteWardenArmSegmentRender extends VoluciteWardenPartRender<VoluciteWardenArmSegmentEntity>
{
	public VoluciteWardenArmSegmentRender(EntityRendererProvider.Context context, VoluciteWardenPartModel.Part part) {super(context, part);}

	@Override public VoluciteWardenPartRenderState createRenderState() {return new VoluciteWardenPartRenderState();}

	@Override public void extractRenderState(VoluciteWardenArmSegmentEntity entity, VoluciteWardenPartRenderState renderState, float partialTick)
	{
		super.extractRenderState(entity, renderState, partialTick);

		if (entity.isBeaming() && entity.getBeamEndPos() != null && entity.getPrevBeamEndPos() != null)
		{
			renderState.beamStartPosition = entity.toRelativePos(entity.getBeamStartPos(partialTick));
			renderState.beamTargetPosition = entity.toRelativePos(BeamRenderHelper.getBeamTargetPosition(entity.getBeamEndPos(), entity.getPrevBeamEndPos(), partialTick));
			renderState.beamTexture = BeamRenderHelper.getBeamTextureLocation(entity.getBeamingPhase());
			renderState.maxBeamLength = entity.getMaxBeamLength();
		}
		else
		{
			renderState.beamStartPosition = null;
			renderState.beamTargetPosition = null;
			renderState.beamTexture = null;
			renderState.maxBeamLength = 0.0F;
		}
	}

	@Override protected void setupRotations(VoluciteWardenPartRenderState renderState, PoseStack poseStack, float bodyYRot, float scale)
	{
		if (renderState.beamStartPosition == null || renderState.beamTargetPosition == null) {super.setupRotations(renderState, poseStack, bodyYRot, scale); return;}

		//is beaming : we align the whole segment with beam
		Vec3 beamDirection = renderState.beamTargetPosition.subtract(renderState.beamStartPosition).normalize();

		float yaw = (float)(Mth.atan2(beamDirection.z, beamDirection.x) * (180D / Math.PI)) - 90.0F;

		super.setupRotations(renderState, poseStack, yaw, scale);

		double horizontalDist = Math.sqrt(beamDirection.x * beamDirection.x + beamDirection.z * beamDirection.z);
		float pitch = (float)(Mth.atan2(beamDirection.y, horizontalDist) * (180D / Math.PI));

		float pivotY = 24.0F / 16.0F; // 24.0F is y of arm segment center of rot
		float pivotZ = 0.0F / 16.0F; // 0.0F is z of arm segment center of rot

		poseStack.translate(0.0F, pivotY, pivotZ);
		poseStack.mulPose(Axis.XP.rotationDegrees(pitch));
		poseStack.translate(0.0F, -pivotY, -pivotZ);
	}

	@Override public void submit(VoluciteWardenPartRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState)
	{
		super.submit(renderState, poseStack, submitNodeCollector, cameraRenderState);

		//beam render
		if (renderState.beamTargetPosition != null && renderState.beamStartPosition != null)
		{
			poseStack.pushPose();
			poseStack.translate(renderState.beamStartPosition);

			BeamRenderHelper.renderBeam(poseStack, submitNodeCollector, renderState.beamTargetPosition.subtract(renderState.beamStartPosition), renderState.beamTexture, renderState.maxBeamLength);
			poseStack.popPose();
		}
	}

	@Override public AABB getBoundingBoxForCulling(VoluciteWardenArmSegmentEntity entity)
	{
		AABB box = super.getBoundingBoxForCulling(entity);

		//-- adapting culling bounding box to beam --
		if (entity.isBeaming() && entity.getBeamEndPos() != null)
		{
			return BeamRenderHelper.calculateBeamCullingBox(box, entity.getBeamStartPos(), entity.getBeamEndPos());
		}
		else {return box;}
	}
}