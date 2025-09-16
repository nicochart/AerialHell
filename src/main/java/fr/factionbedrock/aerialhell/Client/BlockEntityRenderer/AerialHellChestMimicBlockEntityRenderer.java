package fr.factionbedrock.aerialhell.Client.BlockEntityRenderer;

import fr.factionbedrock.aerialhell.BlockEntity.ChestMimicBlockEntity;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellChestMaterials;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;

public class AerialHellChestMimicBlockEntityRenderer extends AerialHellChestBlockEntityRenderer<ChestMimicBlockEntity>
{
	public AerialHellChestMimicBlockEntityRenderer(BlockEntityRendererFactory.Context context) {super(context);}

	@Override protected SpriteIdentifierAndRenderType getSpriteIdentifierAndRenderType(ChestMimicBlockEntity blockEntity, ChestType chestType)
	{
		Block block = blockEntity.getCachedState().getBlock();
		if (block == AerialHellBlocks.AERIAL_TREE_CHEST_MIMIC)
		{
			return new SpriteIdentifierAndRenderType(AerialHellChestMaterials.AERIAL_TREE_SINGLE, RenderLayer::getEntityCutout);
		}
		else if (block == AerialHellBlocks.COPPER_PINE_CHEST_MIMIC)
		{
			return new SpriteIdentifierAndRenderType(AerialHellChestMaterials.COPPER_PINE_SINGLE, RenderLayer::getEntityCutout);
		}
		else if (block == AerialHellBlocks.GOLDEN_BEECH_CHEST_MIMIC)
		{
			return new SpriteIdentifierAndRenderType(AerialHellChestMaterials.GOLDEN_BEECH_SINGLE, RenderLayer::getEntityCutout);
		}
		else if (block == AerialHellBlocks.SKY_CACTUS_FIBER_CHEST_MIMIC)
		{
			return new SpriteIdentifierAndRenderType(AerialHellChestMaterials.SKY_CACTUS_FIBER_SINGLE, RenderLayer::getEntityCutout);
		}
		else //default material (should never happen)
		{
			return new SpriteIdentifierAndRenderType(TexturedRenderLayers.CHEST, RenderLayer::getEntityCutout);
		}
	}
}
