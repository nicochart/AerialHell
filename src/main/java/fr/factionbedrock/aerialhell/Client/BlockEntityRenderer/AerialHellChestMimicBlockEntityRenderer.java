package fr.factionbedrock.aerialhell.Client.BlockEntityRenderer;

import fr.factionbedrock.aerialhell.BlockEntity.ChestMimicBlockEntity;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellChestMaterials;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.ChestType;

public class AerialHellChestMimicBlockEntityRenderer extends AerialHellChestBlockEntityRenderer<ChestMimicBlockEntity>
{
	public AerialHellChestMimicBlockEntityRenderer(BlockEntityRendererProvider.Context context) {super(context);}

	@Override protected SpriteIdentifierAndRenderType getSpriteIdentifierAndRenderType(ChestMimicBlockEntity blockEntity, ChestType chestType)
	{
		Block block = blockEntity.getBlockState().getBlock();
		if (block == AerialHellBlocks.AERIAL_TREE_CHEST_MIMIC)
		{
			return new SpriteIdentifierAndRenderType(AerialHellChestMaterials.AERIAL_TREE_SINGLE, RenderType::entityCutout);
		}
		else if (block == AerialHellBlocks.COPPER_PINE_CHEST_MIMIC)
		{
			return new SpriteIdentifierAndRenderType(AerialHellChestMaterials.COPPER_PINE_SINGLE, RenderType::entityCutout);
		}
		else if (block == AerialHellBlocks.GOLDEN_BEECH_CHEST_MIMIC)
		{
			return new SpriteIdentifierAndRenderType(AerialHellChestMaterials.GOLDEN_BEECH_SINGLE, RenderType::entityCutout);
		}
		else if (block == AerialHellBlocks.SKY_CACTUS_FIBER_CHEST_MIMIC)
		{
			return new SpriteIdentifierAndRenderType(AerialHellChestMaterials.SKY_CACTUS_FIBER_SINGLE, RenderType::entityCutout);
		}
		else //default material (should never happen)
		{
			return new SpriteIdentifierAndRenderType(Sheets.CHEST_LOCATION, RenderType::entityCutout);
		}
	}
}
