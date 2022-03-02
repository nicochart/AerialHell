package fr.factionbedrock.aerialhell.Registry;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;

public class AerialHellDimensions
{
	public static final RegistryKey<DimensionType> AERIAL_HELL_DIMENSION_TYPE = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, name("aerial_hell"));
    public static final RegistryKey<World> AERIAL_HELL_DIMENSION = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, name("aerial_hell"));
    
    private static ResourceLocation name(String name)
    {
        return new ResourceLocation(AerialHell.MODID, name);
    }
}
