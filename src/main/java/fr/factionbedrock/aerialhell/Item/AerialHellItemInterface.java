package fr.factionbedrock.aerialhell.Item;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.datafixers.util.Pair;
import fr.factionbedrock.aerialhell.Item.Ability.*;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

//using interface instead of global class AerialHellItem
//because for compatibility issues (back in 1.21.1)
//some other mods check if item instanceof SwordItem, AxeItem, PickaxeItem...

// For Items with AerialHellToolMaterials : inspired of vanilla Item class, with custom AerialhellItem.Properties properties
// For Tools : Inspired of vanilla AxeItem, HoeItem and ShovelItem, but only takes Item.Properties as constructor parameter.
// AxeItem, HoeItem and ShovelItem interaction abilities are all managed.
// To make the item behave like a tool, call properties.sword(...), properties.pickaxe(...), properties.axe(...), properties.hoe(...), properties.shovel(...) in properties before passing them to the constructor.
// To manage AxeItem, HoeItem and ShovelItem interaction abilities, think about calling .useInteraction(...) if you want your tool to be able to strip, flatten or till.
public interface AerialHellItemInterface extends ExtraHoverTextItem
{
    Item getSelf();
    AbilitySelector abilitySelector();
    int maxUseDuration();
    List<AerialHellItem.UseInteractionType> useInteractionToolTypes();
    UseAnim itemUseAnimation();

