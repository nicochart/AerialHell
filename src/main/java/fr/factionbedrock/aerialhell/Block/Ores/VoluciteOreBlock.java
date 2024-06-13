package fr.factionbedrock.aerialhell.Block.Ores;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public class VoluciteOreBlock extends AerialHellOreBlock
{
    public VoluciteOreBlock(int minExpDropped, int maxExpDropped, Properties properties) {super(minExpDropped, maxExpDropped, properties);}

    @Override
    public boolean canHarvestBlock(BlockState state, BlockGetter level, BlockPos pos, Player player)
    {
        Item usedItem = player.getInventory().getSelected().getItem();
        boolean flag = super.canHarvestBlock(state, level, pos, player);
        return flag && ItemHelper.getItemMiningLevel(usedItem) >= 4;
    }
}
