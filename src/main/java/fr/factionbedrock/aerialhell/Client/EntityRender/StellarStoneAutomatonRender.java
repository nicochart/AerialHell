package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.AutomatonModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.StellarStoneAutomatonEyesLayer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.AutomatonRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.AutomatonEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class StellarStoneAutomatonRender<T extends AutomatonEntity> extends MobEntityRenderer<T, AutomatonRenderState, AutomatonModel<AutomatonRenderState>>
{
	private static String name = "stellar_stone_automaton";
    private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/automaton/" + name + ".png");

    public StellarStoneAutomatonRender(EntityRendererFactory.Context context)
    {
        super(context, new AutomatonModel<AutomatonRenderState>(context.getPart(AerialHellModelLayers.AUTOMATON)), 0.3f);
        this.addFeature(new StellarStoneAutomatonEyesLayer(this));
    }

    @Override public AutomatonRenderState createRenderState() {return new AutomatonRenderState();}

    @Override public void updateRenderState(T entity, AutomatonRenderState renderState, float partialTick)
    {
        super.updateRenderState(entity, renderState, partialTick);
        renderState.texture = TEXTURE;
        renderState.scale = 1.0F;
        renderState.attackTimer = entity.attackTimer;
    }

    @Override public Identifier getTexture(AutomatonRenderState renderState) {return renderState.texture;}
}