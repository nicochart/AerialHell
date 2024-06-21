package fr.factionbedrock.aerialhell.Registry.Worldgen;

import com.google.common.collect.ImmutableSet;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AerialHellPOI
{
    public static final DeferredRegister<PoiType> POI = DeferredRegister.create(BuiltInRegistries.POINT_OF_INTEREST_TYPE, AerialHell.MODID);

    public static final DeferredHolder<PoiType, PoiType> AERIAL_HELL_PORTAL_POI = POI.register("aerial_hell_portal", () -> new PoiType(ImmutableSet.copyOf(AerialHellBlocksAndItems.AERIAL_HELL_PORTAL.get().getStateDefinition().getPossibleStates()), 0, 1));
}