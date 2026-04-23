package fr.factionbedrock.aerialhell.Client;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory.*;
import fr.factionbedrock.aerialhell.Registry.AerialHellFluids;
import fr.factionbedrock.aerialhell.Registry.AerialHellMenuTypes;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderingRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.material.Fluid;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class AerialHellRendering
{
    private static final Identifier UNDER = Identifier.fromNamespaceAndPath(MODID, "textures/misc/under_liquid_of_the_gods.png"),
            STILL = Identifier.fromNamespaceAndPath(MODID, "block/liquid_of_the_gods_still"),
            FLOW = Identifier.fromNamespaceAndPath(MODID, "block/liquid_of_the_gods_flow"),
            OVERLAY = Identifier.fromNamespaceAndPath(MODID, "block/liquid_of_the_gods_overlay");

	public static void registerScreensMenus()
	{
        MenuScreens.register(AerialHellMenuTypes.OSCILLATOR, OscillatorScreen::new);
        MenuScreens.register(AerialHellMenuTypes.FREEZER, FreezerScreen::new);
        MenuScreens.register(AerialHellMenuTypes.STELLAR_FURNACE, StellarFurnaceScreen::new);
        MenuScreens.register(AerialHellMenuTypes.REACTOR, ReactorScreen::new);
    }

    public static void registerFluidsRender()
    {
        FluidRenderingRegistry.register(AerialHellFluids.LIQUID_OF_THE_GODS_STILL, AerialHellFluids.LIQUID_OF_THE_GODS_FLOWING, new FluidModel.Unbaked(new Material(STILL), new Material(FLOW), new Material(OVERLAY), null));
        //TODO : fog?
    }
}
