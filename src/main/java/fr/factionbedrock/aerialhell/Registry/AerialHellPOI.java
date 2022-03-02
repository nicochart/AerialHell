package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AerialHellPOI
{
    public static final DeferredRegister<PointOfInterestType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, AerialHell.MODID);

    public static final RegistryObject<PointOfInterestType> AERIAL_HELL_PORTAL_POI = POI.register("aerial_hell_portal",
            () -> new PointOfInterestType("aerial_hell_portal", PointOfInterestType.getAllStates(AerialHellBlocksAndItems.AERIAL_HELL_PORTAL.get()), 0, 1));
}