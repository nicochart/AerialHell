package fr.factionbedrock.aerialhell.Registry.Worldgen;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.World.StructurePlacement.ConfigSpacingStructurePlacement;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.chunk.placement.StructurePlacement;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;

public class AerialHellStructurePlacement
{
    public static StructurePlacementType<ConfigSpacingStructurePlacement> CONFIG_SPACING = register("config_spacing", ConfigSpacingStructurePlacement.CODEC);

    private static <SP extends StructurePlacement> StructurePlacementType<SP> register(String id, MapCodec<SP> codec)
    {
        return Registry.register(Registries.STRUCTURE_PLACEMENT, AerialHell.id(id), () -> codec);
    }

    public static void load() {}
}
