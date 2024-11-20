package fr.factionbedrock.aerialhell.Block.StandingAndWall;

import fr.factionbedrock.aerialhell.BlockEntity.AerialHellHangingSignBlockEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.WallHangingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class AerialHellWallHangingSignBlock extends WallHangingSignBlock
{
	public AerialHellWallHangingSignBlock(WoodType woodType, AbstractBlock.Settings settings) {super(woodType, settings);}

	@Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new AerialHellHangingSignBlockEntity(pos, state);}
}