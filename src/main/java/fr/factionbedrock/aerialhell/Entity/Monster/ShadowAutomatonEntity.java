package fr.factionbedrock.aerialhell.Entity.Monster;

import com.google.common.collect.ImmutableList;
import fr.factionbedrock.aerialhell.Entity.AI.FleeBlockGoal;
import fr.factionbedrock.aerialhell.Entity.AI.MisleadableNearestAttackableTargetGoal;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Registry.AerialHellPotionEffects;
import fr.factionbedrock.aerialhell.Registry.AerialHellSoundEvents;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.List;

public class ShadowAutomatonEntity extends AutomatonEntity
{
    public ShadowAutomatonEntity(EntityType<? extends MonsterEntity> type, World world) {super(type, world);}
    
    public static AttributeModifierMap.MutableAttribute registerAttributes()
    {
        return MonsterEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 50.0D)
                .createMutableAttribute(Attributes.ARMOR, 3.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 9.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23D);
    }

    @Override protected void registerGoals()
    {
        List<Block> blocksToAvoid = ImmutableList.of(AerialHellBlocksAndItems.VOLUCITE_TORCH.get(), AerialHellBlocksAndItems.VOLUCITE_WALL_TORCH.get());
        this.goalSelector.addGoal(0, new FleeBlockGoal<>(this, blocksToAvoid, 1.0D, 1.2D));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new ShadowAutomatonNearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override public void tick()
    {
        super.tick();
        if (rand.nextFloat() > 0.95) {EntityHelper.addBatParticle(this, this.rand, 1);}
    }

    @Override public boolean attackEntityAsMob(Entity attackedEntity)
    {
        if (super.attackEntityAsMob(attackedEntity))
        {
            if (attackedEntity instanceof LivingEntity && !EntityHelper.isLivingEntityShadowImmune(((LivingEntity) attackedEntity)))
            {
                ((LivingEntity) attackedEntity).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 40, 0));
                ((LivingEntity) attackedEntity).addPotionEffect(new EffectInstance(AerialHellPotionEffects.VULNERABILITY.get(), 60, 0));
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

    @Override protected SoundEvent getAmbientSound() {return AerialHellSoundEvents.ENTITY_SHADOW_AUTOMATON_AMBIENT.get();}
    @Override protected SoundEvent getHurtSound(DamageSource damageSource) {return AerialHellSoundEvents.ENTITY_SHADOW_AUTOMATON_HURT.get();}
    @Override protected SoundEvent getDeathSound() {return AerialHellSoundEvents.ENTITY_SHADOW_AUTOMATON_DEATH.get();}
}