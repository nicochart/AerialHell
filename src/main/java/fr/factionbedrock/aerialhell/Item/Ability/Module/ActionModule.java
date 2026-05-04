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
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
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
    public static ActionModule fromEntity(Consumer<LivingEntity> action) {return new ActionModule((stack, itemOwner, equipmentSlot, enemyEntity, damageSource) -> action.accept(itemOwner));}

    public void apply(AbilityUseSituation useSituation) {this.action.apply(useSituation.itemStack, useSituation.itemOwner, useSituation.equipmentSlot, useSituation.enemyEntity, useSituation.damageSource);}

    public static class MobEffect extends ActionModule
    {
        public MobEffect(MobEffectTemplate template)
        {
            super((stack, itemOwner, equipmentSlot, enemyEntity, damageSource) ->
            {
                if (!itemOwner.level().isClientSide())
                {
                    @Nullable MobEffectInstance instance = template.createNewInstance(itemOwner);
                    if (instance != null) {itemOwner.addEffect(instance);}
                }
            });
        }

        public static Builder builder(Holder<net.minecraft.world.effect.MobEffect> effect) {return builder(effect, 0);}
        public static Builder builder(Holder<net.minecraft.world.effect.MobEffect> effect, int amplifier) {return new Builder(effect, amplifier);}

        public static class Builder
        {
            private final Holder<net.minecraft.world.effect.MobEffect> effect;
            private int duration;
            private int amplifier;
            private boolean ambient;
            private boolean visible;
            private boolean showIcon;

            private Builder(Holder<net.minecraft.world.effect.MobEffect> effect, int amplifier)
            {
                this.effect = effect;
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

            public MobEffect build() {return new MobEffect(new MobEffectTemplate(this.effect, this.duration, this.amplifier, this.ambient, this.visible, this.showIcon));}
            //override duration
            public MobEffect withDuration(int duration) {return new MobEffect(new MobEffectTemplate(this.effect, duration, this.amplifier, this.ambient, this.visible, this.showIcon));}
            public MobEffect withDuration(ToIntFunction<LivingEntity> duration) {return new MobEffect(new MobEffectTemplate(this.effect, duration, (entity) -> this.amplifier, (entity) -> this.ambient, (entity) -> this.visible, (entity) -> this.showIcon));}
            //override duration and amplifier
            public MobEffect with(int duration, int amplifier) {return new MobEffect(new MobEffectTemplate(this.effect, duration, amplifier, this.ambient, this.visible, this.showIcon));}
            public MobEffect with(ToIntFunction<LivingEntity> duration, ToIntFunction<LivingEntity> amplifier) {return new MobEffect(new MobEffectTemplate(this.effect, duration, amplifier, (entity) -> this.ambient, (entity) -> this.visible, (entity) -> this.showIcon));}
            //override duration and visibility. ambient = true to always display icon
            public MobEffect passiveBuild() {return new MobEffect(new MobEffectTemplate(this.effect, 32, this.amplifier, true, true, true));}
        }
    }

    public static class MobEffectList extends ActionModule
    {
        private MobEffectList(List<MobEffectTemplateListProvider> mobEffectTemplateListProviders)
        {
            super((stack, itemOwner, equipmentSlot, enemyEntity, damageSource) ->
            {
                if (!itemOwner.level().isClientSide())
                {
                    for (MobEffectTemplateListProvider templateList : mobEffectTemplateListProviders)
                    {
                        for (MobEffectTemplate template : templateList.get(itemOwner))
                        {
                            @Nullable MobEffectInstance instance = template.createNewInstance(itemOwner);
                            if (instance != null) {itemOwner.addEffect(instance);}

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

            public MobEffectList build() {return new MobEffectList(this.mobEffectTemplateListProviders);}
        }
    }

    public static class ThrowProjectile extends ActionModule
    {
        public ThrowProjectile(EntityType<? extends Projectile> type, float velocity, float inaccuracy)
        {
            super((stack, itemOwner, equipmentSlot, enemyEntity, damageSource) ->
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
            super((stack, itemOwner, equipmentSlot, enemyEntity, damageSource) ->
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
        public Particle(SimpleParticleType particleType, int count, float speed)
        {
            super((stack, itemOwner, equipmentSlot, enemyEntity, damageSource) ->
            {
                if (itemOwner.level() instanceof ServerLevel serverLevel)
                {
                    serverLevel.sendParticles(particleType, itemOwner.getX(), itemOwner.getY(0.5), itemOwner.getZ(), count, 0.5F, 0.5F, 0.5F, speed);
                }
            });
        }

        public static Particle.Builder builder(SimpleParticleType type) {return new Builder(type);}

        public static class Builder
        {
            private final SimpleParticleType type;
            private Builder(SimpleParticleType particleType) {this.type = particleType;}

            public ActionModule.Particle of(int count) {return new ActionModule.Particle(type, count, 0.5F);}
            public ActionModule.Particle of(int count, float speed) {return new ActionModule.Particle(type, count, speed);}
        }
    }

    public static class Sound extends ActionModule
    {
        public Sound(PlaySoundHelper playSoundHelper) {super((stack, itemOwner, equipmentSlot, enemyEntity, damageSource) -> playSoundHelper.playSound(itemOwner));}
        public Sound(Function<LivingEntity, PlaySoundHelper> playSoundHelperProvider)
        {
            super((stack, itemOwner, equipmentSlot, enemyEntity, damageSource) ->
            {
                playSoundHelperProvider.apply(itemOwner).playSound(itemOwner);
            });
        }
    }
}
