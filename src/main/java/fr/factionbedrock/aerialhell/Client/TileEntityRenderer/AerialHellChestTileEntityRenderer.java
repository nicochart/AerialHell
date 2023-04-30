package fr.factionbedrock.aerialhell.Client.BlockEntityRenderer;

import java.util.HashMap;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.BlockEntity.AerialHellChestBlockEntity;
import net.minecraft.block.AbstractChestBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.BlockEntityRenderer;
import net.minecraft.client.renderer.tileentity.BlockEntityRendererDispatcher;
import net.minecraft.world.item.BlockItem;
import net.minecraft.state.properties.ChestType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.tileentity.BlockEntityMerger;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.level.Level;

public class AerialHellChestBlockEntityRenderer<T extends BlockEntity & IChestLid> extends BlockEntityRenderer<AerialHellChestBlockEntity>
{
	private static final HashMap<Block, RenderType[]> LAYERS = Maps.newHashMap();
	private static RenderType[] defaultLayer;

	private static final int SINGLE = 0;
	private static final int LEFT = 1;
	private static final int RIGHT = 2;

	private final ModelPart singleLid;
	private final ModelPart singleBottom;
	private final ModelPart singleLatch;
	private final ModelPart rightLid;
	private final ModelPart rightBottom;
	private final ModelPart rightLatch;
	private final ModelPart leftLid;
	private final ModelPart leftBottom;
	private final ModelPart leftLatch;

