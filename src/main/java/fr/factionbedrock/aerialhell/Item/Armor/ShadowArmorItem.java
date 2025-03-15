package fr.factionbedrock.aerialhell.Item.Armor;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellTags;
import fr.factionbedrock.aerialhell.Util.ItemHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class ShadowArmorItem extends ArmorItem
{
    private final EquipmentType type;
    public ShadowArmorItem(ArmorMaterial material, EquipmentType type, Item.Settings settings) {super(material, type, settings); this.type = type;}

    @Override public void inventoryTick(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected)
    {
        if (entity instanceof PlayerEntity playerEntity && this.type == EquipmentType.CHESTPLATE)
        {
            if (ItemHelper.getItemInTagCount(playerEntity.getArmorItems(), AerialHellTags.Items.SHADOW_ARMOR) >= 4 && !world.isClient())
            {
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 220, 0, false, false));

                int shadowBindAmplifier = playerEntity.hasStatusEffect(AerialHellMobEffects.SHADOW_BIND) ? 0 : 1;
                playerEntity.addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.SHADOW_BIND, 200, shadowBindAmplifier, false, false));
            }
        }
    }

    @Override public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type)
    {
        tooltip.add(this.getDescription().formatted(Formatting.GRAY));
    }

    public MutableText getDescription() {return Text.translatable("item.aerialhell.shadow_armor.desc");}
}
