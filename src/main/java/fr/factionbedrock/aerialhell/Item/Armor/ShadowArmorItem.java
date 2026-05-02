package fr.factionbedrock.aerialhell.Item.Armor;

import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

public class ShadowArmorItem extends Item
{
    public ShadowArmorItem(Item.Properties settings) {super(settings);}

    @Override public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot)
    {
        if (entity instanceof Player playerEntity && slot == EquipmentSlot.CHEST)
        {
            if (ItemHelper.countItemStacksInTag(EntityHelper.getEquippedHumanoidArmorItemList(playerEntity), AerialHellTags.Items.SHADOW_ARMOR) >= 4 && !level.isClientSide())
            {
                playerEntity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false));

                int shadowBindAmplifier = playerEntity.hasEffect(AerialHellMobEffects.SHADOW_BIND) ? 0 : 1;
                playerEntity.addEffect(new MobEffectInstance(AerialHellMobEffects.SHADOW_BIND, 200, shadowBindAmplifier, false, false));
            }
        }
    }

    @Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type)
    {
        if (LoadedConfigParams.ENABLE_SHADOW_BIND_TEXTURE_SHIFT)
        {
            textConsumer.accept(this.getTextureShiftDescription().withStyle(ChatFormatting.GRAY));

            if (LoadedConfigParams.ENABLE_SHADOW_BIND_RELOAD_TEXTURE)
            {
                textConsumer.accept(this.getReloadTextureDescription().withStyle(ChatFormatting.GRAY));
            }
            else
            {
                textConsumer.accept(this.getDisabledReloadTextureDescription().withStyle(ChatFormatting.GRAY));
            }
        }
        super.appendHoverText(stack, context, displayComponent, textConsumer, type);
    }

    public MutableComponent getReloadTextureDescription() {return Component.translatable("item.aerialhell.shadow_armor.reload_texture_desc");}
    public MutableComponent getDisabledReloadTextureDescription() {return Component.translatable("item.aerialhell.shadow_armor.disabled_reload_texture_desc");}
    public MutableComponent getTextureShiftDescription() {return Component.translatable("item.aerialhell.shadow_armor.texture_shift_desc");}
}
