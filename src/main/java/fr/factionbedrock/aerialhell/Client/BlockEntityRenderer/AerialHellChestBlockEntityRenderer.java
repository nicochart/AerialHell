package fr.factionbedrock.aerialhell.Client.BlockEntityRenderer;

import fr.factionbedrock.aerialhell.Client.Registry.AerialHellChestMaterials;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellChestVariants;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.*;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.ChestBlockEntityRenderer;
import net.minecraft.client.render.block.entity.state.ChestBlockEntityRenderState;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteHolder;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.jspecify.annotations.Nullable;

public class AerialHellChestBlockEntityRenderer<T extends ChestBlockEntity> extends ChestBlockEntityRenderer<T>
{
	private final SpriteHolder materials;
	public AerialHellChestBlockEntityRenderer(BlockEntityRendererFactory.Context context)
	{
		super(context);
		this.materials = context.spriteHolder();
	}

	protected SpriteIdentifier getMaterial(ChestBlockEntityRenderState.Variant variant, ChestType chestType)
	{
		if (variant == AerialHellChestVariants.AERIAL_TREE)
		{
			return getSpriteIdentifier(chestType, AerialHellChestMaterials.AERIAL_TREE_SINGLE, AerialHellChestMaterials.AERIAL_TREE_LEFT, AerialHellChestMaterials.AERIAL_TREE_RIGHT);
		}
		else if (variant == AerialHellChestVariants.COPPER_PINE)
		{
			return getSpriteIdentifier(chestType, AerialHellChestMaterials.COPPER_PINE_SINGLE, AerialHellChestMaterials.COPPER_PINE_LEFT, AerialHellChestMaterials.COPPER_PINE_RIGHT);
		}
		else if (variant == AerialHellChestVariants.LAPIS_ROBINIA)
		{
			return getSpriteIdentifier(chestType, AerialHellChestMaterials.LAPIS_ROBINIA_SINGLE, AerialHellChestMaterials.LAPIS_ROBINIA_LEFT, AerialHellChestMaterials.LAPIS_ROBINIA_RIGHT);
		}
		else if (variant == AerialHellChestVariants.STELLAR_JUNGLE_TREE)
		{
			return getSpriteIdentifier(chestType, AerialHellChestMaterials.STELLAR_JUNGLE_TREE_SINGLE, AerialHellChestMaterials.STELLAR_JUNGLE_TREE_LEFT, AerialHellChestMaterials.STELLAR_JUNGLE_TREE_RIGHT);
		}
		else if (variant == AerialHellChestVariants.GOLDEN_BEECH)
		{
			return getSpriteIdentifier(chestType, AerialHellChestMaterials.GOLDEN_BEECH_SINGLE, AerialHellChestMaterials.GOLDEN_BEECH_LEFT, AerialHellChestMaterials.GOLDEN_BEECH_RIGHT);
		}
		else if (variant == AerialHellChestVariants.SHADOW_PINE)
		{
			return getSpriteIdentifier(chestType, AerialHellChestMaterials.SHADOW_PINE_SINGLE, AerialHellChestMaterials.SHADOW_PINE_LEFT, AerialHellChestMaterials.SHADOW_PINE_RIGHT);
		}
		else if (variant == AerialHellChestVariants.GRAY_SHROOM)
		{
			return getSpriteIdentifier(chestType, AerialHellChestMaterials.GRAY_SHROOM_SINGLE, AerialHellChestMaterials.GRAY_SHROOM_LEFT, AerialHellChestMaterials.GRAY_SHROOM_RIGHT);
		}
		else if (variant == AerialHellChestVariants.SKY_CACTUS_FIBER)
		{
			return getSpriteIdentifier(chestType, AerialHellChestMaterials.SKY_CACTUS_FIBER_SINGLE, AerialHellChestMaterials.SKY_CACTUS_FIBER_LEFT, AerialHellChestMaterials.SKY_CACTUS_FIBER_RIGHT);
		}
		else if (variant == AerialHellChestVariants.GHOST_BOAT)
		{
			return getSpriteIdentifier(chestType, AerialHellChestMaterials.GHOST_BOAT_SINGLE, AerialHellChestMaterials.GHOST_BOAT_LEFT, AerialHellChestMaterials.GHOST_BOAT_RIGHT);
		}
		else if (variant == AerialHellChestVariants.MUD)
		{
			return getSpriteIdentifier(chestType, AerialHellChestMaterials.MUD_SINGLE, AerialHellChestMaterials.MUD_LEFT, AerialHellChestMaterials.MUD_RIGHT);
		}
		else if (variant == AerialHellChestVariants.LUNATIC)
		{
			return getSpriteIdentifier(chestType, AerialHellChestMaterials.LUNATIC_SINGLE, AerialHellChestMaterials.LUNATIC_LEFT, AerialHellChestMaterials.LUNATIC_RIGHT);
		}
		else if (variant == AerialHellChestVariants.GOLDEN_NETHER)
		{
			return getSpriteIdentifier(chestType, AerialHellChestMaterials.GOLDEN_NETHER_SINGLE, AerialHellChestMaterials.GOLDEN_NETHER_LEFT, AerialHellChestMaterials.GOLDEN_NETHER_RIGHT);
		}
		else if (variant == AerialHellChestVariants.SHADOW_CATACOMBS)
		{
			return getSpriteIdentifier(chestType, AerialHellChestMaterials.SHADOW_CATACOMBS_SINGLE, AerialHellChestMaterials.SHADOW_CATACOMBS_LEFT, AerialHellChestMaterials.SHADOW_CATACOMBS_RIGHT);
		}
		else if (variant == AerialHellChestVariants.VOLUCITE)
		{
			return getSpriteIdentifier(chestType, AerialHellChestMaterials.VOLUCITE_SINGLE, AerialHellChestMaterials.VOLUCITE_LEFT, AerialHellChestMaterials.VOLUCITE_RIGHT);
		}
		else //default spriteIdentifier (should never happen)
		{
			return getSpriteIdentifier(chestType, TexturedRenderLayers.CHEST, TexturedRenderLayers.CHEST_LEFT, TexturedRenderLayers.CHEST_RIGHT);
		}
	}

