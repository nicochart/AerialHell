package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.VerdigrisZombieModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VerdigrisZombieRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.VerdigrisZombieEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class VerdigrisZombieRender extends MobEntityRenderer<VerdigrisZombieEntity, VerdigrisZombieRenderState, VerdigrisZombieModel>
{
    private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/zombie/verdigris.png");
    
    public VerdigrisZombieRender(EntityRendererFactory.Context context)
    {
        super(context, new VerdigrisZombieModel(context.getPart(AerialHellModelLayers.VERDIGRIS_ZOMBIE)), 0.3f);
    }

    @Override public VerdigrisZombieRenderState createRenderState() {return new VerdigrisZombieRenderState();}

    @Override public void updateRenderState(VerdigrisZombieEntity entity, VerdigrisZombieRenderState renderState, float partialTick)
    {
        super.updateRenderState(entity, renderState, partialTick);
        renderState.isAggressive = entity.isAttacking();
        renderState.attackTime = entity.handSwingTicks; //TODO
    }

    @Override public Identifier getTexture(VerdigrisZombieRenderState renderState) {return TEXTURE;}
}