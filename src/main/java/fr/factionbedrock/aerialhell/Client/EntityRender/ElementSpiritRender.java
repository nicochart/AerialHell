package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.matrix.MatrixStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.ElementSpiritModel;
import fr.factionbedrock.aerialhell.Entity.AbstractElementSpiritEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit.ElectroSpiritEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ElementSpirit.IceSpiritEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class ElementSpiritRender<T extends AbstractElementSpiritEntity> extends MobRenderer<T, ElementSpiritModel<T>>
{
	private static final ResourceLocation ICE_SPIRIT_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/element_spirit/ice_spirit.png");
	private static final ResourceLocation FIRE_SPIRIT_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/element_spirit/fire_spirit.png");
	private static final ResourceLocation ELECTRO_SPIRIT_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/element_spirit/electro_spirit.png");
	
    public ElementSpiritRender(EntityRendererManager renderManagerIn)
	{
    	super(renderManagerIn, new ElementSpiritModel<T>(), 0.3F);
	}
    
    @Override
    protected void preRenderCallback(T entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime)
    {
        float f = 1.25F;
        matrixStackIn.scale(f, f, f);
        matrixStackIn.translate(0.0D, 0.0D, 0.0D);
    }
    
	@Override
	public ResourceLocation getEntityTexture(AbstractElementSpiritEntity entity)
    {
		if (entity instanceof IceSpiritEntity)
		{
			return ICE_SPIRIT_TEXTURE;
		}
		else if (entity instanceof ElectroSpiritEntity)
		{
			return ELECTRO_SPIRIT_TEXTURE;
		}
		else
		{
			return FIRE_SPIRIT_TEXTURE;
		}
    }
}