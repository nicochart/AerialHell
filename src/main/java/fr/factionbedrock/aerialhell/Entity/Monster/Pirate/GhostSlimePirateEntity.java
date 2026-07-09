package fr.factionbedrock.aerialhell.Entity.Monster.Pirate;

import fr.factionbedrock.aerialhell.Entity.AI.GhostGoals;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class GhostSlimePirateEntity extends AbstractSlimePirateEntity
{
    public GhostSlimePirateEntity(EntityType<? extends GhostSlimePirateEntity> type, Level world) {super(type, world);}

    @Override protected void registerBaseGoals()
    {
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new GhostGoals.GhostPirateWaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    @Override protected void registerSpecificGoals()
    {
        this.goalSelector.addGoal(2, new GhostGoals.GhostPirateMeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.addGoal(4, new GhostGoals.GhostPirateLookAtPlayerGoal(this, Player.class, 8.0F));
        this.targetSelector.addGoal(2, new GhostGoals.GhostPirateNearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override public EntityType<? extends AbstractSlimePirateEntity> getDieOffspringType() {return AerialHellEntities.GHOST_SLIME_PIRATE;}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getType() {return AerialHellEntities.GHOST_SLIME_PIRATE;}

    @Override public boolean hurt(DamageSource damageSource, float amount)
    {
        Entity sourceEntity = damageSource.getEntity();
        if (EntityHelper.isImmuneToGhostBlockCollision(sourceEntity) && !EntityHelper.isCreaOrSpecPlayer(sourceEntity)) {return false;}
        return super.hurt(damageSource, amount);
    }

    @Override protected ItemStack getRandomHandItem(EquipmentSlot hand, RandomSource rand)
    {
        return rand.nextInt(2) == 0 ? new ItemStack(AerialHellItems.AZURITE_SWORD) : new ItemStack(AerialHellItems.AZURITE_AXE);
    }

    public static boolean canGhostSpawn(EntityType<? extends Monster> type, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource randomIn)
    {
        return randomIn.nextInt(40) == 0 && checkAnyLightMonsterSpawnRules(type, world, reason, pos, randomIn);
    }
}
