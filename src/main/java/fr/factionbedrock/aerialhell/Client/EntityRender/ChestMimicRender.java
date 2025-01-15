package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.ChestMimicModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ChestMimicRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.AbstractChestMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.*;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class ChestMimicRender<T extends AbstractChestMimicEntity> extends MobRenderer<T, ChestMimicRenderState, ChestMimicModel>
{	
	private static final ResourceLocation OVERWORLD_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/chest_mimic/overworld.png");
	private static final ResourceLocation AERIAL_TREE_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/chest_mimic/aerial_tree.png");
	private static final ResourceLocation SKY_CACTUS_FIBER_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/chest_mimic/sky_cactus_fiber.png");
	private static final ResourceLocation GOLDEN_BEECH_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/chest_mimic/golden_beech.png");
	private static final ResourceLocation COPPER_PINE_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/entity/chest_mimic/copper_pine.png");
	
	public ChestMimicRender(EntityRendererProvider.Context context)
	{
		super(context, new ChestMimicModel(context.bakeLayer(AerialHellModelLayers.CHEST_MIMIC)), 1.0F);
	}

	@Override public ChestMimicRenderState createRenderState() {return new ChestMimicRenderState();}

	@Override public void extractRenderState(T entity, ChestMimicRenderState renderState, float f)
	{
		super.extractRenderState(entity, renderState, f);
		renderState.texture = getTextureLocation(entity);
		renderState.mouthOpeningAmplitude = entity.mouthOpeningAmplitude;
		renderState.mouthOpeningFrequencyMalus = entity.mouthOpeningFrequencyMalus;
	}

	public ResourceLocation getTextureLocation(T mimic)
	{
		if (mimic instanceof AerialTreeChestMimicEntity)
		{
			return AERIAL_TREE_TEXTURE;
		}
		else if (mimic instanceof GoldenBeechChestMimicEntity)
		{
			return GOLDEN_BEECH_TEXTURE;
		}
		else if (mimic instanceof SkyCactusFiberChestMimicEntity)
		{
			return SKY_CACTUS_FIBER_TEXTURE;
		}
		else if (mimic instanceof CopperPineChestMimicEntity)
		{
			return COPPER_PINE_TEXTURE;
		}
		else
		{
			return OVERWORLD_TEXTURE;
		}
	}

	@Override public ResourceLocation getTextureLocation(ChestMimicRenderState renderState) {return renderState.texture;}
}