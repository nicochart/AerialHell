package fr.factionbedrock.aerialhell.Registry;

import com.mojang.serialization.Codec;
import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AerialHellComponents
{
    public static final DeferredRegister<DataComponentType<?>> COMPONENTS = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, AerialHell.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> PLACER_RADIUS_COMPONENT = COMPONENTS.register("special_power", () -> DataComponentType.<Integer>builder().persistent(Codec.INT).build());
}
