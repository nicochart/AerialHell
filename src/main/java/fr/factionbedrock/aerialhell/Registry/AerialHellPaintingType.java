package fr.factionbedrock.aerialhell.Registry;

import net.minecraft.entity.item.PaintingType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class AerialHellPaintingType
{
    public static final DeferredRegister<PaintingType> PAINTING_TYPES = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, MODID);

    public static final RegistryObject<PaintingType> HOSTILE_PARADISE = PAINTING_TYPES.register("hostile_paradise", () -> new PaintingType(16, 16));
    public static final RegistryObject<PaintingType> SPOOKY_ISLANDS = PAINTING_TYPES.register("spooky_islands", () -> new PaintingType(16, 16));

    public static final RegistryObject<PaintingType> BROWN_SHROOM_ISLAND = PAINTING_TYPES.register("brown_shroom_island", () -> new PaintingType(16, 32));

    public static final RegistryObject<PaintingType> LIVING_ISLAND = PAINTING_TYPES.register("living_island", () -> new PaintingType(32, 16));

    public static final RegistryObject<PaintingType> FLOADING_ISLANDS_LANDSCAPE = PAINTING_TYPES.register("floating_islands_landscape", () -> new PaintingType(32, 32));

    public static final RegistryObject<PaintingType> CUTE_SHROOMS = PAINTING_TYPES.register("cute_shrooms", () -> new PaintingType(64, 32));

    public static final RegistryObject<PaintingType> MAGICAL_ISLAND = PAINTING_TYPES.register("magical_island", () -> new PaintingType(64, 48));
    public static final RegistryObject<PaintingType> FOGGY_PEAKS = PAINTING_TYPES.register("foggy_peaks", () -> new PaintingType(64, 48));

    public static final RegistryObject<PaintingType> SHROOM_AND_VEGETATION_ISLANDS = PAINTING_TYPES.register("shroom_and_vegetation_islands", () -> new PaintingType(64, 64));
    public static final RegistryObject<PaintingType> SHROOM_ISLAND_PIXELART = PAINTING_TYPES.register("shroom_island_pixelart", () -> new PaintingType(64, 64));
    public static final RegistryObject<PaintingType> MAGICAL_SHROOM_ISLAND = PAINTING_TYPES.register("magical_shroom_island", () -> new PaintingType(64, 64));
    public static final RegistryObject<PaintingType> MYSTERY_ISLANDS = PAINTING_TYPES.register("mystery_islands", () -> new PaintingType(64, 64));
    public static final RegistryObject<PaintingType> CYAN_SHROOM_ISLAND = PAINTING_TYPES.register("cyan_shroom_island", () -> new PaintingType(64, 64));
}
