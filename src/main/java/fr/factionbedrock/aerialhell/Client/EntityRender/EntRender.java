package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.EntModel;
import fr.factionbedrock.aerialhell.Entity.Monster.EntEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EntRender<E extends EntEntity> extends MobRenderer<E, EntModel<E>>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/ent/stellar_ent.png");

    public EntRender(EntityRendererProvider.Context context)
    {
        super(context, new EntModel(context.bakeLayer(AerialHellModelLayers.ENT)), 0.3f);
    }

    @Override public ResourceLocation getTextureLocation(E entity) {return TEXTURE;}
}