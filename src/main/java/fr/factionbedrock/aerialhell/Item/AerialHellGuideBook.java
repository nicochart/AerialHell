package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Client.Gui.Screen.Inventory.GuideBookScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class AerialHellGuideBook extends Item
{
    public AerialHellGuideBook(Properties properties) {super(properties);}

    @Override public InteractionResult use(Level level, Player player, InteractionHand hand)
    {
        if (level.isClientSide())
        {
            Minecraft.getInstance().setScreen(new GuideBookScreen());
        }
        return InteractionResult.SUCCESS;
    }
}
