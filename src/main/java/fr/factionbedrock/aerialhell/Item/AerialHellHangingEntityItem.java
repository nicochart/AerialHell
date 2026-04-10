package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Entity.AerialHellPaintingEntity;
import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

/* Copy of net.minecraft.item.DecorationItem but for Aerial Hell paintings */

public class AerialHellHangingEntityItem extends Item
{
    private static final Component TOOLTIP_RANDOM_VARIANT = Component.translatable("painting.random").withStyle(ChatFormatting.GRAY);
    private final EntityType<? extends HangingEntity> entityType;

    public AerialHellHangingEntityItem(EntityType<? extends HangingEntity> type, Item.Properties settings)
    {
        super(settings);
        this.entityType = type;
    }

    @Override public InteractionResult useOn(UseOnContext context)
    {
        BlockPos blockPos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        BlockPos blockPos2 = blockPos.relative(direction);
        Player playerEntity = context.getPlayer();
        ItemStack itemStack = context.getItemInHand();
        if (playerEntity != null && !this.mayPlace(playerEntity, direction, itemStack, blockPos2)) {return InteractionResult.FAIL;}
        else
        {
            Level world = context.getLevel();
            HangingEntity abstractDecorationEntity;
            //if (this.type.get() == AerialHellEntities.AERIAL_HELL_PAINTING.get()) //always true atm
            //{
                Optional<AerialHellPaintingEntity> optional = AerialHellPaintingEntity.create(world, blockPos2, direction);
                if (optional.isEmpty()) {return InteractionResult.CONSUME;}

                abstractDecorationEntity = optional.get();
            //}

            if (abstractDecorationEntity.survives())
            {
                if (!world.isClientSide())
                {
                    abstractDecorationEntity.playPlacementSound();
                    world.gameEvent(playerEntity, GameEvent.ENTITY_PLACE, abstractDecorationEntity.position());
                    world.addFreshEntity(abstractDecorationEntity);
                }

                itemStack.shrink(1);
                return InteractionResult.SUCCESS;
            }
            else {return InteractionResult.CONSUME;}
        }
    }

    protected boolean mayPlace(Player player, Direction side, ItemStack stack, BlockPos pos)
    {
        return !side.getAxis().isVertical() && player.mayUseItemAt(pos, side, stack);
    }

    @Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type)
    {
        //if (this.entityType == AerialHellEntities.AERIAL_HELL_PAINTING && displayComponent.shouldDisplay(DataComponentTypes.PAINTING_VARIANT))
        //{
            Holder<PaintingVariant> registryEntry = stack.get(DataComponents.PAINTING_VARIANT);
            if (registryEntry != null)
            {
                registryEntry.value().title().ifPresent(textConsumer);
                registryEntry.value().author().ifPresent(textConsumer);
                textConsumer.accept(Component.translatable("painting.dimensions", registryEntry.value().width(), registryEntry.value().height()));
            }
            else if (type.isCreative()) {textConsumer.accept(TOOLTIP_RANDOM_VARIANT);}
        //}
    }
}
