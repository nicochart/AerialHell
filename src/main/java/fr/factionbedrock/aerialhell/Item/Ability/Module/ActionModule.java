package fr.factionbedrock.aerialhell.Item.Ability.Module;

import fr.factionbedrock.aerialhell.Effect.InstanceTemplate.MobEffectTemplate;
import fr.factionbedrock.aerialhell.Effect.InstanceTemplate.MobEffectTemplateListProvider;
import fr.factionbedrock.aerialhell.Effect.InstanceTemplate.MobEffectTemplateProvider;
import fr.factionbedrock.aerialhell.Entity.Util.PlaySoundHelper;
import fr.factionbedrock.aerialhell.Item.Ability.AbilityUseSituation;
import fr.factionbedrock.aerialhell.Item.Ability.ModuleAction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ToFloatFunction;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class ActionModule extends AbilityModule
{
    private final ModuleAction action;

    private ActionModule(ModuleAction action)
    {
        super();
        this.action = action;
    }

    public static ActionModule create(ModuleAction action) {return new ActionModule(action);}
    public static ActionModule onItemOwner(Consumer<LivingEntity> action) {return new ActionModule((stack, itemOwner, equipmentSlot, damageInfo, miningInfo) -> action.accept(itemOwner));}
    public static ActionModule onOther(Consumer<Entity> action) {return new ActionModule((stack, itemOwner, equipmentSlot, damageInfo, miningInfo) -> {
        if (damageInfo != null && damageInfo.otherEntity() != null) {action.accept(damageInfo.otherEntity());}
    });}

    public void apply(AbilityUseSituation useSituation) {this.action.apply(useSituation.itemStack, useSituation.itemOwner, useSituation.equipmentSlot, useSituation.damageUseSituationInfo, useSituation.miningUseSituationInfo);}

    public static class MobEffect extends ActionModule
    {
        //Effect Target is the one who will get the effect (item owner or other)
        //Test Target is the one who is used by template for mob effect instance creation, i.e. if you do some tests for duration or amplifier, the tests are done on the testTarget.
        public MobEffect(MobEffectTemplate template, EffectTarget effectTarget, TestTarget testTarget)
        {
            super((stack, itemOwner, equipmentSlot, damageInfo, miningInfo) ->
            {
                @Nullable LivingEntity targetEntity = effectTarget == EffectTarget.SELF ? itemOwner : damageInfo != null && damageInfo.otherEntity() instanceof LivingEntity otherLiving ? otherLiving : null;
                @Nullable LivingEntity testEntity = testTarget == TestTarget.ITEM_OWNER ? itemOwner : damageInfo != null && damageInfo.otherEntity() instanceof LivingEntity otherLiving ? otherLiving : null;
                if (targetEntity != null && testEntity != null && !itemOwner.level().isClientSide())
                {
                    @Nullable MobEffectInstance instance = template.createNewInstance(testEntity);
                    if (instance != null) {targetEntity.addEffect(instance);}
                }
            });
        }

        public static Builder toOwnerBuilder(Holder<net.minecraft.world.effect.MobEffect> effect) {return builder(effect, 0, EffectTarget.SELF);}
        public static Builder toOtherBuilder(Holder<net.minecraft.world.effect.MobEffect> effect) {return builder(effect, 0, EffectTarget.OTHER);}
        public static Builder builder(Holder<net.minecraft.world.effect.MobEffect> effect, int amplifier, EffectTarget effectTarget) {return new Builder(effect, amplifier, effectTarget);}

        public static class Builder
        {
            private final Holder<net.minecraft.world.effect.MobEffect> effect;
            private final EffectTarget effectTarget;
            private final TestTarget defaultTestTarget;
            private int duration;
            private int amplifier;
            private boolean ambient;
            private boolean visible;
            private boolean showIcon;

            private Builder(Holder<net.minecraft.world.effect.MobEffect> effect, int amplifier, EffectTarget effectTarget)
            {
                this.effect = effect;
                this.effectTarget = effectTarget;
                this.defaultTestTarget = effectTarget == EffectTarget.SELF ? TestTarget.ITEM_OWNER : TestTarget.OTHER;
                this.duration = 1;
                this.amplifier = amplifier;
                this.ambient = false;
                this.visible = true;
                this.showIcon = true;
            }

            public MobEffect.Builder duration(int duration) {this.duration = duration; return this;}
            public MobEffect.Builder amplifier(int amplifier) {this.amplifier = amplifier; return this;}
            public MobEffect.Builder iconAlwaysVisible(boolean visible) {this.ambient = visible; return this;}
            public MobEffect.Builder visible() {this.visible = false; this.showIcon = false; return this;}
            public MobEffect.Builder invisible() {this.visible = true; this.showIcon = true; return this;}

            public MobEffect build() {return new MobEffect(new MobEffectTemplate(this.effect, this.duration, this.amplifier, this.ambient, this.visible, this.showIcon), this.effectTarget, this.defaultTestTarget);}
            //override duration
            public MobEffect withDuration(int duration) {return new MobEffect(new MobEffectTemplate(this.effect, duration, this.amplifier, this.ambient, this.visible, this.showIcon), this.effectTarget, this.defaultTestTarget);}
            public MobEffect withDuration(ToIntFunction<LivingEntity> duration, TestTarget testTarget) {return new MobEffect(new MobEffectTemplate(this.effect, duration, (entity) -> this.amplifier, (entity) -> this.ambient, (entity) -> this.visible, (entity) -> this.showIcon), this.effectTarget, testTarget);}
            //override duration and amplifier
            public MobEffect with(int duration, int amplifier) {return new MobEffect(new MobEffectTemplate(this.effect, duration, amplifier, this.ambient, this.visible, this.showIcon), this.effectTarget, this.defaultTestTarget);}
            public MobEffect with(ToIntFunction<LivingEntity> duration, ToIntFunction<LivingEntity> amplifier, TestTarget testTarget) {return new MobEffect(new MobEffectTemplate(this.effect, duration, amplifier, (entity) -> this.ambient, (entity) -> this.visible, (entity) -> this.showIcon), this.effectTarget, testTarget);}
            //override duration and visibility. ambient = true to always display icon
            public MobEffect passiveBuild() {return new MobEffect(new MobEffectTemplate(this.effect, 32, this.amplifier, true, true, true), this.effectTarget, this.defaultTestTarget);}
        }
    }

    public static class MobEffectList extends ActionModule
    {
        private MobEffectList(List<MobEffectTemplateListProvider> mobEffectTemplateListProviders, boolean toOwner)
        {
            super((stack, itemOwner, equipmentSlot, damageInfo, miningInfo) ->
            {
                @Nullable LivingEntity target = toOwner ? itemOwner : damageInfo != null && damageInfo.otherEntity() instanceof LivingEntity otherLiving ? otherLiving : null;
                if (target != null && !itemOwner.level().isClientSide())
                {
                    for (MobEffectTemplateListProvider templateList : mobEffectTemplateListProviders)
                    {
                        for (MobEffectTemplate template : templateList.get(target))
                        {
                            @Nullable MobEffectInstance instance = template.createNewInstance(target);
                            if (instance != null) {target.addEffect(instance);}

                        }
                    }
                }
            });
        }

        public static Builder builder() {return new Builder();}

        public static class Builder
        {
            private final List<MobEffectTemplateListProvider> mobEffectTemplateListProviders;
            public Builder() {this.mobEffectTemplateListProviders = new ArrayList<>();}

            public MobEffectList.Builder addEffects(MobEffectTemplateListProvider templateList) {this.mobEffectTemplateListProviders.add(templateList); return this;}

            public MobEffectList.Builder addEffects(MobEffectTemplate... templates)
            {
                for (MobEffectTemplate template : templates) {this.mobEffectTemplateListProviders.add(template.toListProvider());}
                return this;
            }

            public MobEffectList.Builder addEffects(MobEffectTemplateProvider... templates)
            {
                for (MobEffectTemplateProvider template : templates) {this.mobEffectTemplateListProviders.add(template.toListProvider());}
                return this;
            }

            public MobEffectList toOwnerBuild() {return new MobEffectList(this.mobEffectTemplateListProviders, true);}
            public MobEffectList toOtherBuild() {return new MobEffectList(this.mobEffectTemplateListProviders, false);}
        }
    }

    public static class ThrowProjectile extends ActionModule
    {
        public ThrowProjectile(EntityType<? extends Projectile> type, float velocity, float inaccuracy)
        {
            super((stack, itemOwner, equipmentSlot, damageInfo, miningInfo) ->
            {
                Level level = itemOwner.level();
                if (!level.isClientSide())
                {
                    Projectile projectile = type.create(level, EntitySpawnReason.TRIGGERED);
                    if (projectile != null)
                    {
                        projectile.setPos(itemOwner.getX(), itemOwner.getY(0.5D) + 0.5D, itemOwner.getZ());
                        projectile.setOwner(itemOwner);
                        projectile.shoot(itemOwner.getLookAngle().x, itemOwner.getLookAngle().y, itemOwner.getLookAngle().z, velocity, inaccuracy);
                        level.addFreshEntity(projectile);
                    }
                }
            });
        }

        public static ThrowProjectile.Builder builder() {return new ThrowProjectile.Builder();}

        public static class Builder
        {
            private Builder() {}

            public final ThrowProjectile build(EntityType<? extends Projectile> type, float velocity, float inaccuracy) {return new ThrowProjectile(type, velocity, inaccuracy);}
        }
    }

    public static class RemoveMobEffect extends ActionModule
    {
        public RemoveMobEffect(Holder<net.minecraft.world.effect.MobEffect>... mobEffects)
        {
            super((stack, itemOwner, equipmentSlot, damageInfo, miningInfo) ->
            {
                if (!itemOwner.level().isClientSide())
                {
                    for (Holder<net.minecraft.world.effect.MobEffect> mobEffect : mobEffects)
                    {
                        if (itemOwner.hasEffect(mobEffect)) {itemOwner.removeEffect(mobEffect);}
                    }
                }
            });
        }

        public static RemoveMobEffect.Builder builder() {return new RemoveMobEffect.Builder();}

        public static class Builder
        {
            private Builder() {}

            @SafeVarargs public final RemoveMobEffect effects(Holder<net.minecraft.world.effect.MobEffect>... mobEffects) {return new RemoveMobEffect(mobEffects);}
        }
    }

    public static class Particle extends ActionModule
    {
        public Particle(SimpleParticleType particleType, int count, float speed, EffectTarget effectTarget)
        {
            super((stack, itemOwner, equipmentSlot, damageInfo, miningInfo) ->
            {
                @Nullable LivingEntity targetEntity = effectTarget == EffectTarget.SELF ? itemOwner : damageInfo != null && damageInfo.otherEntity() instanceof LivingEntity otherLiving ? otherLiving : null;
                if (targetEntity != null && itemOwner.level() instanceof ServerLevel serverLevel)
                {
                    serverLevel.sendParticles(particleType, targetEntity.getX(), targetEntity.getY(0.5), targetEntity.getZ(), count, 0.5F, 0.5F, 0.5F, speed);
                }
            });
        }

        public static Particle.Builder onOwnerBuilder(SimpleParticleType type) {return new Builder(type, EffectTarget.SELF);}
        public static Particle.Builder onOtherBuilder(SimpleParticleType type) {return new Builder(type, EffectTarget.OTHER);}
        public static Particle.Builder builder(SimpleParticleType type, EffectTarget effectTarget) {return new Builder(type, effectTarget);}

        public static class Builder
        {
            private final SimpleParticleType type;
            private final EffectTarget effectTarget;
            private Builder(SimpleParticleType particleType, EffectTarget effectTarget) {this.type = particleType; this.effectTarget = effectTarget;}

            public ActionModule.Particle of(int count) {return new ActionModule.Particle(type, count, 0.5F, effectTarget);}
            public ActionModule.Particle of(int count, float speed) {return new ActionModule.Particle(type, count, speed, effectTarget);}
        }
    }

    public static class Sound extends ActionModule
    {
        public Sound(PlaySoundHelper playSoundHelper) {super((stack, itemOwner, equipmentSlot, damageInfo, miningInfo) -> playSoundHelper.playSound(itemOwner));}
        public Sound(Function<LivingEntity, PlaySoundHelper> playSoundHelperProvider)
        {
            super((stack, itemOwner, equipmentSlot, damageInfo, miningInfo) ->
            {
                playSoundHelperProvider.apply(itemOwner).playSound(itemOwner);
            });
        }
    }

    public static class MultiplyDamage extends ActionModule
    {
        public MultiplyDamage(ToFloatFunction<LivingEntity> multiplier, TestTarget testTarget) {super((stack, itemOwner, equipmentSlot, damageInfo, miningInfo) ->
        {
            if (damageInfo != null)
            {
                damageInfo.damageAmountMultiplier().set(damageInfo.damageAmountMultiplier().get() * (testTarget == TestTarget.ITEM_OWNER ? multiplier.applyAsFloat(itemOwner) : (damageInfo.otherEntity() instanceof LivingEntity livingOther ? multiplier.applyAsFloat(livingOther) : 1.0F)));
            }
        });}

        public static MultiplyDamage.Builder builder() {return new MultiplyDamage.Builder();}

        public static class Builder
        {
            private Builder() {}

            public MultiplyDamage.MultiplyDamage by(float multiplier) {return this.by((itemOwner) -> multiplier, TestTarget.ITEM_OWNER);}
            public MultiplyDamage.MultiplyDamage by(ToFloatFunction<LivingEntity> multiplier, TestTarget testTarget) {return new ActionModule.MultiplyDamage(multiplier, testTarget);}
        }
    }

    public static class MultiplyMiningSpeed extends ActionModule
    {
        public MultiplyMiningSpeed(ConditionalMultiplier multiplier) {super((stack, itemOwner, equipmentSlot, damageInfo, miningInfo) ->
        {
            if (miningInfo != null)
            {
                miningInfo.miningSpeedMultiplier().set(miningInfo.miningSpeedMultiplier().get() * multiplier.getValue(itemOwner, miningInfo.blockState()));
            }
        });}

        public static MultiplyMiningSpeed.Builder builder() {return new MultiplyMiningSpeed.Builder();}

        public static class Builder
        {
            private Builder() {}

            public MultiplyMiningSpeed.MultiplyMiningSpeed by(float multiplier) {return this.by((itemOwner, blockstate) -> multiplier);}
            public MultiplyMiningSpeed.MultiplyMiningSpeed by(ConditionalMultiplier multiplier) {return new ActionModule.MultiplyMiningSpeed(multiplier);}
        }

        @FunctionalInterface public interface ConditionalMultiplier {float getValue(LivingEntity entity, BlockState state);}
    }

    //SELF == Item Owner
    public enum EffectTarget {SELF, OTHER}
}
