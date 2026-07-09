package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.VerdigrisZombieModel;
import fr.factionbedrock.aerialhell.Entity.Monster.VerdigrisZombieEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class VerdigrisZombieRender extends MobRenderer<VerdigrisZombieEntity, VerdigrisZombieModel>
{
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/zombie/verdigris.png");
    
    public VerdigrisZombieRender(EntityRendererProvider.Context context)
    {
        super(context, new VerdigrisZombieModel(context.bakeLayer(AerialHellModelLayers.VERDIGRIS_ZOMBIE)), 0.3f);
    }

    @Override public ResourceLocation getTextureLocation(VerdigrisZombieEntity entity) {return TEXTURE;}
}