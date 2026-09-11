package fr.factionbedrock.aerialhell.Registry.Worldgen;

import fr.factionbedrock.aerialhell.World.StructurePlacement.ConfigSpacingStructurePlacement;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacementType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class AerialHellStructurePlacements
{
    public static final DeferredRegister<StructurePlacementType<?>> STRUCTURE_PLACEMENTS = DeferredRegister.create(Registries.STRUCTURE_PLACEMENT, MODID);

    public static final RegistryObject<StructurePlacementType<ConfigSpacingStructurePlacement>> CONFIG_SPACING = STRUCTURE_PLACEMENTS.register("config_spacing", () -> () -> ConfigSpacingStructurePlacement.CODEC);
}
