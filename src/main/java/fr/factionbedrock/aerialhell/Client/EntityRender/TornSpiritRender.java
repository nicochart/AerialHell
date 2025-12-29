package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.TornSpiritModel;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class TornSpiritRender extends MobRenderer<TornSpiritEntity, LivingEntityRenderState, TornSpiritModel>
{
	private static String name = "torn_spirit";
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
	
    public TornSpiritRender(EntityRendererProvider.Context context)
	{
		super(context, new TornSpiritModel(context.bakeLayer(AerialHellModelLayers.TORN_SPIRIT)), 0.3F);
	}

	@Override public LivingEntityRenderState createRenderState() {return new LivingEntityRenderState();}

	@Override public Identifier getTextureLocation(LivingEntityRenderState renderState) {return TEXTURE;}
}