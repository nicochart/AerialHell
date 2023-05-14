package fr.factionbedrock.aerialhell.Client.BlockEntityRenderer;

import fr.factionbedrock.aerialhell.BlockEntity.ChestMimicBlockEntity;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellChestMaterials;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.ChestType;

public class AerialHellChestMimicBlockEntityRenderer extends ChestRenderer<ChestMimicBlockEntity>
{
	public AerialHellChestMimicBlockEntityRenderer(BlockEntityRendererProvider.Context context) {super(context);}

	@Override
	protected Material getMaterial(ChestMimicBlockEntity blockEntity, ChestType chestType)
	{
		Block block = blockEntity.getBlockState().getBlock();
		if (block == AerialHellBlocksAndItems.AERIAL_TREE_CHEST_MIMIC.get())
		{
			return AerialHellChestMaterials.AERIAL_TREE_SINGLE;
		}
		else if (block == AerialHellBlocksAndItems.COPPER_PINE_CHEST_MIMIC.get())
		{
			return AerialHellChestMaterials.COPPER_PINE_SINGLE;
		}
		else if (block == AerialHellBlocksAndItems.GOLDEN_BEECH_CHEST_MIMIC.get())
		{
			return AerialHellChestMaterials.GOLDEN_BEECH_SINGLE;
		}
		else if (block == AerialHellBlocksAndItems.SKY_CACTUS_FIBER_CHEST_MIMIC.get())
		{
			return AerialHellChestMaterials.SKY_CACTUS_FIBER_SINGLE;
		}
		else //default material (should never happen)
		{
			return Sheets.CHEST_LOCATION;
		}
	}
}
