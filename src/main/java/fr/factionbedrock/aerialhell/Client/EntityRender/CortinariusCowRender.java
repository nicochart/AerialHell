package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.CortinariusCowShroomModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.CortinariusCowShroomLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.EvilCowEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

public class CortinariusCowRender<T extends EvilCowEntity> extends MobEntityRenderer<T, LivingEntityRenderState, CowEntityModel>
{
    private static final Identifier CORTINARIUS_COW_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/cortinarius_cow/cortinarius_cow.png");
    
    public CortinariusCowRender(EntityRendererFactory.Context context)
    {
        super(context, new CowEntityModel(context.getPart(EntityModelLayers.COW)), 0.7F);
        this.addFeature(new CortinariusCowShroomLayer<LivingEntityRenderState, CowEntityModel>(this, new CortinariusCowShroomModel<>(context.getPart(AerialHellModelLayers.CORTINARIUS_COW_SHROOM))));
    }

    @Override public LivingEntityRenderState createRenderState() {return new LivingEntityRenderState();}

    @Override public Identifier getTexture(LivingEntityRenderState renderState) {return CORTINARIUS_COW_TEXTURE;}
}
