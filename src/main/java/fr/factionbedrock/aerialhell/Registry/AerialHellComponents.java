package fr.factionbedrock.aerialhell.Registry;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

public class AerialHellComponents
{
    public static final DataComponentType<Integer> PLACER_RADIUS_COMPONENT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            AerialHell.id("radius"),
            DataComponentType.<Integer>builder().persistent(Codec.INT).build()
    );

    public static void load() {}
}
