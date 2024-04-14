
package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.HumanoidTwoLayerModel;
import fr.factionbedrock.aerialhell.Entity.Monster.AbstractHumanoidMonster;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.GhostSlimeNinjaPirateEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.GhostSlimePirateEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.SlimeNinjaPirateEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.SlimePirateEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class HumanoidTwoLayerRender extends MobRenderer<AbstractHumanoidMonster, HumanoidTwoLayerModel<AbstractHumanoidMonster>>
{
    private static final ResourceLocation SLIME_PIRATE = new ResourceLocation(AerialHell.MODID, "textures/entity/pirate/slime.png");
    private static final ResourceLocation SLIME_NINJA_PIRATE = new ResourceLocation(AerialHell.MODID, "textures/entity/pirate/slime_ninja.png");
    private static final ResourceLocation GHOST_PIRATE = new ResourceLocation(AerialHell.MODID, "textures/entity/pirate/ghost.png");
    private static final ResourceLocation GHOST_NINJA_PIRATE = new ResourceLocation(AerialHell.MODID, "textures/entity/pirate/ghost_ninja.png");
    private static final ResourceLocation MUMMY = new ResourceLocation(AerialHell.MODID, "textures/entity/mummy/mummy.png");

    public HumanoidTwoLayerRender(EntityRendererProvider.Context context)
    {
        super(context, new HumanoidTwoLayerModel<>(context.bakeLayer(AerialHellModelLayers.SLIME_PIRATE)), 0.4f);
        this.addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
    }

    @Nullable @Override protected RenderType getRenderType(AbstractHumanoidMonster entity, boolean b1, boolean b2, boolean b3)
    {
        return RenderType.entityTranslucent(this.getTextureLocation(entity));
    }

    @Override protected void scale(AbstractHumanoidMonster entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime)
    {
        float scale = entitylivingbaseIn.isBaby() ? 0.5F : 1.0F;
        matrixStackIn.scale(scale, scale, scale);
    }

    @Override @NotNull public ResourceLocation getTextureLocation(AbstractHumanoidMonster entity)
    {
        if (entity instanceof GhostSlimeNinjaPirateEntity) {return GHOST_NINJA_PIRATE;}
        else if (entity instanceof SlimeNinjaPirateEntity) {return SLIME_NINJA_PIRATE;}
        else if (entity instanceof GhostSlimePirateEntity) {return GHOST_PIRATE;}
        else if (entity instanceof SlimePirateEntity) {return SLIME_PIRATE;}
        else {return MUMMY;}
    }
}