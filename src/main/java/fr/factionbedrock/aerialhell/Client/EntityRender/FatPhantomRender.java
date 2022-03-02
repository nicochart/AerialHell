package fr.factionbedrock.aerialhell.Client.EntityRender;

/*Copy of net.minecraft.client.renderer.entity.PhantomRenderer*/

import com.mojang.blaze3d.matrix.MatrixStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Passive.FatPhantomEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.PhantomModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

public class FatPhantomRender extends MobRenderer<FatPhantomEntity, PhantomModel<FatPhantomEntity>>
{
	private static String name = "fat_phantom";
	private static final ResourceLocation FAT_PHANTOM_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

	public FatPhantomRender(EntityRendererManager renderManagerIn)
	{
		super(renderManagerIn, new PhantomModel<>(), 0.75F);
    }

    public ResourceLocation getEntityTexture(FatPhantomEntity entity)
    {
    	return FAT_PHANTOM_TEXTURE;
    }

    protected void preRenderCallback(FatPhantomEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime)
    {
        int i = entitylivingbaseIn.getPhantomSize();
        float f = 1.0F + 0.15F * (float)i;
        matrixStackIn.scale(f, f, f);
        matrixStackIn.translate(0.0D, 1.3125D, 0.1875D);
    }

    protected void applyRotations(FatPhantomEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks)
    {
    	super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        matrixStackIn.rotate(Vector3f.XP.rotationDegrees(entityLiving.rotationPitch));
    }
}