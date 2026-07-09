package fr.factionbedrock.aerialhell.Item.Armor;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ShadowArmorItem extends ArmorItem
{
    public ShadowArmorItem(Holder<ArmorMaterial> material, ArmorItem.Type type, Item.Properties settings) {super(material, type, settings);}

    @Override public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected)
    {
        if (entity instanceof Player playerEntity && this.type == ArmorItem.Type.CHESTPLATE)
        {
            if (ItemHelper.getItemInTagCount(playerEntity.getArmorSlots(), AerialHellTags.Items.SHADOW_ARMOR) >= 4 && !world.isClientSide())
            {
                playerEntity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220, 0, false, false));

                int shadowBindAmplifier = playerEntity.hasEffect(AerialHellMobEffects.SHADOW_BIND) ? 0 : 1;
                playerEntity.addEffect(new MobEffectInstance(AerialHellMobEffects.SHADOW_BIND, 200, shadowBindAmplifier, false, false));
            }
        }
    }

    @Override public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type)
    {
        tooltip.add(this.getDescription().withStyle(ChatFormatting.GRAY));
    }

    public MutableComponent getDescription() {return Component.translatable("item.aerialhell.shadow_armor.desc");}
}
