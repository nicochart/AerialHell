package fr.factionbedrock.aerialhell.Registry;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

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
                        private static final ResourceLocation UNDER = new ResourceLocation(MODID, "block/liquid_of_the_gods_overlay"),
                                STILL = new ResourceLocation(MODID, "block/liquid_of_the_gods_still"),
                                FLOW = new ResourceLocation(MODID, "block/liquid_of_the_gods_flow");

                        @Override public ResourceLocation getStillTexture() {return STILL;}
                        @Override public ResourceLocation getFlowingTexture() {return FLOW;}
                        //@Nullable @Override public ResourceLocation getOverlayTexture() {return UNDER;} //Do not work : "failed to lead texture..."
                        //@Override public ResourceLocation getRenderOverlayTexture(Minecraft mc) {return UNDER;}

                        //@Override public int getTintColor() {return 0x310000;} modifies the whole texture tint, not the fog tint
                        @Override public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {return new Vector3f(49f / 255f, 0f, 0f);}
                        @Override public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape)
                        {
                            RenderSystem.setShaderFogStart(1f);
                            RenderSystem.setShaderFogEnd(4f);
                        }
                    });
                }
            });

    public static final RegistryObject<FlowingFluid> LIQUID_OF_THE_GODS_SOURCE = FLUIDS.register("liquid_of_the_gods_source", () -> new ForgeFlowingFluid.Source(AerialHellFluids.LIQUID_OF_THE_GODS_PROPERTIES));
    public static final RegistryObject<FlowingFluid> LIQUID_OF_THE_GODS_FLOWING = FLUIDS.register("liquid_of_the_gods_flowing", () -> new ForgeFlowingFluid.Flowing(AerialHellFluids.LIQUID_OF_THE_GODS_PROPERTIES));

    public static final ForgeFlowingFluid.Properties LIQUID_OF_THE_GODS_PROPERTIES = new ForgeFlowingFluid.Properties(LIQUID_OF_GODS_TYPE, LIQUID_OF_THE_GODS_SOURCE, LIQUID_OF_THE_GODS_FLOWING).bucket(AerialHellBlocksAndItems.IRON_LIQUID_OF_GODS_BUCKET).block(AerialHellBlocksAndItems.LIQUID_OF_THE_GODS).tickRate(40).levelDecreasePerBlock(3).slopeFindDistance(4);
}
