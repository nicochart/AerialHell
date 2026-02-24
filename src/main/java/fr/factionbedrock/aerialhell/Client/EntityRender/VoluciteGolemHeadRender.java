package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.VoluciteGolemHeadModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteGolemRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.VoluciteGolem.VoluciteGolemHeadEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.jspecify.annotations.Nullable;

public class VoluciteGolemHeadRender extends MobEntityRenderer<VoluciteGolemHeadEntity, VoluciteGolemRenderState, VoluciteGolemHeadModel>
{
	private static String name = "volucite_golem";
    private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");
    private static final Identifier GUARDIAN_BEAM_NORMAL = Identifier.of(AerialHell.MODID, "textures/entity/" + name + "/beam.png");
    private static final Identifier GUARDIAN_BEAM_LOAD = Identifier.of(AerialHell.MODID, "textures/entity/" + name + "/beam_load.png");
    private static final Identifier GUARDIAN_BEAM_OVERHEAT = Identifier.of(AerialHell.MODID, "textures/entity/" + name + "/beam_overheat.png");

    public VoluciteGolemHeadRender(EntityRendererFactory.Context context)
    {
        super(context, new VoluciteGolemHeadModel(context.getPart(AerialHellModelLayers.VOLUCITE_GOLEM_HEAD)), 0.1f);
    }

    @Override public VoluciteGolemRenderState createRenderState() {return new VoluciteGolemRenderState();}

    @Override public void updateRenderState(VoluciteGolemHeadEntity entity, VoluciteGolemRenderState renderState, float partialTick)
    {
        super.updateRenderState(entity, renderState, partialTick);
        renderState.attackTimer = entity.getMaster() != null ? entity.getMaster().attackTimer : 0;
        renderState.eyePosition = entity.getCameraPosVec(partialTick);

        LivingEntity target = entity.getBeamAttackTarget();
        if (entity.isBeaming() && target != null && entity.getBeamEndPos() != null && entity.getPrevBeamEndPos() != null)
        {
            renderState.beamTargetPosition = this.getPosition(entity.getBeamEndPos(), entity.getPrevBeamEndPos(), partialTick);
            renderState.beamTexture = getBeamTextureLocation(entity.getBeamingPhase());
        }
        else
        {
            renderState.beamTargetPosition = null;
            renderState.beamTexture = null;
        }
    }

    private Vec3d getPosition(Vec3d targetPos, Vec3d prevTargetPos, float partialTick)
    {
        double d0 = MathHelper.lerp(partialTick, prevTargetPos.x, targetPos.x);
        double d1 = MathHelper.lerp(partialTick, prevTargetPos.y, targetPos.y);
        double d2 = MathHelper.lerp(partialTick, prevTargetPos.z, targetPos.z);
        return new Vec3d(d0, d1, d2);
    }

    @Override public Identifier getTexture(VoluciteGolemRenderState renderState) {return TEXTURE;}

    @Nullable public Identifier getBeamTextureLocation(int beamingPhase)
    {
        return switch (beamingPhase)
        {
            case 1 -> GUARDIAN_BEAM_LOAD;
            case 2 -> GUARDIAN_BEAM_NORMAL;
            case 3 -> GUARDIAN_BEAM_OVERHEAT;
            default -> null;
        };
    }

    public static RenderLayer getBeamRenderType(Identifier textureLocation)
    {
        return RenderLayers.entityCutoutNoCull(textureLocation);
    }

    @Override public void render(VoluciteGolemRenderState renderState, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState)
    {
        super.render(renderState, matrixStack, orderedRenderCommandQueue, cameraRenderState);
        Vec3d vec3 = renderState.beamTargetPosition;
        if (vec3 != null)
        {
            matrixStack.push();
            matrixStack.translate(0.0F, renderState.standingEyeHeight, 0.0F);
            renderBeam(matrixStack, orderedRenderCommandQueue, vec3.subtract(renderState.eyePosition), renderState.beamTexture);
            matrixStack.pop();
        }
    }

