package fr.factionbedrock.aerialhell.Block;

import fr.factionbedrock.aerialhell.BlockEntity.VoluciteBarrierBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class VoluciteBarrierBlock extends TransparentBlock implements EntityBlock
{
    public VoluciteBarrierBlock(Properties properties) {super(properties);}

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new VoluciteBarrierBlockEntity(pos, state);}
}
