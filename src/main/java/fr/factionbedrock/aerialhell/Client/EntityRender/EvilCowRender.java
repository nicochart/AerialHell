package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Monster.EvilCowEntity;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EvilCowRender extends MobRenderer<EvilCowEntity, CowModel<EvilCowEntity>>
{
    private static final ResourceLocation EVIL_COW_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/evil_cow/evil_cow.png");
    
    public EvilCowRender(EntityRendererProvider.Context context)
    {
        super(context, new CowModel<>(context.bakeLayer(ModelLayers.COW)), 0.7F);
    }

    @Override public ResourceLocation getTextureLocation(EvilCowEntity entity) {return EVIL_COW_TEXTURE;}
}
