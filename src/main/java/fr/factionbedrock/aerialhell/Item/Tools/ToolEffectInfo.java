package fr.factionbedrock.aerialhell.Item.Tools;

import fr.factionbedrock.aerialhell.Entity.Util.PlaySoundHelper;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public abstract class ToolEffectInfo
{
    private final List<RandomMobEffectInstance> mobEffectInstanceList;
    private final Predicate<LivingEntity> condition;
    private final int cooldown;
    @Nullable private final PlaySoundHelper soundInfo;
    @Nullable private final SimpleParticleType particleType;

    abstract boolean isTickEffect();
    abstract boolean isUseEffect();
    abstract boolean isHurtEnemyEffect();

    private ToolEffectInfo(List<MobEffectInstance> mobEffectInstanceList, Predicate<LivingEntity> condition, int cooldown, @Nullable PlaySoundHelper playSoundHelper, @Nullable SimpleParticleType particleType)
    {
        this(mobEffectInstanceList.stream().map(effect -> (RandomMobEffectInstance) (rand -> effect)).toList(), condition, cooldown, playSoundHelper, particleType);
    }

    private ToolEffectInfo(Collection<RandomMobEffectInstance> randomMobEffectInstanceList, Predicate<LivingEntity> condition, int cooldown, @Nullable PlaySoundHelper playSoundHelper, @Nullable SimpleParticleType particleType)
    {
        this.mobEffectInstanceList = List.copyOf(randomMobEffectInstanceList);
        this.condition = condition;
        this.cooldown = cooldown;
        this.soundInfo = playSoundHelper;
        this.particleType = particleType;
    }

    public boolean tryApplyEffect(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
    {
        if (this.isTickEffect() && entity.tickCount % 40 == 0 && this.condition.test(entity))
        {
            this.applyEffect(entity, itemStack, equipmentSlot); return true;
        }

        if ((this.isUseEffect() || this.isHurtEnemyEffect()) && this.condition.test(entity))
        {
            this.applyEffect(entity, itemStack, equipmentSlot); return true;
        }

        return false;
    }

    private void applyEffect(LivingEntity entity, ItemStack itemStack, @Nullable EquipmentSlot equipmentSlot)
    {
        if (!entity.level().isClientSide()) {for (RandomMobEffectInstance randomMobEffectInstance : mobEffectInstanceList)
        {
            MobEffectInstance mobEffectInstanceTemplate = randomMobEffectInstance.get(entity.getRandom());
            //when added to an entity, the mobEffectInstance is modified internally. Need to create a copy.
            entity.addEffect(new MobEffectInstance(mobEffectInstanceTemplate));
        }}

        if (this.soundInfo != null) {this.soundInfo.playSound(entity);}
        if (this.particleType != null) {EntityHelper.addParticlesOnEntity(20, this.particleType, entity);}
        if (this.isUseEffect() && equipmentSlot != null && entity instanceof Player player)
        {
            if (this.cooldown != 0) {player.getCooldowns().addCooldown(itemStack, this.cooldown);}
            itemStack.hurtAndBreak(1, player, equipmentSlot);
        }
    }

    public static class Tick extends ToolEffectInfo
    {
        private Tick(List<RandomMobEffectInstance> randomMobEffectInstanceList, Predicate<LivingEntity> condition, @Nullable SimpleParticleType particleType)
        {
            super(randomMobEffectInstanceList, condition, 0, null, particleType);
        }

        public static Tick fixed(List<MobEffectInstance> mobEffectInstanceList, Predicate<LivingEntity> condition, @Nullable SimpleParticleType particleType)
        {
            return new Tick(mobEffectInstanceList.stream().map(effect -> (RandomMobEffectInstance) (rand -> effect)).toList(), condition, particleType);
        }

        public static Tick random(List<RandomMobEffectInstance> randomMobEffectInstanceList, Predicate<LivingEntity> condition, @Nullable SimpleParticleType particleType)
        {
            return new Tick(randomMobEffectInstanceList, condition, particleType);
        }

        @Override boolean isTickEffect() {return true;}
        @Override boolean isUseEffect() {return false;}
        @Override boolean isHurtEnemyEffect() {return false;}
    }

    public static class Use extends ToolEffectInfo
    {
        private Use(List<RandomMobEffectInstance> randomMobEffectInstanceList, Predicate<LivingEntity> condition, int cooldown, @Nullable PlaySoundHelper playSoundHelper, @Nullable SimpleParticleType particleType)
        {
            super(randomMobEffectInstanceList, condition, cooldown, playSoundHelper, particleType);
        }

        public static Use fixed(MobEffectInstance mobEffectInstance, Predicate<LivingEntity> condition, int cooldown) {return fixed(List.of(mobEffectInstance), condition, cooldown, null, null);}
        public static Use fixed(MobEffectInstance mobEffectInstance, Predicate<LivingEntity> condition, int cooldown, @Nullable SimpleParticleType particleType) {return fixed(List.of(mobEffectInstance), condition, cooldown, null, particleType);}
        public static Use fixed(MobEffectInstance mobEffectInstance, Predicate<LivingEntity> condition, int cooldown, @Nullable PlaySoundHelper playSoundHelper) {return fixed(List.of(mobEffectInstance), condition, cooldown, playSoundHelper, null);}
        public static Use fixed(MobEffectInstance mobEffectInstance, Predicate<LivingEntity> condition, int cooldown, @Nullable PlaySoundHelper playSoundHelper, @Nullable SimpleParticleType particleType) {return fixed(List.of(mobEffectInstance), condition, cooldown, playSoundHelper, particleType);}
        public static Use fixed(List<MobEffectInstance> mobEffectInstanceList, Predicate<LivingEntity> condition, int cooldown, @Nullable PlaySoundHelper playSoundHelper, @Nullable SimpleParticleType particleType)
        {
            return new Use(mobEffectInstanceList.stream().map(effect -> (RandomMobEffectInstance) (rand -> effect)).toList(), condition, cooldown, playSoundHelper, particleType);
        }

        public Use random(List<RandomMobEffectInstance> randomMobEffectInstanceList, Predicate<LivingEntity> condition, int cooldown, @Nullable PlaySoundHelper playSoundHelper, @Nullable SimpleParticleType particleType)
        {
            return new Use(randomMobEffectInstanceList, condition, cooldown, playSoundHelper, particleType);
        }

        @Override boolean isTickEffect() {return false;}
        @Override boolean isUseEffect() {return true;}
        @Override boolean isHurtEnemyEffect() {return false;}
    }

    public static class HurtEnemy extends ToolEffectInfo
    {
        private HurtEnemy(List<RandomMobEffectInstance> randomMobEffectInstanceList, Predicate<LivingEntity> condition, @Nullable SimpleParticleType particleType)
        {
            super(randomMobEffectInstanceList, condition, 0, null, particleType);
        }

        public static HurtEnemy fixed(List<MobEffectInstance> mobEffectInstanceList, Predicate<LivingEntity> condition, @Nullable SimpleParticleType particleType)
        {
            return new HurtEnemy(mobEffectInstanceList.stream().map(effect -> (RandomMobEffectInstance) (rand -> effect)).toList(), condition, particleType);
        }

        public static HurtEnemy random(List<RandomMobEffectInstance> randomMobEffectInstanceList, Predicate<LivingEntity> condition, @Nullable SimpleParticleType particleType)
        {
            return new HurtEnemy(randomMobEffectInstanceList, condition, particleType);
        }

        @Override boolean isTickEffect() {return false;}
        @Override boolean isUseEffect() {return false;}
        @Override boolean isHurtEnemyEffect() {return true;}
    }

    @FunctionalInterface public interface RandomMobEffectInstance {MobEffectInstance get(RandomSource rand);}
}
