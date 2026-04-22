package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.Registry.AerialHellFluids;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import org.jspecify.annotations.Nullable;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class FluidRenderHandler
{
    //see net.neoforged.neoforge.client.ClientNeoForgeMod onRegisterClientExtensions method
    public static void handleFluidRender(RegisterClientExtensionsEvent event)
    {
        //TODO where is texture set ?
        event.registerFluidType(new IClientFluidTypeExtensions()
        {
            private static final Identifier UNDER = Identifier.fromNamespaceAndPath(MODID, "textures/misc/under_liquid_of_the_gods.png"),
                    STILL = Identifier.fromNamespaceAndPath(MODID, "block/liquid_of_the_gods_still"),
                    FLOW = Identifier.fromNamespaceAndPath(MODID, "block/liquid_of_the_gods_flow"),
                    OVERLAY = Identifier.fromNamespaceAndPath(MODID, "block/liquid_of_the_gods_overlay");

            @Override public @Nullable Identifier getRenderOverlayTexture(Minecraft mc) {return UNDER;}
        }, AerialHellFluids.LIQUID_OF_GODS_TYPE.value());
    }
}
