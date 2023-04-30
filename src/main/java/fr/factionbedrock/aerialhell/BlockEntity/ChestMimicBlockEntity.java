package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Block.ChestMimicBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ChestMimicBlockEntity extends BlockEntity
{
	public ChestMimicBlockEntity(BlockEntityType<?> blockEntity, BlockPos pos, BlockState state) {super(blockEntity, pos, state);}

	public ChestMimicBlockEntity(BlockPos pos, BlockState state) {this(AerialHellBlockEntities.CHEST_MIMIC.get(), pos, state);}

	@Override public BlockEntityType<?> getType() {return AerialHellBlockEntities.CHEST_MIMIC.get();}
}