package fr.factionbedrock.aerialhell.Registry;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AerialHellComponents
{
    public static final ComponentType<Integer> PLACER_RADIUS_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            AerialHell.id("radius"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );

    public static void load() {}
}
