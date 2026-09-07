package fr.factionbedrock.aerialhell.Item.Tools;

import fr.factionbedrock.aerialhell.Client.Util.ClientHelper;
import fr.factionbedrock.aerialhell.Item.Ability.AbilitySelector;
import fr.factionbedrock.aerialhell.Item.AerialHellItem;
import fr.factionbedrock.aerialhell.Item.AerialHellItemInterface;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AerialHellAxeItem extends AxeItem implements AerialHellItemInterface
{
    public final int maxUseDuration;
    public final int enchantmentValue;
    public final boolean canDisableShield;
    public final Ingredient repairIngredient;
    public final UseAnim itemUseAnimation;
    @Nullable public final AbilitySelector abilitySelector;
    public final List<AerialHellItem.UseInteractionType> useInteractionToolTypes;

    public AerialHellAxeItem(AerialHellItem.Properties properties)
    {
        super(Tiers.IRON /*tier behavior is overridden*/, properties);
        this.components = properties.buildAndValidateComponents();
        this.maxUseDuration = properties.maxUseDuration();
        this.enchantmentValue = properties.enchantmentValue();
        this.canDisableShield = properties.canDisableShield();
        this.repairIngredient = properties.repairIngredient();
        this.itemUseAnimation = properties.itemUseAnimation();
        this.abilitySelector = properties.abilitySelector();
        this.useInteractionToolTypes = properties.useInteractionTypes();
    }

    @Override public Item getSelf() {return this;}
    @Override public AbilitySelector abilitySelector() {return this.abilitySelector;}
    @Override public int maxUseDuration() {return this.maxUseDuration;}
    @Override public List<AerialHellItem.UseInteractionType> useInteractionToolTypes() {return this.useInteractionToolTypes;}
    @Override public UseAnim itemUseAnimation() {return this.itemUseAnimation;}

    @Override public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, List<Component> components, TooltipFlag tooltipFlag)
    {
        this.appendOptionalDescriptionsHoverText(tooltipContext, components);
        this.appendAbilityDescriptionHoverText(ClientHelper.getLocalPlayer(), tooltipContext, components);
        this.appendReactorMenuHoverText(ClientHelper.getLocalPlayer(), tooltipContext, components);
    }

    //applying tick (passive) tool ability modules
    @Override public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {this.ahInventoryTick(stack, level, entity, slotId, isSelected);}

    //applying use tool ability modules
    @Override public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {return this.ahUse(level, player, hand, super::use);}

    @Override public int getUseDuration(ItemStack itemStack, LivingEntity user) {return this.ahGetUseDuration(itemStack, user, super::getUseDuration);}

    @Override public UseAnim getUseAnimation(ItemStack itemStack) {return this.ahGetUseAnimation(itemStack, super::getUseAnimation);}

    //applying releaseUsing tool ability modules
    @Override public void releaseUsing(ItemStack itemStack, Level level, LivingEntity itemOwner, int remainingTime) {this.ahReleaseUsing(itemStack, level, itemOwner, remainingTime);}

    @Override public int getEnchantmentValue() {return this.enchantmentValue;}

    @Override public boolean isValidRepairItem(ItemStack toRepair, ItemStack repairIngredient) {return this.repairIngredient.test(repairIngredient) || super.isValidRepairItem(toRepair, repairIngredient);}

    @Override public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {return this.canDisableShield;}

    @Override public InteractionResult useOn(UseOnContext context) {return this.ahUseOn(context);}

    @Override public boolean canPerformAction(ItemStack stack, net.neoforged.neoforge.common.ItemAbility itemAbility) {return super.canPerformAction(stack, itemAbility) || this.ahCanPerformAction(stack, itemAbility);}
}