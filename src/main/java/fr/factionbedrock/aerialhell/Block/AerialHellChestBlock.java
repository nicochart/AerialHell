package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.BlockEntity.AerialHellChestBlockEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlockEntities;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class AerialHellChestBlock extends ChestBlock
{
    public AerialHellChestBlock(AbstractBlock.Settings settings) {super(() -> AerialHellBlockEntities.CHEST, SoundEvents.BLOCK_CHEST_OPEN, SoundEvents.BLOCK_CHEST_CLOSE, settings);}

    @Nullable @Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {return new AerialHellChestBlockEntity(pos, state);}
}
