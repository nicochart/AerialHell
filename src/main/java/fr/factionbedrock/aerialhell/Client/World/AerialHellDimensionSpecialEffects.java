package fr.factionbedrock.aerialhell.Client.World;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

import javax.annotation.Nullable;

public class AerialHellDimensionSpecialEffects extends DimensionSpecialEffects
{
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
    public boolean renderSky(ClientLevel level, int ticks, float partialTick, Matrix4f modelViewMatrix, Camera camera, Matrix4f projectionMatrix, boolean isFoggy, Runnable setupFog)
    {
        return AerialHellDimensionSkyRenderer.render(level, projectionMatrix, modelViewMatrix, partialTick, camera, isFoggy, setupFog);
    }

    @Nullable @Override public float[] getSunriseColor(float daycycle, float partialTicks) {return null;}
    @Override public boolean isFoggyAt(int x, int y) {return false;}
}
