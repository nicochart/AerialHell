package fr.factionbedrock.aerialhell.Block.DungeonCores;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

public interface CoreProtectedPropertyUseableBlock extends CoreProtectedPropertyBlock
{
    default boolean canUse(BlockState state, Player player)
    {
        return !this.isProtected(state) || player.isCreative();
    }
}
