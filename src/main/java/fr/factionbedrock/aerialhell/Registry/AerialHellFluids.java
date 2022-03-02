package fr.factionbedrock.aerialhell.Registry;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellFluids
{
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MODID);
	
	public static final RegistryObject<FlowingFluid> LIQUID_OF_THE_GODS_SOURCE = FLUIDS.register("liquid_of_the_gods_source", () -> new ForgeFlowingFluid.Source(AerialHellFluids.LIQUID_OF_THE_GODS_PROPERTIES));
    public static final RegistryObject<FlowingFluid> LIQUID_OF_THE_GODS_FLOWING = FLUIDS.register("liquid_of_the_gods_flowing", () -> new ForgeFlowingFluid.Flowing(AerialHellFluids.LIQUID_OF_THE_GODS_PROPERTIES));

    public static final ForgeFlowingFluid.Properties LIQUID_OF_THE_GODS_PROPERTIES = new ForgeFlowingFluid.Properties(LIQUID_OF_THE_GODS_SOURCE, LIQUID_OF_THE_GODS_FLOWING, FluidAttributes.builder(new ResourceLocation(MODID, "fluid/liquid_of_the_gods_still"), new ResourceLocation(MODID, "fluid/liquid_of_the_gods_flow"))).bucket(AerialHellBlocksAndItems.IRON_LIQUID_OF_GODS_BUCKET).block(AerialHellBlocksAndItems.LIQUID_OF_THE_GODS).tickRate(40).levelDecreasePerBlock(3).slopeFindDistance(4);
}
