package fr.factionbedrock.aerialhell.Recipe;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes;
import fr.factionbedrock.aerialhell.Registry.AerialHellRecipes.RecipeTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;

//vanilla copy : net.minecraft.world.item.crafting.SmeltingRecipe
public class OscillatingRecipe extends AbstractCookingRecipe
{
	public static final MapCodec<OscillatingRecipe> MAP_CODEC = cookingMapCodec(OscillatingRecipe::new, 200);
	public static final StreamCodec<RegistryFriendlyByteBuf, OscillatingRecipe> STREAM_CODEC = cookingStreamCodec(OscillatingRecipe::new);
	public static final RecipeSerializer<OscillatingRecipe> SERIALIZER = new RecipeSerializer(MAP_CODEC, STREAM_CODEC);

	public OscillatingRecipe(Recipe.CommonInfo commonInfo, AbstractCookingRecipe.CookingBookInfo bookInfo, Ingredient ingredient, ItemStackTemplate result, float experience, int cookingTime)
	{
		super(commonInfo, bookInfo, ingredient, result, experience, cookingTime);
	}

	@Override protected Item furnaceIcon() {return AerialHellBlocks.OSCILLATOR.asItem();}

	@Override public RecipeSerializer<OscillatingRecipe> getSerializer() {return AerialHellRecipes.OSCILLATING_SERIALIZER.get();}

	@Override public RecipeType<OscillatingRecipe> getType() {return RecipeTypes.OSCILLATING.get();}

	@Override public RecipeBookCategory recipeBookCategory() {return RecipeBookCategories.FURNACE_BLOCKS;}
}
