package fr.factionbedrock.aerialhell.Entity.Monster;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class SlimePirateEntity extends Zombie
{
    public SlimePirateEntity(EntityType<? extends SlimePirateEntity> type, Level world) {super(type, world);}

    @Override protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder registerAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.23D)
                .add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0.0D)
        		.add(Attributes.FOLLOW_RANGE, 35.0D);
    }

    @Override protected boolean isSunSensitive() {return false;}
    
    @Override protected SoundEvent getAmbientSound(){return SoundEvents.SLIME_SQUISH;}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return SoundEvents.SLIME_HURT;}
    @Override protected SoundEvent getDeathSound() {return SoundEvents.SLIME_DEATH;}
    @Override protected void playStepSound(BlockPos pos, BlockState blockIn) {this.playSound(SoundEvents.SLIME_BLOCK_STEP, 0.15F, 0.5F);}
}
