package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AutomatonModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.ShadowAutomatonShadowLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.StellarStoneAutomatonEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShadowAutomatonRender extends MobRenderer<StellarStoneAutomatonEntity, AutomatonModel<StellarStoneAutomatonEntity>>
{
	private static final ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/entity/automaton/shadow_automaton.png");

	public ShadowAutomatonRender(EntityRendererManager rendererManager)
	{
		super(rendererManager, new AutomatonModel(true), 0.3F);
		this.addLayer(new ShadowAutomatonShadowLayer(this));
	}
	
	@Override
	public ResourceLocation getEntityTexture(StellarStoneAutomatonEntity entity) {return TEXTURE;}
}