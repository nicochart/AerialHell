package fr.factionbedrock.aerialhell.Registry.Worldgen;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class AerialHellDimensions
{
    public static final ResourceKey<DimensionType> AERIAL_HELL_DIMENSION_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, name("aerial_hell"));
    public static final ResourceKey<Level> AERIAL_HELL_DIMENSION = ResourceKey.create(Registries.DIMENSION, name("aerial_hell"));

    private static Identifier name(String name) {return AerialHell.id(name);}

    public static void makePortal() //TODO
    {
        /*CustomPortalBuilder.beginPortal()
                .customPortalBlock(AerialHellBlocks.AERIAL_HELL_PORTAL)
                .frameBlock(AerialHellBlocks.STELLAR_PORTAL_FRAME_BLOCK)
                .lightWithItem(AerialHellItems.STELLAR_LIGHTER)
                .destDimID(AERIAL_HELL_DIMENSION.getValue())
                .registerInPortalAmbienceSound((player) -> new CPASoundEventData(AerialHellSoundEvents.BLOCK_AERIAL_HELL_PORTAL_AMBIENT, 1.0F, 1.0F))
                .registerPostTPPortalAmbience((player) -> new CPASoundEventData(AerialHellSoundEvents.BLOCK_AERIAL_HELL_PORTAL_AMBIENT, 1.0F, 1.0F))
                .tintColor(15, 110, 0)
                .registerPortal();*/
    }
}
