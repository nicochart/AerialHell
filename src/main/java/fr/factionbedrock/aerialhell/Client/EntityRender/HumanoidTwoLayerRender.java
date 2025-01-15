
package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.HumanoidTwoLayerModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.HumanoidTwoLayerRenderState;
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
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class HumanoidTwoLayerRender extends MobRenderer<AbstractHumanoidMonster, HumanoidTwoLayerRenderState, HumanoidTwoLayerModel<HumanoidTwoLayerRenderState>>
{
    private static final ResourceLocation SLIME_PIRATE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/pirate/slime.png");
    private static final ResourceLocation SLIME_NINJA_PIRATE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/pirate/slime_ninja.png");
    private static final ResourceLocation GHOST_PIRATE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/pirate/ghost.png");
    private static final ResourceLocation GHOST_NINJA_PIRATE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/pirate/ghost_ninja.png");
    private static final ResourceLocation MUMMY = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/mummy/mummy.png");

    public HumanoidTwoLayerRender(EntityRendererProvider.Context context)
    {
        super(context, new HumanoidTwoLayerModel<>(context.bakeLayer(AerialHellModelLayers.SLIME_PIRATE)), 0.4f);
        this.addLayer(new ItemInHandLayer<>(this));
    }

    @Override public HumanoidTwoLayerRenderState createRenderState() {return new HumanoidTwoLayerRenderState();}

    @Override public void extractRenderState(AbstractHumanoidMonster entity, HumanoidTwoLayerRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.texture = getTextureLocation(entity);
        renderState.isBaby = entity.isBaby();
        renderState.isAggressive = entity.isAggressive();
        renderState.attackTime = entity.getAttackAnim(partialTick);
    }

    @Nullable @Override protected RenderType getRenderType(HumanoidTwoLayerRenderState renderState, boolean isVisible, boolean renderTranslucent, boolean appearsGlowing)
    {
        return RenderType.entityTranslucent(renderState.texture);
    }

    @Override protected void scale(HumanoidTwoLayerRenderState renderState, PoseStack poseStack)
    {
        float scale = renderState.isBaby ? 0.5F : 1.0F;
        poseStack.scale(scale, scale, scale);
    }

    @Override public ResourceLocation getTextureLocation(HumanoidTwoLayerRenderState renderState) {return renderState.texture;}

    @NotNull public ResourceLocation getTextureLocation(AbstractHumanoidMonster entity)
    {
        if (entity instanceof GhostSlimeNinjaPirateEntity) {return GHOST_NINJA_PIRATE;}
        else if (entity instanceof SlimeNinjaPirateEntity) {return SLIME_NINJA_PIRATE;}
        else if (entity instanceof GhostSlimePirateEntity) {return GHOST_PIRATE;}
        else if (entity instanceof SlimePirateEntity) {return SLIME_PIRATE;}
        else {return MUMMY;}
    }
}