package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.Registry.AerialHellFluids;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
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
            private static final Identifier UNDER = Identifier.fromNamespaceAndPath(MODID, "textures/misc/under_liquid_of_the_gods.png"),
                    STILL = Identifier.fromNamespaceAndPath(MODID, "block/liquid_of_the_gods_still"),
                    FLOW = Identifier.fromNamespaceAndPath(MODID, "block/liquid_of_the_gods_flow"),
                    OVERLAY = Identifier.fromNamespaceAndPath(MODID, "block/liquid_of_the_gods_overlay");

            @Override public Identifier getStillTexture() {return STILL;}
            @Override public Identifier getFlowingTexture() {return FLOW;}
            @Override public Identifier getOverlayTexture() {return OVERLAY;} //TODO render overlay (when in)
            @Override public Identifier getRenderOverlayTexture(Minecraft mc) {return UNDER;}
            @Override public int getTintColor() {return 0x310000;}
            @Override public int getTintColor(FluidState state, BlockAndTintGetter getter, BlockPos pos) {return 0x310000;}
        }, AerialHellFluids.LIQUID_OF_GODS_TYPE.value());
    }
}
