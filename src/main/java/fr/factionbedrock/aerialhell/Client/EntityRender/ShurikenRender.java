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

		Minecraft.getInstance().getItemRenderer().renderStatic(this.getItem(entityIn).getDefaultInstance(), ItemDisplayContext.GROUND, packedLightIn, OverlayTexture.NO_OVERLAY, matrix, bufferIn, entityIn.level(), entityIn.getId());
		matrix.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrix, bufferIn, packedLightIn);
	}

	@Override public ResourceLocation getTextureLocation(T entity)
	{
		if (entity.getType().is(HolderSet.direct(AerialHellEntities.IRON_SHURIKEN))) {return IRON_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.GOLD_SHURIKEN))) {return GOLD_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.VOLUCITE_SHURIKEN))) {return VOLUCITE_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.OBSIDIAN_SHURIKEN))) {return OBSIDIAN_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN))) {return LUNATIC_CRYSTAL_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.ARSONIST_SHURIKEN))) {return ARSONIST_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.DIAMOND_SHURIKEN))) {return DIAMOND_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.NETHERITE_SHURIKEN))) {return NETHERITE_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.RUBY_SHURIKEN))) {return RUBY_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.AZURITE_SHURIKEN))) {return AZURITE_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.MAGMATIC_GEL_SHURIKEN))) {return MAGMATIC_GEL_SHURIKEN_TEXTURE;}
		else {return LIGHTNING_SHURIKEN_TEXTURE;}
	}

	public Item getItem(T entity)
	{
		if (entity.getType().is(HolderSet.direct(AerialHellEntities.IRON_SHURIKEN))) {return AerialHellBlocksAndItems.IRON_SHURIKEN.get();}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.GOLD_SHURIKEN))) {return AerialHellBlocksAndItems.GOLD_SHURIKEN.get();}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.VOLUCITE_SHURIKEN))) {return AerialHellBlocksAndItems.VOLUCITE_SHURIKEN.get();}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.OBSIDIAN_SHURIKEN))) {return AerialHellBlocksAndItems.OBSIDIAN_SHURIKEN.get();}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN))) {return AerialHellBlocksAndItems.LUNATIC_CRYSTAL_SHURIKEN.get();}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.ARSONIST_SHURIKEN))) {return AerialHellBlocksAndItems.ARSONIST_SHURIKEN.get();}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.DIAMOND_SHURIKEN))) {return AerialHellBlocksAndItems.DIAMOND_SHURIKEN.get();}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.NETHERITE_SHURIKEN))) {return AerialHellBlocksAndItems.NETHERITE_SHURIKEN.get();}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.RUBY_SHURIKEN))) {return AerialHellBlocksAndItems.RUBY_SHURIKEN.get();}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.AZURITE_SHURIKEN))) {return AerialHellBlocksAndItems.AZURITE_SHURIKEN.get();}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.MAGMATIC_GEL_SHURIKEN))) {return AerialHellBlocksAndItems.MAGMATIC_GEL_SHURIKEN.get();}
		else {return AerialHellBlocksAndItems.LIGHTNING_SHURIKEN.get();}
	}
}