package fr.factionbedrock.aerialhell.Client.BlockEntityRenderer;

import fr.factionbedrock.aerialhell.BlockEntity.AerialHellChestBlockEntity;

import net.minecraft.client.renderer.tileentity.ItemStackBlockEntityRenderer;
import net.minecraft.world.level.block.Block;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.BlockEntityRendererDispatcher;
import net.minecraft.world.item.ItemStack;

import com.mojang.blaze3d.vertex.PoseStack;

public class AerialHellChestItemBlockEntityRenderer extends ItemStackBlockEntityRenderer
{
	private AerialHellChestBlockEntity chest;

	@Override
	public void func_239207_a_(ItemStack stack, TransformType p_239207_2_, PoseStack matrixStack,IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay)
	{
		if (chest == null)	chest = new AerialHellChestBlockEntity();
		chest.setChest(Block.getBlockFromItem(stack.getItem()));
		BlockEntityRendererDispatcher.instance.renderItem(chest, matrixStack, buffer, combinedLight, combinedOverlay);
	}
}
