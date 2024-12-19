package fr.factionbedrock.aerialhell.Integration.JEI;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Recipe.OscillatingRecipe;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
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
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class OscillatingRecipeCategory implements IRecipeCategory<OscillatingRecipe>
{
	public static final RecipeType<OscillatingRecipe> OSCILLATING = RecipeType.create(AerialHell.MODID, "oscillating", OscillatingRecipe.class);
	public final static Identifier TEXTURE = AerialHell.id("textures/gui/container/oscillator.png");

	private final IDrawable background;
	private final IDrawable icon;
	private final IDrawableStatic oscillating;

	public OscillatingRecipeCategory(IGuiHelper helper)
	{
		this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 83);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(AerialHellBlocks.OSCILLATOR));
		this.oscillating = helper.createDrawable(TEXTURE, 176, 0, 13, 17);
	}

	@Override public IDrawable getBackground() {return this.background;}
	@Override public IDrawable getIcon() {return this.icon;}
	@Override public MutableText getTitle() {return Text.translatable("block.aerialhell.oscillator");}
	@Override public RecipeType<OscillatingRecipe> getRecipeType() {return OSCILLATING;}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, OscillatingRecipe recipe, IFocusGroup focuses)
	{
		builder.addSlot(RecipeIngredientRole.INPUT, 56, 17).addItemStack(recipe.getIngredients().get(0).getMatchingStacks()[0]);
		builder.addSlot(RecipeIngredientRole.CATALYST, 56, 53).addItemStack(new ItemStack(AerialHellItems.FLUORITE));
		if (MinecraftClient.getInstance().world != null)
		{
			builder.addSlot(RecipeIngredientRole.OUTPUT, 115, 34).addItemStack(recipe.getResult(MinecraftClient.getInstance().world.getRegistryManager()));
		}
	}

	@Override
	public void draw(OscillatingRecipe recipe, IRecipeSlotsView recipeSlotsView, DrawContext drawContext, double mouseX, double mouseY)
	{
		this.oscillating.draw(drawContext, 56, 36);
	}
}