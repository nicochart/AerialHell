package fr.factionbedrock.aerialhell.Item.Ability.Module;

import fr.factionbedrock.aerialhell.Effect.MobEffectInstanceProvider;
import fr.factionbedrock.aerialhell.Effect.RandomMobEffectInstance;
import fr.factionbedrock.aerialhell.Entity.Util.PlaySoundHelper;
import fr.factionbedrock.aerialhell.Item.Ability.ModuleAction;
import fr.factionbedrock.aerialhell.Item.Ability.ModuleUseSituation;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.function.Function;

public class ActionModule extends AbilityModule
{
    private final ModuleAction action;

    public ActionModule(ModuleAction action, ModuleUseSituation useSituation)
    {
        super(useSituation);
        this.action = action;
    }

    public static ActionModule passive(ModuleAction action) {return new ActionModule(action, ModuleUseSituation.PASSIVE);}
    public static ActionModule onUse(ModuleAction action) {return new ActionModule(action, ModuleUseSituation.ON_USE);}
    public static ActionModule onHurtEnemy(ModuleAction action) {return new ActionModule(action, ModuleUseSituation.ON_HURT_ENEMY);}

    public void apply(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot) {this.action.apply(entity, itemStack, equipmentSlot);}

    public static class MobEffect extends ActionModule
    {
        private MobEffect(ModuleUseSituation useSituation, MobEffectInstance... mobEffectInstances) {this(useSituation, Arrays.stream(mobEffectInstances).map(effect -> (RandomMobEffectInstance) rand -> effect).toArray(RandomMobEffectInstance[]::new));}
        private MobEffect(ModuleUseSituation useSituation, RandomMobEffectInstance... randomMobEffectInstanceTemplates) {this(useSituation, Arrays.stream(randomMobEffectInstanceTemplates).map(template -> (MobEffectInstanceProvider) entity -> template.get(entity.getRandom())).toArray(MobEffectInstanceProvider[]::new));}
        private MobEffect(ModuleUseSituation useSituation, MobEffectInstanceProvider... mobEffectInstanceProviders)
        {
            super((entity, stack, equipmentSlot) ->
            {
                if (!entity.level().isClientSide())
                {
                    for (MobEffectInstanceProvider mobEffectInstanceProvider : mobEffectInstanceProviders)
                    {
                        MobEffectInstance mobEffectInstanceTemplate = mobEffectInstanceProvider.create(entity);
                        entity.addEffect(new MobEffectInstance(mobEffectInstanceTemplate));
                    }
                }
            }, useSituation);
        }

        public static MobEffect passive(MobEffectInstance... mobEffectInstanceTemplates) {return new MobEffect(ModuleUseSituation.PASSIVE, mobEffectInstanceTemplates);}
        public static MobEffect onUse(MobEffectInstance... mobEffectInstanceTemplates) {return new MobEffect(ModuleUseSituation.ON_USE, mobEffectInstanceTemplates);}
        public static MobEffect onHurtEnemy(MobEffectInstance... mobEffectInstanceTemplates) {return new MobEffect(ModuleUseSituation.ON_HURT_ENEMY, mobEffectInstanceTemplates);}
        public static MobEffect passive(RandomMobEffectInstance... randomMobEffectInstanceTemplates) {return new MobEffect(ModuleUseSituation.PASSIVE, randomMobEffectInstanceTemplates);}
        public static MobEffect onUse(RandomMobEffectInstance... randomMobEffectInstanceTemplates) {return new MobEffect(ModuleUseSituation.ON_USE, randomMobEffectInstanceTemplates);}
        public static MobEffect onHurtEnemy(RandomMobEffectInstance... randomMobEffectInstanceTemplates) {return new MobEffect(ModuleUseSituation.ON_HURT_ENEMY, randomMobEffectInstanceTemplates);}
        public static MobEffect passive(MobEffectInstanceProvider... mobEffectInstanceProvider) {return new MobEffect(ModuleUseSituation.PASSIVE, mobEffectInstanceProvider);}
        public static MobEffect onUse(MobEffectInstanceProvider... mobEffectInstanceProvider) {return new MobEffect(ModuleUseSituation.ON_USE, mobEffectInstanceProvider);}
        public static MobEffect onHurtEnemy(MobEffectInstanceProvider... mobEffectInstanceProvider) {return new MobEffect(ModuleUseSituation.ON_HURT_ENEMY, mobEffectInstanceProvider);}
    }

