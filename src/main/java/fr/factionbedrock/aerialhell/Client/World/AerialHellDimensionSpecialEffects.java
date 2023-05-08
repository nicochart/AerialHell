package fr.factionbedrock.aerialhell.Client.World;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.ISkyRenderHandler;

import javax.annotation.Nullable;

public class AerialHellDimensionSpecialEffects extends DimensionSpecialEffects
{
	private ISkyRenderHandler aerialHellDimSkyRenderer;

    public AerialHellDimensionSpecialEffects(float cloudLevel, boolean skyEffect, DimensionSpecialEffects.SkyType skyType, boolean forceBrightLightmap, boolean hasEntityGroundLit)
    {
        super(cloudLevel, skyEffect, skyType, forceBrightLightmap, hasEntityGroundLit);
    }
    
    // Copy from DimensionSpecialEffects.Overworld
    @Override public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColor, float daylight)
    {
        return biomeFogColor.multiply(daylight * 0.94F + 0.06F, daylight * 0.94F + 0.06F, daylight * 0.91F + 0.09F);
    }

    @Override
    public boolean isFoggyAt(int x, int y) {return false;}

    @Nullable
    @Override
    public ISkyRenderHandler getSkyRenderHandler()
    {
        if (aerialHellDimSkyRenderer == null) {aerialHellDimSkyRenderer = new AerialHellDimensionSkyRenderer();}
        return aerialHellDimSkyRenderer;
    }
}
