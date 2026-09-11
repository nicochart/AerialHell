package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;

import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.*;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;

//see net.minecraft.client.renderer.entity ThrownItemRenderer
public class ShurikenRender<T extends ShurikenEntity> extends EntityRenderer<T>
{
	private static final ResourceLocation IRON_SHURIKEN_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/iron_shuriken.png");
	private static final ResourceLocation GOLD_SHURIKEN_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/gold_shuriken.png");
	private static final ResourceLocation DIAMOND_SHURIKEN_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/diamond_shuriken.png");
	private static final ResourceLocation NETHERITE_SHURIKEN_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/netherite_shuriken.png");
	private static final ResourceLocation RUBY_SHURIKEN_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/ruby_shuriken.png");
	private static final ResourceLocation AZURITE_SHURIKEN_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/azurite_shuriken.png");
	private static final ResourceLocation MAGMATIC_GEL_SHURIKEN_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/magmatic_gel_shuriken.png");
	private static final ResourceLocation VOLUCITE_SHURIKEN_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/volucite_shuriken.png");
	private static final ResourceLocation OBSIDIAN_SHURIKEN_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/obsidian_shuriken.png");
	private static final ResourceLocation LUNATIC_CRYSTAL_SHURIKEN_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/lunatic_crystal_shuriken.png");
	private static final ResourceLocation ARSONIST_SHURIKEN_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/arsonist_shuriken.png");
	private static final ResourceLocation LIGHTNING_SHURIKEN_TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/item/lightning_shuriken.png");
	
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

		Minecraft.getInstance().getItemRenderer().renderStatic(this.getItem(entityIn).getDefaultInstance(), ItemDisplayContext.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrix, bufferIn, entityIn.level(), entityIn.getId());
		matrix.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrix, bufferIn, packedLightIn);
	}

	@Override public ResourceLocation getTextureLocation(T entity)
	{
		if (entity.getType() == AerialHellEntities.IRON_SHURIKEN.get()) {return IRON_SHURIKEN_TEXTURE;}
		else if (entity.getType() == AerialHellEntities.GOLD_SHURIKEN.get()) {return GOLD_SHURIKEN_TEXTURE;}
		else if (entity.getType() == AerialHellEntities.VOLUCITE_SHURIKEN.get()) {return VOLUCITE_SHURIKEN_TEXTURE;}
		else if (entity.getType() == AerialHellEntities.OBSIDIAN_SHURIKEN.get()) {return OBSIDIAN_SHURIKEN_TEXTURE;}
		else if (entity.getType() == AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.get()) {return LUNATIC_CRYSTAL_SHURIKEN_TEXTURE;}
		else if (entity.getType() == AerialHellEntities.ARSONIST_SHURIKEN.get()) {return ARSONIST_SHURIKEN_TEXTURE;}
		else if (entity.getType() == AerialHellEntities.DIAMOND_SHURIKEN.get()) {return DIAMOND_SHURIKEN_TEXTURE;}
		else if (entity.getType() == AerialHellEntities.NETHERITE_SHURIKEN.get()) {return NETHERITE_SHURIKEN_TEXTURE;}
		else if (entity.getType() == AerialHellEntities.RUBY_SHURIKEN.get()) {return RUBY_SHURIKEN_TEXTURE;}
		else if (entity.getType() == AerialHellEntities.AZURITE_SHURIKEN.get()) {return AZURITE_SHURIKEN_TEXTURE;}
		else if (entity.getType() == AerialHellEntities.MAGMATIC_GEL_SHURIKEN.get()) {return MAGMATIC_GEL_SHURIKEN_TEXTURE;}
		else {return LIGHTNING_SHURIKEN_TEXTURE;}
	}

	public Item getItem(T entity)
	{
		if (entity.getType() == AerialHellEntities.IRON_SHURIKEN.get()) {return AerialHellBlocksAndItems.IRON_SHURIKEN.get();}
		else if (entity.getType() == AerialHellEntities.GOLD_SHURIKEN.get()) {return AerialHellBlocksAndItems.GOLD_SHURIKEN.get();}
		else if (entity.getType() == AerialHellEntities.VOLUCITE_SHURIKEN.get()) {return AerialHellBlocksAndItems.VOLUCITE_SHURIKEN.get();}
		else if (entity.getType() == AerialHellEntities.OBSIDIAN_SHURIKEN.get()) {return AerialHellBlocksAndItems.OBSIDIAN_SHURIKEN.get();}
		else if (entity.getType() == AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.get()) {return AerialHellBlocksAndItems.LUNATIC_CRYSTAL_SHURIKEN.get();}
		else if (entity.getType() == AerialHellEntities.ARSONIST_SHURIKEN.get()) {return AerialHellBlocksAndItems.ARSONIST_SHURIKEN.get();}
		else if (entity.getType() == AerialHellEntities.DIAMOND_SHURIKEN.get()) {return AerialHellBlocksAndItems.DIAMOND_SHURIKEN.get();}
		else if (entity.getType() == AerialHellEntities.NETHERITE_SHURIKEN.get()) {return AerialHellBlocksAndItems.NETHERITE_SHURIKEN.get();}
		else if (entity.getType() == AerialHellEntities.RUBY_SHURIKEN.get()) {return AerialHellBlocksAndItems.RUBY_SHURIKEN.get();}
		else if (entity.getType() == AerialHellEntities.AZURITE_SHURIKEN.get()) {return AerialHellBlocksAndItems.AZURITE_SHURIKEN.get();}
		else if (entity.getType() == AerialHellEntities.MAGMATIC_GEL_SHURIKEN.get()) {return AerialHellBlocksAndItems.MAGMATIC_GEL_SHURIKEN.get();}
		else {return AerialHellBlocksAndItems.LIGHTNING_SHURIKEN.get();}
	}
}