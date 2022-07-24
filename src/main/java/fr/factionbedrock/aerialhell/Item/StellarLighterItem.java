package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Block.AerialHellPortalBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class StellarLighterItem extends WithInformationItem
{
	public StellarLighterItem(Properties properties) {super(properties);}
	
	@Override
    public ActionResultType onItemUse(ItemUseContext context)
	{
		PlayerEntity player = context.getPlayer();
        if(context.getPlayer() != null)
        {
            for(Direction direction : Direction.Plane.VERTICAL)
            {
                BlockPos framePos = context.getPos().offset(direction);
                if(((AerialHellPortalBlock) AerialHellBlocksAndItems.AERIAL_HELL_PORTAL.get()).trySpawnPortal(context.getWorld(), framePos))
                {
                	context.getPlayer().getCooldownTracker().setCooldown(this, 50);
                	context.getItem().damageItem(1, player, (p) -> {p.sendBreakAnimation(player.getActiveHand());});
                	return ActionResultType.CONSUME;
                }
                else {return ActionResultType.FAIL;}
            }
        }
        return ActionResultType.FAIL;
    }
}
