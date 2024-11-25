package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.util.math.BlockPos;

public class ChestMimicBlockEntity extends ChestBlockEntity
{
	public ChestMimicBlockEntity(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state) {super(blockEntity, pos, state);}

	public ChestMimicBlockEntity(BlockPos pos, BlockState state) {this(AerialHellBlockEntities.CHEST_MIMIC, pos, state);}

	@Override public BlockEntityType<?> getType() {return AerialHellBlockEntities.CHEST_MIMIC;}
}