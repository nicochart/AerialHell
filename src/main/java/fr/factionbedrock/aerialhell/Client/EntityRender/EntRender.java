package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.EntModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.EntRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.EntEntity;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

import java.awt.*;

public class EntRender<E extends EntEntity> extends MobRenderer<E, EntRenderState, EntModel<EntRenderState>>
{
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/ent/stellar_ent.png");

    public EntRender(EntityRendererProvider.Context context)
    {
        super(context, new EntModel(context.bakeLayer(AerialHellModelLayers.ENT)), 0.3f);
    }

    @Override public void extractRenderState(E entity, EntRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.isAggressive = entity.isAggressive();
        renderState.color = new Color(BiomeColors.getAverageFoliageColor(entity.level(), entity.getOnPos())).getRGB();
        renderState.attackTime = entity.getAttackAnim(partialTick);
    }

    @Override public EntRenderState createRenderState() {return new EntRenderState();}

    @Override public Identifier getTextureLocation(EntRenderState renderState) {return TEXTURE;}
}