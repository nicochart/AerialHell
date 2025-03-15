package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.FlyingJellyfishModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.FlyingJellyfishRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.Flying.FlyingJellyfishEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class FlyingJellyfishRender<J extends FlyingJellyfishEntity> extends MobEntityRenderer<J, FlyingJellyfishRenderState, FlyingJellyfishModel>
{	
	private static String name = "flying_jellyfish";
	private static final Identifier JELLYFISH = Identifier.of(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
	private static final Identifier JELLYFISH_SHOOTING = Identifier.of(AerialHell.MODID, "textures/entity/" + name +"/" + name + "_shooting.png");
	
	public FlyingJellyfishRender(EntityRendererFactory.Context context)
	{
		super(context, new FlyingJellyfishModel(context.getPart(AerialHellModelLayers.FLYING_JELLYFISH)), 1.0F);
	}

	@Override public FlyingJellyfishRenderState createRenderState() {return new FlyingJellyfishRenderState();}

	@Override public void updateRenderState(J entity, FlyingJellyfishRenderState renderState, float partialTick)
	{
		super.updateRenderState(entity, renderState, partialTick);
		renderState.texture = this.getTexture(entity);
	}

	@Override
	protected void scale(FlyingJellyfishRenderState renderState, MatrixStack matrixStack)
	{
		matrixStack.scale(5.0F, 5.0F, 5.0F);
	}

	@Override public Identifier getTexture(FlyingJellyfishRenderState renderState) {return renderState.texture;}

	public Identifier getTexture(J jellyFish)
	{
		if (jellyFish.isAttacking()) {return JELLYFISH_SHOOTING;}
		else {return JELLYFISH;}
	}
}