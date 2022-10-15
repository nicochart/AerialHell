package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.CortinariusCowShroomLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.EvilCowEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.util.ResourceLocation;

public class CortinariusCowRender<T extends EvilCowEntity> extends MobRenderer<T, CowModel<T>>
{
    private static final ResourceLocation CORTINARIUS_COW_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/cortinarius_cow/cortinarius_cow.png");
    
    public CortinariusCowRender(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new CowModel<>(), 0.7F);
        this.addLayer(new CortinariusCowShroomLayer<T, CowModel<T>>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(EvilCowEntity entity) {return CORTINARIUS_COW_TEXTURE;}
}
