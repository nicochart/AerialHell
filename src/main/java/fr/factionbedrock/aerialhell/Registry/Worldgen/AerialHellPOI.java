package fr.factionbedrock.aerialhell.Registry.Worldgen;

import com.google.common.collect.ImmutableSet;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;

import java.util.Set;

public class AerialHellPOI
{
    public static final PointOfInterestType AERIAL_HELL_PORTAL_POI = register(Keys.AERIAL_HELL_PORTAL_POI, AerialHellBlocks.AERIAL_HELL_PORTAL, 0, 1);

    private static PointOfInterestType register(RegistryKey<PointOfInterestType> key, Block block, int ticketCount, int searchDistance)
    {
        return PointOfInterestTypes.register(Registries.POINT_OF_INTEREST_TYPE, key, getStatesOfBlock(block), ticketCount, searchDistance);
    }

    private static Set<BlockState> getStatesOfBlock(Block block) {return ImmutableSet.copyOf(block.getStateManager().getStates());}

    public static class Keys
    {
        public static final RegistryKey<PointOfInterestType> AERIAL_HELL_PORTAL_POI = createKey("aerial_hell_portal");

        private static RegistryKey<PointOfInterestType> createKey(String name)
        {
            return RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, AerialHell.id(name));
        }
    }

    public static void load() {}
}
