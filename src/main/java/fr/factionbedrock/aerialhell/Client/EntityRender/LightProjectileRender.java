package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.LightProjectileRenderState;
import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractLightProjectileEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.LunaticProjectileEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;

//see net.minecraft.client.renderer.entity.DragonFireballRenderer
public class LightProjectileRender<T extends AbstractLightProjectileEntity> extends EntityRenderer<T, LightProjectileRenderState>
{
    public static final Identifier LUNATIC_PROJECTILE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/projectile/lunatic_projectile.png");
    public static final Identifier SHADOW_PROJECTILE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/projectile/shadow_projectile.png");

    public LightProjectileRender(EntityRendererProvider.Context context)
    {
        super(context);
        this.shadowRadius = 0.0F;
    }

    @Override public void submit(LightProjectileRenderState renderState, PoseStack matrices, SubmitNodeCollector queue, CameraRenderState cameraState)
    {
        matrices.pushPose();
        matrices.scale(2.0F, 2.0F, 2.0F);
        matrices.mulPose(cameraState.orientation);
        RenderType layer = getRenderLayer(renderState.texture);
        queue.submitCustomGeometry(matrices, layer, (matricesEntry, vertexConsumer) ->
        {
            vertex(vertexConsumer, matricesEntry, renderState.lightCoords, 0.0F, 0, 0, 1);
            vertex(vertexConsumer, matricesEntry, renderState.lightCoords, 1.0F, 0, 1, 1);
            vertex(vertexConsumer, matricesEntry, renderState.lightCoords, 1.0F, 1, 1, 0);
            vertex(vertexConsumer, matricesEntry, renderState.lightCoords, 0.0F, 1, 0, 0);
        });
        matrices.popPose();
        super.submit(renderState, matrices, queue, cameraState);
    }

    @Override protected int getBlockLightLevel(T entity, BlockPos partialTicks)
    {
        if (entity instanceof LunaticProjectileEntity) {return 15;}
        else {return 13;}
    }

    @Override public LightProjectileRenderState createRenderState() {return new LightProjectileRenderState();}

    @Override public void extractRenderState(T entity, LightProjectileRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.texture = getTextureLocation(entity);
    }

    public static RenderType getRenderLayer(Identifier texture) {return RenderTypes.entityCutout(texture);}

    public Identifier getTextureLocation(T entity)
    {
        if (entity instanceof LunaticProjectileEntity) {return LUNATIC_PROJECTILE;}
        else {return SHADOW_PROJECTILE;}
    }

    private static void vertex(VertexConsumer vertexConsumer, PoseStack.Pose matrix, int packedLight, float x, int y, int u, int v)
    {
        vertexConsumer.addVertex(matrix, x - 0.5F, (float)y - 0.25F, 0.0F).setColor(-1).setUv((float)u, (float)v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(matrix, 0.0F, 1.0F, 0.0F);
    }
}