    //applying tick (passive) tool ability modules
    default void ahInventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected)
    {
        if (level.isClientSide) {return;}
        //temporary solution to get slot
        EquipmentSlot slot = null;
        if (entity instanceof LivingEntity livingEntity)
        {
            if (livingEntity.getItemBySlot(EquipmentSlot.MAINHAND) == stack) {slot = EquipmentSlot.MAINHAND;}
            else if (livingEntity.getItemBySlot(EquipmentSlot.OFFHAND) == stack) {slot = EquipmentSlot.OFFHAND;}
            else if (livingEntity.getItemBySlot(EquipmentSlot.FEET) == stack) {slot = EquipmentSlot.FEET;}
            else if (livingEntity.getItemBySlot(EquipmentSlot.LEGS) == stack) {slot = EquipmentSlot.LEGS;}
            else if (livingEntity.getItemBySlot(EquipmentSlot.CHEST) == stack) {slot = EquipmentSlot.CHEST;}
            else if (livingEntity.getItemBySlot(EquipmentSlot.HEAD) == stack) {slot = EquipmentSlot.HEAD;}
        }

        if (this.abilitySelector() != null && entity instanceof LivingEntity itemOwner && entity.tickCount % 10 == 0)
        {
            @Nullable UsingItemUseSituationInfo usingItemUseSituationInfo = new UsingItemUseSituationInfo(itemOwner instanceof Player player && player.isUsingItem() ? player.getTicksUsingItem() : 0);
            this.abilitySelector().tryUseAbility(new AbilityUseSituation.Tick(stack, itemOwner, slot, usingItemUseSituationInfo));
        }
    }

    //applying use tool ability modules
    default InteractionResultHolder<ItemStack> ahUse(Level level, Player player, InteractionHand hand, SuperUseReference superReference)
    {
        ItemStack heldItemStack = player.getItemInHand(hand);
        boolean used = false;
        if (this.abilitySelector() != null) {used = this.abilitySelector().tryUseAbility(new AbilityUseSituation.OnUse(heldItemStack, player, LivingEntity.getSlotForHand(hand)));}
        if (used && this.maxUseDuration() != 0) {player.startUsingItem(hand);}
        return used ? InteractionResultHolder.consume(heldItemStack) : superReference.use(level, player, hand);
    }

    default int ahGetUseDuration(ItemStack itemStack, LivingEntity user, SuperGetUseDurationReference superReference)
    {
        int vanillaUseDuration = superReference.getUseDuration(itemStack, user);
        return vanillaUseDuration > 0 ? vanillaUseDuration : this.maxUseDuration();
    }

    default UseAnim ahGetUseAnimation(ItemStack itemStack, SuperGetUseAnimationReference superReference)
    {
        UseAnim vanillaUseAnimation = superReference.getUseAnimation(itemStack);
        return vanillaUseAnimation != UseAnim.NONE ? vanillaUseAnimation : this.itemUseAnimation();
    }

    //applying releaseUsing tool ability modules
    default void ahReleaseUsing(ItemStack itemStack, Level level, LivingEntity itemOwner, int remainingTime)
    {
        int ticksUsed = this.getSelf().getUseDuration(itemStack, itemOwner) - remainingTime;
        boolean used = false;
        if (this.abilitySelector() != null) {used = this.abilitySelector().tryUseAbility(new AbilityUseSituation.OnReleaseUsing(itemStack, itemOwner, new UsingItemUseSituationInfo(ticksUsed)));}
    }

    //applying onDealDamage (semi-passive) tool ability modules
    //enemy entity (stored in damageInfo) is taking damage from item owner
    default void onDealDamage(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot slot, DamageUseSituationInfo damageInfo)
    {
        if (this.abilitySelector() != null) {this.abilitySelector().tryUseAbility(new AbilityUseSituation.OnDealDamage(itemStack, itemOwner, slot, damageInfo));}
    }

    //applying onTakeDamage (semi-passive) tool ability modules
    //item owner is taking damage from enemy attacker (stored in damageInfo)
    default void onTakeDamage(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot slot, DamageUseSituationInfo damageInfo)
    {
        if (this.abilitySelector() != null) {this.abilitySelector().tryUseAbility(new AbilityUseSituation.OnTakeDamage(itemStack, itemOwner, slot, damageInfo));}
    }

    //applying onMining (semi-passive) tool ability modules
    default void onMining(ItemStack itemStack, LivingEntity itemOwner, MiningUseSituationInfo miningInfo)
    {
        if (this.abilitySelector() != null) {this.abilitySelector().tryUseAbility(new AbilityUseSituation.OnMining(itemStack, itemOwner, miningInfo));}
    }

    @Override default void appendAbilityDescriptionHoverText(Player player, Item.TooltipContext context, List<Component> tooltipAdder)
    {
        if (!player.level().isClientSide() || this.abilitySelector() == null) {return;}

        //context.player().isShiftKeyDown() do not work here because there is a screen open
        boolean shiftDown = InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) || InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_RIGHT_SHIFT);
        List<String> descIds = this.abilitySelector().getAbilitiesDescIds();

        boolean hasAbilityDetail = false;
        for (String descId : descIds)
        {
            if (Language.getInstance().has("ability.aerialhell."+descId+".desc"))
            {
                hasAbilityDetail = true;
                break;
            }
        }

        if (!shiftDown)
        {
            if (hasAbilityDetail) {tooltipAdder.add(Component.translatable("ability.aerialhell.shift_key_up").withStyle(ChatFormatting.DARK_GRAY));}
            return;
        }

        for (String descId : descIds)
        {
            if (descId.isEmpty()) {continue;}
            this.appendOptionalDescriptionHoverText(context, tooltipAdder, "ability.aerialhell."+descId+".desc", ChatFormatting.GRAY);
            this.appendOptionalDescriptionHoverText(context, tooltipAdder, "ability.aerialhell."+descId+".desc_2", ChatFormatting.GRAY);
            this.appendOptionalDescriptionHoverText(context, tooltipAdder, "ability.aerialhell."+descId+".desc_3", ChatFormatting.GRAY);
            this.appendOptionalDescriptionHoverText(context, tooltipAdder, "ability.aerialhell."+descId+".desc_4", ChatFormatting.GRAY);
            this.appendOptionalDescriptionHoverText(context, tooltipAdder, "ability.aerialhell."+descId+".desc_5", ChatFormatting.GRAY);
            this.appendOptionalDescriptionHoverText(context, tooltipAdder, "ability.aerialhell."+descId+".desc_6", ChatFormatting.GRAY);
            this.appendOptionalDescriptionHoverText(context, tooltipAdder, "ability.aerialhell."+descId+".condition.desc", ChatFormatting.GRAY);
            this.appendOptionalDescriptionHoverText(context, tooltipAdder, "ability.aerialhell."+descId+".cooldown.desc", ChatFormatting.GRAY);
        }
    }

    //inspired of vanilla ShovelItem, AxeItem and HoeItem
    default InteractionResult ahUseOn(UseOnContext context)
    {
        InteractionResult result = InteractionResult.PASS;
        if (this.useInteractionToolTypes().contains(AerialHellItem.UseInteractionType.AXE))
        {
            result = this.useAxeOn(context);
            if (result != InteractionResult.PASS) {return result;}
        }

        if (this.useInteractionToolTypes().contains(AerialHellItem.UseInteractionType.HOE))
        {
            result = this.useHoeOn(context);
            if (result != InteractionResult.PASS) {return result;}
        }

        if (this.useInteractionToolTypes().contains(AerialHellItem.UseInteractionType.SHOVEL))
        {
            result = this.useShovelOn(context);
            if (result != InteractionResult.PASS) {return result;}
        }

        return result;
    }

    //AxeItem useOn
    default InteractionResult useAxeOn(UseOnContext context)
    {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        if (playerHasBlockingItemUseIntent(context)) {return InteractionResult.PASS;}
        else
        {
            Optional<BlockState> newBlock = this.axeEvaluateNewBlockState(level, pos, player, level.getBlockState(pos), context);
            if (newBlock.isEmpty()) {return InteractionResult.PASS;}
            else
            {
                ItemStack itemInHand = context.getItemInHand();
                if (player instanceof ServerPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)player, pos, itemInHand);}

                level.setBlock(pos, newBlock.get(), 11);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newBlock.get()));
                if (player != null) {itemInHand.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));}

                return InteractionResult.SUCCESS;
            }
        }
    }

    //HoeItem useOn
    default InteractionResult useHoeOn(UseOnContext context)
    {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState toolModifiedState = level.getBlockState(pos).getToolModifiedState(context, ItemAbilities.HOE_TILL, false);
        Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> logicPair = toolModifiedState == null ? null : Pair.of((Predicate)(ctx) -> true, hoeTillChangeIntoState(toolModifiedState));
        if (logicPair == null) {return InteractionResult.PASS;}
        else
        {
            Predicate<UseOnContext> predicate = logicPair.getFirst();
            Consumer<UseOnContext> action = logicPair.getSecond();
            if (predicate.test(context))
            {
                Player player = context.getPlayer();
                level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!level.isClientSide())
                {
                    action.accept(context);
                    if (player != null) {context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));}
                }
                return InteractionResult.SUCCESS;
            }
            else {return InteractionResult.PASS;}
        }
    }

    //ShovelItem useOn
    default InteractionResult useShovelOn(UseOnContext context)
    {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState blockState = level.getBlockState(pos);
        Player player = context.getPlayer();
        if (context.getClickedFace() == Direction.DOWN) {return InteractionResult.PASS;}
        else
        {
            BlockState newState = blockState.getToolModifiedState(context, ItemAbilities.SHOVEL_FLATTEN, false);
            BlockState updatedState = null;
            if (newState != null && level.getBlockState(pos.above()).isAir())
            {
                level.playSound(player, pos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                updatedState = newState;
            }
            else if ((updatedState = blockState.getToolModifiedState(context, ItemAbilities.SHOVEL_DOUSE, false)) != null && !level.isClientSide())
            {
                level.levelEvent(player, 1009, pos, 0);
            }

            if (updatedState != null)
            {
                if (!level.isClientSide())
                {
                    level.setBlock(pos, updatedState, 11);
                    level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, updatedState));
                    if (player != null) {context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));}
                }

                return InteractionResult.SUCCESS;
            }
            else {return InteractionResult.PASS;}
        }
    }

    //copy of AxeItem method of same name
    private static boolean playerHasBlockingItemUseIntent(UseOnContext context)
    {
        Player player = context.getPlayer();
        return player != null && context.getHand().equals(InteractionHand.MAIN_HAND) && player.getOffhandItem().is(Items.SHIELD) && !player.isSecondaryUseActive();
    }

    //copy of AxeItem evaluateNewBlockState method
    private Optional<BlockState> axeEvaluateNewBlockState(Level level, BlockPos pos, @Nullable Player player, BlockState oldState, UseOnContext context)
    {
        Optional<BlockState> strippedBlock = Optional.ofNullable(oldState.getToolModifiedState(context, ItemAbilities.AXE_STRIP, false));
        if (strippedBlock.isPresent())
        {
            level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
            return strippedBlock;
        }
        else
        {
            Optional<BlockState> scrapedBlock = Optional.ofNullable(oldState.getToolModifiedState(context, ItemAbilities.AXE_SCRAPE, false));
            if (scrapedBlock.isPresent())
            {
                axeSpawnSoundAndParticle(level, pos, player, oldState, SoundEvents.AXE_SCRAPE, 3005);
                return scrapedBlock;
            }
            else
            {
                Optional<BlockState> waxoffBlock = Optional.ofNullable(oldState.getToolModifiedState(context, ItemAbilities.AXE_WAX_OFF, false));
                if (waxoffBlock.isPresent())
                {
                    axeSpawnSoundAndParticle(level, pos, player, oldState, SoundEvents.AXE_WAX_OFF, 3004);
                    return waxoffBlock;
                }
                else {return Optional.empty();}
            }
        }
    }

    //copy of AxeItem spawnSoundAndParticle method
    private static void axeSpawnSoundAndParticle(Level level, BlockPos pos, @Nullable Player player, BlockState oldState, SoundEvent soundEvent, int particle)
    {
        level.playSound(player, pos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
        level.levelEvent(player, particle, pos, 0);
    }

    //copy of HoeItem changeIntoState method
    private static Consumer<UseOnContext> hoeTillChangeIntoState(BlockState state)
    {
        return (context) ->
        {
            context.getLevel().setBlock(context.getClickedPos(), state, 11);
            context.getLevel().gameEvent(GameEvent.BLOCK_CHANGE, context.getClickedPos(), GameEvent.Context.of(context.getPlayer(), state));
        };
    }

    //inspired of AxeItem, HoeItem and ShovelItem methods of same name
    default boolean ahCanPerformAction(ItemStack stack, net.neoforged.neoforge.common.ItemAbility itemAbility)
    {
        boolean canPerformAxeAction = this.useInteractionToolTypes().contains(AerialHellItem.UseInteractionType.AXE) && ItemAbilities.DEFAULT_AXE_ACTIONS.contains(itemAbility);
        boolean canPerformHoeAction = this.useInteractionToolTypes().contains(AerialHellItem.UseInteractionType.HOE) && ItemAbilities.DEFAULT_HOE_ACTIONS.contains(itemAbility);
        boolean canPerformShovelAction = this.useInteractionToolTypes().contains(AerialHellItem.UseInteractionType.SHOVEL) && ItemAbilities.DEFAULT_SHOVEL_ACTIONS.contains(itemAbility);

        return canPerformAxeAction || canPerformHoeAction || canPerformShovelAction;
    }

    @FunctionalInterface interface SuperGetUseDurationReference{int getUseDuration(ItemStack stack, LivingEntity livingEntity);}
    @FunctionalInterface interface SuperGetUseAnimationReference{UseAnim getUseAnimation(ItemStack stack);}
    @FunctionalInterface interface SuperUseReference{InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand);}
}
