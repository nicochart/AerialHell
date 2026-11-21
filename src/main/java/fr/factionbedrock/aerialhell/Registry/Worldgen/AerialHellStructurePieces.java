package fr.factionbedrock.aerialhell.Registry.Worldgen;

import fr.factionbedrock.aerialhell.AerialHell;
import fr.factionbedrock.aerialhell.World.Structure.RootBridge.RootBridgePiece;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

public class AerialHellStructurePieces
{
    public static final StructurePieceType ROOT_BRIDGE_SEGMENT = register("root_bridge_segment", (StructurePieceType.ContextlessType) RootBridgePiece::new);

    public static StructurePieceType register(String name, StructurePieceType type)
    {
        return Registry.register(BuiltInRegistries.STRUCTURE_PIECE, AerialHell.id(name), type);
    }

    public static void load() {}
}
