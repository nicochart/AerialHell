package fr.factionbedrock.aerialhell.Item.Ability.Module;

import fr.factionbedrock.aerialhell.Effect.RandomMobEffectInstance;
import fr.factionbedrock.aerialhell.Entity.Util.PlaySoundHelper;
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

import java.util.function.Function;

public class ActionModule extends AbilityModule
{
    private final ModuleAction action;

    public ActionModule(ModuleAction action, UseSituation useSituation)
    {
        super(useSituation);
        this.action = action;
    }

    public static ActionModule passive(ModuleAction action) {return new ActionModule(action, UseSituation.PASSIVE);}
    public static ActionModule onUse(ModuleAction action) {return new ActionModule(action, UseSituation.ON_USE);}
    public static ActionModule onHurtEnemy(ModuleAction action) {return new ActionModule(action, UseSituation.ON_HURT_ENEMY);}

    public void apply(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot) {this.action.apply(entity, itemStack, equipmentSlot);}

    public static class MobEffect extends ActionModule
    {
        private MobEffect(MobEffectInstance mobEffectInstance, UseSituation useSituation) {this(rand -> mobEffectInstance, useSituation);}

        private MobEffect(RandomMobEffectInstance randomMobEffectInstanceTemplate, UseSituation useSituation)
        {
            super((entity, stack, equipmentSlot) ->
            {
                if (!entity.level().isClientSide())
                {
                    MobEffectInstance mobEffectInstanceTemplate = randomMobEffectInstanceTemplate.get(entity.getRandom());
                    entity.addEffect(new MobEffectInstance(mobEffectInstanceTemplate));
                }
            }, useSituation);
        }
        public static MobEffect passive(MobEffectInstance mobEffectInstanceTemplate) {return new MobEffect(mobEffectInstanceTemplate, UseSituation.PASSIVE);}
        public static MobEffect onUse(MobEffectInstance mobEffectInstanceTemplate) {return new MobEffect(mobEffectInstanceTemplate, UseSituation.ON_USE);}
        public static MobEffect onHurtEnemy(MobEffectInstance mobEffectInstanceTemplate) {return new MobEffect(mobEffectInstanceTemplate, UseSituation.ON_HURT_ENEMY);}
        public static MobEffect passive(RandomMobEffectInstance randomMobEffectInstanceTemplate) {return new MobEffect(randomMobEffectInstanceTemplate, UseSituation.PASSIVE);}
        public static MobEffect onUse(RandomMobEffectInstance randomMobEffectInstanceTemplate) {return new MobEffect(randomMobEffectInstanceTemplate, UseSituation.ON_USE);}
        public static MobEffect onHurtEnemy(RandomMobEffectInstance randomMobEffectInstanceTemplate) {return new MobEffect(randomMobEffectInstanceTemplate, UseSituation.ON_HURT_ENEMY);}
    }

    public static class ThrowProjectile extends ActionModule
    {
        private ThrowProjectile(EntityType<? extends Projectile> type, float velocity, float inaccuracy, UseSituation useSituation)
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

        public static ThrowProjectile passive(EntityType<? extends Projectile> type, float velocity, float inaccuracy) {return new ThrowProjectile(type, velocity, inaccuracy, UseSituation.PASSIVE);}
        public static ThrowProjectile onUse(EntityType<? extends Projectile> type, float velocity, float inaccuracy) {return new ThrowProjectile(type, velocity, inaccuracy, UseSituation.ON_USE);}
        public static ThrowProjectile onHurtEnemy(EntityType<? extends Projectile> type, float velocity, float inaccuracy) {return new ThrowProjectile(type, velocity, inaccuracy, UseSituation.ON_HURT_ENEMY);}
    }

    public static class RemoveMobEffect extends ActionModule
    {
        public RemoveMobEffect(Holder<net.minecraft.world.effect.MobEffect> mobEffect, UseSituation useSituation)
        {
            super((entity, stack, equipmentSlot) ->
            {
                if (!entity.level().isClientSide())
                {
                    if (entity.hasEffect(mobEffect)) {entity.removeEffect(mobEffect);}
                }
            }, useSituation);
        }

        public static RemoveMobEffect passive(Holder<net.minecraft.world.effect.MobEffect> mobEffect) {return new RemoveMobEffect(mobEffect, UseSituation.PASSIVE);}
        public static RemoveMobEffect onUse(Holder<net.minecraft.world.effect.MobEffect> mobEffect) {return new RemoveMobEffect(mobEffect, UseSituation.ON_USE);}
        public static RemoveMobEffect onHurtEnemy(Holder<net.minecraft.world.effect.MobEffect> mobEffect) {return new RemoveMobEffect(mobEffect, UseSituation.ON_HURT_ENEMY);}
    }

    public static class Particle extends ActionModule
    {
        private Particle(SimpleParticleType particleType, int count, UseSituation useSituation)
        {
            super((entity, stack, equipmentSlot) -> EntityHelper.addParticlesOnEntity(count, particleType, entity), useSituation);
        }

        public static Particle passive(SimpleParticleType particleType) {return new Particle(particleType, 5, UseSituation.PASSIVE);}
        public static Particle onUse(SimpleParticleType particleType) {return new Particle(particleType, 20, UseSituation.ON_USE);}
        public static Particle onHurtEnemy(SimpleParticleType particleType) {return new Particle(particleType, 20, UseSituation.ON_HURT_ENEMY);}
    }

    public static class Sound extends ActionModule
    {
        private Sound(PlaySoundHelper playSoundHelper, UseSituation useSituation) {super((entity, stack, equipmentSlot) -> playSoundHelper.playSound(entity), useSituation);}
        private Sound(Function<LivingEntity, PlaySoundHelper> playSoundHelperProvider, UseSituation useSituation) {super((entity, stack, equipmentSlot) -> playSoundHelperProvider.apply(entity).playSound(entity), useSituation);}

        public static Sound passive(Function<LivingEntity, PlaySoundHelper> playSoundHelperProvider) {return new Sound(playSoundHelperProvider, UseSituation.PASSIVE);}
        public static Sound onUse(Function<LivingEntity, PlaySoundHelper> playSoundHelperProvider) {return new Sound(playSoundHelperProvider, UseSituation.ON_USE);}
        public static Sound onHurtEnemy(Function<LivingEntity, PlaySoundHelper> playSoundHelperProvider) {return new Sound(playSoundHelperProvider, UseSituation.ON_HURT_ENEMY);}
        public static Sound passive(PlaySoundHelper playSoundHelper) {return new Sound(playSoundHelper, UseSituation.PASSIVE);}
        public static Sound onUse(PlaySoundHelper playSoundHelper) {return new Sound(playSoundHelper, UseSituation.ON_USE);}
        public static Sound onHurtEnemy(PlaySoundHelper playSoundHelper) {return new Sound(playSoundHelper, UseSituation.ON_HURT_ENEMY);}
    }
}
