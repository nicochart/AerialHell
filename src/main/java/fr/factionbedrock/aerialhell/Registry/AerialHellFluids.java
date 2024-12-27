package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Fluid.LiquidOfGodsFluid;
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
        return Registry.register(Registries.FLUID, AerialHell.id(id), value);
    }

    public static void load() {}
}
