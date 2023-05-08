package fr.factionbedrock.aerialhell.Client.BlockEntityRenderer;

import java.util.Calendar;
import java.util.HashMap;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.BlockEntity.AerialHellChestBlockEntity;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.ChestType;

import static net.minecraft.client.renderer.Sheets.CHEST_SHEET;

public class AerialHellChestBlockEntityRenderer implements BlockEntityRenderer<AerialHellChestBlockEntity>
{
	private static final HashMap<Block, Material[]> LAYERS = Maps.newHashMap();
	private static Material[] defaultLayer;

	private static final int SINGLE = 0;
	private static final int LEFT = 1;
	private static final int RIGHT = 2;

	private final ModelPart lid;
	private final ModelPart bottom;
	private final ModelPart lock;
	private final ModelPart doubleLeftLid;
	private final ModelPart doubleLeftBottom;
	private final ModelPart doubleLeftLock;
	private final ModelPart doubleRightLid;
	private final ModelPart doubleRightBottom;
	private final ModelPart doubleRightLock;

	public AerialHellChestBlockEntityRenderer(BlockEntityRendererProvider.Context context)
	{
		Calendar calendar = Calendar.getInstance();
		/*if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
			this.xmasTextures = true;
		}*/

		ModelPart modelpart = context.bakeLayer(ModelLayers.CHEST);
		this.bottom = modelpart.getChild("bottom");
		this.lid = modelpart.getChild("lid");
		this.lock = modelpart.getChild("lock");
		ModelPart modelpart1 = context.bakeLayer(ModelLayers.DOUBLE_CHEST_LEFT);
		this.doubleLeftBottom = modelpart1.getChild("bottom");
		this.doubleLeftLid = modelpart1.getChild("lid");
		this.doubleLeftLock = modelpart1.getChild("lock");
		ModelPart modelpart2 = context.bakeLayer(ModelLayers.DOUBLE_CHEST_RIGHT);
		this.doubleRightBottom = modelpart2.getChild("bottom");
		this.doubleRightLid = modelpart2.getChild("lid");
		this.doubleRightLock = modelpart2.getChild("lock");
	}


	@Override
	public void render(AerialHellChestBlockEntity blockEntity, float partialTicks, PoseStack poseStackIn, MultiBufferSource buffer, int combinedLightIn, int combinedOverlay)
	{
		Level level = blockEntity.getLevel();
		boolean flagLevelExists = level != null;
		BlockState blockstate = flagLevelExists ? blockEntity.getBlockState() : Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
		ChestType chesttype = blockstate.hasProperty(ChestBlock.TYPE) ? blockstate.getValue(ChestBlock.TYPE) : ChestType.SINGLE;
		Block block = blockstate.getBlock();
		if (block instanceof AbstractChestBlock)
		{
			AbstractChestBlock<?> abstractchestblock = (AbstractChestBlock)block;
			boolean flagDoubleChest = chesttype != ChestType.SINGLE;
			poseStackIn.pushPose();
			float f = blockstate.getValue(ChestBlock.FACING).toYRot();
			poseStackIn.translate(0.5D, 0.5D, 0.5D);
			poseStackIn.mulPose(Vector3f.YP.rotationDegrees(-f));
			poseStackIn.translate(-0.5D, -0.5D, -0.5D);
			DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> neighborcombineresult;
			if (flagLevelExists) {neighborcombineresult = abstractchestblock.combine(blockstate, level, blockEntity.getBlockPos(), true);}
			else {neighborcombineresult = DoubleBlockCombiner.Combiner::acceptNone;}

			float lidAngle = neighborcombineresult.<Float2FloatFunction>apply(ChestBlock.opennessCombiner(blockEntity)).get(partialTicks);
			lidAngle = 1.0F - lidAngle;
			lidAngle = 1.0F - lidAngle * lidAngle * lidAngle;
			int combinedLight = neighborcombineresult.<Int2IntFunction>apply(new BrightnessCombiner<>()).applyAsInt(combinedLightIn);
			VertexConsumer vertexconsumer = this.getConsumer(buffer, abstractchestblock, chesttype);
			if (flagDoubleChest)
			{
				if (chesttype == ChestType.LEFT)
				{
					this.render(poseStackIn, vertexconsumer, this.doubleLeftLid, this.doubleLeftLock, this.doubleLeftBottom, lidAngle, combinedLight, combinedOverlay);
				}
				else
				{
					this.render(poseStackIn, vertexconsumer, this.doubleRightLid, this.doubleRightLock, this.doubleRightBottom, lidAngle, combinedLight, combinedOverlay);
				}
			}
			else
			{
				this.render(poseStackIn, vertexconsumer, this.lid, this.lock, this.bottom, lidAngle, combinedLight, combinedOverlay);
			}
			poseStackIn.popPose();
		}
	}

	private void render(PoseStack poseStackIn, VertexConsumer vertexconsumer, ModelPart chestLid, ModelPart chestLatch, ModelPart chestBottom, float lidAngle, int combinedLightIn, int combinedOverlayIn)
	{
		chestLid.xRot = -(lidAngle * ((float)Math.PI / 2F));
		chestLatch.xRot = chestLid.xRot;
		chestLid.render(poseStackIn, vertexconsumer, combinedLightIn, combinedOverlayIn);
		chestLatch.render(poseStackIn, vertexconsumer, combinedLightIn, combinedOverlayIn);
		chestBottom.render(poseStackIn, vertexconsumer, combinedLightIn, combinedOverlayIn);
	}
	
	static
	{
		defaultLayer = new Material[]
		{
			new Material(CHEST_SHEET, new ResourceLocation("entity/chest/normal.png")),
			new Material(CHEST_SHEET, new ResourceLocation("entity/chest/normal_left.png")),
			new Material(CHEST_SHEET, new ResourceLocation("entity/chest/normal_right.png"))
		};

		AerialHellBlocksAndItems.ITEMS.getEntries().forEach((item) ->
		{
			if (item.get() instanceof BlockItem)
			{
				Block block = ((BlockItem) item.get()).getBlock();
				if (block instanceof ChestBlock)
				{
					String name = block.getRegistryName().getPath();
					LAYERS.put(block, new Material[]
					{
						new Material(CHEST_SHEET, new ResourceLocation(AerialHell.MODID, "entity/" + name + "/" + name + ".png")),
						new Material(CHEST_SHEET, new ResourceLocation(AerialHell.MODID, "entity/" + name + "/" + name + "_left.png")),
						new Material(CHEST_SHEET, new ResourceLocation(AerialHell.MODID, "entity/" + name + "/" + name + "_right.png"))
					});
				}
			}
		});
	}
	
	public static VertexConsumer getConsumer(MultiBufferSource buffer, Block block, ChestType type)
	{
		Material[] layers = LAYERS.getOrDefault(block, defaultLayer);

		if (type == ChestType.LEFT)
		{
			return layers[LEFT].buffer(buffer, RenderType::entityCutout);
		}
		else if (type == ChestType.RIGHT)
		{
			return layers[RIGHT].buffer(buffer, RenderType::entityCutout);
		}
		else //if (type == ChestType.SINGLE)
		{
			return layers[SINGLE].buffer(buffer, RenderType::entityCutout);
		}
	}
}
