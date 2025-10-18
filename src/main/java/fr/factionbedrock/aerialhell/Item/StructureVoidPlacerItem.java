package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellComponents;
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

import java.util.function.Consumer;

public class StructureVoidPlacerItem extends WithInformationItem
{
    public static BlockState PLACED_BLOCKSTATE = Blocks.WHITE_STAINED_GLASS.defaultBlockState();

    public StructureVoidPlacerItem(Properties properties) {super(properties);}

    @Override public InteractionResult use(Level level, Player player, InteractionHand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        int previousRadius = stack.getOrDefault(AerialHellComponents.PLACER_RADIUS_COMPONENT, 0);
        stack.set(AerialHellComponents.PLACER_RADIUS_COMPONENT, getNextRadius(previousRadius));
        player.playSound(SoundEvents.TURTLE_EGG_HATCH, 1.0F, 0.1F);
        player.getCooldowns().addCooldown(player.getItemInHand(hand), 5);

        return InteractionResult.SUCCESS;
    }

    private int getNextRadius(int previousRadius)
    {
        int incrementedRadius = previousRadius+1;
        return incrementedRadius < 6 ? incrementedRadius : 0;
    }

    @Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag)
    {
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
        int radius = stack.getOrDefault(AerialHellComponents.PLACER_RADIUS_COMPONENT, 0);
        tooltipAdder.accept(Component.translatable(this.getDescriptionId()+".radius").append(Integer.toString(radius)).withStyle(ChatFormatting.GOLD));
    }
}