    private static void renderBeam(MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, Vec3d beamVector, Identifier textureLocation)
    {
        float y = (float)(beamVector.length());
        beamVector = beamVector.normalize();
        float xRotFactor = (float)Math.acos(beamVector.y);
        float yRotFactor = ((float)Math.PI / 2F) - (float)Math.atan2(beamVector.z, beamVector.x);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(yRotFactor * (180F / (float)Math.PI)));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(xRotFactor * (180F / (float)Math.PI)));
        int r = 255, g = 255, b = 255;
        float scale = textureLocation == GUARDIAN_BEAM_LOAD ? 0.3F : 1.0F;

        orderedRenderCommandQueue.submitCustom(matrixStack, getBeamRenderType(textureLocation), (pose, consumer) ->
        {
            float rectangleOffset = 0.05F * scale;
            float size = 0.2F * scale;

            float segmentPerUnit = 1.0F;
            int maxSegments = (int) (VoluciteGolemHeadEntity.MAX_BEAM_LENGTH * segmentPerUnit);
            int segmentCount = MathHelper.clamp((int)(y * segmentPerUnit), 1, maxSegments);
            double segmentLength = y / segmentCount;

            for (int i = 0; i < segmentCount; i++)
            {
                double yMin = i * segmentLength;
                double yMax = (i + 1) * segmentLength;
                Vec3d horizontalMinCoords = new Vec3d(-size, yMin, 0.0F);
                Vec3d horizontalMaxCoords = new Vec3d(size, yMax, 0.0F);
                Vec3d verticalMinCoords = new Vec3d(0.0F, yMin, -size);
                Vec3d verticalMaxCoords = new Vec3d(0.0F, yMax, size);

                //horizontal bottom
                rectangle(consumer, pose, horizontalMinCoords.add(0.0D, 0.0D, -rectangleOffset), horizontalMaxCoords.add(0.0D, 0.0D, -rectangleOffset), r, g, b);
                //horizontal top
                rectangle(consumer, pose, horizontalMinCoords.add(0.0D, 0.0D, rectangleOffset), horizontalMaxCoords.add(0.0D, 0.0D, rectangleOffset), r, g, b);
                //vertical right
                rectangle(consumer, pose, verticalMinCoords.add(-rectangleOffset, 0.0D, 0.0D), verticalMaxCoords.add(-rectangleOffset, 0.0D, 0.0D), r, g, b);
                //vertical left
                rectangle(consumer, pose, verticalMinCoords.add(rectangleOffset, 0.0D, 0.0D), verticalMaxCoords.add(rectangleOffset, 0.0D, 0.0D), r, g, b);
            }
        });
    }

    @Override public Box getBoundingBox(VoluciteGolemHeadEntity entity)
    {
        Box box = super.getBoundingBox(entity);

        if (entity.isBeaming() && entity.getBeamEndPos() != null)
        {
            Vec3d beamStart = entity.getBeamStartPos();
            Vec3d beamEnd = entity.getBeamEndPos();

            Box beamBox = new Box(beamStart, beamEnd).expand(1.0);
            return box.union(beamBox);
        }
        return box;
    }

    private static void rectangle(VertexConsumer consumer, MatrixStack.Entry entry, Vec3d minCoords, Vec3d maxCoords, int r, int g, int b)
    {
        vertex(consumer, entry, (float) minCoords.x, (float) maxCoords.y, (float) minCoords.z, r, g, b, 1.0F, 0.0F);
        vertex(consumer, entry, (float) minCoords.x, (float) minCoords.y, (float) minCoords.z, r, g, b, 1.0F, 1.0F);
        vertex(consumer, entry, (float) maxCoords.x, (float) minCoords.y, (float) maxCoords.z, r, g, b, 0.0F, 1.0F);
        vertex(consumer, entry, (float) maxCoords.x, (float) maxCoords.y, (float) maxCoords.z, r, g, b, 0.0F, 0.0F);
    }

    private static void vertex(VertexConsumer consumer, MatrixStack.Entry entry, float x, float y, float z, int red, int green, int blue, float u, float v)
    {
        consumer.vertex(entry, x, y, z).color(red, green, blue, 255).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(LightmapTextureManager.MAX_LIGHT_COORDINATE).normal(entry, 0.0F, 1.0F, 0.0F);
    }
}