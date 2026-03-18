package fr.factionbedrock.aerialhell.Entity.Monster.Pirate;

import fr.factionbedrock.aerialhell.Entity.AI.ConditionalGoal;
import fr.factionbedrock.aerialhell.Entity.AI.GhostPirateWaterAvoidingRandomStrollGoal;
import fr.factionbedrock.aerialhell.Entity.GoalConditionEntity;
import fr.factionbedrock.aerialhell.Entity.Monster.MisleadableEntity;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class GhostSlimePirateEntity extends AbstractSlimePirateEntity implements MisleadableEntity, GoalConditionEntity.GoalSimpleConditionEntity
{
    public GhostSlimePirateEntity(EntityType<? extends GhostSlimePirateEntity> type, World world) {super(type, world);}

    /* ------- MisleadableEntity : Interface method implementation ------- */
    @Override public boolean isMisleadedBy(LivingEntity livingEntity)
    {
        return EntityHelper.isImmuneToGhostBlockCollision(livingEntity);
    }
    /* ------------------------------------------------------------------- */

    /* ------- MisleadableEntity : Superclass methods Overridden to delegate to interface ------- */
    @Override public boolean damage(ServerWorld serverWorld, DamageSource source, float amount)
    {
        return this.misleadableDamage(serverWorld, source, amount, super::damage);
    }
    /* ------------------------------------------------------------------------------------------ */

    /* ------- MisleadableEntity : Interface methods Overridden for specific behavior ------- */
    @Override public boolean canMisleaderDamage() {return false;}
    /* -------------------------------------------------------------------------------------- */

    /* ------- GoalSimpleConditionEntity : Interface method implementation ------- */
    @Override public PathAwareEntity getSelf() {return this;}

    @Override public boolean canUseGoalsAdditionalCondition() {return !EntityHelper.isImmuneToGhostBlockCollision(this.getTarget());}
    /* --------------------------------------------------------------------------- */

    @Override protected void registerBaseGoals()
    {
        this.targetSelector.add(1, new RevengeGoal(this));
        this.goalSelector.add(3, new GhostPirateWaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    @Override protected void registerSpecificGoals()
    {
        this.goalSelector.add(2, new ConditionalGoal(this, new MeleeAttackGoal(this, 1.25D, false)));
        this.goalSelector.add(4, new ConditionalGoal(this, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F)));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true, (potentialTarget, serverWorld) -> !this.isMisleadedBy(potentialTarget)));
    }

    @Override public EntityType<? extends AbstractSlimePirateEntity> getDieOffspringType() {return AerialHellEntities.GHOST_SLIME_PIRATE;}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getType() {return AerialHellEntities.GHOST_SLIME_PIRATE;}

    @Override protected ItemStack getRandomHandItem(EquipmentSlot hand, Random rand)
    {
        return rand.nextInt(2) == 0 ? new ItemStack(AerialHellItems.AZURITE_SWORD) : new ItemStack(AerialHellItems.AZURITE_AXE);
    }

    public static boolean canGhostSpawn(EntityType<? extends HostileEntity> type, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random randomIn)
    {
        return randomIn.nextInt(40) == 0 && canSpawnIgnoreLightLevel(type, world, reason, pos, randomIn);
    }
}
