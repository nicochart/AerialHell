package fr.factionbedrock.aerialhell.Item.Armor;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.Level;

import java.util.List;

public class ShadowArmorItem extends ArmorItem
{
    private final ArmorType type;
    public ShadowArmorItem(ArmorMaterial pMaterial, ArmorType pType, Properties pProperties) {super(pMaterial, pType, pProperties); this.type = pType;}

    @Override public void inventoryTick(ItemStack stack, Level level, Entity entity, int itemSlot, boolean isSelected)
    {
        if (entity instanceof Player && this.type == ArmorType.CHESTPLATE)
        {
            Player playerEntity = (Player) entity;

            if (ItemHelper.getItemInTagCount(playerEntity.getArmorSlots(), AerialHellTags.Items.SHADOW_ARMOR) >= 4 && !level.isClientSide())
            {
                playerEntity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false));

                int shadowBindAmplifier = playerEntity.hasEffect(AerialHellMobEffects.SHADOW_BIND.getDelegate()) ? 0 : 1;
                playerEntity.addEffect(new MobEffectInstance(AerialHellMobEffects.SHADOW_BIND.getDelegate(), 200, shadowBindAmplifier, false, false));
            }
        }
    }

    @Override public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, List<Component> components, TooltipFlag tooltipFlag)
    {
        components.add(this.getDescription().withStyle(ChatFormatting.GRAY));
    }

    public MutableComponent getDescription()
    {
        return Component.translatable("item.aerialhell.shadow_armor.desc");
    }
}
