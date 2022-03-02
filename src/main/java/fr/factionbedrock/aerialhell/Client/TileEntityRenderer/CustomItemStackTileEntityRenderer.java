package fr.factionbedrock.aerialhell.Client.TileEntityRenderer;

import java.util.function.Supplier;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CustomItemStackTileEntityRenderer extends ItemStackTileEntityRenderer
{
	private final Supplier<? extends TileEntity> tileEntityCreator;
	private TileEntity tileEntity;
	
	public CustomItemStackTileEntityRenderer(Supplier<? extends TileEntity> tileEntityCreator)
	{
		this.tileEntityCreator = tileEntityCreator;
	}
	
	@Override
	public void func_239207_a_(ItemStack itemStackIn, ItemCameraTransforms.TransformType transformType, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn)
	{
		TileEntity tileEntity = this.tileEntity;
		if (tileEntity == null)
		{
			this.tileEntity = tileEntity = tileEntityCreator.get();
		}
		TileEntityRendererDispatcher.instance.renderItem(tileEntity, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
	}
}