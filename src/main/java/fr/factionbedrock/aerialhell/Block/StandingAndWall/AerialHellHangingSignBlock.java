package fr.factionbedrock.aerialhell.Block.StandingAndWall;

import fr.factionbedrock.aerialhell.BlockEntity.AerialHellHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class AerialHellHangingSignBlock extends CeilingHangingSignBlock
{
	public AerialHellHangingSignBlock(WoodType woodType, BlockBehaviour.Properties builder) {super(builder, woodType);}

	@Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new AerialHellHangingSignBlockEntity(pos, state);}
}