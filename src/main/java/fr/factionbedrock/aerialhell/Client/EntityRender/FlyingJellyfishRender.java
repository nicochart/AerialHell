package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.FlyingJellyfishModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.FlyingJellyfishRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.Flying.FlyingJellyfishEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class FlyingJellyfishRender<J extends FlyingJellyfishEntity> extends MobRenderer<J, FlyingJellyfishRenderState, FlyingJellyfishModel>
{	
	private static String name = "flying_jellyfish";
	private static final Identifier JELLYFISH = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
	private static final Identifier JELLYFISH_SHOOTING = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name +"/" + name + "_shooting.png");
	
	public FlyingJellyfishRender(EntityRendererProvider.Context context)
	{
		super(context, new FlyingJellyfishModel(context.bakeLayer(AerialHellModelLayers.FLYING_JELLYFISH)), 1.0F);
	}

	@Override public FlyingJellyfishRenderState createRenderState() {return new FlyingJellyfishRenderState();}

	@Override public void extractRenderState(J entity, FlyingJellyfishRenderState renderState, float partialTick)
	{
		super.extractRenderState(entity, renderState, partialTick);
		renderState.texture = this.getTextureLocation(entity);
	}

	@Override
	protected void scale(FlyingJellyfishRenderState renderState, PoseStack poseStack)
	{
		poseStack.scale(5.0F, 5.0F, 5.0F);
	}

	@Override public Identifier getTextureLocation(FlyingJellyfishRenderState renderState) {return renderState.texture;}

	public Identifier getTextureLocation(J jellyFish)
	{
		if (jellyFish.isAttacking()) {return JELLYFISH_SHOOTING;}
		else {return JELLYFISH;}
	}
}