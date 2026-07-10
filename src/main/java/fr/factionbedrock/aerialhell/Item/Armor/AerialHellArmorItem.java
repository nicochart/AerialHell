package fr.factionbedrock.aerialhell.Item.Armor;

import com.mojang.blaze3d.platform.InputConstants;
import fr.factionbedrock.aerialhell.Client.Util.ClientHelper;
import fr.factionbedrock.aerialhell.Item.Ability.*;
import fr.factionbedrock.aerialhell.Item.AerialHellItem;
import fr.factionbedrock.aerialhell.Item.ExtraHoverTextItem;
import fr.factionbedrock.aerialhell.Item.Material.AerialHellArmorMaterial;
import fr.factionbedrock.aerialhell.Item.Material.AttributeEntryList;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.util.List;
import java.util.function.Supplier;

public class AerialHellArmorItem extends ArmorItem implements ExtraHoverTextItem
{
    private final Supplier<ItemAttributeModifiers> aerialHellDefaultModifiers;
    @Nullable public final AbilitySelector abilitySelector;
    public final int maxUseDuration;
    public AerialHellArmorItem(AerialHellArmorMaterial armorMaterial, Type type, AerialHellItem.Properties properties) {this(armorMaterial, type, new AttributeEntryList(), properties);}
    public AerialHellArmorItem(AerialHellArmorMaterial armorMaterial, Type type, AttributeEntryList additionalAttributes, AerialHellItem.Properties properties)
    {
        super(armorMaterial.vanillaMaterial, type, properties.durability(type.getDurability(armorMaterial.durability)));
        this.abilitySelector = properties.abilitySelector();
        this.maxUseDuration = properties.maxUseDuration();
        aerialHellDefaultModifiers = armorMaterial.createAttributes(type, additionalAttributes);
    }

    @Override public ItemAttributeModifiers getDefaultAttributeModifiers()
    {
        return this.aerialHellDefaultModifiers.get();
    }

    /* ----------------------------------------------------------------
        Copy of AerialHellItem methods for applying ability modules
     ---------------------------------------------------------------- */

    //applying tick (passive) tool ability modules
    @Override public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected)
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

        if (this.abilitySelector != null && entity instanceof LivingEntity itemOwner && entity.tickCount % 10 == 0)
        {
            @Nullable UsingItemUseSituationInfo usingItemUseSituationInfo = new UsingItemUseSituationInfo(itemOwner instanceof Player player && player.isUsingItem() ? player.getTicksUsingItem() : 0);
            this.abilitySelector.tryUseAbility(new AbilityUseSituation.Tick(stack, itemOwner, slot, usingItemUseSituationInfo));
        }
    }

    //applying use tool ability modules
    @Override public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
    {
        ItemStack heldItemStack = player.getItemInHand(hand);
        boolean used = false;
        if (this.abilitySelector != null) {used = this.abilitySelector.tryUseAbility(new AbilityUseSituation.OnUse(heldItemStack, player, LivingEntity.getSlotForHand(hand)));}
        if (used && this.maxUseDuration != 0) {player.startUsingItem(hand);}
        return used ? InteractionResultHolder.consume(heldItemStack) : super.use(level, player, hand);
    }

    //applying releaseUsing tool ability modules
    @Override public void releaseUsing(ItemStack itemStack, Level level, LivingEntity itemOwner, int remainingTime)
    {
        int ticksUsed = this.getUseDuration(itemStack, itemOwner) - remainingTime;
        boolean used = false;
        if (this.abilitySelector != null) {used = this.abilitySelector.tryUseAbility(new AbilityUseSituation.OnReleaseUsing(itemStack, itemOwner, new UsingItemUseSituationInfo(ticksUsed)));}
    }

    //applying onDealDamage (semi-passive) tool ability modules
    //enemy entity (stored in damageInfo) is taking damage from item owner
    public void onDealDamage(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot slot, DamageUseSituationInfo damageInfo)
    {
        if (this.abilitySelector != null) {this.abilitySelector.tryUseAbility(new AbilityUseSituation.OnDealDamage(itemStack, itemOwner, slot, damageInfo));}
    }

    //applying onTakeDamage (semi-passive) tool ability modules
    //item owner is taking damage from enemy attacker (stored in damageInfo)
    public void onTakeDamage(ItemStack itemStack, LivingEntity itemOwner, @Nullable EquipmentSlot slot, DamageUseSituationInfo damageInfo)
    {
        if (this.abilitySelector != null) {this.abilitySelector.tryUseAbility(new AbilityUseSituation.OnTakeDamage(itemStack, itemOwner, slot, damageInfo));}
    }

    //applying onMining (semi-passive) tool ability modules
    public void onMining(ItemStack itemStack, LivingEntity itemOwner, MiningUseSituationInfo miningInfo)
    {
        if (this.abilitySelector != null) {this.abilitySelector.tryUseAbility(new AbilityUseSituation.OnMining(itemStack, itemOwner, miningInfo));}
    }

    /* ------------------------------------------------------------------------
        Copy of AerialHellItem & WithInformationItem methods for custom descs
     ------------------------------------------------------------------------ */

    @Override public void appendAbilityDescriptionHoverText(Player player, TooltipContext context, List<Component> tooltipAdder)
    {
        if (!player.level().isClientSide() || this.abilitySelector == null) {return;}

        //context.player().isShiftKeyDown() do not work here because there is a screen open
        boolean shiftDown = InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT) || InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_RIGHT_SHIFT);
        List<String> descIds = this.abilitySelector.getAbilitiesDescIds();

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

    @Override public void appendHoverText(ItemStack stack, TooltipContext tooltipContext, List<Component> components, TooltipFlag tooltipFlag)
    {
        this.appendOptionalDescriptionsHoverText(tooltipContext, components);
        this.appendAbilityDescriptionHoverText(ClientHelper.getLocalPlayer(), tooltipContext, components);
        this.appendReactorMenuHoverText(ClientHelper.getLocalPlayer(), tooltipContext, components);
    }

    @Override public Item getSelf() {return this;}
}
