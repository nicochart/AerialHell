package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Entity.AerialHellPaintingEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

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
                if (!world.isClientSide)
                {
                    abstractDecorationEntity.playPlacementSound();
                    world.gameEvent(playerEntity, GameEvent.ENTITY_PLACE, abstractDecorationEntity.position());
                    world.addFreshEntity(abstractDecorationEntity);
                }

                itemStack.shrink(1);
                return InteractionResult.sidedSuccess(world.isClientSide);
            }
            else {return InteractionResult.CONSUME;}
        }
    }

    protected boolean mayPlace(Player player, Direction side, ItemStack stack, BlockPos pos)
    {
        return !side.getAxis().isVertical() && player.mayUseItemAt(pos, side, stack);
    }

    @Override public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type)
    {
        super.appendHoverText(stack, context, tooltip, type);
        HolderLookup.Provider wrapperLookup = context.registries();
        if (wrapperLookup != null /*&& this.type == AerialHellEntities.AERIAL_HELL_PAINTING.get()*/)
        {
            CustomData nbtComponent = stack.getOrDefault(DataComponents.ENTITY_DATA, CustomData.EMPTY);
            if (!nbtComponent.isEmpty())
            {
                nbtComponent.read(wrapperLookup.createSerializationContext(NbtOps.INSTANCE), Painting.VARIANT_MAP_CODEC).result().ifPresentOrElse(variant -> {
                    variant.unwrapKey().ifPresent(key ->
                    {
                        tooltip.add(Component.translatable(key.location().toLanguageKey("painting", "title")).withStyle(ChatFormatting.YELLOW));
                        tooltip.add(Component.translatable(key.location().toLanguageKey("painting", "author")).withStyle(ChatFormatting.GRAY));
                    });
                    tooltip.add(Component.translatable("painting.dimensions", ((PaintingVariant)variant.value()).width(), ((PaintingVariant)variant.value()).height()));
                }, () -> tooltip.add(TOOLTIP_RANDOM_VARIANT));
            }
            else if (type.isCreative()) {tooltip.add(TOOLTIP_RANDOM_VARIANT);}
        }
    }
}
