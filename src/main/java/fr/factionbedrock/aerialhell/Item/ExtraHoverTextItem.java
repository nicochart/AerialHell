package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.BlockEntity.ReactorBlockEntity;
import fr.factionbedrock.aerialhell.Inventory.Menu.ReactorMenu;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface ExtraHoverTextItem
{
    Item getSelf();

    default void appendOptionalDescriptionsHoverText(Item.TooltipContext context, Consumer<Component> tooltipAdder)
    {
        this.getOptionalItemDescription(1).ifPresent(description -> tooltipAdder.accept(description.withStyle(ChatFormatting.GRAY)));
        this.getOptionalItemDescription(2).ifPresent(description -> tooltipAdder.accept(description.withStyle(ChatFormatting.GRAY)));
        this.getOptionalItemDescription(3).ifPresent(description -> tooltipAdder.accept(description.withStyle(ChatFormatting.GRAY)));
    }

    default void appendOptionalDescriptionHoverText(Item.TooltipContext context, Consumer<Component> tooltipAdder, String key, ChatFormatting formatting)
    {
        this.getOptionalDescription(key).ifPresent(description -> tooltipAdder.accept(description.withStyle(formatting)));
    }

    default void appendAbilityDescriptionHoverText(Player player, Item.TooltipContext context, Consumer<Component> tooltipAdder) {}

    default void appendReactorMenuHoverText(Player player, Item.TooltipContext context, Consumer<Component> tooltipAdder)
    {
        if (player != null && player.containerMenu instanceof ReactorMenu reactorMenu)
        {
            if (reactorMenu.isLightReactor() && ItemHelper.getOscillatingMap().containsKey(this.getSelf()))
            {
                tooltipAdder.accept(Component.translatable("item.aerialhell.light_reactor_fuel.desc").withStyle(ChatFormatting.GREEN));
                tooltipAdder.accept(Component.translatable("item.aerialhell.light_reactor_fuel.desc_2").append(ItemHelper.getTimeStringFromTicks(ItemHelper.getOscillatingMap().get(this.getSelf()) * ReactorBlockEntity.FACTOR)).withStyle(ChatFormatting.GREEN));
            }
            else if (!reactorMenu.isLightReactor() && ItemHelper.getCorruptingMap().containsKey(this.getSelf()))
            {
                tooltipAdder.accept(Component.translatable("item.aerialhell.shadow_reactor_fuel.desc").withStyle(ChatFormatting.LIGHT_PURPLE));
                tooltipAdder.accept(Component.translatable("item.aerialhell.shadow_reactor_fuel.desc_2").append(ItemHelper.getTimeStringFromTicks(ItemHelper.getCorruptingMap().get(this.getSelf()) * ReactorBlockEntity.FACTOR)).withStyle(ChatFormatting.LIGHT_PURPLE));
            }
        }
    }

    default void appendOptionalItemHoverText(Player player, Item.TooltipContext context, Consumer<Component> tooltipAdder, String suffix, Predicate<AbstractContainerMenu> condition)
    {
        if (player != null && condition.test(player.containerMenu))
        {
            this.getOptionalItemDescription(suffix).ifPresent(description -> tooltipAdder.accept(description.withStyle(ChatFormatting.GOLD)));
        }
    }

    default Optional<MutableComponent> getOptionalItemDescription(int index)
    {
        return this.getOptionalItemDescription(".desc" + (index != 1 ? "_"+index : ""));
    }

    default Optional<MutableComponent > getOptionalItemDescription(String suffix)
    {
        String key = this.getSelf().getDescriptionId() + suffix;
        return Language.getInstance().has(key) ? Optional.of(Component.translatable(key).withStyle(ChatFormatting.GRAY)) : Optional.empty();
    }

    default Optional<MutableComponent > getOptionalDescription(String key)
    {
        return Language.getInstance().has(key) ? Optional.of(Component.translatable(key).withStyle(ChatFormatting.GRAY)) : Optional.empty();
    }
}
