package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Fluid.LiquidOfGodsFluid;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AerialHellFluids
{
    public static final FlowableFluid LIQUID_OF_THE_GODS_STILL = register("liquid_of_the_gods_source", new LiquidOfGodsFluid.Still());
    public static final FlowableFluid LIQUID_OF_THE_GODS_FLOWING = register("liquid_of_the_gods_flowing", new LiquidOfGodsFluid.Flowing());

    private static <T extends Fluid> T register(String id, T value)
    {
        return Registry.register(Registries.FLUID, id, value);
    }

    public static void load() {}

    public static void registerFluidsRender()
    {
        FluidRenderHandlerRegistry.INSTANCE.register(LIQUID_OF_THE_GODS_STILL, LIQUID_OF_THE_GODS_FLOWING, new SimpleFluidRenderHandler(
                AerialHell.id("block/liquid_of_the_gods_overlay"),
                AerialHell.id("block/liquid_of_the_gods_still"),
                AerialHell.id("block/liquid_of_the_gods_flow")
        ));
        //TODO : fog?
    }
}
