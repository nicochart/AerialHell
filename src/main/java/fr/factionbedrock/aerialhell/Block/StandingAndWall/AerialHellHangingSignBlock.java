package fr.factionbedrock.aerialhell.Block.StandingAndWall;

import fr.factionbedrock.aerialhell.BlockEntity.AerialHellHangingSignBlockEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.HangingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class AerialHellHangingSignBlock extends HangingSignBlock
{
	public AerialHellHangingSignBlock(WoodType woodType, AbstractBlock.Settings settings) {super(woodType, settings);}

	@Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {return new AerialHellHangingSignBlockEntity(pos, state);}
}