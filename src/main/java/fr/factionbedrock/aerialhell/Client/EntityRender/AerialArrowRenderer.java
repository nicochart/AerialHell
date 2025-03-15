package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.AerialArrowRenderState;
import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractAerialArrowEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.BlowpipeArrow.RubyArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

public class AerialArrowRenderer<T extends AbstractAerialArrowEntity, S extends AerialArrowRenderState> extends ProjectileEntityRenderer<T, AerialArrowRenderState>
{
	public static final Identifier VOLUCITE_ARROW_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/projectile/arrow/volucite_blowpipe_arrow.png");
	public static final Identifier RUBY_ARROW_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/projectile/arrow/ruby_blowpipe_arrow.png");

    public AerialArrowRenderer(EntityRendererFactory.Context context)
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
    protected Identifier getTexture(AerialArrowRenderState renderState)
    {
        return renderState.texture;
    }

    @Override public void updateRenderState(T entity, AerialArrowRenderState renderState, float f)
    {
        super.updateRenderState(entity, renderState, f);
        renderState.texture = getTexture(entity);
    }

    public Identifier getTexture(T entity)
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