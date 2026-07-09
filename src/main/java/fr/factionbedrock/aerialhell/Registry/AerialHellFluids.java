package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Fluid.LiquidOfGodsFluid;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;

public class AerialHellFluids
{
    public static final FlowingFluid LIQUID_OF_THE_GODS_STILL = register("liquid_of_the_gods_source", new LiquidOfGodsFluid.Still());
    public static final FlowingFluid LIQUID_OF_THE_GODS_FLOWING = register("liquid_of_the_gods_flowing", new LiquidOfGodsFluid.Flowing());

    private static <T extends Fluid> T register(String id, T value)
    {
        return Registry.register(BuiltInRegistries.FLUID, AerialHell.id(id), value);
    }

    public static void load() {}
}
