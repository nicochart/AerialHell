package fr.factionbedrock.aerialhell.Block.StandingAndWall;

import fr.factionbedrock.aerialhell.BlockEntity.AerialHellHangingSignBlockEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.HangingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class AerialHellHangingSignBlock extends HangingSignBlock
{
	public AerialHellHangingSignBlock(WoodType woodType, AbstractBlock.Settings settings) {super(woodType, settings);}

	@Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new AerialHellHangingSignBlockEntity(pos, state);}
}