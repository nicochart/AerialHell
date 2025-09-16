package fr.factionbedrock.aerialhell.Item.Armor;

import fr.factionbedrock.aerialhell.Config.LoadedConfigParams;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ShadowArmorItem extends Item
{
    public ShadowArmorItem(Item.Settings settings) {super(settings);}

    @Override public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot)
    {
        if (entity instanceof PlayerEntity playerEntity && slot == EquipmentSlot.CHEST)
        {
            if (ItemHelper.getItemInTagCount(EntityHelper.getEquippedHumanoidArmorItemList(playerEntity), AerialHellTags.Items.SHADOW_ARMOR) >= 4 && !world.isClient())
            {
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 220, 0, false, false));

                int shadowBindAmplifier = playerEntity.hasStatusEffect(AerialHellMobEffects.SHADOW_BIND) ? 0 : 1;
                playerEntity.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.SHADOW_BIND, 200, shadowBindAmplifier, false, false));
            }
        }
    }

    @Override public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type)
    {
        if (LoadedConfigParams.ENABLE_SHADOW_BIND_TEXTURE_SHIFT)
        {
            textConsumer.accept(this.getTextureShiftDescription().formatted(Formatting.GRAY));

            if (LoadedConfigParams.ENABLE_SHADOW_BIND_RELOAD_TEXTURE)
            {
                textConsumer.accept(this.getReloadTextureDescription().formatted(Formatting.GRAY));
            }
            else
            {
                textConsumer.accept(this.getDisabledReloadTextureDescription().formatted(Formatting.GRAY));
            }
        }
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }

    public MutableText getReloadTextureDescription() {return Text.translatable("item.aerialhell.shadow_armor.reload_texture_desc");}
    public MutableText getDisabledReloadTextureDescription() {return Text.translatable("item.aerialhell.shadow_armor.disabled_reload_texture_desc");}
    public MutableText getTextureShiftDescription() {return Text.translatable("item.aerialhell.shadow_armor.texture_shift_desc");}
}
