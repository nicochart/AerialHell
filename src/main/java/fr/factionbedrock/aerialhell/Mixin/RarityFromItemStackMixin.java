package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellRarities;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class RarityFromItemStackMixin
{
    //fixes a game crash caused by getRarity() when an item with a AerialHellRarity is enchanted, and tooltip is displayed
    //editing AerialHellRarities indexes to 0, 1, 2 or 3 fixes it too. If another problem is discovered, remove this mixin and just edit indexes.
    @Inject(method = "getRarity", at = @At("HEAD"), cancellable = true)
    private void onGetRarity(CallbackInfoReturnable<Rarity> cir)
    {
        ItemStack itemstack = (ItemStack) (Object) this;
        if (itemstack.hasEnchantments())
        {
            Rarity rarity = itemstack.getOrDefault(DataComponentTypes.RARITY, Rarity.COMMON);
            if (AerialHellRarities.VALUES.contains(rarity))
            {
                cir.cancel();
                cir.setReturnValue(rarity);
            }
        }
    }
}
