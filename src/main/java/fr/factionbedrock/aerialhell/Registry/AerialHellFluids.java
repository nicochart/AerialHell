package fr.factionbedrock.aerialhell.Registry;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class AerialHellFluids
{
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, MODID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, MODID);

    //see net.minecraftforge.common.ForgeMod WATER_TYPE and LAVA_TYPE to see possibilities
    public static final RegistryObject<FluidType> LIQUID_OF_GODS_TYPE = FLUID_TYPES.register("liquid_of_the_gods", () ->
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
            {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer)
                {
                    consumer.accept(new IClientFluidTypeExtensions()
                    {
                        private static final ResourceLocation UNDER = new ResourceLocation(MODID, "textures/misc/underliquidofgods.png"),
                                STILL = new ResourceLocation(MODID, "fluid/liquid_of_the_gods_still"),
                                FLOW = new ResourceLocation(MODID, "fluid/liquid_of_the_gods_flow");

                        @Override public ResourceLocation getStillTexture() {return STILL;}
                        @Override public ResourceLocation getFlowingTexture() {return FLOW;}
                        @Nullable @Override public ResourceLocation getOverlayTexture() {return FLOW;}
                        @Override public ResourceLocation getRenderOverlayTexture(Minecraft mc) {return UNDER;}
                    });
                }
            });

    public static final RegistryObject<FlowingFluid> LIQUID_OF_THE_GODS_SOURCE = FLUIDS.register("liquid_of_the_gods_source", () -> new ForgeFlowingFluid.Source(AerialHellFluids.LIQUID_OF_THE_GODS_PROPERTIES));
    public static final RegistryObject<FlowingFluid> LIQUID_OF_THE_GODS_FLOWING = FLUIDS.register("liquid_of_the_gods_flowing", () -> new ForgeFlowingFluid.Flowing(AerialHellFluids.LIQUID_OF_THE_GODS_PROPERTIES));

    public static final ForgeFlowingFluid.Properties LIQUID_OF_THE_GODS_PROPERTIES = new ForgeFlowingFluid.Properties(LIQUID_OF_GODS_TYPE, LIQUID_OF_THE_GODS_SOURCE, LIQUID_OF_THE_GODS_FLOWING).bucket(AerialHellBlocksAndItems.IRON_LIQUID_OF_GODS_BUCKET).block(AerialHellBlocksAndItems.LIQUID_OF_THE_GODS).tickRate(40).levelDecreasePerBlock(3).slopeFindDistance(4);
}
