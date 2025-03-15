package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Entity.AerialHellPaintingEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtOps;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

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

    @Override public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type)
    {
        super.appendTooltip(stack, context, tooltip, type);
        RegistryWrapper.WrapperLookup wrapperLookup = context.getRegistryLookup();
        if (wrapperLookup != null /*&& this.type == AerialHellEntities.AERIAL_HELL_PAINTING.get()*/)
        {
            NbtComponent nbtComponent = stack.getOrDefault(DataComponentTypes.ENTITY_DATA, NbtComponent.DEFAULT);
            if (!nbtComponent.isEmpty())
            {
                nbtComponent.get(wrapperLookup.getOps(NbtOps.INSTANCE), PaintingEntity.VARIANT_MAP_CODEC).result().ifPresentOrElse(variant -> {
                    variant.getKey().ifPresent(key ->
                    {
                        tooltip.add(Text.translatable(key.getValue().toTranslationKey("painting", "title")).formatted(Formatting.YELLOW));
                        tooltip.add(Text.translatable(key.getValue().toTranslationKey("painting", "author")).formatted(Formatting.GRAY));
                    });
                    tooltip.add(Text.translatable("painting.dimensions", ((PaintingVariant)variant.value()).width(), ((PaintingVariant)variant.value()).height()));
                }, () -> tooltip.add(TOOLTIP_RANDOM_VARIANT));
            }
            else if (type.isCreative()) {tooltip.add(TOOLTIP_RANDOM_VARIANT);}
        }
    }
}
