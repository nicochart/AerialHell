package fr.factionbedrock.aerialhell.Mixin;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import net.minecraft.client.renderer.texture.CubeMapTexture;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(CubeMapTexture.class)
public class CubeMapTextureMixin
{
    @ModifyVariable(method = "loadContents", at = @At("STORE"), name = "location")
    private Identifier redirectPanorama(Identifier originalLocation)
    {
        if (originalLocation.getNamespace().equals("minecraft") && originalLocation.getPath().equals("textures/gui/title/background/panorama"))
        {
            if (LoadedConfigParams.USE_CUSTOM_MAIN_MENU_BACKGROUND)
            {
                return Identifier.fromNamespaceAndPath(AerialHell.MODID, "textures/gui/title/background/panorama");
            }
        }
        return originalLocation;
    }
}
