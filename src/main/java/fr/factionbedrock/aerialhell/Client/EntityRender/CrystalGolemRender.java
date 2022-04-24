package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.matrix.MatrixStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellGolemModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.CrystalGolemCrystalLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.CrystalGolemEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrystalGolemRender extends MobRenderer<CrystalGolemEntity, AerialHellGolemModel<CrystalGolemEntity>>
{
	private static String name = "crystal_golem";
    private static final ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public CrystalGolemRender(EntityRendererManager manager)
    {
        super(manager, new AerialHellGolemModel<CrystalGolemEntity>(), 0.6f);
        this.addLayer(new CrystalGolemCrystalLayer<CrystalGolemEntity,AerialHellGolemModel<CrystalGolemEntity>>(this));
    }
    
    @Override
	protected void preRenderCallback(CrystalGolemEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime)
	{
		float f = 0.65F;
		matrixStackIn.scale(f, f, f);
	}
    
    @Override
    public ResourceLocation getEntityTexture(CrystalGolemEntity entity) {return TEXTURE;}
}