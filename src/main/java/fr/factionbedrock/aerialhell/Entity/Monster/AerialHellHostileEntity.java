package fr.factionbedrock.aerialhell.Entity.Monster;

import fr.factionbedrock.aerialhell.Entity.AbstractCaterpillarEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.mob.HostileEntity;

import fr.factionbedrock.aerialhell.Registry.Entities.AerialHellEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public abstract class AerialHellHostileEntity extends HostileEntity
{
    protected AerialHellHostileEntity(EntityType<? extends HostileEntity> type, World world) {super(type, world);}
    
    @Override
    protected void initGoals()
    {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.25D, false));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.6D));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static boolean canHostileEntitySpawn(EntityType<? extends AbstractCaterpillarEntity> type, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random)
    {
        if (type == AerialHellEntities.CRYSTAL_SPIDER)
        {
        	return random.nextInt(10) == 0 && canSpawnInDark(type, world, reason, pos, random);
        }
        else if (type == AerialHellEntities.EVIL_COW || type == AerialHellEntities.CORTINARIUS_COW)
        {
        	return random.nextInt(50) == 0 && canSpawnInDark(type, world, reason, pos, random);
        }
        else
        {
        	return random.nextInt(40) == 0 && canSpawnInDark(type, world, reason, pos, random);
        }
    }
}
