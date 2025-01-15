package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Block.AerialHellPortalBlock;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.UseOnContext;

public class StellarLighterItem extends WithInformationItem
{
	public StellarLighterItem(Properties properties) {super(properties);}
	
	@Override
    public InteractionResult useOn(UseOnContext context)
	{
		Player player = context.getPlayer();
        if(context.getPlayer() != null)
        {
            for(Direction direction : Direction.Plane.VERTICAL)
            {
                BlockPos framePos = context.getClickedPos().relative(direction);
                if(((AerialHellPortalBlock) AerialHellBlocksAndItems.AERIAL_HELL_PORTAL.get()).trySpawnPortal(context.getLevel(), framePos))
                {
                	context.getPlayer().getCooldowns().addCooldown(context.getItemInHand(), 50);
                	context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                	return InteractionResult.CONSUME;
                }
                else {return InteractionResult.FAIL;}
            }
        }
        return InteractionResult.FAIL;
    }
}
