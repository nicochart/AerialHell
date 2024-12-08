package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Recipe.CustomBrewingRecipe;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingRecipeRegistry.class)
public class CustomBrewingRecipeMixin
{
    @Inject(method = "registerDefaults", at = @At("HEAD"), cancellable = true)
    private static void addCustomBrewingRecipe(BrewingRecipeRegistry.Builder builder, CallbackInfo ci)
    {
        CustomBrewingRecipe.addBrewingRecipes(builder);
    }
}
