package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellRarities;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GodsVoluciteBerryItem extends Item
{
    public GodsVoluciteBerryItem()
    {
        super(new Item.Settings().rarity(AerialHellRarities.MYTHICAL)
                .food(new FoodComponent.Builder().alwaysEdible().nutrition(6).saturationModifier(0.8F)
                		.statusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 2400, 2), 1.0F)
                		.statusEffect(new StatusEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS, 2000, 2), 1.0F)
                		.statusEffect(new StatusEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY, 2400, 2), 1.0F)
                		.statusEffect(new StatusEffectInstance(AerialHellMobEffects.GOD, 6000, 0), 1.0F)
                		.statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 0), 1.0F)
                		.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2400, 0), 1.0F)
                		.statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2400, 1), 1.0F)
                		.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 2400, 1), 1.0F)
                		.statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 3), 1.0F)
                .build()));
    }
    
    @Override
    public boolean hasGlint(ItemStack stack) {return true;}
}
