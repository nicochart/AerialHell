package fr.factionbedrock.aerialhell.Client.EntityRender;

/*Copy of net.minecraft.client.renderer.entity.PhantomRenderer*/

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Passive.FatPhantomEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PhantomEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class FatPhantomRender extends MobEntityRenderer<FatPhantomEntity, PhantomEntityModel<FatPhantomEntity>>
{
	private static String name = "fat_phantom";
	private static final Identifier FAT_PHANTOM_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

	public FatPhantomRender(EntityRendererFactory.Context context)
	{
		super(context, new PhantomEntityModel<>(context.getPart(EntityModelLayers.PHANTOM)), 1.1F);
    }

    public Identifier getTexture(FatPhantomEntity entity)
    {
    	return FAT_PHANTOM_TEXTURE;
    }

    @Override protected void scale(FatPhantomEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime)
    {
        int i = entitylivingbaseIn.getPhantomSize();
        float f = 1.0F + 0.15F * (float)i;
        matrixStackIn.scale(f, f, f);
        matrixStackIn.translate(0.0D, 1.3125D, 0.1875D);
    }

    @Override protected void setupTransforms(FatPhantomEntity entity, MatrixStack poseStack, float animationProgress, float bodyYaw, float tickDelta, float scale)
    {
        super.setupTransforms(entity, poseStack, animationProgress, bodyYaw, tickDelta, scale);
        poseStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(entity.getPitch()));
    }
}