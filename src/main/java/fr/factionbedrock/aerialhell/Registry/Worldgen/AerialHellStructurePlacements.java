package fr.factionbedrock.aerialhell.Registry.Worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class AerialHellStructurePlacements
{
    public static final DeferredRegister<StructurePlacementType<?>> STRUCTURE_PLACEMENTS = DeferredRegister.create(Registries.STRUCTURE_PLACEMENT, MODID);

    public static final DeferredHolder<StructurePlacementType<?>, StructurePlacementType<RandomSpreadStructurePlacement>> CONFIG_SPACING_STRUCTURE_PLACEMENT = STRUCTURE_PLACEMENTS.register("config_spacing_structure_placement", () -> () -> RandomSpreadStructurePlacement.CODEC);
}
