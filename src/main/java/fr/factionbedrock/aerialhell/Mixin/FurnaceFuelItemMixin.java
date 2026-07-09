package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;

@Mixin(AbstractFurnaceBlockEntity.class)
public class FurnaceFuelItemMixin
{
    @Inject(method = "getFuel", at = @At("RETURN"), cancellable = true)
    private static void addCustomFuels(CallbackInfoReturnable<Map<Item, Integer>> cir)
    {
        Map<Item, Integer> fuelMap = cir.getReturnValue();
        fuelMap.putAll(ItemHelper.burnTimeMap);
        cir.setReturnValue(fuelMap);
    }
}
