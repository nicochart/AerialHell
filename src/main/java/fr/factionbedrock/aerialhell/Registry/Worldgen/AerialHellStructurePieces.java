package fr.factionbedrock.aerialhell.Registry.Worldgen;

import fr.factionbedrock.aerialhell.World.Structure.RootBridge.RootBridgePiece;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static fr.factionbedrock.aerialhell.AerialHell.MODID;

public class AerialHellStructurePieces
{
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECES = DeferredRegister.create(Registries.STRUCTURE_PIECE, MODID);

    public static final DeferredHolder<StructurePieceType, StructurePieceType> ROOT_BRIDGE_SEGMENT = STRUCTURE_PIECES.register("root_bridge_segment", () -> (StructurePieceType.ContextlessType) RootBridgePiece::new);
}
