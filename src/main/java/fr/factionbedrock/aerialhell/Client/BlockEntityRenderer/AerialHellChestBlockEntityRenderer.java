package fr.factionbedrock.aerialhell.Client.BlockEntityRenderer;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellChestMaterials;
import fr.factionbedrock.aerialhell.Client.Registry.AerialHellChestVariants;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.client.model.object.chest.ChestModel;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.renderer.blockentity.state.ChestRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class AerialHellChestBlockEntityRenderer<T extends ChestBlockEntity> extends ChestRenderer<T>
{
	private final SpriteGetter sprites;
	public AerialHellChestBlockEntityRenderer(BlockEntityRendererProvider.Context context)
	{
		super(context);
		this.sprites = context.sprites();
	}

	protected SpriteId getSpriteId(ChestRenderState.ChestMaterialType variant, ChestType chestType)
	{
		if (variant == AerialHellChestVariants.AERIAL_TREE)
		{
			return chooseSprite(chestType, AerialHellChestMaterials.AERIAL_TREE_SINGLE, AerialHellChestMaterials.AERIAL_TREE_LEFT, AerialHellChestMaterials.AERIAL_TREE_RIGHT);
		}
		else if (variant == AerialHellChestVariants.COPPER_PINE)
		{
			return chooseSprite(chestType, AerialHellChestMaterials.COPPER_PINE_SINGLE, AerialHellChestMaterials.COPPER_PINE_LEFT, AerialHellChestMaterials.COPPER_PINE_RIGHT);
		}
		else if (variant == AerialHellChestVariants.LAPIS_ROBINIA)
		{
			return chooseSprite(chestType, AerialHellChestMaterials.LAPIS_ROBINIA_SINGLE, AerialHellChestMaterials.LAPIS_ROBINIA_LEFT, AerialHellChestMaterials.LAPIS_ROBINIA_RIGHT);
		}
		else if (variant == AerialHellChestVariants.STELLAR_JUNGLE_TREE)
		{
			return chooseSprite(chestType, AerialHellChestMaterials.STELLAR_JUNGLE_TREE_SINGLE, AerialHellChestMaterials.STELLAR_JUNGLE_TREE_LEFT, AerialHellChestMaterials.STELLAR_JUNGLE_TREE_RIGHT);
		}
		else if (variant == AerialHellChestVariants.GOLDEN_BEECH)
		{
			return chooseSprite(chestType, AerialHellChestMaterials.GOLDEN_BEECH_SINGLE, AerialHellChestMaterials.GOLDEN_BEECH_LEFT, AerialHellChestMaterials.GOLDEN_BEECH_RIGHT);
		}
		else if (variant == AerialHellChestVariants.SHADOW_PINE)
		{
			return chooseSprite(chestType, AerialHellChestMaterials.SHADOW_PINE_SINGLE, AerialHellChestMaterials.SHADOW_PINE_LEFT, AerialHellChestMaterials.SHADOW_PINE_RIGHT);
		}
		else if (variant == AerialHellChestVariants.GRAY_SHROOM)
		{
			return chooseSprite(chestType, AerialHellChestMaterials.GRAY_SHROOM_SINGLE, AerialHellChestMaterials.GRAY_SHROOM_LEFT, AerialHellChestMaterials.GRAY_SHROOM_RIGHT);
		}
		else if (variant == AerialHellChestVariants.SKY_CACTUS_FIBER)
		{
			return chooseSprite(chestType, AerialHellChestMaterials.SKY_CACTUS_FIBER_SINGLE, AerialHellChestMaterials.SKY_CACTUS_FIBER_LEFT, AerialHellChestMaterials.SKY_CACTUS_FIBER_RIGHT);
		}
		else if (variant == AerialHellChestVariants.GHOST_BOAT)
		{
			return chooseSprite(chestType, AerialHellChestMaterials.GHOST_BOAT_SINGLE, AerialHellChestMaterials.GHOST_BOAT_LEFT, AerialHellChestMaterials.GHOST_BOAT_RIGHT);
		}
		else if (variant == AerialHellChestVariants.MUD)
		{
			return chooseSprite(chestType, AerialHellChestMaterials.MUD_SINGLE, AerialHellChestMaterials.MUD_LEFT, AerialHellChestMaterials.MUD_RIGHT);
		}
		else if (variant == AerialHellChestVariants.LUNATIC)
		{
			return chooseSprite(chestType, AerialHellChestMaterials.LUNATIC_SINGLE, AerialHellChestMaterials.LUNATIC_LEFT, AerialHellChestMaterials.LUNATIC_RIGHT);
		}
		else if (variant == AerialHellChestVariants.GOLDEN_NETHER)
		{
			return chooseSprite(chestType, AerialHellChestMaterials.GOLDEN_NETHER_SINGLE, AerialHellChestMaterials.GOLDEN_NETHER_LEFT, AerialHellChestMaterials.GOLDEN_NETHER_RIGHT);
		}
		else if (variant == AerialHellChestVariants.SHADOW_CATACOMBS)
		{
			return chooseSprite(chestType, AerialHellChestMaterials.SHADOW_CATACOMBS_SINGLE, AerialHellChestMaterials.SHADOW_CATACOMBS_LEFT, AerialHellChestMaterials.SHADOW_CATACOMBS_RIGHT);
		}
		else if (variant == AerialHellChestVariants.VOLUCITE)
		{
			return chooseSprite(chestType, AerialHellChestMaterials.VOLUCITE_SINGLE, AerialHellChestMaterials.VOLUCITE_LEFT, AerialHellChestMaterials.VOLUCITE_RIGHT);
		}
		else //default material (should never happen)
		{
			return Sheets.CHEST_REGULAR.select(chestType);
		}
	}

	protected ChestRenderState.ChestMaterialType getVariant(T blockEntity)
	{
		Block block = blockEntity.getBlockState().getBlock();
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
		else {return ChestRenderState.ChestMaterialType.REGULAR;} //default variant (should never happen)
	}

	@Override public void extractRenderState(T blockEntity, ChestRenderState chestBlockEntityRenderState, float f, Vec3 vec3d, ModelFeatureRenderer.@Nullable CrumblingOverlay crumblingOverlayCommand)
	{
		super.extractRenderState(blockEntity, chestBlockEntityRenderState, f, vec3d, crumblingOverlayCommand);
		chestBlockEntityRenderState.material = this.getVariant(blockEntity);
	}

	private static SpriteId chooseSprite(ChestType type, Material materialSingle, Material materialLeft, Material materialRight)
	{
		return new SpriteId(Sheets.CHEST_SHEET, switch (type)
		{
			case LEFT -> materialLeft.sprite();
			case RIGHT -> materialRight.sprite();
			case SINGLE -> materialSingle.sprite();
		});
	}

	@Override public void submit(ChestRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState)
	{
		poseStack.pushPose();
		poseStack.mulPose(modelTransformation(renderState.facing));
		float openess = renderState.open;
		openess = 1.0F - openess;
		openess = 1.0F - openess * openess * openess;
		SpriteId spriteId = this.getSpriteId(renderState.material, renderState.type);
		ChestModel model = this.models.select(renderState.type);
		boolean isGhost = spriteId.texture() == AerialHellChestMaterials.GHOST_BOAT_LEFT.sprite() || spriteId.texture() == AerialHellChestMaterials.GHOST_BOAT_RIGHT.sprite() || spriteId.texture() == AerialHellChestMaterials.GHOST_BOAT_SINGLE.sprite();
		RenderType rendertype = isGhost ? spriteId.renderType(RenderTypes::entityTranslucent) : spriteId.renderType(RenderTypes::entityCutout);
		TextureAtlasSprite textureatlassprite = this.sprites.get(spriteId);
		submitNodeCollector.submitModel(model, openess, poseStack, rendertype, renderState.lightCoords, OverlayTexture.NO_OVERLAY, -1, textureatlassprite, 0, renderState.breakProgress);
		poseStack.popPose();
	}
}
