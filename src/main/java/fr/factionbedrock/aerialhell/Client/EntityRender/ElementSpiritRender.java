package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.ElementSpiritModel;
import fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit.AbstractElementSpiritEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit.ElectroSpiritEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit.IceSpiritEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ElementSpiritRender<T extends AbstractElementSpiritEntity> extends MobEntityRenderer<T, ElementSpiritModel<T>>
{
	private static final Identifier ICE_SPIRIT_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/element_spirit/ice_spirit.png");
	private static final Identifier FIRE_SPIRIT_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/element_spirit/fire_spirit.png");
	private static final Identifier ELECTRO_SPIRIT_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/element_spirit/electro_spirit.png");
	
    public ElementSpiritRender(EntityRendererFactory.Context context)
	{
    	super(context, new ElementSpiritModel<T>(context.getPart(AerialHellModelLayers.ELEMENT_SPIRIT)), 0.3F);
	}
    
    @Override
    protected void scale(T entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime)
    {
        float f = 0.6F;
        matrixStackIn.scale(f, f, f);
        matrixStackIn.translate(0.0D, 0.0D, 0.0D);
    }
    
	@Override
	public Identifier getTexture(AbstractElementSpiritEntity entity)
    {
		if (entity instanceof IceSpiritEntity) {return ICE_SPIRIT_TEXTURE;}
		else if (entity instanceof ElectroSpiritEntity) {return ELECTRO_SPIRIT_TEXTURE;}
		else {return FIRE_SPIRIT_TEXTURE;}
    }
}