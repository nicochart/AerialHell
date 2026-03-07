package fr.factionbedrock.aerialhell.Entity.Monster.Pirate;

import fr.factionbedrock.aerialhell.Entity.AI.AdditionalConditionLookAtPlayerGoal;
import fr.factionbedrock.aerialhell.Entity.AI.AdditionalConditionMeleeAttackGoal;
import fr.factionbedrock.aerialhell.Entity.AI.GhostPirateWaterAvoidingRandomStrollGoal;
import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.MisleadableEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class GhostSlimePirateEntity extends AbstractSlimePirateEntity implements MisleadableEntity, GoalConditionEntity.GoalSimpleConditionEntity
{
    public GhostSlimePirateEntity(EntityType<? extends GhostSlimePirateEntity> type, Level world) {super(type, world);}

    /* ------- MisleadableEntity : Interface method implementation ------- */
    @Override public boolean isMisleadedBy(LivingEntity livingEntity)
    {
        return EntityHelper.isImmuneToGhostBlockCollision(livingEntity);
    }
    /* ------------------------------------------------------------------- */

    /* ------- MisleadableEntity : Superclass methods Overridden to delegate to interface ------- */
    @Override public boolean hurtServer(ServerLevel serverLevel, DamageSource source, float amount)
    {
        return this.misleadableHurtServer(serverLevel, source, amount, super::hurtServer);
    }
    /* ------------------------------------------------------------------------------------------ */

    /* ------- MisleadableEntity : Interface methods Overridden for specific behavior ------- */
    @Override public boolean canMisleaderHurt() {return false;}
    /* -------------------------------------------------------------------------------------- */

    /* ------- GoalSimpleConditionEntity : Interface method implementation ------- */
    @Override public PathfinderMob getSelf() {return this;}

    @Override public boolean canUseGoalsAdditionalCondition() {return !EntityHelper.isImmuneToGhostBlockCollision(this.getTarget());}
    /* --------------------------------------------------------------------------- */

    @Override protected void registerBaseGoals()
    {
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new GhostPirateWaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override protected void registerSpecificGoals()
    {
        this.goalSelector.addGoal(2, new AdditionalConditionMeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(4, new AdditionalConditionLookAtPlayerGoal(this, Player.class, 8.0F));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true, (potentialTarget, serverLevel) -> !this.isMisleadedBy(potentialTarget)));
    }

    @Override public EntityType<? extends AbstractSlimePirateEntity> getDieOffspringType() {return AerialHellEntities.GHOST_SLIME_PIRATE.get();}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getType() {return AerialHellEntities.GHOST_SLIME_PIRATE.get();}

    @Override protected ItemStack getRandomHandItem(EquipmentSlot hand, RandomSource rand)
    {
        return rand.nextInt(2) == 0 ? new ItemStack(AerialHellItems.AZURITE_SWORD.get()) : new ItemStack(AerialHellItems.AZURITE_AXE.get());
    }

    public static boolean canGhostSpawn(EntityType<? extends Monster> type, ServerLevelAccessor worldIn, EntitySpawnReason reason, BlockPos pos, RandomSource randomIn)
    {
        return randomIn.nextInt(40) == 0 && checkAnyLightMonsterSpawnRules(type, worldIn, reason, pos, randomIn);
    }
}
