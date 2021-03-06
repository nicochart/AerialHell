package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.matrix.MatrixStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.GoldenWalkerModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.GoldenWalkerEyesLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.GoldenWalkerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GoldenWalkerRender extends MobRenderer<GoldenWalkerEntity, GoldenWalkerModel<GoldenWalkerEntity>>
{
	private static String name = "golden_walker";
    private static final ResourceLocation texture = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public GoldenWalkerRender(EntityRendererManager manager)
    {
        super(manager, new GoldenWalkerModel(), 0.3f);
        this.addLayer(new GoldenWalkerEyesLayer<>(this));
    }

    @Override
    public ResourceLocation getEntityTexture(GoldenWalkerEntity entity)
    {return texture;}

    @Override
    public void applyRotations(GoldenWalkerEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks)
    {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!((double)entityLiving.limbSwingAmount < 0.01D))
        {
            float f1 = entityLiving.limbSwing - entityLiving.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(6.5F * f2));
        }
    }
}