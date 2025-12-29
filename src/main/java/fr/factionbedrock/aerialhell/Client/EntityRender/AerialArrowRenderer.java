package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.AerialArrowRenderState;
import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow.RubyArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class AerialArrowRenderer<T extends AbstractAerialArrowEntity, S extends AerialArrowRenderState> extends ArrowRenderer<T, AerialArrowRenderState>
{
	public static final Identifier VOLUCITE_ARROW_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/projectile/arrow/volucite_blowpipe_arrow.png");
	public static final Identifier RUBY_ARROW_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/projectile/arrow/ruby_blowpipe_arrow.png");

    public AerialArrowRenderer(EntityRendererProvider.Context context)
    {
        super(context);
        this.shadowRadius = 0.0F;
    }

    @Override
    public AerialArrowRenderState createRenderState()
    {
        return new AerialArrowRenderState();
    }

    @Override
    protected Identifier getTextureLocation(AerialArrowRenderState renderState)
    {
        return renderState.texture;
    }

    @Override public void extractRenderState(T entity, AerialArrowRenderState renderState, float f)
    {
        super.extractRenderState(entity, renderState, f);
        renderState.texture = getTextureLocation(entity);
    }

    public Identifier getTextureLocation(T entity)
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