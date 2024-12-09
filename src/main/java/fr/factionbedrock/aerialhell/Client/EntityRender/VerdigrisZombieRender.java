package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.VerdigrisZombieModel;
import fr.factionbedrock.aerialhell.Entity.Monster.VerdigrisZombieEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class VerdigrisZombieRender extends MobEntityRenderer<VerdigrisZombieEntity, VerdigrisZombieModel>
{
    private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/zombie/verdigris.png");
    
    public VerdigrisZombieRender(EntityRendererFactory.Context context)
    {
        super(context, new VerdigrisZombieModel(context.getPart(AerialHellModelLayers.VERDIGRIS_ZOMBIE)), 0.3f);
    }

    @Override public Identifier getTexture(VerdigrisZombieEntity entity) {return TEXTURE;}
}