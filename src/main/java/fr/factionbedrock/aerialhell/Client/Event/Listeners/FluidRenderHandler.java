package fr.factionbedrock.aerialhell.Client.Event.Listeners;

import fr.factionbedrock.aerialhell.Registry.AerialHellFluids;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterFluidModelsEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.fluid.FluidTintSource;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.jspecify.annotations.Nullable;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class FluidRenderHandler
{
    private static final Identifier UNDER = Identifier.fromNamespaceAndPath(MODID, "textures/misc/under_liquid_of_the_gods.png"),
            STILL = Identifier.fromNamespaceAndPath(MODID, "block/liquid_of_the_gods_still"),
            FLOW = Identifier.fromNamespaceAndPath(MODID, "block/liquid_of_the_gods_flow"),
            OVERLAY = Identifier.fromNamespaceAndPath(MODID, "block/liquid_of_the_gods_overlay");

    //see net.neoforged.neoforge.client.ClientNeoForgeMod onRegisterFluidModels method
    public static void handleFluidRender(RegisterFluidModelsEvent event)
    {
        AerialHellFluids.LIQUID_OF_GODS_TYPE.asOptional().ifPresent((var1) ->
        {
            Fluid stillFluid = AerialHellFluids.LIQUID_OF_THE_GODS_SOURCE.value();
            Fluid flowingFluid = AerialHellFluids.LIQUID_OF_THE_GODS_FLOWING.value();
            event.register(new FluidModel.Unbaked(new Material(STILL), new Material(FLOW), new Material(OVERLAY), null), stillFluid, flowingFluid);
        });
    }

    //see net.neoforged.neoforge.client.ClientNeoForgeMod onRegisterClientExtensions method
    public static void handleFluidOverlayRender(RegisterClientExtensionsEvent event)
    {
        event.registerFluidType(new IClientFluidTypeExtensions()
        {
            @Override public @Nullable Identifier getRenderOverlayTexture(Minecraft mc) {return UNDER;}
        }, AerialHellFluids.LIQUID_OF_GODS_TYPE.value());
    }
}
