package fr.factionbedrock.aerialhell.Registry.Worldgen;

import com.google.common.collect.ImmutableSet;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellPOI
{
    public static final DeferredRegister<PoiType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, AerialHell.MODID);

    public static final RegistryObject<PoiType> AERIAL_HELL_PORTAL_POI = POI.register("aerial_hell_portal", () -> new PoiType(ImmutableSet.copyOf(AerialHellBlocksAndItems.AERIAL_HELL_PORTAL.get().getStateDefinition().getPossibleStates()), 0, 1));
}