package fr.factionbedrock.aerialhell.Effect.InstanceTemplate;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class MobEffectTemplate
{
    private final Holder<MobEffect> effect;
    private ToIntFunction<LivingEntity> duration;
    private ToIntFunction<LivingEntity> amplifier;
    private Predicate<LivingEntity> ambient;
    private Predicate<LivingEntity> visible;
    private Predicate<LivingEntity> showIcon;

    public MobEffectTemplate(Holder<MobEffect> effect, int duration, int amplifier) {this(effect, (entity) -> duration, (entity) -> amplifier);}
    public MobEffectTemplate(Holder<MobEffect> effect, ToIntFunction<LivingEntity> duration, ToIntFunction<LivingEntity> amplifier) {this(effect, duration, amplifier, (entity) -> false, (entity) -> true, (entity) -> true);}
    public MobEffectTemplate(Holder<MobEffect> effect, ToIntFunction<LivingEntity> duration, ToIntFunction<LivingEntity> amplifier, boolean visible) {this(effect, duration, amplifier, (entity) -> false, (entity) -> visible, (entity) -> visible);}
    public MobEffectTemplate(Holder<MobEffect> effect,int duration, int amplifier, boolean ambient, boolean visible, boolean showIcon) {this(effect, (entity) -> duration, (entity) -> amplifier, (entity) -> ambient, (entity) -> visible, (entity) -> showIcon);}
    public MobEffectTemplate(Holder<MobEffect> effect, ToIntFunction<LivingEntity> duration, ToIntFunction<LivingEntity> amplifier, Predicate<LivingEntity> ambient, Predicate<LivingEntity> visible, Predicate<LivingEntity> showIcon)
    {
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
        this.ambient = ambient;
        this.visible = visible;
        this.showIcon = showIcon;
    }

    public MobEffectInstance createNewInstance(LivingEntity entity)
    {
        return new MobEffectInstance(this.effect, this.duration.applyAsInt(entity), this.amplifier.applyAsInt(entity), this.ambient.test(entity), this.visible.test(entity), this.showIcon.test(entity));
    }
}
