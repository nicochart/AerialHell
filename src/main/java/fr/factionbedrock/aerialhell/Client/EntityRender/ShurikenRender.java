package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;

import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;

//see net.minecraft.client.renderer.entity ThrownItemRenderer
public class ShurikenRender<T extends AbstractShurikenEntity> extends EntityRenderer<T>
{
	private static final ResourceLocation IRON_SHURIKEN_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/item/iron_shuriken.png");
	private static final ResourceLocation GOLD_SHURIKEN_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/item/gold_shuriken.png");
	private static final ResourceLocation DIAMOND_SHURIKEN_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/item/diamond_shuriken.png");
	private static final ResourceLocation NETHERITE_SHURIKEN_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/item/netherite_shuriken.png");
	private static final ResourceLocation RUBY_SHURIKEN_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/item/ruby_shuriken.png");
	private static final ResourceLocation AZURITE_SHURIKEN_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/item/azurite_shuriken.png");
	private static final ResourceLocation MAGMATIC_GEL_SHURIKEN_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/item/magmatic_gel_shuriken.png");
	private static final ResourceLocation VOLUCITE_SHURIKEN_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/item/volucite_shuriken.png");
	private static final ResourceLocation OBSIDIAN_SHURIKEN_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/item/obsidian_shuriken.png");
	private static final ResourceLocation LUNATIC_CRYSTAL_SHURIKEN_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/item/lunatic_crystal_shuriken.png");
	private static final ResourceLocation ARSONIST_SHURIKEN_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/item/arsonist_shuriken.png");
	private static final ResourceLocation LIGHTNING_SHURIKEN_TEXTURE = ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/item/lightning_shuriken.png");
	
	public ShurikenRender(EntityRendererProvider.Context context)
	{
		super(context);
	}
	
	@Override
	public void render(T entityIn, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource bufferIn, int packedLightIn)
	{
		matrix.pushPose();
		
		entityIn.shurikenZRot -= 4;
		if (entityIn.shurikenZRot <= -360)
		{
			entityIn.shurikenZRot = 360;
		}
		matrix.mulPose(Axis.YP.rotationDegrees(entityIn.getYRot())); /*Vertical plane rotation*/
		matrix.mulPose(Axis.XP.rotationDegrees(- 90.0f - entityIn.xRotO)); /*Pointing to forward*/
		matrix.mulPose(Axis.ZP.rotationDegrees(entityIn.shurikenZRot)); /*Horizontal plane rotation*/

		Minecraft.getInstance().getItemRenderer().renderStatic(entityIn.getItem(), ItemDisplayContext.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrix, bufferIn, entityIn.level(), entityIn.getId());
		matrix.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrix, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(T entity)
	{
		if (entity instanceof IronShurikenEntity) {return IRON_SHURIKEN_TEXTURE;}
		else if (entity instanceof GoldShurikenEntity) {return GOLD_SHURIKEN_TEXTURE;}
		else if (entity instanceof VoluciteShurikenEntity) {return VOLUCITE_SHURIKEN_TEXTURE;}
		else if (entity instanceof ObsidianShurikenEntity) {return OBSIDIAN_SHURIKEN_TEXTURE;}
		else if (entity instanceof LunaticCrystalShurikenEntity) {return LUNATIC_CRYSTAL_SHURIKEN_TEXTURE;}
		else if (entity instanceof ArsonistShurikenEntity) {return ARSONIST_SHURIKEN_TEXTURE;}
		else if (entity instanceof DiamondShurikenEntity) {return DIAMOND_SHURIKEN_TEXTURE;}
		else if (entity instanceof NetheriteShurikenEntity) {return NETHERITE_SHURIKEN_TEXTURE;}
		else if (entity instanceof RubyShurikenEntity) {return RUBY_SHURIKEN_TEXTURE;}
		else if (entity instanceof AzuriteShurikenEntity) {return AZURITE_SHURIKEN_TEXTURE;}
		else if (entity instanceof MagmaticGelShurikenEntity) {return MAGMATIC_GEL_SHURIKEN_TEXTURE;}
		else {return LIGHTNING_SHURIKEN_TEXTURE;}
	}

}