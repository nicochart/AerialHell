package fr.factionbedrock.aerialhell.Client.BlockEntityRenderer;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellChestMaterials;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.BlockEntity.AerialHellChestBlockEntity;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.ChestType;

public class AerialHellChestBlockEntityRenderer extends ChestRenderer<AerialHellChestBlockEntity>
{
	public AerialHellChestBlockEntityRenderer(BlockEntityRendererProvider.Context context) {super(context);}

	@Override
	protected Material getMaterial(AerialHellChestBlockEntity blockEntity, ChestType chestType)
	{
		Block block = blockEntity.getBlockState().getBlock();
		if (block == AerialHellBlocksAndItems.AERIAL_TREE_CHEST.get())
		{
			return chooseMaterial(chestType, AerialHellChestMaterials.AERIAL_TREE_SINGLE, AerialHellChestMaterials.AERIAL_TREE_LEFT, AerialHellChestMaterials.AERIAL_TREE_RIGHT);
		}
		else if (block == AerialHellBlocksAndItems.COPPER_PINE_CHEST.get())
		{
			return chooseMaterial(chestType, AerialHellChestMaterials.COPPER_PINE_SINGLE, AerialHellChestMaterials.COPPER_PINE_LEFT, AerialHellChestMaterials.COPPER_PINE_RIGHT);
		}
		else if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_CHEST.get())
		{
			return chooseMaterial(chestType, AerialHellChestMaterials.LAPIS_ROBINIA_SINGLE, AerialHellChestMaterials.LAPIS_ROBINIA_LEFT, AerialHellChestMaterials.LAPIS_ROBINIA_RIGHT);
		}
		else if (block == AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_CHEST.get())
		{
			return chooseMaterial(chestType, AerialHellChestMaterials.STELLAR_JUNGLE_TREE_SINGLE, AerialHellChestMaterials.STELLAR_JUNGLE_TREE_LEFT, AerialHellChestMaterials.STELLAR_JUNGLE_TREE_RIGHT);
		}
		else if (block == AerialHellBlocksAndItems.GOLDEN_BEECH_CHEST.get())
		{
			return chooseMaterial(chestType, AerialHellChestMaterials.GOLDEN_BEECH_SINGLE, AerialHellChestMaterials.GOLDEN_BEECH_LEFT, AerialHellChestMaterials.GOLDEN_BEECH_RIGHT);
		}
		else if (block == AerialHellBlocksAndItems.SHADOW_PINE_CHEST.get())
		{
			return chooseMaterial(chestType, AerialHellChestMaterials.SHADOW_PINE_SINGLE, AerialHellChestMaterials.SHADOW_PINE_LEFT, AerialHellChestMaterials.SHADOW_PINE_RIGHT);
		}
		else if (block == AerialHellBlocksAndItems.GRAY_SHROOM_CHEST.get())
		{
			return chooseMaterial(chestType, AerialHellChestMaterials.GRAY_SHROOM_SINGLE, AerialHellChestMaterials.GRAY_SHROOM_LEFT, AerialHellChestMaterials.GRAY_SHROOM_RIGHT);
		}
		else if (block == AerialHellBlocksAndItems.SKY_CACTUS_FIBER_CHEST.get())
		{
			return chooseMaterial(chestType, AerialHellChestMaterials.SKY_CACTUS_FIBER_SINGLE, AerialHellChestMaterials.SKY_CACTUS_FIBER_LEFT, AerialHellChestMaterials.SKY_CACTUS_FIBER_RIGHT);
		}
		else if (block == AerialHellBlocksAndItems.MUD_CHEST.get())
		{
			return chooseMaterial(chestType, AerialHellChestMaterials.MUD_SINGLE, AerialHellChestMaterials.MUD_LEFT, AerialHellChestMaterials.MUD_RIGHT);
		}
		else if (block == AerialHellBlocksAndItems.LUNATIC_CHEST.get())
		{
			return chooseMaterial(chestType, AerialHellChestMaterials.LUNATIC_SINGLE, AerialHellChestMaterials.LUNATIC_LEFT, AerialHellChestMaterials.LUNATIC_RIGHT);
		}
		else if (block == AerialHellBlocksAndItems.GOLDEN_NETHER_CHEST.get())
		{
			return chooseMaterial(chestType, AerialHellChestMaterials.GOLDEN_NETHER_SINGLE, AerialHellChestMaterials.GOLDEN_NETHER_LEFT, AerialHellChestMaterials.GOLDEN_NETHER_RIGHT);
		}
		else if (block == AerialHellBlocksAndItems.SHADOW_CATACOMBS_CHEST.get())
		{
			return chooseMaterial(chestType, AerialHellChestMaterials.SHADOW_CATACOMBS_SINGLE, AerialHellChestMaterials.SHADOW_CATACOMBS_LEFT, AerialHellChestMaterials.SHADOW_CATACOMBS_RIGHT);
		}
		else if (block == AerialHellBlocksAndItems.VOLUCITE_CHEST.get())
		{
			return chooseMaterial(chestType, AerialHellChestMaterials.VOLUCITE_SINGLE, AerialHellChestMaterials.VOLUCITE_LEFT, AerialHellChestMaterials.VOLUCITE_RIGHT);
		}
		else //default material (should never happen)
		{
			return chooseMaterial(chestType, Sheets.CHEST_LOCATION, Sheets.CHEST_LOCATION_LEFT, Sheets.CHEST_LOCATION_RIGHT);
		}
	}

	private static Material chooseMaterial(ChestType type, Material materialSingle, Material materialLeft, Material materialRight)
	{
		return switch (type)
		{
			case LEFT -> materialLeft;
			case RIGHT -> materialRight;
			case SINGLE -> materialSingle;
		};
	}
}
