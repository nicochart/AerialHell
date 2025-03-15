package fr.factionbedrock.aerialhell.Client.EntityRender;

/*Copy of net.minecraft.client.renderer.entity.PhantomRenderer*/

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Passive.FatPhantomEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PhantomEntityModel;
import net.minecraft.client.render.entity.state.PhantomEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

public class FatPhantomRender extends MobEntityRenderer<FatPhantomEntity, PhantomEntityRenderState, PhantomEntityModel>
{
	private static String name = "fat_phantom";
	private static final Identifier FAT_PHANTOM_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

	public FatPhantomRender(EntityRendererFactory.Context context)
	{
		super(context, new PhantomEntityModel(context.getPart(EntityModelLayers.PHANTOM)), 1.1F);
    }

    @Override public PhantomEntityRenderState createRenderState() {return new PhantomEntityRenderState();}

    @Override public void updateRenderState(FatPhantomEntity entity, PhantomEntityRenderState renderState, float partialTick)
    {
        super.updateRenderState(entity, renderState, partialTick);
        renderState.wingFlapProgress = (float)entity.getWingFlapTickOffset() + renderState.age;
        renderState.size = entity.getPhantomSize();
    }

    @Override protected void scale(PhantomEntityRenderState renderState, MatrixStack posestack)
    {
        float f = 1.0F + 0.15F * (float) renderState.size;
        posestack.scale(f, f, f);
        posestack.translate(0.0D, 1.3125D, 0.1875D);
    }

    @Override protected void setupTransforms(PhantomEntityRenderState phantomEntityRenderState, MatrixStack matrixStack, float f, float g)
    {
        super.setupTransforms(phantomEntityRenderState, matrixStack, f, g);
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(phantomEntityRenderState.pitch));
    }

    @Override public Identifier getTexture(PhantomEntityRenderState renderState) {return FAT_PHANTOM_TEXTURE;}
}