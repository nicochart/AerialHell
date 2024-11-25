package fr.factionbedrock.aerialhell.Entity.Monster.Shadow;

import com.google.common.collect.ImmutableList;
import fr.factionbedrock.aerialhell.Entity.AI.FleeBlockGoal;
import fr.factionbedrock.aerialhell.Entity.AI.MisleadableNearestAttackableTargetGoal;
import fr.factionbedrock.aerialhell.Entity.Monster.AutomatonEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.Level;

import java.util.List;

public class ShadowAutomatonEntity extends AutomatonEntity
{
    public ShadowAutomatonEntity(EntityType<? extends Monster> type, Level world) {super(type, world);}
    
    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 50.0D)
                .add(Attributes.ARMOR, 3.0D)
                .add(Attributes.ATTACK_DAMAGE, 9.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D);
    }

    @Override protected void registerGoals()
    {
        List<Block> blocksToAvoid = ImmutableList.of(AerialHellBlocksAndItems.VOLUCITE_TORCH.get(), AerialHellBlocksAndItems.VOLUCITE_WALL_TORCH.get());
        this.goalSelector.addGoal(0, new FleeBlockGoal<>(this, blocksToAvoid, 1.0D, 1.2D));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new ShadowAutomatonNearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override public void tick()
    {
        super.tick();
        if (random.nextFloat() > 0.95) {EntityHelper.addBatParticle(this, this.random, 1);}
    }

    @Override public boolean doHurtTarget(Entity attackedEntity)
    {
        if (super.doHurtTarget(attackedEntity))
        {
            if (attackedEntity instanceof LivingEntity && !EntityHelper.isLivingEntityShadowImmune(((LivingEntity) attackedEntity)))
            {
                ((LivingEntity) attackedEntity).addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 40, 0));
                ((LivingEntity) attackedEntity).addEffect(new MobEffectInstance(AerialHellMobEffects.VULNERABILITY.getDelegate(), 60, 0));
            }
            return true;
        }
        else {return false;}
    }

    protected static class ShadowAutomatonNearestAttackableTargetGoal<T extends LivingEntity> extends MisleadableNearestAttackableTargetGoal<T>
    {
        public ShadowAutomatonNearestAttackableTargetGoal(Mob entityIn, Class<T> targetClassIn, boolean checkSight) {super(entityIn, targetClassIn, checkSight);}
        @Override public boolean isPlayerMisleadingGoalOwner(Player player)
        {
            return EntityHelper.isLivingEntityMisleadingShadow(player);
        }
    }

    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_SHADOW_AUTOMATON_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_SHADOW_AUTOMATON_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_SHADOW_AUTOMATON_DEATH.get();}
}