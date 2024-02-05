package fr.factionbedrock.aerialhell.Client.BlockEntityRenderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellChestMaterials;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.BlockEntity.AerialHellChestBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;

import java.util.function.Function;

public class AerialHellChestBlockEntityRenderer extends ChestRenderer<AerialHellChestBlockEntity>
{
	public AerialHellChestBlockEntityRenderer(BlockEntityRendererProvider.Context context) {super(context);}

	protected MaterialAndRenderType getMaterialAndRenderType(AerialHellChestBlockEntity blockEntity, ChestType chestType)
	{
		Block block = blockEntity.getBlockState().getBlock();
		if (block == AerialHellBlocksAndItems.AERIAL_TREE_CHEST.get())
		{
			return new MaterialAndRenderType(chooseMaterial(chestType, AerialHellChestMaterials.AERIAL_TREE_SINGLE, AerialHellChestMaterials.AERIAL_TREE_LEFT, AerialHellChestMaterials.AERIAL_TREE_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocksAndItems.COPPER_PINE_CHEST.get())
		{
			return new MaterialAndRenderType(chooseMaterial(chestType, AerialHellChestMaterials.COPPER_PINE_SINGLE, AerialHellChestMaterials.COPPER_PINE_LEFT, AerialHellChestMaterials.COPPER_PINE_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocksAndItems.LAPIS_ROBINIA_CHEST.get())
		{
			return new MaterialAndRenderType(chooseMaterial(chestType, AerialHellChestMaterials.LAPIS_ROBINIA_SINGLE, AerialHellChestMaterials.LAPIS_ROBINIA_LEFT, AerialHellChestMaterials.LAPIS_ROBINIA_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocksAndItems.STELLAR_JUNGLE_TREE_CHEST.get())
		{
			return new MaterialAndRenderType(chooseMaterial(chestType, AerialHellChestMaterials.STELLAR_JUNGLE_TREE_SINGLE, AerialHellChestMaterials.STELLAR_JUNGLE_TREE_LEFT, AerialHellChestMaterials.STELLAR_JUNGLE_TREE_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocksAndItems.GOLDEN_BEECH_CHEST.get())
		{
			return new MaterialAndRenderType(chooseMaterial(chestType, AerialHellChestMaterials.GOLDEN_BEECH_SINGLE, AerialHellChestMaterials.GOLDEN_BEECH_LEFT, AerialHellChestMaterials.GOLDEN_BEECH_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocksAndItems.SHADOW_PINE_CHEST.get())
		{
			return new MaterialAndRenderType(chooseMaterial(chestType, AerialHellChestMaterials.SHADOW_PINE_SINGLE, AerialHellChestMaterials.SHADOW_PINE_LEFT, AerialHellChestMaterials.SHADOW_PINE_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocksAndItems.GRAY_SHROOM_CHEST.get())
		{
			return new MaterialAndRenderType(chooseMaterial(chestType, AerialHellChestMaterials.GRAY_SHROOM_SINGLE, AerialHellChestMaterials.GRAY_SHROOM_LEFT, AerialHellChestMaterials.GRAY_SHROOM_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocksAndItems.SKY_CACTUS_FIBER_CHEST.get())
		{
			return new MaterialAndRenderType(chooseMaterial(chestType, AerialHellChestMaterials.SKY_CACTUS_FIBER_SINGLE, AerialHellChestMaterials.SKY_CACTUS_FIBER_LEFT, AerialHellChestMaterials.SKY_CACTUS_FIBER_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocksAndItems.GHOST_BOAT_CHEST.get())
		{
			return new MaterialAndRenderType(chooseMaterial(chestType, AerialHellChestMaterials.GHOST_BOAT_SINGLE, AerialHellChestMaterials.GHOST_BOAT_LEFT, AerialHellChestMaterials.GHOST_BOAT_RIGHT), RenderType::entityTranslucent);
		}
		else if (block == AerialHellBlocksAndItems.MUD_CHEST.get())
		{
			return new MaterialAndRenderType(chooseMaterial(chestType, AerialHellChestMaterials.MUD_SINGLE, AerialHellChestMaterials.MUD_LEFT, AerialHellChestMaterials.MUD_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocksAndItems.LUNATIC_CHEST.get())
		{
			return new MaterialAndRenderType(chooseMaterial(chestType, AerialHellChestMaterials.LUNATIC_SINGLE, AerialHellChestMaterials.LUNATIC_LEFT, AerialHellChestMaterials.LUNATIC_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocksAndItems.GOLDEN_NETHER_CHEST.get())
		{
			return new MaterialAndRenderType(chooseMaterial(chestType, AerialHellChestMaterials.GOLDEN_NETHER_SINGLE, AerialHellChestMaterials.GOLDEN_NETHER_LEFT, AerialHellChestMaterials.GOLDEN_NETHER_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocksAndItems.SHADOW_CATACOMBS_CHEST.get())
		{
			return new MaterialAndRenderType(chooseMaterial(chestType, AerialHellChestMaterials.SHADOW_CATACOMBS_SINGLE, AerialHellChestMaterials.SHADOW_CATACOMBS_LEFT, AerialHellChestMaterials.SHADOW_CATACOMBS_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocksAndItems.VOLUCITE_CHEST.get())
		{
			return new MaterialAndRenderType(chooseMaterial(chestType, AerialHellChestMaterials.VOLUCITE_SINGLE, AerialHellChestMaterials.VOLUCITE_LEFT, AerialHellChestMaterials.VOLUCITE_RIGHT), RenderType::entityCutout);
		}
		else //default material (should never happen)
		{
			return new MaterialAndRenderType(chooseMaterial(chestType, Sheets.CHEST_LOCATION, Sheets.CHEST_LOCATION_LEFT, Sheets.CHEST_LOCATION_RIGHT), RenderType::entityCutout);
		}
	}

	@Override protected Material getMaterial(AerialHellChestBlockEntity blockEntity, ChestType chestType)
	{
		return getMaterialAndRenderType(blockEntity, chestType).getMaterial();
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

	@Override public void render(AerialHellChestBlockEntity tileEntityIn, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLightIn, int combinedOverlayIn)
	{
		Level level = tileEntityIn.getLevel();
		boolean levelnotnull = level != null;
		BlockState blockstate = levelnotnull ? tileEntityIn.getBlockState() : Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
		ChestType chesttype = blockstate.hasProperty(ChestBlock.TYPE) ? blockstate.getValue(ChestBlock.TYPE) : ChestType.SINGLE;
		Block block = blockstate.getBlock();
		if (block instanceof AbstractChestBlock<?> abstractchestblock)
		{
			boolean notSingle = chesttype != ChestType.SINGLE;
			poseStack.pushPose();
			float f = blockstate.getValue(ChestBlock.FACING).toYRot();
			poseStack.translate(0.5F, 0.5F, 0.5F);
			poseStack.mulPose(Axis.YP.rotationDegrees(-f));
			poseStack.translate(-0.5F, -0.5F, -0.5F);
			DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> neighborcombineresult;
			if (levelnotnull) {neighborcombineresult = abstractchestblock.combine(blockstate, level, tileEntityIn.getBlockPos(), true);}
			else {neighborcombineresult = DoubleBlockCombiner.Combiner::acceptNone;}

			float openness = neighborcombineresult.apply(ChestBlock.opennessCombiner(tileEntityIn)).get(partialTicks);
			openness = 1.0F - openness;
			openness = 1.0F - openness * openness * openness;
			int i = neighborcombineresult.apply(new BrightnessCombiner<>()).applyAsInt(combinedLightIn);
			MaterialAndRenderType materialAndRenderType = getMaterialAndRenderType(tileEntityIn, chesttype);
			VertexConsumer vertexconsumer = materialAndRenderType.getMaterial().buffer(bufferSource, materialAndRenderType.getRenderType());
			if (notSingle)
			{
				if (chesttype == ChestType.LEFT) {this.render(poseStack, vertexconsumer, this.doubleLeftLid, this.doubleLeftLock, this.doubleLeftBottom, openness, i, combinedOverlayIn);}
				else {this.render(poseStack, vertexconsumer, this.doubleRightLid, this.doubleRightLock, this.doubleRightBottom, openness, i, combinedOverlayIn);}
			}
			else {this.render(poseStack, vertexconsumer, this.lid, this.lock, this.bottom, openness, i, combinedOverlayIn);}
			poseStack.popPose();
		}
	}

	private class MaterialAndRenderType
	{
		private final Function<ResourceLocation, RenderType> renderType;
		private final Material material;

		private MaterialAndRenderType(Material material, Function<ResourceLocation, RenderType> renderType) {this.renderType = renderType; this.material = material;}

		public Function<ResourceLocation, RenderType> getRenderType() {return renderType;}
		public Material getMaterial() {return material;}
	}
}
