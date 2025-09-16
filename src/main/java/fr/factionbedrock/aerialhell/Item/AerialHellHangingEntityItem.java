package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Entity.AerialHellPaintingEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Optional;
import java.util.function.Consumer;

/* Copy of net.minecraft.item.DecorationItem but for Aerial Hell paintings */

public class AerialHellHangingEntityItem extends Item
{
    private static final Text TOOLTIP_RANDOM_VARIANT = Text.translatable("painting.random").formatted(Formatting.GRAY);
    private final EntityType<? extends AbstractDecorationEntity> entityType;

    public AerialHellHangingEntityItem(EntityType<? extends AbstractDecorationEntity> type, Item.Settings settings)
    {
        super(settings);
        this.entityType = type;
    }

    @Override public ActionResult useOnBlock(ItemUsageContext context)
    {
        BlockPos blockPos = context.getBlockPos();
        Direction direction = context.getSide();
        BlockPos blockPos2 = blockPos.offset(direction);
        PlayerEntity playerEntity = context.getPlayer();
        ItemStack itemStack = context.getStack();
        if (playerEntity != null && !this.mayPlace(playerEntity, direction, itemStack, blockPos2)) {return ActionResult.FAIL;}
        else
        {
            World world = context.getWorld();
            AbstractDecorationEntity abstractDecorationEntity;
            //if (this.type.get() == AerialHellEntities.AERIAL_HELL_PAINTING.get()) //always true atm
            //{
                Optional<AerialHellPaintingEntity> optional = AerialHellPaintingEntity.create(world, blockPos2, direction);
                if (optional.isEmpty()) {return ActionResult.CONSUME;}

                abstractDecorationEntity = optional.get();
            //}

            if (abstractDecorationEntity.canStayAttached())
            {
                if (!world.isClient)
                {
                    abstractDecorationEntity.onPlace();
                    world.emitGameEvent(playerEntity, GameEvent.ENTITY_PLACE, abstractDecorationEntity.getPos());
                    world.spawnEntity(abstractDecorationEntity);
                }

                itemStack.decrement(1);
                return ActionResult.SUCCESS;
            }
            else {return ActionResult.CONSUME;}
        }
    }

    protected boolean mayPlace(PlayerEntity player, Direction side, ItemStack stack, BlockPos pos)
    {
        return !side.getAxis().isVertical() && player.canPlaceOn(pos, side, stack);
    }

    @Override public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type)
    {
        //if (this.entityType == AerialHellEntities.AERIAL_HELL_PAINTING && displayComponent.shouldDisplay(DataComponentTypes.PAINTING_VARIANT))
        //{
            RegistryEntry<PaintingVariant> registryEntry = stack.get(DataComponentTypes.PAINTING_VARIANT);
            if (registryEntry != null)
            {
                registryEntry.value().title().ifPresent(textConsumer);
                registryEntry.value().author().ifPresent(textConsumer);
                textConsumer.accept(Text.translatable("painting.dimensions", registryEntry.value().width(), registryEntry.value().height()));
            }
            else if (type.isCreative()) {textConsumer.accept(TOOLTIP_RANDOM_VARIANT);}
        //}
    }
}
