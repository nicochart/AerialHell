package fr.factionbedrock.aerialhell.Registry.Misc;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class AerialHellPaintingVariants
{
    public static final DeferredRegister<PaintingVariant> PAINTING_TYPES = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, MODID);

    public static final RegistryObject<PaintingVariant> HOSTILE_PARADISE = PAINTING_TYPES.register("hostile_paradise", () -> new PaintingVariant(16, 16));
    public static final RegistryObject<PaintingVariant> SPOOKY_ISLANDS = PAINTING_TYPES.register("spooky_islands", () -> new PaintingVariant(16, 16));

    public static final RegistryObject<PaintingVariant> BROWN_SHROOM_ISLAND = PAINTING_TYPES.register("brown_shroom_island", () -> new PaintingVariant(16, 32));

    public static final RegistryObject<PaintingVariant> LIVING_ISLAND = PAINTING_TYPES.register("living_island", () -> new PaintingVariant(32, 16));

    public static final RegistryObject<PaintingVariant> FLOADING_ISLANDS_LANDSCAPE = PAINTING_TYPES.register("floating_islands_landscape", () -> new PaintingVariant(32, 32));

    public static final RegistryObject<PaintingVariant> CUTE_SHROOMS = PAINTING_TYPES.register("cute_shrooms", () -> new PaintingVariant(64, 32));

    public static final RegistryObject<PaintingVariant> MAGICAL_ISLAND = PAINTING_TYPES.register("magical_island", () -> new PaintingVariant(64, 48));
    public static final RegistryObject<PaintingVariant> FOGGY_PEAKS = PAINTING_TYPES.register("foggy_peaks", () -> new PaintingVariant(64, 48));

    public static final RegistryObject<PaintingVariant> SHROOM_AND_VEGETATION_ISLANDS = PAINTING_TYPES.register("shroom_and_vegetation_islands", () -> new PaintingVariant(64, 64));
    public static final RegistryObject<PaintingVariant> SHROOM_ISLAND_PIXELART = PAINTING_TYPES.register("shroom_island_pixelart", () -> new PaintingVariant(64, 64));
    public static final RegistryObject<PaintingVariant> MAGICAL_SHROOM_ISLAND = PAINTING_TYPES.register("magical_shroom_island", () -> new PaintingVariant(64, 64));
    public static final RegistryObject<PaintingVariant> MYSTERY_ISLANDS = PAINTING_TYPES.register("mystery_islands", () -> new PaintingVariant(64, 64));
    public static final RegistryObject<PaintingVariant> CYAN_SHROOM_ISLAND = PAINTING_TYPES.register("cyan_shroom_island", () -> new PaintingVariant(64, 64));
}
