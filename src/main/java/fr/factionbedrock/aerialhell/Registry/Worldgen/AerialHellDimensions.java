package fr.factionbedrock.aerialhell.Registry.Worldgen;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
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
                .frameBlock(Blocks.STONE_BRICKS)
                .lightWithItem(AerialHellItems.STELLAR_LIGHTER)
                .destDimID(AerialHell.id("aerial_hell"))
                .tintColor(15, 110, 0)
                .registerPortal();
    }
}
