package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.ElementSpiritModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ElementSpiritRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit.AbstractElementSpiritEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit.ElectroSpiritEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit.IceSpiritEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ElementSpiritRender<S extends AbstractElementSpiritEntity> extends MobEntityRenderer<S, ElementSpiritRenderState, ElementSpiritModel<ElementSpiritRenderState>>
{
	private static final Identifier ICE_SPIRIT_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/element_spirit/ice_spirit.png");
	private static final Identifier FIRE_SPIRIT_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/element_spirit/fire_spirit.png");
	private static final Identifier ELECTRO_SPIRIT_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/element_spirit/electro_spirit.png");
	
    public ElementSpiritRender(EntityRendererFactory.Context context)
	{
    	super(context, new ElementSpiritModel<ElementSpiritRenderState>(context.getPart(AerialHellModelLayers.ELEMENT_SPIRIT)), 0.3F);
	}

	@Override public ElementSpiritRenderState createRenderState() {return new ElementSpiritRenderState();}

	@Override protected void scale(ElementSpiritRenderState renderState, MatrixStack poseStack)
    {
        float f = 0.6F;
		poseStack.scale(f, f, f);
		poseStack.translate(0.0D, 0.0D, 0.0D);
    }

	@Override public void updateRenderState(S entity, ElementSpiritRenderState renderState, float f)
	{
		super.updateRenderState(entity, renderState, f);
		renderState.texture = getTexture(entity);
	}

	public Identifier getTexture(S entity)
    {
		if (entity instanceof IceSpiritEntity) {return ICE_SPIRIT_TEXTURE;}
		else if (entity instanceof ElectroSpiritEntity) {return ELECTRO_SPIRIT_TEXTURE;}
		else {return FIRE_SPIRIT_TEXTURE;}
    }

	@Override public Identifier getTexture(ElementSpiritRenderState renderState) {return renderState.texture;}
}