package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.EmptyModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public class EmptyRender<T extends LivingEntity> extends LivingEntityRenderer<T, LivingEntityRenderState, EmptyModel<LivingEntityRenderState>>
{
	public EmptyRender(EntityRendererProvider.Context context)
	{
		super(context, new EmptyModel<>(context.bakeLayer(AerialHellModelLayers.EMPTY)), 0.0F);
	}

	@Override public LivingEntityRenderState createRenderState() {return new LivingEntityRenderState();}

	@Override public void submit(LivingEntityRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {}

	//@Override public void extractRenderState(T entity, LivingEntityRenderState renderState, float partialTick) {}

	@Override @NotNull public Identifier getTextureLocation(LivingEntityRenderState renderState) {return MissingTextureAtlasSprite.getLocation();}
}