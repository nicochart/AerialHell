package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.VoluciteWardenPartModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteWardenPartRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenChestPartEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenEntity;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteWardenPartEntity;
import fr.factionbedrock.aerialhell.Entity.MultipartEntity.MasterPartEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class VoluciteWardenPartRender<P extends VoluciteWardenPartEntity> extends MobRenderer<P, VoluciteWardenPartRenderState, VoluciteWardenPartModel>
{
	private final VoluciteWardenPartModel.Part part;

    public VoluciteWardenPartRender(EntityRendererProvider.Context context, VoluciteWardenPartModel.Part part)
	{
		super(context, new VoluciteWardenPartModel(context.bakeLayer(part.getModelLayerLocation()), part), 1.0F);
        this.part = part;
    }

	@Override public VoluciteWardenPartRenderState createRenderState() {return new VoluciteWardenPartRenderState();}

	@Override public void extractRenderState(P entity, VoluciteWardenPartRenderState renderState, float partialTick)
	{
		super.extractRenderState(entity, renderState, partialTick);
		MasterPartEntity master = entity.getMaster();

		//left leg & right leg have -1, other parts (including right leg & left arm) have 1. The parameter is only used for legs in model animation.
		renderState.walkAnimationDirection = master instanceof VoluciteWardenEntity wardenMaster && (wardenMaster.recognizesLeftLegPart(entity) || wardenMaster.recognizesRightArmSegmentPart(entity)) ? 1 : -1;
		renderState.shouldRender = !(entity instanceof VoluciteWardenChestPartEntity chestPartEntity) || chestPartEntity.shouldRender();
		if (master instanceof VoluciteWardenEntity wardenMaster)
		{
			renderState.walkAnimationPos = wardenMaster.walkAnimation.position(partialTick);
			renderState.walkAnimationSpeed = wardenMaster.walkAnimation.speed(partialTick);
		}

		renderState.texture = getTextureLocation(entity, this.part);
	}

	@Override public void submit(VoluciteWardenPartRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState)
	{
		if (!renderState.shouldRender) {return;}
		super.submit(renderState, poseStack, submitNodeCollector, cameraRenderState);
	}

	@Override public AABB getBoundingBoxForCulling(P entity)
	{
		AABB box = super.getBoundingBoxForCulling(entity);

		//specific to Chest part
		if (entity instanceof VoluciteWardenChestPartEntity chestPartEntity && chestPartEntity.shouldRender())
		{
			//Chest total size (lower chest + core, ribs + upper chest) is 10.0F
			float y = 8.0F; //Minus 2.0F because the size of the lower chest is already in the bounding box max position.
			Vec3 start = box.getMinPosition();
			Vec3 end = box.getMaxPosition().add(0.0F, y, 0.0F);
			return new AABB(start, end);
		}
		else {return box;}
	}

	private Identifier getTextureLocation(P entity, VoluciteWardenPartModel.Part part)
    {
		return getPartTextureLocation(part);
    }

	private Identifier getPartTextureLocation(VoluciteWardenPartModel.Part part) {return getPartTextureLocation(part, "");}
	private Identifier getPartTextureLocation(VoluciteWardenPartModel.Part part, String variant)
	{
		String variantString = variant.isEmpty() ? "" : "_" + variant;
		return Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/volucite_warden/" + part.getName() + variantString + ".png");
	}

	@Override public Identifier getTextureLocation(VoluciteWardenPartRenderState renderState) {return renderState.texture;}
}