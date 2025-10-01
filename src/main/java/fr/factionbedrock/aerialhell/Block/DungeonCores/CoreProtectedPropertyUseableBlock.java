package fr.factionbedrock.aerialhell.Block.DungeonCores;


import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;

public interface CoreProtectedPropertyUseableBlock extends CoreProtectedPropertyBlock
{
    default boolean canUse(BlockState state, PlayerEntity player)
    {
        return !this.isProtected(state) || player.isCreative();
    }
}
