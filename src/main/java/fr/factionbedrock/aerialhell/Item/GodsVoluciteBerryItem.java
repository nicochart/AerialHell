package fr.factionbedrock.aerialhell.Item;

import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.Misc.AerialHellRarities;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;

public class GodsVoluciteBerryItem extends Item
{
    public GodsVoluciteBerryItem()
    {
        super(new Item.Properties().rarity(AerialHellRarities.MYTHICAL.getValue())
                .food(new FoodProperties.Builder().alwaysEdible().nutrition(6).saturationModifier(0.8F)
                		.effect(new MobEffectInstance(MobEffects.SLOW_FALLING, 2400, 2), 1.0F)
                		.effect(new MobEffectInstance(AerialHellMobEffects.HEAD_IN_THE_CLOUDS.getDelegate(), 2000, 2), 1.0F)
                		.effect(new MobEffectInstance(AerialHellMobEffects.SHADOW_IMMUNITY.getDelegate(), 2400, 2), 1.0F)
                		.effect(new MobEffectInstance(AerialHellMobEffects.GOD.getDelegate(), 6000, 0), 1.0F)
                		.effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), 1.0F)
                		.effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2400, 0), 1.0F)
                		.effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2400, 1), 1.0F)
                		.effect(new MobEffectInstance(MobEffects.DIG_SPEED, 2400, 1), 1.0F)
                		.effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3), 1.0F)
                .build()));
    }
    
    @Override
    public boolean isFoil(ItemStack stack) {return true;}
}
