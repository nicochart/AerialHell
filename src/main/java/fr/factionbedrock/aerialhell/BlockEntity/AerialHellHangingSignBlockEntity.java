package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.HangingSignBlockEntity;
import net.minecraft.util.math.BlockPos;

public class AerialHellHangingSignBlockEntity extends HangingSignBlockEntity
{
	public AerialHellHangingSignBlockEntity(BlockPos pos, BlockState state) {super(pos, state);}

	@Override public BlockEntityType<?> getType() {return AerialHellBlockEntities.HANGING_SIGN;}
}