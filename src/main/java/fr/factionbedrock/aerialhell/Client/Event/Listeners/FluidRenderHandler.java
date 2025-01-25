package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.Registry.AerialHellFluids;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class FluidRenderHandler
{
    //see net.neoforged.neoforge.client.ClientNeoForgeMod onRegisterClientExtensions method
    public static void handleFluidRender(RegisterClientExtensionsEvent event)
    {
        event.registerFluidType(new IClientFluidTypeExtensions()
        {
            private static final ResourceLocation UNDER = ResourceLocation.fromNamespaceAndPath(MODID, "textures/misc/under_liquid_of_the_gods.png"),
                    STILL = ResourceLocation.fromNamespaceAndPath(MODID, "block/liquid_of_the_gods_still"),
                    FLOW = ResourceLocation.fromNamespaceAndPath(MODID, "block/liquid_of_the_gods_flow"),
                    OVERLAY = ResourceLocation.fromNamespaceAndPath(MODID, "block/liquid_of_the_gods_overlay");

            @Override public ResourceLocation getStillTexture() {return STILL;}
            @Override public ResourceLocation getFlowingTexture() {return FLOW;}
            @Override public ResourceLocation getOverlayTexture() {return OVERLAY;} //TODO render overlay (when in)
            @Override public ResourceLocation getRenderOverlayTexture(Minecraft mc) {return UNDER;}
            @Override public int getTintColor() {return 0x310000;}
            @Override public int getTintColor(FluidState state, BlockAndTintGetter getter, BlockPos pos) {return 0x310000;}
        }, AerialHellFluids.LIQUID_OF_GODS_TYPE.value());
    }
}
