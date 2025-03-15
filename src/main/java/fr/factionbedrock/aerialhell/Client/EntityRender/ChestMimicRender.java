package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityModels.AerialHellModelLayers;
import fr.factionbedrock.aerialhell.Client.EntityModels.ChestMimicModel;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ChestMimicRenderState;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.AbstractChestMimicEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.ChestMimic.*;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class ChestMimicRender<T extends AbstractChestMimicEntity> extends MobEntityRenderer<T, ChestMimicRenderState, ChestMimicModel>
{	
	private static final Identifier OVERWORLD_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/chest_mimic/overworld.png");
	private static final Identifier AERIAL_TREE_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/chest_mimic/aerial_tree.png");
	private static final Identifier SKY_CACTUS_FIBER_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/chest_mimic/sky_cactus_fiber.png");
	private static final Identifier GOLDEN_BEECH_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/chest_mimic/golden_beech.png");
	private static final Identifier COPPER_PINE_TEXTURE = Identifier.of(AerialHell.MODID, "textures/entity/chest_mimic/copper_pine.png");
	
	public ChestMimicRender(EntityRendererFactory.Context context)
	{
		super(context, new ChestMimicModel(context.getPart(AerialHellModelLayers.CHEST_MIMIC)), 1.0F);
	}

	@Override public ChestMimicRenderState createRenderState() {return new ChestMimicRenderState();}

	@Override public void updateRenderState(T entity, ChestMimicRenderState renderState, float f)
	{
		super.updateRenderState(entity, renderState, f);
		renderState.texture = getTexture(entity);
		renderState.mouthOpeningAmplitude = entity.mouthOpeningAmplitude;
		renderState.mouthOpeningFrequencyMalus = entity.mouthOpeningFrequencyMalus;
	}

	public Identifier getTexture(T mimic)
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

	@Override public Identifier getTexture(ChestMimicRenderState renderState) {return renderState.texture;}
}