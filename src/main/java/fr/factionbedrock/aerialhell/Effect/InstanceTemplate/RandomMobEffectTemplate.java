package fr.factionbedrock.aerialhell.Effect.InstanceTemplate;

import net.minecraft.util.RandomSource;

import java.util.List;

@FunctionalInterface public interface RandomMobEffectTemplate
{
    MobEffectTemplate get(RandomSource rand);

    default MobEffectTemplateProvider toProvider() {return entity -> this.get(entity.getRandom());}
    default MobEffectTemplateListProvider toListProvider() {return entity -> List.of(this.get(entity.getRandom()));}
}
