package fr.factionbedrock.aerialhell.Client.BlockEntityRenderer;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.BlockEntity.VoluciteBarrierBlockEntity;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellRenderPipelines;
import net.minecraft.client.renderer.FaceInfo;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.EndPortalRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.Direction;
import net.minecraft.util.Util;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;

//depth illusion on the visible faces of a volucite barrier : translucent particle layers far behind the faces (see AerialHellRenderPipelines.VOLUCITE_BARRIER)
//the translucent light blue color comes from the block model, drawn with the chunk (still visible beyond the view distance of this renderer)
public class VoluciteBarrierRenderer implements BlockEntityRenderer<VoluciteBarrierBlockEntity, EndPortalRenderState>
{
    private static final Vector3fc FROM = new Vector3f(0.0F, 0.0F, 0.0F);
    private static final Vector3fc TO = new Vector3f(1.0F, 1.0F, 1.0F);
    //same face vertices as vanilla AbstractEndPortalRenderer
    private static final Map<Direction, List<Vector3fc>> FACES = Util.makeEnumMap(Direction.class, direction ->
    {
        FaceInfo faceInfo = FaceInfo.fromFacing(direction);
        return List.of(faceInfo.getVertexInfo(0).select(FROM, TO), faceInfo.getVertexInfo(1).select(FROM, TO), faceInfo.getVertexInfo(2).select(FROM, TO), faceInfo.getVertexInfo(3).select(FROM, TO));
    });

    public VoluciteBarrierRenderer(BlockEntityRendererProvider.Context context) {}

    @Override public EndPortalRenderState createRenderState() {return new EndPortalRenderState();}

    @Override public void extractRenderState(VoluciteBarrierBlockEntity blockEntity, EndPortalRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress)
    {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.facesToShow.clear();
        for (Direction direction : Direction.values())
        {
            if (blockEntity.shouldRenderFace(direction)) {state.facesToShow.add(direction);}
        }
    }

    @Override public void submit(EndPortalRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera)
    {
        if (state.facesToShow.isEmpty()) {return;}
        submitNodeCollector.submitCustomGeometry(poseStack, AerialHellRenderPipelines.VOLUCITE_BARRIER_RENDER_TYPE, (pose, buffer) ->
        {
            for (Direction direction : state.facesToShow)
            {
                for (Vector3fc faceVertex : FACES.get(direction)) {buffer.addVertex(pose, faceVertex);}
            }
        });
    }
}
