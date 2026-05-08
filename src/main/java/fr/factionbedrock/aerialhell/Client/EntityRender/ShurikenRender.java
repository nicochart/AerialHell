package fr.factionbedrock.aerialhell.Client.EntityRender;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.EntityRender.State.ShurikenRenderState;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.*;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;

//see net.minecraft.client.render.entity.FlyingItemEntityRenderer
public class ShurikenRender<T extends ShurikenEntity> extends EntityRenderer<T, ShurikenRenderState>
{
	private final ItemModelResolver itemModelResolver;

	private static final Identifier IRON_SHURIKEN_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/item/iron_shuriken.png");
	private static final Identifier GOLD_SHURIKEN_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/item/gold_shuriken.png");
	private static final Identifier DIAMOND_SHURIKEN_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/item/diamond_shuriken.png");
	private static final Identifier NETHERITE_SHURIKEN_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/item/netherite_shuriken.png");
	private static final Identifier RUBY_SHURIKEN_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/item/ruby_shuriken.png");
	private static final Identifier AZURITE_SHURIKEN_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/item/azurite_shuriken.png");
	private static final Identifier MAGMATIC_GEL_SHURIKEN_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/item/magmatic_gel_shuriken.png");
	private static final Identifier VOLUCITE_SHURIKEN_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/item/volucite_shuriken.png");
	private static final Identifier OBSIDIAN_SHURIKEN_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/item/obsidian_shuriken.png");
	private static final Identifier LUNATIC_CRYSTAL_SHURIKEN_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/item/lunatic_crystal_shuriken.png");
	private static final Identifier ARSONIST_SHURIKEN_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/item/arsonist_shuriken.png");
	private static final Identifier LIGHTNING_SHURIKEN_TEXTURE = Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/item/lightning_shuriken.png");

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
		renderState.pitchO = entity.xRotO;
		renderState.shurikenZRot = entity.shurikenZRot;
		this.itemModelResolver.updateForNonLiving(renderState.item, this.getItem(entity).getDefaultInstance(), ItemDisplayContext.GROUND, entity);
	}

	@Override public void submit(ShurikenRenderState renderState, PoseStack matrices, SubmitNodeCollector queue, CameraRenderState cameraState)
	{
		matrices.pushPose();

		renderState.shurikenZRot -= 4;
		if (renderState.shurikenZRot <= -360)
		{
			renderState.shurikenZRot = 360;
		}
		matrices.mulPose(Axis.YP.rotationDegrees(renderState.YRot)); /*Vertical plane rotation*/
		matrices.mulPose(Axis.XP.rotationDegrees(- 90.0f - renderState.pitchO)); /*Pointing to forward*/
		matrices.mulPose(Axis.ZP.rotationDegrees(renderState.shurikenZRot)); /*Horizontal plane rotation*/

		renderState.item.submit(matrices, queue, renderState.lightCoords, OverlayTexture.NO_OVERLAY, renderState.outlineColor);
		matrices.popPose();
		super.submit(renderState, matrices, queue, cameraState);
	}

	public Identifier getTextureLocation(T entity)
	{
		if (entity.getType().is(HolderSet.direct(AerialHellEntities.IRON_SHURIKEN.builtInRegistryHolder()))) {return IRON_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.GOLD_SHURIKEN.builtInRegistryHolder()))) {return GOLD_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.VOLUCITE_SHURIKEN.builtInRegistryHolder()))) {return VOLUCITE_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.OBSIDIAN_SHURIKEN.builtInRegistryHolder()))) {return OBSIDIAN_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.builtInRegistryHolder()))) {return LUNATIC_CRYSTAL_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.ARSONIST_SHURIKEN.builtInRegistryHolder()))) {return ARSONIST_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.DIAMOND_SHURIKEN.builtInRegistryHolder()))) {return DIAMOND_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.NETHERITE_SHURIKEN.builtInRegistryHolder()))) {return NETHERITE_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.RUBY_SHURIKEN.builtInRegistryHolder()))) {return RUBY_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.AZURITE_SHURIKEN.builtInRegistryHolder()))) {return AZURITE_SHURIKEN_TEXTURE;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.MAGMATIC_GEL_SHURIKEN.builtInRegistryHolder()))) {return MAGMATIC_GEL_SHURIKEN_TEXTURE;}
		else {return LIGHTNING_SHURIKEN_TEXTURE;}
	}

	public Item getItem(T entity)
	{
		if (entity.getType().is(HolderSet.direct(AerialHellEntities.IRON_SHURIKEN.builtInRegistryHolder()))) {return AerialHellItems.IRON_SHURIKEN;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.GOLD_SHURIKEN.builtInRegistryHolder()))) {return AerialHellItems.GOLD_SHURIKEN;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.VOLUCITE_SHURIKEN.builtInRegistryHolder()))) {return AerialHellItems.VOLUCITE_SHURIKEN;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.OBSIDIAN_SHURIKEN.builtInRegistryHolder()))) {return AerialHellItems.OBSIDIAN_SHURIKEN;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.LUNATIC_CRYSTAL_SHURIKEN.builtInRegistryHolder()))) {return AerialHellItems.LUNATIC_CRYSTAL_SHURIKEN;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.ARSONIST_SHURIKEN.builtInRegistryHolder()))) {return AerialHellItems.ARSONIST_SHURIKEN;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.DIAMOND_SHURIKEN.builtInRegistryHolder()))) {return AerialHellItems.DIAMOND_SHURIKEN;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.NETHERITE_SHURIKEN.builtInRegistryHolder()))) {return AerialHellItems.NETHERITE_SHURIKEN;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.RUBY_SHURIKEN.builtInRegistryHolder()))) {return AerialHellItems.RUBY_SHURIKEN;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.AZURITE_SHURIKEN.builtInRegistryHolder()))) {return AerialHellItems.AZURITE_SHURIKEN;}
		else if (entity.getType().is(HolderSet.direct(AerialHellEntities.MAGMATIC_GEL_SHURIKEN.builtInRegistryHolder()))) {return AerialHellItems.MAGMATIC_GEL_SHURIKEN;}
		else {return AerialHellItems.LIGHTNING_SHURIKEN;}
	}
}