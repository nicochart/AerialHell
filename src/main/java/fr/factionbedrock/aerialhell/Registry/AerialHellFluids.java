package fr.factionbedrock.aerialhell.Registry;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class AerialHellFluids
{
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, MODID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, MODID);

    //see net.neoforged.neoforge.common.NeoForgeMod WATER_TYPE and LAVA_TYPE to see possibilities
    public static final DeferredHolder<FluidType, FluidType> LIQUID_OF_GODS_TYPE = FLUID_TYPES.register("liquid_of_the_gods", () ->
            new FluidType(FluidType.Properties.create()
                    .descriptionId("block."+MODID+".liquid_of_the_gods")
                    .fallDistanceModifier(0F)
                    .canExtinguish(true)
                    .canConvertToSource(true)
                    .supportsBoating(true)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                    .sound(SoundActions.FLUID_VAPORIZE, SoundEvents.FIRE_EXTINGUISH)
                    .canHydrate(false)
                    .lightLevel(11)
                    .density(3000)
                    .viscosity(5000)
                    .temperature(400))
            {}); //render is handled in fr.factionbedrock.aerialhell.Client.Event.Listeners.FluidRenderHandler

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> LIQUID_OF_THE_GODS_SOURCE = FLUIDS.register("liquid_of_the_gods_source", () -> new BaseFlowingFluid.Source(AerialHellFluids.LIQUID_OF_THE_GODS_PROPERTIES));
    public static final DeferredHolder<Fluid, BaseFlowingFluid.Flowing> LIQUID_OF_THE_GODS_FLOWING = FLUIDS.register("liquid_of_the_gods_flowing", () -> new BaseFlowingFluid.Flowing(AerialHellFluids.LIQUID_OF_THE_GODS_PROPERTIES));

    public static final BaseFlowingFluid.Properties LIQUID_OF_THE_GODS_PROPERTIES = new BaseFlowingFluid.Properties(LIQUID_OF_GODS_TYPE, LIQUID_OF_THE_GODS_SOURCE, LIQUID_OF_THE_GODS_FLOWING).bucket(AerialHellItems.IRON_LIQUID_OF_GODS_BUCKET).block(AerialHellBlocks.LIQUID_OF_THE_GODS).tickRate(40).levelDecreasePerBlock(3).slopeFindDistance(4);
}
