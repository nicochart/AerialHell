
package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.HumanoidTwoLayerModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.HumanoidTwoLayerRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.AbstractHumanoidMonster;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.GhostSlimeNinjaPirateEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.GhostSlimePirateEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.SlimeNinjaPirateEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.SlimePirateEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HumanoidTwoLayerRender extends MobEntityRenderer<AbstractHumanoidMonster, HumanoidTwoLayerRenderState, HumanoidTwoLayerModel<HumanoidTwoLayerRenderState>>
{
    private static final Identifier SLIME_PIRATE = Identifier.of(AerialHell.MODID, "textures/entity/pirate/slime.png");
    private static final Identifier SLIME_NINJA_PIRATE = Identifier.of(AerialHell.MODID, "textures/entity/pirate/slime_ninja.png");
    private static final Identifier GHOST_PIRATE = Identifier.of(AerialHell.MODID, "textures/entity/pirate/ghost.png");
    private static final Identifier GHOST_NINJA_PIRATE = Identifier.of(AerialHell.MODID, "textures/entity/pirate/ghost_ninja.png");
    private static final Identifier MUMMY = Identifier.of(AerialHell.MODID, "textures/entity/mummy/mummy.png");

    public HumanoidTwoLayerRender(EntityRendererFactory.Context context)
    {
        super(context, new HumanoidTwoLayerModel<>(context.getPart(AerialHellModelLayers.SLIME_PIRATE)), 0.4f);
        this.addFeature(new HeldItemFeatureRenderer<>(this));
    }

    @Override public HumanoidTwoLayerRenderState createRenderState() {return new HumanoidTwoLayerRenderState();}

    @Override public void updateRenderState(AbstractHumanoidMonster entity, HumanoidTwoLayerRenderState renderState, float partialTick)
    {
        super.updateRenderState(entity, renderState, partialTick);
        renderState.texture = getTexture(entity);
        renderState.baby = entity.isBaby();
        renderState.isAggressive = entity.isAttacking();
        renderState.handSwingProgress = entity.getHandSwingProgress(partialTick);
    }

    @Nullable @Override protected RenderLayer getRenderLayer(HumanoidTwoLayerRenderState renderState, boolean isVisible, boolean renderTranslucent, boolean appearsGlowing)
    {
        return RenderLayer.getEntityTranslucent(renderState.texture);
    }

    @Override protected void scale(HumanoidTwoLayerRenderState renderState, MatrixStack matrixStack)
    {
        float scale = renderState.baby ? 0.5F : 1.0F;
        matrixStack.scale(scale, scale, scale);
    }

    @Override public Identifier getTexture(HumanoidTwoLayerRenderState renderState) {return renderState.texture;}

    @NotNull public Identifier getTexture(AbstractHumanoidMonster entity)
    {
        if (entity instanceof GhostSlimeNinjaPirateEntity) {return GHOST_NINJA_PIRATE;}
        else if (entity instanceof SlimeNinjaPirateEntity) {return SLIME_NINJA_PIRATE;}
        else if (entity instanceof GhostSlimePirateEntity) {return GHOST_PIRATE;}
        else if (entity instanceof SlimePirateEntity) {return SLIME_PIRATE;}
        else {return MUMMY;}
    }
}