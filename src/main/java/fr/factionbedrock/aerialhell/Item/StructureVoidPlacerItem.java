package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellComponents;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class StructureVoidPlacerItem extends WithInformationItem
{
    public static BlockState PLACED_BLOCKSTATE = Blocks.WHITE_STAINED_GLASS.defaultBlockState();

    public StructureVoidPlacerItem(Properties settings) {super(settings);}

    @Override public InteractionResult use(Level world, Player user, InteractionHand hand)
    {
        ItemStack stack = user.getItemInHand(hand);
        int previousRadius = stack.getOrDefault(AerialHellComponents.PLACER_RADIUS_COMPONENT, 0);
        stack.set(AerialHellComponents.PLACER_RADIUS_COMPONENT, getNextRadius(previousRadius));
        user.playSound(SoundEvents.TURTLE_EGG_HATCH, 1.0F, 0.1F);
        user.getCooldowns().addCooldown(user.getItemInHand(hand), 5);

        return InteractionResult.SUCCESS;
    }

    private int getNextRadius(int previousRadius)
    {
        int incrementedRadius = previousRadius+1;
        return incrementedRadius < 6 ? incrementedRadius : 0;
    }

    @Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type)
    {
        super.appendHoverText(stack, context, displayComponent, textConsumer, type);
        int radius = stack.getOrDefault(AerialHellComponents.PLACER_RADIUS_COMPONENT, 0);
        textConsumer.accept(Component.translatable(this.getDescriptionId()+".radius").append(Integer.toString(radius)).withStyle(ChatFormatting.GOLD));
    }
}
