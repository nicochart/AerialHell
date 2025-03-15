package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.SpiderBarrelMimicModel;
import fr.factionbedrock.aerialhell.Entity.Monster.BarrelMimic.AbstractBarrelMimicEntity;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

public class SpiderBarrelMimicRender<T extends AbstractBarrelMimicEntity> extends MobEntityRenderer<T, LivingEntityRenderState, SpiderBarrelMimicModel>
{
	private static final Identifier SHADOW_PINE_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/barrel_mimic/shadow_pine.png");

	public SpiderBarrelMimicRender(EntityRendererFactory.Context context)
	{
		super(context, new SpiderBarrelMimicModel(context.getPart(AerialHellModelLayers.SPIDER_BARREL_MIMIC)), 1.0F);
	}

	@Override public LivingEntityRenderState createRenderState() {return new LivingEntityRenderState();}

	@Override public Identifier getTexture(LivingEntityRenderState renderState) {return SHADOW_PINE_TEXTURE;}
}