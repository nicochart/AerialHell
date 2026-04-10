package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.AutomatonModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.StellarStoneAutomatonEyesLayer;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.AutomatonRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.AutomatonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class StellarStoneAutomatonRender<T extends AutomatonEntity> extends MobRenderer<T, AutomatonRenderState, AutomatonModel<AutomatonRenderState>>
{
	private static String name = "stellar_stone_automaton";
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/automaton/" + name + ".png");

    public StellarStoneAutomatonRender(EntityRendererProvider.Context context)
    {
        super(context, new AutomatonModel<AutomatonRenderState>(context.bakeLayer(AerialHellModelLayers.AUTOMATON)), 0.3f);
        this.addLayer(new StellarStoneAutomatonEyesLayer(this));
    }

    @Override public AutomatonRenderState createRenderState() {return new AutomatonRenderState();}

    @Override public void extractRenderState(T entity, AutomatonRenderState renderState, float partialTick)
    {
        super.extractRenderState(entity, renderState, partialTick);
        renderState.texture = TEXTURE;
        renderState.scale = 1.0F;
        renderState.attackTimer = entity.attackTimer;
    }

    @Override public Identifier getTextureLocation(AutomatonRenderState renderState) {return renderState.texture;}
}