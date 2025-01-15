package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.BlockEntity.AerialHellChestBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AerialHellChestBlock extends ChestBlock
{
    public AerialHellChestBlock(Properties properties) {super(AerialHellBlockEntities.CHEST::get, properties);}

    @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new AerialHellChestBlockEntity(pos, state);}
}
