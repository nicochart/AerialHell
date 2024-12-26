package fr.factionbedrock.aerialhell.Registry.Worldgen;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.kyrptonaught.customportalapi.event.CPASoundEventData;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class AerialHellDimensions
{
    public static final RegistryKey<DimensionType> AERIAL_HELL_DIMENSION_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, name("aerial_hell"));
    public static final RegistryKey<World> AERIAL_HELL_DIMENSION = RegistryKey.of(RegistryKeys.WORLD, name("aerial_hell"));

    private static Identifier name(String name) {return AerialHell.id(name);}

    public static void makePortal()
    {
        CustomPortalBuilder.beginPortal()
                .customPortalBlock(AerialHellBlocks.AERIAL_HELL_PORTAL)
                .frameBlock(AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK)
                .lightWithItem(AerialHellItems.STELLAR_LIGHTER)
                .destDimID(AERIAL_HELL_DIMENSION.getValue())
                .registerInPortalAmbienceSound((player) -> new CPASoundEventData(AerialHellSoundEvents.BLOCK_AERIAL_HELL_PORTAL_AMBIENT, 1.0F, 1.0F))
                .registerPostTPPortalAmbience((player) -> new CPASoundEventData(AerialHellSoundEvents.BLOCK_AERIAL_HELL_PORTAL_AMBIENT, 1.0F, 1.0F))
                .tintColor(15, 110, 0)
                .registerPortal();
    }
}
