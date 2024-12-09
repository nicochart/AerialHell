package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.AutomatonModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.StellarStoneAutomatonEyesLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.AutomatonEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class StellarStoneAutomatonRender<T extends AutomatonEntity> extends MobEntityRenderer<T, AutomatonModel<T>>
{
	private static String name = "stellar_stone_automaton";
    private static final Identifier texture = Identifier.of(AerialHell.MODID, "textures/entity/automaton/" + name + ".png");

    public StellarStoneAutomatonRender(EntityRendererFactory.Context context)
    {
        super(context, new AutomatonModel<T>(context.getPart(AerialHellModelLayers.AUTOMATON)), 0.3f);
        this.addFeature(new StellarStoneAutomatonEyesLayer<>(this));
    }

    @Override public Identifier getTexture(T entity) {return texture;}
}