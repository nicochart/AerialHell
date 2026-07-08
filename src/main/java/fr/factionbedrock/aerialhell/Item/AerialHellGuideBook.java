package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Client.Util.ClientHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AerialHellGuideBook extends Item
{
    public AerialHellGuideBook(Properties properties) {super(properties);}

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) //copied from ThrownEgg, replacing ThrownEgg with ThrownStellarEgg
    {
        if (level.isClientSide())
        {
            ClientHelper.openAerialHellGuideBookScreen();
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
