package fr.factionbedrock.aerialhell.Recipe;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;

public class FreezingRecipe extends AbstractCookingRecipe
{
	public static final MapCodec<FreezingRecipe> MAP_CODEC = cookingMapCodec(FreezingRecipe::new, 200);
	public static final StreamCodec<RegistryFriendlyByteBuf, FreezingRecipe> STREAM_CODEC = cookingStreamCodec(FreezingRecipe::new);
	public static final RecipeSerializer<FreezingRecipe> SERIALIZER = new RecipeSerializer(MAP_CODEC, STREAM_CODEC);

	public FreezingRecipe(Recipe.CommonInfo commonInfo, AbstractCookingRecipe.CookingBookInfo bookInfo, Ingredient ingredient, ItemStackTemplate result, float experience, int cookingTime)
	{
		super(commonInfo, bookInfo, ingredient, result, experience, cookingTime);
	}

	@Override protected Item furnaceIcon() {return AerialHellBlocks.FREEZER.asItem();}

	@Override public RecipeSerializer<FreezingRecipe> getSerializer() {return AerialHellRecipes.FREEZING_SERIALIZER;}

	@Override public RecipeType<FreezingRecipe> getType() {return RecipeTypes.FREEZING;}

	@Override public RecipeBookCategory recipeBookCategory() {return RecipeBookCategories.FURNACE_BLOCKS;}
}
