package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class StellarLighterItem extends WithInformationItem
{
	public StellarLighterItem(Settings settings) {super(settings);}

    @Override public ActionResult useOnBlock(ItemUsageContext context)
    {
        PlayerEntity player = context.getPlayer();
        if(player != null)
        {
            for(Direction direction : Direction.Type.VERTICAL)
            {
                BlockPos framePos = context.getBlockPos().offset(direction);
                if((AerialHellBlocks.AERIAL_HELL_PORTAL).trySpawnPortal(context.getWorld(), framePos))
                {
                    context.getPlayer().getItemCooldownManager().set(context.getStack(), 50);
                    context.getStack().damage(1, player, context.getHand());
                    return ActionResult.CONSUME;
                }
                else {return ActionResult.FAIL;}
            }
        }
        return ActionResult.FAIL;
    }
}
