package fr.factionbedrock.aerialhell.Registry.Worldgen;

import fr.factionbedrock.aerialhell.AerialHell;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class AerialHellDimensions
{
	public static final ResourceKey<DimensionType> AERIAL_HELL_DIMENSION_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, name("aerial_hell"));
    public static final ResourceKey<Level> AERIAL_HELL_DIMENSION = ResourceKey.create(Registry.DIMENSION_REGISTRY, name("aerial_hell"));
    
    private static ResourceLocation name(String name) {return new ResourceLocation(AerialHell.MODID, name);}
}
