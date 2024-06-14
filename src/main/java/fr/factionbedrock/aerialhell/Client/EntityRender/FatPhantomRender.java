package fr.factionbedrock.aerialhell.Client.EntityRender;

/*Copy of net.minecraft.client.renderer.entity.PhantomRenderer*/

import com.mojang.blaze3d.vertex.PoseStack;

import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Passive.FatPhantomEntity;
import net.minecraft.client.model.PhantomModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FatPhantomRender extends MobRenderer<FatPhantomEntity, PhantomModel<FatPhantomEntity>>
{
	private static String name = "fat_phantom";
	private static final ResourceLocation FAT_PHANTOM_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

	public FatPhantomRender(EntityRendererProvider.Context context)
	{
		super(context, new PhantomModel<>(context.bakeLayer(ModelLayers.PHANTOM)), 1.1F);
    }

    public ResourceLocation getTextureLocation(FatPhantomEntity entity)
    {
    	return FAT_PHANTOM_TEXTURE;
    }

    protected void scale(FatPhantomEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime)
    {
        int i = entitylivingbaseIn.getPhantomSize();
        float f = 1.0F + 0.15F * (float)i;
        matrixStackIn.scale(f, f, f);
        matrixStackIn.translate(0.0D, 1.3125D, 0.1875D);
    }

    protected void setupRotations(FatPhantomEntity entity, PoseStack poseStack, float p_115687_, float p_115688_, float p_115689_, float p_328426_)
    {
        super.setupRotations(entity, poseStack, p_115687_, p_115688_, p_115689_, p_328426_);
        poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot()));
    }
}