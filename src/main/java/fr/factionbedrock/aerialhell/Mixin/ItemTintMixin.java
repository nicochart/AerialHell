package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Color.Item.*;
import net.minecraft.client.render.item.tint.TintSourceTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TintSourceTypes.class)
public class ItemTintMixin
{
    @Inject(method = "bootstrap", at = @At("RETURN"), cancellable = true)
    private static void onBootstrapItemTint(CallbackInfo callbackInfo)
    {
        TintSourceTypes.ID_MAPPER.put(AerialHell.id("stellar_grass"), StellarGrassItemTint.MAP_CODEC);
        TintSourceTypes.ID_MAPPER.put(AerialHell.id("shadow_grass"), ShadowGrassItemTint.MAP_CODEC);
        TintSourceTypes.ID_MAPPER.put(AerialHell.id("glyph_block"), GlyphBlockItemTint.MAP_CODEC);
    }
}
