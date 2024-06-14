package fr.factionbedrock.aerialhell.Enchantments;

import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

public class AerialHellEnchantment extends Enchantment
{
    public AerialHellEnchantment(TagKey<Item> supportedItems, int weight, int maxLevel, int minCostAtLevel1, int minCostBonusPerLevel, int maxCostAtLevel1, int maxCostBonusPerLevel, int anvilCost, EquipmentSlot... validSlots)
    {
        this(supportedItems, weight, maxLevel, Enchantment.dynamicCost(minCostAtLevel1, minCostBonusPerLevel), Enchantment.dynamicCost(maxCostAtLevel1, maxCostBonusPerLevel), anvilCost, FeatureFlags.DEFAULT_FLAGS, validSlots);
    }

    public AerialHellEnchantment(TagKey<Item> supportedItems, int weight, int maxLevel, Enchantment.Cost minCost, Enchantment.Cost maxCost, int anvilCost, EquipmentSlot... validSlots)
    {
        this(supportedItems, weight, maxLevel, minCost, maxCost, anvilCost, FeatureFlags.DEFAULT_FLAGS, validSlots);
    }

    public AerialHellEnchantment(TagKey<Item> supportedItems, int weight, int maxLevel, Enchantment.Cost minCost, Enchantment.Cost maxCost, int anvilCost, FeatureFlagSet requiredFeatures, EquipmentSlot[] validSlots)
    {
        super(Enchantment.definition(supportedItems, weight, maxLevel, minCost, maxCost, anvilCost, requiredFeatures, validSlots));
    }

    public AerialHellEnchantment(TagKey<Item> supportedItems, TagKey<Item> primaryItems, int weight, int maxLevel, Enchantment.Cost minCost, Enchantment.Cost maxCost, int anvilCost, EquipmentSlot[] validSlots)
    {
        super(Enchantment.definition(supportedItems, primaryItems, weight, maxLevel, minCost, maxCost, anvilCost, validSlots));
    }
}
