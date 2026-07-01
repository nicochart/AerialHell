package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.ResonatorShardModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ResonatorShardRenderState;
import fr.factionbedrock.aerialhell.Entity.Projectile.ResonatorShard.RubyShardEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;

//edited copy of ArrowRenderer
public class ResonatorShardRender<T extends AbstractArrow> extends EntityRenderer<T, ResonatorShardRenderState>
{
    public static final Identifier VOLUCITE_SHARD_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/projectile/shard/volucite.png");
    public static final Identifier RUBY_SHARD_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/projectile/shard/ruby.png");

    private final ResonatorShardModel model;

    public ResonatorShardRender(EntityRendererProvider.Context context)
    {
        super(context);
        this.model = new ResonatorShardModel(context.bakeLayer(AerialHellModelLayers.RESONATOR_SHARD));
    }

    @Override public void submit(ResonatorShardRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera)
    {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(state.yRot - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(state.xRot));
        submitNodeCollector.submitModel(this.model, state, poseStack, RenderTypes.entityCutout(state.texture), state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
        poseStack.popPose();
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override public ResonatorShardRenderState createRenderState() {return new ResonatorShardRenderState();}

    protected Identifier getTextureLocation(T entity)
    {
        if (entity instanceof RubyShardEntity) {return RUBY_SHARD_TEXTURE;}
        else {return VOLUCITE_SHARD_TEXTURE;}
    }

    @Override public void extractRenderState(T entity, ResonatorShardRenderState state, float partialTicks)
    {
        super.extractRenderState(entity, state, partialTicks);
        state.xRot = entity.getXRot(partialTicks);
        state.yRot = entity.getYRot(partialTicks);
        state.shake = (float)entity.shakeTime - partialTicks;
        state.texture = this.getTextureLocation(entity);
    }
}
