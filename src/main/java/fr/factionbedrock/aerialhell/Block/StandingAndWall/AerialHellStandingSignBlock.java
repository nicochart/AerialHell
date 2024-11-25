package fr.factionbedrock.aerialhell.Block.StandingAndWall;

import fr.factionbedrock.aerialhell.BlockEntity.AerialHellSignBlockEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class AerialHellStandingSignBlock extends SignBlock
{
	public AerialHellStandingSignBlock(AbstractBlock.Settings settings, WoodType woodType) {super(woodType, settings);}

	@Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {return new AerialHellSignBlockEntity(pos, state);}
}