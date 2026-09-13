package fr.factionbedrock.aerialhell.Item.Armor;

import fr.factionbedrock.aerialhell.Client.Util.ClientHelper;
import fr.factionbedrock.aerialhell.Item.Ability.*;
import fr.factionbedrock.aerialhell.Item.AerialHellItem;
import fr.factionbedrock.aerialhell.Item.AerialHellItemInterface;
import fr.factionbedrock.aerialhell.Item.Material.AerialHellArmorMaterial;
import fr.factionbedrock.aerialhell.Item.Material.AttributeEntryList;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.annotation.Nullable;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Supplier;

public class AerialHellArmorItem extends ArmorItem implements AerialHellItemInterface
{
    private final Supplier<ItemAttributeModifiers> aerialHellDefaultModifiers;
    public final UseAnim itemUseAnimation;
    @Nullable public final AbilitySelector abilitySelector;
    public final boolean canDisableShield;
    public final List<AerialHellItem.UseInteractionType> useInteractionToolTypes;
    public final int maxUseDuration;
    public AerialHellArmorItem(AerialHellArmorMaterial armorMaterial, Type type, AerialHellItem.Properties properties) {this(armorMaterial, type, new AttributeEntryList(), properties);}
    public AerialHellArmorItem(AerialHellArmorMaterial armorMaterial, Type type, AttributeEntryList additionalAttributes, AerialHellItem.Properties properties)
    {
        super(armorMaterial.vanillaMaterial, type, properties.durability(type.getDurability(armorMaterial.durability)));
        this.itemUseAnimation = properties.itemUseAnimation();
        this.abilitySelector = properties.abilitySelector();
        this.canDisableShield = properties.canDisableShield();
        this.useInteractionToolTypes = properties.useInteractionTypes();
        this.maxUseDuration = properties.maxUseDuration();
        aerialHellDefaultModifiers = armorMaterial.createAttributes(type, additionalAttributes);
    }

    @Override public Item getSelf() {return this;}
    @Override public AbilitySelector abilitySelector() {return this.abilitySelector;}
    @Override public int maxUseDuration() {return this.maxUseDuration;}
    @Override public List<AerialHellItem.UseInteractionType> useInteractionToolTypes() {return this.useInteractionToolTypes;}
    @Override public UseAnim itemUseAnimation() {return this.itemUseAnimation;}
    @Override public boolean canDisableShield() {return this.canDisableShield;}

    @Override public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, List<Component> components, TooltipFlag tooltipFlag)
    {
        this.appendOptionalDescriptionsHoverText(tooltipContext, components);
        this.appendAbilityDescriptionHoverText(ClientHelper.getLocalPlayer(), tooltipContext, components);
    }

    @Override public ItemAttributeModifiers getDefaultAttributeModifiers()
    {
        return this.aerialHellDefaultModifiers.get();
    }

    //applying tick (passive) tool ability modules
    @Override public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {this.ahInventoryTick(stack, level, entity, slotId, isSelected);}

    //applying use tool ability modules
    @Override public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {return this.ahUse(level, player, hand, super::use);}

    @Override public int getUseDuration(ItemStack itemStack, LivingEntity user) {return this.ahGetUseDuration(itemStack, user, super::getUseDuration);}

    @Override public UseAnim getUseAnimation(ItemStack itemStack) {return this.ahGetUseAnimation(itemStack, super::getUseAnimation);}

    //applying releaseUsing tool ability modules
    @Override public void releaseUsing(ItemStack itemStack, Level level, LivingEntity itemOwner, int remainingTime) {this.ahReleaseUsing(itemStack, level, itemOwner, remainingTime);}
}
