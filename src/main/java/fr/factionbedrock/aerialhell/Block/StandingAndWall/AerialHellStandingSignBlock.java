package fr.factionbedrock.aerialhell.Block.StandingAndWall;

import fr.factionbedrock.aerialhell.BlockEntity.AerialHellSignBlockEntity;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;

public class AerialHellStandingSignBlock extends StandingSignBlock
{
	public AerialHellStandingSignBlock(BlockBehaviour.Properties builder, WoodType woodType) {super(woodType, builder);}

	@Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new AerialHellSignBlockEntity(pos, state);}
}