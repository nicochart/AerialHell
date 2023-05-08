package fr.factionbedrock.aerialhell.Integration.JEI;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Recipe.FreezingRecipe;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;

public class FreezingRecipeCategory implements IRecipeCategory<FreezingRecipe>
{
	private static final int OUTPUT_SLOT = 0;
	private static final int INPUT_SLOT = 1;

	public final static ResourceLocation UID = new ResourceLocation(AerialHell.MODID, "freezing");
	public final static ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/gui/container/freezer.png");
	
	private final IDrawable background;
	private final IDrawable icon;
	private final IDrawableStatic freezing;
	
	public FreezingRecipeCategory(IGuiHelper helper)
	{
		this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 83);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(AerialHellBlocksAndItems.FREEZER.get()));
		this.freezing = helper.createDrawable(TEXTURE, 176, 0, 13, 17);
	}

	@Override public IDrawable getBackground() {return this.background;}
	@Override public IDrawable getIcon() {return this.icon;}
	@Override public ResourceLocation getUid() {return UID;}
	@Override public Class<? extends FreezingRecipe> getRecipeClass() {return FreezingRecipe.class;}
	@Override public Component getTitle() {return new TranslatableComponent("block.aerialhell.oscillator");}

	@Override
	public void setIngredients(FreezingRecipe recipe, IIngredients ingredients)
	{
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
	}

	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull FreezingRecipe recipe, @Nonnull IIngredients ingredients)
	{
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(OUTPUT_SLOT, true, 115, 34);
		guiItemStacks.init(INPUT_SLOT, true, 55, 16);

		guiItemStacks.set(ingredients);
	}

	@Override
	public void draw(FreezingRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY)
	{
		this.freezing.draw(poseStack, 56, 36);
	}
}
