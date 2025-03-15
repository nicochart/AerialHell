package fr.factionbedrock.aerialhell.Entity.Monster.Shadow;

import com.google.common.collect.ImmutableList;
import fr.factionbedrock.aerialhell.Entity.AI.FleeBlockGoal;
import fr.factionbedrock.aerialhell.Entity.AI.MisleadableNearestAttackableTargetGoal;
import fr.factionbedrock.aerialhell.Entity.Monster.AutomatonEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocks;
import fr.factionbedrock.aerialhell.Registry.AerialHellMobEffects;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

import java.util.List;

public class ShadowAutomatonEntity extends AutomatonEntity
{
    public ShadowAutomatonEntity(EntityType<? extends HostileEntity> type, World world) {super(type, world);}
    
    public static DefaultAttributeContainer.Builder registerAttributes()
    {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, 50.0D)
                .add(EntityAttributes.ARMOR, 3.0D)
                .add(EntityAttributes.ATTACK_DAMAGE, 9.0D)
                .add(EntityAttributes.MOVEMENT_SPEED, 0.23D);
    }

    @Override protected void initGoals()
    {
        List<Block> blocksToAvoid = ImmutableList.of(AerialHellBlocks.VOLUCITE_TORCH, AerialHellBlocks.VOLUCITE_WALL_TORCH);
        this.goalSelector.add(0, new FleeBlockGoal<>(this, blocksToAvoid, 1.0D, 1.2D));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.6D));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ShadowAutomatonNearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override public void tick()
    {
        super.tick();
        if (random.nextFloat() > 0.95) {EntityHelper.addBatParticle(this, this.random, 1);}
    }

    @Override public boolean tryAttack(ServerWorld serverWorld, Entity attackedEntity)
    {
        if (super.tryAttack(serverWorld, attackedEntity))
        {
            if (attackedEntity instanceof LivingEntity && !EntityHelper.isLivingEntityShadowImmune(((LivingEntity) attackedEntity)))
            {
                ((LivingEntity) attackedEntity).addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 40, 0));
                ((LivingEntity) attackedEntity).addStatusEffect(new StatusEffectInstance(AerialHellMobEffects.VULNERABILITY, 60, 0));
            }
            return true;
        }
        else {return false;}
    }

    protected static class ShadowAutomatonNearestAttackableTargetGoal<T extends LivingEntity> extends MisleadableNearestAttackableTargetGoal<T>
    {
        public ShadowAutomatonNearestAttackableTargetGoal(MobEntity entityIn, Class<T> targetClassIn, boolean checkSight) {super(entityIn, targetClassIn, checkSight);}
        @Override public boolean isPlayerMisleadingGoalOwner(PlayerEntity player)
        {
            return EntityHelper.isLivingEntityMisleadingShadow(player);
        }
    }

    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_SHADOW_AUTOMATON_AMBIENT;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_SHADOW_AUTOMATON_HURT;}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_SHADOW_AUTOMATON_DEATH;}
}