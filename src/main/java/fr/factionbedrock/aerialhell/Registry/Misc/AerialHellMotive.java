package fr.factionbedrock.aerialhell.Registry.Misc;

import net.minecraft.world.entity.decoration.Motive;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class AerialHellMotive
{
    public static final DeferredRegister<Motive> PAINTING_TYPES = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, MODID);

    public static final RegistryObject<Motive> HOSTILE_PARADISE = PAINTING_TYPES.register("hostile_paradise", () -> new Motive(16, 16));
    public static final RegistryObject<Motive> SPOOKY_ISLANDS = PAINTING_TYPES.register("spooky_islands", () -> new Motive(16, 16));

    public static final RegistryObject<Motive> BROWN_SHROOM_ISLAND = PAINTING_TYPES.register("brown_shroom_island", () -> new Motive(16, 32));

    public static final RegistryObject<Motive> LIVING_ISLAND = PAINTING_TYPES.register("living_island", () -> new Motive(32, 16));

    public static final RegistryObject<Motive> FLOADING_ISLANDS_LANDSCAPE = PAINTING_TYPES.register("floating_islands_landscape", () -> new Motive(32, 32));

    public static final RegistryObject<Motive> CUTE_SHROOMS = PAINTING_TYPES.register("cute_shrooms", () -> new Motive(64, 32));

    public static final RegistryObject<Motive> MAGICAL_ISLAND = PAINTING_TYPES.register("magical_island", () -> new Motive(64, 48));
    public static final RegistryObject<Motive> FOGGY_PEAKS = PAINTING_TYPES.register("foggy_peaks", () -> new Motive(64, 48));

    public static final RegistryObject<Motive> SHROOM_AND_VEGETATION_ISLANDS = PAINTING_TYPES.register("shroom_and_vegetation_islands", () -> new Motive(64, 64));
    public static final RegistryObject<Motive> SHROOM_ISLAND_PIXELART = PAINTING_TYPES.register("shroom_island_pixelart", () -> new Motive(64, 64));
    public static final RegistryObject<Motive> MAGICAL_SHROOM_ISLAND = PAINTING_TYPES.register("magical_shroom_island", () -> new Motive(64, 64));
    public static final RegistryObject<Motive> MYSTERY_ISLANDS = PAINTING_TYPES.register("mystery_islands", () -> new Motive(64, 64));
    public static final RegistryObject<Motive> CYAN_SHROOM_ISLAND = PAINTING_TYPES.register("cyan_shroom_island", () -> new Motive(64, 64));
}
