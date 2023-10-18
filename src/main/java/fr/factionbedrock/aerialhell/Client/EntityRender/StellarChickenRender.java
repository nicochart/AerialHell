package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.client.renderer.entity.ChickenRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Chicken;

public class StellarChickenRender extends ChickenRenderer
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/stellar_chicken/stellar_chicken.png");

    public StellarChickenRender(EntityRendererProvider.Context context) {super(context);}

    @Override public ResourceLocation getTextureLocation(Chicken entity) {return TEXTURE;}
}
