package fr.factionbedrock.aerialhell.Item.Armor;

import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.Equippable;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ShadowArmorItem extends Item
{
    public ShadowArmorItem(Properties pProperties) {super(pProperties);}

    @Override public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot)
    {
        //TODO use slot (parameter) instead ?
        Equippable equippableComponent = this.components().get(DataComponents.EQUIPPABLE);
        if (entity instanceof Player playerEntity && equippableComponent.slot() == EquipmentSlot.CHEST)
        {
            if (ItemHelper.getItemInTagCount(EntityHelper.getEquippedHumanoidArmorItemList(playerEntity), AerialHellTags.Items.SHADOW_ARMOR) >= 4 && !level.isClientSide())
            {
                playerEntity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false));

                int shadowBindAmplifier = playerEntity.hasEffect(AerialHellMobEffects.SHADOW_BIND.getDelegate()) ? 0 : 1;
                playerEntity.addEffect(new MobEffectInstance(AerialHellMobEffects.SHADOW_BIND.getDelegate(), 200, shadowBindAmplifier, false, false));
            }
        }
    }

    @Override public void appendHoverText(ItemStack stack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag)
    {
        if (LoadedConfigParams.ENABLE_SHADOW_BIND_TEXTURE_SHIFT)
        {
            tooltipAdder.accept(this.getTextureShiftDescription().withStyle(ChatFormatting.GRAY));

            if (LoadedConfigParams.ENABLE_SHADOW_BIND_RELOAD_TEXTURE)
            {
                tooltipAdder.accept(this.getReloadTextureDescription().withStyle(ChatFormatting.GRAY));
            }
            else
            {
                tooltipAdder.accept(this.getDisabledReloadTextureDescription().withStyle(ChatFormatting.GRAY));
            }
        }
    }

    public MutableComponent getReloadTextureDescription() {return Component.translatable("item.aerialhell.shadow_armor.reload_texture_desc");}
    public MutableComponent getDisabledReloadTextureDescription() {return Component.translatable("item.aerialhell.shadow_armor.disabled_reload_texture_desc");}
    public MutableComponent getTextureShiftDescription() {return Component.translatable("item.aerialhell.shadow_armor.texture_shift_desc");}
}
