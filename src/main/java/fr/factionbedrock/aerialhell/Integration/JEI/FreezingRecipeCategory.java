package fr.factionbedrock.aerialhell.Integration.JEI;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Recipe.FreezingRecipe;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;

public class FreezingRecipeCategory implements IRecipeCategory<FreezingRecipe>
{
	public final static ResourceLocation UID = new ResourceLocation(AerialHell.MODID, "freezing");
	public final static ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/gui/container/freezer.png");
	
	private final IDrawable background;
	private final IDrawable icon;
	private final IDrawableStatic freezing;
	
	public FreezingRecipeCategory(IGuiHelper helper)
	{
		this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 83);
		this.icon = helper.createDrawableIngredient(new ItemStack(AerialHellBlocksAndItems.FREEZER.get()));
		this.freezing = helper.createDrawable(TEXTURE, 176, 0, 13, 17);
	}
	
	@Override public ResourceLocation getUid() {return UID;}
	@Override public IDrawable getBackground() {return this.background;}
	@Override public IDrawable getIcon() {return this.icon;}
	@Override public Class<? extends FreezingRecipe> getRecipeClass() {return FreezingRecipe.class;}
	@Override public String getTitle() {return AerialHellBlocksAndItems.FREEZER.get().getTranslatedName().getString();}

	@Override
	public void setIngredients(FreezingRecipe recipe, IIngredients ingredients)
	{
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, FreezingRecipe recipe, IIngredients ingredients)
	{
		recipeLayout.getItemStacks().init(0, true, 55, 16);
		recipeLayout.getItemStacks().init(1, true, 55, 52);
		
		recipeLayout.getItemStacks().init(2, false, 115, 34);
		recipeLayout.getItemStacks().set(ingredients);
	}
	
	@Override
    public void draw(FreezingRecipe recipe, PoseStack matrixStack, double mouseX, double mouseY)
	{
        this.freezing.draw(matrixStack, 56, 36);
    }
}
