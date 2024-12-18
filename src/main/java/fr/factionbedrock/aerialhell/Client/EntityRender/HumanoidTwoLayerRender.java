
package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.HumanoidTwoLayerModel;
import fr.factionbedrock.aerialhell.Entity.Monster.AbstractHumanoidMonster;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.GhostSlimeNinjaPirateEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.GhostSlimePirateEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.SlimeNinjaPirateEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.Pirate.SlimePirateEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HumanoidTwoLayerRender extends MobEntityRenderer<AbstractHumanoidMonster, HumanoidTwoLayerModel<AbstractHumanoidMonster>>
{
    private static final Identifier SLIME_PIRATE = Identifier.of(AerialHell.MODID, "textures/entity/pirate/slime.png");
    private static final Identifier SLIME_NINJA_PIRATE = Identifier.of(AerialHell.MODID, "textures/entity/pirate/slime_ninja.png");
    private static final Identifier GHOST_PIRATE = Identifier.of(AerialHell.MODID, "textures/entity/pirate/ghost.png");
    private static final Identifier GHOST_NINJA_PIRATE = Identifier.of(AerialHell.MODID, "textures/entity/pirate/ghost_ninja.png");
    private static final Identifier MUMMY = Identifier.of(AerialHell.MODID, "textures/entity/mummy/mummy.png");

    public HumanoidTwoLayerRender(EntityRendererFactory.Context context)
    {
        super(context, new HumanoidTwoLayerModel<>(context.getPart(AerialHellModelLayers.SLIME_PIRATE)), 0.4f);
        this.addFeature(new HeldItemFeatureRenderer<>(this, context.getHeldItemRenderer()));
    }

    @Nullable @Override protected RenderLayer getRenderLayer(AbstractHumanoidMonster entity, boolean b1, boolean b2, boolean b3)
    {
        return RenderLayer.getEntityTranslucent(this.getTexture(entity));
    }

    @Override protected void scale(AbstractHumanoidMonster entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime)
    {
        float scale = entitylivingbaseIn.isBaby() ? 0.5F : 1.0F;
        matrixStackIn.scale(scale, scale, scale);
    }

    @Override @NotNull public Identifier getTexture(AbstractHumanoidMonster entity)
    {
        if (entity instanceof GhostSlimeNinjaPirateEntity) {return GHOST_NINJA_PIRATE;}
        else if (entity instanceof SlimeNinjaPirateEntity) {return SLIME_NINJA_PIRATE;}
        else if (entity instanceof GhostSlimePirateEntity) {return GHOST_PIRATE;}
        else if (entity instanceof SlimePirateEntity) {return SLIME_PIRATE;}
        else {return MUMMY;}
    }
}