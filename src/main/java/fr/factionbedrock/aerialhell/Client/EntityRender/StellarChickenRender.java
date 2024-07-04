package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.StellarChickenModel;
import fr.factionbedrock.aerialhell.Entity.Passive.StellarChickenEntity;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class StellarChickenRender extends MobRenderer<StellarChickenEntity, StellarChickenModel<StellarChickenEntity>>
{
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/stellar_chicken/stellar_chicken.png");

    public StellarChickenRender(EntityRendererProvider.Context context)
    {
        super(context, new StellarChickenModel<>(context.bakeLayer(ModelLayers.CHICKEN)), 0.3F);
    }

    @Override public ResourceLocation getTextureLocation(StellarChickenEntity entity) {return TEXTURE;}

    @Override protected float getBob(StellarChickenEntity entity, float flap)
    {
        float f = Mth.lerp(flap, entity.oFlap, entity.flap);
        float f1 = Mth.lerp(flap, entity.oFlapSpeed, entity.flapSpeed);
        return (Mth.sin(f) + 1.0F) * f1;
    }
}