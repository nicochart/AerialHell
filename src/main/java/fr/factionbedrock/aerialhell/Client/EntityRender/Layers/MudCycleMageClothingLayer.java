package fr.factionbedrock.aerialhell.Client.EntityRender.Layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.MudCycleMageClothesModel;
import fr.factionbedrock.aerialhell.Client.EntityModels.MudCycleMageModel;
import fr.factionbedrock.aerialhell.Entity.Bosses.MudCycleMageEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MudCycleMageClothingLayer extends LayerRenderer<MudCycleMageEntity, MudCycleMageModel>
{
	private static final ResourceLocation MUD_CYCLE_MAGE_CLOTHES_TEXTURES = new ResourceLocation(AerialHell.MODID, "textures/entity/mud_cycle_mage/mud_cycle_mage_clothes.png");
	private final MudCycleMageClothesModel clothes = new MudCycleMageClothesModel();

	public MudCycleMageClothingLayer(IEntityRenderer<MudCycleMageEntity, MudCycleMageModel> entityRenderer)
	{
		super(entityRenderer);
	}

	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, MudCycleMageEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if (!entitylivingbaseIn.isInvisible())
	    {
			this.getEntityModel().copyModelAttributesTo(this.clothes);
	        this.clothes.setLivingAnimations(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks);
	        this.clothes.setRotationAngles(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityTranslucent(MUD_CYCLE_MAGE_CLOTHES_TEXTURES));
	        this.clothes.render(matrixStackIn, ivertexbuilder, packedLightIn, LivingRenderer.getPackedOverlay(entitylivingbaseIn, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
	    }
	}
}
