package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Recipe.CustomBrewingRecipe;
import net.minecraft.world.item.alchemy.PotionBrewing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PotionBrewing.class)
public class CustomBrewingRecipeMixin
{
    @Inject(method = "addVanillaMixes", at = @At("HEAD"), cancellable = true)
    private static void addCustomBrewingRecipe(PotionBrewing.Builder builder, CallbackInfo ci)
    {
        CustomBrewingRecipe.addBrewingRecipes(builder);
    }
}
