package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.StellarChickenModel;
import fr.factionbedrock.aerialhell.Entity.Passive.StellarChickenEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class StellarChickenRender extends MobEntityRenderer<StellarChickenEntity, StellarChickenModel<StellarChickenEntity>>
{
    private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/stellar_chicken/stellar_chicken.png");

    public StellarChickenRender(EntityRendererFactory.Context context)
    {
        super(context, new StellarChickenModel<>(context.getPart(EntityModelLayers.CHICKEN)), 0.3F);
    }

    @Override public Identifier getTexture(StellarChickenEntity entity) {return TEXTURE;}

    @Override protected float getAnimationProgress(StellarChickenEntity chickenEntity, float flap)
    {
        float g = MathHelper.lerp(flap, chickenEntity.prevFlapProgress, chickenEntity.flapProgress);
        float h = MathHelper.lerp(flap, chickenEntity.prevMaxWingDeviation, chickenEntity.maxWingDeviation);
        return (MathHelper.sin(g) + 1.0F) * h;
    }
}