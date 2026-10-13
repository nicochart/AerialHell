package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import net.minecraft.client.renderer.CubeMap;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CubeMap.class)
public class CubeMapTextureMixin
{
    @ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true)
    private static ResourceLocation redirectPanorama(ResourceLocation originalLocation)
    {
        if (originalLocation != null && originalLocation.getNamespace().equals("minecraft") && originalLocation.getPath().equals("textures/gui/title/background/panorama"))
        {
            if (LoadedConfigParams.USE_CUSTOM_MAIN_MENU_BACKGROUND)
            {
                return ResourceLocation.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/title/background/panorama");
            }
        }
        return originalLocation;
    }
}
