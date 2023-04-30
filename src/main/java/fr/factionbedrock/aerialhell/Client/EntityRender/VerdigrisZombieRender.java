package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.VerdigrisZombieModel;
import fr.factionbedrock.aerialhell.Entity.Monster.VerdigrisZombieEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VerdigrisZombieRender extends MobRenderer<VerdigrisZombieEntity, VerdigrisZombieModel>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/zombie/verdigris.png");
    
    public VerdigrisZombieRender(EntityRendererManager manager)
    {
        super(manager, new VerdigrisZombieModel(), 0.3f);
    }

    @Override public ResourceLocation getEntityTexture(VerdigrisZombieEntity entity) {return TEXTURE;}
}