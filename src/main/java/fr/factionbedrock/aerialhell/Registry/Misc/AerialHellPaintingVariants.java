package fr.factionbedrock.aerialhell.Registry.Misc;

import com.google.common.collect.Lists;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class AerialHellPaintingVariants
{
    public static final DeferredRegister<PaintingVariant> PAINTING_TYPES = DeferredRegister.create(BuiltInRegistries.PAINTING_VARIANT, MODID);

    public static final DeferredHolder<PaintingVariant, PaintingVariant> HOSTILE_PARADISE = PAINTING_TYPES.register("hostile_paradise", () -> new PaintingVariant(16, 16));
    public static final DeferredHolder<PaintingVariant, PaintingVariant> SPOOKY_ISLANDS = PAINTING_TYPES.register("spooky_islands", () -> new PaintingVariant(16, 16));

    public static final DeferredHolder<PaintingVariant, PaintingVariant> BROWN_SHROOM_ISLAND = PAINTING_TYPES.register("brown_shroom_island", () -> new PaintingVariant(16, 32));

    public static final DeferredHolder<PaintingVariant, PaintingVariant> LIVING_ISLAND = PAINTING_TYPES.register("living_island", () -> new PaintingVariant(32, 16));

    public static final DeferredHolder<PaintingVariant, PaintingVariant> FLOADING_ISLANDS_LANDSCAPE = PAINTING_TYPES.register("floating_islands_landscape", () -> new PaintingVariant(32, 32));
    public static final DeferredHolder<PaintingVariant, PaintingVariant> MUD_CYCLE_MAGE = PAINTING_TYPES.register("mud_cycle_mage", () -> new PaintingVariant(32, 32));

    public static final DeferredHolder<PaintingVariant, PaintingVariant> CUTE_SHROOMS = PAINTING_TYPES.register("cute_shrooms", () -> new PaintingVariant(64, 32));

    public static final DeferredHolder<PaintingVariant, PaintingVariant> MAGICAL_ISLAND = PAINTING_TYPES.register("magical_island", () -> new PaintingVariant(64, 48));
    public static final DeferredHolder<PaintingVariant, PaintingVariant> FOGGY_PEAKS = PAINTING_TYPES.register("foggy_peaks", () -> new PaintingVariant(64, 48));

    public static final DeferredHolder<PaintingVariant, PaintingVariant> SHROOM_AND_VEGETATION_ISLANDS = PAINTING_TYPES.register("shroom_and_vegetation_islands", () -> new PaintingVariant(64, 64));
    public static final DeferredHolder<PaintingVariant, PaintingVariant> SHROOM_ISLAND_PIXELART = PAINTING_TYPES.register("shroom_island_pixelart", () -> new PaintingVariant(64, 64));
    public static final DeferredHolder<PaintingVariant, PaintingVariant> MAGICAL_SHROOM_ISLAND = PAINTING_TYPES.register("magical_shroom_island", () -> new PaintingVariant(64, 64));
    public static final DeferredHolder<PaintingVariant, PaintingVariant> MYSTERY_ISLANDS = PAINTING_TYPES.register("mystery_islands", () -> new PaintingVariant(64, 64));
    public static final DeferredHolder<PaintingVariant, PaintingVariant> CYAN_SHROOM_ISLAND = PAINTING_TYPES.register("cyan_shroom_island", () -> new PaintingVariant(64, 64));
    public static final DeferredHolder<PaintingVariant, PaintingVariant> CHAINED_GOD = PAINTING_TYPES.register("chained_god", () -> new PaintingVariant(64, 64));
    public static final DeferredHolder<PaintingVariant, PaintingVariant> LILITH = PAINTING_TYPES.register("lilith", () -> new PaintingVariant(64, 64));

    public static List<Holder<PaintingVariant>> getAerialHellPaintingVariantList()
    {
        List<Holder<PaintingVariant>> list = Lists.newArrayList();
        list.add(AerialHellPaintingVariants.HOSTILE_PARADISE.getDelegate());
        list.add(AerialHellPaintingVariants.SPOOKY_ISLANDS.getDelegate());
        list.add(AerialHellPaintingVariants.BROWN_SHROOM_ISLAND.getDelegate());
        list.add(AerialHellPaintingVariants.LIVING_ISLAND.getDelegate());
        list.add(AerialHellPaintingVariants.FLOADING_ISLANDS_LANDSCAPE.getDelegate());
        list.add(AerialHellPaintingVariants.MUD_CYCLE_MAGE.getDelegate());
        list.add(AerialHellPaintingVariants.CUTE_SHROOMS.getDelegate());
        list.add(AerialHellPaintingVariants.MAGICAL_ISLAND.getDelegate());
        list.add(AerialHellPaintingVariants.FOGGY_PEAKS.getDelegate());
        list.add(AerialHellPaintingVariants.SHROOM_AND_VEGETATION_ISLANDS.getDelegate());
        list.add(AerialHellPaintingVariants.SHROOM_ISLAND_PIXELART.getDelegate());
        list.add(AerialHellPaintingVariants.MAGICAL_SHROOM_ISLAND.getDelegate());
        list.add(AerialHellPaintingVariants.MYSTERY_ISLANDS.getDelegate());
        list.add(AerialHellPaintingVariants.CYAN_SHROOM_ISLAND.getDelegate());
        list.add(AerialHellPaintingVariants.CHAINED_GOD.getDelegate());
        list.add(AerialHellPaintingVariants.LILITH.getDelegate());
        return list;
    }
}
