package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.StellarChickenModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.StellarChickenRenderState;
import fr.factionbedrock.aerialhell.Entity.Passive.StellarChickenEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class StellarChickenRender extends MobEntityRenderer<StellarChickenEntity, StellarChickenRenderState, StellarChickenModel>
{
    private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/stellar_chicken/stellar_chicken.png");

    public StellarChickenRender(EntityRendererFactory.Context context)
    {
        super(context, new StellarChickenModel(context.getPart(AerialHellModelLayers.STELLAR_CHICKEN)), 0.3F);

        // it is possible to use AgeableMobEntityRenderer and call :
        // super(context, new StellarChickenModel(context.getPart(ModelLayers.CHICKEN)), new StellarChickenModel(context.getPart(ModelLayers.CHICKEN_BABY)), 0.3F);
        // but I hardcoded baby scale in renderToBuffer, like in older versions
    }

    @Override public StellarChickenRenderState createRenderState() {return new StellarChickenRenderState();}

    @Override public Identifier getTexture(StellarChickenRenderState renderState) {return TEXTURE;}

    //copy of ChickenRenderer method of same name
    @Override public void updateRenderState(StellarChickenEntity entity, StellarChickenRenderState renderState, float flap)
    {
        super.updateRenderState(entity, renderState, flap);
        renderState.flapProgress = MathHelper.lerp(flap, entity.prevFlapProgress, entity.flapProgress);
        renderState.maxWingDeviation = MathHelper.lerp(flap, entity.prevMaxWingDeviation, entity.maxWingDeviation);
        renderState.color = entity.getColor();
    }
}