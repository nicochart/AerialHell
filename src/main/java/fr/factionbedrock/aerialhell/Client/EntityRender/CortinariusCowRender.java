package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.CortinariusCowShroomModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.CortinariusCowShroomLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.EvilCowEntity;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class CortinariusCowRender<T extends EvilCowEntity> extends MobRenderer<T, LivingEntityRenderState, CowModel>
{
    private static final ResourceLocation CORTINARIUS_COW_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/cortinarius_cow/cortinarius_cow.png");
    
    public CortinariusCowRender(EntityRendererProvider.Context context)
    {
        super(context, new CowModel(context.bakeLayer(ModelLayers.COW)), 0.7F);
        this.addLayer(new CortinariusCowShroomLayer<LivingEntityRenderState, CowModel>(this, new CortinariusCowShroomModel<>(context.bakeLayer(AerialHellModelLayers.CORTINARIUS_COW_SHROOM))));
    }

    @Override public LivingEntityRenderState createRenderState() {return new LivingEntityRenderState();}

    @Override public ResourceLocation getTextureLocation(LivingEntityRenderState renderState) {return CORTINARIUS_COW_TEXTURE;}
}
