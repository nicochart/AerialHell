package fr.factionbedrock.aerialhell.Integration.JEI;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Recipe.OscillatingRecipe;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;

public class OscillatingRecipeCategory implements IRecipeCategory<OscillatingRecipe>
{
	public static final RecipeType<OscillatingRecipe> OSCILLATING = RecipeType.create(AerialHell.MODID, "oscillating", OscillatingRecipe.class);

	public final static ResourceLocation UID = new ResourceLocation(AerialHell.MODID, "oscillating");
	public final static ResourceLocation TEXTURE = new ResourceLocation(AerialHell.MODID, "textures/gui/container/oscillator.png");

	private final IDrawable background;
	private final IDrawable icon;
	private final IDrawableStatic oscillating;

	public OscillatingRecipeCategory(IGuiHelper helper)
	{
		this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 83);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(AerialHellBlocksAndItems.OSCILLATOR.get()));
		this.oscillating = helper.createDrawable(TEXTURE, 176, 0, 13, 17);
	}

	@Override public IDrawable getBackground() {return this.background;}
	@Override public IDrawable getIcon() {return this.icon;}
	@Override public Component getTitle() {return Component.translatable("block.aerialhell.oscillator");}
	@Override public RecipeType<OscillatingRecipe> getRecipeType() {return OSCILLATING;}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, OscillatingRecipe recipe, IFocusGroup focuses)
	{
		builder.addSlot(RecipeIngredientRole.INPUT, 55, 16).addItemStack(recipe.getIngredients().get(0).getItems()[0]);
		builder.addSlot(RecipeIngredientRole.CATALYST, 55, 52).addItemStack(new ItemStack(AerialHellBlocksAndItems.FLUORITE.get()));
		if (Minecraft.getInstance().level != null)
		{
			builder.addSlot(RecipeIngredientRole.OUTPUT, 115, 34).addItemStack(recipe.getResultItem(Minecraft.getInstance().level.registryAccess()));
		}
	}

	@Override
	public void draw(OscillatingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY)
	{
		this.oscillating.draw(guiGraphics, 56, 36);
	}
}
