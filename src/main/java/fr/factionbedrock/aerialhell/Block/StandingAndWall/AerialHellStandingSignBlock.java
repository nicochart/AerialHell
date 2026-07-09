package fr.factionbedrock.aerialhell.Block.StandingAndWall;

import fr.factionbedrock.aerialhell.BlockEntity.AerialHellSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class AerialHellStandingSignBlock extends StandingSignBlock
{
	public AerialHellStandingSignBlock(BlockBehaviour.Properties settings, WoodType woodType) {super(woodType, settings);}

	@Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new AerialHellSignBlockEntity(pos, state);}
}