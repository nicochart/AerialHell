package fr.factionbedrock.aerialhell.Block.Ores;

import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.BlockView;

public class VoluciteOreBlock extends AerialHellOreBlock
{
    public VoluciteOreBlock(int minExpDropped, int maxExpDropped, AbstractBlock.Settings settings) {super(minExpDropped, maxExpDropped, settings);}

    //TODO : make the block do not loot

    @Override public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, net.minecraft.util.math.BlockPos pos)
    {
        float f = state.getHardness(world, pos);
        if (f == -1.0F) {return 0.0F;}
        else
        {
            boolean canHarvest = player.canHarvest(state) && canHarvest(player.getActiveItem());
            int i = canHarvest ? 30 : 100;
            return player.getBlockBreakingSpeed(state) / f / (float)i;
        }
    }

    boolean canHarvest(ItemStack handItem)
    {
        return ItemHelper.getItemMiningLevel(handItem.getItem()) >= 4;
    }
}
