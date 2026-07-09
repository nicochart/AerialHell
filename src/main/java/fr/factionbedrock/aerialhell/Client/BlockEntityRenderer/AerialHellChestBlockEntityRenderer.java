package fr.factionbedrock.aerialhell.Client.BlockEntityRenderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellChestMaterials;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
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
import net.minecraft.world.level.block.AbstractChestBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import java.util.function.Function;

public class AerialHellChestBlockEntityRenderer<T extends ChestBlockEntity> extends ChestRenderer<T>
{
	public AerialHellChestBlockEntityRenderer(BlockEntityRendererProvider.Context context) {super(context);}

	protected SpriteIdentifierAndRenderType getSpriteIdentifierAndRenderType(T blockEntity, ChestType chestType)
	{
		Block block = blockEntity.getBlockState().getBlock();
		if (block == AerialHellBlocks.AERIAL_TREE_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.AERIAL_TREE_SINGLE, AerialHellChestMaterials.AERIAL_TREE_LEFT, AerialHellChestMaterials.AERIAL_TREE_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocks.COPPER_PINE_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.COPPER_PINE_SINGLE, AerialHellChestMaterials.COPPER_PINE_LEFT, AerialHellChestMaterials.COPPER_PINE_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocks.LAPIS_ROBINIA_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.LAPIS_ROBINIA_SINGLE, AerialHellChestMaterials.LAPIS_ROBINIA_LEFT, AerialHellChestMaterials.LAPIS_ROBINIA_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocks.STELLAR_JUNGLE_TREE_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.STELLAR_JUNGLE_TREE_SINGLE, AerialHellChestMaterials.STELLAR_JUNGLE_TREE_LEFT, AerialHellChestMaterials.STELLAR_JUNGLE_TREE_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocks.GOLDEN_BEECH_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.GOLDEN_BEECH_SINGLE, AerialHellChestMaterials.GOLDEN_BEECH_LEFT, AerialHellChestMaterials.GOLDEN_BEECH_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocks.SHADOW_PINE_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.SHADOW_PINE_SINGLE, AerialHellChestMaterials.SHADOW_PINE_LEFT, AerialHellChestMaterials.SHADOW_PINE_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocks.GRAY_SHROOM_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.GRAY_SHROOM_SINGLE, AerialHellChestMaterials.GRAY_SHROOM_LEFT, AerialHellChestMaterials.GRAY_SHROOM_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocks.SKY_CACTUS_FIBER_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.SKY_CACTUS_FIBER_SINGLE, AerialHellChestMaterials.SKY_CACTUS_FIBER_LEFT, AerialHellChestMaterials.SKY_CACTUS_FIBER_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocks.GHOST_BOAT_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.GHOST_BOAT_SINGLE, AerialHellChestMaterials.GHOST_BOAT_LEFT, AerialHellChestMaterials.GHOST_BOAT_RIGHT), RenderType::entityTranslucent);
		}
		else if (block == AerialHellBlocks.MUD_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.MUD_SINGLE, AerialHellChestMaterials.MUD_LEFT, AerialHellChestMaterials.MUD_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocks.LUNATIC_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.LUNATIC_SINGLE, AerialHellChestMaterials.LUNATIC_LEFT, AerialHellChestMaterials.LUNATIC_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocks.GOLDEN_NETHER_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.GOLDEN_NETHER_SINGLE, AerialHellChestMaterials.GOLDEN_NETHER_LEFT, AerialHellChestMaterials.GOLDEN_NETHER_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocks.SHADOW_CATACOMBS_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.SHADOW_CATACOMBS_SINGLE, AerialHellChestMaterials.SHADOW_CATACOMBS_LEFT, AerialHellChestMaterials.SHADOW_CATACOMBS_RIGHT), RenderType::entityCutout);
		}
		else if (block == AerialHellBlocks.VOLUCITE_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.VOLUCITE_SINGLE, AerialHellChestMaterials.VOLUCITE_LEFT, AerialHellChestMaterials.VOLUCITE_RIGHT), RenderType::entityCutout);
		}
		else //default material (should never happen)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, Sheets.CHEST_LOCATION, Sheets.CHEST_LOCATION_LEFT, Sheets.CHEST_LOCATION_RIGHT), RenderType::entityCutout);
		}
	}

	private static Material getSpriteIdentifier(ChestType type, Material single, Material left, Material right)
	{
		return switch (type)
		{
			case LEFT -> left;
			case RIGHT -> right;
			case SINGLE -> single;
		};
	}

	@Override public void render(T entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay)
	{
		Level world = entity.getLevel();
		boolean worldNotNull = world != null;
		BlockState blockState = worldNotNull ? entity.getBlockState() : Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.SOUTH);
		ChestType chestType = blockState.hasProperty(ChestBlock.TYPE) ? blockState.getValue(ChestBlock.TYPE) : ChestType.SINGLE;
		Block block = blockState.getBlock();
		if (block instanceof AbstractChestBlock<?> abstractChestBlock)
		{
			boolean notSingle = chestType != ChestType.SINGLE;
			matrices.pushPose();
			float f = (blockState.getValue(ChestBlock.FACING)).toYRot();
			matrices.translate(0.5F, 0.5F, 0.5F);
			matrices.mulPose(Axis.YP.rotationDegrees(-f));
			matrices.translate(-0.5F, -0.5F, -0.5F);
			DoubleBlockCombiner.NeighborCombineResult<? extends ChestBlockEntity> propertySource;
			if (worldNotNull) {propertySource = abstractChestBlock.combine(blockState, world, entity.getBlockPos(), true);}
			else {propertySource = DoubleBlockCombiner.Combiner::acceptNone;}

			float openness = propertySource.apply(ChestBlock.opennessCombiner(entity)).get(tickDelta);
			openness = 1.0F - openness;
			openness = 1.0F - openness * openness * openness;
			int i = ((Int2IntFunction)propertySource.apply(new BrightnessCombiner())).applyAsInt(light);
			SpriteIdentifierAndRenderType materialAndRenderType = getSpriteIdentifierAndRenderType(entity, chestType);

			VertexConsumer vertexConsumer = materialAndRenderType.getSpriteIdentifier().buffer(vertexConsumers, materialAndRenderType.getRenderType());
			if (notSingle)
			{
				if (chestType == ChestType.LEFT) {this.render(matrices, vertexConsumer, this.doubleLeftLid, this.doubleLeftLock, this.doubleLeftBottom, openness, i, overlay);}
				else {this.render(matrices, vertexConsumer, this.doubleRightLid, this.doubleRightLock, this.doubleRightBottom, openness, i, overlay);}
			}
			else {this.render(matrices, vertexConsumer, this.lid, this.lock, this.bottom, openness, i, overlay);}

			matrices.popPose();
		}
	}

	protected static class SpriteIdentifierAndRenderType
	{
		private final Function<ResourceLocation, RenderType> renderType;
		private final Material spriteIdentifier;

		protected SpriteIdentifierAndRenderType(Material spriteIdentifier, Function<ResourceLocation, RenderType> renderType) {this.renderType = renderType; this.spriteIdentifier = spriteIdentifier;}

		public Function<ResourceLocation, RenderType> getRenderType() {return renderType;}
		public Material getSpriteIdentifier() {return spriteIdentifier;}
	}
}
