package fr.factionbedrock.aerialhell.Block.Ores;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class VoluciteOreBlock extends AerialHellOreBlock
{
    public VoluciteOreBlock(int minExpDropped, int maxExpDropped, BlockBehaviour.Properties settings) {super(minExpDropped, maxExpDropped, settings);}

    //TODO : make the block do not loot

    @Override public float getDestroyProgress(BlockState state, Player player, BlockGetter world, net.minecraft.core.BlockPos pos)
    {
        float f = state.getDestroySpeed(world, pos);
        if (f == -1.0F) {return 0.0F;}
        else
        {
            boolean canHarvest = player.hasCorrectToolForDrops(state) && canHarvest(player.getUseItem());
            int i = canHarvest ? 30 : 100;
            return player.getDestroySpeed(state) / f / (float)i;
        }
    }

    boolean canHarvest(ItemStack handItem)
    {
        return ItemHelper.getItemMiningLevel(handItem.getItem()) >= 4;
    }
}
