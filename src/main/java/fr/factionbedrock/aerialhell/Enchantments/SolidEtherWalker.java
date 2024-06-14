package fr.factionbedrock.aerialhell.Enchantments;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantments;

public class SolidEtherWalker extends AerialHellEnchantment
{
    public SolidEtherWalker(TagKey<Item> supportedItems, int weight, int maxLevel, int minCostAtLevel1, int minCostBonusPerLevel, int maxCostAtLevel1, int maxCostBonusPerLevel, int anvilCost, EquipmentSlot... validSlots)
    {
        super(supportedItems, weight, maxLevel, minCostAtLevel1, minCostBonusPerLevel, maxCostAtLevel1, maxCostBonusPerLevel, anvilCost, validSlots);
    }

    @Override public boolean isDiscoverable() {return false;}

    @Override public boolean isTreasureOnly() {return true;}
    @Override public boolean isCurse() {return false;}

    @Override public boolean checkCompatibility(Enchantment other) {return super.checkCompatibility(other) && other != Enchantments.FROST_WALKER;}
}