	protected ChestBlockEntityRenderState.Variant getVariant(T blockEntity)
	{
		Block block = blockEntity.getCachedState().getBlock();
		if (block == AerialHellBlocks.AERIAL_TREE_CHEST) {return AerialHellChestVariants.AERIAL_TREE;}
		else if (block == AerialHellBlocks.COPPER_PINE_CHEST) {return AerialHellChestVariants.COPPER_PINE;}
		else if (block == AerialHellBlocks.LAPIS_ROBINIA_CHEST) {return AerialHellChestVariants.LAPIS_ROBINIA;}
		else if (block == AerialHellBlocks.STELLAR_JUNGLE_TREE_CHEST) {return AerialHellChestVariants.STELLAR_JUNGLE_TREE;}
		else if (block == AerialHellBlocks.GOLDEN_BEECH_CHEST) {return AerialHellChestVariants.GOLDEN_BEECH;}
		else if (block == AerialHellBlocks.SHADOW_PINE_CHEST) {return AerialHellChestVariants.SHADOW_PINE;}
		else if (block == AerialHellBlocks.GRAY_SHROOM_CHEST) {return AerialHellChestVariants.GRAY_SHROOM;}
		else if (block == AerialHellBlocks.SKY_CACTUS_FIBER_CHEST) {return AerialHellChestVariants.SKY_CACTUS_FIBER;}
		else if (block == AerialHellBlocks.GHOST_BOAT_CHEST) {return AerialHellChestVariants.GHOST_BOAT;}
		else if (block == AerialHellBlocks.MUD_CHEST) {return AerialHellChestVariants.MUD;}
		else if (block == AerialHellBlocks.LUNATIC_CHEST) {return AerialHellChestVariants.LUNATIC;}
		else if (block == AerialHellBlocks.GOLDEN_NETHER_CHEST) {return AerialHellChestVariants.GOLDEN_NETHER;}
		else if (block == AerialHellBlocks.SHADOW_CATACOMBS_CHEST) {return AerialHellChestVariants.SHADOW_CATACOMBS;}
		else if (block == AerialHellBlocks.VOLUCITE_CHEST) {return AerialHellChestVariants.VOLUCITE;}
		else {return ChestBlockEntityRenderState.Variant.REGULAR;} //default variant (should never happen)
	}

	@Override public void updateRenderState(T blockEntity, ChestBlockEntityRenderState chestBlockEntityRenderState, float f, Vec3d vec3d, ModelCommandRenderer.@Nullable CrumblingOverlayCommand crumblingOverlayCommand)
	{
		super.updateRenderState(blockEntity, chestBlockEntityRenderState, f, vec3d, crumblingOverlayCommand);
		chestBlockEntityRenderState.variant = this.getVariant(blockEntity);
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

	@Override public void render(ChestBlockEntityRenderState chestBlockEntityRenderState, MatrixStack matrixStack, OrderedRenderCommandQueue orderedRenderCommandQueue, CameraRenderState cameraRenderState)
	{
		matrixStack.push();
		matrixStack.translate(0.5F, 0.5F, 0.5F);
		matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-chestBlockEntityRenderState.yaw));
		matrixStack.translate(-0.5F, -0.5F, -0.5F);
		float openness = chestBlockEntityRenderState.lidAnimationProgress;
		openness = 1.0F - openness;
		openness = 1.0F - openness * openness * openness;
		SpriteIdentifier spriteIdentifier = this.getMaterial(chestBlockEntityRenderState.variant, chestBlockEntityRenderState.chestType);

		boolean isGhost = spriteIdentifier == AerialHellChestMaterials.GHOST_BOAT_LEFT || spriteIdentifier == AerialHellChestMaterials.GHOST_BOAT_RIGHT || spriteIdentifier == AerialHellChestMaterials.GHOST_BOAT_SINGLE;
		RenderLayer renderLayer = isGhost ? spriteIdentifier.getRenderLayer(RenderLayers::entityTranslucent) : spriteIdentifier.getRenderLayer(RenderLayers::entityCutout);
		Sprite sprite = this.materials.getSprite(spriteIdentifier);
		if (chestBlockEntityRenderState.chestType != ChestType.SINGLE)
		{
			if (chestBlockEntityRenderState.chestType == ChestType.LEFT) {orderedRenderCommandQueue.submitModel(this.doubleChestLeft, openness, matrixStack, renderLayer, chestBlockEntityRenderState.lightmapCoordinates, OverlayTexture.DEFAULT_UV, -1, sprite, 0, chestBlockEntityRenderState.crumblingOverlay);}
			else {orderedRenderCommandQueue.submitModel(this.doubleChestRight, openness, matrixStack, renderLayer, chestBlockEntityRenderState.lightmapCoordinates, OverlayTexture.DEFAULT_UV, -1, sprite, 0, chestBlockEntityRenderState.crumblingOverlay);}
		}
		else {orderedRenderCommandQueue.submitModel(this.singleChest, openness, matrixStack, renderLayer, chestBlockEntityRenderState.lightmapCoordinates, OverlayTexture.DEFAULT_UV, -1, sprite, 0, chestBlockEntityRenderState.crumblingOverlay);}

		matrixStack.pop();
	}
}
