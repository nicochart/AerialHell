package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.ShadowTrollModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.Layers.ShadowTrollShadowLayer;
import fr.factionbedrock.aerialhell.Entity.Monster.ShadowTrollEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShadowTrollRender extends MobRenderer<ShadowTrollEntity, ShadowTrollModel>
{	
	private static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation(AerialHell.MODID, "textures/entity/shadow_troll/shadow_troll.png");
	private static final ResourceLocation TEXTURE_INVERT = new ResourceLocation(AerialHell.MODID, "textures/entity/shadow_troll/shadow_troll_invert.png");

	public ShadowTrollRender(EntityRendererManager rendererManager)
	{
		super(rendererManager, new ShadowTrollModel(true), 0.3F);
		this.addLayer(new ShadowTrollShadowLayer(this));
	}
	
	@Override public ResourceLocation getEntityTexture(ShadowTrollEntity entity)
	{
		if (Minecraft.getInstance().player.isPotionActive(Effects.NIGHT_VISION)) {return TEXTURE_INVERT;}
		else {return TEXTURE_NORMAL;}
	}
}