package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.item.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.resource.featuretoggle.FeatureSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.SequencedSet;

@Mixin(FuelRegistry.class)
public class FurnaceFuelItemMixin
{
    @Inject(method = "createDefault", at = @At("RETURN"), cancellable = true)
    private static void addCustomFuels(RegistryWrapper.WrapperLookup registries, FeatureSet enabledFeatures, CallbackInfoReturnable<FuelRegistry> cir)
    {
        FuelRegistry fuelRegistry = cir.getReturnValue();
        SequencedSet<Item> fuelSet = fuelRegistry.getFuelItems();
        Iterator<Item> iterator = fuelSet.iterator();
        FuelRegistry.Builder builder = new FuelRegistry.Builder(registries, enabledFeatures);
        while (iterator.hasNext())
        {
            Item item = iterator.next();
            builder.add(item, fuelRegistry.getFuelTicks(item.getDefaultStack()));
        }
        ItemHelper.burnTimeMap.forEach(builder::add);
        cir.setReturnValue(builder.build());
    }
}
