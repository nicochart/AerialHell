package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.VerdigrisZombieModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.VerdigrisZombieRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.VerdigrisZombieEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class VerdigrisZombieRender extends MobRenderer<VerdigrisZombieEntity, VerdigrisZombieRenderState, VerdigrisZombieModel>
{
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/zombie/verdigris.png");
    
    public VerdigrisZombieRender(EntityRendererProvider.Context context)
    {
        super(context, new VerdigrisZombieModel(context.bakeLayer(AerialHellModelLayers.VERDIGRIS_ZOMBIE)), 0.3f);
    }

    @Override public VerdigrisZombieRenderState createRenderState() {return new VerdigrisZombieRenderState();}

    @Override public void extractRenderState(VerdigrisZombieEntity entity, VerdigrisZombieRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.isAggressive = entity.isAggressive();
        renderState.attackTime = entity.swingTime; //TODO
    }

    @Override public Identifier getTextureLocation(VerdigrisZombieRenderState renderState) {return TEXTURE;}
}