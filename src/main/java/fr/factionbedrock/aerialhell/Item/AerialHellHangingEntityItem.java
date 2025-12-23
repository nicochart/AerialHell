package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Entity.AerialHellPaintingEntity;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* Copy of net.minecraft.item.HangingEntityItem but for Aerial Hell paintings */

public class AerialHellHangingEntityItem extends Item
{
    private static final Component TOOLTIP_RANDOM_VARIANT = Component.translatable("painting.random").withStyle(ChatFormatting.GRAY);
    private final Supplier<EntityType<? extends HangingEntity>> type;

    public AerialHellHangingEntityItem(Supplier<EntityType<? extends HangingEntity>> entityType, Item.Properties properties)
    {
        super(properties);
        this.type = entityType;
    }

    public InteractionResult useOn(UseOnContext context)
    {
        BlockPos blockpos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        BlockPos blockpos1 = blockpos.relative(direction);
        Player player = context.getPlayer();
        ItemStack itemstack = context.getItemInHand();
        if (player != null && !this.mayPlace(player, direction, itemstack, blockpos1)) {return InteractionResult.FAIL;}
        else
        {
            Level level = context.getLevel();
            HangingEntity hangingentity;
            //if (this.type.get() == AerialHellEntities.AERIAL_HELL_PAINTING.get()) //always true atm
            //{
                Optional<AerialHellPaintingEntity> optional = AerialHellPaintingEntity.create(level, blockpos1, direction);
                if (optional.isEmpty()) {return InteractionResult.CONSUME;}
                hangingentity = optional.get();
            //}

            if (hangingentity.survives())
            {
                if (!level.isClientSide())
                {
                    hangingentity.playPlacementSound();
                    level.gameEvent(player, GameEvent.ENTITY_PLACE, hangingentity.position());
                    level.addFreshEntity(hangingentity);
                }

                itemstack.shrink(1);
                return InteractionResult.SUCCESS;
            }
            else {return InteractionResult.CONSUME;}
        }
    }

    protected boolean mayPlace(Player playerIn, Direction directionIn, ItemStack itemStackIn, BlockPos posIn)
    {
        return !directionIn.getAxis().isVertical() && playerIn.mayUseItemAt(posIn, directionIn, itemStackIn);
    }

    @Override public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag)
    {
        super.appendHoverText(stack, tooltipContext, tooltipDisplay, tooltipAdder, tooltipFlag);
        //if (this.type == AerialHellEntities.AERIAL_HELL_PAINTING.get() && tooltipDisplay.shows(DataComponents.PAINTING_VARIANT))
        //{
            Holder<PaintingVariant> holder = stack.get(DataComponents.PAINTING_VARIANT);
            if (holder != null)
            {
                (holder.value()).title().ifPresent(tooltipAdder);
                (holder.value()).author().ifPresent(tooltipAdder);
                tooltipAdder.accept(Component.translatable("painting.dimensions", new Object[]{((PaintingVariant)holder.value()).width(), ((PaintingVariant)holder.value()).height()}));
            }
            else if (tooltipFlag.isCreative()) {tooltipAdder.accept(TOOLTIP_RANDOM_VARIANT);}
        //}
    }
}
