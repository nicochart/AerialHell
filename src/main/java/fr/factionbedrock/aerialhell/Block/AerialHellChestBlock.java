package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.BlockEntity.AerialHellChestBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class AerialHellChestBlock extends ChestBlock
{
    public AerialHellChestBlock(BlockBehaviour.Properties settings) {super(settings, () -> AerialHellBlockEntities.CHEST);}

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new AerialHellChestBlockEntity(pos, state);}
}