    public static class ThrowProjectile extends ActionModule
    {
        private ThrowProjectile(EntityType<? extends Projectile> type, float velocity, float inaccuracy, ModuleUseSituation useSituation)
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
            }, useSituation);
        }

        public static ThrowProjectile passive(EntityType<? extends Projectile> type, float velocity, float inaccuracy) {return new ThrowProjectile(type, velocity, inaccuracy, ModuleUseSituation.PASSIVE);}
        public static ThrowProjectile onUse(EntityType<? extends Projectile> type, float velocity, float inaccuracy) {return new ThrowProjectile(type, velocity, inaccuracy, ModuleUseSituation.ON_USE);}
        public static ThrowProjectile onHurtEnemy(EntityType<? extends Projectile> type, float velocity, float inaccuracy) {return new ThrowProjectile(type, velocity, inaccuracy, ModuleUseSituation.ON_HURT_ENEMY);}
    }

    public static class RemoveMobEffect extends ActionModule
    {
        public RemoveMobEffect(Holder<net.minecraft.world.effect.MobEffect> mobEffect, ModuleUseSituation useSituation)
        {
            super((entity, stack, equipmentSlot) ->
            {
                if (!entity.level().isClientSide())
                {
                    if (entity.hasEffect(mobEffect)) {entity.removeEffect(mobEffect);}
                }
            }, useSituation);
        }

        public static RemoveMobEffect passive(Holder<net.minecraft.world.effect.MobEffect> mobEffect) {return new RemoveMobEffect(mobEffect, ModuleUseSituation.PASSIVE);}
        public static RemoveMobEffect onUse(Holder<net.minecraft.world.effect.MobEffect> mobEffect) {return new RemoveMobEffect(mobEffect, ModuleUseSituation.ON_USE);}
        public static RemoveMobEffect onHurtEnemy(Holder<net.minecraft.world.effect.MobEffect> mobEffect) {return new RemoveMobEffect(mobEffect, ModuleUseSituation.ON_HURT_ENEMY);}
    }

    public static class Particle extends ActionModule
    {
        private Particle(SimpleParticleType particleType, int count, ModuleUseSituation useSituation)
        {
            super((entity, stack, equipmentSlot) -> EntityHelper.addParticlesOnEntity(count, particleType, entity), useSituation);
        }

        public static Particle passive(SimpleParticleType particleType) {return new Particle(particleType, 5, ModuleUseSituation.PASSIVE);}
        public static Particle onUse(SimpleParticleType particleType) {return new Particle(particleType, 20, ModuleUseSituation.ON_USE);}
        public static Particle onHurtEnemy(SimpleParticleType particleType) {return new Particle(particleType, 20, ModuleUseSituation.ON_HURT_ENEMY);}
    }

    public static class Sound extends ActionModule
    {
        private Sound(PlaySoundHelper playSoundHelper, ModuleUseSituation useSituation) {super((entity, stack, equipmentSlot) -> playSoundHelper.playSound(entity), useSituation);}
        private Sound(Function<LivingEntity, PlaySoundHelper> playSoundHelperProvider, ModuleUseSituation useSituation)
        {
            super((entity, stack, equipmentSlot) ->
            {
                playSoundHelperProvider.apply(entity).playSound(entity);
            }, useSituation);
        }

        public static Sound passive(Function<LivingEntity, PlaySoundHelper> playSoundHelperProvider) {return new Sound(playSoundHelperProvider, ModuleUseSituation.PASSIVE);}
        public static Sound onUse(Function<LivingEntity, PlaySoundHelper> playSoundHelperProvider) {return new Sound(playSoundHelperProvider, ModuleUseSituation.ON_USE);}
        public static Sound onHurtEnemy(Function<LivingEntity, PlaySoundHelper> playSoundHelperProvider) {return new Sound(playSoundHelperProvider, ModuleUseSituation.ON_HURT_ENEMY);}
        public static Sound passive(PlaySoundHelper playSoundHelper) {return new Sound(playSoundHelper, ModuleUseSituation.PASSIVE);}
        public static Sound onUse(PlaySoundHelper playSoundHelper) {return new Sound(playSoundHelper, ModuleUseSituation.ON_USE);}
        public static Sound onHurtEnemy(PlaySoundHelper playSoundHelper) {return new Sound(playSoundHelper, ModuleUseSituation.ON_HURT_ENEMY);}
    }
}
