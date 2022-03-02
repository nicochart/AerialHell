package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Monster.EvilCowEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.CowModel;
import net.minecraft.util.ResourceLocation;

public class EvilCowRender extends MobRenderer<EvilCowEntity, CowModel<EvilCowEntity>>
{
    private static final ResourceLocation EVIL_COW_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/evil_cow/evil_cow.png");
    
    public EvilCowRender(EntityRendererManager renderManagerIn)
    {
        super(renderManagerIn, new CowModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(EvilCowEntity entity)
    {
        return EVIL_COW_TEXTURE;
    }
}
