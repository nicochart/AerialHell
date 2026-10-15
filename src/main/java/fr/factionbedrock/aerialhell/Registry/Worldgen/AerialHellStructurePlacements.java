package fr.factionbedrock.aerialhell.Registry.Worldgen;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.aerialhell.World.StructurePlacement.ConfigSpacingStructurePlacement;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class AerialHellStructurePlacements
{
    public static final DeferredRegister<MapCodec<? extends StructurePlacement>> STRUCTURE_PLACEMENTS = DeferredRegister.create(Registries.STRUCTURE_PLACEMENT, MODID);

    public static final DeferredHolder<MapCodec<? extends StructurePlacement>, MapCodec<ConfigSpacingStructurePlacement>> CONFIG_SPACING = STRUCTURE_PLACEMENTS.register("config_spacing", () -> ConfigSpacingStructurePlacement.CODEC);
}
