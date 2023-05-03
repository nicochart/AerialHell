package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow.RubyArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class AerialArrowRenderer<T extends AbstractAerialArrowEntity> extends ArrowRenderer<T>
{
	public static final ResourceLocation VOLUCITE_ARROW_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/projectile/arrow/volucite_blowpipe_arrow.png");
	public static final ResourceLocation RUBY_ARROW_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/projectile/arrow/ruby_blowpipe_arrow.png");

    public AerialArrowRenderer(EntityRendererProvider.Context context)
    {
        super(context);
        this.shadowRadius = 0.0F;
    }

    @Override public ResourceLocation getTextureLocation(T entity)
    {
        if (entity instanceof RubyArrowEntity)
        {
        	return RUBY_ARROW_TEXTURE;
        }
        else //if (entity instanceof VoluciteArrowEntity)
        {
        	return VOLUCITE_ARROW_TEXTURE;
        }
    }
}