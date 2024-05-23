package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AerialHellHangingSignBlockEntity extends HangingSignBlockEntity
{
	public AerialHellHangingSignBlockEntity(BlockPos pos, BlockState state) {super(pos, state);}

	@Override public BlockEntityType<?> getType() {return AerialHellBlockEntities.HANGING_SIGN.get();}
}