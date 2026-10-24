package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.Client.EntityRender.Helper.BeamRenderHelper;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VoluciteLightningRenderState;
import fr.factionbedrock.aerialhell.Entity.Bosses.VoluciteWarden.VoluciteLightningEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.util.RandomSource;
import org.joml.Matrix4fc;

//vanilla-like lightning from the entity position to its end position, along local +Y (oriented like a beam)
//same structure as net.minecraft.client.renderer.entity.LightningBoltRenderer, scaled down (segments of 4 blocks instead of 16) :
//4 translucent layers of square tubes, a zigzag trunk with integer offsets, and branches starting near the source
public class VoluciteLightningRender extends EntityRenderer<VoluciteLightningEntity, VoluciteLightningRenderState>
{
    private static final float RED = 0.35F, GREEN = 0.6F, BLUE = 1.0F, ALPHA = 0.3F;
    //nice RED = 0.45F, GREEN = 0.5F, BLUE = 0.6F, ALPHA = 0.3F;
    //vanilla RED = 0.45F, GREEN = 0.45F, BLUE = 0.5F, ALPHA = 0.3F;
    private static final float SCALE = 0.25F; //vanilla lightning scale is 1
    private static final float SEGMENT_LENGTH = 16.0F * SCALE;
    private static final int LAYER_COUNT = 4;
    private static final int BRANCH_COUNT = 2;
    private static final int BRANCH_SEGMENT_COUNT = 3;

    public VoluciteLightningRender(EntityRendererProvider.Context context) {super(context);}

    @Override public VoluciteLightningRenderState createRenderState() {return new VoluciteLightningRenderState();}

    @Override public void extractRenderState(VoluciteLightningEntity entity, VoluciteLightningRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.endOffset = entity.getEndOffset();
        renderState.seed = entity.seed;
    }

    @Override protected boolean affectedByCulling(VoluciteLightningEntity entity) {return false;}

    @Override public void submit(VoluciteLightningRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState)
    {
        float length = (float) renderState.endOffset.length();
        if (length < 0.01F) {return;}

        RandomSource random = RandomSource.createThreadLocalInstance(renderState.seed);
        int segmentCount = Math.max(2, Math.round(length / SEGMENT_LENGTH));
        float segmentLength = length / segmentCount;

        //trunk : vanilla random walk (steps of -5 to 5), corrected so that the bolt ends exactly at its end position
        float[] trunkX = new float[segmentCount + 1];
        float[] trunkZ = new float[segmentCount + 1];
        for (int i = 1; i <= segmentCount; i++)
        {
            trunkX[i] = trunkX[i - 1] + (random.nextInt(11) - 5) * SCALE;
            trunkZ[i] = trunkZ[i - 1] + (random.nextInt(11) - 5) * SCALE;
        }
        float xDrift = trunkX[segmentCount], zDrift = trunkZ[segmentCount];
        for (int i = 1; i <= segmentCount; i++)
        {
            trunkX[i] -= xDrift * i / segmentCount;
            trunkZ[i] -= zDrift * i / segmentCount;
        }

        //branches : vanilla wider steps (-15 to 15), starting from the first trunk joints
        int[] branchStart = new int[BRANCH_COUNT];
        float[][] branchX = new float[BRANCH_COUNT][BRANCH_SEGMENT_COUNT + 1];
        float[][] branchZ = new float[BRANCH_COUNT][BRANCH_SEGMENT_COUNT + 1];
        for (int b = 0; b < BRANCH_COUNT; b++)
        {
            branchStart[b] = Math.min(b + 1, segmentCount - 1);
            branchX[b][0] = trunkX[branchStart[b]];
            branchZ[b][0] = trunkZ[branchStart[b]];
            for (int k = 1; k <= BRANCH_SEGMENT_COUNT; k++)
            {
                branchX[b][k] = branchX[b][k - 1] + (random.nextInt(31) - 15) * SCALE;
                branchZ[b][k] = branchZ[b][k - 1] + (random.nextInt(31) - 15) * SCALE;
            }
        }

        poseStack.pushPose();
        BeamRenderHelper.orientPoseStack(poseStack, renderState.endOffset);
        submitNodeCollector.submitCustomGeometry(poseStack, RenderTypes.lightning(), (pose, buffer) ->
        {
            Matrix4fc poseMatrix = pose.pose();
            for (int layer = 0; layer < LAYER_COUNT; layer++)
            {
                float radius = (0.1F + layer * 0.2F) * SCALE; //vanilla layer radius, scaled
                for (int i = 0; i < segmentCount; i++)
                {
                    tube(poseMatrix, buffer, trunkX[i], i * segmentLength, trunkZ[i], trunkX[i + 1], (i + 1) * segmentLength, trunkZ[i + 1], radius);
                }
                for (int b = 0; b < BRANCH_COUNT; b++)
                {
                    int branchSegmentCount = Math.min(BRANCH_SEGMENT_COUNT, segmentCount - branchStart[b]); //branch does not go further than the trunk end
                    for (int k = 0; k < branchSegmentCount; k++)
                    {
                        float lowY = (branchStart[b] + k) * segmentLength;
                        tube(poseMatrix, buffer, branchX[b][k], lowY, branchZ[b][k], branchX[b][k + 1], lowY + segmentLength, branchZ[b][k + 1], radius);
                    }
                }
            }
        });
        poseStack.popPose();
    }

    //square tube between two joints, 4 faces (see LightningBoltRenderer.quad)
    private static void tube(Matrix4fc pose, VertexConsumer buffer, float lowX, float lowY, float lowZ, float highX, float highY, float highZ, float radius)
    {
        quad(pose, buffer, lowX, lowY, lowZ, highX, highY, highZ, radius, false, false, true, false);
        quad(pose, buffer, lowX, lowY, lowZ, highX, highY, highZ, radius, true, false, true, true);
        quad(pose, buffer, lowX, lowY, lowZ, highX, highY, highZ, radius, true, true, false, true);
        quad(pose, buffer, lowX, lowY, lowZ, highX, highY, highZ, radius, false, true, false, false);
    }

    private static void quad(Matrix4fc pose, VertexConsumer buffer, float lowX, float lowY, float lowZ, float highX, float highY, float highZ, float radius, boolean rightXPositive, boolean rightZPositive, boolean leftXPositive, boolean leftZPositive)
    {
        buffer.addVertex(pose, lowX + (rightXPositive ? radius : -radius), lowY, lowZ + (rightZPositive ? radius : -radius)).setColor(RED, GREEN, BLUE, ALPHA);
        buffer.addVertex(pose, highX + (rightXPositive ? radius : -radius), highY, highZ + (rightZPositive ? radius : -radius)).setColor(RED, GREEN, BLUE, ALPHA);
        buffer.addVertex(pose, highX + (leftXPositive ? radius : -radius), highY, highZ + (leftZPositive ? radius : -radius)).setColor(RED, GREEN, BLUE, ALPHA);
        buffer.addVertex(pose, lowX + (leftXPositive ? radius : -radius), lowY, lowZ + (leftZPositive ? radius : -radius)).setColor(RED, GREEN, BLUE, ALPHA);
    }
}
