package fr.factionbedrock.aerialhell.Client.BlockEntityRenderer;

import fr.factionbedrock.aerialhell.BlockEntity.AerialHellChestBlockEntity;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.ItemStack;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.level.block.ChestBlock;

public class AerialHellChestItemBlockEntityRenderer extends BlockEntityWithoutLevelRenderer
{
	private AerialHellChestBlockEntity chest;
	private final BlockEntityRenderDispatcher renderDispatcher;

	public AerialHellChestItemBlockEntityRenderer(BlockEntityRenderDispatcher renderDispatcher, EntityModelSet modelSet) {super(renderDispatcher, modelSet); this.renderDispatcher = renderDispatcher;}

	@Override
	public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay)
	{
		if (stack.getItem() instanceof BlockItem)
		{
			final Block block = ((BlockItem) stack.getItem()).getBlock();
			if (block instanceof ChestBlock)
			{
				chest.setChest(Block.byItem(stack.getItem()));
				renderDispatcher.renderItem(chest, poseStack, buffer, combinedLight, combinedOverlay);
			}
		}
	}
}
