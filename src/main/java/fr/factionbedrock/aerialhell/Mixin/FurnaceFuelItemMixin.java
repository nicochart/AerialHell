package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.SequencedSet;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.FuelValues;

@Mixin(FuelValues.class)
public class FurnaceFuelItemMixin
{
    @Inject(method = "vanillaBurnTimes", at = @At("RETURN"), cancellable = true)
    private static void addCustomFuels(HolderLookup.Provider registries, FeatureFlagSet enabledFeatures, CallbackInfoReturnable<FuelValues> cir)
    {
        FuelValues fuelRegistry = cir.getReturnValue();
        SequencedSet<Item> fuelSet = fuelRegistry.fuelItems();
        Iterator<Item> iterator = fuelSet.iterator();
        FuelValues.Builder builder = new FuelValues.Builder(registries, enabledFeatures);
        while (iterator.hasNext())
        {
            Item item = iterator.next();
            builder.add(item, fuelRegistry.burnDuration(item.getDefaultInstance()));
        }
        ItemHelper.burnTimeMap.forEach(builder::add);
        cir.setReturnValue(builder.build());
    }
}
