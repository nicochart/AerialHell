package fr.factionbedrock.aerialhell.Integration.JEI;

import com.mojang.blaze3d.matrix.MatrixStack;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Recipe.VibrationRecipe;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class VibrationRecipeCategory implements IRecipeCategory<VibrationRecipe>
{
	public final static ResourceLocation UID = new ResourceLocation(AerialHell.MODID, "vibration");
	public final static ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/gui/container/vibrator.png");
	
	private final IDrawable background;
	private final IDrawable icon;
	private final IDrawableStatic vibration;
	
	public VibrationRecipeCategory(IGuiHelper helper)
	{
		this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 83);
		this.icon = helper.createDrawableIngredient(new ItemStack(AerialHellBlocksAndItems.VIBRATOR.get()));
		this.vibration = helper.createDrawable(TEXTURE, 176, 0, 13, 17);
	}
	
	@Override public ResourceLocation getUid() {return UID;}
	@Override public IDrawable getBackground() {return this.background;}
	@Override public IDrawable getIcon() {return this.icon;}
	@Override public Class<? extends VibrationRecipe> getRecipeClass() {return VibrationRecipe.class;}
	@Override public String getTitle() {return AerialHellBlocksAndItems.VIBRATOR.get().getTranslatedName().getString();}

	@Override
	public void setIngredients(VibrationRecipe recipe, IIngredients ingredients)
	{
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, VibrationRecipe recipe, IIngredients ingredients)
	{
		recipeLayout.getItemStacks().init(0, true, 55, 16);
		recipeLayout.getItemStacks().init(1, true, 55, 52);
		
		recipeLayout.getItemStacks().init(2, false, 115, 34);
		recipeLayout.getItemStacks().set(ingredients);
	}
	
	@Override
    public void draw(VibrationRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY)
	{
        this.vibration.draw(matrixStack, 56, 36);
    }
}
