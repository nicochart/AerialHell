package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.TornSpiritModel;
import fr.factionbedrock.aerialhell.Entity.Monster.TornSpiritEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

public class TornSpiritRender extends MobEntityRenderer<TornSpiritEntity, LivingEntityRenderState, TornSpiritModel>
{
	private static String name = "torn_spirit";
    private static final Identifier TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/" + name +"/" + name + ".png");
	
    public TornSpiritRender(EntityRendererFactory.Context context)
	{
		super(context, new TornSpiritModel(context.getPart(AerialHellModelLayers.TORN_SPIRIT)), 0.3F);
	}

	@Override public LivingEntityRenderState createRenderState() {return new LivingEntityRenderState();}

	@Override public Identifier getTexture(LivingEntityRenderState renderState) {return TEXTURE;}
}