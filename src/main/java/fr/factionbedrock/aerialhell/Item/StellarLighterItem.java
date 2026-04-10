package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;

public class StellarLighterItem extends WithInformationItem
{
	public StellarLighterItem(Properties settings) {super(settings);}

    @Override public InteractionResult useOn(UseOnContext context)
    {
        Player player = context.getPlayer();
        if(player != null)
        {
            for(Direction direction : Direction.Plane.VERTICAL)
            {
                BlockPos framePos = context.getClickedPos().relative(direction);
                if((AerialHellBlocks.AERIAL_HELL_PORTAL).trySpawnPortal(context.getLevel(), framePos))
                {
                    context.getPlayer().getCooldowns().addCooldown(context.getItemInHand(), 50);
                    context.getItemInHand().hurtAndBreak(1, player, context.getHand());
                    return InteractionResult.CONSUME;
                }
                else {return InteractionResult.FAIL;}
            }
        }
        return InteractionResult.FAIL;
    }
}
