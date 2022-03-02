package fr.factionbedrock.aerialhell.Client.TileEntityRenderer;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Block.ChestMimicBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.TileEntity.ChestMimicTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;

import java.util.Calendar;
import java.util.HashMap;

@OnlyIn(Dist.CLIENT)
public class AerialHellChestMimicTileEntityRenderer<T extends TileEntity> extends TileEntityRenderer<T>
{
	private static final HashMap<Block, RenderType[]> LAYERS = Maps.newHashMap();
	private static RenderType[] defaultLayer;

	private static final int NORMAL = 0;
	private static final int LEFT = 1;
	private static final int RIGHT = 2;
	
	private final ModelRenderer singleLid;
	private final ModelRenderer singleBottom;
	private final ModelRenderer singleLatch;
	private final ModelRenderer rightLid;
	private final ModelRenderer rightBottom;
	private final ModelRenderer rightLatch;
	private final ModelRenderer leftLid;
	private final ModelRenderer leftBottom;
	private final ModelRenderer leftLatch;
	private boolean isChristmas;

	public AerialHellChestMimicTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn)
	{
		super(rendererDispatcherIn);
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.MONTH) + 1 == 12 && calendar.get(Calendar.DATE) >= 24 && calendar.get(Calendar.DATE) <= 26)
		{
			this.isChristmas = true;
		}

		this.singleBottom = new ModelRenderer(64, 64, 0, 19);
		this.singleBottom.addBox(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F, 0.0F);
		this.singleLid = new ModelRenderer(64, 64, 0, 0);
		this.singleLid.addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F, 0.0F);
		this.singleLid.rotationPointY = 9.0F;
		this.singleLid.rotationPointZ = 1.0F;
		this.singleLatch = new ModelRenderer(64, 64, 0, 0);
		this.singleLatch.addBox(7.0F, -1.0F, 15.0F, 2.0F, 4.0F, 1.0F, 0.0F);
		this.singleLatch.rotationPointY = 8.0F;
		this.rightBottom = new ModelRenderer(64, 64, 0, 19);
		this.rightBottom.addBox(1.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F, 0.0F);
		this.rightLid = new ModelRenderer(64, 64, 0, 0);
		this.rightLid.addBox(1.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F, 0.0F);
		this.rightLid.rotationPointY = 9.0F;
		this.rightLid.rotationPointZ = 1.0F;
		this.rightLatch = new ModelRenderer(64, 64, 0, 0);
		this.rightLatch.addBox(15.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F, 0.0F);
		this.rightLatch.rotationPointY = 8.0F;
		this.leftBottom = new ModelRenderer(64, 64, 0, 19);
		this.leftBottom.addBox(0.0F, 0.0F, 1.0F, 15.0F, 10.0F, 14.0F, 0.0F);
		this.leftLid = new ModelRenderer(64, 64, 0, 0);
		this.leftLid.addBox(0.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F, 0.0F);
		this.leftLid.rotationPointY = 9.0F;
		this.leftLid.rotationPointZ = 1.0F;
		this.leftLatch = new ModelRenderer(64, 64, 0, 0);
		this.leftLatch.addBox(0.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F, 0.0F);
		this.leftLatch.rotationPointY = 8.0F;
	}

	@Override
	public void render(T tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn)
	{
		World world = tileEntityIn.getWorld();
		boolean flag_world = world != null;
		BlockState blockstate = flag_world ? tileEntityIn.getBlockState() : AerialHellBlocksAndItems.AERIAL_TREE_CHEST_MIMIC.get().getDefaultState().with(ChestMimicBlock.FACING, Direction.SOUTH);
		ChestType chesttype = blockstate.hasProperty(ChestBlock.TYPE) ? blockstate.get(ChestBlock.TYPE) : ChestType.SINGLE;
		Block block = blockstate.getBlock();
		if (block instanceof ChestMimicBlock)
		{
			ChestMimicBlock chestMimicBlock = (ChestMimicBlock) block;
			matrixStackIn.push();
			float f = blockstate.get(ChestBlock.FACING).getHorizontalAngle();
			matrixStackIn.translate(0.5D, 0.5D, 0.5D);
			matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-f));
			matrixStackIn.translate(-0.5D, -0.5D, -0.5D);
			TileEntityMerger.ICallbackWrapper<? extends ChestMimicTileEntity> icallbackwrapper;
			if (world != null)
			{
				icallbackwrapper = chestMimicBlock.combine(blockstate, world, tileEntityIn.getPos(), true);
			}
			else
			{
				icallbackwrapper = TileEntityMerger.ICallback::func_225537_b_;
			}
			combinedLightIn = icallbackwrapper.apply(new DualBrightnessCallback<>()).applyAsInt(combinedLightIn);			
			IVertexBuilder vertexConsumer = getConsumer(bufferIn, block, chesttype);
			if (chesttype != ChestType.SINGLE)
			{
				if (chesttype == ChestType.LEFT)
				{
					this.renderModels(matrixStackIn, vertexConsumer, this.leftLid, this.leftLatch, this.leftBottom, combinedLightIn, combinedOverlayIn);
				}
				else
				{
					this.renderModels(matrixStackIn, vertexConsumer, this.rightLid, this.rightLatch, this.rightBottom, combinedLightIn, combinedOverlayIn);
				}
			}
			else
			{
				this.renderModels(matrixStackIn, vertexConsumer, this.singleLid, this.singleLatch, this.singleBottom, combinedLightIn, combinedOverlayIn);
			}
			matrixStackIn.pop();
		}
		else
		{
			LogManager.getLogger(AerialHellChestMimicTileEntityRenderer.class).warn("Invalid block used with ChestMimicTileEntityRenderer!");
		}
	}

	private void renderModels(MatrixStack matrixStackIn, IVertexBuilder bufferIn, ModelRenderer chestLid, ModelRenderer chestLatch, ModelRenderer chestBottom, int combinedLightIn, int combinedOverlayIn)
	{
		chestLid.render(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
		chestLatch.render(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
		chestBottom.render(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
	}

	private static RenderType getChestTexture(ChestType type, RenderType[] layers)
	{
		switch (type)
		{
		case LEFT:
			return layers[LEFT];
		case RIGHT:
			return layers[RIGHT];
		case SINGLE:
		default:
			return layers[NORMAL];
		}
	}
	
	static
	{
		defaultLayer = new RenderType[]
		{
			RenderType.getEntityCutout(new ResourceLocation("textures/entity/chest/normal.png")),
			RenderType.getEntityCutout(new ResourceLocation("textures/entity/chest/normal_left.png")),
			RenderType.getEntityCutout(new ResourceLocation("textures/entity/chest/normal_right.png"))
		};

		AerialHellBlocksAndItems.ITEMS.getEntries().forEach((item) ->
		{
			if (item.get() instanceof BlockItem)
			{
				Block block = ((BlockItem) item.get()).getBlock();
				if (block instanceof ChestMimicBlock)
				{
					String mimic_name = block.getRegistryName().getPath();
					String chest_name = mimic_name.substring(0, mimic_name.length() - "_mimic".length());
					LAYERS.put(block, new RenderType[]
					{
						RenderType.getEntityCutout(new ResourceLocation(AerialHell.MODID, "textures/entity/" + chest_name + "/" + chest_name + ".png")),
						RenderType.getEntityCutout(new ResourceLocation(AerialHell.MODID, "textures/entity/" + chest_name + "/" + chest_name + "_left.png")),
						RenderType.getEntityCutout(new ResourceLocation(AerialHell.MODID, "textures/entity/" + chest_name + "/" + chest_name + "_right.png"))
					});
				}
			}
		});
	}
	
	public static IVertexBuilder getConsumer(IRenderTypeBuffer provider, Block block, ChestType chestType)
	{
		RenderType[] layers = LAYERS.getOrDefault(block, defaultLayer);
		return provider.getBuffer(getChestTexture(chestType, layers));
	}
}
