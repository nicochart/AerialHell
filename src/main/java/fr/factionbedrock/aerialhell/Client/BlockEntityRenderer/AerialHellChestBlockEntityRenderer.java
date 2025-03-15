package fr.factionbedrock.aerialhell.Client.BlockEntityRenderer;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellChestMaterials;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.block.*;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer;
import net.minecraft.client.render.block.entity.LightmapCoordinatesRetriever;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;

import java.util.function.Function;

public class AerialHellChestBlockEntityRenderer<T extends ChestBlockEntity> extends ChestBlockEntityRenderer<T>
{
	public AerialHellChestBlockEntityRenderer(BlockEntityRendererFactory.Context context) {super(context);}

	protected SpriteIdentifierAndRenderType getSpriteIdentifierAndRenderType(T blockEntity, ChestType chestType)
	{
		Block block = blockEntity.getCachedState().getBlock();
		if (block == AerialHellBlocks.AERIAL_TREE_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.AERIAL_TREE_SINGLE, AerialHellChestMaterials.AERIAL_TREE_LEFT, AerialHellChestMaterials.AERIAL_TREE_RIGHT), RenderLayer::getEntityCutout);
		}
		else if (block == AerialHellBlocks.COPPER_PINE_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.COPPER_PINE_SINGLE, AerialHellChestMaterials.COPPER_PINE_LEFT, AerialHellChestMaterials.COPPER_PINE_RIGHT), RenderLayer::getEntityCutout);
		}
		else if (block == AerialHellBlocks.LAPIS_ROBINIA_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.LAPIS_ROBINIA_SINGLE, AerialHellChestMaterials.LAPIS_ROBINIA_LEFT, AerialHellChestMaterials.LAPIS_ROBINIA_RIGHT), RenderLayer::getEntityCutout);
		}
		else if (block == AerialHellBlocks.STELLAR_JUNGLE_TREE_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.STELLAR_JUNGLE_TREE_SINGLE, AerialHellChestMaterials.STELLAR_JUNGLE_TREE_LEFT, AerialHellChestMaterials.STELLAR_JUNGLE_TREE_RIGHT), RenderLayer::getEntityCutout);
		}
		else if (block == AerialHellBlocks.GOLDEN_BEECH_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.GOLDEN_BEECH_SINGLE, AerialHellChestMaterials.GOLDEN_BEECH_LEFT, AerialHellChestMaterials.GOLDEN_BEECH_RIGHT), RenderLayer::getEntityCutout);
		}
		else if (block == AerialHellBlocks.SHADOW_PINE_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.SHADOW_PINE_SINGLE, AerialHellChestMaterials.SHADOW_PINE_LEFT, AerialHellChestMaterials.SHADOW_PINE_RIGHT), RenderLayer::getEntityCutout);
		}
		else if (block == AerialHellBlocks.GRAY_SHROOM_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.GRAY_SHROOM_SINGLE, AerialHellChestMaterials.GRAY_SHROOM_LEFT, AerialHellChestMaterials.GRAY_SHROOM_RIGHT), RenderLayer::getEntityCutout);
		}
		else if (block == AerialHellBlocks.SKY_CACTUS_FIBER_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.SKY_CACTUS_FIBER_SINGLE, AerialHellChestMaterials.SKY_CACTUS_FIBER_LEFT, AerialHellChestMaterials.SKY_CACTUS_FIBER_RIGHT), RenderLayer::getEntityCutout);
		}
		else if (block == AerialHellBlocks.GHOST_BOAT_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.GHOST_BOAT_SINGLE, AerialHellChestMaterials.GHOST_BOAT_LEFT, AerialHellChestMaterials.GHOST_BOAT_RIGHT), RenderLayer::getEntityTranslucent);
		}
		else if (block == AerialHellBlocks.MUD_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.MUD_SINGLE, AerialHellChestMaterials.MUD_LEFT, AerialHellChestMaterials.MUD_RIGHT), RenderLayer::getEntityCutout);
		}
		else if (block == AerialHellBlocks.LUNATIC_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.LUNATIC_SINGLE, AerialHellChestMaterials.LUNATIC_LEFT, AerialHellChestMaterials.LUNATIC_RIGHT), RenderLayer::getEntityCutout);
		}
		else if (block == AerialHellBlocks.GOLDEN_NETHER_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.GOLDEN_NETHER_SINGLE, AerialHellChestMaterials.GOLDEN_NETHER_LEFT, AerialHellChestMaterials.GOLDEN_NETHER_RIGHT), RenderLayer::getEntityCutout);
		}
		else if (block == AerialHellBlocks.SHADOW_CATACOMBS_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.SHADOW_CATACOMBS_SINGLE, AerialHellChestMaterials.SHADOW_CATACOMBS_LEFT, AerialHellChestMaterials.SHADOW_CATACOMBS_RIGHT), RenderLayer::getEntityCutout);
		}
		else if (block == AerialHellBlocks.VOLUCITE_CHEST)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, AerialHellChestMaterials.VOLUCITE_SINGLE, AerialHellChestMaterials.VOLUCITE_LEFT, AerialHellChestMaterials.VOLUCITE_RIGHT), RenderLayer::getEntityCutout);
		}
		else //default material (should never happen)
		{
			return new SpriteIdentifierAndRenderType(getSpriteIdentifier(chestType, TexturedRenderLayers.NORMAL, TexturedRenderLayers.NORMAL_LEFT, TexturedRenderLayers.NORMAL_RIGHT), RenderLayer::getEntityCutout);
		}
	}

	private static SpriteIdentifier getSpriteIdentifier(ChestType type, SpriteIdentifier single, SpriteIdentifier left, SpriteIdentifier right)
	{
		return switch (type)
		{
			case LEFT -> left;
			case RIGHT -> right;
			case SINGLE -> single;
		};
	}

	@Override public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay)
	{
		World world = entity.getWorld();
		boolean worldNotNull = world != null;
		BlockState blockState = worldNotNull ? entity.getCachedState() : Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.SOUTH);
		ChestType chestType = blockState.contains(ChestBlock.CHEST_TYPE) ? blockState.get(ChestBlock.CHEST_TYPE) : ChestType.SINGLE;
		Block block = blockState.getBlock();
		if (block instanceof AbstractChestBlock<?> abstractChestBlock)
		{
			boolean notSingle = chestType != ChestType.SINGLE;
			matrices.push();
			float f = (blockState.get(ChestBlock.FACING)).getPositiveHorizontalDegrees();
			matrices.translate(0.5F, 0.5F, 0.5F);
			matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-f));
			matrices.translate(-0.5F, -0.5F, -0.5F);
			DoubleBlockProperties.PropertySource<? extends ChestBlockEntity> propertySource;
			if (worldNotNull) {propertySource = abstractChestBlock.getBlockEntitySource(blockState, world, entity.getPos(), true);}
			else {propertySource = DoubleBlockProperties.PropertyRetriever::getFallback;}

			float openness = propertySource.apply(ChestBlock.getAnimationProgressRetriever(entity)).get(tickDelta);
			openness = 1.0F - openness;
			openness = 1.0F - openness * openness * openness;
			int i = ((Int2IntFunction)propertySource.apply(new LightmapCoordinatesRetriever())).applyAsInt(light);
			SpriteIdentifierAndRenderType materialAndRenderType = getSpriteIdentifierAndRenderType(entity, chestType);

			VertexConsumer vertexConsumer = materialAndRenderType.getSpriteIdentifier().getVertexConsumer(vertexConsumers, materialAndRenderType.getRenderType());
			if (notSingle)
			{
				if (chestType == ChestType.LEFT) {this.render(matrices, vertexConsumer, this.doubleChestLeft, openness, i, overlay);}
				else {this.render(matrices, vertexConsumer, this.doubleChestRight, openness, i, overlay);}
			}
			else {this.render(matrices, vertexConsumer, this.singleChest, openness, i, overlay);}

			matrices.pop();
		}
	}

	protected static class SpriteIdentifierAndRenderType
	{
		private final Function<Identifier, RenderLayer> renderType;
		private final SpriteIdentifier spriteIdentifier;

		protected SpriteIdentifierAndRenderType(SpriteIdentifier spriteIdentifier, Function<Identifier, RenderLayer> renderType) {this.renderType = renderType; this.spriteIdentifier = spriteIdentifier;}

		public Function<Identifier, RenderLayer> getRenderType() {return renderType;}
		public SpriteIdentifier getSpriteIdentifier() {return spriteIdentifier;}
	}
}
