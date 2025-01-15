package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.StellarChickenModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.StellarChickenRenderState;
import fr.factionbedrock.aerialhell.Entity.Passive.StellarChickenEntity;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class StellarChickenRender extends MobRenderer<StellarChickenEntity, StellarChickenRenderState, StellarChickenModel>
{
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/stellar_chicken/stellar_chicken.png");

    public StellarChickenRender(EntityRendererProvider.Context context)
    {
        super(context, new StellarChickenModel(context.bakeLayer(ModelLayers.CHICKEN)), 0.3F);

        // it is possible to use AgeableMobRenderer and call :
        // super(context, new StellarChickenModel(context.bakeLayer(ModelLayers.CHICKEN)), new StellarChickenModel(context.bakeLayer(ModelLayers.CHICKEN_BABY)), 0.3F);
        // but I hardcoded baby scale in renderToBuffer, like in older versions
    }

    @Override public StellarChickenRenderState createRenderState() {return new StellarChickenRenderState();}

    @Override public ResourceLocation getTextureLocation(StellarChickenRenderState renderState) {return TEXTURE;}

    //copy of ChickenRenderer method of same name
    @Override public void extractRenderState(StellarChickenEntity entity, StellarChickenRenderState renderState, float flap)
    {
        super.extractRenderState(entity, renderState, flap);
        renderState.flap = Mth.lerp(flap, entity.oFlap, entity.flap);
        renderState.flapSpeed = Mth.lerp(flap, entity.oFlapSpeed, entity.flapSpeed);
        renderState.color = entity.getColor();
    }
}