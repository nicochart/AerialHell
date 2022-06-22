package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.matrix.MatrixStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.AbtractThrowingKnifeEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.ThrowingKnife.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;

import java.util.Objects;

public class ThrowingKnifeRender<T extends AbtractThrowingKnifeEntity> extends EntityRenderer<T>
{
	private static final ResourceLocation IRON_THROWING_KNIFE_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/iron_throwing_knife.png");
	private static final ResourceLocation GOLD_THROWING_KNIFE_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/gold_throwing_knife.png");
	private static final ResourceLocation DIAMOND_THROWING_KNIFE_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/diamond_throwing_knife.png");
	private static final ResourceLocation NETHERITE_THROWING_KNIFE_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/netherite_throwing_knife.png");
	private static final ResourceLocation RUBY_THROWING_KNIFE_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/ruby_throwing_knife.png");
	private static final ResourceLocation AZURITE_THROWING_KNIFE_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/azurite_throwing_knife.png");
	private static final ResourceLocation MAGMATIC_GEL_THROWING_KNIFE_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/magmatic_gel_throwing_knife.png");
	private static final ResourceLocation VOLUCITE_THROWING_KNIFE_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/volucite_throwing_knife.png");
	private static final ResourceLocation LIGHTNING_THROWING_KNIFE_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/lightning_throwing_knife.png");
	
	public ThrowingKnifeRender(EntityRendererManager renderManager)
	{
		super(renderManager);
	}
	
	@Override
	public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer bufferIn, int packedLightIn)
	{
		matrix.push();
		
		entityIn.throwingKnifeZRot -= 4;
		if (entityIn.throwingKnifeZRot <= -360)
		{
			entityIn.throwingKnifeZRot = 360;
		}
		matrix.rotate(Vector3f.YP.rotationDegrees(entityIn.rotationYaw)); /*Vertical plane rotation*/
		matrix.rotate(Vector3f.XP.rotationDegrees(- 90.0f - entityIn.prevRotationPitch)); /*Pointing to forward*/
		matrix.rotate(Vector3f.ZP.rotationDegrees(entityIn.throwingKnifeZRot)); /*Horizontal plane rotation*/
		
		Minecraft.getInstance().getItemRenderer().renderItem(Objects.requireNonNull(((IRendersAsItem)entityIn).getItem()), ItemCameraTransforms.TransformType.GUI, packedLightIn, OverlayTexture.NO_OVERLAY, matrix, bufferIn);
		matrix.pop();
		super.render(entityIn, entityYaw, partialTicks, matrix, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity)
	{
		if (entity instanceof IronThrowingKnifeEntity)
		{
			return IRON_THROWING_KNIFE_TEXTURE;
		}
		else if (entity instanceof GoldThrowingKnifeEntity)
		{
			return GOLD_THROWING_KNIFE_TEXTURE;
		}
		else if (entity instanceof VoluciteThrowingKnifeEntity)
		{
			return VOLUCITE_THROWING_KNIFE_TEXTURE;
		}
		else if (entity instanceof DiamondThrowingKnifeEntity)
		{
			return DIAMOND_THROWING_KNIFE_TEXTURE;
		}
		else if (entity instanceof NetheriteThrowingKnifeEntity)
		{
			return NETHERITE_THROWING_KNIFE_TEXTURE;
		}
		else if (entity instanceof RubyThrowingKnifeEntity)
		{
			return RUBY_THROWING_KNIFE_TEXTURE;
		}
		else if (entity instanceof AzuriteThrowingKnifeEntity)
		{
			return AZURITE_THROWING_KNIFE_TEXTURE;
		}
		else if (entity instanceof MagmaticGelThrowingKnifeEntity)
		{
			return MAGMATIC_GEL_THROWING_KNIFE_TEXTURE;
		}
		else
		{
			return LIGHTNING_THROWING_KNIFE_TEXTURE;
		}
	}

}