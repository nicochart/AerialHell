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
import net.minecraft.client.renderer.entity.state.PhantomRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Phantom;

public class FatPhantomRender extends MobRenderer<FatPhantomEntity, PhantomRenderState, PhantomModel>
{
	private static String name = "fat_phantom";
	private static final ResourceLocation FAT_PHANTOM_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png");

	public FatPhantomRender(EntityRendererProvider.Context context)
	{
		super(context, new PhantomModel(context.bakeLayer(ModelLayers.PHANTOM)), 1.1F);
    }

    @Override public PhantomRenderState createRenderState() {return new PhantomRenderState();}

    @Override public void extractRenderState(FatPhantomEntity entity, PhantomRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.flapTime = (float)entity.getUniqueFlapTickOffset() + renderState.ageInTicks;
        renderState.size = entity.getPhantomSize();
    }

    @Override protected void scale(PhantomRenderState renderState, PoseStack posestack)
    {
        float f = 1.0F + 0.15F * (float) renderState.size;
        posestack.scale(f, f, f);
        posestack.translate(0.0D, 1.3125D, 0.1875D);
    }

    @Override protected void setupRotations(PhantomRenderState renderState, PoseStack poseStack, float partialTick, float scale)
    {
        super.setupRotations(renderState, poseStack, partialTick, scale);
        poseStack.mulPose(Axis.XP.rotationDegrees(renderState.xRot));
    }

    @Override public ResourceLocation getTextureLocation(PhantomRenderState renderState) {return FAT_PHANTOM_TEXTURE;}
}