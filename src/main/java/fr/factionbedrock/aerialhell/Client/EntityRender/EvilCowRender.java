package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Monster.EvilCowEntity;
import net.minecraft.client.model.animal.cow.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class EvilCowRender extends MobRenderer<EvilCowEntity, LivingEntityRenderState, CowModel>
{
    private static final Identifier EVIL_COW_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/evil_cow/evil_cow.png");
    
    public EvilCowRender(EntityRendererProvider.Context context)
    {
        super(context, new CowModel(context.bakeLayer(ModelLayers.COW)), 0.7F);
    }

    @Override public LivingEntityRenderState createRenderState() {return new LivingEntityRenderState();}

    @Override public Identifier getTextureLocation(LivingEntityRenderState renderState) {return EVIL_COW_TEXTURE;}
}
