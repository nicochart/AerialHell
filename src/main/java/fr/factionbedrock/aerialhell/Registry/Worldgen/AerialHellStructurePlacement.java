package fr.factionbedrock.aerialhell.Registry.Worldgen;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.World.StructurePlacement.ConfigSpacingStructurePlacement;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;

public class AerialHellStructurePlacement
{
    public static StructurePlacementType<ConfigSpacingStructurePlacement> CONFIG_SPACING = register("config_spacing", ConfigSpacingStructurePlacement.CODEC);

    private static <SP extends StructurePlacement> StructurePlacementType<SP> register(String id, MapCodec<SP> codec)
    {
        return Registry.register(BuiltInRegistries.STRUCTURE_PLACEMENT, AerialHell.id(id), () -> codec);
    }

    public static void load() {}
}
