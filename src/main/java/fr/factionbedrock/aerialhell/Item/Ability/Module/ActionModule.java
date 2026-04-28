package fr.factionbedrock.aerialhell.Item.Ability.Module;

import fr.factionbedrock.aerialhell.Effect.InstanceTemplate.MobEffectTemplate;
import fr.factionbedrock.aerialhell.Effect.InstanceTemplate.MobEffectTemplateListProvider;
import fr.factionbedrock.aerialhell.Effect.InstanceTemplate.MobEffectTemplateProvider;
import fr.factionbedrock.aerialhell.Entity.Util.PlaySoundHelper;
import fr.factionbedrock.aerialhell.Item.Ability.ModuleAction;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ActionModule extends AbilityModule
{
    private final ModuleAction action;

    private ActionModule(ModuleAction action)
    {
        super();
        this.action = action;
    }

    public static ActionModule create(ModuleAction action) {return new ActionModule(action);}
    public static ActionModule fromEntity(Consumer<LivingEntity> action) {return new ActionModule((entity, stack, equipmentSlot) -> action.accept(entity));}

    public void apply(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot) {this.action.apply(entity, itemStack, equipmentSlot);}

    public static class MobEffect extends ActionModule
    {
        public MobEffect(MobEffectTemplate template)
        {
            super((entity, stack, equipmentSlot) ->
            {
                if (!entity.level().isClientSide())
                {
                    entity.addEffect(template.createNewInstance(entity));
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
            //override duration and amplifier
            public MobEffect with(int duration, int amplifier) {return new MobEffect(new MobEffectTemplate(this.effect, duration, amplifier, this.ambient, this.visible, this.showIcon));}
            //override duration and visibility. ambient = true to always display icon
            public MobEffect passiveBuild() {return new MobEffect(new MobEffectTemplate(this.effect, 32, this.amplifier, true, true, true));}
        }
    }

    public static class MobEffectList extends ActionModule
    {
        private MobEffectList(List<MobEffectTemplateListProvider> mobEffectTemplateListProviders)
        {
            super((entity, stack, equipmentSlot) ->
            {
                if (!entity.level().isClientSide())
                {
                    for (MobEffectTemplateListProvider templateList : mobEffectTemplateListProviders)
                    {
                        for (MobEffectTemplate template : templateList.get(entity))
                        {
                            entity.addEffect(template.createNewInstance(entity));
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
            super((shooter, stack, equipmentSlot) ->
            {
                Level level = shooter.level();
                if (!level.isClientSide())
                {
                    Projectile projectile = type.create(level, EntitySpawnReason.TRIGGERED);
                    if (projectile != null)
                    {
                        projectile.setPos(shooter.getX(), shooter.getY(0.5D) + 0.5D, shooter.getZ());
                        projectile.setOwner(shooter);
                        projectile.shoot(shooter.getLookAngle().x, shooter.getLookAngle().y, shooter.getLookAngle().z, velocity, inaccuracy);
                        level.addFreshEntity(projectile);
                    }
                }
            });
        }
    }

    public static class RemoveMobEffect extends ActionModule
    {
        public RemoveMobEffect(Holder<net.minecraft.world.effect.MobEffect>... mobEffects)
        {
            super((entity, stack, equipmentSlot) ->
            {
                if (!entity.level().isClientSide())
                {
                    for (Holder<net.minecraft.world.effect.MobEffect> mobEffect : mobEffects)
                    {
                        if (entity.hasEffect(mobEffect)) {entity.removeEffect(mobEffect);}
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
        public Particle(SimpleParticleType particleType, int count)
        {
            super((entity, stack, equipmentSlot) -> EntityHelper.addParticlesOnEntity(count, particleType, entity));
        }

        public static Particle.Builder builder(SimpleParticleType type) {return new Builder(type);}

        public static class Builder
        {
            private final SimpleParticleType type;
            private Builder(SimpleParticleType particleType) {this.type = particleType;}

            public ActionModule.Particle ofAmount(int count) {return new ActionModule.Particle(type, count);}
        }
    }

    public static class Sound extends ActionModule
    {
        public Sound(PlaySoundHelper playSoundHelper) {super((entity, stack, equipmentSlot) -> playSoundHelper.playSound(entity));}
        public Sound(Function<LivingEntity, PlaySoundHelper> playSoundHelperProvider)
        {
            super((entity, stack, equipmentSlot) ->
            {
                playSoundHelperProvider.apply(entity).playSound(entity);
            });
        }
    }
}
