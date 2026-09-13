package fr.factionbedrock.aerialhell.Effect.InstanceTemplate;

import net.minecraft.world.entity.LivingEntity;

import java.util.Arrays;
import java.util.List;

//The templates aren't meant to be used directly in livingEntity.addEffect(template).
//Creating a fresh new MobEffectInstance(template) is highly recommended to avoid using the same effect instance multiple times.
@FunctionalInterface public interface MobEffectTemplateListProvider
{
    List<MobEffectTemplate> get(LivingEntity livingEntity);

    static MobEffectTemplateListProvider createFromTemplateList(MobEffectTemplate... mobEffectInstanceTemplates) {return entity -> Arrays.stream(mobEffectInstanceTemplates).toList();}
    static MobEffectTemplateListProvider createFromProviders(MobEffectTemplateProvider... mobEffectInstanceProviders) {return entity -> Arrays.stream(mobEffectInstanceProviders).map(instanceProvider -> instanceProvider.get(entity)).toList();}
    static MobEffectTemplateListProvider createFromRandomList(RandomMobEffectTemplate... randomMobEffectInstance) {return entity -> Arrays.stream(randomMobEffectInstance).map(randomInstance -> randomInstance.get(entity.getRandom())).toList();}
}