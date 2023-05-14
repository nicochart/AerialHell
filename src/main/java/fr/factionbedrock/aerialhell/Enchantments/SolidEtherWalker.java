package fr.factionbedrock.aerialhell.Enchantments;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantments;

public class SolidEtherWalker extends Enchantment
{
    public SolidEtherWalker(Enchantment.Rarity rarityIn, EquipmentSlot... slots)
    {
        super(rarityIn, EnchantmentCategory.ARMOR_FEET, slots);
    }

    @Override public int getMinLevel() {return 1;}
    @Override public int getMaxLevel() {return 1;}

    @Override public boolean isDiscoverable() {return false;}

    @Override public int getMinCost(int enchantmentLevel) {return enchantmentLevel * 25;}
    @Override public int getMaxCost(int enchantmentLevel) {return this.getMinCost(enchantmentLevel) + 50;}

    @Override public boolean isTreasureOnly() {return true;}
    @Override public boolean isCurse() {return false;}

    @Override public boolean checkCompatibility(Enchantment other) {return super.checkCompatibility(other) && other != Enchantments.FROST_WALKER;}
}
