package fr.factionbedrock.aerialhell.Item.Material;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.crafting.Ingredient;

public class AerialHellToolMaterials
{
	public static final AerialHellToolMaterial SKY_WOOD = new AerialHellToolMaterial(0, 59, 2.0F, 0.0F, 15, () -> Ingredient.of(AerialHellTags.Items.AERIALHELL_PLANKS));
	public static final AerialHellToolMaterial STELLAR_STONE = new AerialHellToolMaterial(1, 131, 4.0F, 1.0F, 5, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_STELLAR_STONE_MATERIAL));
	public static final AerialHellToolMaterial RUBY = new AerialHellToolMaterial(2, 250, 6.0F, 2.0F, 14, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_RUBY_MATERIAL));
	public static final AerialHellToolMaterial AZURITE = new AerialHellToolMaterial(0, 110, 12.0F, 0.0F, 22, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_AZURITE_MATERIAL));
	public static final AerialHellToolMaterial MAGMATIC_GEL = new AerialHellToolMaterial(0, 32, 12.0F, 0.0F, 22, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_MAGMATIC_GEL_MATERIAL));
	public static final AerialHellToolMaterial OBSIDIAN = new AerialHellToolMaterial(3, 2031, 8.0F, 3.0F, 10, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_OBSIDIAN_MATERIAL));
	public static final AerialHellToolMaterial SHADOW = new AerialHellToolMaterial(3, 112, 8.0F, 3.0F, 15, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_SHADOW_MATERIAL));
	public static final AerialHellToolMaterial VOLUCITE = new AerialHellToolMaterial(4, 1620, 8.5F, 4.5F, 10, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_VOLUCITE_MATERIAL));
	public static final AerialHellToolMaterial HEAVY = new AerialHellToolMaterial(3, 1561, 8.0F, 6.0F, 10, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_HEAVY_MATERIAL))
			.addAttributeModifier(Attributes.MOVEMENT_SPEED, -0.30F, AttributeModifier.Operation.MULTIPLY_TOTAL);
	public static final AerialHellToolMaterial LUNATIC = new AerialHellToolMaterial(4, 1712, 8.0F, 4.0F, 15, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_LUNATIC_MATERIAL));
	public static final AerialHellToolMaterial BREAKER = new AerialHellToolMaterial(3, 742, 7.5F, 5.0F, 10, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_LUNATIC_MATERIAL));
	public static final AerialHellToolMaterial ARSONIST = new AerialHellToolMaterial(4, 2031, 9.0F, 5.5F, 15, () -> Ingredient.of(AerialHellTags.Items.REPAIRS_ARSONIST_MATERIAL));
}