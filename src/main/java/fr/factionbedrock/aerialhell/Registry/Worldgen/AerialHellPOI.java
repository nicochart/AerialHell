package fr.factionbedrock.aerialhell.Registry.Worldgen;

import com.google.common.collect.ImmutableSet;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import java.util.Set;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class AerialHellPOI
{
    public static final PoiType AERIAL_HELL_PORTAL_POI = register(Keys.AERIAL_HELL_PORTAL_POI, AerialHellBlocks.AERIAL_HELL_PORTAL, 0, 1);

    private static PoiType register(ResourceKey<PoiType> key, Block block, int ticketCount, int searchDistance)
    {
        return PoiTypes.register(BuiltInRegistries.POINT_OF_INTEREST_TYPE, key, getStatesOfBlock(block), ticketCount, searchDistance);
    }

    private static Set<BlockState> getStatesOfBlock(Block block) {return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());}

    public static class Keys
    {
        public static final ResourceKey<PoiType> AERIAL_HELL_PORTAL_POI = createKey("aerial_hell_portal");

        private static ResourceKey<PoiType> createKey(String name)
        {
            return ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, AerialHell.id(name));
        }
    }

    public static void load() {}
}
