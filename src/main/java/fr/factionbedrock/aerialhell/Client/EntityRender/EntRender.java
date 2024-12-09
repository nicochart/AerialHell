package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.EntModel;
import fr.factionbedrock.aerialhell.Entity.Monster.EntEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class EntRender<E extends EntEntity> extends MobEntityRenderer<E, EntModel<E>>
{
    private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/ent/stellar_ent.png");

    public EntRender(EntityRendererFactory.Context context)
    {
        super(context, new EntModel(context.getPart(AerialHellModelLayers.ENT)), 0.3f);
    }

    @Override public Identifier getTexture(E entity) {return TEXTURE;}
}