package fr.factionbedrock.aerialhell.Enchantments;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.entity.EquipmentSlot;

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

    //TODO : cannot Override final method. How to check compatibility ?
    //@Override public final boolean isCompatibleWith(Enchantment other) {return super.isCompatibleWith(other) && other != Enchantments.FROST_WALKER;}
}
