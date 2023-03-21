package fr.factionbedrock.aerialhell.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.inventory.EquipmentSlotType;

public class SolidEtherWalker extends Enchantment
{
    public SolidEtherWalker(Enchantment.Rarity rarityIn, EquipmentSlotType... slots)
    {
        super(rarityIn, EnchantmentType.ARMOR_FEET, slots);
    }

    @Override public int getMinLevel() {return 1;}
    @Override public int getMaxLevel() {return 1;}

    @Override public boolean canGenerateInLoot() {return false;}

    @Override public int getMinEnchantability(int enchantmentLevel) {return enchantmentLevel * 25;}
    @Override public int getMaxEnchantability(int enchantmentLevel) {return this.getMinEnchantability(enchantmentLevel) + 50;}

    @Override public boolean isTreasureEnchantment() {return true;}
    @Override public boolean isCurse() {return false;}

    @Override public boolean canApplyTogether(Enchantment other) {return super.canApplyTogether(other) && other != Enchantments.FROST_WALKER;}
}
