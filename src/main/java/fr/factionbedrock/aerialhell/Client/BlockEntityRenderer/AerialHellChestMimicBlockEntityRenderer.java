package fr.factionbedrock.aerialhell.Client.BlockEntityRenderer;

import fr.factionbedrock.aerialhell.BlockEntity.ChestMimicBlockEntity;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellChestMaterials;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.blockentity.state.ChestRenderState;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.jetbrains.annotations.Nullable;

public class AerialHellChestMimicBlockEntityRenderer extends ChestRenderer<ChestMimicBlockEntity>
{
	public AerialHellChestMimicBlockEntityRenderer(BlockEntityRendererProvider.Context context) {super(context);}

	@Override @Nullable public SpriteId getCustomSprite(ChestMimicBlockEntity blockEntity, ChestRenderState renderState)
	{
		return getMaterial(blockEntity, renderState.type);
	}

	protected SpriteId getMaterial(ChestMimicBlockEntity blockEntity, ChestType chestType)
	{
		Block block = blockEntity.getBlockState().getBlock();
		if (block == AerialHellBlocks.AERIAL_TREE_CHEST_MIMIC.get())
		{
			return new SpriteId(Sheets.CHEST_SHEET, AerialHellChestMaterials.AERIAL_TREE_SINGLE.sprite());
		}
		else if (block == AerialHellBlocks.COPPER_PINE_CHEST_MIMIC.get())
		{
			return new SpriteId(Sheets.CHEST_SHEET, AerialHellChestMaterials.COPPER_PINE_SINGLE.sprite());
		}
		else if (block == AerialHellBlocks.GOLDEN_BEECH_CHEST_MIMIC.get())
		{
			return new SpriteId(Sheets.CHEST_SHEET, AerialHellChestMaterials.GOLDEN_BEECH_SINGLE.sprite());
		}
		else if (block == AerialHellBlocks.SKY_CACTUS_FIBER_CHEST_MIMIC.get())
		{
			return new SpriteId(Sheets.CHEST_SHEET, AerialHellChestMaterials.SKY_CACTUS_FIBER_SINGLE.sprite());
		}
		else //default material (should never happen)
		{
			return Sheets.CHEST_REGULAR.select(chestType);
		}
	}
}
