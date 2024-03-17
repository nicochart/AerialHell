
package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.SlimePirateModel;
import fr.factionbedrock.aerialhell.Entity.Monster.GhostSlimePirateEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.SlimePirateEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class SlimePirateRender extends MobRenderer<SlimePirateEntity, SlimePirateModel<SlimePirateEntity>>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/pirate/slime.png");
    private static final ResourceLocation TEXTURE_GHOST = new ResourceLocation(AerialHell.MODID, "textures/entity/pirate/ghost.png");

    public SlimePirateRender(EntityRendererProvider.Context context)
    {
        super(context, new SlimePirateModel<>(context.bakeLayer(AerialHellModelLayers.SLIME_PIRATE)), 0.4f);
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
    }

    @Nullable @Override protected RenderType getRenderType(SlimePirateEntity entity, boolean b1, boolean b2, boolean b3)
    {
        return RenderType.entityTranslucent(this.getTextureLocation(entity));
    }

    @Override protected void scale(SlimePirateEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime)
    {
        float scale = entitylivingbaseIn.isBaby() ? 0.5F : 1.0F;
        matrixStackIn.scale(scale, scale, scale);
    }

    @Override public ResourceLocation getTextureLocation(SlimePirateEntity entity) {return entity instanceof GhostSlimePirateEntity ? TEXTURE_GHOST : TEXTURE;}
}