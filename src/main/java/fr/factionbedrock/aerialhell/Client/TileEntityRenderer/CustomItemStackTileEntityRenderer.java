package fr.factionbedrock.aerialhell.Client.BlockEntityRenderer;

import java.util.function.Supplier;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackBlockEntityRenderer;
import net.minecraft.client.renderer.tileentity.BlockEntityRendererDispatcher;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CustomItemStackBlockEntityRenderer extends ItemStackBlockEntityRenderer
{
	private final Supplier<? extends BlockEntity> tileEntityCreator;
	private BlockEntity tileEntity;
	
	public CustomItemStackBlockEntityRenderer(Supplier<? extends BlockEntity> tileEntityCreator)
	{
		this.tileEntityCreator = tileEntityCreator;
	}
	
	@Override
	public void func_239207_a_(ItemStack itemStackIn, ItemCameraTransforms.TransformType transformType, PoseStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn)
	{
		BlockEntity tileEntity = this.tileEntity;
		if (tileEntity == null)
		{
			this.tileEntity = tileEntity = tileEntityCreator.get();
		}
		BlockEntityRendererDispatcher.instance.renderItem(tileEntity, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
	}
}