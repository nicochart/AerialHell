package fr.factionbedrock.aerialhell.Client.EntityRender;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Entity.Projectile.AbstractShurikenEntity;
import fr.factionbedrock.aerialhell.Entity.Projectile.Shuriken.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;

//see net.minecraft.client.renderer.entity ThrownItemRenderer
public class ShurikenRender<T extends AbstractShurikenEntity> extends EntityRenderer<T>
{
	private static final Identifier IRON_SHURIKEN_TEXTURE = Identifier.of(AerialHell.MODID, "textures/item/iron_shuriken.png");
	private static final Identifier GOLD_SHURIKEN_TEXTURE = Identifier.of(AerialHell.MODID, "textures/item/gold_shuriken.png");
	private static final Identifier DIAMOND_SHURIKEN_TEXTURE = Identifier.of(AerialHell.MODID, "textures/item/diamond_shuriken.png");
	private static final Identifier NETHERITE_SHURIKEN_TEXTURE = Identifier.of(AerialHell.MODID, "textures/item/netherite_shuriken.png");
	private static final Identifier RUBY_SHURIKEN_TEXTURE = Identifier.of(AerialHell.MODID, "textures/item/ruby_shuriken.png");
	private static final Identifier AZURITE_SHURIKEN_TEXTURE = Identifier.of(AerialHell.MODID, "textures/item/azurite_shuriken.png");
	private static final Identifier MAGMATIC_GEL_SHURIKEN_TEXTURE = Identifier.of(AerialHell.MODID, "textures/item/magmatic_gel_shuriken.png");
	private static final Identifier VOLUCITE_SHURIKEN_TEXTURE = Identifier.of(AerialHell.MODID, "textures/item/volucite_shuriken.png");
	private static final Identifier OBSIDIAN_SHURIKEN_TEXTURE = Identifier.of(AerialHell.MODID, "textures/item/obsidian_shuriken.png");
	private static final Identifier LUNATIC_CRYSTAL_SHURIKEN_TEXTURE = Identifier.of(AerialHell.MODID, "textures/item/lunatic_crystal_shuriken.png");
	private static final Identifier ARSONIST_SHURIKEN_TEXTURE = Identifier.of(AerialHell.MODID, "textures/item/arsonist_shuriken.png");
	private static final Identifier LIGHTNING_SHURIKEN_TEXTURE = Identifier.of(AerialHell.MODID, "textures/item/lightning_shuriken.png");
	
	public ShurikenRender(EntityRendererFactory.Context context)
	{
		super(context);
	}
	
	@Override
	public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrix, VertexConsumerProvider bufferIn, int packedLightIn)
	{
		matrix.push();
		
		entityIn.shurikenZRot -= 4;
		if (entityIn.shurikenZRot <= -360)
		{
			entityIn.shurikenZRot = 360;
		}
		matrix.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entityIn.getYaw())); /*Vertical plane rotation*/
		matrix.multiply(RotationAxis.POSITIVE_X.rotationDegrees(- 90.0f - entityIn.prevPitch)); /*Pointing to forward*/
		matrix.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(entityIn.shurikenZRot)); /*Horizontal plane rotation*/

		MinecraftClient.getInstance().getItemRenderer().renderItem(entityIn.getStack(), ModelTransformationMode.GROUND, packedLightIn, OverlayTexture.DEFAULT_UV, matrix, bufferIn, entityIn.getWorld(), entityIn.getId());
		matrix.pop();
		super.render(entityIn, entityYaw, partialTicks, matrix, bufferIn, packedLightIn);
	}

	@Override
	public Identifier getTexture(T entity)
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