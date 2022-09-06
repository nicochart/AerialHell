package fr.factionbedrock.aerialhell.Client.World;

import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.client.ISkyRenderHandler;

import javax.annotation.Nullable;

public class AerialHellDimensionRenderInfo extends DimensionRenderInfo
{
	private ISkyRenderHandler aerialHellDimSkyRenderer;

    public AerialHellDimensionRenderInfo(float cloudLevel, boolean skyEffect, FogType skyType, boolean forceBrightLightmap, boolean hasEntityGroundLit)
    {
        super(cloudLevel, skyEffect, skyType, forceBrightLightmap, hasEntityGroundLit);
    }
    
    // Copy from DimensionRenderInfo.Overworld
    @Override
    public Vector3d func_230494_a_(Vector3d biomeFogColor, float daylight)
    {
    	return biomeFogColor.mul((double)(daylight * 0.94F + 0.06F), (double)(daylight * 0.94F + 0.06F), (double)(daylight * 0.91F + 0.09F));
    }

    @Override
    public boolean func_230493_a_(int x, int y)
    { 
        return false; // true = nearFog
    }

    @Nullable
    @Override
    public ISkyRenderHandler getSkyRenderHandler()
    {
        if (aerialHellDimSkyRenderer == null) {aerialHellDimSkyRenderer = new AerialHellDimensionSkyRenderer();}
        return aerialHellDimSkyRenderer;
    }
}
