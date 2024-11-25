package fr.factionbedrock.aerialhell.BlockEntity;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.util.math.BlockPos;

public class AerialHellSignBlockEntity extends SignBlockEntity
{
	public AerialHellSignBlockEntity(BlockPos pos, BlockState state) {super(pos, state);}

	@Override public BlockEntityType<?> getType() {return AerialHellBlockEntities.SIGN;}
}