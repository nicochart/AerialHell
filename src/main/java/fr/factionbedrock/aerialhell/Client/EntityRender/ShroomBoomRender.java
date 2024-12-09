package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.ShroomBoomModel;
import fr.factionbedrock.aerialhell.Entity.Monster.ShroomBoomEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

//see net.minecraft.client.render.entity.CreeperEntityRenderer
public class ShroomBoomRender extends MobEntityRenderer<ShroomBoomEntity, ShroomBoomModel>
{
	private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/shroomboom/shroomboom.png");
	
	public ShroomBoomRender(EntityRendererFactory.Context context) {super(context, new ShroomBoomModel(context.getPart(AerialHellModelLayers.SHROOMBOOM)), 0.5F);}

	protected void scale(ShroomBoomEntity shroomBoomEntity, MatrixStack matrixStack, float f)
	{
		float g = shroomBoomEntity.getClientFuseTime(f);
		float h = 1.0F + MathHelper.sin(g * 100.0F) * g * 0.01F;
		g = MathHelper.clamp(g, 0.0F, 1.0F);
		g *= g;
		g *= g;
		float i = (1.0F + g * 0.4F) * h;
		float j = (1.0F + g * 0.1F) / h;
		matrixStack.scale(i, j, i);
	}

	@Override protected float getAnimationCounter(ShroomBoomEntity livingEntityIn, float partialTicks)
	{
		float f = livingEntityIn.getClientFuseTime(partialTicks);
	    return (int)(f * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(f, 0.5F, 1.0F);
	}
	
	@Override public Identifier getTexture(ShroomBoomEntity entity) {return TEXTURE;}
}