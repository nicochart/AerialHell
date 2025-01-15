package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.LightProjectileRenderState;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ShurikenRenderState;
import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;

//see net.minecraft.client.renderer.entity ThrownItemRenderer
public class ShurikenRender<T extends AbstractShurikenEntity> extends EntityRenderer<T, ShurikenRenderState>
{
	private final ItemModelResolver itemModelResolver;

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
		this.itemModelResolver = context.getItemModelResolver();
	}

	@Override public ShurikenRenderState createRenderState() {return new ShurikenRenderState();}

	@Override public void extractRenderState(T entity, ShurikenRenderState renderState, float partialTick)
	{
		super.extractRenderState(entity, renderState, partialTick);
		renderState.texture = getTextureLocation(entity);
		renderState.YRot = entity.getYRot();
		renderState.xRotO = entity.xRotO;
		renderState.shurikenZRot = entity.shurikenZRot;
		this.itemModelResolver.updateForNonLiving(renderState.item, entity.getItem(), ItemDisplayContext.GROUND, entity);
	}

	@Override public void render(ShurikenRenderState renderState, PoseStack poseStack, MultiBufferSource buffer, int packedLight)
	{
		poseStack.pushPose();

		renderState.shurikenZRot -= 4;
		if (renderState.shurikenZRot <= -360)
		{
			renderState.shurikenZRot = 360;
		}
		poseStack.mulPose(Axis.YP.rotationDegrees(renderState.YRot)); /*Vertical plane rotation*/
		poseStack.mulPose(Axis.XP.rotationDegrees(- 90.0f - renderState.xRotO)); /*Pointing to forward*/
		poseStack.mulPose(Axis.ZP.rotationDegrees(renderState.shurikenZRot)); /*Horizontal plane rotation*/

		renderState.item.render(poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY);
		poseStack.popPose();
		super.render(renderState, poseStack, buffer, packedLight);
	}

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