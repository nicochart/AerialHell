package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.BlockEntity.AerialHellSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class AerialHellWallSignBlock extends WallSignBlock
{
    public AerialHellWallSignBlock(Properties builder) {super(builder, WoodType.OAK);}

    @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new AerialHellSignBlockEntity(pos, state);}
}