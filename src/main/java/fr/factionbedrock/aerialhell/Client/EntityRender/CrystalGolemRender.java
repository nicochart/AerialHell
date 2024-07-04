package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalGolemCrystalModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.CrystalGolemModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.CrystalGolemCrystalLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.CrystalGolemEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CrystalGolemRender extends MobRenderer<CrystalGolemEntity, CrystalGolemModel<CrystalGolemEntity>>
{
	private static String name = "crystal_golem";
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

    public CrystalGolemRender(EntityRendererProvider.Context context)
    {
        super(context, new CrystalGolemModel<CrystalGolemEntity>(context.bakeLayer(AerialHellModelLayers.CRYSTAL_GOLEM)), 0.6f);
        this.addLayer(new CrystalGolemCrystalLayer<CrystalGolemEntity,CrystalGolemModel<CrystalGolemEntity>>(this, new CrystalGolemCrystalModel<>(context.bakeLayer(AerialHellModelLayers.CRYSTAL_GOLEM_CRYSTAL))));
    }
    
    @Override
	protected void scale(CrystalGolemEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime)
	{
		float f = 0.65F;
		matrixStackIn.scale(f, f, f);
	}
    
    @Override public ResourceLocation getTextureLocation(CrystalGolemEntity entity) {return TEXTURE;}
}