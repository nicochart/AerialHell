package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.ElementSpiritModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ElementSpiritRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit.AbstractElementSpiritEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit.ElectroSpiritEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit.IceSpiritEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class ElementSpiritRender<S extends AbstractElementSpiritEntity> extends MobRenderer<S, ElementSpiritRenderState, ElementSpiritModel<ElementSpiritRenderState>>
{
	private static final Identifier ICE_SPIRIT_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/element_spirit/ice_spirit.png");
	private static final Identifier FIRE_SPIRIT_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/element_spirit/fire_spirit.png");
	private static final Identifier ELECTRO_SPIRIT_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/element_spirit/electro_spirit.png");
	
    public ElementSpiritRender(EntityRendererProvider.Context context)
	{
    	super(context, new ElementSpiritModel<ElementSpiritRenderState>(context.bakeLayer(AerialHellModelLayers.ELEMENT_SPIRIT)), 0.3F);
	}

	@Override public ElementSpiritRenderState createRenderState() {return new ElementSpiritRenderState();}

	@Override protected void scale(ElementSpiritRenderState renderState, PoseStack poseStack)
    {
        float f = 0.6F;
		poseStack.scale(f, f, f);
		poseStack.translate(0.0D, 0.0D, 0.0D);
    }

	@Override public void extractRenderState(S entity, ElementSpiritRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);
		renderState.texture = getTextureLocation(entity);
	}

	public Identifier getTextureLocation(S entity)
    {
		if (entity instanceof IceSpiritEntity) {return ICE_SPIRIT_TEXTURE;}
		else if (entity instanceof ElectroSpiritEntity) {return ELECTRO_SPIRIT_TEXTURE;}
		else {return FIRE_SPIRIT_TEXTURE;}
    }

	@Override public Identifier getTextureLocation(ElementSpiritRenderState renderState) {return renderState.texture;}
}