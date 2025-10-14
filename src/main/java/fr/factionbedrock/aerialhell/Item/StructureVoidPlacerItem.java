package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellComponents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.function.Consumer;

public class StructureVoidPlacerItem extends WithInformationItem
{
    public static BlockState PLACED_BLOCKSTATE = Blocks.WHITE_STAINED_GLASS.getDefaultState();

    public StructureVoidPlacerItem(Settings settings) {super(settings);}

    @Override public ActionResult use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack stack = user.getStackInHand(hand);
        int previousRadius = stack.getOrDefault(AerialHellComponents.PLACER_RADIUS_COMPONENT, 0);
        stack.set(AerialHellComponents.PLACER_RADIUS_COMPONENT, getNextRadius(previousRadius));
        user.playSound(SoundEvents.ENTITY_TURTLE_EGG_HATCH, 1.0F, 0.1F);
        user.getItemCooldownManager().set(user.getStackInHand(hand), 5);

        return ActionResult.SUCCESS;
    }

    private int getNextRadius(int previousRadius)
    {
        int incrementedRadius = previousRadius+1;
        return incrementedRadius < 6 ? incrementedRadius : 0;
    }

    @Override public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type)
    {
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        int radius = stack.getOrDefault(AerialHellComponents.PLACER_RADIUS_COMPONENT, 0);
        textConsumer.accept(Text.translatable(this.getTranslationKey()+".radius").append(Integer.toString(radius)).formatted(Formatting.GOLD));
    }
}
