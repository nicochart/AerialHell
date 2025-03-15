package fr.factionbedrock.aerialhell.Entity.Monster.Pirate;

import fr.factionbedrock.aerialhell.Entity.AI.GhostGoals;
import fr.factionbedrock.aerialhell.Registry.AerialHellItems;
import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import fr.factionbedrock.aerialhell.Util.EntityHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class GhostSlimePirateEntity extends AbstractSlimePirateEntity
{
    public GhostSlimePirateEntity(EntityType<? extends GhostSlimePirateEntity> type, World world) {super(type, world);}

    @Override protected void registerBaseGoals()
    {
        this.targetSelector.add(1, new RevengeGoal(this));
        this.goalSelector.add(3, new GhostGoals.GhostPirateWaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    @Override protected void registerSpecificGoals()
    {
        this.goalSelector.add(2, new GhostGoals.GhostPirateMeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.add(4, new GhostGoals.GhostPirateLookAtPlayerGoal(this, PlayerEntity.class, 8.0F));
        this.targetSelector.add(2, new GhostGoals.GhostPirateNearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override public EntityType<? extends AbstractSlimePirateEntity> getDieOffspringType() {return AerialHellEntities.GHOST_SLIME_PIRATE;}

    @Override public EntityType<? extends AbstractSlimePirateEntity> getType() {return AerialHellEntities.GHOST_SLIME_PIRATE;}

    @Override public boolean damage(ServerWorld serverWorld, DamageSource damageSource, float amount)
    {
        Entity sourceEntity = damageSource.getAttacker();
        if (EntityHelper.isImmuneToGhostBlockCollision(sourceEntity) && !EntityHelper.isCreaOrSpecPlayer(sourceEntity)) {return false;}
        return super.damage(serverWorld, damageSource, amount);
    }

    @Override protected ItemStack getRandomHandItem(EquipmentSlot hand, Random rand)
    {
        return rand.nextInt(2) == 0 ? new ItemStack(AerialHellItems.AZURITE_SWORD) : new ItemStack(AerialHellItems.AZURITE_AXE);
    }

    public static boolean canGhostSpawn(EntityType<? extends HostileEntity> type, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random randomIn)
    {
        return randomIn.nextInt(40) == 0 && canSpawnIgnoreLightLevel(type, world, reason, pos, randomIn);
    }
}
