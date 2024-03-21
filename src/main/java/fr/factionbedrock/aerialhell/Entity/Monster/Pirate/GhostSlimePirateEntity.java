package fr.factionbedrock.aerialhell.Entity.Monster.Pirate;

import fr.factionbedrock.aerialhell.Entity.AI.AdditionalConditionLookAtPlayerGoal;
import fr.factionbedrock.aerialhell.Entity.AI.AdditionalConditionMeleeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.MisleadableNearestAttackableTargetGoal;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GhostSlimePirateEntity extends AbstractSlimePirateEntity
{
    public GhostSlimePirateEntity(EntityType<? extends GhostSlimePirateEntity> type, Level world) {super(type, world);}

    @Override protected void registerBaseGoals()
    {
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override protected void registerSpecificGoals()
    {
        this.goalSelector.addGoal(2, new GhostPirateMeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(4, new GhostPirateLookAtPlayerGoal(this, Player.class, 8.0F));
        this.targetSelector.addGoal(2, new GhostPirateNearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override public boolean hurt(DamageSource damageSource, float amount)
    {
        Entity sourceEntity = damageSource.getEntity();
        if (EntityHelper.isImmuneToGhostBlockCollision(sourceEntity) && !EntityHelper.isCreaOrSpecPlayer(sourceEntity)) {return false;}
        return super.hurt(damageSource, amount);
    }

    @Override protected ItemStack getRandomWeapon(RandomSource rand)
    {
        return rand.nextInt(2) == 0 ? new ItemStack(AerialHellBlocksAndItems.AZURITE_SWORD.get()) : new ItemStack(AerialHellBlocksAndItems.AZURITE_AXE.get());
    }

    protected static class GhostPirateNearestAttackableTargetGoal<T extends LivingEntity> extends MisleadableNearestAttackableTargetGoal<T>
    {
        public GhostPirateNearestAttackableTargetGoal(Mob entityIn, Class<T> targetClassIn, boolean checkSight) {super(entityIn, targetClassIn, checkSight);}
        @Override public boolean isPlayerMisleadingGoalOwner(Player player) {return EntityHelper.isImmuneToGhostBlockCollision(player);}
    }

    protected static class GhostPirateMeleeAttackGoal extends AdditionalConditionMeleeAttackGoal
    {
        public GhostPirateMeleeAttackGoal(PathfinderMob entityIn, double speedIn, boolean useLongMemory) {super(entityIn, speedIn, useLongMemory);}
        @Override public boolean additionalConditionMet() {return !EntityHelper.isImmuneToGhostBlockCollision(this.goalOwner.getTarget());}
    }

    public static class GhostPirateLookAtPlayerGoal extends AdditionalConditionLookAtPlayerGoal
    {
        public GhostPirateLookAtPlayerGoal(Mob entityIn, Class<? extends LivingEntity> watchTargetClass, float maxDistance) {super(entityIn, watchTargetClass, maxDistance);}
        @Override public boolean additionalConditionMet() {return !EntityHelper.isImmuneToGhostBlockCollision(this.goalOwner.getTarget());}
    }
}
