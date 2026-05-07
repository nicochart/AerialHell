package fr.factionbedrock.aerialhell.Effect.InstanceTemplate;

import net.minecraft.world.entity.LivingEntity;

import java.util.List;

//The templates aren't meant to be used directly in livingEntity.addEffect(template).
//Creating a fresh new MobEffectInstance(template) is highly recommended to avoid using the same effect instance multiple times.
@FunctionalInterface public interface MobEffectTemplateProvider
{
    MobEffectTemplate get(LivingEntity livingEntity);

    default MobEffectTemplateListProvider toListProvider() {return entity -> List.of(this.get(entity));}
}