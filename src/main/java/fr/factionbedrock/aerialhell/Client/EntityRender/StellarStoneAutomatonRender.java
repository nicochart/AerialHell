package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.AutomatonModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.StellarStoneAutomatonEyesLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.AutomatonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class StellarStoneAutomatonRender<T extends AutomatonEntity> extends MobRenderer<T, AutomatonModel<T>>
{
	private static String name = "stellar_stone_automaton";
    private static final ResourceLocation texture = new ResourceLocation(AerialHell.MODID, "textures/entity/automaton/" + name + ".png");

    public StellarStoneAutomatonRender(EntityRendererProvider.Context context)
    {
        super(context, new AutomatonModel<T>(context.bakeLayer(AerialHellModelLayers.AUTOMATON)), 0.3f);
        this.addLayer(new StellarStoneAutomatonEyesLayer<>(this));
    }

    @Override public ResourceLocation getTextureLocation(T entity) {return texture;}
}