	public AerialHellChestBlockEntityRenderer(BlockEntityRendererDispatcher rendererDispatcherIn)
	{
		super(rendererDispatcherIn);
		/*Calendar calendar = Calendar.getInstance();
	    if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26)
	    {
	         this.isChristmas = true;
	    }*/
		
		this.singleBottom = new ModelPart(64, 64, 0, 19);
		this.singleBottom.addBox(1.0F, 0.0F, 1.0F, 14.0F, 9.0F, 14.0F, 0.0F);
		this.singleLid = new ModelPart(64, 64, 0, 0);
		this.singleLid.addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F, 0.0F);
		this.singleLid.rotationPointY = 9.0F;
		this.singleLid.rotationPointZ = 1.0F;
		this.singleLatch = new ModelPart(64, 64, 0, 0);
		this.singleLatch.addBox(7.0F, -1.0F, 15.0F, 2.0F, 4.0F, 1.0F, 0.0F);
		this.singleLatch.rotationPointY = 8.0F;
		this.rightBottom = new ModelPart(64, 64, 0, 19);
		this.rightBottom.addBox(1.0F, 0.0F, 1.0F, 15.0F, 9.0F, 14.0F, 0.0F);
		this.rightLid = new ModelPart(64, 64, 0, 0);
		this.rightLid.addBox(1.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F, 0.0F);
		this.rightLid.rotationPointY = 9.0F;
		this.rightLid.rotationPointZ = 1.0F;
		this.rightLatch = new ModelPart(64, 64, 0, 0);
		this.rightLatch.addBox(15.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F, 0.0F);
		this.rightLatch.rotationPointY = 8.0F;
		this.leftBottom = new ModelPart(64, 64, 0, 19);
		this.leftBottom.addBox(0.0F, 0.0F, 1.0F, 15.0F, 9.0F, 14.0F, 0.0F);
		this.leftLid = new ModelPart(64, 64, 0, 0);
		this.leftLid.addBox(0.0F, 0.0F, 0.0F, 15.0F, 5.0F, 14.0F, 0.0F);
		this.leftLid.rotationPointY = 9.0F;
		this.leftLid.rotationPointZ = 1.0F;
		this.leftLatch = new ModelPart(64, 64, 0, 0);
		this.leftLatch.addBox(0.0F, -1.0F, 15.0F, 1.0F, 4.0F, 1.0F, 0.0F);
		this.leftLatch.rotationPointY = 8.0F;
	}


	@Override
	public void render(AerialHellChestBlockEntity tileEntity, float partialTicks, PoseStack matrixStackIn, IRenderTypeBuffer vertexConsumersIn, int lightIn, int combinedOverlay)
	{
		Level world = tileEntity.getLevel();
		boolean flagWorldExists = world != null;
		BlockState blockState = flagWorldExists ? tileEntity.getBlockState() : (BlockState) Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
		ChestType chestType = blockState.hasProperty(ChestBlock.TYPE) ? (ChestType) blockState.get(ChestBlock.TYPE) : ChestType.SINGLE;
		Block block = blockState.getBlock();
		if (tileEntity.hasChest()) block = tileEntity.getChest();
		if (block instanceof AbstractChestBlock)
		{
			AbstractChestBlock<?> abstractChestBlock = (AbstractChestBlock<?>) block;
			boolean flagDoubleChest = chestType != ChestType.SINGLE;
			float f = ((Direction) blockState.get(ChestBlock.FACING)).getHorizontalAngle();
			BlockEntityMerger.ICallbackWrapper<? extends ChestBlockEntity> propertySource;

			matrixStackIn.push();
			matrixStackIn.translate(0.5D, 0.5D, 0.5D);
			matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-f));
			matrixStackIn.translate(-0.5D, -0.5D, -0.5D);

			if (flagWorldExists)
			{
				propertySource = abstractChestBlock.combine(blockState, world, tileEntity.getPos(), true);
			}
			else
			{
				propertySource = BlockEntityMerger.ICallback::func_225537_b_;
			}

			float lidAngle = ((Float2FloatFunction) propertySource.apply(ChestBlock.getLidRotationCallback((IChestLid) tileEntity))).get(partialTicks);
			lidAngle = 1.0F - lidAngle;
			lidAngle = 1.0F - lidAngle * lidAngle * lidAngle;

			int combinedLight = ((Int2IntFunction) propertySource.apply(new DualBrightnessCallback())).applyAsInt(lightIn);

			IVertexBuilder ivertexbuilder = getConsumer(vertexConsumersIn, block, chestType);

			if (flagDoubleChest)
			{
				if (chestType == ChestType.LEFT)
				{
					renderModels(matrixStackIn, ivertexbuilder, this.leftLid, this.leftLatch, this.leftBottom, lidAngle, combinedLight, combinedOverlay);
				}
				else
				{
					renderModels(matrixStackIn, ivertexbuilder, this.rightLid, this.rightLatch, this.rightBottom, lidAngle, combinedLight, combinedOverlay);
				}
			}
			else
			{
				renderModels(matrixStackIn, ivertexbuilder, this.singleLid, this.singleLatch, this.singleBottom, lidAngle, combinedLight, combinedOverlay);
			}

			matrixStackIn.pop();
		}
	}

	private void renderModels(PoseStack matrixStackIn, IVertexBuilder bufferIn, ModelPart chestLid, ModelPart chestLatch, ModelPart chestBottom, float lidAngle, int combinedLightIn, int combinedOverlayIn)
	{
			chestLid.xRot = -(lidAngle *((float)Math.PI / 2F));
			chestLatch.xRot = chestLid.xRot;
			chestLid.render(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
			chestLatch.render(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
			chestBottom.render(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
	}

	private static RenderType getChestTexture(ChestType type, RenderType[] layers)
	{
		if (type == ChestType.LEFT)
		{
			return layers[LEFT];
		}
		else if (type == ChestType.RIGHT)
		{
			return layers[RIGHT];
		}
		else //if (type == ChestType.SINGLE)
		{
			return layers[SINGLE];
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
				if (block instanceof ChestBlock)
				{
					String name = block.getRegistryName().getPath();
					LAYERS.put(block, new RenderType[]
					{
						RenderType.getEntityCutout(new ResourceLocation(AerialHell.MODID, "textures/entity/" + name + "/" + name + ".png")),
						RenderType.getEntityCutout(new ResourceLocation(AerialHell.MODID, "textures/entity/" + name + "/" + name + "_left.png")),
						RenderType.getEntityCutout(new ResourceLocation(AerialHell.MODID, "textures/entity/" + name + "/" + name + "_right.png"))
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
