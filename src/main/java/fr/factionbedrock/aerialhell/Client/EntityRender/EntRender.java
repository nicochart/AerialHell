package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.EntModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.EntRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.EntEntity;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

import java.awt.*;

public class EntRender<E extends EntEntity> extends MobEntityRenderer<E, EntRenderState, EntModel<EntRenderState>>
{
    private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/ent/stellar_ent.png");

    public EntRender(EntityRendererFactory.Context context)
    {
        super(context, new EntModel(context.getPart(AerialHellModelLayers.ENT)), 0.3f);
    }

    @Override public void updateRenderState(E entity, EntRenderState renderState, float partialTick)
    {
        super.updateRenderState(entity, renderState, partialTick);
        renderState.isAggressive = entity.isAttacking();
        renderState.color = new Color(BiomeColors.getFoliageColor(entity.getEntityWorld(), entity.getSteppingPos())).getRGB();
        renderState.handSwingProgress = entity.getHandSwingProgress(partialTick);
    }

    @Override public EntRenderState createRenderState() {return new EntRenderState();}

    @Override public Identifier getTexture(EntRenderState renderState) {return TEXTURE;}
}